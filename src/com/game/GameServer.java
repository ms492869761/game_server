package com.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.core.net.service.HttpMessageExcuteService;
import com.game.module.star.service.StarService;
import com.game.module.version.service.VersionService;
import com.game.server.config.GlobalConfig;
import com.game.server.msg.MsgQueueHandler;

public class GameServer {
	
	private static final Logger logger=Logger.getLogger(GameServer.class);
	
	
	private static GameServer instance=new GameServer();
	
	/** 系统参数*/
	private GlobalConfig globalConfig;
	/** 版本服务*/
	private VersionService versionService;
	/** 服务器列表服务*/
	private StarService starService;
	
	
	private GameServer() {
	}
	
	
	public static GameServer getInstance() {
		return instance;
	}
	
	private void init() {
		logger.info("服务器初始化");
		logger.info("初始化系统参数");
		globalConfig=GlobalConfig.getInstance();
		Properties properties=new Properties();
		try {
			properties.load(new FileInputStream(System.getProperty("catalina.home")+"/conf/param.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		globalConfig.init(properties);
		versionService=VersionService.getInstance();
		versionService.init();
		starService=StarService.getInstance();
		starService.init();
		
		
	}
	
	
	
	public void start() {
		init();
		HttpMessageExcuteService.getInstance().setIoMessageAdapter(new MsgQueueHandler());
		logger.info("服务器启动");
	}
	
	
	
	public void stop() {
		logger.info("服务器关闭");
	}
	
	
	
	public GlobalConfig getGlobalConfig() {
		return this.globalConfig;
	}
	
	public VersionService getVersionService() {
		return this.versionService;
	}
	
	public StarService getStarService() {
		return this.starService;
	}
	
	
}
