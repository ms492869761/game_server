package com.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestConnectionServlet
 */
@WebServlet("/TestConnectionServlet")
public class TestConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestConnectionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
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
	}
	
	
	
}
