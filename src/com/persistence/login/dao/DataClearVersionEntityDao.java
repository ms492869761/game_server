package com.persistence.login.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.persistence.login.bean.DataClearVersionEntity;
import com.persistence.login.mapp.DataClearVersionEntityMapper;
import com.util.LoggerHelper;

public class DataClearVersionEntityDao extends BaseLoginEntityDao<DataClearVersionEntity>{

	@Override
	public List<DataClearVersionEntity> selectAll() {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			DataClearVersionEntityMapper mapper = openSession.getMapper(DataClearVersionEntityMapper.class);
			return mapper.selectByExample(null);
		} catch (Exception e) {
			LoggerHelper.dealExceptionError(logger, e);
		} finally {
			if(openSession!=null) {
				openSession.close();
			}
		}
		return null;
	}

	@Override
	public int insert(DataClearVersionEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			DataClearVersionEntityMapper mapper = openSession.getMapper(DataClearVersionEntityMapper.class);
			return mapper.insert(entity);
		} catch (Exception e) {
			LoggerHelper.dealExceptionError(logger, e);
		} finally {
			if(openSession!=null) {
				openSession.close();
			}
		}
		return 0;
	}

	@Override
	public int update(DataClearVersionEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			DataClearVersionEntityMapper mapper = openSession.getMapper(DataClearVersionEntityMapper.class);
			return mapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			LoggerHelper.dealExceptionError(logger, e);
		} finally {
			if(openSession!=null) {
				openSession.close();
			}
		}
		return 0;
	}

	@Override
	public int delete(DataClearVersionEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			DataClearVersionEntityMapper mapper = openSession.getMapper(DataClearVersionEntityMapper.class);
			return mapper.deleteByPrimaryKey(entity.getVersion());
		} catch (Exception e) {
			LoggerHelper.dealExceptionError(logger, e);
		} finally {
			if(openSession!=null) {
				openSession.close();
			}
		}
		return 0;
	}
	

	
	
	
	
}
