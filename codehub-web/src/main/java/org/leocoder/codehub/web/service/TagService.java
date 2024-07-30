package org.leocoder.codehub.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.codehub.common.model.domain.Tag;
import org.leocoder.codehub.common.utils.Result;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 08:54
 * @description :
 */
public interface TagService extends IService<Tag> {

    /**
     * 获取标签列表
     *
     * @return Result
     */
    Result findCategoryList();


}
