package com.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class TestServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 511671521229418151L;
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		URL url=new URL("http://127.0.0.1:8888/httpgame/api.php");
		HttpURLConnection openConnection = (HttpURLConnection)url.openConnection();
		openConnection.setDoOutput(true);
		openConnection.setDoInput(true);
		openConnection.setConnectTimeout(10000000);
		OutputStream outputStream = openConnection.getOutputStream();
		DataOutputStream dos=new DataOutputStream(outputStream);
		
		dos.writeByte(127);
		dos.writeShort(101);
		dos.writeInt(1987);
		dos.flush();
		dos.close();
		DataInputStream dis = new DataInputStream(openConnection.getInputStream());
		int i=dis.readInt();
		System.out.println(i);
		
		
		
//		int length=Integer.parseInt(arg0.getHeader("content-length"));
//		ServletInputStream isInputStream = arg0.getInputStream();
//		DataInputStream dataInputStream = new DataInputStream(isInputStream);
//		byte[] bytes=new byte[length];
//		dataInputStream.readFully(bytes);
//		LOGIN_MSG.Req1003UserLogin req1003UserLogin=Req1003UserLogin.parseFrom(bytes);
//		String user = req1003UserLogin.getUser();
//		String pass = req1003UserLogin.getPass();
//		System.out.println("user="+user+"    pass="+pass);
//		
//		Builder newBuilder = MsgBeanPersion.newBuilder();
//		newBuilder.setId("1234567890123456789012345678901234567890123456789012345678901234");
//		newBuilder.setName("白靓");
//		MsgBeanPersion build = newBuilder.build();
//		byte[] byteArray = build.toByteArray();
//		ServletOutputStream outputStream2 = arg1.getOutputStream();
//		DataOutputStream dataOutputStream = new DataOutputStream(outputStream2);
//		dataOutputStream.write(byteArray);
//		dataOutputStream.flush();
//		dataOutputStream.close();
		
	}
	
	public static void main(String[] args) throws IOException {
		URL url=new URL("http://127.0.0.1:8888/httpgame/api.php");
		HttpURLConnection openConnection = (HttpURLConnection)url.openConnection();
		openConnection.setDoOutput(true);
		openConnection.setDoInput(true);
		openConnection.setConnectTimeout(10000000);
		OutputStream outputStream = openConnection.getOutputStream();
		DataOutputStream dos=new DataOutputStream(outputStream);
		
		dos.writeByte(127);
		dos.writeShort(101);
		dos.writeInt(1987);
		dos.flush();
		dos.close();
		DataInputStream dis = new DataInputStream(openConnection.getInputStream());
		int i=dis.readInt();
		System.out.println(i);
	}
	
	
	
}
