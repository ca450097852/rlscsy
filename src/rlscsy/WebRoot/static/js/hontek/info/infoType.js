var dataGrid;
		$(function() {
		     $('#add').window('close');
			 dataGrid = $('#list_infotype').datagrid({
			    url:'infotype_findInfoType.action',
				toolbar:"#tb",    //在添加 增添、删除、修改操作的按钮要用到这个  
				title: '资讯类别管理',
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
				//sortName : 'typeId',//当数据表格初始化时以哪一列来排序  
			    sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。  
				frozenColumns : [ [ {  
			           field : 'ck',  
			           checkbox : true  
			    } ] ],
				columns:[[
					{field:'parName',title:'上级分类',width:100,align:'center',
			        	formatter : function(value, row, index) {
							if (value == 0) {
								return "无";
							} else{
								return value;
							}
			        	}
					},
					{field:'typeName',title:'类型名称',width:200,align:'center'},
					{field:'crttime',title:'创建时间',width:100,align:'center'},
					{field:'nickName',title:'创建人',width:100,align:'center'},
					{field:'remark',title:'备注',width:200,align:'center'}
					
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
			 
			 $("#name").combotree({
					url:'infotype_getInfoTypeTree.action',
					method:'get',
					required:false,
					panelHeight: 'auto'
					/*onLoadSuccess:function(node, data){
						var t = $('#name').combotree('tree');      // 得到树对象 
						var n = t.tree('getChildren');          // 得到选择的节点
						if(n==0){
							$(".enterprise_parentId_tr").remove();
						}else{
							f_timeout(data);
						}
					}*/
				});
			 parent.$.messager.progress('close');
		});
		
				// 添加 - 信息
		function append() {
			$("#parent_id").combotree({
				url:'infotype_getInfoTypeTree.action',
				method:'get',
				required:false,
				panelHeight: 'auto'
				/*onLoadSuccess:function(node, data){
					var t = $('#parent_id').combotree('tree');      // 得到树对象 
					var n = t.tree('getChildren');          // 得到选择的节点
					if(n==0){
						$(".enterprise_parentId_tr").remove();
					}else{
						f_timeout(data);
					}
				}*/
			});
			
			 $('#add_form').form('clear');
			 $("#add").dialog("open").dialog('setTitle', '添加资讯类型');
			 $('#oprerate').attr("value","1");  
		}
		
		// 修改
	
		
		// 隐藏
		function clearForm(){
           $('#add').window('close');
        }		
		
		// 删除
		function removeit(){
			var rows = $("#list_infotype").datagrid("getSelections");
			 //判断是否选择行
			if (!rows || rows.length == 0) {
				$.messager.alert('提示', '请选择要删除的记录!', 'info');
				return false;
			}else{
			    var temp;   //循环给提交删除参数赋值
				$.each(rows, function (i, n) {
					if (i == 0) {
			        	temp = n.typeId;
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
				         	  url:'infotype_deleteInfoType.action',
				         	  data:'infoTypes='+temp,
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
			var rows = $('#list_infotype').datagrid("getSelections");
			var leng = rows.length;
			if(leng==0){
			    $.messager.alert('提示','请选择你需要修改的记录!','info');
			}else{
			    if(leng>1){
			        $.messager.alert('提示','只能修改一条记录!','info');
			        return false;
			    }else{
			         $('#oprerate').attr("value","2"); 
			         var tinfoType = rows[0];
			         
			         $("#parent_id").combotree({
						url:'infotype_getInfoTypeTree.action',
						method:'get',required:true
					});
					 $("#parent_id").combotree('setValue', tinfoType.parentId);
			         $("input[name='tinfoType.typeName']").val(tinfoType.typeName);
			         $("input[name='tinfoType.crttime']").val(tinfoType.crttime);
			         $("textarea[name='tinfoType.remark']").val(tinfoType.remark);
			         $("input[name='tinfoType.userId']").val(tinfoType.userId);
			         $("input[name='tinfoType.typeId']").val(tinfoType.typeId);
					
					 $('#add').window('open').dialog('setTitle', '修改资讯类型');
			         $("#add_form").form("load", {});
			    }
			}
		}
				
				// 搜索
		function find() {
//			var typeName = $('#name').attr("value");
			var typeId = $("#name").combotree('getValue');
			var tinfoType = {};
			if(typeId!=""&&typeId!="0"){tinfoType["tinfoType.typeId"]=typeId};
		
			dataGrid.datagrid('reload', tinfoType); // 点击搜索
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
					url : 'infotype_addInfoType.action',
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
					url : 'infotype_updateInfoType.action',
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
