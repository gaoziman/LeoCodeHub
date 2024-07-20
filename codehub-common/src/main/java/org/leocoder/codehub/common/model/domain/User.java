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
 * @version 1.0
 * @date 2024-07-20 12:15
 * @description : 用户实体类
 */

@ApiModel(description = "用户实体类")
@Data
@TableName(value = "t_user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;


    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;


    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;


    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @TableField(value = "is_deleted")
    @ApiModelProperty(value = "逻辑删除：0：未删除 1：已删除")
    private Byte isDeleted;
}