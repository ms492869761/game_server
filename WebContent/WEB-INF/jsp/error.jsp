<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>有话想对你说</title>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<table align="center">
		<tr>
			<td><%=request.getAttribute("msg")%></td>
		</tr>
		<tr align="center">
			<td><a href="javascript:history.go(-1);">返回</a></td>
		</tr>
	</table>

</body>
</html>