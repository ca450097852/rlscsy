var dataGrid; // 列表
var mainentid = 0;
var queryParams = {};
$(function() {
	mainentid = $("#mainentid").val();
	if(mainentid>0){
		queryParams["complaint.mainentid"]=mainentid;
	}
	
	
	$('#complaint_detail').window('close');
	$('#add').window('close');
	$('#seachStore').window('close');	
	$('#seachVendor').window('close');
	dataGrid = $('#complaintList').datagrid( {
		url : 'complaint_findComplaintList.action',
		queryParams:queryParams,
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '投诉举报管理',
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
		{field : 'title',title : '标题',width : 150,align : 'center'},
		{field : 'entName',title : '区域',width : 100,align : 'center'},
		{field : 'proName',title : '举报产品',width : 100,align : 'center'},
		{field : 'companyName',title : '举报企业',width : 100,align : 'center'},
		{field : 'userName',title : '举报人',width : 100,align : 'center'}, 
		//{field : 'phone',title : '举报人电话',width : 100,align : 'center'},
		{field : 'crttime',title : '举报时间',width : 100,align : 'center'}, 
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
		{field:'cid',title:'操作',width:150,align:'center',formatter: function(value,row,index){ 
			if (row.sts == 0) {
				return "<a href='javascript:void(0)' onclick='tocheck("+index+",1)'><font color=red>处理</font></a>"+
				"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick='toLook("+index+")'>详细</a>";
			}else{
				return "<a href='javascript:void(0)' onclick='tocheck("+index+",0)'>编辑</a>"+
				"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick='toLook("+index+")'>详细</a>";
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
	
	$("#s_entId").combotree({
		url:'complaint_getEntTree.action',
		method:'get'
	});
	
 parent.$.messager.progress('close');
  					
	});
// 添加 - 信息
function append() {
	
	$("input[name='complaint.mainentid']").val(mainentid);
	$("#entId1").combotree({
		url:'complaint_getEntTree.action',
		method:'get',
		required:true,
		panelHeight: 'auto',
		onLoadSuccess:function(node, data){
			var t = $('#typeId').combotree('tree');      // 得到树对象 
			var n = t.tree('getChildren');          // 得到选择的节点
			if(n==0){
				$(".enterprise_parentId_tr").remove();
			}else{
				f_timeout(data);
			}
		}
	});
	$("#start").hide();
	$("#xh_editor").val("");
	$('#add').form("clear");
	$("#add").dialog("open").dialog('setTitle', '添加信息');
	$('#oprerate').attr("value", "1");
}

//查看
function toLook(i) {
	var complaint = dataGrid.datagrid("getRows")[i]; 
	$("#d_title").html(complaint.title);
	$("#d_entName").html(complaint.entName);
	$("#d_userName").html(complaint.userName);
	$("#d_userAddr").html(complaint.userAddr);
	$("#d_phone").html(complaint.phone);
	$("#d_content").html(complaint.content);
	$("#d_proName").html(complaint.proName);
	$("#d_companyName").html(complaint.companyName);
	
	if(complaint.appName!=''){
		$("#d_appName").html("<a class=\"easyui-linkbutton\" href='complaint_downloadFile.action?cid="+complaint.cid+"'>下载附件</a>");
	}else{
		$("#d_appName").html("无附件");
	}
	//	$("#d_remark").html(complaint.remark);
//	$("#d_opinion").html(complaint.opinion);
	if(complaint.finalResult!=''){
		$("#d_finalResult").html(complaint.finalResult);
	}else if(complaint.remark!=''){
		$("#d_finalResult").html(complaint.remark);
	}
	
	if(complaint.sts==0){
		$("#d_sts").html("未处理");
	}else if(complaint.sts==1){
		$("#d_sts").html("处理中");
	}else if(complaint.sts==2){
		$("#d_sts").html("已处理");
	}
	
	if(complaint.typeNo==1){
		$("#d_typeNo").html("追溯企业信息不符");
	}else if(complaint.typeNo==2){
		$("#d_typeNo").html("追溯产品信息不符");
	}
	
	$('#complaint_detail').window('open').dialog('setTitle', '举报详细');
	$("#add_form").form("load", {});
}

// 处理 j==1,或者 编辑j==0
function tocheck(i,j) {
	var complaint = dataGrid.datagrid("getRows")[i]; 
	$('#oprerate').attr("value", "2");
	//表单赋值
	$("input[name='complaint.cid']").val(complaint.cid);
	$("input[name='complaint.crttime']").val(complaint.crttime);
	$("input[name='complaint.sysCode']").val(complaint.sysCode);
	$("input[name='complaint.appName']").val(complaint.appName);
	$("input[name='complaint.title']").val(complaint.title);
	$("input[name='complaint.proName']").val(complaint.proName);
	$("input[name='complaint.companyName']").val(complaint.companyName);
	$("input[name='complaint.entId']").val(complaint.entId);
	$("input[name='complaint.userName']").val(complaint.userName);

	$("input[name='complaint.phone']").val(complaint.phone);
	$("input[name='complaint.content']").val(complaint.content);
	$("input[name='complaint.sts']").val(complaint.sts);
	$("textarea[name='complaint.remark']").val(complaint.remark);
	
	$("input[name='complaint.mainentid']").val(complaint.mainentid);
	
	//只读字段
	$("#up_title").html(complaint.title);
	$("#up_proName").html(complaint.proName);
	$("#up_companyName").html(complaint.companyName);
	$("#up_entName").html(complaint.entName);
	$("#up_userName").html(complaint.userName);
	$("#up_userAddr").html(complaint.userAddr);
	$("#up_phone").html(complaint.phone);
	$("#up_content").html(complaint.content);

	if(complaint.typeNo==1){
		$("#up_typeNo").html("追溯企业信息不符");
	}else if(complaint.typeNo==2){
		$("#up_typeNo").html("追溯产品信息不符");
	}
	if(j==1){
		$("#gdshow").show();
		$('#add').window('open').dialog('setTitle', '举报处理');
	}else{
		$("#gdshow").hide();
		$('#add').window('open').dialog('setTitle', '举报编辑');
	}
	
//	$("#add_form").form("load", {});
}


// 修改
function update() {
	var rows = $('#complaintList').datagrid("getSelections");
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
			var complaint = rows[0];
			$("#entId1").combotree({
				url:'complaint_getEntTree.action',
				method:'get',required:true,
				value:complaint.entId
			});
			
//			$("#entId1").combotree('setValue', complaint.entId);
			$("#sts").combobox('setValue',complaint.sts);
			
			$("input[name='complaint.title']").val(complaint.title);
			$("input[name='complaint.userName']").val(complaint.userName);
			$("input[name='complaint.phone']").val(complaint.phone);
			$("textarea[name='complaint.content']").val(complaint.content);
			
			$("textarea[name='complaint.remark']").val(complaint.remark);
			$("input[name='complaint.cid']").val(complaint.cid);
			$("input[name='complaint.crttime']").val(complaint.crttime);
			$("input[name='complaint.sysCode']").val(complaint.sysCode);
			$("input[name='complaint.mainentid']").val(complaint.mainentid);
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
	var rows = $("#complaintList").datagrid("getSelections");
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
					url : 'complaint_deleteComplaint.action',
					data : 'cids='+ temp,
					type : 'post',
					dataType : 'text',
					success : function(result) {
						parent.$.messager.progress('close');
						$.messager.show( {
							title : '提示',
							msg : result
						});
						queryParams = {};
						queryParams["complaint.mainentid"]=mainentid;
						dataGrid.datagrid('reload',queryParams);
					}
				});
			}
		});
	}
}

//修改状态
function changeCheck(sts){		
	var arr = $('#complaintList').datagrid("getSelections");
	var lg = arr.length;
	if(lg==0){
		$.messager.alert('提示','请选择需要操作的记录!','question');
	}else if(lg!=1){
		$.messager.alert('提示','对不起，一次只能操作一条信息，请重新选择!','question');
	}else{		    
		var info = arr[0];
		if(info.sts==sts&&sts=='0'){
  			$.messager.show({ title : '提示', msg : '已是使用状态!'});
  			return;
  		}else if(info.sts==sts&&sts=='1'){
  			$.messager.show({ title : '提示', msg : '已是禁用状态!'});
  			return;
  		}
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		$.ajax({
         	  url:'info_updateSts.action',
         	  data:{ ids: info.infoId, sts: sts },
         	  type:'post',
         	  dataType:'text',
         	  success : function(result) {
         		   parent.$.messager.progress('close');
				   $.messager.show({ title : '提示', msg : result});
				   $('#complaintList').datagrid("reload");
			  }    
         });
	}		  													
}

// 搜索
function find(){
	var title = $('#s_title').val();
	var entId = $("#s_entId").combotree('getValue');

	var sts = $('#s_sts').val();

	queryParams = {};
	queryParams["complaint.mainentid"]=mainentid;
	if(title!=""){queryParams["complaint.title"]=title};
	if(entId!="" && entId!=null){queryParams["complaint.entId"]=entId};
	if(sts != "-1") {queryParams["complaint.sts"]=sts};

	dataGrid.datagrid('reload', queryParams); // 点击搜索
}

// 隐藏
function clearForm(){
   $('#add').window('close');
}

//重置
function clearSearch(){
	$('#s_title').val("");
	$('#s_entId').combotree('clear');;
	$('#s_sts').val(-1);
	queryParams = {};
	queryParams["complaint.mainentid"]=mainentid;
	dataGrid.datagrid('reload', complaint);
}

// 提交表格 - 添加组织机构类型
function submitForm() {
	// oprerate=1 --> 添加 ， oprerate=2 -- > 修改
	 $('#xh_editor').val();
	var op = $('#oprerate').attr("value");
	if (op == 1) {
			$('#add_form').form('submit', {
				url : 'complaint_addComplaint.action',
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
				queryParams = {};
				queryParams["complaint.mainentid"]=mainentid;
				dataGrid.datagrid('reload',queryParams);
			}
			});
		//}
	} else {
		   $('#add_form').form('submit', {
			url : 'complaint_updateComplaint.action',
			onSubmit : function(result) {
				return $(this).form('validate');// 对数据进行格式化
			},
			success : function(result) {
				$.messager.show( {
					title : '提示',
					msg : result
				});
				clearSearch();
				$('#add').window('close');
				queryParams = {};
				queryParams["complaint.mainentid"]=mainentid;
				dataGrid.datagrid('reload',queryParams);
				
			}
		});
	}
}
