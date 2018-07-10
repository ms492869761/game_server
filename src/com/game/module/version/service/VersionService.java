package com.game.module.version.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.persistence.login.bean.DataClearVersionEntity;
import com.persistence.login.bean.DataPackEntity;
import com.persistence.login.dao.DataClearVersionEntityDao;
import com.persistence.login.dao.DataPackEntityDao;
import com.persistence.login.mapp.DataPackEntityMapper;

public class VersionService {
	
	private static VersionService instance=new VersionService();
	
	public static VersionService getInstance() {
		return instance;
	}
	
	private VersionService() {
		
	}
	
	private Map<String, DataPackEntity> preVersionMap=new HashMap<String, DataPackEntity>();
	
	private Map<String, DataPackEntity> versionMap=new HashMap<String,DataPackEntity>();
	
	private Map<String,DataClearVersionEntity> clearVersionMap=new HashMap<String,DataClearVersionEntity>();
	
	private DataPackEntityDao dataPackEntityDao=new DataPackEntityDao();
	
	private DataClearVersionEntityDao dataClearVersionEntityDao=new DataClearVersionEntityDao();
	
	public void init() {
		preVersionMap.clear();
		versionMap.clear();
		List<DataPackEntity> selectAll = dataPackEntityDao.selectAll();
		for (DataPackEntity dataPackEntity : selectAll) {
			preVersionMap.put(dataPackEntity.getPreVersion(), dataPackEntity);
			versionMap.put(dataPackEntity.getVersion(), dataPackEntity);
		}
		
		clearVersionMap.clear();
		List<DataClearVersionEntity> selectAll2 = dataClearVersionEntityDao.selectAll();
		for (DataClearVersionEntity dataClearVersionEntity : selectAll2) {
			clearVersionMap.put(dataClearVersionEntity.getVersion(), dataClearVersionEntity);
		}
	}
	
	public List<DataPackEntity> getVersionList(String version) {
		List<DataPackEntity> dataList = new ArrayList<DataPackEntity>();
		for (String dataVersion : versionMap.keySet()) {
			if (checkAppVersion(dataVersion, version)) {
				dataList.add(versionMap.get(dataVersion));
			}
		}
		return dataList;
	}
	
	public boolean isClearVersion(String version) {
		return clearVersionMap.containsKey(version);
	}
	
	public Map<String, DataClearVersionEntity> getMainVersion() {
		return clearVersionMap;
	}
	
	public void addClearVersion(String version, int isUse, String url) {
		if (clearVersionMap.containsKey(version)) {
			return;
		}
		DataClearVersionEntity entity = new DataClearVersionEntity();
		entity.setVersion(version);
		entity.setIsUse(isUse);
		entity.setUpdateUrl(url);
		clearVersionMap.put(version, entity);
		dataClearVersionEntityDao.insert(entity);
	}


	public void addVersionEntity(DataPackEntity entity) {
		preVersionMap.put(entity.getPreVersion(), entity);
		versionMap.put(entity.getVersion(), entity);
	}

	
	
	
	private boolean checkAppVersion(String version, String appVersion) {
		String[] split = version.split("\\.");
		String[] split2 = appVersion.split("\\.");
		if (!split[0].equals(split2[0])) {
			return false;
		}
		if (!split[1].equals(split2[1])) {
			return false;
		}
		return true;
	}
	
	
	
	
	
	
		

}
