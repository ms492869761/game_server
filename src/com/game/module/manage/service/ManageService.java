package com.game.module.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.UserConfig;

import com.game.GameServer;
import com.game.module.manage.controller.BaseController;
import com.game.module.manage.controller.FrameController;
import com.game.module.manage.controller.RoleController;
import com.game.module.manage.controller.ServerController;
import com.game.module.manage.controller.UserController;
import com.game.module.manage.controller.VersionController;
import com.persistence.login.bean.FunctionEntity;
import com.persistence.login.bean.RoleEntity;
import com.persistence.login.bean.ServerEntity;
import com.persistence.login.bean.UserEntity;
import com.persistence.login.dao.FunctionEntityDao;
import com.persistence.login.dao.RoleEntityDao;
import com.persistence.login.dao.UserEntityDao;
import com.util.StringUtil;

public class ManageService {
	
	public static String key="kTm0XUDFV4cyoc7FvJU36COkMGYZv935h60uj77cfAYfEoRbpzVsR51cOPp3lvj3";
	
	private UserEntityDao userDao=new UserEntityDao();
	
	private RoleEntityDao roleDao=new RoleEntityDao();
	
	private FunctionEntityDao functionDao=new FunctionEntityDao();
	
	private static Map<String, BaseController> controllerMap=new HashMap<String, BaseController>();
	
	{
		controllerMap.put("frame", new FrameController());
		controllerMap.put("user", new UserController());
		controllerMap.put("role", new RoleController());
		controllerMap.put("star", new ServerController());
		controllerMap.put("version", new VersionController());
	}
	
	
	private static ManageService instance=new ManageService();
	
	private ManageService() {
		
	}
	
	public static ManageService getInstance() {
		return instance;
	}
	
	public void init() {
		
	}
	
	public void login(HttpServletRequest req,HttpServletResponse res) {
		String userName=ManageService.readParam(req, "username");
		String password=ManageService.readParam(req, "password");
		UserEntity userEntity = userDao.selectOne(userName);
		if(userEntity==null) {
			errorToLogin(req, res, "用户名不存在");
			return ;
		}
		if(!userEntity.getPassword().equals(password)) {
			errorToLogin(req, res, "密码错误");
			return ;
		}
		RoleEntity roleEntity = roleDao.selectOne(userEntity.getRole());
		if(roleEntity==null) {
			errorToLogin(req, res, "没有合法的权限");
			return ;
		}
		req.getSession().setAttribute("userName", userName);
		req.getSession().setAttribute("password", password);
		String stars = roleEntity.getStars();
		String[] split = stars.split(",");
		List<Integer> starList=new ArrayList<Integer>();
		for (String star : split) {
			if(StringUtil.isBlank(star)) {
				continue;
			}
			ServerEntity serverById = GameServer.getInstance().getStarService().getServerById(Integer.parseInt(star));
			if(serverById==null) {
				continue;
			}
			starList.add(serverById.getId());
		}
		String funs = roleEntity.getFuns();
		String[] split2 = funs.split(",");
		List<FunctionEntity> funList=new ArrayList<FunctionEntity>();
		for (String fun : split2) {
			if(StringUtil.isBlank(fun)){
				continue;
			}
			FunctionEntity functionEntity = functionDao.selectOne(fun);
			if(functionEntity==null) {
				continue;
			}
			funList.add(functionEntity);
		}
		req.getSession().setAttribute("stars", starList);
		req.getSession().setAttribute("funs", funList);
		toMain(req, res, "登陆成功");
		
		
		
		
	}
	
	
	
	public void error(HttpServletRequest req,HttpServletResponse res,String msg) {
		try{
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, res);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void errorToLogin(HttpServletRequest req,HttpServletResponse res,String msg) {
		try{
			req.setAttribute("error", msg);
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public void toMain(HttpServletRequest req,HttpServletResponse res,String msg) {
		try {
			req.setAttribute("error", msg);
			req.getRequestDispatcher("/WEB-INF/jsp/frame/index.jsp").forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String readParam(HttpServletRequest req,String paramName,String defaultValue) {
		String value=req.getParameter(paramName);
		if(StringUtil.isBlank(value)) {
			value=defaultValue;
		}
		return value;
	}	
	
	public static String readParam(HttpServletRequest req,String paramName) {
		return readParam(req, paramName, "");
	}
	
	public static BaseController getBaseControllerByModel(String model) {
		return controllerMap.get(model);
	}
	
}
