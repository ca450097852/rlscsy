/**
 * 自动完成
 */
$(function () {			
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
});

//溯源查询数字输入
function subnumber(param){
	var param = param;
	if(param >= 0){
		$('#num').val($('#num').val()+param);
	}else if(param == -2){
		$('#num').val('');
	}else {
		var arr = $('#num').val();
   		var NewNum = arr.substring(0,arr.length-1);
   		$('#num').val(NewNum);
	}
}

function sySreach(){
	
	var dimenno = $('#num').val();
	if(dimenno!=''){
		 if(dimenno.length==12){
			window.open("sydetail.jsp?dimenno="+dimenno,"","");//接入企业溯源new
		}else if(dimenno.length==14){
			window.open("sydetail.jsp?dimenno="+dimenno,"","");//接入企业分类溯源new
		}else if(dimenno.length==16){
			window.open("sydetail.jsp?dimenno="+dimenno,"","");//接入企业分类溯源new批次
		}else {
			layer.msg('请正确输入溯源码！');
		}
	}else{
		layer.msg('请输入溯源码！');
		$('#num').focus();
	}
}

//跳转

function checkForRedirect(dimenno){

	var proBatch = {};
	proBatch["proBatch.dimenno"]=dimenno;
	proBatch["proBatch.batchSts"]='1';
	
	$.ajax({
   	    url:'web_ProBatch!findProBatchList.action',
   	    data:proBatch,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
		alert(result.total);
		if(result.total==0){
			window.open("portalweb/suyuan_prodetail.jsp?dimenno="+dimenno,"","");//产品溯源
		}else{
			window.open("portalweb/suyuan_detail.jsp?dimenno="+dimenno,"","");//接入企业产品溯源
		}
	    }
   }); 
	   
}