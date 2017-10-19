var basePath;
var urlType = 1;
var isbatch = 1;

var formUrl = 'proTypeArea_addProTypeArea.action';	

$(function(){
	basePath = $("#basePath").val();
	parent.layer.closeAll();
	
	isbatch = $('#isbatch').val();
	
//	if(isbatch == 1){
//		initProList(1,10);
//	}else{
		initQrcode(1,10);
//	}
	
	 
});
var proTypeList;
function initProList(page,rows){	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['proTypeBatch.entId']=$('#entId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('proTypeBatch_findProTypeBatch.action?tt='+Math.random(),condition,function(result){		
		if(result){		
			proTypeList = result.rows;
			for(var i=0;i<proTypeList.length;i++){
				var proTypeBatch = proTypeList[i];			
				
				var batchState = proTypeBatch.batchState;
				//0待提交；1待审核；2审核通过；3审核不通过
				var option = '无';
				if(batchState == 0){
					batchState = "待提交";
					option = '<a class="tablelink" href="javascript:updateBatchState('+proTypeBatch.ptbId+')">提交审核</a>';
				}else if(batchState == 1){
					batchState = "待审核";
				}else if(batchState == 2){
					batchState = "审核通过";
				}else if(batchState == 3){
					batchState = "审核不通过";
					option = '<a class="tablelink" href="javascript:updateBatchState('+proTypeBatch.ptbId+')">提交审核</a>';
				}
				
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+proTypeBatch.batchName+'</td>\
			        <td>'+proTypeBatch.batchTime+'</td>\
					<td>'+proTypeBatch.dimenno+'</td>\
			        <td>'+batchState+'</td>\
			        <td>'+proTypeBatch.submitTime+'</td>\
			        <td>'+proTypeBatch.auditTime+'</td>\
			        <td>'+option+'</td>\
			        </tr>';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(proTypeList.length==0){
				$('#proTab').append('<tr><td align="center" colspan="'+$('#proTab tr:first').find('th').length+'">暂无数据</td></tr>');
			}						
		}		
	},'JSON');
	
}

function initQrcode(page,rows){	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['entId']=$('#entId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('record_getLoginAuditRecord.action?tt='+Math.random(),condition,function(result){		
		if(result){		
			proTypeList = result;
			for(var i=0;i<proTypeList.length;i++){
				var proTypeBatch = proTypeList[i];			
				
				var type = "品种档案";
				
				if(proTypeBatch.isbatch==1){
					type = "批次档案";
				}
				
				var proState = proTypeBatch.state;
				//0待提交；1待审核；2审核通过；3审核不通过
				var option = '无';
				if(proState == 0){
					proState = "待提交";
					if(proTypeBatch.isbatch==0)
						option = '<a class="tablelink" href="javascript:updateQrcodeState('+proTypeBatch.objId+')">提交审核</a>';
					else
						option = '<a class="tablelink" href="javascript:updateBatchState('+proTypeBatch.objId+')">提交审核</a>';
				}else if(proState == 1){
					proState = "待审核";
				}else if(proState == 2){
					proState = "审核通过";
				}else if(proState == 3){
					proState = "审核不通过";
					if(proTypeBatch.isbatch==0)
						option = '<a class="tablelink" href="javascript:updateQrcodeState('+proTypeBatch.objId+')">提交审核</a>';
					else
						option = '<a class="tablelink" href="javascript:updateBatchState('+proTypeBatch.objId+')">提交审核</a>';
				}
				
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+proTypeBatch.name+'</td>\
			        <td>'+proTypeBatch.typeName+'</td>\
			        <td>'+proTypeBatch.crttime+'</td>\
					<td>'+proTypeBatch.dimenno+'</td>\
			        <td>'+proState+'</td>\
			        <td>'+type+'</td>\
			        <td>'+proTypeBatch.submitTime+'</td>\
			        <td>'+proTypeBatch.auditTime+'</td>\
			        <td>'+option+'</td>\
			        </tr>';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(proTypeList.length==0){
				$('#proTab').append('<tr><td align="center" colspan="'+$('#proTab tr:first').find('th').length+'">暂无数据</td></tr>');
			}						
		}		
	},'JSON');
	
}

function updateBatchState(ptbId){
	
	
	layer.confirm('是否提交审核？', {
	  btn: ['确定','取消'] // 按钮
	}, function(){
		var index = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
		
		$.ajax({
			url : 'proTypeBatch_updateBatchState.action?tt='+Math.random(),
			data : {ptbId:ptbId},
			type : 'post',
			success : function(result){
				layer.close(index);
				layer.msg(result);
				initQrcode(1,10);
			}
		});
		
	});
	
}

function updateQrcodeState(ptqId){
	layer.confirm('是否提交审核？', {
		  btn: ['确定','取消'] // 按钮
		}, function(){
			var index = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
			
			$.ajax({
				url : 'proTypeQrcode_updateQrcodeState.action?tt='+Math.random(),
				data : {ptqId:ptqId},
				type : 'post',
				success : function(result){
					layer.close(index);
					layer.msg(result);
					initQrcode(1,10);
				}
			});
			
		});
}
