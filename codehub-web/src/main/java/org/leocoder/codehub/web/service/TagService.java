package org.leocoder.codehub.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.common.model.domain.Tag;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.model.vo.tag.FindTagArticlePageListReqVO;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 08:54
 * @description :
 */
public interface TagService extends IService<Tag> {

    /**
     * 获取标签列表
     *
     * @return Result
     */
    Result findCategoryList();



    /**
     * 获取标签下文章分页数据
     *
     * @param findTagArticlePageListReqVO 标签下文章分页请求参数
     * @return Result
     */
    Result findCategoryArticlePageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO);
}
