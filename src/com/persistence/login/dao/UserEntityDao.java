package com.persistence.login.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.persistence.login.bean.UserEntity;
import com.persistence.login.bean.UserEntityExample;
import com.persistence.login.mapp.UserEntityMapper;
import com.util.LoggerHelper;

public class UserEntityDao extends BaseLoginEntityDao<UserEntity>{

	@Override
	public List<UserEntity> selectAll() {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			UserEntityMapper mapper = openSession.getMapper(UserEntityMapper.class);
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
	
	public UserEntity selectOne(String userName) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			UserEntityMapper mapper = openSession.getMapper(UserEntityMapper.class);
			return mapper.selectByPrimaryKey(userName);
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
	public int insert(UserEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			UserEntityMapper mapper = openSession.getMapper(UserEntityMapper.class);
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
	public int update(UserEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			UserEntityMapper mapper = openSession.getMapper(UserEntityMapper.class);
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
	public int delete(UserEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			UserEntityMapper mapper = openSession.getMapper(UserEntityMapper.class);
			return mapper.deleteByPrimaryKey(entity.getUser());
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
