package com.zsgroup.xianhezi.api.controller;

import com.zsgroup.xianhezi.common.config.Paginate;
import com.zsgroup.xianhezi.common.model.User;
import com.zsgroup.xianhezi.common.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Resource
	private IUserService userService;

	// 内部服务访问负载均衡
	@Resource
	private RestTemplate restTemplate;

	@RequestMapping(path = "/addUser")
	public void addUser() {
		User user = new User();
		//user.setId("test123");
		user.setUsername("ylylyyl");
		user.setCreatedAt(new Date());
		userService.addUser(user);
	}

	@RequestMapping(path = "/queryUser")
	public Object queryUser() {
		Paginate paginate = new Paginate();
		paginate.setCurrentPage(1);
		paginate.setPageSize(10);
		User user = new User();
		return userService.queryUser(user, paginate);
	}
}