package com.game.server.persistence;

import java.util.ArrayList;

import javax.management.relation.Role;

import org.apache.log4j.Logger;

import com.util.LoggerHelper;


public class RolePersistenceService extends Thread{
	
	private static final Logger logger=Logger.getLogger(RolePersistenceService.class);
	
	private static RolePersistenceService instance=new RolePersistenceService();
	
	private volatile boolean shutdowning=false;
	
	private Object lock=new Object();
	
	private RolePersistenceService() {
		setName("RolePersistenceService");
	}
	
	public static RolePersistenceService getInstance() {
		return instance;
	}
	
	private ArrayList<Role> saveRoles=new ArrayList<Role>();
	
	
	@Override
	public void run() {
		while (!shutdowning) {
			try {
				ArrayList<Role> temp=null;
				synchronized (lock) {
					if(saveRoles.size()>0) {
						temp=new ArrayList<Role>(saveRoles);
						saveRoles.clear();
					}
				}
				if(temp!=null&&temp.size()>0) {
					for (Role role : temp) {
						try {
						} catch (Exception e) {
							LoggerHelper.dealExceptionError(logger, e);
						}
					}
				} else {
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				LoggerHelper.dealExceptionError(logger, e);
			}
		}
	}
	
	public void save(Role role) {
		synchronized (lock) {
			if(!saveRoles.contains(role)) {
				saveRoles.add(role);
			}
		}
	}

	
	public void shutdown() {
		shutdowning=true;
		ArrayList<Role> temp = null;
		synchronized (lock) {
			if (saveRoles.size() > 0) {
				temp = new ArrayList<Role>(saveRoles);
				saveRoles.clear();
			}
		}
		if (temp != null && temp.size() > 0) {
			for (Role role : temp) {
				try {
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
			}
			temp.clear();
		}
	}
	
	
}
