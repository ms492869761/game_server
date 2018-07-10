<%@page import="com.persistence.login.bean.RoleEntity"%>
<%@page import="com.game.module.manage.service.ManageService"%>
<%@page import="com.persistence.login.bean.FunctionEntity"%>
<%@page import="com.persistence.login.bean.ServerEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/common.css" type="text/css"
	media="screen" />

<%
	request.setCharacterEncoding("utf-8");
	String saveAction = (String) request.getAttribute("saveAction");
	List<ServerEntity> stars = (List<ServerEntity>) request.getAttribute("stars");
	List<FunctionEntity> funs = (List<FunctionEntity>) request.getAttribute("funs");
	if (saveAction == null || stars == null || funs == null) {
		ManageService.getInstance().error(request, response, "参数错误");
		return;
	}
	
%>
<title>角色编辑</title>
</head>
<body>
	<form action="api.php" method="post">
		<input type="hidden" name="saveAction" value="<%=saveAction%>" /> 
		<input type="hidden" name="action" value="save" /> 
		<input type="hidden" name="model" value="role" />
		<table>
			<%
				if (saveAction.equals("add")) {
			%>
			<tr>
				<td>角色名</td>
				<td align="left"><input type="text" name="rolename"  /></td>
			</tr>
			<tr>
				<td>服务器</td>
				<td align="left">
					<%
						for (ServerEntity star : stars) {
					%><input type="checkbox" name="stars" value="<%=star.getId()%>" /><%=star.getName()%>
					<%
						}
					%>
				</td>

			</tr>

			<tr>
				<td>功能</td>
				<td align="left">
					<%
						for (FunctionEntity fun : funs) {
					%><input type="checkbox" name="funs" value="<%=fun.getId()%>" /><%=fun.getName()%>
					<%
						}
					%>
				</td>
			</tr>
			<%
				} else {
					RoleEntity role = (RoleEntity) request.getAttribute("role");
					List<String> rolefuns = (List<String>) request.getAttribute("rolefuns");
					List<Integer> rolestars = (List<Integer>) request.getAttribute("rolestars");
			%>
			<tr>
				<td>角色名</td>
				<td><input type="text" name="rolename" value="<%=role.getName()%>" /> 
				<input type="hidden" name="roleid" value="<%=role.getId()%>" /></td>
			</tr>
			<tr>
				<td>星球</td>
				<td align="left">
					<%
						for (ServerEntity star : stars) {
					%><input type="checkbox" name="stars" value="<%=star.getId()%>"
					<%if (rolestars.contains(star.getId())) {
						out.print("checked=checked");
					}%> /><%=star.getName()%>
					<%
						}
					%>
				</td>

			</tr>
			<tr>
				<td>功能</td>
				<td align="left">
					<%
						for (FunctionEntity fun : funs) {
					%><input type="checkbox" name="funs" value="<%=fun.getId()%>"
					<%if (rolefuns.contains(fun.getId())) {
						out.print("checked=checked");
					}%> /><%=fun.getName()%>
					<%
						}
					%>
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td colspan="2"><input type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>