<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src="css/jqueryui/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {

	});
	function sm() {
		$("#sb").val("登录中...");
		$("#sb").attr("disabled", "disabled");
		return true;
	}
</script>
<style type="text/css">
<!--
* {
	padding: 0;
	margin: 0;
}

html,body {
	height: 100%;
	width: 100%;
}

#Layer1 {
	position: absolute;
	width: 100%;
	height: 100%;
	
	background: repeat url('css/images/bg2.png');
}

#Layer2 {
	text-align: center;
	font-size: 75%;
	position: absolute;
	width: 300px;
	height: 250px;
	left: 45%;
	top: 50%;
	margin-left: -200px;
	margin-top: -200px;
	background-color: #FFFFFF;
	line-height: 40px;
	border-radius: 20px;
}
-->
</style>
</head>
<body>
	<div id="Layer1">
		<div id="Layer2">
			<form action="api.php?action=login" method="post"
				onsubmit="return sm();" id="loginfm">
				<table align="center">
					<tr align="center">
						<td colspan="2">
							<h3>
								<img src="css/images/logo.png" />
							</h3>

						</td>
					</tr>
					<tr>
						<td>用户名:</td>
						<td><input type="text" name="username" class="input-medium" />
						</td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input type="password" name="password"
							class="input-medium" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="登   录    "
							id="sb" /></td>
					</tr>
				</table>
				${error}
			</form>
		</div>
	</div>
</html>