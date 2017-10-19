var dataGrid;

$(function() {
 $('#add').window('close');
 dataGrid = $('#mydatagrid').datagrid({  
      title : '角色管理',  
      iconCls : 'icon-group',  
      pageSize : 15,//默认选择的分页是每页5行数据  
	  pageList : [ 10, 15,20, 30, 50 ],// 可以选择的分页集合
      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
      striped : true,//设置为true将交替显示行背景。  
      //collapsible : true,//显示可折叠按钮  
      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
      url:'role_findRoleList.action',//url调用Action方法  
      loadMsg : '数据装载中......',  
      fit:true,
      //singleSelect:true,
      remoteSort : false,  
      pagination : true,//分页  
      rownumbers : true,//行数  
      frozenColumns : [ [ {  
           field : 'ck',  
           checkbox : true  
      } ] ],
      columns:[[
			{field:'account',title:'所属机构',width:200,align:'center'},
			{field:'roleName',title:'角色名称',width:200,align:'center'},
			{field:'sts',title:'状态',width:100,align:'center',formatter: function(value,row,index){
				if(value==0){
					return "待审";
				}else if(value == 1){
					return "<font color=green>发布</font>";
				}else{
					return "<font color=red>作废</font>";
				}
				
			}},
			{field:'level',title:'级别',width:100,align:'center'},
			{field:'desc',title:'角色描述',width:200,align:'center'},
	      ]],
	      onLoadSuccess:function(data){
		 	f_timeout(data);
	 		},
	 		onClickRow: function(rowIndex, rowData){
	 			$(this).datagrid('unselectAll');
	 			$(this).datagrid('selectRow',rowIndex);
	 		}
	      });   
 parent.$.messager.progress('close');
 
//显示 - 组织机构
$("#parentId").combotree({
	url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
	method:'get',
	panelWidth: '200'
});
 
});

// 添加
function append(){
	$('#userlist_update').hide();
	$('#userlist_reset').show();
	$('#userlist_sub').show();
	
	$("#role_entid").combotree({
		url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
		method:'get',required:true,
		onLoadSuccess:function(node, data){
		if(data){
			if(data[0].checked){
				$("#role_entid").combotree("setValue",data[0].id);
			}
		}
		f_timeout(data);	
		
		}
	});
	
	$("#add").dialog("open").dialog('setTitle', '新增角色');
	$('#add_form').form("clear");
	$("#rl_sts").combobox('setValue',1);
	
	$("input[name='role.roleName']").removeAttr('readonly');
	$("input[name='role.roleName']").validatebox('enableValidation');
}


// 修改
function update(){
	//$.messager.alert('提示','请选择需要修改的用户!','question');
$('#userlist_update').show();
$('#userlist_sub').hide();
$('#userlist_reset').hide();
var arr = $('#mydatagrid').datagrid("getSelections");
var lg = arr.length;
//			alert(lg);
if(lg==0){
	$.messager.alert('提示','请选择需要修改的用户!','question');
}else if(lg!=1){
	$.messager.alert('提示','对不起，一次只能修改一个用户，请重新选择!','question');
}else{
	var role = arr[0];
	
	$("input[name='role.roleId']").val(role.roleId);
	
	$("#role_entid").combotree({
		url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
		method:'get',required:true,
		value:role.entId
	});
	//$("#role_entid").combotree('setValue', role.entId);
	
	$("input[name='role.roleName']").val(role.roleName);
	$("input[name='role.roleName']").attr('readonly','true');
	$("input[name='role.roleName']").validatebox('disableValidation');	
	$("#rl_sts").combobox('setValue',role.sts);
	$("#role_level").combobox('setValue',role.level);
	$("textarea[name='role.desc']").val(role.desc);
	
	$("add_form").form('validate');
	$("#add").window("open").window('setTitle', '修改角色');
	}
}


function submitForm(){
   $('#add_form').form('submit', {
	url : 'role_addRole.action',
	onSubmit : function(result) { 
	    return $(this).form('validate');//对数据进行格式化
	},
	success : function(result) {
		$.messager.show({ title : '提示', msg : result });
		$('#add').window('close');
		$('#mydatagrid').datagrid("reload");
		}
	});

	
}
function clearForm(){
	$('#add_form').form("clear");
}


function updateRole(){
	var colId = $("#colId").val();
	$('#add_form').form('submit', {
	url : 'role_updateRole.action?colId='+colId,
	onSubmit : function(result) { 
	    return $(this).form('validate');//对数据进行格式化
	},
	success : function(result) {
		$.messager.show({ title : '提示', msg : result });
		$('#add').window('close');
		$('#mydatagrid').datagrid("reload");
		}
	});
}


function tb_search(){
	var r_roleName = $("#r_roleName").val();
	var r_sts = $("#r_sts").val();
	var ent_id = $("#parentId").combotree('getValue');
	var cod ={};
	if(r_roleName!=""){
	cod["role.roleName"]= r_roleName;
	}
	if(r_sts!=-1){
		cod["role.sts"]= r_sts;
	}
	if(ent_id!=""){
		cod["role.entId"]= ent_id;
	}
	$('#mydatagrid').datagrid('load',cod);
}

function tb_reset(){
	$("#r_roleName").val('');
	$("#r_sts").val('-1');
	$("#parentId").combotree('setValue','');

	$('#mydatagrid').datagrid('load',{});
}

//授权显示
var getPower;
var roleId;
var entId;
function empower(){
	var arr = $('#mydatagrid').datagrid("getSelections");
	var lg = arr.length;
	if(lg==0){
		$.messager.alert('提示','请选择授权角色!','question');
	}else if(lg!=1){
		$.messager.alert('提示','对不起，一次只能授权一个角色。','question');
	}else{
	var role = arr[0];
	roleId = role.roleId;
	entId = role.entId;
	
	$("#rol_col").tree({
    	 url:'rolePurv_findColTree.action?roleId='+roleId+"&tt="+Math.floor(Math.random()*20)+1,
    	 method:'get',
    	 animate:true,
    	 checkbox:true
     });
	$("#rolePower").window("open");
	}
}

//保存角色授权
var s = new Array();
function passPower(){
	s = [];
	var nodes = $('#rol_col').tree('getChecked');
	for(var i=0; i<nodes.length; i++){
		s.push(nodes[i].id);
		var tp = $('#rol_col').tree('uncheck',nodes[i].target);
		if($('#rol_col').tree('isLeaf',nodes[i])){
			var tp = $('#rol_col').tree('getParent',nodes[i].target);
			if(tp!=null){
				if(!tp.checked){
					//alert(checkIsEx(tp.id));
					if(checkIsEx(tp.id)){
						s.push(tp.id);
					}
				}
			}
		}
		
	}
	if(s.length==0){
		return;
	}
	$.post('rolePurv_savePower.action', 'colIds='+s+'&entId='+entId+'&roleId='+roleId, function(result) {
		$.messager.show({ title : '提示', msg : result });
		$("#rolePower").window("close");
	}, "TEXT");
}

function checkIsEx(cid){
	for(var i=0;i<s.length;i++){
		if(s[i]==cid){
			return false;
		}
	}
	return true;
}

//全选
function selectAll(){
	var roots = $("#rol_col").tree("getRoots");
for(var i=0;i<roots.length;i++){
	var node = $("#rol_col").tree('find',roots[i].id);
	$("#rol_col").tree('check',node.target);
	}
}
//反选
function RevSelect(){
	var checkeds = $("#rol_col").tree("getChecked");
var tmp = new Array();
for(var i=0;i<checkeds.length;i++){
	tmp.push(checkeds[i].id);
}
selectAll();
for(var i=0;i<tmp.length;i++){
	var node = $('#rol_col').tree('find', tmp[i]);
	$('#rol_col').tree('uncheck', node.target);
    }
}
/**
 * 更改角色状态
 * type=1 发布
 * type=2 作废
 */
function changeSts(type){
	var arr = $('#mydatagrid').datagrid("getSelections");
	var arrall = new Array();
	var lg = arr.length;
	if(lg==0){
		$.messager.alert('提示','请选要操作的对象!','question');
	}else{
		for(var i=0;i<lg;i++){
			arrall.push(arr[i].roleId)
		}
		$.messager.confirm('提示', '是否要继续执行?', function(r){
			if(r){
				var url = '';
				if(type=='2'){
					url = 'role_disableRole.action';
				}else if(type=='1'){
					url = 'role_enableRole.action';
				}
				$.post(url, 'roleIds='+arrall.join(",")+"&role.sts="+type, function(result) {
					$.messager.show({ title : '提示', msg : result });
					$('#mydatagrid').datagrid("reload");
				}, "TEXT");
			}
			}
		)
	}
}
   		