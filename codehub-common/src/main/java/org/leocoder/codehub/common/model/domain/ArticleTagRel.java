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
 * @description : 文章对应标签关联表
 */


@ApiModel(description = "文章对应标签关联表")
@Data
@Builder
@TableName(value = "t_article_tag_rel")
public class ArticleTagRel {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;


    @TableField(value = "article_id")
    @ApiModelProperty(value = "文章id")
    private Long articleId;


    @TableField(value = "tag_id")
    @ApiModelProperty(value = "标签id")
    private Long tagId;
}