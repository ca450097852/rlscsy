var baseUrl = "portal_Tongbao_findList.action";
$(function() {
	var tid = $('#tid').val();
	if(tid!=""){
		getList(baseUrl,1,1,tid);
	}
	
});
function getList(url,nowPage,pageSize,tid){
	var tongbao ={};
	if(tid!=""){
		tongbao["tongbao.tid"]=tid;
	}
	
//	var typeName ="";
	
	$.ajax( {
		url : url,
		async : false,
		data : tongbao,
		type : 'post',
		dataType : 'json',
		success : function(result) {
	    	var tongbao = result.rows[0];
	    	var title = tongbao.title;          //通报标题
	    	var crttime = tongbao.crttime;	    //通报时间
	    	var userName = tongbao.userName;    //通报人
	    	var entName = tongbao.entName;      //通报区域
	    	var content = tongbao.content;		//通报内容
	    	var remark = tongbao.remark;	    //通报内容
	    	
			$("#tongbao_title").html(title);
			$("#tongbao_crttime").html(crttime);
			$("#tongbao_entName").html(entName);
			$("#tongbao_content").html(content);
			$("#tongbao_remark").html(content);
		}
	});
	
	}

/*
function otherList(typeName,tbcun){
	if(typeName!=""){
		var listUrl = baseUrl+"&page=1&rows=5";
		var info ={};
		info["info.typeName"]=typeName;
		info["info.tbCun"]=tbcun;
		$.ajax( {
			url : listUrl,
			async : false,
			data : info,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				var htm = "<ul>";
				var title="";
		    	var crttime = "";
		    	var infoId = "";
		    	var tbCun = "";
				for ( var i = 0; i < result.rows.length; i++) {
						infoId = result.rows[i].infoId;
						title=result.rows[i].title;
						crttime = result.rows[i].crttime.substring(0, 10);	
						tbCun =result.rows[i].tbCun;
						htm = htm+"<li><a href=\"list_cw_detail.jsp?id="+infoId+"&tbcun="+tbCun+"\">"+title+"</a></li>";
	
				}
				htm=htm+"</ul>";
				$("#other_list").html(htm);
			}
		});
	}
	
}
*/
