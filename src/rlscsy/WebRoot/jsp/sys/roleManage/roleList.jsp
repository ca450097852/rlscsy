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
    <title>系统管理  - 角色列表</title>
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
	<script src="${basePath}static/js/hontek/sys/roleManager/sys_role.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	<style type="text/css">
		#option_col a{
			margin-top: 15px;
		}
	</style>
	
  </head>
  <body>   
    <table id="mydatagrid"> </table>
    <!-- mydatagrid 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="changeSts(1)">发布</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lock',plain:true" onclick="changeSts(2)">作废</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="empower()">授权</a>
		<div>
			&nbsp;&nbsp;角色名: <input class="easyui-validatebox" id="r_roleName" style="width:120px">
			&nbsp;状态: 
			<select id="r_sts">
				<option value="-1">--请选择--</option>
				<option value="0">待审</option>
				<option value="1">发布</option>
				<option value="2">作废</option>
			</select>
			&nbsp;
			所属机构：<input id="parentId" style="width:180px" >&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="tb_search()">搜索</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="tb_reset()">重置</a>
		</div>
	</div>
	
	
	<!-- 添加角色 -->
	<div id="add" class="easyui-window" data-options="modal:true,iconCls:'icon-role'" style="width:500px;top:10;height: 310px;">
		<div align="center">
		<form id="add_form" method="post">
			<table class="formtable" cellspacing="1" cellpadding="0">
			  <tr id="role_entid_tr">
			    <td class="form_label">所属机构：</td>
			    <td class="form_value">
			    	<input type="hidden" name="role.roleId" />
					<input name="role.entId" id="role_entid" style="width:250px;">
				</td>
			  </tr>
			  <tr>
			    <td class="form_label">角色名称：</td>
			    <td class="form_value"><input class="easyui-validatebox" validType="uniqueRoleNameInEnt['role_findIsUnique','role.account','该角色名已经存在此组织机构中']" type="text" name="role.roleName" data-options="required:true" style="width:250px;"/></td>
			  </tr>
			  <tr>
			    <td class="form_label">状态：</td>
			    <td class="form_value"><select id="rl_sts" name="role.sts" class="easyui-combobox"  style="width:250px;">
					<option value="0">待审</option>
					<option value="1">发布</option>
					<option value="2">作废</option>
				</select></td>
			  </tr>
			  <tr>
			    <td class="form_label">级别：</td>
			    <td class="form_value">
			    <select name="role.level" class="easyui-combobox" id="role_level" data-options="required:true" style="width:250px;">
					<option value="1">1级</option>
					<option value="2">2级</option>
					<option value="3">3级</option>
				</select>
			    </td>
			    
			  </tr>
			  <tr>
			    <td class="form_label">角色描述：</td>
			    <td class="form_value"><textarea name="role.desc" rows="5" style="width:250"></textarea></td>
			  </tr>
			</table>
		</form>
		</div>
		<div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" id="userlist_sub" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" id="userlist_update" class="easyui-linkbutton" iconCls="icon-save" onclick="updateRole()">更新</a>
	    	<a href="javascript:void(0)" id="userlist_reset" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm()">重置</a>
	    	<a href="javascript:void(0)" id="userlist_reset" class="easyui-linkbutton" iconCls="icon-no" onclick="$('#add').window('close')">关闭</a>
	    	
	    </div>
	</div>
	
	<!-- 栏目授权 -->
	<div id="rolePower" class="easyui-window" title="角色授权" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;top:50;height:450px">
	<div id="cc" class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',split:true" title="栏目菜单" style="width:250px;">
				<ul id="rol_col"></ul>
			</div>
			<div data-options="region:'center',title:'操作'" align="center" id="option_col">
				<a href="javascript:void(0)" id="userlist_sub" class="easyui-linkbutton" onclick="selectAll()">全选</a><br/>
		    	<a href="javascript:void(0)" id="userlist_update" class="easyui-linkbutton" onclick="RevSelect()">反选</a><br/>
		    	<a href="javascript:void(0)" id="userlist_reset" class="easyui-linkbutton" iconCls="icon-ok" onclick="passPower()">授权</a><br/>
		    	<a href="javascript:void(0)" id="userlist_reset" class="easyui-linkbutton" iconCls="icon-no" onclick="$('#rolePower').window('close')">关闭</a>
			</div>
		</div>
	</div>
  </body>
</html>
