package com.zsgroup.xianhezi.api.interceptor.response;


import com.zsgroup.xianhezi.common.config.exception.BusinessException;
import com.zsgroup.xianhezi.common.config.exception.ExtendException;
import com.zsgroup.xianhezi.common.utils.ErrorConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);


    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = ExtendException.class)
    public ApiResponse errorHandler(ExtendException ex) {
        logger.info("ExtendException:{}:{}",ex.getCode(),ex.getMsg());
        ApiResponse error=ApiResponse.error();
        error.setCode(ex.getCode());
        error.setMsg(ex.getMsg());
        return error;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ApiResponse handleUserExistException(BusinessException ex) {
        logger.info("BusinessException:{}:{}",ex.getCode(),ex.getMsg());
        ApiResponse error=ApiResponse.error();
        error.setCode(ex.getCode());
        error.setMsg(ex.getMsg());
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse handleUserExistException(Exception ex) {

        if(ex instanceof ExtendException){
            ExtendException e = (ExtendException)ex;
            logger.info("ExtendException:{}:{}",e.getCode(),e.getMsg());
            ApiResponse error=ApiResponse.error();
            error.setCode(e.getCode());
            error.setMsg(e.getMsg());
            return error;
        }else {
            ex.printStackTrace();
            ApiResponse error=ApiResponse.error();
            error.setCode(ErrorConstant.SYSTEM_ERROR);
            error.setMsg("系统错误");
            return error;
        }

    }





}
