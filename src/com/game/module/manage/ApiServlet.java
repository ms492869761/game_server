package com.game.module.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jforum.util.legacy.commons.fileupload.DefaultFileItemFactory;
import net.jforum.util.legacy.commons.fileupload.DiskFileUpload;
import net.jforum.util.legacy.commons.fileupload.FileItem;

import com.game.module.manage.controller.BaseController;
import com.game.module.manage.service.ManageService;
import com.game.module.star.net.handler.Req10003SelectServerHandler;
import com.persistence.login.bean.FunctionEntity;
import com.util.HttpUtil;
import com.util.StringUtil;
import com.util.Symbol;

/**
 * Servlet implementation class ApiServlet
 */
@WebServlet("/api.php")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApiServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action=ManageService.readParam(req, "action");
		if(action.equalsIgnoreCase("login")) {
			ManageService.getInstance().login(req, res);
			return ;
		}
		String userName=(String)req.getSession().getAttribute("userName");
		if(userName==null||userName.equals("")) {
			ManageService.getInstance().error(req, res, "请先登录");
			return ;
		}
		String password="****";
		String time=System.currentTimeMillis()+"";
		List<Integer> starList=(List<Integer>)req.getSession().getAttribute("stars");
		String stars="";
		for (Integer starId : starList) {
			stars+=starId+Symbol.DOUHAO;
		}
		List<FunctionEntity> functionList=(List<FunctionEntity>)req.getSession().getAttribute("funs");
		String funs="";
		for (FunctionEntity functionEntity : functionList) {
			funs+=functionEntity.getId()+",";
		}
		String session=req.getSession().getId();
		String sign = HttpUtil.Md5(ManageService.key+userName+password+time+stars+funs+session);
		req.setAttribute("auth",  "a="+userName+"&b="+password+"&c="+time+"&d="+stars+"&e="+funs+"&f="+session+"&g="+sign);	
		String model=ManageService.readParam(req, "model");
		if(StringUtil.isBlank(model)) {
			try {
				DefaultFileItemFactory factory=new DefaultFileItemFactory();
				DiskFileUpload up=new DiskFileUpload(factory);
				List<FileItem> ls=up.parseRequest(req);
				Map<String,FileItem> fileItemMap=new HashMap<String,FileItem>();
				for(FileItem file:ls) {
					fileItemMap.put(file.getFieldName(), file);
				}
				model=fileItemMap.get("model").getString("UTF-8");
				if(model.equals("version")) {
					req.setAttribute("action", fileItemMap.get("action").getString("UTF-8"));
					req.setAttribute("version", fileItemMap.get("version").getString("UTF-8"));
					req.setAttribute("file", fileItemMap.get("file"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		BaseController baseControllerByModel = ManageService.getBaseControllerByModel(model);
		if(baseControllerByModel!=null) {
			baseControllerByModel.service(req, res);
		} else {
			ManageService.getInstance().error(req, res, "未知模块");
		}
		
		
		
		
		
	}

}
