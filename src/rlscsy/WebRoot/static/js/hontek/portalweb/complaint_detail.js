var baseUrl = "compfor_findComplaintList.action";
$(function() {
	var cid = $('#cid').val();
	if(cid!=""){
		getList(baseUrl,1,1,cid);
	}
	
});
function getList(url,nowPage,pageSize,cid){

	var complaint ={};
	if(cid!=""){
		complaint["complaint.cid"]=cid;
		}
	
	$.ajax( {
		url : url,
		async : false,
		data : complaint,
		type : 'post',
		dataType : 'json',
		success : function(result) {
	    	var complaint = result.rows[0];
	    	var title = complaint.title;        //举报标题
	    	var crttime = complaint.crttime;	//举报时间
	    	var userName = complaint.userName;  //举报人
	    	var entName = complaint.entName;     //举报区域
	    	var content = complaint.content;		//举报内容
	    	var stsStr = "" 		            //处理状态
	    	if(complaint.sts == 0){
	    		stsStr = "未处理"
	    	}else if(complaint.sts == 1){
	    		stsStr = "处理中"
	    	}else{
	    		stsStr = "已处理"
	    	}
	    	var finalResult = complaint.finalResult;  //工单最终处理结果
	    	var remark = "";
	    	remark = complaint.remark;			  //举报最终处理结果
			$("#complaint_title").html(title);
			$("#complaint_crttime").html(crttime);
			$("#complaint_entName").html(entName);
			$("#complaint_content").html(content);
			$("#complaint_sts").html(stsStr);
			$("#complaint_finalResult").html(finalResult);
			$("#complaint_remark").html(remark);
			if(finalResult!=''){
				$("#show_finalResult").show();
			}else{
				$("#show_remark").show();
			}
		}
	});
	
}

