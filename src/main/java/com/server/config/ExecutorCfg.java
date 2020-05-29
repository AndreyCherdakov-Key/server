package com.server.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ExecutorCfg {

	/*
	 * I create an executor bean and configure parameters
	 * Async annotation will use these settings
	 */
	@Bean(name = "CustomAsyncExecutor")
	public Executor customThreadPoolTaskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(50);
	    executor.setMaxPoolSize(100);
	    executor.setQueueCapacity(1000);
	    executor.setThreadNamePrefix("Async-Thread-");
	    /*
	     * Waiting for all threads to complete if a shutdown request arrives
	     */
	    executor.setWaitForTasksToCompleteOnShutdown(true);
	    executor.initialize();
	    return executor;
	}
	
}
