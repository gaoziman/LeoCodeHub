package org.leocoder.codehub.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.model.vo.tag.FindTagArticlePageListReqVO;
import org.leocoder.codehub.web.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 08:53
 * @description :
 */
@RestController
@RequestMapping("/tag")
@Api(tags = "标签")
public class TagController {


    @Autowired
    private TagService tagService;


    /**
     * 获取标签列表
     *
     * @return Result
     */
    @PostMapping("/list")
    @ApiOperation(value = "前台获取标签列表")
    @ApiOperationLog(description = "前台获取blog标签列表")
    public Result findCategoryList() {
        return tagService.findCategoryList();
    }

    /**
     * 获取标签下文章分页数据
     *
     * @param findTagArticlePageListReqVO 标签下文章分页请求参数
     * @return Result
     */
    @PostMapping("/article/list")
    @ApiOperation(value = "前台获取分类下文章分页数据")
    @ApiOperationLog(description = "前台获取分类下文章分页数据")
    public Result findCategoryArticlePageList(@RequestBody @Validated FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        return tagService.findCategoryArticlePageList(findTagArticlePageListReqVO);
    }
}
