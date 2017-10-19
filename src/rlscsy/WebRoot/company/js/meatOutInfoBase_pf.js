
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


var gmeatOutInfoBaseList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['meatOutInfoBase.ptbId'] = $('#h_ptbId').val();
	condition['meatOutInfoBase.flag'] = $('#h_flag').val();
	
	$('#proTab tr:not(:first)').remove();
	$.post('meatOutInfoBase_findList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gmeatOutInfoBaseList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var meatOutInfoBaseList = result.rows;
			for(var i=0;i<meatOutInfoBaseList.length;i++){
				var meatOutInfoBase = meatOutInfoBaseList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
		        	<td>'+meatOutInfoBase.butcherFacId+'</td>\
				    <td>'+meatOutInfoBase.butcherFacName+'</td>\
			        <td>'+meatOutInfoBase.tranDate+'</td>\
			        <td>'+meatOutInfoBase.sellerId+'</td>\
			        <td>'+meatOutInfoBase.sellerName+'</td>\
			        <td>'+meatOutInfoBase.buyerId+'</td>\
			        <td>'+meatOutInfoBase.buyerName+'</td>\
			        <td>'+meatOutInfoBase.dest+'</td>\
			        <td>'+meatOutInfoBase.tranId+'</td>\
			        <td><a class="tablelink" onclick="toDetail('+meatOutInfoBase.moibId+','+'\''+meatOutInfoBase.tranDate+'\''+')">明细</a>\
			        <a class="tablelink" onclick="updateMarketIn('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteInfo('+meatOutInfoBase.moibId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(meatOutInfoBaseList.length==0){
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
		    $.post('meatOutInfoBase_delete.action',{ids:id},function(result){
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
	var formUrl = 'meatOutInfoBase_add.action';
	if(urlType!=1){
		formUrl = 'meatOutInfoBase_update.action';
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
	$('.formtitle span').html('添加批发交易基本信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset');
	//生成交易凭证号
	$.post('meatOutInfoBase_getTranId.action',function(result){
		$("#tranId").val(result);
	},'TEXT');
}
//修改档案
function updateMarketIn(index){
	var meatOutInfoBase = gmeatOutInfoBaseList[index];
	if(meatOutInfoBase){
		//addRecord();
		
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset');
		
		urlType = 2;
		
		$('.formtitle span').html('修改批发交易基本信息');
		
		for(var item in meatOutInfoBase){
			$('[name="meatOutInfoBase.'+item+'"]').val(meatOutInfoBase[item]);
		}
		
		$('#_tranDate').datebox('setValue',meatOutInfoBase.tranDate);
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

function toDetail(moibId,tranDate){
	var batchName=$('#h_batchName').val();
	var ptbId=$('#h_ptbId').val();
	window.location.href=$('#basePath').val()+"company/meatOutInfoDetail_pf.jsp?moibId="+moibId+"&batchName="+encodeURI(encodeURI(batchName))+"&ptbId="+ptbId+"&tranDate="+tranDate;
}