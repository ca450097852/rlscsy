var infoUrl = "portal_Info_findNewsList.action";
var types = 1;//默认类型为“农产品安全事件公布”
var pageSize = 5;	//设置每页显示条数;
var basePath = "";
$(function() {
	
	var info ={};
	info["info.rsts"]=1;
	info["info.typeName"]="应急管理";	
	
	$.ajax( {
		url : infoUrl,
		async : false,
		data : info,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			$("#news_list").html('');
			var total = result.total;
			total = parseInt(total);
			
			
			var htm1 = "";
			var title = "";
			var time = "";
			var infoId = "";
			var content = "";
			for ( var i = 0; i < result.rows.length; i++) {
				
					title = result.rows[i].title;
					content = result.rows[i].content;
					
					if(title.length>51){title=title.substring(0,50)+"...";}
					time = result.rows[i].crttime;
					if(time.length>11){time=time.substring(0, 10);}
					infoId = result.rows[i].infoId;
					if(i>3){
						htm1="<div style='display:none' id='ul"+i+"'><li><a target=\"_blank\" href=\"newsdetail.jsp?typecode=news&infoId="+infoId+"\">"+title+"</a><span>    ("+time+")</span></li></div>";
					}else{
						htm1="<div id='ul"+i+"'><li><a target=\"_blank\" href=\"newsdetail.jsp?typecode=news&infoId="+infoId+"\">"+title+"</a><span>    ("+time+")</span></li></div>";
					}
				
					$("#infotitle").append(htm1);
					
			}
			
			
		}
	});
	basePath = $('#basePath').val();
	getList(1,pageSize,types,"");
});



function getList(nowPage,pageSize,types,searchTitle){
	
	loading();//加载gif
	var url = infoUrl+"?page="+nowPage+"&rows="+pageSize;
	
	var info ={};
	info["info.rsts"]=1;
	info["info.typeName"]="应急管理";	
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
			$("#news_list").html('');
			var total = result.total;
			total = parseInt(total);
			
			var htm = "";
			var htm1 = "";
			var title = "";
			var time = "";
			var infoId = "";
			var content = "";
			for ( var i = 0; i < result.rows.length; i++) {
				
					title = result.rows[i].title;
					content = result.rows[i].content;
					content=delHtmlTag(content);
					content=content.substring(0, 150)+"...";
					if(title.length>51){title=title.substring(0,50)+"...";}
					time = result.rows[i].crttime;
					if(time.length>11){time=time.substring(0, 10);}
					infoId = result.rows[i].infoId;
					var titleImg = result.rows[i].titleImg;
					if(titleImg == '' || titleImg==null){
						titleImg = 'images/news-img.png';
					}else{
						titleImg = '/nytsyFiles/titleImg/'+titleImg;
					}
					
					htm='<li><div class="news-img"><img src="images/banner01.png" /></div><div class="news-txt"><a href="newsdetail.jsp?typecode=news&infoId='+infoId+'">'+title+'</a><p>'+content+'</p><i class="date">'+time+'</i></div></li>';
					
					
					$("#news_list").append(htm);
					$('#tmp_div').html(content);
					$('#p_'+i).text($('#tmp_div').text());
					
					
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
				$("#news_list").html(htm);
			}
			createPageTool(nowPage,total,pageSize);
		}
	});
}
function createPageTool(nowPage,total,pageSize){
	var pageCount = Math.ceil(total/pageSize);
	var perpage = (nowPage-1)>0?(nowPage-1):1;
	var nextpage = (nowPage+1)<=pageCount?(nowPage+1):pageCount;
	
	/*var tools1 = "第"+nowPage+"页/共"+pageCount+"页&nbsp;&nbsp;";
	var tools2 = "<a href=\"javascript:void(0)\" onclick=\"goPage("+1+");\">首页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage("+perpage+");\">上一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage("+nextpage+");\">下一页</a>&nbsp;&nbsp;" +
			"<a href=\"javascript:void(0)\" onclick=\"goPage("+pageCount+");\">尾页</a>&nbsp;&nbsp;";
	var tools3 = "跳转至:<select name=\"\" onchange=\"goPage(this.value)\">";

	for(var i=1;i<=pageCount;i++){
			tools3 = tools3 +"<option  value=\""+i+"\" selected=\"selected\">"+i+"</option>";
		}else{
			tools3 = tools3 +"<option  value=\""+i+"\">"+i+"</option>";
		}
	}
	tools3 =tools3 +"</select>";*/
	var tools="<div class='page'><a href='#' onclick='goPage("+perpage+");'> &lt; </a>";
	if(pageCount>10){
		
	
	var index=10;
	var int =1
	if(nowPage>5){
	index=index+(nowPage-5);
	int=int+nowPage-5;
	}
	if(index==pageCount){
		$("#tt").val(int);
	}else if(index>pageCount){
		int=$("#tt").val();
	}
		for (int; int <=index; int++) {
			if(int>pageCount){
				break;
			}
			tools+="<a href='#' onclick='goPage("+int+")' onmouseover='yj("+int+","+nowPage+");' onmouseout='yc("+int+","+nowPage+")'; id='box"+int+"'>"+int+"</a>";
		}
	}
	else{
		for (var int=1; int <=pageCount; int++) {
			tools+="<a href='#' onclick='goPage("+int+")' onmouseover='yj("+int+","+nowPage+");' onmouseout='yc("+int+","+nowPage+")'; id='box"+int+"'>"+int+"</a>";
		
		}
		
	}
	tools+="<a href='#' onclick='goPage("+nextpage+");'> &gt; </a></div>";
	$("#pageTools").html(tools);
	
	var box = document.getElementById("box"+nowPage);
	 box.style.backgroundColor="#20B2AA";
	 box.style.color="#FFF";
}


//鼠标移进
function yj(int,nowPage){
	if(int==nowPage){
		
	}else{
	 var box = document.getElementById("box"+int);
	
	 box.style.backgroundColor="#20B2AA";
	 box.style.color="#FFF";
	}
}
//鼠标移出
function yc(int,nowPage){
	if(int==nowPage){
	}else{
	 var box = document.getElementById('box'+int);
	 box.style.backgroundColor="#FFF";
	 box.style.color="#20B2AA";
}
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

function delHtmlTag(str){
	return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
	}