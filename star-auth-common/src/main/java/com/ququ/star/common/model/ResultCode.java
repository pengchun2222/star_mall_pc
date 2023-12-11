package com.ququ.star.common.model;


/**
 * @Author: 彭淳
 * @Date: 2023/11/21 11:01
 */
public enum ResultCode implements IErrorCode{

    CLIENT_AUTHENTICATION_FAILED(1001,"客户端认证失败"),

    USERNAME_OR_PASSWORD_ERROR(1002,"用户名或密码错误"),

    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式"),

    NO_PERMISSION(1005,"无权限访问！"),

    INVALID_TOKEN(1004,"无效的token"),

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    INSUFFICIENT_STOCK(500, "库存不足"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");




    private final int code;

    private final String message;

    private ResultCode(int code,String message){
        this.code=code;
        this.message=message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
