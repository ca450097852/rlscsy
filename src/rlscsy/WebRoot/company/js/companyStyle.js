var treeData;
var basePath;
var baseEntId;

var formUrl = "entStyle_addEntStyle.action?tt="+Math.floor(Math.random()*20)+1;//当前的风格关系为空，添加

$(function(){
	basePath =$("#basePath").val();
	baseEntId =$("#baseEntId").val();
	
	$("#companyStyle").show();//显示--风格类型
	$("#infoTable").hide();//隐藏-编辑表单
	
	parent.layer.closeAll();
	$("#uploadify").uploadify({
		'swf'      		: '../uploadify/uploadify.swf',
		'fileObjName' 	: 'fileQueue',
		'uploader' 		: 'entStyle_uploadFile.action;jsessionid='+$('#jsessionid').val(),
		'buttonText'    : '上传附件',
		'height'		: 20,
		'width'			: 100,
		'fileSizeLimit'	: 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID'       : 'fileQueue',
		'multi'			: false,
		'removeCompleted' : false,
		'itemTemplate' : '<div id="\${fileID}" class="uploadify-queue-item">\
            <div class="uploadcancel">\
	            <a href="" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" id="pre_\${fileID}">预览</a>\
                <a onclick="$(\'#st_logoUrl\').val(\'\');" href="javascript:$(\'#\${instanceID}\').uploadify(\'cancel\', \'\${fileID}\')">X</a>\
            </div>\
            <span class="fileName">\${fileName} (\${fileSize})</span><span class="data"></span>\
        </div>',
		'onQueueComplete' : function(queueData) {
			//alert(queueData.uploadsSuccessful + ' files were successfully uploaded.');
			//showZizhiAttachFile(zid)
		},
		'onUploadSuccess' : function(file, data, response) {
			
			$('#id_logoImage').val(data);
			$("a#pre_"+file.id).attr('href','/nytsyFiles/entstyle/'+data);
        	$("a#pre_"+file.id).fancybox();
		},
		'onSelect':function(){
			if($('.uploadify-queue-item').length>1){
				$('.uploadify-queue-item:first').remove();
			}
		}
	});
	
	function removeAppd(fileID){
		$('#st_logoUrl').val('');
	}
	
	
	//指引信息
	/*$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'qyxx'},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result&&result.length==1){
				var useguide = result[0];					
				var html = '<font>'+useguide.title+'</font>'+useguide.contents;
				$('#useguide').html(html);
			}
			
		}
	});*/
});


//打开编辑页面窗口
function styleEdit(scType){
	$("#companyStyle").hide();//隐藏--风格类型
	$("#infoTable").show();//显示-编辑表单
	
	if(scType==1){
		$("#html_title").html("门户风格设置");
	}else if(scType==2){
		$("#html_title").html("风格设置");
	}
	
	$("#entStyle_scId").combobox({
		url:'styleconfig_getStyleCombobox.action?scType='+scType+'&tt='+Math.floor(Math.random()*20)+1,
		method:'get',
		required:true,
		valueField:'id',    
	    textField:'text'
	});
	//scType--1设置门户风格,2设置会员风格
	if(baseEntId){
		$.ajax( {
			url : 'entStyle_getEntStyleInfo.action?entId='+baseEntId+'&scType='+scType,
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result!=null&&result!=""){
					console.info("result=="+result);
					var entStyle = result;
					formUrl = "entStyle_updateEntStyle.action?tt="+Math.floor(Math.random()*20)+1;
					
					for(var item in entStyle){
						if(item=='logoImage'){
							if(entStyle[item]!=''){
								$('#fileQueue').html('<a id="toFun" href="/nytsyFiles/entstyle/'+entStyle[item]+'"><img width="50px" src="/nytsyFiles/entstyle/'+entStyle[item]+'"><a>');
								$("a#toFun").fancybox();
							}
						}else if(item=='scId'){
							/*if(entStyle[item]!=''){
								$("#entStyle_scId").combobox('setValue',entStyle.scId);
							}*/
							/*$("#entStyle_scId").combobox({
								url:'styleconfig_getStyleCombobox.action?scType='+scType+'&tt='+Math.floor(Math.random()*20)+1,
								method:'get',
								required:true,
								valueField:'id',    
							    textField:'text',   
								value:entStyle.scId
							});*/
							console.info("entStyle=="+entStyle);
							console.info("entStyle.scId=="+entStyle.scId);
							$("#entStyle_scId").combobox('setValue',entStyle.scId);
							
						}else{
							var obj = $("[name='entStyle."+item+"']:eq(0)");
							if(obj.length>0){
								obj.val(entStyle[item]);
							}else{
								$('#hiddenValue').append("<input type='hidden' name='entStyle."+item+"' value='"+entStyle[item]+"' />");
							}
						}
					}
					
				}else{
					formUrl = "entStyle_addEntStyle.action?tt="+Math.floor(Math.random()*20)+1;
					
					$("#add_form").form("reset");
					/*$("#entStyle_scId").combobox({
						url:'styleconfig_getStyleCombobox.action?scType='+scType+'&tt='+Math.floor(Math.random()*20)+1,
						method:'get',
						valueField:'id',    
					    textField:'text',
						required:true
					});*/
					$("#entStyle_scId").combobox('setValue','');
					$("[name='entStyle.entId']").val(baseEntId);
					$("[name='entStyle.esId']").val(0);
					$("[name='entStyle.scType']").val(scType);
				}	
			}
		});
	}
	
	
}


function submitForm(){
	$('#add_form').form('submit', {
		url : formUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			layer.msg(result);
			returnToInfo();
		}
});
}


//返回信息列表
function returnToInfo(){
	$("#companyStyle").show();//显示--风格类型
	$("#infoTable").hide();//隐藏-编辑表单
}