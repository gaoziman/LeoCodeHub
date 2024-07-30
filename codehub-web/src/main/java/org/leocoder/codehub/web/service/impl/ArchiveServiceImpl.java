package org.leocoder.codehub.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.leocoder.codehub.common.mapper.ArticleMapper;
import org.leocoder.codehub.common.model.domain.Article;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.web.convert.ArticleConvert;
import org.leocoder.codehub.web.model.vo.archive.FindArchiveArticlePageListReqVO;
import org.leocoder.codehub.web.model.vo.archive.FindArchiveArticlePageListRspVO;
import org.leocoder.codehub.web.model.vo.archive.FindArchiveArticleRspVO;
import org.leocoder.codehub.web.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 19:38
 * @description :
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 获取文章归档分页数据
     *
     * @param findArchiveArticlePageListReqVO 分页查询参数
     * @return 分页数据
     */
    @Override
    public PageResponse findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO) {
        // 获取数据
        Long pageNum = findArchiveArticlePageListReqVO.getPageNum();
        Long pageSize = findArchiveArticlePageListReqVO.getPageSize();

        Page<Article> articlePage = articleMapper.selectPageList(pageNum, pageSize, null, null, null);
        List<Article> articleList = articlePage.getRecords();


        // 组装数据
        List<FindArchiveArticlePageListRspVO> vos = Lists.newArrayList();
        if (articleList!= null && !articleList.isEmpty()) {
            // 转换数据
            List<FindArchiveArticleRspVO> archiveArticleRspVOS = articleList.stream()
                    .map(ArticleConvert.INSTANCE::convertEntity2ArchiveArticleVO)
                    .collect(Collectors.toList());

            // 按创建的月份进行分组
            Map<YearMonth, List<FindArchiveArticleRspVO>> map = archiveArticleRspVOS.stream()
                    .collect(Collectors.groupingBy(FindArchiveArticleRspVO::getCreateMonth));

            // 使用 TreeMap 按月份倒序排列
            Map<YearMonth, List<FindArchiveArticleRspVO>> sortedMap = new TreeMap<>(Collections.reverseOrder());
            sortedMap.putAll(map);

            // 遍历排序后的 Map，将其转换为归档 VO
            sortedMap.forEach((key, value) -> vos.add(FindArchiveArticlePageListRspVO.builder()
                    .month(key)
                    .articles(value)
                    .build()));
        }
        return PageResponse.success(articlePage,vos);
    }
}
