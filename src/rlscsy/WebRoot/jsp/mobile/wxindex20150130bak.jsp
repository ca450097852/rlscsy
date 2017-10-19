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
    <title>农产品溯源系统 - 二维码查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script src="${basePath}jsp/mobile/codeInter.js" type="text/javascript"></script>
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
	   // String code = request.getParameter("id");
	%>
    <!-- 条件 第一个条件用来返回首页  、 第二个条件用来保存当前操作的销售申请saId  --><!--
    <input type="hidden" name="code" id="code" value="">
    <input type="hidden" name="entId" id="entId">  
    
	-->
	<DIV class="INDEX" id="INDEX">
	<DIV class="TOP">
    	<ul class="ul01">
        	<li class="check" id="c_a1"><a href='javascript:void(0)' id="a1" onclick='check1()'>企业信息</a></li>
            <li class="bor_no" id="c_a2"><a href='javascript:void(0)' id="a2" onclick='check2()'>耳标信息</a></li>
            <li id="c_a3"><a href='javascript:void(0)' id="a3" onclick='check3()'>防疫信息</a></li>
			<li id="c_a4"><a href='javascript:void(0)' id="a4" onclick='check4()'>产地检疫</a></li>
            <div class="clear"></div>
        </ul>   
    </DIV>
    <!--<div align="center" id="sh_info1">
    	<span><font color=green><b>以下信息已经广州市农业厅初次审核</b></font></span>
    </div>
        <div align="center" id="sh_info2">
    	<span><font color=green><b>以下信息已经广东省农业厅最终审核</b></font></span>
    </div>
    <div align="center" id="yx_info">
    	<span><font color=red><b>未经广东省农业厅审核</b></font></span>
    </div>
    --><!-- 是否过期 -->
    <!--<div align="center" id="info">
    	<span id="gq_info"></span>
    </div>
    --><!-- 产品信息 -->
    <DIV class="CENTER" id="d1">
    	<dl>
        	<dt>企业名称:</dt>
            <dd>广东壹号食品股份有限公司</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt>企业法人:</dt>
            <dd>黎小兵</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt>注册地址:</dt>
            <dd>湛江市经济技术开发区乐山东路<br/>35号银隆广场A座908室</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt>追溯码:</dt>
            <dd>A002000021</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt>联系电话:</dt>
            <dd>020-38105666</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt>邮政编码:</dt>
            <dd>510630</dd>
            <div class="clear"></div>
        </dl>
         <dl>
        	<dt>企业网址:</dt>
            <dd>www.gdyhsp.com</dd>
            <div class="clear"></div>
        </dl>
         <dl>
        	<dt>电子邮箱:</dt>
            <dd>gdyhspadmin@163.com</dd>
            <div class="clear"></div>
        </dl>
         <dl>
        	<dt>企业简介:</dt>
            <dd>广东壹号食品股份有限公司</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt></dt>
            <dd>其前身为广东天地食品有限公司，主要以“壹号土猪”为主导品牌，集育种研发、养殖生产、鲜肉销售于一体，采取“公司+基地+专业户+连锁店”全产业链管理模式，是一家对产品品质精益求精、对顾客满意度孜孜追求、从产地到餐桌全充分整合国内地方猪（俗称土猪）资源的现代创新型农业企业。</dd>
            <div class="clear"></div>
        </dl>
        
        <div class="clear"></div> 
    </DIV>	 	 	 	 	 	 
    
    
    <!-- 耳标佩戴信息 -->
    <DIV class="CENTER" id="d2" style="display: none;">
    	<dl>
        	<dt>耳标号:</dt>
            <dd>144018401870246</dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>动物种类:</dt>
            <dd>猪</dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>畜主:</dt>
            <dd>徐镜和</dd>
            <div class="clear"></div>
        </dl>
                <dl>
            <dt>产地:</dt>
            <dd>鳌头镇龙潭村</dd>
            <div class="clear"></div>
        </dl>
                <dl>
            <dt>操作人:</dt>
            <dd>骆宝玲</dd>
            <div class="clear"></div>
        </dl>
                <dl>
            <dt>防疫机构:</dt>
            <dd>鳌头畜牧兽医站</dd>
            <div class="clear"></div>
        </dl>
                <dl>
            <dt>佩戴时间:</dt>
            <dd>2014-09-23 15:28</dd>
            <div class="clear"></div>
        </dl>
        <div class="clear"></div>
    </DIV> 	
    
    <!-- 耳标防疫信息 -->
    <DIV class="CENTER" id="d3" style="display: none;">
    	<dl>
        	<dt>耳标号:</dt>
            <dd>144018401870246</dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>免疫日龄:</dt>
            <dd>100</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt>疫苗名称:</dt>
            <dd>猪瘟活疫苗(Ⅰ)</dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>动物数量:</dt>
            <dd>96</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt>操作人:</dt>
            <dd>骆宝玲</dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>防疫机构:</dt>
            <dd>鳌头畜牧兽医站</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt>免疫时间:</dt>
            <dd>2014-09-23 15:28 </dd>
            <div class="clear"></div>
        </dl>
    </DIV>

    <!-- 动物产地检疫合格证明 -->
    <DIV class="CENTER" id="d4" style="display: none;">
    	<dl>
        	<dt>检疫证号:</dt>
            <dd>4411473544</dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>动物种类:</dt>
            <dd>猪</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt>数量:</dt>
            <dd>1</dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>开证单位:</dt>
            <dd>鳌头动物卫生监督分所</dd>
            <div class="clear"></div>
        </dl>
        <dl>
        	<dt>开证人:</dt>
            <dd>何少平</dd>
            <div class="clear"></div>
        </dl>
         <dl>
        	<dt>开证时间:</dt>
            <dd>2015-01-19</dd>
            <div class="clear"></div>
        </dl>
        <dl>
            <dt>目的地:</dt>
            <dd>广州市从化市鳌头镇龙潭屠宰场 </dd>
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
