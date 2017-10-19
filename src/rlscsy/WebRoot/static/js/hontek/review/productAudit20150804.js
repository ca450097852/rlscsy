var filePath = "";
$(function() {
	
	filePath = $("#filePath").val();
	
	$('#product_detail').window('close');

	// 表格数据
	$('#productdatagrid').datagrid({
		title : '产品信息管理',
		iconCls : 'icon-product',
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [10, 20, 50,100],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		// collapsible : true,//显示可折叠按钮
		toolbar : "#tb",// 在添加 增添、删除、修改操作的按钮要用到这个
		url : 'pro_findProductList.action',// url调用Action方法
		loadMsg : '数据装载中......',
		fit : true,
		// singleSelect:true,//为true时只能选择单行
		fitColumns : true,// 允许表格自动缩放，以适应父容器
		// sortName : 'purchNo',//当数据表格初始化时以哪一列来排序
		// sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		frozenColumns : [[{
					field : 'ck',
					checkbox : true
				}]],
		columns : [[{
					field : 'proCode',
					title : '产品编码',
					width : 80,
					align : 'center'
				}, {
					field : 'proName',
					title : '产品名称',
					width : 100,
					align : 'center'
				}, {
					field : 'typeName',
					title : '分类',
					width : 50,
					align : 'center'
				},
				{
					field : 'entName',
					title : '所属企业',
					width : 120,
					align : 'center'
				},
				{
					field : 'distributorAddr',
					title : '企业地址',
					width : 120,
					align : 'center'
				}, /*{
					field : 'state',
					title : '状态',
					width : 50,
					align : 'center',
					formatter : function(value, row, index) {
						if (value == 0) {
							return "待审";
						} else if (value == 1) {
							return "生产中";
						} else {
							return "已停产";
						}
					}
				},*/ {
					field : 'sysCode',
					title : '操作',
					width : 80,
					align : 'center',
					formatter : function(value, row, index) {
						if(value=="A002001"){
							return "<a href='javascript:void(0)' onclick='showDetail2("+ index+ ")'>查看明细</a>&nbsp;&nbsp;";
						}
						return "<a href='javascript:void(0)' onclick='showDetail("+ index+ ")'>查看明细</a>&nbsp;&nbsp;";
						
					}
				}

		]],
		pagination : true,// 分页
		rownumbers : true// 行数
		,
		onLoadSuccess : function(data) {
			f_timeout(data);
		},
		onClickRow : function(rowIndex, rowData) {
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow', rowIndex);
		}
	});
	
	
	$('#pro_type_1').combotree({
		url:'proType_getProTypeTreeToPro.action',
		panelWidth:150
	});
	
	
	$("#product_tabs").tabs({
		onSelect: function(title,index){
		var target = this;
		//选择不是第一个选项卡页
		if(index>0){
			var proId = $("#proId").val();
			//1种植类； 2养殖类
			var typeClass = $("#typeClass").val();

			if(index==1){
				//产地信息
				showProArea(proId);						
			}else if(index==2){
				//种源信息 
				showProSeed(proId,typeClass);		
			}else if(index==3){
				//种养信息 
				showPlantRaise(proId,typeClass);		
			}else if(index==4){
				//防疫信息 
				showPrevention(proId,typeClass);	
			}else if(index==5){
				//检验信息 
				showProCheck(proId);	
			}else if(index==6){
				//加工信息 
				showProcess(proId);	
			}else if(index==7){
				//储运信息 
				showStoreTransport(proId);	
			}else if(index==8){
				//批次信息 
				showProBatch(proId);	
			}
		}
	 }
	});//打开时选中第一个tab页
	
	$("#product_tabs2").tabs({
		onSelect: function(title,index){
		var target = this;
		//选择不是第一个选项卡页
		if(index>0){
			var proId = $("#proId").val();
			//1种植类； 2养殖类
			if(index==1){
				//批次信息 
				showProBatch2(proId);	
			}
		}
	 }
	});//打开时选中第一个tab页
	
});

function showDetail(index) {
	var row = $('#productdatagrid').datagrid("getRows")[index];
	var state = row.state == 0 ? "待审" : row.state == 1 ? "生产中" : "已停产";
	var typeClass = row.typeClass;
	var proId = row.proId;
	//赋值
	$("#proId").val(row.proId);
	$("#typeClass").val(typeClass);
	//1种植类； 2养殖类	
	var tab3 = $('#product_tabs').tabs('getTab',3);  // 获取选择的面板
	var tab4 = $('#product_tabs').tabs('getTab',4);  // 获取选择的面板

	if(typeClass==1){
		$('#product_tabs').tabs('update', {
			tab: tab3,options: {title: '施肥信息'}
		});
		$('#product_tabs').tabs('update', {
			tab: tab4,options: {title: '植保信息'}
		});
	}else{
		$('#product_tabs').tabs('update', {
			tab: tab3,options: {title: '喂养信息'}
		});
		$('#product_tabs').tabs('update', {
			tab: tab4,options: {title: '防疫信息'}
		});
	}

	$("#product_tabs").tabs("select",0);//打开时选中第一个tab页
	
	var tableHtml = "<tr><td style='width: 80px;height: 25px'>产品名称：</td><td class='td_text'>"+row.proName+"</td><td style='width: 80px'>产品分类：</td><td class='td_text'>"+row.typeName+"</td></tr>";
	
		tableHtml += "<tr><td style='width: 80px;height: 25px'>产品编码：</td><td>"+row.proCode+"</td><td style='width: 80px'>条形码：</td><td>"+row.barcode+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>单位规格：</td><td>"+row.unit+"</td><td style='width: 80px'>生产状态：</td><td>"+state+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>储藏条件：</td><td>"+row.storageConditions+"</td><td style='width: 80px'>保质期：</td><td>"+row.shelfLife+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>三品一标一名牌：</td><td>"+row.authentication+"</td><td style='width: 80px'>保鲜防腐：</td><td>"+row.retain+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>生产商：</td><td>"+row.manufacturer+"</td><td style='width: 80px'>生产商电话：</td><td>"+row.sourceTel+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>生产商地址：</td><td colspan='3'>"+row.sourceAddr+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>经销商：</td><td>"+row.distributor+"</td><td style='width: 80px'>经销商电话：</td><td>"+row.distributorTel+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>经销商地址：</td><td colspan='3'>"+row.distributorAddr+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px'>产品说明：</td><td colspan='3'><textarea rows='3' style='width: 100%;border: 0'>"+row.proDesc+"</textarea></td></tr>";
		    			
		$("#det").html(tableHtml);			
		$("#productPicBox").html("");	

		//附件
		var productAppend = {};
		productAppend["productAppend.proId"]=proId;
		$.ajax({
			url : 'proApp_findProAppList.action',
			data : productAppend,
			type : 'post',
			dataType : 'json',
			cache: false,
			success : function(apps) {
				if(apps&&apps.total>0){					
					var liHtml = '<div class="picMoveX01 c_scrollPic01"><p class="bbtn prevBtn"><span class="last"></span></p><div class="picBox"><ul>';							    						            			
					for(var i=0;i<apps.rows.length;i++){
						var app = apps.rows[i];
						var imgPath = filePath+"proimg/"+app.path;
						liHtml += "<li><p><a rel=\"product_group\" href=\""+imgPath+"\" title=\""+app.appName+"\"><img border=0 src=\""+imgPath+"\" height=\"100\"></a></p><p>"+app.appName+"</p></li>";							
					}
					liHtml +='</ul></div><p class="bbtn nextBtn"><span></span></p></div>';
					
					$("#productPicBox").html(liHtml);	
												
					//图片横向滚动
					var imgScroll_01 = new funPicScrollX(".c_scrollPic00 .picBox",".c_scrollPic00 .prevBtn span",".c_scrollPic00 .nextBtn span",28,false).init();
					
					$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
						var lenth = $(this).find("li").length;
						if(lenth<=4){
							$(this).find(".nextBtn span").eq(0).addClass("last");
						}
					});
					
					////产地图片预览效果
					$("a[rel=product_group]").fancybox({
						'transitionIn'		: 'none',
						'transitionOut'		: 'none',
						'titlePosition' 	: 'over',
						'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
							return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
						}
					});							
				}
			}
		});
		
		
		$('#product_detail').window('open');

}

/**
 * 显示产地信息
 * @param proId 产品ID
 * @return
 */
function showProArea(proId) {
	var proArea = {};
	proArea["proArea.proId"]=proId;
	$("#proArea").html('');	
	$("#proAreaPicBox").html('');	
	
	$.ajax({
		url : 'proArea_findProAreaList.action',
		data : proArea,
		type : 'post',
		dataType : 'json',
		cache: false,
		success : function(result) {
			if(result&&result.total>0){
				var tableHtml = "";
				for(var i=0;i<result.total;i++){
					var row = result.rows[i];
										
					tableHtml += "<table width='99%'>";					
					tableHtml += "<tr><td style='width: 60px;height: 25px'>产地名称：</td><td>"+row.areaName+"</td></tr>"; 
					tableHtml += "<tr><td style='width: 60px'>产地地址：</td><td>"+row.areaAddr+"</td></tr>";					
					tableHtml += "<tr><td style='width: 60px'>产地介绍：</td><td colspan='3'><textarea rows='2' style='width: 100%;border: 0'>"+row.areaIntro+"</textarea></td></tr>";			
					tableHtml += "<tr><td style='width: 60px'>气候环境：</td><td colspan='3'><textarea rows='2' style='width: 100%;border: 0'>"+row.climatope+"</textarea></td></tr>";
					tableHtml += "<tr><td style='width: 60px'>土壤环境：</td><td colspan='3'><textarea rows='2' style='width: 100%;border: 0'>"+row.edatope+"</textarea></td></tr>";
					tableHtml += "<tr><td style='width: 60px'>水源环境：</td><td colspan='3'><textarea rows='2' style='width: 100%;border: 0'>"+row.areaWater+"</textarea></td></tr>";
					tableHtml += "</table>";
				}				
				$("#proArea").html(tableHtml);	
				
				//附件
				var traceAppdix = {};
				traceAppdix["traceAppdix.proId"]=proId;
				traceAppdix["traceAppdix.appdixType"]="1";				
				$.ajax({
					url : 'traceApp_findTraceAppdixsListByProId.action',
					data : traceAppdix,
					type : 'post',
					dataType : 'json',
					cache: false,
					success : function(apps) {
						if(apps&&apps.length>0){
							var liHtml = '<div class="picMoveX01 c_scrollPic01"><p class="bbtn prevBtn"><span class="last"></span></p><div class="picBox"><ul>';							    						            			
							for(var i=0;i<apps.length;i++){
								var app = apps[i];
								var imgPath = filePath+"proimg/"+app.appdixUrl;
								liHtml += "<li><p><a rel=\"area_group\" href=\""+imgPath+"\" title=\""+app.appdixName+"\"><img border=0 src=\""+imgPath+"\" height=\"100\"></a></p><p>"+app.appdixName+"</p></li>";							
							}
							liHtml +='</ul></div><p class="bbtn nextBtn"><span></span></p></div>';
							
							$("#proAreaPicBox").html(liHtml);	
														
							//图片横向滚动
							var imgScroll_01 = new funPicScrollX(".c_scrollPic01 .picBox",".c_scrollPic01 .prevBtn span",".c_scrollPic01 .nextBtn span",28,false).init();
							
							$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
								var lenth = $(this).find("li").length;
								if(lenth<=4){
									$(this).find(".nextBtn span").eq(0).addClass("last");
								}
							});
							
							////产地图片预览效果
							$("a[rel=area_group]").fancybox({
								'transitionIn'		: 'none',
								'transitionOut'		: 'none',
								'titlePosition' 	: 'over',
								'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
									return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
								}
							});							
						}
					}
				});
			}else{
				$("#proArea").html("<font size='15'>该产品没有对应的产地信息!<font>");
			}
		}
	});
}

/**
 * 显示种源信息
 * @param proId 产品ID
 * @return
 */
function showProSeed(proId,typeClass) {
	var proSeed = {};
	proSeed["proSeed.proId"]=proId;
	$("#proSeed").html('');	
	$("#proSeedPicBox").html('');	
	$.ajax({
		url : 'proSeed_findProSeedList.action',
		data : proSeed,
		type : 'post',
		dataType : 'json',
		cache: false,
		success : function(result) {
			if(result&&result.total>0){
				var tableHtml = "";
				for(var i=0;i<result.total;i++){
					var row = result.rows[i];			
					tableHtml += "<table width='99%'>";					
					tableHtml += "<tr><td class='td_title'>种苗名称：</td><td>"+row.seedName+"</td></tr>" ;
					tableHtml += "<tr><td class='td_title'>种苗厂家：</td><td>"+row.seedCompany+"</td></tr>";					
					tableHtml += "<tr><td class='td_title'>厂家地址：</td><td>"+row.seedAddr+"</td></tr>";			
					tableHtml += "<tr><td class='td_title'>特征特性：</td><td><textarea rows='3' style='width: 100%;border: 0'>"+row.feature+"</textarea></td></tr>";
					tableHtml += "</table>";
				}				
				$("#proSeed").html(tableHtml);		
				
				//附件
				var traceAppdix = {};
				traceAppdix["traceAppdix.proId"]=proId;
				traceAppdix["traceAppdix.appdixType"]="2";				
				$.ajax({
					url : 'traceApp_findTraceAppdixsListByProId.action',
					data : traceAppdix,
					type : 'post',
					dataType : 'json',
					cache: false,
					success : function(apps) {
						if(apps&&apps.length>0){
							var liHtml = '<div class="picMoveX01 c_scrollPic02"><p class="bbtn prevBtn"><span class="last"></span></p><div class="picBox"><ul>';							    						            			
							for(var i=0;i<apps.length;i++){
								var app = apps[i];
								var imgPath = filePath+"proimg/"+app.appdixUrl;
								liHtml += "<li><p><a rel=\"seed_group\" href=\""+imgPath+"\" title=\""+app.appdixName+"\"><img border=0 src=\""+imgPath+"\" height=\"100\"></a></p><p>"+app.appdixName+"</p></li>";							
							}
							liHtml +='</ul></div><p class="bbtn nextBtn"><span></span></p></div>';

							$("#proSeedPicBox").html(liHtml);	
														
							//图片横向滚动
							var imgScroll_01 = new funPicScrollX(".c_scrollPic02 .picBox",".c_scrollPic02 .prevBtn span",".c_scrollPic02 .nextBtn span",28,false).init();
							
							$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
								var lenth = $(this).find("li").length;
								if(lenth<=4){
									$(this).find(".nextBtn span").eq(0).addClass("last");
								}
							});
							
							//图片预览效果
							$("a[rel=seed_group]").fancybox({
								'transitionIn'		: 'none',
								'transitionOut'		: 'none',
								'titlePosition' 	: 'over',
								'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
									return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
								}
							});							
						}
					}
				});
				
			}else{
				$("#proSeed").html("<font size='15'>该产品没有对应的种源信息!<font>");
			}
		}
	});
}

/**
 * 显示施肥/喂养信息
 * @param proId 产品ID
 * @return
 */
function showPlantRaise(proId,typeClass) {
	var plantRaise = {};
	plantRaise["plantRaise.proId"]=proId;
	//1种植类； 2养殖类	
	var fieldName1 = "肥料名称：";
	var fieldName2 = "厂家名称：";
	var fieldName3 = "施肥方法：";
	var fieldName4 = "施肥周期：";
	var fieldName5 = "施肥时间：";
	var fieldName6 = "施肥用量：";
	if(typeClass==2){
		var fieldName1 = "饲料名称：";
		var fieldName2 = "厂家名称：";
		var fieldName3 = "喂养方法：";
		var fieldName4 = "喂养周期：";
		var fieldName5 = "喂养时间：";
		var fieldName6 = "喂养用量：";
	}
	$("#plantRaise").html('');		
	$("#plantRaisePicBox").html('');	
	
	$.ajax({
		url : 'plantRaise_findPlantRaiseList.action',
		data : plantRaise,
		type : 'post',
		dataType : 'json',
		cache: false,
		success : function(result) {
			if(result&&result.total>0){
				var tableHtml = "";
				for(var i=0;i<result.total;i++){
					var row = result.rows[i];			
					tableHtml += "<table width='99%'>";					
					tableHtml += "<tr><td class='td_title'>"+fieldName1+"</td><td class='td_text'>"+row.feedName+"</td><td class='td_title'>"+fieldName2+"</td><td class='td_text'>"+row.feedCompany+"</td></tr>";					
					tableHtml += "<tr><td class='td_title'>"+fieldName3+"</td><td>"+row.feedWay+"</td><td class='td_title'>"+fieldName4+"</td><td>"+row.feedCycle+"</td></tr>";					
					tableHtml += "<tr><td class='td_title'>"+fieldName5+"</td><td>"+row.feedTime+"</td><td class='td_title'>"+fieldName6+"</td><td>"+row.dosage+"</td></tr>";					
					tableHtml += "</table>";
				}				
				$("#plantRaise").html(tableHtml);		
				
				//附件
				var traceAppdix = {};
				traceAppdix["traceAppdix.proId"]=proId;
				traceAppdix["traceAppdix.appdixType"]="3";				
				$.ajax({
					url : 'traceApp_findTraceAppdixsListByProId.action',
					data : traceAppdix,
					type : 'post',
					dataType : 'json',
					cache: false,
					success : function(apps) {
						if(apps&&apps.length>0){
							var liHtml = '<div class="picMoveX01 c_scrollPic03"><p class="bbtn prevBtn"><span class="last"></span></p><div class="picBox"><ul>';							    						            			
							for(var i=0;i<apps.length;i++){
								var app = apps[i];
								var imgPath = filePath+"proimg/"+app.appdixUrl;
								liHtml += "<li><p><a rel=\"plantRaise_group\" href=\""+imgPath+"\" title=\""+app.appdixName+"\"><img border=0 src=\""+imgPath+"\" height=\"100\"></a></p><p>"+app.appdixName+"</p></li>";							
							}
							liHtml +='</ul></div><p class="bbtn nextBtn"><span></span></p></div>';

							$("#plantRaisePicBox").html(liHtml);	
														
							//图片横向滚动
							var imgScroll_01 = new funPicScrollX(".c_scrollPic03 .picBox",".c_scrollPic03 .prevBtn span",".c_scrollPic03 .nextBtn span",28,false).init();
							
							$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
								var lenth = $(this).find("li").length;
								if(lenth<=4){
									$(this).find(".nextBtn span").eq(0).addClass("last");
								}
							});
							
							////图片预览效果
							$("a[rel=plantRaise_group]").fancybox({
								'transitionIn'		: 'none',
								'transitionOut'		: 'none',
								'titlePosition' 	: 'over',
								'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
									return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
								}
							});							
						}
					}
				});
								
			}else{
				$("#plantRaise").html("<font size='15'>该产品没有对应的种值或养殖信息!<font>");
			}
		}
	});
}

/**
 * 显示防疫信息
 * @param proId 产品ID
 * @return
 */
function showPrevention(proId,typeClass) {
	var prevention = {};
	prevention["prevention.proId"]=proId;
	$("#prevention").html('');
	$("#preventionPicBox").html('');	
	$.ajax({
		url : 'prevention_findPreventionList.action',
		data : prevention,
		type : 'post',
		dataType : 'json',
		cache: false,
		success : function(result) {
			if(result&&result.total>0){
				var tableHtml = "";
				for(var i=0;i<result.total;i++){
					var row = result.rows[i];			
					tableHtml += "<table width='99%'>";					
					tableHtml += "<tr><td class='td_title'>药品名称：</td><td class='td_text'>"+row.drugName+"</td><td class='td_title'>药品厂家：</td><td class='td_text'>"+row.drugCompany+"</td></tr>";					
					tableHtml += "<tr><td >用药方法：</td><td>"+row.drugWay+"</td><td >用药剂量：</td><td>"+row.dosage+"</td></tr>";			
					tableHtml += "<tr><td >用药时间：</td><td>"+row.drugTime+"</td><td >用药周期：</td><td>"+row.drugCycle+"</td></tr>";			
					tableHtml += "<tr><td >防治对象：</td><td colspan='3'>"+row.drugObject+"</td></tr>";
					tableHtml += "</table>";
				}								
				$("#prevention").html(tableHtml);		
				
				//附件
				var traceAppdix = {};
				traceAppdix["traceAppdix.proId"]=proId;
				traceAppdix["traceAppdix.appdixType"]="4";				
				$.ajax({
					url : 'traceApp_findTraceAppdixsListByProId.action',
					data : traceAppdix,
					type : 'post',
					dataType : 'json',
					cache: false,
					success : function(apps) {
						if(apps&&apps.length>0){
							var liHtml = '<div class="picMoveX01 c_scrollPic04"><p class="bbtn prevBtn"><span class="last"></span></p><div class="picBox"><ul>';							    						            			
							for(var i=0;i<apps.length;i++){
								var app = apps[i];
								var imgPath = filePath+"proimg/"+app.appdixUrl;
								liHtml += "<li><p><a rel=\"prevention_group\" href=\""+imgPath+"\" title=\""+app.appdixName+"\"><img border=0 src=\""+imgPath+"\" height=\"100\"></a></p><p>"+app.appdixName+"</p></li>";							
							}
							liHtml +='</ul></div><p class="bbtn nextBtn"><span></span></p></div>';

							$("#preventionPicBox").html(liHtml);	
														
							//图片横向滚动
							var imgScroll_01 = new funPicScrollX(".c_scrollPic04 .picBox",".c_scrollPic04 .prevBtn span",".c_scrollPic04 .nextBtn span",28,false).init();
							
							$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
								var lenth = $(this).find("li").length;
								if(lenth<=4){
									$(this).find(".nextBtn span").eq(0).addClass("last");
								}
							});
							
							////图片预览效果
							$("a[rel=prevention_group]").fancybox({
								'transitionIn'		: 'none',
								'transitionOut'		: 'none',
								'titlePosition' 	: 'over',
								'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
									return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
								}
							});							
						}
					}
				});
				
			}else{
				$("#prevention").html("<font size='15'>该产品没有对应的防疫信息!<font>");
			}
		}
	});
}

/**
 * 显示检验信息
 * @param proId 产品ID
 * @return
 */
function showProCheck(proId) {
	var proCheck = {};
	proCheck["proCheck.proId"]=proId;
	$("#proCheck").html('');		
	$("#proCheckPicBox").html('');		
	$.ajax({
		url : 'proCheck_findProCheckList.action',
		data : proCheck,
		type : 'post',
		dataType : 'json',
		cache: false,
		success : function(result) {
			if(result&&result.total>0){
				var tableHtml = "";
				for(var i=0;i<result.total;i++){
					var row = result.rows[i];			
					tableHtml += "<table width='99%'>";					
					tableHtml += "<tr><td class='td_title'>报告名称：</td><td class='td_text'>"+row.checkName+"</td><td class='td_title'>报告编号：</td><td class='td_text'>"+row.checkNum+"</td></tr>";					
					tableHtml += "<tr><td class='td_title'>检验单位：</td><td>"+row.checkUnit+"</td><td class='td_title'>检验时间：</td><td>"+row.checkTime+"</td></tr>";			
					tableHtml += "<tr><td class='td_title'>检验结果：</td><td colspan='3'>"+row.checkResult+"</td></tr>";
					tableHtml += "</table>";
				}				
				$("#proCheck").html(tableHtml);		
				
				//附件
				var traceAppdix = {};
				traceAppdix["traceAppdix.proId"]=proId;
				traceAppdix["traceAppdix.appdixType"]="5";				
				$.ajax({
					url : 'traceApp_findTraceAppdixsListByProId.action',
					data : traceAppdix,
					type : 'post',
					dataType : 'json',
					cache: false,
					success : function(apps) {
						if(apps&&apps.length>0){
							var liHtml = '<div class="picMoveX01 c_scrollPic05"><p class="bbtn prevBtn"><span class="last"></span></p><div class="picBox"><ul>';							    						            			
							for(var i=0;i<apps.length;i++){
								var app = apps[i];
								var imgPath = filePath+"proimg/"+app.appdixUrl;
								liHtml += "<li><p><a rel=\"proCheck_group\" href=\""+imgPath+"\" title=\""+app.appdixName+"\"><img border=0 src=\""+imgPath+"\" height=\"100\"></a></p><p>"+app.appdixName+"</p></li>";							
							}
							liHtml +='</ul></div><p class="bbtn nextBtn"><span></span></p></div>';

							$("#proCheckPicBox").html(liHtml);	
														
							//图片横向滚动
							var imgScroll_01 = new funPicScrollX(".c_scrollPic05 .picBox",".c_scrollPic05 .prevBtn span",".c_scrollPic05 .nextBtn span",28,false).init();
							
							$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
								var lenth = $(this).find("li").length;
								if(lenth<=4){
									$(this).find(".nextBtn span").eq(0).addClass("last");
								}
							});
							
							////图片预览效果
							$("a[rel=proCheck_group]").fancybox({
								'transitionIn'		: 'none',
								'transitionOut'		: 'none',
								'titlePosition' 	: 'over',
								'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
									return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
								}
							});							
						}
					}
				});
				
			}else{
				$("#proCheck").html("<font size='15'>该产品没有对应的检验信息!<font>");
			}
		}
	});
}

/**
 * 显示加工包装信息
 * @param proId 产品ID
 * @return
 */
function showProcess(proId) {
	var process = {};
	process["process.proId"]=proId;
	$("#proProcess").html('');		
	$("#proProcessPicBox").html('');	
	$.ajax({
		url : 'process_findProcessList.action',
		data : process,
		type : 'post',
		dataType : 'json',
		cache: false,
		success : function(result) {
			if(result&&result.total>0){
				var tableHtml = "";
				for(var i=0;i<result.total;i++){
					var row = result.rows[i];			
					tableHtml += "<table width='99%'>";					
					tableHtml += "<tr><td style='width: 70px;height: 25px'>加工厂家：</td><td class='td_text'>"+row.processCompany+"</td><td style='width: 60px'>加工地址：</td><td class='td_text'>"+row.processAddr+"</td></tr>";			
					tableHtml += "<tr><td style='width: 70px;height: 25px'>加工负责人：</td><td>"+row.processUser+"</td><td style='width: 60px'>加工时间：</td><td>"+row.processTime+"</td></tr>";					

					tableHtml += "</table>";
				}				
				$("#proProcess").html(tableHtml);		
				
				
				//附件
				var traceAppdix = {};
				traceAppdix["traceAppdix.proId"]=proId;
				traceAppdix["traceAppdix.appdixType"]="6";				
				$.ajax({
					url : 'traceApp_findTraceAppdixsListByProId.action',
					data : traceAppdix,
					type : 'post',
					dataType : 'json',
					cache: false,
					success : function(apps) {
						if(apps&&apps.length>0){
							var liHtml = '<div class="picMoveX01 c_scrollPic06"><p class="bbtn prevBtn"><span class="last"></span></p><div class="picBox"><ul>';							    						            			
							for(var i=0;i<apps.length;i++){
								var app = apps[i];
								var imgPath = filePath+"proimg/"+app.appdixUrl;
								liHtml += "<li><p><a rel=\"process_group\" href=\""+imgPath+"\" title=\""+app.appdixName+"\"><img border=0 src=\""+imgPath+"\" height=\"100\"></a></p><p>"+app.appdixName+"</p></li>";							
							}
							liHtml +='</ul></div><p class="bbtn nextBtn"><span></span></p></div>';

							$("#proProcessPicBox").html(liHtml);	
														
							//图片横向滚动
							var imgScroll_01 = new funPicScrollX(".c_scrollPic06 .picBox",".c_scrollPic06 .prevBtn span",".c_scrollPic06 .nextBtn span",28,false).init();
							
							$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
								var lenth = $(this).find("li").length;
								if(lenth<=4){
									$(this).find(".nextBtn span").eq(0).addClass("last");
								}
							});
							
							////图片预览效果
							$("a[rel=process_group]").fancybox({
								'transitionIn'		: 'none',
								'transitionOut'		: 'none',
								'titlePosition' 	: 'over',
								'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
									return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
								}
							});							
						}
					}
				});
				
			}else{
				$("#proProcess").html("<font size='15'>该产品没有对应的加工包装信息!<font>");
			}
		}
	});
}

/**
 * 显示仓储运输信息
 * @param proId 产品ID
 * @return
 */
function showStoreTransport(proId) {
	var storeTransport = {};
	storeTransport["storeTransport.proId"]=proId;
	$("#proStoreTransport").html('');	
	$("#proStoreTransportPicBox").html('');	

	$.ajax({
		url : 'storetransport_findStoreTransportList.action',
		data : storeTransport,
		type : 'post',
		dataType : 'json',
		cache: false,
		success : function(result) {
			if(result&&result.total>0){
				var tableHtml = "";
				for(var i=0;i<result.total;i++){
					var row = result.rows[i];			
					tableHtml += "<table width='99%'>";					
					tableHtml += "<tr><td class='td_title'>仓储方式：</td><td>"+row.storageWay+"</td></tr>";	
					tableHtml += "<tr><td class='td_title'>仓储条件：</td><td>"+row.storageCondi+"</td></tr>";
					tableHtml += "<tr><td class='td_title'>运输方式：</td><td>"+row.transportWay+"</td></tr>";

					tableHtml += "</table>";
				}				
				$("#proStoreTransport").html(tableHtml);		
				
				//附件
				var traceAppdix = {};
				traceAppdix["traceAppdix.proId"]=proId;
				traceAppdix["traceAppdix.appdixType"]="7";				
				$.ajax({
					url : 'traceApp_findTraceAppdixsListByProId.action',
					data : traceAppdix,
					type : 'post',
					dataType : 'json',
					cache: false,
					success : function(apps) {
						if(apps&&apps.length>0){
							var liHtml = '<div class="picMoveX01 c_scrollPic07"><p class="bbtn prevBtn"><span class="last"></span></p><div class="picBox"><ul>';							    						            			
							for(var i=0;i<apps.length;i++){
								var app = apps[i];
								var imgPath = filePath+"proimg/"+app.appdixUrl;
								liHtml += "<li><p><a rel=\"storeTransport_group\" href=\""+imgPath+"\" title=\""+app.appdixName+"\"><img border=0 src=\""+imgPath+"\" height=\"100\"></a></p><p>"+app.appdixName+"</p></li>";							
							}
							liHtml +='</ul></div><p class="bbtn nextBtn"><span></span></p></div>';

							$("#proStoreTransportPicBox").html(liHtml);	
														
							//图片横向滚动
							var imgScroll_01 = new funPicScrollX(".c_scrollPic07 .picBox",".c_scrollPic07 .prevBtn span",".c_scrollPic07 .nextBtn span",28,false).init();
							
							$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
								var lenth = $(this).find("li").length;
								if(lenth<=4){
									$(this).find(".nextBtn span").eq(0).addClass("last");
								}
							});
							
							////图片预览效果
							$("a[rel=storeTransport_group]").fancybox({
								'transitionIn'		: 'none',
								'transitionOut'		: 'none',
								'titlePosition' 	: 'over',
								'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
									return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
								}
							});							
						}
					}
				});
			}else{
				$("#proStoreTransport").html("<font size='15'>该产品没有对应的仓储运输信息!<font>");
			}
		}
	});
}

/**
 * 显示批次信息
 * @param proId 产品ID
 * @return
 */
function showProBatch(proId) {
	var proBatch = {};
	proBatch["proBatch.proId"]=proId;
	
	$('#proBatch').datagrid({
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [10, 20, 30, 50, 100],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		url : 'proBatch_findProBatchList.action',// url调用Action方法
		loadMsg : '数据装载中......',
		fit : true,
		fitColumns : true,// 允许表格自动缩放，以适应父容器
		remoteSort : false,
		queryParams:proBatch,
		columns : [[{
					field : 'batchNo',
					title : '批次编号',
					width : 120,
					align : 'center'
				}, {
					field : 'proTime',
					title : '批次生产时间',
					width : 100,
					align : 'center'
				}, {
					field : 'dimenno',
					title : '批次二维码',
					width : 120,
					align : 'center'
				}, {
					field : 'batchSts',
					title : '批次状态',
					width : 50,
					align : 'center',
					formatter : function(value, row, index) {
						if(value==1){
							return "审核通过"
						}else{
							return "待审核"
						}
					}
				}, {
					field : 'batchId',
					title : '操作',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						if(row.batchSts==1){
							return "<a href=\"javascript:void(0)\" onclick=\"showImg('"+ row.codeImg+ "')\">查看标签</a>";
						}else{
							return "<a href=\"javascript:void(0)\" onclick=\"auditBatch("+value+")\">审核通过</a>&nbsp;&nbsp;"+
								   "<a href=\"javascript:void(0)\" onclick=\"showImg('"+ row.codeImg+ "')\">查看标签</a>";
						}
					}
				}]],
		pagination : true,// 分页
		rownumbers : true// 行数
		,
		onLoadSuccess : function(data) {
			f_timeout(data);
		}
	});
	
}

/**
 * 审核批次信息
 * @param batchId
 * @return
 */
function auditBatch(batchId){
	$.ajax({
		url : 'proBatch_updateProBatchState.action',
		data : 'ids=' + batchId,
		type : 'post',
		dataType : 'text',
		success : function(result) {
			$.messager.show({title : '提示',msg : result	});
			$('#proBatch').datagrid("reload");
		}
	});
}

/**
 * 修改状态
 */
function updateProTypeState(sts) {
	// getSelections
	var arr = $('#productdatagrid').datagrid("getSelections");
	if (arr.length == 0) {
		$.messager.alert('提示', '请选择要操作的产品信息!', 'question');
		return;
	}
	var obj = new Array();
	for (i = 0; i < arr.length; i++) {
		obj.push(arr[i].proId);
	}
	var ids = obj.join(',');
	$.messager.confirm('提示', '确定要继续操作?', function(r) {
		if (r) {
			$.ajax({
				url : 'pro_updateProductState.action',
				data : 'ids=' + ids + '&state=' + sts,
				type : 'post',
				dataType : 'text',
				success : function(result) {
					$.messager.show({title : '提示',msg : result	});
					$('#productdatagrid').datagrid("reload");
				}
			});
		}
	});
}

/**
 * 搜索产品
 * @return
 */
function tb_search() {
	var companyName = $("#companyName").val();
	var productName = $("#productName").val();
	var sysCode = $("#sysCode").combobox('getValue');
	var traceState = $("#traceState").combobox('getValue');
	
	var pro_type = $("#pro_type_1").combotree('getValue');
	var cod = {};
	if (productName != "") {
		cod["product.proName"] = productName;
	}
	if (pro_type != "") {
		cod["product.typeId"] = pro_type;
	}
	if (companyName != "") {
		cod["product.entName"] = companyName;
	}
	if (sysCode != "") {
		cod["product.sysCode"] = sysCode;
	}
	if (traceState != "") {
		cod["product.traceState"] = traceState;
	}
	
	
	$('#productdatagrid').datagrid('load', cod);
}
/**
 * 重置搜索
 * @return
 */
function tb_reset() {
	$('#myseach').form("clear");
	$('#productdatagrid').datagrid('load', {});
}

/**
 * 删除产品
 * @return
 */
function removerProduct() {
	var arr = $('#productdatagrid').datagrid("getSelections");
	if (arr.length == 0) {
		$.messager.alert('提示', '请选择要操作的产品信息!', 'question');
		return;
	}
	var obj = new Array();
	for (i = 0; i < arr.length; i++) {
		obj.push(arr[i].proId);
	}
	var ids = obj.join(',');
	$.messager.confirm('提示', '确定要继续操作?', function(r) {
		if (r) {
			$.ajax({
				url : 'pro_deleteProduct.action',
				data : 'ids=' + ids,
				type : 'post',
				dataType : 'text',
				success : function(result) {
					$.messager.show({title : '提示',msg : result});
					$('#productdatagrid').datagrid("reload");
				}
			});
		}
	});
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


//图片横向滚动
function funPicScrollX(scrollBox,prevBtn,nextBtn,spacing,isCycle){
    this.scroll = $(scrollBox);
    this.lBtn = $(prevBtn);
    this.rBtn = $(nextBtn);
    this.bd = this.scroll.find('ul');
	this.spacing = spacing;
    //是否循环播放
    this.isCycle = isCycle ? isCycle : false;
    //容器显示的宽度
    this.W = this.scroll.width();
    //item集合
    this.item = this.bd.find('li');
    //每一个item容器的宽度
    this.w = this.scroll.find('li').eq(0).width() + this.spacing;
    //可见item个数
    this.isShowNum = this.W / this.w;
    //item容器的个数
    this.len = this.scroll.find('li').length;
    //动画播放key
    this.key = true;
    //起始位置
    this.start = 0;
    //结束位置
    this.end = 0;
    this.timer = null;
}
funPicScrollX.prototype = {
    constructor: funPicScrollX,
    init: function(){
        var that = this, i = 0;
        
        //如果item个数不足以滚动就退出
        if (that.isShowNum > that.len) 
            return;
        
        //是否循环播放
        if (that.isCycle) {
            for (; i < that.isShowNum; i++) {
                that.item.eq(i).clone().appendTo(that.bd);
            }
        }
        else {
            that.bd.css('width', that.len * that.w + 'px');
        }
        
        that.rBtn.click(function(){
            that.play(1);
        })
        
        that.rBtn.bind('click', function(){
            that.play(1);
        })
        
        that.lBtn.bind('click', function(){
            that.play(0);
        })
    },
    play: function(k){
        var that = this, m = that.w;
        if (!k) {
            m = -that.w;
        }
        if (that.key) {
            that.key = false;
            that.scroll.animate({
                scrollLeft: that.scroll.scrollLeft() + m
            }, 500, function(){
                that.key = true;
				if(!that.isCycle && that.scroll.scrollLeft() == 0){
					that.lBtn.addClass('last');
				}else{
					that.lBtn.removeClass('last');
				}
				if(!that.isCycle && that.scroll.scrollLeft() == that.w * (that.len-that.isShowNum)){
					that.rBtn.addClass('last');
				}else{
					that.rBtn.removeClass('last');
				}
            })
        }
    }
}

function showDetail2(index) {
	var row = $('#productdatagrid').datagrid("getRows")[index];
	var state = row.state == 0 ? "待审" : row.state == 1 ? "生产中" : "已停产";
	var typeClass = row.typeClass;
	var proId = row.proId;
	//赋值
	$("#proId").val(row.proId);
	$("#typeClass").val(typeClass);


	$("#product_tabs2").tabs("select",0);//打开时选中第一个tab页
	
	var tableHtml = "<tr><td style='width: 80px;height: 25px'>产品名称：</td><td class='td_text'>"+row.proName+"</td><td style='width: 80px'>产品分类：</td><td class='td_text'>"+row.typeName+"</td></tr>";
	
		tableHtml += "<tr><td style='width: 80px;height: 25px'>产品编码：</td><td>"+row.proCode+"</td><td style='width: 80px'>条形码：</td><td>"+row.barcode+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>单位规格：</td><td>"+row.unit+"</td><td style='width: 80px'>生产状态：</td><td>"+state+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>储藏条件：</td><td>"+row.storageConditions+"</td><td style='width: 80px'>保质期：</td><td>"+row.shelfLife+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>三品一标一名牌：</td><td>"+row.authentication+"</td><td style='width: 80px'>保鲜防腐：</td><td>"+row.retain+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>生产商：</td><td>"+row.manufacturer+"</td><td style='width: 80px'>生产商电话：</td><td>"+row.sourceTel+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>生产商地址：</td><td colspan='3'>"+row.sourceAddr+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>经销商：</td><td>"+row.distributor+"</td><td style='width: 80px'>经销商电话：</td><td>"+row.distributorTel+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px;height: 25px'>经销商地址：</td><td colspan='3'>"+row.distributorAddr+"</td></tr>";
		
		tableHtml += "<tr><td style='width: 80px'>产品说明：</td><td colspan='3'><textarea rows='3' style='width: 100%;border: 0'>"+row.proDesc+"</textarea></td></tr>";
		    			
		$("#det2").html(tableHtml);			
		$("#productPicBox2").html("");	

		//附件
		var productAppend = {};
		productAppend["productAppend.proId"]=proId;
		$.ajax({
			url : 'proApp_findProAppList.action',
			data : productAppend,
			type : 'post',
			dataType : 'json',
			cache: false,
			success : function(apps) {
				if(apps&&apps.total>0){					
					var liHtml = '<div class="picMoveX01 c_scrollPic01"><p class="bbtn prevBtn"><span class="last"></span></p><div class="picBox"><ul>';							    						            			
					for(var i=0;i<apps.rows.length;i++){
						var app = apps.rows[i];
						var imgPath = filePath+"proimg/"+app.path;
						liHtml += "<li><p><a rel=\"product_group\" href=\""+imgPath+"\" title=\""+app.appName+"\"><img border=0 src=\""+imgPath+"\" height=\"100\"></a></p><p>"+app.appName+"</p></li>";							
					}
					liHtml +='</ul></div><p class="bbtn nextBtn"><span></span></p></div>';
					
					$("#productPicBox").html(liHtml);	
												
					//图片横向滚动
					var imgScroll_01 = new funPicScrollX(".c_scrollPic00 .picBox",".c_scrollPic00 .prevBtn span",".c_scrollPic00 .nextBtn span",28,false).init();
					
					$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
						var lenth = $(this).find("li").length;
						if(lenth<=4){
							$(this).find(".nextBtn span").eq(0).addClass("last");
						}
					});
					
					////产地图片预览效果
					$("a[rel=product_group]").fancybox({
						'transitionIn'		: 'none',
						'transitionOut'		: 'none',
						'titlePosition' 	: 'over',
						'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
							return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
						}
					});							
				}
			}
		});				
		$('#product_detail2').window('open');
}
/**
 * 显示批次信息
 * @param proId 产品ID
 * @return
 */
function showProBatch2(proId) {
	var proBatch = {};
	proBatch["proBatch.proId"]=proId;
	
	$('#proBatch2').datagrid({
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [10, 20, 30, 50, 100],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		url : 'proBatch_findProBatchList.action',// url调用Action方法
		loadMsg : '数据装载中......',
		fit : true,
		fitColumns : true,// 允许表格自动缩放，以适应父容器
		remoteSort : false,
		queryParams:proBatch,
		columns : [[{
					field : 'batchNo',
					title : '批次编号',
					width : 120,
					align : 'center'
				}, {
					field : 'proTime',
					title : '批次生产时间',
					width : 100,
					align : 'center'
				}, {
					field : 'dimenno',
					title : '批次二维码',
					width : 120,
					align : 'center'
				}, {
					field : 'batchSts',
					title : '批次状态',
					width : 50,
					align : 'center',
					formatter : function(value, row, index) {
						if(value==1){
							return "审核通过"
						}else{
							return "待审核"
						}
					}
				}, {
					field : 'batchId',
					title : '操作',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						return "<a href=\"javascript:void(0)\" onclick=\"linkTrace('"+ row.batchNo+ "')\">查看溯源信息</a>";
						/*if(row.batchSts==1){
							return "<a href=\"javascript:void(0)\" onclick=\"showImg('"+ row.codeImg+ "')\">查看标签</a>";
						}else{
							return "<a href=\"javascript:void(0)\" onclick=\"auditBatch("+value+")\">审核通过</a>&nbsp;&nbsp;"+
								   "<a href=\"javascript:void(0)\" onclick=\"showImg('"+ row.codeImg+ "')\">查看标签</a>";
						}*/
					}
				}]],
		pagination : true,// 分页
		rownumbers : true// 行数
		,
		onLoadSuccess : function(data) {
			f_timeout(data);
		}
	});
	
}

function linkTrace(batchNo){
	window.open('http://trace1.gdahsi.org/checkJyEbCode_.action?ebcode_='+batchNo);
}