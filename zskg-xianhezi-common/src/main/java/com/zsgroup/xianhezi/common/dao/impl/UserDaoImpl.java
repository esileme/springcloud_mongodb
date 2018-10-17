package com.zsgroup.xianhezi.common.dao.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zsgroup.xianhezi.common.config.Paginate;
import com.zsgroup.xianhezi.common.dao.UserDao;
import com.zsgroup.xianhezi.common.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userDao")
public class UserDaoImpl extends MongodbBaseDao<User> implements UserDao {
	private static Log logger = LogFactory.getLog(UserDaoImpl.class);


	public void addUser(User user) {
		mongoTemplate.insert(user);
		logger.info("新增用户成功：user=" + user.getId());
	}

	public List<User> queryUser(User user, Paginate paginate) {
		Query query = new Query();
		if (user.getId() != null && !"".equals(user.getId()))
			query.addCriteria(Criteria.where("id").is(user.getId()));
		if (user.getUsername() != null && !"".equals(user.getUsername()))
			query.addCriteria(Criteria.where("username").is(user.getUsername()));
		query.with(new Sort(Sort.Direction.DESC, "createAt"));
		query.skip(paginate.getOffset());
		query.limit(paginate.getPageSize());
		return mongoTemplate.find(query, User.class);
	}

	public long updateUser(User user) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(user.getId()));
		Update update = new Update();
		update.set("username", user.getUsername());
		UpdateResult updateFirst = mongoTemplate.updateFirst(query, update, User.class);
		return updateFirst.getModifiedCount();
	}

	public long deleteUser(User user) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(user.getId()));
		DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
		return deleteResult.getDeletedCount();
	}

}
