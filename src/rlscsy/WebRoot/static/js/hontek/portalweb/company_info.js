/*var compUrl = "portal_Company_findList.action";
var rows = 3;	//设置每页显示条数;
var BPath = "";//网站路径
var infofilePath = "";//文件路径
$(function() {
	BPath = $('#BPath').val();
	infofilePath = $('#infofilePath').val();
	getCompanyList(1,rows);
});

function getCompanyList(nowPage,rows){
	
	var url = compUrl+"?page="+nowPage+"&rows="+rows;
	
	var enterprise ={};
	
	$.ajax( {
		url : url,
		async : false,
		data : enterprise,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			var htm = "";
			var name = "";
			var intro = "";
			var logoUrl = "";
			var entId = "";
			for ( var i = 0; i < result.rows.length; i++) {
					name = result.rows[i].name;
					if(name.length>18){name=name.substring(0, 17)+"...";}
					intro = result.rows[i].intro;
					if(intro.length>31){intro=intro.substring(0, 30)+"...";}
					logoUrl = result.rows[i].logoUrl;
					entId = result.rows[i].entId;
					if(logoUrl==''){
						logoUrl = BPath + "static/image/portalweb/20130227150958855.jpg";
					}else{
						logoUrl = infofilePath+logoUrl;
					}
					
					htm=htm+"<div class=\"firm\">"+
							"<h4><a target=\"_blank\" href=\""+BPath+"portalweb/company_detail.jsp?entId="+entId+"\">"+name+"</a></h4>"+
							   "<div class=\"firm_content\">"+
							   	"<div class=\"firm_logo\"><a target=\"_blank\" href=\""+BPath+"portalweb/company_detail.jsp?entId="+entId+"\"><img src=\""+logoUrl+"\" width='76' height='76'/></a></div>"+
							       "<div class=\"firm_text\"><a target=\"_blank\" href=\""+BPath+"portalweb/company_detail.jsp?entId="+entId+"\">"+intro+"</a></div>"+
							       "<div class=\"clear\"></div>"+
							   "</div></div>";
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#info_title").html(htm);
		}
	});
}

*/

var compUrl = "portal_Company_findList.action";
var rows = 11;	//设置每页显示条数;
var BPath = "";//网站路径
var infofilePath = "";//文件路径
$(function() {
	BPath = $('#BPath').val();
	infofilePath = $('#infofilePath').val();
	getCompanyList(1,rows);
});

function getCompanyList(nowPage,rows){
	
	var url = compUrl+"?page="+nowPage+"&rows="+rows;
	
	var enterprise ={};
	enterprise["enterprise.sysCode"]="S076901";
	$.ajax( {
		url : url,
		async : false,
		data : enterprise,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			var htm = "";
			var name = "";
			var purl = "";

			for ( var i = 0; i < result.rows.length; i++) {
					name = result.rows[i].name;
					purl = result.rows[i].purl;
					if(name.length>18){name=name.substring(0, 17)+"...";}					
					htm=htm+"<li><a href=\""+purl+"\" target='_blank'>"+name+"</a></li>";										
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#info_title").html(htm);
		}
	});
}

