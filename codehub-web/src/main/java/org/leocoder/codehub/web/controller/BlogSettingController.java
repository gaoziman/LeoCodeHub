package org.leocoder.codehub.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.service.BlogSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 09:18
 * @description :
 */
@RestController
@RequestMapping("/blog/setting")
@Api(tags = "博客设置")
public class BlogSettingController {

    @Autowired
    private BlogSettingService blogSettingService;


    /**
     * 获取博客详情
     *
     * @return Result
     */
    @PostMapping("/detail")
    @ApiOperation(value = "前台获取博客详情")
    @ApiOperationLog(description = "前台获取博客详情")
    public Result findDetail() {
        return blogSettingService.findDetail();
    }
}
