package org.leocoder.codehub.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.mapper.*;
import org.leocoder.codehub.common.model.domain.*;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.web.model.vo.FindIndexArticlePageListReqVO;
import org.leocoder.codehub.web.model.vo.article.FindIndexArticlePageListRspVO;
import org.leocoder.codehub.web.model.vo.category.FindCategoryListRspVO;
import org.leocoder.codehub.web.model.vo.tag.FindTagListRspVO;
import org.leocoder.codehub.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-29 17:14
 * @description :
 */

@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


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
     * 获取首页文章分页数据
     *
     * @param findIndexArticlePageListReqVO 分页查询条件
     * @return 分页数据
     */
    @Override
    public PageResponse findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO) {
        Long pageNum = findIndexArticlePageListReqVO.getPageNum();
        Long pageSize = findIndexArticlePageListReqVO.getPageSize();

        // 第一步：分页查询文章主体记录
        Page<Article> articlePage = articleMapper.selectPageList(pageNum, pageSize, null, null, null);

        // 返回的分页数据
        List<Article> articleList = articlePage.getRecords();

        if (articleList.isEmpty()) {
            return PageResponse.success(articlePage, Collections.emptyList());
        }

        List<FindIndexArticlePageListRspVO> vos = null;

        // 获取文章ID集合
        List<Long> articleIds = articleList.stream().map(Article::getId).collect(Collectors.toList());


        // 查询分类
        LambdaQueryWrapper<ArticleCategoryRel> acrWrapper = new LambdaQueryWrapper<>();
        acrWrapper.in(ArticleCategoryRel::getArticleId, articleIds);
        List<ArticleCategoryRel> articleCategoryRels = articleCategoryRelMapper.selectList(acrWrapper);

        // 获取分类ID集合
        List<Long> categoryIds = articleCategoryRels.stream().map(ArticleCategoryRel::getCategoryId).collect(Collectors.toList());
        LambdaQueryWrapper<Category> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper.in(Category::getId, categoryIds);
        List<Category> categoryList = categoryMapper.selectList(categoryWrapper);

        // 分类ID-分类映射
        Map<Long, Category> categoryMap = categoryList.stream().collect(Collectors.toMap(Category::getId, category -> category));

        // 查询标签
        LambdaQueryWrapper<ArticleTagRel> atrWrapper = new LambdaQueryWrapper<>();
        atrWrapper.in(ArticleTagRel::getArticleId, articleIds);
        List<ArticleTagRel> articleTagRels = articleTagRelMapper.selectList(atrWrapper);

        // 获取标签ID集合
        List<Long> tagIds = articleTagRels.stream().map(ArticleTagRel::getTagId).collect(Collectors.toList());
        LambdaQueryWrapper<Tag> tagWrapper = new LambdaQueryWrapper<>();
        tagWrapper.in(Tag::getId, tagIds);
        List<Tag> tagList = tagMapper.selectList(tagWrapper);

        // 标签ID-标签映射
        Map<Long, Tag> tagMap = tagList.stream().collect(Collectors.toMap(Tag::getId, tag -> tag));


        // 构建返回对象
        vos = articleList.stream().map(article -> {
            FindIndexArticlePageListRspVO vo = new FindIndexArticlePageListRspVO();
            // 设置文章基本信息
            vo.setId(article.getId());
            vo.setTitle(article.getTitle());
            vo.setSummary(article.getSummary());
            vo.setCover(article.getCover());
            vo.setCreateTime(article.getCreateTime());

            // 设置分类
            ArticleCategoryRel articleCategoryRel = articleCategoryRels.stream().filter(rel -> rel.getArticleId().equals(article.getId())).findFirst().orElse(null);
            if (articleCategoryRel != null) {
                Category category = categoryMap.get(articleCategoryRel.getCategoryId());
                if (category != null) {
                    FindCategoryListRspVO rspVO = new FindCategoryListRspVO();
                    rspVO.setId(category.getId());
                    rspVO.setName(category.getName());
                    vo.setCategory(rspVO);
                }
            }

            // 设置标签
            List<FindTagListRspVO> articleTags = articleTagRels.stream().filter(rel -> rel.getArticleId().equals(article.getId())).map(rel -> {
                Tag tag = tagMap.get(rel.getTagId());
                FindTagListRspVO rspVO = new FindTagListRspVO();
                rspVO.setId(tag.getId());
                rspVO.setName(tag.getName());
                return rspVO;
            }).collect(Collectors.toList());
            vo.setTags(articleTags);
            return vo;
        }).collect(Collectors.toList());

        return PageResponse.success(articlePage, vos);
    }
}
