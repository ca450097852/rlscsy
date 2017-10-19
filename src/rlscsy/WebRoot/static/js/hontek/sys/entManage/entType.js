var dataGrid;
		$(function() {
		     $('#add').window('close');
			 
			 dataGrid = $('#list_enttype').datagrid({  
			    url:'enttype_findEntTypeList.action',
				toolbar:"#tb",    //在添加 增添、删除、修改操作的按钮要用到这个  
				title: '机构类别管理',
				iconCls: 'icon-enterprise',
				loadMsg:'数据加载中...',
				pageSize : 15,//默认选择的分页是每页10行数据  
			    pageList : [ 10, 15,20,25 ],//可以选择的分页集合
			    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
			    striped : true,//设置为true将交替显示行背景。
				fit:true,
				fitColumns: true,
				//singleSelect:true,
				pagination:true,
				rownumbers:true,
				remoteSort : false,
				//collapsible : true,//显示可折叠按钮  
				//sortName : 'typeId',//当数据表格初始化时以哪一列来排序  
			    sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。  
				frozenColumns : [ [ {  
			           field : 'ck',  
			           checkbox : true  
			    } ] ],
				columns:[[
					//{field:'typeId',title:'类型编号',width:100,align:'center'},
					{field:'typeName',title:'类型名称',width:100,align:'center'},
					{field:'remarks',title:'备注',width:100,align:'center'},
					
				]],
				onLoadSuccess:function(data){
				 	f_timeout(data);
		 		},
		 		onClickRow: function(rowIndex, rowData){
			 		$(this).datagrid('unselectAll');
			 		$(this).datagrid('selectRow',rowIndex);
			 	}
			});	
			 parent.$.messager.progress('close');
			 
			//验证机构类型是否已经存在
			$("#typeName").blur(function(){
				 var value = $("#typeName").val();
				 var oldValue = $("#oldTypeName").val();
				 if(value){
					 if(oldValue==''||value!=oldValue){
						 //使用Ajax同步请求
						 var flag = $.ajax({
							  url: "enttype_checkEntTypeIsExist.action",
							  data:"typeId="+value,
							  async: false,
							  type: "POST"
						 }).responseText;
						 if(flag=='true'){
							 $.messager.show({ title : '提示', msg : '机构类型【'+value+'】已存在!,不能重复添加' });
							 $("#typeName").val('');
							 return;	
						 }	 
					 }
				 }			 
			});
			 
		});
		
		// 显示
		function append(){
			//$('#add').window('open');
			 // 情况表单数据
			 $('#add_form').form('clear');
			 $("#add").dialog("open").dialog('setTitle', '添加机构类型');
			 $('#oprerate').attr("value","1");  
		}
		
		// 修改
		function update(){
			var rows = $('#list_enttype').datagrid("getSelections");
			var leng = rows.length;
			if(leng==0){
			    $.messager.alert('提示','请选择你需要修改的记录!','info');
			}else{
			    if(leng>1){
			        $.messager.alert('提示','只能修改一条记录!','info');
			        return false;
			    }else{
			         $('#oprerate').attr("value","2"); 
			         var entType = rows[0];
			         $("input[name='entType.typeId']").val(entType.typeId);
			         $("input[name='entType.typeName']").val(entType.typeName);
					 $("#oldTypeName").val(entType.typeName);

					 $("textarea[name='entType.remarks']").val(entType.remarks);
					 
					 $('#add_form').form('validate');
					 $('#add').window('open').dialog('setTitle', '修改机构类型');
					 
			    }
			}
		}
		
		// 隐藏
		function clearForm(){
           $('#add').window('close');
        }		
		
		// 删除
		function removeit(){
			var rows = $("#list_enttype").datagrid("getSelections");
			 //判断是否选择行
			if (!rows || rows.length == 0) {
				$.messager.alert('提示', '请选择要删除的记录!', 'info');
				return false;
			}else{
			    var temp;   //循环给提交删除参数赋值
				$.each(rows, function (i, n) {
					if (i == 0) {
						temp =  n.typeId;
					} else {
						temp += "," + n.typeId;
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
				         	  url:'enttype_deleteEntType.action',
				         	  data:'List_entTypeId='+temp,
				         	  type:'post',
				         	  dataType:'text',
				         	  success : function(result) {
				         		   parent.$.messager.progress('close');
								   $.messager.show({ title : '提示', msg : result});
								   dataGrid.datagrid('reload');
							  }    
				         });
				    }
				});    
			}
		}
		
		// 搜索
		function find(){
		   var typeName = $('#name').val();
		   var remarks = $('#remark').val();		       
		   var entType = {};
		   entType["entType.typeName"] = typeName;
		   entType["entType.remarks"] = remarks;
		   dataGrid.datagrid('load',entType); // 点击搜索
		   
		}
		
		// 提交表格 - 添加组织机构类型
        function submitForm(){
           // oprerate=1 --> 添加 ，  oprerate=2 -- > 修改 
        	if($('#add_form').form('validate')==false){
        		$.messager.show({ title : '提示', msg : '录入验证未通过!'});
        		return;	
        	}
           var op = $('#oprerate').attr("value"); 
           if(op==1){
                parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
			    });
	            $('#add_form').form('submit', {
					url : 'enttype_addEntType.action',
					onSubmit : function(result) { 
					    return $(this).form('validate');//对数据进行格式化
					},
					success : function(result) {
						parent.$.messager.progress('close');
						$.messager.show({ title : '提示', msg : result });
						dataGrid.datagrid('reload');
					}
				});
           }else{
	            parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
		        $('#add_form').form('submit', {
					url : 'enttype_updateEntType.action',
					onSubmit : function(result) { 
						 return $(this).form('validate');//对数据进行格式化
					},
					success : function(result) {
						 parent.$.messager.progress('close');
						 $.messager.show({ title : '提示', msg : result });
						 dataGrid.datagrid('reload');
					}
				});
           }
           $('#add').window('close');
        }