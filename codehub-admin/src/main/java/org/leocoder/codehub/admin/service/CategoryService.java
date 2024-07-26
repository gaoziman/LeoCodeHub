package org.leocoder.codehub.admin.service;

import org.leocoder.codehub.admin.model.vo.category.req.AddCategoryReqVO;
import org.leocoder.codehub.admin.model.vo.category.req.DeleteCategoryReqVO;
import org.leocoder.codehub.admin.model.vo.category.req.FindCategoryPageListReqVO;
import org.leocoder.codehub.admin.model.vo.category.req.UpdateCategoryReqVO;
import org.leocoder.codehub.common.model.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.common.model.vo.SelectRspVO;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;

import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-25 15:37
 * @description :
 */

public interface CategoryService extends IService<Category> {


    /**
     * 添加分类
     *
     * @param addCategoryReqVO 分类信息
     * @return Result
     */
    Result addCategory(AddCategoryReqVO addCategoryReqVO);


    /**
     * 更新分类
     *
     * @param updateCategoryReqVO 分类信息
     * @return Result
     */
    Result updateCategory(UpdateCategoryReqVO updateCategoryReqVO);


    /**
     * 删除分类
     * @param deleteCategoryReqVO 分类信息
     * @return  Result
     */
    Result deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);


    /**
     * 获取分类分页数据
     * @param findCategoryPageListReqVO 分类分页查询条件
     * @return PageResponse
     */
    PageResponse getCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO);


    /**
     * 获取分类 Select 下拉列表数据
     * @return Result
     */
    Result<List<SelectRspVO>> getCategorySelectList();
}
