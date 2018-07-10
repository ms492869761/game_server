package com.game.module.star.net.resmsg;

import java.io.IOException;
import java.util.List;

import com.core.net.message.bean.MyStreamWriter;
import com.persistence.login.bean.ServerEntity;

public class Res10006ServerListMsg extends MyStreamWriter{
	
	public Res10006ServerListMsg(String sessionId,List<ServerEntity> dataList) throws IOException {
		writeString(sessionId);
		writeShort(dataList.size());
		for (ServerEntity serverEntity : dataList) {
			writeInteger(serverEntity.getId());
			writeString(serverEntity.getName());
			writeByte(serverEntity.getState());
		}
		
		
	}
	
	
}
