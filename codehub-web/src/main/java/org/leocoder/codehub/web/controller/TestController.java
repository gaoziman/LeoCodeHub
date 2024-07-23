package org.leocoder.codehub.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.exception.BizException;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-17 22:10
 * @description :
 */
@RestController
@Slf4j
@Api(tags = "测试接口")
public class TestController {

    @PostMapping("/admin/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "测试接口")
    public Result<User> test(@RequestBody @Validated User user) {
        // 返回用户信息测试

        // 设置三种日期字段值
        // user.setCreateTime(LocalDateTime.now());
        // user.setUpdateDate(LocalDate.now());
        // user.setTime(LocalTime.now());
        log.info("user: {}", user);
        return Result.success(user);
    }


    /**
     * 测试接口
     *
     * @return Result
     */
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("hello world");
    }


    @PostMapping("/add")
    public boolean add(@RequestBody User user) {
        // 如果姓名为空就手动抛出一个自定义的异常！
        if (user.getUsername() == null) {
            throw new BizException(-1, "用户姓名不能为空！");
        }
        return true;
    }

    @ApiOperationLog(description = "测试更新接口")
    @PostMapping("/admin/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result update() {
        log.info("更新成功...");
        return Result.success();
    }
}
