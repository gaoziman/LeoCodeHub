package org.leocoder.codehub.common.enums;

import lombok.Getter;
import org.leocoder.codehub.common.exception.BaseExceptionInterface;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-18 22:03
 * @description : HttpStatus枚举类
 */
@Getter
public enum HttpStatusEnum implements BaseExceptionInterface {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 对象创建成功
     */
    CREATED(201, "对象创建成功"),
    /**
     * 请求已经被接受
     */
    ACCEPTED(202, "请求已经被接受"),
    /**
     * 操作已经执行成功，但是没有返回数据
     */
    NO_CONTENT(204, "操作已经执行成功，但是没有返回数据"),
    /**
     * 资源已被移除
     */
    MOVED_PERM(301, "资源已被移除"),
    /**
     * 重定向
     */
    SEE_OTHER(303, "重定向"),
    /**
     * 资源没有被修改
     */
    NOT_MODIFIED(304, "资源没有被修改"),
    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    BAD_REQUEST(400, "参数列表错误（缺少，格式不匹配）"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权"),
    /**
     * 访问受限，授权过期
     */
    FORBIDDEN(403, "访问受限，授权过期"),
    /**
     * 资源，服务未找到
     */
    NOT_FOUND(404, "资源，服务未找！"),
    /**
     * 不允许的http方法
     */
    BAD_METHOD(405, "不允许的http方法"),
    /**
     * 资源冲突，或者资源被锁
     */
    CONFLICT(409, "资源冲突，或者资源被锁"),
    /**
     * 不支持的数据，媒体类型
     */
    UNSUPPORTED_TYPE(415, "不支持的数据，媒体类型"),
    /**
     * 系统内部错误
     */
    ERROR(500, "系统内部错误"),
    /**
     * 接口未实现
     */
    NOT_IMPLEMENTED(501, "接口未实现"),
    /**
     * 系统警告消息
     */
    WARN(601, "系统警告消息"),

    PARAM_NOT_VALID(701,"参数校验失败"), USERNAME_OR_PWD_ERROR(20001, "用户名或密码错误"),

    LOGIN_FAIL(20000, "登录失败,请稍后再试"),

    FORBIDDEN_YAN(20004, "演示账号仅支持查询操作！"), USERNAME_NOT_FOUND(20005, "用户名不存在！");

    private final Integer code;
    private final String message;

    HttpStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
