package com.game.module.manage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.GameServer;
import com.game.module.manage.service.ManageService;
import com.persistence.login.bean.RoleEntity;
import com.persistence.login.bean.ServerEntity;
import com.persistence.login.bean.UserEntity;
import com.persistence.login.dao.RoleEntityDao;
import com.persistence.login.dao.ServerEntityDao;
import com.persistence.login.dao.UserEntityDao;

public class ServerController extends BaseController{
	
	private ServerEntityDao serverDao=new ServerEntityDao();
	
	private UserEntityDao userDao=new UserEntityDao();
	
	private RoleEntityDao roleDao=new RoleEntityDao();
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = ManageService.readParam(req, "action");
		if(action.equals("list")) {
			list(req, res);
			return;
		}
		if(action.equals("open")) {
			open(req, res);
			return;
		}
		if(action.equals("add")) {
			add(req, res);
			return ;
		}
		if(action.equals("upd")) {
			upd(req, res);
			return ;
		}
		if(action.equals("save")) {
			save(req, res);
			return ;
		}
		if(action.equals("remove")) {
			remove(req, res);
			return ;
		}
		if(action.equals("frame")) {
			frame(req,res);
			return ;
		}
	}
	
	
	private void frame(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String location = ManageService.readParam(req, "location");
		if (location == null || location.equals("")) {
			ManageService.getInstance().error(req, res, "参数错误");
			return;
		}
		if(location.equals("top")){
			List<ServerEntity> stars = GameServer.getInstance().getStarService().getAllStarEntity();
			if (stars == null) {
				ManageService.getInstance().error(req, res, "没有星球");
				return;
			}
			String userName= req.getSession().getAttribute("userName").toString();
			UserEntity userEntity = userDao.selectOne(userName);
			RoleEntity roleEntity = roleDao.selectOne(userEntity.getRole());
			String stars2 = roleEntity.getStars();
			String[] split = stars2.split(",");
			List<ServerEntity> starEntities=new ArrayList<ServerEntity>();
			for(ServerEntity star:stars) {
				for(String starName:split) {
					if(starName.equals(star.getId()+"")) {
						starEntities.add(star);
						break;
					}
				}
			}
			req.setAttribute("stars", starEntities);
			req.getRequestDispatcher("/WEB-INF/jsp/star/frame/top.jsp").forward(req, res);
		}
		if(location.equals("main")){
			req.getRequestDispatcher("/WEB-INF/jsp/star/frame/main.jsp").forward(req, res);
		}
		if(location.equals("index")){
			req.getRequestDispatcher("/WEB-INF/jsp/star/frame/index.jsp").forward(req, res);
		}
		
	}


	private void remove(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String serverId= req.getParameter("serverId");
		ServerEntity serverById = GameServer.getInstance().getStarService().getServerById(Integer.parseInt(serverId));
		GameServer.getInstance().getStarService().getAllStarEntity().remove(serverById);
		serverDao.delete(serverById);
		list(req, res);
	}


	private void save(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String saveAction = req.getParameter("saveAction");
		String serverId = req.getParameter("serverId");
		String serverName= req.getParameter("serverName");
		String serverUrl=req.getParameter("serverUrl");
		String openTime=req.getParameter("openTime");
		String isOpen=req.getParameter("isOpen");
		String state=req.getParameter("state");
		String version=req.getParameter("version");
		if(saveAction.equals("add")) {
			ServerEntity serverById = GameServer.getInstance().getStarService().getServerById(Integer.parseInt(serverId));
			if(serverById!=null) {
				ManageService.getInstance().error(req, res, "此服务器ID已被创建");
				return ;
			}
			ServerEntity entity=new ServerEntity();
			entity.setId(Integer.parseInt(serverId));
			entity.setName(serverName);
			entity.setUrl(serverUrl);
			entity.setTime(Long.parseLong(openTime));
			entity.setIsOpen(Integer.parseInt(isOpen));
			entity.setState(Integer.parseInt(state));
			entity.setVersion(version);
			GameServer.getInstance().getStarService().getAllStarEntity().add(0, entity);
			serverDao.insert(entity);
		} else if (saveAction.equals("upd")) {
			ServerEntity entity = GameServer.getInstance().getStarService().getServerById(Integer.parseInt(serverId));
			entity.setId(Integer.parseInt(serverId));
			entity.setName(serverName);
			entity.setUrl(serverUrl);
			entity.setTime(Long.parseLong(openTime));
			entity.setIsOpen(Integer.parseInt(isOpen));
			entity.setState(Integer.parseInt(state));
			entity.setVersion(version);
			serverDao.update(entity);
		}
		list(req, res);
	}


	private void upd(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		String serverId = req.getParameter("serverId");
		ServerEntity serverById =GameServer.getInstance().getStarService().getServerById(Integer.parseInt(serverId));
		req.setAttribute("star", serverById);
		req.setAttribute("saveAction", "upd");
		req.getRequestDispatcher("/WEB-INF/jsp/star/detail.jsp").forward(req, res);
	}


	private void add(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		req.setAttribute("saveAction", "add");
		req.getRequestDispatcher("/WEB-INF/jsp/star/detail.jsp").forward(req, res);
	}


	public void list(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		List<ServerEntity> allServerEntity = GameServer.getInstance().getStarService().getAllStarEntity();
		req.setAttribute("stars", allServerEntity);
		req.getRequestDispatcher("/WEB-INF/jsp/star/list.jsp").forward(req, res);
	}
	
	public void open(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		String serverId = req.getParameter("serverId");
		ServerEntity serverById = GameServer.getInstance().getStarService().getServerById(Integer.parseInt(serverId));
		serverById.setIsOpen(serverById.getIsOpen()==1?0:1);
		serverDao.update(serverById);
		list(req, res);
	}

}
