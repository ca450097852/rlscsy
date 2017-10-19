<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>

<title>广州肉菜溯源|溯源查询</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="css/product-slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/suyuan.js"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
</head>

<body>
	<div class="container ">
		<jsp:include page="head1.jsp" ><jsp:param value="news" name="navckeckId"/></jsp:include>
		<div class="main clearfix">
			<div class="main-content">
				<div class="left-box clearfix">
					<h3 class="page-title">
						<span>溯源查询</span>
					</h3>
					<div class="left-box-content">
						<div class="code-page">
							<div class="code_content">

								<div class="code_box">
									<input type="text" placeholder="输入溯源码"  id="num" />
									<ul>
										<li><a onclick="javascript:subnumber(1)">1</a>
										</li>
										<li><a onclick="javascript:subnumber(2)">2</a>
										</li>
										<li><a onclick="javascript:subnumber(3)">3</a>
										</li>
										<li><a onclick="javascript:subnumber(-2)" class="cancel">清除</a>
										</li>
										<li><a onclick="javascript:subnumber(4)">4</a>
										</li>
										<li><a onclick="javascript:subnumber(5)">5</a>
										</li>
										<li><a onclick="javascript:subnumber(6)">6</a>
										</li>
										<li><a onclick="javascript:subnumber(-1)" class="cancel">退格</a>
										</li>
										<li><a onclick="javascript:subnumber(7)">7</a>
										</li>
										<li><a onclick="javascript:subnumber(8)">8</a>
										</li>
										<li><a onclick="javascript:subnumber(9)">9</a>
										</li>
										<li><a onclick="javascript:subnumber(0)">0</a>
										</li>
									</ul>
								  <button onclick="sySreach();">确认</button>
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="right-box clearfix">
					<div class="tips">
						<h4>应急管理</h4>
						<ul class="right-box-list" id="infotitle">
							
							
						</ul>
					</div>
				</div>

			</div>
		</div>

<jsp:include page="bottom1.jsp" ><jsp:param value="news" name="navckeckId"/></jsp:include>
	</div>
</body>
</html>
