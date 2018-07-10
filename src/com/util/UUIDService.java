package com.util;

import java.util.HashSet;
import java.util.UUID;

public class UUIDService {
	private static UUIDService instance=new UUIDService();
	public static UUIDService getInstance(){
		return instance;
	}	
	private Short id = 0;
	public long getId(int serverId) {
		synchronized (UUID.class) {
			if(id>=Short.MAX_VALUE){
				id=0;
			}else{
				id++;
			}
			return (serverId & 0xFFFF) << 48 | (System.currentTimeMillis()/1000 & 0xFFFFFFFF) << 16 | id & 0xFFFF;
		}
	}
	
	
	public long getNewId() {
		return getId(1);
	}
	
	
	public static void main(String agrs[]) throws InterruptedException{
		
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(currentTimeMillis);
		HashSet<Long> longs=new HashSet<>();
		for (int i = 0; i < 65535*2; i++) {
			long id2 = getInstance().getId(1);
			if(longs.contains(id2)){
				System.out.println(id2+" "+longs.size());
				break;
			}
			longs.add(id2);
			if(i>1000&&i%1000==0){
				Thread.sleep(100);
			}
		}
		System.out.println(longs.size());
		System.out.println(System.currentTimeMillis()-currentTimeMillis);
		
	}
}