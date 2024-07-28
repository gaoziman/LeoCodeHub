package org.leocoder.codehub.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.leocoder.codehub.common.model.domain.ArticleCategoryRel;

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
}