<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/";

//判断是从那里访问过来的
LoginUser loginUser = (LoginUser) session.getAttribute ( "loginUser" );
String visfrom = loginUser.getFlag();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>产品列表</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<!-- 引入的文件的语句必须在引入jquery语句的后面 -->
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="${basePath}<%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>	
  	<script src="${basePath}static/js/hontek/review/productAudit20150804.js" type="text/javascript"></script>
  	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
 	
 	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
 	
 	<style type="text/css">
		#det td {
			border-color: #CCCCCC;
			border-style: dotted;
		    border-width: 0 1px 1px 0;
		    margin: 10;
		    padding: 0;
		}
		.td_title{
			width: 60px;
			height: 25px;
		}
		.td_text{
			width: 260px;
		}
		
		/*图片滚动模块01*/
		.picMoveX01 .bbtn{float:left;cursor:pointer;}
		.picMoveX01 .bbtn span{height:125px;width:15px;display:block;background:url(<%=basePath %>static/image/portalweb/preNext_btn.png) no-repeat;}
		.picMoveX01 .prevBtn span{background-position:-56px center;}
		.picMoveX01 .nextBtn span{background-position:-90px center;}
		.picMoveX01 .prevBtn span.last{background-position:4px center;}
		.picMoveX01 .nextBtn span.last{background-position:-30px center;}
		.picMoveX01 .picBox{width:650px;height:150px;float:left;overflow:hidden;position:relative;}
		.picMoveX01 .picBox ul{width:650px;;}
		.picMoveX01 .picBox ul li{width:130px;height:125px;padding:0px;line-height:10px;text-align:center;margin:0 12px;float:left;display:inline;}
		
	</style>
  </head>
  <body>
     <table id="productdatagrid">  
    </table>  
    <!-- purchasedatagrid 工具栏 -->
    <div id="tb" style="height:auto">
   		<%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendProType()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateProType()">修改</a>
		--%>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="updateProTypeState(2)">已停产</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="updateProTypeState(1)">生产中</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removerProduct()">删除</a>
		<div>
		<from id="myseach">
			&nbsp;&nbsp;企业名称: <input class="easyui-validatebox" id="companyName" style="width:100px">
			&nbsp;&nbsp;产品名称: <input class="easyui-validatebox" id="productName" style="width:100px">
			&nbsp;&nbsp;产品分类:<input id="pro_type_1" style="width:100px;">
			&nbsp;&nbsp;数据来源:<select id="sysCode" class="easyui-combobox" name="sysCode" style="width:120px;"  data-options="panelWidth:200">   
								    <option value="">--请选择--</option>   								    
								    <option value="A002001">广东省动物溯源数据库管理系统</option>   
								    <option value="L075201">惠州市粮油溯源监管系统</option>   
								    <option value="S076901">东莞市</option>   
								</select> 
			&nbsp;&nbsp;数据状态:<select id="traceState" class="easyui-combobox" name="dept" style="width:120px;">   
			 						<option value="">--请选择--</option>   	
								    <option value="1">无溯源数据</option>   								    
								    <option value="2">有溯源数据</option>   
								</select>
			&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="tb_search()">搜 索</a>
			&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="tb_reset()">重置</a>
			</from>
		</div>
	</div>
	
	<div id="hiden_value">
		<input type="hidden" id="proId"> 
    	<input type="hidden" id="typeClass"/>
    	<input type="hidden" name="product.dimenno" />
    	<input type="hidden" id="sessionId" value="<%=session.getId() %>" />
		<input type="hidden" id="h_path" value='<%=basePath %>' />
    	<input type="hidden" id="filePath" value='<%=filePath %>' />
    	
	</div>	
	
	<!-- 显示详细信息 -->
	<div id="product_detail" class="easyui-window" data-options="iconCls:'icon-product',closed:true"  title="产品溯源详细信息" style="width:750px;height:500px;top:1px">
		<div id="product_tabs" class="easyui-tabs" data-options="fit:true">   
		    <div title="产品信息" style="padding:10px;">		       
		        <table id="det" width="100%">			    
				    
			    </table> 
			    <div id="productPicBox" style="width: 100%;"></div>		        
		    </div> 
		      
		    <div title="产地信息" style="padding:10px">   
		        <div id="proArea" style="width: 100%;height: 60%;"></div>
		        <!-- 产地图片展示 -->
		        <div id="proAreaPicBox" style="width: 100%;"></div>		        
		    </div>   
		    
		    <div title="种源信息" style="padding:10px;">   
		        <div id="proSeed" style="width: 100%;height: 60%;"></div>
		        <!-- 图片展示 -->
		        <div id="proSeedPicBox" style="width: 100%;"></div>
		             					        	    
		    </div>
		    
		    <div title="种养信息" style="padding:10px;">   
		        <div id="plantRaise" style="width: 100%;height: 60%;"></div>
		        <!-- 图片展示 -->
		        <div id="plantRaisePicBox" style="width: 100%;"></div>    
		    </div> 
		      
		    <div title="防疫信息" style="padding:10px;">   
		        <div id="prevention" style="width: 100%;height: 60%;"></div>
		        <!-- 图片展示 -->
		        <div id="preventionPicBox" style="width: 100%;"></div>    				    
		    </div>
		    
		    <div title="检验信息" style="padding:10px;">   
		        <div id="proCheck" style="width: 100%;height: 60%;"></div>
		        <!-- 图片展示 -->
		         <div id="proCheckPicBox" style="width: 100%;"></div>   		              			
		    </div>   
		    
		    <div title="加工信息" style="padding:10px;">   
		        <div id="proProcess" style="width: 100%;height: 60%;"></div>
		        <!-- 图片展示 -->
		         <div id="proProcessPicBox" style="width: 100%;"></div> 		            			  
		    </div>
		    
		    <div title="储运信息" style="padding:10px;">   
		        <div id="proStoreTransport" style="width: 100%;height: 60%;"></div>
		        <!-- 图片展示 -->
		         <div id="proStoreTransportPicBox" style="width: 100%;"></div> 	              			
		    </div>
		    
		    <div title="批次信息">   
		        <table id="proBatch" style="width: 100%;height: 100%;"></table>	    
		    </div>    
		    
		</div>  

	</div>
	
	
		<!-- 显示详细信息 -->
	<div id="product_detail2" class="easyui-window" data-options="iconCls:'icon-product',closed:true"  title="产品溯源详细信息" style="width:750px;height:500px;top:1px">
		<div id="product_tabs2" class="easyui-tabs" data-options="fit:true">   
		    <div title="产品信息" style="padding:10px;">		       
		        <table id="det2" width="100%">			    
				    
			    </table> 
			    <div id="productPicBox2" style="width: 100%;"></div>		        
		    </div> 		      
		    
		    <div title="批次信息">   
		        <table id="proBatch2" style="width: 100%;height: 100%;"></table>	    
		    </div>    
		    
		</div>  

	</div>
	
	
	<!-- 显示详细信息 -->
	<div id="batchCode" class="easyui-window" data-options="iconCls:'icon-qrcode',closed:true" title="批次二维码信息" style="width:550px;height:400px;top:1px">
     	 <div style="height:20px;width: 100%" align="center">
	         <br/>
	         	<span id="sh_info"></span>	
	         <br/>	 
	     </div>
	        <!--startprint-->
	        <div style="height:250px;width: 100%" align="center">
	        	<span id="code_Img"></span>
	        </div>
	        <!--endprint-->
	        <div style="height:50px;width: 100%" align="center">
	        	<input type="hidden" id="Dimenno_Img">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-download'" onclick="downloadCodeImg(1)">下载标签图片</a>&nbsp;&nbsp;
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-download'" onclick="downloadCodeImg(2)">下载二维码图片</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-download'" onclick="downloadCodeImg(3)">下载二维码高清大图</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="$('#batchCode').window('close')">关闭页面</a>
			</div>
	</div>
	
  </body>
</html>
