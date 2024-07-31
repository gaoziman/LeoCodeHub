package org.leocoder.codehub.web.convert;

import org.leocoder.codehub.common.model.domain.Article;
import org.leocoder.codehub.web.model.vo.tag.FindTagArticlePageListRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 22:14
 * @description :
 */
@Mapper
public interface TagConvert {

    /**
     * 初始化 convert 实例
     */
    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);

    /**
     * 将 实体类 转化为 VO
     * @param article  实体类
     * @return VO
     */
    FindTagArticlePageListRspVO convertEntity2VO(Article article);

}
