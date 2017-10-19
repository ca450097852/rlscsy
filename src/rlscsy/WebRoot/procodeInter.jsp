<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/proimg/";
String Agent = request.getHeader("User-Agent");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>二维码查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/code/procodeInter.js" type="text/javascript"></script>
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
  <body onLoad="getScreen()">
	<%
	    // 接口
	    String code = request.getParameter("id");
	%>
    <!-- 条件 第一个条件用来返回首页  、 第二个条件用来保存当前操作的销售申请saId  -->
    <input type="hidden" name="code" id="code" value="<%=code%>">
    <input type="hidden" id="filePath" value="<%=filePath%>">
    
    <input type="hidden" name="proId" id="proId">  
    
	<DIV class="INDEX" id="INDEX">

	<DIV class="TOP">
    	<ul>
        	<li class="check" id="c_a1"><a href='javascript:void(0)' id="a1" onclick='check1()'>产品信息</a></li>
            <li class="bor_no" id="c_a2"><a href='javascript:void(0)' id="a2" onclick='check2()'>产地信息</a></li>
            <li id="c_a3"><a href='javascript:void(0)' id="a3" onclick='check3()'>种源信息</a></li>
            <li id="c_a4"><a href='javascript:void(0)' id="a4" onclick='check4()'>施肥喂养</a></li>
            <li id="c_a5" class="bor_no"><a href='javascript:void(0)' id="a5" onclick='check5()'>防疫植保</a></li>
            <li id="c_a6"><a href='javascript:void(0)' id="a6" onclick='check6()'>加工包装</a></li>
			<li id="c_a7"><a href='javascript:void(0)' id="a7" onclick='check7()'>仓储运输</a></li>
			<li id="c_a8"><a href='javascript:void(0)' id="a8" onclick='check8()'>检验报告</a></li>
			
            <div class="clear"></div>
        </ul>
    </DIV>
    <div align="center" style="display: none;" id="sh_info">
    	<span><font color=green><b>以下信息已经惠州市粮油行业协会审核</b></font></span>
    </div>
    <div align="center" style="display: none;" id="tc_info">
		<p><font color=red><b>该产品的二维码已作废</b></font></p>
	</div>
   <div align="center" style="display: none;" id="gq_info">
		<p><font color=red><b>未经惠州市粮油行业协会审核或检验报告已过期</b></font></p>
		<p><b>如有疑问请通过以下方式联系惠州市粮油行业协会</b></p>		
	</div>
    <!-- 产品信息 -->
    <DIV class="CENTER" id="cpxx">
    <table width="100%;">
    
    
    	<tr>
        	<td align="right" style="width: 25%;">追溯码:</td>
            <td><span id="cpxx_dimenno"></span></td>
            <div class="clear"></div>
        </tr>
        <tr>
             <td align="right" style="width: 25%;">产品名称:</td>
             <td><span id="cpxx_proName"></span></td>
             <div class="clear"></div>
        </tr>
        <tr>
             <td align="right" style="width: 25%;">产品规格:</td>
             <td><span id="cpxx_sizeAttr"></span></td>
             <div class="clear"></div>
        </tr>
        <tr>
             <td align="right" style="width: 25%;">类型:</td>
             <td><span id="cpxx_proType"></span></td>
             <div class="clear"></div>
        </tr>
		 <tr>
             <td align="right" style="width: 25%;">所属企业:</td>
             <td><span id="cpxx_entName"></span></td>
             <div class="clear"></div>
        </tr>
		<tr>
             <td align="right" style="width: 25%;">三品一标:</td>
             <td><span id="cpxx_authentication"></span></td>
             <div class="clear"></div>
        </tr>
		
        <tr>
             <td align="right" style="width: 25%;">生产商:</td>
             <td><span id="cpxx_manufacturer"></span></td>
             <div class="clear"></div>
        </tr>
		<tr>
             <td align="right" style="width: 25%;">生产商电话:</td>
             <td><span id="cpxx_sourceTel"></span></td>
             <div class="clear"></div>
        </tr>
         <tr>
             <td align="right" style="width: 25%;">生产商地址:</td>
             <td><div id="cpxx_sourceAddr"></span></div>
             <div class="clear"></div>
        </tr>
        <tr>
             <td align="right" style="width: 25%;">经销商:</td>
             <td><div id="cpxx_distributor"></span></div>
             <div class="clear"></div>
        </tr>
		<tr>
             <td align="right" style="width: 25%;">经销商电话:</td>
             <td><span id="cpxx_distributorTel"></span></td>
             <div class="clear"></div>
        </tr>
        <tr>
             <td align="right" style="width: 25%;">经销商地址:</td>
             <td><div id="cpxx_distributorAddr"></span></div>
             <div class="clear"></div>
        </tr>
		<tr>
             <td align="right" style="width: 25%;">保鲜防腐:</td>
             <td><div id="cpxx_retain"></span></div>
             <div class="clear"></div>
        </tr>
		<tr>
             <td align="right" style="width: 25%;">储藏条件:</td>
             <td><div id="cpxx_storageConditions"></span></div>
             <div class="clear"></div>
        </tr>
        <tr>
             <td align="right" style="width: 25%;">生产日期:</td>
             <td><div id="cpxx_scrq"></span></div>
             <div class="clear"></div>
        </tr>
		<tr>
             <td align="right" style="width: 25%;">保质期:</td>
             <td><div id="cpxx_shelfLife"></span></div>
             <div class="clear"></div>
        </tr>
		<tr>
             <td align="right" style="width: 25%;">产品说明:</td>
             <td><span id="cpxx_proDesc"></span></td>
             <div class="clear"></div>
        </tr>
        <div class="clear"></div> 
        
        </table>
    </DIV>
    
	 <!-- 产地信息 -->
    <DIV class="CENTER" id="cdxx">
    	<div class="clear"></div>
    </DIV>
	
	 <!-- 种源信息 -->
    <DIV class="CENTER" id="zyxx">
    	<div class="clear"></div>
    </DIV>
	
	 <!-- 施肥或者喂养 -->
    <DIV class="CENTER" id="sfwy">
    	<div class="clear"></div>
    </DIV>
	
	 <!-- 植保防疫 -->
    <DIV class="CENTER" id="zbfy">
    	<div class="clear"></div>
    </DIV>
	
    <!-- 加工屠宰 -->
    <DIV class="CENTER" id="jgtz">

	</DIV>
	
    <!-- 仓储运输信息 -->
    <DIV class="CENTER" id="czys">
    	
    </DIV>
    
   
    
    <!-- 检测报告 -->
    <DIV class="CENTER" id="jcbg"> 
       
    </DIV>
    
   
    
    <DIV class="BOTTOM">
    	监制单位：广东省农业厅<br/>
		技术支持：广州薪火网络科技有限公司
    </DIV>
</DIV>
  </body>
</html>
