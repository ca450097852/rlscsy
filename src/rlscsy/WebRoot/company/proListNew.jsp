<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("loginCompany");

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
<title>产品列表</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/product.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(document).ready(function(){
  /*$(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});*/

});
</script>

<style type="text/css">
.weitianxie table td{line-height:25px;text-align: center;}

#proImgInfo td{line-height:20px;text-align: left;}

#proImgList table td input {
    border: 1px solid #ccc;
    height: 25px;
    width: 300px;
}

</style>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>首页</li>
    <li>产品列表</li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click" onclick="addProduct();"><span><img src="images/t01.png" /></span>添加</li>
        </ul>
        
    </div>
    
    <div id='proDiv'>
	    <table id="proTab" class="tablelist">
	    	<thead>
	    	<tr>
	        <!-- <th><input name="" type="checkbox" value="" onchange="selectEvent(this.checked);"/></th> -->
	        <th width="15%">产品名称</th>
	        <th width="10%">产品分类</th>
	        <th width="10%">是否认证</th>
			<th width="10%">是否检测</th>
			<th width="15%">生产基地</th>
			<th width="10%">规模</th>
	        <th width="10%">状态</th>
	        <th width="15%">操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        	<tr>
	        		<td colspan="8" align="center">
	        			数据加载中...
	        		</td>
	        	</tr>
	        </tbody>
	    </table>
    
   
	    <div class="pagin">
	    	<div class="message">共<i class="blue" id="total">0</i>条记录，当前显示第&nbsp;<i class="blue" id="page">0</i>&nbsp;页</div>
	        <ul class="paginList">
	        </ul>
	    </div>
	    
    </div>
    
    <div class="center_content3" style="display: none;">
    <div class="baocunhou" id="proFormTable">
        	<form id="proForm" method="post">
        		<div id="hiddenValue" style="display: none;"></div>
            	<table class="formtable" >
                  <tr>
                  		<td class="form_label">产品名称:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="product.proName" maxlength="50" data-options="required:true"  />
				    	</td>
				        <td class="form_label" width="85">产品分类:</td>
					    <td class="form_value" width="191">
					      <input name="product.typeId" id="typeId_id" style="width:300px;"/>
					      <input type="hidden" name="product.entId" value=""/>
				      </td>
				      
					</tr>	
					
					<tr>
					    <td class="form_label">是否认证:</td>
					    <td class="form_value">
					    
					   	<input type="hidden" name="productInfo.proInfoId" />
					    <input type="hidden" name="productInfo.traceSeq"/>
					    <input type="hidden" name="productInfo.proId" />
					     
					    <select id="certificate_id" class="easyui-combobox" name="productInfo.certificate" style="width: 300px"  >
								<option value="5">无</option>
								<option value="1">有机</option>
								<option value="2">绿色</option>
								<option value="3">无公害</option>
								<option value="4">地理标志</option>
								
							</select>
				    	</td>
				    	<td class="form_label">是否检测:</td>
					    <td class="form_value">
							<select id="ischeck_id" name="productInfo.ischeck"  class="easyui-combobox" style="width: 300px">
								<option value="3">无</option>
								<option value="1">自检</option>
								<option value="2">委托检测</option>
							</select>
				    	</td>
				    </tr>
				    <tr>
					    <td class="form_label">检测方式:</td>
					    <td class="form_value">
					    	<input type="radio" style="width: auto;vertical-align: middle;" name="productInfo.checkway" value="1"/>快速检测&nbsp;&nbsp;
							<input type="radio" style="width: auto;vertical-align: middle;"name="productInfo.checkway" value="2"/>定量检测
				    	</td>
				    	<td class="form_label">检测结果:</td>
					    <td class="form_value">
							<input class="easyui-validatebox"  name="productInfo.checkresult" maxlength="30" />
				    	</td>
				    </tr>
				    
				    <tr>
					    <td class="form_label">主要销售地:</td>
					    <td class="form_value">
							<input type="radio" style="width: auto;vertical-align: middle;"name="productInfo.salearea" value="1"/>内销&nbsp;&nbsp;
							<input type="radio" style="width: auto;vertical-align: middle;"name="productInfo.salearea" value="2"/>出口
				    	</td>
				    	<td class="form_label">产品包装:</td>
					    <td class="form_value">
							<input type="radio" style="width: auto;vertical-align: middle;"name="productInfo.packing" value="1"/>有&nbsp;&nbsp;
							<input type="radio" style="width: auto;vertical-align: middle;"name="productInfo.packing" value="2"/>无
				    	</td>
				    </tr>
				    <tr>
					    <td class="form_label">上市日期:</td>
					    <td class="form_value" colspan="3">
							<input class="easyui-datebox" id="listed" name="productInfo.listed" style="width:300px"/>
				    	</td>
				    </tr>
				    <tr>
					    <td class="form_label">生产基地:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="productInfo.basearea" maxlength="100" style="300px"/>
				    	</td>
	
					    <td class="form_label">规模:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="productInfo.scale" maxlength="100" style="300px"/>
				    	</td>
				    </tr>
					
					
								    
				    <tr>
					    <td class="form_label">产品状态:</td>
					    <td class="form_value">
							<select id="state_id" name="product.state"  class="easyui-combobox" style="width: 300px">
								<option value="0">待审</option>
								<option value="1">生产中</option>
								<option value="2">已停产</option>
							</select>
				    	</td>
				    	<td class="form_label">产品条码:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="product.barcode" maxlength="50" />
				    	</td>
				    </tr>
				    <tr>
					    <td class="form_label">产品规格:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="product.unit"  maxlength="100" id="product_unit" style="width:200px" placeholder="多个规格则用“/”(斜杠)分隔。"/>(例：5公斤/10公斤)
				    	</td>
					    <td class="form_label">保鲜防腐:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="product.retain" maxlength="100"/>
				    	</td>
				    </tr>
				    <tr>
					    <td class="form_label">储藏条件:</td>
					    <td class="form_value">
					    	<input class="easyui-validatebox" name="product.storageConditions" maxlength="100" />
					    </td>
					    <td class="form_label">保质期:</td>
					    <td class="form_value">
					    	<input class="easyui-validatebox" name="product.shelfLife"  />
					    </td>
				    </tr>
				    
				     <tr>
					    <td class="form_label">生产商名称:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="product.manufacturer" maxlength="50"/>
				    	</td>
				    	<td class="form_label">生产商电话:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="product.sourceTel" maxlength="30" />
				    	</td>
				    </tr>
				    <tr>
					    <td class="form_label">生产商地址:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="product.sourceAddr" maxlength="100"/>
				    	</td>
				    	
				    	<td class="form_label">经销商地址:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="product.distributorAddr" maxlength="100"/>
				    	</td>
				    </tr>
				    <tr>
					    <td class="form_label">经销商名称:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="product.distributor" maxlength="50" />
				    	</td>
				    	<td class="form_label">经销商电话:</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="product.distributorTel" maxlength="30" />
				    	</td>
				    </tr>
				    <tr>
						<td class="form_label">产品介绍：</td>
						<td class="form_value" colspan="3">
							<textarea id="prodesc" rows="3" name="product.proDesc" style="width: 700px"></textarea>
						</td>
					</tr>
				    <tr>
						<td class="form_label">备注：</td>
						<td class="form_value" colspan="3" >
							<textarea id="p_remark" rows="2" name="product.remark" style="width: 700px"></textarea>
						</td>
					</tr>
               </table>
               </form>
               <div class="btn_area">
               		<input  name="" type="button" class="btn" onclick="submitProTypeForm();" value="保 存"/>
               		<input  name="" type="button" class="btn" onclick="cancelPro();" value="取消"/>
               	</div>
            </div>
    </div>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
        <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>
