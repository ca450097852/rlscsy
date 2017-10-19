var basePath;
var urlType = 1;

var formUrl = 'proTypeArea_addProTypeArea.action';	

$(function(){
	basePath = $("#basePath").val();
	parent.layer.closeAll();
	
	initProList(1,10);
	
	$("#areaImg").uploadify({
		'swf' : '../uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'elementApp_uploadFile.action;jsessionid='	+ $('#sessionId').val(),
		'buttonText' : '上传图片',
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
			$('#areaImg2').val(data);
			$('#areaImg').uploadify('disable', true);
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
	$('#proTab tr:not(:first)').remove();
	$.post('proTypeArea_findProTypeAreaList.action?tt='+Math.random(),condition,function(result){		
		if(result){		
			proTypeList = result.rows;
			for(var i=0;i<proTypeList.length;i++){
				var proTypeArea = proTypeList[i];			
				
				var massifName = "地块信息";
				var areaType = "种植基地";
				if(proTypeArea.areaType==2){
					areaType = "养殖基地";
					massifName = "场地信息";
				}
				
				var ptaId = "id_"+proTypeArea.ptaId;	
				var areaImg = "";
				if(proTypeArea.areaImg!=""){
					areaImg = '<a rel="'+ptaId+'" href="/nytsyFiles/element/'+proTypeArea.areaImg+'"><img width="50" height=50" src="/nytsyFiles/element/'+proTypeArea.areaImg+'"/></a>'
				}
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+proTypeArea.areaName+'</td>\
			        <td>'+proTypeArea.chandi+proTypeArea.areaAddr+'</td>\
			        <td>'+areaType+'</td>\
					<td>'+proTypeArea.scale+'</td>\
					<td>'+areaImg+'</td>\
					<td><a class="tablelink" href="massif.jsp?ptaId='+proTypeArea.ptaId+'&areaType='+proTypeArea.areaType+'" target="rightFrame">'+massifName+'</a>&nbsp;&nbsp;<a class="tablelink" onclick="updateRecord('+i+')">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="tablelink" onclick="deleteArea('+proTypeArea.ptaId+')">删除</a>\
			        </td>\
			        </tr>';			  
				
			     $('#proTab').append(content);		     
				 $("a[rel="+ptaId+"]").fancybox();

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
	$('.formtitle span').html('添加基地信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset')
	
	 setup();
	 preselect('广东省');
	
	formUrl = 'proTypeArea_addProTypeArea.action';	
	
	if(!flag){
		$('.tools').hide();
	}
}


//删除
function deleteArea(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
			$.ajax( {
				url : 'proTypeArea_deleteProTypeArea.action',
				data : 'ids='+ id,
				type : 'post',
				dataType : 'text',
				success : function(result) {
					layer.msg(result);
					initProList(1,10);
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
	var province = $("#s1").val();
	var city = $("#s2").val();
	var town = $("#s3").val();
	
	if(province=='省份'){
		$.messager.show({title:'消息',msg:'请选择省份!',});
		return;
	}
	
	if(city=='城市'){
		$.messager.show({title:'消息',msg:'请选择城市!',});
		return;
	}
	
	if(town=='区、县'&&$("select[name=town] option").size()>1){
		$.messager.show({title:'消息',msg:'请选择区、县!',});
		return;
	}
	if(town=='区、县'&&$("select[name=town] option").size()==1){
		town='';
	}
	
	var areaImg2 = $("#areaImg2").val();
	if(areaImg2==""){
		layer.msg("基地平面图必须提供，请上传！");
		return;
	}
	
	var params = {};
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

//修改档案
function updateRecord(index){
	var proTypeArea = proTypeList[index];
	if(proTypeArea){
		
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset')
		
		$('.formtitle span').html('修改基地信息');
		
		$('#ptaId').val(proTypeArea.ptaId);
		
/*		$('#ptqId').combobox({    
			url : 'proTypeQrcode_getLoginProType.action',
		    valueField:'value',    
		    textField:'text',
		    value:proTypeArea.ptqId
		}); */ 
		
		formUrl = 'proTypeArea_updateProTypeArea.action';	

		 setup();
		 preselect('广东省');
		


		$('#areaName').val(proTypeArea.areaName);		
		$('#areaAddr').val(proTypeArea.areaAddr);		
		$('#scale').val(proTypeArea.scale);		
		
		//$('#areaAcreage').val(proTypeArea.areaAcreage);				
		//$('#areaValue').val(proTypeArea.areaValue);				
		//$('#startTime').datebox('setValue',proTypeArea.startTime);		
		//$('#getTime').val(proTypeArea.getTime);
		
		$('#lat').val(proTypeArea.lat);
		$('#lng').val(proTypeArea.lng);		
		 $("#s1").val(proTypeArea.province);
		 $("#s2").val(proTypeArea.city);		 
		 $("#s2").change();		 
		 $("#s3").val(proTypeArea.town);
		
		 $("#entId").val(proTypeArea.entId);
		 
		 $("#areaType").combobox("setValue",proTypeArea.areaType);		 
		 
		 $("input[name='proTypeArea.areaImg']").val(proTypeArea.areaImg);
			
			if(proTypeArea.areaImg!=''){
				var content = '<div class="uploadify-queue-item" id="SWFUpload_0_0"><div class="uploadcancel">\
								<a href="javascript:removeAppd();">X</a></div>\
								<span class="fileName">'+proTypeArea.areaImg+'</span></div>';
				$('#fileQueue').html(content);
				
				$('#prev').removeClass('disabled');
				$('#a_pre').css({'color':'white'});
				$("a#a_pre").attr('href','/nytsyFiles/element/'+proTypeArea.areaImg);
				$("a#a_pre").fancybox();
				
				$('#areaImg').uploadify('disable', true);
			}

		$('#recordForm').form('validate')
	}
	
}

function removeAppd(){
	$('#fileQueue').html("");
	$("input[name='proTypeArea.areaImg']").val("");
	$('#areaImg').uploadify('disable', false);
	$("a#a_pre").attr('href','');
}

function exitContent(){
	$('#proDiv').show();
	$('.center_content3').hide();
		
}

//这个函数是必须的，因为在geo.js里每次更改地址时会调用此函数
function promptinfo()
{
   /* var address = document.getElementById('address');
    var s1 = document.getElementById('s1');
    var s2 = document.getElementById('s2');
    var s3 = document.getElementById('s3');
    address.value = s1.value + s2.value + s3.value;*/
}
