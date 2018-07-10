package com.persistence.login.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.persistence.login.bean.FunctionEntity;
import com.persistence.login.mapp.FunctionEntityMapper;
import com.util.LoggerHelper;

public class FunctionEntityDao extends BaseLoginEntityDao<FunctionEntity>{

	@Override
	public List<FunctionEntity> selectAll() {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			FunctionEntityMapper mapper = openSession.getMapper(FunctionEntityMapper.class);
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

	public FunctionEntity selectOne(String funName) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			FunctionEntityMapper mapper = openSession.getMapper(FunctionEntityMapper.class);
			return mapper.selectByPrimaryKey(funName);
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
	public int insert(FunctionEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			FunctionEntityMapper mapper = openSession.getMapper(FunctionEntityMapper.class);
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
	public int update(FunctionEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			FunctionEntityMapper mapper = openSession.getMapper(FunctionEntityMapper.class);
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
	public int delete(FunctionEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			FunctionEntityMapper mapper = openSession.getMapper(FunctionEntityMapper.class);
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
