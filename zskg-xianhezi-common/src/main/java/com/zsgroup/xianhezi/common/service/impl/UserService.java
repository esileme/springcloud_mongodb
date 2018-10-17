package com.zsgroup.xianhezi.common.service.impl;

import com.zsgroup.xianhezi.common.config.Paginate;
import com.zsgroup.xianhezi.common.dao.UserDao;
import com.zsgroup.xianhezi.common.model.User;
import com.zsgroup.xianhezi.common.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userService")
public class UserService implements IUserService {
	private static Log logger = LogFactory.getLog(UserService.class);

	@Autowired
	private UserDao userDao;

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public List<User> queryUser(User user, Paginate paginate) {
		return userDao.queryUser(user, paginate);
	}

	public long updateUser(User user) {
		return userDao.updateUser(user);
	}

	public long deleteUser(User user) {
		long deleteCount = userDao.deleteUser(user);
		logger.info("删除了" + deleteCount + "条记录");
		return deleteCount;
	}

}
