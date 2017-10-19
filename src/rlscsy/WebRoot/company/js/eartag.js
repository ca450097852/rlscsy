
var urlType = 1;
$(function(){
	parent.layer.closeAll();
	initProList(1,10);
});
var geartagList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['eartag.recId'] = $('#h_recId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('eartag_findEartagList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			geartagList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var eartagList = result.rows;
			//<td><input type="checkbox" class="proCheck" value="'+pro.proId+'" /></td>\
			for(var i=0;i<eartagList.length;i++){
				var eartag = eartagList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+eartag.earNo+'</td>\
			        <td>'+eartag.wearTime+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteEartag('+eartag.earId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(eartagList.length==0){
				$('#proTab').append('<tr><td align="center" colspan="'+$('#proTab tr:first').find('th').length+'">暂无数据</td></tr>');
			}
			
			
			//处理分页
			var total = result.total;
			var pagect = parseInt(total/rows)+((total%rows==0)?0:1);
			$('.paginList').html('');
			$('.paginList').append('<li class="paginItem" onclick="initProList(1,10)"><a href="javascript:;"><span class="pagepre"></span></a></li>');
			for(var i=1;i<=pagect;i++){
				var className = "";
				var onclick = 'initProList('+i+',10)';
				if(i==page){
					className = "current";
					onclick = 'javascript:void(0);';
				}
				$('.paginList').append('<li class="paginItem '+className+'"><a href="javascript:'+onclick+';">'+i+'</a></li>');
			}
			$('.paginList').append('<li class="paginItem" onclick="initProList('+pagect+',10)"><a href="javascript:;"><span class="pagenxt"></span></a></li>');
		}
		
	},'JSON');
	
}

//删除
function deleteEartag(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('eartag_deleteEartag.action',{ids:id},function(result){
				layer.msg(result);
				initProList(1,10);
			},'TEXT');
		}, function(){
		    layer.close(index);
		});
	}
}

//提交表单
function submitForm(){
	if($('#recordForm').form('validate')==false){
		return;
	}
	var params = {};
	var formUrl = 'eartag_addEartag.action';
	if(urlType!=1){
		formUrl = 'eartag_updateEartag.action';
	}
	/*$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});*/
	var index = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	$('#recordForm').form('submit', {
		url : formUrl,
		success : function(result) {
			/*$.messager.progress('close');
			$.messager.show({
						title : '提示',
						msg : result
					});*/
			layer.close(index);
			layer.msg(result);
			initProList(1,10);
		}
	});
	
	
}


//添加档案
function addRecord(flag){
	$('.formtitle span').html('添加耳标信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset')
	if(!flag){
		$('.tools').hide();
	}
}
//修改档案
function updateRecord(index){
	var eartag = geartagList[index];
	if(eartag){
		addRecord(true);
		urlType = 2;
		
		$('.formtitle span').html('修改耳标信息');
		
		$('input[name="eartag.earId"]').val(eartag.earId);
		$('input[name="eartag.recId"]').val(eartag.recId);
		$('input[name="eartag.earNo"]').val(eartag.earNo);
		
		
		$('#wearTime').datebox('setValue',eartag.wearTime);
		
		
		$('#recordForm').form('validate')
	}
	
}

function exitContent(){
	$('#proDiv').show();
	$('.center_content3').hide();
	if(urlType==1){
		$('.tools').show();
	}
}
