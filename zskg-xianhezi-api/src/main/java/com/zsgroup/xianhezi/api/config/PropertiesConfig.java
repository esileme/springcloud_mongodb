package com.zsgroup.xianhezi.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * =================================
 * <p>
 * Created by yl on 2018-9-20.
 * <p>
 * 描述:
 */
@Data
@Configuration
@PropertySource(value = "classpath:${spring.cloud.config.profile}/config.properties")
public class PropertiesConfig {

    @Value("${huayingbao.client.id}")
    private String hybClientId;

    @Value("${huayingbao.redirect_uri}")
    private String hybRedirectUri;

    @Value("${huayingbao.client_secret}")
    private String hybClientSecret;

    @Value("${huayingbao.accesstoken.url}")
    private String hybAccessTokenUrl;


    @Value("${huayingbao.authorize.url}")
    private String hybAuthorizeUrl;

    @Value("${huayingbao.api.url}")
    private String hybApiUrl;


}
