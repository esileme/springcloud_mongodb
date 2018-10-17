package com.zsgroup.xianhezi.api.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zsgroup.xianhezi.api.utils.Constant;
import com.zsgroup.xianhezi.common.config.exception.BusinessException;
import com.zsgroup.xianhezi.common.config.exception.ExceptionResultCode;
import com.zsgroup.xianhezi.common.config.exception.ExtendException;
import com.zsgroup.xianhezi.common.utils.SignUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

public class ApiInterceptor implements HandlerInterceptor {
    private final static Logger log = LoggerFactory.getLogger(ApiInterceptor.class);
    @Autowired
    protected RedisTemplate<String, String> redisTemplate;
    @Autowired
    protected Environment environment;

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception exception) throws Exception {
        log.debug("ApiInterceptor afterCompletion");
        printResponseLog(request);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.debug("ApiInterceptor postHandle");
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.debug("ApiInterceptor preHandle");
        return request(request, response);
    }

    private boolean checkParameters(HttpServletRequest request, HttpServletResponse response) {
        if (!checkRequestFrequency(request)) {
            return false;
        }
        LinkedHashMap<String, Object> parameters = getParameters(request);

        if (!SignUtils.checkSign(parameters, Constant.MD5_KEY)) {
            printResponseLog(request);
            throw new BusinessException(ExceptionResultCode.SIGNATURE_INVALID);
        }

        //只做签名校验用，使用security做权限控制
        /*String method = request.getHeader("method");
        //过滤不登陆接口
        if (method.equals(Constant.USER_LOGIN) || method.equals(Constant.USER_SMS_LOGIN)
                || method.equals(Constant.SYSTEM_PROFILE)) {
            return true;
        }
        HttpSession session = request.getSession();

        String sessionStr = session.getId();
        if (sessionStr == null) {
            printResponseLog(request);
            throw new BusinessException(ExceptionResultCode.ACCESS_TOKEN_INVALID);
        }

        Object user = session.getAttribute(Constant.SESSION_USER_KEY + session.getId());
        if (user == null) {
            printResponseLog(request);
            throw new BusinessException(ExceptionResultCode.LOGIN_EXPIRED);
        }*/
        return true;
    }

    private LinkedHashMap<String, Object> getParameters(HttpServletRequest request) {

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        String method = request.getHeader("method");
        String timestamp = request.getHeader("timestamp");
        String nonce = request.getHeader("nonce");
        String version = request.getHeader("version");
        String device_type = request.getHeader("device_type");
        String channel = request.getHeader("channel");
        String sign_type = request.getHeader("sign_type");
        String sign = request.getHeader("sign");
        map.put("method", method);
        map.put("timestamp", timestamp);
        map.put("nonce", nonce);
        map.put("version", version);
        map.put("device_type", device_type);
        map.put("channel", channel);
        map.put("sign_type", sign_type);
        map.put("sign", sign);

        log.info("请求头参数：" + JSONObject.toJSONString(map));

        if (StringUtils.isBlank(method) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce)
                || StringUtils.isBlank(version) || StringUtils.isBlank(device_type) || StringUtils.isBlank(sign_type)
                || StringUtils.isBlank(sign)) {
            printResponseLog(request);
            throw new BusinessException(ExceptionResultCode.INVILID_HEADER);
        }

        String action = request.getRequestURI();
        if (!StringUtils.contains(action, method)) {
            printResponseLog(request);
            throw new BusinessException(ExceptionResultCode.METHOD_INVALID);
        }

        return map;
    }

    private boolean checkRequestFrequency(HttpServletRequest request) {
        boolean rfSwitch = Boolean.valueOf(environment.getProperty("request.frequency.switch", "true"));
        if (rfSwitch) {
            String ip = getIp(request);
            String key = "accesscheck_" + ip;
            String lockKey = "accesslock_" + ip;
            int limitCount = Integer.valueOf(environment.getProperty("request.frequency.maxcount", "60"));
            long timeout = Long.valueOf(environment.getProperty("request.frequency.time.interval", "2"));
            long timelock = Integer.valueOf(environment.getProperty("request.frequency.time.locked", "300"));
            ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
            String lockCount = opsForValue.get(lockKey);
            if (StringUtils.isNotBlank(lockCount)) {
                log.error("ip:" + ip + ",在" + timeout + "秒内连续请求次数达到" + lockCount + "次,已锁定" + timelock + "秒");
                printResponseLog(request);
                throw new ExtendException(ExceptionResultCode.USER_LOCKED);
            }
            String countStr = opsForValue.get(key);
            Integer count = 0;
            if (StringUtils.isNotBlank(countStr)) {
                count = Integer.valueOf(countStr);
            }
            count++;
            opsForValue.set(key, count + "", timeout, TimeUnit.SECONDS);
            if (count > limitCount) {
                log.error("ip:" + ip + ",在" + timeout + "秒内连续请求次数达到" + limitCount + "次,已锁定" + timelock + "秒");
                opsForValue.set(lockKey, limitCount + "", timelock, TimeUnit.SECONDS);
                printResponseLog(request);
                throw new ExtendException(ExceptionResultCode.REQUEST_TOO_MANY_TIMES);
            }
        }
        return true;
    }

    protected String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null)
            ip = request.getRemoteAddr();
        return ip;
    }

    public boolean request(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("startTimeLong", System.currentTimeMillis());
        String ip = getIp(request);
        String action = request.getRequestURI();
        String sessionId = request.getSession().getId();
        log.info("--------------Api请求开始,action:" + action + ",ip:" + ip + ",sessionId:" + sessionId + "--------------");

        return checkParameters(request, response);
    }

    private void printResponseLog(HttpServletRequest request) {

        long startTimeLong = (Long) request.getAttribute("startTimeLong");
        log.info("--------------Api请求结束,action:" + request.getRequestURI() + ",ip:" + getIp(request) + ",耗时:"
                + (System.currentTimeMillis() - startTimeLong) + "毫秒--------------");

    }

}
