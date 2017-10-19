$(function() { 	 	
	//获取数据
	updateSysconfig();
		
});
			
	/**
	 * 修改
	 * @return
	 */
	function updateSysconfig(){		
	
		$.ajax( {
			url : 'sysconfig_findSysconfig.action',
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result!=null){
					var sysconfig = result ;
					$("input[name='sysconfig.configId']").val(sysconfig.configId);
					
					$("input[name='sysconfig.needareqaudit'][value='"+sysconfig.needareqaudit+"']").attr("checked",'checked');
					$("input[name='sysconfig.needcityaudit'][value='"+sysconfig.needcityaudit+"']").attr("checked",'checked');
					$("input[name='sysconfig.showpingjia'][value='"+sysconfig.showpingjia+"']").attr("checked",'checked');
					$("input[name='sysconfig.showjianguan'][value='"+sysconfig.showjianguan+"']").attr("checked",'checked');
					$("input[name='sysconfig.showjijian'][value='"+sysconfig.showjijian+"']").attr("checked",'checked');
					
				}	
			}
		});
			
		
	}
	
	//提交
    function submitForm(){
    	if($('#sysconfigForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#sysconfigForm').form('submit', {
			url : 'sysconfig_updateSysconfig.action',
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {    	
				$.messager.show({ title : '提示', msg : result });   	
				parent.$.messager.progress('close');	
			}       		
		});     		        		       	 
    }
	

		     
		