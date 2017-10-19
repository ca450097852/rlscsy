<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.company.pojo.Company"%>
<%@page import="com.hontek.company.pojo.CompanyUser"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String entId = request.getParameter("entId")==null?"":request.getParameter("entId");
Object obj = session.getAttribute("loginCompany");
Object objUser = session.getAttribute("loginCompanyUser");
Company company = null;
if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}else{
	 company = (Company)obj;
	
}
CompanyUser tsUser = null;
if(objUser!=null){
	tsUser = (CompanyUser)objUser;
}


%>

<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta content="text/html; charset=UTF-8" http-equiv="content-type">
	<meta content="text/javascript" http-equiv="Content-Script-Type">
	<meta content="text/css" http-equiv="Content-Style-Type">
	<link rel="STYLESHEET" href="<%=basePath%>portal/css/ie.css" type="text/css">
	<link href="<%=basePath%>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath%>static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath%>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery.cookie.js"></script>
	<link href="<%=basePath%><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath%>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="<%=basePath%>static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>	
	<script src="<%=basePath%>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<style type="text/css">
		#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GP0T6CBjquSpz4pUgK8KFzyb"></script>		
    <title>农产品质量安全追溯平台</title>

	<style>
		.poi_wrapper{width:250px;float:left;font-size:12px;}
		.poi_filter{background:#fafcff;border-bottom:1px solid #cdcdcd;line-height:20px;text-align:left;padding: 1px 10px}
		.poilist{padding:5px 9px 0;}
		.base_item{padding:6px 0;border-bottom:1px dashed #ccc;}
		.div_col_l{float:left;margin-right:10px;}
		.div_col_r{float:right;}
		.clear{clear:both;}
		.img_wrap{width:58px;height:71px;overflow:hidden;}
		.div_name{color:#00c;font-weight: bold;}
	</style>

</head>

<body style="padding: 0">

<input type="hidden" id="comName" value="<%=company.getName() %>"/>
<input type="hidden" id="comAddr" value="<%=company.getAddr() %>"/>
<input type="hidden" id="comTel" value="<%=company.getPhone() %>"/>


        <div id="allmap"></div>
</body>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	//map.centerAndZoom("广州",12);                   // 初始化地图,设置城市和地图级别。
	//var point = new BMap.Point(116.331398,39.897445);
	//map.centerAndZoom(point,12);
	//var lng1 = 111.8;
	//var lat1 = 23;	
	$.ajaxSetup({
  async: false
  });
	  	$(function() {	
					map.clearOverlays();								
					var agName = $('#comName').val();
						var agAddr = $('#comAddr').val();
						var agTel = $('#comTel').val();
						setTimeout(addMarker(agName,agAddr,agTel,""),200);
			
	  	});
	      
	  	function addMarker(agName,agAddr,agTel,seqnum){
			if(agAddr){
				// 将地址解析结果显示在地图上,并调整地图视野
				var myGeo = new BMap.Geocoder();						
				myGeo.getPoint(agAddr, function(point){							
					if (point) {
							map.centerAndZoom(point,12);
						var content = '<div style="font-size:12px;"><p>地址：'+agAddr+'</p><p>电话：'+agTel+'</p></div>';								
						//map.addOverlay(new BMap.Marker(point));								
						var marker = new BMap.Marker(point);  // 创建标注
						var label = new BMap.Label(seqnum,{offset:new BMap.Size(4,2)});																			
						label.setStyle({ border : "0",color:"#ffffff" ,backgroundColor : "transparent" }) ;
	  			  		marker.setLabel(label);
	  			  		marker.setTitle(agName);
	  			  		map.addOverlay(marker);    			  		
	  			  		var opts = {title : agName };// 信息窗口标题					   	  			  		
	  			  		addClickHandler(content,opts,marker);
					}
				} ,"广州");
				
				setTimeout(100);

		}
	  		
	  	}
	      
/* 			$.ajax( {
			url : "portal_Company_findList.action?page=1&rows=20",
			async : true,
			type : 'post',
			dataType : 'json',
			success : function(result) {												
				for ( var i = 0; i < result.rows.length; i++) {
					var agency = result.rows[i];
					var lng =lng1+Math.random() ;
					var lat =lat1+Math.random() ;		
					var agName = agency.name;
					var agAddr = agency.regAddr;
					var agTel = agency.tel;

					var seqnum = (i+1);
					var content = '<div style="font-size:12px;"><p>地址：'+agAddr+'</p><p>电话：'+agTel+'</p></div>';

					var marker = new BMap.Marker(new BMap.Point(lng, lat));  // 创建标注
					var label = new BMap.Label(seqnum,{offset:new BMap.Size(4,2)});																			
					label.setStyle({ border : "0",color:"#ffffff" ,backgroundColor : "transparent" }) ;
  			  		marker.setLabel(label);
  			  		marker.setTitle(agName);
  			  		map.addOverlay(marker);    			  		
  			  		var opts = {title : agName };// 信息窗口标题					   	  			  		
  			  		addClickHandler(content,opts,marker);
  			  		
				}
			}
		}); */
	
	
		   
	function addClickHandler(content,opts,marker){
		marker.addEventListener("click",function(e){
			openInfo(content,opts,e);
			}
		);
	}
	
	function openInfo(content,opts,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
	
	//关于状态码
	//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
	//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
	//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
	//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
	//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
	//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
	//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
	//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
	//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
	
	function panToMap(lng,lat){
		map.panTo(new BMap.Point(lng, lat));
	}
	
	
</script>


</html>