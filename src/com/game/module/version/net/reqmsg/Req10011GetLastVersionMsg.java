package com.game.module.version.net.reqmsg;

import java.io.IOException;

import com.core.net.message.bean.MyStreamReader;

public class Req10011GetLastVersionMsg extends MyStreamReader{
	
	private String version;
	
	
	public Req10011GetLastVersionMsg(byte[] bytes) throws IOException {
		super(bytes);
		this.version=readString();
	}
	
	
	public String getVersion() {
		return this.version;
	}
	
}
