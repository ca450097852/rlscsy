<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String certCode = request.getParameter("code");
	certCode = certCode == null ? "" : certCode.trim();
	//System.out.println("获得的验证码是：" + certCode);
	String code = (String) session.getAttribute("certCode");
	String str="0";
	if (certCode.equals(code)) {
		//System.out.println("验证码正确！");
		str = "1";
	} else {
		//System.out.println("验证码错误！");
		str = "0";
	}
	//System.out.println("Login_ajax退回去的值是：" + str);
	response.getWriter().write(str);
%>
