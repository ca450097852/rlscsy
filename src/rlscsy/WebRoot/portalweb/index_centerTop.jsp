<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页-幻灯片新闻溯源</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<link href="<%=basePath %>static/css/portalweb/banner.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>static/js/hontek/portalweb/index_centerTop.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/jquery-ui.min.js"　type="text/javascript"></script>
<link href="<%=basePath %>static/js/jquery-ui.css"　type="text/css" rel="stylesheet"></link>

<script type="text/javascript">
$(function () {
	
	 var datas = ["J075000763001141113","J075000754001141125","A002004661144011500393616","H075801273144122406059083","265201405190176","262201401200051"];
     $("#num").autocomplete({
     	source: datas
     });	
});

</script>


</head>
  
 
<body>
<div class="CENTER_top">
        	<div class="CENTER_top_left">
            	<div class="news_img">
            		<%-- <img src="<%=basePath %>static/image/portalweb/img.png" /> --%>
	            	<!--商城首页幻灯片广告位开始-->	
					<div id="banner" class="banner" height="210" width="280">
						<div id="focus">
							<ul>
						       <li><a href="#" target="blank" alt="#">
						           <img src="<%=basePath %>static/image/portalweb/img.png"/>
						           </a>
						      </li>
						      <li><a href="#" target="blank" alt="#">
						           <img src="<%=basePath %>static/image/portalweb/img1.jpg"/>
						           </a>
						      </li>
						      <li><a href="#" target="blank" alt="#">
						           <img src="<%=basePath %>static/image/portalweb/img2.jpg"/>
						           </a>
						      </li>
						      <%--
						      <li><a href="#" target="blank" alt="#">
						           <img src="<%=basePath %>static/image/portalweb/img.png"/>
						           </a>
						      </li>
						      <li><a href="#" target="blank" alt="#">
						           <img src="<%=basePath %>static/image/portalweb/img.png"/>
						           </a>
						      </li>
						      <li><a href="#" target="blank" alt="#">
						           <img src="<%=basePath %>static/image/portalweb/img.png"/>
						           </a>
						      </li>
							--%></ul>
						</div>
					</div>
		 			<!--商城首页幻灯片广告位结束-->
            	
            	</div>
               <!--  <div class="news_img_NO"><span class="sp_check">1</span><span>2</span><span>3</span><span>4</span></div> -->
               <!--  <div class="news_text">全省劳动保障信访和维稳工作会议</div>  -->
            </div>
            <div class="CENTER_top_middle">
            	<div class="CENTER_top_middle_title"><span class="more"><a href="<%=basePath %>portalweb/allNews_list.jsp?typecode=news">更多</a>>></span><span><img src="<%=basePath %>static/image/portalweb/news_title.png" /></span></div>
                <div class="news_list">
                	<ul id="newslist">
                    	

                    </ul>
                </div>
            </div>
            <div class="CENTER_top_right">
            	<div class="CENTER_top_right_title"><img src="<%=basePath %>static/image/portalweb/sy_ch_title.png" /></div>
                <div class="con">
                	<div class="symSreach"><input id="num" /></div>
                    <div class="con_NO">
                    	<i onclick="javascript:subnumber(1)">1</i>
                            <i onclick="javascript:subnumber(2)">2</i>
                            <i onclick="javascript:subnumber(3)">3</i>
                            <i class="color" onclick="javascript:subnumber(-2)">清除</i>
                            <i onclick="javascript:subnumber(4)">4</i>
                            <i onclick="javascript:subnumber(5)">5</i>
                            <i onclick="javascript:subnumber(6)">6</i>
                            <i class="color" onclick="javascript:subnumber(-1)">退格</i>
                            <i onclick="javascript:subnumber(7)">7</i>
                            <i onclick="javascript:subnumber(8)">8</i>
                            <i onclick="javascript:subnumber(9)">9</i>
                            <i onclick="javascript:subnumber(0)">0</i>
                        <div class="clear"></div>
                    </div>
                    <div class="btn btn_symSreach"><a href="javascript:void(0);" onclick="sySreach();">立刻查询</a></div>
                </div>
            </div>
  <div class="clear"></div>
</div>
</body>
</html>
