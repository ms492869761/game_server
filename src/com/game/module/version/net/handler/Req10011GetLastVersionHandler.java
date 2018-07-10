package com.game.module.version.net.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.game.GameServer;
import com.game.module.version.net.reqmsg.Req10011GetLastVersionMsg;
import com.game.module.version.net.resmsg.Res10018LastVersionMsg;
import com.game.server.msg.MsgHandler;
import com.persistence.login.bean.DataPackEntity;

public class Req10011GetLastVersionHandler extends MsgHandler<Req10011GetLastVersionMsg> {

	@Override
	public void doAction() throws IOException {
		String version = getMsg().getVersion();
		System.out.println("10011  Msg: version="+version);
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
		String resUrl = GameServer.getInstance().getGlobalConfig().getResUrl();
		if(dataList.size()<=0) {
			System.out.println("10011   MsgBack:  version="+version+"   resUrl="+resUrl);
			Res10018LastVersionMsg msg=new Res10018LastVersionMsg(version,resUrl);
			sendMessage(msg);
		} else {
			System.out.println("10011   MsgBack:  version="+version+"   resUrl="+resUrl);
			DataPackEntity dataPackEntity = dataList.get(dataList.size()-1);
			Res10018LastVersionMsg msg=new Res10018LastVersionMsg(dataPackEntity.getVersion(),resUrl);
			sendMessage(msg);
		}
	}

}
