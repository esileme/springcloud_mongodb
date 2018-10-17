package com.zsgroup.xianhezi.scheduler.job;

import com.zsgroup.xianhezi.common.config.Paginate;
import com.zsgroup.xianhezi.common.model.User;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zsgroup.xianhezi.common.service.IUserService;

import java.util.List;

public class TestJob extends QuartzJobBean {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private IUserService userService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("TestJob");
		Paginate paginate = new Paginate();
		paginate.setCurrentPage(1);
		paginate.setPageSize(10);
        List<User> users = userService.queryUser(new User(), paginate);
        for (User user : users) {
            System.out.println(user.toString());
        }
        System.out.println(redisTemplate);
		System.out.println(userService);
	}

}
