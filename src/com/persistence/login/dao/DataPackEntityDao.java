package com.persistence.login.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.persistence.login.bean.DataPackEntity;
import com.persistence.login.mapp.DataPackEntityMapper;
import com.util.LoggerHelper;

public class DataPackEntityDao extends BaseLoginEntityDao<DataPackEntity>{

	@Override
	public List<DataPackEntity> selectAll() {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			DataPackEntityMapper mapper = openSession.getMapper(DataPackEntityMapper.class);
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
	public int insert(DataPackEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			DataPackEntityMapper mapper = openSession.getMapper(DataPackEntityMapper.class);
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
	public int update(DataPackEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			DataPackEntityMapper mapper = openSession.getMapper(DataPackEntityMapper.class);
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
	public int delete(DataPackEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			DataPackEntityMapper mapper = openSession.getMapper(DataPackEntityMapper.class);
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
