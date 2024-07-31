package org.leocoder.codehub.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.leocoder.codehub.common.model.domain.Article;

import java.time.LocalDate;
import java.util.List;
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
        wrapper.like(Objects.nonNull(title), Article::getTitle, title)
                // 大于等于 startDate
                .ge(Objects.nonNull(startDate), Article::getCreateTime, startDate)
                // 小于等于 endDate
                .le(Objects.nonNull(endDate), Article::getCreateTime, endDate)
                .orderByDesc(Article::getCreateTime);

        return this.selectPage(page, wrapper);
    }


    /**
     * 根据文章id列表查询文章列表
     *
     * @param pageNum    页码
     * @param pageSize   每页条数
     * @param articleIds 文章id列表
     * @return 分页对象
     */
    default Page<Article> selectPageListByArticleIds(Long pageNum, Long pageSize, List<Long> articleIds) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .in(Article::getId, articleIds)
                .orderByDesc(Article::getCreateTime);
        return this.selectPage(page, wrapper);
    }

    /**
     * 根据文章id查询上一篇文章
     *
     * @param articleId 文章id
     * @return 文章
     */
    default Article selectPreArticle(Long articleId) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        // id进行升序排列
        wrapper.orderByAsc(Article::getId)
                // 查询比当前文章 ID 大的
                .ge(Article::getId, articleId)
                // 第一条记录即为上一篇文章
                .last("limit 1");
        return this.selectOne(wrapper);
    }


    /**
     * 根据文章id查询下一篇文章
     *
     * @param articleId 文章id
     * @return 文章
     */
    default Article selectNextArticle(Long articleId) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        // id进行降序排列
        wrapper.orderByDesc(Article::getId)
                // 查询比当前文章 ID 小的
                .le(Article::getId, articleId)
                // 第一条记录即为下一篇文章
                .last("limit 1");
        return this.selectOne(wrapper);
    }


    /**
     * 阅读量+1
     *
     * @param articleId 文章id
     * @return 影响行数
     */
    default int increaseReadNum(Long articleId) {
        // 执行 SQL : UPDATE t_article SET read_num = read_num + 1 WHERE (id = XX)
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.setSql("read_num = read_num + 1")
                .eq(Article::getId, articleId);

        return this.update(null, updateWrapper);
    }
}