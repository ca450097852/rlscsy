var dataGrid;
		$(function() {
		     $('#add').window('close');
			 dataGrid = $('#list_agriSupplier').datagrid({
			    url:'agriSupplier_findAgriSupplierList.action',
				toolbar:"#tb",    //在添加 增添、删除、修改操作的按钮要用到这个  
				title: '投入品供应商管理',
				iconCls: 'icon-ok',
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
				//sortName : 'asId',//当数据表格初始化时以哪一列来排序  
			    sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。  
				frozenColumns : [ [ {  
			           field : 'ck',  
			           checkbox : true  
			    } ] ],
				columns:[[
					{field:'asName',title:'投入品供应商名称',width:200,align:'center'},
					{field:'asAddr',title:'地址',width:100,align:'center'},
					{field:'asPhone',title:'电话',width:100,align:'center'},
					{field:'asGoods',title:'主营产品',width:100,align:'center'},
					{field:'asMan',title:'联系人',width:100,align:'center'}
				]],
				onHeaderContextMenu: function(e, field){
					e.preventDefault();
					if (!cmenu){
						createColumnMenu();
					}
					cmenu.menu('tb', {
						left:e.pageX,
						top:e.pageY
					});
				},
				onLoadSuccess:function(data){
				 	f_timeout(data);
			 		}
			});	
			 
			 parent.$.messager.progress('close');
		});
		
				// 添加 - 信息
		function append() {
			
			 $('#add_form').form('clear');
			 $("#add").dialog("open").dialog('setTitle', '添加投入品供应商');
			 $('#oprerate').attr("value","1");  
		}
		
		// 修改
	
		
		// 隐藏
		function clearForm(){
           $('#add').window('close');
        }		
		
		// 删除
		function removeit(){
			var rows = $("#list_agriSupplier").datagrid("getSelections");
			 //判断是否选择行
			if (!rows || rows.length == 0) {
				$.messager.alert('提示', '请选择要删除的记录!', 'info');
				return false;
			}else{
			    var temp;   //循环给提交删除参数赋值
				$.each(rows, function (i, n) {
					if (i == 0) {
			        	temp = n.asId;
				   } else {
						temp += "," + n.asId;
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
				         	  url:'agriSupplier_deleteAgriSupplier.action',
				         	  data:'ids='+temp,
				         	  type:'post',
				         	  dataType:'text',
				         	  success : function(result) {
				         		   parent.$.messager.progress('close');
								   $.messager.show({ title : '提示', msg : result});
								   dataGrid.datagrid('reload',{});
							  }    
				         });
				    }
				});    
			}
		}
		
		function update(){
			var rows = $('#list_agriSupplier').datagrid("getSelections");
			var leng = rows.length;
			if(leng==0){
			    $.messager.alert('提示','请选择你需要修改的记录!','info');
			}else{
			    if(leng>1){
			        $.messager.alert('提示','只能修改一条记录!','info');
			        return false;
			    }else{
			         $('#oprerate').attr("value","2"); 
			         var agriSupplier = rows[0];
			         
			         $("input[name='agriSupplier.asName']").val(agriSupplier.asName);
			         $("input[name='agriSupplier.asAddr']").val(agriSupplier.asAddr);
			         $("input[name='agriSupplier.asPhone']").val(agriSupplier.asPhone);
			         $("input[name='agriSupplier.asGoods']").val(agriSupplier.asGoods);
			         $("input[name='agriSupplier.asMan']").val(agriSupplier.asMan);
			         
			         $("input[name='agriSupplier.asId']").val(agriSupplier.asId);
			         $("input[name='agriSupplier.crttime']").val(agriSupplier.crttime);
					
					 $('#add').window('open').dialog('setTitle', '修改投入品供应商');
			         $("#add_form").form("load", {});
			    }
			}
		}
				
				// 搜索
		function find() {
			var asName = $('#s_asName').attr("value");
			var agriSupplier = {};
			
			if(asName!=""){
				agriSupplier["agriSupplier.asName"]=asName;
			};
		
			dataGrid.datagrid('reload', agriSupplier); // 点击搜索
		}
		
		
		function clearSearch(){
			$('#name').combotree('clear');;
		}
		
		// 提交表格 - 添加组织机构类型
        function submitForm(){
           // oprerate=1 --> 添加 ，  oprerate=2 -- > 修改 
           var op = $('#oprerate').attr("value"); 
           if(op==1){
                
	            $('#add_form').form('submit', {
					url : 'agriSupplier_addAgriSupplier.action',
					onSubmit : function(result) { 
					    return $(this).form('validate');//对数据进行格式化
					},
					success : function(result) {
        				$.messager.show( {
        					title : '提示',
        					msg : result
        				});
        				$('#add').window('close');
        				clearSearch();
        				dataGrid.datagrid('reload',{});
        			}
				});
           }else{
	            
		        $('#add_form').form('submit', {
					url : 'agriSupplier_updateAgriSupplier.action',
					onSubmit : function(result) { 
						 return $(this).form('validate');//对数据进行格式化
					},
					success : function(result) {
	        			$.messager.show( {
	        				title : '提示',
	        				msg : result
	        			});
	        			$('#add').window('close');
	        			clearSearch();
	        			dataGrid.datagrid('reload',{});
	        			
	        		}
				});
           }
        }
