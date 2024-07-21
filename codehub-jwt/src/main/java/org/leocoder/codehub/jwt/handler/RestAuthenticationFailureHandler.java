package org.leocoder.codehub.jwt.handler;

import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.enums.HttpStatusEnum;
import org.leocoder.codehub.common.utils.Result;
import org.leocoder.codehub.jwt.exception.UsernameOrPasswordNullException;
import org.leocoder.codehub.jwt.utils.ResultUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-21 20:10
 * @description : 认证失败处理器
 */
@Component
@Slf4j
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.warn("AuthenticationException: ", exception);
        if (exception instanceof UsernameOrPasswordNullException) {
            // 用户名或密码为空
            ResultUtil.fail(response, Result.fail(exception.getMessage()));
        }else if (exception instanceof BadCredentialsException) {
            // 用户名或密码错误
            ResultUtil.fail(response, Result.fail(HttpStatusEnum.USERNAME_OR_PWD_ERROR));
        }

        // 登录失败
        ResultUtil.fail(response, Result.fail(HttpStatusEnum.LOGIN_FAIL));
    }
}