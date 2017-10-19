
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


var gmarketInInfoBaseList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['marketInInfoBase.ptbId'] = $('#h_ptbId').val();
	condition['marketInInfoBase.flag'] = $('#h_flag').val();
	
	$('#proTab tr:not(:first)').remove();
	$.post('marketInInfoBase_findList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gmarketInInfoBaseList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			var marketInInfoBaseList = result.rows;
			for(var i=0;i<marketInInfoBaseList.length;i++){
				var marketInInfoBase = marketInInfoBaseList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
		        	<td>'+marketInInfoBase.marketId+'</td>\
				    <td>'+marketInInfoBase.marketName+'</td>\
			        <td>'+marketInInfoBase.inDate+'</td>\
			        <td>'+marketInInfoBase.wholesalerId+'</td>\
			        <td>'+marketInInfoBase.wholesalerName+'</td>\
			        <td>'+marketInInfoBase.tranId+'</td>\
			        <td>'+marketInInfoBase.quarantineAnimalProductsId+'</td>\
			        <td>'+marketInInfoBase.inspectionMeatId+'</td>\
			        <td>'+marketInInfoBase.batchId+'</td>\
			        <td><a class="tablelink" onclick="toDetail('+marketInInfoBase.miibId+','+'\''+marketInInfoBase.inDate+'\''+')">明细</a>\
			        <a class="tablelink" onclick="updateMarketIn('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteInfo('+marketInInfoBase.miibId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(marketInInfoBaseList.length==0){
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
		    $.post('marketInInfoBase_delete.action',{ids:id},function(result){
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
	var formUrl = 'marketInInfoBase_add.action';
	if(urlType!=1){
		formUrl = 'marketInInfoBase_update.action';
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
	var marketInInfoBase = gmarketInInfoBaseList[index];
	if(marketInInfoBase){
		//addRecord();
		
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset');
		
		urlType = 2;
		
		$('.formtitle span').html('修改进场基本信息');
		
		for(var item in marketInInfoBase){
			$('[name="marketInInfoBase.'+item+'"]').val(marketInInfoBase[item]);
		}
		
		$('#_inDate').datebox('setValue',marketInInfoBase.inDate);
		$('#_remark').val(marketInInfoBase.remark);
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

function toDetail(miibId,inDate){
	var batchName=$('#h_batchName').val();
	var ptbId=$('#h_ptbId').val();
	window.location.href=$('#basePath').val()+"company/marketDetail_pf.jsp?miibId="+miibId+'&ptbId='+ptbId+'&batchName='+encodeURI(encodeURI(batchName))+'&inDate='+inDate;
}