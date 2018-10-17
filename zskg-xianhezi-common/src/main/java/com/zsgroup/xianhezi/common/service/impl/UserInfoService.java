package com.zsgroup.xianhezi.common.service.impl;

import com.zsgroup.xianhezi.common.config.exception.BusinessException;
import com.zsgroup.xianhezi.common.config.exception.ExceptionResultCode;
import com.zsgroup.xianhezi.common.dao.UserInfoDao;
import com.zsgroup.xianhezi.common.model.UserInfo;
import com.zsgroup.xianhezi.common.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * =================================
 * <p>
 * Created by yl on 2018-9-21.
 * <p>
 * 描述:
 */
@Service
public class UserInfoService implements IUserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findOne(UserInfo userInfo) {
        userInfo = userInfoDao.findOne(userInfo);
        if (userInfo==null){
            throw new BusinessException(ExceptionResultCode.LOGIN_EXPIRED);
        }
        return userInfo;
    }
}
