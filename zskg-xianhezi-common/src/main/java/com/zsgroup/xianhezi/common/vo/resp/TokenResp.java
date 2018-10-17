package com.zsgroup.xianhezi.common.vo.resp;

import lombok.Data;

/**
 * =================================
 * <p>
 * Created by yl on 2018-9-20.
 * <p>
 * 描述:
 */

@Data
public class TokenResp {

    private String accessToken;

    private Integer expireIn;

    private String mobile;

    private String name;

    private String avatar;

    public TokenResp(String accessToken, Integer expireIn, String mobile, String name, String avatar) {
        this.accessToken = accessToken;
        this.expireIn = expireIn;
        this.mobile = mobile;
        this.name = name;
        this.avatar = avatar;
    }
}
