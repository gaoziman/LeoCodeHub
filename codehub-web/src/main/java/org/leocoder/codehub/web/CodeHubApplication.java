package org.leocoder.codehub.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-17 21:23
 * @description : CodeHub启动类
 */
@SpringBootApplication
@ComponentScan({"org.leocoder.codehub.*"})
@EnableAspectJAutoProxy
public class CodeHubApplication {
    public static void main(String[] args) {
            ConfigurableApplicationContext context = SpringApplication.run(CodeHubApplication.class, args);
            Environment environment = context.getBean(Environment.class);

            System.out.println("访问链接：http://localhost:" + environment.getProperty("server.port"));
            System.out.println("(♥◠‿◠)ﾉﾞ  项目启动成功 ლ(´ڡ`ლ)ﾞ \n");
        }
}
