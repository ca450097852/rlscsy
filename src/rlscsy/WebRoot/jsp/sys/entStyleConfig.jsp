<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String reqEntId = request.getParameter("entId");//主体管理设置风格
String entId = "";
try{
	//预防坑爹
	if(reqEntId!=null && !"".equals(reqEntId)){
		Integer.parseInt(reqEntId);
		entId = reqEntId;
	}else{
		String isAdmin = "";
		LoginUser luser = (LoginUser)session.getAttribute("loginUser");
		if(luser!=null){
				entId = luser.getEntId()==0?"":luser.getEntId()+"";
				isAdmin = luser.getAdmin()==null?"":luser.getAdmin();
		}
		if(isAdmin.equals("y")){
			out.print("您是超级管理员，无法使用该菜单!");
			return;
		}
	}
}catch(Exception e){
	out.print("entId参数错误");
	return;
}


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理  - 风格设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath %>static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="<%=basePath %>static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>	
	<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="<%=basePath %>static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/css/imgselect.css" media="screen" />
	
	
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/browser.js"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	
	<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
	<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
	
	
	<script src="<%=basePath %>static/js/hontek/sys/entStyleConfig.js" type="text/javascript"></script>
	<style type="text/css">
	.current {
	  background: #eaf2ff;
	  color: #000000;
	  border: 1px solid #b7d2ff;
	  -moz-border-radius: 5px 5px 5px 5px;
	  -webkit-border-radius: 5px 5px 5px 5px;
	  border-radius: 5px 5px 5px 5px;
	}
	#entStyle_tabs .tabs-wrap{
	margin-left:160px;
	}
	</style>       
  </head>
  <body style="padding: 0px" >
   
   <input type="hidden" id="basePath" value="<%=basePath %>"/>
   <input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 	
   <input type="hidden" id="hrentId" value="<%=entId %>"/>
  
  
  <div id="cc" class="easyui-layout" data-options="fit:true">  
	    <div data-options="region:'west',title:'风格类型',split:true,collapsible:false" style="width:150px;">
	    <a id="changeType1" style="padding:15px;width: 80%;" href="javascript:void(0);" class="cs-navi-tab easyui-linkbutton current" plain="true" onclick="changeType(1)">门户风格</a><br/>
	    <a id="changeType2" style="padding:15px;width: 80%;" href="javascript:void(0);" class="cs-navi-tab easyui-linkbutton" plain="true" onclick="changeType(2)">会员后台</a><br/>
	    <a id="changeType3" style="padding:15px;width: 80%;" href="javascript:void(0);" class="cs-navi-tab easyui-linkbutton" plain="true" onclick="changeType(3)">监管后台</a><br/>
	    </div>   
	    <div data-options="region:'center',title:'选择风格',collapsible:false" style="padding:5px;">
	    	
			<div id="p" class="easyui-panel"   style="width:100%;height:auto;padding:10px;background:#fafafa;"   data-options="collapsible:false,minimizable:true,maximizable:true,fit:true">   
			    <div style="width: 100%">
	    				
	    				<div style="width: 100%" id="aixuexi">
				   			<table style="padding: 0px;width: 100%;" align="left">
				   				<tr>
				   					<td align="right" width="150px">选择风格:</td>   											  	   					
				   					<td align="left"> 
				   						<ul class="imglist"  id="stylelist">
										    <!-- <li class="selected">
										    <span><img src="images/img01.png" width="170px" height="127px" title=""/></span>
										    <h2>软件界面设计下载</h2>
										    <p>
										    <input type="checkbox" class="scImg_checkbox" value="" checked="checked"/>
										    </p>
										    </li> -->
									    </ul>
				   					</td>
				   				</tr>
				   			</table>
				   		</div>
				   		
				   		
				   		<div style="width: 100%;display: none;" id="bannerHtml">
				   			<table style="padding: 0px;width: 100%;" align="left">
				   				
				   				<tr style="border-bottom: 1px;border-bottom-color: blue;">
				   					<td align="right" width="150px">banner广告:</td>   											  	   					
				   					<td align="left" width="150px"> 
				   						<input type="file" id="bannerFile"/>
				   					</td>
				   					<td><div id="bannerfileQueue"></div></td>
				   				</tr>
				   				
				   				<tr>
							        <td></td>
							        <td colspan="2" align="left"><font color="red">图片最佳规格为：1900*500像素(可上传多张图片)</font></td>
							    </tr>
				   				<tr>
				   					<td align="right" width="150px"></td>   											  	   					
				   					<td colspan="2" align="left"> 
				   						<ul class="imglist" id="bannerlist">
										    <!-- <li class="selected">
										    <span><img src="images/img01.png" width="170px" height="127px" title=""/></span>
										    <h2>软件界面设计下载</h2>
										    <p>
										    <input type="checkbox" class="scImg_checkbox" value="" checked="checked"/>
										    </p>
										    </li> -->
										    
										    
									    </ul>
				   					</td>
				   				</tr>
				   			</table>
				   			</div>
				   		
				   		
				   		<div style="width: 100%;display: none;" id="advertHtml">
				   			<table style="padding: 0px;width: 100%;" align="left">
				   				
				   				<tr style="border-bottom: 1px;border-bottom-color: blue;">
				   					<td align="right" width="150px">中部广告:</td>   											  	   					
				   					<td align="left" width="150px"> 
				   						<input type="file" id="advertFile"/>
				   					</td>
				   					<td><div id="advertfileQueue"></div></td>
				   				</tr>
				   				
				   				<tr>
							        <td></td>
							        <td colspan="2" align="left"><font color="red">图片最佳规格为：1900*400像素</font></td>
							    </tr>
				   				<tr>
				   					<td align="right" width="150px"></td>   											  	   					
				   					<td colspan="2" align="left"> 
				   						<ul class="imglist" id="advertlist">
										    <!-- <li class="selected">
										    <span><img src="images/img01.png" width="170px" height="127px" title=""/></span>
										    <h2>软件界面设计下载</h2>
										    <p>
										    <input type="checkbox" class="scImg_checkbox" value="" checked="checked"/>
										    </p>
										    </li> -->
									    </ul>
				   					</td>
				   				</tr>
				   			</table>
				   			</div>
				   			
				   			
				   		<div style="width: 100%">	
				   		<form id="interAccountForm" method="post">
				   			<input type="hidden" name="entStyle.esId" >
				   			<input type="hidden" name="entStyle.scId" >
				   			<input type="hidden" name="entStyle.entId" >
				   			<input type="hidden" name="entStyle.scType" >
				   			<input type="hidden" name="entStyle.logoImage">
				   			<input type="hidden" name="entStyle.userId" >
				   			<input type="hidden" name="entStyle.createTime" >
				   			
				   			
				   			<table style="width: 100%;padding: 0px;" align="left">
				   				<tr style="border-bottom: 1px;border-bottom-color: blue;">
				   					<td align="right" width="150px">头部LOGO:</td>   											  	   					
				   					<td align="left" width="150px"> 
				   						<input type="file" id="logoImageFile"/>
				   					</td>
				   					<td><div id="fileQueue"></div></td>
				   				</tr>
				   				
				   				<tr>
							        <td></td>
							        <td colspan="2" align="left"><font color="red">logo图片最佳规格为：410*70像素</font></td>
							    </tr>
							    
				   				<tr>
				   					<td align="right" width="150px">底部信息:</td>   											  	   					
				   					<td colspan="2" align="left"> <input name="entStyle.bottomInfo" style="width:550px;" class="easyui-validatebox"></td><!--  required="true" -->
				   				</tr>	
				   				
				   				<!-- <tr>
				   					<td align="right" width="150px">登录界面:</td>   											  	   					
				   					<td colspan="2" align="left"> <input name="entStyle.loginView" style="width:550px;" class="easyui-validatebox"></td>
				   				</tr>	 -->
				   							
				   				<tr>
				   					<td align="right">备注:</td>
				   					<td colspan="2" align="left"><textarea class="easyui-validatebox" name="entStyle.remark" data-options="validType:'length[0,500]'" rows="3" style="width: 550px"></textarea></td>
				   				</tr>		
				   				
				   			</table>
				   			
				   		</form>
				   		</div>
				   		
				   		
				   		
				   		<div style="width: 100%;">
				   			<table style="padding: 0px;width: 100%;" align="left">
				   				<tr>
				   					<td colspan="3" align="center" style="padding: 20">
				   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitForm()">保存</a>
				   					</td>
				   				</tr>
				   			</table>
				   			</div>
				   				
				   	</div>  
			</div> 
		
		
	    </div>
	    
	</div>  
	
	
	
	

	
  </body>
</html>
