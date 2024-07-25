package org.leocoder.codehub.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leocoder.codehub.admin.model.vo.user.FindUserInfoRspVO;
import org.leocoder.codehub.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import org.leocoder.codehub.admin.service.AdminUserService;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-24 15:09
 * @description : 管理员用户模块
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin用户模块")
public class AdminUserController {

    @Autowired
    private AdminUserService userService;


    /**
     * 修改用户密码
     *
     * @param updateAdminUserPasswordReqVO 修改用户密码请求参数
     * @return Result
     */
    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改用户密码")
    @ApiOperationLog(description = "修改用户密码")
    public Result<String> updatePassword(@RequestBody @Validated UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        return userService.updatePassword(updateAdminUserPasswordReqVO);
    }

    /**
     * 获取登录用户信息
     *
     * @return Result
     */
    @PostMapping("/user/info")
    @ApiOperation(value = "获取用户信息")
    @ApiOperationLog(description = "获取用户信息")
    public Result<FindUserInfoRspVO> getUserInfo() {
        return userService.getUserInfo();
    }
}
