package com.game.module.manage.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jforum.util.legacy.commons.fileupload.FileItem;

import org.apache.commons.io.FileUtils;

import com.game.GameServer;
import com.game.module.manage.service.ManageService;
import com.persistence.login.bean.DataClearVersionEntity;
import com.persistence.login.bean.DataPackEntity;
import com.persistence.login.dao.DataClearVersionEntityDao;
import com.persistence.login.dao.DataPackEntityDao;
import com.util.StringUtil;

@SuppressWarnings("deprecation")
public class VersionController extends BaseController {

	private DataClearVersionEntityDao mainVersionDao = new DataClearVersionEntityDao();

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (StringUtil.isBlank(action)) {
			action = (String) req.getAttribute("action");
		}
		if (action.equals("list")) {
			list(req, res);
			return;
		}
		if (action.equals("add")) {
			add(req, res);
			return;
		}
		if (action.equals("upd")) {
			upd(req, res);
			return;
		}
		if (action.equals("save")) {
			save(req, res);
			return;
		}
		if (action.equals("upload")) {
			upload(req, res);
			return;
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private void upload(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String targetPath = System.getProperty("catalina.home") + "/webapps/updatepack"; // 目标存储路径，服务器部署目录下
		try {
			File dir = new File(targetPath);
			if (!dir.exists()) {
				dir.mkdir();
			}
			String mainVersion = (String) req.getAttribute("version");
			List<DataPackEntity> versionList = GameServer.getInstance().getVersionService().getVersionList(mainVersion);
			String[] mainSplit = mainVersion.split("\\.");
			mainVersion = mainSplit[0] + "." + mainSplit[1] + ".";
			int beforeVersion = 0;
			int afterVersion = 1;
			for (DataPackEntity entity : versionList) {
				String[] split = entity.getVersion().split("\\.");
				int version = Integer.parseInt(split[2]);
				if (version > beforeVersion) {
					beforeVersion = version;
					afterVersion = beforeVersion + 1;
				}
			}
			String preVersion = mainVersion + beforeVersion;
			String version = mainVersion + afterVersion;

			FileItem file = (FileItem) req.getAttribute("file");
			long size = file.getSize();
			if (size <= 0) {
				ManageService.getInstance().error(req, res, "上传文件大小取值异常 请重新上传");
				return;
			}
			File sFile = new File(file.getName());
			file.write(sFile);
			File tFile = new File(targetPath, version + ".xml");
			if (tFile.exists()) {
				System.out.println("同名文件已上传！");
				sFile.delete();
				return ;
			} else {
				FileUtils.copyFile(sFile, tFile);
				sFile.delete();
				System.out.println("文件上传成功");
			}
			// 上传FTP
//			FTPClient ftpClient=new FTPClient();
//			ftpClient.setRemoteHost("c790c5f3dea35bc5022d1b.ftp.tan14.net");
//			ftpClient.setRemotePort(21);
//			ftpClient.setControlEncoding("UTF-8");
//			ftpClient.connect();
//			ftpClient.login("sprootcdn", "uLaY2Z1k");
//			
//			ftpClient.setType(FTPTransferType.BINARY);
//			String ftpPath="/updatepack";
//			ftpClient.chdir(ftpPath);
//			ftpClient.put(targetPath+"/"+tFile.getName(),ftpPath+"/"+ tFile.getName());
			
			
			DataPackEntity insertEntity = new DataPackEntity();
			insertEntity.setVersion(version);
			insertEntity.setPreVersion(preVersion);
			insertEntity.setUrl(GameServer.getInstance().getGlobalConfig().getDataPackageUrl() + "updatepack/" + version + ".xml");
			System.out.println("上传文件大小：" + size + "Byte");
			insertEntity.setSize(size / (float) 1024 / (float) 1024);
			DataPackEntityDao dao = new DataPackEntityDao();
			dao.insert(insertEntity);
			GameServer.getInstance().getVersionService().addVersionEntity(insertEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		list(req, res);
	}

	private void save(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String saveAction = req.getParameter("saveAction");
		String version = req.getParameter("version");
		String isUse = req.getParameter("isUse");
		String updateUrl = req.getParameter("updateUrl");
		String updateContext = req.getParameter("updateContext");
		if (saveAction.equals("add")) {
			DataClearVersionEntity dataClearVersionEntity = GameServer.getInstance().getVersionService().getMainVersion().get(version);
			if (dataClearVersionEntity != null) {
				ManageService.getInstance().error(req, res, "当前程序版本好以存在");
				return;
			}
			DataClearVersionEntity entity = new DataClearVersionEntity();
			entity.setVersion(version);
			entity.setIsUse(Integer.parseInt(isUse));
			entity.setUpdateUrl(updateUrl);
			entity.setUpdateContext(updateContext);
			Map<String, DataClearVersionEntity> mainVersion = GameServer.getInstance().getVersionService().getMainVersion();
			mainVersion.put(entity.getVersion(), entity);
			mainVersionDao.insert(entity);
		} else if (saveAction.equals("upd")) {
			DataClearVersionEntity entity = GameServer.getInstance().getVersionService().getMainVersion().get(version);
			entity.setIsUse(Integer.parseInt(isUse));
			entity.setUpdateUrl(updateUrl);
			entity.setUpdateContext(updateContext);
			mainVersionDao.update(entity);
		}

		list(req, res);
	}

	private void upd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String version = req.getParameter("version");
		req.setAttribute("version", version);
		req.setAttribute("saveAction", "upd");
		req.getRequestDispatcher("/WEB-INF/jsp/version/mainDetail.jsp").forward(req, res);
	}

	private void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("saveAction", "add");
		req.getRequestDispatcher("/WEB-INF/jsp/version/mainDetail.jsp").forward(req, res);
	}

	private void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<DataClearVersionEntity> selectAll = mainVersionDao.selectAll();
		req.setAttribute("mainVersionList", selectAll);
		Map<String, List<DataPackEntity>> dataPackMap = new HashMap<String, List<DataPackEntity>>();
		for (DataClearVersionEntity dataClearVersionEntity : selectAll) {
			List<DataPackEntity> versionList = GameServer.getInstance().getVersionService().getVersionList(dataClearVersionEntity.getVersion());
			dataPackMap.put(dataClearVersionEntity.getVersion(), versionList);
		}
		req.setAttribute("dataVersionList", dataPackMap);
		req.getRequestDispatcher("/WEB-INF/jsp/version/list.jsp").forward(req, res);
	}

}
