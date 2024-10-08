package org.leocoder.codehub.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.leocoder.codehub.common.config.InsertBatchMapper;
import org.leocoder.codehub.common.model.domain.ArticleTagRel;

import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 17:16
 * @description :
 */

public interface ArticleTagRelMapper extends InsertBatchMapper<ArticleTagRel> {


    /**
     * 根据文章id删除文章标签关系
     *
     * @param articleId 文章id
     * @return 删除的行数
     */
    default int deleteByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleTagRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTagRel::getArticleId, articleId);
        return this.delete(wrapper);
    }

    /**
     * 根据文章 ID 查询
     *
     * @param articleId 文章 ID
     * @return 文章标签关系
     */
    default List<ArticleTagRel> selectByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleTagRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTagRel::getArticleId, articleId);
        return this.selectList(wrapper);
    }


    /**
     * 根据标签 ID 查询
     * @param tagId 标签 ID
     * @return 文章标签关系
     */
    default ArticleTagRel selectOneByTagId(Long tagId) {
        LambdaQueryWrapper<ArticleTagRel> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(ArticleTagRel::getTagId, tagId)
                .last("LIMIT 1");
        return this.selectOne(wrapper);
    }

    /**
     * 根据标签ID 查询 文章标签关系列表
     *
     * @param tagId 标签ID
     * @return 文章分类关系列表
     */
    default List<ArticleTagRel> selectListByTagId(Long tagId) {
        LambdaQueryWrapper<ArticleTagRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTagRel::getTagId, tagId);
        return this.selectList(wrapper);
    }
}