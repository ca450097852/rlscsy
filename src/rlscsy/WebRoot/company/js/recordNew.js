var GData;
var Gadd = [];//新增的分类
var Gdell = [];//要删除的已创建分类
var gflag = true;
$(function(){
	parent.layer.closeAll();
	init();
});


function init(){
	$('#my_tree').tree({
		url:'proTypeQrcode_getEntTypeTree.action',
		//checkbox:true,
		onLoadSuccess:function(node, data){
			if(gflag){
				GData = data;
				gflag = false;
			}
		},
		onDblClick:function(node){
			deleteSelect();
		}
	});
	
	$('#type_tree').tree({
		url:'proType_getProTypeTreeToPro.action',
		checkbox:true,
		onBeforeSelect:function(node){
//			var flag = $('#type_tree').tree('isLeaf',node.target);
//			if(!flag){
//				layer.msg('请选子节点，谢谢！');
//				return false;
//			}
		},
		onLoadSuccess:function(node, data){
			$('.tree-folder').next().remove();
		},
		onDblClick:function(node){
			//addSelect();
		}
	});
}

//删除已建产品分类
function deleteSelect(){
	var node = $('#my_tree').tree('getSelected');
	
	if(!node){
		layer.msg('请选择要删除的产品分类');
		return;
	}
	
	if(node.attributes){
		Gdell.push(node.attributes);
	}
	for(var i=0;i<Gadd.length;i++){
		var d = Gadd[i];
		if(!node.attributes && d==node.id){
			Gadd.splice(i, 1);
			i--;
		}
	}
	
	$('#my_tree').tree('remove',node.target);
	
	if(Gadd.length==0&&Gdell.length==0){
		$('#saveBtn').linkbutton({disabled:true});
	}else{
		$('#saveBtn').linkbutton({disabled:false});
	}
	
}
//添加已选分类
function addSelect(){
	var nodes = $('#type_tree').tree('getChecked');
	if(nodes.length==0){
		layer.msg('请选择要添加的产品分类');
		return;
	}
	//判断是否存在 
//	var nodes = $('#my_tree').tree('getRoots');
//	for(var i=0;i<nodes.length;i++){
//		var d = nodes[i];
//		if(d.id==node.id){
//			layer.msg('该产品分类已选择');
//			return ;
//		}
//	}
	
	var content = '<div style="padding:5px;" class="setProName">'+
					'<table class="tablelist"><tr><th>产品名称设置</th><th>是否批次</th></tr>';
	
	for(var i=0;i<nodes.length;i++){
		var node = nodes[i];
		content += '<tr><td><input name="'+node.id+'" value="'+node.text+'" class="text"/></td>'+
					'<td> <input type="radio" value="0" name="isbatch_'+node.id+'" />否  &nbsp; <input type="radio" value="1" name="isbatch_'+node.id+'" checked="checked"/>是 </td></tr>';
	}
	
	content += '</table></div>';
	
	layer.open({
	  type: 1, //page层
	  area: ['500px', '300px'],
	  btn: ['保存','取消'],
	  title: '设置产品名称',
	  shade: 0.6, //遮罩透明度
	  moveType: 1, //拖拽风格，0是默认，1是传统拖动
	  shift: -1, //0-6的动画形式，-1不开启
	  content: content,
	  yes:function(){
		 
		 var inputs = $('.setProName input');
		 for(var i=0;i<inputs.length;i++){
			 var input = inputs[i];
			 if($(input).val()==''){
				 layer.tips('产品名称不能为空', input);
				 return;
			 }
		 }
		 
		 $('.setProName .text').each(function(){
			 var id = $(this).attr('name');
			 
			 var isbatch = $('input[name="isbatch_'+id+'"][type="radio"]:checked').val();
			 
			 //id += '@-@'+isbatch;
			 
			 $('#my_tree').tree('append',{data:[{id:id,text:$(this).val()}]});
			 Gadd.push($(this).attr('name'));
		 });
		 
		 if(Gadd.length==0&&Gdell.length==0){
			$('#saveBtn').linkbutton({disabled:true});
		 }else{
			$('#saveBtn').linkbutton({disabled:false});
		 }
		 
		 layer.closeAll();
	  }
	});
	
	
	/*for(var i=0;i<nodes.length;i++){
		var node = nodes[i];
		
		$('#my_tree').tree('append',{data:[{id:node.id,text:node.text}]});
		Gadd.push(node.id);
		
		if(Gadd.length==0&&Gdell.length==0){
			$('#saveBtn').linkbutton({disabled:true});
		}else{
			$('#saveBtn').linkbutton({disabled:false});
		}
	}*/
	
	
}


function saveSelect(){
	if(Gadd.length==0&&Gdell.length==0){
		return;
	}
	
	var nodes = $('#my_tree').tree('getRoots');
	
	var ids = new Array();
	for(var i=0;i<nodes.length;i++){
		var node = nodes[i];
		if(!node.attributes){
			ids.push(node.id+'@-@'+node.text);
		}
	}
	
	var index = layer.load(1, {
	    shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	
	
	$.post('record_addOrDelRecord.action',{'ids':ids.join('@--@'),'dels':Gdell.join(',')},function(result){
		layer.msg(result);
		Gadd = [];
		Gdell=[];
		gflag = true;
		$('#my_tree').tree({
			url:'proTypeQrcode_getEntTypeTree.action',
			//checkbox:true,
			onLoadSuccess:function(node, data){
				layer.close(index);
				if(gflag){
					GData = data;
					gflag = false;
				}
				$('#saveBtn').linkbutton({disabled:true});
			}
		});
	},'TEXT');
	
}


function toSearch(){
	var typeName = $('#s_typeName').val();
	
	if(typeName==''){
		return;
	}
	
	$.post('proType_getProTypeTreeByName.action',{typeName:typeName},function(result){
		$('#type_tree').tree('loadData',result);
		if(result.length==0){
			$('#nodata').show();
		}
	},'JSON')
	
}

function toReset(){
	$('#type_tree').tree('reload');
	$('#s_typeName').val('');
	$('#nodata').hide();
}