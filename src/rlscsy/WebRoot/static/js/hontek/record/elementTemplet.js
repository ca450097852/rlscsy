var dataGrid; // 列表
$(function() {
	
	$('#add').window('close');
	dataGrid = $('#list_elementTemplet').datagrid( {
		url : 'elementTemplet_findElementTempletList.action',
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '对象档案参照管理',
		iconCls : 'icon-ok',
		loadMsg : '数据加载中...',
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [ 10, 15,20, 30, 50 ],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		fit : true,
		pagination : true,
		rownumbers : true,
		remoteSort : false,
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}] ],
		columns:[ [ 
		{field : 'archivesType',title : '档案类型',width : 350,align : 'center',
	        	formatter : function(value, row, index) {
				if (value == 1) {
					return "普通";
				} else if (value == 2) {
					return "三品一标";
				}else if (value == 3) {
					return "种植类";
				}else if (value == 4) {
					return "畜牧类";
				}
	    	}
		}, 
		{field : 'elementName',title : '要素名称',width : 250,align : 'center'}
	     ] ],
		onHeaderContextMenu : function(e, field) {
			e.preventDefault();
			if (!cmenu) {
				createColumnMenu();
			}
			cmenu.menu('tb', {
				left : e.pageX,
				top : e.pageY
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
	$("#add").dialog("open").dialog('setTitle', '添加对象档案参照');
	$('#oprerate').attr("value", "1");
}


	// 修改
function update() {
	var rows = $('#list_elementTemplet').datagrid("getSelections");
	var leng = rows.length;
	if (leng == 0) {
		$.messager.alert('提示', '请选择你需要修改的记录!', 'info');
	} else {
		if (leng > 1) {
			$.messager.alert('提示', '只能修改一条记录!', 'info');
			return false;
		} else {
			$('#oprerate').attr("value", "2");// 修改
			var elementTemplet = rows[0];
			
			$("input[name='elementTemplet.tempId']").val(elementTemplet.tempId);
			$("input[name='elementTemplet.archivesType']").val(elementTemplet.archivesType);
			$('#elementidss').combobox('setValue', elementTemplet.elementId);
			$('#add').window('open').dialog('setTitle', '修改对象档案参照');
			$("#add_form").form("load", {});
		}
	}
}


// 隐藏
function clearForm(id) {
	if(id==1){
		$('#add').window('close');
	}else{
		$('#show').window('close');
	}
}

// 删除
function removeit() {
	var rows = $("#list_elementTemplet").datagrid("getSelections");
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要删除的记录!', 'info');
		return false;
	} else {
		var temp; // 循环给提交删除参数赋值
		$.each(rows, function(i, n) {
			if (i == 0) {
				temp = n.tempId;
			} else {
				temp += "," + n.tempId;
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
					url : 'elementTemplet_deleteElementTemplet.action',
					data : 'ids='+ temp,
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
	var archivesType = $('#s_archivesType').combobox('getValue');
	var elementTemplet = {};
	if(archivesType!='-1'){elementTemplet["elementTemplet.archivesType"]=archivesType};
	dataGrid.datagrid('reload', elementTemplet); // 点击搜索
}

// 隐藏
function clearForm(){
   $('#add').window('close');
}

function clearSearch(){
	$('#elementName').val("");
	dataGrid.datagrid('reload',{});
}

// 提交表格 - 对象档案参照
function submitForm() {
	// oprerate=1 --> 添加 ， oprerate=2 -- > 修改
	var op = $('#oprerate').attr("value");
	if (op == 1) {
			$('#add_form').form('submit', {
				url : 'elementTemplet_addElementTemplet.action',
				onSubmit : function(result) {
					return $(this).form('validate');// 对数据进行格式化
			},
			success : function(result) {
				$.messager.show( {
					title : '提示',
					msg : result
				});
				$('#add').window('close');
				clearSearch();
				dataGrid.datagrid('reload');
			}
			});

	} else {
		   $('#add_form').form('submit', {
			url : 'elementTemplet_updateElementTemplet.action',
			onSubmit : function(result) {
				return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			$.messager.show( {
				title : '提示',
				msg : result
			});
			clearSearch();
			dataGrid.datagrid('reload');
			$('#add').window('close');
		}
		});
	}
}
