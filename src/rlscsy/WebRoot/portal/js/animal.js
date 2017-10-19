var company_ptqurl = "portal_Company_findAnimalProTypeQrcode.action";
var filePath = "";

$(function() {
	filePath = $("#filePath").val();
	
	getCompanyPTQ(1,8);
	
});

function getCompanyPTQ(page,rows){
	$('#pageNum').val(page);//;
	$.ajax( {
		url : company_ptqurl,
		data: {"page":page,"rows":rows},
		type : 'post',
		dataType : 'json',
		success : function(result) {
		  if(result){
			  var animal = result.rows;
			  if(animal){
				  var total = result.total;
				  total = parseInt(total);
				  createPageTool(page,total,rows);
					
				  var ptqhtml = "";
				  for(var i=0;i<animal.length;i++){
					  var ptq = animal[i];
					  if(ptq){
						  var typeImg = ptq.typeImg;
						  typeImg = typeImg==""?"images/pic1.jpg":filePath+typeImg;
						  var regAddr = ptq.regAddr;
						  if(regAddr&&regAddr.length>13){
							  regAddr = regAddr.substring(0,12)+"..."
						  }
						  var typeName = ptq.typeName;
						  var dimenno = ptq.dimenno;
						  var companyName = ptq.companyName;
						  var linkUrl = "sydetail.jsp?dimenno="+dimenno;
						  
						  ptqhtml += "<li>"+
						  "<a target='_blank' href="+linkUrl+"><img src="+typeImg+" width=\"180\" />"+
						    "<p>"+
						        "追溯码:"+dimenno+"<br/>"+
						        "产品种类："+typeName+"<br/>"+
						        companyName+"<br>"+
						        "地址："+regAddr+"<br/>"+
						    "</p>"+
						 "</a><span class=\"more-check\"><a target='_blank' href=\""+linkUrl+"\">更多&nbsp;+</a></span>"+
						 "</li>";
						  
					  }
				  }
				  
				  $("#animalul").html(ptqhtml);
			  }
			  
		  }
		}
	});
	
}

//生成 分页工具
function createPageTool(nowPage,total,pageSize){
	var pageCount = Math.ceil(total/pageSize);
	var perpage = (nowPage-1)>0?(nowPage-1):1;
	var nextpage = (nowPage+1)<=pageCount?(nowPage+1):pageCount;
	
	var tools3 = "<select name=\"\" onchange=\"goPage(this.value)\">";
	for(var i=1;i<=pageCount;i++){
		if(i==nowPage){
			tools3 = tools3 +"<option  value=\""+i+"\" selected=\"selected\">"+i+"</option>";
		}else{
			tools3 = tools3 +"<option  value=\""+i+"\">"+i+"</option>";
		}
	}
	tools3 =tools3 +"</select>";
	
	var tools1 = "&nbsp;第&nbsp;"+tools3+"&nbsp;页/共&nbsp;"+pageCount+"&nbsp;页&nbsp;&nbsp;";
	var tools2 = "<a href=\"javascript:void(0)\" onclick=\"goPage("+1+");\">首页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage("+perpage+");\">上一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage("+nextpage+");\">下一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage("+pageCount+");\">尾页</a>&nbsp;&nbsp;";
	
	$("#pageTools").html(tools1+tools2);
}



//翻页方法
function goPage(tem){
	var pageNum = $('#pageNum').val();//获取页面保存的当前页码
	pageNum = parseInt(pageNum);
	tem = parseInt(tem);
	if(tem!=pageNum)
	{
		getCompanyPTQ(tem,8);
		
	}
}