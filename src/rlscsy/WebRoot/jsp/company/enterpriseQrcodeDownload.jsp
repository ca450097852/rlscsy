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
	<script src="${basePath}static/js/hontek/company/enterpriseQrcodeDownload.js" type="text/javascript"></script>
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
	<div id="add" class="easyui-window" data-options="iconCls:'icon-house',closed:true,fit:true" >
		<div id="recordDiv">
			
		</div>
	</div>
	
	<div id="recordWin" class="easyui-window" title="查看二维码生成记录" data-options="iconCls:'icon-house',closed:true,fit:true"  style="width: 800px;height: 400px">
		<table id="recordTable"></table>
	</div>
	
	<div id="setQrcodeWin" class="easyui-window" title="二维码图片设置" data-options="iconCls:'icon-house',closed:true,modal:true" style="width:400px;height:200px;top:10px;">
		<form id="add_form" method="post" enctype="multipart/form-data">
	    	<input type="hidden" name="objId" id="_objId" value="">
		    <input type="hidden" name="typeId" id="_typeId" value=""> 
		    <input type="hidden" name="entId" id="_entId">
		    <table class="formtable" cellspacing="1" cellpadding="0">
		     	<tr height="40px">
			  		<td class="form_label" width="130px">生成二图码图片数量：</td>
				    <td class="form_value">
				    	<input class="easyui-numberbox" type="text" id="_count" name="count" data-options="required:true">
				    </td>
			    </tr>
			    <tr height="40px">
				    <td class="form_value" colspan="2">
				    	请设置要生成二维码图片的数量，单次生成不得超过5000张。
				    </td>
			    </tr>
			    <tr height="10px">
			    	<td class="form_value" colspan="2">
			    		<div style="text-align:center;padding:5px">
			    			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submintForm();">提交</a>
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="$('#setQrcodeWin').window('close')">关闭</a>
					   </div>					    		
			    	</td>
			    </tr>			    
		    </table>
	    </form>
	</div>

  </body>
  <script type="text/javascript">

 </script>
</html>
