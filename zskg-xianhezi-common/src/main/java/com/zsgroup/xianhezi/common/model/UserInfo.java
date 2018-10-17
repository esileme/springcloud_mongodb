package com.zsgroup.xianhezi.common.model;

import com.zsgroup.xianhezi.common.config.inject.QueryField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * =================================
 * <p>
 * Created by yl on 2018-9-20.
 * <p>
 * 描述:
 */
@Document(collection = "userInfo")
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String photo;

    private String userId;

    private String openid;

    private String wechatUnionId;

    private String wechatOpenId;

    private String mark;

    private String inviteCode;

    private String username;

    private Integer sex;

    private String telephone;

    @QueryField
    private String phone;

    private String nativePlace;

    private Date createdAt;

    private Date lastLoginTime;

    private String registration;

    private String referId;

    private Double balance;

    private Double intergral;

    private List<String> belong;

    private Integer homeShow;

    private Integer submitShow;

    private Double hyCoin;

    private Object addr;

    private Boolean isRemoved;

    private String password;


}
