package org.leocoder.codehub.web.model.vo.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.leocoder.codehub.web.model.vo.category.FindCategoryListRspVO;
import org.leocoder.codehub.web.model.vo.tag.FindTagListRspVO;

import java.util.Date;
import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-29 15:57
 * @description : 文章列表返回值对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindIndexArticlePageListRspVO {
    private Long id;
    private String cover;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String summary;
    /**
     * 文章分类
     */
    private FindCategoryListRspVO category;

    /**
     * 文章标签
     */
    private List<FindTagListRspVO> tags;

}
