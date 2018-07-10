<%@page import="com.game.GameServer"%>
<%@page import="com.game.module.manage.service.ManageService"%>
<%@page import="com.persistence.login.bean.DataClearVersionEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<%
		String saveAction=(String) request.getAttribute("saveAction");
	if (saveAction == null) {
		ManageService.getInstance().error(request, response, "参数错误");
		return;
	}		
	%>
	<form action="api.php" method="post">
		<input type="hidden" name="action" value="save"/>
		<input type="hidden" name="model" value="version"/>
		<input type="hidden" name="saveAction" value="<%=saveAction %>"/>
		<table>
		<%
			if (saveAction.equals("add")) {
		%>
			<tr>
				<td>版本号</td>
				<td><input type="text" name="version"/></td>
			</tr>
			<tr>
				<td>是否使用</td>
				<td>
					<input type="radio" name="isUse" value="1" checked="checked"/>启用
					<input type="radio" name="isUse" value="0" />禁用
				</td>
			</tr>
			<tr>
				<td>下版本程序升级地址</td>
				<td><input type="text" name="updateUrl"/></td>
			</tr>
			<tr>
				<td>下版本程序升级介绍</td>
				<td><input type="text" name="updateContext"/></td>
			</tr>
		<%
			} else if(saveAction.equals("upd"))  {
				String mainVersion= (String)request.getAttribute("version");
				DataClearVersionEntity mainEntity= GameServer.getInstance().getVersionService().getMainVersion().get(mainVersion);
		%>		
			<tr>
				<td>版本号</td>
				<td><input type="text" name="version" value="<%=mainVersion %>" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>是否使用</td>
				<td>
					<input type="radio" name="isUse" value="1" <%=mainEntity.getIsUse()==1?"checked=\"checked\"":"" %>/>启用
					<input type="radio" name="isUse" value="0" <%=mainEntity.getIsUse()==0?"checked=\"checked\"":"" %>/>禁用
				</td>
			</tr>
			<tr>
				<td>下版本程序升级地址</td>
				<td><input type="text" name="updateUrl" value="<%=mainEntity.getUpdateUrl() %>"/></td>
			</tr>
			<tr>
				<td>下版本程序升级介绍</td>
				<td><input type="text" name="updateContext" value="<%=mainEntity.getUpdateContext() %>"/></td>
			</tr>
		<%
			}
		%>
		<tr>
			<td colspan="2"><input type="submit"/></td>
		</tr>
		</table>
	</form>
	
</body>
</html>