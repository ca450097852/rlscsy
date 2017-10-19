<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.company.pojo.Company"%>
<%@page import="com.hontek.company.pojo.CompanyUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String ischarge = "2";//是否收费用户，1是；2否
Object obj = session.getAttribute("loginCompany");
Object objUser = session.getAttribute("loginCompanyUser");
Object objNode = session.getAttribute("node");
if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}else{
	Company company = (Company)obj;
	
}
Company loginCompany = (Company)obj;
CompanyUser tsUser = null;
if(objUser!=null){
	tsUser = (CompanyUser)objUser;
}
Company node = (Company)objNode;

EntStyle companyStyle = session.getAttribute("companyStyle")==null?null:(EntStyle)session.getAttribute("companyStyle");
String styleFile = "blue";
String logo = basePath + "company/images/logo.png";
String bottomInfo = "版权所有 广州薪火网络科技有限公司";
if(companyStyle!=null){
	if(companyStyle.getLogoImage()!=null && !"".equals(companyStyle.getLogoImage())){
		logo = "/nytsyFiles/entstyle/"+companyStyle.getLogoImage();
	}
	if(companyStyle.getBottomInfo()!=null && !"".equals(companyStyle.getBottomInfo())){
		bottomInfo = companyStyle.getBottomInfo();
	}
	if(companyStyle.getScCss()!=null && !"".equals(companyStyle.getScCss())){
		styleFile = companyStyle.getScCss();
	}
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" name="viewport">
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<meta content="IE=9; IE=8; IE=7; IE=EDGE" http-equiv="X-UA-Compatible">
		<title>企业会员中心</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/new.css">
		<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
		<!--[if lt IE 9]>
		  <script src="js/html5shiv.js"></script>
		  <script src="js/respond.min.js"></script>
		<![endif]-->
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.scrollUp.js"></script>
		<!-- jQuery UI JS -->
		<script src="js/jquery-ui-v1.10.3.js"></script>
		<!-- Custom JS -->
		<script src="js/menu.js"></script>
		<script type="text/javascript" src="js/jquery.layer.js"></script>
		<link rel="stylesheet" href="js/skin/layer.css">
		<script type="text/javascript" src="js/jquery.countdown.js"></script>
		<script type="text/javascript" src="js/validate.js"></script>
		<script src="js/beau.js"></script>
		
		<script>
		$(function(){
				
			$("#topmenu  li a").click(function(){
				
				layer.msg('页面加载中', {icon: 16,time: 5000});

				$("#topmenu li.active").removeClass("active");
				var pp = $(this).parent();
				pp.addClass("active");			
				var text = $(this).text();
				var level = $(this).attr("level");				
				if(level==1){
					//一级菜单
					var sublia = pp.find("ul li a");
					$("#sub-nav").html('<li><a href="#" class="heading">'+text+'</a></li>');			
					$.each( sublia, function(i, n){
						if(i==0){
							$("#sub-nav").append('<li class="hidden-sm hidden-xs"><a href="'+n.href+'" class="selected" target="rightFrame">'+n.text+'</a></li>');
						}else{
							$("#sub-nav").append('<li class="hidden-sm hidden-xs"><a href="'+n.href+'" target="rightFrame">'+n.text+'</a></li>');
						}
					});
				}else{
					//二级菜单
					var ppp = pp.parent().parent();			
					var plia = ppp.find("a");				
					var sublia = ppp.find("ul li a");
					$("#sub-nav").html('<li><a href="#" class="heading">'+plia[0].text+'</a></li>');			
					$.each( sublia, function(i, n){
						if(n.text==text){
							$("#sub-nav").append('<li class="hidden-sm hidden-xs"><a href="'+n.href+'" class="selected" target="rightFrame">'+n.text+'</a></li>');
						}else{
							$("#sub-nav").append('<li class="hidden-sm hidden-xs"><a href="'+n.href+'" target="rightFrame">'+n.text+'</a></li>');
						}
					});
					
					$("#topmenu li.active").removeClass("active");
					ppp.addClass("active");			
				}				
			});	
			
			$("#sub-nav a").click(function(){
				console.info("ddd");
				$(".hidden-sm a.selected").removeClass("selected");
				$(this).addClass("selected");				
			});
			
			var height = document.documentElement.clientHeight;
			$('#rightFrame').attr('height',height-311);
			
		});
		
		window.onresize = function(){
			var height = document.documentElement.clientHeight;			
			$('#rightFrame').attr('height',height-311);
		}
	</script>

</head>

<body>
	<jsp:include page="top.jsp"></jsp:include>

		<!-- Main Container start -->
		<div class="dashboard-container">

			<div class="container">
				<!-- Top Nav Start -->
				<div id="cssmenu">
					<div id="menu-button">Menu</div>
					<ul id="topmenu">
						<li class="active">
							<a href="index.jsp" target="rightFrame" level="1">
								<i class="fa fa-home"></i>中心首页
							</a>

						</li>
						<li class="has-sub"><span class="submenu-button"></span>
							<a href="qyxx.jsp" target="rightFrame" level="1"><i class="fa fa-info-circle"></i>企业信息</a>
							<ul>
								<li>
									<a href="qyxx.jsp" target="rightFrame">基本信息</a>
								</li>
								<li>
									<a href="zzxx.jsp" target="rightFrame">资质证书</a>
								</li>
								<li>
									<a href="recordNew.jsp" target="rightFrame">种类设置</a>
								</li>

							</ul>
						</li>
						<li class="has-sub"><span class="submenu-button"></span>
							<a href="proTypeBatch.jsp" target="rightFrame" level="1"><i class="fa fa-file-text-o "></i>生产档案</a>
							
						</li>
						
						<li class=""><span class="submenu-button"></span>
							<a href="CompanyMarketChart.jsp" level="1" target="rightFrame"><i class="fa fa-bars"></i>统计管理</a>
						</li>
						
						<li class=""><span class="submenu-button"></span>
							<a href="qykh.jsp" level="1" target="rightFrame"><i class="fa fa-pencil-square-o"></i>企业考核</a>
						</li>
						<%if ("5".equals(node.getFlag())){ %>
						<li class=""><span class="submenu-button"></span>
							<a href="ttxflist.jsp" level="1" target="rightFrame"><i class="fa fa-qrcode"></i>团体消费</a>
						</li>
						<%} %>
						<li class="has-sub"><span class="submenu-button"></span>
							<a href="xgmm.jsp" level="1"  target="rightFrame"><i class="fa fa-cogs"></i>系统设置</a>
							<ul>
								<li>
									<a href="xgmm.jsp"  target="rightFrame">修改密码</a>
								</li>
								<li>
									<a href="avatarupload.jsp"  target="rightFrame">头像设置</a>
								</li>
							</ul>
						</li>

						<li>
							<a href="<%=basePath %>complogout.action" target="_parent">
								<i class="fa fa-sign-out"></i> 安全退出
							</a>
						</li>
					</ul>
				</div>
				<!-- Top Nav End -->
				<div class="sub-nav hidden-sm hidden-xs">
					<ul id="sub-nav">			
						<li><a href="#" class="heading">首页</a></li>
					</ul>
					
					<div class="custom-search hidden-sm hidden-xs">
						
					</div>
				</div>
				<!-- Sub Nav End -->
				<div class="dashboard-wrapper">
					<div class="row">
						<div class="col-sm-12" style="min-height: 500px">
							
							<iframe src="index.jsp" name="rightFrame" width="100%" style="min-height: 500px" id="rightFrame" title="rightFrame" frameborder="0"></iframe>

						</div>
					</div>
					
				</div>
				<footer>
					<p><%=bottomInfo %></p>
				</footer>
			</div>
		</div>



<%-- <div id="box"> 
	<div id="main"> 
		<div id="left">
			<div style="width: 187px;">
		<div style="background:#f0f9fd;">
		
		    <div class="lefttop"><span></span>企业信息</div>
    
		    <dl class="leftmenu">
		    <dd>
		    	<ul class="menuson">
		        <li class="active"><cite></cite><a href="qyxx.jsp" target="rightFrame">基本信息</a><i></i></li>
		        <li><cite></cite><a href="zzxx.jsp" target="rightFrame">资质证书</a><i></i></li>
		        <%if(ischarge.equals("1")){ %>
		        <li><cite></cite><a href="companyStyle.jsp" target="rightFrame">风格设置</a><i></i></li>
		        <%} %>
		        <li><cite></cite><a href="qykh.jsp" target="rightFrame">企业考核</a><i></i></li>
		        <li><cite></cite><a href="<%=basePath %>/company/CompanyMarketChart.jsp" target="rightFrame">企业统计</a><i></i></li>
		         <%if(loginCompany.getComType().equals("5")){ %>
		        <li><cite></cite><a href="ttxflist.jsp" target="rightFrame">团体消费进货验收管理</a><i></i></li>
		        <%} %>
		        </ul>    
		    </dd>
		        
		    
		    </dl>
		</div>
	</div>
		</div>
		<div id="right">
		<iframe src="qyxx.jsp" name="rightFrame" width="100%" height="100%" id="rightFrame" title="rightFrame"></iframe>
		</div>
	</div> 
	<div id="footer" >
		<div class="footer">
	    <span><%=bottomInfo %></span>
	    </div>
	</div> 
</div>  --%>
</body>
</html>