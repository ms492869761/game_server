package com.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public abstract class BaseDAO {
	
	protected abstract SqlSessionFactory getSqlSessionFactory();
	
	protected SqlSession openSession(){
		return getSqlSessionFactory().openSession();
	}
	
}
