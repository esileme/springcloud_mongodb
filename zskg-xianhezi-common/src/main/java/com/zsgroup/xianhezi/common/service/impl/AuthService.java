package com.zsgroup.xianhezi.common.service.impl;

import com.zsgroup.xianhezi.common.dao.UserDao;
import com.zsgroup.xianhezi.common.model.UserInfo;
import com.zsgroup.xianhezi.common.service.IAuthService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * =================================
 * <p>
 * Created by yl on 2018-9-20.
 * <p>
 * 描述:
 */

@Service
public class AuthService implements IAuthService {


    @Resource
    private UserDao userDao;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public UserInfo getHybToken(String url, Map<String, Object> params) {

        /*String result = HttpUtils.URLPost(url, params);
        HybTokenBean data = JSON.parseObject(result, HybTokenBean.class);
        if (data.getCode() != 0) {
            throw new BusinessException(data.getCode(), data.getMessage());
        }*/

        Query query = new Query();
        //data.getMobile();
        query.addCriteria(Criteria.where("phone").is("15638179325"));
        UserInfo one = mongoTemplate.findOne(query, UserInfo.class);
        if (one!=null){
            return one;
        }
        //todo create new user

        return null;
        //return new TokenResp(data.getAccessToken(),data.getExpireIn(),data.getMobile(),data.getName(),data.getAvatar());
    }

}
