package org.leocoder.codehub.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 08:53
 * @description :
 */
@RestController
@RequestMapping("/category")
@Api(tags = "分类")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类列表
     *
     * @return Result
     */
    @PostMapping("/list")
    @ApiOperation(value = "前台获取分类列表")
    @ApiOperationLog(description = "前台获取分类列表")
    public Result findCategoryList() {
        return categoryService.findCategoryList();
    }
}
