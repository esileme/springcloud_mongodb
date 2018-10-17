package com.zsgroup.xianhezi.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

	@Value("${spring.cloud.config.profile}")
	private String profile;

	@RequestMapping("/getProfile")
	public String hello(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		String str = (String) session.getAttribute("user");
		if (str == null) {
			session.setAttribute("user", "user");
		}
		System.out.println(str);
		return this.profile + "|" + session.getId();
	}
}