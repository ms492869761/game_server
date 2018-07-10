package com.core.net.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.net.service.HttpMessageExcuteService;

/**
 * Servlet implementation class RequestMessageServlet
 */
@WebServlet("/RequestMessageServlet")
public class RequestMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestMessageServlet() {
        super();
        
    }

    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
    	HttpMessageExcuteService.getInstance().requestMessage(arg0, arg1);
    }
	

}
