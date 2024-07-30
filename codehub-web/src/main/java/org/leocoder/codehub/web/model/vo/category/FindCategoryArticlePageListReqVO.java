package org.leocoder.codehub.web.model.vo.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.leocoder.codehub.common.model.BasePageQuery;

import javax.validation.constraints.NotNull;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 21:38
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCategoryArticlePageListReqVO extends BasePageQuery {
    /**
     * 分类 ID
     */
    @NotNull(message = "分类 ID 不能为空")
    private Long id;

}
