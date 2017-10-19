<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.comm.util.DateUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/";

Object obj = session.getAttribute("loginUser");
String isAdmin = "";
if(obj!=null){
	isAdmin  = ((LoginUser)obj).getAdmin();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>企业管理 - 企业信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="${basePath}<%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
		<script src="${basePath}static/js/easyui-1.3.4/datagrid-detailview.js"　type="text/javascript"></script>		
	
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>	
	<script src="${basePath}static/js/hontek/company/enterprise.js" type="text/javascript"></script>
	<!-- 图片显示 -->
	<script type="text/javascript" src="${basePath}static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="${basePath}static/js/fancybox/jquery.fancybox-1.3.4.js"></script>
	<link rel="stylesheet" type="text/css" href="${basePath}static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	
	<script type="text/javascript" src="${basePath}static/js/xheditor-1.2.1/xheditor-1.2.1.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/xheditor-1.2.1/xheditor_lang/zh-cn.js"></script>
	
	<style type="text/css">
		#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GP0T6CBjquSpz4pUgK8KFzyb"></script> -->
	
	<script type="text/javascript">
		$(function(){
			//初始化xhEditor编辑器插件
			$('#xh_editor').xheditor({
				tools:'full',
				skin:'default',
				upMultiple:true,
				upImgUrl: "<%=basePath%>/UploadFileServlet",
				upImgExt: "jpg,jpeg,gif,bmp,png",
				onUpload:insertUpload,
				html5Upload:false
			});
			//xbhEditor编辑器图片上传回调函数
			function insertUpload(msg) {
				var _msg = msg.toString();
				var _picture_name = _msg.substring(_msg.lastIndexOf("/")+1);
				var _picture_path = Substring(_msg);
				var _str = "<input type='checkbox' name='_pictures' value='"+_picture_path+"' checked='checked' onclick='return false'/><label>"+_picture_name+"</label><br/>";
				$("#xh_editor").append(_msg);				
				$("#uploadList").append(_str);
			}
			//处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.
			function Substring(s){
				return s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length);
			}
		});
		
		function showChildImg(){
			$("#chimgDiv a").fancybox();
			$("#chimgDiv a:eq(0)").click();
		}
	
	</script>
	
  </head>
  <body>
  <div id="chimgDiv" style="display:none;">
  	
  </div>
  <input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 	 
  <input type="hidden" id="h_path" value="<%=path %>">
  <input type="hidden" id="filePath" value="<%=filePath %>">
  <input type="hidden" id="isAdmin" value="<%=isAdmin %>">
  
  <!-- 组织机构列表 -->
   <table id="list_enterprise">
   </table>  
    <!-- list_enttype 工具栏 -->
    <div id="tb" style="height:auto">
		<%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update(-1)">修改</a>--%>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="removeitBtn" onclick="removeit()">删除</a>
		<div>
				所属行政机构: <input class="easyui-validatebox" style="width:150px" id="entSearch">&nbsp;&nbsp;
				企业名称: <input class="easyui-validatebox" style="width:150px" id="nameSearch">&nbsp;&nbsp;
				企业状态: <select class="easyui-combobox" style="width:100px;" id="companyRstsSearch">
					      	<option value=''>--请选择--</option>
					      	<option value='0'>待提交</option>
					      	<option value='1'>待审核</option>
					      	<option value='2'>暂停</option>
					      	<option value='3'>退回</option>
					      	<option value='4'>通过</option>
					      </select>&nbsp;&nbsp;
				地区：<input id="areaId" style="width:200px;">&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find()">搜 索</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
		</div>
	</div>
	
	<!--企业基本信息-->
	<div id="add" class="easyui-window" data-options="iconCls:'icon-house',closed:true" style="width:900px;height:460px;">
		 <div class="easyui-tabs" id="companyTab" data-options="fit:true">		 
	         <div align="center" title="企业基本信息">
			    <form id="add_form" method="post" enctype="multipart/form-data">
			    	<input type="hidden" name="enterprise.flag" value="3">
				    <input type="hidden" name="enterprise.integrityRecord" value=""> 
				    <input type="hidden" name="enterprise.isReported" value="0">
				   	<input type="hidden" name="enterprise.sysCode" value="086020"> 			 
				   	<input type="hidden" name="enterprise.entCode" value=""> 			     		    	 				   	    		    	 
			    	<input type="hidden" id="entId" name="enterprise.entId"/>
			    	<input type="hidden" name="enterprise.account">	
			    	<input type="hidden" name="enterprise.crtUserId">					    						    	   	
					<input type="hidden" id="oprerate"/>				
					<input type="hidden" name="enterprise.sign">
					<input id="enterprise_seq" type="hidden" name="enterprise.seq">
					
				    <table class="formtable" cellspacing="1" cellpadding="0">
				     	<tr height="15px">
				     	
					  		<td class="form_label">企业名称：</td>
						    <td class="form_value">
						    	<input class="easyui-validatebox" type="text" name="enterprise.name" style="width:300px">
						    </td>
						  
						    <td  class="form_label">企业简称：</td>
						    <td class="form_value">
						      <input class="easyui-validatebox" type="text" name="enterprise.simpleName" style="width:300px">
						    </td>
					    </tr>
					    <tr height="15px">
						    <td class="form_label">所属区域：</td>
						    <td class="form_value">
							   <input name="enterprise.areaId" id="areaId" style="width:300px;">
						    </td>
						    
							<td class="form_label">企业法人：</td>
						    <td class="form_value">
						    	<input class="easyui-validatebox" type="text" name="enterprise.legalPerson"  style="width:300px">
						    </td>
					    </tr>				    
					    <tr height="15px">
					  		<td class="form_label">企业类型：</td>
						    <td class="form_value">
								<input name="enterprise.entType" style="width: 300px;" id="entType_id">
							</td>
								
					        <td class="form_label">统一社会信用代码：</td>
						    <td class="form_value">
						      	<input class="easyui-validatebox" type="text" name="enterprise.orgCode" style="width:300px">					      						   
						    </td>
					    </tr>				    
					     <tr height="15px">
					        <td class="form_label">注册地址：</td>
						    <td class="form_value">
								<input class="easyui-validatebox" type="text" name="enterprise.regAddr" style="width:300px">					      
						    </td>
						    <td class="form_label">经营地址：</td>
						    <td class="form_value">
								<input class="easyui-validatebox" type="text" name="enterprise.manageAddr" style="width:300px">					      
						    </td>
		
					    </tr>
					    <tr height="15px">
					  		<td class="form_label">联系电话：</td>
						    <td class="form_value">
						      <input class="easyui-validatebox" type="text" name="enterprise.tel" style="width:300px">
						    </td>
							<td class="form_label">手机号码：</td>
						    <td class="form_value">
						      <input class="easyui-validatebox" type="text" name="enterprise.mobile" style="width:300px">
						    </td>
					    </tr>
					    <tr height="15px">
					  		<td class="form_label">企业网址：</td>
						    <td class="form_value">
						      <input class="easyui-validatebox" type="text" name="enterprise.domName" style="width:300px">
						    </td>
					        <td class="form_label">企业状态：</td>
						    <td class="form_value">						    
						      <input name="enterprise.companyRsts" id="companyRsts"  style="width:300px;">						      	
						    </td>
					    </tr>
					    <tr height="15px">
					    	<td class="form_label">电子邮箱：</td>
						    <td class="form_value">
						      <input class="easyui-validatebox" type="text" name="enterprise.email" style="width:300px" >
						    </td>
						   <td class="form_label">邮政编码：</td>
						    <td class="form_value">
						      <input class="easyui-validatebox" type="text" name="enterprise.postCode" data-options="validType:'ZIP'" style="width:300px">
						    </td>
					    </tr>
					    <tr height="15px">
					    	<td class="form_label">是否批次追溯：</td>
						    <td class="form_value" colspan="3">
						      <input class="easyui-validatebox" type="text" name="enterprise.isbatch" style="width:300px" >
						    </td>
						   
					    </tr>
					    <%--<tr height="10px">
					    	<td class="form_label">企业logo：</td>
					    	<td>
					    		<input type="file" name="fileQueue" id="uploadifyLogo" />
					    		<input type="hidden" name="enterprise.logoUrl" id="logoUrl" />
					    		
							</td>
						    <td colspan="2">
					    		<div id="fileQueueLogo" style="width: 300px"></div>
					   		</td>					   						
						</tr>							
						--%>
						<tr height="15px">
					    	<td class="form_label">简介：</td>
						    <td  class="form_value" colspan="3">
					    	<textarea class="easyui-validatebox" name="enterprise.intro" rows="4"  style="width:100%"></textarea>
					   		</td>
					    </tr>	
					    
					    <tr height="10px">
					    	<td class="form_value" colspan="4">
					    		<div style="text-align:center;padding:5px">
								    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="clearForm(1)">关闭页面</a>
							   </div>					    		
					    	</td>
					    </tr>			    
				    </table>
			    </form>
	       </div>
	       
	       <div align="center" title="资质信息">
		       
				<table id="zizhiDataGrid"></table>  
	 
				<input type="hidden" id="zizhiEntId" name="zizhi.entId">				   			
				  		
				<div id="attachWin" class="easyui-window" title="附件管理" data-options="iconCls:'icon-attach',closed:true" style="width: 550px; height: 400px; padding: 10px">
					<div id="zizhiFile">
						
					</div>
				</div>       
	      </div>
	      <%--
	      
	       <div align="center" title="生产信息">
	       	 <table id="productionDataGrid"></table>  			  
			 <input type="hidden" id="proEntId" name="production.entId">			   		


		   	 <div id="attachWin1" class="easyui-window" title="附件管理" data-options="iconCls:'icon-attach',closed:true" style="width: 550px; height: 450px;top:20px">
				
				<div id="cc" class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north'" title="产品图片" style="height:200px">
						<div id="productionFile"></div>
					</div>
				
					<div data-options="region:'center'" title="许可证扫描件">
						<div id="licenceFile"></div>
					</div>
				</div>
			</div>
	       
	      </div>
	      
	      --%>
	      <div align="center" title="审核信息">
	       	<table id="auditRecordDataGrid"></table>
	       	<input type="hidden" id="auditRecordEntId" name="auditRecord.entId">	       	
	     </div>	 
	     
	     <div align="center" title="监管信息">
	      	<table id="superviseDataGrid"></table>
	      	    <div id="tb_supervise" style="height:auto;padding: 5">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendSuperVise()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateSuperVise()">修改</a>
				
				</div>
		</div>
		<%--
	     
	    <div align="center" title="评价信息">
      	    <div>
				<form id="evaluationForm" method="post" >
					<input type="hidden" id="evaluation_companyId" name="evaluation.companyId">
					<input type="hidden" id="evaluation_evalId" name="evaluation.evalId">	
					<input type="hidden" name="evaluation.evalTime">	
						
					<table class="formtable" cellspacing="1" cellpadding="0">
					<tr>
						<td class="form_label" width="15%">评价内容:</td>
						<td class="form_value" colspan="3" width="80%"><textarea name="evaluation.evalContents" id="evalContents" style="width: 99%;height: 160px"></textarea> 
					
						</td>
					</tr>
					<tr>
						<td class="form_label">企业回复:</td>
						<td class="form_value" colspan="3"><textarea name="evaluation.companyContents" id="companyContents" style="width: 99%;height: 150px;" readonly="readonly"></textarea> 
						
						</td>
					</tr>	
					<tr>
						<td class="form_value" colspan="4" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEvaluationForm()" data-options="iconCls:'icon-ok'">提交</a>
						</td>
						</tr>				
					</table>
				</form>
			
			</div>
		</div>
		
		--%>
		<div id="recordDiv" align="center" title="生产档案信息">				
			<%--
			<table id="recordDatagrid"></table>				
			--%>
						
		</div>
	     
	     <div align="center" title="基地信息">
 	     	 	<!-- <div id="allmap"></div> -->
				<table id="chandiDatagrid"></table>		
	     </div>
   
   		<div align="center" title="标识管理">
   			<iframe id="dimennoframe" width="99%" height="99%" frameborder="0"></iframe>
	     </div>
       
	</div>
</div>
	
	
	<div id="addSupervise" class="easyui-window" title="添加监管信息" data-options="iconCls:'icon-save',modal:true,closed:true" style="width:900px;height:450;padding:0px;top:10px">
		<form id="superviseForm" method="post" >
			<input type="hidden" id="supervise_entId" name="supervise.entId">
			<input type="hidden" id="supervise_supId" name="supervise.supId">	
			<input type="hidden" name="supervise.supEnt">
			<input type="hidden" name="supervise.supUser">
			<input type="hidden" name="supervise.supCode">		
			<table class="formtable" cellspacing="1" cellpadding="0">
			<tr>
				<td class="form_label">信息标题:</td>
				<td class="form_value" colspan="3"><input type="text" class="easyui-validatebox" data-options="required:true" id="supervise_title" name="supervise.title" style="width: 500px"></td>
			</tr>
			<tr>
							<td class="form_label">信息状态:</td>
					<td class="form_value">
							<select id="state" class="easyui-combobox" name="supervise.state" style="width:200px;">   
							    <option value="1">待审</option>   
							    <option value="2">对内通告</option>   
							    <option value="3">对外发布</option>   
							</select>  
					</td>
			<td class="form_label">发布时间:</td>
				<td class="form_value">
					<input type="text" class="easyui-datebox" data-options="required:true" id="supervise_supTime" name="supervise.supTime" value="<%=DateUtil.formatDate() %>">
				</td>
			</tr>
			<tr>
				<td class="form_label">信息内容:</td>
				<td class="form_value" colspan="3"><textarea id="xh_editor" name="supervise.contents" style="width: 99%;height: 250px"></textarea> 
				
					<div id="uploadList"></div>
				</td>
			</tr>
				
			<tr>
				<td class="form_value" colspan="4" align="center">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitSuperviseForm()" data-options="iconCls:'icon-ok'" id="btnSupervise">提交</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#addSupervise').window('close')" data-options="iconCls:'icon-no'">关闭页面</a>
				</td>
			</tr>				
		</table>
	</form>
	</div>
	
		<div id="showSupervise" class="easyui-window" title="查看监管信息" data-options="iconCls:'icon-save',modal:true,closed:true" style="width:900px;height:450;padding:0px;top:10px">
			<table>
			<tr>
				<td class="form_label">信息标题:</td>
				<td class="form_value"><input type="text" class="easyui-validatebox" id="supervise_title2" name="supervise.title" style="width: 300px"></td>
				<td class="form_label">发布时间:</td>
				<td class="form_value">
					<input type="text" class="easyui-datebox" id="supervise_supTime2" name="supervise.supTime">
				</td>
			</tr>
			<tr>
				<td class="form_label">信息内容:</td>
				<td class="form_value" colspan="3"><textarea name="supervise.contents" id="supervise_contents2" style="width: 99%;height: 200px"></textarea></td>
			</tr>
				
			<tr>
				<td class="form_label">企业回复:</td>
				<td class="form_value" colspan="3"><textarea name="supervise.companyContents" id="supervise_companyContents2" style="width: 99%;height: 100px"></textarea></td>
			</tr>	
				
			<tr>
				<td class="form_value" colspan="4" align="center">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#showSupervise').window('close')" data-options="iconCls:'icon-no'">关闭页面</a>
				</td>
			</tr>				
		</table>
	</div>
	
	
	<div id="showRecord" class="easyui-window" title="查看档案信息" data-options="iconCls:'icon-table',modal:true,closed:true" style="width:1050px;height:450;">
		   	<iframe id="recordframe" width="99%" height="99%" frameborder="0"></iframe>
		   	
	</div>	
	
	<div id="showLog" class="easyui-window" title="查看操作记录信息" data-options="iconCls:'icon-log',modal:true,closed:true" style="width:900px;height:470px;">
		<table id="logTable"></table>
		   	
	</div>	
	
	
	<div id="batchAudit" class="easyui-window" title="审核批次信息" data-options="iconCls:'icon-table',modal:true,closed:true" style="width:350px;height:150;">
			<table style="width: 320px">
			<tr>
				<td class="form_label">审核意见:</td>
				<td class="form_value">				
				<input type="hidden" id="batchStateId" > 
				<input type="hidden" id="typeTabId"> 
				
				<input type="radio" name="batchState"  value="1" checked="checked">正常 
				<input type="radio" name="batchState"  value="2">异常				
				</td>
			</tr>
			
			<tr>
				<td class="form_value" colspan="2" align="center" style="padding-top: 30px">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitBatchAudit()" data-options="iconCls:'icon-ok'">提交</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#batchAudit').window('close')" data-options="iconCls:'icon-no'">关闭</a>
				</td>
			</tr>
		   	
	</div>	

  </body>
  <script type="text/javascript">

 </script>
</html>
