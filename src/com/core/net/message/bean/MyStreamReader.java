package com.core.net.message.bean;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class MyStreamReader {
	
	private DataInputStream dis;
	
	public MyStreamReader(byte[] bytes) {
		dis=new DataInputStream(new ByteArrayInputStream(bytes));
	}
	
	
	public byte readByte() throws IOException {
		byte value = dis.readByte();
		return value;
	}
	
	
	public short readShort() throws IOException {
		short value=dis.readShort();
		return value;
	}
	
	public int readInteger() throws IOException {
		int value=dis.readInt();
		return value;
	}
	
	public long readLong() throws IOException {
		long value=dis.readLong();
		return value;
	}
	
	public float readFloat() throws IOException {
		float value=dis.readFloat();
		return value;
	}
	
	public double readDouble() throws IOException {
		double value=dis.readDouble();
		return value;
	}
	
	public String readString() throws IOException {
		int length=dis.readInt();
		byte[] strBytes=new byte[length];
		dis.read(strBytes, 0, strBytes.length);
		String value = new String(strBytes, Charset.forName("UTF-8"));
		return value;
	}
	
	
	public byte[] readBytes() throws IOException {
		int length=dis.readInt();
		byte[] value=new byte[length];
		dis.read(value,0,value.length);
		return value;
	}
	
	
}
