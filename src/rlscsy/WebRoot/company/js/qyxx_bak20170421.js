var treeData;
var basePath;
$(function(){
	basePath =$("#basePath").val();
	init();	
	
	parent.layer.closeAll();
	
	
	$("#uploadify").uploadify({
		'swf'      		: '../uploadify/uploadify.swf',
		'fileObjName' 	: 'fileQueue',
		'uploader' 		: 'ent_uploadFile.action;jsessionid='+$('#jsessionid').val(),
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
			
			$('#st_logoUrl').val(data);
			$("a#pre_"+file.id).attr('href','/nytsyFiles/company/'+data);
        	$("a#pre_"+file.id).fancybox();
		},
		'onSelect':function(){
			
			if($('.uploadify-queue-item').length>1){
				$('.uploadify-queue-item:first').remove();
			}
		}
	});
	
	
	
	function removeAppd(fileID){
		alert(11);
		$('#st_logoUrl').val('');
	}
	
	
	$('#entType_id').combobox({onSelect: function(param){
			var text = param.text;
			if(text=='政府单位'||text=='企业'||text=='合作社'){
				$('#t_orgCode').validatebox({required:true});
			}else{
				$('#t_orgCode').validatebox({required:false});
			}
		}
	});
	
	//初始化xhEditor编辑器插件
	$('#xh_editor').xheditor({
		tools:'full',
		skin:'default',
		upMultiple:true,
		upImgUrl: basePath+"UploadFileServlet",
		upImgExt: "jpg,jpeg,gif,bmp,png",
		onUpload:insertUpload,
		html5Upload:false
	});
	//xbhEditor编辑器图片上传回调函数
	function insertUpload(msg) {
		var _msg = msg.toString();
		var _picture_name = _msg.substring(_msg.lastIndexOf("/")+1);
		var _picture_path = Substring(_msg);
//		var _str = "<input type='checkbox' name='_pictures' value='"+_picture_path+"' checked='checked' onclick='return false'/><label>"+_picture_name+"</label><br/>";
		$("#xh_editor").append(_msg);
//		$("#uploadList").append(_str);
	}
	//处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.
	function Substring(s){
		return s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length);
	}
	
	
	//指引信息
	$.ajax( {
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
	});
});

function init(){
	$.post('ent_getLoginEntInfo.action?tt='+Math.floor(Math.random()*20)+1,'',function(result){
		try{
			f_timeout(data);
		}catch(e){
			
		}
		enterprise = result[0];
		
		//信息不齐全
		//if(enterprise.name==''||enterprise.legalPerson==''||enterprise.orgCode==''||enterprise.regAddr==''||enterprise.manageAddr==''||enterprise.tel==''){
		if(false){
			$('#infoTable').show();
			$('#companyInfo').hide();
			for(var item in enterprise){
					var obj = $("[name='enterprise."+item+"']:eq(0)");
					if(obj.length>0){
						var className=obj.attr('class');
						if('combo-value'==className){
							//$('#'+item+'_id').combobox('setValue',enterprise[item]);
							o1 = $('#'+item+'_id').attr('class');
							//alert(item+"==="+$('#'+item+'_id').attr('class').indexOf('combotree-f')+"==="+$('#'+item+'_id').attr('class').indexOf('combobox-f'));
							if(o1.indexOf('combotree-f')!=-1){
								$('#'+item+'_id').combotree('setValue',enterprise[item]);
							}else if(o1.indexOf('combobox-f')!=-1){
								$('#'+item+'_id').combobox('setValue',enterprise[item]);
							}
						}else{
							obj.val(enterprise[item]);
						}
					}else{
						$('#hiddenValue').append("<input type='hidden' name='enterprise."+item+"' value='"+enterprise[item]+"' />");
					}
					
				}
				$("#add_form").form("validate");
		}else{
			$('#infoTable').hide();
			$('#companyInfo').show();
			for(var item in enterprise){
				if(item=='logoUrl'){
					if(enterprise[item]!=''){
						$('#s_'+item).html('<a id="toFun" href="/nytsyFiles/company/'+enterprise[item]+'"><img width="50px" src="/nytsyFiles/company/'+enterprise[item]+'"><a>');
						$("a#toFun").fancybox();
					}
				}else if(item=='isbatch'){//是否批次追溯；
					var isb = enterprise[item];
					if(isb=='0'){
						$('#s_'+item).html("否");
					}else{
						$('#s_'+item).html("是");
					}
					
				}else{
					$('#s_'+item).html(enterprise[item]);
				}
			}
			var date = $('#entType_id').combobox('getData');
			for(i=0;i<date.length;i++){
				ob = date[i];
				if(ob.id==enterprise.entType){
					//alert(ob.text);
					$('#s_entType').html(ob.text);
					break;
				}
			}
			$('#s_areaId').html(enterprise.areaName);
			
		}
				
	},'JSON');
	
	
	
}

function getAreaName(value){
	var tVo = treeData[0].children;
	for(i=0;i<tVo.length;i++){
		vo = tVo[i];
		if(vo.id==value){
			return vo.text;
		}
	}
}

function submitForm(){
	$('#add_form').form('submit', {
		url : 'ent_updateEnterprise.action?tt='+Math.floor(Math.random()*20)+1,
		onSubmit : function(result) {
				return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			
			layer.msg(result);
			
			$.post('ent_getLoginEntInfo.action?tt='+Math.floor(Math.random()*20)+1,'',function(result){
				
				enterprise = result[0];
				for(var item in enterprise){
					if(item=='logoUrl'){
						if(enterprise[item]!=''){
							$('#s_'+item).html('<a id="toFun" href="/nytsyFiles/company/'+enterprise[item]+'"><img width="50px" src="/nytsyFiles/company/'+enterprise[item]+'"><a>');
							$("a#toFun").fancybox();
						}else{
							$('#s_'+item).html('');
						}
					}else if(item=='isbatch'){//是否批次追溯；
						var isb = enterprise[item];
						if(isb=='0'){
							$('#s_'+item).html("否");
						}else{
							$('#s_'+item).html("是");
						}
						
					}else{
						$('#s_'+item).html(enterprise[item]);
					}
					//$('#s_'+item).html(enterprise[item]);
				}
				var date = $('#entType_id').combobox('getData');
				for(i=0;i<date.length;i++){
					ob = date[i];
					if(ob.id==enterprise.entType){
						//alert(ob.text);
						$('#s_entType').html(ob.text);
						break;
					}
				}
				$('#s_areaId').html(enterprise.areaName);
				$('#infoTable').hide();
				$('#companyInfo').show();
			},'JSON');
		}
});
}
var flag = true;
function updateEnt(){
	$('#infoTable').show();
	$('#companyInfo').hide();
	
	$.post('ent_getLoginEntInfo.action?tt='+Math.floor(Math.random()*20)+1,'',function(result){
		
		if(flag){
			$("#areaId_id").combotree({
				url:'ent_getEntAreaTree.action?tt='+Math.floor(Math.random()*20)+1,
				method:'get',
				required:true,
				onLoadSuccess:function(node, data){
				if(data){
					treeData = data;
				}
				flag = false;
				f_timeoutToCompany(data);	
				$("#areaId_id").combotree('setValue',enterprise.areaId);
			}
			});
		}
		
		for(var item in enterprise){
			var obj = $("[name='enterprise."+item+"']:eq(0)");
			if(obj.length>0){
				var className=obj.attr('class');
				if('combo-value'==className){
					o1 = $('#'+item+'_id').attr('class');
					if(o1.indexOf('combotree-f')!=-1){
						$('#'+item+'_id').combotree('setValue',enterprise[item]);
					}else if(o1.indexOf('combobox-f')!=-1){
						$('#'+item+'_id').combobox('setValue',enterprise[item]);
					}
				}else{
					obj.val(enterprise[item]);
				}
			}else{
				$('#hiddenValue').append("<input type='hidden' name='enterprise."+item+"' value='"+enterprise[item]+"' />");
			}
			
		}
		
		if(enterprise.logoUrl!=''){
			var content = '<div id="file_'+enterprise.entId+'" class="uploadify-queue-item">\
				            <div class="uploadcancel">\
					            <a href="" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" id="pre_'+enterprise.entId+'">预览</a>\
				                <a onclick="$(\'#st_logoUrl\').val(\'\');$(\'#file_'+enterprise.entId+'\').remove();">X</a>\
				            </div>\
				            <span class="fileName">'+enterprise.logoUrl+' </span><span class="data"></span>\
				        </div>';
			$('#fileQueue').html(content);
			
			$("a#pre_"+enterprise.entId).attr('href','/nytsyFiles/company/'+enterprise.logoUrl);
        	$("a#pre_"+enterprise.entId).fancybox();
		}else{
			$('#fileQueue').html('');
		}
		
		
		var text = enterprise.entType;
		if(text==1||text==2||text==4){
			$('#t_orgCode').validatebox({required:true});
		}else{
			$('#t_orgCode').validatebox({required:false});
		}
		
		$("#add_form").form("validate");
	},'JSON');
}
//返回信息列表
function returnToCompanyInfo(){
	$('#companyInfo').show();
	$('#div_dim').hide();
}