var baseUrl = "portal_Info_findNewsList.action";
$(function() {

	var info ={};
	info["info.rsts"]=1;
	info["info.typeName"]="应急管理";	
	
	$.ajax( {
		url : "portal_Info_findNewsList.action",
		async : false,
		data : info,
		type : 'post',
		dataType : 'json',
		success : function(result) {
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
	var infoId = $('#infoId').val();
	if(infoId!=""){
		getList(baseUrl,1,2,infoId);
	}
	
});
function getList(url,nowPage,pageSize,infoId){

	var info ={page:1,rows:pageSize,'info.typeName':'应急管理','info.rsts':1};
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
			$("#tit").html(crttime);
			$("#infoContent").html(content);
			
			if(result.rows[1]){
				$('#nextNews').html(result.rows[1].title);
				$('#nextNews').attr('href','newsdetail.jsp?typecode=news&infoId='+result.rows[1].infoId);
			}
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
