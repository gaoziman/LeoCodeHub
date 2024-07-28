package org.leocoder.codehub.admin.service;

import org.leocoder.codehub.admin.model.vo.article.DeleteArticleReqVO;
import org.leocoder.codehub.admin.model.vo.article.PublishArticleReqVO;
import org.leocoder.codehub.common.model.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
