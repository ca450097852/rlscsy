
var urlType = 1;
$(function(){
	//parent.layer.closeAll();
	
	initProList(1,10);
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'saleinfo'},
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
var gsaleinfoList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['saleinfo.recId'] = $('#h_recId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('saleinfo_findSaleinfoList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gsaleinfoList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var saleinfoList = result.rows;
			for(var i=0;i<saleinfoList.length;i++){
				var saleinfo = saleinfoList[i];
				
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+saleinfo.listed+'</td>\
			        <td>'+saleinfo.proDate+'</td>\
			        <td>'+saleinfo.saleUser+'</td>\
			        <td>'+saleinfo.salearea+'</td>\
			        <td>'+saleinfo.sgCompany+'</td>\
			        <td>'+saleinfo.sgUser+'</td>\
			        <td>'+saleinfo.tranId+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        <a class="tablelink" onclick="deletesaleinfo('+saleinfo.saleId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(saleinfoList.length==0){
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
function deletesaleinfo(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('saleinfo_deleteSaleinfo.action',{ids:id},function(result){
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
	var formUrl = 'saleinfo_addSaleinfo.action';
	if(urlType!=1){
		formUrl = 'saleinfo_updateSaleinfo.action';
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
	$('.formtitle span').html('添加销售记录');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset');
	//生成交易凭证号
	$.post('meatOutInfoBase_getTranId.action',function(result){
		$("#tranId").val(result);
	},'TEXT');
	if(!flag){
		$('.tools').hide();
	}
}
//修改档案
function updateRecord(index){
	var saleinfo = gsaleinfoList[index];
	if(saleinfo){
		addRecord(true);
		urlType = 2;
		
		$('.formtitle span').html('修改销售记录');
		
		$('input[name="saleinfo.saleId"]').val(saleinfo.saleId);
		$('input[name="saleinfo.crttime"]').val(saleinfo.crttime);
		$('input[name="saleinfo.recId"]').val(saleinfo.recId);
		
		$('input[name="saleinfo.salearea"]').val(saleinfo.salearea);
		$('input[name="saleinfo.sgCompany"]').val(saleinfo.sgCompany);
		$('input[name="saleinfo.sgUser"]').val(saleinfo.sgUser);
		$('input[name="saleinfo.saleUser"]').val(saleinfo.saleUser);
		$('input[name="saleinfo.tranId"]').val(saleinfo.tranId);
		$('#listed').val(saleinfo.listed);
		$('#proDate').val(saleinfo.proDate);

		$('#salearea').val(saleinfo.salearea);
		
		$('#packing').combobox('setValue',saleinfo.packing);
		$('#havetag').combobox('setValue',saleinfo.havetag);
		
		
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
