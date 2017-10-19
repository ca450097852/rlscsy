var dataGrid; // 列表
var isbatch = 0;
$(function() {	
	dataGrid = $('#list_enterprise').datagrid( {
		url : 'ent_findCompanyList.action',
		queryParams: {"enterprise.companyRsts": '4'},
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '企业信息列表',
		iconCls : 'icon-house',
		loadMsg : '数据加载中...',
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [ 10, 15,20, 30, 50 ],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		fit : true,
		fitColumns: true,
		// singleSelect:true,
		pagination : true,
		rownumbers : true,
		remoteSort : false,
		// collapsible : true,//显示可折叠按钮
		columns : [ [ 
		//{field : 'areaName',title : '所属区域',width : 80,align : 'center'}, 
		//{field : 'parentName',title : '所属行政机构',width : 150,align:'center'}, 
		{field : 'name',title : '企业名称',width : 200,halign:'center'}, 
		{field : 'regAddr',title : '注册地址',width : 200,halign:'center'}, 
		{field : 'crtDt',title : '注册时间',width : 100,halign:'center'}, 
		{field : 'tel',title : '联系电话',width : 100,align : 'center'}, 
		{field : 'legalPerson',title : '法人代表',width : 60,align : 'center'}, 
		//{field : 'typeName',title : '企业分类',width : 80,align : 'center'}, 	
		/*{field : 'townRsts',title : '镇审核',width : 50,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);	
			}
		},*/
		{field : 'areaRsts',title : '区审核',width : 50,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);	
			}
		},
		{field : 'cityRsts',title : '市审核',width : 50,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);	
			}
		},
		/*{field : 'provinceRsts',title : '省审核',width : 50,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);	
			}
		},*/
		{field : 'companyRsts',title : '企业状态',width : 60,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);
			}
		},
		{field : 'entId',title : '操作',width : 150,align : 'center',
			formatter : function(value, row, index) {
				var e = "<a href='javascript:void(0)' onclick='update("+index+")'>查看详细</a>&nbsp;&nbsp;&nbsp;&nbsp;";
				e += "<a href='javascript:void(0)' onclick='shwoLog("+value+")'>操作记录</a>&nbsp;&nbsp;&nbsp;&nbsp;";
				e += "<a href='javascript:void(0)' onclick='stopUse("+value+",\""+row.name+"\")'>暂停使用</a>&nbsp;&nbsp;";
				return e;			
			}
		}
		] ],
		onLoadSuccess:function(data){
		 	f_timeout(data);
		 	
		 	
	 	},
 		onClickRow: function(rowIndex, rowData){
 			$(this).datagrid('unselectAll');
 			$(this).datagrid('selectRow',rowIndex);
 		}
	});
	 
	 $("#companyTab").tabs({
		 onSelect: function(title,index){
			var target = this;
			//选择不是第一个选项卡页
			if(index>0){
				var zizhiEntId = $("#zizhiEntId").val();
				//var proEntId = $("#proEntId").val();
				var auditRecordEntId =$("#auditRecordEntId").val();

				if(zizhiEntId==null||zizhiEntId==''){
					$("#companyTab").tabs('select',0);//选择到第一个
					$.messager.show({ title : '提示', msg : '请先填写企业信息并保存!' });   	
					return;
				}
				if(index==1){
					//表格数据
					$('#zizhiDataGrid').datagrid({  
						  title:'资质证书列表',
						  iconCls : 'icon-table',
					      pageSize : 15,//默认选择的分页是每页5行数据  
					      pageList : [ 10, 15,20,30, 50 ],//可以选择的分页集合  
					      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
					      striped : true,//设置为true将交替显示行背景。  
					      //collapsible : true,//显示可折叠按钮  
					      //toolbar:"#tb_zizhi",//在添加 增添、删除、修改操作的按钮要用到这个  
					      url:'zizhi_findZizhiPagerList.action',//url调用Action方法  
					      queryParams:{"zizhi.entId":zizhiEntId},
					      //toolbar:"#tb",
					      loadMsg : '数据装载中......',  			      
					      fit:true,			      
					      //singleSelect:true,//为true时只能选择单行  
					      fitColumns:true,//允许表格自动缩放，以适应父容器  
					      remoteSort : false,  
					      columns:[[
								{field:'zizhiName',title:'证书名称',width:200,align:'center'},			
								{field:'appType',title:'证书类型',width:100,align:'center',
									formatter: function(value,row,index){ 
										var displayValue = '';
										if(value==1){
											displayValue = '资质文件'
										}else if(value==2){
											displayValue = '营业执照'
										}else if(value==3){
											displayValue = '企业荣誉'
										}else if(value==5){
											displayValue = '组织机构代码证'
										}else{
											displayValue = '其它证书'
										}
										return displayValue;
									}
								},
								{field:'state',title:'状态',width:100,align:'center',
									formatter: function(value,row,index){ 
										var displayValue = '';
										if(value==1){
											displayValue = '正常'
										}else if(value==2){
											displayValue = '申请修改中'
										}else if(value==3){
											displayValue = '同意修改'
										}
										return displayValue;
									}
								},					
								{field:'awardUnit',title:'颁发单位',width:150,align:'center'},
								//{field:'awardTime',title:'颁发时间',width:100,align:'center'},
								//{field:'remark',title:'备注',width:200,align:'center'},
								{field:'action',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
									var t1='<a href=\'javascript:void(0);\' onclick=\'attachWin('+index+');\'>附件管理</a>'										
									return t1;
								}}
					      ]],
				          pagination : true,//分页  
				          rownumbers : true,//行数  				          
				          onLoadSuccess : function(data) {
							f_timeout(data);
						 },
					 	 onClickRow: function(rowIndex, rowData){
				 			$(this).datagrid('unselectAll');
				 			$(this).datagrid('selectRow',rowIndex);
				 		 }
					}); 							
				}else if(index==2){
					/*
					//表格数据
					$('#productionDataGrid').datagrid({  
						  title:'生产信息列表',
						  iconCls : 'icon-table',
					      pageSize : 15,//默认选择的分页是每页5行数据  
					      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
					      nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取  
					      striped : true,//设置为true将交替显示行背景。  
					      //collapsible : true,//显示可折叠按钮  
					      //toolbar:"#tb_pro",//在添加 增添、删除、修改操作的按钮要用到这个  
					      url:'production_findProductionPagerList.action',//url调用Action方法  
					      queryParams:{"production.entId":proEntId},
					      loadMsg : '数据装载中......',  			      
					      fit:true,			      
					      //singleSelect:true,//为true时只能选择单行  
					      fitColumns:true,//允许表格自动缩放，以适应父容器  
					      remoteSort : false,
					      columns:[[
								{field:'productinfo',title:'产品描述',width:500,align:'center'},			
								{field:'license',title:'产品许可证号',width:100,align:'center'},
								//{field:'entName',title:'企业名称',width:150,align:'center'},
								//{field:'crttime',title:'录入时间',width:100,align:'center'},
								{field:'action',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
									var t1='<a href=\'javascript:void(0);\' onclick=\'attachWin1('+index+');\'>附件管理</a>'										
									return t1;
								}}
					      ]],
				          pagination : true,//分页  
				          rownumbers : true,//行数  				          
				          onLoadSuccess : function(data) {
							f_timeout(data);
						 },
					 	 onClickRow: function(rowIndex, rowData){
				 			$(this).datagrid('unselectAll');
				 			$(this).datagrid('selectRow',rowIndex);
				 		 }
					}); 
				*/
					//表格数据
					$('#auditRecordDataGrid').datagrid({  
						  title:'审核信息记录表',
						  iconCls : 'icon-table',
					      pageSize : 20,//默认选择的分页是每页5行数据  
					      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
					      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
					      striped : true,//设置为true将交替显示行背景。  
					      //collapsible : true,//显示可折叠按钮  
					      //toolbar:"#tb_pro",//在添加 增添、删除、修改操作的按钮要用到这个  
					      url:'auditRecord_findAuditRecordPagerList.action',//url调用Action方法  
					      queryParams:{"auditRecord.entId":auditRecordEntId},
					      loadMsg : '数据装载中......',  			      
					      fit:true,			      
					      //singleSelect:true,//为true时只能选择单行  
					      fitColumns:true,//允许表格自动缩放，以适应父容器  
					      remoteSort : false,  
					      /*frozenColumns : [ [ {  
					           field : 'ck',  
					           checkbox : true  
					      } ] ],*/
					      columns:[[
								{field:'entName',title:'企业名称',width:150,halign:'center'},			
								{field:'auditEntName',title:'审核机构',width:100,halign:'center'},
								{field:'auditUserName',title:'审核人员',width:50,align:'center'},
								{field:'auditState',title:'审核状态',width:50,align:'center',formatter: function(value,row,index){ 					
									var t1='';
									if(value==2){
										t1='<font color=blue>暂停</font>';
									}else if(value==3){
										t1='<font color=red>退回</font>';
									}else if(value==4){
										t1='<font color=green>通过</font>';
									}
									return t1;
								}},
								{field:'auditOpinion',title:'审核意见',width:100,align:'center'},
								{field:'auditTime',title:'审核时间',width:100,align:'center'}/*,

								{field:'action',title:'操作',width:50,align:'center',formatter: function(value,row,index){ 					
									var t1='<a href=\'javascript:void(0);\' onclick=\'attachWin1('+index+');\'>查看</a>'										
									return t1;
								}}*/
					      ]],
				          pagination : true,//分页  
				          rownumbers : true,//行数  				          
				          onLoadSuccess : function(data) {
							f_timeout(data);
						 },
					 	 onClickRow: function(rowIndex, rowData){
				 			$(this).datagrid('unselectAll');
				 			$(this).datagrid('selectRow',rowIndex);
				 		 }
					}); 
				}else if(index==3){
					//表格数据
					$('#superviseDataGrid').datagrid({  
						  title:'监管信息记录表',
						  iconCls : 'icon-table',
					      pageSize : 15,//默认选择的分页是每页5行数据  
					      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
					      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
					      striped : true,//设置为true将交替显示行背景。  
					      //collapsible : true,//显示可折叠按钮  
					      toolbar:"#tb_supervise",//在添加 增添、删除、修改操作的按钮要用到这个  
					      url:'supervise_findSupervisePagerList.action',//url调用Action方法  
					      queryParams:{"supervise.entId":auditRecordEntId},
					      loadMsg : '数据装载中......',  			      
					      fit:true,			      
					      //singleSelect:true,//为true时只能选择单行  
					      fitColumns:true,//允许表格自动缩放，以适应父容器  
					      remoteSort : false,  
					      columns:[[
								{field:'title',title:'信息标题',width:300,align:'center'},			
								{field:'state',title:'信息状态',width:100,align:'center',formatter: function(value,row,index){ 	
									if(value==1){
										return "待审";
									}else if(value==2){
										return "对内通告";
									}else if(value==3){
										return "对外发布";
									}
								}},
								{field:'supTime',title:'发布时间',width:100,align:'center'},
								{field:'action',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
									var t1='<a href=\'javascript:void(0);\' onclick=\'showSupervise('+index+');\'>查看</a>';
										t1+='&nbsp;&nbsp;<a href=\'javascript:void(0);\' onclick=\'deleteSupervise('+index+');\'>删除</a>'
									return t1;
								}}
					      ]],
				          pagination : true,//分页  
				          rownumbers : true,//行数  				          
				          onLoadSuccess : function(data) {
							f_timeout(data);
						 },
					 	 onClickRow: function(rowIndex, rowData){
				 			$(this).datagrid('unselectAll');
				 			$(this).datagrid('selectRow',rowIndex);
				 		 }
					}); 
				}else if(index==4){					
					$("#recordDiv").html("");							
					//isbatch==0 不是批次追溯					
					if(isbatch==0){
						$.post("record_findRecordList.action", {"entId":auditRecordEntId} ,function(records){						
							if(records.total>0){												
								for(var i=0;i<records.total;i++){
									var record = records.rows[i];							
									var objId = record.objId;		//企业ID；分类ID；产品ID
									var objTypeId = record.objTypeId;	//企业1；分类二维码2；产品3
									var recId = record.recId;	//
									var data = {"objElement.objId":objId,"objElement.objTypeId":objTypeId};								
									var recTabId = "recordtab"+recId;																 								
									var recordHtml ='<div id="p'+recId+'" class="easyui-panel" title="'+record.recName+'"><div id="'+recTabId+'" class="easyui-tabs" data-options="fit:true"></div></div>';								
									$("#recordDiv").append(recordHtml);								
									$("#p"+recId).panel({width:'99%',height:'200',collapsible:true,collapsed:i>1});								
									$('#'+recTabId).tabs({ border:false});  																
									$.ajax({
										   type: "POST",
										   url: "objElement_findObjElementList.action",
										   async: false,
										   data: data,
										   dataType:"JSON",
										   success: function(result){
											for(var j=0;j<10;j++){
												var exists = $('#'+recTabId).tabs('exists', 0);  
												if(exists){
													$('#'+recTabId).tabs('close', 0);  
												}else{
													break;
												}
											}
											
											if(result.total>0){
												for(var j=0;j<result.total;j++){
													var elem = result.rows[j];				
													var elementName = elem.elementName;								
													$('#'+recTabId).tabs('add',{    
													    title:elementName,
													    selected: j==0,		// 选中第一个    
													    content:'<iframe src="jsp/element/'+elem.elementUrl+'?flag=out&recId='+recId+'" width="99%" height="99%" frameborder="0"></iframe>'    
													});  				
												}			
												
											}else{
												$('#'+recTabId).tabs('add',{    
												    title:'提示',    
												    content:'<div>该档案暂无信息!</div>'    
												});  
											}
										     									     									     
										   }
										});										
								}							
							}
						},"JSON");
					}else{
						//批次追溯
						//首先显示主体档案							
						$.ajax({
							   type: "POST",
							   url: "record_findRecordList.action",
							   async: false,
							   data:  {"record.objId":auditRecordEntId,"record.objTypeId":1} ,
							   dataType:"JSON",
							   success: function(records){
								if(records.total==1||records.total=='1'){												
									var record = records.rows[0];							
									var recId = record.recId;	//
									var data = {"objElement.objId":auditRecordEntId,"objElement.objTypeId":1};								
									var recTabId = "recordtab0";																 								
									var recordHtml ='<div id="p" class="easyui-panel" title="主体档案"><div id="'+recTabId+'" class="easyui-tabs" data-options="fit:true"></div></div>';								
									$("#recordDiv").append(recordHtml);								
									$("#p").panel({width:'99%',height:'200',collapsible:true,collapsed:false});								
									$('#'+recTabId).tabs({ border:false});  																
									$.ajax({
										   type: "POST",
										   url: "objElement_findObjElementList.action",
										   async: false,
										   data: data,
										   dataType:"JSON",
										   success: function(result){
											for(var j=0;j<10;j++){
												var exists = $('#'+recTabId).tabs('exists', 0);  
												if(exists){
													$('#'+recTabId).tabs('close', 0);  
												}else{
													break;
												}
											}											
											if(result.total>0){
												for(var j=0;j<result.total;j++){
													var elem = result.rows[j];				
													var elementName = elem.elementName;								
													$('#'+recTabId).tabs('add',{    
													    title:elementName,
													    selected: j==0,		// 选中第一个    
													    content:'<iframe src="jsp/element/'+elem.elementUrl+'?recId='+recId+'" width="99%" height="99%" frameborder="0"></iframe>'    
													});  				
												}												
											}else{
												$('#'+recTabId).tabs('add',{    
												    title:'提示',    
												    content:'<div>该档案暂无信息!</div>'    
												});  
											}							     									     									     
										   }
										});											
								}
							   }
						});
	
						//显示批次档案
						//查询有几个种类，然后显示种类的批次档案信息
						$.ajax({
							   type: "POST",
							   url: "proTypeQrcode_findProTypeQrcodeList.action",
							   async: false,
							   data: {"proTypeQrcode.entId":auditRecordEntId},
							   dataType:"JSON",
							   success: function(result){
						     		for(var j=0;j<result.length;j++){
						     			var proTypeQrcode = result[j];
						     			var ptqId = proTypeQrcode.ptqId;
						     			//种类面板
										var typeTabId = "typetab"+ptqId;																 								
										var recordHtml ='<div id="p'+ptqId+'" class="easyui-panel" title="'+proTypeQrcode.typeName+'档案信息"><table id="'+typeTabId+'" class="easyui-datagrid" data-options="fit:true"></table></div>';								
										$("#recordDiv").append(recordHtml);			
										
										$("#p"+ptqId).panel({width:'99%',height:'200',collapsible:true,collapsed:false});								

										$("#"+typeTabId).datagrid({  
											  title:'批次信息列表'+typeTabId,
											  iconCls : 'icon-table',
										      pageSize : 15,//默认选择的分页是每页5行数据  
										      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
										      nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取  
										      striped : true,//设置为true将交替显示行背景。  
										      url:'proTypeBatch_findProTypeBatch.action',//url调用Action方法  
										      queryParams:{"proTypeBatch.ptqId":ptqId},
										      loadMsg : '数据装载中......',  			      
										      fit:true,			      
										      fitColumns:true,//允许表格自动缩放，以适应父容器  
										      remoteSort : false,
										      columns:[[
													{field:'batchName',title:'批次名称',width:200,align:'center'},			
													{field:'batchTime',title:'批次生产时间',width:100,align:'center'},
													{field:'dimenno',title:'批次编码',width:150,align:'center'},
													{field:'batchState',title:'审核状态',width:50,align:'center',formatter: function(value,row,index){ 					
															if(value==''||value==0){
																return '待审核';
															}else if(value==1){
																return '<font color=green>正常</font>';
															}else if(value==2){
																return '<font color=red>异常</font>';
															}
													}},
													{field:'recId',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
														var t1='<a href=\'javascript:void(0);\' onclick=\'recordWin('+value+','+row.ptqId+');\'>批次档案信息</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\'javascript:void(0);\' onclick=\'batchAudit('+row.ptbId+','+row.ptqId+');\'>审核</a>';										
														return t1;
													}}
										      ]],
									          pagination : true,//分页  
									          rownumbers : true,//行数  				          
									          onLoadSuccess : function(data) {
												f_timeout(data);
											 },
										 	 onClickRow: function(rowIndex, rowData){
									 			$(this).datagrid('unselectAll');
									 			$(this).datagrid('selectRow',rowIndex);
									 		 }
										}); 
																				
/*										
						     			//查询批次
										$.ajax({
											   type: "POST",
											   url: "proTypeBatch_findProTypeBatchList.action",
											   async: false,
											   data: {"proTypeBatch.ptqId":ptqId},
											   dataType:"JSON",
											   success: function(result){
												   
													for(var j=0;j<10;j++){
														var exists = $('#'+typeTabId).tabs('exists', 0);  
														if(exists){
															$('#'+typeTabId).tabs('close', 0);  
														}else{
															break;
														}
													}	
												   
										     		for(var k=0;k<result.length;k++){
										     			var proTypeBatch = result[k];
										     			var ptbId = proTypeBatch.ptbId;
										     			var batchName = proTypeBatch.batchName;
										     			var recId = proTypeBatch.recId;
										     			//查询档案	
														$('#'+typeTabId).tabs('add',{    
														    title:batchName,
														    selected:k==0,		// 选中第一个    
														    content:'<iframe src="jsp/element/records.jsp?recId='+recId+'&objId='+ptqId+'" width="99%" height="99%" frameborder="0"></iframe>'    
														});  				
																						     			
										     		}
											   }
											});		*/						     			
						     		}
							   }
							});				
					}									
					
				}else if(index==5){

					//表格数据
					$('#chandiDatagrid').datagrid({  
						  title:'基地信息列表',
						  iconCls : 'icon-table',
					      pageSize : 15,//默认选择的分页是每页5行数据  
					      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
					      nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取  
					      striped : true,//设置为true将交替显示行背景。  
					      url:'proTypeArea_findProTypeAreaList.action',//url调用Action方法  
					      queryParams:{"proTypeArea.entId":zizhiEntId},
					      loadMsg : '数据装载中......',  			      
					      fit:true,			      
					      //singleSelect:true,//为true时只能选择单行  
					      fitColumns:true,//允许表格自动缩放，以适应父容器  
					      remoteSort : false,
					      columns:[[
								{field:'areaName',title:'基地名称',width:150,align:'center'},			
								{field:'areaAddr',title:'基地地址',width:150,align:'center'},
								{field:'scale',title:'基地规模',width:100,align:'center'},
								{field : 'areaImg',title : '基地平面图',width : 50,align : 'center',formatter: function(value,row,index){
									if(value!=""){
										var maId = "ids_"+row.ptaId;
										var maImg = '<a rel="'+maId+'" href="/nytsyFiles/element/'+value+'"><img width="50" height=50" src="/nytsyFiles/element/'+value+'"/></a>';
										return maImg;
									}
								}}
				
					      ]],
				          pagination : true,//分页  
				          rownumbers : true,//行数  				          
				          onLoadSuccess : function(data) {
				        	  
				  			if(data.total>0){						
								for(var i=0; i< data.total;i++){
									var rowX = data.rows[i];
									if(rowX.areaImg!=''){
										var app_group = "ids_"+rowX.ptaId;
										$("a[rel="+app_group+"]").fancybox({});
									}							
								}						
							}
							
							f_timeout(data);
						 },
					 	 onClickRow: function(rowIndex, rowData){
				 			$(this).datagrid('unselectAll');
				 			$(this).datagrid('selectRow',rowIndex);
				 		 },
				 		 
				 		 view: detailview,
				 	    detailFormatter:function(index,row){
					        return '<div style="padding:2px"><table class="ddv"></table></div>';
					    },
					    
					    onExpandRow: function(index,row){
						     var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');					     
						   	 var queryParams = {"ptaId":row.ptaId};
						        
						        ddv.datagrid({
						            url:'massif_findMassifList.action',
						            queryParams:queryParams,
						            fitColumns:true,
						            singleSelect:true,
						            rownumbers:true,
						            loadMsg:'数据加载中。。。',
						            height:'auto',
						            columns:[[
						                {field:'maName',title:'地块名称',width:100,align : 'center'},
						                {field:'maAcreage',title:'地块面积',width:50,align : 'center'},
						                {field:'typeName',title:'种植品种',width:100,align : 'center'},
						                {field:'startTime',title:'种植时间',width:50,align : 'center'},
						                {field:'getTime',title:'收获时间',width:50,align : 'center'},
						                {field:'state',title:'上期种植品种',width:50,align : 'center'},
										{field : 'maImg',title : '地块平面图',width : 50,align : 'center',formatter: function(value,row,index){
											if(value!=""){
												var maId = "id_"+row.maId;
												var maImg = '<a rel="'+maId+'" href="/nytsyFiles/element/'+value+'"><img width="50" height=50" src="/nytsyFiles/element/'+value+'"/></a>'
												return maImg;
											}
										}},
						            ]],
						            onResize:function(){
						                $('#chandiDatagridchandiDatagrid').datagrid('fixDetailRowHeight',index);
						            },
						            onLoadSuccess:function(data){
						            	
										if(data.total>0){						
											for(var i=0; i< data.total;i++){
												var rowX = data.rows[i];
												if(rowX.maImg!=''){
													var app_group = "id_"+rowX.maId;
													$("a[rel="+app_group+"]").fancybox({});
												}							
											}						
										}
						            	
						                setTimeout(function(){
						                    $('#chandiDatagrid').datagrid('fixDetailRowHeight',index);
						                },0);
						            }
						        });
						        $('#chandiDatagrid').datagrid('fixDetailRowHeight',index);
						    }
					    
					}); 

				}else if(index == 6){
					$("#dimennoframe").attr('src',"jsp/company/dimennoList.jsp?entId="+$("#zizhiEntId").val()); 
				}
			}
		 }
	 
	});	 
	 $.ajax({
		 url:'compfor_getEntTreeByAreaId.action',
		 type:'post',
		 dataType:'json',
		 success:function(result){
			 if(result){
				 result.splice(0, 0, {id:-1,text:'--请选择--'});  
				 $("#areaId").combotree({
						data:result,
						value:-1
					});
			 }
		 }
	 });
	 
});

function stopUse(entId,name){
	$.messager.confirm('提示', '确定要暂停使用选中企业【'+name+'】吗?', function(r) {
		if(r){				
			var data ={"entId":entId,"sts":2}
			$.ajax( {
				url : 'ent_updateSts.action',
				data : data,
				type : 'post',
				dataType : 'text',
				success : function(result) {
					$.messager.show( {title : '提示',msg : result});
					dataGrid.datagrid('reload');
				}
			});
		}
	});
}

function recordWin(recId,objId){
			
	$("#recordframe").attr('src',"jsp/element/records.jsp?recId="+recId+"+&objId="+objId); 
	
	$("#showRecord").window('open').window("center");

}

function batchAudit(ptbId,ptqId){
	$("#batchStateId").val(ptbId);
		
	var typeTabId = "typetab"+ptqId;																 								
	
	$("#typeTabId").val(typeTabId);
	
	$("#batchAudit").window('open').window("center");
}

function submitBatchAudit(){
	
	var ptbId = $("#batchStateId").val();	

	var batchState = $('input[name="batchState"]:checked ').val();
	
	var data = {"proTypeBatch.ptbId":ptbId,"proTypeBatch.batchState":batchState};
	
	$.ajax({
		   type: "POST",
		   url: "proTypeBatch_updateProTypeBatchState.action",
		   data: data,
		   success: function(result){
			   
			var dg = $("#typeTabId").val();
			   
			$("#"+dg).datagrid("reload");
			
			$("#batchAudit").window('close');
			
			$.messager.show( {title : '提示',msg : result});

		}
	});	
	
}



/**
 * 生产档案
 */
/*function showRecord(i){
	$("#showRecord").window('open');
	var record = $('#recordDatagrid').datagrid('getRows')[i];
	
	var objId = record.objId;		//企业ID；分类ID；产品ID
	var objTypeId = record.objTypeId;	//企业1；分类二维码2；产品3
	var recId = record.recId;	//

	var data = {"objElement.objId":objId,"objElement.objTypeId":objTypeId};
	
	$.post("objElement_findObjElementList.action", data ,function(result){
		
		for(var j=0;j<20;j++){
			var exists = $('#recordtab').tabs('exists', 0);  
			if(exists){
				$('#recordtab').tabs('close', 0);  
			}else{
				break;
			}
		}

		if(result.total>0){
			for(var j=0;j<result.total;j++){
				var elem = result.rows[j];				
				var elementName = elem.elementName;								
				$('#recordtab').tabs('add',{    
				    title:elementName,
				    selected: j==0,		// 选中第一个    
				    content:'<iframe src="jsp/element/'+elem.elementUrl+'?recId='+recId+'" width="99%" height="99%" frameborder="0"></iframe>'    
				});  				
			}			
		}else{
			$('#recordtab').tabs('add',{    
			    title:'提示',    
			    content:'<div>该档案暂无信息!</div>'    
			});  
		}
	},"JSON");	
	
}*/


/********************************监管信息开始*****************************************/
var superviseFormUrl = '';
function appendSuperVise(){
	$("#supervise_title").val('');
	$("#xh_editor").val('');	
	$("#btnSupervise").show();
	$("#uploadList").html('');
	$("#supervise_supId").val('');
	$('#superviseForm').form('validate');
	superviseFormUrl = 'supervise_addSupervise.action';
	$('#addSupervise').window('open').window('setTitle', '添加监管信息');;
}

function showSupervise(i){
	var supervise = $('#superviseDataGrid').datagrid("getRows")[i];		
	
	$("#supervise_title2").val(supervise.title);
	$("#supervise_supTime2").val(supervise.supTime);
	$("#supervise_contents2").val(supervise.contents);
	$("#supervise_companyContents2").val(supervise.companyContents);

	$('#showSupervise').window('open').window('setTitle', '查看监管信息');;
}

function updateSuperVise(){
	var supervise = $('#superviseDataGrid').datagrid("getSelected");		
	if(supervise==null){
		$.messager.show( {title : '提示',msg : '请在列表中选择您要修改的监管信息!'});
		return;
	}
	$("input[name='supervise.supId']").val(supervise.supId);
	$("input[name='supervise.supEnt']").val(supervise.supEnt);
	$("input[name='supervise.supUser']").val(supervise.supUser);
	$("input[name='supervise.supCode']").val(supervise.supCode);
	$("input[name='supervise.title']").val(supervise.title);
	$("#supTime").datebox("setValue",supervise.supTime);
	$("textarea[name='supervise.contents']").val(supervise.contents);
	$("#state").combobox("setValue",supervise.state);
	$("#btnSupervise").show();
	$('#superviseForm').form('validate');
	superviseFormUrl = 'supervise_updateSupervise.action';
	$('#addSupervise').window('open').window('setTitle', '修改监管信息');
}

function deleteSupervise(i){
	var supervise = $('#superviseDataGrid').datagrid("getRows")[i];		
	var id = supervise.supId;	
	$.messager.confirm('提示', '是否删除选中数据?', function(r) {
		if (r) {
			$.ajax( {
				url : 'supervise_deleteSupervise.action',
				data : 'ids='+ id,
				type : 'post',
				dataType : 'text',
				success : function(result) {
					$.messager.show( {title : '提示',msg : result});
					$('#superviseDataGrid').datagrid('reload');
				}
			});
		}
	});
}

function submitSuperviseForm(){	
	
	$("#xh_editor").val();
	$('#superviseForm').form('submit', {
		url : superviseFormUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			$.messager.show({ title : '提示', msg : result });   					
			//隐藏提交按钮
			$('#addSupervise').window('close');
			$('#superviseDataGrid').datagrid('reload');
		}
	});
}

/********************************监管信息结束*****************************************/

function submitEvaluationForm(){	
	
	$('#evaluationForm').form('submit', {
		url : 'evaluation_addEvaluation.action',
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			$.messager.show({ title : '提示', msg : result });   					
		}
	});
}


function submitAuditForm(){
	
	var auditstate = $("input[name='auditRecord.state']:checked").val();
	var auditOpinion = $("#auditOpinion").val();
	if(auditOpinion==''||auditOpinion==undefined){
		$.messager.show({ title : '提示', msg : '请填写您的审核意见!' });   
		return;
	}
	$('#auditRecordForm').form('submit', {
		url : 'auditRecord_addAuditRecord.action',
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			$.messager.show({ title : '提示', msg : result });   					
			$('#auditRecordDataGrid').datagrid('reload');
		}
	});
}

function getAuditState(sts){
	//0待提交，1待审核，2暂停，3不通过，4通过；默认为空
	var auditeState = '';
	if(sts==''){
		auditeState = '----';
	}else if(sts==0){
		auditeState = '待提交';
	}else if(sts==1){
		auditeState = '待审核';
	}else if(sts==2){
		auditeState = '暂停';
	}else if(sts==3){
		auditeState = '退回';
	}else if(sts==4){
		auditeState = '通过';
	}
	return auditeState;
}

function reportEnt(i){
	var enterprise = $('#list_enterprise').datagrid("getRows")[i];		
	var entId = enterprise.entId;
	var account = enterprise.account;
	var fileName = account+"-"+entId+".pdf";
	var filePath = $("#filePath").val();
	$.post('ent_findCompanyInfo.action', 'entId='+entId, function(result) {
		if(result&&result==true){
			window.location.href = "downloadFile.action?fileName="+"company/"+fileName;
		}		
	}, "JSON");
}


// 修改
function update(i) {
	$("#companyTab").tabs('select',0);//选择到第一个
	$("#zizhiEntId").val('');
	//$("#proEntId").val('');
	$("#fileQueueLogo").html('');
	$("#logoUrl").val('');
	
	var enterprise = $('#list_enterprise').datagrid("getRows")[i];		
	/*
	if(enterprise.companyRsts!='4'){
		tab_option = $('#companyTab').tabs('getTab',"二维码信息").panel('options').tab;
		tab_option.hide();
	}else{
		tab_option = $('#companyTab').tabs('getTab',"二维码信息").panel('options').tab;
		tab_option.show();
	}
	*/
	$("#zizhiEntId").val(enterprise.entId);
	//$("#proEntId").val(enterprise.entId);
	$("#auditRecordEntId").val(enterprise.entId);
	$("#auditRecordEntName").val(enterprise.name);
	$("#supervise_entId").val(enterprise.entId);
	$("#evaluation_companyId").val(enterprise.entId);
	
	$("#areaId").val(enterprise.areaName);
						
	$("input[name='enterprise.account']").val(enterprise.account);
					
	$('#companyRsts').val(getAuditState( enterprise.companyRsts));
	
	$('#entType_id').val(enterprise.typeName);
	
	$("input[name='enterprise.flag']").val(enterprise.flag);
	$("input[name='enterprise.legalPerson']").val(enterprise.legalPerson);
	$("input[name='enterprise.orgCode']").val(enterprise.orgCode);

	$("input[name='enterprise.entCode']").val(enterprise.entCode);
	$("input[name='enterprise.name']").val(enterprise.name);
	$("input[name='enterprise.regAddr']").val(enterprise.regAddr);
	$("input[name='enterprise.manageAddr']").val(enterprise.manageAddr);

	$("input[name='enterprise.simpleName']").val(enterprise.simpleName);
	$("input[name='enterprise.tel']").val(enterprise.tel);
	$("input[name='enterprise.mobile']").val(enterprise.mobile);

	$("input[name='enterprise.postCode']").val(enterprise.postCode);
	$("input[name='enterprise.domName']").val(enterprise.domName);
	$("input[name='enterprise.sign']").val(enterprise.sign);
	$("input[name='enterprise.email']").val(enterprise.email);
	$("input[name='enterprise.seq']").val(enterprise.seq);
	$("input[name='enterprise.logoUrl']").val(enterprise.logoUrl);
	$("input[name='enterprise.sign']").val(enterprise.sign);
	$("textarea[name='enterprise.intro']").val(delHtmlTag(enterprise.intro));
	$("input[name='enterprise.entId']").val(enterprise.entId);
	$("input[name='enterprise.crtUserId']").val(enterprise.crtUserId);

	$("input[name='enterprise.isReported']").val(enterprise.isReported);
	$("input[name='enterprise.sysCode']").val(enterprise.sysCode);
	
	

	isbatch = enterprise.isbatch;
	if(isbatch==0){
		$("input[name='enterprise.isbatch']").val('否');
	}else{
		$("input[name='enterprise.isbatch']").val('是');
	}
	

		
	$("#add_form").form("validate");
	$('#add').window('open').window('setTitle', '查看企业信息').window("maximize");	

}
//去掉html标签
function delHtmlTag(str){
	  return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
}
// 隐藏
function clearForm(id) {
	if(id==1){
		$('#add').window('close');
	}else{
		$('#show').window('close');
	}
}

function getRsts(value){
	if( value=='0'){ return '待提交' }
	else if( value=='1'){ return '待审核' }
	else if( value=='2'){ return '暂停' }
	else if( value=='3'){ return '退回' }
	else if( value=='4'){ return '通过' }
	else{return '';};   
}


// 搜索
function find() {
	var entSearch = $('#entSearch').val();
	var name = $('#nameSearch').val();
	var companyRstsSearch = $('#companyRstsSearch').combobox('getValue');
/*	var enterpriseisReported = $('#enterpriseisReported').combobox('getValue');*/
	var enterprise = {};
	if(entSearch!=""){enterprise["enterprise.parentName"]=entSearch};
	if(name != ""){enterprise["enterprise.name"]=name};
	if (companyRstsSearch != "") {enterprise["enterprise.companyRsts"]=companyRstsSearch};
	/*if (enterpriseisReported != "") {enterprise["enterprise.isReported"]=enterpriseisReported};*/
	var areaId = $("#areaId").combotree('getValue');
	if(areaId!=-1){
		enterprise["enterprise.areaId"]=areaId;
	}


	dataGrid.datagrid('load', enterprise); // 点击搜索
}

//重置
function clearSearch(){
	$('#entSearch').val("");
	$('#nameSearch').val("");
	$('#companyRstsSearch').combobox('setValue','');
	$('#enterpriseisReported').combobox('setValue','0');
//	var enterpriseisReported = $('#enterpriseisReported').combobox('getValue');
	var enterprise = {};
	//if (enterpriseisReported != "") {enterprise["enterprise.isReported"]=enterpriseisReported};
	
	$("#areaId").combotree('setValue',-1);
	
	dataGrid.datagrid('load',enterprise);
}

function removeit(){

	var rows = $("#list_enterprise").datagrid("getSelections");
	// 判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要删除的记录!', 'info');
		return false;
	} else {
		var enterprise = rows[0];
		var temp = enterprise.entId;
		var isAdmin = $("#isAdmin").val();	
		if(enterprise.companyRsts=='0'||isAdmin=='y'){
			$.messager.confirm('提示', '确定要删除选中企业【'+enterprise.name+'】吗?', function(r) {
				if(r){
					$.ajax( {
						url : 'ent_deleteEnterprise.action',
						data : 'list_enterprise=' + temp,
						type : 'post',
						dataType : 'text',
						success : function(result) {
							$.messager.show( {title : '提示',msg : result});
							dataGrid.datagrid('reload');
						}
					});
				}
			});
		}else{
			$.messager.show( {title : '提示',msg : "待审核及审核完成企业不能进行删除!"});
		}

	}

}
/**
 * 
 * @param zid
 * @return
 */
function attachWin(i){
	var zizhi = $('#zizhiDataGrid').datagrid("getRows")[i]; 
	var zid = zizhi.id;
	var zname = zizhi.zizhiName;
	showZizhiAttachFile(zid);
		
	$("#attachWin").window('open').window('center').window('setTitle',zname+'-附件管理');
}
/**
 * 显示上传的资质附件
 * @param zid
 * @return
 */	
function showZizhiAttachFile(zid){	
	var filePath = $("#filePath").val();
	$("#zizhiFile").html('');
	$.post('zizhiAppendix_findZizhiAppendixPagerList.action', 'zid='+zid, function(result) {
		if(result&&result.total>0){
			var html ='';
			for(var i = 0; i<result.total;i++){
				var appd = result.rows[i];
				if(appd){
					var path = filePath+"zizhi/"+appd.path;
					var appName = appd.appName;
					html +='<a rel="zizhi_group" href="'+path+'" title="'+appName+'"><img border="0" style="margin:5px" alt="" width="150" height="150" src="'+path+'" /></a>';							
				}				
			}
			//	alert(html);
			$("#zizhiFile").html(html);
				
			$("a[rel=zizhi_group]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
		}else{
			$("#zizhiFile").html('暂无附件!');
		}			
	}, "JSON");	
}

/**************************************************生产信息***********************************************************************/

/**
 * 
 * @param index
 * @return
 */
function attachWin1(i){
	var production = $('#productionDataGrid').datagrid("getRows")[i]; 
	var filePath = $("#filePath").val();

	$("#productionFile").html('');
	$("#licenceFile").html('');

	var proId = production.proId;
	$.post('productionAppendix_findProductionAppendixPagerList.action', 'proId='+proId, function(result) {
		if(result&&result.total>0){
			var itemTemplate ='';				
			var itemTemplate1 ='';			
			for(var i = 0; i<result.total;i++){
				var appd = result.rows[i];
				var appName = appd.appName;
				var path = filePath+"production/"+appd.path;
				
				if(appd.appType==0){
					itemTemplate1 +='<a rel="licence_group" href="'+path+'" title="'+appName+'"><img border="0" style="margin:5px" alt="" width="150" height="150" src="'+path+'" /></a>';							
				}else if(appd.appType==1){
					itemTemplate +='<a rel="production_group" href="'+path+'" title="'+appName+'"><img border="0" style="margin:5px" alt="" width="150" height="150" src="'+path+'" /></a>';							
				}												
			}
			if(itemTemplate==''){
				itemTemplate = '暂无附件!';
			}else{
				$("#productionFile").html(itemTemplate);
				$("a[rel=production_group]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}
			if(itemTemplate1==''){itemTemplate1 = '暂无附件!';}else{
				$("#licenceFile").html(itemTemplate1);
				$("a[rel=licence_group]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}
		}else{
			$("#productionFile").html('暂无附件!');
			$("#licenceFile").html('暂无附件!');
		}			
	}, "JSON");
	
	$("#attachWin1").window('open').window('center').window('setTitle','生产信息-附件管理');
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

function shwoLog(entId) {	  
	  var logVo = {};
	  logVo["logVo.entId"]=entId;
	   $('#logTable').datagrid({  
	       //title : '操作记录管理',  
	      // iconCls : 'icon-log',  
	       pageSize : 15,//默认选择的分页是每页5行数据  
	 	  pageList : [ 10, 15,20, 30, 50,100 ],// 可以选择的分页集合
	       nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	       striped : true,//设置为true将交替显示行背景。  
	       //collapsible : true,//显示可折叠按钮  
	       //toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	       url:'log_findLogList.action',//url调用Action方法  
	       queryParams:logVo,
	       loadMsg : '数据装载中......',  
	       fit:true,
	   	   fitColumns: true,
	       remoteSort : false,  
	       pagination : true,//分页  
	       rownumbers : true,//行数  
	       columns:[[
	 			{field:'funcName',title:'操作记录',width:200,align:'center'},
	 			{field:'actType',title:'操作类型',width:100,align:'center',formatter: function(value,row,index){
	 				if(value == 1){
	 					return "新增";
	 				}else if(value == 2){
	 					return "删除";
	 				}else if(value == 3){
	 					return "修改";
	 				}else if(value == 4){
	 					return "查询";
	 				}else if(value == 5){
	 					return "登陆";
	 				}else if(value == 6){
	 					return "退出";
	 				}else if(value == 7){
	 					return "发布";
	 				}else if(value == 8){
	 					return "停用";
	 				}
	 				
	 			}},
	 			//{field:'account',title:'用户所属机构',width:100,align:'center'},
	 			{field:'userName',title:'操作用户',width:100,align:'center'},
	 			{field:'computerIp',title:'操作电脑IP',width:100,align:'center'},
	 			{field:'logTime',title:'日期',width:100,align:'center'}
	       ]],
	       onLoadSuccess:function(data){
	 	 		$("#showLog").window("open"); 	 	
	  		}
	       });   
	 }
