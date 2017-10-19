<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	Object obj = session.getAttribute("entStyle_QT");
	String bottomInfo = "技术支持：广州薪火网络科技有限公司";
	if(obj!=null){
		EntStyle entStyle = (EntStyle)obj;
		if(entStyle.getBottomInfo()!=null && !"".equals(entStyle.getBottomInfo())){
			bottomInfo = entStyle.getBottomInfo();
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<!-- 如果使用IE浏览器，优先使用IE高版本 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
	<!-- 如果使用双核浏览器，优先使用极速模式 -->
	<meta name="renderer" content="webkit"></meta>
    <title>底部</title>    
  </head>
<body>
	<!--footer-->
	<div class="footer mar_t35">
        	<div class="w mar0 padding_tb">
            	<p class="p_line"><a href="<%=basePath%>jsp/login/login.jsp">监管用户登录</a> <a class="a_border" href="<%=basePath%>company/login.jsp">企业用户登录</a> <a href="<%=basePath%>portal/register.jsp">企业用户注册</a></p>
            	<!-- <p>技术支持：广州薪火网络科技有限公司   肇庆市农业经济管理干部学校@版权所有Copyright</p> -->
            	<p><%=bottomInfo %></p>
            	<p>建议使用Internet Explorer 8.0以上版本, 极速模式下浏览本网站</p>            	
            </div>
        </div>
	<!--footer=end-->
		
		
  </body>
</html>
