package org.leocoder.codehub.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.aspect.ApiOperationLog;
import org.leocoder.codehub.common.exception.BizException;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.web.domain.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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
    public Result<String> test(@RequestBody @Validated User user, BindingResult bindingResult) {
        // 返回用户信息测试
        log.info("user: {}", user);
        // 是否存在校验错误
        if (bindingResult.hasErrors()) {
            log.error("bindingResult: {}", bindingResult.getAllErrors());
            String errorMsg = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return Result.fail(errorMsg);
        }
        // 返回参数
        return Result.success("参数没有任何问题");
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

    @PutMapping("/update")
    public boolean update(@RequestBody User user) {
        //这里故意造成一个空指针的异常，并且不进行处理
        String str = null;
        str.equals("111");
        return true;
    }
}
