package org.leocoder.codehub.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.common.model.domain.Category;
import org.leocoder.codehub.common.utils.Result;

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

}
