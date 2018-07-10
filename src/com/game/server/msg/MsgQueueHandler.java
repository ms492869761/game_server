package com.game.server.msg;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.core.net.message.bean.MyStreamReader;
import com.core.net.service.IoMessageAdapter;
import com.game.module.web.reqmsg.MsgPackageReq;
import com.util.LoggerHelper;

public class MsgQueueHandler implements IoMessageAdapter {
	
	private static final Logger logger=Logger.getLogger(MsgQueueHandler.class);
	
	
	
	@Override
	public void requestMessage(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException {
		try {
			int length = Integer.parseInt(arg0.getHeader("content-length"));
			ServletInputStream inputStream = arg0.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			byte[] bytes = new byte[length];
			dataInputStream.readFully(bytes);
			
			MsgPackageReq parseFrom = new MsgPackageReq(bytes);
			int msgCode = parseFrom.getMsgCode();
			byte[] byteArray = parseFrom.getBytes();
			Class<? extends MyStreamReader> reqMsgClass = MsgService.getInstance().getReqMsg((short) msgCode);
			MyStreamReader reader = reqMsgClass.getDeclaredConstructor(new Class[]{byte[].class}).newInstance(new Object[]{byteArray});
			final MsgHandler<?> handler = MsgService.getInstance().getHandler((short)msgCode, arg0, arg1, reader);
			HttpSession session = arg0.getSession();
			logger.info("session: "+session.getId()+"  C->S   msgcode"+msgCode);
			synchronized (session) {
				try {
//					if(handler instanceof RoleMsgHandler<?>) {
//						OfflineRoleBean offlineRoleBean = ((RoleMsgHandler<?>) handler).getOfflineRoleBean();
//						if(offlineRoleBean!=null) {
//							Star star = GameServer.getInstance().getGalaxyService().getStar(offlineRoleBean.getCreateStarId());
//							star.addTask(new IFrameTask() {
//								@Override
//								public void doAction() {
//									try {
//										handler.doAction();
//									} catch (IOException e) {
//										LoggerHelper.dealExceptionError(logger, e);
//									}
//								}
//							});
//						} else {
							//TODO 进入重登陆流程
							
//						}
//					} else {
						handler.doAction();
//					}
				} catch (Exception e) {
					LoggerHelper.dealExceptionError(logger, e);
				}
			}
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			LoggerHelper.dealExceptionError(logger, e);
		}

	}

	@Override
	public void destroyedMessage(HttpSession session) {
		
	}

}
