package com.zsgroup.xianhezi.common.dao;

import com.zsgroup.xianhezi.common.config.Paginate;
import com.zsgroup.xianhezi.common.model.User;

import java.util.List;

public interface UserDao {

	void addUser(User user);

	List<User> queryUser(User user, Paginate paginate);

	long updateUser(User user);

	long deleteUser(User user);

	User save(User bean);
}