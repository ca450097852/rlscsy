<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hontek.comm.base.LoginUser" %>
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
	<link href="${basePath}<%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/sys/userManage/sys_user.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
  </head>
  <body>
    <input type="hidden" id="isAdmin" value="<%=((LoginUser)session.getAttribute("loginUser")).getAdmin() %>"/>
    <input type="hidden" id="h_noticeUrl"/>
     <table id="mydatagrid">  
    </table>
      
    <!-- mydatagrid 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>		
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="changeSts(0)">启用</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lock',plain:true" onclick="changeSts(1)">禁用</a>		
		<div>
			&nbsp;&nbsp;用户名: <input class="easyui-validatebox" id="userName" style="width:150px">
			&nbsp;
			所属机构：<input id="parentId" style="width:150px" >&nbsp;
			&nbsp;用户类型: 
			<select class="easyui-combobox" id="u_flag">
				<option value=''>--请选择--</option>
				<option value='1'>系统用户</option>
				<option value='2'>企业用户</option>
			</select>
			&nbsp;使用状态: 
			<select id="u_sts">
				<option value="-1">--请选择--</option>
				<option value="0">使用</option>
				<option value="1">禁用</option>
			</select>
			&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="tb_search()">搜索</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="tb_reset()">重置</a>
		</div>
	</div>
	
	<!-- 添加用户 -->
	<div id="add" class="easyui-window" title="添加用户" style="padding:10px;width:600px;top:10px">
		<div class="easyui-panel" style="width:560px">
		<form id="add_form" method="post">
			<table class="formtable" cellspacing="1" cellpadding="0">
				<tr>
					<td class="form_label">组织机构：</td>
					<td class="form_value">
						<input name="user.entId" id="user_entid" style="width:200px;">
					</td>
					<td class="form_label">登陆帐号：</td>
					<td class="form_value">
						<input type="text" name="user.userName" class="easyui-validatebox" data-options="required:true"
						validType="uniqueUserNameInEnt['user_findIsUniqueAccount','account','该用户名已经存在此机构中']"  style="width:200"/>
						<input type="hidden" name="user.userId" value=""/>
						<input type="hidden" name="user.regDate" value=""/>
						<input type="hidden" id="method"/>
						
					</td>
				</tr>
				<tr>
					<td class="form_label">密码：</td>
					<td class="form_value"><input type="password" name="user.password" class="easyui-validatebox" data-options="required:true" style="width:200"/></td>
					<td class="form_label">昵称：</td>
					<td class="form_value"><input type="text" name="user.nickname"  style="width:200" style="width:200"/></td>
				</tr>
				<tr>
				   
				    <td class="form_label">用户类型：</td>
					<td class="form_value">
						<select id="user_flag" name="user.flag" class="easyui-combobox" style="width:200">
							<option value='1'>系统用户</option>
							<option value='2'>企业用户</option>
						</select>
					</td>
					<td class="form_label">状态：</td>
					<td class="form_value">
						<select id="user_se_sts" name="user.sts" class="easyui-combobox" style="width:200">
							<option value=0>使用</option>
							<option value=1>禁用</option>
						</select>
					</td>
				  </tr>
				<tr>
					<td class="form_label">性别：</td>
					<td class="form_value">
					<input type="radio" name="user.sex" value="0" checked="checked" />男&nbsp;&nbsp;<input type="radio" value="1" name="user.sex" />女
					</td>
					<td class="form_label">年龄：</td>
					<td class="form_value"><input class="easyui-numberbox" type="text" name="user.age"  style="width:200"/></td>
				</tr>				
				<tr>
					<td class="form_label">出生日期：</td>
					<td class="form_value"><input type="text" id="birthDate" name="user.birthDate" class="easyui-datebox" style="width:200"/>
					</td>
					<td class="form_label">电子邮件：</td>
					<td class="form_value"><input type="text" name="user.email" class="easyui-validatebox" data-options="validType:'email'" style="width:200"/></td>
				</tr>
				<tr>
					<td class="form_label">QQ号码：</td>
					<td class="form_value"><input class="easyui-numberbox" type="text" name="user.qqnum" style="width:200" /></td>
					<td class="form_label">固定电话：</td>
					<td class="form_value"><input class="easyui-numberbox" type="text" name="user.tel" style="width:200" /></td>
				</tr>
				<tr>
					<td class="form_label">传真号码：</td>
					<td class="form_value"><input class="easyui-numberbox" type="text" name="user.fax" style="width:200" /></td>
					<td class="form_label">手机：</td>
					<td class="form_value"><input class="easyui-numberbox" type="text" name="user.phone" style="width:200" /></td>
				</tr>
				<tr>
					<td class="form_label">详细地址：</td>
					<td class="form_value" colspan="3"><input type="text" name="user.addr" style="width:100%"/></td>
				</tr>
				<tr>					
					<td class="form_value">用户签名：</td>
					<td class="form_value"  colspan="3"><input type="text" name="user.signature" style="width:100%" /></td>
				</tr>
				<tr>
				    <td class="form_label">用户简介：</td>
				    <td class="form_value" colspan="3">
				    	<textarea name="user.intrest"  style="width:100%"></textarea>
				    </td>
				  </tr>
			</table>
		</form>
		</div>
		<div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" id="userlist_sub" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" id="userlist_no" class="easyui-linkbutton" iconCls="icon-no" onclick="closeWin()">关闭</a>
	    </div>
	</div>
	
	<div id="assignRoleWindow" class="easyui-window" title="用户角色设置" style="width:550px;top:0;height:440px;">
		<div id="assignRoleLayout" class="easyui-layout" style="width:520px;height:400px;">   		   
		   <div data-options="region:'west',iconCls:'icon-group',title:'未分配角色',split:true" style="width:210px;">
		    	<select multiple id="wait" style="width:200px;height:360px;">
		    		
		    	</select>
		    </div>   
		    
		    <div data-options="region:'east',iconCls:'icon-group',title:'已分配角色',split:true" style="width:210px;">
		    	<select multiple id="had" style="width:200px;height:360px;">
		    			
		    	</select>
		    </div>   
		    
		    <div data-options="region:'center',title:'操作'" style="background:#eee;" align="center">
		    		<div style="height: 50px;"></div>
		    		<div style="height: 50px;">
		    			<a href="javascript:void(0)" id="addRole" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
		    		</div>
		    		
		    		<div style="height: 50px;">
		    			<a href="javascript:void(0)" id="removeRole" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">移除</a>
		    		</div>
					
					<div style="height: 50px;">
						<a href="javascript:void(0)" id="saveRole" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>	
					</div>
						
					<div style="height: 50px;">
						<a href="javascript:void(0)" id="closeRoleWindow" class="easyui-linkbutton" data-options="iconCls:'icon-no'">关闭</a>	
					</div>
		    </div>   
		</div>  
	</div>
	
  </body>
</html>
