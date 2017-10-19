var proUrl = "portal_Product_findList.action";
var pageSize = 2;	//设置每页显示条数;
var pageSize1 = 5;	//设置每页显示条数(动物类);
var pageSize2 = 5;	//设置每页显示条数(企业溯源类);
var CompanyUrl = "portal_Company_findList.action";

var pageSize4 = 5;	//设置每页显示条数(企业分类溯源);
var proTypeUrl = "portal_Company_findProTypeQrcodeList.action";

var basePath = "";
var basePath2 = "";

$(function() {
	basePath = $('#basePath').val();
	basePath2 = $('#basePath2').val();
	
	findAnimalProductList(1,pageSize1); //动物类产品表格
	
	getSuyuanCompanyList(1,pageSize2);    //企业溯源模块
	
//	getProTypeCompanyList(1,pageSize4);  //企业分类溯源列表
	
//	getProductList(1,pageSize,"");		//产品（有图）列表
	
});

/*获取列表*/
function getProductList(nowPage,pageSize,searchTitle){
	
	proloading("product_list");//加载中的gif图片
	
	var url = proUrl+"?page="+nowPage+"&rows="+pageSize;
	var product ={};
	product['product.state']=1;
	$('#pageNum').val(nowPage);//页面上保存当前的pageNum;
	
	$.ajax( {
		url : url,
		async : false,
		data : product,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			createPageTool(nowPage,total,pageSize);
			
			var htm = "";
			var htm1 = "<div class=\"product mar_right\">";
			var htm2 = "<div class=\"product\">";
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
					//获取产品图片
					
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
					if(temcodeImg!=''){
						temcodeImg = basePath2+"nytsyFiles/qrcode/"+temcodeImg;
					}else{
						temcodeImg = basePath+"static/image/portalweb/no_pro.png";
					}
					
					if(i == 0||i%2==0){
						htm = htm+ htm1+"<h4>"+proName+"<!-- <span class=\"CPC\"  onmouseover=\"showPic("+i+");\"  onmouseout=\"hidePic("+i+")\"></span> --> </h4>"+
						"<div class=\"CPC_img\" id=\"CPC_img"+i+"\" style=\"display:none;\"><img src=\""+temcodeImg+"\" /><p>二维码:"+dimenno+"</p></div>"+
						"<div class=\"product_img\"><a target=\"_blank\" href=\"portalweb/product_detail.jsp?proId="+proId+"\"><img src=\""+temproImg+"\" style=\"width: 330px;height: 210px;\"/></a></div>"+
						"<div class=\"product_text\">"+
						    "<p>品种："+typeName+"</p>"+
						    "<p>地址："+distributorAddr+"</p>"+
						    "<p>"+entName+"</p>"+
						"</div>"+
						"<div class=\"product_time\">"+
							"<div class=\"product_time_txt\">上市时间："+updateTime+" </div>"+
						    "<div class=\"btn btn_pro_go\"><a target=\"_blank\" href=\"portalweb/product_detail.jsp?proId="+proId+"\">去看看</a></div></div></div>";
					}else{
						htm = htm+ htm2+"<h4>"+proName+"<!-- <span class=\"CPC\" onmouseover=\"showPic("+i+");\"  onmouseout=\"hidePic("+i+")\"></span> --></h4>"+
						"<div class=\"CPC_img\" id=\"CPC_img"+i+"\" style=\"display:none;\"><img src=\""+temcodeImg+"\" /><p>二维码:"+dimenno+"</p></div>"+
						"<div class=\"product_img\"><a target=\"_blank\" href=\"portalweb/product_detail.jsp?proId="+proId+"\"><img src=\""+temproImg+"\" style=\"width: 330px;height: 210px;\"/></a></div>"+
						"<div class=\"product_text\">"+
							"<p>品种："+typeName+"</p>"+
						    "<p>地址："+distributorAddr+"</p>"+
						    "<p>"+entName+"</p>"+
						"</div>"+
						"<div class=\"product_time\">"+
							"<div class=\"product_time_txt\">上市时间："+updateTime+" </div>"+
						    "<div class=\"btn btn_pro_go\"><a target=\"_blank\" href=\"portalweb/product_detail.jsp?proId="+proId+"\">去看看</a></div></div></div>";
					}
					if((i+1==result.rows.length)){
							htm = htm+"<div class=\"clear\"></div>";
						}
			}
			
			$("#product_list").html(htm);
			
			
		}
	});
}

//生成 分页工具
function createPageTool(nowPage,total,pageSize){
	var pageCount = Math.ceil(total/pageSize);
	var perpage = (nowPage-1)>0?(nowPage-1):1;
	var nextpage = (nowPage+1)<=pageCount?(nowPage+1):pageCount;
	var tools1 = "第"+nowPage+"页/共"+pageCount+"页&nbsp;&nbsp;";
	var tools2 = "<a href=\"javascript:void(0)\" onclick=\"goPage("+1+");\">首页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage("+perpage+");\">上一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage("+nextpage+");\">下一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage("+pageCount+");\">尾页</a>&nbsp;&nbsp;";
	var tools3 = "跳转至:<select name=\"\" onchange=\"goPage(this.value)\">";

	for(var i=1;i<=pageCount;i++){
		if(i==nowPage){
			tools3 = tools3 +"<option  value=\""+i+"\" selected=\"selected\">"+i+"</option>";
		}else{
			tools3 = tools3 +"<option  value=\""+i+"\">"+i+"</option>";
		}
	}
	tools3 =tools3 +"</select>";
  
	$("#pageTools").html(tools1+tools2+tools3);
}



//翻页方法
function goPage(tem){
	var pageNum = $('#pageNum').val();//获取页面保存的当前页码
	pageNum = parseInt(pageNum);
	tem = parseInt(tem);
	if(tem!=pageNum)
	{
		getProductList(tem,pageSize,"");
		
	}
}

//显示二维码\图片
function showPic(tem){
	$("#CPC_img"+tem).show(); 
}
//隐藏二维码\图片
function hidePic(tem){
	$("#CPC_img"+tem).hide();
}

//加载前显示gif
function proloading(divId){
	var loading = "<div  align=\"center\"><img src=\""+basePath+"static/image/comm/waitanim.gif\"/></div>";
	$("#"+divId).html(loading);
	}


var htr = "<tr class=\"headcolor\">" +
"<th width=\"15%\" height=\"28\" align=\"center\"><strong><font style=\"color: #ff6600\">追溯码</font></strong></th>" +
"<th width=\"35%\" align=\"center\"><strong><font style=\"color: #ff6600\">企业名称</font></strong></th>" +
"<th width=\"40%\"><strong><font style=\"color: #ff6600\">企业地址</font></strong></th>" +
"<th width=\"10%\" align=\"center\"><strong><font style=\"color: #ff6600\">操作</font></strong></th>" +
"</tr>";

/*获取动物溯源数据列表*/
function findAnimalProductList(nowPage,pageSize1){
	
	proloading("animal_product_list");//加载中的gif图片
	
	var url = "portal_Company_findList.action?page="+nowPage+"&rows="+pageSize1;
	
	var enterprise ={};
	enterprise["enterprise.sysCode"]="A002001";
	
	$('#pageNum1').val(nowPage);//页面上保存当前的pageNum;
	
	$.ajax( {
		url : url,
		async : false,
		data : enterprise,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			
			var htm = "";
			var name = "";
			var entCode = "";
			var regAddr = "";
			var typeName = "";
			var legalPerson = "";
			var manageAddr = "";
			var tel = "";
			var entId = "";
			
			for ( var i = 0; i < result.rows.length; i++) {
				var ent = result.rows[i];
				name = ent.name;
//				if(name.length>13){name=name.substring(0, 12)};
				entCode= ent.entCode;
				regAddr= ent.regAddr;
				typeName= ent.typeName;
				legalPerson = ent.legalPerson;
				manageAddr = ent.manageAddr;
				tel = ent.tel;
				entId = ent.entId;	
			
				htm = htm +
				"<tr height=\"25\" >"+
				"<td>"+entCode+"</td>"+
				"<td align=\"left\">"+name+"</td>"+
				"<td align=\"left\">"+regAddr+"</td>"+
//				"<td align=\"center\">"+tel+"</td>"+
				"<td align=\"center\">"+
//				"<a target=\"_blank\" href=\""+basePath+"portalweb/suyuan_detail2.jsp?dimenno="+entCode+"\">查看详细</a>"+
				"<a target=\"_blank\" href=\""+basePath+"portalweb/suyuan_entdetail.jsp?dimenno="+entCode+"\">查看详细</a>"+
				"</td>"+
				"</tr>";
			}
			
			if(total>0){
				$("#animal_product_list").html(htr+htm);
				
				createPageTool1(nowPage,total,pageSize1);
				
				altRows("animal_product_list");
			}else{
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			
			
		}
	});
}


//动物类生成 分页工具
function createPageTool1(nowPage1,total1,pageSize1){
	var pageCount = Math.ceil(total1/pageSize1);
	var perpage = (nowPage1-1)>0?(nowPage1-1):1;
	var nextpage = (nowPage1+1)<=pageCount?(nowPage1+1):pageCount;
	var tools1 = "第"+nowPage1+"页/共"+pageCount+"页&nbsp;&nbsp;";
	var tools2 = "<a href=\"javascript:void(0)\" onclick=\"goPage1("+1+");\">首页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage1("+perpage+");\">上一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage1("+nextpage+");\">下一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage1("+pageCount+");\">尾页</a>&nbsp;&nbsp;";
	var tools3 = "跳转至:<select name=\"\" onchange=\"goPage1(this.value)\">";

	for(var i=1;i<=pageCount;i++){
		if(i==nowPage1){
			tools3 = tools3 +"<option  value=\""+i+"\" selected=\"selected\">"+i+"</option>";
		}else{
			tools3 = tools3 +"<option  value=\""+i+"\">"+i+"</option>";
		}
	}
	tools3 =tools3 +"</select>";
  
	$("#pageTools1").html(tools1+tools2+tools3);
}



//动物类翻页方法
function goPage1(tem){
	var pageNum1 = $('#pageNum1').val();//获取页面保存的当前页码
	pageNum1 = parseInt(pageNum1);
	tem = parseInt(tem);
	if(tem!=pageNum1)
	{
		findAnimalProductList(tem,pageSize1);
		
	}
}




var htr_company = "<tr class=\"headcolor\">" +
"<th width=\"15%\" height=\"28\" align=\"center\"><strong><font style=\"color: #ff6600\">追溯码</font></strong></th>" +
"<th width=\"35%\" align=\"center\"><strong><font style=\"color: #ff6600\">企业名称</font></strong></th>" +
"<th width=\"40%\"><strong><font style=\"color: #ff6600\">企业地址</font></strong></th>" +
"<th width=\"10%\" align=\"center\"><strong><font style=\"color: #ff6600\">操作</font></strong></th>" +
"</tr>";

//溯源企业列表
function getSuyuanCompanyList(nowPage,pageSize2){
	
	proloading("index_company_list");//加载gif
	
	var url = CompanyUrl+"?page="+nowPage+"&rows="+pageSize2;
	
	var enterprise ={};
	enterprise["enterprise.sysCode"]="086020";
	$('#pageNum2').val(nowPage);//页面上保存当前的pageNum;
	
	$.ajax( {
		url : url,
		async : false,
		data : enterprise,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			
			var htm = "";
			var name = "";
			var entCode = "";
			var regAddr = "";
			var typeName = "";
			var legalPerson = "";
			var manageAddr = "";
			var tel = "";
			var entId = "";
			
			for ( var i = 0; i < result.rows.length; i++) {
				var ent = result.rows[i];
				name = ent.name;
//				if(name.length>13){name=name.substring(0, 12)};
				entCode= ent.entCode;
				regAddr= ent.regAddr;
				typeName= ent.typeName;
				legalPerson = ent.legalPerson;
				manageAddr = ent.manageAddr;
				tel = ent.tel;
				entId = ent.entId;	
			
				htm = htm +
				"<tr height=\"25\" >"+
				"<td>"+entCode+"</td>"+
				"<td align=\"left\">"+name+"</td>"+
				"<td align=\"left\">"+regAddr+"</td>"+
//				"<td align=\"center\">"+tel+"</td>"+
				"<td align=\"center\">"+
//				"<a target=\"_blank\" href=\""+basePath+"portalweb/suyuan_detail2.jsp?dimenno="+entCode+"\">查看详细</a>"+
				"<a target=\"_blank\" href=\""+basePath+"portalweb/suyuan_entdetail.jsp?dimenno="+entCode+"\">查看详细</a>"+
				"</td>"+
				"</tr>";
			}
			
			if(total>0){
				$("#index_company_list").html(htr_company+htm);
				
				createPageTool2(nowPage,total,pageSize2);
				
				altRows("index_company_list");
			}else{
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			
		}
	});
}


//企业类生成分页工具
function createPageTool2(nowPage2,total2,pageSize2){
	var pageCount = Math.ceil(total2/pageSize2);
	var perpage = (nowPage2-1)>0?(nowPage2-1):1;
	var nextpage = (nowPage2+1)<=pageCount?(nowPage2+1):pageCount;
	var tools1 = "第"+nowPage2+"页/共"+pageCount+"页&nbsp;&nbsp;";
	var tools2 = "<a href=\"javascript:void(0)\" onclick=\"goPage2("+1+");\">首页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage2("+perpage+");\">上一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage2("+nextpage+");\">下一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage2("+pageCount+");\">尾页</a>&nbsp;&nbsp;";
	var tools3 = "跳转至:<select name=\"\" onchange=\"goPage2(this.value)\">";

	for(var i=1;i<=pageCount;i++){
		if(i==nowPage2){
			tools3 = tools3 +"<option  value=\""+i+"\" selected=\"selected\">"+i+"</option>";
		}else{
			tools3 = tools3 +"<option  value=\""+i+"\">"+i+"</option>";
		}
	}
	tools3 =tools3 +"</select>";

	$("#pageTools2").html(tools1+tools2+tools3);
}



//企业类翻页方法
function goPage2(tem){
	var pageNum2 = $('#pageNum2').val();//获取页面保存的当前页码
	pageNum2 = parseInt(pageNum2);
	tem = parseInt(tem);
	if(tem!=pageNum2)
	{
		getSuyuanCompanyList(tem,pageSize2);
		
	}
}



var htr_proType_company = "<tr class=\"headcolor\">" +
"<th width=\"25%\" height=\"28\" align=\"center\"><strong><font style=\"color: #ff6600\">追溯码</font></strong></th>" +
"<th width=\"30%\"><strong><font style=\"color: #ff6600\">分类名称</font></strong></th>" +
"<th width=\"35%\" align=\"center\"><strong><font style=\"color: #ff6600\">企业名称</font></strong></th>" +
"<th width=\"10%\" align=\"center\"><strong><font style=\"color: #ff6600\">操作</font></strong></th>" +
"</tr>";

//企业分类溯源列表
function getProTypeCompanyList(nowPage,pageSize4){
	
	proloading("proType_company_list");//加载gif
	
	var url = proTypeUrl+"?page="+nowPage+"&rows="+pageSize4;
	
	var ptq ={};
//	ptq["ptq.sysCode"]="086020";
	$('#pageNum4').val(nowPage);//页面上保存当前的pageNum;
	
	$.ajax( {
		url : url,
		async : false,
		data : ptq,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			var htm = "";
			
			var ptqId = "";
			var entId = "";
			var typeId = "";
			var dimenno = "";
			var typeName = "";	//分类名称；
			var companyName = "";	//企业名称；
						
			for ( var i = 0; i < result.rows.length; i++) {
				var ptq = result.rows[i];
				companyName = ptq.companyName;
//				if(companyName.length>13){companyName=companyName.substring(0, 12)};
				dimenno= ptq.dimenno;
				typeName= ptq.typeName;
				
				ptqId = ptq.ptqId;	
				entId = ptq.entId;
				typeId = ptq.typeId;
			
				htm = htm +
				"<tr height=\"25\" >"+
				"<td>"+dimenno+"</td>"+
				"<td align=\"left\">"+typeName+"</td>"+
				"<td align=\"left\">"+companyName+"</td>"+
//				"<td align=\"center\">"+tel+"</td>"+
				"<td align=\"center\">"+
				"<a target=\"_blank\" href=\""+basePath+"portalweb/suyuan_entdetail.jsp?dimenno="+dimenno+"\">查看详细</a>"+
				"</td>"+
				"</tr>";
			}
			
			if(total>0){
				$("#proType_company_list").html(htr_proType_company+htm);
				
				createPageTool4(nowPage,total,pageSize4);
				
				altRows("proType_company_list");
			}else{
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			
		}
	});
}


//企业分类溯源生成分页工具
function createPageTool4(nowPage4,total4,pageSize4){
	var pageCount = Math.ceil(total4/pageSize4);
	var perpage = (nowPage4-1)>0?(nowPage4-1):1;
	var nextpage = (nowPage4+1)<=pageCount?(nowPage4+1):pageCount;
	var tools1 = "第"+nowPage4+"页/共"+pageCount+"页&nbsp;&nbsp;";
	var tools2 = "<a href=\"javascript:void(0)\" onclick=\"goPage4("+1+");\">首页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage4("+perpage+");\">上一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage4("+nextpage+");\">下一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage4("+pageCount+");\">尾页</a>&nbsp;&nbsp;";
	var tools3 = "跳转至:<select name=\"\" onchange=\"goPage4(this.value)\">";

	for(var i=1;i<=pageCount;i++){
		if(i==nowPage4){
			tools3 = tools3 +"<option  value=\""+i+"\" selected=\"selected\">"+i+"</option>";
		}else{
			tools3 = tools3 +"<option  value=\""+i+"\">"+i+"</option>";
		}
	}
	tools3 =tools3 +"</select>";

	$("#pageTools4").html(tools1+tools2+tools3);
}



//企业分类溯源翻页方法
function goPage4(tem){
	var pageNum4 = $('#pageNum4').val();//获取页面保存的当前页码
	pageNum4 = parseInt(pageNum4);
	tem = parseInt(tem);
	if(tem!=pageNum4)
	{
		getProTypeCompanyList(tem,pageSize4);
		
	}
}








///表格样式控制
function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){ 
			if(i==0){
//				rows[i].className = "headcolor";
			}else 
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
}
