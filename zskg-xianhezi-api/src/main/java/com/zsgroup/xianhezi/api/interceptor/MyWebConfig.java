package com.zsgroup.xianhezi.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsgroup.xianhezi.common.config.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport{

	/**
	 * @Bean 声明bean拦截器中才能自动注入成功
	 * @return
	 */
	@Bean
	public ApiInterceptor getApiInterceptor() {
		return new ApiInterceptor();
	}

	/**
	 * 注册 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//excludePathPatterns 配置拦截器不拦截的地址
		//registry.addInterceptor(getApiInterceptor()).excludePathPatterns("/getProfile","/auth/**");
		//registry.addInterceptor(getWebInterceptor()).addPathPatterns("/bc/**");
	}




	/**
	 * Http message converter http message converter.
	 *
	 * @return the http message converter
	 */
	@Bean
	public HttpMessageConverter httpMessageConverter() {
		return new MappingJackson2HttpMessageConverter(this.objectMapper());
	}

	/**
	 * Object mapper object mapper.
	 *
	 * @return the object mapper
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new JsonMapper();
	}
}
