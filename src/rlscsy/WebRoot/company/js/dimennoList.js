
$(function(){
	
	parent.layer.closeAll();
	
	layer.load(2);
	
	var auditPro = $('#auditPro').val();
	var auditBatch = $('#auditBatch').val();
	

	$.post("ent_getLoginCompanyDimenno.action","",function(result){
		if(result[0]){
			
			$('#companyInfo').hide();
			$('#div_dim').show();
			
			var obj = result[0];
			
			var content = '<div id="p_ent" style="float: left;width: 500px;">\
		    	<img src="/nytsyFiles/qrcode/'+obj.dimImg+'?tt='+Math.random()+'" />\
		    </div>\
		    <div>\
		    	<br><br><br><br><br><br><a onclick="downloadCodeImg(\''+obj.dimImg+'\')" data-options="iconCls:\'icon-edit\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" ><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载追溯标识图</span></span></a><br><br><br><br>\
		    	<a onclick="printImg(\'p_ent\')" data-options="iconCls:\'icon-print\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" ><span class="l-btn-left"><span class="l-btn-text icon-print l-btn-icon-left">打印追溯标识图</span></span></a>\
		    </div>';
			
		    //<a onclick="downloadCodeImg2(\''+obj.dimImg+'\')" data-options="iconCls:\'icon-edit\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" ><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载二维码图</span></span></a><br><br>\
		    //<a onclick="downloadCodeImg3(\''+obj.dimImg+'\')" data-options="iconCls:\'icon-edit\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" ><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载高清二维码图</span></span></a><br><br>\
		    	
			$('#entDiv').html(content);
			
			if(obj.isbatch==0){
				var params = {'proTypeQrcode.entId':$('#h_entId').val()};
				if(auditPro == 1){
					params['proTypeQrcode.proState'] = 2;
				}
				$.post('proTypeQrcode_findProTypeQrcode.action',params,function(result){
					if(result){
						for(var i=0;i<result.length;i++){
							var row = result[i];
							if(row.codeImg==''||row.codeImg==null){
								continue;
							}
							content = '<div style="height:270px;"><div id="p_'+row.ptqId+'" style="float: left;width: 500px;">\
						    	<img src="/nytsyFiles/qrcode/'+row.codeImg+'?tt='+Math.random()+'" />\
						    </div>\
						    <div>\
						    	<br><br><br><br><br><br><a onclick="downloadCodeImg(\''+row.codeImg+'\')" data-options="iconCls:\'icon-edit\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" ><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载追溯标识图</span></span></a><br><br><br><br>\
						    	<a onclick="printImg(\'p_'+row.ptqId+'\')" data-options="iconCls:\'icon-print\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" ><span class="l-btn-left"><span class="l-btn-text icon-print l-btn-icon-left">打印追溯标识图</span></span></a>\
						    </div></div><div class="formtitle" ><span></span></div>';
							//<a onclick="downloadCodeImg2(\''+row.codeImg+'\')" data-options="iconCls:\'icon-edit\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" ><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载二维码图</span></span></a><br><br>\
					    	//<a onclick="downloadCodeImg3(\''+row.codeImg+'\')" data-options="iconCls:\'icon-edit\',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" ><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载高清二维码图</span></span></a><br><br>\
					    	
							$('#typeDiv').append(content);
						}
						
					}
					layer.closeAll();
				},'JSON');
			}else{
				//批次二维码
				
				//显示批次档案
				//查询有几个种类，然后显示种类的批次档案信息
				$.ajax({
					   type: "POST",
					   url: "proTypeQrcode_findProTypeQrcodeList.action",
					   async: false,
					   data: {"proTypeQrcode.entId":obj.entId},
					   dataType:"JSON",
					   success: function(result){
				     		for(var j=0;j<result.length;j++){
				     			var proTypeQrcode = result[j];
				     			var ptqId = proTypeQrcode.ptqId;
				     			//种类面板
								var typeTabId = "typetab"+ptqId;																 								
								var recordHtml ='<div id="p'+ptqId+'" class="easyui-panel" title="'+proTypeQrcode.typeName+'追溯标识信息"><table id="'+typeTabId+'" class="easyui-datagrid" data-options="fit:true"></table></div>';								
								$("#typeDiv").append(recordHtml);			
								
								$("#p"+ptqId).panel({width:'99%',height:'300',collapsible:true,collapsed:false});		
								
								var params = {"proTypeQrcode.ptqId":ptqId};
								if(auditBatch == 1){
									params['proTypeQrcode.proState'] = 2;
								}

								$("#"+typeTabId).datagrid({  
									  title:'批次列表',
								      pageSize : 10,//默认选择的分页是每页5行数据  
								      pageList : [ 10,20,30,50],//可以选择的分页集合  
								      nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取  
								      striped : true,//设置为true将交替显示行背景。  
								      url:'proTypeQrcode_findProTypeBatch.action',//url调用Action方法  
								      queryParams:params,
								      loadMsg : '数据装载中......',  			      
								      fit:true,			      
								      fitColumns:true,//允许表格自动缩放，以适应父容器  
								      remoteSort : false,
								      columns:[[
											{field:'batchName',title:'批次名称',width:100,align:'center'},			
											{field:'batchTime',title:'批次生产时间',width:100,align:'center'},
											{field:'dimenno',title:'批次编码',width:150,align:'center'},
											{field:'codeImg',title:'追溯标识',width:50,align:'center',formatter:function(value,row,index){
												
												if(value!=""){
													var maImg = '<a href=\'javascript:void(0);\' onclick=\'showCodeImg("'+value+'");\'><img  id="id_'+row.ptbId+'" width="50" height=50" src="/nytsyFiles/qrcode/'+value+'"/></a>';
													return maImg;
												}
												
											}},
											{field:'ptbId',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
												var t1='<a href=\'javascript:void(0);\' onclick=\'downloadCodeImg("'+row.codeImg+'");\'>下载</a>    '	+
												'<a href=\'javascript:void(0);\'  onclick=\'showCodeImg("'+row.codeImg+'");\' >打印</a>';
												return t1;
											}}
								      ]],
							          pagination : true,//分页  
							          rownumbers : true,//行数  				          
							          onLoadSuccess : function(data) {

							          },
								 	 onClickRow: function(rowIndex, rowData){
							 			$(this).datagrid('unselectAll');
							 			$(this).datagrid('selectRow',rowIndex);
							 		 }
								}); 
														     			
				     		}
					   }
					});	
				
				layer.closeAll();
			}			
		}
	},"JSON");

	
});

//显示二维码
function showCodeImg(codeImg){
	$("#codeImg").attr("src","/nytsyFiles/qrcode/"+codeImg);	
	$("#batchWin").window("open").window("center");
}

function printBatchImg(){
	$('#codeImg').jqprint();
}

//下载二维码组合图片
function downloadCodeImg(dimenno){
	var img = dimenno;
	window.location.href="download.action?fileName="+img;
}
// 下载二维码图片-四方图
function downloadCodeImg2(dimenno){
	var img = dimenno;
	img=img.substring(0,img.indexOf(".png"));
	img=img+"_2.png"
	window.location.href="download.action?fileName="+img;
}
// 下载二维码高清大图片-四方图
function downloadCodeImg3(dimenno){
	var img = dimenno;
	img=img.substring(0,img.indexOf(".png"));
	img=img+"_3.png"
	window.location.href="download.action?fileName="+img;
}

function printImg(pid){
	$('#'+pid).jqprint();
}