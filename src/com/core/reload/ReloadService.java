package com.core.reload;

import java.util.HashMap;

import com.util.TimerTick;

public class ReloadService {
	  private HashMap<String, HashMap<String, IReloadSupport>> reloadList = new HashMap();

	  public void addReloadListener(IReloadSupport reloadSupport) {
	    if (!this.reloadList.containsKey(reloadSupport.getModuleName())) {
	      HashMap map = new HashMap();
	      this.reloadList.put(reloadSupport.getModuleName(), map);
	    }
	    HashMap hashMap = (HashMap)this.reloadList.get(reloadSupport.getModuleName());
	    hashMap.put(reloadSupport.getCacheName(), reloadSupport);
	  }

	  public String reload(String modelName, String cacheName)
	  {
	    HashMap hashMap = (HashMap)this.reloadList.get(modelName);
	    if (hashMap == null) {
	      return "没有注册的重加载模块 " + modelName;
	    }
	    IReloadSupport iReloadSupport = (IReloadSupport)hashMap.get(cacheName);
	    if (iReloadSupport == null) {
	      return "没有注册的重加载项modelname" + modelName + " cachename" + cacheName;
	    }
	    TimerTick createTick = TimerTick.createTick(System.currentTimeMillis());
	    try {
	      iReloadSupport.reload();
	    } catch (Exception ex) {
	      return "重加载发生错误" + ex.getMessage();
	    }
	    return String.format("重加载%S成功耗时%sMS", new Object[] { modelName + ":" + cacheName, Long.valueOf(createTick.tickNow()) });
	  }
	  public HashMap<String, HashMap<String, IReloadSupport>> getCacheList() {
	    return this.reloadList;
	  }
}
