	var dataGrid; // 列表
	var formUrl = '';
	$(function() {
		dataGrid = $('#list_enterprise').datagrid( {
			url : 'ent_findAreaList.action',
			toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
			title : '区域管理',
			iconCls : 'icon-map',
			loadMsg : '数据加载中...',
			pageSize : 15,// 默认选择的分页是每页10行数据
			pageList : [ 10, 15,20, 30, 50 ],// 可以选择的分页集合
			nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
			striped : true,// 设置为true将交替显示行背景。
			fit : true,
			// fitColumns: true,
			// singleSelect:true,
			pagination : true,
			rownumbers : true,
			remoteSort : false,
			// collapsible : true,//显示可折叠按钮
	//		sortName : 'entId',// 当数据表格初始化时以哪一列来排序
	//		sortOrder : 'desc',// 定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			}, 
				{field : 'parentName',title : '上级区域',width : 300,align : 'center'}, 
				{field : 'name',title : '区域名称',width : 300,align : 'center'}, 
				{field : 'entCode',title : '区域编号',width : 300,align : 'center'}
			] ],
			onLoadSuccess:function(data){
			 	f_timeout(data);
		 	},
	 		onClickRow: function(rowIndex, rowData){
	 			$(this).datagrid('unselectAll');
	 			$(this).datagrid('selectRow',rowIndex);
	 		}
		});
		 parent.$.messager.progress('close');
	});


	// 显示 - 组织机构
	function append() {
		var rows = $('#list_enterprise').datagrid("getSelections");
		var defValue = '';
		if(rows.length==1){
			defValue = rows[0].entId;
		}
		$("#parentId").combotree({
			url:'ent_getEntAreaTree.action?tt='+Math.floor(Math.random()*20)+1,
			method:'get',
			required:true,
			value:defValue,
			onLoadSuccess:function(node, data){			
				f_timeout(data);			
			}
		});
	
		$('#add_form').form("reset");
		$("#add").dialog("open").dialog('setTitle', '添加区域');
		formUrl = 'ent_addEnterprise.action';
	}


	// 修改
	function update() {
		var rows = $('#list_enterprise').datagrid("getSelections");
		var leng = rows.length;
		if (leng == 0) {
			$.messager.alert('提示', '请选择你需要修改的记录!', 'info');
		} else {
			if (leng > 1) {
				$.messager.alert('提示', '只能修改一条记录!', 'info');
				return false;
			} else {
				var enterprise = rows[0];
				
				formUrl = 'ent_updateEnterprise.action';
			
				$("#parentId").combotree({
					url:'ent_getEntAreaTree.action?tt='+Math.floor(Math.random()*20)+1,
					method:'get',
					required:true,
					value :enterprise.parentId
				});						
				$("input[name='enterprise.flag']").val(enterprise.flag);
				$("input[name='enterprise.name']").val(enterprise.name);
				$("input[name='enterprise.entCode']").val(enterprise.entCode);
				$("input[name='enterprise.entId']").val(enterprise.entId);						
				$('#add').dialog('open').dialog('setTitle', '修改区域');
				$("#add_form").form('validate');
			}
		}
	}


	// 删除
	function removeit() {
		var rows = $("#list_enterprise").datagrid("getSelections");
		// 判断是否选择行
		if (!rows || rows.length == 0) {
			$.messager.alert('提示', '请选择要删除的记录!', 'info');
			return false;
		} else {
			var temp; // 循环给提交删除参数赋值
			$.each(rows, function(i, n) {
				if (i == 0) {
					temp = n.entId;
				} else {
					temp += "," + n.entId;
				}
			});
			$.messager.confirm('提示', '是否删除选中数据?', function(r) {
				if (!r) {
					return;
				} else {
					parent.$.messager.progress( {
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.ajax( {
						url : 'ent_deleteEnterprise.action',
						data : 'list_enterprise=' + temp,
						type : 'post',
						dataType : 'text',
						success : function(result) {
							parent.$.messager.progress('close');
							$.messager.show( {
								title : '提示',
								msg : result
							});
							dataGrid.datagrid('reload');
						}
					});
				}
			});
		}
	}

	
	// 搜索
	function find() {
		var name = $("#name").val();
		var ent = {};
		ent["enterprise.name"] = name;
		dataGrid.datagrid('load',ent);
	
	}


	// 提交表格 - 添加组织机构类型
	function submitForm() {
		$('#add_form').form('submit', {
			url : formUrl,
			onSubmit : function(result) {
				return $(this).form('validate');// 对数据进行格式化
			},
			success : function(result) {
				$.messager.show( {title : '提示',msg : result});
				dataGrid.datagrid('reload');
				$('#add').window('close');
			}
		});
	}


