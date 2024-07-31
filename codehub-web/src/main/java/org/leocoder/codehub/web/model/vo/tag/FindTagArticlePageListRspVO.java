package org.leocoder.codehub.web.model.vo.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 23:36
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagArticlePageListRspVO {
    private Long id;
    private String cover;
    private String title;
    /**
     * 发布日期
     */
    private LocalDate createDate;

}
