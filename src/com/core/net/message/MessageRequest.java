package com.core.net.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.core.net.message.bean.MyStreamWriter;


public class MessageRequest {
	
	
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private DataInputStream dataInputStream;
	
	private DataOutputStream dataOutputStream;
	
	private int msgCode;
	
	private HttpSession session;
	
	public MessageRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
		this.request=request;
		this.response=response;
		session=this.request.getSession();
		this.dataInputStream = new DataInputStream(this.request.getInputStream());
		this.dataOutputStream = new DataOutputStream(this.response.getOutputStream());
	}
	

	
	public void sendMessage(MyStreamWriter writer) {
		
	}
	
	
	public int getMsgCode() {
		return msgCode;
	}
	
	public HttpSession getSession() {
		return session;
	}
	
	

	
	
	
	
	
	
	
	
	
}
