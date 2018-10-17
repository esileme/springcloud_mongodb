package com.zsgroup.xianhezi.common.bean;

import lombok.Data;

/**
 * =================================
 * <p>
 * Created by yl on 2018-9-20.
 * <p>
 * 描述:
 */
@Data
public class HybTokenBean {

    private Integer code;

    private String message;

    private Long timestamp;

    private String sign;

    private String accessToken;

    private Integer expireIn;

    private String mobile;

    private String name;

    private String avatar;


}
