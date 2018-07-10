<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<frameset rows="150,*" cols="*" frameborder="yes" border="1"
	framespacing="1">
	<frame src="api.php?model=star&action=frame&location=top&tag=<%=request.getParameter("tag") %>" name="starlist">
	<frame src="api.php?model=star&action=frame&location=main" name="stardetail">
</frameset>