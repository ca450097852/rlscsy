<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("enterprse");

if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}


EntStyle companyStyle = session.getAttribute("companyStyle")==null?null:(EntStyle)session.getAttribute("companyStyle");
String styleFile = "blue";
if(companyStyle!=null){
	if(companyStyle.getScCss()!=null && !"".equals(companyStyle.getScCss())){
		styleFile = companyStyle.getScCss();
	}
}


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-产品管理</title>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
<script src="<%=basePath %>static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
    
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script type="text/javascript" src="<%=basePath %>company/js/proList.js"></script>

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

<style type="text/css">
.weitianxie table td{line-height:25px;text-align: center;}
.combo{
background-color: #fff;
border-color: #07aa94;
}
#proImgInfo td{line-height:20px;text-align: left;}
</style>
</head>

<body>
<div>
    <div class="CENTER">
        <div class="center_content">
        	<div class="baocunhou" style="display:none" id="proFormTable">
        	<form id="proForm" method="post">
        		<div id="hiddenValue" style="display: none;"></div>
            	<table border="0" cellspacing="0" cellpadding="0" style="margin: 0px">
                  <tr>
				        <td width="85">产品分类:</td>
					    <td width="191">
					      <input name="product.typeId" id="typeId_id" style="width:300px;"/>
					      <input type="hidden" name="product.entId" value=""/>
				      </td>
				      <td>产品名称:</td>
					    <td>
							<input class="easyui-validatebox" name="product.proName" maxlength="50" data-options="required:true"  />
				    	</td>
					</tr>				    
				    <tr>
					    <td>产品状态:</td>
					    <td>
							<select id="pro_state" name="product.state"   >
								<option value="0">待审</option>
								<option value="1">生产中</option>
								<option value="2">已停产</option>
							</select>
				    	</td>
				    	<td>产品条码:</td>
					    <td>
							<input class="easyui-validatebox" name="product.barcode" maxlength="50" />
				    	</td>
				    </tr>
				    <tr>
					    <td>产品规格:</td>
					    <td colspan="3">
							<input class="easyui-validatebox" name="product.unit"  maxlength="100" id="product_unit" style="width:300px" placeholder="如相同产品有多个规格则用“/”(斜杠)分隔。"/>(例：5公斤/10公斤或5L/10L)
				    	</td>
				    </tr>
				    <tr>
					    <td>保鲜防腐:</td>
					    <td colspan="3">
							<input class="easyui-validatebox" name="product.retain" maxlength="100" style="width:668px"/>
				    	</td>
				    </tr>
				    <tr>
					    <td>储藏条件:</td>
					    <td>
					    	<input class="easyui-validatebox" name="product.storageConditions" maxlength="100" />
					    </td>
					    <td>保质期:</td>
					    <td>
					    	<input class="easyui-validatebox" name="product.shelfLife"  />
					    </td>
				    </tr>
				    
				     <tr>
					    <td>生产商:</td>
					    <td>
							<input class="easyui-validatebox" name="product.manufacturer" maxlength="50" data-options="required:true"  />
				    	</td>
				    	<td>生产商电话:</td>
					    <td>
							<input class="easyui-validatebox" name="product.sourceTel" maxlength="30" />
				    	</td>
				    </tr>
				    <tr>
					    <td>生产商地址:</td>
					    <td colspan="3">
							<input class="easyui-validatebox" name="product.sourceAddr" maxlength="100" style="width:668px"/>
				    	</td>
				    </tr>
				    <tr>
					    <td>经销商:</td>
					    <td>
							<input class="easyui-validatebox" name="product.distributor" maxlength="30" />
				    	</td>
				    	<td>经销商电话:</td>
					    <td>
							<input class="easyui-validatebox" name="product.distributorTel" maxlength="30" />
				    	</td>
				    </tr>
				     <tr>
					    <td>经销商地址:</td>
					    <td colspan="3">
							<input class="easyui-validatebox" name="product.distributorAddr" maxlength="100" style="width:668px"/>
				    	</td>
				    </tr>
				    <tr>
						<td>产品说明：</td>
						<td colspan="3">
							<textarea id="prodesc" rows="3" name="product.proDesc" style="width: 666px"></textarea>
						</td>
					</tr>
				    <tr>
						<td>备注：</td>
						<td colspan="3" >
							<textarea id="p_remark" rows="2" name="product.remark" style="width: 666px"></textarea>
						</td>
					</tr>
               </table>
               </form>
               <div class="btn_area2"><a class="btn" onclick="submitProTypeForm()" >保存</a> <a class="btn_next" onclick="cancelPro();">取消</a></div>
            </div>
        
        
        
			<div class="weitianxie" id="proTable">
			<div class="caozuo"><a class="xiugai" onclick="addProduct();">添加</a><a class="shanchu" onclick="deletePros();">删除</a></div>
            	<table width="100%" id="proTab" border="0" cellspacing="1" cellpadding="1" style="margin:0;" bgcolor="#CCCCCC">

				</table>
				<div class="page">
				</div>
            </div>
            
            <!-- 产品图片 -->
            <div id="proImg" class="weitianxie" style="display: none">
            	<table width="100%" border="0" cellspacing="1" id="proAppend" cellpadding="1" style="margin:0;"  bgcolor="#CCCCCC">
				</table>
				<table width="100%" border="0" id="proImgInfo" cellspacing="1" cellpadding="1" style="margin:0;"  bgcolor="#CCCCCC">
					<tr bgcolor="#FFFFFF"><td colspan="2" style="text-align: center;line-height: 25px">添加产品图片</td></tr>				
				   	<tr bgcolor="#FFFFFF"><td>排序</td><td><input id="app_orderby" value="5"/></td></tr>
				    <tr bgcolor="#FFFFFF"><td>备注</td><td><input id="app_remark"/></td></tr>
				    <tr bgcolor="#FFFFFF">
				    	<td>请选择图片</td>
				    	<td>
				    		<table width="100%;" style="margin:0;">
				    			<tr>
				    				<td  width="120"><input type="file" id="uploadify"/></td>
				    				<td><div id="fileQueue"></div></td>
				    			</tr>
				    		</table>
				    	</td>
				    </tr>
				</table>
				<input type="hidden" id="filePath"/>
				<input type="hidden" id="fileName"/>
				<div class="btn_area2" >
					<a class="btn" onclick="submitAppForm();">保存</a>
					<a class="btn" style="margin-left: 10px;" onclick="cancelAddApp();">取消</a>
				</div>
            </div>
        </div>
        <div class="clear"></div>
        <input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
        <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
    </div>
</div>
</body>
</html>
