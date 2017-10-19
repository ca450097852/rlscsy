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
var entisbatch = 0;//是否批次追溯 0否，1是。默认0
var companyRsts='';//企业审核状态：4为通过

var html_1 = "<h3 class=\"htit htit-fregray\">";//加上标题
var html_2 = "</h3><ul class=\"info-list m-btm\">";//加上数据，li
var html_3 = "</ul>";
var html_4 = "<div class=\"cutline\"></div>";//空行

var record_1 = "<div class=\"tabdiv\"><table class=\"tabview\" id=\"";//档案table——id
var record_2 = "\"><thead><tr><th>";//加档案名称
var record_3 = "</th></tr></thead></table></div>";//

var elements_1 = "<h4 class=\"htit htit-fregray\">";//加要素名称

var elements_2 = "</h4><ul class=\"info-list m-btm\">";//加要素信息
var elements_3 = "</ul>";//


var line_tr = "<div class=\"cutline\"></div>";//


var element_table1 = "<table class=\"tabview\"><thead>";//
var element_table2 = "</thead>";//
var element_table3 = "</table>";//
				


var headarr = new Array();//保存li的html数据
var sectionarr = new Array();//保存section的外层html数据
var recordarr = new Array();//保存section的里面的html数据

/*上面三个数组，按下标一一对应*/

headarr[1] = '<a class="weui-navbar__item weui-bar__item--on" href="#section-circlefill-1">企业信息</a>';
sectionarr[1] = '<div id="section-circlefill-1" class="weui-tab__bd-item weui-tab__bd-item--active"></div>';

headarr[2] = '<a class="weui-navbar__item" href="#section-circlefill-2">资质信息</a>';
sectionarr[2] = '<div id="section-circlefill-2" class="weui-tab__bd-item"></div>';

headarr[3] = '<a class="weui-navbar__item" href="#section-circlefill-3">档案信息</a>';
sectionarr[3] = '<div id="section-circlefill-3" class="weui-tab__bd-item"><div style="border-bottom:#ccc 1px solid; padding:0 15px 10px 15px" id="recordDiv"></div></div>';

var nav_count = 4;//数组下标；

var iscompile = 1;//企业数据是否完善？0为否

var thiscompanyinfo;//当前企业的基本信息；
var baseAid = "";//当前动物检疫证号
$(function() {
	
	elementDir = $("#elementDir").val();
	productionDir = $("#productionDir").val();
	proimgDir = $("#proimgDir").val();
	zizhiDir = $("#zizhiDir").val();
	basePath = $("#basePath").val();
	dimenno = $("#dimenno").val();
	
	if(dimenno!=null&&dimenno.length==16){//批次码
		/*步骤
		 * 1、根据批次码查询批次表信息，获取comId（entId）、ptbId、ptqId
		 * findPTBinfoByDimenno(dimenno)
		 * 2、根据ptqId查询种类信息
		 * 3、根据comId查询企业信息，获取comType
		 * findCompanyInfoByComId(comId)
		 * 4、结合comType和ptbId查询具体要素。先获取
		 * */
		var ptbinfo = findPTBinfoByDimenno(dimenno);
		if(ptbinfo){
			var comId = ptbinfo.entId;
			var ptqId = ptbinfo.ptqId;
			var ptbId = ptbinfo.ptbId;
			var batchName = ptbinfo.batchName;
					
			//根据ptqId查询种类信息
			var ptqinfo = getProTypeQrcodeById(ptqId);
			var ptqHtml = "";
			if(ptqinfo){
				var proName = ptqinfo.proName;//产品名称
				var certificate = ptqinfo.certificate;//是否认证
				certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
				var quantity = ptqinfo.quantity;//年商品量
				var listed = ptqinfo.listed;//上市期
				var salearea = ptqinfo.salearea;//主要销售地
				//var brandName = ptqinfo.brandName;//品牌名称
				var liList = "";
				liList+="<li><span>种类名称：</span><p>"+proName+"</p></li>";
				liList+="<li><span>是否认证：</span><p>"+certificate+"</p></li>";
				liList+="<li><span>年商品量(吨)：</span><p>"+quantity+"</p></li>";
				liList+="<li><span>上市期：</span><p>"+listed+"</p></li>";
				liList+="<li><span>主要销售地：</span><p>"+salearea+"</p></li>";
				
				if(liList!=''){
					ptqHtml += elements_1+"种类基本信息"+elements_2+liList+elements_3+line_tr;
				}
				
			}
			
			
			//根据comId查询企业信息
			var companyinfo_html = findCompanyInfoByComId(comId);
			recordarr[1] =companyinfo_html;
			recordarr[2] ="";
			if(thiscompanyinfo){
				
				
				var comType = thiscompanyinfo.comType;
				
				if(comType==1){
					var daHtml = "";
					
					headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">屠宰节点信息</a>';
					sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
					
					daHtml += animalInInfo(ptbId);//生猪进厂信息
					daHtml += quarantineInfo(ptbId,1);//屠宰检验检疫信息,(获取当前节点的动物检疫合格证号baseAid)
					daHtml += meatOutInfoBaseInfo(ptbId,'1');//屠宰交易基本信息
					
					recordarr[nav_count] = ptqHtml+daHtml;//获取html
					nav_count = nav_count+1;
					
				}else if(comType==2){
					baseAid = "";
					var daHtml = "";
					daHtml += marketInInfoBaseInfo(ptbId,'2',1);//批发进场信息,(获取当前节点的动物检疫合格证号baseAid)
					daHtml += qmarketDetectionInfo(ptbId);//批发检验检疫信息
					daHtml += meatOutInfoBaseInfo(ptbId,'2');//批发交易基本信息
					
					headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">批发节点信息</a>';
					sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
					
					recordarr[nav_count] = ptqHtml+daHtml;//获取html
					nav_count = nav_count+1;
					
					if(baseAid){
						var thisPtbId = findByJyhQuarantineInfo(baseAid);//屠宰厂检疫检验信息表-根据检疫证号查询批次编号
						if(thisPtbId){
							daHtml = "";
							headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">屠宰节点信息</a>';
							sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
							
							daHtml += animalInInfo(thisPtbId);//生猪进厂信息
							daHtml += quarantineInfo(thisPtbId,'');//屠宰检验检疫信息
							daHtml += meatOutInfoBaseInfo(thisPtbId,'1');//屠宰交易基本信息
							
							recordarr[nav_count] = daHtml;//获取html
							nav_count = nav_count+1;
						}
						
					}

					
					
					
				}else if(comType==3){
					baseAid = "";
					var daHtml = "";
					daHtml += marketInInfoBaseInfo(ptbId,'3',1);//零售进场信息,(获取当前节点的动物检疫合格证号baseAid)
					daHtml += retailMarketTranInfoSumm(ptbId);//销售汇总信息
					
					headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">零售节点信息</a>';
					sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
					
					recordarr[nav_count] = ptqHtml+daHtml;//获取html
					nav_count = nav_count+1;
					
					
					if(baseAid){
						var thisPtbId = findByJyhQuarantineInfo(baseAid);//屠宰厂检疫检验信息表-根据检疫证号查询批次编号
						if(thisPtbId){
							daHtml = "";
							headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">屠宰节点信息</a>';
							sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
							
							daHtml += animalInInfo(thisPtbId);//生猪进厂信息
							daHtml += quarantineInfo(thisPtbId,'');//屠宰检验检疫信息
							daHtml += meatOutInfoBaseInfo(thisPtbId,'1');//屠宰交易基本信息
							
							recordarr[nav_count] = daHtml;//获取html
							nav_count = nav_count+1;
						}
						
						//批发、零售市场、超市肉类蔬菜进场基本信息表-根据检疫证号查询批次编号
						thisPtbId = "";
						thisPtbId = findByJyhMarketInInfoBaseInfo(baseAid,2);
						if(thisPtbId){
							daHtml = "";
							daHtml += marketInInfoBaseInfo(thisPtbId,'2','');//批发进场信息
							daHtml += qmarketDetectionInfo(thisPtbId);//批发检验检疫信息
							daHtml += meatOutInfoBaseInfo(thisPtbId,'2');//批发交易基本信息
							
							headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">批发节点信息</a>';
							sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
							
							recordarr[nav_count] = daHtml;//获取html
							nav_count = nav_count+1;
						}
					}

					
					
				}else if(comType==4){
					baseAid = "";
					var daHtml = "";
					daHtml += marketInInfoBaseInfo(ptbId,'4',1);//超市进场信息,(获取当前节点的动物检疫合格证号baseAid)
					headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">超市节点信息</a>';
					sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
					
					recordarr[nav_count] = ptqHtml+daHtml;//获取html
					nav_count = nav_count+1;
					
					if(baseAid){
						var thisPtbId = findByJyhQuarantineInfo(baseAid);//屠宰厂检疫检验信息表-根据检疫证号查询批次编号
						if(thisPtbId){
							daHtml = "";
							headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">屠宰节点信息</a>';
							sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
							
							daHtml += animalInInfo(thisPtbId);//生猪进厂信息
							daHtml += quarantineInfo(thisPtbId,'');//屠宰检验检疫信息
							daHtml += meatOutInfoBaseInfo(thisPtbId,'1');//屠宰交易基本信息
							
							recordarr[nav_count] = daHtml;//获取html
							nav_count = nav_count+1;
						}
						
						//批发、零售市场、超市肉类蔬菜进场基本信息表-根据检疫证号查询批次编号
						thisPtbId = "";
						thisPtbId = findByJyhMarketInInfoBaseInfo(baseAid,2);
						if(thisPtbId){
							daHtml = "";
							daHtml += marketInInfoBaseInfo(thisPtbId,'2','');//批发进场信息
							daHtml += qmarketDetectionInfo(thisPtbId);//批发检验检疫信息
							daHtml += meatOutInfoBaseInfo(thisPtbId,'2');//批发交易基本信息
							
							headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">批发节点信息</a>';
							sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
							
							recordarr[nav_count] = daHtml;//获取html
							nav_count = nav_count+1;
						}
											
						
						//批发、零售市场、超市肉类蔬菜进场基本信息表-根据检疫证号查询批次编号
						thisPtbId = "";
						thisPtbId = findByJyhMarketInInfoBaseInfo(baseAid,3);
						if(thisPtbId){
							daHtml = "";
							daHtml += marketInInfoBaseInfo(thisPtbId,'3','');//零售进场信息
							daHtml += retailMarketTranInfoSumm(thisPtbId);//销售汇总信息
							
							headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">零售节点信息</a>';
							sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
							
							recordarr[nav_count] = daHtml;//获取html
							nav_count = nav_count+1;
						}
						
					}
					
					
					
				}else if(comType==5){
					var daHtml = "";
					
					headarr[nav_count] = '<a class="subnav" href="#section-circlefill-'+nav_count+'">生产档案信息</a>';
					sectionarr[nav_count] = '<div id="section-circlefill-'+nav_count+'" class="weui-tab__bd-item"></div>';
					
					daHtml += findFertilizerUseList(ptbId);//肥料使用记录
					daHtml += findAgriUseList(ptbId);//农药使用记录
					daHtml += findNodeinfoList(ptbId);//生产节点
					daHtml += findCheckinfoList(ptbId);//检验检疫信息
					daHtml += findSaleinfoList(ptbId);//交易信息
					
					recordarr[nav_count] = ptqHtml+daHtml;//获取html
					nav_count = nav_count+1;
				}
				
				
			}
		}
		
		
	}


	for(var i=4;i<nav_count;i++){
		if(headarr[i]){
			$('#recordDiv').append(headarr[i]);
			$('#section-circlefill-3').append(sectionarr[i]);
		}
		
	}
	
	$('#recordDiv').append('<div style="clear:both"></div>');
	
	
	if(recordarr[2]==''){
		$('a[href="#section-circlefill-2"]').hide();
	}
	
	for(var j=1;j<nav_count;j++){
		if(recordarr[j]){
			$("#section-circlefill-"+j).html(recordarr[j]);
		}
	}
	
	
	
	$('.weui-navbar__item').click(function(){
		$('.weui-navbar__item').removeClass('weui-bar__item--on');
		$(this).addClass('weui-bar__item--on');
		
		$('.weui-tab__bd-item').hide();
		$($(this).attr('href')).show();
		
		if($(this).attr('href')=='#section-circlefill-3'){
			$('#section-circlefill-4').show();
		}
		return false;
	});
	
	$('.subnav').click(function(){
		$('#section-circlefill-3 .weui-tab__bd-item').hide();
		$($(this).attr('href')).show();
		return false;
	});
	
	$('#section-circlefill-1 img').css('width','95%');
	
	
});


/**
 * 溯源统计
 * @param comId
 * @param comName
 * @return
 */
function scanCount(comId,comName){
	var cond = {};
	cond["scanCount.comId"]=comId;
	cond["scanCount.comName"]=comName;
	cond["scanCount.dimenno"]=$('#dimenno').val();
	$.ajax({
	    url:'scanCount_addScanCount.action',
	    data:cond,
	    type:'post',
	    dataType:'json',
	    success : function(data) {
			console.info(data);
		}
	}); 
}

//根据经营者编码获取，企业信息
function findCompanyInfoByComCode(comCode){
	var companyinfo = {};
	var cond = {};
	cond["comCode"]=comCode;
	$.ajax({
		    url:'webcompany_getCompanyInfoByComCode.action',
		    data:cond,
		    type:'post',
		    async : false,
		    dataType:'json',
		    success : function(data) {
		    	if(data){
					var a = data[0];
					companyinfo =data[0];
					var aUlHtml='';					
					aUlHtml += '<li><span>企业名称：</span><p>'+a.name+'</p></li>';
					aUlHtml += '<li><span>经营类型：</span><p>'+a.getComType(a.comType)+'</p></li>';
					aUlHtml += '<li><span>经营者编码：</span><p>'+a.comCode+'</p></li>';
					aUlHtml += '<li><span>企业法人：</span><p>'+a.corporate+'</p></li>';
					aUlHtml += '<li><span>企业性质：</span><p>'+a.getNature(a.nature)+'</p></li>';
					aUlHtml += '<li><span>联系电话：</span><p>'+a.phone+'</p></li>';
					aUlHtml += '<li><span>联系邮箱：</span><p>'+a.email+'</p></li>';
					aUlHtml += '<li><span>联系地址：</span><p>'+a.addr+'</p></li>';
					if(aUlHtml!=''){
						recordarr[1] = html_1+"企业信息"+html_2+aUlHtml+html_3;
					}
				}
	    }
	});
	return companyinfo;
}


//根据comId获取，企业信息
function findCompanyInfoByComId(comId){
	var res = "";
	var cond = {};
	cond["comId"]=comId;
	$.ajax({
		    url:'webcompany_getCompanyInfoByComId.action',
		    data:cond,
		    type:'post',
		    async : false,
		    dataType:'json',
		    success : function(data) {
				if(data){
					var a = data[0];
					thiscompanyinfo =data[0];
					
					 scanCount(comId,a.name);
					
					var aUlHtml='';					
					aUlHtml += '<li><span>企业名称：</span><p>'+a.name+'</p></li>';
					aUlHtml += '<li><span>经营类型：</span><p>'+getComType(a.comType)+'</p></li>';
					aUlHtml += '<li><span>经营者编码：</span><p>'+a.comCode+'</p></li>';
					aUlHtml += '<li><span>企业法人：</span><p>'+a.corporate+'</p></li>';
					aUlHtml += '<li><span>企业性质：</span><p>'+getNature(a.nature)+'</p></li>';
					aUlHtml += '<li><span>联系电话：</span><p>'+a.phone+'</p></li>';
					aUlHtml += '<li><span>联系邮箱：</span><p>'+a.email+'</p></li>';
					aUlHtml += '<li><span>联系地址：</span><p>'+a.addr+'</p></li>';
					if(aUlHtml!=''){
						res = html_1+"企业信息"+html_2+aUlHtml+html_3;
					}
				}
	    }
	});
	
	return res;
}



/////资质信息
function findZizhiInfo(entCode){
	var res = "";
	
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
    				var appName = a.zName;
    				var awardUnit = a.awardUnit;
    				if(!awardUnit)
    					awardUnit = "--";
    				var awardTime = a.awardTime;
    				if(!awardTime)
    					awardTime = "--";
					zzUlHtml+= '<li><span><a href="javascript:void(0)" onclick="showIMG(\''+src+'\')" ><img src="'+src+'" width="80" height="80"/></a></span>'+
								'<p class="p_zizhi"><span>证书名称：'+appName+'</span><span>颁发单位：'+awardUnit+'</span><span>颁发时间：'+awardTime+'</span></p></li>';
				}
			}
			
			if(zzUlHtml.length>0){
				
				res = html_1+"企业资质信息"+html_2+zzUlHtml+html_3;
				//$("#company_info").append(html_4+html_1+"企业资质信息"+html_2+zzUlHtml+html_3);
			}
		
		}
	}); 
	
	return res;
}




//获取分类基本信息
function findPtqInfo(ptq){
	var res = "";
	var typeClass = ptq["ptq.typeClass"];
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
				var proTypeArea = a.proTypeArea;
				var areaImg;//基地平面图；
				
				if(proTypeArea.length!=0){
					areaImg = proTypeArea[0].areaImg;
				}
				
				var areaImgStr = "无";
				if(areaImg){
					areaImgStr = "<a href=\"javascript:void(0)\" onclick=\"showIMG('"+elementDir+areaImg+"')\" ><img src=\""+elementDir+areaImg+"\" width=\"50\" height=\"50\"/></a>";
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
							imgStr = "<a  href=\"javascript:void(0)\" onclick=\"showIMG('"+elementDir+maImg+"')\" ><img src=\""+elementDir+maImg+"\" width=\"50\" height=\"50\"/></a>";
						}
						if(typeClass==1){
							cddkList+="<li><span>所属基地：</span><p>"+areaName+"</p></li>";
							cddkList+="<li><span>地块名称：</span><p>"+maName+"</p></li>";
							cddkList+="<li><span>地块面积：</span><p>"+maAcreage+"</p></li>";
							cddkList+="<li><span>种植时间：</span><p>"+startTime+"</p></li>";
							cddkList+="<li><span>收获时间：</span><p>"+getTime+"</p></li>";
							cddkList+="<li><span>地块平面图：</span><p>"+imgStr+"</p></li>";
						}else{
							cddkList+="<li><span>所属基地：</span><p>"+areaName+"</p></li>";
							cddkList+="<li><span>场地名称：</span><p>"+maName+"</p></li>";
							cddkList+="<li><span>场地面积：</span><p>"+maAcreage+"</p></li>";
							cddkList+="<li><span>养殖时间：</span><p>"+startTime+"</p></li>";
							cddkList+="<li><span>收获时间：</span><p>"+getTime+"</p></li>";
							cddkList+="<li><span>地块平面图：</span><p>"+imgStr+"</p></li>";
						}
						
						
					}
				}
				
				liList+="<li><span>种类名称：</span><p>"+a.typeName+"</p></li>";
				liList+="<li><span>是否认证：</span><p>"+certificate+"</p></li>";
				liList+="<li><span>年商品量：</span><p>"+a.quantity+"(吨)</p></li>";
				liList+="<li><span>上市期：</span><p>"+a.listed+"</p></li>";
				liList+="<li><span>主要销售地：</span><p>"+a.salearea+"</p></li>";
				liList+="<li><span>基地及规模：</span><p>"+chandi+"</p></li>";
				liList+="<li><span>基地平面图：</span><p>"+areaImgStr+"</p></li>";
				
				var ttype = "种植";
				if(typeClass==2){
					ttype = "养植";
				}else if(typeClass==3){
					ttype = "加工";
				}
				
				if(liList!=''){
					res += elements_1+ttype+"基本信息"+elements_2+liList+elements_3+line_tr;
					liList = "";
				}
				if(cddkList!=''){
					res += elements_1+ttype+"地块信息"+elements_2+cddkList+elements_3+line_tr;
					cddkList = "";
				}
			}
		}
	});
	
	return res;
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



function showIMG(src){
	if(src==''){
		src = "weixin/images/tips1.png";
	}
	var image = '<img src="'+src+'" align="center" style="width: 100%;min-height: 80px;"/>';
	
	layer.open({
		type: 1,
        closeBtn: true,//不关闭
        title: false, //不显示默认标题栏
        shade: [0], //不显示遮罩
        //time: 5000,//5秒后关闭
        shadowOpacity:0.8,
        area: ['85%', 'auto'],
        //area: ['85%', '100px'],
        content: image
    });
}


////////////////////////////////////////////////////////////////////////////


/**
 * 根据批次码，查询批次实体
 * @param dcode
 * @returns {___anonymous3669_3670}
 */
function findPTBinfoByDimenno(dcode){
	var ptbinfo = "";
	$.ajax({
   	    url:'webproTypeBatch_getProTypeBatchByDimenno.action?dimenno='+dcode,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			if(result.length>0){
				ptbinfo = result[0];
			}
	    }
   });
	
	return ptbinfo;
}


/** 
 * 根据ptqid 查找分类二维码信息（包括种类的一些信息）
 * @param ptqId
 * @returns {___anonymous4390_4391}
 */
function getProTypeQrcodeById(ptqId){
	var ptqinfo = "";
	$.ajax({
   	    url:'webproTypeQrcode_getProTypeQrcodeById.action?ptqId='+ptqId,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			if(result.length>0){
				ptqinfo = result[0];
			}
	    }
   });
	
	return ptqinfo;
}





//根据分类追溯码查询种类表信息，获取comId（entId）、ptqId,及种类其它信息
function findProTypeQrcodeByDimenno(code){
	var ptqinfo = "";
	$.ajax({
   	    url:'webproTypeQrcode_findProTypeQrcodeByDimenno.action?dimenno='+code,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			if(result.length>0){
				ptqinfo = result[0];
			}
	    }
   });
	
	return ptqinfo;
}

/**
 * 根据分类二维码id，查批次列表
 * @param ptqId
 * @returns {String}
 */
function findPTBListByPtqId(ptqId){
	var res = "";
	var condition = {};
	condition['proTypeBatch.ptqId'] = ptqId;	
	$.ajax({
   	    url:'webproTypeBatch_findProTypeBatchByPtqId.action',
   	    data:condition,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
			if(result){
				var ptbList = result;
   				var liList = "";
   				for(var i=0;i<ptbList.length;i++){
   					var ptbinfo = ptbList[i];
   					
   					var url = basePath+'trace.jsp?code='+ptbinfo.dimenno;
   					  					
   					liList+="<li><span>批次名称：</span><p>"+ptbinfo.batchName+"</p></li>";
   					liList+="<li><span>创建时间：</span><p>"+ptbinfo.batchTime+"</p></li>";
   					liList+="<li><span>批次码：</span><p>"+ptbinfo.dimenno+"</p></li>";
   					liList+="<li><span>操作：</span><p><a class=\"tablelink\"  target=\"_blank\" href=\""+url+"\" >查看详细</a></p></li>";
   					
   					if(liList!=''){
   						res += elements_1+"批次信息"+elements_2+liList+elements_3+line_tr;
   						liList = "";
   					}
   				}
   				
			}
	    }
   });
	
	return res;
}



//屠宰信息
function animalInInfo(ptbId){
	var res = "";
	var condition = {};
	condition['animalInInfo.ptbId'] = ptbId;	
	$.ajax({
   	    url:'animalInInfo_findList.action?tt='+Math.random(),
   	    data:condition,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
   	    	if(result){
   				var animalInInfoList = result.rows;
   				var liList = "";
   				for(var i=0;i<animalInInfoList.length;i++){
   					var animalInInfo = animalInInfoList[i];
   					
   					liList+="<li><span>屠宰厂名称：</span><p>"+animalInInfo.butcherFacName+"</p></li>";
   					liList+="<li><span>进场日期：</span><p>"+animalInInfo.inDate+"</p></li>";
   					liList+="<li><span>货主名称：</span><p>"+animalInInfo.sellerName+"</p></li>";
   					liList+="<li><span>产地检疫证号：</span><p>"+animalInInfo.quarantineId+"</p></li>";
   					liList+="<li><span>进场数量：</span><p>"+animalInInfo.quarantineNum+"</p></li>";
   					liList+="<li><span>采购价：</span><p>"+animalInInfo.price+"</p></li>";
   					liList+="<li><span>养殖场名称：</span><p>"+animalInInfo.farmName+"</p></li>";
   					
   					if(liList!=''){
   						res += elements_1+"屠宰厂肉类蔬菜进场基本信息"+elements_2+liList+elements_3+line_tr;
   						liList = "";
   					}
   					
   				}
   				
   			}
	    }
   });

	return res;
}


//屠宰检验检疫信息
function quarantineInfo(ptbId,ba){
	var res = "";
	var condition = {};
	condition['quarantineInfo.ptbId'] = ptbId;
	$.ajax({
   	    url:'quarantineInfo_findList.action?tt='+Math.random(),
   	    data:condition,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
   	    	if(result){
   				var quarantineInfoList = result.rows;
   				var liList = "";
   				
   				for(var i=0;i<quarantineInfoList.length;i++){
   					var quarantineInfo = quarantineInfoList[i];
   					if(i==0&&ba){
   						baseAid = quarantineInfo.quarantineAnimalProductsId;
   					}
   					
   					liList+="<li><span>屠宰厂名称：</span><p>"+quarantineInfo.butcherFacName+"</p></li>";
   					liList+="<li><span>检疫合格证号：</span><p>"+quarantineInfo.quarantineAnimalProductsId+"</p></li>";
   					liList+="<li><span>货主名称：</span><p>"+quarantineInfo.sellerName+"</p></li>";
   					liList+="<li><span>产地检疫证号：</span><p>"+quarantineInfo.quarantineId+"</p></li>";
   					liList+="<li><span>采样头数：</span><p>"+quarantineInfo.sampleNum+"</p></li>";
   					liList+="<li><span>采样样品编号：</span><p>"+quarantineInfo.sampleId+"</p></li>";
   					liList+="<li><span>抽检日期：</span><p>"+quarantineInfo.checkDate+"</p></li>";
   					
   					if(liList!=''){
   						res += elements_1+"屠宰厂检疫检验信息"+elements_2+liList+elements_3+line_tr;
   						liList = "";
   					}
   					
   				}
   				
   			}
	    }
   });
   return res;
}




//交易信息(屠宰交易或者批发交易，基本信息)
function meatOutInfoBaseInfo(ptbId,flag){
	var res = "";
	var condition = {};
	condition['meatOutInfoBase.ptbId'] = ptbId;
	condition['meatOutInfoBase.flag'] = flag;
	$.ajax({
 	    url:'webmeatOutInfoBase_findList.action?tt='+Math.random(),
 	    data:condition,
 	    type:'post',
 	    async : false,
 	    dataType:'json',
 	    success : function(result) {
 	    	if(result){
 				var meatOutInfoBaseList = result.rows;
 				var qycode = "";
 				var qyname = "";
 				var showtitle="";
 				if(flag=='1'){
 					qycode = "屠宰厂编码";
 	 				qyname = "屠宰厂名称";
 	 				showtitle="屠宰交易基本信息";
 				}else if(flag=='2'){
 					qycode = "批发市场编码";
 	 				qyname = "批发市场名称";
 	 				showtitle="批发交易基本信息";
 				}
 				var liList = "";
 				
 				for(var i=0;i<meatOutInfoBaseList.length;i++){
 					var meatOutInfoBase = meatOutInfoBaseList[i];
 					 					
 					liList+="<li><span>"+qycode+"：</span><p>"+meatOutInfoBase.butcherFacId+"</p></li>";
   					liList+="<li><span>"+qyname+"：</span><p>"+meatOutInfoBase.butcherFacName+"</p></li>";
   					liList+="<li><span>交易日期：</span><p>"+meatOutInfoBase.tranDate+"</p></li>";
   					liList+="<li><span>货主名称：</span><p>"+meatOutInfoBase.sellerName+"</p></li>";
   					liList+="<li><span>买主名称：</span><p>"+meatOutInfoBase.buyerName+"</p></li>";
   					liList+="<li><span>到达地：</span><p>"+meatOutInfoBase.dest+"</p></li>";
   					liList+="<li><span>交易凭证号：</span><p>"+meatOutInfoBase.tranId+"</p></li>";
   					
   					if(liList!=''){
   						res += elements_1+showtitle+elements_2+liList+elements_3+line_tr;
   						liList = "";
   					}
   					
 				}
 				
 			}
	    }
 });
	
 return res;	
}







//进场基本信息(2批发、3零售、4超市进场基本信息)
function marketInInfoBaseInfo(ptbId,flag,ba){
	var res ="";
	var condition = {};
	condition['marketInInfoBase.ptbId'] = ptbId;
	condition['marketInInfoBase.flag'] = flag;
	$.ajax({
	    url:'webmarketInInfoBase_findList.action?tt='+Math.random(),
	    data:condition,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
	    	if(result){
				var marketInInfoBaseList = result.rows;
				var qycode = "";
				var qyname = "";
				var qycode2 = "";
				var qyname2 = "";
				var showtitle="";
				if(flag=='2'){
					qycode = "批发市场编码";
	 				qyname = "批发市场名称";
	 				qycode2 = "批发商编码";
	 				qyname2 = "批发商名称";
	 				showtitle="批发市场肉类蔬菜进场基本信息";
				}else if(flag=='3'){
					qycode = "零售市场编码";
	 				qyname = "零售市场名称";
	 				qycode2 = "零售商编码";
	 				qyname2 = "零售商名称";
	 				showtitle="零售市场肉类蔬菜进场基本信息";
				}else if(flag=='4'){
					qycode = "超市编码";
	 				qyname = "超市名称";
	 				qycode2 = "供应商编码";
	 				qyname2 = "供应商名称";
	 				showtitle="超市肉类蔬菜进场基本信息";
				}
				
		        
				var liList = "";
				
				for(var i=0;i<marketInInfoBaseList.length;i++){
					var marketInInfoBase = marketInInfoBaseList[i];
					if(i==0&&ba){
   						baseAid = marketInInfoBase.quarantineAnimalProductsId;
   					}
 					
 					liList+="<li><span>"+qycode+"：</span><p>"+marketInInfoBase.marketId+"</p></li>";
   					liList+="<li><span>"+qyname+"：</span><p>"+marketInInfoBase.marketName+"</p></li>";
   					liList+="<li><span>进场日期：</span><p>"+marketInInfoBase.inDate+"</p></li>";
   					liList+="<li><span>"+qycode2+"：</span><p>"+marketInInfoBase.wholesalerId+"</p></li>";
   					liList+="<li><span>"+qyname2+"：</span><p>"+marketInInfoBase.wholesalerName+"</p></li>";
   					liList+="<li><span>交易凭证号：</span><p>"+marketInInfoBase.tranId+"</p></li>";
   					liList+="<li><span>动物产品检疫合格证号：</span><p>"+marketInInfoBase.quarantineAnimalProductsId+"</p></li>";
   					liList+="<li><span>肉品品质检验合格证号：</span><p>"+marketInInfoBase.inspectionMeatId+"</p></li>";
   					liList+="<li><span>进货批次号：</span><p>"+marketInInfoBase.batchId+"</p></li>";
   					
   					if(liList!=''){
   						res += elements_1+showtitle+elements_2+liList+elements_3+line_tr;
   						liList = "";
   					}
   					
				}
				
			}
	    }
});
	
	return res;	
}


/**
 * 批发市场检验信息
 * @param ptbId
 */
function qmarketDetectionInfo(ptbId){
	var res ="";
	var condition = {};
	condition['qmarketDetectionInfo.ptbId'] = ptbId;
	
	$.ajax({
	    url:'webqmarketDetectionInfo_findList.action?tt='+Math.random(),
	    data:condition,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
	    	if(result){
	    		var qmarketDetectionInfoList = result.rows;
	    		var liList = "";
	    		
				for(var i=0;i<qmarketDetectionInfoList.length;i++){
					var qmarketDetectionInfo = qmarketDetectionInfoList[i];
				
					liList+="<li><span>批发市场编码：</span><p>"+qmarketDetectionInfo.marketId+"</p></li>";
   					liList+="<li><span>批发市场名称：</span><p>"+qmarketDetectionInfo.marketName+"</p></li>";
   					liList+="<li><span>批发商编码：</span><p>"+qmarketDetectionInfo.wholesalerId+"</p></li>";
   					liList+="<li><span>批发商名称：</span><p>"+qmarketDetectionInfo.wholesalerName+"</p></li>";
   					liList+="<li><span>交易凭证号：</span><p>"+qmarketDetectionInfo.tranId+"</p></li>";
   					liList+="<li><span>动物产品检疫合格证号：</span><p>"+qmarketDetectionInfo.quarantineAnimalProductsId+"</p></li>";
   					liList+="<li><span>肉品品质检验证号：</span><p>"+qmarketDetectionInfo.inspectionMeatId+"</p></li>";
   					liList+="<li><span>检测日期：</span><p>"+qmarketDetectionInfo.detectionDate+"</p></li>";
   					
   					if(liList!=''){
   						res += elements_1+"批发市场检验信息"+elements_2+liList+elements_3+line_tr;
   						liList = "";
   					}
				}
	    	}
	    }
	});
	return res;	
}


//零售市场销售汇总信息
function retailMarketTranInfoSumm(ptbId){
	var res = "";
	var condition = {};
	condition['retailMarketTranInfoSumm.ptbId'] = ptbId;
	
	$.ajax({
	    url:'webretailMarketTranInfoSumm_findList.action?tt='+Math.random(),
	    data:condition,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
	    	if(result){
				var retailMarketTranInfoSummList = result.rows;
				var liList = "";
				
				for(var i=0;i<retailMarketTranInfoSummList.length;i++){
					var retailMarketTranInfoSumm = retailMarketTranInfoSummList[i];
					
					liList+="<li><span>零售市场名称：</span><p>"+retailMarketTranInfoSumm.retailMarketName+"</p></li>";
   					liList+="<li><span>进货日期：</span><p>"+retailMarketTranInfoSumm.inDate+"</p></li>";
   					liList+="<li><span>销售日期：</span><p>"+retailMarketTranInfoSumm.saleDate+"</p></li>";
   					liList+="<li><span>零售商名称：</span><p>"+retailMarketTranInfoSumm.retailerName+"</p></li>";
   					liList+="<li><span>零售摊位号：</span><p>"+retailMarketTranInfoSumm.positionId+"</p></li>";
   					liList+="<li><span>交易凭证号：</span><p>"+retailMarketTranInfoSumm.tranId+"</p></li>";
   					liList+="<li><span>动物产品检疫合格证号：</span><p>"+retailMarketTranInfoSumm.quarantineAnimalProductsId+"</p></li>";
   					liList+="<li><span>商品名称：</span><p>"+retailMarketTranInfoSumm.goodsName+"</p></li>";
   					
   					if(liList!=''){
   						res += elements_1+"零售市场销售汇总信息"+elements_2+liList+elements_3+line_tr;
   						liList = "";
   					}
				}
				
	    	}
	    }
	});
	return res;
}


//屠宰厂检疫检验信息表-根据检疫证号查询批次编号
function findByJyhQuarantineInfo(jyh){
	var res = "";
	var condition = {};
	condition['quarantineInfo.quarantineAnimalProductsId'] = jyh;
	$.ajax({
   	    url:'quarantineInfo_findByJyh.action?tt='+Math.random(),
   	    data:condition,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
	    	if(result){
	    		res = result[0].ptbId;
	    	}
	    }
	});
	
	return res;
}



//批发、零售市场、超市肉类蔬菜进场基本信息表-根据检疫证号查询批次编号
function findByJyhMarketInInfoBaseInfo(jyh,flag){
	var res ="";
	var condition = {};
	condition['marketInInfoBase.quarantineAnimalProductsId'] = jyh;
	condition['marketInInfoBase.flag'] = flag;
	$.ajax({
	    url:'webmarketInInfoBase_findByJyh.action?tt='+Math.random(),
	    data:condition,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
	    	if(result){
	    		res = result[0].ptbId;
	    	}
	    }
	});
	
	return res;
}

//--------------------种植类 --start-------------------
//种植类-销售信息
function findSaleinfoList(recId){
	var res = "";
	
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
					res += elements_1+"交易信息"+elements_2+liList+elements_3+line_tr;
					liList = "";
				}
			}
		
		}
	});
	
	return res;
}


//获取肥料使用记录
function findFertilizerUseList(recId){
	var res = "";
	
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
				liList+="<li><span>施用人：</span><p>"+a.useman+"</p></li>";
				if(liList!=''){
					res += elements_1+"肥料投入品使用记录"+elements_2+liList+elements_3+line_tr;
					liList = "";
				}
			}
		}
	});
	
	return res;
}



//投入品使用记录
function findAgriUseList(recId){
	var res = "";
	
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
				liList+="<li><span>药品名称：</span><p>"+a.agriName+"</p></li>";
				liList+="<li><span>防治对象：</span><p>"+a.useObject+"</p></li>";
				liList+="<li><span>施用方式：</span><p>"+a.useWay+"</p></li>";
				liList+="<li><span>稀释浓度：</span><p>"+a.useDosage+"</p></li>";
				liList+="<li><span>施用量：</span><p>"+a.useTotal+"</p></li>";
				liList+="<li><span>施用人：</span><p>"+a.useMan+"</p></li>";
				liList+="<li><span>使用日期：</span><p>"+a.useDate+"</p></li>";
				liList+="<li><span>安全隔离天数：</span><p>"+a.safeDay+"</p></li>";
				liList+="<li><span>安全采收期：</span><p>"+a.safeDate+"</p></li>";
				
				
				if(liList!=''){
					res += elements_1+"农药投入品使用信息"+elements_2+liList+elements_3+line_tr;
					liList = "";
				}
			}
		}
	});
	
	return res;
}


//生产节点信息
function findNodeinfoList(recId){
	var res = "";
	var nodeinfo = {};
	nodeinfo["nodeinfo.recId"]=recId;
	$.ajax({
	    url:'portal_Elements_findNodeinfoList.action',
	    data:nodeinfo,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(result) {
			var niName = "";//节点名称 
			var nodeImg = "";//节点图片
			var liList = "";
			var imgStr = "";
			for(var i=0;i<result.rows.length;i++){
				var a = result.rows[i];
				niName = a.niName;
				var src = elementDir+a.nodeImg;
				liList+='<li><span>节点名称：</span><p>'+niName+'</p></li>';
				liList+='<li><span><a  href="javascript:void(0)" onclick="showIMG(\''+src+'\')"><img src="'+src+'" width="80" height="80"/></a></span><p>'+niName+'</p></li>';
			}
			if(liList!=''){
				res += elements_1+"生产节点信息"+elements_2+liList+elements_3+line_tr;
				liList = "";
			}
		}
	});
	
	return res;
}


//检验报告
function findCheckinfoList(recId){
	var res = "";
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
							var imgCont =  '<li><span><a  href="javascript:void(0)" onclick="showIMG(\''+src+'\')"><img src="'+src+'" width="80" height="80"/></a></span><p>'+appName+'</p></li>';
							imgStr += imgCont;
						}
					}
				}
				liList+=imgStr;
				if(liList!=''){
					res += elements_1+"检验报告信息"+elements_2+liList+elements_3+line_tr;
					liList = "";
				}
			}
		}
	});
	
	return res;
}

//--------------------种植类 --end-------------------


//获取节点类型
function getFlag(value){
	var res = '无';//1代表屠宰企业、2代表批发企业、3代表菜市场、4代表超市、5代表团体消费单位、6代表其他
	if(value=='1'){
		res = '屠宰企业';
	}else if(value=='2'){
		res = '批发企业';
	}else if(value=='3'){
		res = '菜市场';
	}else if(value=='4'){
		res = '超市';
	}else if(value=='5'){
		res = '团体消费单位';
	}else if(value=='6'){
		res = '其他';
	}
	return res;
}

//企业性质
function getNature(value){
	var res = '无';
	if(value=='1'){
		res = '企业';
	}else if(value=='2'){
		res = '个体户';
	}
	return res;
}

//经营者类型
function getComType(value){
	var res = '无';//主要分生猪批发商、肉类蔬菜批发商、肉类蔬菜零售商、配送企业、其他等类型
	if(value=='1'){
		res = '生猪批发商';
	}else if(value=='2'){
		res = '肉菜批发商';
	}else if(value=='3'){
		res = '肉菜零售商';
	}else if(value=='4'){
		res = '配送企业';
	}else if(value=='5'){
		res = '其他';
	}
	return res;
}