package org.leocoder.codehub.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.common.model.domain.BlogSetting;
import org.leocoder.codehub.common.utils.Result;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 08:54
 * @description :
 */
public interface BlogSettingService extends IService<BlogSetting> {


    /**
     * 获取博客详情
     *
     * @return Result
     */
    Result findDetail();
}
