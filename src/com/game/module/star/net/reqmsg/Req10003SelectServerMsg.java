package com.game.module.star.net.reqmsg;

import java.io.IOException;

import com.core.net.message.bean.MyStreamReader;

public class Req10003SelectServerMsg extends MyStreamReader{
	
	private int serverId;
	
	
	public Req10003SelectServerMsg(byte[] bytes) throws IOException {
		super(bytes);
		this.serverId=readInteger();
	}

	public int getServerId() {
		return this.serverId;
	}
	
	
}
