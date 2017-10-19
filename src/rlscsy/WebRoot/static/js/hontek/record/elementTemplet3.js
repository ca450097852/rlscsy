$(function() {
	
	//获取类型数据
	getTypeData("putong_element",1,"putong");//普通类型
	getTypeData("sanping_element",2,"sanping");//三品一标
	getTypeData("zhongzhi_element",3,"zhongzhi");//种植类
	getTypeData("xumu_element",4,"xumu");//畜牧类
	
});
//获取类型数据
function getTypeData(tdId,type,name){
	if(tdId==''||type==''){return;}
	$.ajax({
		url:'elementTemplet_findElementTempletTree.action?archivesType='+type+'&tt='+Math.floor(Math.random()*20)+1,
		async : false,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var html = "";
//			html = "<input type=\"checkbox\" onclick=\"selectAll("+type+",this);\"> 全选&nbsp;&nbsp;";
			var vo;
			if(result){
				for(var i=0;i<result.length;i++){
					vo = result[i];
					if(vo){
						var checked = vo.checked;
						if(checked){
							html += "<input type=\"checkbox\" checked=\"checked\" name=\""+name+"\" value=\""+vo.id+"\"> "+vo.text+"&nbsp;&nbsp;&nbsp;&nbsp;";
						}else{
							html += "<input type=\"checkbox\" name=\""+name+"\" value=\""+vo.id+"\"> "+vo.text+"&nbsp;&nbsp;&nbsp;&nbsp;";
						}
					}
				}
			}
			$("#"+tdId).html(html);
		}
	});
}


function selectAll(type,obj){
	var flag = $(obj).attr("checked");
	if(type==1&&flag=='checked'){
		$("input[name='putong']").attr("checked",true);
	}else if(type==1&&flag!='checked'){
		$("input[name='putong']").attr("checked",false);
	}
	
	if(type==2&&flag=='checked'){
		$("input[name='sanping']").attr("checked",true);
	}else if(type==2&&flag!='checked'){
		$("input[name='sanping']").attr("checked",false);
	}
	
	if(type==3&&flag=='checked'){
		$("input[name='zhongzhi']").attr("checked",true);
	}else if(type==3&&flag!='checked'){
		$("input[name='zhongzhi']").attr("checked",false);
	}
	
	if(type==4&&flag=='checked'){
		$("input[name='xumu']").attr("checked",true);
	}else if(type==4&&flag!='checked'){
		$("input[name='xumu']").attr("checked",false);
	}
}

function submitForm(){

	var putong_value =[];
	$('input[name="putong"]:checked').each(function(){
	putong_value.push($(this).val());
	});
//	alert(putong_value.length==0 ?'你还没有选择任何内容！':putong_value); 
	
	var sanping_value =[];
	$('input[name="sanping"]:checked').each(function(){
	sanping_value.push($(this).val());
	});
//	alert(sanping_value.length==0 ?'你还没有选择任何内容！':sanping_value); 
	
	var zhongzhi_value =[];
	$('input[name="zhongzhi"]:checked').each(function(){
	zhongzhi_value.push($(this).val());
	});
//	alert(zhongzhi_value.length==0 ?'你还没有选择任何内容！':zhongzhi_value);
	
	var xumu_value =[];
	$('input[name="xumu"]:checked').each(function(){
	xumu_value.push($(this).val());
	});
//	alert(xumu_value.length==0 ?'你还没有选择任何内容！':xumu_value);
	
	$.post('elementTemplet_addAll.action',
			'putong='+putong_value+'&sanping='+sanping_value+'&zhongzhi='+zhongzhi_value+'&xumu='+xumu_value,
			function(result) {
				$.messager.show({ title : '提示', msg : result });
				//获取类型数据
				getTypeData("putong_element",1,"putong");//普通类型
				getTypeData("sanping_element",2,"sanping");//三品一标
				getTypeData("zhongzhi_element",3,"zhongzhi");//种植类
				getTypeData("xumu_element",4,"xumu");//畜牧类
			},
			"TEXT");

}

