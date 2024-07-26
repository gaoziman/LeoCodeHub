package org.leocoder.codehub.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.admin.model.vo.setting.UpdateBlogSettingsReqVO;
import org.leocoder.codehub.admin.service.AdminBlogSettingService;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-26 16:47
 * @description :
 */
@RestController
@RequestMapping("/admin/blog/setting")
@Api(tags = "Admin 博客设置模块")
public class AdminSettingController {

    @Autowired
    private AdminBlogSettingService blogSettingService;


    /**
     * 更新博客基础信息
     *
     * @param updateBlogSettingsReqVO 博客基础信息
     * @return Result
     */
    @PostMapping("/update")
    @ApiOperation(value = "博客基础信息修改")
    @ApiOperationLog(description = "博客基础信息修改")
    public Result updateBlogSettings(@RequestBody @Validated UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        return blogSettingService.updateBlogSettings(updateBlogSettingsReqVO);
    }


    /**
     * 获取博客设置详情
     *
     * @return Result
     */
    @PostMapping("/detail")
    @ApiOperation(value = "获取博客设置详情")
    @ApiOperationLog(description = "获取博客设置详情")
    public Result findDetail() {
        return blogSettingService.findDetail();
    }
}
