//搜素
var myBasePath ="";
function myTopSreach(){
	
	myBasePath = $('#basePath').val();
	var proNamess = '';
	proNamess = $('#proNamess').val();
	if(proNamess==''||proNamess=='请输入需要搜索的内容'){
//		alert('请输入需要搜索的内容！');
		 parent.layer.confirm('请输入需要搜索的内容！', {icon: 3, title:'提示'}, function(index){
			 parent.layer.close(index);
		});
		$('#proNamess').focus();
		return;
	}else{
		window.open(myBasePath+"portal/company.jsp?entName="+encodeURI(encodeURI(proNamess)),"","");//企业产品溯源
	}
}

function syBottomSreach(){
	var dimenno = $('#bt_num').val();
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
		$('#bt_num').focus();
	}
}