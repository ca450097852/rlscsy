$(function() {	
	
	$('#add').form("reset");

	var tt = Math.floor(Math.random()*20)+1;

	
	$("#parentId").combotree({
		url:'ent_getEntTree.action?tt='+tt,
		method:'get',
		required:true,
		onLoadSuccess:function(node, data){
		if(data){
			if(data[0].checked){
				$("#parentId").combotree("setValue",data[0].id);
			}
		}
		f_timeout(data);			
		}
	});		
	
    $('#entType_id').combobox({    
        url:'enttype_getEntTypeToSelect.action?tt='+tt,    
        valueField:'id',    
        textField:'text',
        required:true,
        editable:false
    });
    
	$("#areaId").combotree({
		url:'ent_getEntAreaTree.action?tt='+tt,
		method:'get',
		required:true,
		onLoadSuccess:function(node, data){			
			f_timeout(data);			
		}
	});
	 parent.$.messager.progress('close');
});


function submitForm(){
	if($('#add_form').form('validate')==false){
		$.messager.alert('提示','请检查必填项是否已填写!','question');
		return;
	}   
	
	$('#add_form').form('submit', {
		url : 'ent_addEntAndUser.action',
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
	},
	success : function(result) {
		$.messager.show( {
			title : '提示',
			timeout:10000,
			msg : result
		});
		$('#add_form').form('reset');
	}
	});
}
    
function closeWin(){
	var tab = parent.$('#tabs').tabs('getSelected');
	var index = parent.$('#tabs').tabs('getTabIndex',tab);  	   
	parent.$('#tabs').tabs('close',index);
}
