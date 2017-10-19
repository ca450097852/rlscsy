var basePath;
var urlType = 1;
$(function(){
	basePath = $("#basePath").val();
	parent.layer.closeAll();
	
	initProList(1,10);
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'proTypeDetail'},
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
	
	 
	 setup();
	 preselect('广东省')

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
	$.post('proTypeQrcode_getLoginProTypeQrcode.action?tt='+Math.random(),condition,function(result){		
		if(result){
			//显示条数
			//$('#total').html(result.total);
			//$('#page').html(page);			
			proTypeList = result;
			for(var i=0;i<proTypeList.length;i++){
				var proTypeQrcode = proTypeList[i];
				
				var cf = proTypeQrcode.certificate;				
				var certificate = cf==1?"有机":cf==2?"绿色":cf==3?"无公害产品":cf==4?"地理标志认证":"无";
						
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+proTypeQrcode.typeName+'</td>\
			        <td>'+certificate+'</td>\
			        <td>'+proTypeQrcode.quantity+'</td>\
					<td>'+proTypeQrcode.listed+'</td>\
			        <td>'+proTypeQrcode.salearea+'</td>\
			        <td>'+proTypeQrcode.brandName+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        </td>\
			        </tr>';
			        //&nbsp;&nbsp;&nbsp;&nbsp;<a class="tablelink" onclick="updateChandi('+i+')">产地标注</a>
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(proTypeList.length==0){
				$('#proTab').append('<tr><td align="center" colspan="'+$('#proTab tr:first').find('th').length+'">暂无数据</td></tr>');
			}
			
			/*
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
			*/
			
		}
		
	},'JSON');
	
	$("#uploadify").uploadify({
		'swf'      		: basePath+'uploadify/uploadify.swf',
		'fileObjName' 	: 'uploadify',
		'uploader' 		: basePath+'proTypeQrcode_uploadTypeImg.action;jsessionid='+$('#jsessionid').val(),
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
			$("a#pre_"+file.id).attr('href','/nytsyFiles/protype/'+data);
        	$("a#pre_"+file.id).fancybox();
        	$('#typeImg').val(data);
		}
	});
	
}

//删除
function deletesaleinfo(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('saleinfo_deleteSaleinfo.action',{ids:id},function(result){
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
	var formUrl = 'proTypeQrcode_updateProTypeQrcode.action';	
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
	var proTypeQrcode = proTypeList[index];
	if(proTypeQrcode){
		
		$('#proDiv').hide();
		$('.center_content3').show();
		$('#recordForm').form('reset')
		
		$('.formtitle span').html('修改种类信息');
		
		$('#ptqId').val(proTypeQrcode.ptqId);
		$('#certificate').combobox('setValue',proTypeQrcode.certificate);
		$('#quantity').val(proTypeQrcode.quantity);		
		$('#listed').val(proTypeQrcode.listed);		
		
		$('input[name="proTypeQrcode.brandName"]').val(proTypeQrcode.brandName);
		$('textarea[name="proTypeQrcode.proDesc"]').val(proTypeQrcode.proDesc);
		
		$('#salearea').combobox('setValue',proTypeQrcode.salearea);
		
		var itemTemplate ='';
		itemTemplate +='<div class="uploadify-queue-item" id="udiv_'+proTypeQrcode.typeImg+'">'+
		'<div class="uploadcancel">'+
		'<a id="pre_'+proTypeQrcode.ptqId+'" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" href="/nytsyFiles/protype/'+proTypeQrcode.typeImg+'">预览</a>'+
		'<a href="javascript:removeAppd('+proTypeQrcode.typeImg+');">X</a>'+
		'</div>'+
		'<span class="fileName">'+proTypeQrcode.typeImg+'</span><span class="data"></span>'+
		'</div>';
		
		if(proTypeQrcode.typeImg!=''){
			$('#typeImg').val(proTypeQrcode.typeImg);
			
			$('#fileQueue').html(itemTemplate);
			$("a#pre_"+proTypeQrcode.ptqId).fancybox();
		}
		
		
		$('#recordForm').form('validate')
	}
	
}

function removeAppd(fileName){
	$('#typeImg').val("");
	$('#udiv_'+fileName).remove();
	
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

//修改产地
function updateChandi(index){
	var proTypeQrcode = proTypeList[index];
	
	if(proTypeQrcode){
		var ptqId = proTypeQrcode.ptqId;
		var typeName = proTypeQrcode.typeName;
		$("#cdptqId").val(ptqId);
		$('#cdDataGrid').datagrid({    
		    url:'proTypeArea_findProTypeAreaList.action',
		    toolbar: '#tb',
		    queryParams: {'proTypeArea.ptqId': ptqId},
		    fit:true,
		    rownumbers:true,
		    singleSelect:true,
		    fitColumns:true,//允许表格自动缩放，以适应父容器  
		    columns:[[    
		        {field:'chandi',title:'产地',width:200,align:'center',
					formatter: function(value,row,index){ 
					var displayValue = row.province+row.city+row.town;					
					return displayValue;
				}},    
		        {field:'scale',title:'规模(面积或者年产量)',width:100,align:'center'},    
		        {field:'ptaId',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
					var t1='<a href=\'javascript:void(0);\' onclick=\'deleteChandi('+index+');\'>删除</a>'										
					return t1;
				}}    
		    ]]    
		});  
		
		$("#chandiDialog").dialog("open").dialog("center").dialog("setTitle",typeName+" 产地标注");
		
	}
}

function appendChandi(){
	
	var scale = $("#scale").val();
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
	
	if(scale==''){
		$.messager.show({title:'消息',msg:'请填写规模!',});
		return;
	}
	
	if(town=='区、县'&&$("select[name=town] option").size()==1){
		town='';
	}
	
	var ptqId = $("#cdptqId").val();
	
	var data = {'proTypeArea.ptqId': ptqId,'proTypeArea.province': province,'proTypeArea.city': city,'proTypeArea.town': town,'proTypeArea.scale': scale}
	
	$("#scale").val('');
	$("#s2").val('城市');
	$("#s3").val('区、县');
	
	$.post('proTypeArea_addProTypeArea.action',data,function(result){
		layer.msg(result);
		$('#cdDataGrid').datagrid("reload");
	},'TEXT');
}

function deleteChandi(i){
	var proTypeArea = $('#cdDataGrid').datagrid("getRows")[i];		
	var id = proTypeArea.ptaId;	
	$.messager.confirm('提示', '是否删除选中数据?', function(r) {
		if (r) {
			$.ajax( {
				url : 'proTypeArea_deleteProTypeArea.action',
				data : 'ids='+ id,
				type : 'post',
				dataType : 'text',
				success : function(result) {
					$.messager.show( {title : '提示',msg : result});
					$('#cdDataGrid').datagrid('reload');
				}
			});
		}
	});
}