package com.game.module.web.resmsg;

import java.io.IOException;

import com.core.net.message.bean.MyStreamWriter;

public class MsgPackageRes extends MyStreamWriter{
	
	
	public MsgPackageRes(int msgCode,byte[] bytes) throws IOException {
		writeInteger(msgCode);
		writeBytes(bytes);
	}
	
	
}
