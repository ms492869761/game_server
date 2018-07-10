package com.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		URL url=new URL("http://115.29.141.111/TestGame/testData.jsp");
		HttpURLConnection openConnection = (HttpURLConnection)url.openConnection();
		openConnection.setDoOutput(true);
		openConnection.setDoInput(true);
		openConnection.setConnectTimeout(10000000);
		OutputStream outputStream = openConnection.getOutputStream();
		DataOutputStream dos=new DataOutputStream(outputStream);
		
		dos.writeInt(3);
		dos.writeInt(15);
		dos.flush();
		dos.close();
		DataInputStream dis = new DataInputStream(openConnection.getInputStream());
		int j=dis.readInt();
		int b=dis.readInt();
		System.out.println(j+" "+b);

	}

}
