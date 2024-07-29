package org.leocoder.codehub.admin.service;

import org.leocoder.codehub.admin.model.vo.article.DeleteArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.PublishArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.req.FindArticleDetailReqVO;
import org.leocoder.codehub.admin.model.vo.article.req.FindArticlePageListReqVO;
import org.leocoder.codehub.admin.model.vo.article.req.UpdateArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.resp.FindArticleDetailRspVO;
import org.leocoder.codehub.common.model.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 17:14
 * @description :
 */

public interface AdminArticleService extends IService<Article> {


    /**
     * 文章发布
     *
     * @param publishArticleReqVO 文章发布请求参数
     * @return Result
     */
    Result publishArticle(PublishArticleReqVO publishArticleReqVO);


    /**
     * 文章删除
     *
     * @param deleteArticleReqVO 文章删除请求参数
     * @return Result
     */
    Result deleteArticle(DeleteArticleReqVO deleteArticleReqVO);


    /**
     * 查询文章分页数据
     *
     * @param findArticlePageListReqVO 查询文章分页请求参数
     * @return Result
     */
    PageResponse findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO);


    /**
     * 查询文章详情 包括文章内容、评论、点赞、收藏等信息
     *
     * @param findArticlePageListReqVO 查询文章详情请求参数
     * @return Result
     */
    Result<FindArticleDetailRspVO> findArticleDetail(FindArticleDetailReqVO findArticlePageListReqVO);


    /**
     * 更新文章 包括文章内容、标题、分类、标签等信息
     *
     * @param updateArticleReqVO 更新文章请求参数
     * @return Result
     */
    Result updateArticle(UpdateArticleReqVO updateArticleReqVO);

}
