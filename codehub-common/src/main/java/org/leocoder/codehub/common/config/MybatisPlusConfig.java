package org.leocoder.codehub.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-20 01:34
 * @description :
 */
@Configuration
@MapperScan("org.leocoder.codehub.common.mapper")
public class MybatisPlusConfig {
}