
var urlType = 1;
$(function(){
	parent.layer.closeAll();
	
	initProList(1,10);
	
	
	
	initUploadFile();
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'materials'},
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


function initUploadFile(flag){
	$('#fileQueue').html('');
	$("#maImg").uploadify({
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
		'onSWFReady':function(){
			if(flag){
				$('#maImg').uploadify('disable', false);
				$('#prev').addClass('disabled');
				$('#a_pre').css('color','#808080');
				$("a#a_pre").attr('href','');
			}else{
				$('#maImg').uploadify('disable', true);
				$('#prev').removeClass('disabled');
				$('#a_pre').css({'color':'white'});
			}
		},
		'onUploadSuccess' : function(file, data, response) {
			$('input[name="materials.maImg"]').val(data);
			$('#maImg').uploadify('disable', true);
			$('#prev').removeClass('disabled');
			$('#a_pre').css({'color':'white'});
			$("a#a_pre").attr('href','/nytsyFiles/element/'+data);
			$("a#a_pre").fancybox();
		}
	});
}

var gRows;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['materials.recId'] = $('#h_recId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('materials_findPager.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gRows = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var rowList = result.rows;
			for(var i=0;i<rowList.length;i++){
				var row = rowList[i];
				var maName = (row.maName==null||row.maName=="")?"":row.maName;
				var buyTime = (row.buyTime==null||row.buyTime=="")?"":row.buyTime;
				var totalNum = (row.totalNum==null||row.totalNum=="")?"":row.totalNum;
				var addr = (row.addr==null||row.addr=="")?"":row.addr;
				
//				var fpath = '/nytsyFiles/element/';
//				var imgp = '<a rel="previewImg" href="'+fpath+maImg+'" title="'+niName+'"><img alt="预览" src="'+fpath+maImg+'" style="width:150px; height:60px;padding:2px;"></a>';
				
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+maName+'</td>\
			        <td>'+buyTime+'</td>\
			        <td>'+totalNum+'</td>\
			        <td>'+addr+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteNodeinfo('+row.maId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			
			$("a[rel=previewImg]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
			
			//没有数据
			if(rowList.length==0){
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
function deleteNodeinfo(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('materials_delete.action',{ids:id},function(result){
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
	var formUrl = 'materials_add.action';
	if(urlType!=1){
		formUrl = 'materials_update.action';
	}
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


//添加档案
function addRecord(flag){
	$('.formtitle span').html('添加原料信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset')
	if(!flag){
		$('.tools').hide();
	}
//	$('#maImg').uploadify('disable', false);
//	$('#prev').addClass('disabled');
//	$('#a_pre').css('color','#808080');
//	$("a#a_pre").attr('href','');
	initUploadFile(true);
}
//修改档案
function updateRecord(index){
	var row = gRows[index];
	if(row){
		addRecord(true);
		urlType = 2;
		
		$('.formtitle span').html('修改原料信息');
		
		for(var item in row){
			$('input[name="materials.'+item+'"]').val(row[item]);
		}
		
		try {
			if(row.maImg){
				initUploadFile(false);
				$("a#a_pre").attr('href','/nytsyFiles/element/'+row.maImg);
				$("a#a_pre").fancybox();
			}else{
				initUploadFile(true);
			}
		} catch (e) {
			// TODO: handle exception
		}
		
		
		$('#buyTime').datebox('setValue',row.buyTime);
		
		$('#recordForm').form('validate');
	}
	
}

function exitContent(){
	$('#proDiv').show();
	$('.center_content3').hide();
	
	if(urlType==1){
		$('.tools').show();
	}
}
