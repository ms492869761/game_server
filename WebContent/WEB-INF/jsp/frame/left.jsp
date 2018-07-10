
<%@page import="com.persistence.login.bean.FunctionEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
<title>功能列表</title>
<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
<link rel="stylesheet" href="css/common.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="css/dtree/dtree.js"></script>
</head>

<body>
	<div class="dtree">
		<script type="text/javascript">
		<!--
		d = new dTree('d');		
		d.add(0,-1,'功能列表');
		<%List<FunctionEntity> list=(List<FunctionEntity> )request.getAttribute("funs");
		for(FunctionEntity fun:list) {
		%>
			d.add(<%=fun.getId()%>,<%=fun.getPid()%>,'<%=fun.getName()%>','<%=fun.getUrl()%>', "title", "mainFrame");
		<%
		}
		%>
		document.write(d);
		</script>
	</div>
</body>
</html>