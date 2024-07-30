package org.leocoder.codehub.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.mapper.CategoryMapper;
import org.leocoder.codehub.common.model.domain.Category;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.model.vo.category.FindCategoryListRspVO;
import org.leocoder.codehub.web.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 08:55
 * @description :
 */
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /**
     * 获取分类列表
     *
     * @return Result
     */
    @Override
    public Result findCategoryList() {
        // 查询分类列表
        List<Category> categoryList = baseMapper.selectList(null);

        // 转换对象
        List<FindCategoryListRspVO> vos = null;
        if (categoryList!= null && !categoryList.isEmpty()) {
            vos = categoryList.stream().map(category ->
                 FindCategoryListRspVO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build()
            ).collect(Collectors.toList());
        }
        return Result.success(vos);
    }
}
