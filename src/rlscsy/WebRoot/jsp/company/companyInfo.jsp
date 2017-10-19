<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
LoginUser obj = (LoginUser)session.getAttribute("loginUser");

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
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>	
	<script src="${basePath}static/js/hontek/company/companyinfo.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
    <link rel="stylesheet" href="uploadify/uploadify.css" type="text/css"></link>
    
    <script language="javascript" type="text/javascript">

   function doPrint() {
        bdhtml=window.document.body.innerHTML;
        sprnstr="<!--startprint-->";
        eprnstr="<!--endprint-->";
        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
        window.document.body.innerHTML=prnhtml;
        window.print();
        document.body.innerHTML = bdhtml;
    	return false; 
        } 
    </script>
  </head>
  <body>
  
  <input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 	 
  <input type="hidden" id="h_path" value="<%=path %>">
	<!-- 添加组织机构 -->
		 <div class="easyui-tabs" id="companyTab" data-options="fit:true">		 
	         <div align="center" title="企业信息">
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
					
					<input type="hidden" name="enterprise.townRsts">	
					<input type="hidden" name="enterprise.areaRsts">	
					<input type="hidden" name="enterprise.cityRsts">	
					<input type="hidden" name="enterprise.provinceRsts">
					<input type="hidden" name="enterprise.sts">
					<input type="hidden" name="enterprise.seq">
					<input type="hidden" name="enterprise.companyRsts">
					
					<input type="hidden" name="enterprise.parentId">
					<input type="hidden" name="enterprise.sign">
						
				    <table>
				     	<tr height="15px">
					  		<td width="90px">企业名称:</td>
						    <td>
						    	<input class="easyui-validatebox" type="text" name="enterprise.name" data-options="required:true,validType:'length[0,50]'" style="width:250px">
						    </td>
					    	<td width="90px">简称:</td>
						    <td>
						      <input class="easyui-validatebox" type="text" name="enterprise.simpleName" data-options="validType:'length[0,25]'" style="width:250px">
						    </td>
					    </tr>
					    <tr height="15px">
						    <td width="60px">所属区域:</td>
						    <td>
							   <input name="enterprise.areaId" id="areaId" style="width:250px;">
						    </td>
						    
							<td width="60px">企业法人:</td>
						    <td>
						    	<input class="easyui-validatebox" type="text" name="enterprise.legalPerson" data-options="required:true,validType:'length[0,10]'" style="width:250px">
						    </td>
					    </tr>				    
					    <tr height="15px">
					  		<td width="60px">企业类型:</td>
						    <td>
						    <!-- multiple:true, 多选属性 -->
						      <input class="easyui-combobox" 
								name="enterprise.entType"
								style="width:250px;"
								id="entType_id"
								data-options="
										url:'enttype_getEntTypeToSelect.action',
										method:'get',
										valueField:'id',
										textField:'text',
										required:'true',
										editable: false
							  ">
						    </td>
					        <td width="60px">组织机构代码:</td>
						    <td>
						      	<input class="easyui-validatebox" type="text" name="enterprise.orgCode" data-options="validType:'length[0,30]'" style="width:250px">					      						   
						    </td>
					    </tr>				    
					     <tr height="15px">
					        <td width="60px">注册地址:</td>
						    <td>
								<input class="easyui-validatebox" type="text" name="enterprise.regAddr" data-options="required:true,validType:'length[0,50]'" style="width:250px">					      
						    </td>
						    <td width="60px">经营地址:</td>
						    <td>
								<input class="easyui-validatebox" type="text" name="enterprise.manageAddr" data-options="required:true,validType:'length[0,50]'" style="width:250px">					      
						    </td>
		
					    </tr>
					    <tr height="15px">
					  		<td width="60px">联通电话:</td>
						    <td>
						      <input class="easyui-validatebox" type="text" name="enterprise.tel" data-options="required:true,validType:'length[0,30]'" style="width:250px">
						    </td>
						    <td width="60px">手机号码:</td>
						    <td>
						      <input class="easyui-validatebox" type="text" name="enterprise.mobile" data-options="validType:'length[0,30]'" style="width:250px">
						    </td>
					    </tr>
					    <tr height="15px">
					    	<td width="60px">邮编:</td>
						    <td>
						      <input class="easyui-validatebox" type="text" name="enterprise.postCode" data-options="validType:'ZIP'" style="width:250px">
						    </td>
					        <td width="60px">电子邮箱:</td>
						    <td>
						      <input class="easyui-validatebox" type="text" name="enterprise.email" style="width:250px"  data-options="validType:'email'">
						    </td>
					    </tr>
					    <tr>
					    	<td width="60px">企业网址:</td>
						    <td colspan="3">
						      <input class="easyui-validatebox" type="text" name="enterprise.domName" data-options="validType:'length[0,60]'" style="width:600px">
						    </td>
					    </tr>
					    <!-- 
					    <tr height="15px">
					    	<td width="60px">电子邮箱:</td>
						    <td>
						      <input class="easyui-validatebox" type="text" name="enterprise.email" style="width:250px"  data-options="validType:'email'">
						    </td>
					  		<td width="60px">排序号:</td>
						    <td>
						      <input class="easyui-numberbox" id="enterprise_seq" type="text" name="enterprise.seq" value="3" style="width:250px">
						    </td>
					    </tr>
					    <tr height="15px">
					        <td width="60px">企业签名:</td>
						    <td colspan="3">
						      <input class="easyui-validatebox" type="text" name="enterprise.sign" data-options="validType:'length[0,50]'" style="width:600px">
						    </td>
					    </tr>
					     -->
					    <tr height="10px" id="tr_logo">
					    	<td width="60px">企业logo:</td>
					    	<td>
					    		<input type="file" name="fileQueue" id="uploadifyLogo" />
					    		<input type="hidden" name="enterprise.logoUrl" id="logoUrl" />
					    		
							</td>
						    <td colspan="2">
					    		<div id="fileQueueLogo" style="width: 300px"></div>
					   		</td>					   						
						</tr>							
						<tr height="15px">
					    	<td width="60px">简介:</td>
						    <td colspan="3">
					    	<textarea class="easyui-validatebox" name="enterprise.intro" rows="3" data-options="validType:'length[0,2000]'" style="width:600px"></textarea>
					   		</td>
					    </tr>	
					    
					    <tr height="10px">
					    	<td colspan="4">
					    		<div style="text-align:center;padding:15px">
								    <a href="javascript:void(0)" class="easyui-linkbutton" style="display: none" id="savB" iconcls="icon-save" onclick="submitForm()">保存</a>&nbsp;&nbsp;&nbsp;
								    <a href="javascript:void(0)" class="easyui-linkbutton" style="display: none" id="subA" iconcls="icon-ok" onclick="submitAud();">提交审核</a>
							   </div>					    		
					    	</td>
					    </tr>			    
				    </table>
			    </form>
	       </div>
	       
	       <div align="center" title="资质信息">
		       
				<table id="zizhiDataGrid"></table>  
	 
				  
				 <div id="tb_zizhi" style="height:auto">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendZizhi()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateZizhi()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeZizhi()">删除</a>
				 </div>
				 
				 <div id="h_value_zizhi">
				 	<input type="hidden" name="zizhi.id">
				 </div>
				 
			   	<div id="zizhiDlg" class="easyui-window" title="添加资质信息" data-options="iconCls:'icon-save',closed:true"  style="width:500px;height:300px;top: 10px">
					<div style="width: 100%">
				   		<form id="zizhiForm" method="post">
				   			<div id="in_value_zizhi"></div>
				   			<input type="hidden" id="zizhiEntId" name="zizhi.entId">				   			
				   			<table style="width: 450px;padding: 0px;" align="center">
				   				<tr>
				   					<td>证书名称:</td>
				   					<td>
				   					<input name="zizhi.zizhiName" class="easyui-validatebox"  data-options="required:true,validType:'length[0,100]'" style="width: 300px">
			   					  </td>   										
				   				</tr>
				   				   				
								<tr>
				   					<td>证书类型：</td>
				   					<td>
				   						<select id="appType" class="easyui-combobox" name="zizhi.appType" style="width:300px;">   
										    <option value=1>资质文件</option>   
										    <option value=2>营业执照</option>   
										    <option value=3>企业荣誉</option>   
										    <option value=4>其他证书</option>   
										    <option value=5>组织机构扫描件</option>  
										</select>
				   					</td>
				   				</tr>
				   				
				   				<tr>
				   					<td>授予单位:</td>
				   					<td>
				   					<input name="zizhi.grantUnit" class="easyui-validatebox" data-options="validType:'length[0,50]'"  style="width: 300px">
			   					  </td>   										
				   				</tr>
				   				
				   				<tr>
				   					<td>颁发单位:</td>
				   					<td>
				   					<input name="zizhi.awardUnit" class="easyui-validatebox" data-options="validType:'length[0,50]'"  style="width: 300px">
			   					  </td>   										
				   				</tr>
				   				
				   				<tr>
				   					<td>颁发时间:</td>
				   					<td>
				   					<input name="zizhi.awardTime" id="zizhi_awardTime" class="easyui-datebox"  data-options="required:true" style="width: 300px">
			   					  </td>   										
				   				</tr>
			
				   				<tr>
				   					<td>备注:</td>
				   					<td><textarea class="easyui-validatebox" name="zizhi.remark" data-options="validType:'length[0,500]'"  style="width: 300"></textarea></td>
				   				</tr>
				   				<tr>
				   					<td colspan="2" align="center" style="padding: 15">
				   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitZizhiForm()">提交</a>
				   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="$('#zizhiDlg').window('close')">取消</a>
				   					</td>
				   				</tr>
				   			</table>
				   		</form>
				   	</div>
				</div>
			
				<div id="attachWin" class="easyui-window" title="附件管理" data-options="iconCls:'icon-attach',closed:true" style="width: 500px; height: 400px; padding: 10px">
					<table>
						<tr>
							<td>
								附件:
							</td>
							<td>
								<div id="l_uploadify">
									<input type="file" name="uploadify" id="uploadify" />
								</div>
							</td>
						</tr>
							
							<tr>
								<td colspan="2">
									<div id="fileQueue" style="width: 400px"></div>
								</td>
							</tr>					
					</table>
				</div>       
	      </div>
	      
	       <div align="center" title="生产信息">
	       	 <table id="productionDataGrid"></table>  			  
			 <div id="tb_pro" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendProduction()">添加</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateProduction()">修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeProduction()">删除</a>
			 </div>
			 
			 <div id="h_value_pro">
			 	<input type="hidden" name="production.proId">
			 	<input type="hidden" name="production.userId">	 	
			 	<input type="hidden" name="production.crttime"> 	
			 </div>
			 
		   	<div id="productionDlg" class="easyui-window" title="添加生产信息" data-options="iconCls:'icon-save',closed:true"  style="width:520px;height:320px;top: 10px">
				<div style="width: 100%">
			   		<form id="productionForm" method="post">
			   			<input type="hidden" id="proEntId" name="production.entId">			   		
			   			<div id="in_value_pro"></div>
			   			<table style="width: 480px;padding: 0px;" align="center">
			   				<tr>
			   					<td>产品情况:</td>
			   					<td><textarea class="easyui-validatebox" name="production.productinfo" data-options="required:true,validType:'length[0,2000]'" rows="10" style="width: 400"></textarea></td>
			   				</tr>
		   				
			   				<tr>
			   					<td>生产许可证:</td>
			   					<td>
			   					<input name="production.license" class="easyui-validatebox" data-options="validType:'length[0,50]'"  style="width: 400px">
		   					  </td>   										
			   				</tr>
			   					   				
			   				<tr>
			   					<td colspan="2" align="center" style="padding: 15">
			   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitProForm()">提交</a>
			   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="$('#productionDlg').window('close')">取消</a>
			   					</td>
			   				</tr>
			   			</table>
			   		</form>
			   	</div>
			</div>
		   
		   
		   <div id="attachWin1" class="easyui-window" title="附件管理" data-options="iconCls:'icon-attach',closed:true" style="width: 500px; height: 420px; padding: 10px;top:20px">
				<table>
					<tr>
						<td>
							产品图片:
						</td>
						<td>
							<div id="l_uploadify">
								<input type="file" name="uploadify1" id="uploadify1" />
							</div>
						</td>
					</tr>
						
						<tr>
							<td colspan="2">
								<div id="fileQueue1" style="width: 400px;height: 200;OVERFLOW-Y: auto; OVERFLOW-X:hidden"></div>
							</td>
						</tr>		
						
						
						<tr>
						<td>
							生产许可证扫描件:
						</td>
						<td>
							<input type="file" name="uploadify2" id="uploadify2" />
						</td>
					</tr>
						
						<tr>
							<td colspan="2">
								<div id="fileQueue2" style="width: 400px"></div>
							</td>
						</tr>					
				</table>
			</div>
	       
	      </div>
	       
	       <div align="center" title="二维码信息">
	       		<div style="height:90px;width: 100%" align="center">
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
		         	<a href="javascript:void(0)" style="background-color: silver " class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="downloadCodeImg()">下  载</a>&nbsp;&nbsp;
		         	<a href="javascript:void(0)" style="background-color: silver " class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="downloadCodeImg2()">下载二维码图片</a>&nbsp;&nbsp;
					<a href="javascript:void(0)" style="background-color: silver " class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="downloadCodeImg3()">下载二维码高清大图</a>&nbsp;&nbsp;					
					<a href="javascript:void(0)" style="background-color: silver " class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" onclick="doPrint()">打  印</a>
		         </div>
	       </div>
	   		
	   		<div align="center" title="审核信息">
	   			<table id="auditRecordDataGrid"></table>
	       </div>
	</div>


	<!-- 显示企业 -->
	<div id="showEnt" class="easyui-window" data-options="iconCls:'icon-save',closed:true" style="width:720px;height:400px;padding:10px;top:10px">
		<div class="easyui-panel" style="width:680px">
	        <div align="center">		    				     		    	 
				 <table id="showTable">
						    
				 </table>
	       </div>
		   <div style="text-align:center;padding:15px">
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="$('#showEnt').window('close')">关 闭</a>
		   </div>
		</div>
	</div>
	<input type="hidden" id="hrentId" value="<%=obj.getEntId() %>"/>
  </body>
</html>
