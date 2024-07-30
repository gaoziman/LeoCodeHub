package org.leocoder.codehub.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.mapper.TagMapper;
import org.leocoder.codehub.common.model.domain.Tag;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.model.vo.tag.FindTagListRspVO;
import org.leocoder.codehub.web.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 08:55
 * @description :
 */
@Slf4j
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


    /**
     * 获取标签列表
     *
     * @return Result
     */
    @Override
    public Result findCategoryList() {
        // 查询标签列表
        List<Tag> tagList = baseMapper.selectList(null);

        // 转换对象
        List<FindTagListRspVO> vos = null;
        if (tagList != null && !tagList.isEmpty()) {
            vos = tagList.stream().map(tag ->
                    FindTagListRspVO.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .build()
            ).collect(Collectors.toList());
        }
        return Result.success(vos);
    }
}
