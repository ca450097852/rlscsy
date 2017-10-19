/**
 * 企业溯源二维码扫描结果页
 * 
 */
$(function() {
	$("#qyxx").show();
	$("#zizhixx").hide();
	$("#scxx").hide();
	$("#sh_info1").hide();
	$("#sh_info2").hide();
	$("#yx_info").hide();
	
	var code = $('#code').val();
	$.ajax({
   	    url:'codeInter_findProductByCode.action',
   	    data:'code='+code,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(data) {
			for(var i=0;i<data.length;i++){
				var a = data[i];
				$("#cpxx_dimenno").html(a.dimenno);
				$("#cpxx_proName").html(a.proName);
				$("#cpxx_proNo").html(a.proNo);
				$("#cpxx_proType").html(a.typeName);
				$("#cpxx_sourceAddr").html(a.sourceAddr);
				$("#cpxx_manufacturer").html(a.manufacturer);
				$("#cpxx_distributor").html(a.distributor);
				$("#cpxx_distributorAddr").html(a.distributorAddr);
				$("#cpxx_proDesc").html(a.proDesc);
				//alert("审核："+a.rsts+"   有效："+a.sts);
				if(a.rsts==1){
					$("#sh_info1").show();
					$("#sh_info2").hide();
					$("#yx_info").hide();
				}else if(a.rsts==5){
					$("#sh_info2").show();
					$("#sh_info1").hide();
					$("#yx_info").hide();
				}else{
					$("#sh_info1").hide();
					$("#sh_info2").hide();
					$("#yx_info").show();
				}
				if(a.sts==1){
					$("#gq_info").html("<font color=red><b>本产品的检测报告已过期.</b></font>");
				}
				$("#saId").val(a.saId);
				
			}
	    }    
   }); 
});
// 企业信息
function check1(){
	$("#qyxx").show();
	$("#zizhixx").hide();
	$("#scxx").hide();
	$("#a1").attr("style", "color:#fff");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
}
// 资质信息
function check2(){
	$("#qyxx").hide();
	$("#zizhixx").show();
	$("#scxx").hide();
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#fff");
	$("#a3").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	
	var saId = $("#saId").val();
	var code = $('#code').val();
	$.ajax({
   	    url:'codeInter_findProcessBySaId.action',
   	    data:'code='+code+'&saId='+saId,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(data) {
			for(var i=0;i<data.length;i++){
				var a = data[i];
				$("#jgxx_headman").html(a.headman);
				$("#jgxx_processName").html(a.processName);
				$("#jgxx_processTime").html(a.processTime);
			}
	    }    
   }); 
}
// 生产信息
function check3(){
	$("#qyxx").hide();
	$("#zizhixx").hide();
	$("#scxx").show();
	
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#fff");
	
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/nav_bg.jpg')");
	var saId = $("#saId").val();
	var code = $('#code').val();
	$.ajax({
   	    url:'codeInter_findDeliveryBySaId.action',
   	    data:'code='+code+'&saId='+saId,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(data) {
			for(var i=0;i<data.length;i++){
				var a = data[i];
				$("#ysxx_startTime").html(a.startTime);
				$("#ysxx_consignCom").html(a.consignCom);
				$("#ysxx_driver").html(a.driver);
				$("#ysxx_vehicleNum").html(a.vehicleNum);
				$("#ysxx_startAddr").html(a.startAddr);
				$("#ysxx_endAddr").html(a.endAddr);
				$("#ysxx_remark").html(a.remark);
			}
	    }    
   }); 
}
