package org.leocoder.codehub.admin.convert;

import org.leocoder.codehub.admin.model.vo.article.resp.FindArticleDetailRspVO;
import org.leocoder.codehub.common.model.domain.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-28 23:02
 * @description : ArticleDetail转换器
 */

@Mapper
public interface ArticleDetailConvert {
    /**
     * 初始化 convert 实例
     */
    ArticleDetailConvert INSTANCE = Mappers.getMapper(ArticleDetailConvert.class);

    /**
     * 将 Article实体类 转化为 VO
     *
     * @param article Article实体类
     * @return FindArticleDetailRspVO
     */
    FindArticleDetailRspVO convertEntityToVO(Article article);

}