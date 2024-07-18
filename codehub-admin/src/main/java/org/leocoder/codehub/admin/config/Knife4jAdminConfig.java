package org.leocoder.codehub.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-19 00:57
 * @description : Knife4j 配置类
 */
@Configuration
@EnableSwagger2WebMvc
@Profile("dev") // 只在 dev 环境中开启
public class Knife4jAdminConfig {

    @Bean("adminApi")
    public Docket createApiDoc() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                // 分组名称
                .groupName("Admin 后台接口")
                .select()
                // 这里指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("org.leocoder.codehub.admin.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 构建 API 信息
     * @return
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("LeoCodeHub 博客 Admin 后台接口文档")
                // 描述
                .description("LeoCodeHub 是一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。")
                // API 服务条款
                .termsOfServiceUrl("https://gaoziman.github.io/toLeoJavaer/")
                // 联系人
                .contact(new Contact("程序员Leo", "https://gaoziman.github.io/toLeoJavaer/", "2942894660@qq.com"))
                // 版本号
                .version("1.0")
                .build();
    }
}

