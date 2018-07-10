package com.core.net.message.bean;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class MyStreamWriter {
	
	private ByteArrayOutputStream bos=new ByteArrayOutputStream();
	private DataOutputStream dos;
	
	public MyStreamWriter() {
		dos=new DataOutputStream(bos);
	}
	
	
	public byte[] getBytes() throws IOException {
		byte[] value = bos.toByteArray();
		System.out.println("length >>>>>>>> " + value.length + "");
		return value;
	}
	
	public int size() {
		return dos.size();
	}
	
	public void writeByte(byte value) throws IOException {
		dos.writeByte(value);
	}
	
	public void writeByte(int value) throws IOException {
		dos.writeByte(value);
	}
	
	public void writeShort(short value) throws IOException {
		dos.writeShort(value);
	}
	
	public void writeShort(int value) throws IOException {
		dos.writeShort(value);
	}
	
	public void writeInteger(int value) throws IOException {
		dos.writeInt(value);
	}
	
	public void writeLong(long value) throws IOException {
		dos.writeLong(value);
	}
	
	public void writeFloat(float value) throws IOException {
		dos.writeFloat(value);
	}
	
	public void writeDouble(double value) throws IOException {
		dos.writeDouble(value);
	}
	
	public void writeString(String value) throws IOException {
		byte[] bytes = value.getBytes(Charset.forName("UTF-8"));
		dos.writeInt(bytes.length);
		dos.write(bytes);
	}
	
	public void writeBytes(byte[] value) throws IOException {
		dos.writeInt(value.length);
		dos.write(value);
	}
	
	
}
