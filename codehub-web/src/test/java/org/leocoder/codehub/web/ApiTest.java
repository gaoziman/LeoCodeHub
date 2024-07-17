package org.leocoder.codehub.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-17 21:25
 * @description :
 */
@SpringBootTest(classes = CodeHubApplication.class)
@Slf4j
public class ApiTest {

    /**
     * 用于测试: 依赖是否正常
     */
    @Test
    public void test01() {
        System.out.println("test01");
    }


    /**
     *  用于测试: 测试日志
     */
    @Test
    public void test02() {
        log.info("这是一行 Info 级别日志");
        log.warn("这是一行 Warn 级别日志");
        log.error("这是一行 Error 级别日志");

        // 占位符
        String author = "程序员Leo";
        log.info("这是一行带有占位符日志，作者：{}", author);
    }
}
