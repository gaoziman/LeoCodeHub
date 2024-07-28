package org.leocoder.codehub.admin.convert;

import org.leocoder.codehub.admin.model.vo.article.PublishArticleReqVO;
import org.leocoder.codehub.common.model.domain.ArticleContent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 17:25
 * @description :
 */
@Mapper
public interface ArticleContentConvert {

    /**
     * 初始化 convert 实例
     */
    ArticleContentConvert INSTANCE = Mappers.getMapper(ArticleContentConvert.class);


    /**
     * 将 VO 转化为 实体类
     * @param publishArticleReqVO VO 对象
     * @return 实体类对象
     */
    ArticleContent convertVOToEntity(PublishArticleReqVO publishArticleReqVO,Long articleId);
}
