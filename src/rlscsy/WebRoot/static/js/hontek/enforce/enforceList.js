 var queryParams="";
 var formUrl = "Findenforc_AddLawUser.action";
$(function() {
	
		$('#list_enforceNode').datagrid( {
		url : 'Findenforc_findList.action',
		queryParams: queryParams,
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '执法人员列表',
		iconCls : 'icon-house',
		loadMsg : '数据加载中...',
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [ 10,15,20,25,30],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		fit : true,
		fitColumns: true,
		pagination : true,
		rownumbers : true,
		remoteSort : false,
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}] ],
		columns : [[ 
		            
					{field : 'userName',title : '执法人员名称',width : 120,halign:'left'}, 
					{field : 'entName',title : '执法单位',width : 80,align : 'center',	},
					{field : 'sex',title : '性别',width : 80,align:'center'},
					{field : 'age',title : '年龄',width : 80,align:'center'},
					{field : 'userNo',title : '执法编号',width : 80,align:'center'}, 
					{field : 'addr',title : '联系地址',width : 100,align : 'center'}, 
					{field : 'createTime',title : '创建时间',width : 60,align : 'center',},
					
						
		]],
	
 	
	});
});

	//搜索
	function find() {
		var name = $('#e_name').val();
		var endDate=$("#endDate").datebox('getValue');
		var startDate=$("#startDate").datebox('getValue');
			queryParams = {};
			if(name!=''){
				queryParams["enterprise.name"]=name;
			}
			if(startDate!=''){
				queryParams["enterprise.startDate"]=startDate;
			}
			if(endDate!=''){
				queryParams["enterprise.endDate"]=endDate;
	}

	$('#list_enforceNode').datagrid('load', queryParams); // 点击搜索
}

	//重置
	function clearSearch(){
		$('#e_name').val("");
		var endDate=$("#endDate").datebox('setValue',"");
		var startDate=$("#startDate").datebox('setValue',"");
		queryParams = {};
		$('#list_enforceNode').datagrid('load',queryParams);
}
	//显示添加页面
	function addEnforce(){
	
		formUrl = "Findenforc_AddLawUser.action";
		$('#add').panel({title: "添加执法人员信息",maximized:true});
		$('#add_form').form("reset");
		$('#add').window('open');	
	}
	
	//提交表单
	function submitForm(){
		var name = $("input[name='lawUser.userName']").val();
		if(name==''){
			$.messager.alert('提示', '请输入名称!', 'info');
			return;
		}
		if ($('#add_form').form('validate') == false) {
			$.messager.show({title : '提示',msg : '输入验证没有通过!'});
			return;
		}
		
		$.messager.progress( {title : '提示',text : '数据处理中，请稍后....'});
		$('#add_form').form('submit', {
			url : formUrl,
			onSubmit : function(result) {
				
			},
			success : function(result) {
				$.messager.progress('close');
				$.messager.show( {title : '提示',msg : result});
				$('#list_enforceNode').datagrid('reload');
				$('#add_form').form("reset");
				$('#add').window('close');
				
			}
		});
	}
	//删除
	function deleteLawUser(){
		var rows = $('#list_enforceNode').datagrid("getSelections");
		if (!rows || rows.length == 0) {// 判断是否选择行
			$.messager.alert('提示1', '请选择要删除的记录!', 'info');
			return false;
		} else {
			var temp=""; // 循环给提交删除参数赋值
			$.each(rows, function(i, n) {
				temp += n.userId+",";
			});
		
			$.messager.confirm('提示','确定要删除选中记录吗？', function(r) {
				if (!r) {
					return;
				} else {
					$.messager.progress( {
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.ajax( {
						url : 'Findenforc_delLawUser.action',
						data : {"ids":temp},
						type : 'post',
						dataType : 'text',
						success : function(result) {
							$.messager.progress('close');
							$.messager.show( {
								title : '提示',
								msg : result
							});
							$('#list_enforceNode').datagrid('reload');
						}
					});
				}
			});
		}

	}
	
	
	
	//修改
	function updateLawUser(){
		var rows = $('#list_enforceNode').datagrid("getSelections");
		if (!rows || rows.length == 0) {// 判断是否选择行
			$.messager.alert('提示', '请选择要修改的记录!', 'info');
			return false;
		}else if(rows.length>1){
			$.messager.alert('提示', '只能选择一条记录修改!', 'info');
			return false;
		}else{
			formUrl ="Findenforc_updateLawUser.action";
			var lawUser = rows[0];
			
			$('#add_form').form("reset");
			$("#add_form").form("load",lawUser);
			$('#add').panel({title: "修改执法人员信息",maximized:true});
			$('#add').window('open');	
			for(var item in lawUser){
				var text = lawUser[item]==null?"":lawUser[item];
				var obj = $("input[name='lawUser."+item+"']");
				if(obj){
					if(item=='sex'){
						
						if(text=='女'){
							 $("input[name='lawUser.sex'][value='女']").attr("checked",true);
						}
					}else{
						obj.val(text);
					}
				}
			
			}
		}
	}
	
	//关闭
	function clearForm(){
		$('#add_form').form('reset');
		$('#add').window('close');
	}
	//地区三级联动
	
