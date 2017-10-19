var dataGrid;
		$(function() {
			
			  $('#list_warning_ent').datagrid({
				    url:'warning_findWaringEntList.action',
					//toolbar:"#tb",    //在添加 增添、删除、修改操作的按钮要用到这个  
					title: '预警信息管理',
					iconCls: 'icon-ok',
					loadMsg:'数据加载中...',
					pageSize : 50,//默认选择的分页是每页10行数据  
				    pageList : [ 10, 15,20,50 ],//可以选择的分页集合
				    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
				    striped : true,//设置为true将交替显示行背景。
					fit:true,
					fitColumns: true,
					pagination:true,
					rownumbers:true,
					remoteSort : false,
					columns:[[
						{field:'entName',title:'企业名称',width:250,align:'center'},
						{field:'count',title:'未读预警信息数量',width:200,align:'center'},
						{field:'action',title:'操作',width:100,align:'center',formatter:
							function(value,row,index){
								return '<input type="button" onclick="showWaring('+index+')" value="查看"/>&nbsp;';
							}
						}
					]],
					onLoadSuccess:function(data){
					 	f_timeout(data);
				 		}
				});	

		});
		
				// 添加 - 信息
		function showWaring(index) {
			
			var row = $('#list_warning_ent').datagrid("getRows")[index];	

				var querys = {};
				querys["warning.entId"] = row.entId;

			 dataGrid = $('#list_warning').datagrid({
			    url:'warning_findWarningList.action',
			    queryParams:querys,
				toolbar:"#tb",    //在添加 增添、删除、修改操作的按钮要用到这个  
				title: '预警信息管理',
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
					{field:'wtype',title:'预警类型',width:100,align:'center',
			        	formatter : function(value, row, index) {
							if (value == '1') {
								return "安全间隔期预警";
							}else if (value == '2') {
								return "信息上报预警";
							}
			        	}
					},
					{field:'contents',title:'预警内容',width:250,align:'center'},
					{field:'warningTime',title:'预警时间',width:100,align:'center'},
					{field:'state',title:'状态',width:100,align:'center',
			        	formatter : function(value, row, index) {
							if (value == 1) {
								return "未读";
							} else if(value == 2){
								return "已读";
							} else if(value == 3){
								return "待处理";
							}
			        	}
					},
					{field:'action',title:'操作',width:100,align:'center',formatter:
						function(value,row,index){
							return '<input type="button" onclick="toLock('+index+')" value="查看"/>&nbsp;';
						}
					}
				]],
				onLoadSuccess:function(data){
				 	f_timeout(data);
					 $("#showWaring").window("open").window("maximize");

			 		}
			});	
			
			 
		}
		
		
		// 隐藏
		function clearForm(){
           $('#add').window('close');
           dataGrid.datagrid('reload');
        }		
		
		// 删除
		function removeit(){
			var rows = $("#list_warning").datagrid("getSelections");
			 //判断是否选择行
			if (!rows || rows.length == 0) {
				$.messager.alert('提示', '请选择要删除的记录!', 'info');
				return false;
			}else{
			    var temp;   //循环给提交删除参数赋值
				$.each(rows, function (i, n) {
					if (i == 0) {
			        	temp = n.wid;
				   } else {
						temp += "," + n.wid;
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
				         	  url:'warning_deleteWarning.action',
				         	  data:'ids='+temp,
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
		
		function readWaring(state){
			var rows = $("#list_warning").datagrid("getSelections");
			 //判断是否选择行
			if (!rows || rows.length == 0) {
				$.messager.alert('提示', '请选择要操作的记录!', 'info');
				return false;
			}else{
			    var temp;   //循环给提交删除参数赋值
				$.each(rows, function (i, n) {
					if (i == 0) {
			        	temp = n.wid;
				   } else {
						temp += "," + n.wid;
			       }
				}); 
		        parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
		         });
		         
		         $.ajax({
		         	  url:'warning_updateStates.action',
		         	  data:{"ids":temp,"state":state},
		         	  type:'post',
		         	  dataType:'text',
		         	  success : function(result) {
		         		   parent.$.messager.progress('close');
						   $.messager.show({ title : '提示', msg : result});
						   dataGrid.datagrid('reload');
					  }    
		         });		         
			}			
		}
		
		//查看
		function toLock(index){
			var row = $('#list_warning').datagrid("getRows")[index];	
			if(row){
//				$('#oprerate').attr("value","2"); 
		         var warning = row;
		         
		         var state = warning.state;
		         if(state==1){
		        	 $.ajax({
			         	  url:'warning_updateState.action',
			         	  data:'ids='+row.wid,
			         	  type:'post',
			         	  dataType:'text'
			         });
		         }
		         
		         $("#wtype").combobox('setValue',warning.wtype);
		         $('#warningTime').datetimebox('setValue',warning.warningTime);
		         $("textarea[name='warning.contents']").val(warning.contents);
		         $("input[name='warning.wid']").val(warning.wid);
		         $("input[name='warning.crttime']").val(warning.crttime);
		         $("input[name='warning.state']").val(warning.state);
		         
				 $('#add').window('open').dialog('setTitle', '查看预警');
		         $("#add_form").form("load", {});
			}
		}
		
				
				// 搜索
		function find() {
			var wtype = $('#s_wtype').attr("value");
			var state = $('#s_state').attr("value");
			var warning = {};
			
			if(wtype!="-1"){
				warning["warning.wtype"]=wtype;
			};
			if(state!="-1"){
				warning["warning.state"]=state;
			};
		
			dataGrid.datagrid('reload', warning); // 点击搜索
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
					url : 'warning_addWarning.action',
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
					url : 'warning_updateWarning.action',
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
