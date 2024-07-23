package org.leocoder.codehub.common.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author : Leo
 * @date  2024-07-23 13:58
 * @version 1.0
 * @description : 用户角色表
 */

@ApiModel(description="用户角色表")
@Data
@TableName(value = "t_user_role")
public class UserRole {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="id")
    private Long id;

    @TableField(value = "username")
    @ApiModelProperty(value="用户名")
    private String username;


    @TableField(value = "`role`")
    @ApiModelProperty(value="角色")
    private String role;


    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;
}