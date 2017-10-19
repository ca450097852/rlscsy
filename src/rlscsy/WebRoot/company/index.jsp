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
Company company = null;
String company_top_css="";
if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}else{
	 company = (Company)obj;
	 String comType = company.getComType();
	 if("1".equals(comType)){
		 company_top_css="company-top-01";
	 }else if("2".equals(comType)){
		 company_top_css="company-top-02";
	 }else if("3".equals(comType)){
		 company_top_css="company-top-03";
	 }
}
CompanyUser tsUser = null;
if(objUser!=null){
	tsUser = (CompanyUser)objUser;
}


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="js/jquery.js"></script>

</head>


<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<meta content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" name="viewport">
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<meta content="IE=9; IE=8; IE=7; IE=EDGE" http-equiv="X-UA-Compatible">
		<title>会员中心|中心首页</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/new.css">
		<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
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
		<script type="text/javascript" src="<%=basePath %>company/js/index.js"></script>
		<style type="">
			
	.company-top-01{
		background: url(images/company-bg-01.png) no-repeat center;
		background-size: cover;
	}
	.company-top-02{
		background: url(images/company-bg-02.png) no-repeat center;
		background-size: cover;
	}
	.company-top-03{
		background: url(images/company-bg-03.png) no-repeat center;
		background-size: cover;
	}
		</style>
	</head>

	<body>
	
	<input type="hidden"  id="basePath" value="<%=basePath%>"/> 
				<div class="dashboard-wrapper">
					<div class="row">
						<div class="col-sm-12">
						
							<div class="col-sm-2" >
								<div class="panel companynew" >
									<div class="company-top <%=company_top_css%>">
										<div class="company-head">
											<img id="logoImg" width="50px" src=""/>
										</div>
									</div>
									<div class="company-name">
										<p id="company_msg"></p>
									</div>

								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="panel company-infonew bor-t-3">
									<p class="title">企业基本信息</p>
									<div class="panel-body">
										<ul>
											<li>企业名称：<b><%=company.getName() %></b></li>
											<li>企业类型：<b><%=company.getComType().equals("1")?"生猪批发企业":company.getComType().equals("2")?"肉类批发企业":company.getComType().equals("3")?"零售商":company.getComType().equals("4")?"配送企业":company.getComType().equals("5")?"生产企业":"其他" %></b></li>
											<li>企业法人：<b><%=company.getCorporate() %></b></li>
											<li>联系电话：<b><%=company.getPhone()%></b></li>
											<li>注册地址：<b><%=company.getAddr()%></b></li>											
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="panel company-map">
									<iframe src="<%=basePath %>jsp/statistic/companyAddrMap.jsp" width="100%" height="100%"></iframe>
								</div>
							</div>
							

						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="col-sm-6">
								<div class="panel system-news bor-t-3">
									<p class="title">应急管理</p>
									<div class="panel-body">
										<ul id="news_list">
											<li>
												<a href="#">树中选择您企业产品的种类</a>
											</li>
											<li>
												<a href="#">择您企业产品的种类</a>
											</li>
											<li>
												<a href="#">企业产品的种类</a>
											</li>
											<li>
												<a href="#">选择您企业产品的种类</a>
											</li>
											<li>
												<a href="#">企业产品的种类</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="row">
									<div class="col-sm-6">
										<div class="panel code-info bor-lt-3-dgreen">
											<div class="panel-body">
												<div class="code-info-icon icon-primary">
													<i class="fa fa-check-square-o "></i>
												</div>
												<p class="code-info-txt">
													已生成批次
													<span id="createCount"></span>
												</p>
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="panel code-info bor-lt-3-lgreen">
											<div class="panel-body">
												<div class="code-info-icon icon-secondary">
													<i class="fa fa-print"></i>
												</div>
												<p class="code-info-txt">
													已打印批次
													<span id="printCount"></span>
												</p>
											</div>
										</div>
									</div>
									<div class="col-sm-6">

										<div class="panel code-info bor-lt-3-yellow">
											<div class="panel-body">
												<div class="code-info-icon icon-tertiary">
													<i class="fa fa-qrcode "></i>
												</div>
												<p class="code-info-txt">
													扫码统计
													<span id="scanCount"></span>
												</p>
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="panel code-info bor-lt-3-orange">
											<div class="panel-body">
												<div class="code-info-icon icon-quartenary">
													<i class="fa fa-exclamation-triangle"></i>
												</div>
												<p class="code-info-txt">
													被投诉标签
													<span>0</span>
												</p>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>

											<input type="hidden" id="comType" value="<%=company.getComType() %>"/> 
											<input type="hidden" name="proTypeBatch.entId" id="entId" value="<%=company.getComId()%>"/>
					<div class="row">
						<div class="col-sm-12">
							<div class="col-sm-12">
								<div class="panel recent-record bor-t-3">
									<p class="title">近期生产档案管理记录</p>
									<div class="panel-body" >
										<div class="table-responsive">
											<table class="table table-striped mb-none">
												<thead>
													<tr>
														<th>批次名称</th>
														<th>生产时间</th>
														<th>批次二维码</th>
														<th>档案信息</th>
													</tr>
												</thead>
												<tbody id="proTab">
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>

	</body>

</html>
