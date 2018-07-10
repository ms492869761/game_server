package com.game.module.test.handler;

import java.io.IOException;

import com.game.module.test.reqmsg.Req101TestMsg;
import com.game.module.test.resmsg.Res102ReturnTest;
import com.game.server.msg.MsgHandler;

public class Req101TestMsgHandler extends MsgHandler<Req101TestMsg> {

	@Override
	public void doAction() throws IOException {
		int id = getMsg().getId();
		String name = getMsg().getName();
		int age = getMsg().getAge();
		System.out.println("id="+id+"  name="+name+"  age="+age);
		Res102ReturnTest res102ReturnTest = new Res102ReturnTest(2,"赵振",4);
		sendMessage(res102ReturnTest);
	}

}
