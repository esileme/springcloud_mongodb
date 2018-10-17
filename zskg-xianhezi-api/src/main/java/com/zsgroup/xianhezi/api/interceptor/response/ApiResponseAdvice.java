package com.zsgroup.xianhezi.api.interceptor.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@SuppressWarnings("NullableProblems")
@ControllerAdvice(basePackages = "com.zsgroup.xianhezi.api.controller")
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {
    private final static Logger log = LoggerFactory.getLogger(ApiResponseAdvice.class);


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //System.out.println(converterType);
        //log.info(converterType.toString());

        if(converterType.equals(MappingJackson2HttpMessageConverter.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        return ApiResponse.success().setData(body);
    }
}