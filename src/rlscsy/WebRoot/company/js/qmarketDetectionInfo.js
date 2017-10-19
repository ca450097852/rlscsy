
var urlType = 1;
$(function(){
	//parent.layer.closeAll();
	
	initProList(1,10);
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'agriInventoryRecord'},
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


var gqmarketDetectionInfoList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['qmarketDetectionInfo.ptbId'] = $('#h_ptbId').val();
	
	$('#proTab tr:not(:first)').remove();
	$.post('qmarketDetectionInfo_findList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gqmarketDetectionInfoList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var qmarketDetectionInfoList = result.rows;
			for(var i=0;i<qmarketDetectionInfoList.length;i++){
				var qmarketDetectionInfo = qmarketDetectionInfoList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
		        	<td>'+qmarketDetectionInfo.marketId+'</td>\
				    <td>'+qmarketDetectionInfo.marketName+'</td>\
			        <td>'+qmarketDetectionInfo.wholesalerId+'</td>\
			        <td>'+qmarketDetectionInfo.wholesalerName+'</td>\
			        <td>'+qmarketDetectionInfo.tranId+'</td>\
			        <td>'+qmarketDetectionInfo.quarantineAnimalProductsId+'</td>\
			        <td>'+qmarketDetectionInfo.inspectionMeatId+'</td>\
			        <td>'+qmarketDetectionInfo.detectionDate+'</td>\
			        <td><a class="tablelink" onclick="updateMarketIn('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteInfo('+qmarketDetectionInfo.qdiId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(qmarketDetectionInfoList.length==0){
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
		    $.post('qmarketDetectionInfo_delete.action',{ids:id},function(result){
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
	var formUrl = 'qmarketDetectionInfo_add.action';
	if(urlType!=1){
		formUrl = 'qmarketDetectionInfo_update.action';
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
	$('.formtitle span').html('添加批发市场检验信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset');
	
}
//修改档案
function updateMarketIn(index){
	var qmarketDetectionInfo = gqmarketDetectionInfoList[index];
	if(qmarketDetectionInfo){
		//addRecord();
		
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset');
		
		urlType = 2;
		
		$('.formtitle span').html('修改批发市场检验信息');
		
		for(var item in qmarketDetectionInfo){
			$('[name="qmarketDetectionInfo.'+item+'"]').val(qmarketDetectionInfo[item]);
		}
		
		$('#_detectionDate').datebox('setValue',qmarketDetectionInfo.detectionDate);
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

function toDetail(miibId){
	window.location.href=$('#basePath').val()+"company/marketDetail_pf.jsp?miibId="+miibId;
}