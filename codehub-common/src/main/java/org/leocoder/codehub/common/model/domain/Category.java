package org.leocoder.codehub.common.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author : Leo
 * @date  2024-07-25 15:37
 * @version 1.0
 * @description : 文章分类表
 */

@ApiModel(description="文章分类表")
@Data
@Builder
@TableName(value = "t_category")
public class Category {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="分类id")
    private Long id;


    @TableField(value = "`name`")
    @ApiModelProperty(value="分类名称")
    private String name;


    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;


    @TableField(value = "update_time")
    @ApiModelProperty(value="最后一次更新时间")
    private Date updateTime;


    @TableField(value = "is_deleted")
    @ApiModelProperty(value="逻辑删除标志位：0：未删除 1：已删除")
    private Integer isDeleted;
}