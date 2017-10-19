var atype;
$(function() {
	
	$('#element_type').tree({
		animate:true,
   	 	checkbox:true,
		data: 
		[
		 {'id':1,'text': '普通','checked':true},
		 {'id':2,'text': '三品一标'},
		 {'id':3,'text': '种植类'},
		 {'id':4,'text': '畜牧类'}
		],
		onLoadSuccess:function(node, data){
			$('.tree-checkbox tree-checkbox1').unbind('click');
		},
		onSelect : function(node){
			var cknodes = $(this).tree("getChecked");
			for(var i = 0 ; i < cknodes.length ; i++){
			$(this).tree("uncheck", cknodes[i].target);
			}
			//再选中改节点
			$(this).tree("check", node.target);
		},
		onBeforeCheck: function(node,checked){
			if(checked){
				var cknodes = $(this).tree("getChecked");
				for(var i = 0 ; i < cknodes.length ; i++){
					$(this).tree("uncheck", cknodes[i].target);
				}
			}
		},
		onCheck: function(node,checked){//勾选事件触发
			var archivesType = node.id;
			atype = archivesType;
			if(archivesType){
				$("#element_tree").tree({
			    	 url:'elementTemplet_findElementTempletTree.action?archivesType='+archivesType+"&tt="+Math.floor(Math.random()*20)+1,
			    	 method:'get',
			    	 animate:true,
			    	 checkbox:true
			     });
			}
		}
		

	});


	
	
});


//保存角色授权
var s = new Array();
function passPower(){
	s = [];
	var nodes = $('#element_tree').tree('getChecked');
	for(var i=0; i<nodes.length; i++){
		s.push(nodes[i].id);
		var tp = $('#element_tree').tree('uncheck',nodes[i].target);
		if($('#element_tree').tree('isLeaf',nodes[i])){
			var tp = $('#element_tree').tree('getParent',nodes[i].target);
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
	$.post('elementTemplet_addElementTemplets.action', 'ids='+s+'&archivesType='+atype, function(result) {
		$.messager.show({ title : '提示', msg : result });
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
	var roots = $("#element_tree").tree("getRoots");
	
	for(var i=0;i<roots.length;i++){
		var node = $("#element_tree").tree('find',roots[i].id);
		$("#element_tree").tree('check',node.target);
	}
}
//反选
function RevSelect(){
	var checkeds = $("#element_tree").tree("getChecked");
	var tmp = new Array();
	for(var i=0;i<checkeds.length;i++){
		tmp.push(checkeds[i].id);
	}
	
	selectAll();
	
	for(var i=0;i<tmp.length;i++){
		var node = $('#element_tree').tree('find', tmp[i]);
		$('#element_tree').tree('uncheck', node.target);
    }
}
