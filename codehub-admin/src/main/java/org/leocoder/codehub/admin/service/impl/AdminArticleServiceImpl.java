package org.leocoder.codehub.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.admin.convert.ArticleContentConvert;
import org.leocoder.codehub.admin.convert.ArticleConvert;
import org.leocoder.codehub.admin.convert.ArticleDetailConvert;
import org.leocoder.codehub.admin.model.vo.article.DeleteArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.PublishArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.req.FindArticleDetailReqVO;
import org.leocoder.codehub.admin.model.vo.article.req.FindArticlePageListReqVO;
import org.leocoder.codehub.admin.model.vo.article.req.UpdateArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.resp.FindArticleDetailRspVO;
import org.leocoder.codehub.admin.model.vo.article.resp.FindArticlePageListRspVO;
import org.leocoder.codehub.admin.service.AdminArticleService;
import org.leocoder.codehub.common.enums.HttpStatusEnum;
import org.leocoder.codehub.common.exception.BizException;
import org.leocoder.codehub.common.mapper.*;
import org.leocoder.codehub.common.model.domain.*;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 17:14
 * @description :
 */

@Service
@Slf4j
public class AdminArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements AdminArticleService {


    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;

    /**
     * 文章发布
     *
     * @param publishArticleReqVO 文章发布请求参数
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result publishArticle(PublishArticleReqVO publishArticleReqVO) {
        try {
            // 1. VO 转 Article, 并保存
            Article article = ArticleConvert.INSTANCE.convertVOToEntity(publishArticleReqVO);
            baseMapper.insert(article);

            // 拿到插入记录的主键 ID
            Long articleId = article.getId();

            // 2. VO 转 ArticleContent，并保存
            ArticleContent articleContent = ArticleContentConvert.INSTANCE.convertVOToEntity(publishArticleReqVO, articleId);
            articleContentMapper.insert(articleContent);

            // 3. 处理文章关联的分类
            Long categoryId = publishArticleReqVO.getCategoryId();

            // 3.1 校验提交的分类是否真实存在
            Category category = categoryMapper.selectById(categoryId);
            if (category == null) {
                return Result.fail(HttpStatusEnum.CATEGORY_NOT_EXISTED);
            }
            ArticleCategoryRel articleCategoryRel = ArticleCategoryRel.builder()
                    .articleId(articleId)
                    .categoryId(categoryId)
                    .build();

            // 3.2 保存文章分类关系
            articleCategoryRelMapper.insert(articleCategoryRel);


            // 4. 保存文章关联的标签集合
            List<String> publishTags = publishArticleReqVO.getTags();
            insertTags(articleId, publishTags);
            return Result.success();
        } catch (Exception e) {
            log.error("Error occurred during publishing article: ", e);
            // 重新抛出异常，以确保事务回滚
            throw e;
        }
    }


    /**
     * 文章删除
     *
     * @param deleteArticleReqVO 文章删除请求参数
     * @return Result
     */
    @Override
    public Result deleteArticle(DeleteArticleReqVO deleteArticleReqVO) {
        Long articleId = deleteArticleReqVO.getId();

        // 1. 删除文章
        articleMapper.deleteById(articleId);

        // 2. 删除文章内容
        articleContentMapper.deleteByArticleId(articleId);

        // 3. 删除文章-分类关联记录
        articleCategoryRelMapper.deleteByArticleId(articleId);

        // 4. 删除文章-标签关联记录
        articleTagRelMapper.deleteByArticleId(articleId);

        return Result.success();
    }


    /**
     * 查询文章分页数据
     *
     * @param findArticlePageListReqVO 查询文章分页请求参数
     * @return PageResponse
     */
    @Override
    public PageResponse findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO) {
        // 获取数据
        String title = findArticlePageListReqVO.getTitle();
        LocalDate startDate = findArticlePageListReqVO.getStartDate();
        LocalDate endDate = findArticlePageListReqVO.getEndDate();
        Long pageNum = findArticlePageListReqVO.getPageNum();
        Long pageSize = findArticlePageListReqVO.getPageSize();


        // 分页对象(查询第几页、每页多少数据)
        Page<Article> page = new Page<>(pageNum, pageSize);

        // 构造查询条件
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        // 标题模糊查询
        wrapper.like(StringUtils.isNotBlank(title), Article::getTitle, title)
                // 大于等于创建时间
                .ge(Objects.nonNull(startDate), Article::getCreateTime, startDate)
                // 小于等于创建时间
                .le(Objects.nonNull(endDate), Article::getCreateTime, endDate)
                .orderByDesc(Article::getCreateTime);

        // 查询数据
        Page<Article> articlePage = baseMapper.selectPage(page, wrapper);
        List<Article> articleList = articlePage.getRecords();

        // 转换对象
        List<FindArticlePageListRspVO> articlePageListRspVOS = ArticleConvert.INSTANCE.convertentityToRspVO(articleList);
        // 转换分页对象
        return PageResponse.success(articlePage, articlePageListRspVOS);
    }


    /**
     * 查询文章详情 包括文章内容、评论、点赞、收藏等信息
     *
     * @param findArticlePageListReqVO 查询文章详情请求参数
     * @return Result
     */
    @Override
    public Result<FindArticleDetailRspVO> findArticleDetail(FindArticleDetailReqVO findArticlePageListReqVO) {
        // 获取文章 ID
        Long articleId = findArticlePageListReqVO.getId();

        // 判断文章是否存在
        Article article = articleMapper.selectById(articleId);
        if (Objects.isNull(article)) {
            log.warn("==> 查询的文章不存在，articleId: {}", articleId);
            throw new BizException(HttpStatusEnum.ARTICLE_NOT_FOUND);
        }

        ArticleContent articleContent = articleContentMapper.selectByArticleId(articleId);
        ArticleCategoryRel articleCategoryRel = articleCategoryRelMapper.selectByArticleId(articleId);
        List<ArticleTagRel> articleTagRelList = articleTagRelMapper.selectByArticleId(articleId);

        // 获取对应标签 ID 集合
        List<Long> tagIds = articleTagRelList.stream().map(ArticleTagRel::getTagId).collect(Collectors.toList());

        // 转换对象
        FindArticleDetailRspVO findArticleDetailRspVO = ArticleDetailConvert.INSTANCE.convertEntityToVO(article);
        findArticleDetailRspVO.setContent(articleContent.getContent());
        findArticleDetailRspVO.setCategoryId(articleCategoryRel.getCategoryId());
        findArticleDetailRspVO.setTagIds(tagIds);
        return Result.success(findArticleDetailRspVO);
    }


    /**
     * 更新文章 包括文章内容、标题、分类、标签等信息
     *
     * @param updateArticleReqVO 更新文章请求参数
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateArticle(UpdateArticleReqVO updateArticleReqVO) {
        try {
            Long articleId = updateArticleReqVO.getId();
            // 1. VO 转 Article
            Article article = Article.builder()
                    .id(updateArticleReqVO.getId())
                    .title(updateArticleReqVO.getTitle())
                    .cover(updateArticleReqVO.getCover())
                    .summary(updateArticleReqVO.getSummary())
                    .updateTime(new Date())
                    .build();


            // 根据是否更新成功来判断文章是否存在
            int count = articleMapper.updateById(article);
            if (count == 0) {
                log.warn("==> 更新的文章不存在，articleId: {}", updateArticleReqVO.getId());
                throw new BizException(HttpStatusEnum.ARTICLE_NOT_FOUND);
            }

            // 2. VO 转 ArticleContent，并更新
            ArticleContent articleContent = ArticleContent.builder()
                    .articleId(articleId)
                    .content(updateArticleReqVO.getContent())
                    .build();
            articleContentMapper.updateByArticleId(articleContent);

            // 3. 更新文章分类
            Long categoryId = updateArticleReqVO.getCategoryId();
            // 3.1 校验提交的分类是否真实存在
            Category category = categoryMapper.selectById(categoryId);
            if (category == null) {
                log.warn("==> 分类不存在, categoryId: {}", categoryId);
                throw new BizException(HttpStatusEnum.CATEGORY_NAME_NOT_EXISTED);
            }
            // 先删除该文章关联的分类记录，再插入新的关联关系
            articleCategoryRelMapper.deleteByArticleId(articleId);
            ArticleCategoryRel  articleCategoryRel = ArticleCategoryRel.builder()
                    .articleId(articleId)
                    .categoryId(categoryId)
                    .build();
            articleCategoryRelMapper.insert(articleCategoryRel);

            // 4. 更新文章标签
            // 先删除该文章对应的标签
            articleTagRelMapper.deleteByArticleId(articleId);
            List<String> tags = updateArticleReqVO.getTags();
            insertTags(articleId, tags);
            return Result.success();
        } catch (Exception e) {
            log.error("Error occurred during publishing article: ", e);
            // 重新抛出异常，以确保事务回滚
            throw e;
        }
    }


    /**
     * 插入标签集合
     *
     * @param publishTags 标签集合
     */
    public void insertTags(Long articleId, List<String> publishTags) {
        // 筛选提交的标签（表中不存在的标签）
        List<String> notExistTags = new ArrayList<>();
        // 筛选提交的标签（表中已存在的标签）
        List<String> existedTags = new ArrayList<>();

        // 查询出所有标签
        List<Tag> tagList = tagMapper.selectList(null);

        // 如果表中还没有添加任何标签
        if (tagList == null || tagList.isEmpty()) {
            // 将提交的上来的所有标签，都作为新增标签入库
            notExistTags = publishTags;
        } else {
            /*// 创建标签名称到标签 ID 的映射（大小写不敏感）
            Map<String, Long> tagNameIdMap = tagList.stream()
                    .collect(Collectors.toMap(tag -> tag.getName().toLowerCase(), Tag::getId));
            for (String publishTag : publishTags) {
                String lowerCaseTag = publishTag.toLowerCase();
                // 如果标签已存在，则添加到 existedTags，并获取其 ID
                if (tagNameIdMap.containsKey(lowerCaseTag)) {
                    existedTags.add(String.valueOf(tagNameIdMap.get(lowerCaseTag)));
                } else {
                    // 否则，添加到 notExistTags
                    notExistTags.add(publishTag);
                }
            }*/

            List<String> tagIds = tagList.stream().map(tag -> String.valueOf(tag.getId())).collect(Collectors.toList());
            // 表中已添加相关标签，则需要筛选
            // 通过标签 ID 来筛选，包含对应 ID 则表示提交的标签是表中存在的
            existedTags = publishTags.stream().filter(tagIds::contains).collect(Collectors.toList());
            // 否则则是不存在的
            notExistTags = publishTags.stream().filter(publishTag -> !tagIds.contains(publishTag)).collect(Collectors.toList());

            // 补充逻辑：
            // 还有一种可能：按字符串名称提交上来的标签，也有可能是表中已存在的，比如表中已经有了 Java 标签，用户提交了个 java 小写的标签，需要内部装换为 Java 标签
            Map<String, Long> tagNameIdMap = tagList.stream().collect(Collectors.toMap(tag -> tag.getName().toLowerCase(), Tag::getId));
            // 使用迭代器进行安全的删除操作
            Iterator<String> iterator = notExistTags.iterator();
            while (iterator.hasNext()) {
                String notExistTag = iterator.next();
                // 转小写, 若 Map 中相同的 key，则表示该新标签是重复标签
                if (tagNameIdMap.containsKey(notExistTag.toLowerCase())) {
                    // 从不存在的标签集合中清除
                    iterator.remove();
                    // 并将对应的 ID 添加到已存在的标签集合
                    existedTags.add(String.valueOf(tagNameIdMap.get(notExistTag.toLowerCase())));
                }
            }
        }

        // 将提交的上来的，已存在于表中的标签，文章-标签关联关系入库
        if (!CollectionUtils.isEmpty(existedTags)) {
            List<ArticleTagRel> articleTagRels = new ArrayList<>();
            existedTags.forEach(tagId -> {
                ArticleTagRel articleTagRel = ArticleTagRel.builder()
                        .articleId(articleId)
                        .tagId(Long.valueOf(tagId))
                        .build();
                articleTagRels.add(articleTagRel);
            });
            // 批量插入
            articleTagRelMapper.insertBatchSomeColumn(articleTagRels);
        }

        // 将提交的上来的，不存在于表中的标签，文章-标签关联关系入库
        List<ArticleTagRel> articleTagRelList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(notExistTags)) {
            notExistTags.forEach(tagName -> {
                Tag tag = Tag.builder()
                        .name(tagName)
                        .createTime(new Date())
                        .updateTime(new Date())
                        .build();
                tagMapper.insert(tag);

                // 获取tagId
                Long tagId = tag.getId();
                // 文章-标签关联关系
                ArticleTagRel articleTagRelDO = ArticleTagRel.builder()
                        .articleId(articleId)
                        .tagId(tagId)
                        .build();
                articleTagRelList.add(articleTagRelDO);
            });
            // 批量插入
            articleTagRelMapper.insertBatchSomeColumn(articleTagRelList);
        }

    }
}
