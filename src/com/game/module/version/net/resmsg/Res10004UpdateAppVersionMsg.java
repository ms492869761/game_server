package com.game.module.version.net.resmsg;

import java.io.IOException;

import com.core.net.message.bean.MyStreamWriter;

public class Res10004UpdateAppVersionMsg extends MyStreamWriter{
	
	public Res10004UpdateAppVersionMsg(String URL) throws IOException {
		writeString(URL);
	}
}
