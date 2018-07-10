package com.game.module.manage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {
	
	public abstract void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException;
	
	
	
}
