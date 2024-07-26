package org.leocoder.codehub.admin.model.vo.category.req;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.leocoder.codehub.common.model.BasePageQuery;

import java.time.LocalDate;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-25 16:36
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询分类分页数据入参 VO")
public class FindCategoryPageListReqVO extends BasePageQuery {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 创建的起始日期
     */
    private LocalDate startDate;

    /**
     * 创建的结束日期
     */
    private LocalDate endDate;

}
