
$(function() {
	
	loading();//加载中的gif图片
	
	var basePath = $('#basePath').val();
	var basePath2 = $('#basePath2').val();
	
	var proId = $('#proId').val();
	var product ={};
	product["product.proId"]=proId;
		
	//获取产品图片
	var temproImg = "";//产品图片				
	$.ajax({
		url : 'proWeixin_findProAppList.action',
		data : {'productAppend.proId':proId},
		dataType : 'json',
		success : function(result1) {
			if(result1.total>0){
				temproImg = result1.rows[0].path;			
			}
			if(temproImg!=''){
				temproImg = basePath2+"nytsyFiles/proimg/"+temproImg;
			}else{
				temproImg = basePath+"weixin/images/no_pro.png";
			}				
			$("#proimg").html('<img src="'+temproImg+'" width="300px" height="300px"/>');
		}
	});
	
	
	
	$.ajax( {
		url : "proWeixin_findList.action?tt="+Math.random(),
		data : product,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.total>0){
				var info = result.rows[0];				
				var htm = '<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC";>';
				var proName = "";//产品名称
				var typeName = "";//分类名称
				var proCode = "";//产品编码
				var barcode = "";//产品 条码
				var proDesc = "";//产品说明
				var sizeAttr = "";//产品规格
				var unit = "";//单位
				var entName = "";//机构名称
				var dimenno = "";//产品二维码
				var manufacturer = "";//生产商
				var sourceAddr = "";//生产地址
				var sourceTel="";
				var distributorAddr = "";//经销商地址
				var updateTime = "";//updateTime
							
				proName = info.proName;
				typeName= info.typeName;
				proCode= info.proCode;
				barcode= info.barcode;
				proDesc= info.proDesc;
				sizeAttr= info.sizeAttr;
				unit= info.unit;
				entName= info.entName;
				manufacturer = info.manufacturer;
				sourceAddr= info.sourceAddr;
				sourceTel = info.sourceTel;
				distributorAddr= info.distributorAddr;				
				dimenno= info.dimenno;
				logoUrl= info.logoUrl;				
				updateTime = info.updateTime;
			
				htm+='<tr bgcolor="#fff"><td class="td_title"><strong>产品名称：</strong></td><td>'+proName+'</td></tr>';
				htm+='<tr bgcolor="#fff"><td class="td_title"><strong>产品分类：</strong></td><td>'+typeName+'</td></tr>';
				htm+='<tr bgcolor="#fff"><td class="td_title"><strong>产品编码：</strong></td><td>'+proCode+'</td></tr>';
				htm+='<tr bgcolor="#fff"><td class="td_title"><strong>产品条码：</strong></td><td>'+barcode+'</td></tr>';
				//htm+='<tr bgcolor="#fff"><td class="td_title"><strong>产品规格：</strong></td><td>'+sizeAttr+'</td></tr>';
				htm+='<tr bgcolor="#fff"><td class="td_title"><strong>产品单位：</strong></td><td>'+unit+'</td></tr>';
				htm+='<tr bgcolor="#fff"><td class="td_title"><strong>生产商：</strong></td><td>'+manufacturer+'</td></tr>';
				htm+='<tr bgcolor="#fff"><td class="td_title"><strong>生产商电话：</strong></td><td>'+sourceTel+'</td></tr>';
				htm+='<tr bgcolor="#fff"><td class="td_title"><strong>生产商地址：</strong></td><td>'+sourceAddr+'</td></tr>';
				htm+='<tr bgcolor="#fff"><td class="td_title"><strong>产品说明：</strong></td><td>'+proDesc+'</td></tr>';
				htm+='</table>';				
				$("#ptable").html(htm);
			}
		}
	});

});





//加载前显示gif
function loading(){
	var loading = "<div  align=\"center\"><img src=\"../static/image/comm/waitanim.gif\"/></div>";
	$("#ptable").html(loading);
}

