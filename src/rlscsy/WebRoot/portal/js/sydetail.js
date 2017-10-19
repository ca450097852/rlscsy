//企业分类溯源查询
var filePath = '';
var basePath = '';
var dimenno = '';
var thisEntId='';//保存企业Id；即company表的comId
var companyRsts='';//企业状态：1使用，2停用

//<h2><a name="02">——批次名——</a></h2>
var header_1 = '<div id="';//需要加上article_id
var header_2 = '"><h3><a>——';//需加上批次名
var header_3 = '——</a></h3></div>';
//header_1 + (article_id) + header_2 + (批次名) + header_3;

var baseAid="";//当前节点的动物产品检疫合格证号

var innerHeader_1 = '<div';//需要加上div 的属性如 : class="unload" id="jdId" aid="" comType="1"
var innerHeader_2 = '><h3><a><font class="rmfont" color="green">点击加载-</font>';//需加上节点名称如：屠宰节点信息
var innerHeader_3 = '</a></h3></div>';
//innerHeader_1 + (article_id) + innerHeader_2 + (屠宰节点信息) + innerHeader_3;


var section_1 = "<h4><span>";//加上要素名称
var section_2 = "</span></h4><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
var section_3 = "</table>";
//section_1 + (要素名) + section_2 + (trlist) + section_3;


var simg_1 = "<h4><span>";//企业资质
var simg_2 = "</span></h4>";
var simg_3 = "";
//simg_1 + (要素名) + simg_2 + (divlist) + simg_3;


$(function() {
	filePath = $('#filePath').val();
	basePath = $('#basePath').val();
	dimenno = $('#dimenno').val();
	
	
	
	if(dimenno!=null&&dimenno.length==13){//企业经营者编码
		
		findCompanyInfoByComCode(dimenno);//企业信息
		
	}else if(dimenno!=null&&dimenno.length==16){//批次码
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
			var article_id = "article_id"+dimenno;
			var fristHtml = header_1+article_id+header_2+batchName+header_3;
			
			$("#ss").append("<a href=\"#"+article_id+"\" class=\"taptitle\">"+batchName+"</a>");
			$(".div_content").append(fristHtml);//主体档案外层，html代码
			
			//根据ptqId查询种类信息
			var ptqinfo = getProTypeQrcodeById(ptqId);
			
			//根据comId查询企业信息
			var companyinfo = findCompanyInfoByComId(comId);
			if(companyinfo){
				
				if(ptqinfo){
					var zlxx_start_html = "<tr bgcolor=\"#f2f2f2\"><td>(种类信息)</td></tr><tr bgcolor=\"#f2f2f2\"><td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody>";
					var zlxx_end_html = "</tbody></table></td></tr>";
					var zlxx_befortr = "<tr bgcolor=\"#f2f2f2\"><td>种类名称</td><td>是否认证</td><td>年商品量(吨)</td><td>上市期</td><td>主要销售地</td></tr>";
					var proName = ptqinfo.proName;//产品名称
					var certificate = ptqinfo.certificate;//是否认证
					certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
					var quantity = ptqinfo.quantity;//年商品量
					var listed = ptqinfo.listed;//上市期
					var salearea = ptqinfo.salearea;//主要销售地
					//var brandName = ptqinfo.brandName;//品牌名称
					
					zlxx_befortr += "<tr bgcolor=\"#ffffff\" class=\"bor_b\"><td>"+proName+"</td><td>"+certificate+"</td><td>"+quantity+"</td><td>"+listed+"</td><td>"+salearea+"</td></tr>";
					
					var liList = zlxx_start_html + zlxx_befortr+zlxx_end_html;
					$("#"+article_id).append(section_1+"种类基本信息"+section_2+liList+section_3);
				}
				
				var comType = companyinfo.comType;
				getPTBSuyuanBy(comType,ptbId,article_id);//结合comType和ptbId查询具体要素
			}
		}
		
		
	}else if(dimenno!=null&&dimenno.length==20){//分类追溯码
		/*步骤
		 * 1、根据分类追溯码查询种类表信息，获取comId（entId）、ptqId,及种类其它信息
		 * 2、根据comId查询企业信息，获取comType
		 * 3、根据ptqId查询，获取批次列表list
		 * 4、结合comType和list[i].ptbId查询具体批次要素。（没用到第四步）
		 * */
		
		var ptqinfo = findProTypeQrcodeByDimenno(dimenno);
		if(ptqinfo){
			//根据comId查询企业信息
			var companyinfo = findCompanyInfoByComId(ptqinfo.entId);
			
			var article_id = "article_id"+dimenno;
			var fristHtml = header_1+article_id+header_2+ptqinfo.proName+header_3;
			$("#ss").append("<a href=\"#"+article_id+"\" class=\"taptitle\">"+ptqinfo.proName+"</a>");
			$(".div_content").append(fristHtml);//主体档案外层，html代码
			
			
			var liList = "<tr bgcolor=\"#f2f2f2\"><td>种类名称</td><td>是否认证</td><td>年商品量(吨)</td><td>上市期</td><td>主要销售地</td></tr>";
			var proName = ptqinfo.proName;//产品名称
			var certificate = ptqinfo.certificate;//是否认证
			certificate = certificate=='1'?"有机产品":(certificate=='2'?"绿色产品":(certificate=='3'?"无公害产品":(certificate=='4'?"地理标志认证":"无")));
			var quantity = ptqinfo.quantity;//年商品量
			var listed = ptqinfo.listed;//上市期
			var salearea = ptqinfo.salearea;//主要销售地
			//var brandName = ptqinfo.brandName;//品牌名称
			
			liList += "<tr bgcolor=\"#ffffff\" class=\"bor_b\"><td>"+proName+"</td><td>"+certificate+"</td><td>"+quantity+"</td><td>"+listed+"</td><td>"+salearea+"</td></tr>";
			
			$("#"+article_id).append(section_1+"种类基本信息"+section_2+liList+section_3);
			
			
			//获取批次列表数据；
			findPTBListByPtqId(ptqinfo.ptqId,article_id);
		}
	}
	
	
////////////////////////////////new search end ///////////////////////////////
	
	//加载其它节点的信息-start
	$(".unload").click(function(){
		var divID = $(this).attr("id");//外层id
		var aid = $(this).attr("aid");//动物产品检疫合格证号
		var comType = $(this).attr("comType");//节点类型
		$(this).find(".rmfont").remove();
		$(this).attr("aid","");
		
		if(aid){
			
			if(comType==1){//屠宰
				var thisPtbId = findByJyhQuarantineInfo(aid);//屠宰厂检疫检验信息表-根据检疫证号查询批次编号
				if(thisPtbId){
					animalInInfo(thisPtbId,divID);//生猪进厂信息
					quarantineInfo(thisPtbId,divID);//屠宰检验检疫信息
					meatOutInfoBaseInfo(thisPtbId,'1',divID);//屠宰交易基本信息
				}
				
			}else if(comType==2){//批发
				//批发、零售市场、超市肉类蔬菜进场基本信息表-根据检疫证号查询批次编号
				var thisPtbId = findByJyhMarketInInfoBaseInfo(aid,2);
				if(thisPtbId){
					marketInInfoBaseInfo(thisPtbId,'2',divID);//批发进场信息
					qmarketDetectionInfo(thisPtbId,divID);//批发检验检疫信息
					meatOutInfoBaseInfo(thisPtbId,'2',divID);//批发交易基本信息
				}
			}else if(comType==3){//零售
				//批发、零售市场、超市肉类蔬菜进场基本信息表-根据检疫证号查询批次编号
				var thisPtbId = findByJyhMarketInInfoBaseInfo(aid,3);
				if(thisPtbId){
					marketInInfoBaseInfo(thisPtbId,'3',divID);//零售进场信息
					retailMarketTranInfoSumm(thisPtbId,divID);//销售汇总信息
				}
			}
		}
	});
	//加载其它节点的信息-end
	
});

//根据经营者编码获取，企业信息
function findCompanyInfoByComCode(comCode){
	var cond = {};
	cond["comCode"]=comCode;
	$.ajax({
		    url:'webcompany_getCompanyInfoByComCode.action',
		    data:cond,
		    type:'post',
		    async : false,
		    dataType:'json',
		    success : function(data) {
				for(var i=0;i<data.length;i++){
					var a = data[i];
					
					$("#qyjbxx").html(
							"<strong>企业名称："+a.name+"</strong>"+
							"<p>经营类型："+getComType(a.comType)+"</p>"+
						    "<p>经营者编码："+a.comCode+"</p>"+
						    "<p>企业法人："+a.corporate+"</p>" +
						    "<p>企业性质："+getNature(a.nature)+"</p>"
						    );
					
					$("#qylxfs").html("" +
						    "<p>联系电话："+a.phone+"</p>"+
						    "<p>联系邮箱："+a.email+"</p>"+
						    "<p>联系地址："+a.addr+"</p>"/*+
						    "<p>企业网址："+a.domName+"</p>"*/
							);
					
					$("#qyjj").html(a.introduction);
					
				}
				if(data.length>0){
					thisEntId=data[0].comId;
					companyRsts = data[0].state;
				}
	    }
	});
}


//根据comId获取，企业信息
function findCompanyInfoByComId(comId){
	var companyinfo = {};
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
					companyinfo =data[0];
					$("#qyjbxx").html(
							"<strong>企业名称："+a.name+"</strong>"+
							"<p>经营类型："+getComType(a.comType)+"</p>"+
						    "<p>经营者编码："+a.comCode+"</p>"+
						    "<p>企业法人："+a.corporate+"</p>" +
						    "<p>企业性质："+getNature(a.nature)+"</p>"
						    );
					
					$("#qylxfs").html("" +
						    "<p>联系电话："+a.phone+"</p>"+
						    "<p>联系邮箱："+a.email+"</p>"+
						    "<p>联系地址："+a.addr+"</p>"/*+
						    "<p>企业网址："+a.domName+"</p>"*/
							);
					
					$("#qyjj").html(a.introduction);
				}
	    }
	});
	
	return companyinfo;
}





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
function findPTBListByPtqId(ptqId,divID){
	var ptblist = "";
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
				ptblist = result;
				
				var ptbList = result;
   				var liList = "<tr bgcolor=\"#f2f2f2\"><td>批次名称</td><td>创建时间</td><td>批次码</td><td>操作</td></tr>";
   				for(var i=0;i<ptbList.length;i++){
   					var ptbinfo = ptbList[i];
   					
   					var url = basePath+'portal/sydetail.jsp?dimenno='+ptbinfo.dimenno;
   					
   					liList += '<tr bgcolor="#ffffff" class="bor_b">\
   					    <td>'+ptbinfo.batchName+'</td>\
   				        <td>'+ptbinfo.batchTime+'</td>\
   				        <td>'+ptbinfo.dimenno+'</td>\
	   				    <td><a class="tablelink"  target="_blank" href="'+url+'" >查看详细</a>\
	 			        </td>\
   				        </tr> ';
   				}
   				
   				$("#"+divID).append(section_1+"批次信息"+section_2+liList+section_3);
   				
   				
			}
	    }
   });
	
	return ptblist;
}




/**
 * 结合comType和ptbId查询具体要素
 * @param comType
 * @param ptbId
 * @param divID
 */
function getPTBSuyuanBy(comType,ptbId,divID){
	if(comType==1){
		animalInInfo(ptbId,divID);//生猪进厂信息
		quarantineInfo(ptbId,divID);//屠宰检验检疫信息,(获取当前节点的动物检疫合格证号baseAid)
		meatOutInfoBaseInfo(ptbId,'1',divID);//屠宰交易基本信息
		
	}else if(comType==2){
		marketInInfoBaseInfo(ptbId,'2',divID);//批发进场信息,(获取当前节点的动物检疫合格证号baseAid)
		qmarketDetectionInfo(ptbId,divID);//批发检验检疫信息
		meatOutInfoBaseInfo(ptbId,'2',divID);//批发交易基本信息
		
		var tuzai = innerHeader_1+' class="unload" id="'+divID+'_tz" aid="'+baseAid+'" comType="1"'+innerHeader_2+'屠宰节点信息'+innerHeader_3;
		$("#"+divID).append(tuzai);
	}else if(comType==3){
		marketInInfoBaseInfo(ptbId,'3',divID);//零售进场信息,(获取当前节点的动物检疫合格证号baseAid)
		retailMarketTranInfoSumm(ptbId,divID);//销售汇总信息
		
		var tuzai = innerHeader_1+' class="unload" id="'+divID+'_tz" aid="'+baseAid+'" comType="1"'+innerHeader_2+'屠宰节点信息'+innerHeader_3;
		var pifa  = innerHeader_1+' class="unload" id="'+divID+'_pf" aid="'+baseAid+'" comType="2"'+innerHeader_2+'批发节点信息'+innerHeader_3;
		$("#"+divID).append(tuzai+pifa);
	}else if(comType==4){
		marketInInfoBaseInfo(ptbId,'4',divID);//超市进场信息,(获取当前节点的动物检疫合格证号baseAid)
		
		var tuzai 	= innerHeader_1+' class="unload" id="'+divID+'_tz" aid="'+baseAid+'" comType="1"'+innerHeader_2+'屠宰节点信息'+innerHeader_3;
		var pifa 	= innerHeader_1+' class="unload" id="'+divID+'_pf" aid="'+baseAid+'" comType="2"'+innerHeader_2+'批发节点信息'+innerHeader_3;
		var lingshou= innerHeader_1+' class="unload" id="'+divID+'_ls" aid="'+baseAid+'" comType="3"'+innerHeader_2+'零售节点信息'+innerHeader_3;
		
		$("#"+divID).append(tuzai+pifa+lingshou);
	}
	

}












//屠宰信息
function animalInInfo(ptbId,divID){
	
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
   				//var zlxx_start_html = "<tr bgcolor=\"#f2f2f2\"><td>(生猪进厂信息)</td></tr><tr bgcolor=\"#f2f2f2\"><td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody>";
   				//var zlxx_end_html = "</tbody></table></td></tr>";
   				var zlxx_befortr = "<tr bgcolor=\"#f2f2f2\"><td>屠宰厂名称</td><td>进场日期</td><td>货主名称</td><td>产地检疫证号</td><td>进场数量</td><td>采购价</td><td>养殖场名称</td></tr>";
   				for(var i=0;i<animalInInfoList.length;i++){
   					var animalInInfo = animalInInfoList[i];
   					
   					zlxx_befortr += '<tr bgcolor="#ffffff" class="bor_b">\
   					    <td>'+animalInInfo.butcherFacName+'</td>\
   				        <td>'+animalInInfo.inDate+'</td>\
   				        <td>'+animalInInfo.sellerName+'</td>\
   				        <td>'+animalInInfo.quarantineId+'</td>\
   				        <td>'+animalInInfo.quarantineNum+'</td>\
   				        <td>'+animalInInfo.price+'</td>\
   				        <td>'+animalInInfo.farmName+'</td>\
   				        </tr> ';
   				}
   				//var liList = zlxx_start_html + zlxx_befortr+zlxx_end_html;
   				var liList =zlxx_befortr;
   				
   				$("#"+divID).append(section_1+"屠宰厂肉类蔬菜进场基本信息"+section_2+liList+section_3);
   				
   			}
	    }
   });
	
}


//屠宰检验检疫信息
function quarantineInfo(ptbId,divID){
	
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
   				var liList = "<tr bgcolor=\"#f2f2f2\"><td>屠宰厂名称</td><td>检疫合格证号</td><td>货主名称</td><td>产地检疫证号</td><td>采样头数</td><td>采样样品编号</td><td>抽检日期</td></tr>";
   				
   				for(var i=0;i<quarantineInfoList.length;i++){
   					var quarantineInfo = quarantineInfoList[i];
   					if(i==0){
   						baseAid = quarantineInfo.quarantineAnimalProductsId;
   					}
   					liList += '<tr bgcolor="#ffffff" class="bor_b">\
   					    <td>'+quarantineInfo.butcherFacName+'</td>\
   				        <td>'+quarantineInfo.quarantineAnimalProductsId+'</td>\
   				        <td>'+quarantineInfo.sellerName+'</td>\
   				        <td>'+quarantineInfo.quarantineId+'</td>\
   				        <td>'+quarantineInfo.sampleNum+'</td>\
   				        <td>'+quarantineInfo.sampleId+'</td>\
   				        <td>'+quarantineInfo.checkDate+'</td>\
   				        </tr> ';
   				}
   				
   				$("#"+divID).append(section_1+"屠宰厂检疫检验信息"+section_2+liList+section_3);
   				
   			}
	    }
   });
	
	
}




//交易信息(屠宰交易或者批发交易，基本信息)
function meatOutInfoBaseInfo(ptbId,flag,divID){
	
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
 				var liList = "<tr bgcolor=\"#f2f2f2\">" +
 						"<td>"+qycode+"</td>" +
 						"<td>"+qyname+"</td>" +
 						"<td>交易日期</td>" +
 						"<td>货主名称</td>" +
 						"<td>买主名称</td>" +
 						"<td>到达地</td>" +
 						"<td>交易凭证号</td>" +
 						"</tr>";
 				
 				for(var i=0;i<meatOutInfoBaseList.length;i++){
 					var meatOutInfoBase = meatOutInfoBaseList[i];
 					liList += '<tr bgcolor="#ffffff" class="bor_b">\
 						<td>'+meatOutInfoBase.butcherFacId+'</td>\
 					    <td>'+meatOutInfoBase.butcherFacName+'</td>\
 				        <td>'+meatOutInfoBase.tranDate+'</td>\
 				        <td>'+meatOutInfoBase.sellerName+'</td>\
 				        <td>'+meatOutInfoBase.buyerName+'</td>\
 				        <td>'+meatOutInfoBase.dest+'</td>\
 				        <td>'+meatOutInfoBase.tranId+'</td>\
 				        </tr> ';
 				}
 				
 				$("#"+divID).append(section_1+showtitle+section_2+liList+section_3);
 			}
	    }
 });
	
	
}







//进场基本信息(2批发、3零售、4超市进场基本信息)
function marketInInfoBaseInfo(ptbId,flag,divID){
	
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
				
		        
				var liList = "<tr bgcolor=\"#f2f2f2\">" +
						"<td>"+qycode+"</td>" +
						"<td>"+qyname+"</td>" +
						"<td>进场日期</td>" +
						"<td>"+qycode2+"</td>" +
						"<td>"+qyname2+"</td>" +
						"<td>交易凭证号</td>" +
						"<td>动物产品检疫合格证号</td>" +
						"<td>肉品品质检验合格证号</td>" +
						"<td>进货批次号</td>" +
						"</tr>";
				
				for(var i=0;i<marketInInfoBaseList.length;i++){
					var marketInInfoBase = marketInInfoBaseList[i];
					if(i==0){
   						baseAid = marketInInfoBase.quarantineAnimalProductsId;
   					}
					liList += '<tr bgcolor="#ffffff" class="bor_b">\
						<td>'+marketInInfoBase.marketId+'</td>\
					    <td>'+marketInInfoBase.marketName+'</td>\
				        <td>'+marketInInfoBase.inDate+'</td>\
				        <td>'+marketInInfoBase.wholesalerId+'</td>\
				        <td>'+marketInInfoBase.wholesalerName+'</td>\
				        <td>'+marketInInfoBase.tranId+'</td>\
				        <td>'+marketInInfoBase.quarantineAnimalProductsId+'</td>\
				        <td>'+marketInInfoBase.inspectionMeatId+'</td>\
				        <td>'+marketInInfoBase.batchId+'</td>\
				        </tr> ';
				}
				
				$("#"+divID).append(section_1+showtitle+section_2+liList+section_3);
			}
	    }
});
	
	
}


/**
 * 批发市场检验信息
 * @param ptbId
 * @param flag
 * @param divID
 */
function qmarketDetectionInfo(ptbId,divID){
	
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
	    		var liList = "<tr bgcolor=\"#f2f2f2\"><td>批发市场编码</td><td>批发市场名称</td><td>批发商编码</td><td>批发商名称</td><td>交易凭证号</td><td>动物产品检疫合格证号</td><td>肉品品质检验证号</td><td>检测日期</td></tr>";
	    		
				for(var i=0;i<qmarketDetectionInfoList.length;i++){
					var qmarketDetectionInfo = qmarketDetectionInfoList[i];
					liList += '<tr bgcolor="#ffffff" class="bor_b">\
			        	<td>'+qmarketDetectionInfo.marketId+'</td>\
					    <td>'+qmarketDetectionInfo.marketName+'</td>\
				        <td>'+qmarketDetectionInfo.wholesalerId+'</td>\
				        <td>'+qmarketDetectionInfo.wholesalerName+'</td>\
				        <td>'+qmarketDetectionInfo.tranId+'</td>\
				        <td>'+qmarketDetectionInfo.quarantineAnimalProductsId+'</td>\
				        <td>'+qmarketDetectionInfo.inspectionMeatId+'</td>\
				        <td>'+qmarketDetectionInfo.detectionDate+'</td>\
				        </tr> ';
				}
				$("#"+divID).append(section_1+"批发市场检验信息"+section_2+liList+section_3);
	    	}
	    }
	});
	
}


//零售市场销售汇总信息
function retailMarketTranInfoSumm(ptbId,divID){
	
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
				var liList = "<tr bgcolor=\"#f2f2f2\"><td>零售市场名称</td><td>进货日期</td><td>销售日期</td><td>零售商名称</td><td>零售摊位号</td><td>交易凭证号</td><td>动物产品检疫合格证号</td><td>商品名称</td></tr>";
				
				for(var i=0;i<retailMarketTranInfoSummList.length;i++){
					var retailMarketTranInfoSumm = retailMarketTranInfoSummList[i];
					liList += '<tr bgcolor="#ffffff" class="bor_b">\
					    <td>'+retailMarketTranInfoSumm.retailMarketName+'</td>\
				        <td>'+retailMarketTranInfoSumm.inDate+'</td>\
				        <td>'+retailMarketTranInfoSumm.saleDate+'</td>\
				        <td>'+retailMarketTranInfoSumm.retailerName+'</td>\
				        <td>'+retailMarketTranInfoSumm.positionId+'</td>\
				        <td>'+retailMarketTranInfoSumm.tranId+'</td>\
				        <td>'+retailMarketTranInfoSumm.quarantineAnimalProductsId+'</td>\
				        <td>'+retailMarketTranInfoSumm.goodsName+'</td>\
				        </tr> ';
				}
				
				$("#"+divID).append(section_1+"零售市场销售汇总信息"+section_2+liList+section_3);
	    	}
	    }
	});
	
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




///////////////////////////////////////////////////////////////////////

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
