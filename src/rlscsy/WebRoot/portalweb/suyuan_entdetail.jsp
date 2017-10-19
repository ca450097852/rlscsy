<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dimenno = request.getParameter("dimenno");
dimenno = dimenno==null ? "4401060000014401" : dimenno;
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/";
String Agent = request.getHeader("User-Agent");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-企业溯源查询结果</title>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/suyuan_entstyle.css"/>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/suyuan_detail2.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/hontek/portalweb/suyuan_entdetail.js"　type="text/javascript"></script>

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

<!-- IE 8 以下 html5兼容性问题 -->
<!-- <script type="text/javascript" src="http://haiqiancun.com/file/demo/custom.modernizr.js" defer="defer"></script>-->

<script> 
   (function() {
     if (! 
     /*@cc_on!@*/
     0) return;
     var e = "abbr, article, aside, audio, canvas, datalist, details, dialog, eventsource, figure, footer, header, hgroup, mark, menu, meter, nav, output, progress, section, time, video".split(', ');
     var i= e.length;
     while (i--){
         document.createElement(e[i])
     } 
})() 
</script>


<style type="text/css">
article,aside,dialog,footer,header,section,footer,nav,figure,menu{display:block};
</style>

</head>
<body>
<DIV>
	<input type="hidden" id="dimenno" value="<%=dimenno %>"/>
	<input type="hidden" id="code" value="<%=dimenno%>">
    <input type="hidden" id="filePath" value="<%=filePath%>">
    <input type="hidden" id="basePath" value="<%=basePath%>">
<jsp:include page="head.jsp"><jsp:param value="sycx" name="navckeckId"/></jsp:include>
<DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_leftnew">
            	<div class="product_titlenew"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>溯源结果</font></div>
                <div class="suyuan_content">
                	<div class="suyuan_text" id="suyuan_text" align="center">
                    
					<!-- 增加 --> 
					<section class="c_main01 mt15">
					 	<header class="h_tit">
					     	<h2>企业信息</h2>
					   	</header>
					   	<!-- article111111111111111111111111-->   
					     <article class="c_pm01 c_cpdaxx" id="company_text">
					     	<a name="zzry" class="anchor_line"></a>
					     	
					     	<h3 class="h3tit" id="sh_info" style="display: none;"><font color=green><b>以下信息已经通过审核！</b></font></font></h3>
							<h3 class="h3tit" id="yx_info" style="display: none;"><font color=red><b>以下信息未经审核！</b></font></h3>
							
							<div class="bd cz_zzry">
							    <ul>
							        <li class="bg01">
							            <table>
							                <tbody>
							                    <tr>
							                        <th>基本信息</th>
							                        <td id="qyjbxx" align="left">
							                        </td>
							                        
							                        <td id="qylxfs" align="left">
							                        </td>
							                    </tr>
							                </tbody>
							            </table>
							        </li>
							        
							        <li class="bg01">
							            <table>
							                <tbody>
							                    <tr>
							                        <th>企业简介</th>
							                        <td id="qyjj"  align="left">
							                        	
							                        </td>
							                    </tr>
							                </tbody>
							            </table>
							        </li>
							        
							    </ul>
							</div>
							
											

						
						</article>
					</section>
                    <!-- 增加 end-->
                    
  
                    </div>
                </div>
            </div>
            

        </div>
        <div class="clear"></div>
    </DIV>
 <jsp:include page="bottom.jsp"></jsp:include>
 </DIV>
</body>
</html>


