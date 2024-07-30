package org.leocoder.codehub.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.web.model.vo.archive.FindArchiveArticlePageListReqVO;
import org.leocoder.codehub.web.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 20:24
 * @description :
 */
@RestController
@Api(tags = "文章归档")
public class ArchiveController {

    @Autowired
    private ArchiveService archiveService;


    /**
     * 获取文章归档分页数据
     *
     * @param findArchiveArticlePageListReqVO 文章归档分页查询参数
     * @return Result
     */
    @PostMapping("/archive/list")
    @ApiOperation(value = "获取文章归档分页数据")
    @ApiOperationLog(description = "获取文章归档分页数据")
    public PageResponse findArchivePageList(@RequestBody FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO) {
        return archiveService.findArchivePageList(findArchiveArticlePageListReqVO);
    }

}
