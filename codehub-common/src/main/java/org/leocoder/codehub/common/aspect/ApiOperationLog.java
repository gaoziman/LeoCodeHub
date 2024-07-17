package org.leocoder.codehub.common.aspect;

import java.lang.annotation.*;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-17 22:14
 * @description : API 操作日志注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {

    /**
     * API 功能描述
     *
     * @return
     */
    String description() default "";
}

