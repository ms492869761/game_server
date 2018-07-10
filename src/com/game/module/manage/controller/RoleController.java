package com.game.module.manage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.GameServer;
import com.game.module.manage.service.ManageService;
import com.persistence.login.bean.FunctionEntity;
import com.persistence.login.bean.RoleEntity;
import com.persistence.login.bean.ServerEntity;
import com.persistence.login.bean.UserEntity;
import com.persistence.login.dao.FunctionEntityDao;
import com.persistence.login.dao.RoleEntityDao;
import com.persistence.login.dao.UserEntityDao;
import com.util.StringUtil;

public class RoleController extends BaseController {

	private FunctionEntityDao functionDao = new FunctionEntityDao();

	private RoleEntityDao roleDao = new RoleEntityDao();

	private UserEntityDao userDao = new UserEntityDao();

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = ManageService.readParam(req, "action");
		if (action.equals("list")) {
			list(req, res);
			return;
		}
		if (action.equals("modify")) {
			modify(req, res);
			return;
		}
		if (action.equals("save")) {
			save(req, res);
			return;
		}
		if (action.equals("add")) {
			add(req, res);
			return;
		}
		if (action.equals("remove")) {
			remove(req, res);
			return;
		}
	}

	private void remove(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String roleId = ManageService.readParam(req, "roleid");
		if (roleId == null || roleId.equals("")) {
			ManageService.getInstance().error(req, res, "参数错误");
			return;
		}
		String userName = (String) req.getSession().getAttribute("userName");

		RoleEntity roleEntity = roleDao.selectOne(Integer.parseInt(roleId));
		if (roleEntity == null) {
			ManageService.getInstance().error(req, res, "不存在的角色");
			return;
		}
		UserEntity userEntity = userDao.selectOne(userName);
		if (roleEntity.getId().intValue() == userEntity.getRole()) {
			ManageService.getInstance().error(req, res, "当前使用的权限不能删除");
			return;
		}
		roleDao.delete(roleEntity);
		List<RoleEntity> roles = roleDao.selectAll();
		List<FunctionEntity> funs = functionDao.selectAll();
		HashMap<String, FunctionEntity> funmap = new HashMap<>();
		for (FunctionEntity functionEntity : funs) {
			funmap.put(functionEntity.getId(), functionEntity);
		}
		req.setAttribute("roles", roles);
		req.getRequestDispatcher("/WEB-INF/jsp/role/list.jsp").forward(req, res);
	}

	private void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("saveAction", "add");
		List<FunctionEntity> functionList = functionDao.selectAll();
		List<ServerEntity> allServerEntity = GameServer.getInstance().getStarService().getInstance().getAllStarEntity();
		req.setAttribute("funs", functionList);
		req.setAttribute("stars", allServerEntity);
		req.getRequestDispatcher("/WEB-INF/jsp/role/detail.jsp").forward(req, res);
	}

	private void save(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String readParam = ManageService.readParam(req, "saveAction");
		if (!readParam.equals("add")) {
			String roleId = ManageService.readParam(req, "roleid");
			String roleName = ManageService.readParam(req, "rolename");
			RoleEntity role = roleDao.selectOne(Integer.parseInt(roleId));
			if (role == null) {
				ManageService.getInstance().error(req, res, "不存在的角色");
				return;
			}

			String[] parmfuns = req.getParameterValues("funs");
			String[] parmstars = req.getParameterValues("stars");
			String funexpress = "";
			if (parmfuns != null)
				for (String string : parmfuns) {
					funexpress += string + ",";
				}
			String starexpress = "";
			if (parmstars != null)
				for (String string : parmstars) {
					starexpress += string + ",";
				}
			role.setName(roleName);
			role.setFuns(funexpress);
			role.setStars(starexpress);
			roleDao.update(role);

		} else {
			String roleName = ManageService.readParam(req, "rolename");
			List<RoleEntity> select = roleDao.selectByName(roleName);
			if (select != null && select.size() > 0) {
				ManageService.getInstance().error(req, res, "角色己存在");
				return;
			}
			String[] parmfuns = req.getParameterValues("funs");
			String[] parmstars = req.getParameterValues("stars");
			String funexpress = "";
			for (String string : parmfuns) {
				funexpress += string + ",";
			}

			String starexpress = "";
			for (String string : parmstars) {
				starexpress += string + ",";
			}
			RoleEntity role = new RoleEntity();
			role.setName(roleName);
			role.setFuns(funexpress);
			role.setStars(starexpress);
			roleDao.insert(role);
		}
		List<FunctionEntity> funs = functionDao.selectAll();
		HashMap<String, FunctionEntity> funmap = new HashMap<>();
		for (FunctionEntity functionEntity : funs) {
			funmap.put(functionEntity.getId(), functionEntity);
		}
		req.setAttribute("funmap", funmap);
		List<RoleEntity> roles = roleDao.selectAll();
		req.setAttribute("roles", roles);
		req.getRequestDispatcher("/WEB-INF/jsp/role/list.jsp").forward(req, res);

	}

	private void modify(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("saveAction", "update");
		List<FunctionEntity> functionList = functionDao.selectAll();
		List<ServerEntity> allServerEntity = GameServer.getInstance().getStarService().getAllStarEntity();
		req.setAttribute("funs", functionList);
		req.setAttribute("stars", allServerEntity);
		String roleId = req.getParameter("roleid");
		RoleEntity roleEntity = roleDao.selectOne(Integer.parseInt(roleId));
		List<String> roleFuns = new ArrayList<String>();
		String[] funSplit = roleEntity.getFuns().split(",");
		for (String string : funSplit) {
			if (StringUtil.isBlank(string)) {
				continue;
			}
			roleFuns.add(string);
		}
		req.setAttribute("rolefuns", roleFuns);
		List<Integer> roleStars = new ArrayList<Integer>();
		String[] starSplit = roleEntity.getStars().split(",");
		for (String string : starSplit) {
			if (StringUtil.isBlank(string)) {
				continue;
			}
			roleStars.add(Integer.parseInt(string));
		}
		req.setAttribute("rolestars", roleStars);
		req.setAttribute("role", roleEntity);
		req.getRequestDispatcher("/WEB-INF/jsp/role/detail.jsp").forward(req, res);
	}

	private void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<FunctionEntity> functionList = functionDao.selectAll();
		Map<String, FunctionEntity> functionMap = new HashMap<String, FunctionEntity>();
		for (FunctionEntity functionEntity : functionList) {
			functionMap.put(functionEntity.getId(), functionEntity);
		}
		List<ServerEntity> allServerEntity = GameServer.getInstance().getStarService().getAllStarEntity();
		List<RoleEntity> roleList = roleDao.selectAll();
		req.setAttribute("funmap", functionMap);
		req.setAttribute("stars", allServerEntity);
		req.setAttribute("roles", roleList);
		req.getRequestDispatcher("/WEB-INF/jsp/role/list.jsp").forward(req, res);
	}

}
