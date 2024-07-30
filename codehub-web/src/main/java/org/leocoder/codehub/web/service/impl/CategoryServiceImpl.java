package org.leocoder.codehub.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.enums.HttpStatusEnum;
import org.leocoder.codehub.common.exception.BizException;
import org.leocoder.codehub.common.mapper.ArticleCategoryRelMapper;
import org.leocoder.codehub.common.mapper.ArticleMapper;
import org.leocoder.codehub.common.mapper.CategoryMapper;
import org.leocoder.codehub.common.model.domain.Article;
import org.leocoder.codehub.common.model.domain.ArticleCategoryRel;
import org.leocoder.codehub.common.model.domain.Category;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.convert.CategoryConvert;
import org.leocoder.codehub.web.model.vo.category.FindCategoryArticlePageListReqVO;
import org.leocoder.codehub.web.model.vo.category.FindCategoryArticlePageListRspVO;
import org.leocoder.codehub.web.model.vo.category.FindCategoryListRspVO;
import org.leocoder.codehub.web.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;


    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 获取分类列表
     *
     * @return Result
     */
    @Override
    public Result findCategoryList() {
        // 查询分类列表
        List<Category> categoryList = baseMapper.selectList(null);

        // 转换对象
        List<FindCategoryListRspVO> vos = null;
        if (categoryList!= null && !categoryList.isEmpty()) {
            vos = categoryList.stream().map(category ->
                 FindCategoryListRspVO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build()
            ).collect(Collectors.toList());
        }
        return Result.success(vos);
    }


    /**
     * 获取分类下文章分页数据
     *
     * @param findCategoryArticlePageListReqVO 分类下文章分页请求参数
     * @return Result
     */
    @Override
    public Result findCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO) {
        // 获取数据
        Long pageNum = findCategoryArticlePageListReqVO.getPageNum();
        Long pageSize = findCategoryArticlePageListReqVO.getPageSize();
        Long categoryId = findCategoryArticlePageListReqVO.getId();

        Category category = baseMapper.selectById(categoryId);

        // 判断该分类是否存在
        if (Objects.isNull(category)) {
            log.warn("==> 该分类不存在, categoryId: {}", categoryId);
            throw new BizException(HttpStatusEnum.CATEGORY_NOT_EXISTED);
        }

        // 通过分类id查询文章分类列表
        List<ArticleCategoryRel> articleCategoryRels = articleCategoryRelMapper.selectListByCategoryId(categoryId);

        // 该分类下面没有发布任何文章
        if (CollectionUtils.isEmpty(articleCategoryRels)) {
            log.info("==> 该分类下还未发布任何文章, categoryId: {}", categoryId);
            return PageResponse.success(200, null,null);
        }
        // 转换对象
        List<Long> articleIdList = articleCategoryRels.stream().map(ArticleCategoryRel::getArticleId).collect(Collectors.toList());

        // 调用文章服务获取文章分页数据
        Page<Article> articlePage = articleMapper.selectPageListByArticleIds(pageNum, pageSize, articleIdList);
        List<Article> articleList = articlePage.getRecords();

        // 转换对象
        List<FindCategoryArticlePageListRspVO> vos = null;
        if (articleList != null && !articleList.isEmpty()) {
            vos = articleList.stream().map(CategoryConvert.INSTANCE::convertEntity2VO).collect(Collectors.toList());
        }

        return PageResponse.success(articlePage,vos);
    }
}
