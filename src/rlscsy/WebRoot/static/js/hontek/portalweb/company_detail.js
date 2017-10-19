var proUrl = "portal_Company_findList.action";
var basePath = "";
var filePath = "";

$(function() {
	basePath = $('#basePath').val();
	filePath = $("#filePath").val();

	getList(1,1,"");
});

/*获取列表*/
function getList(nowPage,pageSize,searchTitle){
	
	loading();//加载中的gif图片
	var entId = $('#entId').val();
	var url = proUrl+"?page=1&rows=1";
	var enterprise ={};
	enterprise["enterprise.entId"]=entId;
	
	$.ajax( {
		url : url,
		async : false,
		data : enterprise,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var enterprise = result.rows[0];
			
			var htm = "";
			var name = "";
			var intro = "";
			var entCode = "";
			var regAddr = "";
			var postCode = "";
			var email = "";
			var logoUrl = "";
			var domName = "";
			var typeName = "";
			var parentName = "";
			var dimenno = "";
			
			var legalPerson = "";
			var manageAddr = "";
			var tel = "";
			
			name = enterprise.name;
//			if(name.length>13){name=name.substring(0, 12)};
			intro= enterprise.intro;
			entCode= enterprise.entCode;
			regAddr= enterprise.regAddr;
			postCode= enterprise.postCode;
			email = enterprise.email;
			logoUrl= enterprise.logoUrl;
			typeName= enterprise.typeName;
//			if(typeName.length>10){typeName=typeName.substring(0,9)};
//			dimenno= enterprise.dimenno;
			legalPerson = enterprise.legalPerson;
			manageAddr = enterprise.manageAddr;
			tel = enterprise.tel;
			if(logoUrl==''){
				temlogoUrl = basePath + "static/image/portalweb/no_pro.png";
			}else{
				temlogoUrl = filePath+logoUrl;
			}	
			htm ="<div class=\"product_picture\">" +
			"<h4>"+name+"</h4>"+
			"<div class=\"product_img\"><img src=\""+temlogoUrl+"\"  width='250' height='200' /></div>"+
			"</div>"+
			"<div class=\"product_text\">"+
			    "<p><strong>企业编码：</strong>"+entCode+"</p>"+
			    "<p><strong>企业类型：</strong>"+typeName+"</p>"+
			    "<p><strong>企业法人：</strong>"+legalPerson+"</p>"+
			    "<p><strong>联系电话：</strong>"+tel+"</p>"+
			    "<p><strong>邮政编码：</strong>"+postCode+"</p>"+
			    "<p><strong>电子邮箱：</strong>"+email+"</p>"+
			    "<p><strong>注册地址：</strong>"+regAddr+"</p>"+
			    "<p><strong>经营地址：</strong>"+manageAddr+"</p>"+
			    "<p><strong>企业简介：</strong>"+intro+"</p>"+
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
	$("#product").html(loading);
	}

/*
function checkSearch() {
	var searchTitle = $('#searchTitle');
	if (searchTitle.val() == "") {
		alert('请输入标题关键字！');
		searchTitle.focus();
		return false;
	} else {
		getList(1,1,searchTitle.val());
		return true;
	}
}
*/
