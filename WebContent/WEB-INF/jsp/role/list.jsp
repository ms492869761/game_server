<%@page import="com.persistence.login.bean.RoleEntity"%>
<%@page import="com.persistence.login.bean.FunctionEntity"%>
<%@page import="com.persistence.login.bean.ServerEntity"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/common.css" type="text/css"
	media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%
	HashMap<String, FunctionEntity> funmap = (HashMap<String, FunctionEntity>) request.getAttribute("funmap");
	List<ServerEntity> stars = (List<ServerEntity>) request.getAttribute("stars");
	List<RoleEntity> roles = (List<RoleEntity>) request.getAttribute("roles");
	
%>
</head>
<body>
	<table width="100%" style="margin:auto;">
		<tr>
			<td>角色名</td>
			<td>操作</td>
			<td></td>
			<td></td>
		</tr>
		<%
			for (RoleEntity role : roles) {
		%>
		<tr>
			<td rowspan="3"><%=role.getName()%></td>
			<td rowspan="3"><a
				href="api.php?model=role&action=modify&roleid=<%=role.getId()%>" name="">编辑 </a>&nbsp;<a
				href="api.php?model=role&action=remove&roleid=<%=role.getId()%>">删除</a></td>
			<td align="left">功能：</td>
			<td align="left">
				<%
					String[] strs = role.getFuns().split(",");
						for (String str : strs) {
							FunctionEntity fun=funmap.get(str);
							if(fun!=null){
								out.print(fun.getName()+",");	
							}else{
								out.print(str);
							}
							
						}
				%>
			</td>
		</tr>
		<tr align="left">
			<td>星球：</td>
			<td><%=role.getStars()%></td>
		</tr>
		<tr align="left">
			<td>平台：</td>
			<td><%=role.getPids() %></td>
		</tr> 
	<%
		}
	%>
	</table>
	<p align="left">
		<a href="api.php?model=role&action=add" name="">增加角色 </a>
	</p>
	<table></table>
</body>
</html>