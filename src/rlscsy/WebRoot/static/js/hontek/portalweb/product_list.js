var proUrl = "portal_Product_findList.action";
var pageSize = 4;	//设置每页显示条数;
var basePath = "";
var basePath2 = "";
var proNmaes = "";//搜索传值
$(function() {
	basePath = $('#basePath').val();
	basePath2 = $('#basePath2').val();
	proNmaes = $('#proNmaes').val();
//	alert(proNames);
	getList(1,pageSize,proNmaes);
});

/*获取列表*/
function getList(nowPage,pageSize,searchTitle){
	
	loading();//加载中的gif图片
	
	var url = proUrl+"?page="+nowPage+"&rows="+pageSize;
	var product ={};
	product["product.state"]='1';
	if(searchTitle!=''){
		$('#proNmaes').val(searchTitle);
		product["product.proName"]=searchTitle;
	}
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
			
			var htm = "";
			var htm1 = "<div class=\"product mar_right\">";
			var htm2 = "<div class=\"product\">";
			var proName = "";//产品名称
			var typeName = "";//分类名称
			var entName = "";//机构名称
			var dimenno = "";//产品二维码
			var sourceAddr = "";//生产地址
			var distributorAddr = "";//经销商地址
			var updateTime = "";//updateTime
			var proId = "";
			var logoUrl = "";//二维码logo地址
			
		if(result.rows.length>0){
			for ( var i = 0; i < result.rows.length; i++) {
					var temcodeImg = ""; //二维码图片
					var temproImg = "";//产品图片
					
					proName = result.rows[i].proName;
					if(proName.length>25){proName=proName.substring(0, 24)};
					typeName= result.rows[i].typeName;
					if(typeName.length>25){typeName=typeName.substring(0,24)};
					entName= result.rows[i].entName;
					if(entName.length>25){entName=entName.substring(0,24)};
					sourceAddr= result.rows[i].sourceAddr;
					if(sourceAddr.length>25){sourceAddr=sourceAddr.substring(0,24)};
					distributorAddr= result.rows[i].distributorAddr;
					if(distributorAddr.length>25){distributorAddr=distributorAddr.substring(0,24)};
					
					dimenno= result.rows[i].dimenno;
					logoUrl= result.rows[i].logoUrl;
					temcodeImg = result.rows[i].codeImg;
					proId = result.rows[i].proId;
					
					updateTime = result.rows[i].updateTime;
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
						htm = htm+ htm1+"<h4>"+proName+"<!-- <span class=\"CPC\"  onmouseover=\"showPic("+i+");\"  onmouseout=\"hidePic("+i+")\"></span>--></h4>"+
						"<div class=\"CPC_img\" id=\"CPC_img"+i+"\" style=\"display:none;\"><img src=\""+temcodeImg+"\" /><p>二维码:"+dimenno+"</p></div>"+
						"<div class=\"product_img\"><a href=\"product_detail.jsp?proId="+proId+"\"><img src=\""+temproImg+"\" style=\"width: 330px;height: 210px;\"/></a></div>"+
						"<div class=\"product_text\">"+
						    "<p>品种："+typeName+"</p>"+
						    "<p>地址："+distributorAddr+"</p>"+
						    "<p>"+entName+"</p>"+
						"</div>"+
						"<div class=\"product_time\">"+
							"<div class=\"product_time_txt\">上市时间："+updateTime+" </div>"+
						    "<div class=\"btn btn_pro_go\"><a href=\"product_detail.jsp?proId="+proId+"\">去看看</a></div></div></div>";
						
					}else{
						htm = htm+ htm2+"<h4>"+proName+"<!-- <span class=\"CPC\" onmouseover=\"showPic("+i+");\"  onmouseout=\"hidePic("+i+")\"></span>--></h4>"+
						"<div class=\"CPC_img\" id=\"CPC_img"+i+"\" style=\"display:none;\"><img src=\""+temcodeImg+"\" /><p>二维码:"+dimenno+"</p></div>"+
						"<div class=\"product_img\"><a href=\"product_detail.jsp?proId="+proId+"\"><img src=\""+temproImg+"\" style=\"width: 330px;height: 210px;\"/></a></div>"+
						"<div class=\"product_text\">"+
							"<p>品种："+typeName+"</p>"+
						    "<p>地址："+distributorAddr+"</p>"+
						    "<p>"+entName+"</p>"+
						"</div>"+
						"<div class=\"product_time\">"+
							"<div class=\"product_time_txt\">上市时间："+updateTime+" </div>"+
						    "<div class=\"btn btn_pro_go\"><a href=\"product_detail.jsp?proId="+proId+"\">去看看</a></div></div></div>";
					}
					
					if((i+1==result.rows.length)){
							htm = htm+"<div class=\"clear\"></div>";
						}
			}
	}else{
		htm = "<div  align=\"center\"><img src=\""+basePath+"static/image/portalweb/defaultPic.jpg\"/></div>";
	}
			$("#product_list").html(htm);
			
			createPageTool(nowPage,total,pageSize);
		}
	});
}
//"<a href=\"product_detail.jsp?proId="+proId+"\"></a>"
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
	proNmaes = $('#proNmaes').val();
	var pageNum = $('#pageNum').val();//获取页面保存的当前页码
	pageNum = parseInt(pageNum);
	tem = parseInt(tem);
	if(tem!=pageNum)
	{
		getList(tem,pageSize,proNmaes);
		
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
function loading(){
	var loading = "<div  align=\"center\"><img src=\"static/image/comm/waitanim.gif\"/></div>";
	$("#product_list").html(loading);
	}

/*
function checkSearch() {
	var searchTitle = $('#searchTitle');
	if (searchTitle.val() == "") {
		alert('请输入标题关键字！');
		searchTitle.focus();
		return false;
	} else {
		getList(1,pageSize,searchTitle.val());
		return true;
	}
}
*/
