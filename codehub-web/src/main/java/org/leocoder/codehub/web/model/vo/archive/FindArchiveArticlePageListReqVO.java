package org.leocoder.codehub.web.model.vo.archive;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import org.leocoder.codehub.common.model.BasePageQuery;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 19:35
 * @description : 文章归档分页 VO
 */
@Data
@Builder
@ApiModel(value = "文章归档分页 VO")
public class FindArchiveArticlePageListReqVO extends BasePageQuery {
}
