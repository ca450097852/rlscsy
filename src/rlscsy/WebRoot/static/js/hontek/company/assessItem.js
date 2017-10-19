$(function() {
		$('#purchasedtlWindow').window('close');
		$('#addAssessItem').window('close');  // close a window
		$('#addPurchasedtl').window('close');
		
			
		 //表格数据
		 $('#assessItemdatagrid').datagrid({  
		      title : '考核项目管理',  
		      iconCls : 'icon-ok',  
		      pageSize : 15,//默认选择的分页是每页10行数据  
		      pageList : [ 10, 15,20,30,50 ],//可以选择的分页集合  
		      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		      striped : true,//设置为true将交替显示行背景。  
		      //collapsible : true,//显示可折叠按钮  
		      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
		      url:'assessItem_findAssessItemList.action',//url调用Action方法  
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
						{field:'itemName',title:'考核指标',width:100,align:'left'},
						{field:'itemDesc',title:'指标描述',width:100,align:'center'},
						{field:'nodeType',title:'节点类型',width:100,align:'center',formatter: function(value,row,index){ 
							if(value==1){return "屠宰企业";}
							if(value==2){return "批发企业";}
							if(value==3){return "菜市场";}
							if(value==4){return "超市";}
							if(value==5){return "团体消费单位";}
							else{return "其他";}}},
						{field:'crrtime',title:'创建时间',width:100,align:'center'}
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
		function appendAssessItem(){
			$('#addAssessItem').window('open').dialog('setTitle','添加考核项目信息');			
			$('#assessItem_form').form("clear");
			$('#method').val("add");
			$('#seq').numberbox("setValue","5");
		}
		
		// 修改
		function updateAssessItem(){	
			$('#assessItem_form').form("clear");
			$('#method').val("update");
			var arr = $('#assessItemdatagrid').datagrid("getSelections");			
			var lg = arr.length;
			if(lg==0){
				$.messager.alert('提示','请选择需要修改的信息!','question');
			}else if(lg!=1){
				$.messager.alert('提示','对不起，一次只能修改一个考核项目信息，请重新选择!','question');
			}else{		    
				var assessItem = arr[0];
				$("input[name='assessItem.itemName']").val(assessItem.itemName);
				$("input[name='assessItem.seq']").val(assessItem.seq);
				$('#item_type').combobox('setValue',assessItem.nodeType);
				$('#item_desc').val(assessItem.itemDesc);
				$('#itemId').val(assessItem.itemId);
				$('#addAssessItem').window('open').dialog('setTitle','修改考核项目信息');			
				$("#in_value").html($("#hiden_value").html());
				$('#assessItem_form').form('validate');
				}
		}
		
		// 删除
		function removeit(){
			var rows = $("#assessItemdatagrid").datagrid("getSelections");
			 //判断是否选择行
			if (!rows || rows.length == 0) {
				$.messager.alert('提示', '请选择要删除的记录!', 'info');
				return false;
			}else{
			    var temp;   //循环给提交删除参数赋值
				$.each(rows, function (i, n) {
					if (i == 0) {
			        	temp = n.itemId;
				   } else {
						temp += "," + n.itemId;
			       }
				}); 
				$.messager.confirm('提示', '是否删除选中数据?', function (r){
					if (!r) {
						return;
				    }else{
				         parent.$.messager.progress({
							title : '提示',
							text : '数据处理中，请稍后....'
				         });
				         $.ajax({
				         	  url:'assessItem_deleteAssessItem.action',
				         	  data:'ids='+temp,
				         	  type:'post',
				         	  dataType:'text',
				         	  success : function(result) {
				         		   parent.$.messager.progress('close');
								   $.messager.show({ title : '提示', msg : result});
								   $('#assessItemdatagrid').datagrid("reload");
							  }    
				         });
				    }
				});    
			}
		}
		
		
		
        
		//提交form
        function submitAssessItemForm(){
        	if($('#assessItem_form').form('validate')==false){
        		$.messager.show({ title : '提示', msg : '验证没有通过!' });  
        		return;
        	}
        	parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
        	var method = $('#method').val();
        	if(method=='add'){//执行添加       		
        		$('#assessItem_form').form('submit', {     
    				url : 'assessItem_addAssessItem.action',
    				onSubmit : function(result) { 
    				    return $(this).form('validate');//对数据进行格式化
    				},
    				success : function(result) {    	
    					parent.$.messager.progress('close');
						$.messager.show({ title : '提示', msg : result });   					
						$('#addAssessItem').window('close');
    					$('#assessItemdatagrid').datagrid("reload");
    				}       		
        		});
        	}else if(method=='update'){//执行修改
        			$('#assessItem_form').form('submit', {
    				url : 'assessItem_updateAssessItem.action',
    				onSubmit : function(result) { 
    				    return $(this).form('validate');//对数据进行格式化
    				},
    				success : function(result) {    					
    					parent.$.messager.progress('close');
						$.messager.show({ title : '提示', msg : result });   					
						$('#addAssessItem').window('close');
    					$('#assessItemdatagrid').datagrid("reload");
    					
    					$("#in_value").html("");
    				}       		
        		});
        	}  
        	//隐藏添加窗口
        	$('#addPurchase').window('close');  // close a window  
        }
