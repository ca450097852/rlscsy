var infoUrl = "portal_Info_findNewsList.action";
var types = 1;//默认类型为“农产品安全事件公布”
var pageSize = 10;	//设置每页显示条数;
var basePath = "";
$(function() {
	basePath = $('#basePath').val();
	getList(1,pageSize,types,"");
});

function getList(nowPage,pageSize,types,searchTitle){
	
	loading();//加载gif
	var url = infoUrl+"?page="+nowPage+"&rows="+pageSize;
	
	var info ={};
	info["info.rsts"]=1;
	if(types==1){
		info["info.typeName"]="农产品安全事件公布";
	}else if(types==2){
		info["info.typeName"]="违规企业公布";
	}else{
		info["info.typeName"]="农产品追回公布";
	}
//	info["info.typeName"]="新闻发布";//新闻发布//农产品安全监管
	if(searchTitle!="")
	{
		info["info.title"]=searchTitle;
	}
	
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
			var time = "";
			var infoId = "";
			for ( var i = 0; i < result.rows.length; i++) {
					title = result.rows[i].title;
					if(title.length>51){title=title.substring(0,50)+"...";}
					time = result.rows[i].crttime;
					if(time.length>11){time=time.substring(0, 10);}
					infoId = result.rows[i].infoId;
					htm=htm+"<li><span>"+time+"</span><a target=\"_blank\" href=\"news_detail.jsp?typecode=news&infoId="+infoId+"\">"+title+"</a></li>";
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#news_list").html(htm);
			createPageTool(nowPage,total,pageSize);
		}
	});
}
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



//翻页
function goPage(tem){
	var pageNum = $('#pageNum').val();//页面保存的当前页码
	pageNum = parseInt(pageNum);
	tem = parseInt(tem);
	if(tem!=pageNum)
	{
		getList(tem,pageSize,types,"");
		
	}
}

//切换三个分类
function changeInfoType(css1){
	$("a").removeClass();
	
	$("#newsType"+css1).addClass("curre");
	
	types = css1;
	
	getList(1,pageSize,types,"");
}


//加载前显示gif
function loading(){
	var loading = "<div  align=\"center\"><img src=\"../static/image/comm/waitanim.gif\"/></div>";
	$("#news_list").html(loading);
	}

/*
function checkSearch() {
	var searchTitle = $('#searchTitle');
	if (searchTitle.val() == "") {
		alert('请输入标题关键字！');
		searchTitle.focus();
		return false;
	} else {
		getList(1,pageSize,types,searchTitle.val());
		return true;
	}
}
*/