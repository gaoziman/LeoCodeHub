package org.leocoder.codehub.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.admin.model.vo.tag.req.AddTagReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.DeleteTagReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.FindTagPageListReqVO;
import org.leocoder.codehub.admin.service.AdminTagService;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.utils.PageResponse;
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
 * @date 2024-07-27 01:50
 * @description : Admin 标签模块
 */
@RestController
@RequestMapping("/admin")
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
    @PostMapping("/tag/add")
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
    @PostMapping("/tag/list")
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
    @PostMapping("/tag/delete")
    @ApiOperation(value = "删除标签")
    @ApiOperationLog(description = "删除标签")
    public Result deleteTag(@RequestBody @Validated DeleteTagReqVO deleteTagReqVO) {
        return tagService.deleteTag(deleteTagReqVO);
    }

    // @PostMapping("/category/select/list")
    // @ApiOperation(value = "分类 Select 下拉列表数据获取")
    // @ApiOperationLog(description = "分类 Select 下拉列表数据获取")
    // public Response findCategorySelectList() {
    //     return categoryService.findCategorySelectList();
    // }


}

