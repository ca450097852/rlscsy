var treeData;
var basePath;
$(function(){
	basePath =$("#basePath").val();
	init();	
	
	parent.layer.closeAll();
	
	
	$("#uploadify").uploadify({
		'swf'      		: '../uploadify/uploadify.swf',
		'fileObjName' 	: 'uploadify',
		'uploader' 		: 'company_uploadFile.action;jsessionid='+$('#jsessionid').val(),
		'buttonText'    : '上传附件',
		'height'		: 20,
		'width'			: 100,
		'fileSizeLimit'	: 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID'       : 'fileQueue',
		'multi'			: false,
		'removeCompleted' : false,
		'itemTemplate' : '<div id="\${fileID}" class="uploadify-queue-item uploadify-img">\
            <div class="uploadcancel">\
	            <a href="" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" id="pre_\${fileID}">预览</a>\
                <a onclick="$(\'#licenseImg\').val(\'\');" href="javascript:$(\'#\${instanceID}\').uploadify(\'cancel\', \'\${fileID}\')">X</a>\
            </div>\
            <span class="fileName">\${fileName} (\${fileSize})</span><span class="data"></span>\
        </div>',
		'onQueueComplete' : function(queueData) {
			//alert(queueData.uploadsSuccessful + ' files were successfully uploaded.');
			//showZizhiAttachFile(zid)
		},
		'onUploadSuccess' : function(file, data, response) {
			$('#licenseImg').val(data);
			$("a#pre_"+file.id).attr('href','/nytsyFiles/company/'+data);
        	$("a#pre_"+file.id).fancybox();
		},
		'onSelect':function(){
			$("#fileQueue").html();
		}
	});
	

	$("#uploadify_logo").uploadify({
		'swf'      		: '../uploadify/uploadify.swf',
		'fileObjName' 	: 'uploadify',
		'uploader' 		: 'company_uploadLogo.action;jsessionid='+$('#jsessionid').val(),
		'buttonText'    : '上传logo',
		'height'		: 20,
		'width'			: 100,
		'fileSizeLimit'	: 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID'       : 'fileQueue_logo',
		'multi'			: false,
		'removeCompleted' : false,
		'itemTemplate' : '<div id="\${fileID}" class="uploadify-queue-item">\
            <div class="uploadcancel">\
	            <a href="" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" id="pre_\${fileID}">预览</a>\
                <a onclick="$(\'#comLogo\').val(\'\');" href="javascript:$(\'#\${instanceID}\').uploadify(\'cancel\', \'\${fileID}\')">X</a>\
            </div>\
            <span class="fileName">\${fileName} (\${fileSize})</span><span class="data"></span>\
        </div>',
		'onQueueComplete' : function(queueData) {
			//alert(queueData.uploadsSuccessful + ' files were successfully uploaded.');
			//showZizhiAttachFile(zid)
		},
		'onUploadSuccess' : function(file, data, response) {
			$('#comLogo').val(data);
			$("a#pre_"+file.id).attr('href','/nytsyFiles/company/'+data);
        	$("a#pre_"+file.id).fancybox();
		},
		'onSelect':function(){
			$("#fileQueue_logo").html();
		}
	});
	
	
	function removeAppd(fileID){
		$('#licenseImg').val('');
	}
	
	

	
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
	$.post('webcompany_getLoginCompany.action?tt='+Math.floor(Math.random()*20)+1,'',function(result){
		try{
			f_timeout(data);
		}catch(e){
			
		}
		company = result[0];
		
		//信息不齐全
		if(false){
			$('#infoTable').show();
			$('#companyInfo').hide();
			for(var item in company){
					var obj = $("[name='company."+item+"']:eq(0)");
					if(obj.length>0){
						var className=obj.attr('class');
						if('combo-value'==className){
							//$('#'+item).combobox('setValue',company[item]);
							o1 = $('#'+item).attr('class');
							//alert(item+"==="+$('#'+item).attr('class').indexOf('combotree-f')+"==="+$('#'+item).attr('class').indexOf('combobox-f'));
							if(o1.indexOf('combotree-f')!=-1){
								$('#'+item).combotree('setValue',company[item]);
							}else if(o1.indexOf('combobox-f')!=-1){
								$('#'+item).combobox('setValue',company[item]);
							}
						}else{
							obj.val(company[item]);
						}
					}else{
						$('#hiddenValue').append("<input type='hidden' name='company."+item+"' value='"+company[item]+"' />");
					}
					
				}
				$("#add_form").form("validate");
		}else{
			$('#infoTable').hide();
			$('#companyInfo').show();
			for(var item in company){
				
				var text = company[item]==null?"":company[item];
				if(item=='comLogo'){
					if(text!=''){
						$('#s_'+item).html('<a id="toComLogo" href="/nytsyFiles/company/'+text+'"><img width="50px" src="/nytsyFiles/company/'+text+'"><a>');
						$("a#toComLogo").fancybox();
					}
				}else if(item=='licenseImg'){
					if(text!=''){
						$('#s_'+item).html('<a id="toFun" href="/nytsyFiles/company/'+text+'"><img width="50px" src="/nytsyFiles/company/'+text+'"><a>');
						$("a#toFun").fancybox();
					}
				}else if(item=='state'){
					$('#s_'+item).html(getState(text));
				}else if(item=='flag'){
					if(text!=''){
						$('#s_'+item).html(getFlag(text));
						$('#isnode').show();
					}
					
				}else if(item=='nature'){
					$('#s_'+item).html(getNature(text));
				}else if(item=='comType'){
					$('#s_'+item).html(getComType(text));
				}else{
					$('#s_'+item).html(text);
				}
				
			}
		
			
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
		url : 'webcompany_updateCompany.action?tt='+Math.floor(Math.random()*20)+1,
		onSubmit : function(result) {
				return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			
			layer.msg(result);
			
			init();
		}
});
}
var flag = true;
function updateEnt(){
	$('#infoTable').show();
	$('#companyInfo').hide();
	
	$.post('webcompany_getLoginCompany.action?tt='+Math.floor(Math.random()*20)+1,'',function(result){
		
		if(flag){
			$("#parentId").combobox({
				url:'webcompany_getCompanyToSelect.action?area='+company.area,
				valueField: 'id',    
		        textField: 'text',  
				method:'get',
				required:true
			});
			$("#parentId").combobox('setValue',company.parentId);
			
			$("#area").combotree({
				url:'ent_getEntAreaTree2.action?tt='+Math.floor(Math.random()*20)+1,
				method:'get',
				required:true,
				onLoadSuccess:function(node, data){
					if(data){
						treeData = data;
					}
					flag = false;
					f_timeoutToCompany(data);	
					$("#area").combotree('setValue',company.area);
				},
				onSelect:function(node){
					var area = node.id;
					$('#parentId').combobox('reload','webcompany_getCompanyToSelect.action?area='+area);  
				}
			});
		}
		
		for(var item in company){
			var obj = $("[name='company."+item+"']:eq(0)");
			if(obj.length>0){
				var className=obj.attr('class');
				if('combo-value'==className){
					o1 = $('#'+item).attr('class');
					if(o1.indexOf('combotree-f')!=-1){
						$('#'+item).combotree('setValue',company[item]);
					}else if(o1.indexOf('combobox-f')!=-1){
						$('#'+item).combobox('setValue',company[item]);
					}else if(o1.indexOf('datebox-f')!=-1){
						$('#'+item).datebox('setValue',company[item]);
					}
					
				}else{
					obj.val(company[item]);
				}
				
			}else{
				$('#hiddenValue').append("<input type='hidden' name='company."+item+"' value='"+company[item]+"' />");
			}
			
		}
		
		if(company.licenseImg!=''){
			var content = '<div id="file_'+company.comId+'" class="uploadify-queue-item">\
				            <div class="uploadcancel">\
					            <a href="" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" id="pre_'+company.comId+'">预览</a>\
				                <a onclick="$(\'#licenseImg\').val(\'\');$(\'#file_'+company.comId+'\').remove();">X</a>\
				            </div>\
				            <span class="fileName">'+company.licenseImg+' </span><span class="data"></span>\
				        </div>';
			$('#fileQueue').html(content);
			
			$("a#pre_"+company.comId).attr('href','/nytsyFiles/company/'+company.licenseImg);
        	$("a#pre_"+company.comId).fancybox();
		}else{
			$('#fileQueue').html('');
		}
		
		if(company.comLogo!=''){
			var content = '<div id="file_'+company.comId+company.area+'" class="uploadify-queue-item">\
				            <div class="uploadcancel">\
					            <a href="" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" id="pre_'+company.comId+company.area+'">预览</a>\
				                <a onclick="$(\'#comLogo\').val(\'\');$(\'#file_'+company.comId+company.area+'\').remove();">X</a>\
				            </div>\
				            <span class="fileName">'+company.comLogo+' </span><span class="data"></span>\
				        </div>';
			$('#fileQueue_logo').html(content);
			
			$("a#pre_"+company.comId+company.area).attr('href','/nytsyFiles/company/'+company.comLogo);
        	$("a#pre_"+company.comId+company.area).fancybox();
		}else{
			$('#fileQueue_logo').html('');
		}
		
		
		
		$("#add_form").form("validate");
	},'JSON');
}
//返回信息列表
function returnToCompanyInfo(){
	$('#companyInfo').show();
	$('#div_dim').hide();
}




//获取状态
function getState(value){
	var res = '无';
	if(value=='1'){
		res = '使用';
	}else if(value=='2'){
		res = '停用';
	}
	return res;
}

//获取节点类型
function getFlag(value){
	var res = '无';//1代表屠宰企业、2代表批发企业、3代表菜市场、4代表超市、5代表团体消费单位、6代表其他
	if(value=='1'){
		res = '屠宰企业';
	}else if(value=='2'){
		res = '批发企业';
	}else if(value=='3'){
		res = '菜市场';
	}else if(value=='4'){
		res = '超市';
	}else if(value=='5'){
		res = '团体消费单位';
	}else if(value=='6'){
		res = '其他';
	}
	return res;
}

//企业性质
function getNature(value){
	var res = '无';
	if(value=='1'){
		res = '企业';
	}else if(value=='2'){
		res = '个体户';
	}
	return res;
}

//经营者类型
function getComType(value){
	var res = '无';//主要分生猪批发商、肉类蔬菜批发商、肉类蔬菜零售商、配送企业、其他等类型
	if(value=='1'){
		res = '生猪批发商';
	}else if(value=='2'){
		res = '肉菜批发商';
	}else if(value=='3'){
		res = '肉菜零售商';
	}else if(value=='4'){
		res = '配送企业';
	}else if(value=='5'){
		res = '其他';
	}
	return res;
}