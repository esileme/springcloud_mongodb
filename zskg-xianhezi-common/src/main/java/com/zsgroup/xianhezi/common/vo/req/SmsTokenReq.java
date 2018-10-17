package com.zsgroup.xianhezi.common.vo.req;/**
 * =================================
 * <p>
 * Created by Administrator on 2018/9/24.
 * <p>
 * 描述:
 */


import lombok.Data;

/**
 * @author
 * @create 2018-09-24 18:00
 **/
@Data
public class SmsTokenReq {

    private String mobile;

    private String smsCode;

}
