package com.game.module.manage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.module.manage.service.ManageService;
import com.persistence.login.bean.RoleEntity;
import com.persistence.login.bean.UserEntity;
import com.persistence.login.dao.RoleEntityDao;
import com.persistence.login.dao.UserEntityDao;
import com.util.StringUtil;

public class UserController extends BaseController{
	
	private RoleEntityDao roleDao=new RoleEntityDao();
	
	private UserEntityDao userDao=new UserEntityDao();
	
	
	
	
	
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action=ManageService.readParam(req, "action");
		if(action.equals("list")) {
			list(req, res);
			return ;
		}
		if(action.equals("modify")) {
			modify(req, res);
			return ;
		}
		if(action.equals("save")) {
			save(req, res);
			return ;
		}
		if(action.equals("add")) {
			add(req, res);
			return ;
		}
		if(action.equals("remove")) {
			remove(req, res);
			return ;
		}
		
	}
	
	public void list(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		List<RoleEntity> roleAll = roleDao.selectAll();
		List<UserEntity> userAll = userDao.selectAll();
		req.setAttribute("list", userAll);
		req.setAttribute("rolelist", roleAll);
		req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, res);
	}

	
	public void modify(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		
		List<RoleEntity> roleAll = roleDao.selectAll();
		List<UserEntity> userAll = userDao.selectAll();
		String userName = req.getParameter("user");
		UserEntity userEntity = userDao.selectOne(userName);
		req.setAttribute("list", userAll);
		req.setAttribute("rolelist", roleAll);
		req.setAttribute("user", userEntity);
		req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, res);
		
		
	}
	
	
	public void save(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String userName = req.getParameter("username");
		UserEntity userEntity = userDao.selectOne(userName);
		String oldPass = req.getParameter("oldpass");
		if(!userEntity.getPassword().equals(oldPass)) {
			ManageService.getInstance().error(req, res, "鍘熷瘑鐮侀敊璇�");
			return ;
		}
		String pass1 = req.getParameter("pass1");
		String pass2 = req.getParameter("pass2");
		if(StringUtil.isBlank(pass1)||StringUtil.isBlank(pass2)||!pass1.equals(pass2)) {
			ManageService.getInstance().error(req, res, "鏂板瘑鐮佷簩娆¤緭鍏ラ敊璇�");
			return ;
		}
		String roleId = req.getParameter("role");
		RoleEntity roleEntity = roleDao.selectOne(Integer.parseInt(roleId));
		if(roleEntity==null) {
			ManageService.getInstance().error(req, res, "閫夋嫨鏉冮檺涓嶅瓨鍦�");
			return ;
		}
		userEntity.setRole(Integer.parseInt(roleId));
		userEntity.setPassword(pass1);
		userDao.update(userEntity);
		list(req, res);
	}
	
	
	public void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String roleId = req.getParameter("role");
		String pass1 = req.getParameter("pass1");
		String pass2 = req.getParameter("pass2");
		UserEntity userEntity = userDao.selectOne(userName);
		if(userEntity!=null) {
			ManageService.getInstance().error(req, res, "姝よ处鍙峰凡琚垱寤�");
			return;
		}
		RoleEntity roleEntity = roleDao.selectOne(Integer.parseInt(roleId));
		if(roleEntity==null) {
			ManageService.getInstance().error(req, res, "閫夋嫨鏉冮檺涓嶅瓨鍦�");
			return ;
		}
		if(StringUtil.isBlank(pass1)||StringUtil.isBlank(pass2)||!pass1.equals(pass2)) {
			ManageService.getInstance().error(req, res, "瀵嗙爜杈撳叆閿欒");
			return ;
		}
		userEntity=new UserEntity();
		userEntity.setUser(userName);
		userEntity.setCreatetime(System.currentTimeMillis());
		userEntity.setPassword(pass1);
		userEntity.setRole(Integer.parseInt(roleId));
		userDao.insert(userEntity);
		list(req, res);
	}
	
	
	public void remove(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userName = req.getParameter("user");
		String sUserName = (String)req.getSession().getAttribute("userName");
		if(sUserName.equals(userName)) {
			ManageService.getInstance().error(req, res, "涓嶈兘鍒犻櫎姝ｅ湪浣跨敤璐﹀彿");
			return ;
		}
		UserEntity userEntity = userDao.selectOne(userName);
		if(userEntity!=null) {
			userDao.delete(userEntity);
		}
		list(req, res);
		
		
		
	}
	
	
}
