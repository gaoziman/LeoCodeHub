package org.leocoder.codehub.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.leocoder.codehub.common.model.domain.ArticleContent;

/**
 * @author : Leo
 * @date  2024-07-27 17:16
 * @version 1.0
 * @description :
 */

public interface ArticleContentMapper extends BaseMapper<ArticleContent> {



    /**
     * 根据文章id删除文章内容
     *
     * @param articleId 文章id
     * @return 删除的行数
     */
    default int deleteByArticleId (Long articleId){
        LambdaQueryWrapper<ArticleContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleContent::getArticleId, articleId);
        return this.delete(wrapper);
    }
}