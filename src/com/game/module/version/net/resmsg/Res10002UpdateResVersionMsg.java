package com.game.module.version.net.resmsg;

import java.io.IOException;
import java.util.List;

import com.core.net.message.bean.MyStreamWriter;
import com.game.module.version.bean.DataPackBean;

public class Res10002UpdateResVersionMsg extends MyStreamWriter{
	
	public Res10002UpdateResVersionMsg(List<DataPackBean> dataList,String resUrl) throws IOException {
		writeShort(dataList.size());
		for (DataPackBean dataPackBean : dataList) {
			writeString(dataPackBean.getVersion());
			writeString(dataPackBean.getURL());
		}
		writeString(resUrl);
	}
}
