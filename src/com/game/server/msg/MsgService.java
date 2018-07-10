package com.game.server.msg;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.core.net.message.bean.MyStreamReader;
import com.core.net.message.bean.MyStreamWriter;
import com.game.module.star.net.handler.Req10003SelectServerHandler;
import com.game.module.star.net.reqmsg.Req10003SelectServerMsg;
import com.game.module.star.net.resmsg.Res10006ServerListMsg;
import com.game.module.star.net.resmsg.Res10008ServerUrlMsg;
import com.game.module.test.handler.Req101TestMsgHandler;
import com.game.module.test.reqmsg.Req101TestMsg;
import com.game.module.test.resmsg.Res102ReturnTest;
import com.game.module.version.net.handler.Req10001ConnectionLoginHandler;
import com.game.module.version.net.handler.Req10011GetLastVersionHandler;
import com.game.module.version.net.reqmsg.Req10001ConnectionLoginMsg;
import com.game.module.version.net.reqmsg.Req10011GetLastVersionMsg;
import com.game.module.version.net.resmsg.Res10002UpdateResVersionMsg;
import com.game.module.version.net.resmsg.Res10004UpdateAppVersionMsg;
import com.game.module.version.net.resmsg.Res10018LastVersionMsg;
import com.game.module.web.resmsg.MsgPackageRes;

public class MsgService {
	
	private static final Logger logger=Logger.getLogger(MsgService.class);
	
	
	
	private HashMap<Class<? extends MyStreamWriter>, Short> response=new HashMap<Class<? extends MyStreamWriter>,Short>();
	private HashMap<Short, ReqHandler> reqmap=new HashMap<Short,ReqHandler>();
	
	
	private static MsgService instance=new MsgService();
	
	
	public static MsgService getInstance() {
		return instance;
	}
	
	private MsgService() {
		
		// 测试
		registerHandler(101, Req101TestMsgHandler.class, Req101TestMsg.class);

		registerResponse(102, Res102ReturnTest.class);
		
		//登录
		registerHandler(10001, Req10001ConnectionLoginHandler.class, Req10001ConnectionLoginMsg.class);
		registerHandler(10003, Req10003SelectServerHandler.class, Req10003SelectServerMsg.class);
		registerHandler(10011, Req10011GetLastVersionHandler.class, Req10011GetLastVersionMsg.class);
		
		registerResponse(10002, Res10002UpdateResVersionMsg.class);
		registerResponse(10004, Res10004UpdateAppVersionMsg.class);
		registerResponse(10006, Res10006ServerListMsg.class);
		registerResponse(10008, Res10008ServerUrlMsg.class);
		registerResponse(10018, Res10018LastVersionMsg.class);
		
	}
	
	private void registerHandler(int msgCode,Class<? extends MsgHandler<?>> handler,Class<? extends MyStreamReader> msg) {
		reqmap.put((short)msgCode, new ReqHandler(handler, msg));
	}
	
	
	
	private void registerResponse(int msgCode,Class<? extends MyStreamWriter> msg) {
		response.put(msg, (short)msgCode);
	}
	
	
	public MsgHandler<?> getHandler(short msgCode,HttpServletRequest req,HttpServletResponse res,MyStreamReader invoke) throws InstantiationException, IllegalAccessException {
		ReqHandler reqHandler = reqmap.get(msgCode);
		MsgHandler<?> newInstance = reqHandler.getHandler().newInstance();
		newInstance.setMsg(invoke);
		newInstance.setRequest(req);
		newInstance.setResponse(res);
		return newInstance;
	}
	
	
	public Class<? extends MyStreamReader> getReqMsg(short msgCode) {
		ReqHandler reqHandler = reqmap.get(msgCode);
		return reqHandler.getReqmsg();
	}
	
	
	
	
	public void sendMsg(MyStreamWriter msg,HttpServletResponse resp) throws IOException {
		//验证消息是否被注册
		Short msgId = response.get(msg.getClass());
		if(msgId==null) {
			logger.error(msg.getClass()+" 消息未注册");
			return ;
		}
		//获得消息内二进制数据
		byte[] byteArray = msg.getBytes();
		MyStreamWriter msgPackageRes = new MsgPackageRes(msgId, byteArray);
		ServletOutputStream outputStream = resp.getOutputStream();
		outputStream.write(msgPackageRes.getBytes());
		outputStream.flush();
		outputStream.close();
	}
	
	
}



class ReqHandler{
	private boolean use=true;
	private Class<? extends MsgHandler<?>> handler;
	private Class<? extends MyStreamReader> reqmsg;
	public ReqHandler(Class<? extends MsgHandler<?>> handler,Class<? extends MyStreamReader> reqmsg){
		this.handler=handler;
		this.reqmsg=reqmsg;
	}
	public Class<? extends MsgHandler<?>> getHandler() {
		return handler;
	}
	public Class<? extends MyStreamReader> getReqmsg() {
		return reqmsg;
	}
	public boolean isUse() {
		return use;
	}
	public void setUse(boolean use) {
		this.use = use;
	}
}