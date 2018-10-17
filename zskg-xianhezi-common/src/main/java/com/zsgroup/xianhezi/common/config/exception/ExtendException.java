package com.zsgroup.xianhezi.common.config.exception;

public class ExtendException extends  RuntimeException {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return message;
    }


    public ExtendException(){

    }

    public ExtendException(ExceptionResultCode exceptionResultCode) {
        super("");
        this.code = exceptionResultCode.getCode();
        this.message = exceptionResultCode.getMsg();
    }

    public ExtendException(int code,String mas) {
        super("");
        this.code = code;
        this.message = mas;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    //隐藏自定义异常堆栈信息
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }




}
