<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%><%@page import="com.hontek.sys.pojo.EntStyle"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String uri = request.getRequestURI();
int wh = 0;
if(uri.endsWith("zhxx.jsp")){
	wh = 1;
}else if(uri.endsWith("xgmm.jsp")){
	wh = 2;
}else if(uri.endsWith("proList.jsp")||uri.endsWith("trace.jsp")){
	wh = 3;
}
LoginUser user = (LoginUser)session.getAttribute ( "loginUser" );
%>
<style>
.top_text{text-align:right;}
.top_text font{color:#060;}
</style>
<DIV class="TOP">
     <div class="logocenter">
     <div class="top_text">用户名：<font><%=user.getUserName() %></font> | <a onclick="logout();">退出</a></div>
<div class="logo"><img src="<%=basePath %>company/images/logo.png" />
</div>
     </div>
     <div class="navigation">
     	<ul>
         	<li <%=wh==0?"class=\"check\"":"" %>><a href="<%=basePath %>company/qyxx.jsp">企业信息</a></li>
            <li <%=wh==3?"class=\"check\"":"" %>><a href="<%=basePath %>company/proList.jsp">产品管理</a></li>           
            <li <%=wh==1?"class=\"check\"":"" %>><a href="<%=basePath %>company/zhxx.jsp">账号信息</a></li>
            <li <%=wh==2?"class=\"check\"":"" %>><a href="<%=basePath %>company/xgmm.jsp">修改密码</a></li>
         </ul>
         
     </div>
 </DIV>
 
 <script type="text/javascript">
 function logout(){
		$.messager.confirm('提示', '你确定要退出?', function(r){
			if(r){
				window.location.href="compangLogout.action";
			}
		});
	}
 </script>