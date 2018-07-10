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
	List<ServerEntity> stars=(List<ServerEntity>)request.getAttribute("stars");
%>
</head>
<body>
	<table width="100%" style="margin:auto;">
		<tr height="10">
			<td>服务器ID</td>
			<td>服务器标识</td>
			<td>服务器URL</td>
			<td>服务器开关</td>
			<td>服务器状态</td>
			<td>服务器版本</td>
			<td>操作</td>
		</tr>
		<%
			for (ServerEntity star: stars) {
		%>
		<tr>
			<td><%=star.getId()%></td>
			<td><%=star.getName()%></td>
			<td><%=star.getUrl() %></td>
			<td><a href="api.php?model=star&action=open&serverId=<%=star.getId() %>"><%=star.getIsOpen()==1?"开启":"关闭" %></a></td>
			<td>
				<%
					switch(star.getState()) {
					case 0:
						%>维护<%
						break;
					case 1:
						%>正常<%
						break;
					case 2:
						%>推荐<%
						break;
					case 3:
						%>新服<%
						break;
					case 4:
						%>火爆<%
						break;
						
					}
				%>
			</td>
			<td><%=star.getVersion() %></td>
			<td><a href="api.php?model=star&action=upd&serverId=<%=star.getId() %>">修改</a><a href="api.php?model=star&action=remove&serverId=<%=star.getId() %>">删除</a></td>
		</tr>
		<%
		}
	%>
	</table>
	<a href="api.php?model=star&action=add">新增服务器</a>
</body>
</html>