var dataGrid;
var filePath = '';
var proId = '';
var typeClass = '';
var code = '';
$(function() {

	$(".TOP,.CENTER").hide();
	code = $('#code').val();	
	filePath = $('#filePath').val();
	//查询是否已经停产
/*	var r = $.ajax({
			url:'codeInter_findIsStop.action',
			data:'code='+code,
			async: false		
	}).responseText;
	if(r==true||r=='true'){
		$("#tc_info").show();
		return;
	}
	
	//查询检验报告是否过期
	var r = $.ajax({
   	    			url:'codeInter_findProductCheckIspastDue.action',
   	    			data:'code='+code,
   	    			async: false		
				}).responseText;
	if(r==false||r=='false'){
		$("#gq_info").show();
		return;
	}else{
		$(".TOP,.CENTER").show();
		$("#sh_info").show();
	}*/
	
	//portal_Product_findList
	
	
	$("#cpxx").hide();
	$("#cdxx").hide();
	$("#zyxx").hide();
	$("#sfwy").hide();
	$("#zbfy").hide();
	$("#jgtz").hide();
	$("#czys").hide();
	$("#jcbg").hide();
	
	$("#sh_info").hide();
	$("#yx_info").hide();
	
	/*
	 * 先查询批次看是否有批次信息
	 * 
	 */
	var proBatch = {};
	proBatch["proBatch.dimenno"]=code;
	proBatch["proBatch.batchSts"]='1';
	
	$.ajax({
   	    url:'web_ProBatch!findProBatchList.action',
   	    data:proBatch,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			var total = result.total;
			if(total>0){
				var data = result.rows[0];
				proId = data.proId;
				$("#proId").val(proId);
			}
	    }
   }); 
	//traceWeixin_findTrace
	if(proId==''){
		$.ajax({
	   	    url:'traceWeixin_findTrace.action',
	   	    data:'qrCode='+code,
	   	    type:'post',
	   	    async : false,
	   	    dataType:'json',
	   	    success : function(data) {
				var purl = data.purl;
				if(purl!=''){
					window.open(purl,"","");//产品溯源(hzly)
				}
		    }
	   }); 
	}else{
		$(".TOP,.CENTER").show();
		var product = {};
		product["product.proId"]=proId;
		
		//优先加载的产品信息
		$.ajax({
	   	    url:'portal_Product_findList.action',
	   	    data:product,
	   	    type:'post',
	   	    async : false,
	   	    dataType:'json',
	   	    success : function(result) {
				if(result.total!=0){
					var myprod = result.rows[0];
					var authentication = "";
					if(myprod.authentication=='1'){
						 authentication = "有机产品";
					}else if(myprod.authentication=='2'){
						 authentication = "绿色产品";
					}else if(myprod.authentication=='3'){
						 authentication = "无公害产品";
					}else if(myprod.authentication=='4'){
						 authentication = "广东名牌产品";
					}
					
					
					$("#cpxx_dimenno").html(code);
					$("#cpxx_proName").html(myprod.proName);
					$("#cpxx_sizeAttr").html(myprod.unit);
					$("#cpxx_proType").html(myprod.typeName);
					$("#cpxx_entName").html(myprod.entName);
					$("#cpxx_authentication").html(authentication);
					$("#cpxx_manufacturer").html(myprod.manufacturer);
					$("#cpxx_sourceTel").html(myprod.sourceTel);
					$("#cpxx_sourceAddr").html(myprod.sourceAddr);
					$("#cpxx_distributor").html(myprod.distributor);
					$("#cpxx_distributorTel").html(myprod.distributorTel);
					$("#cpxx_distributorAddr").html(myprod.distributorAddr);
					$("#cpxx_retain").html(myprod.retain);
					$("#cpxx_storageConditions").html(myprod.storageConditions);
					$("#cpxx_shelfLife").html(myprod.shelfLife);
					$("#cpxx_proDesc").html(myprod.proDesc);
					$("#cpxx_scrq").html(myprod.updateTime);
				
					typeClass = myprod.typeClass;
					
				}else{
					return;
				}
					
		    }
	   });
	}
});
// 产品信息
function check1(){
	$("#cpxx").show();
	$("#cdxx").hide();
	$("#zyxx").hide();
	$("#sfwy").hide();
	$("#zbfy").hide();
	$("#jgtz").hide();
	$("#czys").hide();
	$("#jcbg").hide();
	
	$("#a1").attr("style", "color:#fff");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#000");
	$("#a4").attr("style", "color:#000");
	$("#a5").attr("style", "color:#000");
	$("#a6").attr("style", "color:#000");
	$("#a7").attr("style", "color:#000");
	$("#a8").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a4").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a5").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a6").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a7").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a8").css("background","url('static/image/code/xt_02.jpg')");
}
// 产地信息
function check2(){
	$("#cpxx").hide();
	$("#cdxx").show();
	$("#zyxx").hide();
	$("#sfwy").hide();
	$("#zbfy").hide();
	$("#jgtz").hide();
	$("#czys").hide();
	$("#jcbg").hide();
	
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#fff");
	$("#a3").attr("style", "color:#000");
	$("#a4").attr("style", "color:#000");
	$("#a5").attr("style", "color:#000");
	$("#a6").attr("style", "color:#000");
	$("#a7").attr("style", "color:#000");
	$("#a8").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a4").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a5").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a6").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a7").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a8").css("background","url('static/image/code/xt_02.jpg')");
	
	
	proId = $('#proId').val();
	var proArea = {};
	proArea["proArea.proId"]=proId;
	
	$.ajax({
   	    url:'web_ProArea!findProAreaList.action',
   	    data:proArea,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
	    var div = document.getElementById("cdxx");
	    if(result.total==0){
	    	div.innerHTML = "<div align=\"center\">暂无数据！</div>";
	    }else{
	    	
	    
	    
	    var tp="";
		var ww="";  // 用来循环保存信息
		var imgStr="";   //用来循环保存图片的标题

		var width = document.body.clientWidth;
		document.body.clientHeight;
		width=width-50;
		var height =width-200;
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			ww="<dl><dt>产地名称:</dt><dd><span>"+a.areaName+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>产地地址:</dt><dd><span>"+a.areaAddr+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>产地概况:</dt><dd><span>"+a.areaIntro+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>产地环境:</dt><dd><span>"+a.edatope+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>产地水源:</dt><dd><span>"+a.areaWater+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>产地气候:</dt><dd><span>"+a.climatope+"</span></dd><div class=clear></div></dl>" +
				"<center>";
			
			var tbas=a.traceAppdixs;
			if(tbas.length>0){//存在图片
				for(var j=0;j<tbas.length;j++){
					var b=tbas[j];
						imgStr+="<span id=imgStr_img><a href='proshow.jsp?img="+b.appdixUrl+"' target=_blank><img src="+filePath+b.appdixUrl+"  width=\"100%\"></a></span>&nbsp;";
				}
			}
			
			if(imgStr&&imgStr!=''){
				imgStr="产地图片<br>"+imgStr;
			}
			ww+=imgStr+"<br>";
			imgStr="";//重新置空
			
			ww+='<br>&nbsp;</center>&nbsp;<br><div style="border-bottom:2px dashed #cccccc;height:10px;overflow:hidden"></div>';
			tp+=ww;
		}
		//tp+="<br><a href='codeInterjgxxls.jsp?code="+code+"&saId="+saId+"'><B><font color=blue>加工历史信息>></font></B></a><br><br>";
		div.innerHTML=tp;
	    }
	}
   }); 
}
//种源信息
function check3(){
	$("#cpxx").hide();
	$("#cdxx").hide();
	$("#zyxx").show();
	$("#sfwy").hide();
	$("#zbfy").hide();
	$("#jgtz").hide();
	$("#czys").hide();
	$("#jcbg").hide();
	
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#fff");
	$("#a4").attr("style", "color:#000");
	$("#a5").attr("style", "color:#000");
	$("#a6").attr("style", "color:#000");
	$("#a7").attr("style", "color:#000");
	$("#a8").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a4").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a5").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a6").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a7").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a8").css("background","url('static/image/code/xt_02.jpg')");
	
	proId = $('#proId').val();
	var proSeed = {};
	proSeed["proSeed.proId"]=proId;
	$.ajax({
   	    url:'web_ProSeed!findProSeedList.action',
   	    data:proSeed,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
	    var div = document.getElementById("zyxx");
	    if(result.total==0){
	    	div.innerHTML = "<div align=\"center\">暂无数据！</div>";
	    }else{
	    
	    var tp="";
		var ww="";  // 用来循环保存信息
		var imgStr="";   //用来循环保存图片的标题

		var width = document.body.clientWidth;
		document.body.clientHeight;
		width=width-50;
		var height =width-200;
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			ww="<dl>" +
				"<dt>种源名称:</dt><dd><span id=jgxx_headman>"+a.seedName+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>种源地址:</dt><dd><span id=jgxx_processName>"+a.seedAddr+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>种源厂家:</dt><dd><span id=ylly_addr>"+a.seedCompany+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>种源特性:</dt><dd><span id=jgxx_processName>"+a.feature+"</span></dd><div class=clear></div></dl>" +
				"<center>";
			
			var tbas=a.traceAppdixs;
			if(tbas.length>0){//存在图片
				for(var j=0;j<tbas.length;j++){
					var b=tbas[j];
						imgStr+="<span id=imgStr_img><a href='proshow.jsp?img="+b.appdixUrl+"' target=_blank><img src="+filePath+b.appdixUrl+"  width=\"100%\"></a></span>&nbsp;";
				}
			}
			
			if(imgStr&&imgStr!=''){
				imgStr="种源图片<br>"+imgStr;
			}
			ww+=imgStr+"<br>";
			imgStr="";//重新置空

			ww+='<br>&nbsp;</center>&nbsp;<br><div style="border-bottom:2px dashed #cccccc;height:10px;overflow:hidden"></div>';
			tp+=ww;
		}
		div.innerHTML=tp;
	    }
	}
   }); 
}
// 施肥喂养
function check4(){
	$("#cpxx").hide();
	$("#cdxx").hide();
	$("#zyxx").hide();
	$("#sfwy").show();
	$("#zbfy").hide();
	$("#jgtz").hide();
	$("#czys").hide();
	$("#jcbg").hide();
	
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#000");
	$("#a4").attr("style", "color:#fff");
	$("#a5").attr("style", "color:#000");
	$("#a6").attr("style", "color:#000");
	$("#a7").attr("style", "color:#000");
	$("#a8").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a4").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a5").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a6").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a7").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a8").css("background","url('static/image/code/xt_02.jpg')");
	
	proId = $('#proId').val();
	var plantRaise = {};
	plantRaise["plantRaise.proId"]=proId;
	$.ajax({
   	    url:'web_PlantRaise!findList.action',
   	    data:plantRaise,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
	    var div = document.getElementById("sfwy");
	    if(result.total==0){
	    	div.innerHTML = "<div align=\"center\">暂无数据！</div>";
	    }else{
	    
	    var tp="";
		var ww="";  // 用来循环保存信息
		var imgStr="";   //用来循环保存图片的标题

		var width = document.body.clientWidth;
		document.body.clientHeight;
		width=width-50;
		var height =width-200;
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			if(typeClass=='1'){
				//种植
				ww="<dl>" +
				"<dt>肥料名称:</dt><dd><span id=jgxx_headman>"+a.feedName+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>肥料厂家:</dt><dd><span id=jgxx_processName>"+a.feedCompany+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>施肥方法:</dt><dd><span id=ylly_addr>"+a.feedWay+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>施肥周期:</dt><dd><span id=jgxx_processName>"+a.feedCycle+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>施肥时间:</dt><dd><span id=jgxx_processName>"+a.feedTime+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>施肥用量:</dt><dd><span id=jgxx_processName>"+a.dosage+"</span></dd><div class=clear></div></dl>" +
				"<center>";
			}else{
				//养殖
				ww="<dl>" +
				"<dt>饲料名称:</dt><dd><span id=jgxx_headman>"+a.feedName+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>饲料厂家:</dt><dd><span id=jgxx_processName>"+a.feedCompany+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>喂养方法:</dt><dd><span id=ylly_addr>"+a.feedWay+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>喂养周期:</dt><dd><span id=jgxx_processName>"+a.feedCycle+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>喂养时间:</dt><dd><span id=jgxx_processName>"+a.feedTime+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>喂养用量:</dt><dd><span id=jgxx_processName>"+a.dosage+"</span></dd><div class=clear></div></dl>" +
				"<center>";
			}
			
			var tbas=a.traceAppdixs;
			if(tbas.length>0){//存在图片
				for(var j=0;j<tbas.length;j++){
					var b=tbas[j];
						imgStr+="<span id=imgStr_img><a href='proshow.jsp?img="+b.appdixUrl+"' target=_blank><img src="+filePath+b.appdixUrl+"  width=\"100%\"></a></span>&nbsp;";
				}
			}
			
			if(imgStr&&imgStr!=''){
				if(typeClass=='1'){
					imgStr="施肥图片<br>"+imgStr;
				}else{
					imgStr="喂养图片<br>"+imgStr;
				}
			}
			ww+=imgStr+"<br>";
			imgStr="";//重新置空

			ww+='<br>&nbsp;</center>&nbsp;<br><div style="border-bottom:2px dashed #cccccc;height:10px;overflow:hidden"></div>';
			tp+=ww;
		}
		div.innerHTML=tp;
	    }
	}
   }); 
}

// 防疫植保
function check5(){
	$("#cpxx").hide();
	$("#cdxx").hide();
	$("#zyxx").hide();
	$("#sfwy").hide();
	$("#zbfy").show();
	$("#jgtz").hide();
	$("#czys").hide();
	$("#jcbg").hide();
	
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#000");
	$("#a4").attr("style", "color:#000");
	$("#a5").attr("style", "color:#fff");
	$("#a6").attr("style", "color:#000");
	$("#a7").attr("style", "color:#000");
	$("#a8").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a4").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a5").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a6").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a7").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a8").css("background","url('static/image/code/xt_02.jpg')");
	
	proId = $('#proId').val();
	var prevention = {};
	prevention["prevention.proId"]=proId;
	$.ajax({
   	    url:'web_Prevention!findList.action',
   	    data:prevention,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
	    var div = document.getElementById("zbfy");
	    if(result.total==0){
	    	div.innerHTML = "<div align=\"center\">暂无数据！</div>";
	    }else{
	    
	    var tp="";
		var ww="";  // 用来循环保存信息
		var imgStr="";   //用来循环保存图片的标题

		var width = document.body.clientWidth;
		document.body.clientHeight;
		width=width-50;
		var height =width-200;
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			ww="" +
			"<dl><dt>药品名称:</dt><dd><span id=jgxx_headman>"+a.drugName+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>药品厂家:</dt><dd><span id=jgxx_processName>"+a.drugCompany+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>防治对象:</dt><dd><span id=ylly_addr>"+a.drugObject+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>用药方法:</dt><dd><span id=ylly_addr>"+a.drugWay+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>用药周期:</dt><dd><span id=jgxx_processName>"+a.drugCycle+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>用药时间:</dt><dd><span id=jgxx_processName>"+a.drugTime+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>用药用量:</dt><dd><span id=jgxx_processName>"+a.dosage+"</span></dd><div class=clear></div></dl>" +
			"<center>";
				
			var tbas=a.traceAppdixs;
			if(tbas.length>0){//存在图片
				for(var j=0;j<tbas.length;j++){
					var b=tbas[j];
					imgStr+="<span id=imgStr_img><a href='proshow.jsp?img="+b.appdixUrl+"' target=_blank><img src="+filePath+b.appdixUrl+"  width=\"100%\"></a></span>&nbsp;";
				}
			}
			
			if(imgStr&&imgStr!=''){
				if(typeClass=='1'){
					imgStr="植保图片<br>"+imgStr;
				}else{
					imgStr="防疫图片<br>"+imgStr;
				}
			}
			ww+=imgStr+"<br>";
			imgStr="";//重新置空

			ww+='<br>&nbsp;</center>&nbsp;<br><div style="border-bottom:2px dashed #cccccc;height:10px;overflow:hidden"></div>';
			tp+=ww;
		}
		div.innerHTML=tp;
	    }
	}
   }); 
}

// 加工屠宰
function check6(){
	$("#cpxx").hide();
	$("#cdxx").hide();
	$("#zyxx").hide();
	$("#sfwy").hide();
	$("#zbfy").hide();
	$("#jgtz").show();
	$("#czys").hide();
	$("#jcbg").hide();
	
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#000");
	$("#a4").attr("style", "color:#000");
	$("#a5").attr("style", "color:#000");
	$("#a6").attr("style", "color:#fff");
	$("#a7").attr("style", "color:#000");
	$("#a8").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a4").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a5").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a6").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a7").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a8").css("background","url('static/image/code/xt_02.jpg')");
	
	proId = $('#proId').val();
	var process = {};
	process["process.proId"]=proId;
	$.ajax({
	    url:'web_Process!findProcessList.action',
	    data:process,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
	    var div = document.getElementById("jgtz");
	    if(result.total==0){
	    	div.innerHTML = "<div align=\"center\">暂无数据！</div>";
	    }else{
	    var tp="";
		var ww="";  // 用来循环保存信息
		var imgStr="";   //用来循环保存图片的标题

		var width = document.body.clientWidth;
		document.body.clientHeight;
		width=width-50;
		var height =width-200;
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			if(typeClass=='1'){
				//种植
				ww="<dl>" +
				"<dt>加工负责人:</dt><dd><span id=jgxx_headman>"+a.processUser+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>加工厂家:</dt><dd><span id=jgxx_processName>"+a.processCompany+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>加工地址:</dt><dd><span id=ylly_addr>"+a.processAddr+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>加工时间:</dt><dd><span id=jgxx_processName>"+a.processTime+"</span></dd><div class=clear></div></dl>" +
				"<center>";
								
			}else{
				//养殖
				ww="<dl>" +
				"<dt>屠宰负责人:</dt><dd><span id=jgxx_headman>"+a.processUser+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>屠宰厂家:</dt><dd><span id=jgxx_processName>"+a.processCompany+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>屠宰地址:</dt><dd><span id=ylly_addr>"+a.processAddr+"</span></dd><div class=clear></div></dl>" +
				"<dl><dt>屠宰时间:</dt><dd><span id=jgxx_processName>"+a.processTime+"</span></dd><div class=clear></div></dl>" +
				"<center>";
			}
				
			var tbas=a.traceAppdixs;
			if(tbas.length>0){//存在图片
				for(var j=0;j<tbas.length;j++){
					var b=tbas[j];
						imgStr+="<span id=imgStr_img><a href='proshow.jsp?img="+b.appdixUrl+"' target=_blank><img src="+filePath+b.appdixUrl+"  width=\"100%\"></a></span>&nbsp;";
				}
			}
			
			if(imgStr&&imgStr!=''){
				if(typeClass=='1'){
					imgStr="加工图片<br>"+imgStr;
				}else{
					imgStr="屠宰图片<br>"+imgStr;
				}
			}
			ww+=imgStr+"<br>";
			imgStr="";//重新置空

			ww+='<br>&nbsp;</center>&nbsp;<br><div style="border-bottom:2px dashed #cccccc;height:10px;overflow:hidden"></div>';
			tp+=ww;
		}
		div.innerHTML=tp;
	    }
	}
   }); 
	/*
	var code = $('#code').val();
	$.ajax({
   	    url:'codeInter_findAppendixByCode.action',
   	    data:'code='+code,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(data) {
		    var tp="";
			for(var i=0;i<data.length;i++){
				var a = data[i];
				tp += "<a href='proshow.jsp?img="+a.path+"'  target=_blank><img src="+filePath+a.path+" width=300 height=200></a>";
			}
			$("#tacz_img").html(tp);
	    }    
   }); 
   */
}

// 仓储运输
function check7(){
	$("#cpxx").hide();
	$("#cdxx").hide();
	$("#zyxx").hide();
	$("#sfwy").hide();
	$("#zbfy").hide();
	$("#jgtz").hide();
	$("#czys").show();
	$("#jcbg").hide();
	
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#000");
	$("#a4").attr("style", "color:#000");
	$("#a5").attr("style", "color:#000");
	$("#a6").attr("style", "color:#000");
	$("#a7").attr("style", "color:#fff");
	$("#a8").attr("style", "color:#000");
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a4").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a5").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a6").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a7").css("background","url('static/image/code/nav_bg.jpg')");
	$("#c_a8").css("background","url('static/image/code/xt_02.jpg')");
	
	proId = $('#proId').val();
	var storeTransport = {};
	storeTransport["storeTransport.proId"]=proId;
	$.ajax({
	    url:'web_StoreTransport!findStoreTransportList.action',
	    data:storeTransport,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
	    var div = document.getElementById("czys");
	    if(result.total==0){
	    	div.innerHTML = "<div align=\"center\">暂无数据！</div>";
	    }else{
	    	
	    var tp="";
		var ww="";  // 用来循环保存信息
		var imgStr="";   //用来循环保存图片的标题

		var width = document.body.clientWidth;
		document.body.clientHeight;
		width=width-50;
		var height =width-200;
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			ww="<dl>" +
			"<dt>仓储方式:</dt><dd><span id=jgxx_headman>"+a.storageWay+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>仓储条件:</dt><dd><span id=jgxx_processName>"+a.storageCondi+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>运输方式:</dt><dd><span id=ylly_addr>"+a.transportWay+"</span></dd><div class=clear></div></dl>" +
			"<center>";
				
			var tbas=a.traceAppdixs;
			if(tbas.length>0){//存在图片
				for(var j=0;j<tbas.length;j++){
					var b=tbas[j];
					imgStr+="<span id=imgStr_img><a href='proshow.jsp?img="+b.appdixUrl+"' target=_blank><img src="+filePath+b.appdixUrl+"  width=\"100%\"></a></span>&nbsp;";
				}
			}
			
			if(imgStr&&imgStr!=''){
				imgStr="仓储运输图片<br>"+imgStr;
			}
			ww+=imgStr+"<br>";
			imgStr="";//重新置空

			ww+='<br>&nbsp;</center>&nbsp;<br><div style="border-bottom:2px dashed #cccccc;height:10px;overflow:hidden"></div>';
			tp+=ww;
		}
		div.innerHTML=tp;
	    }
	}
   }); 
}


//检验报告
function check8(){
	$("#cpxx").hide();
	$("#cdxx").hide();
	$("#zyxx").hide();
	$("#sfwy").hide();
	$("#zbfy").hide();
	$("#jgtz").hide();
	$("#czys").hide();
	$("#jcbg").show();
	
	$("#a1").attr("style", "color:#000");
	$("#a2").attr("style", "color:#000");
	$("#a3").attr("style", "color:#000");
	$("#a4").attr("style", "color:#000");
	$("#a5").attr("style", "color:#000");
	$("#a6").attr("style", "color:#000");
	$("#a7").attr("style", "color:#000");
	$("#a8").attr("style", "color:#fff");
	$("#c_a1").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a2").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a3").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a4").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a5").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a6").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a7").css("background","url('static/image/code/xt_02.jpg')");
	$("#c_a8").css("background","url('static/image/code/nav_bg.jpg')");
	
	proId = $('#proId').val();
	var proCheck = {};
	proCheck["proCheck.proId"]=proId;
	$.ajax({
	    url:'web_ProCheck!findProCheckList.action',
	    data:proCheck,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
	    var div = document.getElementById("jcbg");
	    if(result.total==0){
	    	div.innerHTML = "<div align=\"center\">暂无数据！</div>";
	    }else{
	    
	    var tp="";
		var ww="";  // 用来循环保存信息
		var imgStr="";   //用来循环保存图片的标题

		var width = document.body.clientWidth;
		document.body.clientHeight;
		width=width-50;
		var height =width-200;
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			ww="<dl>" +
			"<dt>报告名称:</dt><dd><span id=jgxx_headman>"+a.checkName+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>检验单位:</dt><dd><span id=jgxx_processName>"+a.checkUnit+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>检验时间:</dt><dd><span id=ylly_addr>"+a.checkTime+"</span></dd><div class=clear></div></dl>" +
			"<dl><dt>检验结果:</dt><dd><span id=ylly_addr>"+a.checkResult+"</span></dd><div class=clear></div></dl>" +
			"<center>";
			
			var tbas=a.traceAppdixs;
			if(tbas.length>0){//存在图片
				for(var j=0;j<tbas.length;j++){
					var b=tbas[j];
					imgStr+="<span id=imgStr_img><a href='proshow.jsp?img="+b.appdixUrl+"' target=_blank><img src="+filePath+b.appdixUrl+" width=\"100%\"></a></span>&nbsp;";
				}
			}
			
			if(imgStr&&imgStr!=''){
				imgStr="检测报告图片<br>"+imgStr;
			}
			ww+=imgStr+"<br>";
			imgStr="";//重新置空

			ww+='<br>&nbsp;</center>&nbsp;<br><div style="border-bottom:2px dashed #cccccc;height:10px;overflow:hidden"></div>';
			tp+=ww;
		}
		div.innerHTML=tp;
	}
	}
   }); 
}
