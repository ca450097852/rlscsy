
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


var gmarketInInfoDetailList;
function initProList(page,rows){
	goHref();
	$('#proDiv').show();
	$('.center_content3').hide();
	
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['marketInInfoDetail.miibId'] = $('#h_miibId').val();
	
	$('#proTab tr:not(:first)').remove();
	$.post('marketInInfoDetail_findList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gmarketInInfoDetailList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var marketInInfoDetailList = result.rows;
			for(var i=0;i<marketInInfoDetailList.length;i++){
				var marketInInfoDetail = marketInInfoDetailList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
		        	<td>'+marketInInfoDetail.goodsCode+'</td>\
				    <td>'+marketInInfoDetail.goodsName+'</td>\
			        <td>'+marketInInfoDetail.weight+'</td>\
			        <td>'+marketInInfoDetail.areaOriginId+'</td>\
			        <td>'+marketInInfoDetail.areaOriginName+'</td>\
			        <td>'+marketInInfoDetail.baseName+'</td>\
			        <td>'+marketInInfoDetail.wsSupplierId+'</td>\
			        <td>'+marketInInfoDetail.wsSupplierName+'</td>\
			        <td>'+marketInInfoDetail.saleTranId+'</td>\
			        <td><a class="tablelink" onclick="updateMarketIn('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteInfo('+marketInInfoDetail.miidId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(marketInInfoDetailList.length==0){
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

function goHref(){
	var batchName=$('#batchName').val();
	var ptbId=$('#ptbId').val();
	var comType = $("#comType").val();
	if(comType==2){
		$("#goPro").attr("href","marketIn_pf.jsp?ptbId="+ptbId+'&batchName='+encodeURI(encodeURI(batchName))); 
	}
	if(comType==3){
		$("#goPro").attr("href","marketIn_ls.jsp?ptbId="+ptbId+'&batchName='+encodeURI(encodeURI(batchName))); 
	}
	if(comType==4){
		$("#goPro").attr("href","marketIn_cs.jsp?ptbId="+ptbId+'&batchName='+encodeURI(encodeURI(batchName))); 
	}
	
}

//删除
function deleteInfo(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('marketInInfoDetail_delete.action',{ids:id},function(result){
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
	var formUrl = 'marketInInfoDetail_add.action';
	if(urlType!=1){
		formUrl = 'marketInInfoDetail_update.action';
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
	$('.formtitle span').html('添加进场明细基本信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset');
	
}
//修改档案
function updateMarketIn(index){
	var marketInInfoDetail = gmarketInInfoDetailList[index];
	if(marketInInfoDetail){
		//addRecord();
		
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset');
		
		urlType = 2;
		
		$('.formtitle span').html('修改进场明细基本信息');
		
		for(var item in marketInInfoDetail){
			$('[name="marketInInfoDetail.'+item+'"]').val(marketInInfoDetail[item]);
		}
		
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