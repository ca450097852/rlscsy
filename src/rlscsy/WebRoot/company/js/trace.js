/**
 * 点击更换
 * @param id
 * @return
 */
function changeTab(id,ts){
	$('.center_content3').hide();
	
	$('.title_step2_1 div').removeClass('step04Check');
	$(ts).addClass('step04Check');
	
	$('#'+id).show();
	if(id=='proArea'){
		initProArea();
	}else if(id=='proSeed'){
		initProSeed();
	}else if(id=='plantRaise'){
		initPlantRaise();
	}else if(id=='prevention'){
		initPrevention();
	}else if(id=='proCheck'){
		initProCheck();
	}else if(id=='process'){
		initProcess();
	}else if(id=='storeTransport'){
		initStoreTransport();
	}else if(id=='proBatch'){
		initProBatch(1,10);
	}
}

var proType ="";
var filePath = "";

$(function(){
	initProArea();
	filePath = $("#filePath").val();
	proType = $("#proType").val();
	
	$("#append_proArea").uploadify({
		'swf'             : '../uploadify/uploadify.swf',
		'fileObjName'     : 'uploadify',
		'uploader'        : 'traceApp_uploadFile.action;jsessionid='+ $('#sessionId').val(),
		'buttonText'      : '上传附件',
		'height'          : 20,
		'width'           : 100,
		'multi'           : false,
		'fileSizeLimit'   : 1024,
		'fileTypeDesc'    : '支持格式:jpg/gif/jpeg/png',
		'fileTypeExts'    : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID'         : 'fileQueue',
		'multi' 		  : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			$('#fileList').append("<div id='d_"+file.id+"'></div>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+file.name+"'/>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+data+"'/>");
			index++;
		}
	});
	
	$("#append_seed").uploadify({
		'swf' : '../uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'traceApp_uploadFile.action;jsessionid='+ $('#sessionId').val(),
		'buttonText' : '上传附件',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc' : '支持格式:jpg/gif/jpeg/png',
		'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID' : 'fileQueue_seed',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			$('#fileList_seed').append("<div id='d_"+file.id+"'></div>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+file.name+"'/>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+data+"'/>");
			index++;
		}
	});
	
	
	$("#append_plantRaise").uploadify({
		'swf' : '../uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'traceApp_uploadFile.action;jsessionid='+ $('#sessionId').val(),
		'buttonText' : '上传附件',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc' : '支持格式:jpg/gif/jpeg/png',
		'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID' : 'fileQueue_plantRaise',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			$('#fileList_plantRaise').append("<div id='d_"+file.id+"'></div>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+file.name+"'/>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+data+"'/>");
			index++;
		}
	});
	
	$("#append_prevention").uploadify({
		'swf' : '../uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'traceApp_uploadFile.action;jsessionid='+ $('#sessionId').val(),
		'buttonText' : '上传附件',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc' : '支持格式:jpg/gif/jpeg/png',
		'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID' : 'fileQueue_prevention',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			$('#fileList_prevention').append("<div id='d_"+file.id+"'></div>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+file.name+"'/>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+data+"'/>");
			index++;
		}
	});
	
	$("#append_proCheck").uploadify({
		'swf' : '../uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'traceApp_uploadFile.action;jsessionid='+ $('#sessionId').val(),
		'buttonText' : '上传附件',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc' : '支持格式:jpg/gif/jpeg/png',
		'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID' : 'fileQueue_proCheck',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			$('#fileList_proCheck').append("<div id='d_"+file.id+"'></div>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+file.name+"'/>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+data+"'/>");
			index++;
		}
	});
	
	$("#append_process").uploadify({
		'swf' : '../uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'traceApp_uploadFile.action;jsessionid='+ $('#sessionId').val(),
		'buttonText' : '上传附件',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc' : '支持格式:jpg/gif/jpeg/png',
		'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID' : 'fileQueue_process',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			$('#fileList_process').append("<div id='d_"+file.id+"'></div>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+file.name+"'/>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+data+"'/>");
			index++;
		}
	});
	
	$("#append_storeTransport").uploadify({
		'swf' : '../uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'traceApp_uploadFile.action;jsessionid='+ $('#sessionId').val(),
		'buttonText' : '上传附件',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc' : '支持格式:jpg/gif/jpeg/png',
		'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID' : 'fileQueue_storeTransport',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			$('#fileList_storeTransport').append("<div id='d_"+file.id+"'></div>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+file.name+"'/>");
			$('#d_'+file.id).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+data+"'/>");
			index++;
		}
	});
	
});
//****************产地****************************

var index = 0;
var optType_proArea = 0;
var Gob_proAreaList;
function submitProAreaForm(){
	if ($('#proAreaForm').form('validate') == false) {
		layer.msg('验证没有通过!');
		return;
	}
	//$.messager.progress({title : '提示',text : '数据处理中，请稍后....'});
	var it = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	var formUrl = '';
	
	if(optType_proArea==0){
		formUrl = 'proArea_addProAreaAndAppend.action';
	}else{
		formUrl = 'proArea_updateProAreaAndApp.action';
	}
	$('#proAreaForm').form('submit', {
		url : formUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			layer.close(it);
			layer.msg(result);
			initProArea();
		}
	});
}

function initProArea(){
	//清除原来的
    $('#proAreaList').html("");
	$('#proFormTable').hide();
	$('#proAreaTable').show();
	
	var condition = {};
	condition['proArea.proId']=$('#proId').val();
	
	$.post('proArea_findProAreaList.action?tt='+Math.random(),condition,function(result){
		//alert(result);
		Gob_proAreaList = result.rows;
		//加载列表
		var list = result.rows;
		var proArea;
		for(var i=0;i<list.length;i++){
			proArea = list[i];	        
			var imgList = proArea.traceAppdixs;			
			var imgHtml = "";
			for(var j=0;j<imgList.length;j++){
				var appdixUrl = "/nytsyFiles/proimg/"+imgList[j].appdixUrl;
				var appdixName = imgList[j].appdixName;
				imgHtml+= '<a rel="area_group_'+i+'" href="'+appdixUrl+'" title="'+appdixName+'"><img src="'+appdixUrl+'" width="50px;" height="50px;" style="margin-right:20px"/></a>';
			}		
			
			var content = '<div class="" id="div_'+proArea.areaId+'">\
		    	<div class="caozuo"><a class="xiugai" onclick="updateProArea('+proArea.areaId+')">修改</a><a class="shanchu" onclick="deleteProAreas('+proArea.areaId+')">删除</a></div>\
		    	<table class="formtable">\
		          <tr>\
		            <td class="form_label" style="width:150px;">产地名称：</td>\
		            <td class="form_value">'+proArea.areaName+'</td>\
			      </tr>\
			      <tr>\
		            <td class="form_label">产地地址：</td>\
		            <td class="form_value">'+proArea.areaAddr+'</td>\
		          </tr>\
		          <tr>\
		            <td class="form_label">产地概况：</td>\
		            <td  class="form_value"colspan="3">'+proArea.areaIntro+'</td>\
		          </tr>\
		          <tr>\
		            <td class="form_label">土壤环境：</td>\
		            <td  class="form_value"colspan="3">'+proArea.edatope+'</td>\
		          </tr>\
		          <tr>\
		            <td class="form_label">水源环境：</td>\
		            <td  class="form_value"colspan="3">'+proArea.areaWater+'</td>\
		          </tr>\
		          <tr>\
		            <td class="form_label">气候环境：</td>\
		            <td  class="form_value"colspan="3">'+proArea.climatope+'</td>\
		          </tr>\
		          <tr>\
		            <td class="form_label" style="vertical-align:text-top;">附件：</td>\
		            <td  class="form_value"colspan="3" id="img_'+proArea.areaId+'">'+imgHtml+'</td>\
		          </tr>\
		        </table>\
		    </div>';
	        $('#proAreaList').append(content);
	        
			$("a[rel=area_group_"+i+"]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});			
		}	
		
		if(list.length==0){
			$('#proAreaList').append('<div class="noInfo">暂无产地信息</div>');
		}
		
		
	},'JSON')
}
/**
 * 添加
 * @return
 */
function addProArea(){
	optType_proArea = 0;
	$('#proFormTable').show();
	$('#proAreaTable').hide();
	$("#proAreaForm").form("reset");
	$('#hiddenValue').html('');
	$('#hiddenValue').append('<input type="hidden" name="proArea.proId" value="'+$('#proId').val()+'"/>');
	index = 0 ;
	$('#fileList').html('');
	$('#fileQueue').html('');
}



function checkSelect(s,name){
	$('input[type="checkbox"][name="'+name+'"]').attr('checked',s);
}
/**
 * 修改产地信息
 */
function updateProArea(areaId){
	addProArea();
	optType_proArea = 1;
	for(var i=0;i<Gob_proAreaList.length;i++){
		var pro = Gob_proAreaList[i];
		if(pro.areaId==areaId){			
			for(var item in pro){
				var obj = $("[name='proArea."+item+"']:eq(0)");
				if(obj.length>0){
					var className=obj.attr('class');
					if('combo-value'==className){
						o1 = $('#'+item+'_id').attr('class');
						if(o1.indexOf('combotree-f')!=-1){
							$('#'+item+'_id').combotree('setValue',pro[item]);
						}else if(o1.indexOf('combobox-f')!=-1){
							$('#'+item+'_id').combobox('setValue',pro[item]);
						}
					}else{
						obj.val(pro[item]);
					}
				}else{
					if(item!="traceAppdixs"){
						$('#hiddenValue').append("<input type='hidden' name='proArea."+item+"' value='"+pro[item]+"' />");
					}
				}				
			}
			
			//获取图片
			var condition = {};
			condition['traceAppdix.pid']=areaId;
			condition['traceAppdix.appdixType']="1";
			$.post('traceApp_findTraceAppdixsListByPid.action',condition,function(result){
				var appendixList = result;
				for(var j=0;j<appendixList.length;j++){
					var tObj = appendixList[j];					
					var itemTemplate ='';
					itemTemplate += '<div id="udiv_'+tObj.appdixId+'" class="uploadify-queue-item">\<div class="cancel">';				
					var t1 = '<a href="javascript:void(0)" onclick="removeAppd(\''+tObj.appdixId+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+tObj.appdixName+'</span><span class="data"></span>\</div>';						
					itemTemplate = itemTemplate+t1;
					$('#fileQueue').append(itemTemplate);
					$('#fileList').append("<div id='d_"+tObj.appdixId+"'></div>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+tObj.appdixName+"'/>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+tObj.appdixUrl+"'/>");
					index++;
				}
			},'JSON');			
			$("#proAreaForm").form("validate");
			break;
		}
	}	
}

function cancelProArea(){
	$('#proFormTable').hide();
	$('#proAreaTable').show();
}
/**
 * 删除产地信息
 */
function deleteProAreas(areaId){
	var index = layer.confirm('确定要删除?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
	    layer.close(index);
	    $.ajax({
			url : 'proArea_deleteProArea.action',
			data : 'ids=' + areaId,
			type : 'post',
			dataType : 'text',
			success : function(result) {
				layer.msg(result);
				$('#div_'+areaId).remove();
			}
		});
	}, function(){
	    layer.close(index);
	});
}

function removeAppd(fileID){
	$('#d_'+fileID).remove();
	$('#udiv_'+fileID).remove();
}

//**************************************************************************种源管理

function submitProSeedForm(){
	if ($('#proSeedForm').form('validate') == false) {
		layer.msg('验证没有通过!');
		return;
	}
	var it = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	var formUrl = '';
	if(optType_proArea==0){
		formUrl = 'proSeed_addProSeedAndAppdix.action';
	}else{
		formUrl = 'proSeed_updateProSeedAndAppdix.action';
	}
	$('#proSeedForm').form('submit', {
		url : formUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			layer.close(it);
			layer.msg(result);
			initProSeed();
		}
	});
}

var Gob_proSeedList;
function initProSeed(){
	$('#proSeedFormTable').hide();
	$('#proSeedTable').show();
	
	//清除原来的行
	$('#proSeedList').html("");
	
	var condition = {};
	condition['proSeed.proId']=$('#proId').val();

	$.post('proSeed_findProSeedList.action?tt='+Math.random(),condition,function(result){
		//alert(result);
		Gob_proSeedList = result.rows;
		//加载列表
		var list = result.rows;
		var proSeed;		
		for(var i=0;i<list.length;i++){
			proSeed = list[i];		
			imgList = proSeed.traceAppdixs;
			var imgHtml = "";
			for(var j=0;j<imgList.length;j++){
				var appdixUrl = "/nytsyFiles/proimg/"+imgList[j].appdixUrl;
				var appdixName = imgList[j].appdixName;
				imgHtml+= '<a rel="seed_group_'+i+'" href="'+appdixUrl+'" title="'+appdixName+'"><img src="'+appdixUrl+'" width="50px;" height="50px;" style="margin-right:20px"/></a>';
			}
			var content = '<div class="" id="div_'+proSeed.seedId+'">\
		    	<div class="caozuo"><a class="xiugai" onclick="updateProSeed('+proSeed.seedId+')">修改</a><a class="shanchu" onclick="deleteProSeed('+proSeed.seedId+')">删除</a></div>\
		    	<table class="formtable">\
		          <tr>\
		            <td class="form_label" style="width:150px;">种苗名称：</td>\
		            <td  class="form_value">'+proSeed.seedName+'</td>\
		          </tr>\
		          <tr>\
		            <td class="form_label">种苗厂家：</td>\
		            <td  class="form_value">'+proSeed.seedCompany+'</td>\
		          </tr>\
		          <tr>\
		            <td class="form_label">厂家地址：</td>\
		            <td  class="form_value" colspan="3">'+proSeed.seedAddr+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label">特征特性：</td>\
		            <td  class="form_value" colspan="3">'+proSeed.feature+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label" style="vertical-align:text-top;">附件：</td>\
		            <td  class="form_value" colspan="3" id="img_'+proSeed.seedId+'">'+imgHtml+'</td>\
		          </tr>\
		        </table>\
		    </div>';
		    $('#proSeedList').append(content);		
		    $("a[rel=seed_group_"+i+"]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});	
		}
		
		if(list.length==0){
			$('#proSeedList').append('<div class="noInfo">暂无种源信息</div>');
		}
	},'JSON')
}
/**
 * 添加
 * @return
 */
function addProSeed(){
	optType_proArea = 0;
	$('#proSeedFormTable').show();
	$('#proSeedTable').hide();
	$("#proSeedForm").form("reset");
	$('#hiddenValue_seed').html('<input type="hidden" name="proSeed.proId" value="'+$('#proId').val()+'"/>');
	index = 0 ;
	$('#fileList_seed').html('');
	$('#fileQueue_seed').html('');
}
function cancelProSeed(){
	$('#proSeedFormTable').hide();
	$('#proSeedTable').show();
}

function updateProSeed(id){
	addProSeed();
	optType_proArea = 1;
	for(var i=0;i<Gob_proSeedList.length;i++){
		var pro = Gob_proSeedList[i];
		if(pro.seedId==id){
			
			for(var item in pro){
				var obj = $("[name='proSeed."+item+"']:eq(0)");
				if(obj.length>0){
					var className=obj.attr('class');
					if('combo-value'==className){
						o1 = $('#'+item+'_id').attr('class');
						if(o1.indexOf('combotree-f')!=-1){
							$('#'+item+'_id').combotree('setValue',pro[item]);
						}else if(o1.indexOf('combobox-f')!=-1){
							$('#'+item+'_id').combobox('setValue',pro[item]);
						}
					}else{
						obj.val(pro[item]);
					}
				}else{
					if(item!="traceAppdixs"){
						$('#hiddenValue_seed').append("<input type='hidden' name='proSeed."+item+"' value='"+pro[item]+"' />");
					}
				}			
			}
			
			//获取图片
			var condition = {};
			condition['traceAppdix.pid']=id;
			condition['traceAppdix.appdixType']="2";
			$.post('traceApp_findTraceAppdixsListByPid.action',condition,function(result){
				var appendixList = result;
				for(var j=0;j<appendixList.length;j++){
					var tObj = appendixList[j];					
					var itemTemplate ='';
					itemTemplate += '<div id="udiv_'+tObj.appdixId+'" class="uploadify-queue-item">\<div class="cancel">';				
					var t1 = '<a href="javascript:void(0)" onclick="removeAppd(\''+tObj.appdixId+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+tObj.appdixName+'</span><span class="data"></span>\</div>';						
					itemTemplate = itemTemplate+t1;
					$('#fileQueue_seed').append(itemTemplate);
					$('#fileList_seed').append("<div id='d_"+tObj.appdixId+"'></div>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+tObj.appdixName+"'/>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+tObj.appdixUrl+"'/>");
					index++;
				}
			},'JSON');
			
			$("#proSeedForm").form("validate");
			break;
		}
	}
}

function deleteProSeed(id){
	
	var index = layer.confirm('确定删除信息?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
	    layer.close(index);
	    $.ajax({
			url : 'proSeed_deleteProSeed.action',
			data : 'ids=' + id,
			type : 'post',
			dataType : 'text',
			success : function(result) {
				$.messager.show({title : '提示',msg : result});
				$('#div_'+id).remove();
			}
		});
	}, function(){
	    layer.close(index);
	});
}

//**************************************************************************种植、养殖管理

function submitplantRaisedForm(){
	if ($('#plantRaiseForm').form('validate') == false) {
		layer.msg('验证没有通过!');
		return;
	}
	var it = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	var formUrl = '';
	if(optType_proArea==0){
		formUrl = 'plantRaise_addPlantRaise.action';
	}else{
		formUrl = 'plantRaise_updatePlantRaise.action';
	}
	$('#plantRaiseForm').form('submit', {
		url : formUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			layer.close(it);
			layer.msg(result);
			initPlantRaise();
		}
	});
}

var Gob_plantRaiseList;
function initPlantRaise(){
	$('#plantRaiseFormTable').hide();
	$('#plantRaiseTable').show();
	
	//清除原来的行
	$('#plantRaiseList').html("");
	
	var condition = {};
	condition['plantRaise.proId']=$('#proId').val();

	$.post('plantRaise_findPlantRaiseList.action?tt='+Math.random(),condition,function(result){
		//alert(result);
		Gob_plantRaiseList = result.rows;
		//加载列表
		var list = result.rows;
		var plantRaise;
		var name1 = "饲料";
		var name2 = "喂养";
		if(proType==1){
			//1种植类； 2养殖类
			name1 = "肥料";
			name2 = "施肥";
		}
		for(var i=0;i<list.length;i++){
			plantRaise = list[i];					
			imgList = plantRaise.traceAppdixs;
			var imgHtml = "";
			for(var j=0;j<imgList.length;j++){
				var appdixUrl = "/nytsyFiles/proimg/"+imgList[j].appdixUrl;
				var appdixName = imgList[j].appdixName;
				imgHtml+= '<a rel="pr_group_'+i+'" href="'+appdixUrl+'" title="'+appdixName+'"><img src="'+appdixUrl+'" width="50px;" height="50px;" style="margin-right:20px"/></a>';
			}					
			var content = '<div class="" id="div_'+plantRaise.prId+'">\
		    	<div class="caozuo"><a class="xiugai" onclick="updatePlantRaise('+plantRaise.prId+')">修改</a><a class="shanchu" onclick="deletePlantRaise('+plantRaise.prId+')">删除</a></div>\
		    	<table class="formtable">\
		          <tr>\
		            <td class="form_label" style="width: 150px">'+name1+'名称：</td>\
		            <td  class="form_value">'+plantRaise.feedName+'</td>\
		            <td class="form_label" style="width: 150px">'+name1+'厂家：</td>\
		            <td  class="form_value">'+plantRaise.feedCompany+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label">'+name2+'方法：</td>\
		            <td  class="form_value">'+plantRaise.feedWay+'</td>\
		            <td  class="form_label">'+name2+'时间：</td>\
		            <td  class="form_value">'+plantRaise.feedTime+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label">'+name2+'周期：</td>\
		            <td  class="form_value">'+plantRaise.feedCycle+'</td>\
		            <td  class="form_label">'+name2+'用量：</td>\
		            <td  class="form_value">'+plantRaise.dosage+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label" style="vertical-align:text-top;">附件：</td>\
		            <td  class="form_value" colspan="3" id="img_'+plantRaise.prId+'">'+imgHtml+'</td>\
		          </tr>\
		        </table>\
		    </div>';
		    $('#plantRaiseList').append(content);		
		    $("a[rel=pr_group_"+i+"]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});			
		}
		if(list.length==0){
			$('#plantRaiseList').append('<div class="noInfo">暂无喂养信息</div>');
		}
	},'JSON')
}
/**
* 添加
* @return
*/
function addPlantRaise(){
	optType_proArea = 0;
	$('#plantRaiseFormTable').show();
	$('#plantRaiseTable').hide();
	$("#plantRaiseForm").form("reset");
	$('#hiddenValue_plantRaise').html('<input type="hidden" name="plantRaise.proId" value="'+$('#proId').val()+'"/>');
	index = 0 ;
	$('#fileList_plantRaise').html('');
	$('#fileQueue_plantRaise').html('');
}
function cancelPlantRaise(){
	$('#plantRaiseFormTable').hide();
	$('#plantRaiseTable').show();
}

function updatePlantRaise(id){
	addPlantRaise();
	optType_proArea = 1;
	for(var i=0;i<Gob_plantRaiseList.length;i++){
		var pro = Gob_plantRaiseList[i];
		if(pro.prId==id){
			
			for(var item in pro){
				var obj = $("[name='plantRaise."+item+"']:eq(0)");
				if(obj.length>0){
					var className=obj.attr('class');
					if('combo-value'==className){
						o1 = $('#'+item+'_id').attr('class');
						if(o1.indexOf('combotree-f')!=-1){
							$('#'+item+'_id').combotree('setValue',pro[item]);
						}else if(o1.indexOf('combobox-f')!=-1){
							$('#'+item+'_id').combobox('setValue',pro[item]);
						}else if(o1.indexOf('datebox-f')!=-1){
							$('#'+item+'_id').datebox('setValue',pro[item]);
						}
					}else{
						obj.val(pro[item]);
					}
				}else{
					if(item!="traceAppdixs"){
						$('#hiddenValue_plantRaise').append("<input type='hidden' name='plantRaise."+item+"' value='"+pro[item]+"' />");
					}
				}			
			}
			
			//获取图片
			var condition = {};
			condition['traceAppdix.pid']=id;
			condition['traceAppdix.appdixType']="3";
			$.post('traceApp_findTraceAppdixsListByPid.action',condition,function(result){
				var appendixList = result;
				for(var j=0;j<appendixList.length;j++){
					var tObj = appendixList[j];					
					var itemTemplate ='';
					itemTemplate += '<div id="udiv_'+tObj.appdixId+'" class="uploadify-queue-item">\<div class="cancel">';				
					var t1 = '<a href="javascript:void(0)" onclick="removeAppd(\''+tObj.appdixId+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+tObj.appdixName+'</span><span class="data"></span>\</div>';						
					itemTemplate = itemTemplate+t1;
					$('#fileQueue_plantRaise').append(itemTemplate);
					$('#fileList_plantRaise').append("<div id='d_"+tObj.appdixId+"'></div>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+tObj.appdixName+"'/>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+tObj.appdixUrl+"'/>");
					index++;
				}
			},'JSON');
			
			$("#plantRaiseForm").form("validate");
			break;
		}
	}
}

function deletePlantRaise(id){
	var index = layer.confirm('确定删除信息?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
	    layer.close(index);
	    $.ajax({
			url : 'plantRaise_deletePlantRaise.action',
			data : 'ids=' + id,
			type : 'post',
			dataType : 'text',
			success : function(result) {
				$.messager.show({title : '提示',msg : result});
				$('#div_'+id).remove();
			}
		});
	}, function(){
	    layer.close(index);
	});
}

//**************************************************************************防疫管理

function submitpreventionForm(){
	if ($('#preventionForm').form('validate') == false) {
		layer.msg('验证没有通过!');
		return;
	}
	var it = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	var formUrl = '';
	if(optType_proArea==0){
		formUrl = 'prevention_addPrevention.action';
	}else{
		formUrl = 'prevention_updatePrevention.action';
	}
	$('#preventionForm').form('submit', {
		url : formUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			layer.close(it);
			layer.msg(result);
			initPrevention();
		}
	});
}

var Gob_preventionList;
function initPrevention(){
	$('#preventionFormTable').hide();
	$('#preventionTable').show();
	
	//清除原来的行
	$('#preventionList').html("");
	
	var condition = {};
	condition['prevention.proId']=$('#proId').val();

	$.post('prevention_findPreventionList.action?tt='+Math.random(),condition,function(result){
		//alert(result);
		Gob_preventionList = result.rows;
		//加载列表
		var list = result.rows;
		var prevention;		
		for(var i=0;i<list.length;i++){
			prevention = list[i];
			imgList = prevention.traceAppdixs;
			var imgHtml = "";
			for(var j=0;j<imgList.length;j++){
				var appdixUrl = "/nytsyFiles/proimg/"+imgList[j].appdixUrl;
				var appdixName = imgList[j].appdixName;
				imgHtml+= '<a rel="prevention_group_'+i+'" href="'+appdixUrl+'" title="'+appdixName+'"><img src="'+appdixUrl+'" width="50px;" height="50px;" style="margin-right:20px"/></a>';
			}
			var content = '<div class="" id="div_'+prevention.ptId+'">\
		    	<div class="caozuo"><a class="xiugai" onclick="updatePrevention('+prevention.ptId+')">修改</a><a class="shanchu" onclick="deletePrevention('+prevention.ptId+')">删除</a></div>\
		    	<table class="formtable">\
		          <tr>\
		            <td class="form_label" style="width: 150px">药品名称：</td>\
		            <td class="form_value">'+prevention.drugName+'</td>\
		            <td class="form_label" style="width: 150px">药品厂家：</td>\
		            <td class="form_value">'+prevention.drugCompany+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label">用药方法：</td>\
		            <td  class="form_value">'+prevention.drugWay+'</td>\
		            <td  class="form_label">用药时间：</td>\
		            <td  class="form_value">'+prevention.drugTime+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label">用药周期：</td>\
		            <td  class="form_value">'+prevention.drugCycle+'</td>\
		            <td  class="form_label">用药剂量：</td>\
		            <td  class="form_value">'+prevention.dosage+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label">防治对象：</td>\
		            <td  class="form_value" colspan="3">'+prevention.drugObject+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label" style="vertical-align:text-top;">附件：</td>\
		            <td  class="form_value" colspan="3" id="img_'+prevention.ptId+'">'+imgHtml+'</td>\
		          </tr>\
		        </table>\
		    </div>';
		    $('#preventionList').append(content);		
		    $("a[rel=prevention_group_"+i+"]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
		}
		
		if(list.length==0){
			$('#preventionList').append('<div class="noInfo">暂无防疫信息</div>');
		}
	},'JSON')
}
/**
* 添加
* @return
*/
function addPrevention(){
	optType_proArea = 0;
	$('#preventionFormTable').show();
	$('#preventionTable').hide();
	$("#preventionForm").form("reset");
	$('#hiddenValue_prevention').html('<input type="hidden" name="prevention.proId" value="'+$('#proId').val()+'"/>');
	index = 0 ;
	$('#fileList_prevention').html('');
	$('#fileQueue_prevention').html('');
}
function cancelprevention(){
	$('#preventionFormTable').hide();
	$('#preventionTable').show();
}

function updatePrevention(id){
	addPrevention();
	optType_proArea = 1;
	for(var i=0;i<Gob_preventionList.length;i++){
		var pro = Gob_preventionList[i];
		if(pro.ptId==id){			
			for(var item in pro){
				var obj = $("[name='prevention."+item+"']:eq(0)");
				if(obj.length>0){
					var className=obj.attr('class');
					if('combo-value'==className){
						o1 = $('#'+item+'_id').attr('class');
						if(o1.indexOf('combotree-f')!=-1){
							$('#'+item+'_id').combotree('setValue',pro[item]);
						}else if(o1.indexOf('combobox-f')!=-1){
							$('#'+item+'_id').combobox('setValue',pro[item]);
						}else if(o1.indexOf('datebox-f')!=-1){
							$('#'+item+'_id').datebox('setValue',pro[item]);
						}
					}else{
						obj.val(pro[item]);
					}
				}else{
					if(item!="traceAppdixs"){
						$('#hiddenValue_prevention').append("<input type='hidden' name='prevention."+item+"' value='"+pro[item]+"' />");
					}
				}				
			}
			
			//获取图片
			var condition = {};
			condition['traceAppdix.pid']=id;
			condition['traceAppdix.appdixType']="4";
			$.post('traceApp_findTraceAppdixsListByPid.action',condition,function(result){
				var appendixList = result;
				for(var j=0;j<appendixList.length;j++){
					var tObj = appendixList[j];					
					var itemTemplate ='';
					itemTemplate += '<div id="udiv_'+tObj.appdixId+'" class="uploadify-queue-item">\<div class="cancel">';				
					var t1 = '<a href="javascript:void(0)" onclick="removeAppd(\''+tObj.appdixId+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+tObj.appdixName+'</span><span class="data"></span>\</div>';						
					itemTemplate = itemTemplate+t1;
					$('#fileQueue_prevention').append(itemTemplate);
					$('#fileList_prevention').append("<div id='d_"+tObj.appdixId+"'></div>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+tObj.appdixName+"'/>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+tObj.appdixUrl+"'/>");
					index++;
				}
			},'JSON');
			
			$("#preventionForm").form("validate");
			break;
		}
	}
}

function deletePrevention(id){
	var index = layer.confirm('确定删除信息?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
	    layer.close(index);
	    $.ajax({
			url : 'prevention_deletePrevention.action',
			data : 'ids=' + id,
			type : 'post',
			dataType : 'text',
			success : function(result) {
				$.messager.show({title : '提示',msg : result});
				$('#div_'+id).remove();
			}
		});
	}, function(){
	    layer.close(index);
	});
}


//**************************************************************************产品检验管理

function submitProCheckForm(){
	if ($('#proCheckForm').form('validate') == false) {
		layer.msg('验证没有通过!');
		return;
	}
	var it = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	var formUrl = '';
	if(optType_proArea==0){
		formUrl = 'proCheck_addProCheck.action';
	}else{
		formUrl = 'proCheck_updateProCheck.action';
	}
	$('#proCheckForm').form('submit', {
		url : formUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			layer.close(it);
			layer.msg(result);
			initProCheck();
		}
	});
}

var Gob_proCheckList;
function initProCheck(){
	$('#proCheckFormTable').hide();
	$('#proCheckTable').show();
	
	//清除原来的行
	$('#proCheckList').html("");
	
	var condition = {};
	condition['proCheck.proId']=$('#proId').val();

	$.post('proCheck_findProCheckList.action?tt='+Math.random(),condition,function(result){
		//alert(result);
		Gob_proCheckList = result.rows;
		//加载列表
		var list = result.rows;
		var proCheck;		
		for(var i=0;i<list.length;i++){
			proCheck = list[i];		
			imgList = proCheck.traceAppdixs;
			var imgHtml = "";
			for(var j=0;j<imgList.length;j++){
				var appdixUrl = "/nytsyFiles/proimg/"+imgList[j].appdixUrl;
				var appdixName = imgList[j].appdixName;
				imgHtml+= '<a rel="check_group_'+i+'" href="'+appdixUrl+'" title="'+appdixName+'"><img src="'+appdixUrl+'" width="50px;" height="50px;" style="margin-right:20px"/></a>';
			}
			var content = '<div class="" id="div_'+proCheck.checkId+'">\
		    	<div class="caozuo"><a class="xiugai" onclick="updateProCheck('+proCheck.checkId+')">修改</a><a class="shanchu" onclick="deleteProCheck('+proCheck.checkId+')">删除</a></div>\
		    	<table class="formtable">\
		          <tr>\
		            <td class="form_label" style="width: 150px">报告名称：</td>\
		            <td class="form_value">'+proCheck.checkName+'</td>\
		            <td class="form_label" style="width: 150px">报告编号：</td>\
		            <td class="form_value">'+proCheck.checkNum+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label">检验单位：</td>\
		            <td  class="form_value">'+proCheck.checkUnit+'</td>\
		            <td  class="form_label">检验时间：</td>\
		            <td  class="form_value">'+proCheck.checkTime+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label">检验结果：</td>\
		            <td  class="form_value" colspan="3">'+proCheck.checkResult+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label" style="vertical-align:text-top;">附件：</td>\
		            <td  class="form_value" colspan="3" id="img_'+proCheck.checkId+'">'+imgHtml+'</td>\
		          </tr>\
		        </table>\
		    </div>';
		    $('#proCheckList').append(content);		
		    $("a[rel=check_group_"+i+"]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
		}
		
		if(list.length==0){
			$('#proCheckList').append('<div class="noInfo">暂无产品检验信息</div>');
		}
	},'JSON')
}
/**
* 添加
* @return
*/
function addProCheck(){
	optType_proArea = 0;
	$('#proCheckFormTable').show();
	$('#proCheckTable').hide();
	$("#proCheckForm").form("reset");
	$('#hiddenValue_proCheck').html('<input type="hidden" name="proCheck.proId" value="'+$('#proId').val()+'"/>');
	index = 0 ;
	$('#fileList_proCheck').html('');
	$('#fileQueue_proCheck').html('');
}
function cancelProCheck(){
	$('#proCheckFormTable').hide();
	$('#proCheckTable').show();
}

function updateProCheck(id){
	addProCheck();
	optType_proArea = 1;
	$('#fileList_proCheck').html("");
	for(var i=0;i<Gob_proCheckList.length;i++){
		var pro = Gob_proCheckList[i];
		if(pro.checkId==id){			
			for(var item in pro){
				var obj = $("[name='proCheck."+item+"']:eq(0)");
				if(obj.length>0){
					var className=obj.attr('class');
					if('combo-value'==className){
						o1 = $('#'+item+'_id').attr('class');
						if(o1.indexOf('combotree-f')!=-1){
							$('#'+item+'_id').combotree('setValue',pro[item]);
						}else if(o1.indexOf('combobox-f')!=-1){
							$('#'+item+'_id').combobox('setValue',pro[item]);
						}else if(o1.indexOf('datebox-f')!=-1){
							$('#'+item+'_id').datebox('setValue',pro[item]);
						}
					}else{
						obj.val(pro[item]);
					}
				}else{
					if(item!="traceAppdixs"){
						$('#hiddenValue_proCheck').append("<input type='hidden' name='proCheck."+item+"' value='"+pro[item]+"' />");
					}
				}			
			}
			
			//获取图片
			var condition = {};
			condition['traceAppdix.pid']=id;
			condition['traceAppdix.appdixType']="7";
			$.post('traceApp_findTraceAppdixsListByPid.action',condition,function(result){
				var appendixList = result;
				for(var j=0;j<appendixList.length;j++){
					var tObj = appendixList[j];					
					var itemTemplate ='';
					itemTemplate += '<div id="udiv_'+tObj.appdixId+'" class="uploadify-queue-item">\<div class="cancel">';				
					var t1 = '<a href="javascript:void(0)" onclick="removeAppd(\''+tObj.appdixId+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+tObj.appdixName+'</span><span class="data"></span>\</div>';						
					itemTemplate = itemTemplate+t1;
					$('#fileQueue_proCheck').append(itemTemplate);
					$('#fileList_proCheck').append("<div id='d_"+tObj.appdixId+"'></div>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+tObj.appdixName+"'/>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+tObj.appdixUrl+"'/>");
					index++;
				}
			},'JSON');			
			$("#proCheckForm").form("validate");
			break;
		}
	}
}

function deleteProCheck(id){
	
	var index = layer.confirm('确定删除信息?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
	    layer.close(index);
	    $.ajax({
			url : 'proCheck_delteProCheckByIds.action',
			data : 'ids=' + id,
			type : 'post',
			dataType : 'text',
			success : function(result) {
				$.messager.show({title : '提示',msg : result});
				$('#div_'+id).remove();
			}
		});
	}, function(){
	    layer.close(index);
	});
	
}


//**************************************************************************加工管理

function submitProcessForm(){
	if ($('#processForm').form('validate') == false) {
		layer.msg('验证没有通过!');
		return;
	}
	var it = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	var formUrl = '';
	if(optType_proArea==0){
		formUrl = 'process_addProcess.action';
	}else{
		formUrl = 'process_updateProcess.action';
	}
	$('#processForm').form('submit', {
		url : formUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			layer.close(it);
			layer.msg(result);
			initProcess();
		}
	});
}

var Gob_processList;
function initProcess(){
	$('#processFormTable').hide();
	$('#processTable').show();
	
	//清除原来的行
	$('#processList').html("");
	
	var condition = {};
	condition['process.proId']=$('#proId').val();

	$.post('process_findProcessList.action?tt='+Math.random(),condition,function(result){
		//alert(result);
		Gob_processList = result.rows;
		//加载列表
		var list = result.rows;
		var process;		
		for(var i=0;i<list.length;i++){
			process = list[i];		
			imgList = process.traceAppdixs;
			var imgHtml = "";
			for(var j=0;j<imgList.length;j++){
				var appdixUrl = "/nytsyFiles/proimg/"+imgList[j].appdixUrl;
				var appdixName = imgList[j].appdixName;
				imgHtml+= '<a rel="process_group_'+i+'" href="'+appdixUrl+'" title="'+appdixName+'"><img src="'+appdixUrl+'" width="50px;" height="50px;" style="margin-right:20px"/></a>';
			}
			var content = '<div class="" id="div_'+process.processId+'">\
		    	<div class="caozuo"><a class="xiugai" onclick="updateProcess('+process.processId+')">修改</a><a class="shanchu" onclick="deleteProcess('+process.processId+')">删除</a></div>\
		    	<table class="formtable">\
		          <tr>\
		            <td class="form_label" style="width: 150px">加工厂家：</td>\
		            <td class="form_value">'+process.processCompany+'</td>\
		            <td class="form_label" style="width: 150px">厂家地址：</td>\
		            <td class="form_value">'+process.processAddr+'</td>\
		          </tr>\
		          <tr>\
		            <td class="form_label">负责人：</td>\
		            <td  class="form_value">'+process.processUser+'</td>\
		            <td  class="form_label">加工时间：</td>\
		            <td  class="form_value">'+process.processTime+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label" style="vertical-align:text-top;">附件：</td>\
		            <td  class="form_value" colspan="3" id="img_'+process.processId+'">'+imgHtml+'</td>\
		          </tr>\
		        </table>\
		    </div>';
		    $('#processList').append(content);		
		    $("a[rel=process_group_"+i+"]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
		}
		if(list.length==0){
			$('#processList').append('<div class="noInfo">暂无加工包装信息</div>');
		}
	},'JSON')
}
/**
* 添加
* @return
*/
function addProcess(){
	optType_proArea = 0;
	$('#processFormTable').show();
	$('#processTable').hide();
	$("#processForm").form("reset");
	$('#hiddenValue_process').html('<input type="hidden" name="process.proId" value="'+$('#proId').val()+'"/>');
	index = 0 ;
	$('#fileList_process').html('');
	$('#fileQueue_process').html('');
}
function cancelProcess(){
	$('#processFormTable').hide();
	$('#processTable').show();
}

function updateProcess(id){
	addProcess();
	optType_proArea = 1;
	$('#fileList_process').html("");
	for(var i=0;i<Gob_processList.length;i++){
		var pro = Gob_processList[i];
		if(pro.processId==id){			
			for(var item in pro){
				var obj = $("[name='process."+item+"']:eq(0)");
				if(obj.length>0){
					var className=obj.attr('class');
					if('combo-value'==className){
						o1 = $('#'+item+'_id').attr('class');
						if(o1.indexOf('combotree-f')!=-1){
							$('#'+item+'_id').combotree('setValue',pro[item]);
						}else if(o1.indexOf('combobox-f')!=-1){
							$('#'+item+'_id').combobox('setValue',pro[item]);
						}else if(o1.indexOf('datebox-f')!=-1){
							$('#'+item+'_id').datebox('setValue',pro[item]);
						}
					}else{
						obj.val(pro[item]);
					}
				}else{
					if(item!="traceAppdixs"){
						$('#hiddenValue_process').append("<input type='hidden' name='process."+item+"' value='"+pro[item]+"' />");
					}
				}
				
			}
			
			//获取图片
			var condition = {};
			condition['traceAppdix.pid']=id;
			condition['traceAppdix.appdixType']="5";
			$.post('traceApp_findTraceAppdixsListByPid.action',condition,function(result){
				var appendixList = result;
				for(var j=0;j<appendixList.length;j++){
					var tObj = appendixList[j];					
					var itemTemplate ='';
					itemTemplate += '<div id="udiv_'+tObj.appdixId+'" class="uploadify-queue-item">\<div class="cancel">';				
					var t1 = '<a href="javascript:void(0)" onclick="removeAppd(\''+tObj.appdixId+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+tObj.appdixName+'</span><span class="data"></span>\</div>';						
					itemTemplate = itemTemplate+t1;
					$('#fileQueue_process').append(itemTemplate);
					$('#fileList_process').append("<div id='d_"+tObj.appdixId+"'></div>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+tObj.appdixName+"'/>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+tObj.appdixUrl+"'/>");
					index++;
				}
			},'JSON');			
			$("#processForm").form("validate");
			break;
		}
	}
}

function deleteProcess(id){
	var index = layer.confirm('确定删除信息?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
	    layer.close(index);
	    $.ajax({
			url : 'process_deleteProcessByIds.action',
			data : 'ids=' + id,
			type : 'post',
			dataType : 'text',
			success : function(result) {
				$.messager.show({title : '提示',msg : result});
				$('#div_'+id).remove();
			}
		});
	}, function(){
	    layer.close(index);
	});
}
//**************************************************************************仓储运输管理

function submitStoreTransportForm(){
	if ($('#storeTransportForm').form('validate') == false) {
		layer.msg('验证没有通过!');
		return;
	}
	var it = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	var formUrl = '';
	if(optType_proArea==0){
		formUrl = 'storetransport_addStoreTransport.action';
	}else{
		formUrl = 'storetransport_updateStoreTransport.action';
	}
	$('#storeTransportForm').form('submit', {
		url : formUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			layer.close(it);
			layer.msg(result);
			initStoreTransport();
		}
	});
}

var Gob_storeTransportList;
function initStoreTransport(){
	$('#storeTransportFormTable').hide();
	$('#storeTransportTable').show();
	
	//清除原来的行
	$('#storeTransportList').html("");
	
	var condition = {};
	condition['storeTransport.proId']=$('#proId').val();

	$.post('storetransport_findStoreTransportList.action?tt='+Math.random(),condition,function(result){
		//alert(result);
		Gob_storeTransportList = result.rows;
		//加载列表
		var list = result.rows;
		var storeTransport;		
		for(var i=0;i<list.length;i++){
			storeTransport = list[i];		
			imgList = storeTransport.traceAppdixs;
			var imgHtml = "";
			for(var j=0;j<imgList.length;j++){
				var appdixUrl = "/nytsyFiles/proimg/"+imgList[j].appdixUrl;
				var appdixName = imgList[j].appdixName;
				imgHtml+= '<a rel="st_group_'+i+'" href="'+appdixUrl+'" title="'+appdixName+'"><img src="'+appdixUrl+'" width="50px;" height="50px;" style="margin-right:20px"/></a>';
			}
			var content = '<div class="" id="div_'+storeTransport.stId+'">\
		    	<div class="caozuo"><a class="xiugai" onclick="updateStoreTransport('+storeTransport.stId+')">修改</a><a class="shanchu" onclick="deleteStoreTransport('+storeTransport.stId+')">删除</a></div>\
		    	<table class="formtable">\
		          <tr>\
		            <td class="form_label" style="width: 150px">仓储方式：</td>\
		            <td  class="form_value">'+storeTransport.storageWay+'</td>\
			      </tr>\
			      <tr>\
		            <td  class="form_label">仓储条件：</td>\
		            <td  class="form_value">'+storeTransport.storageCondi+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label">运输方式：</td>\
		            <td  class="form_value">'+storeTransport.transportWay+'</td>\
		          </tr>\
		          <tr>\
		            <td  class="form_label" style="vertical-align:text-top;">附件：</td>\
		            <td  class="form_value" id="img_'+storeTransport.stId+'">'+imgHtml+'</td>\
		          </tr>\
		        </table>\
		    </div>';
		    $('#storeTransportList').append(content);		
		    $("a[rel=st_group_"+i+"]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
		}
		if(list.length==0){
			$('#storeTransportList').append('<div class="noInfo">暂无仓储运输信息</div>');
		}
	},'JSON')
}
/**
* 添加
* @return
*/
function addStoreTransport(){
	optType_proArea = 0;
	$('#storeTransportFormTable').show();
	$('#storeTransportTable').hide();
	$("#storeTransportForm").form("reset");
	$('#hiddenValue_storeTransport').html('<input type="hidden" name="storeTransport.proId" value="'+$('#proId').val()+'"/>');
	index = 0 ;
	$('#fileList_storeTransport').html('');
	$('#fileQueue_storeTransport').html('');
}
function cancelStoreTransport(){
	$('#storeTransportFormTable').hide();
	$('#storeTransportTable').show();
}

function updateStoreTransport(id){
	addStoreTransport();
	optType_proArea = 1;
	for(var i=0;i<Gob_storeTransportList.length;i++){
		var pro = Gob_storeTransportList[i];
		if(pro.stId==id){			
			for(var item in pro){
				var obj = $("[name='storeTransport."+item+"']:eq(0)");
				if(obj.length>0){
					var className=obj.attr('class');
					if('combo-value'==className){
						o1 = $('#'+item+'_id').attr('class');
						if(o1.indexOf('combotree-f')!=-1){
							$('#'+item+'_id').combotree('setValue',pro[item]);
						}else if(o1.indexOf('combobox-f')!=-1){
							$('#'+item+'_id').combobox('setValue',pro[item]);
						}else if(o1.indexOf('datebox-f')!=-1){
							$('#'+item+'_id').datebox('setValue',pro[item]);
						}
					}else{
						obj.val(pro[item]);
					}
				}else{
					if(item!="traceAppdixs"){
						$('#hiddenValue_storeTransport').append("<input type='hidden' name='storeTransport."+item+"' value='"+pro[item]+"' />");
					}
				}
				
			}
			
			//获取图片
			var condition = {};
			condition['traceAppdix.pid']=id;
			condition['traceAppdix.appdixType']="6";
			$.post('traceApp_findTraceAppdixsListByPid.action',condition,function(result){
				var appendixList = result;
				for(var j=0;j<appendixList.length;j++){
					var tObj = appendixList[j];					
					var itemTemplate ='';
					itemTemplate += '<div id="udiv_'+tObj.appdixId+'" class="uploadify-queue-item">\<div class="cancel">';				
					var t1 = '<a href="javascript:void(0)" onclick="removeAppd(\''+tObj.appdixId+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+tObj.appdixName+'</span><span class="data"></span>\</div>';						
					itemTemplate = itemTemplate+t1;
					$('#fileQueue_storeTransport').append(itemTemplate);
					$('#fileList_storeTransport').append("<div id='d_"+tObj.appdixId+"'></div>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixName' value='"+tObj.appdixName+"'/>");
					$('#d_'+tObj.appdixId).append("<input type='hidden' name='traceAppList["+index+"].appdixUrl' value='"+tObj.appdixUrl+"'/>");
					index++;
				}
			},'JSON');			
			$("#storeTransportForm").form("validate");
			break;
		}
	}
}

function deleteStoreTransport(id){
	var index = layer.confirm('确定删除信息?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
	    layer.close(index);
	    $.ajax({
			url : 'storetransport_deleteStoreTransportByIds.action',
			data : 'ids=' + id,
			type : 'post',
			dataType : 'text',
			success : function(result) {
				$.messager.show({title : '提示',msg : result});
				$('#div_'+id).remove();
			}
		});
	}, function(){
	    layer.close(index);
	});
}
//**************************************************************************批次管理

function submitProBatchForm(){
	if ($('#proBatchForm').form('validate') == false) {
		layer.msg('验证没有通过!');
		return;
	}
	var it = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	var formUrl = '';
	if(optType_proArea==0){
		formUrl = 'proBatch_addProBatch.action';
	}else{
		formUrl = 'proBatch_updateProBatch.action';
	}
	$('#proBatchForm').form('submit', {
		url : formUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			layer.close(it);
			layer.msg(result);
			initProBatch(1,10);
		}
	});
}

var Gob_proBatchList;
function initProBatch(page,rows){
	$('#proBatchFormTable').hide();
	$('#proBatchTab').show();
	
	//清除原来的行
	$('#proBatchTable').html("");
	
	var condition = {};
	condition['proBatch.proId']=$('#proId').val();
	condition['page']=page;
	condition['rows']=rows;
	
	$.post('proBatch_findProBatchList.action?tt='+Math.random(),condition,function(result){
		//alert(result);
		Gob_proBatchList = result.rows;
		//加载列表
		var list = result.rows;
		var proBatch;		
		
		var content = '<tr bgcolor="#FFFFFF">\
			<td width="150px">批次编号</td>\
			<td width="150px">批次生产日期</td>\
			<td width="150px">批次二维码</td>\
			<td width="100px">批次状态</td>\
			<td width="100px">操作</td>\
			</tr>';
			
		$('#proBatchTable').append(content);
		
		for(var i=0;i<list.length;i++){
			proBatch = list[i];		
			var batchId = proBatch.batchId;
			var codeImg = proBatch.codeImg;
			content = '<tr bgcolor="#FFFFFF">\
				    <td>'+proBatch.batchNo+'</td>\
				    <td>'+proBatch.proTime+'</td>\
				    <td>'+proBatch.dimenno+'</td>\
				    <td>'+(proBatch.batchSts==0?"<span style=\"color:blue;\">待审</span>":"<span style=\"color:green;\">审核通过</span>")+'</td>\
				    <td>'+(proBatch.batchSts==0?"<a onclick=\"deleteProBatch('"+batchId+"');\">删除</a>":"<a onclick=\"showImg('"+codeImg+"');\">查看标签</a>")+'</td>\
				  </tr>';

			$('#proBatchTable').append(content);
		}
		
		//初始化分页
		
		var pre = 1;
		var next = 1;
		var pcount = 0;
		
		pcount = parseInt((result.total+rows-1)/rows);
		
		if(pcount>page){
			next = page+1;
		}else{
			next = page;
		}
		
		if(page==1){
			pre = page;
		}
		if(page>pcount){
			pre = page-1;
		}
		
		
		var pc = '第'+page+'页/共'+pcount+'页 <a onclick="initProBatch(1,10)">首页</a> <a onclick="initProBatch('+pre+',10)">上一页</a> <a onclick="initProBatch('+next+',10)">下一页</a> <a onclick="initProList('+pcount+',10)">尾页</a> 跳转至：<select onchange="toPage(this.value);" >';
		
		for(var j=1;j<=pcount;j++){
		pc+='<option value="'+j+'" '+(j==page?"selected = \"selected\"":"")+' >'+j+'</option>';
		}
		pc+='</select>';
		
		$('.page').html(pc);

	},'JSON')
}
/**
* 添加
* @return
*/
function addProBatch(){
	optType_proArea = 0;
	$('#proBatchFormTable').show();
	$('#proBatchTab').hide();
	$('#proBatchCodeImg').hide();	
	$("#proBatchForm").form("reset");
	$('#hiddenValue_proBatch').html('<input type="hidden" name="proBatch.proId" value="'+$('#proId').val()+'"/>');
	index = 0 ;
}
function cancelProBatch(){
	$('#proBatchFormTable').hide();
	$('#proBatchTab').show();
	$('#proBatchCodeImg').hide();
}

/**
 * 显示二维码信息
 * @param codeImg
 * @return
 */
function showImg(codeImg){
	$("#Dimenno_Img").val(codeImg);		

	$("#code_Img").html("<img src='"+filePath+"qrcode/"+codeImg+"' />");
	$("#batchCode").window("open");
}

//下载二维码图片
function downloadCodeImg(index){
	var img = $("#Dimenno_Img").val();
	if(index>1){
		img=img.substring(0,img.indexOf(".png"));
		img=img+"_"+index+".png"
	}
	//判断文件是否存在可以下载
	$.ajax({
		  url: filePath+"qrcode/"+img,
		  cache: false,
		  error : function(){
			alert("图片文件不存在!");
		  },
		  success: function(){
			window.location.href="download.action?fileName="+img;
		  }
	});
}


function deleteProBatch(id){
	var index = layer.confirm('确定删除信息?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
	    layer.close(index);
	    $.ajax({
			url : 'proBatch_deleteProBatch.action',
			data : 'ids=' + id,
			type : 'post',
			dataType : 'text',
			success : function(result) {
				$.messager.show({title : '提示',msg : result});
				initProBatch(1,10);
			}
		});
	}, function(){
	    layer.close(index);
	});
}