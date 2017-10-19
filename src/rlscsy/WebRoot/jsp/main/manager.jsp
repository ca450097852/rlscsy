<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hontek.sys.pojo.TsRolePurv" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	EntStyle entStyle = session.getAttribute("entStyle")==null?null:(EntStyle)session.getAttribute("entStyle");
	String logo = basePath + "static/images/loginlogo.png";
	String bottomInfo = "版权所有 广州薪火网络科技有限公司";
	if(entStyle!=null){
		if(entStyle.getLogoImage()!=null && !"".equals(entStyle.getLogoImage())){
			logo = "/nytsyFiles/entstyle/"+entStyle.getLogoImage();
		}
		if(entStyle.getBottomInfo()!=null && !"".equals(entStyle.getBottomInfo())){
			bottomInfo = entStyle.getBottomInfo();
		}
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<title><%=session.getAttribute("systemName") %></title>
    <link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/><!--
	<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" rel="stylesheet" type="text/css"/>-->
	<link href="<%=basePath %>static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>				
	<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>		
	<link href="${basePath}<%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	
  	<link href="<%=basePath %>static/css/style.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="<%=basePath %>static/js/menu.js"></script>
  
  </head>
  <style>
	  body {
		font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
		padding: 0;
		margin: 0;
	  }
  </style>   
  
  <body class="easyui-layout">
    <!-- 左边 - 菜单开始 -->
    <div data-options="region:'north',border:false" style="height:88px;background:url('<%=basePath %>static/images/topbg.gif') repeat-x;">
	   	<div class="topleft">
	    	<a href="#"><img src="<%=logo %>" title="系统首页" /></a>
	    </div>
        
	    <div class="topright">    
	    <ul>
	    	<li><span><img src="<%=basePath %>static/images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
	    	<li><a href="javascript:void(0)" onClick="logout()">退出系统</a></li>
	    </ul>
     
	    <div class="user">
		    <span>${loginUser.userName }</span>
		    <%--<i>消息</i>
		    <b>5</b>
		    --%>
	    </div>      
    </div>
    </div>
    
	<div data-options="region:'west',iconCls:'icon-application_cascade',split:true,title:'系统导航栏'" style="width:180px;background:#f0f9fd ;">
		<div id="dhl" class="easyui-accordion" data-options="fit:true,border:false"  align="center">
			<%
			List list = (List)request.getAttribute("cols");
			Map<String,Integer> map = (Map)request.getAttribute("mapConut");
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
					if("预警记录管理".equals(chd.getColName())&&map.get("warning")>0){
						%>
						<a style="padding:5px 10px 5px 10px" href="javascript:void(0);" src="<%=basePath %><%=chd.getColUrl() %>" id="<%=chd.getColId() %>" class="cs-navi-tab easyui-linkbutton" plain="true"><%=chd.getColName() %></a><font style="vertical-align: middle;" color='red'>(<%=map.get("warning")%>)</font><br/>
					<%
						
					}else if("投诉举报管理".equals(chd.getColName())&&map.get("complaint")>0){
						%>
						<a style="padding:5px 10px 5px 10px" href="javascript:void(0);" src="<%=basePath %><%=chd.getColUrl() %>" id="<%=chd.getColId() %>" class="cs-navi-tab easyui-linkbutton" plain="true"><%=chd.getColName() %></a><font style="vertical-align: middle;" color='red'>(<%=map.get("complaint")%>)</font><br/>
					<%
						
					}else{
				%>
					<a style="padding:5px 10px 5px 10px" href="javascript:void(0);" src="<%=basePath %><%=chd.getColUrl() %>" id="<%=chd.getColId() %>" class="cs-navi-tab easyui-linkbutton" plain="true"><%=chd.getColName() %></a><br/>
				<%
					}
				}
				%>
				</div>
				<%
			}
			 %>
						
			</div>
	  <iframe></iframe>
	</div>
                <%--<dl class="leftmenu">
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
							<dd>
							 <div class="title">
							 	<span>
							 		<img alt="" src="<%=basePath %>static/js/easyui-1.3.4/themes/icons/<%=imageName%>"></img><%=col.getColName()%>
							 	</span>
							 </div>
							 <ul class="menuson">
							<%
						}else{
							%>
							<dd>
							 <div class="title">
							 	<span>
							 		<%=col.getColName()%>
							 	</span>
							 </div>
							 <ul class="menuson">
							<% 
						}	
						for(int j=0;j<col.getClildrenList().size();j++){
							chd = col.getClildrenList().get(j);
						%>				
							<li><cite></cite><a href="javascript:void(0);" src="<%=basePath %><%=chd.getColUrl() %>" id="<%=chd.getColId() %>" class="cs-navi-tab"><%=chd.getColName() %></a><i></i></li>
						<%
						}
						%>
						</ul>
						</<dd>
						<%
					}
					%>
                </dl>	
	--%></div>
	<!-- 左边 - 菜单结束 -->
	
	<div data-options="region:'center'">
	     <div id="tabs" class="easyui-tabs"  fit="true" border="false" >			
			<div title="首页" style="padding: 0">            
				<%-- <div class="index_img" style="width: 100%"><img src="<%=basePath %>/static/image/comm/mamager_bg.jpg" style="width: 100%;height: 100%"/></div> --%>
				<iframe src="<%=basePath %>jsp/statistic/nearagency.jsp" width="100%" height="100%"></iframe>		
			</div>				
        </div>
	</div>		
	  
	<div data-options="region:'south',border:false" style="height:20px;padding:0px;text-align: center;color:#333;background: #f0f9fd">
		<%=bottomInfo %>
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

      	//导航切换
      	$(".menuson .header").click(function(){
      		var $parent = $(this).parent();
      		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
      		
      		$parent.addClass("active");
      		if(!!$(this).next('.sub-menus').size()){
      			if($parent.hasClass("open")){
      				$parent.removeClass("open").find('.sub-menus').hide();
      			}else{
      				$parent.addClass("open").find('.sub-menus').show();	
      			}
      			
      			
      		}
      	});
      	
      	// 三级菜单点击
      	$('.sub-menus li').click(function(e) {
              $(".sub-menus li.active").removeClass("active")
      		$(this).addClass("active");
          });
      	
      	$('.title').click(function(){
      		var $ul = $(this).next('ul');
      		$('dd').find('.menuson').slideUp();
      		if($ul.is(':visible')){
      			$(this).next('.menuson').slideUp();
      		}else{
      			$(this).next('.menuson').slideDown();
      		}
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
			
			var currTab = $('#tabs').tabs('getSelected');
			
			var index = $('#tabs').tabs('getTabIndex',currTab);
			if(index>=10){
				alert("温馨提示：您已经打开了超过10个窗口了!");
			}
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
</html>
