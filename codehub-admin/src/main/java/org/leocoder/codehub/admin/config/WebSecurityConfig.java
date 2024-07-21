package org.leocoder.codehub.admin.config;

import org.leocoder.codehub.jwt.config.JwtAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-21 19:37
 * @description : SpringSecurity 配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf
        http.csrf().disable()
                // 禁用表单登录
                .formLogin().disable()
                // 设置用户登录认证相关配置
                .apply(jwtAuthenticationSecurityConfig)
                .and()
                .authorizeHttpRequests()
                // 认证所有以 /admin 为前缀的 URL 资源
                .mvcMatchers("/admin/**").authenticated()
                // 其他都需要放行，无需认证
                .anyRequest().permitAll()
                .and()
                // 前后端分离，无需创建会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}