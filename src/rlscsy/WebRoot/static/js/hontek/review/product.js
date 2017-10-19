var visfrom ;
var lh_entId ;

$(function() {
	visfrom = $('#visfrom').val();
	lh_entId = $('#lh_entId').val();
	
	$('#addProduct').window('close'); // close a window

	$('#product_detail').window('close');
	
	if(visfrom!=2){
		$('#s_entId').combobox({
						url:'ent_getEntListJson.action?jsonType=0',
						required:true, 
						valueField:'id',    
	    				textField:'text'
				});
	}
	/*$("#pro_type").combotree({
		url : 'proType_getProTypeTreeToPro.action?tt='
				+ Math.floor(Math.random() * 20) + 1,
		method : 'get',
		required : true,
		panelHeight : 'auto'
	});

	$("#pro_type_1").combotree({
				url : 'proType_getProTypeTreeToPro.action',
				method : 'get',
				panelHeight : 'auto'
			});*/
	$('#pro_type').combotree({
					url:'proType_getProTypeTreeToPro.action',
					required:true
			});
	
	$('#pro_type_1').combotree({
					url:'proType_getProTypeTreeToPro.action'
			});

	// 表格数据
	$('#productdatagrid').datagrid({
		title : '产品信息管理',
		iconCls : 'icon-ok',
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [10, 20, 50,100],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		// collapsible : true,//显示可折叠按钮
		toolbar : "#tb",// 在添加 增添、删除、修改操作的按钮要用到这个
		url : 'pro_findProductList.action',// url调用Action方法
		loadMsg : '数据装载中......',
		fit : true,
		// singleSelect:true,//为true时只能选择单行
		fitColumns : true,// 允许表格自动缩放，以适应父容器
		// sortName : 'purchNo',//当数据表格初始化时以哪一列来排序
		// sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		frozenColumns : [[{
					field : 'ck',
					checkbox : true
				}]],
		columns : [[{
					field : 'proCode',
					title : '产品编码',
					width : 80,
					align : 'center'
				}, {
					field : 'proName',
					title : '产品名称',
					width : 100,
					align : 'center'
				}, {
					field : 'typeName',
					title : '分类',
					width : 100,
					align : 'center'
				},

				{
					field : 'entName',
					title : '所属企业',
					width : 100,
					align : 'center'
				}, {
					field : 'state',
					title : '状态',
					width : 80,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == 0) {
							return "待审";
						} else if (value == 1) {
							return "生产中";
						} else {
							return "已停产";
						}
					}
				}, {
					field : 'state11',
					title : '操作',
					width : 80,
					align : 'center',
					formatter : function(value, row, index) {
						return "<a href='javascript:void(0)' onclick='showDetail("
								+ index
								+ ")'>查看明细</a>&nbsp;&nbsp;"
								+ "<a href='javascript:void(0)' onclick='proAppendix("
								+ row.proId + ")'>产品图片管理</a>";
					}
				}

		]],
		pagination : true,// 分页
		rownumbers : true// 行数
		,
		onLoadSuccess : function(data) {
			f_timeout(data);
		},
		onClickRow : function(rowIndex, rowData) {
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow', rowIndex);
		}
	});

	$('#proAppList').datagrid({
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [10, 15, 20, 25],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		// toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
		url : 'proApp_findProAppList.action',// url调用Action方法
		loadMsg : '数据装载中......',
		fit : true,
		fitColumns : true,// 允许表格自动缩放，以适应父容器
		remoteSort : false,
		/*
		 * frozenColumns : [ [ { field : 'ck', checkbox : true } ] ],
		 */
		columns : [[{
					field : 'appName',
					title : '附件名称',
					width : 80,
					align : 'center'
				}, {
					field : 'uploadTime',
					title : '上传时间',
					width : 80,
					align : 'center'
				}, {
					field : 'remark',
					title : '备注',
					width : 100,
					align : 'center'
				}, {
					field : 'orderby',
					title : '排序号',
					width : 80,
					align : 'center'
				}, {
					field : 'path',
					title : '操作',
					width : 80,
					align : 'center',
					formatter : function(value, row, index) {
						// alert("<a href=\"javascript:void(0)\"
						// onclick=\"showImg('"+path+"')\">预览</a>&nbsp;&nbsp;&nbsp;<a
						// href='javascript:deleteApp("+row.appId+")'>删除</a>");
						return "<a href=\"javascript:void(0)\" onclick=\"showImg('"
								+ value
								+ "')\">预览</a>&nbsp;&nbsp;&nbsp;<a href='javascript:deleteApp("
								+ row.appId + ")'>删除</a>";
					}
				}]],
		pagination : true,// 分页
		rownumbers : true// 行数
		,
		onLoadSuccess : function(data) {
			f_timeout(data);
		}
	});
});

function showDetail(index) {
	var row = $('#productdatagrid').datagrid("getRows")[index];
	$('#product_detail').window('open');

	$("#d_typeName").html(row.typeName);
	$("#d_proNo").html(row.proCode);

	$("#d_proName").html(row.proName);
	$("#d_barcode").html(row.barcode);
	$("#d_sizeAttr").html(row.sizeAttr);

	$("#d_dimenno").html(row.dimenno);
	$("#d_proDesc").html(row.proDesc);
	$("#d_unit").html(row.unit);
	$("#d_sourceAddr").html(row.sourceAddr);
	$("#d_remark").html(row.remark);

	$("#d_manufacturer").html(row.manufacturer);
	$("#d_distributor").html(row.distributor);
	$("#d_distributorAddr").html(row.distributorAddr);
	
	$("#d_retain").html(row.retain);
	$("#d_storageConditions").html(row.storageConditions);
	$("#d_shelfLife").html(row.shelfLife);

	$("#d_sourceTel").html(row.sourceTel);
	$("#d_distributorTel").html(row.distributorTel);
	$("#d_codeImg").attr('src', '/nytsyFiles/qrcode/' + row.codeImg);

	var tmp = row.state == 0 ? "待审" : row.state == 1 ? "生产中" : "已停产";
	$("#d_state").html(tmp);
}

// 添加
function appendProType() {
	$('#product_form').form("clear");

	if(visfrom!=2){
		$('#s_entId').combobox('reload','ent_getEntListJson.action?jsonType=0');
		$('#s_entId').combobox('enable');
	}else{
		$("#ventId").val(lh_entId);
	}
	$('#addProduct').window('open').dialog('setTitle', '添加产品信息');
	
	$('#method').val("add");
	$('#pro_state').combobox('setValue', 1);

	$("#product_unit").inputTip(); // 调用inputTip方法

}

// 删除
function updateProTypeState(sts) {
	// getSelections
	var arr = $('#productdatagrid').datagrid("getSelections");
	if (arr.length == 0) {
		$.messager.alert('提示', '请选择要操作的产品信息!', 'question');
		return;
	}
	var obj = new Array();
	for (i = 0; i < arr.length; i++) {
		obj.push(arr[i].proId);
	}
	var ids = obj.join(',');
	$.messager.confirm('提示', '确定要继续操作?', function(r) {
				if (r) {
					$.ajax({
								url : 'pro_updateProductState.action',
								data : 'ids=' + ids + '&state=' + sts,
								type : 'post',
								dataType : 'text',
								success : function(result) {
									parent.$.messager.progress('close');
									$.messager.show({
												title : '提示',
												msg : result
											});
									$('#productdatagrid').datagrid("reload");
								}
							});
				}
			});
}

// 打开修改窗口
function updateProType() {
	$('#product_form').form("clear");
	$('#method').val("update");
	var arr = $('#productdatagrid').datagrid("getSelections");
	var lg = arr.length;
	if (lg == 0) {
		$.messager.alert('提示', '请选择需要修改的信息!', 'question');
	} else if (lg != 1) {
		$.messager.alert('提示', '对不起，一次只能修改一个产品信息，请重新选择!', 'question');
	} else {

		var product = arr[0];

		// $("input[name='product.typeId']").val(product.typeId);
		// $('#pro_type').combobox('setValues', product.typeId);
		if(visfrom!=2)
			$('#s_entId').combobox('reload','ent_getEntListJson.action?jsonType=1');
		
		
		$("input[name='product.proCode']").val(product.proCode);

		$("input[name='product.proName']").val(product.proName);
		$("input[name='product.barcode']").val(product.barcode);
		$("input[name='product.sizeAttr']").val(product.sizeAttr);

		$("input[name='product.proId']").val(product.proId);
		$("input[name='product.entId']").val(product.entId);

		$("input[name='product.sourceTel']").val(product.sourceTel);
		$("input[name='product.distributorTel']").val(product.distributorTel);

		$("input[name='product.dimenno']").val(product.dimenno);
		$("#prodesc").val(product.proDesc);
		$("input[name='product.unit']").val(product.unit);
		$("input[name='product.sourceAddr']").val(product.sourceAddr);
		$("#p_remark").val(product.remark);

		$('#pro_state').combobox('setValue', product.state);
		// alert(product.tbProType.typeName);
		$('#pro_type').combotree('setValue', product.typeId);

		$("input[name='product.manufacturer']").val(product.manufacturer);
		$("input[name='product.distributor']").val(product.distributor);
		$("input[name='product.distributorAddr']").val(product.distributorAddr);
		
		
		$("input[name='product.retain']").val(product.retain);
		$("input[name='product.storageConditions']").val(product.storageConditions);
		$("input[name='product.shelfLife']").val(product.shelfLife);
		
		if(visfrom!=2){
			$('#s_entId').combobox("setValue",product.entId);
			$('#s_entId').combobox('disable');
		}
		
		$('#product_form').form("validate");
		$('#addProduct').window('open').dialog('setTitle', '修改产品信息');
		$("#in_value").html($("#hiden_value").html());
	}
}

// 提交form
function submitProTypeForm() {
	if ($('#product_form').form('validate') == false) {
		$.messager.show({
					title : '提示',
					msg : '验证没有通过!'
				});
		return;
	}
	if ($("#product_unit").val() == $("#product_unit").attr("placeholder")) {
		$("#product_unit").val('');
	}

	parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
	var method = $('#method').val();
	if (method == 'add') {// 执行添加
		$('#product_form').form('submit', {
					url : 'pro_addProduct.action',
					onSubmit : function(result) {
						return $(this).form('validate');// 对数据进行格式化
					},
					success : function(result) {
						parent.$.messager.progress('close');
						$.messager.show({
									title : '提示',
									msg : result
								});
						$('#addProduct').window('close');
						$('#productdatagrid').datagrid("reload");
					}
				});
	} else if (method == 'update') {// 执行修改
		$('#product_form').form('submit', {
					url : 'pro_updateProduct.action',
					onSubmit : function(result) {
						return $(this).form('validate');// 对数据进行格式化
					},
					success : function(result) {
						parent.$.messager.progress('close');
						$.messager.show({
									title : '提示',
									msg : result
								});
						$('#addProduct').window('close');
						$('#productdatagrid').datagrid("reload");

						$("#in_value").html("");
					}
				});
	}
	// 隐藏添加窗口
	$('#addPurchase').window('close'); // close a window
}

function tb_search() {
	var productName = $("#productName").val();
	var pro_type = $("#pro_type_1").combotree('getValue');
	var cod = {};
	if (productName != "") {
		cod["product.proName"] = productName;
	}
	if (pro_type != "") {
		cod["product.typeId"] = pro_type;
	}
	$('#productdatagrid').datagrid('load', cod);
}
function tb_reset() {
	$('#myseach').form("clear");
	// $("#actType").val(-1);
}

function removerProduct() {
	var arr = $('#productdatagrid').datagrid("getSelections");
	if (arr.length == 0) {
		$.messager.alert('提示', '请选择要操作的产品信息!', 'question');
		return;
	}
	var obj = new Array();
	for (i = 0; i < arr.length; i++) {
		obj.push(arr[i].proId);
	}
	var ids = obj.join(',');
	$.messager.confirm('提示', '确定要继续操作?', function(r) {
				if (r) {
					$.ajax({
								url : 'pro_deleteProduct.action',
								data : 'ids=' + ids,
								type : 'post',
								dataType : 'text',
								success : function(result) {
									parent.$.messager.progress('close');
									$.messager.show({
												title : '提示',
												msg : result
											});
									$('#productdatagrid').datagrid("reload");
								}
							});
				}
			});

}

function proAppendix(proId) {
	$('#app_proId').val(proId);
	$('#appMamager').window('open');
	var cod = {};
	cod['productAppend.proId'] = proId;
	$('#proAppList').datagrid('load', cod);

	$("#uploadify").uploadify({
		'swf' : 'uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'proApp_uploadFile.action;jsessionid='
				+ $('#sessionId').val(),
		// 'formData' : {'zid':zid},
		'buttonText' : '上传附件',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc' : '支持格式:jpg/gif/jpeg/png',
		'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID' : 'fileQueue',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			$('#uploadify').uploadify('disable', true);
			$("#h_file").html("<input type='text' class='path' id='hp_"
					+ file.id + "' value=" + data + ">"
					+ "<input type='text' class='appName' id='ha_" + file.id
					+ "' value=" + file.name + ">");
		}
	});
}
function removeAppd(fileID) {
	$('#hp_' + fileID).remove();
	$('#ha_' + fileID).remove();
	$('#uploadify').uploadify('disable', false);
}

function submitAppForm() {
	var path = $("#h_file input[class='path']").eq(0).val();
	var appName = $("#h_file input[class='appName']").eq(0).val();
	var remark = $('#appRemark').val();
	var proId = $('#app_proId').val();
	var orderby = $('#app_orderby').val();

	if (!path) {
		$.messager.show({
					title : '提示',
					msg : '请选择上传图片'
				});
		return;
	}

	var cod = {};
	cod['productAppend.proId'] = proId;
	cod['productAppend.appName'] = appName;
	cod['productAppend.path'] = path;
	cod['productAppend.orderby'] = orderby;
	cod['productAppend.remark'] = remark;
	$.post('proApp_addProAppendix.action', cod, function(rst) {
				$.messager.show({
							title : '提示',
							msg : rst
						});
				$('#proAppList').datagrid("reload");
				$("#h_file").html("");
				$('#appRemark').val("");
				$('#uploadify').uploadify('disable', false);
				$('#fileQueue').html("");
				$('#app_orderby').val(5);
			}, 'TEXT');
}

function showImg(path) {
	window.open($("#h_path").val()
							+ '/jsp/company/preview.jsp?imgPath=proimg/' + path,
					'图片预览',
					'height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no')
}

function deleteApp(appId) {
	$.messager.confirm('提示', '确定要继续操作?', function(r) {
				if (r) {
					$.post('proApp_deleteApps.action', 'ids=' + appId,
							function(rst) {
								$('#proAppList').datagrid("reload");
							}, 'TEXT');
				}
			});
}