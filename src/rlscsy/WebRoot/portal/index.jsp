<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@page import="com.hontek.sys.pojo.Banner"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/protype/";
String entName = request.getParameter("entName");
entName = entName==null ? "" : entName;

entName = java.net.URLDecoder.decode(entName , "UTF-8");
Object sobj = session.getAttribute("entStyle_QT");
String banner1Html = "<div class=\"banner_box\"> <img src=\"images/banner_01.jpg\"/> </div>"+
  "<div class=\"banner_box\"> <img src=\"images/banner_01.jpg\"/> </div>"+
  "<div class=\"banner_box\"> <img src=\"images/banner_01.jpg\"/> </div>";
String banner2Html ="";
String advertHtml = "<img src=\"images/product_banner.jpg\" />";

boolean adflag = false;
if(sobj!=null){
	EntStyle entStyle = (EntStyle)sobj;
	if(entStyle!=null&&entStyle.getBanner()!=null&&!entStyle.getBanner().isEmpty()){
		List<Banner> blist = entStyle.getBanner();
		if(blist!=null&&!blist.isEmpty()){
			for(Banner bnl :blist){
				if(bnl.getPosition()==1&&bnl.getImgUrl()!=null&&!bnl.getImgUrl().equals("")){
					banner2Html += "<div class=\"banner_box\"> <img src=\"/nytsyFiles/banner/"+bnl.getImgUrl()+"\"/> </div>"; 
				}else if(bnl.getPosition()==2&&bnl.getImgUrl()!=null&&!bnl.getImgUrl().equals("")&&adflag==false){
					advertHtml = "<img src=\"/nytsyFiles/banner/"+bnl.getImgUrl()+"\" />";
					adflag = true;
				}
			}
		}else{
			banner2Html = banner1Html;
		}
		
	}else{
			banner2Html = banner1Html;
		}
}else{
	banner2Html = banner1Html;
}

if(banner2Html.equals("")){
	banner2Html = banner1Html;
}

//System.out.println("banner2Html===@=="+banner2Html);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("systemName") %></title>


<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="js/suyuan.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>

<body>
	<div class="container clearfix">
		<!-- head -->
  		<jsp:include page="head1.jsp" ><jsp:param value="index" name="navckeckId"/></jsp:include>
  		<!-- head end -->
   		<input type="hidden" id="filePath" value="<%=filePath%>"/>
   		<input type="hidden" id="pageNum" value="<%=filePath%>"/>
			<div class="main clearfix">
				<div class="main-content">
					<div class="row1">
						<div class="code bor-r-5">
							<h3>溯源查询</h3>
							<div class="code-input">
								<form>
									<input type="text" placeholder="输入溯源码" id="num" />
								</form>
								<button onclick="sySreach();">查询</button>
							</div>
						</div>
						<div class="banner">
							<div class="lubo">
								<ul class="lubo_box">
									<li style=" opacity: 1;filter:alpha(opacity=100);">
										<a href=""><img src="images/1.jpg"></a>
									</li>
									<li>
										<a href=""><img src="images/2.jpg"></a>
									</li>
									<li>
										<a href=""><img src="images/3.jpg"></a>
									</li>
									<li>
										<a href=""><img src="images/4.jpg"></a>
									</li>
									<li>
										<a href=""><img src="images/5.jpg"></a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="news clearfix">
						<p class="news-title">应急管理<a href="<%=basePath %>portal/news.jsp">更多>></a></p>
						<div class="news-recent clearfix" id="news_recent">
							
						</div>
						<ul id="news_list">
							
						</ul>
					</div>
					<div class="catalog clearfix">
						<p class="title">企业名录</p>
						<div class="catalog-content">
							<ul id=companys_list>
							
							</ul>
						</div>
						<a href="<%=basePath%>portal/company.jsp" class="catalog-btn">更多>></a>
					</div>
					
					<div class="product bor-r-5">
						<div class="product-container">
							<div class="product-title bor-r-5">
								<a href="<%=basePath%>portal/suyuan.jsp">溯源产品展示</a>
							</div>

							<div class="mr_frbox">
								<img class="mr_frBtnL prev" src="images/mfrL.png" />
								<div class="mr_frUl">
									<ul id="suyuan_list">
										
									</ul>
								</div>
								<img class="mr_frBtnR next" src="images/mfrR.png" />
							</div>

						</div>
					</div>
				</div>
			</div>
			<!-- footer -->
			<jsp:include page="bottom1.jsp" />
			<!-- footer end-->
		</div>
</body>
</html>
