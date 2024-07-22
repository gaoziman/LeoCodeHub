package org.leocoder.codehub.jwt.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-22 17:24
 * @description : 登录成功访问收保护的资源，但是权限不够
 */
@Slf4j
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.warn("登录成功访问收保护的资源，但是权限不够: ", accessDeniedException);
        // 预留，后面引入多角色时会用到
    }
}