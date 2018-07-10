package com.game.server.persistence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 登陆线程池服务
 * @author Administrator
 *
 */
public class LoginPersistenceService {
	
	private static LoginPersistenceService instance=new LoginPersistenceService();
	
	private ExecutorService LoginThreadPool;
	
	
	private LoginPersistenceService() {
		LoginThreadPool=Executors.newScheduledThreadPool(1000);
	}
	
	public static LoginPersistenceService getInstance() {
		return instance;
	}
	
	public void addLoginTask(Runnable task) {
		LoginThreadPool.submit(task);
	}
	
	public void shutdown() {
		LoginThreadPool.shutdown();
	}
	
	
	
}
