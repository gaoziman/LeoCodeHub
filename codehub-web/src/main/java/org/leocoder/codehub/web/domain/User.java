package org.leocoder.codehub.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-17 22:09
 * @description : 测试实体类
 */
@Data
@ApiModel(description = "用户实体类")
public class User {

    @ApiModelProperty(value = "用户id", required = true)
    private String id;

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "性别", required = true)
    @NotNull(message = "性别不能为空")
    private Integer sex;


    @ApiModelProperty(value = "年龄", required = true)
    @NotNull(message = "年龄不能为空")
    @Min(value = 18, message = "年龄必须大于或等于 18")      // 注解确保年龄大于等于 18
    @Max(value = 100, message = "年龄必须小于或等于 100")   // 注解确保年龄小于或等于 100
    private Integer age;


    @ApiModelProperty(value = "邮箱", required = true)
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;


    // 创建时间
    private LocalDateTime createTime;
    // 更新日期
    private LocalDate updateDate;
    // 时间
    private LocalTime time;
}
