package org.leocoder.codehub.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.admin.model.vo.tag.req.AddTagReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.DeleteTagReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.FindTagPageListReqVO;
import org.leocoder.codehub.admin.model.vo.tag.req.SearchBlurTagReqVO;
import org.leocoder.codehub.common.model.domain.Tag;
import org.leocoder.codehub.common.model.vo.SelectRspVO;
import org.leocoder.codehub.common.utils.PageResponse;
import org.leocoder.codehub.common.utils.Result;

import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 01:50
 * @description :
 */
public interface AdminTagService  extends IService<Tag> {

    /**
     * 添加标签
     *
     * @param addTagReqVO addTagReqVO
     * @return Result
     */
    Result addTags(AddTagReqVO addTagReqVO);


    /**
     * 标签分页数据获取
     *
     * @param findTagPageListReqVO findTagPageListReqVO
     * @return PageResponse
     */
    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);


    /**
     * 删除标签
     *
     * @param deleteTagReqVO deleteTagReqVO
     * @return Result
     */
    Result deleteTag(DeleteTagReqVO deleteTagReqVO);


    /**
     * 标签select模糊查询
     *
     * @param searchBlurTagReqVO searchBlurTagReqVO
     * @return Result
     */
    Result<List<SelectRspVO>> findBlurTagList(SearchBlurTagReqVO searchBlurTagReqVO);


    /**
     * 获取标签 Select 下拉列表数据
     *
     * @return Result
     */
    Result<List<SelectRspVO>> findTagSelectList();


}
