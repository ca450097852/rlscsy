//操作类型 0 添加 1 修改
var optType = 0;
$(function(){
	initProList(1,10);
	parent.layer.closeAll();
});
var gproList;
function initProList(page,rows){
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	
	$.post('pro_findProductListForEnt.action?tt='+Math.random(),condition,function(result){
		if(result){
			
			$('#proTab tr:not(:first)').remove();
			
			gproList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var proList = result.rows;
			//<td><input type="checkbox" class="proCheck" value="'+pro.proId+'" /></td>\
			for(var i=0;i<proList.length;i++){
				var pro = proList[i];
				
				var certificate = '';
				var ischeck = '';
				var checkway = '';
				var checkresult = '';
				var basearea = '';
				var scale = '';
				var listed = '';
				var salearea = '';
				var packing = '';
				
				if(pro.productInfo&&pro.productInfo.length>0){
					var pi = pro.productInfo[0];
					
					if(pi.certificate=='1'){
						certificate = '有机农产品';
					}else if(pi.certificate=='2'){
						certificate = '绿色农产品';
					}else if(pi.certificate=='3'){
						certificate = '无公害农产品';
					}else if(pi.certificate=='4'){
						certificate = '地理标志农产品';
					}else if(pi.certificate=='5'){
						certificate = '无';
					}
					
					if(pi.ischeck=='1'){
						ischeck = '自检';
					}else if(pi.ischeck=='2'){
						ischeck = '委托检测';
					}else if(pi.ischeck=='3'){
						ischeck = '无';
					}
					
					if(pi.checkway=='1'){
						checkway = '快速检测';
					}else if(pi.checkway=='2'){
						checkway = '定量检测';
					}														
					
					if(pi.salearea=='1'){
						salearea = '内销';
					}else if(pi.salearea=='2'){
						salearea = '出口';
					}
					
					if(pi.packing=='1'){
						packing = '有';
					}else if(pi.packing=='2'){
						packing = '无';
					}
					
					checkresult = pi.checkresult;
					 basearea = pi.basearea;
					 scale = pi.scale;
					 listed = pi.listed;
				}
				
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
				    <td>'+pro.proName+'</td>\
				    <td>'+pro.typeName+'</td>\
				    <td>'+certificate+'</td>\
				    <td>'+ischeck+'</td>\
				    <td>'+basearea+'</td>\
				    <td>'+scale+'</td>\
			        <td>'+(pro.state==0?"<span style=\"color:blue;\">待审</span>":pro.state==1?"<span>生产中</span>":"<span style=\"color:red;\">已停产</span>")+'</td>\
			        <td><a class="tablelink" onclick="updateProduct('+pro.proId+');">修改</a>\
			        <a class="tablelink" onclick="addProImg('+pro.proId+');">产品图片</a>\
			        </td>\
			        </tr> ';
			      //<a class="tablelink" onclick="window.location.href=\'trace.jsp?proId='+pro.proId+'&proType='+pro.typeClass+'\'">溯源信息</a>\
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(proList.length==0){
				$('#proTab').append('<tr><td align="center" colspan="'+$('#proTab tr:first').find('th').length+'">暂无数据</td></tr>');
			}
			
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
//显示添加界面
function addProduct(flag){
	$('#typeId_id').combotree({
		url:'proType_getProTypeTreeToPro.action',
		required:true
	});
	
	$('input:radio').attr("checked",false);
	$('input[name="productInfo.proInfoId"]').val(0);
	$('input[name="productInfo.proId"]').val(0);
	
	$('#proForm').form('reset');
	$('#hiddenValue').html("");
	optType = 0;
	$('#proDiv').hide();
	$('.center_content3').show();
	
	if(!flag){
		$('.tools').hide();
	}
	
}
//显示增加产品
function cancelPro(){
	$('#proDiv').show();
	$('.center_content3').hide();
	if(optType==0){
		$('.tools').show();
	}
}

//提交form
function submitProTypeForm() {
	if ($('#proForm').form('validate') == false) {
		layer.msg('验证没有通过!');
		return;
	}
	if ($("#product_unit").val() == $("#product_unit").attr("placeholder")) {
		$("#product_unit").val('');
	}
	$('input[name="product.entId"]').val($('#h_entId').val());
	
	var it = layer.msg('数据处理中，请稍后....', {icon: 16,time: 200000});
	var formUrl = '';
	if(optType==0){
		formUrl = 'pro_addProduct.action';
	}else{
		formUrl = 'pro_updateProduct.action';
	}
	$('#proForm').form('submit', {
				url : formUrl,
				onSubmit : function(result) {
					return $(this).form('validate');// 对数据进行格式化
				},
				success : function(result) {
					layer.close(it);
					layer.msg(result);
					initProList(1,10);
				}
			});
}
//修改产品信息
function updateProduct(proId){
	addProduct(true);
	optType = 1;
	for(var i=0;i<gproList.length;i++){
		var pro = gproList[i];
		if(pro.proId==proId){
			for(var item in pro){
				
				if(item!='productInfo'){
					var obj = $("[name='product."+item+"']:eq(0)");
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
						$('#hiddenValue').append("<input type='hidden' name='product."+item+"' value='"+pro[item]+"' />");
					}
				}
			}
			
			
			if(pro.productInfo.length!=0){
				var productInfo = pro.productInfo[0];
//				$('select[name="productInfo.certificate"]').val(productInfo.certificate);
//				$('select[name="productInfo.ischeck"]').val(productInfo.ischeck);
				
				$('#certificate_id').combobox('setValue',productInfo.certificate);
				$('#ischeck_id').combobox('setValue',productInfo.ischeck);
				
				$('input[name="productInfo.checkresult"]').val(productInfo.checkresult);
				
				$('input[name="productInfo.basearea"]').val(productInfo.basearea);
				$('input[name="productInfo.scale"]').val(productInfo.scale);
				
				$('input[name="productInfo.proInfoId"]').val(productInfo.proInfoId);
				$('input[name="productInfo.traceSeq"]').val(productInfo.traceSeq);
				$('input[name="productInfo.proId"]').val(proId);
				
				
				$('input:radio[name="productInfo.checkway"][value="'+productInfo.checkway+'"]').attr("checked",'checked');
				$('input:radio[name="productInfo.salearea"][value="'+productInfo.salearea+'"]').attr("checked",'checked');
				$('input:radio[name="productInfo.packing"][value="'+productInfo.packing+'"]').attr("checked",'checked');
				
				$('#listed').datebox('setValue',productInfo.listed);
				
				
			}
			
			
			
			
			
			$("#proForm").form("validate");
			break;
		}
		
		
	}
}

function addProImg(proId){
	
	var content = '<div style="height:225px;overflow-x:auto;"><table id="proAppend" class="tablelist">\
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
		<tr style="height: 30px;background: #eaeaea;border-bottom: 1px solid #d5d5d5;border-top: 1px solid #d5d5d5;"><td colspan="2">添加产品图片</td></tr>\
	   	<tr style="height: 30px"><td width="100px">排序</td><td><input id="app_orderby" value="5"/></td></tr>\
	    <tr style="height: 30px"><td>备注</td><td><input id="app_remark"/></td></tr>\
	    <tr style="height: 30px">\
	    	<td>请选择图片</td>\
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
		title:'产品图片管理',
		btn:['保存','关闭'],
	    type: 5,
	    shift:5,
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
				cod['productAppend.proId'] = proId;
				cod['productAppend.appName'] = appName;
				cod['productAppend.path'] = path;
				cod['productAppend.orderby'] = orderby;
				cod['productAppend.remark'] = remark;
				$.post('proApp_addProAppendix.action', cod, function(rst) {
					layer.msg(rst);
					$("#fileName").val("");
					$("#filePath").val("");
					$('#app_remark').val("");
					$('#uploadify').uploadify('disable', false);
					$('#fileQueue').html("");
					$('#app_orderby').val(5);
					initProAppList(proId);
				}, 'TEXT');
		}
	});
	
	$("#uploadify").uploadify({
		'swf' : '../uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'proApp_uploadFile.action;jsessionid='	+ $('#sessionId').val(),
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
		'onUploadSuccess' : function(file, data, response) {
			$('#uploadify').uploadify('disable', true);
			$('#filePath').val(data);
			$('#fileName').val(file.name);
		}
	});
	initProAppList(proId);
	
}


function initProAppList(proId){
	$('#proAppend tr:not(:first)').remove();
	var cond = {};
	cond['productAppend.proId'] = proId;
	cond['rows'] = 10000;
	$.post('proApp_findProAppList.action',cond,function(result){
		var imgList = result.rows;
		for(var i=0;i<imgList.length;i++){
			cont = '<tr '+(i%2==0?"":"class=\"odd\"")+'><td>'+imgList[i].appName+'</td><td>'+imgList[i].uploadTime+'</td><td>'+imgList[i].remark+'</td><td>'+imgList[i].orderby+'</td><td><a id="proImg_'+i+'" href="/nytsyFiles/proimg/'+imgList[i].path+'" >预览</a>&nbsp;&nbsp;&nbsp;<a onclick="deleteApp('+imgList[i].appId+','+proId+')">删除</a></td></tr>';
			$('#proAppend').append(cont);
			$("a#proImg_"+i).fancybox();
		}
	},'JSON');
}

//删除附件
function deleteApp(appId,proId) {
	var index = layer.confirm('确定删除信息?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
	    layer.close(index);
	    $.post('proApp_deleteApps.action', 'ids=' + appId,
				function(rst) {
					initProAppList(proId);
				}, 'TEXT');
	}, function(){
	    layer.close(index);
	});
}
//全选或全不选
function selectEvent(flag){
	if(flag){
		$('input:checkbox[class="proCheck"]').attr("checked",'checked');
	}else{
		$('input:checkbox[class="proCheck"]').attr("checked",false);
	}
}
