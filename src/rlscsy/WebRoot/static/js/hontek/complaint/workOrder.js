var dataGrid; // 列表
$(function() {
	
	$('#workOrder_detail').window('close');
	$('#add').window('close');
	$('#seachStore').window('close');	
	$('#seachVendor').window('close');
	// 列表
	dataGrid = $('#workOrderList').datagrid( {
		url : 'workOrder_findWorkOrderList.action',
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '举报工单管理',
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
		{field : 'title',title : '举报标题',width : 200,align : 'center'},
		{field : 'opinion',title : '处理意见',width : 300,align : 'center',
				formatter : function(value, row, index) {
				if (value.length>15) {
					return value.substring(0,14);
				} else{
					return value;
				} 
			}
		}, 
		{field : 'dotime',title : '处理时间',width : 150,align : 'center'}, 
		{field : 'douser',title : '处理人',width : 150,align : 'center'}, 
		{field : 'sts',title : '状态',width : 100,align : 'center',
				formatter : function(value, row, index) {
					if (value == 0) {
						return "<font color=red>未处理</font>";
					} else if (value == 1) {
						return "<font>处理中</font>";
					} else if (value ==2) {
						return "<font color=green>已处理</font>";
					}
				}
		},
		{field:'woId',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 
			if (row.sts != 2) {
				return "<a href='javascript:void(0)' onclick='tocheck("+index+",1)'><font color=red>处理</font></a>"+
				"&nbsp;&nbsp;<a href='javascript:void(0)' onclick='toLook("+index+")'>详细</a>";
			}else{
				return "<a href='javascript:void(0)' onclick='tocheck("+index+",0)'>编辑</a>"+
				"&nbsp;&nbsp;<a href='javascript:void(0)' onclick='toLook("+index+")'>详细</a>";
			}
		}}
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
	 	},
	 	onClickRow: function(rowIndex, rowData){
 			$(this).datagrid('unselectAll');
 			$(this).datagrid('selectRow',rowIndex);
 		}
	});
 parent.$.messager.progress('close');
	
	});

// 添加 - 信息
function append() {

	$("#start").hide();
	$('#add').form("clear");
	$("#add").dialog("open").dialog('setTitle', '添加工单');
	$('#oprerate').attr("value", "1");
}

//查看
function toLook(i) {
	var workOrder = dataGrid.datagrid("getRows")[i]; 
	$("#d_title").html(workOrder.title);
	$("#d_proName").html(workOrder.proName);
	$("#d_companyName").html(workOrder.companyName);
	$("#d_content").html(workOrder.content);
	$("#d_opinion").html(workOrder.opinion);
	$("#d_finalResult").html(workOrder.finalResult);
	$("#d_remark").html(workOrder.remark);
	$("#d_douser").html(workOrder.douser);
	
	if(workOrder.sts==0){
		$("#d_sts").html("未处理");
	}else if(workOrder.sts==1){
		$("#d_sts").html("处理中");
	}else if(workOrder.sts==2){
		$("#d_sts").html("已处理");
	}
	
	$('#workOrder_detail').window('open').dialog('setTitle', '工单详细');
	$("#add_form").form("load", {});
}

//处理 or 编辑
function tocheck(i,j) {
	var workOrder = dataGrid.datagrid("getRows")[i]; 
	$('#oprerate').attr("value", "2");
	
	$("textarea[name='workOrder.opinion']").val(workOrder.opinion);
	$("textarea[name='workOrder.finalResult']").val(workOrder.finalResult);
	$("textarea[name='workOrder.remark']").val(workOrder.remark);
	$("input[name='workOrder.douser']").val(workOrder.douser);
	
	$("#up_title").html(workOrder.title);
	$("#up_proName").html(workOrder.proName);
	$("#up_companyName").html(workOrder.companyName);
	$("#up_content").html(workOrder.content);
	
	$("input[name='workOrder.woId']").val(workOrder.woId);
	$("input[name='workOrder.cid']").val(workOrder.cid);
	$("input[name='workOrder.dotime']").val(workOrder.dotime);
	
	
	if(j==1){//处理--设sts=2：表示已处理
		$("input[name='workOrder.sts']").val(2);
		$('#add').window('open').dialog('setTitle', '处理工单');
	}else{
		$("input[name='workOrder.sts']").val(workOrder.sts);
		$('#add').window('open').dialog('setTitle', '编辑工单');
	}
	
	$("#add_form").form("load", {});
}
	// 修改
function update() {
	var rows = $('#workOrderList').datagrid("getSelections");
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
			var workOrder = rows[0];
			
			$("input[name='workOrder.woId']").val(workOrder.woId);
			$("input[name='workOrder.cid']").val(workOrder.cid);
//			$("input[name='workOrder.title']").val(workOrder.title);
			
			
			$("#d_title").html(workOrder.title);
			$("#d_content").html(workOrder.content);
			
			$("textarea[name='workOrder.opinion']").val(workOrder.opinion);
			$("textarea[name='workOrder.finalResult']").val(workOrder.finalResult);
			$("textarea[name='workOrder.remark']").val(workOrder.remark);
			$("input[name='workOrder.dotime']").val(workOrder.dotime);
			$("input[name='workOrder.douser']").val(workOrder.douser);
			$("#sts").combobox('setValue',workOrder.sts);
			
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
	var rows = $("#workOrderList").datagrid("getSelections");
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要删除的记录!', 'info');
		return false;
	} else {
		var temp; // 循环给提交删除参数赋值
		$.each(rows, function(i, n) {
			if (i == 0) {
				temp = n.cid;
			} else {
				temp += "," + n.cid;
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
					url : 'workOrder_deleteWorkOrder.action',
					data : 'cids='+ temp,
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
function Search() {
	var title = $('#title').attr("value");
	var rsts = $('#rsts').val();
	var workOrder = {};
	if(title!=""){workOrder["workOrder.title"]=title};
	if (rsts != "-1") {
		workOrder["workOrder.sts"]=rsts;
		}

	dataGrid.datagrid('reload', workOrder); // 点击搜索
}

// 隐藏
function clearForm(){
   $('#add').window('close');
}

function clearSearch(){
	$('#title').val("");
	$('#rsts').val(-1);
	var workOrder = {};
	dataGrid.datagrid('reload', workOrder);
}

// 提交表格 - 添加组织机构类型
function submitForm() {
	// oprerate=1 --> 添加 ， oprerate=2 -- > 修改
	var op = $('#oprerate').attr("value");
	if (op == 1) {
		return;
	} else {
		   $('#add_form').form('submit', {
			url : 'workOrder_updateWorkOrder.action',
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
