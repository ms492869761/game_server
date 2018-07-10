<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<frameset rows="50,*" cols="*" frameborder="yes" border="1"
	framespacing="1">
	<frame src="api.php?model=frame&action=top" name="topFrame" scrolling="NO" noresize>
	<frameset rows="*" cols="250,*" framespacing="0" frameborder="yes"
		border="1">
		<frame src="api.php?model=frame&action=left" name="leftFrame" scrolling="AUTO" noresize>
		<frame src="api.php?model=frame&action=main" name="mainFrame">
	</frameset>
</frameset>