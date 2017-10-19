var sreachType ='2';//默认1为产品搜索 ----2为企业
var myBasePath ="";
//切换搜索
function changeType(num){
		$('#sycp').removeClass();
		$('#sysj').removeClass();
		if(num==1){
			sreachType ='1';
			$('#sycp').addClass("top_search_check");
		}else{
			sreachType ='2';
			$('#sysj').addClass("top_search_check");
		}
}	

//搜素
function mySreach(){
	myBasePath = $('#myBasePath').val();
	var proNamess = $('#proNamess').val();
	
	if(proNamess==''){
		alert("请输入关键字!");
		$('#proNamess').focus();
	}else{
		if(sreachType=='1'){
			window.location.href=myBasePath+"portalweb/product_list.jsp?proNmaes="+encodeURI(encodeURI(proNamess));
		}
		else if(sreachType=='2'){
			window.location.href=myBasePath+"portalweb/company_list.jsp?entName="+encodeURI(encodeURI(proNamess));
		}
	}
}
