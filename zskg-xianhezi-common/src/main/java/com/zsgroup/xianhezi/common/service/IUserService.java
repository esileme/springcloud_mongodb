package com.zsgroup.xianhezi.common.service;

import com.zsgroup.xianhezi.common.config.Paginate;
import com.zsgroup.xianhezi.common.model.User;

import java.util.List;

public interface IUserService {
	void addUser(User user);

	List<User> queryUser(User user, Paginate paginate);

	long updateUser(User user);

	long deleteUser(User user);
}
