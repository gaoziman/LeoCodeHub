package org.leocoder.codehub.jwt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-21 19:57
 * @description :
 */

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        QueryWrapper<org.leocoder.codehub.common.model.domain.User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        org.leocoder.codehub.common.model.domain.User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // authorities 用于指定角色，这里写死为 ADMIN 管理员
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("ADMIN")
                .build();
    }
}
