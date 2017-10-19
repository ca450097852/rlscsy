var compUrl = "portal_Company_findList.action";
var rows = 5;	//设置每页显示条数;
var basePath = "";
var entName = "";
var filePath = "";

$(function() {
	basePath = $('#basePath').val();
	entName = $('#entName').val();
	filePath = $("#filePath").val();

	getCompanyList(1,rows,entName);
});

function getCompanyList(nowPage,rows,searchTitle){
	
	loading();//加载gif
	var url = compUrl+"?page="+nowPage+"&rows="+rows;
	
	var enterprise ={};
	
	if(searchTitle!="")
	{
		enterprise["enterprise.name"]=searchTitle;
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
			var entId = "";
			for ( var i = 0; i < result.rows.length; i++) {
					var temlogoUrl = "";
					name = result.rows[i].name;
					//if(name.length>16){name=name.substring(0, 15);}
					intro = result.rows[i].intro;
					if(intro.length>151){intro=intro.substring(0, 150)+"...";}
					parentName = result.rows[i].parentName;
					logoUrl = result.rows[i].logoUrl;
					entId = result.rows[i].entId;
					if(logoUrl==''){
						temlogoUrl = basePath + "static/image/portalweb/20130227150958855.jpg";
					}else{
						temlogoUrl = filePath+logoUrl;
					}
					htm=htm+"<div class=\"firm\">"+
					"<h4><a target=\"_blank\" href=\"company_detail.jsp?entId="+entId+"\">"+name+"</a></h4>"+
					"<div class=\"firm_content\">"+
						"<div class=\"firm_logo\"><a target=\"_blank\" href=\"company_detail.jsp?entId="+entId+"\"><img src=\""+temlogoUrl+"\" width='76' height='76' /></a></div>"+
					    "<div class=\"firm_text\"><a target=\"_blank\" href=\"company_detail.jsp?entId="+entId+"\">"+intro+"</a></div>"+
					    "<div class=\"clear\"></div></div></div>";
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#qiye_list").html(htm);
			createPageTool(nowPage,total,rows);
		}
	});
}

    
function createPageTool(nowPage,total,rows){
	var pageCount = Math.ceil(total/rows);
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

