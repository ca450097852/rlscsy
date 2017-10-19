var dataGrid;
		$(function() {
		     $('#add').window('close');
			 dataGrid = $('#list_agriDis').datagrid({
			    url:'agriDis_findAgriDisList.action',
				toolbar:"#tb",    //在添加 增添、删除、修改操作的按钮要用到这个  
				title: '禁用投入品管理',
				iconCls: 'icon-ok',
				loadMsg:'数据加载中...',
				pageSize : 15,//默认选择的分页是每页10行数据  
			    pageList : [ 10, 15,20,25 ],//可以选择的分页集合
			    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
			    striped : true,//设置为true将交替显示行背景。
				fit:true,
				fitColumns: true,
				pagination:true,
				rownumbers:true,
				remoteSort : false,
				frozenColumns : [ [ {  
			           field : 'ck',  
			           checkbox : true  
			    } ] ],
				columns:[[
					{field:'agName',title:'禁用投入品名称',width:100,align:'center'},
					{field:'agReason',title:'禁用原因',width:150,align:'center'},
					{field:'agTime',title:'禁用时间',width:100,align:'center'},
					{field:'agNum',title:'登记证号',width:100,align:'center'}
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
			 $("#add").dialog("open").dialog('setTitle', '添加禁用投入品');
			 $('#oprerate').attr("value","1");  
			 
			 $('#agTime').datebox({    
				    required:true   
				});  
		}
		
		// 修改
	
		
		// 隐藏
		function clearForm(){
           $('#add').window('close');
        }		
		
		// 删除
		function removeit(){
			var rows = $("#list_agriDis").datagrid("getSelections");
			 //判断是否选择行
			if (!rows || rows.length == 0) {
				$.messager.alert('提示', '请选择要删除的记录!', 'info');
				return false;
			}else{
			    var temp;   //循环给提交删除参数赋值
				$.each(rows, function (i, n) {
					if (i == 0) {
			        	temp = n.agId;
				   } else {
						temp += "," + n.agId;
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
				         	  url:'agriDis_deleteAgriDis.action',
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
			var rows = $('#list_agriDis').datagrid("getSelections");
			var leng = rows.length;
			if(leng==0){
			    $.messager.alert('提示','请选择你需要修改的记录!','info');
			}else{
			    if(leng>1){
			        $.messager.alert('提示','只能修改一条记录!','info');
			        return false;
			    }else{
			         $('#oprerate').attr("value","2"); 
			         var agriDis = rows[0];
			         
			         $("input[name='agriDis.agName']").val(agriDis.agName);
			         $('#agTime').datebox('setValue',agriDis.agTime);
			         
			         $("textarea[name='agriDis.agReason']").val(agriDis.agReason);
			         $("input[name='agriDis.agNum']").val(agriDis.agNum);
			         
			         $("input[name='agriDis.agId']").val(agriDis.agId);
			         $("input[name='agriDis.crttime']").val(agriDis.crttime);
					
					 $('#add').window('open').dialog('setTitle', '修改禁用投入品');
			         $("#add_form").form("load", {});
			    }
			}
		}
				
				// 搜索
		function find() {
			var agName = $('#s_agName').attr("value");
			var agriDis = {};
			
			if(agName!=""){
				agriDis["agriDis.agName"]=agName;
			};
		
			dataGrid.datagrid('reload', agriDis); // 点击搜索
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
					url : 'agriDis_addAgriDis.action',
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
					url : 'agriDis_updateAgriDis.action',
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
