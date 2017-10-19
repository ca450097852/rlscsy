<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dimenno = request.getParameter("dimenno");
dimenno = dimenno==null ? "075200755" : dimenno;
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/";
String Agent = request.getHeader("User-Agent");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-企业溯源查询结果</title>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/suyuan_style.css"/>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/suyuan_detail2.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/hontek/portalweb/suyuan_detail.js"　type="text/javascript"></script>

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

<!-- IE 8 以下 html5兼容性问题 -->
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
	<input id="dimenno" type="hidden" value="<%=dimenno %>"/>
	<input type="hidden" name="code" id="code" value="<%=dimenno%>">
    <input type="hidden" id="filePath" value="<%=filePath%>">
    <input type="hidden" name="saId" id="saId">
    
<jsp:include page="head.jsp"><jsp:param value="sycx" name="navckeckId"/></jsp:include>
<DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>企业溯源结果</font></div>
                <div class="suyuan_content">
                	<div class="suyuan_text" id="suyuan_text" align="center">
                    <!-- <Iframe id="myiframes" src="" scrolling="yes" frameborder="0" height="100%" width="100%" style="font-size:14px !important"></iframe> -->
                    
					<!-- 增加 -->                   
					<section class="c_main01 mt30">
					 	<header class="h_tit">
					     	<h2>企业信息</h2>
					   	</header>
					   	<!-- article111111111111111111111111-->   
					     <article class="c_pm01 c_cpdaxx">
					     	<a name="zzry" class="anchor_line"></a>
					     	<h3 class="h3tit" id="sh_info1" style="display: none;"><font color=green><b>以下信息已经广州市农业厅初次审核</b></font></h3>
					     	<h3 class="h3tit" id="sh_info2" style="display: none;"><font color=green><b>以下信息已经广东省农业厅最终审核</b></font></font></h3>
							<h3 class="h3tit" id="yx_info" style="display: none;"><font color=red><b>未经广东省农业厅审核</b></font></h3>
							<h3 class="h3tit" id="gq_info" style="display: none;"></h3>
							<div class="bd cz_zzry">
							    <ul>
							        <li class="bg01">
							            <table>
							                <tbody>
							                    <tr>
							                        <th>基本信息</th>
							                        <td id="qyjbxx" align="left">
							                            
							                        </td>
							                    </tr>
							                    
							                </tbody>
							            </table>
							        </li>
							        
							        
							        <li class="bg01">
							            <table>
							                <tbody>
							                    <tr>
							                        <th>联系方式</th>
							                        <td id="qylxfs"  align="left">
							                            
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
						
						
						<!-- article2222222222222222222222222222-->   
						 
					</section>
                    <!-- 增加 -->
                    
                    <!-- 增加 -->                   
					<section class="c_main01 mt30" id="section_zzxx">
					 	<header class="h_tit">
					     	<h2>资质信息</h2>
					   	</header>
					   	<!-- article111111111111111111111111-->   
					     <article class="c_pm01 c_cpdaxx">
					     	<a name="zzry" class="anchor_line"></a>
					     	<!--<h3 class="h3tit">资质信息</h3>-->
					     	<div class="bd cz_zzry">
							    <ul id="cpjgxx">
							        
							    </ul>
							</div>
							
							<div class="picMoveX01 mt20 clearfix c_scrollPic01">
								<p class="bbtn prevBtn"><span class="last"></span></p>
							    <div class="picBox">
							    	<ul id="qyzztp">
							    		
							            
							        </ul>
							    </div>
							    <p class="bbtn nextBtn"><span></span></p>
							</div>
							
						</article>
						
						
						<!-- article2222222222222222222222222222-->   
						 
					</section>
                    <!-- 增加 -->
                 
                   
                    <!-- 增加 -->                   
					<section class="c_main01 mt30" id="section_qyscxx">
					 	<header class="h_tit">
					     	<h2>生产信息</h2>
					   	</header>
					   	<!-- article111111111111111111111111-->   
					     <article class="c_pm01 c_cpdaxx">
					     	<a name="zzry" class="anchor_line"></a>
							<div class="bd cz_zzry">
							    <ul id="qyscqk">
							        
							    </ul>
							</div>
							<div class="picMoveX01 mt20 clearfix c_scrollPic02">
								<p class="bbtn prevBtn"><span class="last"></span></p>
							    <div class="picBox">
							    	<ul id="qysctpxx">
							    		
							        	
							            
							        </ul>
							    </div>
							    <p class="bbtn nextBtn"><span></span></p>
							</div>
							
						</article>
						
						
						<!-- article2222222222222222222222222222-->   
						 
					</section>
                    <!-- 增加 -->
                    
                    <!-- 增加 -->                   
					<section class="c_main01 mt30" id="section_qyjgxx">
					 	<header class="h_tit">
					     	<h2>监管信息</h2>
					   	</header>
					   	<!-- article111111111111111111111111-->   
					     <article class="c_pm01 c_cpdaxx">
					     	<a name="zzry" class="anchor_line"></a>
							<div class="bd cz_zzry">
							    <ul id="qyjgxx">
							        
							    </ul>
							</div>							
						</article>																	 
					</section>
                    <!-- 增加 -->
                    
                    </div>
                </div>
            </div>
            
            <div class="CENTER_content_right">
            	
			<!-- 两个信息列表  -->
            <jsp:include page="news_info.jsp"></jsp:include>

            </div>
        </div>
        <div class="clear"></div>
    </DIV>
 <jsp:include page="bottom.jsp"></jsp:include>
 </DIV>
</body>
</html>
