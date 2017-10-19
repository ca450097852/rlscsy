<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>	
  	<script src="${basePath}static/js/hontek/review/product.js" type="text/javascript"></script>
  	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
  	<script src="${basePath}static/js/hontek/valid/validate.js"　type="text/javascript"></script>
 	<script src="<%=basePath%>static/js/jquery-myplaceholder.js" type="text/javascript"></script>
 	
 	<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
    <link rel="stylesheet" href="uploadify/uploadify.css" type="text/css"></link>
 	
 	<style type="text/css">
		#det td {
			border-color: #CCCCCC;
			border-style: dotted;
		    border-width: 0 1px 1px 0;
		    margin: 0;
		    padding: 0;
		}
	</style>
  </head>
  <body>
     <table id="productdatagrid">  
    </table>  
    <!-- purchasedatagrid 工具栏 -->
    <div id="tb" style="height:auto">
   		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendProType()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateProType()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="updateProTypeState(2)">已停产</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="updateProTypeState(1)">生产中</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removerProduct()">删除</a>
		<div>
		<from id="myseach">
			&nbsp;&nbsp;产品名称: <input class="easyui-validatebox" id="productName" style="width:80px">
			&nbsp;分类: 
			<input id="pro_type_1" style="width:150px;">
			&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="tb_search()">搜 索</a>
			&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="tb_reset()">重置</a>
			</from>
		</div>
	</div>
	
	<div id="hiden_value">
		<input type="hidden" name="product.proId"> 
    	<input type="hidden" name="product.dimenno" />
    	<input type="hidden" name="product.proCode" />
	</div>
	
	<!-- 显示详细信息 -->
	<div id="product_detail" class="easyui-window" title="商品详细" resizable='false' shadow='false' style="width:680px;top:10">
		<div style="padding:10px 10px 10px 10px">
		<table id="det" width="100%">			    
				    <tr>
				        <td width="85">分类:</td>
					    <td width="230">
					      <span id="d_typeName"></span></td>
					    <td colspan="2" rowspan="5">
					    <img id="d_codeImg" width="150px" alt="二维码图片" src="">
					    </td>			      				      				    					    					    				</td>
				    </tr>			
					 <tr>
					    <td width="85">产品编码:</td>
					    <td width="230">
					      <span id="d_proNo"></span></td>		   
				    </tr>		    
				    <tr>
					    <td>产品名称:</td>
					    <td>
							<span id="d_proName"></span></td>
			    	</tr>
					
					 <tr>
				    	<td>条码:</td>
					    <td>
							<span id="d_barcode"></span></td>
					</tr>
					
					
				    <tr>
				    	<td>二维码:</td>
					    <td>
							<span id="d_dimenno"></span></td>
					</tr>
				    <tr>
					    <td>规格:</td>
					    <td>
							<span id="d_unit"></span></td>
				    	<td width="90">状态:</td>
					    <td>
							<span id="d_state"></span></td>
				    </tr>
				    
				    <tr>
					    <td>保鲜防腐:</td>
					    <td colspan="3">
							<span id="d_retain"></span></td>
				    </tr>
				    <tr>
					    <td>储藏条件:</td>
					    <td>
							<span id="d_storageConditions"></span></td>
				    	<td width="90">保质期:</td>
					    <td>
							<span id="d_shelfLife"></span></td>
				    </tr>
				    
				     <tr>
					    <td>生产商:</td>
					    <td>
							<span id="d_manufacturer"></span></td>
						<td>生产商电话:</td>
					    <td>
							<span id="d_sourceTel"></span></td>
				    </tr>
				    <tr>
					    <td>生产商地址:</td>
					    <td colspan="3">
							<span id="d_sourceAddr"></span></td>
				    </tr>
				    <tr>
					    <td>经销商:</td>
					    <td>
							<span id="d_distributor"></span></td>
				    	<td>经销商电话:</td>
					    <td>
							<span id="d_distributorTel"></span></td>
				    </tr>
				    <tr>
					    <td>经销商地址:</td>
					    <td colspan="3">
							<span id="d_distributorAddr"></span></td>
				    </tr>
				    <tr>
						<td>产品说明：</td>
						<td colspan="3">
							<span id="d_proDesc"></span></td>
					</tr>
				    <tr>
						<td>备注：</td>
						<td colspan="3" >
							<span id="d_remark"></span></td>
					</tr>
			    </table>
	 	 </div>
		<div id="dlg-buttons1" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" onclick="javascript:$('#product_detail').dialog('close')">关闭</a>
		</div>
	</div>
	
	<!-- 添加产品信息 -->
	<div id="addProduct" class="easyui-window" title="添加产品信息" style="width:600px;top:1;height: 465px">
		<div style="padding:5px 0 5px 5px">
		    <form id="product_form" method="post">
		    
		    	<input type="hidden" id="method"> 
		    	<div id="in_value" style="display: none"></div>
			   <table>
			   <%
			   if("2".equals(visfrom)){
				   %>
				    <input type="hidden"  name="product.entId" id="ventId"/> 
				   <%
			   }else{
				   %>
				   <tr>
					    <td>选择企业:</td>
					    <td colspan="3">
							<input name="product.entId" id="s_entId" style="width:300px;"/>
				    	</td>
				    </tr>    
				   <%
			   }
			   %>
			   		
				    <tr>
				        <td width="85">分类:</td>
					    <td width="191">
					      <input name="product.typeId" id="pro_type" style="width:150px;">
				      </td>
				      <td>产品名称:</td>
					    <td>
							<input class="easyui-validatebox" name="product.proName" maxlength="50" data-options="required:true"  style="width:150px"/>
				    	</td>
					</tr>				    
				    <tr>
					    <td>状态:</td>
					    <td>
							<select id="pro_state" name="product.state"  class="easyui-combobox" data-options="panelHeight: 'auto'" style="width:150" data-options="required:true" >
								<option value="0">待审</option>
								<option value="1">生产中</option>
								<option value="2">已停产</option>
							</select>
				    	</td>
				    	<td>条码:</td>
					    <td>
							<input class="easyui-validatebox" name="product.barcode" maxlength="50" style="width:150px"/>
				    	</td>
				    </tr>
				    <tr>
					    <td>规格:</td>
					    <td colspan="3">
							<input class="easyui-validatebox" name="product.unit"  maxlength="100" id="product_unit" style="width:300px" placeholder="如相同产品有多个规格则用“/”(斜杠)分隔。"/>(例：5公斤/10公斤或5L/10L)
				    	</td>
				    </tr>
				    <tr>
					    <td>保鲜防腐:</td>
					    <td colspan="3">
							<input class="easyui-validatebox" name="product.retain" maxlength="100" style="width:430px"/>
				    	</td>
				    </tr>
				    <tr>
					    <td>储藏条件:</td>
					    <td>
					    	<input class="easyui-validatebox" name="product.storageConditions" maxlength="100" style="width:150px"/>
					    </td>
					    <td>保质期:</td>
					    <td>
					    	<input class="easyui-validatebox" name="product.shelfLife"  style="width:150px"/>
					    </td>
				    </tr>
				    
				     <tr>
					    <td>生产商:</td>
					    <td>
							<input class="easyui-validatebox" name="product.manufacturer" maxlength="50" data-options="required:true"  style="width:150px"/>
				    	</td>
				    	<td>生产商电话:</td>
					    <td>
							<input class="easyui-validatebox" name="product.sourceTel" maxlength="30" style="width:150px"/>
				    	</td>
				    </tr>
				    <tr>
					    <td>生产商地址:</td>
					    <td colspan="3">
							<input class="easyui-validatebox" name="product.sourceAddr" maxlength="100" style="width:430px"/>
				    	</td>
				    </tr>
				    <tr>
					    <td>经销商:</td>
					    <td>
							<input class="easyui-validatebox" name="product.distributor" maxlength="30" style="width:150px"/>
				    	</td>
				    	<td>经销商电话:</td>
					    <td>
							<input class="easyui-validatebox" name="product.distributorTel" maxlength="30" style="width:150px"/>
				    	</td>
				    </tr>
				     <tr>
					    <td>经销商地址:</td>
					    <td colspan="3">
							<input class="easyui-validatebox" name="product.distributorAddr" maxlength="100" style="width:430px"/>
				    	</td>
				    </tr>
				    <tr>
						<td>产品说明：</td>
						<td colspan="3">
							<textarea id="prodesc" rows="3" name="product.proDesc" style="width: 430px"></textarea>
						</td>
					</tr>
				    <tr>
						<td>备注：</td>
						<td colspan="3" >
							<textarea id="p_remark" rows="2" name="product.remark" style="width: 430px"></textarea>
						</td>
					</tr>
			    </table>
		    </form>
	 	 </div>
		<div id="dlg-buttons" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitProTypeForm()";>提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" onclick="javascript:$('#addProduct').dialog('close')">关闭</a>
		</div>
	</div>
	
	
	<div id="appMamager" class="easyui-window" title="产品图片管理" data-options="closed:true" style="width:600px;top:10">
		<div style="height: 200px">
			<table id="proAppList">  
	    	</table>  
	 	 </div>
		<div id="dlg-buttons" align="center">
			<table width="100%">
				<tr>
					<td width="80">请选择图片：</td>
					<td>
					<table width="100%">
						<tr>
							<td><input type="file" name="uploadify" id="uploadify" /></td>
							<td id="fileQueue"></td>
						</tr>
					</table>
					
					</td>
				</tr>
				<tr>
					<td>排序：</td>
					<td>
						<input type="text" class="easyui-numberbox" data-options="required:true" id="app_orderby" value='5'/>
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td>
						<textarea id="appRemark" rows="3" cols="" style="width:100%"></textarea>
					</td>
				</tr>
			</table>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitAppForm()";>提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" onclick="javascript:$('#appMamager').dialog('close')">关闭</a>
		</div>
	</div>
	
	<input type="hidden" id="sessionId" value="<%=session.getId() %>" />
	<input type="hidden" id="app_proId" value="" />
	<input type="hidden" id="h_path" value='<%=basePath %>' />
	<div id="h_file">
		
	</div>
	<input type="hidden" id="visfrom" value="<%=visfrom %>"/>
	<input type="hidden" id="lh_entId" value="<%=loginUser.getEntId() %>"/>
  </body>
</html>
