package org.leocoder.codehub.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.admin.service.AdminFileService;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-26 15:46
 * @description :
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 文件模块")
public class AdminFileController {


    @Autowired
    private AdminFileService fileService;

    /**
     * 文件上传
     *
     * @param file 上传的文件
     * @return Result
     */
    @PostMapping("/file/upload")
    @ApiOperation(value = "文件上传")
    @ApiOperationLog(description = "文件上传")
    public Result uploadFile(@RequestParam MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
