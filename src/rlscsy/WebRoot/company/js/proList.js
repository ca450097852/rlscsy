//操作类型 0 添加 1 修改
var optType = 0;
$(function(){
	$('#typeId_id').combotree({
		url:'proType_getProTypeTreeToPro.action',
		required:true
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
	
	
	
	initProList(1,10);
});

//提交form
function submitProTypeForm() {
	if ($('#proForm').form('validate') == false) {
		$.messager.show({
					title : '提示',
					msg : '验证没有通过!'
				});
		return;
	}
	if ($("#product_unit").val() == $("#product_unit").attr("placeholder")) {
		$("#product_unit").val('');
	}
	$('input[name="product.entId"]').val($('#h_entId').val());
	parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
	var formUrl = '';
	if(optType==0){
		formUrl = 'pro_addProduct.action';
	}else{
		formUrl = 'pro_updateProduct.action';
	}
	//alert(formUrl);
	$('#proForm').form('submit', {
				url : formUrl,
				onSubmit : function(result) {
					return $(this).form('validate');// 对数据进行格式化
				},
				success : function(result) {
					parent.$.messager.progress('close');
					$.messager.show({
								title : '提示',
								msg : result
							});
					initProList(1,10);
				}
			});
}
var gproList;
function initProList(page,rows){
	$('#proFormTable').hide();
	$('#proTable').show();
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	
	//清除原来的行
//	alert($('#proTab tr').length);
	$('#proTab tr').remove();
	
	$.post('pro_findProductListForEnt.action?tt='+Math.random(),condition,function(result){
		//alert(result);
		//加载列表
		var list = result.rows;
		gproList = list;
		var pro;
		var content = '<tr bgcolor="#FFFFFF">\
						<td width="20px"><input type="checkbox" onclick="checkSelect(this.checked);" style="width:15px;"/></td>\
						<td width="150px">产品编码</td>\
						<td width="200px">产品名称</td>\
						<td width="80px">分类</td>\
						<td width="50px">状态</td>\
						<td width="125px">操作</td>\
						</tr>';
		$('#proTab').append(content);
		
		for(var i=0;i<list.length;i++){
			pro = list[i];
			
			content = '<tr bgcolor="#FFFFFF">\
							<td><input type="checkbox" name="itCk" value="'+pro.proId+'" style="width:15px;"/></td>\
						    <td>'+pro.proCode+'</td>\
						    <td>'+pro.proName+'</td>\
						    <td>'+pro.typeName+'</td>\
						    <td>'+(pro.state==0?"<span style=\"color:blue;\">待审</span>":pro.state==1?"<span>生产中</span>":"<span style=\"color:red;\">已停产</span>")+'</td>\
						    <td><a onclick="updateProduct('+pro.proId+');">修改</a>\
						    &nbsp;&nbsp;<a onclick="addProImg('+pro.proId+');">产品图片</a>\
						    &nbsp;&nbsp;<a onclick="window.location.href=\'trace.jsp?proId='+pro.proId+'&proType='+pro.typeClass+'\'">溯源信息</a></td>\
						  </tr>';

			$('#proTab').append(content);
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
		
		
		var pc = '第'+page+'页/共'+pcount+'页 <a onclick="initProList(1,10)">首页</a> <a onclick="initProList('+pre+',10)">上一页</a> <a onclick="initProList('+next+',10)">下一页</a> <a onclick="initProList('+pcount+',10)">尾页</a> 跳转至：<select onchange="toPage(this.value);" >';

		for(var j=1;j<=pcount;j++){
			pc+='<option value="'+j+'" '+(j==page?"selected = \"selected\"":"")+' >'+j+'</option>';
		}
		pc+='</select>';

		$('.page').html(pc);
	},'JSON')
}
//页面跳转
function toPage(value){
	initProList(value,10);
}
function checkSelect(s){
	$('input[type="checkbox"][name="itCk"]').attr('checked',s);
}
function addProduct(){
	$('#proFormTable').show();
	$('#proTable').hide();
	$('#proForm').form('reset');
	$('#hiddenValue').html("");
	optType = 0;
}
function updateProduct(proId){
	addProduct();
	optType = 1;
	for(var i=0;i<gproList.length;i++){
		var pro = gproList[i];
		if(pro.proId==proId){
			for(var item in pro){
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
			$("#proForm").form("validate");
			break;
		}
	}
}
//取消
function cancelPro(){
	$('#proFormTable').hide();
	$('#proTable').show();
}

//删除
function deletePros(){
	
	//alert($('input[type="checkbox"][name="itCk"]:checked').length);
	var proIdList = new Array();
	$('input[type="checkbox"][name="itCk"]:checked').each(function(){
		proIdList.push($(this).val());
	} );
	if(proIdList.length==0)
		return;
	$.messager.confirm('提示', '确定要删除所选的产品?', function(r) {
		if (r) {
			$.ajax({
				url : 'pro_deleteProduct.action',
				data : 'ids=' + proIdList.join(','),
				type : 'post',
				dataType : 'text',
				success : function(result) {
					$.messager.show({
								title : '提示',
								msg : result
							});
					initProList(1,10);
				}
			});
		}
	});
}
var appProId;
//添加产品图片
function addProImg(proId){
	for(var i=0;i<gproList.length;i++){
		var pro = gproList[i];
		if(pro.proId==proId){
			//alert(pro.proName);
			appProId = proId
			$('#proImg').show();
			$('#proTable').hide();
			initProAppList();
			break;
		}
	}
}
/**
 * 提交产品图片
 * @return
 */
function submitAppForm() {
	var path = $("#filePath").val();
	var appName = $("#fileName").val();
	var remark = $('#app_remark').val();
	var proId = appProId;
	var orderby = $('#app_orderby').val();

	if (!path) {
		$.messager.show({
					title : '提示',
					msg : '请选择上传图片'
				});
		return;
	}

	var cod = {};
	cod['productAppend.proId'] = proId;
	cod['productAppend.appName'] = appName;
	cod['productAppend.path'] = path;
	cod['productAppend.orderby'] = orderby;
	cod['productAppend.remark'] = remark;
	$.post('proApp_addProAppendix.action', cod, function(rst) {
				$.messager.show({
							title : '提示',
							msg : rst
						});
				$("#fileName").val("");
				$("#filePath").val("");
				$('#app_remark').val("");
				$('#uploadify').uploadify('disable', false);
				$('#fileQueue').html("");
				$('#app_orderby').val(5);
				initProAppList();
			}, 'TEXT');
}
/**
 * 初始化产品图片列表
 * @return
 */
function initProAppList(){
	$('#proAppend tr').remove();
	var cont = '<tr bgcolor="#FFFFFF"><td>附件名称</td><td>上传时间</td><td>备注</td><td>排序号</td><td>操作</td></tr>';
	$('#proAppend').append(cont);
	
	
	var cond = {};
	cond['productAppend.proId'] = appProId;
	cond['rows'] = 10000;
	$.post('proApp_findProAppList.action',cond,function(result){
		var imgList = result.rows;
		for(var i=0;i<imgList.length;i++){
			cont = '<tr bgcolor="#FFFFFF"><td>'+imgList[i].appName+'</td><td>'+imgList[i].uploadTime+'</td><td>'+imgList[i].remark+'</td><td>'+imgList[i].orderby+'</td><td><a id="proImg_'+i+'" href="/nytsyFiles/proimg/'+imgList[i].path+'" >预览</a>&nbsp;&nbsp;&nbsp;<a onclick="deleteApp('+imgList[i].appId+')">删除</a></td></tr>';
			$('#proAppend').append(cont);
			$("a#proImg_"+i).fancybox();
		}
	},'JSON');
}
//删除附件
function deleteApp(appId) {
	$.messager.confirm('提示', '确定要继续操作?', function(r) {
		if (r) {
			$.post('proApp_deleteApps.action', 'ids=' + appId,
				function(rst) {
					initProAppList();
				}, 'TEXT');
		}
	});
}
/**
 * 返回产品列表
 * @return
 */
function cancelAddApp(){
	$('#proImg').hide();
	$('#proTable').show();
}