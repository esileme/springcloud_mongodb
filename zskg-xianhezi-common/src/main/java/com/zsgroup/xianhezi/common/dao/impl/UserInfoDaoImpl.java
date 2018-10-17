package com.zsgroup.xianhezi.common.dao.impl;

import com.zsgroup.xianhezi.common.config.redis.RedisRepository;
import com.zsgroup.xianhezi.common.dao.UserInfoDao;
import com.zsgroup.xianhezi.common.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * =================================
 * <p>
 * Created by yl on 2018-9-21.
 * <p>
 * 描述:
 */

@Component("userInfoDao")
public class UserInfoDaoImpl extends MongodbBaseDao<UserInfo> implements UserInfoDao {

    @Autowired
    private RedisRepository redis;

    private String getId(){

        redis.incr("");
        return null;

    }




}
