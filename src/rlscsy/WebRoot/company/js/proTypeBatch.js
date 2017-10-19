var basePath;
var urlType = 1;
var formUrl = 'proTypeArea_addProTypeArea.action';	


$(function(){
	basePath = $("#basePath").val();
	parent.layer.closeAll();
	$.ajaxSetup({
		  async: false
		  });
	
	$('#_ptqId').combobox({
		url:'proTypeQrcode_findProTypeQrcode.action?proTypeQrcode.entId='+$('#entId').val()+'&proTypeQrcode.isbatch=1',
		valueField:'ptqId',    
	    textField:'typeName',
	    onLoadSuccess:function(){
			//判定有没有数据，如果没有提示用户先添加种类信息
			var json = $('#_ptqId').combobox('getData');
			if(json.length==0){
				alert('请先添加产品信息');
			}
		}
	});
	
	initProList(1,10);
	
	 
});
var proTypeList;
function initProList(page,rows){	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	
	var comType = $("#comType").val();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['proTypeBatch.entId']=$('#entId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('proTypeBatch_findProTypeBatch.action?tt='+Math.random(),condition,function(result){		
		if(result){		
			proTypeList = result.rows;
			
			for(var i=0;i<proTypeList.length;i++){
				var proTypeBatch = proTypeList[i];
				var condition2 = {};
				condition['ptbId']=proTypeBatch.ptbId;
				
				//生猪进厂信息
				var actionHtml="";
				if(comType==1){
					actionHtml='<a class="tablelink" onclick="showPage(\'生猪进厂信息\',\'marketIn_tz.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">生猪进厂信息</a> '+
											'<a class="tablelink"onclick="showPage(\'检验检疫信息\',\'quarantine.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">检验检疫信息</a> '+
											'<a class="tablelink"onclick="showPage(\'交易信息\',\'meatOutInfoBase_tz.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">交易信息</a>';
				}else if(comType==2){
					actionHtml='<a class="tablelink"onclick="showPage(\'批发进场信息\',\'marketIn_pf.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">批发进场信息</a> '+
								' <a class="tablelink"onclick="showPage(\'检验检疫信息\',\'qmarketDetectionInfo.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">检验检疫信息</a>'+
								' <a class="tablelink"onclick="showPage(\'交易信息\',\'meatOutInfoBase_pf.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">交易信息</a>';
				}else if(comType==3){
					actionHtml='<a class="tablelink"onclick="showPage(\'零售进场信息\',\'marketIn_ls.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">零售进场信息</a> '+
								' <a class="tablelink"onclick="showPage(\'销售汇总信息\',\'retailMarketTranInfoSumm.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">销售汇总信息</a>';
				}else if(comType==4){
					actionHtml='<a class="tablelink"onclick="showPage(\'超市进场信息\',\'marketIn_cs.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">超市进场信息</a> ';
				}else if(comType==5){
					actionHtml='<a class="tablelink"onclick="showPage(\'肥料使用记录\',\'fertilizerUse.jsp?recId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">肥料使用记录</a> '+
										  '<a class="tablelink"onclick="showPage(\'农药使用记录\',\'agriUse.jsp?recId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">农药使用记录</a>'+
										  '<a class="tablelink"onclick="showPage(\'生产节点\',\'nodeinfo.jsp?recId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">生产节点</a>'+
										  '<a class="tablelink"onclick="showPage(\'检验检疫信息\',\'checkinfo.jsp?recId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">检验检疫信息</a>'+										  
										 '<a class="tablelink"onclick="showPage(\'交易信息\',\'saleinfo.jsp?recId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">交易信息</a>';
				}
				if(comType==2||comType==3||comType==4){
					actionHtml+='<a class="tablelink" onclick="showup('+comType+','+proTypeBatch.ptbId+')">查看上级节点流通信息</a>';
				}
				
				var value = proTypeBatch.codeImg;
				if(value){
					var maImg = ' <a class="tablelink" href=\'javascript:void(0);\' onclick=\'showCodeImg("'+value+'");\'>追溯标识</a>';
					actionHtml+=maImg;
				}

				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+proTypeBatch.batchName+'</td>\
			        <td>'+proTypeBatch.batchTime+'</td>\
					<td>'+proTypeBatch.dimenno+'</td>\
					<td>'+actionHtml+'</td>\
			        </td>\
			        </tr>';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(proTypeList.length==0){
				$('#proTab').append('<tr><td align="center" colspan="'+$('#proTab tr:first').find('th').length+'">暂无数据</td></tr>');
			}	
			
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
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


function showPage(title,url){
	//iframe层
	layer.open({
	  type: 2,
	  title: title,
	  shadeClose: true,
	  maxmin: true, //开启最大化最小化按钮
	  shade: 0.5,
	  area: ['95%', '95%'],
	  content: url //iframe的url
	}); 
}

//显示二维码
function showCodeImg(codeImg){
	$("#codeImg").attr("src","/nytsyFiles/qrcode/"+codeImg);	
	$("#batchWin").window("open").window("center");
}

function printBatchImg(){
	$('#codeImg').jqprint();
}

//添加档案
function addRecord(){
	$('.formtitle span').html('添加批次信息');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset')
	
	formUrl = 'proTypeBatch_addProTypeBatch.action';	
	
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
				url : 'proTypeBatch_deleteproTypeBatch.action',
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
	
	var params = {};
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

function showup(comType,ptbId){
	var condition = {};
	condition['ptbId']=ptbId;
	var upPtbId=0;
	if(comType==2){
		$.post('proTypeBatch_findPtBIdByProTypeBatch.action?tt='+Math.random(),condition,function(result){	
			if(result){
				upPtbId=result;
			}
		},'text');
		}
	
	if(comType==3||comType==4){
		$.post('proTypeBatch_findPtBIdByProOut.action?tt='+Math.random(),condition,function(result){	
			if(result){
				upPtbId=result;
			}
		},'text');
	}
	if(upPtbId=="0"){
		layer.msg("请先添加交易凭证号");
	}
	var condition2 = {};
	condition2['ptbId']=upPtbId;
	$.post('proTypeBatch_findDimenNoByPtBId.action?tt='+Math.random(),condition2,function(result){	
			if(result){
				var url=$('#basePath').val()+'portal/sydetail_com.jsp?dimenno='+result;
				//iframe层
				layer.open({
				  type: 2,
				  title: '流通信息',
				  shadeClose: true,
				  maxmin: true, //开启最大化最小化按钮
				  shade: 0.5,
				  area: ['95%', '95%'],
				  content: url //iframe的url
				}); 
			}
		},'text');	
		
	
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
