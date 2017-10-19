<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理  - 用户列表</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
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
	function submitForm(){
        $('#update_form').form('submit', {
				url : 'user_updateOwnerUser.action',
				onSubmit : function(result) { 
				    return $(this).form('validate');
				},
				success : function(result) {
					
					$.messager.show({ title : '提示', msg : result });
				}
			});
			
     }

    
    function closeWin(){
    	var tab = parent.$('#tabs').tabs('getSelected');
    	var index = parent.$('#tabs').tabs('getTabIndex',tab);  	   
    	parent.$('#tabs').tabs('close',index);
    }
     
     $(function() {
      parent.$.messager.progress('close');
     });
	</script>
	
  </head>
  <body style="padding: 0">
     <div class="easyui-panel" title="修改用户信息" data-options="iconCls:'icon-user',fit:true,border:false">
      <form id="update_form"   method="post" >
		<table class="formtable" cellspacing="1" cellpadding="0">
		  <tr>
			<td class="form_label">登陆帐号：</td>
			<td class="form_value"><input class="easyui-validatebox" id="userName" style="width:250px" value="${loginUser.userName }"  readonly="readonly"></td>
			<td class="form_label">密码：</td>
			<td class="form_value"><input type="password" name="user.password" style="width:250px" class="easyui-validatebox" data-options="required:true" value="${loginUser.password }"/></td>
		  </tr>
		   <tr>
			<td class="form_label">昵称：</td>
			<td class="form_value"><input type="text" name="user.nickname" style="width:250px" value="${loginUser.nickname }"/></td>
			<td class="form_label">性别：</td>
			<td class="form_value">
				<input type="radio" name="user.sex" value="0" 
				<c:if test="${loginUser.sex == 0 }">
					checked="checked" 
				</c:if>
				/>男
				&nbsp;&nbsp;<input type="radio" value="1" name="user.sex"
				<c:if test="${loginUser.sex == 1 }">
					checked="checked" 
				</c:if>
				 />女
			</td>
		  </tr>
		   <tr>
		   	<td class="form_label">出生日期：</td>
			<td class="form_value"><input type="text" id="birthDate" name="user.birthDate" style="width:200px" class="easyui-datebox" value="${loginUser.birthDate }"/></td>
			<td class="form_label">年龄：</td>
			<td class="form_value"><input class="easyui-validatebox" validType="age" style="width:250px" type="text" name="user.age" value="${loginUser.age }"/></td>			
		  </tr>
		  <tr>
			<td class="form_label">固定电话：</td>
			<td class="form_value"><input class="easyui-validatebox" validType="phone" type="text" style="width:250px" name="user.tel" value="${loginUser.tel }"/></td>
			<td class="form_label">手机号码：</td>
			<td class="form_value"><input class="easyui-validatebox" validType="mobile" type="text" style="width:250px" name="user.phone" value="${loginUser.phone }"/></td>
		  </tr>
		  <tr>
			<td class="form_label">传真号码：</td>
			<td class="form_value"><input class="easyui-numberbox" type="text" name="user.fax" style="width:250px" value="${loginUser.fax }"/></td>
			<td class="form_label">QQ号码：</td>
			<td class="form_value"><input class="easyui-numberbox" type="text" name="user.qqnum" style="width:250px" value="${loginUser.qqnum }" /></td>
		  </tr>
		  <tr>
			<td class="form_label">详细地址：</td>
			<td class="form_value"><input type="text" name="user.addr" style="width:99%" value="${loginUser.addr }"/></td>
			<td class="form_label">电子邮件：</td>
			<td class="form_value"><input type="text" name="user.email" class="easyui-validatebox" style="width:99%" data-options="validType:'email'" value="${loginUser.email }"/></td>			
		  </tr>
		  <tr>
			<td class="form_label">用户签名：</td>
			<td class="form_value" colspan="3"><input type="text" name="user.signature" style="width:99%" value="${loginUser.signature }" /></td>
		  </tr>
		  <tr>
			<td class="form_label">用户简介：</td>
			<td class="form_value" colspan="3"><textarea name="user.intrest" rows="5" style="width:99%" >${loginUser.intrest }</textarea></td>
		  </tr>
		  <tr>
			<td class="form_value" align="center" colspan="4">
				<a href="javascript:void(0)" style="margin: 10px" class="easyui-linkbutton" iconCls="icon-save" onclick="submitForm()">保存修改</a>
			</td>
		  </tr>
		</table>

	</form>
	
	</div>
  </body>
</html>
