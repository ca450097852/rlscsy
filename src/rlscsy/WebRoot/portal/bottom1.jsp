<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String navckeckId = request.getParameter("navckeckId");
	navckeckId = navckeckId==null ? "head":navckeckId;
	
	Object obj = session.getAttribute("entStyle_QT");
	String bottomInfo = "@版权所有Copyright<span>广州薪火网络科技有限公司</span>";
	if(obj!=null){
		EntStyle entStyle = (EntStyle)obj;
		if(entStyle.getBottomInfo()!=null && !"".equals(entStyle.getBottomInfo())){
			bottomInfo = entStyle.getBottomInfo();
		}
	}
	
	String entAddr = "广州市越秀区中山一路57号南方铁道大厦";
	if(session.getAttribute("entAddr")!=null && !"".equals(session.getAttribute("entAddr"))){
		entAddr = (String)session.getAttribute("entAddr");
	}
	String entPhone = "020-8765 9788";
	if(session.getAttribute("entPhone")!=null && !"".equals(session.getAttribute("entPhone"))){
		entPhone = (String)session.getAttribute("entPhone");
	}
%>
<div class="footer">
				<div class="footer-content">
					<ul class="footer-nav">
								<li>
							<a href="<%=basePath%>portal/index.jsp" <%="index".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>首页</a>
							</li>
							<li>
							<a href="<%=basePath %>portal/plant.jsp" <%="product".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>溯源产品</a>
							</li>
							<li>
      						<a href="<%=basePath %>portal/news.jsp" <%="news".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>应急管理</a>
							</li>
							<li>
      						<a href="<%=basePath%>portal/complaint.jsp" <%="complaint".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>投诉举报</a>
							</li>
							<li>
							<a href="<%=basePath%>portal/suyuan.jsp" <%="suyuan".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>溯源查询</a>
							</li>
							<li>
							<a href="<%=basePath%>portal/company.jsp" <%="company".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>企业名录</a>
							</li>
					</ul>
					<div class="contact">
						<p><span><img src="images/contact-icon-01.png"></span> <%=entAddr %> </p>
						<p><span><img src="images/contact-icon-02.png"></span><%=entPhone %></p>
					</div>
				</div>
				<div class="bottom"><%=bottomInfo %></div>
				
			</div>