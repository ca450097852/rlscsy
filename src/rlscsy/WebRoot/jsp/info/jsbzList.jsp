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
    <title>系统管理 - 信息列表</title>
    <META http-equiv="X-UA-Compatible" content="IE=8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
<!--	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>-->
	<script type="text/javascript" src="${basePath}static/js/xheditor-1.2.1/jquery/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/xheditor-1.2.1/xheditor-1.2.1.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/xheditor-1.2.1/xheditor_lang/zh-cn.js"></script>
	
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/info/jsbz.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
    <link rel="stylesheet" href="uploadify/uploadify.css" type="text/css"></link>
    
	<script type="text/javascript">
		$(function() {
			var infoQuery = {};
			infoQuery["info.typeId"]=12;
			
		$('#add').window('close');
		$('#seachStore').window('close');	
		$('#seachVendor').window('close');
		dataGrid = $('#list_info').datagrid( {
			url : 'info_findInfoList.action',
			queryParams: infoQuery,
			toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
			title : '技术标准管理',
			iconCls : 'icon-ok',
			loadMsg : '数据加载中...',
			pageSize : 10,// 默认选择的分页是每页10行数据
			pageList : [ 10, 20, 30, 50 ],// 可以选择的分页集合
			nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
			striped : true,// 设置为true将交替显示行背景。
			fit : true,
			pagination : true,
			rownumbers : true,
			remoteSort : false,
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			}] ],
			columns:[ [ 
			{field : 'typeName',title : '分类',width : 150,align : 'center'}, 
			{field : 'title',title : '标题',width : 250,align : 'center'},
			{field : 'nickName',title : '创建人',width : 200,align : 'center'}, 
			{field : 'crttime',title : '创建日期',width : 100,align : 'center'}, 
			{field : 'rsts',title : '状态',width : 100,align : 'center',
					formatter : function(value, row, index) {
						if (value == 0) {
							return "<font>待审</font>";
						} else if (value == 1) {
							return "<font color=green>通过</font>";
						} else if (value ==2) {
							return "<font color=red>未通过</font>";
						}
					}
			}
		     ] ],
			onHeaderContextMenu : function(e, field) {
				e.preventDefault();
				if (!cmenu) {
					createColumnMenu();
				}
				cmenu.menu('tb', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onLoadSuccess:function(data){
			 	f_timeout(data);
		 	}
		});
		
	 		parent.$.messager.progress('close');
		
      		parent.$.messager.progress('close');
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
			//加载信息分类
			$("#typeId1").combotree({
				url:'info_getInfoTypeTree.action',
				method:'get',
				panelHeight: 'auto'
				//onLoadSuccess:function(node, data){
				//	var t = $('#typeId').combotree('tree');      // 得到树对象 
				//	var n = t.tree('getChildren');          // 得到选择的节点
				//	if(n==0){
						//$(".enterprise_parentId_tr").remove();
				//	}else{
				//		f_timeout(data);
				//	}
				//}
			});
			//显示审批意见
			$('#sts_id').combobox({
				onChange : function(newValue, oldValue){
					if(newValue == 1 || newValue == 2){
						$("#opinionText").show();
					}else {
						$("#opinionText").hide();
					}
				}
			});
			
    	});
	</script>
  </head>
  <body>
  <!-- 信息列表 -->
   <table id="list_info">
   </table>  
    <!-- list_enttype 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="changeCheck(1)">通过</a>	
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="changeCheck(2)">未通过</a>
		<div>
				&nbsp;&nbsp;标题: <input class="easyui-validatebox" style="width:200px" id="title" name="title">
				&nbsp;&nbsp;信息分类: <input name="typeId" id="typeId1" style="width:200px;">
				&nbsp;&nbsp;状态: <select id="rsts" style="width:100px;">
							      	<option value='-1'>--请选择--</option>
							      	<option value='0'>待审</option>
							      	<option value='1'>通过</option>
							      	<option value='2'>未通过</option>
							      </select>
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="find()">搜 索</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
		</div>
	</div>
	<!-- 添加信息-->
	<div id="add" class="easyui-window" title="添加资讯" data-options="iconCls:'icon-save',resizable:false,shadow:false" style="width:890px;height:auto;padding:10px;top:10px">
		 <div class="easyui-panel" style="width:860px">
         <div align="center">
		    <form id="add_form" method="post" enctype="multipart/form-data">
		    		<input type="hidden" id="infoId" name="info.infoId"/>
   				    <input type="hidden" id="userId" name="info.userId"/>
   				    <input type="hidden" id="entId" name="info.entId"/>
   				    <input type="hidden" id="crttime" name="info.crttime"/>
   				    <input type="hidden" id="auditor" name="info.auditor"/>
   				    <input type="hidden" id="audiDate" name="info.audiDate"/>
   				    <input type="hidden" id="sysCode" name="info.sysCode"/>
				    <input type="hidden" id="oprerate"/>
				    
				<table class="formtable" cellspacing="1" cellpadding="0">
			    	<tr height="30px">
				        <td class="form_label">标题：</td>
					    <td class="form_value" colspan="3">
					      <input class="easyui-validatebox" type="text" name="info.title" data-options="required:true,validType:'length[0,50]'" style="width:95%" />
					    </td>
				    </tr>
			    	<tr height="30px">
					    <td class="form_label">信息分类：</td>
						<td class="form_value">
					      <input name="info.typeId" id="typeId" style="width:300px;" />
					    </td>
					    <td class="form_label" style="width:70px" align="right">排序号：</td>
					    <td class="form_value">
						   <input class="easyui-numberbox" type="text" name="info.seq" style="width:300px" value="5" />
					    </td>
				    </tr>
				    <tr height="50px">
					    <td class="form_label">内容：</td>
					    <td class="form_value" colspan="3">
					       <textarea  id="xh_editor" rows="10" class="easyui-validatebox"  style="width:98%" name="info.content" data-options="required:true"></textarea>
					    </td>
				    </tr>
				    <tr height="30px">
				        <td class="form_label">备注：</td>
					    <td class="form_value" colspan="3">
					      <textarea class="easyui-validatebox" rows="1" style="width:98%" id="remark" name="info.remark" data-options="resizable:false,validType:'length[0,250]'"></textarea>
					    </td>
				    </tr>
		    		<tr height="30px" style="display: none;" id="start">
		    			<td class="form_label">状态：</td>
		    			<td class="form_value" colspan="3">
		    				<select id="sts_id" class="easyui-combobox" name="info.rsts"  style="width:100px;">   
							    <option value='-1'>--请选择--</option>
						      	<option value='0'>待审</option>
						      	<option value='1'>通过</option>
						      	<option value='2'>未通过</option> 
							</select>  
		    			</td>
		    		</tr>
		    		<tr height="30px" style="display: none;" id="opinionText">
		    			<td class="form_label">审核意见：</td>
		    			<td class="form_value" colspan="3">
		    				<textarea class="easyui-validatebox" rows="1" style="width:98%" id="opinion" name="info.opinion" data-options="resizable:false,validType:'length[0,250]'"></textarea>
		    			</td>
		    		</tr>
			    	</table>
		    </form>
       </div>
	   <div style="text-align:center;padding:5px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="clearForm()">关 闭</a>
	   </div>
  </body>
</html>