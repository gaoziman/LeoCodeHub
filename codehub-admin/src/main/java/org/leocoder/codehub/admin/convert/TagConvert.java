package org.leocoder.codehub.admin.convert;

import org.leocoder.codehub.admin.model.vo.tag.req.AddTagReqVO;
import org.leocoder.codehub.admin.model.vo.tag.resp.FindTagPageListRspVO;
import org.leocoder.codehub.common.model.domain.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 02:28
 * @description :
 */
@Mapper
public interface TagConvert {

    /**
     * 初始化 convert 实例
     */
    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);

    /**
     * 将 VO 转化为 实体类
     * @param addTagReqVO VO 对象
     * @return 实体类对象
     */

    default List<Tag> convertVOToEntity(AddTagReqVO addTagReqVO) {
        return addTagReqVO.getTags().stream()
                .map(tag -> Tag.builder()
                        .name(tag)
                        .build())
                .collect(Collectors.toList());
    }



    /**
     * 将 实体对象集合转换为出参
     * @param tagList 实体对象集合
     * @return 出参对象
     */
    List<FindTagPageListRspVO> convertEntityToVO (List<Tag> tagList);
}
