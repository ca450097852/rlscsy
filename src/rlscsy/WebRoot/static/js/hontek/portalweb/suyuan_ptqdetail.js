//企业分类溯源查询
var filePath = '';
var dimenno = '';
var ptqId = 0;
var recId = '';//档案id

var section_1 = "<section class=\"c_main01 mt30\"><header class=\"h_tit\"><h2>";//需要加上title
var section_2 = "</h2></header><article class=\"c_pm01 c_cpdaxx\"><a name=\"zzry\" class=\"anchor_line\"></a><div class=\"bd cz_zzry\"><ul>";
var section_3 = "</ul></div>";
var section_4 = "<div class=\"picMoveX01 mt20 clearfix ";//需要加上c_scrollPic01
var section_5 = "\"><p class=\"bbtn prevBtn\"><span class=\"last\"></span></p><div class=\"picBox\"><ul>";
var section_6 = "</ul></div><p class=\"bbtn nextBtn\"><span></span></p></div>";
var section_7 = "</article></section>";
var tpmove=0;//图片横向滚动开关,0关，1开；
//下面为两种情况概述
//111:1+title+2+li+3+7;//无图片附件
//222:1+title+2+li+3+4+class+5+tpli+6+7;//有图片附件

$(function() {
	filePath = $('#filePath').val();
	dimenno = $('#dimenno').val();
	
//////////////////////////////new search start ///////////////////////////////
	findCompanyInfo();//分类信息和企业信息，并且获取分类二维码编号；
	
	ptqId = parseInt(ptqId);
	//获取分类档案
	$.ajax({
   	    url:'portal_Elements_findRecordByPtqDimennno.action?dimenno='+dimenno,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			if(result.length>0){
				recId = result[0].recId;
			}
	    }
   });
	//获取分类档案要素
	var objElement = {};
	objElement["objElement.objId"]=ptqId;
	objElement["objElement.objTypeId"]=2;//
	$.ajax({
   	    url:'portal_ObjElement_findObjElementList.action',
   	    data:objElement,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			var elementId = "";	//要素编号
			var tableName = "";	//要素表名
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				elementId = a.elementId;
				tableName = a.tableName;
				if(tableName=='tb_threea'){//三品一标
					findThreeaList();
				}else if(tableName=='tb_eartag'){//耳标信息
					findEartagList();
				}else if(tableName=='tb_checkinfo'){//检验报告
					findCheckinfoList();
				}else if(tableName=='tb_agri_input'){//投入品购买信息
					findAgriInputList();
				}else if(tableName=='tb_agri_use'){//投入品使用记录
					findAgriUseList();
				}
			}
	    }
   });
	
	
	
////////////////////////////////new search end ///////////////////////////////
	
	if(tpmove==1){
		//图片横向滚动
		$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
			var lenth = $(this).find("li").length;
			if(lenth<=4){
				$(this).find(".nextBtn span").eq(0).addClass("last");
			}
		});
	}
	
	
});

//企业信息
function findCompanyInfo(){
	var ptq = {};
	ptq["ptq.dimenno"]=dimenno;
	//企业信息
	$.ajax({
		    url:'portal_Company_findProTypeQrcodeList.action',
		    data:ptq,
		    type:'post',
		    async : false,
		    dataType:'json',
		    success : function(data) {
			 if(data.rows.length>0){
				for(var i=0;i<data.rows.length;i++){
					var a = data.rows[0];
					ptqId = a.ptqId;//获取分类二维码编号；
					$("#qyjbxx").html("" +
							"<h4>产品名称："+a.typeName+"</h4>"+
							"<p>产品溯源码:"+a.dimenno+"</p>"+
						    "<p>企业名称:"+a.companyName+"</p>"+
						    "<p>企业法人:"+a.legalPerson+"</p>");
					
					$("#qylxfs").html("" +
						    "<p>电话:"+a.tel+"</p>"+
						    "<p>邮箱:"+a.email+"</p>"+
						    "<p>注册地址:"+a.regAddr+"</p>"+
						    "<p>经营地址:"+a.manageAddr+"</p>"+
						    "<p>网址:"+a.domName+"</p>"+
						    "");
					
					$("#qyjj").html("" +
						    "<p>"+a.intro+"</p>");
				}
		    }
		}
	});
}



//三品一标
function findThreeaList(){
	var threea = {};
	threea["threea.recId"]=recId;
	$.ajax({
   	    url:'portal_Elements_findThreeaList.action',
   	    data:threea,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			var cuoshi = "";//生产质量控制措施
			var guicheng = "";//生产操作规程
			var jianyan = "";//产地环境检验
			var biaozhun = "";//产品执行标准/
			var shengming = "";//保证执行标准声明
			var liList = "";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				cuoshi = a.cuoshi;
				guicheng = a.guicheng;
				jianyan = a.jianyan;
				biaozhun = a.biaozhun;
				shengming = a.shengming;
				liList+="<li class=\"bg01\"><table><tbody><tr><th>三品一标</th><td>" +
						"<p>生产质量控制措施："+cuoshi+"</p>" +
						"<p>生产操作规程："+guicheng+"</p>" +
						"<p>产地环境检验："+jianyan+"</p>" +
						"<p>产品执行标准："+biaozhun+"</p>" +
						"<p>保证执行标准声明："+shengming+"</p>" +
						"</td></tr></tbody></table></li>";
			}
			if(result.total>0){
				
				$("#suyuan_text").append(section_1+"三品一标信息"+section_2+liList+section_3+section_7);
			}
			
		
		}
	});
}

//耳标信息
function findEartagList(){
	var eartag = {};
	eartag["eartag.recId"]=recId;
	$.ajax({
   	    url:'portal_Elements_findEartagList.action',
   	    data:eartag,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			var earNo = "";//耳标编号
			var wearTime = "";//佩戴时间
			var liList = "";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				earNo = a.earNo;
				wearTime = a.wearTime;
				liList+="<li class=\"bg01\"><table><tbody><tr><th>耳标信息</th><td>" +
						"<p>耳标编号:"+earNo+"</p>" +
						"<p>佩戴时间:"+wearTime+"</p>" +
						"</td></tr></tbody></table></li>";
			}
			if(result.total>0){
				$("#suyuan_text").append(section_1+"耳标信息"+section_2+liList+section_3+section_7);
			}
		}
	});
}



//检验报告
function findCheckinfoList(){
	var checkinfo = {};
	checkinfo["checkinfo.recId"]=recId;
	$.ajax({
   	    url:'portal_Elements_findCheckinfoList.action',
   	    data:checkinfo,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			var checkName = "";//报告名称 
			var checkNum = "";//报告编号
			var checkUnit = "";//检验单位
			var checkTime = "";//检验时间
			var checkResult = "";//检验结果
			var checkType = "";//1生产档案检测报告；2企业自检报告；
			var elementApp;
			var liList = "";
			var imgStr = "";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				checkName = a.checkName;
				checkUnit = a.checkUnit;
				checkTime = a.checkTime;
				checkResult = a.checkResult;
				checkType = a.checkType;
				elementApp = a.elementApp;//附件list
				if(checkType=='1'){
					checkType = "生产档案检测报告";
				}else{
					checkType = "企业自检报告";
				}
				liList+="<li class=\"bg01\"><table><tbody><tr><th>报告信息</th><td>" +
						"<p>报告类型："+checkType+"</p>" +
						"<p>报告名称："+checkName+"</p>" +
						"<p>检验单位："+checkUnit+"</p>" +
						"<p>检验时间："+checkTime+"</p>" +
						"<p>检验结果："+checkResult+"</p>" +
						"</td></tr></tbody></table></li>";
				if(elementApp.length>0){
					for(var j=0;j<elementApp.length;j++){
						var app = elementApp[j];
						var appType =app.appType;
						appType = appType==1?'检验报告':appType;
						var imgCont = "<li><p><a rel=\"jybg_group\" href=\""+filePath+"element/"+app.appUrl+"\" title=\""+app.appName+"\"><img src=\""+filePath+"element/"+app.appUrl+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
						imgStr += imgCont;
					}
				}
			}
			if(result.total>0&&imgStr==''){
				$("#suyuan_text").append(section_1+"检验报告信息"+section_2+liList+section_3+section_7);
			}else if(result.total>0&&imgStr.length>0){
				tpmove =1;//打开图片横向滚动开关
				//222:----1+title+2+li+3+4+class+5+tpli+6+7;//有图片附件c_scrollPic01
				$("#suyuan_text").append(section_1+"检验报告信息"+section_2+liList+section_3+section_4+"c_scrollPic01"+section_5+imgStr+section_6+section_7);
				//图片横向滚动
				var imgScroll_01 = new funPicScrollX(".c_scrollPic01 .picBox",".c_scrollPic01 .prevBtn span",".c_scrollPic01 .nextBtn span",28,0).init();
				////原料图片预览效果
				$("a[rel=jybg_group]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}
		}
	});
}

//投入品购买信息
function findAgriInputList(){
	var agriInput = {};
	agriInput["agriInput.recId"]=recId;
	$.ajax({
   	    url:'portal_Elements_findAgriInputList.action',
   	    data:agriInput,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
//			var agriName = "";	//品种名称
//			var buyDate = "";	//购买日期
//			var buyNum = "";	//购买数量
//			var agriImg = "";	//产品标签
//			var crttime = "";	//录入时间
			var liList = "";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				liList+="<li class=\"bg01\"><table><tbody><tr><th>投入品信息</th><td>" +
						"<p>品种名称:"+a.agriName+"</p>" +
						"<p>购买日期:"+a.buyDate+"</p>" +
						"<p>购买数量:"+a.buyNum+"</p>" +
						"</td></tr></tbody></table></li>";
			}
			if(result.total>0){
				$("#suyuan_text").append(section_1+"投入品购买信息"+section_2+liList+section_3+section_7);
			}
		}
	});
}


//投入品使用记录
function findAgriUseList(){
	var agriUse = {};
	agriUse["agriUse.recId"]=recId;
	$.ajax({
   	    url:'portal_Elements_findAgriUseList.action',
   	    data:agriUse,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
//			var agriName = "";	//药物名称
//			var useDate = "";	//使用日期
//			var useObject = "";	//使用对象
//			var useDosage = "";	//使用剂量
//			var stopDate = "";	//停用日期
//			var harvestDate = "";	//收获日期
//			var listedDate = "";	//上市日期
//			var crttime = "";	//
			var havetag = "";	//是否有追溯标识：1有，
			var liList = "";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				havetag = a.havetag;
				havetag = havetag=="1"?"有":"无";
				liList+="<li class=\"bg01\"><table><tbody><tr><th>投入品使用信息</th><td>" +
						"<p>药物名称:"+a.agriName+"</p>" +
						"<p>使用日期:"+a.useDate+"</p>" +
						"<p>使用对象:"+a.useObject+"</p>" +
						"<p>使用剂量:"+a.useDosage+"</p>" +
						"<p>停用日期:"+a.stopDate+"</p>" +
						"<p>收获日期:"+a.harvestDate+"</p>" +
						"<p>上市日期:"+a.listedDate+"</p>" +
						"<p>是否有追溯标识:"+havetag+"</p>" +
						"</td></tr></tbody></table></li>";
			}
			if(result.total>0){
				$("#suyuan_text").append(section_1+"投入品使用信息"+section_2+liList+section_3+section_7);
			}
		
		}
	});
}






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

