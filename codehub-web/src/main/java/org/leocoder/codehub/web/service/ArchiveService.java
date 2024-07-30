package org.leocoder.codehub.web.service;

import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.web.model.vo.archive.FindArchiveArticlePageListReqVO;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 19:37
 * @description :
 */
public interface ArchiveService {

    /**
     * 获取文章归档分页数据
     *
     * @param findArchiveArticlePageListReqVO 分页查询参数
     * @return 分页数据
     */
    PageResponse findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO);
}
