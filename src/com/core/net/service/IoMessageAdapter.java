package com.core.net.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface IoMessageAdapter {
	
	public void requestMessage(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException ;
	
	public void destroyedMessage(HttpSession session);
	
	
	
}
