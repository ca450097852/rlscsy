<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hontek.sys.pojo.TsRolePurv" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<title>广州市肉类蔬菜流通追溯管理平台</title>
    <link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/><!--
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" rel="stylesheet" type="text/css"/>-->
	<link href="<%=basePath %>static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>				
	<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>		
	<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	
  	<link href="<%=basePath %>static/css/menu.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="<%=basePath %>static/js/menu.js"></script>
  
  </head>
  <style>
	  body {
		font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
		padding: 0;
		margin: 0;
	  }
  </style>   
  <script type="text/javascript">
     $(function(){
     	
          $('.cs-navi-tab').click(function() {
				var $this = $(this);
				var href = $this.attr('src');
				$('#mainFrame').attr("src",href);
		  });		 
          //addTab("企业信息管理", "<%=basePath %>/jsp/company/companyInfo.jsp");

     });

	function logout(){
		$.messager.confirm('提示', '你确定要退出?', function(r){
			if(r){
				window.location.href="<%=basePath %>user_logout.action";
			}
		});
	}

  </script>
  
  
  <body class="easyui-layout">
    <!-- 左边 - 菜单开始 -->
    <div data-options="region:'north',border:false" style="height:80px;background:url('<%=basePath %>/static/images/20140905_02.png') repeat-x;">
		<div style="height: 80px;background:url('<%=basePath %>/static/images/20140905top02.jpg') no-repeat;">    
	    	<div id="pf" align="right">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-house',plain:true">${enterprse.name }</a>&nbsp;&nbsp;	    		    		    		    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user',plain:true">${loginUser.userName }</a>&nbsp;&nbsp;	    		    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit',plain:true" onClick="logout()">退出系统</a>&nbsp;&nbsp;	    		    	
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
   		</div>
    </div>
	<div data-options="region:'west',iconCls:'icon-application_cascade',split:true,title:'系统导航栏'" style="width:180px;background-image:url('<%=basePath %>/static/images/left_bottombg.png');background-position:bottom;background-repeat:no-repeat;background-color:#9cead2;">
		<div  align="left">
			<div class="box">
                <ul class="menu" style="background:none;border:none;">
					 <li style="margin:10px 0; background:url('<%=basePath %>/static/images/nav_bg.png') ;height:45px;width:156px;padding:15px 0 0 10px;">
					 	<a href="javascript:void(0);" src="<%=basePath %>/jsp/company/companyInfo.jsp" class="cs-navi-tab" style="font-size:16px;color:#0e2d5f;line-height:45px;height:45px">
					 		<img border="0" style="padding:0 5px 0 10px;vertical-align: middle" alt="" src="<%=basePath %>/static/images/icon01.png">企业信息管理
					 	</a>
					</li>
					<li style="background:url('<%=basePath %>/static/images/nav_bg.png') ;height:45px;width:156px;padding:15px 0 0 10px;">
						<a href="javascript:void(0);" src="<%=basePath %>/jsp/review/productList.jsp" class="cs-navi-tab" style="font-size:16px;color:#0e2d5f;line-height:45px;height:45px">
						<img border="0" style="padding:0 5px 0 10px;vertical-align: middle" alt="" src="<%=basePath %>/static/images/icon02.png">产品信息管理</a>
					</li>
					<li style="margin:10px 0;background:url('<%=basePath %>/static/images/nav_bg.png') ;height:45px;width:156px;padding:15px 0 0 10px;">
						<a href="javascript:void(0);" src="<%=basePath %>/jsp/sys/userManage/updateUserInfo.jsp" class="cs-navi-tab" style="font-size:16px;color:#0e2d5f;line-height:45px;height:45px">
						<img border="0" style="padding:0 5px 0 10px;vertical-align: middle" alt="" src="<%=basePath %>/static/images/icon03.png">修改个人信息</a>
					</li>
                </ul>
			</div>
		</div>		
	</div>
	<!-- 左边 - 菜单结束 -->
	
	<div data-options="region:'center'">
         <iframe scrolling="auto" id="mainFrame" frameborder="0"  src="<%=basePath %>/jsp/company/companyInfo.jsp" style="width:100%;height:100%;"></iframe>
	</div>		
	  
	<div data-options="region:'south',border:false" style="height:20px;padding:0px;text-align: center;background:#66ccbe;color:#333;">
		广州薪火网络科技有限公司  版权所有
	</div>

	
	<!-- 选项卡-右击事件 -->
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
  </body>
</html>
