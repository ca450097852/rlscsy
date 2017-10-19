<%@page contentType="image/jpeg" pageEncoding="utf-8"%>
<jsp:useBean id="image" scope="page"
	class="com.hontek.comm.util.CodeAction" />
<%
	String str = image.getCertPic(114, 46, response.getOutputStream());
	session.setAttribute("certCode", str);
	out.clear();
	out = pageContext.pushBody();
%>

