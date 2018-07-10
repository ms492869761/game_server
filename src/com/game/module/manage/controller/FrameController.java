package com.game.module.manage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.module.manage.service.ManageService;
import com.persistence.login.bean.FunctionEntity;
import com.persistence.login.bean.RoleEntity;
import com.persistence.login.bean.UserEntity;
import com.persistence.login.dao.FunctionEntityDao;
import com.persistence.login.dao.RoleEntityDao;
import com.persistence.login.dao.UserEntityDao;
import com.util.StringUtil;

public class FrameController extends BaseController{
	
	
	private FunctionEntityDao functionDao=new FunctionEntityDao();
	
	private UserEntityDao userDao=new UserEntityDao();
	
	private RoleEntityDao roleDao=new RoleEntityDao();
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = ManageService.readParam(req, "action");
		if(action.equalsIgnoreCase("left")) {
			String userName = req.getSession().getAttribute("userName").toString();
			UserEntity userEntity = userDao.selectOne(userName);
			RoleEntity roleEntity = roleDao.selectOne(userEntity.getRole());
			String funs = roleEntity.getFuns();
			String[] split = funs.split(",");
			List<FunctionEntity> funsList=new ArrayList<>();
			for (String fun : split) {
				if(StringUtil.isBlank(fun)) {
					continue;
				}
				FunctionEntity functionEntity = functionDao.selectOne(fun);
				if(functionEntity!=null) {
					funsList.add(functionEntity);	
				}
			}
			req.setAttribute("funs", funsList);
			req.getRequestDispatcher("/WEB-INF/jsp/frame/left.jsp").forward(req, res);
		}
		if (action.equalsIgnoreCase("main")) {
			req.getRequestDispatcher("/WEB-INF/jsp/frame/main.jsp").forward(req, res);
			return;
		}
		if (action.equalsIgnoreCase("top")) {
			req.getRequestDispatcher("/WEB-INF/jsp/frame/top.jsp").forward(req, res);
			return;
		}
	}
	
	
	
	
	
	
}
