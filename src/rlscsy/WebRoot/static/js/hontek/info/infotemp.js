var dataGrid; // 列表

// 添加 - 信息
function append() {
	
	/*$("#typeId").combotree({
		url:'info_getInfoTypeTree.action',
		method:'get',
		required:true,
		panelHeight: 'auto',
		onLoadSuccess:function(node, data){
			var t = $('#typeId').combotree('tree');      // 得到树对象 
			var n = t.tree('getChildren');          // 得到选择的节点
			f_timeout(data);
		}
	});*/
	$("#typeId").combotree();
	$("#typeId").combotree('loadData', [{	id: 12,	text: '技术标准'}]);
	
	$("#xh_editor").val("");
	$('#add').form("clear");
	$("#add").dialog("open").dialog('setTitle', '添加信息');
	$('#oprerate').attr("value", "1");
}


	// 修改
function update() {
	var rows = $('#list_infotemp').datagrid("getSelections");
	var leng = rows.length;
	if (leng == 0) {
		$.messager.alert('提示', '请选择你需要修改的记录!', 'info');
	} else {
		if (leng > 1) {
			$.messager.alert('提示', '只能修改一条记录!', 'info');
			return false;
		} else {
			$('#oprerate').attr("value", "2");
			$("#start").show();
			var infotemp = rows[0];
			$("#typeId").combotree({
				url:'info_getInfoTypeTree.action',
				method:'get',required:true,
				value:infotemp.typeId
			});
			
			$("input[name='infotemp.tid']").val(infotemp.tid);
			$("input[name='infotemp.title']").val(infotemp.title);
			$("textarea[name='infotemp.remark']").val(infotemp.remark);
			$("textarea[name='infotemp.content']").val(infotemp.content);
			$("input[name='infotemp.infoId']").val(infotemp.infoId);
			$("input[name='infotemp.crttime']").val(infotemp.crttime);
			$("input[name='infotemp.sysCode']").val(infotemp.sysCode);
			
			$('#add').window('open').dialog('setTitle', '修改信息');
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
	var rows = $("#list_infotemp").datagrid("getSelections");
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要删除的记录!', 'info');
		return false;
	} else {
		var temp; // 循环给提交删除参数赋值
		$.each(rows, function(i, n) {
			if (i == 0) {
				temp = n.tid;
			} else {
				temp += "," + n.tid;
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
					url  : 'info_deleteInfo.action',
					data : 'tIds='+ temp,
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
	var title = $('#title').attr("value");
	var t = $('#typeId1').combotree('tree');	// 获取树对象
	var n = t.tree('getSelected');		// 获取选择的节点
	var typeId = n == null ? "" : n.id;

	var info = {};
	if(title!=""){info["infotemp.title"]=title};
	if(typeId!="" && typeId != null){info["infotemp.typeId"]=typeId};

	dataGrid.datagrid('reload', info); // 点击搜索
}

// 隐藏
function clearForm(){
   $('#add').window('close');
}

function clearSearch(){
	$('#title').val("");
	$('#typeId1').combotree('clear');;
	dataGrid.datagrid('reload');
}

// 提交表格 - 添加组织机构类型
function submitForm() {
	// oprerate=1 --> 添加 ， oprerate=2 -- > 修改
	$('#xh_editor').val();
	var op = $('#oprerate').attr("value");
	if (op == 1) {
			$('#add_form').form('submit', {
				url : 'info_addInfo.action',
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
			url : 'info_updateInfo.action',
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
//上报临时资讯
function report(){
	var rows = $("#list_infotemp").datagrid("getSelections");
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要上报的记录!', 'info');
		return false;
	} else {
		var temp; // 循环拼接上报临时资讯id
		$.each(rows, function(i, n) {
			if (i == 0) {
				temp = n.tid;
			} else {
				temp += "," + n.tid;
			}
		});
		$.messager.confirm('提示', '是否要上报选中数据?', function(r) {
			if (!r) {
				return;
			} else {
				parent.$.messager.progress( {
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.ajax( {
					url  : 'infotemp_reportInfotemp.action',
					data : 'tIds='+ temp,
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
