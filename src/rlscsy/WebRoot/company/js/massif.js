var basePath;
var urlType = 1;
var ptaId; 

var formUrl = 'massif_addMassif.action';	

$(function(){
	basePath = $("#basePath").val();
	ptaId = $("#ptaId").val();
	
	parent.layer.closeAll();
	
	initProList(1,50);
	 
	$("#maImg").uploadify({
		'swf' : '../uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'elementApp_uploadFile.action;jsessionid='	+ $('#sessionId').val(),
		'buttonText' : '上传附件',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc' : '支持格式:jpg/gif/jpeg/png',
		'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID' : 'fileQueue',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			$('input[name="massif.maImg"]').val(data);
			$('#maImg').uploadify('disable', true);
			$('#prev').removeClass('disabled');
			$('#a_pre').css({'color':'white'});
			$("a#a_pre").attr('href','/nytsyFiles/element/'+data);
			$("a#a_pre").fancybox();
		}
	});
	
});

var proTypeList;
function initProList(page,rows){	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['ptaId']=ptaId;
	$('#proTab tr:not(:first)').remove();
	$.post('massif_findMassifList.action?tt='+Math.random(),condition,function(result){		
		if(result){		
			proTypeList = result.rows;
			for(var i=0;i<proTypeList.length;i++){
				var massif = proTypeList[i];			
				var maId = "id_"+massif.maId;
				var stateStr = massif.state;
				
				var maImg = "";
				if(massif.maImg!=""){
					maImg = '<a rel="'+maId+'" href="/nytsyFiles/element/'+massif.maImg+'"><img width="50" height=50" src="/nytsyFiles/element/'+massif.maImg+'"/></a>'
				}
				
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+massif.maName+'</td>\
					<td>'+massif.maAcreage+'</td>\
			        <td>'+massif.typeName+'</td>\
					<td>'+massif.startTime+'</td>\
					<td>'+massif.getTime+'</td>\
					<td>'+stateStr+'</td>\
			        <td>'+maImg+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="tablelink" onclick="deleteMa('+massif.maId+')">删除</a>\
			        </td>\
			        </tr>';
			        
			     $('#proTab').append(content);
			     $("a[rel="+maId+"]").fancybox();     
			}
			
			//没有数据
			if(proTypeList.length==0){
				$('#proTab').append('<tr><td align="center" colspan="'+$('#proTab tr:first').find('th').length+'">暂无数据</td></tr>');
			}						
		}		
	},'JSON');
	
}

//添加档案
function addRecord(){
	$('.formtitle span').html('添加信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset')
	
	$('#fileQueue').html("");
		
	$('#ptqId').combobox({    
		url : 'proTypeQrcode_getLoginProType.action',
	    valueField:'value',    
	    textField:'text'   
	});  
	
	formUrl = 'massif_addMassif.action';	
	
	if(!flag){
		$('.tools').hide();
	}
}


//删除
function deleteMa(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
			$.ajax( {
				url : 'massif_deleteMassif.action',
				data : 'ids='+ id,
				type : 'post',
				dataType : 'text',
				success : function(result) {
					layer.msg(result);
					initProList(1,50);
				}
			});
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
	var index = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	$('#recordForm').form('submit', {
		url : formUrl,
		success : function(result) {
			layer.close(index);
			layer.msg(result);
			initProList(1,50);
		}
	});
	
	
}


//修改档案
function updateRecord(index){
	var massif = proTypeList[index];
	if(massif){
		
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset')
		
		$('.formtitle span').html('修改信息');
		
		$('#maId').val(massif.maId);
		
		$('#ptqId').combobox({    
			url : 'proTypeQrcode_getLoginProType.action',
		    valueField:'value',    
		    textField:'text',
		    value:massif.ptqId
		});  
		
		formUrl = 'massif_updateMassif.action';		

		$('#maName').val(massif.maName);		
		$('#maAcreage').val(massif.maAcreage);		
		
		$('#ptaId').val(massif.ptaId);		
		$('#startTime').datebox('setValue',massif.startTime);		
		$('#getTime').datebox('setValue',massif.getTime);
		$('#state').val(massif.state);
		$("input[name='massif.maImg']").val(massif.maImg);
		
		if(massif.maImg!=''){
			var content = '<div class="uploadify-queue-item" id="SWFUpload_0_0"><div class="uploadcancel">\
							<a href="javascript:removeAppd();">X</a>\
							</div>\
							<span class="fileName">'+massif.maImg+'</span>\
							</div>';
			$('#fileQueue').html(content);
			
			$('#prev').removeClass('disabled');
			$('#a_pre').css({'color':'white'});
			$("a#a_pre").attr('href','/nytsyFiles/element/'+massif.maImg);
			$("a#a_pre").fancybox();
			
			$('#maImg').uploadify('disable', true);
			
		}

		$('#recordForm').form('validate')
	}
	
}

function removeAppd(){
	$('#fileQueue').html("");
	$("input[name='massif.maImg']").val("");
	$('#maImg').uploadify('disable', false);
	$("a#a_pre").attr('href','');


}

function exitContent(){
	$('#proDiv').show();
	$('.center_content3').hide();
		
}
