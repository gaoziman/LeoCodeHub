package org.leocoder.codehub.admin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-24 14:57
 * @description : 更新管理员用户密码请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "修改用户密码 VO")
public class UpdateAdminUserPasswordReqVO {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
}
