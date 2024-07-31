package org.leocoder.codehub.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.leocoder.codehub.common.model.domain.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 01:55
 * @description :
 */
public interface TagMapper extends BaseMapper<Tag> {


    /**
     * 分页查询标签列表
     *
     * @param pageNum   页码
     * @param pageSize  每页条数
     * @param name      标签名称
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 分页对象
     */
    default Page<Tag> selectPageList(Long pageNum, Long pageSize, String name, LocalDate startDate, LocalDate endDate) {
        // 分页对象(查询第几页、每页多少数据)
        Page<Tag> page = new Page<>(pageNum, pageSize);

        // 构建查询条件
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        // 名称模糊查询
        wrapper.like(Tag::getName, name)
                // 大于等于 startDate
                .ge(Objects.nonNull(startDate), Tag::getCreateTime, startDate)
                // 小于等于 endDate
                .le(Objects.nonNull(endDate), Tag::getCreateTime, endDate)
                .orderByDesc(Tag::getCreateTime);

        return this.selectPage(page, wrapper);
    }


    /**
     * 根据 id 列表查询标签列表
     *
     * @param tagIds id 列表
     * @return 标签列表
     */
    default List<Tag> selectByIds(List<Long> tagIds) {
        // 构建查询条件
        LambdaQueryWrapper<Tag> tagWrapper = new LambdaQueryWrapper<>();
        tagWrapper.in(Tag::getId, tagIds);
        return this.selectList(tagWrapper);
    }
}
