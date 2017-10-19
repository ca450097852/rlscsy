var infopageSize = 6;	//设置每页显示条数;
var infoUrl = "portal_Info_findNewsList.action";
var InfoPath = "";
$(function() {
	InfoPath = $('#InfoPath').val();
	getInfoList(1,infopageSize,'tzgg');
	
	getInfoList(1,infopageSize,'zcxc');
});

function getInfoList(nowPage,infopageSize,tcode){

	loading(tcode);//加载gif
	var url = infoUrl+"?page="+nowPage+"&rows="+infopageSize;
	var info ={};
	info["info.rsts"]=1;
	if(tcode=='zcxc'){
		info["info.typeName"]="政策宣传";
		$("#"+tcode+"_title").html("政策宣传");
	}else if(tcode=='tzgg'){
		info["info.typeName"]="通知公告";
		$("#"+tcode+"_title").html("通知公告");
	}
	
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
					if(title.length>18){title=title.substring(0, 17)+"...";}
					time = result.rows[i].crttime;
					if(time.length>11){time=time.substring(0, 10);}
					infoId = result.rows[i].infoId;
					htm=htm+"<li><a href=\""+InfoPath+"portalweb/news_detail.jsp?typecode="+tcode+"&infoId="+infoId+"\">"+title+"</a></li>";
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#"+tcode).html(htm);
		}
	});
}


//加载前显示gif
function loading(tcodes){
	var loading = "<div  align=\"center\"><img src=\"../static/image/comm/waitanim.gif\"/></div>";
	$("#"+tcodes).html(loading);
}