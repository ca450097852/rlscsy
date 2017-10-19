
var urlType = 1;
$(function(){
	parent.layer.closeAll();
	
	initProList(1,10);
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'xiujianchucao'},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result&&result.length==1){
				var useguide = result[0];					
				var html = '<font>'+useguide.title+'</font>'+useguide.contents;
				$('#useguide').html(html);
			}
		}
	});
	
});

function removeAppd(){
	$('input[name="agriInput.agriImg"]').val("");
	$('#prev').addClass('disabled');
	$('#a_pre').css({'color':'#808080'});
	
	$('#fileQueue').html('');
}

var gagriInputList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['xiujianchucao.recId'] = $('#h_recId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('xiujianchucao_findList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gagriInputList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var agriInputList = result.rows;
			//<td><input type="checkbox" class="proCheck" value="'+pro.proId+'" /></td>\
			for(var i=0;i<agriInputList.length;i++){
				var agriInput = agriInputList[i];
				var type = agriInput.dotype==1?"修剪":"除草";
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
		        	<td>'+agriInput.dodate+'</td>\
				    <td>'+type+'</td>\
			        <td>'+agriInput.dodesc+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteAgriInput('+agriInput.giid+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(agriInputList.length==0){
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
function deleteAgriInput(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('xiujianchucao_deleteXiujianchucao.action',{ids:id},function(result){
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
	var formUrl = 'xiujianchucao_addXiujianchucao.action';
	if(urlType!=1){
		formUrl = 'xiujianchucao_updateXiujianchucao.action';
	}
	/*$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});*/
	var index = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	$('#recordForm').form('submit', {
		url : formUrl,
		success : function(result) {
			layer.close(index);
			layer.msg(result);
			initProList(1,10);
		}
	});
	
	
}


//添加档案
function addRecord(flag){
	if(!flag){
		$('.tools').hide();
	}
	$('.formtitle span').html('添加田间管理信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset');
	
	removeAppd();
	$('#fileQueue').html('');
	
}
//修改档案
function updateRecord(index){
	var agriInput = gagriInputList[index];
	if(agriInput){
		//addRecord();
		
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset')
		
		urlType = 2;
		
		$('.formtitle span').html('修改田间管理信息');
		
		/*for(var item in agriInput){
			$('input[name="agriInventory.'+item+'"]').val(agriInput[item]);
		}*/
		$('input[name="xiujianchucao.giid"]').val(agriInput.giid);
		$('#s_dodate').datebox('setValue',agriInput.dodate);
		$('#s_dotype').combobox('setValue',agriInput.dotype);
		$('#s_dodesc').val(agriInput.dodesc);
	}
	
}

function exitContent(){
	$('#proDiv').show();
	$('.center_content3').hide();
	if(urlType==1){
		$('.tools').show();
	}
}
//档案分类变化
function recordTypeChange(val){
	if(val==1||val==2){
		$('#proType').hide();
		$('#typeId').combotree('setValue','');
		$('#typeId').combotree({'required':false});
	}else{
		$('#proType').show();
		$('#typeId').combotree({'required':true});
	}
}
