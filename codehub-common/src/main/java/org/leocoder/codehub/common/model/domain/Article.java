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
 * @version 1.0
 * @date 2024-07-27 17:14
 * @description : 文章表
 */


@ApiModel(description = "文章表")
@Data
@Builder
@TableName(value = "t_article")
public class Article {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "文章id")
    private Long id;


    @TableField(value = "title")
    @ApiModelProperty(value = "文章标题")
    private String title;


    @TableField(value = "cover")
    @ApiModelProperty(value = "文章封面")
    private String cover;


    @TableField(value = "summary")
    @ApiModelProperty(value = "文章摘要")
    private String summary;


    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @TableField(value = "update_time")
    @ApiModelProperty(value = "最后一次更新时间")
    private Date updateTime;


    @TableField(value = "is_deleted")
    @ApiModelProperty(value = "删除标志位：0：未删除 1：已删除")
    private Byte isDeleted;


    @TableField(value = "read_num")
    @ApiModelProperty(value = "被阅读次数")
    private Integer readNum;
}