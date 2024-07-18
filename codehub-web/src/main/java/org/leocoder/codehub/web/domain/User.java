package org.leocoder.codehub.web.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-17 22:09
 * @description : 测试实体类
 */
@Data
public class User {
    private String id;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private Integer sex;

    @NotNull(message = "年龄不能为空")
    @Min(value = 18, message = "年龄必须大于或等于 18")      // 注解确保年龄大于等于 18
    @Max(value = 100, message = "年龄必须小于或等于 100")   // 注解确保年龄小于或等于 100
    private Integer age;

    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
}
