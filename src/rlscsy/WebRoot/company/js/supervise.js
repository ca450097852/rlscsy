//操作类型 0 添加 1 修改
var optType = 0;
$(function(){
	initProList(1,10);
});
var gproList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['supervise.entId']=$('#h_entId').val();
	$.post('supervise_findSupervisePagerList.action?tt='+Math.random(),condition,function(result){
		if(result){
			
			$('#proTab tr:not(:first)').remove();
			
			gproList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var superviseList = result.rows;
			//<td><input type="checkbox" class="proCheck" value="'+pro.proId+'" /></td>\
			for(var i=0;i<superviseList.length;i++){
				var supervise = superviseList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+supervise.title+'</td>\
			        <td>'+supervise.supTime+'</td>\
			        <td>'+supervise.contents+'</td>\
			        <td><a class="tablelink" onclick="updateSupervise('+supervise.supId+');">'+(supervise.companyTime==""?"查看":"查看")+'</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(superviseList.length==0){
				$('#proTab').append('<tr><td align="center" colspan="'+$('#proTab tr:first').find('th').length+'">暂无数据</td></tr>');
			}
			
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
	
	parent.layer.closeAll();
	
}


var GsupId;
//回复
function updateSupervise(supId){
	
	GsupId = supId;
	
	$('#proDiv').hide();
	$('.center_content3').show();
	
	$('#supCompanyContents').val('');
	
	for(var i=0;i<gproList.length;i++){
		var supervise = gproList[i];
		if(supervise.supId==supId){
			$('#supTitle').html(supervise.title);
			$('#supTime').html(supervise.supTime);
			
			$('#supContents').html(supervise.contents);
			
			/*if(supervise.companyTime!=""){
				$('#supCompanyContents').val(supervise.companyContents);
				$('#supCompanyContents').attr("disabled",true);
				$('#subBut').hide();
			}else{
				$('#supCompanyContents').attr("disabled",false);
				$('#subBut').show();
			}*/
			
			break;
		}
	}
}
//企业回复
function companyRep(){
	
	if($('#supCompanyContents').val()==''){
		layer.msg('请输入回复内容');
		return;
	}
	
	for(var i=0;i<gproList.length;i++){
		var supervise = gproList[i];
		if(supervise.supId==GsupId){
			
			var params = {};
			
			for(var item in supervise){
				console.log(item);
				params['supervise.'+item] = supervise[item];
			}
			
			params['supervise.companyContents'] = $('#supCompanyContents').val();
			
			$.post('supervise_updateSupervise.action',params,function(result){
				layer.msg(result);
				initProList(1,10);
			},'TEXT');
			
			
			
			break;
		}
	}
}

function exitContent(){
	$('#proDiv').show();
	$('.center_content3').hide();
}
