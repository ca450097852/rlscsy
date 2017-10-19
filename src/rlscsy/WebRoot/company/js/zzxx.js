var index = 0;
var optType = 0;
$(function(){
	$("#uploadify").uploadify({
		'swf'      		: '../uploadify/uploadify.swf',
		'fileObjName' 	: 'uploadify',
		'uploader' 		: 'zizhiAppendix_uploadFile.action;jsessionid='+$('#jsessionid').val(),
		//'formData'		: {'zid':zid},
		'buttonText'    : '上传附件',
		'height'		: 20,
		'width'			: 100,
		'fileSizeLimit'	: 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID'       : 'fileQueue',
		'multi'			: true,
		'removeCompleted' : false,
		'itemTemplate' : '<div id="\${fileID}" class="uploadify-queue-item">\
            <div class="uploadcancel">\
	            <a href="" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" id="pre_\${fileID}">预览</a>\
                <a href="javascript:$(\'#\${instanceID}\').uploadify(\'cancel\', \'\${fileID}\')">X</a>\
            </div>\
            <span class="fileName">\${fileName} (\${fileSize})</span><span class="data"></span>\
        </div>',
		'onQueueComplete' : function(queueData) {
			//alert(queueData.uploadsSuccessful + ' files were successfully uploaded.');
			//showZizhiAttachFile(zid)
		},
		'onUploadSuccess' : function(file, data, response) {
			$("a#pre_"+file.id).attr('href','/nytsyFiles/zizhi/'+data);
        	$("a#pre_"+file.id).fancybox();
        	
			$('#fileList').append("<div id='d_"+file.id+"'></div>");
			$('#d_'+file.id).append("<input type='hidden' name='appendixList["+index+"].appName' value='"+file.name+"'/>");
			$('#d_'+file.id).append("<input type='hidden' name='appendixList["+index+"].path' value='"+data+"'/>");
			index++;
		}
	});

	initZlist();
	
	parent.layer.closeAll();
	
	$('#typeName').combobox({onSelect:function(){
		zizhiTypeEvent($('#typeName').combobox('getValue'),null);
	}});
	
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'zzxx'},
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

var gzzList;

function initZlist(){
	$('#zzsList').html("");
	$.post('zizhi_findZizhiList.action','',function(result){
		gzzList = result;
		if(result.length>0){
			for(var i=0;i<result.length;i++){
				zz = result[i];
				var zizhiTypeList = zz.zizhiTypeList;
				var zizhiType = {};
				var typeName ="";
				if(zizhiTypeList.length>0){
					zizhiType = zizhiTypeList[0];
					if(zizhiType){
						typeName = zizhiType.typeName;
					}
				}
				var content = '<div class="baocunhou" id="zDiv_'+zz.id+'">\
			            	<div class="caozuo"><a class="xiugai" onclick="updateZizhi('+zz.id+')">修改</a><a class="shanchu" onclick="deleteZizhi('+zz.id+')">删除</a></div>\
			            	<table class="formtable">\
			                  <tr>\
			                    <td class="form_label">资质类型：</td>\
			                    <td class="form_value">'+typeName+'</td>\
			                  </tr>\
			                  <tr>\
			                    <td class="form_label" style="width:250px;">证书名称：</td>\
			                    <td class="form_value">'+zz.zizhiName+'</td>\
			                  </tr>\
			                  <tr>\
			                    <td class="form_label">颁发单位：</td>\
			                    <td class="form_value">'+zz.awardUnit+'</td>\
			                  </tr>\
			                  <tr>\
			                    <td class="form_label">颁发时间：</td>\
			                    <td class="form_value">'+zz.awardTime+'</td>\
			                  </tr>\
			                    <td class="form_label" style="vertical-align:text-top;">状态：</td>\
			                    <td class="form_value">'+getState(zz.state)+'</td>\
			                  </tr>\
			                  <tr>\
			                    <td class="form_label" style="vertical-align:text-top;">附件：</td>\
			                    <td class="form_value">';

								var zaList = zz.appendix;
								for(var j=0;j<zaList.length;j++){										
									var appdixUrl = "/nytsyFiles/zizhi/"+zaList[j].path;
									var appdixName = zaList[j].appName;
									content+= '<a rel="zz_group_'+i+'" href="'+appdixUrl+'" title="'+appdixName+'"><img src="'+appdixUrl+'" width="50px;" height="50px;" style="margin-right:20px"/></a>';
								}
			                    
			                    content += '</td>\
					                  </tr>\
						     </table></div>';

						/*if(i==result.length-1){
							content+=' <div class="btn_area2"><a class="btn_next" onclick="window.location.href=\'scxx.jsp\'">下一步</a></div>';
						}			*/	                    			                    
				$('#zzsList').append(content);
			    $("a[rel=zz_group_"+i+"]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}
		}else{
			addZizhi();
		}
	},'JSON');
}

function getState(state){
	if(state==1||state==''){
		return "正常";
	}else if(state==2){
		return "申请中";
	}else if(state==3){
		return "同意修改";
	}
}


function submitZizhiForm(){	
	if($('#zizhiForm').form('validate')==false){
		layer.msg('必填验证没有通过');
		return;
	}   	
	layer.msg('数据处理中，请稍后....', {icon: 16});
	/*parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});*/
	  	
	//提交
	var opturl = '';
	if(optType==0){
		opturl = 'zizhi_addZizhiAndAppend.action';
	}else{
		opturl = 'zizhi_updateZizhiAndAppend.action';
	}
	$('#zizhiForm').form('submit', {
		url : opturl,
		onSubmit : function(param) {
		    return $(this).form('validate');//对数据进行格式化
		},
		success : function(result) {
			layer.closeAll();
			layer.msg(result);
			$('#zizhiForm').form('reset');
			//parent.$.messager.progress('close');	
			$('#zzsList').show();
			$('.weitianxie').hide();

			initZlist();
			
		}
	});    

}

function cancelSubmit(){
	$('#zzsList').show();
	$('.weitianxie').hide();

	initZlist();
}

function removeAppd(fileID){
	$('#d_'+fileID).remove();
	$('#udiv_'+fileID).remove();
}

function addZizhi(){
	$('#zzsList').hide();
	$('.weitianxie').show();
	$('#fileQueue').html("");
	$('#fileList').html("");
	$('#zizhiForm').form('reset');
	$('#h_value').html("");
	$('#h_value').append("<input type='hidden' name='zizhi.appType' value='1'/>");
	$('#levelTr').hide();
//	$("#level").find("option").remove();
//	
//	$('#level').combobox({required:true});
	
	
	optType = 0;
	index = 0;
}

function deleteZizhi(id){
	
	/*for(var i=0;i<gzzList.length;i++){
		if(gzzList[i].id==id){
			var zizhi = gzzList[i];				
			var state = zizhi.state;
			if(state==2){
				layer.msg('正在申请修改或者删除中，请等待监管部门审核后再操作！');
				return;
			}else if(state==3){
				console.info("可以修改");
			}else{
				//需要填写申请
				//layer.msg('需要填写申请');
				$("#zizhiAuditRecord_zid").val(zizhi.id);
				$("#zizhiAuditRecord_zname").val(zizhi.zizhiName);				
				$("#applyWin").window("open");				
				return;
			}
		}
	}*/
	
	var index = layer.confirm('确定删除该资质 信息?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
		layer.close(index);
		$.post('zizhi_deleteZizhi.action', 'ids='+id, function(result) {
			layer.msg(result);
			$('#zDiv_'+id).remove();
		}, "TEXT");	
	}, function(){
	    layer.close(index);
	});
}

function submitApplyForm(){
	
	$('#audit_form').form('submit', {
		url : "zizhiAudit_addZizhiAuditRecord.action",
		onSubmit : function(param) {
		    return $(this).form('validate');//对数据进行格式化
		},
		success : function(result) {
			$("#applyWin").window("close");
			layer.msg(result);
			$('#zzsList').show();
			$('.weitianxie').hide();
			initZlist();			
		}
	});    
	
}

function updateZizhi(id){
	
	/*for(var i=0;i<gzzList.length;i++){
		if(gzzList[i].id==id){
			var zizhi = gzzList[i];				
			var state = zizhi.state;
			if(state==2){
				layer.msg('正在申请修改或者删除中，请等待监管部门审核后再操作！');
				return;
			}else if(state==3){
				console.info("可以修改");
			}else{
				//需要填写申请
				//layer.msg('需要填写申请');
				$("#zizhiAuditRecord_zid").val(zizhi.id);
				$("#zizhiAuditRecord_zname").val(zizhi.zizhiName);				
				$("#applyWin").window("open");				
				return;
			}
		}
	}*/
	
	addZizhi();
	optType = 1;
	for(var i=0;i<gzzList.length;i++){
		if(gzzList[i].id==id){
			var zizhi = gzzList[i]; 

			$("#appType").combobox('setValue',zizhi.appType);
			$("input[name='zizhi.zizhiName']").val(zizhi.zizhiName);
//			$("input[name='zizhi.grantUnit']").val(zizhi.grantUnit);
			$("input[name='zizhi.awardUnit']").val(zizhi.awardUnit);
			$("input[name='zizhi.state']").val(zizhi.state);
			$("#zizhi_awardTime").datebox('setValue',zizhi.awardTime);
			$("#zz_remark").val(zizhi.remark);
			$('#h_value').html("");
			$('#h_value').append("<input type='hidden' name='zizhi.id' value='"+zizhi.id+"'/>");
			$('#h_value').append("<input type='hidden' name='zizhi.entId' value='"+zizhi.entId+"'/>");
			$('#h_value').append("<input type='hidden' name='zizhi.appType' value='"+zizhi.appType+"'/>");
			
			if(zizhi.zizhiTypeList){
				var types = zizhi.zizhiTypeList;
				if(types[0]){
					$("#typeName").combobox('setValue',types[0].typeName);
					zizhiTypeEvent(types[0].typeName,types);
				}
			}
			
			$("#zizhiForm").form('validate');

			var appendixList = zizhi.appendix;
			for(var j=0;j<appendixList.length;j++){
				var tObj = appendixList[j];
				
				var itemTemplate ='';
//				itemTemplate += '<div id="udiv_'+tObj.appId+'" class="uploadify-queue-item">\<div class="uploadcancel">';				
//				var t1 = '<a href="javascript:void(0)" onclick="removeAppd(\''+tObj.appId+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+tObj.appName+'</span><span class="data"></span>\</div>';						
//				itemTemplate = itemTemplate+t1;
//				$('#fileQueue').append(itemTemplate);
				
				itemTemplate +='<div class="uploadify-queue-item" id="udiv_'+tObj.appId+'">\
				<div class="uploadcancel">\
				<a id="pre_'+tObj.appId+'" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" href="/nytsyFiles/element/'+tObj.appUrl+'">预览</a> \
				<a href="javascript:removeAppd('+tObj.appId+');">X</a>\
				</div>\
				<span class="fileName">'+tObj.appName+'</span><span class="data"></span>\
				</div>';
				
				$('#fileQueue').append(itemTemplate);
				$("a#pre_"+tObj.appId).fancybox();

				$('#fileList').append("<div id='d_"+tObj.appId+"'></div>");
				$('#d_'+tObj.appId).append("<input type='hidden' name='appendixList["+index+"].appName' value='"+tObj.appName+"'/>");
				$('#d_'+tObj.appId).append("<input type='hidden' name='appendixList["+index+"].path' value='"+tObj.path+"'/>");
				index++;
			}
			break;
		}
	}
}

function zizhiTypeEvent(val,types){
	if(val==0||val=='家庭农场'||val=='其他'){
		$('#levelTr').hide();
		$("#level").html("");
		//$("#level").find("option").remove();
//		$("#level").combobox({'required':false});
		return;
	}
//	$("#level").combobox({'required':true});
	var type;
	if(val=='龙头企业类'||val=='示范合作社'||val=='示范区、场'){
		type = 1;
	}else if(val=='认证类'){
		type = 2;
	}
	var htm ='';
	if(type!=''){
		//$("#level").find("option").remove();
		$.post('level_getLevelListByType.action','ids='+type,function(result){
			if(result){
				var data = new Array();
				for(var i=0;i<result.length;i++){
					var row = result[i];
					var value = row.levelId;
					var text = row.levelTitle;
					//用 zizhiType的remark字段保存 level的id值；如（"1,2,3,4"）
					htm += "<input style=\"width: 10px;height:13px;\" type=\"checkbox\" name=\"zizhiType.remark\" value=\""+value+"\">"+text+"&nbsp;";
				}
				$("#level").html(htm);//级别复选框内容
				$('#levelTr').show();//显示级别行
				////已有的类型级别；赋予选中效果
				if(types!=null&&types.length>0){
					var type ={};
					var levelId ="";
					for(var j=0;j<types.length;j++){
						type = types[j];
						if(type){
							levelId = type.levelId;
							$("input[name='zizhiType.remark'][value='"+levelId+"']").attr("checked",'checked');
						}
					}
				}
				
			}
		},'JSON');
		
		
	}
	
}
