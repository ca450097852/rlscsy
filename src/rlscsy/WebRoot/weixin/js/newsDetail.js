$(function() {
	var infoId = $('#infoId').val();
	var baseUrl = "infoWeixin_findNewsList.action";

	if(infoId!=""){
		var info ={};
		info["info.infoId"]=infoId;
		
		$.ajax( {
			url : baseUrl,
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
				$("#tit").html("发布时间："+crttime+"　作者："+nickName+"");
				$("#info_content").html(content);
			}
		});
	}	
});
