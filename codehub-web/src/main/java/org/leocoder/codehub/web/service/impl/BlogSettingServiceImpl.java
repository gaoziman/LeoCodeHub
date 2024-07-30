package org.leocoder.codehub.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.mapper.BlogSettingMapper;
import org.leocoder.codehub.common.model.domain.BlogSetting;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.model.vo.setting.FindBlogSettingsDetailRspVO;
import org.leocoder.codehub.web.service.BlogSettingService;
import org.springframework.stereotype.Service;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 08:55
 * @description :
 */
@Slf4j
@Service
public class BlogSettingServiceImpl extends ServiceImpl<BlogSettingMapper, BlogSetting> implements BlogSettingService {


    /**
     * 获取博客详情
     *
     * @return Result
     */
    @Override
    public Result findDetail() {
        // 查询博客设置
        BlogSetting blogSetting = baseMapper.selectById(1);

        // 转换对象
        // FindBlogSettingsDetailRspVO findBlogSettingsDetailRspVO = BlogSettingConvert.INSTANCE.convertEntity2VO(blogSetting);
        FindBlogSettingsDetailRspVO findBlogSettingsDetailRspVO  =  FindBlogSettingsDetailRspVO.builder()
                .name(blogSetting.getName())
                .logo(blogSetting.getLogo())
                .author(blogSetting.getAuthor())
                .avatar(blogSetting.getAvatar())
                .introduction(blogSetting.getIntroduction())
                .csdnHomepage(blogSetting.getCsdnHomepage())
                .zhihuHomepage(blogSetting.getZhihuHomepage())
                .githubHomepage(blogSetting.getGithubHomepage())
                .giteeHomepage(blogSetting.getGiteeHomepage())
                .build();

        return Result.success(findBlogSettingsDetailRspVO);
    }
}
