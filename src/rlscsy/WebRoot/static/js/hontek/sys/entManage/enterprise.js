var dataGrid; // 列表
$(function() {	
	
	var entParam = {};
	entParam["enterprise.flag"]='2';
	dataGrid = $('#list_enterprise').datagrid( {
		url : 'ent_findEnterpriseList.action',
		queryParams: entParam,
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '机构管理',
		iconCls : 'icon-enterprise',
		loadMsg : '数据加载中...',
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [ 10, 15,20, 30, 50 ],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		fit : true,
		fitColumns: true,
		// singleSelect:true,
		pagination : true,
		rownumbers : true,
		remoteSort : false,
		// collapsible : true,//显示可折叠按钮
//		sortName : 'entId',// 当数据表格初始化时以哪一列来排序
//		sortOrder : 'desc',// 定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, 
		//{field : 'entId',title : '组织编号',width : 80,align : 'center'}, 
		{field : 'parentName',title : '上级组织',width : 150,align : 'center'}, 
		{field : 'name',title : '组织名称',width : 150,align : 'center'}, 
		{field : 'account',title : '组织帐号',width : 100,align : 'center'}, 
		{field : 'regAddr',title : '地址',width : 200,align : 'center'}, 

		//{field : 'legalPerson',title : '法人代表',width : 80,align : 'center'}, 
		{field : 'areaName',title : '区域',width : 80,align : 'center'}, 
		{field : 'typeName',title : '分类',width : 100,align : 'center'}, 
		/*{field : 'flag',title : '机构标识',width : 100,align : 'center',
			formatter : function(value, row, index) {
					if (value == 0) {
						return "超级管理员";
					} else if (value == 1) {
						return "区域";
					} else if (value == 2) {
						return "行政机构";
					} else {
						return "企业";
					}
				}
			}, */
		{field : 'sts',title : '状态',width : 80,align : 'center',
			formatter : function(value, row, index) {
				if (value == 0) {
					return "<font color=green>使用</font>";
				} else if (value == 1) {
					return "<font color=red>禁用</font>";
				} else if (value == 2) {
					return "待确认";
				} else if (value == 3) {
					return "注销";
				}
			}
		}
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
	
	var tt = Math.floor(Math.random()*20)+1;
	
	$("#parentId").combotree({
		url:'ent_getEntTree.action?tt='+tt,
		method:'get',
		required:true,
		onLoadSuccess:function(node, data){
			if(data){
				if(data[0].checked){
					$("#parentId").combotree("setValue",data[0].id);
				}
			}
			f_timeout(data);			
		}
	});
	
	$("#areaId").combotree({
		url:'ent_getEntAreaTree.action?tt='+tt,
		method:'get',
		required:true,
		onLoadSuccess:function(node, data){			
			f_timeout(data);			
		}
	});
	
	$('#add_form').form('clear');
	$("#add").dialog("open").dialog('setTitle', '添加组织机构');
	$("input[name='enterprise.account']").removeAttr('readonly');
	$("input[name='enterprise.account']").validatebox('enableValidation');
	$('#oprerate').attr("value", "1");
	$('#sts_id').attr("value", "0");

	$("input[name='enterprise.seq']").val('5');

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
			
			$('#add_form').form('clear');
			
			$('#oprerate').attr("value", "2");
			var enterprise = rows[0];
			var tt = Math.floor(Math.random()*20)+1;
			
			$("#parentId").combotree({
				url:'ent_getEntTree.action?tt='+tt,
				method:'get',
				required:true,
				value:enterprise.parentId
			});
								
			
			$("#areaId").combotree({
				url:'ent_getEntAreaTree.action?tt='+tt,
				method:'get',
				required:true,
				value:enterprise.areaId,
				onLoadSuccess:function(node, data){			
					f_timeout(data);			
				}
			});
			
			
			$("input[name='enterprise.account']").attr('readonly','true');
			$("input[name='enterprise.account']").val(enterprise.account);
			$("input[name='enterprise.account']").validatebox('disableValidation');
						
			$('#flag_id').combobox('setValue', enterprise.flag);
			$('#entType_id').combobox('setValue', enterprise.entType);
			
			$('#sts_id').val(enterprise.sts);
			$("input[name='enterprise.flag']").val(enterprise.flag);
			$("input[name='enterprise.legalPerson']").val(enterprise.legalPerson);
			
			$("input[name='enterprise.entCode']").val(enterprise.entCode);
			$("input[name='enterprise.name']").val(enterprise.name);
			$("input[name='enterprise.regAddr']").val(enterprise.regAddr);
			$("input[name='enterprise.manageAddr']").val(enterprise.manageAddr);

			$("input[name='enterprise.simpleName']").val(enterprise.simpleName);
			$("input[name='enterprise.tel']").val(enterprise.tel);
			$("input[name='enterprise.postCode']").val(enterprise.postCode);
			$("input[name='enterprise.domName']").val(enterprise.domName);
			$("input[name='enterprise.sign']").val(enterprise.sign);
			$("input[name='enterprise.email']").val(enterprise.email);
			$("input[name='enterprise.seq']").val(enterprise.seq);
			$("input[name='enterprise.logoUrl']").val(enterprise.logoUrl);
			$("input[name='enterprise.sign']").val(enterprise.sign);
			$("textarea[name='enterprise.intro']").val(enterprise.intro);
			$("input[name='enterprise.entId']").val(enterprise.entId);
			
			$("input[name='enterprise.isReported']").val(enterprise.isReported);
			$("input[name='enterprise.sysCode']").val(enterprise.sysCode);

			$("#add_form").form("validate");

			$('#add').window('open').window('setTitle', '修改组织机构');
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

//修改状态
function changeCheck(sts){		
	var arr = $('#list_enterprise').datagrid("getSelections");
	var lg = arr.length;
	if(lg==0){
		$.messager.alert('提示','请选择需要操作的记录!','question');
	}else if(lg!=1){
		$.messager.alert('提示','对不起，一次只能操作一条信息，请重新选择!','question');
	}else{		    
		var enterprise = arr[0];
		if(enterprise.sts==sts&&sts=='0'){
  			$.messager.show({ title : '提示', msg : '已是使用状态!'});
  			return;
  		}else if(enterprise.sts==sts&&sts=='1'){
  			$.messager.show({ title : '提示', msg : '已是禁用状态!'});
  			return;
  		}else if(enterprise.sts==sts&&sts=='3'){
  			$.messager.show({ title : '提示', msg : '已是注销状态!'});
  			return;
  		}
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		$.ajax({
         	  url:'ent_updateSts.action',
         	  data:{ entId: enterprise.entId, sts: sts },
         	  type:'post',
         	  dataType:'text',
         	  success : function(result) {
         		   parent.$.messager.progress('close');
				   $.messager.show({ title : '提示', msg : result});
				   $('#list_enterprise').datagrid("reload");
			  }    
         });
	}		  													
}

// 搜索
function find() {
	var account = $('#account').attr("value");
	var name = $('#name').attr("value");
	var sts = $('#sts').val();
	
	var enterprise = {};
	if(account!=""){enterprise["enterprise.account"]=account};
	if(name != ""){enterprise["enterprise.name"]=name};
	if (sts != "-1") {enterprise["enterprise.sts"]=sts}
	enterprise["enterprise.flag"]='2';

	dataGrid.datagrid('load', enterprise); // 点击搜索
}

function clearSearch(){
	$('#account').val("");
	$('#name').val("");
	$('#sts').val(-1);
	var enterprise = {};
	enterprise["enterprise.flag"]='2';

	dataGrid.datagrid('load',enterprise);
}

// 提交表格 - 添加组织机构类型
function submitForm() {
	// oprerate=1 --> 添加 ， oprerate=2 -- > 修改
	var op = $('#oprerate').attr("value");
	if (op == 1) {
			$('#add_form').form('submit', {
				url : 'ent_addEnterprise.action',
				onSubmit : function(result) {
					return $(this).form('validate');// 对数据进行格式化
			},
			success : function(result) {
				$.messager.show( {
					title : '提示',
					msg : result,
					width:300,//定义消息窗口的宽度。默认：250px。
					height:150//定义消息窗口的高度。默认：100px。
				});
				$('#add').window('close');
				dataGrid.datagrid('reload');
			}
			});
	} else {
		$('#add_form').form('submit', {
			url : 'ent_updateEnterprise.action',
			onSubmit : function(result) {
				return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			$.messager.show( {
				title : '提示',
				msg : result
			});
			dataGrid.datagrid('reload');
			$('#add').window('close');
		}
		});
	}
}
