/**
 * 查询结果页面
 */
var elementDir ='';
var productionDir ='';
var proimgDir ='';
var zizhiDir ='';
var basePath ='';
var dimenno ='';
var thisEntId='';//保存企业Id；
var companyRsts='';//企业审核状态：4为通过

var ptqId = 0;
var ptq_recId = '';//分类档案id

var html_1 = "<h3 class=\"htit htit-fregray\">";//加上标题
var html_2 = "</h3><ul class=\"info-list m-btm\">";//加上数据，li
var html_3 = "</ul>";
var html_4 = "<div class=\"cutline\"></div>";//空行

var record_1 = "<div class=\"tabdiv\"><table class=\"tabview\" id=\"";//档案table——id
var record_2 = "\"><thead><tr><th>";//加档案名称
var record_3 = "</th></tr></thead></table></div>";//
//record_1+tableId+record_2+"档案名"+record_3

var elements_1 = "<tr><td><h4 class=\"htit htit-fregray\">";//加要素名称
var elements_2 = "</h4><ul class=\"info-list m-btm\">";//加要素信息
//<li><span>企业名称：</span><p>天河ssss</p></li>
var elements_3 = "</ul></td></tr>";//
//elements_1+"要素名称"+elements_2+elementli+elements_3+line_tr

var line_tr = "<tr><td><div class=\"cutline\"></div></td></tr>";//


var element_table1 = "<table class=\"tabview\"><thead>";//
//<tr><th>纯度</th><th>纯度</th><th>纯度</th><th>纯度</th></tr>
var element_table2 = "</thead>";//
//<tr><td>1</td><td>1</td><td>1</td><td>1</td></tr>          	           	
var element_table3 = "</table>";//
				

$(function() {
	elementDir = $("#elementDir").val();
	productionDir = $("#productionDir").val();
	proimgDir = $("#proimgDir").val();
	zizhiDir = $("#zizhiDir").val();
	basePath = $("#basePath").val();
	dimenno = $("#dimenno").val();
	
	var entCode = '';
	entCode = dimenno;
	if(dimenno!=null&&dimenno.length>12){//分类信息
		entCode = entCode.substring(0,12);
		findPtqCompanyInfo();//获取具体分类档案id --ptq_recId;
	}
	
	if(entCode!=''){
		//首先获取企业信息；
		findCompanyInfo(entCode);
		//资质信息
		findZizhiInfo(entCode);
		//监管信息
		findJianguanInfo(entCode);
		// 企业生产信息
//		findProductInfo(entCode);
		
		if(thisEntId!=''){
			//获取企业所有档案
			findCompanyRecord();
		}
		if(companyRsts!='4'){//未审核
			var tips = "<img src=\"static/image/code/tips.png\" align=\"center\" style=\"width: 100%\">";
			showInfo(tips);
		}
	}
});
		
/**
 * 溯源统计
 * @param entId
 * @param entName
 * @return
 */
function scanCount(entId,entName){
	var cond = {};
	cond["scanCount.entId"]=entId;
	cond["scanCount.entName"]=entName;
	$.ajax({
	    url:'scanCount_updateScanCount.action',
	    data:cond,
	    type:'post',
	    dataType:'json',
	    success : function(data) {
			console.info(data);
		}
	}); 
}

//企业信息
function findCompanyInfo(entCode){
	var cond = {};
	cond["entCode"]=entCode;
	$.ajax({
		    url:'mbent_getCompanyInfoByEntCode.action',
		    data:cond,
		    type:'post',
		    async : false,
		    dataType:'json',
		    success : function(data) {
			if(data){
				var company = data[0];
				if(company){
					thisEntId = company.entId;
					companyRsts = company.companyRsts;
					
					//溯源统计
					scanCount(thisEntId,company.name);
				}
				
				var aUlHtml='';
				for(var i=0;i<data.length;i++){
					var a = data[i];
					if(a){
						aUlHtml += '<li><span>企业名称：</span><p>'+a.name+'</p></li>';
						aUlHtml += '<li><span>企业法人：</span><p>'+a.legalPerson+'</p></li>';
						aUlHtml += '<li><span>联系电话：</span><p>'+a.tel+'</p></li>';
						aUlHtml += '<li><span>联系地址：</span><p>'+a.regAddr+'</p></li>';
						aUlHtml += '<li><span>邮政编码：</span><p>'+a.postCode+'</p></li>';
						aUlHtml += '<li><span>电子邮箱：</span><p>'+a.email+'</p></li>';
						aUlHtml += '<li><span>企业网址：</span><p>'+a.domName+'</p></li>';
						aUlHtml += '<li><span>企业介绍：</span><p>'+a.intro+'</p></li>';
					}
				}
				if(aUlHtml!=''){
					$("#company_info").append(html_1+"企业信息"+html_2+aUlHtml+html_3);
				}
				
			}	
	    }
	});
}


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

/////资质信息
function findZizhiInfo(entCode){
	var cond = {};
	cond["entCode"]=entCode;
	cond["rows"]=1000;
	$.ajax({
	    url:'mbzizhi_findZizhiPagerListforMobile.action',
	    data:cond,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(data) {
			var zzUlHtml="";//用来循环保存图片的标题
			for(var i=0;i<data.length;i++){
				var a = data[i];
				if(a){
					var src = zizhiDir+a.path;
    				var appName = a.appName;
					zzUlHtml+= '<li><span><a href="'+src+'"><img src="'+src+'" width="80" height="80"/></a></span><p>'+appName+'</p></li>';
				}
			}
			
			if(zzUlHtml.length>0){
				$("#company_info").append(html_4+html_1+"企业资质信息"+html_2+zzUlHtml+html_3);
			}
		
		}
	}); 
}


//监管信息
function findJianguanInfo(entCode){
	var cond = {};
	cond["entCode"]=entCode;
	cond["ids"]=entCode;
	$.post('mbsupervise_findSuperviseList.action',cond,function(result){
		var jgUlHtml = "";			
		for(var i=0;i<result.length;i++){
			var supervise = result[i];
			if(supervise){
				jgUlHtml += '<li><span>'+supervise.title+'：</span><p>'+supervise.contents+'</p></li>';
			}
		}
		if(jgUlHtml.length>0){
			$("#company_info").append(html_4+html_1+"监管信息"+html_2+jgUlHtml+html_3);
		}	
	},'JSON');
}

// 企业生产信息
function findProductInfo(entCode){
	var cond = {};
	cond["entCode"]=entCode;
	cond["ids"]=entCode;
	$.ajax({
   	    url:'mbproduction_findProductForMobile.action',
   	    data:cond,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(data) {
			var rows = data[0].data;
			var UlHtml = "";
			for(var i=0;i<rows.length;i++){
				var obj = rows[i];
				var appList = obj.appList;
				
				UlHtml+= '<li><span>生产情况'+i+'：</span><p>'+obj.productinfo+'</p></li>';
				
				if(appList.length>0){//存在图片
					
					for(var j=0;j<appList.length;j++){
						var app = appList[j];
						if(app){
							var src = productionDir+app.path;
		    				var appName = app.appName;
		    				var appType =app.appType;
							appType = appType==1?'产品图片':'生产许可证扫描件';
		    				UlHtml+= '<li><span><a href="'+src+'"><img src="'+src+'" width="80" height="80"/></a></span><p>'+appType+'</p></li>';
						}
						
					}
				}
			}
			if(UlHtml.length>0){
				$("#company_info").append(html_4+html_1+"生产信息"+html_2+UlHtml+html_3);
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
							if(objTypeId==1||recId==ptq_recId){//
								var article_id = "article_id"+i;
								fristHtml += record_1+article_id+record_2+record.recName+record_3;
							}
						}else{					//所有
							var article_id = "article_id"+i;
							fristHtml += record_1+article_id+record_2+record.recName+record_3;
						}
					}
				}
				$(".wrap-container").append(fristHtml);
				
				for(var j=0;j<recordList.length;j++){//第二次循环获取档案要素内容
					//获取档案
					var record = recordList[j];
					if(record){
						var recId = record.recId;
						var objId = record.objId;
						var objTypeId = record.objTypeId;//企业1,2；分类二维码3,4
						
						if(dimenno.length>12){	//主体和具体分类
							if(objTypeId==1||recId==ptq_recId){//
								if(recId==ptq_recId){//种类档案，则先获取分类基本信息
									var ptq = {};
									ptq["ptq.ptqId"]=objId;
									ptq["ptq.entId"]=thisEntId;
									findPtqInfo(ptq,"article_id"+j);
								}
								//获取档案要素
								findRecordElement(recId,"article_id"+j);
							}
						}else{					//所有
							if(objTypeId==3||objTypeId==4){ //种类档案，则先获取分类基本信息
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
function findPtqInfo(ptq,divId){
	
	$.ajax({
		url:'portal_Company_findProTypeQrcodeList.action',
 	    data:ptq,
 	    type:'post',
 	    async : false,
 	    dataType:'json',
 	    success : function(result) {
			var liList = "";
			var cddkList = "";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				var certificate = a.certificate;//1有机、2绿色、3无公害产品、4地理标志认证、5无
				certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
				var chandi = "";
				var areaImg = area.areaImg;//基地平面图；
				var areaImgStr = "无";
				if(areaImg!=''){
					areaImgStr = "<a href=\""+elementDir+areaImg+"\"  target=\"_blank\"><img src=\""+elementDir+areaImg+"\" width=\"50\" height=\"50\"/></a>";
				}
				
				var b = a.proTypeArea;//基地列表
				var massifList = a.chlist;//基地地块列表
				if(b.length>0){
					for(var j=0;j<b.length;j++){
						var scpara =  b[j].scale;
						if(scpara!=''){
							chandi += b[j].chandi+"：" + scpara +"；<br>";
						}else{
							chandi += b[j].chandi+"<br>";
						}
						
						
						
						
					}
				}
				if(massifList.length>0){
					for(var k=0;k<massifList.length;k++){
						cddk_length = massifList.length;
						var massif = massifList[k];
						
						var areaName = massif.areaName;
						var maName = massif.maName;//地块名称
						var maAcreage = massif.maAcreage;//地块面积
						var startTime = massif.startTime;//种植时间
						var getTime = massif.getTime;//收获时间
						var state = massif.state;//
						var maImg = massif.maImg;//地块平面图
						var typeName = massif.typeName;//
						var imgStr = "无";
						if(maImg!=''){
							imgStr = "<a href=\""+proimgDir+maImg+"\" target=\"_blank\"><img src=\""+proimgDir+maImg+"\" width=\"50\" height=\"50\"/></a>";
						}
						cddkList+="<li><span>所属基地：</span><p>"+areaName+"</p></li>";
						cddkList+="<li><span>地块名称：</span><p>"+maName+"</p></li>";
						cddkList+="<li><span>地块面积：</span><p>"+maAcreage+"</p></li>";
						cddkList+="<li><span>种植时间：</span><p>"+startTime+"</p></li>";
						cddkList+="<li><span>收获时间：</span><p>"+getTime+"</p></li>";
						cddkList+="<li><span>地块平面图：</span><p>"+imgStr+"</p></li>";
						
					}
				}
				
				liList+="<li><span>种类名称：</span><p>"+a.typeName+"</p></li>";
				liList+="<li><span>是否认证：</span><p>"+certificate+"</p></li>";
				liList+="<li><span>年商品量：</span><p>"+a.quantity+"(吨)</p></li>";
				liList+="<li><span>上市期：</span><p>"+a.listed+"</p></li>";
				liList+="<li><span>主要销售地：</span><p>"+a.salearea+"</p></li>";
				liList+="<li><span>基地及规模：</span><p>"+chandi+"</p></li>";
				liList+="<li><span>基地平面图：</span><p>"+areaImgStr+"</p></li>";
				
				if(liList!=''){
					$("#"+divId).append(elements_1+"种类基本信息"+elements_2+liList+elements_3+line_tr);
					liList = "";
				}
				if(cddkList!=''){
					$("#"+divId).append(elements_1+"种类地块信息"+elements_2+cddkList+elements_3+line_tr);
					cddkList = "";
				}
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
						}else if(tableName=='tb_fertilizer_use'){//肥料投入品使用记录
							findFertilizerUseList(recId,divId);
						}
					}
				}
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
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				cuoshi = a.cuoshi;
				guicheng = a.guicheng;
				jianyan = a.jianyan;
				biaozhun = a.biaozhun;
				shengming = a.shengming;
				
				liList+="<li><span>生产质量控制措施：</span><p>"+cuoshi+"</p></li>";
				liList+="<li><span>生产操作规程：</span><p>"+guicheng+"</p></li>";
				liList+="<li><span>产地环境检验：</span><p>"+jianyan+"</p></li>";
				liList+="<li><span>产品执行标准：</span><p>"+biaozhun+"</p></li>";
				liList+="<li><span>保证执行标准声明：</span><p>"+shengming+"</p></li>";
				
				if(liList!=''){
					$("#"+divId).append(elements_1+"三品一标信息"+elements_2+liList+elements_3+line_tr);
					liList = "";
				}
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
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				earNo = a.earNo;
				wearTime = a.wearTime;
				var searchUrl = "<a href=\"http://trace1.gdahsi.org/checkJyEbCode_.action?ebcode_="+earNo+"\" target=\"_blank\">查看详细</a>";
				liList+="<li><span>耳标编号：</span><p>"+earNo+"</p></li>";
//				liList+="<li><span>佩戴时间：</span><p>"+wearTime+"</p></li>";
				liList+="<li><span>操作：</span><p>"+searchUrl+"</p></li>";
				
				if(liList!=''){
					$("#"+divId).append(elements_1+"耳标信息"+elements_2+liList+elements_3+line_tr);
					liList = "";
				}
			}
		}
	});
}



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
			var checkType = "";//1第三方检测报告；2企业自检报告；
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
					checkType = "第三方检测报告";
				}else{
					checkType = "企业自检报告";
				}
				liList+="<li><span>报告类型：</span><p>"+checkType+"</p></li>";
				liList+="<li><span>报告名称：</span><p>"+checkName+"</p></li>";
				liList+="<li><span>检验单位：</span><p>"+checkUnit+"</p></li>";
				liList+="<li><span>检验时间：</span><p>"+checkTime+"</p></li>";
				liList+="<li><span>检验结果：</span><p>"+checkResult+"</p></li>";
				
				if(elementApp.length>0){
					for(var j=0;j<elementApp.length;j++){
						var app = elementApp[j];
						if(app){
							var appType =app.appType;
							appType = appType==1?'检验报告':appType;
							var src = elementDir+app.appUrl;
		    				var appName = app.appName;
							var imgCont =  '<li><span><a href="'+src+'"><img src="'+src+'" width="80" height="80"/></a></span><p>'+appName+'</p></li>';
							imgStr += imgCont;
						}
					}
				}
				liList+=imgStr;
				if(liList!=''){
					$("#"+divId).append(elements_1+"检验报告信息"+elements_2+liList+elements_3+line_tr);
					liList = "";
				}
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
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				liList+="<li><span>投入品名称：</span><p>"+a.agriName+"</p></li>";
				liList+="<li><span>投入品分类：</span><p>"+a.typeName+"</p></li>";
				liList+="<li><span>生产厂家：</span><p>"+a.agriCompany+"</p></li>";
				liList+="<li><span>购买人：</span><p>"+a.buyUser+"</p></li>";
				liList+="<li><span>销售商：</span><p>"+a.buyAddr+"</p></li>";
				liList+="<li><span>购买日期：</span><p>"+a.buyDate+"</p></li>";
				liList+="<li><span>购买数量：</span><p>"+a.buyNum+a.buyUnit+"</p></li>";
				
				if(liList!=''){
					$("#"+divId).append(elements_1+"投入品购买信息"+elements_2+liList+elements_3+line_tr);
					liList = "";
				}
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
				liList+="<li><span>投入品名称：</span><p>"+a.agriName+"</p></li>";
				liList+="<li><span>使用日期：</span><p>"+a.useDate+"</p></li>";
				liList+="<li><span>使用对象：</span><p>"+a.useObject+"</p></li>";
				liList+="<li><span>使用剂量：</span><p>"+a.useDosage+"</p></li>";
				liList+="<li><span>施用量：</span><p>"+a.useTotal+"</p></li>";
				liList+="<li><span>施用方法：</span><p>"+a.useWay+"</p></li>";
				liList+="<li><span>安全隔离天数：</span><p>"+a.safeDay+"</p></li>";
				liList+="<li><span>安全隔离期：</span><p>"+a.safeDate+"</p></li>";
				liList+="<li><span>施用人：</span><p>"+a.useMan+"</p></li>";
				
				if(liList!=''){
					$("#"+divId).append(elements_1+"农药投入品使用信息"+elements_2+liList+elements_3+line_tr);
					liList = "";
				}
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
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				
				var certificate = a.certificate;//是否认证:1有机、2绿色、3无公害产品、4地理标志认证、5无
				certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
				var ischeck = a.ischeck;//是否检测:1自检，2委托检测，3无
				ischeck = ischeck=='1'?"自检":(ischeck=='2'?"委托检测":"无");
				var checkway = a.checkway;//检测方式:1快速检测，2定量检测
				checkway = checkway=='1'?"快速检测":"定量检测";
				
				liList+="<li><span>收获日期：</span><p>"+a.harvestDate+"</p></li>";
				liList+="<li><span>生产基地：</span><p>"+a.basearea+"</p></li>";
				liList+="<li><span>规模：</span><p>"+a.scale+"</p></li>";
//				liList+="<li><span>录入时间：</span><p>"+a.crttime+"</p></li>";
				liList+="<li><span>是否认证：</span><p>"+certificate+"</p></li>";
				liList+="<li><span>是否检测：</span><p>"+ischeck+"</p></li>";
				liList+="<li><span>检测方式：</span><p>"+checkway+"</p></li>";
				liList+="<li><span>检测结果：</span><p>"+a.checkresult+"</p></li>";
				
				if(liList!=''){
					$("#"+divId).append(elements_1+"采摘收获信息"+elements_2+liList+elements_3+line_tr);
					liList = "";
				}
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
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				
				liList+="<li><span>上市期：</span><p>"+a.listed+"</p></li>";
				liList+="<li><span>主要销售地：</span><p>"+a.salearea+"</p></li>";
				liList+="<li><span>产品包装：</span><p>"+a.packing+"</p></li>";
				liList+="<li><span>是否有追溯标识：</span><p>"+a.havetag+"</p></li>";
				liList+="<li><span>收购厂家：</span><p>"+a.sgCompany+"</p></li>";
				liList+="<li><span>收购人：</span><p>"+a.sgUser+"</p></li>";
				liList+="<li><span>销售人：</span><p>"+a.saleUser+"</p></li>";
//				liList+="<li><span>录入时间：</span><p>"+a.crttime+"</p></li>";
				
				if(liList!=''){
					$("#"+divId).append(elements_1+"销售信息"+elements_2+liList+elements_3+line_tr);
					liList = "";
				}
			}
		
		}
	});
}


//获取肥料使用记录
function findFertilizerUseList(recId,divId){
	var fertilizerUse = {};
	fertilizerUse["fertilizerUse.recId"]=recId;
	$.ajax({
	    url:'portal_Elements_findFertilizerUseList.action',
	    data:fertilizerUse,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
	    	var liList = "";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				
				liList+="<li><span>肥料名称：</span><p>"+a.funame+"</p></li>";
				liList+="<li><span>施用目的：</span><p>"+a.purpose+"</p></li>";
				liList+="<li><span>施用面积：</span><p>"+a.userarea+"</p></li>";
				liList+="<li><span>施用量：</span><p>"+a.usertotal+"</p></li>";
				liList+="<li><span>使用日期：</span><p>"+a.usedate+"</p></li>";
				liList+="<li><span>施用人：</span><p>"+a.useMan+"</p></li>";
				if(liList!=''){
					$("#"+divId).append(elements_1+"肥料投入品使用记录"+elements_2+liList+elements_3+line_tr);
					liList = "";
				}
			}
		
		}
	});
}



//水印提示；审核信息；
function showInfo(tips){
	layer.open({
        type: 1,
        closeBtn: false,//不关闭
        title: false, //不显示默认标题栏
//        shade: [0], //不显示遮罩
        shade: [0.8, '#393D49'],
        area: ['80%', '100px'],
        content: tips
    });
}
