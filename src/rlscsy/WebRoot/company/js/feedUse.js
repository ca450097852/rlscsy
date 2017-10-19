
var urlType = 1;
$(function(){
	parent.layer.closeAll();
	
	initProList(1,10);
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'feedUse'},
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
	$('input[name="feedUse.agriImg"]').val("");
	$('#prev').addClass('disabled');
	$('#a_pre').css({'color':'#808080'});
	
	$('#fileQueue').html('');
}

var gfeedList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['feedUse.recId'] = $('#h_recId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('feedUse_findList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gfeedList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var feedList = result.rows;
			//<td><input type="checkbox" class="proCheck" value="'+pro.proId+'" /></td>\
			for(var i=0;i<feedList.length;i++){
				var feedUse = feedList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
		        	<td>'+feedUse.feedname+'</td>\
				    <td>'+feedUse.purpose+'</td>\
			        <td>'+feedUse.userarea+'</td>\
			        <td>'+feedUse.usedate+'</td>\
				    <td>'+feedUse.usertotal+'</td>\
			        <td>'+feedUse.useman+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        <a class="tablelink" onclick="deletefeed('+feedUse.fuid+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(feedList.length==0){
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
function deletefeed(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('feedUse_deleteFeed.action',{ids:id},function(result){
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
	var formUrl = 'feedUse_addFeedUse.action';
	if(urlType!=1){
		formUrl = 'feedUse_updateFeedUse.action';
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
	$('.formtitle span').html('添加饲料投入品使用记录信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset');
	
	$('#feedUse_feedname').combobox({    
	    url:'agriInput_findAgriInputComboList.action?ids=4'
	});
	
	removeAppd();
	$('#fileQueue').html('');
	
}
//修改档案
function updateRecord(index){
	var feedUse = gfeedList[index];
	if(feedUse){
		//addRecord();
		
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset')
		
		urlType = 2;
		
		$('.formtitle span').html('修改饲料投入品使用记录信息');
		
		$('input[name="feedUse.recId"]').val(feedUse.recId);
		$('input[name="feedUse.feedid"]').val(feedUse.feedid);
		$('input[name="feedUse.crttime"]').val(feedUse.crttime);
		$('input[name="feedUse.feedname"]').val(feedUse.feedname);
		
		$('#feedUse_feedname').combobox({    
		    url:'agriInput_findAgriInputComboList.action?ids=4',
		    value:feedUse.feedname
		});
		
		$('input[name="feedUse.purpose"]').val(feedUse.purpose);
		$('input[name="feedUse.userarea"]').val(feedUse.userarea);
		$('input[name="feedUse.usertotal"]').val(feedUse.usertotal);
		$('input[name="feedUse.useman"]').val(feedUse.useman);
		$('#s_remark').val(feedUse.remark);
		$('#s_usedate').datebox('setValue',feedUse.usedate);
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
