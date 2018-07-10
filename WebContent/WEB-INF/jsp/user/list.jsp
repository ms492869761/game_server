<%@page import="com.persistence.login.bean.RoleEntity"%>
<%@page import="com.persistence.login.bean.UserEntity"%>
<%@page import="com.util.TimeUtil"%>
<%@page import="javax.management.relation.RoleList"%>
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
	
	List<UserEntity> list = (List<UserEntity>) request.getAttribute("list");
	List<RoleEntity> roleList = (List<RoleEntity>) request.getAttribute("rolelist");
	UserEntity userEntity = (UserEntity) request.getAttribute("user");
%>
</head>
<body>
	<%
		if (userEntity != null) {
	%>
	<form action="api.php" method="post">
		<input type="hidden" name="model" value="user" /> <input
			type="hidden" name="action" value="save" />
		<table>
			<tr>
				<td>用户名</td>
				<td>权限</td>
				<td>旧密码</td>
				<td>新密码</td>
				<td>确认密码</td>
			</tr>
			<tr>
				<td><input type="text" name="username"
					value="<%=userEntity.getUser()%>"></td>
				<td><select name="role">
						<%
							for (RoleEntity role : roleList) {
						%>
						<option value="<%=role.getId()%>"
							<%=userEntity.getRole() == role.getId() ? "selected=\"selected\""
							: ""%>><%=role.getName()%></option>
						<%
							}
						%>
				</select></td>
				<td><input type="password" name="oldpass" ></td>
				<td><input type="password" name="pass1" ></td>
				<td><input type="password" name="pass2" ></td>
			</tr>
			<tr>
				<td colspan="5" align="center"><input type="submit" value="保存"></td>
			</tr>
		</table>
	</form>


	<%
		} else {
	%>
	<form action="api.php" method="post">
		<input type="hidden" name="model" value="user" /> <input
			type="hidden" name="action" value="add" />
		<table>
			<tr align="left">
				<td>用户名</td>
				<td>权限</td>
				<td>密码</td>
				<td>密码确认</td>
			</tr>
			<tr align="left">
				<td><input type="text" name="username"></td>
				<td><select name="role">
						<%
							for (RoleEntity role : roleList) {
						%>
						<option value="<%=role.getId()%>" selected="selected"><%=role.getName()%></option>
						<%
						
							}
						%>
				</select></td>
				<td><input type="password" name="pass1"></td>
				<td><input type="password" name="pass2"></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="保存"></td>
			</tr>
		</table>
	</form>
	<%
		}
	%>

	<br>
	<br>
	<br>
	<br>
	<br>

	<table>
		<tr>
			<td colspan="7"><span>用户列表</span></td>
		</tr>
		<tr>
			<td align="left">用户名</td>
			<td>角色</td>
			<td>创建时间</td>
			<td>最后编辑时间</td>
			<td>最后登录时间</td>
			<td>最后登录ip</td>
			<td>操作</td>
		</tr>
		<%
			for (UserEntity user : list) {
		%>
		<tr>
			<td align="left"><%=user.getUser()%></td>
			<%
				for(RoleEntity roleEntity:roleList) {
					if(roleEntity.getId()==user.getRole().intValue()) {
						%><td align="left"><%=roleEntity.getName()%></td><%			
					}
				}
			
			%>
			
			<td><%=TimeUtil.getFormatDate(user.getCreatetime() == null ? 0 : user.getCreatetime(),"yyyy-MM-dd HH:mm:ss")%></td>
			<td><%=TimeUtil.getFormatDate(user.getLastedittime() == null ? 0 : user.getLastedittime(), "yyyy-MM-dd HH:mm:ss")%></td>
			<td><%=TimeUtil.getFormatDate(user.getLastlogintime() == null ? 0 : user.getLastlogintime(), "yyyy-MM-dd HH:mm:ss")%></td>
			<td><%=user.getLastloginip()%></td>
			<td><a
				href="api.php?model=user&action=modify&user=<%=user.getUser()%>">修改</a>
				<a href="api.php?model=user&action=remove&user=<%=user.getUser()%>">删除</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>