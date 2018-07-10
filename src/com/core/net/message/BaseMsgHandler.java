package com.core.net.message;

import java.io.IOException;

import javax.servlet.http.HttpSession;

public abstract class BaseMsgHandler {
	
	protected MessageRequest request;
	
	private boolean enable;
	
	public BaseMsgHandler() {
		
	}
	
	public void setRequest(MessageRequest request) {
		this.request=request;
	}
	
	public abstract void excute() throws IOException;
	
	
	
	public void setEnable(boolean enable) {
		this.enable=enable;
	}
	
	
	public boolean getEnable() {
		return this.enable;
	}
	
	
	protected HttpSession getSession() {
		return request.getSession();
	}
	
	
	
	
}
