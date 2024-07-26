package org.leocoder.codehub.admin.service;

import org.leocoder.codehub.admin.model.vo.setting.UpdateBlogSettingsReqVO;
import org.leocoder.codehub.common.model.domain.BlogSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.common.utils.Result;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-26 19:09
 * @description :
 */

public interface AdminBlogSettingService extends IService<BlogSetting> {


    /**
     * 更新博客基础信息
     *
     * @param updateBlogSettingsReqVO 博客基础信息
     * @return Result
     */
    Result updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);


    /**
     * 获取博客设置详情
     *
     * @return Result
     */
    Result findDetail();

}
