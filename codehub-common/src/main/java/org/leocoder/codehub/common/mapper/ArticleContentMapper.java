package org.leocoder.codehub.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.leocoder.codehub.common.model.domain.ArticleContent;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 17:16
 * @description :
 */

public interface ArticleContentMapper extends BaseMapper<ArticleContent> {


    /**
     * 根据文章id删除文章内容
     *
     * @param articleId 文章id
     * @return 删除的行数
     */
    default int deleteByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleContent::getArticleId, articleId);
        return this.delete(wrapper);
    }

    /**
     * 根据文章 ID 查询
     *
     * @param articleId 文章 ID
     * @return 文章内容
     */
    default ArticleContent selectByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleContent::getArticleId, articleId);
        return this.selectOne(wrapper);
    }

    default int updateByArticleId(ArticleContent articleContent) {
        LambdaQueryWrapper<ArticleContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleContent::getArticleId, articleContent.getArticleId());
        return this.update(articleContent, wrapper);
    }
}