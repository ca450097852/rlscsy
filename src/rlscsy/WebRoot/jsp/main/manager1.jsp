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
	
  	<link href="<%=basePath %>static/css/menu.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="<%=basePath %>static/js/menu.js"></script>
  
  </head>
  <style>
	  body {
		font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
		padding: 0;
		margin: 0;
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
    <div data-options="region:'north',border:false" style="height:80px;background:url('<%=basePath %>/static/images/20140905_02.png') repeat-x;">
		<div style="height: 80px;background:url('<%=basePath %>/static/images/20140905top02.jpg') no-repeat;">    
	    	<div id="pf" align="right">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-house',plain:true">${enterprse.name }</a>&nbsp;&nbsp;	    		    		    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user',plain:true">${loginUser.userName }</a>&nbsp;&nbsp;	    		    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-exit',plain:true" onClick="logout()">退出系统</a>&nbsp;&nbsp;	    		    	
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
   		</div>
    </div>
	<div data-options="region:'west',iconCls:'icon-application_cascade',split:true,title:'系统导航栏'" style="width:180px;background-image:url('<%=basePath %>/static/images/left_bottombg.png');background-position:bottom;background-repeat:no-repeat;background-color:#9cead2;">
		<div  align="left">
			<div class="box">
                <ul class="menu" style="background:none;border:none;">
                	<%
					List list = (List)request.getAttribute("cols");
					TsRolePurv col = null;
					TsRolePurv chd = null;
					for(int i=0;i<list.size();i++){
						col = (TsRolePurv)list.get(i);	
						String icon = col.getIconUrl();
						if(icon!=null&&!"".equals(icon)){
							String imageName = icon.substring(5).concat(".png");
							%>
							 <li class="level1">
							 	<a href="javascript:void(0);" >
							 		<img alt="" src="<%=basePath %>static/js/easyui-1.3.4/themes/icons/<%=imageName%>"></img><%=col.getColName()%>
							 	</a>
							 <ul class="level2">
							<%
						}else{
							%>
							 <li class="level1"><a href="javascript:void(0);"><%=col.getColName() %></a>
							 <ul class="level2">
							<% 
						}	
						for(int j=0;j<col.getClildrenList().size();j++){
							chd = col.getClildrenList().get(j);
						%>						
							<li><a href="javascript:void(0);" src="<%=basePath %><%=chd.getColUrl() %>" id="<%=chd.getColId() %>" class="cs-navi-tab"><%=chd.getColName() %></a></li>
						<%
						}
						%>
						</ul>
						</li>
						<%
					}
					%>
                </ul>
                
			</div>
		</div>		
	</div>
	<!-- 左边 - 菜单结束 -->
	
	<div data-options="region:'center'">
	     <div id="tabs" class="easyui-tabs"  fit="true" border="false" >			
			<div title="首页" style="padding: 0">            
                <iframe scrolling="auto" frameborder="0"  src="<%=basePath %>echars/mapgd.jsp" style="width:100%;height:100%;"></iframe>
			</div>				
        </div>
	</div>		
	  
	<div data-options="region:'south',border:false" style="height:20px;padding:0px;text-align: center;background:#66ccbe;color:#333;">
		广东省农业厅版权所有	 技术支持:广州薪火网络科技有限公司
	</div>

	
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
