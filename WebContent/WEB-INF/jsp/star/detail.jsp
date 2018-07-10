<%@page import="com.persistence.login.bean.DataClearVersionEntity"%>
<%@page import="java.util.Map"%>
<%@page import="com.game.GameServer"%>
<%@page import="com.game.module.manage.service.ManageService"%>
<%@page import="com.persistence.login.bean.ServerEntity"%>
<%@page import="org.apache.commons.lang.Validate"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/common.css" type="text/css"
	media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
	request.setCharacterEncoding("utf-8");
	String saveAction = (String) request.getAttribute("saveAction");
	if (saveAction == null) {
		ManageService.getInstance().error(request, response, "参数错误");
		return;
	}
%>
<title>角色编辑</title>
</head>
<body>
	<form action="api.php" method="post">
		<input type="hidden" name="action" value="save" /> 
		<input type="hidden" name="model" value="star" />
		<input type="hidden" name="saveAction" value="<%=saveAction %>"/>
		<table>
		<%
			if (saveAction.equals("add")) {
		%>
		<tr>
			<td>服务器ID</td>
			<td align="left"><input type="text" name="serverId" /></td>
		</tr>
		<tr>
			<td>服务器名字</td>
			<td align="left"><input type="text" name="serverName" /></td>
		</tr>
		<tr>
			<td>服务器地址</td>
			<td align="left"><input type="text" name="serverUrl" /></td>
		</tr>
		<tr>
			<td>服务器开启时间</td>
			<td align="left"><input type="text" name="openTime"/></td>
		</tr>
		<tr>
			<td>服务器是否开启</td>
			<td align="left">
				<input type="radio" name="isOpen" value="1" checked="checked"/>开启  
				<input type="radio" name="isOpen" value="0"/>关闭   
				</td>
		</tr>
		<tr>
			<td>服务器状态</td>
			<td align="left">
				<input type="radio" name="state" value="0" checked="checked"/> 维护  
				<input type="radio" name="state" value="1"/> 正常
				<input type="radio" name="state" value="2"/> 推荐
				<input type="radio" name="state" value="3"/> 新服
				<input type="radio" name="state" value="4"/> 火爆
			</td>
		</tr>
		<tr>
				<td>可用的程序版本</td>
				<td>
					<%
						Map<String,DataClearVersionEntity> mainVersionMap= GameServer.getInstance().getVersionService().getMainVersion();
						for(DataClearVersionEntity entity:mainVersionMap.values()) {
							%> 
								<input type="radio" name="version" value="<%=entity.getVersion() %>"/><%=entity.getVersion() %>   
							<% 
						}
					%>
				</td>
			</tr>
		<%
			} else if(saveAction.equals("upd"))  {
				ServerEntity star = (ServerEntity) request.getAttribute("star");
			%>
			<tr>
				<td>服务器ID</td>
				<td align="left"><input type="text" name="serverId" value="<%=star.getId()%>" readonly="readonly"  /></td>
			</tr>
			<tr>
				<td>服务器名字</td>
				<td align="left"><input type="text" name="serverName" value="<%=star.getName()%>" /></td>
			</tr>
			<tr>
				<td>服务器地址</td>
				<td align="left"><input type="text" name="serverUrl" value="<%=star.getUrl() %>"/></td>
			</tr>
			<tr>
				<td>服务器开启时间</td>
				<td align="left"><input type="text" name="openTime" value="<%=star.getTime() %>"/></td>
			</tr>
			<tr>
				<td>服务器是否开启</td>
				<td align="left">
				<%if(star.getIsOpen()==1) {%>
						<input type="radio" name="isOpen" value="1" checked="checked"/>开启  
						<input type="radio" name="isOpen" value="0"/>关闭		
				<%} else {%>
						<input type="radio" name="isOpen" value="1" />开启  
						<input type="radio" name="isOpen" value="0" checked="checked"/>关闭		
				<%}%>
				</td>
			</tr>
			<tr>
				<td>服务器状态</td>
				<td align="left">
				<%if(star.getState()==0) {%>
						<input type="radio" name="state" value="0" checked="checked"/> 维护  
				<%} else {%>
						<input type="radio" name="state" value="0" /> 维护  
				<%}%>
				<%if(star.getState()==1) {%>
						<input type="radio" name="state" value="1" checked="checked"/> 正常  
				<%} else {%>
						<input type="radio" name="state" value="1" /> 正常 
				<%}%>
				<%if(star.getState()==2) {%>
						<input type="radio" name="state" value="2" checked="checked"/> 推荐  
				<%} else {%>
						<input type="radio" name="state" value="2" /> 推荐  
				<%}%>
				<%if(star.getState()==3) {%>
						<input type="radio" name="state" value="3" checked="checked"/> 新服  
				<%} else {%>
						<input type="radio" name="state" value="3" /> 新服  
				<%}%>
				<%if(star.getState()==4) {%>
						<input type="radio" name="state" value="4" checked="checked"/> 火爆
				<%} else {%>
						<input type="radio" name="state" value="4" /> 火爆
				<%}%>
				</td>
			</tr>			
			<tr>
				<td>可用的程序版本</td>
				<td>
					<%
						Map<String,DataClearVersionEntity> mainVersionMap= GameServer.getInstance().getVersionService().getMainVersion();
						for(DataClearVersionEntity entity:mainVersionMap.values()) {
							if(star.getVersion()!=null&& star.getVersion().equals(entity.getVersion())) {
								%> 
									<input type="radio" name="version" value="<%=entity.getVersion() %>" checked="checked"/><%=entity.getVersion() %>   
								<% 
							} else {
								%> 
									<input type="radio" name="version" value="<%=entity.getVersion() %>"/><%=entity.getVersion() %>   
								<% 
							}
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