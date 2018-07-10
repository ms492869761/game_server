package com.persistence;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.util.TimerTick;

public class PersistenceConfig {
	
	private final static Logger logger=Logger.getLogger(PersistenceConfig.class);
	
	private static String loginconf="/mapp/db-config-login.xml";
	private static String dbConfigPath = System.getProperty("catalina.home")	+ "/conf/db.properties";
	
	
	private SqlSessionFactory loginFactory;
	
	private static PersistenceConfig instance=new PersistenceConfig();
	
	
	
	public static PersistenceConfig getInstance() {
		return instance;
	}
	
	
	
	private PersistenceConfig() {
		
		try {
			InputStream loginStream=getClass().getResourceAsStream(loginconf);
			TimerTick createTick=TimerTick.createTick();
			Properties dbconfig=new Properties();
			dbconfig.load(new FileInputStream(dbConfigPath));
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			loginFactory=builder.build(loginStream,dbconfig);
			logger.info("数据库配置加载完成 耗时    "+createTick.tickNow()+" ms");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			Runtime.getRuntime().exit(1);
		}
	}
	
	
	
	public SqlSessionFactory getLoginFactory() {
		return loginFactory;
	}
	
	
}
