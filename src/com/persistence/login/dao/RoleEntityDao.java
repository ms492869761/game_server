package com.persistence.login.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.persistence.login.bean.RoleEntity;
import com.persistence.login.bean.RoleEntityExample;
import com.persistence.login.bean.RoleEntityExample.Criteria;
import com.persistence.login.mapp.RoleEntityMapper;
import com.util.LoggerHelper;

public class RoleEntityDao extends BaseLoginEntityDao<RoleEntity>{

	@Override
	public List<RoleEntity> selectAll() {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			RoleEntityMapper mapper = openSession.getMapper(RoleEntityMapper.class);
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
	
	public RoleEntity selectOne(int roleId) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			RoleEntityMapper mapper = openSession.getMapper(RoleEntityMapper.class);
			return mapper.selectByPrimaryKey(roleId);
		} catch (Exception e) {
			LoggerHelper.dealExceptionError(logger, e);
		} finally {
			if(openSession!=null) {
				openSession.close();
			}
		}
		return null;
	}
	
	public List<RoleEntity> selectByName(String roleName) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			RoleEntityMapper mapper = openSession.getMapper(RoleEntityMapper.class);
			RoleEntityExample example=new RoleEntityExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andNameEqualTo(roleName);
			List<RoleEntity> selectByExample = mapper.selectByExample(example);
			return selectByExample;
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
	public int insert(RoleEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			RoleEntityMapper mapper = openSession.getMapper(RoleEntityMapper.class);
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
	public int update(RoleEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			RoleEntityMapper mapper = openSession.getMapper(RoleEntityMapper.class);
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
	public int delete(RoleEntity entity) {
		SqlSession openSession=null;
		try {
			openSession=openSession();
			RoleEntityMapper mapper = openSession.getMapper(RoleEntityMapper.class);
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
