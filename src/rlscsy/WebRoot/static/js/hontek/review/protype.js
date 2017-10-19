$(function() {
		$('#purchasedtlWindow').window('close');
		$('#addProType').window('close');  // close a window
		$('#addPurchasedtl').window('close');
		
		$('#upcateId').combotree({
					url:'proType_getProTypeTree.action',
					required:true
			});
			
		 //表格数据
		 $('#protypedatagrid').datagrid({  
		      title : '产品分类管理',  
		      iconCls : 'icon-ok',  
		      pageSize : 15,//默认选择的分页是每页10行数据  
		      pageList : [ 10, 15,20,30,50 ],//可以选择的分页集合  
		      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		      striped : true,//设置为true将交替显示行背景。  
		      //collapsible : true,//显示可折叠按钮  
		      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
		      url:'proType_findProTypeList.action',//url调用Action方法  
		      loadMsg : '数据装载中......',  			      
		      fit:true,			      
		      //singleSelect:true,//为true时只能选择单行  
		      fitColumns:true,//允许表格自动缩放，以适应父容器  
		      //sortName : 'purchNo',//当数据表格初始化时以哪一列来排序  
		      //sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。  
		      remoteSort : false, 
		      frozenColumns : [ [ {  
		           field : 'ck',  
		           checkbox : true  
		      } ] ],  
		      columns:[[
					{field:'typeNo',title:'分类编码',width:100,align:'left'},
					{field:'typeName',title:'分类名称',width:100,align:'center'},
					{field:'state',title:'分类状态',width:100,align:'center',formatter: function(value,row,index){ 
						if(value==0){
							return "启用";
						}else{
							return "停用";
						}
					}},
					{field:'remark',title:'备注',width:100,align:'center'}
		      ]],
              pagination : true,//分页  
              rownumbers : true//行数
	           ,
               onLoadSuccess:function(data){
		 		f_timeout(data);
	 		   }
		      });   
		});
	
        
		// 添加
		function appendProType(){
			$('#addProType').window('open').dialog('setTitle','添加分类信息');			
			$('#proType_form').form("clear");
			$('#method').val("add");
			$('#pro_state').combobox('setValue', 0);
			
			$('#upcateId').combotree('reload');
		}
		
		// 删除
		function updateProTypeState(sts){			
			//getSelections
			var arr = $('#protypedatagrid').datagrid("getSelections");
			if(arr.length==0){
				$.messager.alert('提示','请选择要操作的分类信息!','question');
				return;
			}
			var obj = new Array();
			for(i=0;i<arr.length;i++){
				obj.push(arr[i].typeId);
			}
			var ids = obj.join(',');
			$.messager.confirm('提示', '确定要继续操作?', function(r){
				if (r){
					$.ajax({
			         	  url:'proType_updateProTypeState.action',
			         	  data:'ids='+ids+'&state='+sts,
			         	  type:'post',
			         	  dataType:'text',
			         	  success : function(result) {
			         		   parent.$.messager.progress('close');
							   $.messager.show({ title : '提示', msg : result});
							   $('#protypedatagrid').datagrid("reload");
						  }    
			         }); 
				}
			});
		}
		
		// 打开修改窗口
		function updateProType(){	
			$('#proType_form').form("clear");
			$('#method').val("update");
			var arr = $('#protypedatagrid').datagrid("getSelections");			
			var lg = arr.length;
			if(lg==0){
				$.messager.alert('提示','请选择需要修改的信息!','question');
			}else if(lg!=1){
				$.messager.alert('提示','对不起，一次只能修改一个分类信息，请重新选择!','question');
			}else{		    
				
				var proType = arr[0];
				$("input[name='proType.typeNo']").val(proType.typeNo);
				$("input[name='proType.typeName']").val(proType.typeName);
				
				$("input[name='proType.typeId']").val(proType.typeId);
				$("input[name='proType.upcateId']").val(proType.upcateId);
				$("input[name='proType.entId']").val(proType.entId);
				
				$('#upcateId').combotree('reload');
				$('#upcateId').combotree('setValue',proType.upcateId);
				$('#P_remark').val(proType.remark);
				
				$('#_typeClass').combobox('setValue',proType.typeClass);
				
				$('#pro_state').combobox('setValue', proType.state);

				$('#addProType').window('open').dialog('setTitle','修改产品分类信息');			
				
				$("#in_value").html($("#hiden_value").html());
				
				$('#proType_form').form('validate');
			}			
		}
		
        
		//提交form
        function submitProTypeForm(){
        	if($('#proType_form').form('validate')==false){
        		$.messager.show({ title : '提示', msg : '验证没有通过!' });  
        		return;
        	}
        	parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
        	var method = $('#method').val();
        	if(method=='add'){//执行添加       		
        		$('#proType_form').form('submit', {     
    				url : 'proType_addProType.action',
    				onSubmit : function(result) { 
    				    return $(this).form('validate');//对数据进行格式化
    				},
    				success : function(result) {    	
    					parent.$.messager.progress('close');
						$.messager.show({ title : '提示', msg : result });   					
						$('#addProType').window('close');
    					$('#protypedatagrid').datagrid("reload");
    				}       		
        		});
        	}else if(method=='update'){//执行修改
        		$('#proType_form').form('submit', {
    				url : 'proType_updateProType.action',
    				onSubmit : function(result) { 
    				    return $(this).form('validate');//对数据进行格式化
    				},
    				success : function(result) {    					
    					parent.$.messager.progress('close');
						$.messager.show({ title : '提示', msg : result });   					
						$('#addProType').window('close');
    					$('#protypedatagrid').datagrid("reload");
    					
    					$("#in_value").html("");
    				}       		
        		});
        	}  
        	//隐藏添加窗口
        	$('#addPurchase').window('close');  // close a window  
        }
