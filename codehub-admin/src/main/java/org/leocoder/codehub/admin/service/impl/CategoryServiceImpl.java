package org.leocoder.codehub.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.admin.model.vo.category.req.AddCategoryReqVO;
import org.leocoder.codehub.admin.model.vo.category.req.DeleteCategoryReqVO;
import org.leocoder.codehub.admin.model.vo.category.req.FindCategoryPageListReqVO;
import org.leocoder.codehub.admin.model.vo.category.req.UpdateCategoryReqVO;
import org.leocoder.codehub.admin.model.vo.category.resp.FindCategoryPageListRspVO;
import org.leocoder.codehub.admin.service.CategoryService;
import org.leocoder.codehub.common.enums.HttpStatusEnum;
import org.leocoder.codehub.common.exception.BizException;
import org.leocoder.codehub.common.mapper.CategoryMapper;
import org.leocoder.codehub.common.model.domain.Category;
import org.leocoder.codehub.common.model.vo.SelectRspVO;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.leocoder.codehub.common.utils.Result.success;

/**
 * @author : Leo
 * @date  2024-07-25 15:37
 * @version 1.0
 * @description :
 */

@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    /**
     * 获取分类分页数据
     * @param findCategoryPageListReqVO 分类分页查询条件
     * @return PageResponse
     */
    @Override
    public PageResponse getCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
        // 获取分页数据
        Long getPageNum = findCategoryPageListReqVO.getPageNum();
        Long pageSize = findCategoryPageListReqVO.getPageSize();

        // 分页对象(查询第几页、每页多少数据)
        Page<Category> page = new Page<>(getPageNum, pageSize);

        // 构建查询条件
        LambdaUpdateWrapper<Category> wrapper = new LambdaUpdateWrapper<>();
        String name = findCategoryPageListReqVO.getName();
        LocalDate startDate = findCategoryPageListReqVO.getStartDate();
        LocalDate endDate = findCategoryPageListReqVO.getEndDate();
                // 名称模糊查询
        wrapper.like(Category::getName, name)
                // 大于等于 startDate
                .ge(Objects.nonNull(startDate), Category::getCreateTime, startDate)
                 // 小于等于 endDate
                .le(Objects.nonNull(endDate), Category::getCreateTime, endDate);

        // 查询分页数据
        Page<Category> categoryPage = baseMapper.selectPage(page, wrapper);
        List<Category> categoryList = categoryPage.getRecords();

        // 封装分页数据
        List<FindCategoryPageListRspVO> categoryVO = null;
        if (categoryList != null && !categoryList.isEmpty()) {
            categoryVO = categoryList.stream()
                    .map(category -> FindCategoryPageListRspVO.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .createTime(category.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }
        return PageResponse.success(categoryPage, categoryVO);
    }


    /**
     * 获取分类 Select 下拉列表数据
     * @return Result
     */
    @Override
    public Result<List<SelectRspVO>> getCategorySelectList() {
        // 查询所有分类
        List<Category> categoryList = baseMapper.selectList(null);

        // 封装分类 Select 下拉列表数据
        List<SelectRspVO> selectRspVOList = null;
        if (categoryList != null && !categoryList.isEmpty()) {
            selectRspVOList = categoryList.stream()
                    .map(category -> SelectRspVO.builder()
                            .label(category.getName())
                            .value(category.getId())
                            .build())
                    .collect(Collectors.toList());
        }
        return Result.success(selectRspVOList);
    }


    /**
     * 添加分类
     *
     * @param addCategoryReqVO 分类信息
     * @return Result
     */
    @Override
    public Result addCategory(AddCategoryReqVO addCategoryReqVO) {
        String categoryName = addCategoryReqVO.getName();
        // 判断分类是否存在
        LambdaUpdateWrapper<Category> wrapper = new LambdaUpdateWrapper<Category>()
                .eq(Category::getName, categoryName);
        Category category = baseMapper.selectOne(wrapper);
        if (category != null) {
            log.warn("分类名称： {}, 此分类已存在", categoryName);
            throw new BizException(HttpStatusEnum.CATEGORY_NAME_IS_EXISTED);
        }

        Category build = Category.builder()
                .name(categoryName)
                .build();
        baseMapper.insert(build);
        return success();
    }


    /**
     * 更新分类
     *
     * @param updateCategoryReqVO 分类信息
     * @return Result
     */
    @Override
    public Result updateCategory(UpdateCategoryReqVO updateCategoryReqVO) {
        Long categoryId = updateCategoryReqVO.getId();
        String newCategoryName = updateCategoryReqVO.getName();



        // 查询是否存在
        Category category = baseMapper.selectById(categoryId);
        if (category == null) {
            throw new BizException(HttpStatusEnum.CATEGORY_NAME_NOT_EXISTED);
        }

        // 检查新名称是否已存在于其他记录
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<Category>()
                .eq(Category::getName, newCategoryName)
                  // 排除当前记录
                .ne(Category::getId, categoryId);
        Category existingCategory = baseMapper.selectOne(queryWrapper);
        if (existingCategory != null) {
            throw new BizException(HttpStatusEnum.CATEGORY_NAME_IS_EXISTED);
        }

        // 更新分类信息
        category.setName(newCategoryName);
        category.setUpdateTime(new Date());
        baseMapper.updateById(category);
        return success();


       /* //先查询是否存在，在进行更新
        LambdaUpdateWrapper<Category> wrapper = new LambdaUpdateWrapper<Category>()
                .eq(Category::getName, categoryName);
        Category category = baseMapper.selectOne(wrapper);
        if (category == null) {
            throw new BizException(HttpStatusEnum.CATEGORY_NAME_NOT_EXISTED);
        }
        category.setName(updateCategoryReqVO.getName());
        category.setUpdateTime(new Date());
        baseMapper.updateById(category);*/
    }




    /**
     * 删除分类
     * @param deleteCategoryReqVO 分类信息
     * @return  Result
     */
    @Override
    public Result deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO) {
        // 分类 ID
        Long categoryId = deleteCategoryReqVO.getId();
        // 删除分类
        baseMapper.deleteById(categoryId);

        return success();
    }
}
