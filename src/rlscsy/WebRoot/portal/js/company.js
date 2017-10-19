var compUrl = "portal_Company_findList.action";
var rows = 8;	//设置每页显示条数;
var basePath = "";
var entName = "";
var filePath = "";

$(function() {
	basePath = $('#basePath').val();
	entName = $('#entName').val();
	filePath = $("#filePath").val();

	getCompanyList(1,rows,entName);
	getNewsList();//应急管理列表
});

function getCompanyList(nowPage,rows,searchTitle){
	
	loading();//加载gif
	var url = compUrl+"?page="+nowPage+"&rows="+rows;	
	var enterprise ={};	
	if(searchTitle!=""){
		enterprise["company.name"]=searchTitle;
	}
		
	$('#pageNum').val(nowPage);//页面上保存当前的pageNum;
	
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
			var intro = "";
			var parentName = "";
			var logoUrl = "";
			var comId = "";
			for ( var i = 0; i < result.rows.length; i++) {
					var temlogoUrl = "";
					name = result.rows[i].name;					
					if(intro.length>151){intro=intro.substring(0, 150)+"...";}
					logoUrl = result.rows[i].comLogo;
					
					comId = result.rows[i].comId;
					var linkUrl = "sydetail.jsp?dimenno="+result.rows[i].comCode;
					if(logoUrl==''||logoUrl==undefined){
						temlogoUrl = basePath + "portal/images/qiye.jpg";
					}else{
						temlogoUrl = filePath+logoUrl;
					}
					
					var pl = (i+1)%4==0?"style=\"margin-right:0px;height: 242px;\"":"style=\"height: 242px;\"";
					
					htm+='<li><a href="'+linkUrl+'">\
							<div class="company-catalog-img">\
							<img src="'+temlogoUrl+'" /></div>\
							<div class="company-catalog-content">\
							<p>'+name+'</p>\
							</div> </a></li>';
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#qiye_list").html(htm);
			createPageTool(nowPage,total,rows);
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



//翻页
function goPage(tem){
	entName = $('#entName').val();
	var pageNum = $('#pageNum').val();//页面保存的当前页码
	pageNum = parseInt(pageNum);
	tem = parseInt(tem);
	if(tem!=pageNum)
	{
		getCompanyList(tem,rows,entName);
	}
}


//加载前显示gif
function loading(){
	var loading = "<div  align=\"center\"><img src=\"../static/image/comm/waitanim.gif\"/></div>";
	$("#qiye_list").html(loading);
	}


//应急管理list
function getNewsList(){
	var infoUrl = "portal_Info_findNewsList.action";
	var types = 1;//默认类型为“农产品安全事件公布”
	var pageSize = 5;	//设置每页显示条数;
	var url = infoUrl+"?page=1"+"&rows="+pageSize;
	
	var info ={};
	info["info.rsts"]=1;
	info["info.typeName"]="应急管理";	
	
	$.ajax( {
		url : url,
		async : false,
		data : info,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			$("#news_list").html('');		
			for ( var i = 0; i < result.rows.length; i++) {
					 title = result.rows[i].title;
					if(title.length>20){title=title.substring(0,20)+"...";}
					infoId = result.rows[i].infoId;
					htm = '<li><a href="newsdetail.jsp?typecode=news&infoId='+infoId+'">'+title+'</a></li>';
					$("#news_list").append(htm);
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
				$("#news_list").html(htm);
			}
		}
	});
}

