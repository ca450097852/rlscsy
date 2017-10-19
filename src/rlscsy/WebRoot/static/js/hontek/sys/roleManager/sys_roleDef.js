/*默认角色授权*/
$(function() {
	parent.$.messager.progress('close');
});

//授权显示
var typeId;
function empower(id){
	$("#rol_col").tree({
    	 url:'rolePurv_findDefaultColTree.action?typeId='+id+"&tt="+Math.floor(Math.random()*20)+1,
    	 method:'get',
    	 animate:true,
    	 checkbox:true
     });
	typeId = id;
	if(id=='0'){
		$("#rolePower").window("open").window("setTitle","设置默认管理员角色权限");
	}else if(id=='1'){
		$("#rolePower").window("open").window("setTitle","设置默认普通用户权限");		
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
	$.post('rolePurv_saveDefPower.action', 'colIds='+s+'&typeId='+typeId, function(result) {
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

   		