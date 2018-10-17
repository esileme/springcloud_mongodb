package com.zsgroup.xianhezi.common.exception;

public enum ExceptionResultCode {


    //系统相关状态9000
    SYS_PARAM_ERROR(9000, "参数不正确"),
    SYS_CONF_ERROR(9001, "获取系统配置异常"),
    SYS_ERROR(9002, "系统异常"),
    INVILID_HEADER(9003, "请求头不正确"),
    USER_LOCKED(9004, "用户被锁定"),
    REQUEST_TOO_MANY_TIMES(9005, "请求太频繁，已暂时锁定"),
    METHOD_INVALID(9006, "method无效"),
    ACCESS_TOKEN_INVALID(9007, "access_token无效"),
    LOGIN_EXPIRED(9008, "请登录账户"),
    SIGNATURE_INVALID(9009, "签名无效"),



    ;


    private String msg;
    private int code;

    ExceptionResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ExceptionResultCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }
}
