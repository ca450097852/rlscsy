//溯源查询数字输入
var filePath = '';
var dimenno = '';
var proId = '';
var typeClass = '';
$(function() {
	filePath = $('#filePath').val();
	
	dimenno = $('#dimenno').val();
	
	
	/*
	 * 先查询批次看是否有批次信息
	 * 
	 */
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
			var total = result.total;
			if(total>0){
				var data = result.rows[0];
				proId = data.proId;
				$("#proId").val(proId);
			}else{
				$("#suyuan_text").html("<img src=\"../static/image/portalweb/defaultPic.jpg\"/>");
				return;
			}
	    }
   }); 
	
	$("#section_cdxx").hide();
	$("#section_zyxx").hide();
	$("#section_sfwyxx").hide();
	$("#section_fyzbxx").hide();
	$("#section_jgtzxx").hide();
	$("#section_ccysxx").hide();
	$("#section_jcbgxx").hide();
	
if(proId!=''){

	var product = {};
	product["product.proId"]=proId;
	
	//产品信息
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
			
				$("#cpjbxx").html("" +
						"<h4>"+myprod.proName+"</h4>"+
					    "<p>类型:"+myprod.typeName+"</p>"+
					    "<p>所属企业:"+myprod.entName+"</p>"+
					    "<p>三品一标:"+authentication+"</p>"+
					    "<p>溯源码:"+dimenno+"</p>"+
					    "<p>规格单位:"+myprod.unit+"</p>"+
					    //"<p>单位:"+myprod.unit+"</p>"+
					    "");
				$("#scjy").html("" +
						"<p>生产商:"+myprod.manufacturer+"</p>"+
						"<p>生产商电话:"+myprod.sourceTel+"</p>"+
					    "<p>生产商地址:"+myprod.sourceAddr+"</p>"+
					    "<p>经销商:"+myprod.distributor+"</p>"+
						"<p>经销商电话:"+myprod.distributorTel+"</p>"+
					    "<p>经销商地址:"+myprod.distributorAddr+"</p>"+
					    "");
				
				$("#cpqtxx").html("" +
					    "<p>保鲜防腐:"+myprod.retain+"</p>"+
					    "<p>储藏条件:"+myprod.storageConditions+"</p>"+
					    "<p>保质期:"+myprod.shelfLife+"</p>"+
					    "");
				
				$("#cpjjxx").html("" +
					    "<p>"+myprod.proDesc+"</p>");
				
				//proId = myprod.proId;
				typeClass = myprod.typeClass;
				
			}else{
				$("#suyuan_text").html("<img src=\"images/defaultPic.jpg\"/>");
				return;
			}
				
	    }
   });
	
	
	/////产地信息
	
	var proArea = {};
	proArea["proArea.proId"]=proId;
	$.ajax({
	    url:'web_ProArea!findProAreaList.action',
	    data:proArea,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
		if(result.total!=0){
		$("#section_cdxx").show();
		
		var cdxx_html="";
		var imgStr = '';
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			var counter =i+1;
			cdxx_html+="<li class=\"bg01\"><table><tbody><tr><th>产地"+counter+"</th><td>" +
					"<p>产地名称:"+a.areaName+"</p>" +
					"<p>产地地址:"+a.areaAddr+"</p>" +
					"<p>产地概况:"+a.areaIntro+"</p>" +
					"<p>环境:"+a.edatope+"</p>" +
					"<p>水源:"+a.areaWater+"</p>" +
					"<p>气候:"+a.climatope+"</p>" +
					"</td></tr></tbody></table></li>";
			
			var appList = a.traceAppdixs;
			
			if(appList.length>0){//存在图片
				var appType = '产地图片';
				for(var j=0;j<appList.length;j++){
					var app = appList[j];
					var imgCont = "<li><p><a rel=\"cdtpxx_group\" href=\""+filePath+"proimg/"+app.appdixUrl+"\" title=\""+app.appdixName+"\"><img src=\""+filePath+"proimg/"+app.appdixUrl+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
					imgStr += imgCont;
				}
			}
		}
		if(imgStr!=''){
			$("#cdxxpicBox").show();
		}
		
		$("#cpcdxx").html(cdxx_html);
		$("#cpcdtpxx").html(imgStr);
		
	}
}
}); 
	/////种源信息
	var proSeed = {};
	proSeed["proSeed.proId"]=proId;
	$.ajax({
	    url:'web_ProSeed!findProSeedList.action',
	    data:proSeed,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
		if(result.total!=0){
		$("#section_zyxx").show();
		
		var zyxx_html="";
		var imgStr = '';
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			var counter =i+1;
			zyxx_html+="<li class=\"bg01\"><table><tbody><tr><th>种源"+counter+"</th><td>" +
					"<p>名称:"+a.seedName+"</p>" +
					"<p>地址:"+a.seedAddr+"</p>" +
					"<p>厂家:"+a.seedCompany+"</p>" +
					"<p>特性:"+a.feature+"</p>" +
					"</td></tr></tbody></table></li>";
			
			var appList = a.traceAppdixs;
			
			if(appList.length>0){//存在图片
				var appType = '种源图片';
				for(var j=0;j<appList.length;j++){
					var app = appList[j];
					var imgCont = "<li><p><a rel=\"zytpxx_group\" href=\""+filePath+"proimg/"+app.appdixUrl+"\" title=\""+app.appdixName+"\"><img src=\""+filePath+"proimg/"+app.appdixUrl+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
					imgStr += imgCont;
				}
			}
		}
		if(imgStr!=''){
			$("#zyxxpicBox").show();
		}
		
		$("#cpzyxx").html(zyxx_html);
		$("#cpzytpxx").html(imgStr);
	}
}
}); 
	/*
	 */
	//施肥或者喂养信息 section_sfwyxx
	
	var plantRaise = {};
	plantRaise["plantRaise.proId"]=proId;
	$.ajax({
	    url:'web_PlantRaise!findList.action',
	    data:plantRaise,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
		if(result.total!=0){
		$("#section_sfwyxx").show();
		
		var sfwy_html="";
		var imgStr = '';
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			var counter =i+1;
			if(typeClass=='1'){
				//种植
				$("#sforwy").html("产品施肥信息");
				sfwy_html+="<li class=\"bg01\"><table><tbody><tr><th>施肥"+counter+"</th><td>" +
				"<p>肥料名称:"+a.feedName+"</p>" +
				"<p>肥料厂家:"+a.feedCompany+"</p>" +
				"<p>施肥方法:"+a.feedWay+"</p>" +
				"<p>施肥周期:"+a.feedCycle+"</p>" +
				"<p>施肥时间:"+a.feedTime+"</p>" +
				"<p>施肥用量:"+a.dosage+"</p>" +
				"</td></tr></tbody></table></li>";
			}else{
				//养殖
				$("#sforwy").html("产品喂养信息");
				sfwy_html+="<li class=\"bg01\"><table><tbody><tr><th>喂养"+counter+"</th><td>" +
				"<p>饲料名称:"+a.feedName+"</p>" +
				"<p>饲料厂家:"+a.feedCompany+"</p>" +
				"<p>喂养方法:"+a.feedWay+"</p>" +
				"<p>喂养周期:"+a.feedCycle+"</p>" +
				"<p>喂养时间:"+a.feedTime+"</p>" +
				"<p>喂养用量:"+a.dosage+"</p>" +
				"</td></tr></tbody></table></li>";
			}
			
			
			var appList = a.traceAppdixs;
			
			if(appList.length>0){//存在图片
				var appType = typeClass=='1'?'施肥图片':'喂养图片';
				for(var j=0;j<appList.length;j++){
					var app = appList[j];
					var imgCont = "<li><p><a rel=\"sfwytpxx_group\" href=\""+filePath+"proimg/"+app.appdixUrl+"\" title=\""+app.appdixName+"\"><img src=\""+filePath+"proimg/"+app.appdixUrl+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
					imgStr += imgCont;
				}
			}
		}
		if(imgStr!=''){
			$("#sfwypicBox").show();
		}
		$("#sfwyxx").html(sfwy_html);
		$("#sfwytpxx").html(imgStr);
	}
}
}); 
	
	/*
	 */
	//防疫或者植保信息 section_fyzbxx
	
	var prevention = {};
	prevention["prevention.proId"]=proId;
	$.ajax({
	    url:'web_Prevention!findList.action',
	    data:prevention,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
		if(result.total!=0){
		$("#section_fyzbxx").show();
		
		var fyzb_html="";
		var imgStr = '';
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			var counter =i+1;
			if(typeClass=='1'){
				//种植
				$("#fyorzb").html("植保信息");
				fyzb_html+="<li class=\"bg01\"><table><tbody><tr><th>植保信息"+counter+"</th><td>" +
				"<p>药品名称:"+a.drugName+"</p>" +
				"<p>药品厂家:"+a.drugCompany+"</p>" +
				"<p>防治对象:"+a.drugObject+"</p>" +
				"<p>用药方法:"+a.drugWay+"</p>" +
				"<p>用药周期:"+a.drugCycle+"</p>" +
				"<p>用药时间:"+a.drugTime+"</p>" +
				"<p>用药剂量:"+a.dosage+"</p>" +
				"</td></tr></tbody></table></li>";
			}else{
				//养殖
				$("#fyorzb").html("防疫信息");
				fyzb_html+="<li class=\"bg01\"><table><tbody><tr><th>防疫信息"+counter+"</th><td>" +
				"<p>药品名称:"+a.drugName+"</p>" +
				"<p>药品厂家:"+a.drugCompany+"</p>" +
				"<p>防治对象:"+a.drugObject+"</p>" +
				"<p>用药方法:"+a.drugWay+"</p>" +
				"<p>用药周期:"+a.drugCycle+"</p>" +
				"<p>用药时间:"+a.drugTime+"</p>" +
				"<p>用药剂量:"+a.dosage+"</p>" +
				"</td></tr></tbody></table></li>";
			}
			
			var appList = a.traceAppdixs;
			
			if(appList.length>0){//存在图片
				var appType = typeClass=='1'?'植保图片':'防疫图片';
				for(var j=0;j<appList.length;j++){
					var app = appList[j];
					var imgCont = "<li><p><a rel=\"fyzbtpxx_group\" href=\""+filePath+"proimg/"+app.appdixUrl+"\" title=\""+app.appdixName+"\"><img src=\""+filePath+"proimg/"+app.appdixUrl+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
					imgStr += imgCont;
				}
			}
		}
		if(imgStr!=''){
			$("#fyzbpicBox").show();
		}
		$("#fyzbxx").html(fyzb_html);
		$("#fyzbtbxx").html(imgStr);
	}
}
}); 
	
	/*
	 */
	//加工包装或者屠宰信息 section_fyzbxx
	
	var process = {};
	process["process.proId"]=proId;
	$.ajax({
	    url:'web_Process!findProcessList.action',
	    data:process,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
		if(result.total!=0){
		$("#section_jgtzxx").show();
		
		var jgtz_html="";
		var imgStr = '';
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			var counter =i+1;
			if(typeClass=='1'){
				//种植
				$("#jgtzxx").html("加工包装信息");
				jgtz_html+="<li class=\"bg01\"><table><tbody><tr><th>加工包装信息"+counter+"</th><td>" +
				"<p>加工负责人:"+a.processUser+"</p>" +
				"<p>加工厂家:"+a.processCompany+"</p>" +
				"<p>加工地址:"+a.processAddr+"</p>" +
				"<p>加工时间:"+a.processTime+"</p>" +
				"</td></tr></tbody></table></li>";
			}else{
				//养殖
				$("#jgtzxx").html("屠宰信息");
				jgtz_html+="<li class=\"bg01\"><table><tbody><tr><th>屠宰信息"+counter+"</th><td>" +
				"<p>屠宰负责人:"+a.processUser+"</p>" +
				"<p>屠宰厂家:"+a.processCompany+"</p>" +
				"<p>屠宰地址:"+a.processAddr+"</p>" +
				"<p>屠宰时间:"+a.processTime+"</p>" +
				"</td></tr></tbody></table></li>";
			}
			
			var appList = a.traceAppdixs;
			
			if(appList.length>0){//存在图片
				var appType = typeClass=='1'?'加工图片':'屠宰图片';
				for(var j=0;j<appList.length;j++){
					var app = appList[j];
					var imgCont = "<li><p><a rel=\"jgtztpxx_group\" href=\""+filePath+"proimg/"+app.appdixUrl+"\" title=\""+app.appdixName+"\"><img src=\""+filePath+"proimg/"+app.appdixUrl+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
					imgStr += imgCont;
				}
			}
		}
		if(imgStr!=''){
			$("#jgbzpicBox").show();
		}
		$("#cpjgtzxx").html(jgtz_html);
		$("#cpjgtztpxx").html(imgStr);
	}
}
}); 
	
	/////仓储运输信息//section_ccysxx
	
	var storeTransport = {};
	storeTransport["storeTransport.proId"]=proId;
	$.ajax({
	    url:'web_StoreTransport!findStoreTransportList.action',
	    data:storeTransport,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
		if(result.total!=0){
		$("#section_ccysxx").show();
		
		var ccysxx_html="";
		var imgStr = '';
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			var counter =i+1;
			ccysxx_html+="<li class=\"bg01\"><table><tbody><tr><th>仓储运输"+counter+"</th><td>" +
					"<p>仓储方式:"+a.storageWay+"</p>" +
					"<p>仓储条件:"+a.storageCondi+"</p>" +
					"<p>运输方式:"+a.transportWay+"</p>" +
					"</td></tr></tbody></table></li>";
			
			var appList = a.traceAppdixs;
			
			if(appList.length>0){//存在图片
				var appType = '仓储运输图片';
				for(var j=0;j<appList.length;j++){
					var app = appList[j];
					var imgCont = "<li><p><a rel=\"ccystpxx_group\" href=\""+filePath+"proimg/"+app.appdixUrl+"\" title=\""+app.appdixName+"\"><img src=\""+filePath+"proimg/"+app.appdixUrl+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
					imgStr += imgCont;
				}
			}
		}
		if(imgStr!=''){
			$("#ccyspicBox").show();
		}
		$("#cpccysxx").html(ccysxx_html);
		$("#cpccystpxx").html(imgStr);
	}
}
}); 

	/////产品检验信息//section_ccysxx

	var proCheck = {};
	proCheck["proCheck.proId"]=proId;
	$.ajax({
	    url:'web_ProCheck!findProCheckList.action',
	    data:proCheck,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
		if(result.total!=0){
		$("#section_jcbgxx").show();
		
		var jcbgxx_html="";
		var imgStr = '';
		for(var i=0;i<result.rows.length;i++){
			var a = result.rows[i];
			var counter =i+1;
			jcbgxx_html+="<li class=\"bg01\"><table><tbody><tr><th>检验信息"+counter+"</th><td>" +
					"<p>报告名称:"+a.checkName+"</p>" +
					"<p>检验单位:"+a.checkUnit+"</p>" +
					"<p>检验时间:"+a.checkTime+"</p>" +
					"<p>检验结果:"+a.checkResult+"</p>" +
					"</td></tr></tbody></table></li>";
			
			var appList = a.traceAppdixs;
			
			if(appList.length>0){//存在图片
				var appType = '检测图片';
				for(var j=0;j<appList.length;j++){
					var app = appList[j];
					var imgCont = "<li><p><a rel=\"jcbgtpxx_group\" href=\""+filePath+"proimg/"+app.appdixUrl+"\" title=\""+app.appdixName+"\"><img src=\""+filePath+"proimg/"+app.appdixUrl+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
					imgStr += imgCont;
				}
			}
		}
		if(imgStr!=''){
			$("#cpjcpicBox").show();
		}
		
		$("#cpjcbgxx").html(jcbgxx_html);
		$("#cpjcbgtpxx").html(imgStr);
		
	}
}
}); 
	
	//图片横向滚动
	var imgScroll_01 = new funPicScrollX(".c_scrollPic01 .picBox",".c_scrollPic01 .prevBtn span",".c_scrollPic01 .nextBtn span",28,0).init();
	var imgScroll_02 = new funPicScrollX(".c_scrollPic02 .picBox",".c_scrollPic02 .prevBtn span",".c_scrollPic02 .nextBtn span",28,0).init();
	var imgScroll_03 = new funPicScrollX(".c_scrollPic03 .picBox",".c_scrollPic03 .prevBtn span",".c_scrollPic03 .nextBtn span",28,0).init();
	var imgScroll_04 = new funPicScrollX(".c_scrollPic04 .picBox",".c_scrollPic04 .prevBtn span",".c_scrollPic04 .nextBtn span",28,0).init();
	var imgScroll_05 = new funPicScrollX(".c_scrollPic05 .picBox",".c_scrollPic05 .prevBtn span",".c_scrollPic05 .nextBtn span",28,0).init();
	var imgScroll_06 = new funPicScrollX(".c_scrollPic06 .picBox",".c_scrollPic06 .prevBtn span",".c_scrollPic06 .nextBtn span",28,0).init();
	var imgScroll_07 = new funPicScrollX(".c_scrollPic07 .picBox",".c_scrollPic07 .prevBtn span",".c_scrollPic07 .nextBtn span",28,0).init();
	
	$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
		var lenth = $(this).find("li").length;
		if(lenth<=4){
			$(this).find(".nextBtn span").eq(0).addClass("last");
		}
	});
	
	////产地图片预览效果
	$("a[rel=cdtpxx_group]").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'titlePosition' 	: 'over',
		'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
			return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
		}
	});
	////种源图片预览效果
	$("a[rel=zytpxx_group]").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'titlePosition' 	: 'over',
		'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
			return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
		}
	});
	
	
	////施肥喂养图片预览效果
	$("a[rel=sfwytpxx_group]").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'titlePosition' 	: 'over',
		'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
			return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
		}
	});
	////防疫或植保图片预览效果
	$("a[rel=fyzbtpxx_group]").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'titlePosition' 	: 'over',
		'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
			return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
		}
	});
	
	
	////加工或屠宰图片预览效果
	$("a[rel=jgtztpxx_group]").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'titlePosition' 	: 'over',
		'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
			return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
		}
	});

	////仓储运输图片预览效果
	$("a[rel=ccystpxx_group]").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'titlePosition' 	: 'over',
		'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
			return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
		}
	});
	////检测报告图片预览效果
	$("a[rel=jcbgtpxx_group]").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'titlePosition' 	: 'over',
		'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
			return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
		}
	});
	
	////////////////////////////////
	
	}

});



//图片横向滚动
function funPicScrollX(scrollBox,prevBtn,nextBtn,spacing,isCycle){
    this.scroll = $(scrollBox);
    this.lBtn = $(prevBtn);
    this.rBtn = $(nextBtn);
    this.bd = this.scroll.find('ul');
	this.spacing = spacing;
    //是否循环播放
    this.isCycle = isCycle ? isCycle : false;
    //容器显示的宽度
    this.W = this.scroll.width();
    //item集合
    this.item = this.bd.find('li');
    //每一个item容器的宽度
    this.w = this.scroll.find('li').eq(0).width() + this.spacing;
    //可见item个数
    this.isShowNum = this.W / this.w;
    //item容器的个数
    this.len = this.scroll.find('li').length;
    //动画播放key
    this.key = true;
    //起始位置
    this.start = 0;
    //结束位置
    this.end = 0;
    this.timer = null;
}
funPicScrollX.prototype = {
    constructor: funPicScrollX,
    init: function(){
        var that = this, i = 0;
        
        //如果item个数不足以滚动就退出
        if (that.isShowNum > that.len) 
            return;
        
        //是否循环播放
        if (that.isCycle) {
            for (; i < that.isShowNum; i++) {
                that.item.eq(i).clone().appendTo(that.bd);
            }
        }
        else {
            that.bd.css('width', that.len * that.w + 'px');
        }
        
        that.rBtn.click(function(){
            that.play(1);
        })
        
        that.rBtn.bind('click', function(){
            that.play(1);
        })
        
        that.lBtn.bind('click', function(){
            that.play(0);
        })
    },
    play: function(k){
        var that = this, m = that.w;
        if (!k) {
            m = -that.w;
        }
        if (that.key) {
            that.key = false;
            that.scroll.animate({
                scrollLeft: that.scroll.scrollLeft() + m
            }, 500, function(){
                that.key = true;
				if(!that.isCycle && that.scroll.scrollLeft() == 0){
					that.lBtn.addClass('last');
				}else{
					that.lBtn.removeClass('last');
				}
				if(!that.isCycle && that.scroll.scrollLeft() == that.w * (that.len-that.isShowNum)){
					that.rBtn.addClass('last');
				}else{
					that.rBtn.removeClass('last');
				}
            })
        }
    }
}

