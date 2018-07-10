package com.persistence.login.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.persistence.login.bean.ServerEntity;
import com.persistence.login.mapp.ServerEntityMapper;
import com.util.LoggerHelper;

public class ServerEntityDao extends BaseLoginEntityDao<ServerEntity>{

	@Override
	public List<ServerEntity> selectAll() {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			ServerEntityMapper mapper = openSession.getMapper(ServerEntityMapper.class);
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
	public int insert(ServerEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			ServerEntityMapper mapper = openSession.getMapper(ServerEntityMapper.class);
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
	public int update(ServerEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			ServerEntityMapper mapper = openSession.getMapper(ServerEntityMapper.class);
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
	public int delete(ServerEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			ServerEntityMapper mapper = openSession.getMapper(ServerEntityMapper.class);
			return mapper.deleteByPrimaryKey(entity.getId());
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
