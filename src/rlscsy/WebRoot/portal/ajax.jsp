<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String referer = request.getHeader("Referer");
    response.sendRedirect(referer);
%>
