package com.game.module.test.reqmsg;

import java.io.IOException;

import com.core.net.message.bean.MyStreamReader;

public class Req101TestMsg extends MyStreamReader{
	
	private int id;
	
	private String name;
	
	private int age;
	
	public Req101TestMsg(byte[] bytes) throws IOException {
		super(bytes);
		this.id=readInteger();
		this.name=readString();
		this.age=readInteger();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}
}
