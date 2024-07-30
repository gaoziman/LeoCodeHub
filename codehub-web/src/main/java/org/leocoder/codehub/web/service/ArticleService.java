package org.leocoder.codehub.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.common.model.domain.Article;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.web.model.vo.FindIndexArticlePageListReqVO;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 17:14
 * @description :
 */

public interface ArticleService extends IService<Article> {



    /**
     * 获取首页文章分页数据
     *
     * @param findIndexArticlePageListReqVO 分页查询条件
     * @return 分页数据
     */
    PageResponse findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO);

}
