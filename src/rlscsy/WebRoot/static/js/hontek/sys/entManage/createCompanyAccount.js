$(function() {	
	
	$('#add').form("reset");
	
	$("#areaId").combotree({
		url:'ent_getEntAreaTree.action?tt='+Math.floor(Math.random()*20)+1,
		method:'get',
		required:true,
		onLoadSuccess:function(node, data){
			if(data){
				if(data[0].checked){
					$("#areaId").combotree("setValue",data[0].id);
				}
			}
			f_timeout(data);			
		}
	});
	
	$("#parentId").combotree({
		url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
		method:'get',
		required:true,
		onLoadSuccess:function(node, data){
		if(data){
			if(data[0].checked){
				$("#parentId").combotree("setValue",data[0].id);
			}
		}
		f_timeout(data);			
		},
		onSelect: function(node){
			var entId = node.id;  // 在用户点击的时候提示			
			$.ajax({
				  url: "ent_getEntLevel.action?entId="+entId,
				  cache: false,
				  success: function(level){
					if(level==2){
						$("#provinceRsts").val("0");
					}else if(level==3){
						$("#cityRsts").val("0");
					}else if(level==4){
						$("#areaRsts").val("0");
					}else if(level==5){
						$("#townRsts").val("0");
					}
				  }
			});	
			//指定审核机构
			$.ajax({
				  url: "ent_getAuditEntTree.action?entId="+entId,
				  cache: false,
				  success: function(auditEntHTML){
					$("#auditEnt").html(auditEntHTML);
				  }
			});			
		}

	});		
	
    $('#entType_id').combobox({    
        url:'enttype_getEntTypeToSelect.action?tt='+Math.floor(Math.random()*20)+1,    
        valueField:'id',    
        textField:'text',
        required:true,
        editable:false
    });
    

});

/**
 * 选择控制
 * @param size
 * @param i
 * @return
 */
function checkFlag(size,i){
	//alert(size+"------"+i);
	var after = i+1;
	if(after<size){
		var afterCheck =$("#ck"+after).is(":checked");
		if(afterCheck==true){
			$("#ck"+i).attr("checked","checked");
		}		
	}
	
	if(after<=size){
		var thisCheck =$("#ck"+i).is(":checked");
		if(thisCheck==true){
			for(var s =i;s>0;s--){
				$("#ck"+s).attr("checked","checked");
			}
		}
	}
}


function submitForm(){
	if($('#add_form').form('validate')==false){
		$.messager.alert('提示','请检查必填项是否已填写!','question');
		return;
	}   
	
	$('#add_form').form('submit', {
		url : 'ent_addCompanyAccount.action',
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
	},
	success : function(result) {
		$.messager.show( {
			title : '提示',
			timeout:30000,
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
