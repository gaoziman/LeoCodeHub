package org.leocoder.codehub.admin.convert;

import org.leocoder.codehub.admin.model.vo.article.PublishArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.resp.FindArticlePageListRspVO;
import org.leocoder.codehub.common.model.domain.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 17:25
 * @description :
 */
@Mapper
public interface ArticleConvert {

    /**
     * 初始化 convert 实例
     */
    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);


    /**
     * 将 VO 转化为 实体类
     * @param publishArticleReqVO VO 对象
     * @return 实体类对象
     */
    Article convertVOToEntity(PublishArticleReqVO publishArticleReqVO);


    /**
     * 将 实体类转换为出参对象
     * @param articleList 集合对象
     * @return 返回参数对象
     */
    List<FindArticlePageListRspVO> convertentityToRspVO(List<Article> articleList);
}
