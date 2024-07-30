package org.leocoder.codehub.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.leocoder.codehub.common.model.domain.Category;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-25 15:37
 * @description :
 */

public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 分页查询分类列表
     *
     * @param pageNum   页码
     * @param pageSize  每页条数
     * @param name      分类名称
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 分页对象
     */
    default Page<Category> selectPageList(Long pageNum, Long pageSize, String name, LocalDate startDate, LocalDate endDate) {
        // 分页对象(查询第几页、每页多少数据)
        Page<Category> page = new Page<>(pageNum, pageSize);

        // 构建查询条件
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        // 名称模糊查询
        wrapper.like(Category::getName, name)
                // 大于等于 startDate
                .ge(Objects.nonNull(startDate), Category::getCreateTime, startDate)
                // 小于等于 endDate
                .le(Objects.nonNull(endDate), Category::getCreateTime, endDate)
                .orderByDesc(Category::getCreateTime);

        return this.selectPage(page, wrapper);
    }
}