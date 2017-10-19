var baseUrl = "portal_Info_findNewsList.action";
$(function() {
	var infoId = $('#infoId').val();
	if(infoId!=""){
		getList(baseUrl,1,1,infoId);
	}
	
});
function getList(url,nowPage,pageSize,infoId){

	var info ={};
	if(infoId!=""){
		info["info.infoId"]=infoId;
		}
	
	var typeName ="";
	
	$.ajax( {
		url : url,
		async : false,
		data : info,
		type : 'post',
		dataType : 'json',
		success : function(result) {
	    	var info = result.rows[0];
	    	var title = info.title;
	    	typeName = info.typeName;
			var content = info.content;
	    	var crttime = info.crttime;
	    	var nickName = info.nickName;
			$("#info_title").html(title);
			$("#tit").html("发布时间："+crttime+"");
			$("#info_content").html(content);
			$("#weizhi").html(typeName+"");
	 
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
