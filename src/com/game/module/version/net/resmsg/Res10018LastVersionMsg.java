package com.game.module.version.net.resmsg;

import java.io.IOException;

import com.core.net.message.bean.MyStreamWriter;

public class Res10018LastVersionMsg extends MyStreamWriter{
	
	public Res10018LastVersionMsg(String version,String resurl) throws IOException {
		writeString(version);
		writeString(resurl);
	}
	
}
