<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String entId = request.getParameter("entId")==null?"":request.getParameter("entId");

%>

<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta content="text/html; charset=UTF-8" http-equiv="content-type">
	<meta content="text/javascript" http-equiv="Content-Script-Type">
	<meta content="text/css" http-equiv="Content-Style-Type">
	<style type="text/css">
		#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GP0T6CBjquSpz4pUgK8KFzyb"></script>
    <title></title>

</head>

<body>
	<div id="allmap"></div>
</body>

<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	//map.centerAndZoom("肇庆",12);                   // 初始化地图,设置城市和地图级别。
	var point = new BMap.Point(112.331398,23.297445);
	
	map.centerAndZoom(point,12);
	
	var mk = new BMap.Marker(point);
	map.addOverlay(mk);
	mk.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画			
	map.panTo(point);
	   
  	var opts = {title : '标题' };// 信息窗口标题					   	  			  		
	var infoWindow = new BMap.InfoWindow('111',opts);  // 创建信息窗口对象 
	map.openInfoWindow(infoWindow,point); //开启信息窗口
	
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
</script>
</html>
