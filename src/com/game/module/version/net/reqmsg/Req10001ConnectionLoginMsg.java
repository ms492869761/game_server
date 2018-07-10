package com.game.module.version.net.reqmsg;

import java.io.IOException;

import com.core.net.message.bean.MyStreamReader;

public class Req10001ConnectionLoginMsg extends MyStreamReader{
	
	private String version;
	
	private String device;
	
	private String plat;
	
	
	public Req10001ConnectionLoginMsg(byte[] bytes) throws IOException {
		super(bytes);
		this.version=readString();
		this.device=readString();
		this.plat=readString();
	}


	public String getVersion() {
		return version;
	}


	public String getDevice() {
		return device;
	}


	public String getPlat() {
		return plat;
	}
	
	

}
