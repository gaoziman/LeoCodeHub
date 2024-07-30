package org.leocoder.codehub.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.common.model.domain.Category;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.model.vo.category.FindCategoryArticlePageListReqVO;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 08:54
 * @description :
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取分类列表
     *
     * @return Result
     */
    Result findCategoryList();


    /**
     * 获取分类下文章分页数据
     *
     * @param findCategoryArticlePageListReqVO 分类下文章分页请求参数
     * @return Result
     */
    Result findCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO);

}
