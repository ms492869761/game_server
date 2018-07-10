package com.game.server.config;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.util.StringUtil;

public class GlobalConfig {
	
	private static final Logger logger=Logger.getLogger(GlobalConfig.class);
	
	
	private String savePackagePath;
	
	private String dataPackageUrl;
	
	private String resUrl;
	
	
	private static GlobalConfig instance=new GlobalConfig();
	
	private GlobalConfig() {
		
	}
	
	public static GlobalConfig getInstance() {
		return instance;
	}
	
	
	public void init(Properties param) {
		this.savePackagePath=param.getProperty("login.savePackagePath");
		this.dataPackageUrl=param.getProperty("login.dataPackageUrl");
		this.resUrl=param.getProperty("login.resUrl");
		
	}
	
	
	
	private int loadInteger(Properties param,String paramName) {
		String property = param.getProperty(paramName);
		if(StringUtil.isBlank(property)) {
			logger.error("必要参数错误："+paramName);
			System.exit(1);
		}
		if(!StringUtil.isNumeric(property)) {
			logger.error("必要参数必须是数字："+paramName);
			System.exit(1);
		}
		return Integer.parseInt(property);
		
	}
	
	
	public String getSavePackagePath() {
		return this.savePackagePath;
	}
	
	public String getDataPackageUrl() {
		return this.dataPackageUrl;
	}
	
	public String getResUrl() {
		return this.resUrl;
	}
	
	
}
