var dataGrid; // 列表
var entIdStr;
var queryParams = {};
var formUrl = "company_addCompany.action";

var flag;

$(function() {
	
    $('#tt').tree({
		data: [{    
		    "id":0,    
		    "text":"流通节点",    
		    "iconCls":"icon-save",    
		    "children":[{    
		    	"id":1,    
		        "text":"屠宰企业"
		    },{    
		    	"id":2,    
		        "text":"批发企业"
		    },{    
		    	"id":3,    
		        "text":"菜市场"
		    },{    
		    	"id":4,    
		        "text":"超市"
		    },{    
		    	"id":5,    
		        "text":"团体消费单位"
		    },{    
		    	"id":6,    
		        "text":"其他"
		    }]    		
		}],
		onClick: function(node){
			flag = node.id;
			find();
		} 
	});
	
	
	
	entIdStr = $("#entIdStr").val();
	
	queryParams['company.parentId']='0';
	dataGrid = $('#list_companyNode').datagrid( {
		url : 'company_findCompanyPagerList.action',
		queryParams: queryParams,
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '流通节点信息列表',
		iconCls : 'icon-house',
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
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}] ],
		columns : [[ 
					{field : 'name',title : '节点名称',width : 120,halign:'left'}, 
					{field : 'flag',title : '节点类型',width : 80,align : 'center',
						formatter : function(value, row, index) {
							return getFlag(value);
						}
					},
					{field : 'linkMan',title : '联系人',width : 80,align:'center'}, 
					{field : 'phone',title : '手机号码',width : 100,align : 'center'}, 
					{field : 'regTime',title : '注册时间',width : 120,align : 'center'}, 
					{field : 'state',title : '状态',width : 60,align : 'center',
						formatter : function(value, row, index) {
							return getState(value);
						}
					},
					{field : 'comId',title : '操作',width :90,align : 'center',
						formatter : function(value, row, index) {
							var e = "<a href='javascript:void(0)' onclick='showDetail("+index+")'>查看详细</a>&nbsp;&nbsp;&nbsp;&nbsp;";
							return e;
						}
					}
		]],
		onLoadSuccess:function(data){
		 	f_timeout(data);
	 	},
 		onClickRow: function(rowIndex, rowData){
 			$(this).datagrid('unselectAll');
 			$(this).datagrid('selectRow',rowIndex);
 		}
	});
	 
	

	$("#uploadify").uploadify({
		'swf' : 'uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'company_uploadFile.action;jsessionid='+ $('#sessionId').val(),
		'buttonText' : '上传图片',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID' : 'fileQueue',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			if(data){
				var filePath = "/nytsyFiles/company/";
				$('input[name="company.licenseImg"]').val(data);
				var img_html = "<a rel=\"previewImg\" href=\""+filePath+data+"\"><img alt=\"预览\" src=\""+filePath+data+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
				$("#fileQueue").html(img_html);
				$("a[rel=previewImg]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}
		}
	});
	
	 
});

//设置状态
function setState(num){
	var stopname ='';
	if(num==1){
		stopname = '确定要设为使用选中节点吗?';
	}else if(num==2){
		stopname = '确定要暂停使用选中节点吗?';
	}
	var rows = dataGrid.datagrid('getSelections');
	var leng = rows.length;
	if (leng == 0) {
		$.messager.alert('提示', '请选择你需要操作的记录!', 'info');
	}else{
		var temp; // 循环给提交删除参数赋值
		$.each(rows, function(i, n) {
			if (i == 0) {
				temp = n.comId;
			} else {
				temp += "," + n.comId;
			}
		});
		$.messager.confirm('提示',stopname, function(r) {
			if (!r) {
				return;
			} else {
				$.messager.progress( {
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.ajax( {
					url : 'company_updateCompanyState.action',
					data : {"ids":temp,"state":num},
					type : 'post',
					dataType : 'text',
					success : function(result) {
						$.messager.progress('close');
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

function nodeHtml(num){
	if(num==1){
		$('input[name="company.nodeAnCom"]').val("y");
		$(".nodeAnCom").show();
	}else{
		$('input[name="company.nodeAnCom"]').val("");
		
		$('input[name="company.account"]').val("");
		$('input[name="company.password"]').val("");
		$("#nature").val("");
		$("#comType").combobox('setValue','');
		$("#licenseImg").val("");
		$("#introduction").val("");
		$(".nodeAnCom").hide();
	}
}


//添加
function append() {
	formUrl ="company_addCompany.action";
	$('#add_form').form("reset");
	
	$(".nodeAnCom").hide();//默认只添加节点数据；
	$(".ifupdate").show();
	$('input[name="company.nodeAnCom"]').val("");
	
	$("input[name='company.parentId']").val(0);
	$("input[name='company.entId']").val(entIdStr);//
	
	
	$("#area").combotree({
		url:'ent_getEntAreaTree2.action',
		method:'get',
		required:true,
		onLoadSuccess:function(node, data){			
			f_timeout(data);			
		}
	});
	
	$("#add").dialog("open").dialog('setTitle', '添加节点信息').dialog("maximize");
}

// 修改
function update() {
	
	var rows = dataGrid.datagrid('getSelections');
	var leng = rows.length;
	if (leng == 0) {
		$.messager.alert('提示', '请选择你需要修改的记录!', 'info');
	}else if (leng >1) {
		$.messager.alert('提示', '只能选择一条记录进行修改!', 'info');
	}else{
		formUrl ="company_updateCompany.action";
		var company = rows[0];
		
		$('#add_form').form("reset");
		$("#add_form").form("load",company);
		
		$(".nodeAnCom").hide();//只修改节点数据；
		$(".ifupdate").hide();//只修改节点数据；
		
		
		for(var item in company){
			var text = company[item]==null?"":company[item];
			var obj = $("input[name='company."+item+"']");
			if(obj){
				obj.val(text);
			}
			if(item=='state'||item=='flag'||item=='nature'||item=='comType'){
				$("#"+item).combobox('setValue',text);
			}
			if(item=='introduction'){
				$("#"+item).val(text);
			}
			if(item=='recordDate'){
				$("#"+item).datebox('setValue',text);
			}
		}
		

		if(company.licenseImg){
			var fpath = "/nytsyFiles/company/";
			$('input[name="company.licenseImg"]').val(company.licenseImg);
			var img_html = "<a rel=\"previewImg\" href=\""+fpath+company.licenseImg+"\"><img alt=\"预览\" src=\""+fpath+company.licenseImg+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
			$("#fileQueue").html(img_html);
			$("a[rel=previewImg]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
		}
		
		$("#area").combotree({
			url:'ent_getEntAreaTree2.action',
			method:'get',
			required:true,
			value:company.area
		});
		
		$("#add").dialog("open").dialog('setTitle', '修改节点信息').dialog("maximize");
		
	}
}


//去掉html标签
function delHtmlTag(str){
	  return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
}
// 隐藏
function clearForm(id) {
	if(id==1){
		$('#add').window('close');
	}else{
		$('#show').window('close');
	}
}

// 搜索
function find() {
	var name = $('#s_name').val();
	//var flag = $('#s_flag').combobox('getValue');
	var state = $('#s_state').combobox('getValue');
	
	queryParams = {};
	queryParams['company.parentId']='0';
	
	if(name!=''){
		queryParams["company.name"]=name;
	}
	if(flag!=''){
		queryParams["company.flag"]=flag;
	}
	if(state!=''){
		queryParams["company.state"]=state;
	}

	dataGrid.datagrid('load', queryParams); // 点击搜索
}

//重置
function clearSearch(){
	$('#s_name').val("");
	//$('#s_flag').combobox('setValue','');
	$('#s_state').combobox('setValue','');
	
	queryParams = {};
	queryParams['company.parentId']='0';
	
	dataGrid.datagrid('load',queryParams);
}

function removeit(){
	var rows = dataGrid.datagrid("getSelections");
	if (!rows || rows.length == 0) {// 判断是否选择行
		$.messager.alert('提示', '请选择要删除的记录!', 'info');
		return false;
	} else {
		var temp; // 循环给提交删除参数赋值
		$.each(rows, function(i, n) {
			if (i == 0) {
				temp = n.comId;
			} else {
				temp += "," + n.comId;
			}
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
					url : 'company_deleteCompany.action',
					data : {"ids":temp},
					type : 'post',
					dataType : 'text',
					success : function(result) {
						$.messager.progress('close');
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




//提交表格
function submitForm() {
	
	var name = $("input[name='company.name']").val();
	var nodeAnCom = $('input[name="company.nodeAnCom"]').val();
	var comType = $("#comType").combobox("getValue");
	if(name==''){
		$.messager.alert('提示', '请输入节点名称!', 'info');
		return;
	}
	
	if(nodeAnCom!=''&&comType==''){
		$.messager.alert('提示', '请选择经营者类型!', 'info');
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
			return $(this).form('validate');//对数据进行格式化
		},
		success : function(result) {
			$.messager.progress('close');
			$.messager.show( {title : '提示',msg : result});
			
			dataGrid.datagrid('reload');
			$('#add_form').form("reset");
			closeWin('add');
		}
	});
}



/**
 * 详细
 */
function showDetail(index){
	var company = dataGrid.datagrid("getRows")[index];
	if(company){
		for(var item in company){
			var obj = $("#d_"+item);
			var text = company[item]==null?"":company[item];
			if(obj){
				if(item=='state'){
					obj.html(getState(text));
				}else if(item=='flag'){
					obj.html(getFlag(text));
				}else if(item=='nature'){
					obj.html(getNature(text));
				}else if(item=='comType'){
					obj.html(getComType(text));
				}else{
					obj.html(text);
				}
			}
		}
		

		if(company.licenseImg){
			var fpath = "/nytsyFiles/company/";
			var img_html = "<a rel=\"previewImg\" href=\""+fpath+company.licenseImg+"\"><img alt=\"预览\" src=\""+fpath+company.licenseImg+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
			$("#d_fileQueue").html(img_html);
			$("a[rel=previewImg]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
		}
		
		$("#showWin").dialog("open").dialog('setTitle', '查看节点信息');
	}
}

//获取状态
function getState(value){
	var res = '无';
	if(value=='1'){
		res = '使用';
	}else if(value=='2'){
		res = '停用';
	}
	return res;
}

//获取节点类型
function getFlag(value){
	var res = '无';//1代表屠宰企业、2代表批发企业、3代表菜市场、4代表超市、5代表团体消费单位、6代表其他
	if(value=='1'){
		res = '屠宰企业';
	}else if(value=='2'){
		res = '批发企业';
	}else if(value=='3'){
		res = '菜市场';
	}else if(value=='4'){
		res = '超市';
	}else if(value=='5'){
		res = '团体消费单位';
	}else if(value=='6'){
		res = '其他';
	}
	return res;
}

//企业性质
function getNature(value){
	var res = '无';
	if(value=='1'){
		res = '企业';
	}else if(value=='2'){
		res = '个体户';
	}
	return res;
}

//经营者类型
function getComType(value){
	var res = '无';//主要分生猪批发商、肉类蔬菜批发商、肉类蔬菜零售商、配送企业、其他等类型
	if(value=='1'){
		res = '生猪批发商';
	}else if(value=='2'){
		res = '肉菜批发商';
	}else if(value=='3'){
		res = '肉菜零售商';
	}else if(value=='4'){
		res = '配送企业';
	}else if(value=='5'){
		res = '其他';
	}
	return res;
}
