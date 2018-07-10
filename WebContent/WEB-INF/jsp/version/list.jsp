<%@page import="com.persistence.login.bean.DataPackEntity"%>
<%@page import="java.util.Map"%>
<%@page import="com.persistence.login.bean.DataClearVersionEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/common.css" type="text/css"
	media="screen" />
<title></title>
</head>
<body>
	<%
		List<DataClearVersionEntity> mainVersionList=(List<DataClearVersionEntity>)request.getAttribute("mainVersionList");
		Map<String,List<DataPackEntity>> dataVersionList=(Map<String,List<DataPackEntity>>)request.getAttribute("dataVersionList");
	%>
	<table width="100%" style="margin:auto;">
		<tr>
			<td>程序版本</td>
			<td>是否启用/数据包版本</td>
			<td>升级URL/数据包前版本</td>
			<td>版本升级说明/数据包URL</td>
			<td>操作/数据包大小</td>
		</tr>
		<%
			for(DataClearVersionEntity mainEntity:mainVersionList) {
				List<DataPackEntity> dataPackList=dataVersionList.get(mainEntity.getVersion());
				%>
					<tr>
						<td><%=mainEntity.getVersion() %></td>
						<td><%=mainEntity.getIsUse()==1?"启用":"关闭" %></td>
						<td><%=mainEntity.getUpdateUrl() %></td>
						<td><%=mainEntity.getUpdateContext() %></td>
						<td><a href="api.php?model=version&action=upd&version=<%=mainEntity.getVersion() %>">编辑</a></td>
					</tr>
					<tr>
						<td rowspan="<%=dataPackList.size()+1 %>"></td>
						<% 
							for(DataPackEntity entity:dataPackList) {
								%>
								<td><%=entity.getVersion() %></td>
								<td><%=entity.getPreVersion() %></td>
								<td><%=entity.getUrl() %></td>
								<td><%=entity.getSize() %></td>
								</tr>
								<tr>
								<%
							}
						%>
					<td colspan="2">添加新的数据包  主版本：<%=mainEntity.getVersion() %></td>
					<form action="api.php" method="post" enctype="multipart/form-data">
						<input type="hidden" name="model" value="version"/>
						<input type="hidden" name="action" value="upload"/>
						<input type="hidden" name="version" value="<%=mainEntity.getVersion() %>"/>
						<td><input type="file" name="file" size="50"/></td>
						<td><input type="submit" value="上传新版本"/></td>
					</form>
					
					
					
					
					</tr>
				<%
			}
		%>
		<tr>
			<td colspan="5"><a href="api.php?model=version&action=add">新增程序版本</a></td>
		</tr>
		
	</table>
	
	
	
</body>
</html>