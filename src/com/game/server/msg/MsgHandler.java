package com.game.server.msg;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.core.net.message.bean.MyStreamReader;
import com.core.net.message.bean.MyStreamWriter;
import com.util.LoggerHelper;

public abstract class MsgHandler <T extends MyStreamReader>{
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = Logger.getLogger(MsgHandler.class);

	private T msg;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public abstract void doAction() throws IOException;
	public T getMsg() {
		return msg;
	}
	@SuppressWarnings("unchecked")
	public void setMsg(MyStreamReader msg) {
		this.msg = (T) msg;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void sendMessage(MyStreamWriter writer){
		HttpSession session = request.getSession();
		logger.info("session: "+session.getId()+"   S->C "+writer.getClass());
		try {
			MsgService.getInstance().sendMsg(writer, response);
		} catch (IOException e) {
			LoggerHelper.dealExceptionError(logger, e);
		}
	}

	
	
	public HttpSession getSession(){
		return request.getSession();
	}
	
}
