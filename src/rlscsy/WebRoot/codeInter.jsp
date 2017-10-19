<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String Agent = request.getHeader("User-Agent");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>溯源系统 - 二维码查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/code/codeInter.js" type="text/javascript"></script>
	<%
     if(Agent.contains("MicroMessenger")){
     	// 这里是微信区
     	if(Agent.contains("iPhone")){
     		// iPhone 系列
	     	%>
				<link type="text/css" rel="stylesheet" href="${basePath}static/css/codeInter/iphone_cpxx.css"/>
			<%
     	}else{
     		// Android 系列
     		%>
				<link type="text/css" rel="stylesheet" href="${basePath}static/css/codeInter/android_cpxx.css"/>
			<%
     	}
	 }else{
	    // 非微信区
	 	%>
			<link type="text/css" rel="stylesheet" href="${basePath}static/css/codeInter/bc_cpxx.css"/>
		<%
	 }
	 %>
	
	<script type="text/javascript">
		//获取屏幕的分辨率
		function getScreen(){
			var width = document.body.clientWidth;
			var height = document.body.clientHeight;
			document.getElementById("INDEX").style.width = width+"px";
			document.getElementById("INDEX").style.height = height+"px";
			//alert("分辨率:"+width+"*"+height);
		}
	</script>
  </head>
  <body onload="getScreen()">
	<%
	    // 接口
	    String code = request.getParameter("id");
	%>
    <!-- 条件 第一个条件用来返回首页  、 第二个条件用来保存当前操作的销售申请saId  -->
    <input type="hidden" name="code" id="code" value="<%=code%>">
    <input type="hidden" name="entId" id="entId">  
    
	<DIV class="INDEX" id="INDEX">
	<DIV class="TOP">
    	<ul class="ul01">
        	<li class="check" id="c_a1"><a href='javascript:void(0)' id="a1" onclick='check1()'>企业信息</a></li>
            <li class="bor_no" id="c_a2"><a href='javascript:void(0)' id="a2" onclick='check2()'>资质信息</a></li>
            <li id="c_a3"><a href='javascript:void(0)' id="a3" onclick='check3()'>生产信息</a></li>

            <div class="clear"></div>
        </ul>   
    </DIV>
    <div align="center" id="sh_info1">
    	<span><font color=green><b>以下信息已经广州市农业厅初次审核</b></font></span>
    </div>
        <div align="center" id="sh_info2">
    	<span><font color=green><b>以下信息已经广东省农业厅最终审核</b></font></span>
    </div>
    <div align="center" id="yx_info">
    	<span><font color=red><b>未经广东省农业厅审核</b></font></span>
    </div>
    <!-- 是否过期 -->
    <div align="center" id="info">
    	<span id="gq_info"></span>
    </div>
    <!-- 产品信息 -->
    <DIV class="CENTER" id="qyxx">
    	<dl>
        	<dt>追溯码:</dt>
            <dd><span id="cpxx_dimenno"></span></dd>
            <div class="clear"></div>
        </dl>
        <div class="clear"></div> 
    </DIV>
    
    
    <!-- 资质信息 -->
    <DIV class="CENTER" id="zizhixx">
    	<dl>
        	<dt>负责人:</dt>
            <dd><span id="jgxx_headman"></span></dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>加工厂家:</dt>
            <dd><span id="jgxx_processName"></span></dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>加工日期:</dt>
            <dd><span id="jgxx_processTime"></span></dd>
            <div class="clear"></div>
        </dl>
        <div class="clear"></div>
    </DIV>
    
    <!-- 生产信息 -->
    <DIV class="CENTER" id="scxx">
    	<dl>
        	<dt>产品描述:</dt>
            <dd><span id="jgxx_headman"></span></dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>生产许可证:</dt>
            <dd><span id="jgxx_processName"></span></dd>
            <div class="clear"></div>
        </dl>
    </DIV>

   
    <DIV class="BOTTOM">
    	监制单位：广东省农业厅<br/>
    	监督电话：020-37288509<br/>
		技术支持：广州薪火网络科技有限公司
    </DIV>
</DIV>
  </body>
</html>
