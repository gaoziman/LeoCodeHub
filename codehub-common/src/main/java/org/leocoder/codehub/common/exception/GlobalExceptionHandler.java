package org.leocoder.codehub.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.leocoder.codehub.common.enums.HttpStatusEnum;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-18 22:50
 * @description : 全局异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义业务异常
     *
     * @param request 请求
     * @param e       异常
     * @return Result
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result<String> exceptionHandler(HttpServletRequest request, BizException e) {
        log.warn("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getErrorCode(), e.getErrorMsg());
        return Result.fail(e.getErrorCode(), e.getErrorMsg());
    }


    /**
     * 处理空指针的异常
     *
     * @param request 请求
     * @param e       异常
     * @return Result
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result<String> exceptionHandler(HttpServletRequest request, NullPointerException e) {
        log.error(" {} 发生空指针异常！原因是:", e.getMessage());
        return Result.fail(HttpStatusEnum.BAD_REQUEST);
    }

    /**
     * 处理其他异常
     *
     * @param request 请求
     * @param e       异常
     * @return Result
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("发生未知异常！原因是:", e);
        return Result.fail(HttpStatusEnum.ERROR);
    }

}
