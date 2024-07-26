package org.leocoder.codehub.admin.service.impl;

import org.leocoder.codehub.admin.convert.BlogSettingsConvert;
import org.leocoder.codehub.admin.model.vo.setting.FindBlogSettingsRspVO;
import org.leocoder.codehub.admin.model.vo.setting.UpdateBlogSettingsReqVO;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.leocoder.codehub.common.model.domain.BlogSetting;
import org.leocoder.codehub.common.mapper.BlogSettingMapper;
import org.leocoder.codehub.admin.service.AdminBlogSettingService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Leo
 * @date  2024-07-26 19:09
 * @version 1.0
 * @description :
 */

@Service
@Transactional
public class AdminBlogSettingServiceImpl extends ServiceImpl<BlogSettingMapper, BlogSetting> implements AdminBlogSettingService {


    /**
     * 更新博客基础信息
     *
     * @param updateBlogSettingsReqVO 博客基础信息
     * @return Result
     */
    @Override
    public Result updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        BlogSetting blogSetting = BlogSettingsConvert.INSTANCE.convertVOToEntity(updateBlogSettingsReqVO);
        // 设置 ID 为 1 的记录为唯一记录
        blogSetting.setId(1L);
        // 保存或更新（当数据库中存在 ID 为 1 的记录时，则执行更新操作，否则执行插入操作）
        this.saveOrUpdate(blogSetting);
        return Result.success();
    }


    /**
     * 获取博客设置详情
     *
     * @return Result
     */
    @Override
    public Result findDetail() {
        // 转换成 VO 对象并返回
        FindBlogSettingsRspVO findBlogSettingsRspVO = BlogSettingsConvert.INSTANCE.convertEntityToVO(this.getById(1L));
        return Result.success(findBlogSettingsRspVO);
    }
}
