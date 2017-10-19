
var urlType = 1;
var gAppList = [];
var ids = [];
$(function(){
	//parent.layer.closeAll();
	
	initProList(1,10);
	
	$("#uploadify").uploadify({
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
		'itemTemplate' : '<div id="\${fileID}" class="uploadify-queue-item">\
            <div class="uploadcancel">\
	            <a href="" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" id="pre_\${fileID}">预览</a>\
                <a href="javascript:$(\'#\${instanceID}\').uploadify(\'cancel\', \'\${fileID}\')">X</a>\
            </div>\
            <span class="fileName">\${fileName} (\${fileSize})</span><span class="data"></span>\
        </div>',
		'onUploadSuccess' : function(file, data, response) {
        	$("a#pre_"+file.id).attr('href','/nytsyFiles/element/'+data);
        	$("a#pre_"+file.id).fancybox();
			//$('#filePath').val(data);
			//$('#fileName').val(file.name);
        	
        	var item = {appType:file.id,appName:file.name,appUrl:data};
        	gAppList.push(item);
        	
        	console.log(gAppList);
        	//alert(JSON.stringify(gAppList));
		}
	});
	
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'checkinfo'},
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

function removeAppd(fileID){
	
	$('#'+fileID).remove();
	
	if(!isNaN(fileID)){
		ids.push(fileID);
	}
	
	for(var i=0;i<gAppList.length;i++){
		if(gAppList[i].appType==fileID){
			gAppList.splice(i,1);
			return;
		}
	}
	
	/*$('input[name="agriInput.agriImg"]').val("");
	$('#agriImg').uploadify('disable', false);
	$('#prev').addClass('disabled');
	$('#a_pre').css({'color':'#808080'});*/
}

var gcheckinfoList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['checkinfo.recId'] = $('#h_recId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('checkinfo_findCheckinfoList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gcheckinfoList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var checkinfoList = result.rows;
			//<td><input type="checkbox" class="proCheck" value="'+pro.proId+'" /></td>\
			//<a class="tablelink" onclick="addAppend('+i+')">添加附件</a>\
			for(var i=0;i<checkinfoList.length;i++){
				var checkinfo = checkinfoList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+checkinfo.checkName+'</td>\
			        <td>'+checkinfo.checkNum+'</td>\
			        <td>'+checkinfo.checkUnit+'</td>\
			        <td>'+checkinfo.checkTime+'</td>\
			        <td>'+checkinfo.checkResult+'</td>\
			        <td>'+(checkinfo.checkType==1?"第三方检测报告":"企业自检报告")+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteAppend('+checkinfo.checkId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(checkinfoList.length==0){
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
function deleteAppend(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('checkinfo_deleteCheckinfo.action',{ids:id},function(result){
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
	var formUrl = 'checkinfo_addCheckinfo.action';
	if(urlType!=1){
		formUrl = 'checkinfo_updateCheckinfo.action';
	}
	$('#jsonApp').val(JSON.stringify(gAppList));
	
	$('#ids').val(ids.join(','));
	
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
	gAppList = [];
	ids = [];
	$('.formtitle span').html('添加检验报告');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset')
	removeAppd();
	$('#fileQueue').html('');
	if(!flag){
		$('.tools').hide();
	}
}
//修改档案
function updateRecord(index){
	var checkinfo = gcheckinfoList[index];
	if(checkinfo){
		addRecord(true);
		urlType = 2;
		$('.formtitle span').html('修改检验报告');
		
		$('input[name="checkinfo.checkId"]').val(checkinfo.checkId);
		$('input[name="checkinfo.recId"]').val(checkinfo.recId);
		
		$('input[name="checkinfo.checkName"]').val(checkinfo.checkName);
		$('input[name="checkinfo.checkNum"]').val(checkinfo.checkNum);
		$('input[name="checkinfo.checkUnit"]').val(checkinfo.checkUnit);
		$('input[name="checkinfo.checkResult"]').val(checkinfo.checkResult);
		
		$('#checkType_id').combobox('setValue',checkinfo.checkType);
		
		$('#checkTime').datebox('setValue',checkinfo.checkTime);
		
		$.post('elementApp_findAppList.action',{'elementApp.fkId':checkinfo.checkId,'elementApp.appType':1},function(result){
			var rows =  result.rows;
			for(var i=0;i<rows.length;i++){
				var row = rows[i];
				var content = '<div class="uploadify-queue-item" id="'+row.appId+'">\
								<div class="uploadcancel">\
								<a id="pre_'+row.appId+'" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" href="/nytsyFiles/element/'+row.appUrl+'">预览</a> \
								<a href="javascript:removeAppd('+row.appId+');">X</a>\
								</div>\
								<span class="fileName">'+row.appName+'</span><span class="data"></span>\
								</div>';
								
				$('#fileQueue').append(content);
	        	$("a#pre_"+row.appId).fancybox();
			}
		},'JSON')
		
		
		$('#recordForm').form('validate')
	}
	
}
//添加附件
function addAppend(index){
	var checkinfo = gcheckinfoList[index];
	if(checkinfo){
		
		
		var content = '<div style="height:240px;overflow-x:auto;"><table id="checkinfoApp" class="tablelist">\
	    	<thead>\
			<tr>\
		    <th>附件名称</th>\
		    <th>上传时间</th>\
		    <th>备注</th>\
		    <th>排序号</th>\
		    <th>操作</th>\
		    </tr>\
		    </thead>\
		    <tbody>\
		    </tbody>\
		</table></div>';
		
		content += '<div id="proImgList">\
			<input type="hidden" id="filePath"/>\
			<input type="hidden" id="fileName"/>\
			<table style="width: 100%">\
		    <tr style="height: 30px;background: #eaeaea;border-bottom: 1px solid #d5d5d5;border-top: 1px solid #d5d5d5;"><td colspan="2">&nbsp;&nbsp;添加附件</td></tr>\
		   	<tr style="height: 30px"><td width="100px">&nbsp;排序</td><td><input id="app_orderby" value="5"/></td></tr>\
		    <tr style="height: 30px"><td>&nbsp;备注</td><td><input id="app_remark"/></td></tr>\
		    <tr style="height: 30px">\
		    	<td>&nbsp;请选择图片</td>\
		    	<td>\
		    		<table width="100%;" style="margin:0;">\
		    			<tr>\
		    				<td  width="120"><input type="file" id="uploadify"/></td>\
		    				<td><div id="fileQueue"></div></td>\
		    			</tr>\
		    		</table>\
		    	</td>\
		    </tr>\
		</table></div>';
		
		
		layer.open({
			closeBtn: false,
			title:'附件管理',
			shift:5,
			btn:['保存','关闭'],
		    type: 5,
		    skin: 'layui-layer-rim', //加上边框
		    area: ['700px', '450px'], //宽高
		    content: content,
		    yes:function(index,layero){
			var path = $("#filePath").val();
				var appName = $("#fileName").val();
				var remark = $('#app_remark').val();
				var orderby = $('#app_orderby').val();
		
				if (!path) {
					layer.msg('请选择上传图片');
					return;
				}
		
				var cod = {};
				cod['elementApp.fkId'] = checkinfo.checkId;
				cod['elementApp.appName'] = appName;
				cod['elementApp.appUrl'] = path;
				cod['elementApp.seq'] = orderby;
				cod['elementApp.remark'] = remark;
				cod['elementApp.appType'] = 1;
				$.post('elementApp_addElementApp.action', cod, function(rst) {
					layer.msg(rst);
					$("#fileName").val("");
					$("#filePath").val("");
					$('#app_remark').val("");
					$('#uploadify').uploadify('disable', false);
					$('#fileQueue').html("");
					$('#app_orderby').val(5);
					initCheckinfoAppList(checkinfo.checkId);
				}, 'TEXT');
			}
		});
		
		$('#app_orderby').numberbox({    
		    min:0
		});  

		$("#uploadify").uploadify({
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
			'itemTemplate' : '<div id="\${fileID}" class="uploadify-queue-item">\
	            <div class="uploadcancel">\
		            <a href="" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" id="pre_\${fileID}">预览</a>\
	                <a href="javascript:$(\'#\${instanceID}\').uploadify(\'cancel\', \'\${fileID}\')">X</a>\
	            </div>\
	            <span class="fileName">\${fileName} (\${fileSize})aa</span><span class="data"></span>\
	        </div>',
			'onUploadSuccess' : function(file, data, response) {
	        	$("a#pre_"+file.id).attr('href','/nytsyFiles/element/'+data);
	        	$("a#pre_"+file.id).fancybox();
	        	$('#uploadify').uploadify('disable', true);
				$('#filePath').val(data);
				$('#fileName').val(file.name);
			}
		});
		
		initCheckinfoAppList(checkinfo.checkId);
	}
}

function initCheckinfoAppList(checkId){
	$('#checkinfoApp tr:not(:first)').remove();
	$.post('elementApp_findAppList.action',{'elementApp.fkId':checkId,'elementApp.appType':'1'},function(result){
		var imgList = result.rows;
		for(var i=0;i<imgList.length;i++){
			cont = '<tr '+(i%2==0?"":"class=\"odd\"")+'><td>'+imgList[i].appName+'</td><td>'+imgList[i].uploadTime+'</td>\
						<td>'+imgList[i].remark+'</td><td>'+imgList[i].seq+'</td>\
						<td><a id="proImg_'+i+'" href="/nytsyFiles/element/'+imgList[i].appUrl+'" >预览</a>\
						&nbsp;&nbsp;&nbsp;<a onclick="deleteApp('+imgList[i].appId+','+checkId+')">删除</a></td></tr>';
			$('#checkinfoApp').append(cont);
			$("a#proImg_"+i).fancybox();
		}
		if(imgList.length==0){
			$('#checkinfoApp').append('<tr><td align="center" colspan="'+$('#checkinfoApp tr:first').find('th').length+'">暂无数据</td></tr>');
		}
	},'JSON');
}

function deleteApp(appId,checkId){
	$.post('elementApp_deleteApps.action',{'ids':appId},function(result){
		layer.msg(result);
		initCheckinfoAppList(checkId);
	},'TEXT');
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
