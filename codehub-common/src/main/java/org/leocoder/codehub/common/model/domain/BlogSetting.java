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
 * @date  2024-07-26 19:09
 * @version 1.0
 * @description : 博客设置表
 */

@ApiModel(description="博客设置表")
@Data
@Builder
@TableName(value = "t_blog_settings")
public class BlogSetting {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="id")
    private Long id;

    @TableField(value = "logo")
    @ApiModelProperty(value="博客Logo")
    private String logo;


    @TableField(value = "`name`")
    @ApiModelProperty(value="博客名称")
    private String name;


    @TableField(value = "author")
    @ApiModelProperty(value="作者名")
    private String author;


    @TableField(value = "introduction")
    @ApiModelProperty(value="介绍语")
    private String introduction;


    @TableField(value = "avatar")
    @ApiModelProperty(value="作者头像")
    private String avatar;


    @TableField(value = "github_homepage")
    @ApiModelProperty(value="GitHub 主页访问地址")
    private String githubHomepage;


    @TableField(value = "csdn_homepage")
    @ApiModelProperty(value="CSDN 主页访问地址")
    private String csdnHomepage;


    @TableField(value = "gitee_homepage")
    @ApiModelProperty(value="Gitee 主页访问地址")
    private String giteeHomepage;


    @TableField(value = "zhihu_homepage")
    @ApiModelProperty(value="知乎主页访问地址")
    private String zhihuHomepage;
}