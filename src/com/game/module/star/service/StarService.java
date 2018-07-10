package com.game.module.star.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.persistence.login.bean.ServerEntity;
import com.persistence.login.dao.ServerEntityDao;

public class StarService {
	
	private static StarService instance=new StarService();
	
	public static StarService getInstance() {
		return instance;
	}
	
	private StarService() {
		
	}
	
	private ServerEntityDao dao=new ServerEntityDao();
	
	private List<ServerEntity> dataList=new ArrayList<ServerEntity>();
	
	public void init() {
		List<ServerEntity> selectAll = dao.selectAll();
		Collections.sort(selectAll, new Comparator<ServerEntity>() {
			@Override
			public int compare(ServerEntity o1, ServerEntity o2) {
				return o1.getTime()>o2.getTime()?1:-1;
			}
		});
		dataList.clear();
		dataList.addAll(selectAll);
	}
	
	public List<ServerEntity> getAllStarEntity() {
		return dataList;
	}
	
	public List<ServerEntity> getStarEntityListByVersion(String version) {
		List<ServerEntity> dataList=new ArrayList<ServerEntity>();
		for (ServerEntity serverEntity : this.dataList) {
			if(serverEntity.getVersion().equals(version)) {
				dataList.add(serverEntity);
			}
		}
		return dataList;
	}
	
	public ServerEntity getServerById(int id) {
		for (ServerEntity entity : dataList) {
			if(entity.getId()==id) {
				return entity;
			}
		}
		return null;
	}
	
}
