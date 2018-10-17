package com.zsgroup.xianhezi.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class ConfigClientController extends BaseController{

	@Value("${spring.cloud.config.profile}")
	private String profile;

	@RequestMapping("/getProfile")
	public Object hello(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		HashMap<String, Object> map = new HashMap<>();
		map.put("profile",profile+"|"+session.getId());
		return map;
	}
}