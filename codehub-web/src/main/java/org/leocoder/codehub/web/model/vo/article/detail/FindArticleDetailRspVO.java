package org.leocoder.codehub.web.model.vo.article.detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.leocoder.codehub.web.model.vo.tag.FindTagListRspVO;

import java.util.Date;
import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-31 09:25
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArticleDetailRspVO {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章正文（HTML）
     */
    private String content;
    /**
     * 发布时间
     */
    private Date createTime;
    /**
     * 分类 ID
     */
    private Long categoryId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 阅读量
     */
    private Integer readNum;
    /**
     * 标签集合
     */
    private List<FindTagListRspVO> tags;
    /**
     * 上一篇文章
     */
    private FindPreNextArticleRspVO preArticle;
    /**
     * 下一篇文章
     */
    private FindPreNextArticleRspVO nextArticle;
}