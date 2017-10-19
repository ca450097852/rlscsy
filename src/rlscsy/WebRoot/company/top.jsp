<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@page import="com.hontek.company.pojo.Company"%>
<%@page import="com.hontek.company.pojo.CompanyUser"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object objUser = session.getAttribute("loginCompanyUser");
CompanyUser companyUser = null;
if(objUser!=null){
	companyUser = (CompanyUser)objUser;
}

String ischarge = "2";//是否收费用户，1是；2否
Object obj = session.getAttribute("loginCompany");
Company company = null;
if(obj!=null){
	company = (Company)obj;
}


EntStyle companyStyle = session.getAttribute("companyStyle")==null?null:(EntStyle)session.getAttribute("companyStyle");
String logo = basePath + "company/images/logo.png";
if(companyStyle!=null){
	if(companyStyle.getLogoImage()!=null && !"".equals(companyStyle.getLogoImage())){
		logo = "/nytsyFiles/entstyle/"+companyStyle.getLogoImage();
	}
	
}

String showCode = "1";
Object scObj = session.getAttribute("showCode");
if(scObj!=null && !"".equals(scObj))
	showCode = (String)scObj;

String auditPro = "1";
scObj = session.getAttribute("auditPro");
if(scObj!=null && !"".equals(scObj))
	auditPro = (String)scObj;

String auditBatch = "1";
scObj = session.getAttribute("auditBatch");
if(scObj!=null && !"".equals(scObj))
	auditBatch = (String)scObj;

%>
<script type="text/javascript">

$(function(){
	
	init();
	 
});


 function logout(){
	window.location.href="complogout.action";
	}
	
	function init(){
	
	$.post('webcompany_getLoginCompany.action?tt='+Math.floor(Math.random()*20)+1,'',function(result){
		if(result){
			var company = result[0];
			if(company.comLogo){
				
				$("#logoImg").attr("src","/nytsyFiles/company/"+company.comLogo);
				
			}else{
				
				$("#logoImg").attr("src","images/user5.png");

			}
			
		}
		
	},'JSON');
	
	
	
}
</script>

<header>
			<a href="#" class="logo">
				<img  src="images/logo.png" alt="Gold International">
			</a>
			<div class="pull-right">
				<ul id="mini-nav" class="clearfix">
					<li class="list-box">
						<a href="#">
							<i class="fa fa-user text-warning"></i> <span class="text-white"><%=companyUser.getRealName() %></span>
						</a>
					</li>
					<li class="list-box user-profile">
						<a id="drop7" href="#" role="button" class="dropdown-toggle user-avtar" data-toggle="dropdown">
							<img id="logoImg" src="" alt="zm9999">
						</a>
						<ul class="dropdown-menu server-activity">
							<li>
								<p class="center-align-text">欢迎您, <b><%=companyUser.getRealName() %></b> !</p>
							</li>
							<li>
								<a href="xgmm.jsp"  target="rightFrame" style="color: black;"><p><i class="fa fa-lock text-info"></i><span>修改密码</span></p></a>
							</li>
							<li>
								<div class="demo-btn-group  text-center clearfix">
									<button class="btn btn-danger btn-sm" onclick="logout()">安全退出</button>
								</div>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</header>

<%-- 

<<<<<<< .mine

=======
		layer.msg('页面加载中', {icon: 16,time: 20000});
		
		if(href=='qyxx.jsp'){
			$('.lefttop').html('<span></span>企业信息');
					
			$('#left').show();
			$('#right').css({'margin-left':'187px'});
			var content='<dd>\
		    	<ul class="menuson">\
	    		<li><cite></cite><a href="qyxx.jsp" target="rightFrame">基本信息</a><i></i></li>\
		        <li><cite></cite><a href="zzxx.jsp" target="rightFrame">资质证书</a><i></i></li>\
		        <li><cite></cite><a href="qykh.jsp" target="rightFrame">企业考核</a><i></i></li>\<li><cite></cite>
		        <a href="<%=basePath %>/company/CompanyMarketChart.jsp" target="rightFrame">企业统计</a><i></i></li></ul>\
		        </dd>';
			if($('#comType').val()=='5'){
			content='<dd>\
		    	<ul class="menuson">\
	    		<li><cite></cite><a href="qyxx.jsp" target="rightFrame">基本信息</a><i></i></li>\
		        <li><cite></cite><a href="zzxx.jsp" target="rightFrame">资质证书</a><i></i></li>\
		        <li><cite></cite><a href="qykh.jsp" target="rightFrame">企业考核</a><i></i></li>\<li><cite></cite><a href="<%=basePath %>/company/CompanyMarketChart.jsp" target="rightFrame">企业统计</a><i></i></li>\
		        <li><cite></cite><a href="ttxflist.jsp" target="rightFrame">团体消费进货验收管理</a><i></i></li>\
		        </ul>\
		        </dd>';
			}
		        $('.leftmenu').html(content);
>>>>>>> .r238



<div class="head-background">

<<<<<<< .mine
=======
		        //<li><cite></cite><a href=\"proTypeBatch.jsp\" target=\"rightFrame\">批次管理</a><i></i></li></ul>\
		        $('.leftmenu').html(content);

			$('.menuson li').removeClass('active');
			$('.menuson li:first').addClass('active');

			$('.menuson li').bind('click',function(){
				$('.menuson li').removeClass('active');
				$(this).addClass('active');
				layer.msg('页面加载中', {icon: 16,time: 20000});
			});
			
		}else if(text=='标识管理'){

			$('#rightFrame').attr('src',"dimennoList.jsp");

			$('#left').hide();
			$('#right').css({'margin-left':'0'});
			
		}else if(text=='流通档案'){
			
			$('#rightFrame').attr('src',"proTypeBatch.jsp");

			$('#left').hide();
			$('#right').css({'margin-left':'0'});
			
			/* var count = 0 ;
			$.ajax({
			   type: "POST",
			   url: "proTypeQrcode_findTypeConut.action",
			   async: false,			   
			   success: function(data){
			      count = data ;
			   }
			});
			if(count>0){
				layer.msg('请先选择您的企业生产的产品种类，并填写种类信息和标注产地!');
				return;
			};
			
			$('.lefttop').html('<span></span>生产档案');
			$('#left').show();
			$('#right').css({'margin-left':'187px'}); */
			
			/* $.post('record_getElementsByEntId.action','entId=',function(result){
				if(result){
					$('.menuson').html('');

					var ent = result.ent;
					var type = result.type;

					
					if(ent.length!=0){
						var record = ent[0];//主体档案只有一个，如果有多个的话只取第一个
						var elements = record.elements;

						var content = '<dd ><div class="title">\
						    <span><img src="images/leftico02.png" /></span>基本信息\
						    </div>\
						    <ul class="menuson">';
						    
					    for(var i=0;i<elements.length;i++){
							var el = elements[i];
							var item = '<li><cite></cite><a href="'+el.elementUrl+'?recId='+record.recId+'" target="rightFrame">'+el.elementName+'</a><i></i></li>';
							content += item;
						}

						content +=  ' </ul></dd>';

						$('.leftmenu').html(content);
						
					}

					if(type){
						    
						for(var i=0;i<type.length;i++){
							var record = type[i];//主体档案只有一个，如果有多个的话只取第一个
							var elements = record.elements;
							var batch = record.batch;
							var isBatch = record.isBatch;
							var content = '<dd >\
								<div class="title">\
							    <span><img src="images/leftico02.png" /></span>'+record.typeName+'\
							    </div>\
							    <ul class="menuson">';
							if(isBatch==1){
								for(var j=0;j<batch.length;j++){
									var b = batch[j];
									content += '<li><div class="header">\
							            <cite></cite>\
							            <a href="javascript:void(0)">'+b.batchName+'</a>\
							            <i></i>\
							            </div>\
							            <ul class="sub-menus">';
						        		for(var ji=0;ji<elements.length;ji++){
											var el = elements[ji];
											content += '<li class="open" >\
												<a href="'+el.elementUrl+'?recId='+b.recId+'" target="rightFrame">'+el.elementName+'</a>\
									        		<i></i>\
									        	</li>';
										}
							        	
							        content += '</ul>\
							        </li>';
								}
							}else{
								
							    for(var j=0;j<elements.length;j++){
									var el = elements[j];
									content += '<li><div class="header">\
							            <cite></cite>\
							            <a href="'+el.elementUrl+'?recId='+record.recId+'" target="rightFrame">'+el.elementName+'</a>\
							            <i></i>\
							            </div></li>';
								}
							}

							content +=  ' </ul>\
										</dd>';
							$('.leftmenu').append(content);
						}
						
					}
					

					if(ent.length!=0){
						$('#rightFrame').attr('src',ent[0].elements[0].elementUrl+'?recId='+ent[0].recId);
					}
					$('.menuson li:first').addClass('active');
					$('.menuson li:first').addClass('open');
					$('.menuson li:first').find('.sub-menus').show();
					$('.menuson li:first').find('.sub-menus li:first').addClass('active');
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
				        layer.msg('页面加载中', {icon: 16,time: 20000});
				    });
				    

					$('.menuson li').bind('click',function(){
						$('.menuson li').removeClass('active');
						$(this).addClass('active');
						if($(this).find('.sub-menus').length==0){
							layer.msg('页面加载中', {icon: 16,time: 20000});
						}
						
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
				}
			},'JSON'); */

			
		}/*else if(href=="supervise.jsp"){
			$('.lefttop').html('<span></span>监管信息');
			
			$('#left').show();
			$('#right').css({'margin-left':'187px'});
			
			var content = '<dd>\
		    	<ul class="menuson">\
	    		<li><cite></cite><a href="supervise.jsp" target="rightFrame">监管列表</a><i></i></li>\
		        <li><cite></cite><a href="evaluation.jsp" target="rightFrame">信用评价</a><i></i></li>\
	    		<li><cite></cite><a href="supervise.jsp" target="rightFrame">监管信息</a><i></i></li>\
		        //<li><cite></cite><a href="evaluation.jsp" target="rightFrame">企业评价</a><i></i></li>\
		        </ul>\
		        </dd>';
		        $('.leftmenu').html(content);

			$('.menuson li').removeClass('active');
			$('.menuson li:first').addClass('active');

			$('.menuson li').bind('click',function(){
				$('.menuson li').removeClass('active');
				$(this).addClass('active');
				layer.msg('页面加载中', {icon: 16,time: 20000});
			});
		}*/else{
			$('#left').hide();
			$('#right').css({'margin-left':'0'});
		}
		
	})	
});	



</script>
<div class="head-background">
<input type="hidden" id="comType" value="<%=company.getComType()%>"/>
>>>>>>> .r238
    <div class="topleft head-background" >
    <img src="<%=logo %>" title="系统首页" />
    </div>
        
    <ul class="nav">
    <li><a href="qyxx.jsp" target="rightFrame" class="selected"><img src="images/icon01.png" /><h2>企业信息</h2></a></li>
    <li><a href="recordNew.jsp" target="rightFrame"><img src="images/prolist.png" /><h2>产品种类</h2></a></li>
    <%
    if("1".equals(showCode)){
    	%>
    	<!--<li><a href="javascript:void(0);" target="rightFrame"><img src="images/icon04.png"/><h2>标识管理</h2></a></li>-->
    	<%
    }
    %>
    <li><a href="javascript:void(0);" target="rightFrame"><img src="images/record.png"/><h2>流通档案</h2></a></li>
     <!--<li><a href="agriInventory.jsp" target="rightFrame"><img src="images/storehouse.png"/><h2>库存管理</h2></a></li>
     <li><a href="supervise.jsp" target="rightFrame"><img src="images/sup.png" /><h2>监管信息</h2></a></li> -->
    <li><a href="xgmm.jsp"  target="rightFrame"><img src="images/icon06.png" /><h2>修改密码</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
     
    <!-- <li><a href="../doc/userbook.doc">操作手册下载</a></li> -->
    <!--<li><a href="#">关于</a></li> -->
    <li><a href="<%=basePath %>complogout.action"  target="_parent">退出系统</a></li>
    </ul>
     
    <div class="user">
    <span><%=companyUser.getRealName() %></span>
    <!-- 
    <i>消息</i>
    <b>5</b>
     -->
    </div>    
    
    </div>
</div> --%>