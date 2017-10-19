
var urlType = 1;
$(function(){
	parent.layer.closeAll();
	
	initProList(1,10);
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'gaininfo'},
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
var ggaininfoList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['gaininfo.recId'] = $('#h_recId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('gaininfo_findGaininfoList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			ggaininfoList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var gaininfoList = result.rows;
			for(var i=0;i<gaininfoList.length;i++){
				var gaininfo = gaininfoList[i];
				var cf = gaininfo.certificate;
				var c = gaininfo.ischeck;
				var ischeck = c==1?"自检":c==2?"委托检测":"无";
				var w = gaininfo.checkway;
				var checkway = w==1?"快速检测":w==2?"定量检测":"无";
				
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+gaininfo.harvestDate+'</td>\
			        <td>'+gaininfo.scale+'</td>\
			        <td>'+ischeck+'</td>\
			        <td>'+checkway+'</td>\
			        <td>'+gaininfo.checkresult+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteGaininfo('+gaininfo.giId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(gaininfoList.length==0){
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
function deleteGaininfo(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('gaininfo_deleteGaininfo.action',{ids:id},function(result){
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
	var formUrl = 'gaininfo_addGaininfo.action';
	if(urlType!=1){
		formUrl = 'gaininfo_updateGaininfo.action';
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
	$('.formtitle span').html('添加采摘收获信息');
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
	var gaininfo = ggaininfoList[index];
	if(gaininfo){
		addRecord(true);
		urlType = 2;
		
		$('.formtitle span').html('修改采摘收获信息');
		
		$('input[name="gaininfo.giId"]').val(gaininfo.giId);
		$('input[name="gaininfo.crttime"]').val(gaininfo.crttime);
		$('input[name="gaininfo.recId"]').val(gaininfo.recId);
		
		$('#harvestDate').val(gaininfo.harvestDate);
		//$('#proDate').val(gaininfo.proDate);
		//$('#basearea').val(gaininfo.basearea);		
		//$('#certificate').combobox('setValue',gaininfo.certificate);
		$('#ischeck').combobox('setValue',gaininfo.ischeck);
		$('#checkway').combobox('setValue',gaininfo.checkway);
		
		$('input[name="gaininfo.checkresult"]').val(gaininfo.checkresult);
		$('input[name="gaininfo.scale"]').val(gaininfo.scale);
		
		
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
