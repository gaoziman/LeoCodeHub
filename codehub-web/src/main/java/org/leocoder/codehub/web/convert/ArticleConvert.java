package org.leocoder.codehub.web.convert;

import org.leocoder.codehub.common.model.domain.Article;
import org.leocoder.codehub.web.model.vo.archive.FindArchiveArticleRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 20:06
 * @description :
 */
@Mapper
public interface ArticleConvert {

    /**
     * 初始化 convert 实例
     */
    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);


    /**
     * 将 实体类 转化为 归档文章 VO
     *
     * @param article 实体类
     * @return 归档文章 VO
     */
    @Mapping(target = "createDate", expression = "java(ArticleConvert.convertToLocalDate(article.getCreateTime()))")
    @Mapping(target = "createMonth", expression = "java(ArticleConvert.convertToYearMonth(article.getCreateTime()))")
    FindArchiveArticleRspVO convertEntity2ArchiveArticleVO(Article article);

    /**
     * 将 Date 类型转换为 LocalDate
     */
    static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 将 Date 类型转换为 YearMonth
     */
    static YearMonth convertToYearMonth(Date date) {
        return YearMonth.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
