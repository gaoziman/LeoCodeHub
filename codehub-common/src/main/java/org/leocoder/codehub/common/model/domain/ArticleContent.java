package org.leocoder.codehub.common.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 17:16
 * @description : 文章详情表
 */


@ApiModel(description = "文章内容表")
@Data
@Builder
@TableName(value = "t_article_content")
public class ArticleContent {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "文章内容id")
    private Long id;

    @TableField(value = "article_id")
    @ApiModelProperty(value = "文章id")
    private Long articleId;


    @TableField(value = "content")
    @ApiModelProperty(value = "教程正文")
    private String content;
}