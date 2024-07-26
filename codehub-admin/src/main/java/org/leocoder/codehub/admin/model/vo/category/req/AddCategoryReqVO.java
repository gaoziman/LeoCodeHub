package org.leocoder.codehub.admin.model.vo.category.req;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-25 15:45
 * @description :
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "添加分类 VO")
public class AddCategoryReqVO {

    @NotBlank(message = "分类名称不能为空")
    private String name;

}
