var dataGrid; // 列表

// 添加 - 信息
function append() {
	$('#prevBtn').attr('href','javascript:void(0);');
	$('#prevBtn').unbind("click");
	
	$("#typeId").combotree({
		url:'info_getInfoTypeTree.action',
		method:'get',
		required:true,
		panelHeight: 'auto',
		onLoadSuccess:function(node, data){
			var t = $('#typeId').combotree('tree');      // 得到树对象 
			var n = t.tree('getChildren');          // 得到选择的节点
			f_timeout(data);
		}
	});
	$("#start").hide();
	$("#opinionText").hide();
	$("#xh_editor").val("");
	$('#add').form("clear");
	$("#add").dialog("open").dialog('setTitle', '添加信息');
	$('#oprerate').attr("value", "1");
}


	// 修改
function update() {
	var rows = $('#list_info').datagrid("getSelections");
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
			var info = rows[0];
			$("#typeId").combotree({
				url:'info_getInfoTypeTree.action',
				method:'get',required:true,
				value:info.typeId
			});
			
//			$("#typeId").combotree('setValue', info.typeId);
			$("#sts_id").combobox('setValue',info.rsts);
			
			$("input[name='info.title']").val(info.title);
			$("textarea[name='info.remark']").val(info.remark);
			$("textarea[name='info.content']").val(info.content);
			$("input[name='info.infoId']").val(info.infoId);
			$("input[name='info.seq']").val(info.seq);
			$("input[name='info.crttime']").val(info.crttime);
			$("input[name='info.userId']").val(info.userId);
			$("input[name='info.entId']").val(info.entId);
			$("input[name='info.auditor']").val(info.auditor);
			$("input[name='info.audiDate']").val(info.audiDate);
			$("textarea[name='info.opinion']").val(info.opinion);
			$("input[name='info.sysCode']").val(info.sysCode);
			$("input[name='info.titleImg']").val(info.titleImg);
			if(info.titleImg){
				$("a#prevBtn").attr('href','/nytsyFiles/titleImg/'+info.titleImg);
				$("a#prevBtn").fancybox();
			}
			
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
	var rows = $("#list_info").datagrid("getSelections");
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要删除的记录!', 'info');
		return false;
	} else {
		var temp; // 循环给提交删除参数赋值
		$.each(rows, function(i, n) {
			if (i == 0) {
				temp = n.infoId;
			} else {
				temp += "," + n.infoId;
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
					url : 'info_deleteInfo.action',
					data : 'infoIds='+ temp,
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
function changeCheck(rsts){		
	var arr = $('#list_info').datagrid("getSelections");
	var lg = arr.length;
	
	if(lg==0){
		$.messager.alert('提示','请选择需要操作的记录!','question');
	}else{	
		var infoids;
		for(var i = 0; i < arr.length; i++){
			var info = arr[i];
			if(info.rsts==rsts&&rsts=='1'){
	  			$.messager.show({ title : '提示', msg : '有选项已是通过状态!'});
	  			return;
	  		}else if(info.rsts==rsts&&rsts=='2'){
	  			$.messager.show({ title : '提示', msg : '有选项已是未通过状态!'});
	  			return;
	  		}
			$.each(arr, function(i, n) {
				if (i == 0) {
					infoids = n.infoId;
				} else {
					infoids += "," + n.infoId;
				}
			});
		}
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		$.ajax({
         	  url:'info_updateAuditInfo.action',
         	  data:{ infoIds: infoids, rsts: rsts },
         	  type:'post',
         	  dataType:'text',
         	  success : function(result) {
         		   parent.$.messager.progress('close');
				   $.messager.show({ title : '提示', msg : result});
				   $('#list_info').datagrid("reload");
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

	var rsts = $('#rsts').val();
	var info = {};
	if(title!=""){info["info.title"]=title};
	if(typeId!="" && typeId != null){info["info.typeId"]=typeId};
	if(rsts != "-1") {info["info.rsts"]=rsts};

	dataGrid.datagrid('reload', info); // 点击搜索
}

// 隐藏
function clearForm(){
   $('#add').window('close');
}

function clearSearch(){
	$('#title').val("");
	$('#typeId1').combotree('clear');;
	$('#rsts').val(-1);
	dataGrid.datagrid('reload',{});
}

// 提交表格 - 添加组织机构类型
function submitForm() {
	var titleImg = $('input[name="info.titleImg"]').val();
	
	if(titleImg==''){
		$.messager.show( {
			title : '提示',
			msg : '请上传标题图片，谢谢！'
		});
		return;
	}
	
	
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
		//}
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
