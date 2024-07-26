package org.leocoder.codehub.admin.convert;

import org.leocoder.codehub.admin.model.vo.setting.FindBlogSettingsRspVO;
import org.leocoder.codehub.admin.model.vo.setting.UpdateBlogSettingsReqVO;
import org.leocoder.codehub.common.model.domain.BlogSetting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-26 21:12
 * @description :
 */
@Mapper
public interface BlogSettingsConvert {
    /**
     * 初始化 convert 实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * 将 VO 转化为 实体类
     * @param bean VO 对象
     * @return 实体类对象
     */
    BlogSetting convertVOToEntity(UpdateBlogSettingsReqVO bean);



    /**
     * 将 实体类 转化为 VO
     * @param bean  实体类对象
     * @return VO 对象
     */
    FindBlogSettingsRspVO convertEntityToVO(BlogSetting bean);

}
