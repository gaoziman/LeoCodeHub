package org.leocoder.codehub.web.convert;

import org.leocoder.codehub.common.model.domain.BlogSetting;
import org.leocoder.codehub.web.model.vo.setting.FindBlogSettingsDetailRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 09:29
 * @description :
 */
@Mapper
public interface BlogSettingConvert {

    /**
     * 初始化 convert 实例
     */
    BlogSettingConvert INSTANCE = Mappers.getMapper(BlogSettingConvert.class);

    /**
     * 将 实体类 转化为 VO
     * @param blogSetting  实体类
     * @return
     */
    FindBlogSettingsDetailRspVO convertEntity2VO(BlogSetting blogSetting);
}
