package com.zsgroup.xianhezi.common;

/**
 * @author huzj
 */
public class RedisKey {

    //短信redis缓存前缀
    public static final String CAPTCHA_KEY = "captcha_";


    //Token 类型
    public static final String TOKEN_TYPE_BEARER = "Bearer";

    //权限缓存前缀
    public static final String REDIS_PREFIX_AUTH = "auth:";

    //用户信息缓存前缀
    public static final String REDIS_PREFIX_USER = "user-details:";

    //短信登录标示
    public static final String SMS_LOGIN_PREFIX = "smslogin_";

    //人脸识别前缀
    public static final String FACE_AUTH_PREFIX = "face_auth_";

    //bind
    public static final String BIND_TRANS_ID_PREFIX = "bind_trans_id_";

    //银行信息前缀
    public static final String REDIS_PREFIX_BANK = "bank_info_";

    //魔盒任务前缀
    public static final String MOHE_TASK_PREFIX = "mohetask_";

    public static final String MOHE_TASK_WAIT_NOTIFY_PREFIX = "mohe_task_wait_notify_";

    //重置密码前缀
    public static final String REDIS_RESET_PREFIX = "resetpass_";


    //信用卡认证前缀
    public static final String CREDIT_AUTH = "credit_auth_";

    //首单前缀
    public static final String IS_FIRST = "is_first_";


    public static final String ZHIMA_TASK = "zhima_task_";

    //运行商回调
    public static final String MOHE_NOT_NOTIFY = "mohe_not_notify";
    
    //短信redis缓存前缀
    public static final String KDCREDIT_KEY = "KDCREDIT_";
    //手机列表前缀
    public static final String PHONE_LIST_PREFIX = "phone_list_";


}
