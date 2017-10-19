 var queryParams="";
 var formUrl = "FindLog_AddLawLog.action";
 var name="1";

$(function() {
	$.ajax({
		url:'companyUser_findCompanyUserPagerList.action',
		type:'post',
		success:function(result){
			var pager=JSON.parse(result);
			var data=[];
			for ( var int = 0; int < pager.total; int++) {
				data.push({"text":pager.rows[int].realName,"value":pager.rows[int].comId});
			}
			
			$("#selqq").combobox("loadData",data); 
			
		}
	});
	
	//上传附件
	 var URL=$("#b_path").val()+'FindLog_logApp.action';	
	 if(navigator.userAgent.indexOf('Firefox') >= 0)
	{
		URL=$("#b_path").val()+'FindLog_logApp.action;jsessionid='+$('#jsessionid').val();
	};
			
		
		$("#uploadify").uploadify({
		'auto'			:true,
		'swf'      		: $("#b_path").val()+'uploadify/uploadify.swf',
		'fileObjName' 	: 'uploadify',
		'uploader' 		: URL,
		'buttonText'    : '上传附件',
		'height'		: 20,
		'width'			: 100,
		'fileSizeLimit'	: 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'multi'			: false,
		'removeCompleted': true,
		'onUploadSuccess' : function(file, data, response) {
			$('#logApp').val(data);
		}
	});
	
		//加载数据
		$('#list_enforceNode').datagrid( {
		url : 'FindLog_findList.action',
		queryParams: queryParams,
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '执法日志列表',
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
		            
					{field : "userName",title : '执法人员',width : 80,align:'left',}, 
					{field : 'entName',title : '执法单位',width : 80,align : 'center',},
					{field : 'lawTime',title : '执法时间',width : 80,align:'center',},
					{field : 'addr',title : '执法地点',width : 80,align:'center',},
					{field : 'comId',title : '被执法企业',width : 80,align:'center',}, 
					{field : 'lawResult',title : '处理结果',width : 100,align : 'center',}, 
					{field : 'createTime',title : '创建时间',width : 60,align : 'center',},
					{field : 'logApp',title : '附件',width : 100,align : 'center',
						formatter: function(value,row,index){
							 var e='<a  href="javascript:void(0)" onclick="showlogApp(\''+value+'\')">'+value+'</a>';
							 return e;
						}

					},
					
					
		]],
	
 	
	});
		
		//监听窗口关闭事件
	    $('#add').window({
	    	onBeforeClose: function () {
            	if($('#logApp').val()!=""){
            		delLogApp();
            	}
            }   
	});
});

	
	//搜索
	function find() {
		var name = $('#e_name').val();
		var endDate=$("#endDate").datebox('getValue');
		var startDate=$("#startDate").datebox('getValue');
		var comId=$('#selqq').combobox('getValue');
			queryParams = {};
			if(name!=''){
				queryParams["enterprise.name"]=name;
			}
			if(startDate!=''){
				queryParams["enterprise.startDate"]=startDate;
			}
			if(endDate!=''){
				queryParams["enterprise.endDate"]=endDate;
			}if(comId!=''){
				queryParams["enterprise.account"]=comId;
			}

	$('#list_enforceNode').datagrid('load', queryParams); // 点击搜索
}

	
	//重置
	function clearSearch(){
		$('#e_name').val("");
		var endDate=$("#endDate").datebox('setValue',"");
		var startDate=$("#startDate").datebox('setValue',"");
		$('#selqq').combobox('setValue',"");
		queryParams = {};
		$('#list_enforceNode').datagrid('load',queryParams);
}
	
	//显示附件app
	function showlogApp(value){
		document.getElementById("imgi").src='http://localhost:8080/nytsyFiles/LawLog/'+value+'';
		$('#img').window('open');	
		
		
	}
	
	
	
	
	//显示添加页面
	function addEnforce(){
		
		$('#add').panel({title: "添加执法日志信息",maximized :true});
		formUrl ="FindLog_AddLawLog.action";
		$.ajax({
			url:'companyUser_findCompanyUserPagerList.action',
			type:'post',
			success:function(result){
				var pager=JSON.parse(result);
				var data=[];
				for ( var int = 0; int < pager.total; int++) {
					data.push({"text":pager.rows[int].realName,"value":pager.rows[int].comId});
				}
				
				$("#selq").combobox("loadData",data); 
				
			}
		});
		//人员显示
		$.ajax({
			url:'Findenforc_findList.action',
			type:'post',
			success:function(result){
				var pager=JSON.parse(result);
				var data=[];
				for ( var int = 0; int < pager.total; int++) {
					data.push({"text":pager.rows[int].userName,"value":pager.rows[int].userId});
				}
				$("#sel").combobox("loadData",data); 
				
			}
		});
		
		$('#add_form').form("reset");
		$('#add').window('open');	
	}
	
	//提交表单
	function submitForm(){
		var name = $('#sel').combobox('getValue'); 
		var name1=$('#sel').combobox('getValue');
	
		if(name=='---请选择---'){
			$.messager.alert('提示', '请选择人员!', 'info');
			return;
		}
		if(name1=='---请选择---'){
			$.messager.alert('提示', '请选择企业!', 'info');
			return;
		}
		if ($('#add_form').form('validate') == false) {
			$.messager.show({title : '提示',msg : '输入验证没有通过!'});
			return;
		}
		
		$.messager.progress( {title : '提示',text : '数据处理中，请稍后....'});
		
		
		$('#add_form').form('submit', {
			url :formUrl,
			onSubmit : function(lawLog) {
				lawLog.userId=name;
				lawLog.comId=name1;
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
			$.messager.alert('提示', '请选择要删除的记录!', 'info');
			return false;
		} else {
			var temp=""; // 循环给提交删除参数赋值
			var uploadifyFileName="";
			$.each(rows, function(i, n) {
					temp += n.userId+",";
					uploadifyFileName += n.logApp+",";
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
						url : 'FindLog_delLawLog.action',
						data : {"ids":temp,"uploadifyFileName":uploadifyFileName},
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
		$('#add').panel({title: "修改执法日志信息",maximized:true});
		
		$.ajax({
			url:'companyUser_findCompanyUserPagerList.action',
			type:'post',
			success:function(result){
				var pager=JSON.parse(result);
				var data=[];
				for ( var int = 0; int <pager.total; int++) {
					data.push({"text":pager.rows[int].realName,"value":pager.rows[int].comId});
				}
				
				$("#selq").combobox("loadData",data); 
				
			}
		});
		$.ajax({
			url:'Findenforc_findList.action',
			type:'post',
			
			success:function(result){
				var pager=JSON.parse(result);
				var data=[];
				for ( var int = 0; int < pager.total; int++) {
					data.push({"text":pager.rows[int].userName,"value":pager.rows[int].userId});
				}
				$("#sel").combobox("loadData",data); 
				
			}
		});
		var rows = $('#list_enforceNode').datagrid("getSelections");
		
		if (!rows || rows.length == 0) {// 判断是否选择行
			$.messager.alert('提示', '请选择要修改的记录!', 'info');
			return false;
		}else if(rows.length>1){
			$.messager.alert('提示', '只能选择一条记录修改!', 'info');
			return false;
		}else{
			formUrl ="FindLog_updateLawLog.action";
			var lawLog = rows[0];
			
			$('#add_form').form("reset");
			$("#add_form").form("load",lawLog);
		
			$('#add').window('open');	
			for(var item in lawLog){
				
				var text = lawLog[item]==null?"":lawLog[item];
				var obj = $("input[name='lawLog."+item+"']");
				if(obj){
					obj.val(text);
				}
				if(item='userId'){
					$('#sel').combobox('setValue',lawLog[item]);
				}
				
				if(item='comId'){
					$('#selq').combobox('select',lawLog[item]);
				}
				if(item='lawTime'){
					$('#time').datebox('setValue',lawLog[item]);
				}
				
				if(item=="logApp"){
					$('#uploadifyFileName').val(text);
				}
				
			
			}
		}
	}
	//关闭
	function clearForm(){
		$('#add').window('close');
	}
	//附件上传
	//删除上传的文件
	function delLogApp (){
			if($('#logApp').val()!=""){
				$.post('FindLog_delLogApp.action', 'uploadifyFileName='+$('#logApp').val(), function(result) {
					$("#logApp").val('');
				});
			}
	}
	
	

