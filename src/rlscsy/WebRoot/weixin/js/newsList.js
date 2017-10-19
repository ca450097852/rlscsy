var infoUrl = "infoWeixin_findNewsList.action";
var typecode = "tzgg";//页面传新闻类型
var pageSize = 10;	//设置每页显示条数;
var basePath = "";
$(function() {
	basePath = $('#basePath').val();	
	getList(1,pageSize,typecode,"");
});

function getList(nowPage,pageSize,typecode,searchTitle){
	
	loading();//加载gif
	var url = infoUrl+"?page="+nowPage+"&rows="+pageSize;
	
	var info ={};
	if(searchTitle!=""){
		info["info.title"]=searchTitle;
	}	
	info["info.typeName"]="通知公告";	
	info["info.rsts"]="1";	//审核通过的

	$('#pageNum').val(nowPage);//页面上保存当前的pageNum;
	
	$.ajax( {
		url : url,
		async : false,
		data : info,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);			
			var htm = "";
			var title = "";
			//var time = "";
			var infoId = "";
			for ( var i = 0; i < result.rows.length; i++) {
					title = result.rows[i].title;
					if(title.length>30){title=title.substring(0, 30)+"…";}
					//time = result.rows[i].crttime;
					//if(time.length>11){time=time.substring(0, 10);}<span>"+time+"</span>
					infoId = result.rows[i].infoId;
					htm=htm+"<li><a target=\"_blank\" href=\"newsDetail.jsp?infoId="+infoId+"\">"+title+"</a></li>";
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#news_list").html(htm);
			createPageTool(nowPage,total,pageSize);
		}
	});
}
//生成 分页工具
function createPageTool(nowPage,total,pageSize){
	var pageCount = Math.ceil(total/pageSize);
		
	var perpage = (nowPage-1)>0?(nowPage-1):1;
	var nextpage = (parseInt(nowPage)+1)<=pageCount?(parseInt(nowPage)+1):pageCount;
	
	//var tools1 = "第"+nowPage+"页/共"+pageCount+"页&nbsp;&nbsp;";
	var tools2 = "<a href=\"javascript:void(0)\" onclick=\"goPage("+perpage+");\">上一页</a>&nbsp;&nbsp;" +
				 "<a href=\"javascript:void(0)\" onclick=\"goPage("+nextpage+");\">下一页</a>&nbsp;&nbsp;" ;
	var tools3 = "第&nbsp;<select name=\"\" onchange=\"goPageSelect(this.value)\">";

	for(var i=1;i<=pageCount;i++){
		if(i==nowPage){
			tools3 = tools3 +"<option  value=\""+i+"\" selected=\"selected\">"+i+"</option>";
		}else{
			tools3 = tools3 +"<option  value=\""+i+"\">"+i+"</option>";
		}
	}
	tools3 =tools3 +"</select>&nbsp;页/共"+pageCount+"页&nbsp;&nbsp;"; 
	$("#pageTools").html(tools2+tools3);
}



//翻页方法
function goPage(tem){
	var pageNum = $('#pageNum').val();//获取页面保存的当前页码
	pageNum = parseInt(pageNum);	
	if(tem!=pageNum)
	{
		var searchText = $('#searchText');
		getList(tem,pageSize,searchText.val());		
	}
}

//翻页方法
function goPageSelect(tem){
	var pageNum = $('#pageNum').val();//获取页面保存的当前页码
	pageNum = parseInt(pageNum);	
	if(tem!=pageNum)
	{
		var searchText = $('#searchText');
		getList(tem,pageSize,searchText.val());		
	}
}


//加载前显示gif
function loading(){
	var loading = "<div  align=\"center\"><img src=\"../static/image/comm/waitanim.gif\"/></div>";
	$("#news_list").html(loading);
}
