package org.leocoder.codehub.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.leocoder.codehub.common.model.domain.ArticleCategoryRel;

import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 17:15
 * @description :
 */

public interface ArticleCategoryRelMapper extends BaseMapper<ArticleCategoryRel> {


    /**
     * 根据文章id删除文章分类关系
     *
     * @param articleId 文章id
     * @return 删除的行数
     */
    default int deleteByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleCategoryRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleCategoryRel::getArticleId, articleId);
        return this.delete(wrapper);
    }

    /**
     * 根据文章 ID 查询
     *
     * @param articleId 文章 ID
     * @return 文章分类关系
     */
    default ArticleCategoryRel selectByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleCategoryRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleCategoryRel::getArticleId, articleId);
        return this.selectOne(wrapper);
    }

    /**
     * 根据分类ID 查询
     *
     * @param categoryId 分类ID
     * @return 文章分类关系
     */
    default ArticleCategoryRel selectOneByCategoryId(Long categoryId) {
        LambdaQueryWrapper<ArticleCategoryRel> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(ArticleCategoryRel::getCategoryId, categoryId)
                .last("LIMIT 1");
        return this.selectOne(wrapper);
    }


    /**
     * 根据分类ID 查询 文章分类关系列表
     *
     * @param categoryId 分类ID
     * @return 文章分类关系列表
     */
    default List<ArticleCategoryRel> selectListByCategoryId(Long categoryId) {
        LambdaQueryWrapper<ArticleCategoryRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleCategoryRel::getCategoryId, categoryId);
        return this.selectList(wrapper);
    }
}