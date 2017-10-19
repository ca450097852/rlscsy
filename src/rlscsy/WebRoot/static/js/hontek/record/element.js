var dataGrid; // 列表
$(function() {
	
	$('#add').window('close');
	dataGrid = $('#list_element').datagrid( {
		url : 'element_findElementList.action',
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '档案要素管理',
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
		{field : 'elementName',title : '要素名称',width : 250,align : 'center'}, 
		{field : 'tableName',title : '要素表名',width : 250,align : 'center'},
		{field : 'seq',title : '排序',width : 100,align : 'center'}, 
		{field : 'remark',title : '备注',width : 250,align : 'center'}
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
 	
 		$("#uploadifyLogo").uploadify({
			'swf'      		: $("#b_path").val()+'uploadify/uploadify.swf',
			'fileObjName' 	: 'fileQueue',
			'uploader' 		: 'element_uploadFile.action;jsessionid='+$('#jsessionid').val(),
			'buttonText'    : '上传附件',
			'height'		: 20,
			'width'			: 100,
			'fileSizeLimit'	: 1024,
			'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
	        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
			'queueID'       : 'fileQueueLogo',
			'multi'			: false,
			'removeCompleted' : false,		
			'onUploadSuccess' : function(file, data, response) {
				$("#fileQueueLogo").html('');
				var itemTemplate ='';
				itemTemplate+= '<div id="d_logo" class="uploadify-queue-item">\<div class="cancel">';				
				itemTemplate+= '\<span class="fileName">'+file.name+'</span>&nbsp;&nbsp;<span class="data"  style=\'float:right\'>';
				itemTemplate+= '<a href="javascript:void(0)" onclick="removeIcon(\''+data+'\')" title=\'删除\'>X</a>&nbsp;&nbsp;<a href=\'javascript:void(0);\' onclick=\'previewIcon("'+data+'");\' style=\'background: url("'+$("#b_path").val()+'uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'></a>';			
				itemTemplate+= '</span>\</div></div>';
				$("#fileQueueLogo").html(itemTemplate);
				$("#elementIcon").val(data);
			}
		});
	
	});
// 添加 - 信息
function append() {
	
	$('#add').form("clear");
	$('#seq').numberbox('setValue', 5);

	$("#add").dialog("open").dialog('setTitle', '添加档案要素');
	$('#oprerate').attr("value", "1");
}


	// 修改
function update() {
	$("#fileQueueLogo").html("");
	var rows = $('#list_element').datagrid("getSelections");
	var leng = rows.length;
	if (leng == 0) {
		$.messager.alert('提示', '请选择你需要修改的记录!', 'info');
	} else {
		if (leng > 1) {
			$.messager.alert('提示', '只能修改一条记录!', 'info');
			return false;
		} else {
			$('#oprerate').attr("value", "2");// 修改
			var element = rows[0];
			
			$("input[name='element.elementId']").val(element.elementId);
			$("input[name='element.elementName']").val(element.elementName);
			$("input[name='element.tableName']").val(element.tableName);
			$("input[name='element.elementIcon']").val(element.elementIcon);
			$("input[name='element.elementUrl']").val(element.elementUrl);
			
			if(element.elementIcon!=''){
				var itemTemplate ='';
				itemTemplate+= '<div id="d_logo" class="uploadify-queue-item">\<div class="cancel">';				
				itemTemplate+= '\<span class="fileName">'+element.elementIcon+'</span>&nbsp;&nbsp;<span class="data">';
				itemTemplate+= '<a href="javascript:void(0)"  style=\'float:right\' onclick="removeIcon(\''+element.elementIcon+'\')" title=\'删除\'>X</a>&nbsp;&nbsp;<a href=\'javascript:void(0);\' onclick=\'previewIcon("'+element.elementIcon+'");\' style=\'background: url("'+$("#b_path").val()+'uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'><img src=\'uploadify/da.jpg\'/></a>';			
				itemTemplate+= '\</span>\</div>\</div>';
				
				$("#fileQueueLogo").html(itemTemplate);
			}
			
			$('#seq').numberbox('setValue', element.seq);
			$("textarea[name='element.remark']").val(element.remark);
			
			$('#add').window('open').dialog('setTitle', '修改档案要素');
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
	var rows = $("#list_element").datagrid("getSelections");
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要删除的记录!', 'info');
		return false;
	} else {
		var temp; // 循环给提交删除参数赋值
		$.each(rows, function(i, n) {
			if (i == 0) {
				temp = n.elementId;
			} else {
				temp += "," + n.elementId;
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
					url : 'element_deleteElement.action',
					data : 'elementIds='+ temp,
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
	var elementName = $('#elementName').attr("value");
	var element = {};
	if(elementName!=""){element["element.elementName"]=elementName};
	dataGrid.datagrid('reload', element); // 点击搜索
}

// 隐藏
function clearForm(){
   $('#add').window('close');
}

function clearSearch(){
	$('#elementName').val("");
	dataGrid.datagrid('reload',{});
}

// 提交表格 - 添加组织机构类型
function submitForm() {
	// oprerate=1 --> 添加 ， oprerate=2 -- > 修改
	var op = $('#oprerate').attr("value");
	if (op == 1) {
			$('#add_form').form('submit', {
				url : 'element_addElement.action',
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
			url : 'element_updateElement.action',
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


/**
 * 预览窗口
 * @param path
 * @return
 */
function previewIcon(path){
	window.open ($("#h_path").val()+'/jsp/company/preview.jsp?imgPath=element/'+path,'图片预览','height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no')
}

function removeIcon (path){
	
	$.post('element_deleteFile.action', 'fileQueueFileName='+path, function(result) {
		$("#d_logo").remove();
		$("#elementIcon").val('');
	}, "TEXT");
}

