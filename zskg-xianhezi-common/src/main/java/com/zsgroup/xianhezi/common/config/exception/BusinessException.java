package com.zsgroup.xianhezi.common.config.exception;

/**
 * 业务异常.
 *
 * @author zhangxd
 */
public class BusinessException extends RuntimeException {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return message;
    }

    public BusinessException(){}


    public BusinessException(ExceptionResultCode exceptionResultCode) {
        super("");
        this.code = exceptionResultCode.getCode();
        this.message = exceptionResultCode.getMsg();
    }

    public BusinessException(Integer code,String msg) {
        super("");
        this.code = code;
        this.message = msg;
    }

    public BusinessException(String msg) {
        super("");
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}