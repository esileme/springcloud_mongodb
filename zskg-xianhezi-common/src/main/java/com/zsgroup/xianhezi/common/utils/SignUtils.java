package com.zsgroup.xianhezi.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class SignUtils {
	private final static Logger log = LoggerFactory.getLogger(SignUtils.class);


	public static String encodeToUtf8(String value) {
		try {
			value = URLEncoder.encode(value, "utf-8").replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String decodeFromUtf8(String value) {
		try {
			value = URLDecoder.decode(value.replaceAll("%20", "+"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成签名
	 * 
	 * @param params
	 * @param
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String createSign(Map<String, Object> params, String md5Key) {
		String signStr = generateSignStr(params, md5Key);
		String sign = DigestUtils.md5Hex(signStr);
		return sign;
	}

	/**
	 * 生成签名原串
	 * 
	 * @param params
	 * @param md5Key
	 * @return
	 */
	private static String generateSignStr(Map<String, Object> params, String md5Key) {
		params.remove("sign");
		ArrayList<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuffer sb = new StringBuffer();
		for (String key : keys) {
			Object value = params.get(key);
			if (value != null) {
				if (sb.length() > 0)
					sb.append("&");
				sb.append(key).append("=").append(encodeToUtf8(value.toString()));
			}
		}
		sb.append("&key=");
		sb.append(md5Key);
		return sb.toString();
	}

	/**
	 * 校验客户端sign
	 * 
	 * @param parameters
	 * @return
	 */
	public static boolean checkSign(LinkedHashMap<String, Object> parameters, String key) {
		String sign = (String) parameters.get("sign");
		parameters.remove("sign");
		String signStr = generateSignStr(parameters, key);
		String sign1 = createSign(parameters, key);
		if (!sign.equals(sign1)) {
			log.warn(String.format("签名[%s]校验不通过，原串=%s，实际值=%s", sign, signStr, sign1));
			return false;
		}
		return true;
	}

	/*public static void main(String[] args) {
		//System.out.println(getRandomString(32));
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("method","user/queryUser");
		map.put("timestamp",1500000000000L);
		map.put("nonce","45asd45asdf412sew532d5d2a3add2d2");
		map.put("version",1.0);
		map.put("device_type",1);
		map.put("channel",1);
		map.put("sign_type",1);
		map.put("sign","b75d9859c3d2e15849b70e021172a297");
		String signStr = generateSignStr(map, "cu89eMgDkGjo8HC6TzYt6jBqkEZGvHEJ");
		System.out.println(signStr);
		String md5Key = createSign(map, "cu89eMgDkGjo8HC6TzYt6jBqkEZGvHEJ");
		System.out.println(md5Key);


	}*/
}
