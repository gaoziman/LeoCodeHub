package org.leocoder.codehub.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.admin.model.vo.article.DeleteArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.PublishArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.req.FindArticleDetailReqVO;
import org.leocoder.codehub.admin.model.vo.article.req.FindArticlePageListReqVO;
import org.leocoder.codehub.admin.model.vo.article.req.UpdateArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.resp.FindArticleDetailRspVO;
import org.leocoder.codehub.admin.service.AdminArticleService;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * /**
 *
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 01:50
 * @description : Admin 文章模块
 */
@RestController
@RequestMapping("/admin/article")
@Api(tags = "Admin 文章模块")
public class AdminArticleController {

    @Autowired
    private AdminArticleService articleService;


    /**
     * 文章发布
     *
     * @param publishArticleReqVO 文章发布请求参数
     * @return Result
     */
    @PostMapping("/publish")
    @ApiOperation(value = "文章发布")
    @ApiOperationLog(description = "文章发布")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result publishArticle(@RequestBody @Validated PublishArticleReqVO publishArticleReqVO) {
        return articleService.publishArticle(publishArticleReqVO);
    }


    /**
     * 文章删除
     *
     * @param deleteArticleReqVO 文章删除请求参数
     * @return Result
     */
    @PostMapping("/delete")
    @ApiOperation(value = "文章删除")
    @ApiOperationLog(description = "文章删除")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result deleteArticle(@RequestBody @Validated DeleteArticleReqVO deleteArticleReqVO) {
        return articleService.deleteArticle(deleteArticleReqVO);
    }


    /**
     * 查询文章分页数据
     *
     * @param findArticlePageListReqVO 查询文章分页请求参数
     * @return Result
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询文章分页数据")
    @ApiOperationLog(description = "查询文章分页数据")
    public PageResponse findArticlePageList(@RequestBody @Validated FindArticlePageListReqVO findArticlePageListReqVO) {
        return articleService.findArticlePageList(findArticlePageListReqVO);
    }


    /**
     * 查询文章详情 包括文章内容、评论、点赞、收藏等信息
     *
     * @param findArticlePageListReqVO 查询文章详情请求参数
     * @return Result
     */
    @PostMapping("/detail")
    @ApiOperation(value = "查询文章详情")
    @ApiOperationLog(description = "查询文章详情")
    public Result<FindArticleDetailRspVO> findArticleDetail(@RequestBody @Validated FindArticleDetailReqVO findArticlePageListReqVO) {
        return articleService.findArticleDetail(findArticlePageListReqVO);
    }


    /**
     * 更新文章 包括文章内容、标题、分类、标签等信息
     *
     * @param updateArticleReqVO 更新文章请求参数
     * @return Result
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新文章")
    @ApiOperationLog(description = "更新文章")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result updateArticle(@RequestBody @Validated UpdateArticleReqVO updateArticleReqVO) {
        return articleService.updateArticle(updateArticleReqVO);
    }

}
