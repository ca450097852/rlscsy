<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hontek.sys.pojo.TsRolePurv" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<title>广州市肉类蔬菜流通追溯管理平台</title>
    <link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/><!--
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" rel="stylesheet" type="text/css"/>-->
	<link href="<%=basePath %>static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>				
	<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>		
	<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
  	
  
  </head>
  <style>
	  body {
		font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
		padding: 0;
		margin: 0;
	  }
     .cs-navi-tab {
		padding: 5px;
	 }
  </style>   
  <script type="text/javascript">
     $(function(){
     	
     	tabCloseEven();
          $('.cs-navi-tab').click(function() {
				var $this = $(this);
				var href = $this.attr('src');
				var title = $this.text();
				var colId =	$this.attr('id');
				$.cookie('colId', colId, {
					expires : 1//cookie
				});
				addTab(title, href);
		  });		 
  		$('#dhl').accordion({
  	     	onSelect:function(title,index){
  	     		$('#dhl').accordion('getSelected').css('overflow','scroll');
  	     	} 
  		});	
     });
     function addTab(title, url){
		  if ($('#tabs').tabs('exists', title)){
				$('#tabs').tabs('select', title);//选中并刷新
				var currTab = $('#tabs').tabs('getSelected');
				var url = $(currTab.panel('options').content).attr('src');
				if(url != undefined && currTab.panel('options').title != '首页') {
					$('#tabs').tabs('update',{
						tab:currTab,
						options:{
							content:createFrame(url)
						}
					});
				}
		  } else {
				var content = createFrame(url);
				$('#tabs').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
		  }
	      tabClose();
    }
    function createFrame(url) {
		var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		return s;
    }
    
	function tabClose() {
		/*双击关闭TAB选项卡*/
		$(".tabs-inner").dblclick(function(){
			var subtitle = $(this).children(".tabs-closable").text();
			$('#tabs').tabs('close',subtitle);
		})
		/*为选项卡绑定右键*/
		$(".tabs-inner").bind('contextmenu',function(e){
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
	
			var subtitle =$(this).children(".tabs-closable").text();
	
			$('#mm').data("currtab",subtitle);
			$('#tabs').tabs('select',subtitle);
			return false;
		});
	}
	
	//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != '首页') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != '首页') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != '首页') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != '首页') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}	

	//更改皮肤	
	function changeTheme(themeName) {
		var $easyuiTheme = $('#easyuiTheme');
		var url = $easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
		$easyuiTheme.attr('href', href);
	
		var $iframe = $('iframe');
		if ($iframe.length > 0) {
			for ( var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
			}
		}	
		$.cookie('easyuiThemeName', themeName, {
			expires : 90//cookie
		});
	}

	function logout(){
		$.messager.confirm('提示', '你确定要退出?', function(r){
			if(r){
				window.location.href="<%=basePath %>user_logout.action";
			}
		});
	}

  </script>
  <body class="easyui-layout">
    <!-- 左边 - 菜单开始 -->
    <div data-options="region:'north',border:false" 
    style="height:80px;background:#B3DFDA;padding:10px;background:url('<%=basePath %>/static/image/comm/bg.jpg') 0px top;padding:0px;marggin:0px">
		<div style="background:url('<%=basePath %>/static/image/comm/01.jpg') no-repeat 0px top;height: 80px">    
	    	<div id="pf" align="right">
	    	欢迎您:<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user',plain:true">${loginUser.userName }</a>&nbsp;&nbsp;	    		    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit',plain:true" onclick="logout()">退出系统</a>&nbsp;&nbsp;	    		    	
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%--<a href="javascript:void(0);" onclick="window.location.href='download.action?fileName=UserHandBook.doc'">操作手册下载</a>
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	--%><!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	
	    		更换皮肤:<select id="pfSelect">
	    				<option>--请选择--</option>
	    				<option value="default" onclick="changeTheme('default')">default</option>
	    				<option value="gray" onclick="changeTheme('gray')">gray</option>
	    				<option value="black" onclick="changeTheme('black')">black</option>
	    				<option value="metro" onclick="changeTheme('metro')">metro</option>
	    				<option value="bootstrap" onclick="changeTheme('bootstrap')">bootstrap</option>
	    		</select>   		
	   		--></div>
   		</div>
    </div>
	<div data-options="region:'west',iconCls:'icon-application_cascade',split:true,title:'系统导航栏'" style="width:180px;">
		<div id="dhl" class="easyui-accordion" data-options="fit:true,border:false"  align="center">
			<%
			List list = (List)request.getAttribute("cols");
			TsRolePurv col = null;
			TsRolePurv chd = null;
			for(int i=0;i<list.size();i++){
				col = (TsRolePurv)list.get(i);
				String icon = col.getIconUrl();
				if(icon!=null&&!"".equals(icon)){
					%>			
					<div title="<%=col.getColName() %>" data-options="iconCls:'<%=icon %>'" style="padding:10px">
					<%
				}else{
					%>			
					<div title="<%=col.getColName() %>" style="padding:10px">
					<%
				}
				for(int j=0;j<col.getClildrenList().size();j++){
					chd = col.getClildrenList().get(j);
				%>
					<a href="javascript:void(0);" src="<%=basePath %><%=chd.getColUrl() %>" id="<%=chd.getColId() %>" class="cs-navi-tab easyui-linkbutton" plain="true"><%=chd.getColName() %></a><br/><br/>
				<%
				}
				%>
				</div>
				<%
			}
			 %>
			
			
			</div>
	  <iframe></iframe>
	</div>
	<!--<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>-->
	<div data-options="region:'south',border:false" style="height:20px;padding:0px;text-align: center">
		广州薪火网络科技有限公司  版权所有
	</div>
	<div data-options="region:'center'">
	     <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
				
			<div title="首页"  align="center">            
                <div class="easyui-layout" style="width:100%;height:100%">
		        	<div data-options="region:'east',split:true,title:'使用说明',iconCls:'icon-book'" style="width:300px">
		            
		       		</div>
		        	<div data-options="region:'center',title:'欢迎使用农产品溯源平台'"><%--
		        		<div class="index_img"><img src="<%=basePath %>/static/image/comm/mamager_bg.jpg" /></div>
		        	--%></div>
		    	</div>
			</div>
				
        </div>
	</div>
	<!-- 左边 - 菜单结束 -->
	
	<!-- 选项卡-右击事件 -->
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
  </body>
</html>
