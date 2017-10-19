
$(function() {
	
	loading();//加载gif
	
	var cid = $('#cid').val();
	var infoUrl = "complaintWeixin_findList.action";
	var complaint ={};
	complaint["complaint.cid"]=cid;
	
	$.ajax( {
		url : infoUrl,
		async : false,
		data : complaint,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);	
			if(total>0){
				var comp = result.rows[0];
				var htm =   '<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC";>'+
		                '<tr bgcolor="#fff"><td class="td_title">举报主题：</td><td>'+comp.title+'</td></tr>'+
		                '<tr bgcolor="#fff"><td class="td_title">所属区域：</td><td>'+comp.entName+'</td></tr>'+
		                '<tr bgcolor="#fff"><td class="td_title">举报内容：</td><td>'+comp.content+'</td></tr>'+
		                '<tr bgcolor="#fff"><td class="td_title">处理结果：</td><td>'+comp.finalResult+'</td></tr>'+
		                '</table>';
	    			$("#complaint_result").html(htm);
			}
		}
	});

});




//加载前显示gif
function loading(){
	var loading = "<div  align=\"center\"><img src=\"../static/image/comm/waitanim.gif\"/></div>";
	$("#complaint_result").html(loading);
}
