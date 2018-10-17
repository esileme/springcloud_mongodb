package com.zsgroup.xianhezi.common.utils;

public class ErrorConstant {
	/** 成功 **/
	public static final int SUCCESS = 0;
	/** 系统错误 **/
	public static final int SYSTEM_ERROR = 9000;
	/** 系统维护中 **/
	public static final int SYSTEM_MAINTAINING = 9001;
	/** 公共请求参数不对 **/
	public static final int REQUEST_PUBLIC_PARAMETER_INVALID = 9002;
	/** 缺少请求参数 **/
	public static final int REQUEST_PARAMETER_MISSING = 9003;
	/** 请求参数不对 **/
	public static final int REQUEST_PARAMETER_INVALID = 9004;
	/** 请求太频繁，已暂时锁定 **/
	public static final int REQUEST_TOO_MANY_TIMES = 9005;
	/** 不支持的签名方式 **/
	public static final int SIGN_TYPE_NONSUPPORT = 9006;
	/** 签名无效 **/
	public static final int SIGNATURE_INVALID = 9007;
	/** access_token无效 **/
	public static final int ACCESS_TOKEN_INVALID = 9008;
	/** method无效 **/
	public static final int METHOD_INVALID = 9009;
	/** 服务器错误 **/
	public static final int SERVICE_ERROR = 9010;
	public static final int FAILURE = 9011;
	/** access_token获取失败 **/
	public static final int ACCESS_TOKEN_GET_FAILED = 1004;
	/** app_key无效 **/
	public static final int APP_KEY_INVALID = 1005;
	/** 密码输入错误三次，已暂时锁定登录 **/
	public static final int LOGIN_IS_LOCKED = 1006;
	/** 登录密码错误 **/
	public static final int LOGIN_PWD_ERROR = 1007;
	/** 获取验证码失败 **/
	public static final int VERIFY_CODE_GET_ERROR = 1008;
	/** 请登录账户 **/
	public static final int LOGIN_EXPIRED = 1009;
	/** 邀请人是黑名单用户 **/
	public static final int INVITE_USER_ON_THE_BLACKLIST = 1010;
	/** 注册次数超限 **/
	public static final int REGISTER_OVERRUN = 1011;
	/** 用户已存在 **/
	public static final int USER_ALREADY_EXISTS = 1012;
	/** 验证码无效 **/
	public static final int VERIFY_CODE_INVALID = 1013;
	/** 邀请码无效 **/
	public static final int INVITE_CODE_INVALID = 1014;
	/** 修改失败 **/
	public static final int UPDATE_FAILED = 1015;
	/** 创建失败 **/
	public static final int CREATE_FAILED = 1016;
	/** 用户不存在 **/
	public static final int USER_NOT_EXISTS = 1017;
	/** 模板不存在 **/
	public static final int TEMPLATE_NOT_EXISTS = 1018;
	/** 原密码错误 **/
	public static final int OLD_PASSWD_ERROR = 1019;
	/** 用户账户不存在 **/
	public static final int USER_ACCOUNT_NOT_EXISTS = 1022;


	public static String getErrorMsg(int errorCode) {
		switch (errorCode) {
		case SUCCESS:
			return "成功";
		case SYSTEM_ERROR:
			return "系统错误";
		case SYSTEM_MAINTAINING:
			return "系统维护中";
		case REQUEST_PUBLIC_PARAMETER_INVALID:
			return "公共请求参数不对";
		case REQUEST_PARAMETER_MISSING:
			return "缺少请求参数";
		case REQUEST_PARAMETER_INVALID:
			return "请求参数不对";
		case REQUEST_TOO_MANY_TIMES:
			return "请求太频繁，已暂时锁定";
		case SIGN_TYPE_NONSUPPORT:
			return "不支持的签名方式";
		case SIGNATURE_INVALID:
			return "签名无效";
		case ACCESS_TOKEN_INVALID:
			return "access_token无效";
		case METHOD_INVALID:
			return "method无效";
		case SERVICE_ERROR:
			return "服务器错误";
		case FAILURE:
			return "失败";

		case ACCESS_TOKEN_GET_FAILED:
			return "access_token获取失败";
		case APP_KEY_INVALID:
			return "app_key无效";
		case LOGIN_IS_LOCKED:
			return "请一小时后再试，建议您点击“忘记密码”进行密码重置";
		case LOGIN_PWD_ERROR:
			return "登录密码错误";
		case VERIFY_CODE_GET_ERROR:
			return "获取验证码失败";
		case LOGIN_EXPIRED:
			return "请登录账户";
		case INVITE_USER_ON_THE_BLACKLIST:
			return "邀请人是黑名单用户";
		case REGISTER_OVERRUN:
			return "注册次数超限";
		case USER_ALREADY_EXISTS:
			return "用户已存在";
		case VERIFY_CODE_INVALID:
			return "验证码无效";
		case INVITE_CODE_INVALID:
			return "邀请码无效";
		case UPDATE_FAILED:
			return "修改失败";
		case CREATE_FAILED:
			return "创建失败";
		case USER_NOT_EXISTS:
			return "用户不存在";
		case TEMPLATE_NOT_EXISTS:
			return "模板不存在";
		case OLD_PASSWD_ERROR:
			return "原密码错误";
		}
		return "未知错误码";
	}
}
