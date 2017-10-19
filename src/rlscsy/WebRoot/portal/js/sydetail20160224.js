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

var header_1 = "<div class=\"isrecord\" id=\"";//需要加上article_id
var header_2 = "\"><div class=\"tc font24 light-grey mt20\">——";//需加上档案名称
var header_3 = "——</div></div>";
//header_1 + (article_id) + header_2 + (档案名) + header_3;

var section_1 = "<div class=\"line_bb\"><span class=\"font18\">";//加上要素名称
var section_2 = "</span></div><div class=\"mt20 hidden ml40\"><div class=\"div_table\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
var section_3 = "</table></div></div>";
//section_1 + (要素名) + section_2 + (trlist) + section_3;


var simg_1 = "<div class=\"line_bb\"><span class=\"font18\">";//企业资质
var simg_2 = "</span></div><div class=\"mt20 hidden ml40\"><div>";
var simg_3 = "</div></div>";
//simg_1 + (要素名) + simg_2 + (divlist) + simg_3;


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
	
	//资质信息
	findZizhiInfo();
	//监管信息
	findJianguanInfo();
	
	
	if(thisEntId!=''){
		//获取企业所有档案
		findCompanyRecord();
	}
	
	
	
////////////////////////////////new search end ///////////////////////////////
	
	
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
								//header_1 + (article_id) + header_2 + (档案名) + header_3;
								fristHtml += header_1+article_id+header_2+record.recName+header_3;
								
								$(".results_nav").append("<a  href=\"#"+article_id+"\" class=\"taptitle\">"+record.recName+"</a>");
								
							}
						}else{					//所有
							var article_id = "article_id"+i;
							fristHtml += header_1+article_id+header_2+record.recName+header_3;
							
							$(".results_nav").append("<a href=\"#"+article_id+"\" class=\"taptitle\">"+record.recName+"</a>");
						}
					}
				}
				
				$(".div_content").append(fristHtml);
				
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
var zlxx_start_html = "<tr bgcolor=\"#f2f2f2\"><td>(种类信息)</td></tr><tr bgcolor=\"#f2f2f2\"><td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody>";
var zlxx_end_html = "</tbody></table></td></tr>";

var cdxx_start_html = "<tr bgcolor=\"#f2f2f2\"><td>(基地信息)</td></tr><tr bgcolor=\"#f2f2f2\"><td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody>";
var cdxx_end_html = "</tbody></table></td></tr>";

var cddk_start_html = "<tr bgcolor=\"#f2f2f2\"><td>(地块信息)</td></tr><tr bgcolor=\"#f2f2f2\"><td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody>";
var cddk_end_html = "</tbody></table></td></tr>";

var zlxx_length;
var cdxx_length;
var cddk_length;
function findPtqInfo(ptq,divId){
	
	$.ajax({
		url:'portal_Company_findProTypeQrcodeList.action',
 	    data:ptq,
 	    type:'post',
 	    async : false,
 	    dataType:'json',
 	    success : function(result) {
			var liList = "";
			var zlxx_befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>种类名称</td>" +
					"<td>是否认证</td>" +
					"<td>年商品量(吨)</td>" +
					"<td>上市期</td>" +
					"<td>主要销售地</td>" +
					"</tr>";
			var cdxx_befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>基地名称</td>" +
					"<td>基地地址</td>" +
//					"<td>面积</td>" +
					"<td>规模</td>" +
					"<td>基地平面图</td>" +
					"</tr>";
			var cddk_befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>所属基地</td>" +
					"<td>地块名称</td>" +
					"<td>地块面积</td>" +
					"<td>种植时间</td>" +
					"<td>收获时间</td>" +
					"<td>地块平面图</td>" +
					"</tr>";
			for(var i=0;i<result.rows.length;i++){
				zlxx_length = result.rows.length;
				var a = result.rows[i];
				var certificate = a.certificate;//1有机、2绿色、3无公害产品、4地理标志认证、5无
				certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
				var chandi = "";
				var b = a.proTypeArea;
				var massifList = a.chlist;//基地地块列表
				if(b.length>0){
					for(var j=0;j<b.length;j++){
						cdxx_length = b.length;
						var area = b[j];
						var areaAddr =  area.areaAddr;//地址
						if(areaAddr!=''){
							chandi += area.chandi+"：" + areaAddr +"；<br>";
						}else{
							chandi += area.chandi+"<br>";
						}
						
						var scale = area.scale;//规模
						var areaName = area.areaName;//基地名称
						var areaAcreage = area.areaAcreage;//基地面积
						var areaValue  = area.areaValue;//预产值；
						var areaImg = area.areaImg;//基地平面图；
						
						var areaImgStr = "无";
						if(areaImg!=''){
							areaImgStr = "<a rel=\"cdxx_group\" href=\""+filePath+"/element/"+areaImg+"\" style=\"color: #808080\">查看</a>";
						}
						
						cdxx_befortr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
							"<td>"+areaName+"</td>" +
							"<td>"+chandi+"</td>" +
//							"<td>"+areaAcreage+"</td>" +
							"<td>"+scale+"</td>" +
							"<td>"+areaImgStr+"</td>" +
							"</tr>";
						
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
							imgStr = "<a rel=\"dkxx_group\" href=\""+filePath+"/proimg/"+maImg+"\" style=\"color: #808080\">查看</a>";
						}
						
						cddk_befortr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
							"<td>"+areaName+"</td>" +
							"<td>"+maName+"</td>" +
							"<td>"+maAcreage+"</td>" +
							"<td>"+startTime+"</td>" +
							"<td>"+getTime+"</td>" +
							"<td>"+imgStr+"</td>" +
							"</tr>";
					}
				}
				
				
				zlxx_befortr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
						"<td>"+a.typeName+"</td>" +
						"<td>"+certificate+"</td>" +
						"<td>"+a.quantity+"</td>" +
						"<td>"+a.listed+"</td>" +
						"<td>"+a.salearea+"</td>" +
						"</tr>";
			}
			
			if(zlxx_length>0){
				liList = liList+zlxx_start_html + zlxx_befortr+zlxx_end_html;
			}
			if(cdxx_length>0){
				liList = liList+cdxx_start_html + cdxx_befortr+cdxx_end_html;
			}
			if(cddk_length>0){
				liList = liList+cddk_start_html + cddk_befortr+cddk_end_html;
			}
			if(result.total>0){
				//section_1 + (要素名) + section_2 + (trlist) + section_3;
				$("#"+divId).append(section_1+"种类基本信息"+section_2+liList+section_3);
			}
			if(cdxx_length>0){
				$("a[rel=cdxx_group]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}
			if(cddk_length>0){
				$("a[rel=dkxx_group]").fancybox({
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
						}else if(tableName=='tb_agri_use'){//农药投入品使用记录
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
				for(var i=0;i<data.length;i++){
					var a = data[i];
					
					$("#qyjbxx").html(
							"<p class='lh30'><strong>企业名称："+a.name+"</strong></p>"+
						    "<p class='lh30'>企业简称："+a.simpleName+"</p>"+
						    "<p class='lh30'>溯源码："+a.entCode+"</p>"+
						    "<p class='lh30'>企业法人："+a.legalPerson+"</p>" +
						    "<p class='lh30'>邮政编码："+a.postCode+"</p>"
						    );
					
					$("#qylxfs").html("" +
						    "<p class='lh30'>联系电话："+a.tel+"</p>"+
						    "<p class='lh30'>联系邮箱："+a.email+"</p>"+
						    "<p class='lh30'>注册地址："+a.regAddr+"</p>"+
						    "<p class='lh30'>经营地址："+a.manageAddr+"</p>"+
						    "<p class='lh30'>企业网址："+a.domName+"</p>"
							);
					
					$("#qyjj").html(a.intro);
					
				}
				if(data.length>0){
					thisEntId=data[0].entId;
					companyRsts = data[0].companyRsts;
					//溯源统计
//					scanCount(thisEntId,data[0].name);
			}
	    }
	});
}

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
			var jgsb="";//用来循环保存图片
		
			for(var i=0;i<data.length;i++){
				var a = data[i];
				jgsb+="<div class=\"div_zi_img\"><p><a rel=\"example_group\" href=\""+filePath+"zizhi/"+a.path+"\" title=\""+a.appName+"\"><img src=\""+filePath+"zizhi/"+a.path+"\" height=\"175\"></a></p><p>"+a.zName+"</p></div>";
			}
			
			if(data.length>0&&jgsb.length>0){
				//simg_1 + (要素名) + simg_2 + (divlist) + simg_3;
				$(".entdetail").append(simg_1+"企业资质"+simg_2+jgsb+simg_3);
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
			ht+="<table><tbody><tr><th>"+supervise.title+"</th>" +
					"<td><p>"+supervise.contents+"</p></td>" +
					"</tr></tbody></table>";
		}
		if(result.length>0&&ht.length>0){
			//simg_1 + (要素名) + simg_2 + (divlist) + simg_3;
			var jgxx = "<div class=\"line_bb\"><span class=\"font18\">监管信息</span></div><div class=\"mt20 hidden ml40\"><div class=\"div_txt\">";
			$(".entdetail").append(jgxx+ht+"</div></div>");
		}
	},'JSON');
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
			var jianyan = "";//基地环境检验
			var biaozhun = "";//产品执行标准/
			var shengming = "";//保证执行标准声明
			var liList = "";
			var litr = "";
			var befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>质量控制措施</td>" +
					"<td>操作规程</td>" +
					"<td>产品执行标准</td>" +
					"<td>保证执行标准声明</td>" +
					"<td>产地环境检验</td>" +
					"</tr>";
			var aftertr = "</tbody></table></li>";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				cuoshi = a.cuoshi;
				guicheng = a.guicheng;
				jianyan = a.jianyan;
				biaozhun = a.biaozhun;
				shengming = a.shengming;
				
				litr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
						"<td>"+cuoshi+"</td>" +
						"<td>"+guicheng+"</td>" +
						"<td>"+biaozhun+"</td>" +
						"<td>"+shengming+"</td>" +
						"<td>"+jianyan+"</td>" +
						"</tr>";
			}
			
			liList = befortr+litr+aftertr;
			if(result.total>0){
				//section_1 + (要素名) + section_2 + (trlist) + section_3;
				$("#"+divId).append(section_1+"三品一标信息"+section_2+liList+section_3);
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
			var befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>耳标编号</td>" +
//					"<td>佩戴时间</td>" +
					"<td>操作</td>" +
					"</tr>";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				earNo = a.earNo;
				wearTime = a.wearTime;
				var searchUrl = "<a href=\"http://trace1.gdahsi.org/checkJyEbCode_.action?ebcode_="+earNo+"\" target=\"_blank\">查看详细</a>";
				litr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
						"<td>"+earNo+"</td>" +
//						"<td>"+wearTime+"</td>" +
						"<td>"+searchUrl+"</td>" +
						"</tr>";
			}
			liList = befortr+litr;
			if(result.total>0){
				$("#"+divId).append(section_1+"耳标信息"+section_2+liList+section_3);
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
			var befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>报告名称</td>" +
					"<td>报告类型</td>" +
					"<td>检验单位</td>" +
					"<td>检验时间</td>" +
					"<td>检验结果</td>" +
					"</tr>";
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
				
				litr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
						"<td>"+checkName+"</td>" +
						"<td>"+checkType+"</td>" +
						"<td>"+checkUnit+"</td>" +
						"<td>"+checkTime+"</td>" +
						"<td>"+checkResult+"</td>" +
						"</tr>";
				if(elementApp.length>0){
					for(var j=0;j<elementApp.length;j++){
						var app = elementApp[j];
						var appType =app.appType;
						appType = appType==1?'检验报告':appType;
						var imgCont = "<div class=\"div_zi_img\"><a rel=\""+group+"\" href=\""+filePath+"element/"+app.appUrl+"\" title=\""+app.appName+"\"><img src=\""+filePath+"element/"+app.appUrl+"\" height=\"175\"></a>"+appType+"</div>";
						imgStr += imgCont;
					}
				}
			}
			liList = befortr+litr;
			if(result.total>0&&imgStr==''){
				$("#"+divId).append(section_1+"检验报告信息"+section_2+liList+section_3);
			}else if(result.total>0&&imgStr.length>0){
				//222:----1+title+2+li+3+4+class+5+tpli+6+7;//有图片附件c_scrollPic01
				$("#"+divId).append(section_1+"检验报告信息"+section_2+liList+section_3+"<div>"+imgStr+"</div>");
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
			
			var befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>投入品名称</td>" +
					"<td>投入品分类</td>" +
					"<td>生产厂家</td>" +
					"<td>购买人</td>" +
					"<td>销售商</td>" +
					"<td>购买日期</td>" +
					"<td>购买数量</td>" +
					"</tr>";

			
			
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
			
				litr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
						"<td>"+a.agriName+"</td>" +
						"<td>"+a.typeName+"</td>" +
						"<td>"+a.agriCompany+"</td>" +
						"<td>"+a.buyUser+"</td>" +
						"<td>"+a.buyAddr+"</td>" +
						"<td>"+a.buyDate+"</td>" +
						"<td>"+a.buyNum+a.buyUnit+"</td>" +
						"</tr>";
			}
			liList = befortr+litr;
			
			if(result.total>0){
				$("#"+divId).append(section_1+"投入品购买信息"+section_2+liList+section_3);
			}
		}
	});
}


//农药投入品使用记录
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
			
			var befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>投入品名称</td>" +
					"<td>使用对象</td>" +
					"<td>使用剂量</td>" +
					"<td>使用日期</td>" +
					"<td>施用量</td>" +
					"<td>施用方法</td>" +
					"<td>安全隔离天数</td>" +
					"<td>安全隔离期</td>" +
					"<td>施用人</td>" +
					"</tr>";
			
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				litr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
						"<td>"+a.agriName+"</td>" +
						"<td>"+a.useObject+"</td>" +
						"<td>"+a.useDosage+"</td>" +
						"<td>"+a.useDate+"</td>" +
						"<td>"+a.useTotal+"</td>" +
						"<td>"+a.useWay+"</td>" +
						"<td>"+a.safeDay+"</td>" +
						"<td>"+a.safeDate+"</td>" +
						"<td>"+a.useMan+"</td>" +
						"</tr>";
			}
			liList = befortr+litr;
			if(result.total>0){
				$("#"+divId).append(section_1+"农药投入品使用信息"+section_2+liList+section_3);
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
			var befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>生产基地</td>" +
					"<td>规模</td>" +
					"<td>收获日期</td>" +
					"<td>是否认证</td>" +
					"<td>是否检测</td>" +
					"<td>检测方式</td>" +
					"<td>检测结果</td>" +
//					"<td>录入时间</td>" +
					"</tr>";
			
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				
				var certificate = a.certificate;//是否认证:1有机、2绿色、3无公害产品、4地理标志认证、5无
				certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
				var ischeck = a.ischeck;//是否检测:1自检，2委托检测，3无
				ischeck = ischeck=='1'?"自检":(ischeck=='2'?"委托检测":"无");
				var checkway = a.checkway;//检测方式:1快速检测，2定量检测
				checkway = checkway=='1'?"快速检测":"定量检测";
				
				litr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
				"<td>"+a.basearea+"</td>" +
				"<td>"+a.scale+"</td>" +
				"<td>"+a.harvestDate+"</td>" +
				"<td>"+certificate+"</td>" +
				"<td>"+ischeck+"</td>" +
				"<td>"+checkway+"</td>" +
				"<td>"+a.checkresult+"</td>" +
//				"<td>"+a.crttime+"</td>" +
				"</tr>";
			}
			liList = befortr+litr;
			if(result.total>0){
				$("#"+divId).append(section_1+"采摘收获信息"+section_2+liList+section_3);
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
			var befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>上市日期</td>" +
					"<td>主要销售地</td>" +
					"<td>产品包装</td>" +
					"<td>是否有追溯标识</td>" +
					"<td>收购厂家</td>" +
					"<td>收购人</td>" +
					"<td>销售人</td>" +
//					"<td>录入时间</td>" +
					"</tr>";

			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				
				litr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
				"<td>"+a.listed+"</td>" +
				"<td>"+a.salearea+"</td>" +
				"<td>"+a.packing+"</td>" +
				"<td>"+a.havetag+"</td>" +
				"<td>"+a.sgCompany+"</td>" +
				"<td>"+a.sgUser+"</td>" +
				"<td>"+a.saleUser+"</td>" +
//				"<td>"+a.crttime+"</td>" +
				"</tr>";
				
			}
			liList = befortr+litr;
			if(result.total>0){
				$("#"+divId).append(section_1+"销售信息"+section_2+liList+section_3);
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
			var litr = "";
			var befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>肥料名称</td>" +
					"<td>施用目的</td>" +
					"<td>施用面积</td>" +
					"<td>施用量</td>" +
					"<td>使用日期</td>" +
					"</tr>";
			
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				litr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
						"<td>"+a.funame+"</td>" +
						"<td>"+a.purpose+"</td>" +
						"<td>"+a.userarea+"</td>" +
						"<td>"+a.usertotal+"</td>" +
						"<td>"+a.usedate+"</td>" +
						"</tr>";
			}
			liList = befortr+litr;
			if(result.total>0){
				$("#"+divId).append(section_1+"肥料投入品使用记录"+section_2+liList+section_3);
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






/*
 * 2015-12-22 bak
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
			var liList = "";
			var litr = "";
			var befortr = "<tr bgcolor=\"#f2f2f2\">" +
					"<td>种类名称</td>" +
					"<td>是否认证</td>" +
					"<td>年商品量(吨)</td>" +
					"<td>上市期</td>" +
					"<td>主要销售地</td>" +
					"<td>基地及规模</td>" +
					"</tr>";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				var certificate = a.certificate;//1有机、2绿色、3无公害产品、4地理标志认证、5无
				certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
				var chandi = "";
				var b = a.proTypeArea;
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
				litr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\">" +
						"<td>"+a.typeName+"</td>" +
						"<td>"+certificate+"</td>" +
						"<td>"+a.quantity+"</td>" +
						"<td>"+a.listed+"</td>" +
						"<td>"+a.salearea+"</td>" +
						"<td>"+chandi+"</td>" +
						"</tr>";
			}
	
			liList = befortr+litr;
			if(result.total>0){
				//section_1 + (要素名) + section_2 + (trlist) + section_3;
				$("#"+divId).append(section_1+"种类基本信息"+section_2+liList+section_3);
			}
		}
	});
}
*/
