package com.zsgroup.xianhezi.api.controller;

import com.zsgroup.xianhezi.api.config.PropertiesConfig;
import com.zsgroup.xianhezi.api.security.utils.TokenUtil;
import com.zsgroup.xianhezi.common.RedisKey;
import com.zsgroup.xianhezi.common.model.UserInfo;
import com.zsgroup.xianhezi.common.service.IAuthService;
import com.zsgroup.xianhezi.common.service.IUserInfoService;
import com.zsgroup.xianhezi.common.vo.req.CodeReq;
import com.zsgroup.xianhezi.common.vo.req.TokenReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * =================================
 * <p>
 * Created by yl on 2018-9-20.
 * <p>
 * 描述:
 */
@RestController
@RequestMapping(path = "/auth")
public class AuthController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    /**
     * 权限管理
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * Token工具类
     */
    @Autowired
    private TokenUtil jwtTokenUtil;


    private static final String GRANT_TYPE = "authorization_code";
    private static final String RESPONSE_TYPE = "code";

    @Autowired
    private IAuthService authService;
    @Autowired
    private IUserInfoService userInfoService;

    @Resource
    private PropertiesConfig config;

    @RequestMapping(path = "/hyb/getCode")
    public Object hybCode(HttpServletRequest request, @RequestBody CodeReq codeReq) {
        HashMap<String, Object> map = new HashMap<>();
        String hybClientId = config.getHybClientId();
        //String hybRedirectUri = config.getHybRedirectUri();

        StringBuilder url = new StringBuilder(config.getHybAuthorizeUrl());
        url.append("?response_type=").append(RESPONSE_TYPE)
                .append("&client_id=").append(hybClientId)
                .append("&redirect_uri=").append(codeReq.getRedirectUri())
                .append("&token=").append(codeReq.getToken());
        map.put("url",url.toString());

        log.info("用户请求华赢宝获取code接口,url:" + url.toString());

        return map;

    }


    @RequestMapping(path = "/hyb/getToken")
    public Object hybGetAccessToken(HttpServletRequest request, @RequestBody TokenReq token) {
        HashMap<String, Object> map = new HashMap<>();
        String hybClientId = config.getHybClientId();
        String hybClientSecret = config.getHybClientSecret();
        String code = token.getCode();
        String hybAccessTokenUrl = config.getHybAccessTokenUrl();
        map.put("client_id", hybClientId);
        map.put("client_secret", hybClientSecret);
        map.put("grant_type", GRANT_TYPE);
        map.put("code", code);
        UserInfo userInfo = authService.getHybToken(hybAccessTokenUrl, map);

        //完成授权
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userInfo.getPhone(), userInfo.getId())
        );

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String tokenStr = jwtTokenUtil.generateToken(userDetails); //生成xToken


        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", tokenStr);
        tokenMap.put("expires_in", jwtTokenUtil.getExpiration());
        tokenMap.put("token_type", RedisKey.TOKEN_TYPE_BEARER);

        return tokenMap;

    }

    @RequestMapping(path = "/queryMyself")
    public Object queryMe() {
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone("15638179325");
        return userInfoService.findOne(userInfo);
    }


}
