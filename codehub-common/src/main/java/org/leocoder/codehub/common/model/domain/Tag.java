package org.leocoder.codehub.common.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.mapstruct.Mapper;

import java.util.Date;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 01:54
 * @description : 标签实体类
 */
@Mapper
@ApiModel(description="标签实体类表")
@Data
@Builder
@TableName(value = "t_tag")
public class Tag {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="标签id")
    private Long id;


    @TableField(value = "`name`")
    @ApiModelProperty(value="标签名称")
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
