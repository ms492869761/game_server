<%@page import="com.persistence.login.bean.ServerEntity"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<%
	String tag =request.getParameter("tag");
	List<ServerEntity> stars=(List<ServerEntity>)request.getAttribute("stars");
	Collections.sort(stars, new Comparator<ServerEntity>() {
		public int compare(ServerEntity o1,ServerEntity o2) {
			return o1.getId()-o2.getId();
		}
	});
	for(ServerEntity star:stars){
		String auth=(String)request.getAttribute("auth");
	%>
	<img src="<%=star.getUrl()+"server.jpg?v="+System.currentTimeMillis()%>"  alt="状态" width="20" />
		<%if(tag.equals("selectrolelist")){ %>
		 <a href="<%=star.getUrl()%>mg/selectroleform.jsp?<%=auth %>" target="stardetail"><%=star.getName() %></a>
		<%} %>
		<%if(tag.equals("statistics")){ %>
		 <a href="<%=star.getUrl()%>mg/statistics.jsp?<%=auth %>" target="stardetail"><%=star.getName() %></a>
		<%} %>
		<%if(tag.equals("selectorderlist")){ %>
		 <a href="<%=star.getUrl()%>mg/selectorderform.jsp?<%=auth %>" target="stardetail"><%=star.getName() %></a>
		<%} %>
	<%}%>
</body>
</html>