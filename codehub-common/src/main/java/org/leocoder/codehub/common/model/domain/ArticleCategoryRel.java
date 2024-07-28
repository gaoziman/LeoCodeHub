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
 * @date 2024-07-27 17:15
 * @description : 文章所属分类关联表
 */


@ApiModel(description = "文章所属分类关联表")
@Data
@Builder
@TableName(value = "t_article_category_rel")
public class ArticleCategoryRel {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;


    @TableField(value = "article_id")
    @ApiModelProperty(value = "文章id")
    private Long articleId;


    @TableField(value = "category_id")
    @ApiModelProperty(value = "分类id")
    private Long categoryId;
}