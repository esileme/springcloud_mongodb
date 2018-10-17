package com.zsgroup.xianhezi.common.dao;

import com.zsgroup.xianhezi.common.model.UserInfo;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * =================================
 * <p>
 * Created by yl on 2018-9-21.
 * <p>
 * 描述:
 */
public interface UserInfoDao {

    UserInfo save(UserInfo bean);

    void deleteById(UserInfo userInfo);

    void deleteByCondition(UserInfo userInfo);

    void update(Query query, Update update);

    void updateById(String id, UserInfo userInfo);

    List<UserInfo> find(Query query);

    List<UserInfo> findByCondition(UserInfo userInfo);

    UserInfo findOne(Query query);

    UserInfo findOne(UserInfo userInfo);

    UserInfo get(String id);

    UserInfo get(String id, String collectionName);

}
