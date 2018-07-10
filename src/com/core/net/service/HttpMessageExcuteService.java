package com.core.net.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HttpMessageExcuteService {
	
	private static HttpMessageExcuteService instance=new HttpMessageExcuteService();
	
	private IoMessageAdapter ioMessageAdapter=null;
	
	
	private HttpMessageExcuteService() {
		
	}
	
	public static HttpMessageExcuteService getInstance() {
		return instance;
	}
	
	
	public void setIoMessageAdapter(IoMessageAdapter ioMessageAdapter) {
		this.ioMessageAdapter=ioMessageAdapter;
	}
	
	public void requestMessage(HttpServletRequest arg0,HttpServletResponse arg1) throws IOException {
		if(ioMessageAdapter!=null) {
			ioMessageAdapter.requestMessage(arg0, arg1);
		}
	}
	
	
	public void destroyedMessage(HttpSession session) {
		if(ioMessageAdapter!=null) {
			ioMessageAdapter.destroyedMessage(session);
		}
	}
	
	
	
	
	
	
	
	
	
}
