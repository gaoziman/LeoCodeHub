package org.leocoder.codehub.web.model.vo.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.leocoder.codehub.common.model.BasePageQuery;

import javax.validation.constraints.NotNull;

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
public class FindTagArticlePageListReqVO extends BasePageQuery {

    /**
     * 标签 ID
     */
    @NotNull(message = "标签 ID 不能为空")
    private Long id;

}
