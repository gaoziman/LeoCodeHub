package org.leocoder.codehub.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.admin.model.vo.category.req.AddCategoryReqVO;
import org.leocoder.codehub.admin.model.vo.category.req.DeleteCategoryReqVO;
import org.leocoder.codehub.admin.model.vo.category.req.FindCategoryPageListReqVO;
import org.leocoder.codehub.admin.model.vo.category.req.UpdateCategoryReqVO;
import org.leocoder.codehub.admin.service.AdminCategoryService;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.model.vo.SelectRspVO;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-24 15:09
 * @description : 文章分类模块
 */
@RestController
@RequestMapping("/admin/category")
@Api(tags = "Admin 分类模块")
public class AdminCategoryController {
    @Autowired
    private AdminCategoryService categoryService;


    /**
     * 获取分类分页数据
     * @param findCategoryPageListReqVO 分类分页查询条件
     * @return PageResponse
     */
    @PostMapping("/list")
    @ApiOperation(value = "分类分页数据获取")
    @ApiOperationLog(description = "分类分页数据获取")
    public PageResponse getCategoryList(@RequestBody @Validated FindCategoryPageListReqVO findCategoryPageListReqVO) {
        return categoryService.getCategoryList(findCategoryPageListReqVO);
    }


    /**
     * 获取分类 Select 下拉列表数据
     * @return Result
     */
    @PostMapping("/select/list")
    @ApiOperation(value = "分类 Select 下拉列表数据获取")
    @ApiOperationLog(description = "分类 Select 下拉列表数据获取")
    public Result<List<SelectRspVO>> getCategorySelectList() {
        return categoryService.getCategorySelectList();
    }

    /**
     * 添加分类
     *
     * @param addCategoryReqVO 分类信息
     * @return Result
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加分类")
    @ApiOperationLog(description = "添加分类")
    public Result addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO) {
        return categoryService.addCategory(addCategoryReqVO);
    }


    /**
     * 更新分类
     *
     * @param updateCategoryReqVO 分类信息
     * @return Result
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新分类")
    @ApiOperationLog(description = "更新分类")
    public Result updateCategory(@RequestBody @Validated UpdateCategoryReqVO updateCategoryReqVO) {
        return categoryService.updateCategory(updateCategoryReqVO);
    }


    /**
     * 删除分类
     * @param deleteCategoryReqVO 分类信息
     * @return  Result
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除分类")
    @ApiOperationLog(description = "删除分类")
    public Result deleteCategory(@RequestBody @Validated DeleteCategoryReqVO deleteCategoryReqVO) {
        return categoryService.deleteCategory(deleteCategoryReqVO);
    }

}
