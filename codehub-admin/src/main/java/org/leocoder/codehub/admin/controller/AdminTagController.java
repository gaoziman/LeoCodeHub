package org.leocoder.codehub.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.admin.model.vo.tag.req.AddTagReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.DeleteTagReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.FindTagPageListReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.SearchBlurTagReqVO;
import org.leocoder.codehub.admin.service.AdminTagService;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.model.vo.SelectRspVO;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 01:50
 * @description : Admin 标签模块
 */
@RestController
@RequestMapping("/admin/tag")
@Api(tags = "Admin 标签模块")
public class AdminTagController {

    @Autowired
    private AdminTagService tagService;


    /**
     * 添加标签
     *
     * @param addTagReqVO addTagReqVO
     * @return Result
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加标签")
    @ApiOperationLog(description = "添加标签")
    public Result addTags(@RequestBody @Validated AddTagReqVO addTagReqVO) {
        return tagService.addTags(addTagReqVO);
    }


    /**
     * 标签分页数据获取
     *
     * @param findTagPageListReqVO findTagPageListReqVO
     * @return PageResponse
     */
    @PostMapping("/list")
    @ApiOperation(value = "标签分页数据获取")
    @ApiOperationLog(description = "标签分页数据获取")
    public PageResponse findTagPageList(@RequestBody @Validated FindTagPageListReqVO findTagPageListReqVO) {
        return tagService.findTagPageList(findTagPageListReqVO);
    }


    /**
     * 删除标签
     *
     * @param deleteTagReqVO deleteTagReqVO
     * @return Result
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除标签")
    @ApiOperationLog(description = "删除标签")
    public Result deleteTag(@RequestBody @Validated DeleteTagReqVO deleteTagReqVO) {
        return tagService.deleteTag(deleteTagReqVO);
    }


    /**
     * 标签select模糊查询
     *
     * @param searchBlurTagReqVO searchBlurTagReqVO
     * @return Result
     */
    @PostMapping("/search")
    @ApiOperation(value = "标签select模糊查询")
    @ApiOperationLog(description = "标签select模糊查询")
    public Result<List<SelectRspVO>> findCategorySelectList(@RequestBody @Validated SearchBlurTagReqVO searchBlurTagReqVO) {
        return tagService.findBlurTagList(searchBlurTagReqVO);
    }


    /**
     * 获取标签 Select 下拉列表数据
     *
     * @return Result
     */
    @PostMapping("/select/list")
    @ApiOperation(value = "查询标签 Select 列表数据")
    @ApiOperationLog(description = "查询标签 Select 列表数据")
    public Result<List<SelectRspVO>> findTagSelectList() {
        return tagService.findTagSelectList();
    }
}

