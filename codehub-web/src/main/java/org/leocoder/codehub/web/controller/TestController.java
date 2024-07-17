package org.leocoder.codehub.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.web.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-17 22:10
 * @description :
 */
@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public User test(@RequestBody User user) {
        // 返回用户信息测试
        log.info("user: {}", user);
        return user;
    }
}
