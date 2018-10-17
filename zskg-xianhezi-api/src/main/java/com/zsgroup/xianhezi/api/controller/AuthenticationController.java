package com.zsgroup.xianhezi.api.controller;


import com.zsgroup.xianhezi.api.security.utils.TokenUtil;
import com.zsgroup.xianhezi.common.RedisKey;
import com.zsgroup.xianhezi.common.config.redis.RedisRepository;
import com.zsgroup.xianhezi.common.vo.req.SmsTokenReq;
import com.zsgroup.xianhezi.security.base.AuthenticationTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Authentication controller.
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    public static final String PHONETYPE = "phoneType";
    public static final String VERSION = "version";
    /**
     * 权限管理
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 用户信息服务
     */
    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * Token工具类
     */
    @Autowired
    private TokenUtil jwtTokenUtil;

    @Autowired
    private RedisRepository redisRepository;


    @PostMapping(value = "/smsToken")
    public Map<String, Object> smsToken(
            @RequestBody SmsTokenReq tokenReq, HttpServletRequest request) {

        //存入 Redis表示此次为验证码验证
        redisRepository.setExpire(RedisKey.SMS_LOGIN_PREFIX + tokenReq.getMobile(), "success", 40L);
        //完成授权
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(tokenReq.getMobile(), tokenReq.getSmsCode())
        );

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails); //生成xToken


        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", token);
        tokenMap.put("expires_in", jwtTokenUtil.getExpiration());
        tokenMap.put("token_type", RedisKey.TOKEN_TYPE_BEARER);

        return tokenMap;
    }

    /**
     * Refresh and get authentication token map.
     *
     * @param request the request
     * @return the map
     */
    @PostMapping(value = "/refresh", produces = "application/json; charset=UTF-8")
    public Map<String, Object> refreshAndGetAuthenticationToken(
            HttpServletRequest request) {

        String tokenHeader = request.getHeader(AuthenticationTokenFilter.TOKEN_HEADER);
        String token = tokenHeader.split(" ")[1];

        //重新生成Token
        String username = jwtTokenUtil.getUsernameFromToken(token);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String refreshedToken = jwtTokenUtil.generateToken(userDetails);

        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", refreshedToken);
        tokenMap.put("expires_in", jwtTokenUtil.getExpiration());
        tokenMap.put("token_type", RedisKey.TOKEN_TYPE_BEARER);

        return tokenMap;
    }

    /**
     * Delete authentication token map.
     *
     * @param request the request
     * @return the map
     */
    @PostMapping(value = "/remove", produces = "application/json; charset=UTF-8")
    public void deleteAuthenticationToken(
            HttpServletRequest request) {

        String tokenHeader = request.getHeader(AuthenticationTokenFilter.TOKEN_HEADER);
        String token = tokenHeader.split(" ")[1];

        //移除token
        jwtTokenUtil.removeToken(token);

    }


}
