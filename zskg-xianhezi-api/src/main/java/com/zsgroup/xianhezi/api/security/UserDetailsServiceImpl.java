package com.zsgroup.xianhezi.api.security;

import com.zsgroup.xianhezi.api.security.model.AuthUserFactory;
import com.zsgroup.xianhezi.common.RedisKey;
import com.zsgroup.xianhezi.common.config.redis.RedisRepository;
import com.zsgroup.xianhezi.common.model.UserInfo;
import com.zsgroup.xianhezi.common.service.IUserInfoService;
import com.zsgroup.xianhezi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Security User Detail Service
 */
@Service("userDetailServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String loginName) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(loginName);
        userInfo = userInfoService.findOne(userInfo);

        String isSmsLogin = redisRepository.get(RedisKey.SMS_LOGIN_PREFIX + loginName);
        if (!StringUtils.isNull(isSmsLogin)) {
            //短信登录
            userInfo.setPassword(passwordEncoder.encode("111111"));
            //短信登录成功获取后删除缓存 避免脏数据
            redisRepository.del(RedisKey.SMS_LOGIN_PREFIX+loginName);
        } else {
            //授权登录
            userInfo.setPassword(passwordEncoder.encode(userInfo.getId()));
        }

        if (userInfo == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", loginName));
        } else {
            return AuthUserFactory.create(userInfo);
        }
    }
}
