
var urlType = 1;
$(function(){
	initProList(1,10);
	parent.layer.closeAll();
	
	var item = [{value:1,text:'普通',group:'企业'},{value:2,text:'三品一标',group:'企业'},{value:3,text:'种植类',group:'分类'},{value:4,text:'畜牧类',group:'分类'}];
	
	$('#h_recordType').combobox({'onSelect':function(){
		recordTypeChange($('#h_recordType').combobox('getValue'));
	},
	valueField:'value',
    textField:'text',
    groupField:'group',
    data:item
	});
	
});
var grecordList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['entId']=$('#h_entId').val();
	$.post('record_findRecordList.action?tt='+Math.random(),condition,function(result){
		if(result){
			$('#proTab tr:not(:first)').remove();
			grecordList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var recordList = result.rows;
			//<td><input type="checkbox" class="proCheck" value="'+pro.proId+'" /></td>\
			//<a class="tablelink" onclick="showDimenno('+i+')">查看二维码标签</a>\
			//<a class="tablelink" href="elementList.jsp?recId='+record.recId+'">设置</a>\
			//<a class="tablelink" href="javascript:showElementList('+record.recId+',this);">设置</a>\
			//<a class="tablelink" href="elementList.jsp?recId='+record.recId+'">设置</a>\
			for(var i=0;i<recordList.length;i++){
				var record = recordList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+record.recName+'</td>\
			        <td>'+record.crttime+'</td>\
			        <td>'+record.seq+'</td>\
			        <td>'+((record.objTypeId==1||record.objTypeId==2)?"企业":(record.objTypeId==3||record.objTypeId==4)?"分类":"产品")+'</td>\
			        <td>'+(record.recSts=="1"?"启用":"停用")+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        	<a class="tablelink" onclick="deleteRecord('+record.recId+')">删除</a>\
			        	<a class="tablelink setElement" onclick="showElement('+record.recId+',this);">设置</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(recordList.length==0){
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


function deleteRecord(recId){
	if(recId){
			
		$('#tr_'+recId).remove();
		var index = layer.confirm('确定删除档案?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    
		    $.post('record_deleteRecord.action',{ids:recId},function(result){
		    	layer.msg(result);
		    	initProList(1,10);
		    },'TEXT');
		    
		}, function(){
		    layer.close(index);
		});
	}
}



var gRecId = 0;
function showElement(recId,event){
	
	if(gRecId!=0){
		var o = gRecId;
		$('#ul_'+gRecId).animate({'height':'0px'},100,function(){
			$('#tr_'+o).remove();
		});
	}
	
	var text = $(event).text();
	
	if(text=='设置'){
		$('.setElement').text('设置');
		$(event).text('关闭')
	}else{
		$(event).text('设置')
		return;
	}
	
	
	gRecId = recId;
	var tr = $(event).parent().parent();
	var content = '<tr id="tr_'+recId+'"><td align="center" colspan="'+$('#proTab tr:first').find('th').length+'"><ul id="ul_'+recId+'" class="iconlist"></ul></td></tr>';
	tr.after(content);
	
	//获取要素
	$.post('record_getElements.action','recId='+recId,function(result){
		if(result){
			for(var i=0;i<result.length;i++){
				var el = result[i];
				if(el){
					var srcImg = 'images/icon19.png';
					if(el.elementIcon!=''){
						srcImg = '/nytsyFiles/element/'+el.elementIcon;
					}
					var content = '<li onclick="openDialog(\''+el.elementUrl+'?recId='+recId+'\',\''+el.elementName+'\')">\
							    <img src="'+srcImg+'" />\
							    <p><a href="#">'+el.elementName+'</a></p>\
							    </li>';
					$('#ul_'+recId).append(content);
				}
			}
			var width = $(window).width();
			var stratP = (width-125*result.length)/2;
			$('#ul_'+recId).css({'padding-left':stratP});
			
			$('#ul_'+recId).animate({'height':'150px'});
		}
	},'JSON');
}

function openDialog(url,title){
	var index = layer.open({
		title:title,
	    type: 2,
	    shift: 5,
	    maxmin: false,
	    area: ['700px', '530px'],
	    fix: false, //不固定
	    content: url,
	    success:function(){
			layer.tips('单击这里退出','.layui-layer-setwin',{
		   	    tips: 1
		   	});
	    }
	});
	layer.full(index);
}


//提交表单
function submitForm(){
	if($('#recordForm').form('validate')==false){
		return;
	}
	var params = {};
	var formUrl = 'record_addRecord.action';
	if(urlType!=1){
		formUrl = 'record_updateRecord.action';
	}
	var it = layer.msg('数据处理中，请稍后....', {icon: 16,time: 20000});
	
	$('#recordForm').form('submit', {
		url : formUrl,
		success : function(result) {
			layer.close(it);
			layer.msg(result);
			initProList(1,10);
			$('.tools').show();
		}
	});
	
	
}

//显示二维码
function showDimenno(index){
	var record = grecordList[index];
	if(record){
		
		var content = '<div style="height:250px;width: 100%" align="center">\
	     	<span id="code_Img"></span>\
	     </div>\
	     <div style="height:50px;width: 100%" align="center">\
	     	<input type="hidden" id="Dimenno_Img">\
	     	<a onclick="downloadCodeImg()" data-options="iconCls:\'icon-edit\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" group="" id=""><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下  载</span></span></a>&nbsp;&nbsp;\
	     	<a onclick="downloadCodeImg2()" data-options="iconCls:\'icon-edit\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" group="" id=""><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载二维码图片</span></span></a>&nbsp;&nbsp;\
	     	<a onclick="downloadCodeImg3()" data-options="iconCls:\'icon-edit\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" group="" id=""><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载二维码高清大图</span></span></a>&nbsp;&nbsp;\
	     </div>';
	     
		layer.open({
			title:'二维码信息',
			shift:5,
			type: 1,
			skin: 'layui-layer-rim',
			area: ['530px', '350px'], //宽高
			content: content
		});
		
		if(record.objTypeId==1){//主体分类
			$.post("ent_getLoginCompanyDimenno.action","",function(result){
				if(result[0]){
					var obj = result[0];
					$("#code_Img").html("<img src='/nytsyFiles/qrcode/"+obj.dimImg+"' />");
					$("#Dimenno_Img").val(obj.dimImg);
				}
			},"JSON");
		}else if(record.objTypeId==2){
			$.post("proTypeQrcode_getProTypeQrcodeById.action",{ptqId:record.objId},function(result){
				if(result[0]){
					var obj = result[0];
					$("#code_Img").html("<img src='/nytsyFiles/qrcode/"+obj.codeImg+"' />");
					$("#Dimenno_Img").val(obj.codeImg);
				}
			},"JSON");
		}
	}
}

//下载二维码组合图片
function downloadCodeImg(){
	var img = $("#Dimenno_Img").val();
	window.location.href="download.action?fileName="+img;
}
// 下载二维码图片-四方图
function downloadCodeImg2(){
	var img = $("#Dimenno_Img").val();
	img=img.substring(0,img.indexOf(".png"));
	img=img+"_2.png"
	window.location.href="download.action?fileName="+img;
}
// 下载二维码高清大图片-四方图
function downloadCodeImg3(){
	var img = $("#Dimenno_Img").val();
	img=img.substring(0,img.indexOf(".png"));
	img=img+"_3.png"
	window.location.href="download.action?fileName="+img;
}


//添加档案
function addRecord(flag){
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	
	$('#recordType').show();
	
	$('#recordForm').form('reset');
	
	if(!flag){
		$('.tools').hide();
	}
	$('#proType').hide();
	
	$('input[name="record.recName"]').val('生产档案');
}
//修改档案
function updateRecord(index){
	var record = grecordList[index];
	if(record){
		addRecord(true);
		urlType = 2;
		
		$('#recordType').hide();
		$('input[name="record.recName"]').val(record.recName);
		$('input[name="record.recId"]').val(record.recId);
		
		$('#rSeq').numberbox('setValue',record.seq);
		$('#recSts').combobox('setValue',record.recSts);
		
		if(record.objTypeId==3||record.objTypeId==4){
			$('#proType').show();
			
			$('#typeId').combotree({
				url:'proType_getProTypeTreeToProbyType.action?typeClass='+(record.objTypeId-2),
				'required':true,
				onLoadSuccess:function(){
					$.post('proTypeQrcode_getProTypeQrcodeById.action','ptqId='+record.objId,function(result){
						$('#typeId').combotree('setValue',result[0].typeId);
						$("#typeId").combotree('disable');
					},'JSON');
				}
			});
			/*
			$('#typeId').combotree({onLoadSuccess:function(){
				$.post('proTypeQrcode_getProTypeQrcodeById.action','ptqId='+record.objId,function(result){
					$('#typeId').combotree('setValue',result[0].typeId);
					$("#typeId").combotree('disable');
				},'JSON');
				
			}});*/
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
		//$('#typeId').combotree({'required':true});
		$('#typeId').combotree({
			url:'proType_getProTypeTreeToProbyType.action?typeClass='+(val-2),
			'required':true,
			onLoadSuccess:function(){
				$("#typeId").combotree('enable');
			}
		});
	}
}
//添加要求信息
function editRecord(index){
	var record = grecordList[index];
	
}

//************************************************************************************

function showElementList(recId,event){
	
	
	var tr = $(event).parent().parent();
	
	return;
	//$('.mask').show();
	var  itList = $('.elItem');
	
	
	var width = $(window).width();
	var height = $(window).height();
	//元素之间的间隔为20像素
	var jg = 20;
	
	var ct = itList.length;
	
	//开始位置
	var start = (width - ct*188+(ct-1)*jg)/2;
	
	//alert(width+"  "+height);
	
	$('.elItem').css('left',(width-188)/2);
	
	
	for(var i=0;i<itList.length;i++){
		var it = itList[i];
		
		$(it).animate({'top':'100px'}).animate({'left':start+(208*i)});
	}
	
	//$('.elItem').animate({'top':'100px'}).animate({'left':'100px'});
	
	//获取要素
	/*$.post('record_getElements.action','recId=<%=recId %>',function(result){
		if(result){
			for(var i=0;i<result.length;i++){
				var el = result[i];
				if(el){
					var srcImg = 'images/l01.png';
					if(el.elementIcon!=''){
						srcImg = '/nytsyFiles/element/'+el.elementIcon;
					}
					var content = '<li onclick="openDialog(\''+el.elementUrl+'?recId=<%=recId %>\',\''+el.elementName+'\')">\
							    <span><img src="'+srcImg+'" /></span>\
							    <h2><a href="#">'+el.elementName+'</a></h2>\
							    </li>';
					$('.imglist').append(content);
				}
			}
		}
	},'JSON');*/
}











