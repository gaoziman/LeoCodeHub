package org.leocoder.codehub.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.leocoder.codehub.common.model.domain.UserRole;

import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-23 13:58
 * @description :
 */

public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户名查询用户角色关系
     *
     * @param username 用户名
     * @return 用户角色关系列表
     */
    default List<UserRole> selectByUsername(String username) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUsername, username);
        return selectList(wrapper);
    }
}