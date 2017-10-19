
var urlType = 1;
$(function(){
	parent.layer.closeAll();
	
	$("#agriImg").uploadify({
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
			$('input[name="agriInput.agriImg"]').val(data);
			$('#agriImg').uploadify('disable', true);
			$('#prev').removeClass('disabled');
			$('#a_pre').css({'color':'white'});
			$("a#a_pre").attr('href','/nytsyFiles/proimg/'+data);
			$("a#a_pre").fancybox();
		}
	});
	
	initProList(1,10);
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'agriInput'},
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
	$('input[name="agriInput.agriImg"]').val("");
	$('#agriImg').uploadify('disable', false);
	$('#prev').addClass('disabled');
	$('#a_pre').css({'color':'#808080'});
	
	$('#fileQueue').html('');
}


function checkName(){
	var flag = true;
	var condition = {};
	var agriInput_agriName = $('#agriInput_agriName').val()
	condition['agriDis.agName'] = agriInput_agriName;
	$.ajax({
		url : 'agriDis_findAgriDisList.action',
		data : condition,
		type : 'post',
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result) {
				var total = result.total;
				if (total && parseInt(total) > 0) {
					layer.msg("【" + agriInput_agriName + "】为禁用投入品！");
					flag = false;
				}
			}
		}
	});
	return flag;
}

var gagriInputList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['agriInput.recId'] = $('#h_recId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('agriInput_findAgriInputList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gagriInputList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var agriInputList = result.rows;
			//<td><input type="checkbox" class="proCheck" value="'+pro.proId+'" /></td>\
			for(var i=0;i<agriInputList.length;i++){
				var agriInput = agriInputList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
		        	<td>'+agriInput.typeName+'</td>\
				    <td>'+agriInput.agriName+'</td>\
			        <td>'+agriInput.agriCompany+'</td>\
			        <td>'+agriInput.buyUser+'</td>\
			        <td>'+agriInput.buyAddr+'</td>\
			        <td>'+agriInput.buyDate+'</td>\
			        <td>'+agriInput.buyNum+agriInput.buyUnit+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteAgriInput('+agriInput.agriId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(agriInputList.length==0){
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
function deleteAgriInput(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('agriInput_deleteAgriInput.action',{ids:id},function(result){
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
		
	if(!checkName()){
		return;
	}
	
	var data1 = $('input[name="agriInput.agriImg"]').val();
	if(data1==''){
		layer.msg('必须上传外包装图片或说明书等图片!');
		return;
	}
	
	var params = {};
	var formUrl = 'agriInput_addAgriInput.action';
	if(urlType!=1){
		formUrl = 'agriInput_updateAgriInput.action';
	}
	/*$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});*/
	var index = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	$('#recordForm').form('submit', {
		url : formUrl,
		success : function(result) {
			/*$.messager.progress('close');
			$.messager.show({
						title : '提示',
						msg : result
					});*/
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
	$('.formtitle span').html('添加投入品购买记录');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset');
	
	$('#typeId').combobox({    
	    url:'agriType_findAgriTypeComboList.action'
	});
	
	removeAppd();
	$('#fileQueue').html('');
	
}
//修改档案
function updateRecord(index){
	var agriInput = gagriInputList[index];
	if(agriInput){
		//addRecord();
		
		$("#agriImg").uploadify({
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
				$('input[name="agriInput.agriImg"]').val(data);
				$('#agriImg').uploadify('disable', true);
				$('#prev').removeClass('disabled');
				$('#a_pre').css({'color':'white'});
				$("a#a_pre").attr('href','/nytsyFiles/element/'+data);
				$("a#a_pre").fancybox();
			}
		});
		
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset')
		$('#fileQueue').html('');
		
		urlType = 2;
		
		$('.formtitle span').html('修改投入品购买记录');
		
		$('input[name="agriInput.crttime"]').val(agriInput.crttime);
		$('input[name="agriInput.agriImg"]').val(agriInput.agriImg);
		$('input[name="agriInput.agriId"]').val(agriInput.agriId);
		$('input[name="agriInput.recId"]').val(agriInput.recId);
		$('input[name="agriInput.buyAddr"]').val(agriInput.buyAddr);
		$('input[name="agriInput.buyNum"]').val(agriInput.buyNum);
		
		$('input[name="agriInput.agriCompany"]').val(agriInput.agriCompany);
		$('input[name="agriInput.agspc"]').val(agriInput.agspc);
		$('input[name="agriInput.agnum"]').val(agriInput.agnum);

		$('input[name="agriInput.buyUser"]').val(agriInput.buyUser);
		$('#buyUnit').combobox('setValue',agriInput.buyUnit);
		
		$('input[name="agriInput.agriName"]').val(agriInput.agriName);
		$('#buyDate').datebox('setValue',agriInput.buyDate);
		$('#typeId').combobox({    
		    url:'agriType_findAgriTypeComboList.action', 
		    value:agriInput.typeId
		});
		
		$('#recordForm').form('validate')
		
		if(agriInput.agriImg!=''){
			var content = '<div class="uploadify-queue-item" id="SWFUpload_0_0">\
							<div class="uploadcancel">\
							<a href="javascript:removeAppd();">X</a>\
							</div>\
							<span class="fileName">'+agriInput.agriImg+'</span>\
							</div>';
			$('#fileQueue').html(content);
			
			$('#prev').removeClass('disabled');
			$('#a_pre').css({'color':'white'});
			$("a#a_pre").attr('href','/nytsyFiles/element/'+agriInput.agriImg);
			$("a#a_pre").fancybox();
			
			$('#agriImg').uploadify('disable', true);
			
		}
		
		
		
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
