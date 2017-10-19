//企业分类溯源查询
var filePath = '';
var basePath = '';
var dimenno = '';
var code = '';
var ptqId = 0;
var ptq_recId = '';//分类档案id
//var recId = '';//档案id
var thisEntId='';//保存企业Id；
var companyRsts='';//企业审核状态：4为通过

var header_1 = "<section class=\"c_main01 mt15\"><header class=\"h_tit\"><h2>";//需要加上title
var header_2 = "</h2></header><article class=\"c_pm01 c_cpdaxx\" id=\"";//需要加上article_id
var header_3 = "\"><a name=\"zzry\" class=\"anchor_line\"></a><div class=\"bd cz_zzry\"><ul>";//需要加上li
var header_4 = "</ul></div></article></section>";

var section_1 = "<section class=\"c_main02 mt15\"><header class=\"h_tit\"><h2>";//需要加上title
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
	basePath = $('#basePath').val();
	dimenno = $('#dimenno').val();
	code = dimenno.substring(0, 12);
	
	if(dimenno!=null&&dimenno.length>12){//分类信息
		findPtqCompanyInfo();//获取具体分类档案id --ptq_recId;
	}
	
	//企业信息
	findCompanyInfo();
	
	if(companyRsts!='4'){//审核
		$('#yx_info').show();
	}else{
		$('#sh_info').show();
	}
	
	//产品信息
//	getProductList(1,10);
	//资质信息
	findZizhiInfo();
	// 企业生产信息
//	findProductInfo();
	//监管信息
	findJianguanInfo();
	
	
	if(thisEntId!=''){
		//获取企业所有档案
		findCompanyRecord();
	}
	
	
	
////////////////////////////////new search end ///////////////////////////////
	
	if(tpmove==1){
		//图片横向滚动
		$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
			var lenth = $(this).find("li").length;
			if(lenth<=3){
				$(this).find(".nextBtn span").eq(0).addClass("last");
			}
		});
	}
	
	
});
//企业分类档案
function findPtqCompanyInfo(){
	//获取分类档案
	$.ajax({
   	    url:'portal_Elements_findRecordByPtqDimennno.action?dimenno='+dimenno,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			if(result.length>0){
				var record = result[0];
				if(record){
					ptq_recId = record.recId;
				}
			}
	    }
   });
}
//获取企业所有档案
function findCompanyRecord(){
	var condition = {};
	condition['page']=1;
	condition['rows']=10;
	condition['entId']=thisEntId;
	
	$.ajax({
	    url:'portal_Elements_findRecordList.action',
	    data:condition,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
			if(result){
				var recordList = result.rows;
				var fristHtml = "";
				for(var i=0;i<recordList.length;i++){//第一次循环获取外层div（档案内容）
					//获取档案
					var record = recordList[i];
					if(record){
						var recId = record.recId;
						var objTypeId = record.objTypeId;//企业1；分类二维码2；产品3
						if(dimenno.length>12){	//主体和具体分类
							if(objTypeId==1||recId==ptq_recId){
								var article_id = "article_id"+i;
								fristHtml += header_1+record.recName+header_2+article_id+header_3+header_4;
							}
						}else{					//所有
							var article_id = "article_id"+i;
							fristHtml += header_1+record.recName+header_2+article_id+header_3+header_4;
						}
					}
					
				}
				
				$("#suyuan_text").append(fristHtml);
				
				for(var j=0;j<recordList.length;j++){//第二次循环获取档案要素内容
					//获取档案
					var record = recordList[j];
					if(record){
						var recId = record.recId;
						var objId = record.objId;
						var objTypeId = record.objTypeId;//企业1,2；分类二维码3,4
						if(dimenno.length>12){//主体和具体分类
							if(objTypeId==1||recId==ptq_recId){
								if(recId==ptq_recId){//种类档案，则先获取分类基本信息
									var ptq = {};
									ptq["ptq.ptqId"]=objId;
									ptq["ptq.entId"]=thisEntId;
									findPtqInfo(ptq,"article_id"+j);
								}
								findRecordElement(recId,"article_id"+j);//获取档案要素
							}
						}else{//所有
							if(objTypeId==3||objTypeId==4){//种类档案，则先获取分类基本信息
								var ptq = {};
								ptq["ptq.ptqId"]=objId;
								ptq["ptq.entId"]=thisEntId;
								findPtqInfo(ptq,"article_id"+j);
							}
							findRecordElement(recId,"article_id"+j);//获取档案要素
						}
					}
				}
			}
		}
	});
	
}

//获取分类基本信息
//ptq={}
//var ptq = {};
//ptq["ptqId"]=ptqId;
//ptq["entId"]=thisEntId;
function findPtqInfo(ptq,divId){
	
	$.ajax({
		url:'portal_Company_findProTypeQrcodeList.action',
   	    data:ptq,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
//			var typeName;//种类名称
//			var certificate;//是否认证
//			var quantity;///年商品量
//			var listed;//上市期
//			var salearea;//主要销售地
			var liList = "";
			var litr = "";
			var befortr = "<li class=\"bg02\"><table><tbody>" +
					"<tr class=\"thstyle\">" +
					"<td><p>种类名称</p></td>" +
					"<td><p>是否认证</p></td>" +
					"<td><p>年商品量(吨)</p></td>" +
					"<td><p>上市期</p></td>" +
					"<td><p>主要销售地</p></td>" +
					"<td><p>产地及规模</p></td>" +
					"</tr>";
			var aftertr = "</tbody></table></li>";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				var certificate = a.certificate;//1有机、2绿色、3无公害产品、4地理标志认证、5无
				certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
				var chandi = "";
				var b = a.proTypeArea;
				if(b.length>0){
					for(var j=0;j<b.length;j++){
						chandi += b[j].chandi+"：" + b[j].scale+"；<br>";
					}
				}
				litr += "<tr>" +
						"<td><p>"+a.typeName+"</p></td>" +
						"<td><p>"+certificate+"</p></td>" +
						"<td><p>"+a.quantity+"</p></td>" +
						"<td><p>"+a.listed+"</p></td>" +
						"<td><p>"+a.salearea+"</p></td>" +
						"<td><p>"+chandi+"</p></td>" +
						"</tr>";
			}
	
			liList = befortr+litr+aftertr;
			if(result.total>0){
				$("#"+divId).append(section_1+"种类基本信息"+section_2+liList+section_3+section_7);
			}
		}
	});
}


//获取档案要素
function findRecordElement(recId,divId){
	
	$.ajax({
   	    url:'portal_Elements_getElements.action',
   	    data:'recId='+recId,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			var elementId = "";	//要素编号
			var tableName = "";	//要素表名
			if(result){
				for(var i=0;i<result.length;i++){
					var a = result[i];
					if(a){
						elementId = a.elementId;
						tableName = a.tableName;
						if(tableName=='tb_threea'){//三品一标
							findThreeaList(recId,divId);
						}else if(tableName=='tb_eartag'){//耳标信息
							findEartagList(recId,divId);
						}else if(tableName=='tb_checkinfo'){//检验报告
							findCheckinfoList(recId,divId);
						}else if(tableName=='tb_agri_input'){//投入品购买信息
							findAgriInputList(recId,divId);
						}else if(tableName=='tb_agri_use'){//投入品使用记录
							findAgriUseList(recId,divId);
						}else if(tableName=='tb_gaininfo'){//采摘收获信息
							findGaininfoList(recId,divId);
						}else if(tableName=='tb_saleinfo'){//销售信息
							findSaleinfoList(recId,divId);
						}
					}
				}
			}
			
	    }
   });
}


//企业信息
function findCompanyInfo(){
	var cond = {};
	cond["entCode"]=code;
	$.ajax({
		    url:'mbent_getCompanyInfoByEntCode.action',
		    data:cond,
		    type:'post',
		    async : false,
		    dataType:'json',
		    success : function(data) {
				if(data.length>0){
					thisEntId=data[0].entId;
					companyRsts = data[0].companyRsts;
				}
				for(var i=0;i<data.length;i++){
					var a = data[i];
					$("#qyjbxx").html("" +
							"<h4>名称："+a.name+"</h4>"+
						    "<p>简称:"+a.simpleName+"</p>"+
						    "<p>溯源码:"+a.entCode+"</p>"+
						    "<p>企业法人:"+a.legalPerson+"</p>" +
						    "<p>邮编:"+a.postCode+"</p>"+
						    "");
					
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
	});
}
/////资质信息
function findZizhiInfo(){
	var cond = {};
	cond["entCode"]=code;
	cond["rows"]=1000;
	$.ajax({
	    url:'mbzizhi_findZizhiPagerListforMobile.action',
	    data:cond,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(data) {
		    var tp="";
			var jght="";   //用来循环保存图片的标题
			var jgsb="";
		
			for(var i=0;i<data.length;i++){
				var a = data[i];
				jgsb+="<li><p><a rel=\"example_group\" href=\""+filePath+"zizhi/"+a.path+"\" title=\""+a.appName+"\"><img src=\""+filePath+"zizhi/"+a.path+"\" height=\"175\"></a></p><p>"+a.zName+"</p></li>";
			}
			
			if(data.length>0&&jgsb.length>0){
				tpmove =1;//打开图片横向滚动开关
				$("#company_text").append(section_1+"企业资质信息"+section_2+section_3+section_4+"c_scrollPic01"+section_5+jgsb+section_6+section_7);
				//图片横向滚动
				var imgScroll_01 = new funPicScrollX(".c_scrollPic01 .picBox",".c_scrollPic01 .prevBtn span",".c_scrollPic01 .nextBtn span",28,0).init();
				////原料图片预览效果
				$("a[rel=example_group]").fancybox({
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

//监管信息
function findJianguanInfo(){
	var cond = {};
	cond["entCode"]=code;
	cond["ids"]=code;
	$.post('mbsupervise_findSuperviseList.action',cond,function(result){
		var ht = "";			
		for(var i=0;i<result.length;i++){
			var supervise = result[i];
			ht+="<li class=\"bg01\"><table><tbody><tr><th>"+supervise.title+"</th>" +
					"<td><p>"+supervise.contents+"</p></td>" +
					"</tr></tbody></table></li>";
		}
		if(result.length>0&&ht.length>0){
			$("#company_text").append(section_1+"监管信息"+section_2+ht+section_3+section_7);
		}	
	},'JSON');
}

// 企业生产信息
function findProductInfo(){
	var cond = {};
	cond["entCode"]=code;
	cond["ids"]=code;
	$.ajax({
   	    url:'mbproduction_findProductForMobile.action',
   	    data:cond,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(data) {
			var rows = data[0].data;
			var cont = '';
			var imgStr = '';
			for(var i=0;i<rows.length;i++){
				var obj = rows[i];
				var appList = obj.appList;
				cont+="<li class=\"bg01\"><table><tbody><tr><th>生产情况</th><td><p>"+obj.productinfo+"</p></td></tr></tbody></table></li>";
				
				if(appList.length>0){//存在图片
					
					for(var j=0;j<appList.length;j++){
						var app = appList[j];
						var appType =app.appType;
						appType = appType==1?'产品图片':'生产许可证扫描件';
						var imgCont = "<li><p><a rel=\"scxx_group\" href=\""+filePath+"production/"+app.path+"\" title=\""+app.appName+"\"><img src=\""+filePath+"production/"+app.path+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
						imgStr += imgCont;
					}
				}
			}
			if(cont!=''&&imgStr==''){
				$("#company_text").append(section_1+"生产信息"+section_2+cont+section_3+section_7);
			}else if(cont!=''&&imgStr!=''){
				tpmove =1;//打开图片横向滚动开关
				$("#company_text").append(section_1+"生产信息"+section_2+cont+section_3+section_4+"c_scrollPic02"+section_5+imgStr+section_6+section_7);
				//图片横向滚动
				var imgScroll_02 = new funPicScrollX(".c_scrollPic02 .picBox",".c_scrollPic02 .prevBtn span",".c_scrollPic02 .nextBtn span",28,0).init();
				////原料图片预览效果
				$("a[rel=scxx_group]").fancybox({
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


/*获取产品列表*/
function getProductList(nowPage,pageSize){
	
	var url = "portal_Product_findCompanyProList.action?page="+nowPage+"&rows="+pageSize;
	var product ={};
	product['product.state']=1;
	product['product.entCode']=code;
	$.ajax( {
		url : url,
		async : false,
		data : product,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			
			var proName = "";//产品名称
			var typeClass = "";//分类属性:1种植,2养殖
			var typeName = "";//分类名称
			var entName = "";//机构名称
			var dimenno = "";//产品二维码
			var sourceAddr = "";//生产地址
			var distributorAddr = "";//经销商地址
			var updateTime = "";//updateTime
			var proId = "";
			var logoUrl = "";//二维码logo地址
			
			var appendixList;//产品图片(列表)
			
			var productInfo;//产品补充信息(列表)
			var certificate;//是否认证:1有机、2绿色、3无公害产品、4地理标志认证、5无
			var ischeck;//是否检测:1自检，2委托检测，3无
			var checkway;//检测方式:1快速检测，2定量检测
			var checkresult;//合格，不合格
			var basearea;//生产基地
			var scale//规模:规模（面积或年产量）
			
			var cont = '';
			var imgStr = '';
			
			for ( var i = 0; i < result.rows.length; i++) {
					var temcodeImg = ""; //二维码图片
					var temproImg = "";//产品图片
					
					var pro = result.rows[i];
					typeClass =pro.typeClass;
					proName = pro.proName;
					if(proName.length>25){proName=proName.substring(0, 24)};
					typeName= pro.typeName;
					if(typeName.length>16){typeName=typeName.substring(0,15)};
					entName= pro.entName;
					if(entName.length>25){entName=entName.substring(0,24)};
					sourceAddr= pro.sourceAddr;
					if(sourceAddr.length>25){sourceAddr=sourceAddr.substring(0,24)};
					distributorAddr= pro.distributorAddr;
					if(distributorAddr.length>25){distributorAddr=distributorAddr.substring(0,24)};
					
					dimenno= pro.dimenno;
					logoUrl= pro.logoUrl;
					temcodeImg = pro.codeImg;
					proId = pro.proId;
					updateTime = pro.updateTime;
					if(updateTime.length>11){updateTime=updateTime.substring(0, 10)};
					
					productInfo = pro.productInfo;//产品补充信息
					if(productInfo.length>0){
						var info = productInfo[0];
							if(info){
								certificate = info.certificate;//是否认证:1有机、2绿色、3无公害产品、4地理标志认证、5无
								certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
								ischeck = info.ischeck;//是否检测:1自检，2委托检测，3无
								ischeck = ischeck=='1'?"自检":(ischeck=='2'?"委托检测":"无");
								checkway = info.checkway;//检测方式:1快速检测，2定量检测
								checkway = checkway=='1'?"快速检测":"定量检测";
								checkresult= info.checkresult;//检测结果：合格，不合格
								basearea= info.basearea;//生产基地
								scale= info.scale;//规模:规模（面积或年产量）
							}
					}
					
					cont+="<li class=\"bg01\"><table><tbody><tr><th>产品信息</th>" +
							"<td>" +
							"<p>产品名称："+proName+"</p>" +
							"<p>产品二维码："+dimenno+"</p>" +
							"<p>产品标签："+certificate+"</p>" +
							"<p>是否检测："+ischeck+"</p>" +
							"</td>" +
							"<td>" +
							"<p>检测方式："+checkway+"</p>" +
							"<p>检测结果："+checkresult+"</p>" +
							"<p>生产基地："+basearea+"</p>" +
							"<p>规模："+scale+"</p>" +
							"</td>" +
							"</tr></tbody></table></li>";	
					
					//获取产品图片
					appendixList = pro.appendixList;
					if(appendixList.length>0){
						for(var j=0;j<appendixList.length;j++){
							temproImg = appendixList[j].path;
							if(temproImg!=''){
								temproImg = filePath+"proimg/"+temproImg;
								imgStr += "<li><p><a rel=\"cptuxx_group\" href=\""+temproImg+"\" title=\""+proName+"\"><img src=\""+temproImg+"\" height=\"175\"></a></p><p>"+proName+"</p></li>";
							}
						}
					}
			}
			
			if(cont!=''&&imgStr==''){
				$("#company_text").append(section_1+"产品信息"+section_2+cont+section_3+section_7);
			}else if(cont!=''&&imgStr!=''){
				tpmove =1;//打开图片横向滚动开关
				$("#company_text").append(section_1+"产品信息"+section_2+cont+section_3+section_4+"c_scrollPic03"+section_5+imgStr+section_6+section_7);
				//图片横向滚动
				var imgScroll_03 = new funPicScrollX(".c_scrollPic03 .picBox",".c_scrollPic03 .prevBtn span",".c_scrollPic03 .nextBtn span",28,0).init();
				////原料图片预览效果
				$("a[rel=cptuxx_group]").fancybox({
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



	
	

//三品一标
function findThreeaList(recId,divId){
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
			var litr = "";
			var befortr = "<li class=\"bg02\"><table><tbody>" +
					"<tr class=\"thstyle\"><td><p>质量控制措施</p></td><td><p>操作规程</p></td><td><p>产品执行标准</p></td><td><p>保证执行标准声明</p></td><td><p>产地环境检验</p></td></tr>";
			var aftertr = "</tbody></table></li>";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				cuoshi = a.cuoshi;
				guicheng = a.guicheng;
				jianyan = a.jianyan;
				biaozhun = a.biaozhun;
				shengming = a.shengming;
				
				litr += "<tr><td><p>"+cuoshi+"</p></td><td><p>"+guicheng+"</p></td><td><p>"+biaozhun+"</p></td><td><p>"+shengming+"</p></td><td><p>"+jianyan+"</p></td></tr>";
			}
			
			liList = befortr+litr+aftertr;
			if(result.total>0){
				
				$("#"+divId).append(section_1+"三品一标信息"+section_2+liList+section_3+section_7);
			}
			
		
		}
	});
}


//耳标信息
function findEartagList(recId,divId){
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
			var litr = "";
			var befortr = "<li class=\"bg02\"><table><tbody>" +
					"<tr class=\"thstyle\">" +
					"<td><p>耳标编号</p></td>" +
					"<td><p>佩戴时间</p></td>" +
					"<td><p>操作</p></td>" +
					"</tr>";
			var aftertr = "</tbody></table></li>";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				earNo = a.earNo;
				wearTime = a.wearTime;
				var searchUrl = "<a href=\"http://trace1.gdahsi.org/checkJyEbCode_.action?ebcode_="+earNo+"\" target=\"_blank\">查看详细</a>";
				litr += "<tr>" +
						"<td><p>"+earNo+"</p></td>" +
						"<td><p>"+wearTime+"</p></td>" +
						"<td><p>"+searchUrl+"</p></td>" +
						"</tr>";
			}
			liList = befortr+litr+aftertr;
			if(result.total>0){
				$("#"+divId).append(section_1+"耳标信息"+section_2+liList+section_3+section_7);
			}
		}
	});
}


//http://trace1.gdahsi.org/checkJyEbCode_.action?ebcode_=
//检验报告
function findCheckinfoList(recId,divId){
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
			var litr = "";
			var befortr = "<li class=\"bg02\"><table><tbody>" +
					"<tr class=\"thstyle\">" +
					"<td><p>报告名称</p></td>" +
					"<td><p>报告类型</p></td>" +
					"<td><p>检验单位</p></td>" +
					"<td><p>检验时间</p></td>" +
					"<td><p>检验结果</p></td>" +
					"</tr>";
			var aftertr = "</tbody></table></li>";
			var imgStr = "";
			var group = "group"+recId;
			var scrollPic = "c_scrollPic01"+recId;
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				checkName = a.checkName;
				checkUnit = a.checkUnit;
				checkTime = a.checkTime;
				checkResult = a.checkResult;
				checkType = a.checkType;
				elementApp = a.elementApp;//附件list
				if(checkType=='1'){
					checkType = "第三方检测报告";
				}else{
					checkType = "企业自检报告";
				}
				
				litr += "<tr>" +
						"<td><p>"+checkName+"</p></td>" +
						"<td><p>"+checkType+"</p></td>" +
						"<td><p>"+checkUnit+"</p></td>" +
						"<td><p>"+checkTime+"</p></td>" +
						"<td><p>"+checkResult+"</p></td>" +
						"</tr>";
				if(elementApp.length>0){
					for(var j=0;j<elementApp.length;j++){
						var app = elementApp[j];
						var appType =app.appType;
						appType = appType==1?'检验报告':appType;
						var imgCont = "<li><p><a rel=\""+group+"\" href=\""+filePath+"element/"+app.appUrl+"\" title=\""+app.appName+"\"><img src=\""+filePath+"element/"+app.appUrl+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
						imgStr += imgCont;
					}
				}
			}
			liList = befortr+litr+aftertr;
			if(result.total>0&&imgStr==''){
				$("#"+divId).append(section_1+"检验报告信息"+section_2+liList+section_3+section_7);
			}else if(result.total>0&&imgStr.length>0){
				tpmove =1;//打开图片横向滚动开关
				//222:----1+title+2+li+3+4+class+5+tpli+6+7;//有图片附件c_scrollPic01
				$("#"+divId).append(section_1+"检验报告信息"+section_2+liList+section_3+section_4+scrollPic+section_5+imgStr+section_6+section_7);
				//图片横向滚动
				var imgScroll_01 = new funPicScrollX("."+scrollPic+" .picBox","."+scrollPic+" .prevBtn span","."+scrollPic+" .nextBtn span",28,0).init();
				////原料图片预览效果
				$("a[rel="+group+"]").fancybox({
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
function findAgriInputList(recId,divId){
	var agriInput = {};
	agriInput["agriInput.recId"]=recId;
	$.ajax({
   	    url:'portal_Elements_findAgriInputList.action',
   	    data:agriInput,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			var liList = "";
			var litr = "";
			
			var befortr = "<li class=\"bg02\"><table><tbody>" +
					"<tr class=\"thstyle\">" +
					"<td><p>投入品名称</p></td>" +
					"<td><p>投入品分类</p></td>" +
					"<td><p>生产厂家</p></td>" +
					"<td><p>购买人</p></td>" +
					"<td><p>销售商</p></td>" +
					"<td><p>购买日期</p></td>" +
					"<td><p>购买数量</p></td>" +
					"</tr>";

			
			var aftertr = "</tbody></table></li>";
			
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
			
				litr += "<tr>" +
						"<td><p>"+a.agriName+"</p></td>" +
						"<td><p>"+a.typeName+"</p></td>" +
						"<td><p>"+a.agriCompany+"</p></td>" +
						"<td><p>"+a.buyUser+"</p></td>" +
						"<td><p>"+a.buyAddr+"</p></td>" +
						"<td><p>"+a.buyDate+"</p></td>" +
						"<td><p>"+a.buyNum+a.buyUnit+"</p></td>" +
						"</tr>";
			}
			liList = befortr+litr+aftertr;
			
			if(result.total>0){
				$("#"+divId).append(section_1+"投入品购买信息"+section_2+liList+section_3+section_7);
			}
		}
	});
}


//投入品使用记录
function findAgriUseList(recId,divId){
	var agriUse = {};
	agriUse["agriUse.recId"]=recId;
	$.ajax({
   	    url:'portal_Elements_findAgriUseList.action',
   	    data:agriUse,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			var liList = "";
			var litr = "";
			var befortr = "<li class=\"bg02\"><table><tbody>" +
					"<tr class=\"thstyle\">" +
					"<td><p>投入品名称</p></td>" +
					"<td><p>使用对象</p></td>" +
					"<td><p>使用剂量</p></td>" +
					"<td><p>使用日期</p></td>" +
//					"<td><p>停用日期</p></td>" +
					"</tr>";
			var aftertr = "</tbody></table></li>";
			
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				litr += "<tr>" +
						"<td><p>"+a.agriName+"</p></td>" +
						"<td><p>"+a.useObject+"</p></td>" +
						"<td><p>"+a.useDosage+"</p></td>" +
						"<td><p>"+a.useDate+"</p></td>" +
//						"<td><p>"+a.stopDate+"</p></td>" +
						"</tr>";
			}
			liList = befortr+litr+aftertr;
			if(result.total>0){
				$("#"+divId).append(section_1+"投入品使用信息"+section_2+liList+section_3+section_7);
			}
		
		}
	});
}



//采摘收获信息
function findGaininfoList(recId,divId){
	var gaininfo = {};
	gaininfo["gaininfo.recId"]=recId;
	$.ajax({
 	    url:'portal_Elements_findGaininfoList.action',
 	    data:gaininfo,
 	    type:'post',
 	    async : false,
 	    dataType:'json',
 	    success : function(result) {
			
			var liList = "";
			var litr = "";
			var befortr = "<li class=\"bg02\"><table><tbody>" +
					"<tr class=\"thstyle\">" +
					"<td><p>生产基地</p></td>" +
					"<td><p>规模</p></td>" +
					"<td><p>收获日期</p></td>" +
					"<td><p>是否认证</p></td>" +
					"<td><p>是否检测</p></td>" +
					"<td><p>检测方式</p></td>" +
					"<td><p>检测结果</p></td>" +
//					"<td><p>录入时间</p></td>" +
					"</tr>";
			var aftertr = "</tbody></table></li>";
			
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				
				var certificate = a.certificate;//是否认证:1有机、2绿色、3无公害产品、4地理标志认证、5无
				certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
				var ischeck = a.ischeck;//是否检测:1自检，2委托检测，3无
				ischeck = ischeck=='1'?"自检":(ischeck=='2'?"委托检测":"无");
				var checkway = a.checkway;//检测方式:1快速检测，2定量检测
				checkway = checkway=='1'?"快速检测":"定量检测";
				
				litr += "<tr>" +
				"<td><p>"+a.basearea+"</p></td>" +
				"<td><p>"+a.scale+"</p></td>" +
				"<td><p>"+a.harvestDate+"</p></td>" +
				"<td><p>"+certificate+"</p></td>" +
				"<td><p>"+ischeck+"</p></td>" +
				"<td><p>"+checkway+"</p></td>" +
				"<td><p>"+a.checkresult+"</p></td>" +
//				"<td><p>"+a.crttime+"</p></td>" +
				"</tr>";
			}
			liList = befortr+litr+aftertr;
			if(result.total>0){
				$("#"+divId).append(section_1+"采摘收获信息"+section_2+liList+section_3+section_7);
			}
			
		
		}
	});
}


//销售信息
function findSaleinfoList(recId,divId){
	var saleinfo = {};
	saleinfo["saleinfo.recId"]=recId;
	$.ajax({
	    url:'portal_Elements_findSaleinfoList.action',
	    data:saleinfo,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
			
			var liList = "";
			var litr = "";
			var befortr = "<li class=\"bg02\"><table><tbody>" +
					"<tr class=\"thstyle\">" +
					"<td><p>上市日期</p></td>" +
					"<td><p>主要销售地</p></td>" +
					"<td><p>产品包装</p></td>" +
					"<td><p>是否有追溯标识</p></td>" +
					"<td><p>收购厂家</p></td>" +
					"<td><p>收购人</p></td>" +
					"<td><p>销售人</p></td>" +
//					"<td><p>录入时间</p></td>" +
					"</tr>";

			var aftertr = "</tbody></table></li>";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				
				litr += "<tr >" +
				"<td><p>"+a.listed+"</p></td>" +
				"<td><p>"+a.salearea+"</p></td>" +
				"<td><p>"+a.packing+"</p></td>" +
				"<td><p>"+a.havetag+"</p></td>" +
				"<td><p>"+a.sgCompany+"</p></td>" +
				"<td><p>"+a.sgUser+"</p></td>" +
				"<td><p>"+a.saleUser+"</p></td>" +
//				"<td><p>"+a.crttime+"</p></td>" +
				"</tr>";
				
			}
			liList = befortr+litr+aftertr;
			if(result.total>0){
				$("#"+divId).append(section_1+"销售信息"+section_2+liList+section_3+section_7);
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

