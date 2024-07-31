package org.leocoder.codehub.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.enums.HttpStatusEnum;
import org.leocoder.codehub.common.exception.BizException;
import org.leocoder.codehub.common.mapper.ArticleMapper;
import org.leocoder.codehub.common.mapper.ArticleTagRelMapper;
import org.leocoder.codehub.common.mapper.TagMapper;
import org.leocoder.codehub.common.model.domain.Article;
import org.leocoder.codehub.common.model.domain.ArticleTagRel;
import org.leocoder.codehub.common.model.domain.Tag;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.convert.TagConvert;
import org.leocoder.codehub.web.model.vo.tag.FindTagArticlePageListReqVO;
import org.leocoder.codehub.web.model.vo.tag.FindTagArticlePageListRspVO;
import org.leocoder.codehub.web.model.vo.tag.FindTagListRspVO;
import org.leocoder.codehub.web.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 08:55
 * @description :
 */
@Slf4j
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 获取标签列表
     *
     * @return Result
     */
    @Override
    public Result findCategoryList() {
        // 查询标签列表
        List<Tag> tagList = baseMapper.selectList(null);

        // 转换对象
        List<FindTagListRspVO> vos = null;
        if (tagList != null && !tagList.isEmpty()) {
            vos = tagList.stream().map(tag ->
                    FindTagListRspVO.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .build()
            ).collect(Collectors.toList());
        }
        return Result.success(vos);
    }


    /**
     * 获取标签下文章分页数据
     *
     * @param findTagArticlePageListReqVO 标签下文章分页请求参数
     * @return Result
     */
    @Override
    public Result findCategoryArticlePageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        // 获取数据
        Long pageNum = findTagArticlePageListReqVO.getPageNum();
        Long pageSize = findTagArticlePageListReqVO.getPageSize();
        Long tagId = findTagArticlePageListReqVO.getId();

        Tag tag = baseMapper.selectById(tagId);

        // 判断该标签是否存在
        if (Objects.isNull(tag)) {
            log.warn("==> 该标签不存在, tagId: {}", tagId);
            throw new BizException(HttpStatusEnum.TAG_NOT_EXISTED);
        }

        // 通过分类id查询文章标签列表
        List<ArticleTagRel> articleTagRelList = articleTagRelMapper.selectListByTagId(tagId);

        // 该分类下面没有发布任何文章
        if (CollectionUtils.isEmpty(articleTagRelList)) {
            log.info("==> 该标签下还未发布任何文章, tagId: {}", tagId);
            return PageResponse.success(200, null,null);
        }
        // 转换对象
        List<Long> tagIdList = articleTagRelList.stream().map(ArticleTagRel::getArticleId).collect(Collectors.toList());

        // 调用文章服务获取文章分页数据
        Page<Article> articlePage = articleMapper.selectPageListByArticleIds(pageNum, pageSize, tagIdList);
        List<Article> articleList = articlePage.getRecords();

        // 转换对象
        List<FindTagArticlePageListRspVO> vos = null;
        if (articleList != null && !articleList.isEmpty()) {
            vos = articleList.stream().map(TagConvert.INSTANCE::convertEntity2VO).collect(Collectors.toList());
        }

        return PageResponse.success(articlePage,vos);
    }
}
