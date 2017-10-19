/**
 * 企业溯源二维码扫描结果页
 * 
 */
$(function() {
	/*$("#qyxx").show();
	$("#zizhixx").hide();
	$("#scxx").hide();
	$("#sh_info1").hide();
	$("#sh_info2").hide();
	$("#yx_info").hide();
	
	var code = $('#code').val();
	$.ajax({}); */
});
// 企业信息
function check1(){
	$("#d1").show();
	$("#d2").hide();
	$("#d3").hide();
	$("#d4").hide();
	$("#a1").attr("style", "color:#fff");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#000");
	$("#a4").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a4").css("background","url('static/image/code/xt_02.jpg')");
}
// 资质信息
function check2(){
	$("#d1").hide();
	$("#d2").show();
	$("#d3").hide();
	$("#d4").hide();
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#fff");
	$("#a3").attr("style", "color:#000");
	$("#a4").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a4").css("background","url('static/image/code/xt_02.jpg')");

}
// 生产信息
function check3(){
	$("#d1").hide();
	$("#d2").hide();
	$("#d3").show();
	$("#d4").hide();
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#fff");
	$("#a4").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a4").css("background","url('static/image/code/xt_02.jpg')"); 
}


//生产信息
function check4(){
	$("#d1").hide();
	$("#d2").hide();
	$("#d3").hide();
	$("#d4").show();
	
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#000");
	$("#a4").attr("style", "color:#fff");
	
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a4").css("background","url('static/image/code/nav_bg.jpg')");
}