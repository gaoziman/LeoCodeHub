package org.leocoder.codehub.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.admin.convert.TagConvert;
import org.leocoder.codehub.admin.model.vo.tag.req.AddTagReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.DeleteTagReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.FindTagPageListReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.SearchBlurTagReqVO;
import org.leocoder.codehub.admin.model.vo.tag.resp.FindTagPageListRspVO;
import org.leocoder.codehub.admin.service.AdminTagService;
import org.leocoder.codehub.common.enums.HttpStatusEnum;
import org.leocoder.codehub.common.exception.BizException;
import org.leocoder.codehub.common.mapper.ArticleTagRelMapper;
import org.leocoder.codehub.common.mapper.TagMapper;
import org.leocoder.codehub.common.model.domain.ArticleTagRel;
import org.leocoder.codehub.common.model.domain.Tag;
import org.leocoder.codehub.common.model.vo.SelectRspVO;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 01:50
 * @description :
 */
@Service
@Slf4j
@Transactional
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, Tag> implements AdminTagService {


    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;


    /**
     * 添加标签
     *
     * @param addTagReqVO addTagReqVO
     * @return Result
     */
    @Override
    public Result addTags(AddTagReqVO addTagReqVO) {
        // 转换为Tag对象
        List<Tag> tag = TagConvert.INSTANCE.convertVOToEntity(addTagReqVO);

        // 批量插入
        try {
            this.saveBatch(tag);
        } catch (Exception e) {
            log.warn("该标签已存在", e);
        }

        return Result.success();
    }


    /**
     * 标签分页数据获取
     *
     * @param findTagPageListReqVO findTagPageListReqVO
     * @return PageResponse
     */
    @Override
    public PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO) {
        // 获取分页参数、条件参数
        Long pageNum = findTagPageListReqVO.getPageNum();
        Long pageSize = findTagPageListReqVO.getPageSize();
        String name = findTagPageListReqVO.getName();
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();

        // 构建查询条件
        Page<Tag> tagPage = baseMapper.selectPageList(pageNum, pageSize, name, startDate, endDate);
        List<Tag> tagList = tagPage.getRecords();

        // 转换对象
        List<FindTagPageListRspVO> findTagPageListRspVO = TagConvert.INSTANCE.convertEntityToVO(tagList);

        // 封装分页数据
        return PageResponse.success(tagPage, findTagPageListRspVO);
    }


    /**
     * 删除标签
     *
     * @param deleteTagReqVO deleteTagReqVO
     * @return Result
     */
    @Override
    public Result deleteTag(DeleteTagReqVO deleteTagReqVO) {
        // 标签 ID
        Long tagId = deleteTagReqVO.getId();

        // 校验该分类下是否已经有文章，若有，则提示需要先删除分类下所有文章，才能删除
        ArticleTagRel articleTagRel = articleTagRelMapper.selectOneByTagId(tagId);

        if (Objects.nonNull(articleTagRel)) {
            log.warn("==> 此标签下包含文章，无法删除，tagId: {}", tagId);
            throw new BizException(HttpStatusEnum.CATEGORY_CAN_NOT_DELETE_TAG);
        }

        // 根据标签 ID 删除
        int count = baseMapper.deleteById(tagId);

        return count == 1 ? Result.success() : Result.fail(HttpStatusEnum.TAG_NOT_EXISTED);
    }


    /**
     * 标签select模糊查询
     *
     * @param searchBlurTagReqVO searchBlurTagReqVO
     * @return Result
     */
    @Override
    public Result<List<SelectRspVO>> findBlurTagList(SearchBlurTagReqVO searchBlurTagReqVO) {
        String key = searchBlurTagReqVO.getKey();

        // 构建查询条件
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Tag::getName, key)
                .orderByDesc(Tag::getCreateTime);

        // 查询标签列表
        List<Tag> tagList = baseMapper.selectList(wrapper);

        // 封装分类 Select 下拉列表数据
        List<SelectRspVO> selectTagList = null;
        if (tagList != null && !tagList.isEmpty()) {
            selectTagList = tagList.stream()
                    .map(tag -> SelectRspVO.builder()
                            .label(tag.getName())
                            .value(tag.getId())
                            .build())
                    .collect(Collectors.toList());
        }
        return Result.success(selectTagList);
    }


    /**
     * 获取标签 Select 下拉列表数据
     *
     * @return Result
     */
    @Override
    public Result<List<SelectRspVO>> findTagSelectList() {
        // 查询标签列表
        List<Tag> tagList = baseMapper.selectList(null);
        // 封装分类 Select 下拉列表数据
        List<SelectRspVO> selectTagList = null;
        if (tagList != null && !tagList.isEmpty()) {
            selectTagList = tagList.stream()
                    .map(tag -> SelectRspVO.builder()
                            .label(tag.getName())
                            .value(tag.getId())
                            .build())
                    .collect(Collectors.toList());
        }
        return Result.success(selectTagList);
    }


}
