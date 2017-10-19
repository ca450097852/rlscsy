<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.sys.pojo.TsEnterprise"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
TsEnterprise enterprise = null;
Object o = session.getAttribute("enterprse");
if(o!=null){
	enterprise = (TsEnterprise)o;
}else{
	out.print("您是超级管理员，无法使用该菜单!");
	return;
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理 - 机构修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	<script type="text/javascript">
		$(function() {

			var enterprise_flag = <%=enterprise.getFlag()%>

			$("#flag_id").combobox("setValue",enterprise_flag);
		
			
      		parent.$.messager.progress('close');
    	});


		  function closeWin(){
		    	var tab = parent.$('#tabs').tabs('getSelected');
		    	var index = parent.$('#tabs').tabs('getTabIndex',tab);  	   
		    	parent.$('#tabs').tabs('close',index);
		    }
			
		
    	function submitForm(){
			if($('#add_form').form('validate')==false){
				$.messager.alert('提示','请检查必填项是否已填写!','question');
				return;
			}   	    
		  
    		$('#add_form').form('submit', {
    			url : 'ent_updateEnterpriseInfo.action',
    			onSubmit : function(result) {
    				return $(this).form('validate');// 对数据进行格式化
    		},
    		success : function(result) {
    			$.messager.show( {
    				title : '提示',
    				msg : result
    			});
    		}
    		});
        }
	</script>
		<style type="text/css">
		td{
			border-color: #CCCCCC;
			border-style: dotted;
		    border-width: 0 1px 1px 0;
		    margin: 0;
		    padding: 0;
		}
	</style>
  </head>
  <body style="padding: 0">
		<!--修改组织机构 -->
        <div class="easyui-panel" title="修改机构信息" data-options="iconCls:'icon-enterprise',fit:true,border:false">
           <div style="width: 100%;">	
        
		    <form id="add_form" method="post">
		    	<input type="hidden" id="entId" name="enterprise.entId" value="${enterprse.entId}"/>	
		    	<input type="hidden" name="enterprise.parentId" id="parentId" value="${enterprse.parentId}">		    	  
		    	<input type="hidden" name="enterprise.account" value="${enterprse.account}"/>	
		    	<input type="hidden" name="enterprise.sts" value="${enterprse.sts}"/>	
		    	<input type="hidden" name="enterprise.entType" value="${enterprse.entType}"/>	
		    	<input type="hidden" name="enterprise.seq" value="${enterprse.seq}"/>	
	    	  	<input type="hidden" name="enterprise.flag" value="${enterprise.flag}"/>	
	    	  	<input type="hidden" name="enterprise.sysCode" value="${enterprise.sysCode}"> 	
	    	  	<input type="hidden" name="enterprise.crtDt" value="${enterprise.crtDt}"> 			     		    	 	    	  	  	    	    	  	    
	    	  			     		    	 	    	  	  	    	    	  	    
			    <table>
				    <tr height="30px">
				        <td>机构名称:</td>
					    <td>
					    	<input class="easyui-validatebox" type="text" name="enterprise.name" value="${enterprse.name}" data-options="required:true" style="width:300px">
					    </td>
					    <td>简称:</td>
					    <td>
					      <input class="easyui-validatebox" type="text" name="enterprise.simpleName" value="${enterprse.simpleName}" style="width:300px">
					    </td>
				    </tr>				    
				     <tr height="30px">
				        <td>联系地址:</td>
					    <td>
							<input class="easyui-validatebox" type="text" name="enterprise.regAddr" value="${enterprse.regAddr}" style="width:300px">					      
					   		<input type="hidden" name="enterprise.manageAddr" value="${enterprise.manageAddr}" >					      					   
					    </td>
					    <td>电子邮箱:</td>
					    <td>
					      <input class="easyui-validatebox" type="text" name="enterprise.email" style="width:300px" value="${enterprse.email}"  data-options="validType:'email'">
					    </td>
					    
				    </tr>
				    <tr height="30px">
				  		<td>联系电话:</td>
					    <td>
					      <input class="easyui-validatebox" type="text" name="enterprise.tel" value="${enterprse.tel}" style="width:300px">
					    </td>
				        <td>邮政编码:</td>
					    <td>
					      <input class="easyui-validatebox" type="text" name="enterprise.postCode" value="${enterprse.postCode}" style="width:300px">
					    </td>
				    </tr>
				    <tr height="30px">
				  		<td>网址:</td>
					    <td>
					      <input class="easyui-validatebox" type="text" name="enterprise.domName" value="${enterprse.domName}" style="width:300px">
					    </td>
				        <td>签名:</td>
					    <td>
					      <input class="easyui-validatebox" type="text" name="enterprise.sign" value="${enterprse.sign}" style="width:300px">
					    </td>
				    </tr>

					<tr height="30px">
				    	<td>简介:</td>
					    <td colspan="3">
				    		<textarea name="enterprise.intro" rows="5" style="width:100%">${enterprse.intro}</textarea>
				   		</td>
				    </tr>		
				    
				    
				    <tr height="30px">
					    <td colspan="4">
				    		<div style="text-align:center;padding:15px">
						    	<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
					  			<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-no" onclick="closeWin()">关闭</a>
					  		
					  		</div>
				   		</td>
				    </tr>					    	    
			    </table>
		    </form>    
  		</div>   
  	</div>
  </body>
</html>
