package org.leocoder.codehub.admin.config;

import org.leocoder.codehub.jwt.config.JwtAuthenticationSecurityConfig;
import org.leocoder.codehub.jwt.filter.TokenAuthenticationFilter;
import org.leocoder.codehub.jwt.handler.RestAccessDeniedHandler;
import org.leocoder.codehub.jwt.handler.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-21 19:37
 * @description : SpringSecurity 配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;
    @Autowired
    private RestAuthenticationEntryPoint authEntryPoint;
    @Autowired
    private RestAccessDeniedHandler deniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf

        http.csrf().disable().
                // 禁用表单登录
                        formLogin().disable()
                // 设置用户登录认证相关配置
                .apply(jwtAuthenticationSecurityConfig)
                .and()
                .authorizeHttpRequests()
                // 认证所有以 /admin 为前缀的 URL 资源
                .mvcMatchers("/admin/**").authenticated()
                .anyRequest().permitAll() // 其他都需要放行，无需认证
                .and()
                // 处理用户未登录访问受保护的资源的情况
                .httpBasic().authenticationEntryPoint(authEntryPoint)
                .and()
                // 处理登录成功后访问受保护的资源，但是权限不够的情况
                .exceptionHandling().accessDeniedHandler(deniedHandler)
                .and()
                // 前后端分离，无需创建会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 将 Token 校验过滤器添加到用户认证过滤器之前
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
    }

    /**
     * Token 校验过滤器
     *
     * @return
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

}