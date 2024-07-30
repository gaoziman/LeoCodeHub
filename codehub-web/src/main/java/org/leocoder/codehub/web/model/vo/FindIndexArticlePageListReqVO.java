package org.leocoder.codehub.web.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import org.leocoder.codehub.common.model.BasePageQuery;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-29 15:54
 * @description : 首页查询文章分页 VO
 */
@Data
@Builder
@ApiModel(value = "首页查询文章分页 VO")
public class FindIndexArticlePageListReqVO extends BasePageQuery {
}