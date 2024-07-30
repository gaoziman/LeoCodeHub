package org.leocoder.codehub.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.leocoder.codehub.common.model.domain.Article;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 17:14
 * @description :
 */

public interface ArticleMapper extends BaseMapper<Article> {


    /**
     * 分页查询文章列表
     *
     * @param pageNum   页码
     * @param pageSize  每页条数
     * @param title     文章标题
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 分页对象
     */
    default Page<Article> selectPageList(Long pageNum, Long pageSize, String title, LocalDate startDate, LocalDate endDate) {
        // 分页对象(查询第几页、每页多少数据)
        Page<Article> page = new Page<>(pageNum, pageSize);

        // 构建查询条件
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        // 名称模糊查询
        wrapper.like(Objects.nonNull(title),Article::getTitle, title)
                // 大于等于 startDate
                .ge(Objects.nonNull(startDate), Article::getCreateTime, startDate)
                // 小于等于 endDate
                .le(Objects.nonNull(endDate), Article::getCreateTime, endDate)
                .orderByDesc(Article::getCreateTime);

        return this.selectPage(page, wrapper);
    }
}