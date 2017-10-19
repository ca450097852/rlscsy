$(function() {
	getComplaintList();
	getTongbaoList();
});

function getTongbaoList(){
	loading("tbcf");//加载gif
	var url = "portal_Tongbao_findList.action?page=1&rows=7";
	var tongbao ={};
	
	$.ajax( {
		url : url,
		async : false,
		data : tongbao,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			var htm = "";
			var title = "";
			var time = "";
			var tid = "";
			for ( var i = 0; i < result.rows.length; i++) {
					title = result.rows[i].title;
					if(title.length>18){title=title.substring(0, 17)+"...";}
					time = result.rows[i].crttime;
					if(time.length>11){time=time.substring(0, 10);}
					tid = result.rows[i].tid;
					htm=htm+"<li><a href=\"tongbao_detail.jsp?tid="+tid+"\">"+title+"</a></li>";
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#tbcf").html(htm);
		}
	});
}

function getComplaintList(){
	loading("tsjb");//加载gif
	var url = "portal_Complaint_findList.action?page=1&rows=7";
	var complaint ={};
	complaint["complaint.sts"]=2;
	$.ajax( {
		url : url,
		async : false,
		data : complaint,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			
			var htm = "";
			var title = "";
			var time = "";
			var cid = "";
			for ( var i = 0; i < result.rows.length; i++) {
					title = result.rows[i].title;
					if(title.length>18){title=title.substring(0, 17)+"...";}
					time = result.rows[i].crttime;
					if(time.length>11){time=time.substring(0, 10);}
					cid = result.rows[i].cid;
					htm=htm+"<li><a href=\"complaint_detail.jsp?cid="+cid+"\">"+title+"</a></li>";
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#tsjb").html(htm);
		}
	});
}


//加载前显示gif
function loading(tcodes){
	var loading = "<div  align=\"center\"><img src=\"../static/image/comm/waitanim.gif\"/></div>";
	$("#"+tcodes).html(loading);
}