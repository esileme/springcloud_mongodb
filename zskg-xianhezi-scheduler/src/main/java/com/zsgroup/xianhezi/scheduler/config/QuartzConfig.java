package com.zsgroup.xianhezi.scheduler.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.Scheduler;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz配置类
 * 
 * @author Yang
 * 
 */
@Configuration
public class QuartzConfig {

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(MyJobFactory myJobFactory) throws IOException {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

		schedulerFactoryBean.setQuartzProperties(quartzProperties());
		// 覆盖已存在的任务
		schedulerFactoryBean.setOverwriteExistingJobs(true);
		// 延迟5s启动quartz
		// schedulerFactoryBean.setStartupDelay(5);
		// 自定义Job Factory，用于Spring注入
		schedulerFactoryBean.setJobFactory(myJobFactory);

		return schedulerFactoryBean;
	}

	@Bean
	public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		return scheduler;
	}

	/**
	 * 加载quartz数据源配置
	 * 
	 * @return
	 * @throws IOException
	 */
	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

}
