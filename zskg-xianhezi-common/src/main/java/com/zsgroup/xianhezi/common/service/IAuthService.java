package com.zsgroup.xianhezi.common.service;

import com.zsgroup.xianhezi.common.model.UserInfo;

import java.util.Map;

public interface IAuthService {

    UserInfo getHybToken(String url, Map<String, Object> params);

}
