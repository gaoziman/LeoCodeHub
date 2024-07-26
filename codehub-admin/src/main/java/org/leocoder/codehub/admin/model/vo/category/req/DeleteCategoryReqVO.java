package org.leocoder.codehub.admin.model.vo.category.req;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-25 16:21
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "修改分类 VO")
public class DeleteCategoryReqVO {

    @NotNull(message = "分类 ID 不能为空")
    private Long id;


}
