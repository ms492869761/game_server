package com.game.module.web.reqmsg;

import java.io.IOException;

import sun.awt.image.BytePackedRaster;

import com.core.net.message.bean.MyStreamReader;
import com.core.net.message.bean.MyStreamWriter;

public class MsgPackageReq extends MyStreamReader{
	
	private int msgCode;
	
	private byte[] bytes;
	
	public MsgPackageReq(byte[] bytes) throws IOException {
		super(bytes);
		this.msgCode=readInteger();
		this.bytes=readBytes();
	}
	
	public int getMsgCode() {
		return this.msgCode;
	}
	
	public byte[] getBytes() {
		return this.bytes;
	}
	
	
}
