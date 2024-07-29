package org.leocoder.codehub.admin.model.vo.tag.req;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-29 10:31
 * @description : 标签模糊搜索入参 VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "标签模糊搜索入参 VO")
public class SearchBlurTagReqVO {

    @NotBlank(message = "标签关键字不能为空")
    private String key;
}
