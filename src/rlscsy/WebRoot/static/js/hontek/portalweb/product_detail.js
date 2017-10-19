var proUrl = "portal_Product_findList.action";
var basePath = "";
var basePath2 = "";
$(function() {
	basePath = $('#basePath').val();
	basePath2 = $('#basePath2').val();
	getList(1,1,"");
});

/*获取列表*/
function getList(nowPage,pageSize,searchTitle){
	
	loading();//加载中的gif图片
	var proId = $('#proId').val();
	var url = proUrl+"?page=1&rows=1";
	var product ={};
	product["product.proId"]=proId;
	
	
	//获取产品图片
	var temproImg = "";//产品图片				
	$.ajax({
		url : 'portal_ProductAppendix_findProAppList.action',
		async : false,
		data : {
		'productAppend.proId':proId
		},
		dataType : 'json',
		success : function(result1) {
			if(result1.total==0){
				return;
			}
			temproImg = result1.rows[0].path;
			
		}
	});
	if(temproImg!=''){
		temproImg = basePath2+"nytsyFiles/proimg/"+temproImg;
	}else{
		temproImg = basePath+"static/image/portalweb/no_pro.png";
	}
	
	
	$.ajax( {
		url : url,
		async : false,
		data : product,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			var info = result.rows[0];
			
			var htm = "";
			var proName = "";//产品名称
			var typeName = "";//分类名称
			var proCode = "";//产品编码
			var barcode = "";//产品 条码
			var proDesc = "";//产品说明
			var sizeAttr = "";//产品规格
			var unit = "";//单位
			var entName = "";//机构名称
			var dimenno = "";//产品二维码
			
			
			var updateTime = "";//updateTime
			var logoUrl = "";//二维码logo地址
			var temcodeImg = ""; //二维码图片
			
			var manufacturer = "";//生产商
			var sourceTel = "";//生产商电话
			var sourceAddr = "";//生产地址
			
			var distributor = "";//经销商
			var distributorTel = "";//经销商电话
			var distributorAddr = "";//经销商地址
			var retain = "";//保鲜防腐
			var storageConditions = "";//储藏条件
			var shelfLife = "";//保质期
			
			
			proName = info.proName;
//			if(proName.length>15){proName=proName.substring(0, 14)};
			typeName= info.typeName;
//			if(typeName.length>10){typeName=typeName.substring(0,9)};
			proCode= info.proCode;
			barcode= info.barcode;
			proDesc= info.proDesc;
			sizeAttr= info.sizeAttr;
			unit= info.unit;
			entName= info.entName;
//			if(entName.length>15){entName=entName.substring(0,14)};
			
			manufacturer = info.manufacturer;
			sourceTel = info.sourceTel;
			sourceAddr= info.sourceAddr;
//			if(sourceAddr.length>10){sourceAddr=sourceAddr.substring(0,9)};
			
			distributor = info.distributor;
			distributorTel = info.distributorTel;
			distributorAddr= info.distributorAddr;
//			if(distributorAddr.length>10){distributorAddr=distributorAddr.substring(0,9)};
			
			retain = info.retain;
			storageConditions = info.storageConditions;
			shelfLife = info.shelfLife;
			
			dimenno= info.dimenno;
			logoUrl= info.logoUrl;
			
			updateTime = info.updateTime;
			if(updateTime.length>11){updateTime=updateTime.substring(0, 10)};
			
			temcodeImg = info.codeImg;
			if(temcodeImg!=''){
				temcodeImg = basePath2+"nytsyFiles/qrcode/"+temcodeImg;
			}else{
				temcodeImg = basePath+"static/image/portalweb/no_pro.png";
			}
			
			htm ="<div class=\"product_picture\">" +
			"<h4>"+proName+"<!--<span class=\"CPC\"  onmouseover=\"showPic();\"  onmouseout=\"hidePic()\"></span>--></h4>"+
			"<div class=\"CPC_img\" id=\"CPC_img\" style=\"display:none;\"><img src=\""+temcodeImg+"\" /><p>二维码:"+dimenno+"</p></div>"+
			"<div class=\"product_img\"><img src=\""+temproImg+"\" style=\"width: 333px;height: 212px;\"/></div>" +
			"</div>"+
			"<div class=\"product_text\">"+
			    "<p><strong>产品编码：</strong>"+proCode+"</p>"+
			    "<p><strong>产品分类：</strong>"+typeName+"</p>"+
			    "<p><strong>产品条码：</strong>"+barcode+"</p>"+
			    //"<p><strong>规格：</strong>"+sizeAttr+"</p>"+
			    "<p><strong>单位：</strong>"+unit+"</p>"+
			    "<p><strong>生产商：</strong>"+manufacturer+"</p>"+
			    "<p><strong>生产地址：</strong>"+sourceAddr+"</p>"+
			    "<p><strong>生产商电话：</strong>"+sourceTel+"</p>"+
			    "<p><strong>经销商：</strong>"+distributor+"</p>"+
			    "<p><strong>经销地址：</strong>"+distributorAddr+"</p>"+
			    "<p><strong>经销商电话：</strong>"+distributorTel+"</p>"+
			    "<p><strong>保鲜防腐：</strong>"+retain+"</p>"+
			    "<p><strong>储藏条件：</strong>"+storageConditions+"</p>"+
			    "<p><strong>保质期：</strong>"+shelfLife+"</p>"+
			    "<p><strong>产品说明：</strong>"+proDesc+"</p>"+
			"</div>";
			
			$("#product").html(htm);
		}
	});
}




//显示二维码\图片
function showPic(){
	$("#CPC_img").show();
}
//隐藏二维码\图片
function hidePic(){
	$("#CPC_img").hide();
}

//加载前显示gif
function loading(){
	var loading = "<div  align=\"center\"><img src=\"static/image/comm/waitanim.gif\"/></div>";
	$("#product_list").html(loading);
	}
