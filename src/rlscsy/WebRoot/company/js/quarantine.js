
var urlType = 1;
$(function(){
	//parent.layer.closeAll();
	
	initProList(1,10);
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'quarantine'},
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


var gquarantineInfoList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['quarantineInfo.ptbId'] = $('#h_ptbId').val();	
	$('#proTab tr:not(:first)').remove();
	$.post('quarantineInfo_findList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gquarantineInfoList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var quarantineInfoList = result.rows;
			for(var i=0;i<quarantineInfoList.length;i++){
				var quarantineInfo = quarantineInfoList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
		        	<td>'+quarantineInfo.butcherFacId+'</td>\
				    <td>'+quarantineInfo.butcherFacName+'</td>\
			        <td>'+quarantineInfo.quarantineAnimalProductsId+'</td>\
			        <td>'+quarantineInfo.sellerId+'</td>\
			        <td>'+quarantineInfo.sellerName+'</td>\
			        <td>'+quarantineInfo.quarantineId+'</td>\
			        <td>'+quarantineInfo.sampleNum+'</td>\
			        <td>'+quarantineInfo.sampleId+'</td>\
			        <td>'+quarantineInfo.checkDate+'</td>\
			        <td><a class="tablelink" onclick="updateMarketIn('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteInfo('+quarantineInfo.qiId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(quarantineInfoList.length==0){
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
function deleteInfo(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('quarantineInfo_delete.action',{ids:id},function(result){
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
	var formUrl = 'quarantineInfo_add.action';
	if(urlType!=1){
		formUrl = 'quarantineInfo_update.action';
	}
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
	$('.formtitle span').html('添加进场基本信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset');
	
}
//修改档案
function updateMarketIn(index){
	var quarantineInfo = gquarantineInfoList[index];
	if(quarantineInfo){
		//addRecord();	
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset');		
		urlType = 2;
		$('.formtitle span').html('修改进场基本信息');	
		for(var item in quarantineInfo){
			$('[name="quarantineInfo.'+item+'"]').val(quarantineInfo[item]);
		}		
		$('#quarantineInfo_checkDate').datebox('setValue',quarantineInfo.checkDate);
		$('#recordForm').form('validate');
	}
	
}

function exitContent(){
	$('#proDiv').show();
	$('.center_content3').hide();
	if(urlType==1){
		$('.tools').show();
	}
}