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

    SUCCESS(200, "操作成功"),

    CREATED(201, "对象创建成功"),

    ACCEPTED(202, "请求已经被接受"),

    NO_CONTENT(204, "操作已经执行成功，但是没有返回数据"),

    MOVED_PERM(301, "资源已被移除"),

    SEE_OTHER(303, "重定向"),

    NOT_MODIFIED(304, "资源没有被修改"),

    BAD_REQUEST(400, "参数列表错误（缺少，格式不匹配）"),

    UNAUTHORIZED(401, "未授权"),

    FORBIDDEN(403, "访问受限，授权过期"),

    NOT_FOUND(404, "资源，服务未找！"),

    BAD_METHOD(405, "不允许的http方法"),

    CONFLICT(409, "资源冲突，或者资源被锁"),

    UNSUPPORTED_TYPE(415, "不支持的数据，媒体类型"),

    ERROR(500, "系统内部错误"),

    NOT_IMPLEMENTED(501, "接口未实现"),

    WARN(601, "系统警告消息"),

    PARAM_NOT_VALID(701,"参数校验失败"),

    USERNAME_OR_PWD_ERROR(20001, "用户名或密码错误"),

    LOGIN_FAIL(20000, "登录失败,请稍后再试"),

    FORBIDDEN_YAN(20004, "演示账号仅支持查询操作！"),

    USERNAME_NOT_FOUND(20005, "用户名不存在！"),

    CATEGORY_NAME_IS_EXISTED(20006, "该分类已存在，请勿重复添加！"),

    CATEGORY_NAME_NOT_EXISTED(20007,"该分类不存在！" ),

    FILE_UPLOAD_FAILED(20008, "文件上传失败！"),

    TAG_NOT_EXISTED(20009, "标签不存在！" );

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
