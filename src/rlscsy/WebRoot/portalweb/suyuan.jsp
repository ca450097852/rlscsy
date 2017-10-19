<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-溯源查询</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/suyuan.css"/>

<script src="<%=basePath %>static/js/jquery-ui.min.js"　type="text/javascript"></script>
<link href="<%=basePath %>static/js/jquery-ui.css"　type="text/css" rel="stylesheet"></link>

<script type="text/javascript">
		$(function () {			
			 var datas = ["440111000076","440106001070","J075000763001141113","J075000754001141125","A002004661144011500393616"];
		     $("#num").autocomplete({
		     	source: datas
		     });	
		});

	//溯源查询数字输入
	function subnumber(param){
		var param = param;
		if(param >= 0){
		$('#num').val($('#num').val()+param);
		}else if(param == -2){
			$('#num').val('');
		}else {
			var arr = $('#num').val();
	   		var NewNum = arr.substring(0,arr.length-1);
	   		$('#num').val(NewNum);
		}
	}

	function sySreach(){
		var dimenno = $('#num').val();
		if(dimenno!=''){
			window.open("suyuan_entdetail.jsp?dimenno="+dimenno,"","");//接入企业溯源new
		}else{
			alert("请输入二维码!");
			$('#num').focus();
		}
	}

	//产品信息
	function checkForRedirect(dimenno){

		var proBatch = {};
		proBatch["proBatch.dimenno"]=dimenno;
		proBatch["proBatch.batchSts"]='1';
		
		$.ajax({
	   	    url:'web_ProBatch!findProBatchList.action',
	   	    data:proBatch,
	   	    type:'post',
	   	    async : false,
	   	    dataType:'json',
	   	    success : function(result) {
				if(parseInt(result.total)==0){
					window.open("suyuan_prodetail.jsp?dimenno="+dimenno,"","");//产品溯源
				}else{
					window.open("suyuan_detail.jsp?dimenno="+dimenno,"","");//产品溯源(hzly)
				}
		    }
	   }); 
		   
	}
</script>
		
</head>
  
<body>
<DIV>
<jsp:include page="head.jsp"><jsp:param value="sycx" name="navckeckId"/></jsp:include>
<DIV class="CENTER">
        <div class="CENTER_content">
        	
            <div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>溯源查询</font></div>
                <div class="suyuan_content">
                	<div class="suyuan_up">
                        <div class="con">
                    	<h3>请输入二维码</h3>
                        <div class="symSreach"><input id="num"  value=""/></div>
                        <div class="con_NO">
                            <i onclick="javascript:subnumber(1)">1</i>
                            <i onclick="javascript:subnumber(2)">2</i>
                            <i onclick="javascript:subnumber(3)">3</i>
                            <i class="color" onclick="javascript:subnumber(-2)">清除</i>
                            <i onclick="javascript:subnumber(4)">4</i>
                            <i onclick="javascript:subnumber(5)">5</i>
                            <i onclick="javascript:subnumber(6)">6</i>
                            <i class="color" onclick="javascript:subnumber(-1)">退格</i>
                            <i onclick="javascript:subnumber(7)">7</i>
                            <i onclick="javascript:subnumber(8)">8</i>
                            <i onclick="javascript:subnumber(9)">9</i>
                            <i onclick="javascript:subnumber(0)">0</i>
                            <div class="clear"></div>
                        </div>
                        <div class="btn btn_symSreach"><a href="javascript:void(0);" onclick="sySreach();">立刻查询</a></div>
                	</div>
                        <div class="suyuan_up_right">
                        	<div><img src="<%=basePath %>static/image/portalweb/erweima_img.jpg" /></div>
                            <div>手机扫描产品标签上的二维码图片，即可查看产品的溯源信息：如产品批次、生产信息、检测信息等。</div>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="suyuan_text">
民以食为天，食以安为先，全社会一直在关注这样一个问题：如何才能更加有效的规范和引导企业在生产过程中保障产品质量？如何让消费者购买的食物变得更加的安全可靠？二维码溯源系统是从产品的源头开始规范管理，由事后检测转变成事前预案、过程管控，包括产地检测、种苗处理、施肥、用药、采摘、运输、加工等环节，以提高产品质量。实现"生产有记录、过程留痕迹、质量安全保障"的农产品质量安全管理模式。
                    </div>
                </div>
            </div>
            
             
            <div class="CENTER_content_right">
            	

			<!-- 两个信息列表  -->
                <jsp:include page="news_info.jsp"></jsp:include>

            </div>
        </div>
        <div class="clear"></div>
    </DIV>
 <jsp:include page="bottom.jsp"></jsp:include>
 </DIV>
</body>
</html>
