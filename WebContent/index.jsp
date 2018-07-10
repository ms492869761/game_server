<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳转页</title>
</head>
<body>
	<%
	
		String userid=(String)request.getSession().getAttribute("user");
		if(userid==null||userid.equals("")){
			request.setAttribute("error", "请先登录");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);		
		}else{
			request.getRequestDispatcher("/WEB-INF/jsp/frame/index.jsp").forward(request, response);
		}	
%>
</body>
</html>