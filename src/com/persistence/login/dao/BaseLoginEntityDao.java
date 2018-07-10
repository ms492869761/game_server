package com.persistence.login.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.persistence.BaseDAO;
import com.persistence.PersistenceConfig;

public abstract class BaseLoginEntityDao<E> extends BaseDAO {
	
	protected static final Logger logger=Logger.getLogger(BaseLoginEntityDao.class);
	
	public abstract List<E> selectAll();
	
	public abstract int insert(E entity);
	
	public abstract int update(E entity);

	public abstract int delete(E entity);
	
	@Override
	protected SqlSessionFactory getSqlSessionFactory() {
		return PersistenceConfig.getInstance().getLoginFactory();
	}
	
	
	
	
}
