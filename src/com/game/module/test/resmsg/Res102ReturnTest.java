package com.game.module.test.resmsg;

import java.io.IOException;

import com.core.net.message.bean.MyStreamWriter;

public class Res102ReturnTest extends MyStreamWriter{
	
	
	public Res102ReturnTest(int id,String name,int age) throws IOException {
		writeInteger(id);
		writeString(name);
		writeInteger(age);
	}
	
	
}
