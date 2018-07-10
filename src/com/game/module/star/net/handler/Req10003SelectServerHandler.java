package com.game.module.star.net.handler;

import java.io.IOException;

import com.game.GameServer;
import com.game.module.star.net.reqmsg.Req10003SelectServerMsg;
import com.game.module.star.net.resmsg.Res10008ServerUrlMsg;
import com.game.server.msg.MsgHandler;
import com.persistence.login.bean.ServerEntity;

public class Req10003SelectServerHandler extends MsgHandler<Req10003SelectServerMsg>{

	@Override
	public void doAction() throws IOException {
		int serverId = getMsg().getServerId();
		ServerEntity serverById = GameServer.getInstance().getStarService().getServerById(serverId);
		Res10008ServerUrlMsg msg=new Res10008ServerUrlMsg(serverById.getState().byteValue(), serverById.getUrl()+"RequestMessageServlet");
		sendMessage(msg);
	}

}
