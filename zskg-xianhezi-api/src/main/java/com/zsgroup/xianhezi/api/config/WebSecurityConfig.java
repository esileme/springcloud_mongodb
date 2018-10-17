package com.zsgroup.xianhezi.api.config;

import com.zsgroup.xianhezi.security.config.AbstractWebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

/**
 * spring-security配置
 *
 */
@Order(1)
@Configuration
public class WebSecurityConfig extends AbstractWebSecurityConfig {

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略权限校验的访问路径
        web
                .ignoring()
                .antMatchers(
                        "/hello",
                        "/favicon.ico",
                        "/swagger**/**",
                        "/*/api-docs",
                        "/webjars/**",
                        "/*/sms/**",
                        "/notify/**",
                        "/*.html",
                        "/*/*.css",
                        "/*/*.png",
                        "/*/*.js",
                        "/*/*.jpg"
                )
                .antMatchers(HttpMethod.POST, "/*/user")
        ;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/**", "/notify/*").permitAll();
        super.configure(security);
    }
}
