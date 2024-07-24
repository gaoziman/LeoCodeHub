package org.leocoder.codehub.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.leocoder.codehub.admin.model.vo.FindUserInfoRspVO;
import org.leocoder.codehub.admin.model.vo.UpdateAdminUserPasswordReqVO;
import org.leocoder.codehub.admin.service.AdminUserService;
import org.leocoder.codehub.common.enums.HttpStatusEnum;
import org.leocoder.codehub.common.mapper.UserMapper;
import org.leocoder.codehub.common.model.domain.User;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-24 15:32
 * @description :
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<UserMapper, User> implements AdminUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 修改用户密码
     *
     * @param updateAdminUserPasswordReqVO 修改用户密码请求参数
     * @return Result
     */
    @Override
    public Result<String> updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        // 1. 获取前端传过来的vo
        String username = updateAdminUserPasswordReqVO.getUsername();
        String password = updateAdminUserPasswordReqVO.getPassword();
        // 加密密码
        String encodePassword = passwordEncoder.encode(password);

        // 2. 通用用户名更改用户名密码
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = new User();
        user.setPassword(encodePassword);
        user.setUsername(username);
        int update = baseMapper.update(user, wrapper);
        return update == 1 ? Result.success() : Result.fail(HttpStatusEnum.USERNAME_NOT_FOUND);
    }


    /**
     * 获取登录用户信息
     *
     * @return Result
     */
    @Override
    public Result<FindUserInfoRspVO> getUserInfo() {
        // 获取存储在 ThreadLocal 中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取用户名
        String username = authentication.getName();

        return Result.success(FindUserInfoRspVO.builder().username(username).build());
    }
}

