package org.leocoder.codehub.common.exception;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-18 22:52
 * @description : 通用异常接口
 */
public interface BaseExceptionInterface {
    Integer getErrorCode();

    String getErrorMessage();
}
