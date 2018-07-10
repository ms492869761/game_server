package com.game.module.version.net.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.game.GameServer;
import com.game.module.star.net.resmsg.Res10006ServerListMsg;
import com.game.module.version.bean.DataPackBean;
import com.game.module.version.net.reqmsg.Req10001ConnectionLoginMsg;
import com.game.module.version.net.resmsg.Res10002UpdateResVersionMsg;
import com.game.module.version.net.resmsg.Res10004UpdateAppVersionMsg;
import com.game.server.msg.MsgHandler;
import com.persistence.login.bean.DataClearVersionEntity;
import com.persistence.login.bean.DataPackEntity;
import com.persistence.login.bean.ServerEntity;

public class Req10001ConnectionLoginHandler extends MsgHandler<Req10001ConnectionLoginMsg>{

	@Override
	public void doAction() throws IOException {
		String version = getMsg().getVersion();
		String device = getMsg().getDevice();
		String plat = getMsg().getPlat();
		String[] split = version.split("\\.");
		String baseVersion=split[0]+"."+split[1]+".0";
		DataClearVersionEntity dataClearVersionEntity = GameServer.getInstance().getVersionService().getMainVersion().get(baseVersion);
		if(dataClearVersionEntity.getIsUse()!=1) {
			Res10004UpdateAppVersionMsg msg=new Res10004UpdateAppVersionMsg(dataClearVersionEntity.getUpdateUrl());
			sendMessage(msg);
			return ;
		}
		List<DataPackEntity> versionList = GameServer.getInstance().getVersionService().getVersionList(version);
		List<DataPackEntity> dataList=new ArrayList<DataPackEntity>();
		while(true) {
			DataPackEntity entity=null;
			for (DataPackEntity dataPackEntity : versionList) {
				if(dataPackEntity.getPreVersion().equals(version)) {
					entity=dataPackEntity;
				}
			}
			if(entity==null) {
				break;
			} else {
				version=entity.getVersion();
				versionList.remove(entity);
				dataList.add(entity);
			}
		}
		if(dataList.size()>0) {
			List<DataPackBean> sendList=new ArrayList<DataPackBean>();
			for (DataPackEntity dataPackEntity : dataList) {
				DataPackBean bean=new DataPackBean();
				bean.setVersion(dataPackEntity.getVersion());
				bean.setURL(dataPackEntity.getUrl());
				sendList.add(bean);
			}
			String resUrl = GameServer.getInstance().getGlobalConfig().getResUrl();
			Res10002UpdateResVersionMsg msg=new Res10002UpdateResVersionMsg(sendList,resUrl);
			sendMessage(msg);
			return;
		} else {
			String sessionId = getSession().getId();
			List<ServerEntity> allStarEntity = GameServer.getInstance().getStarService().getStarEntityListByVersion(baseVersion);
			Res10006ServerListMsg msg=new Res10006ServerListMsg(sessionId, allStarEntity);
			sendMessage(msg);
			
		}
		
		
		
		
	}

}
