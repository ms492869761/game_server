package com.game.module.star.net.resmsg;

import java.io.IOException;

import com.core.net.message.bean.MyStreamWriter;

public class Res10008ServerUrlMsg extends MyStreamWriter{
	
	public Res10008ServerUrlMsg(byte state,String serverUrl) throws IOException {
		writeByte(state);
		writeString(serverUrl);
	}
	
}
