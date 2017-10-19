var dataGrid; // 列表
var gElementList = [];
$(function() {	
	dataGrid = $('#list_enterprise').datagrid( {
		url : 'ent_getEntHasRecord.action',
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
		columns : [ [ 
		{field : 'parentName',title : '所属行政机构',width : 150,align:'center'}, 
		{field : 'name',title : '企业名称',width : 200,halign:'center'}, 
		{field : 'regAddr',title : '注册地址',width : 200,halign:'center'}, 
		{field : 'legalPerson',title : '法人代表',width : 60,align : 'center'}, 
		{field : 'tel',title : '联系电话',width : 100,halign:'center'}, 
		{field : 'companyRsts',title : '企业状态',width : 60,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);
			}
		},
		{field : 'entId',title : '操作',width : 100,align : 'center',
			formatter : function(value, row, index) {
				var e = "<a href='javascript:void(0)' onclick='setElement("+row.entId+")'>设置</a>&nbsp;&nbsp;";
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
	 
});

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

//打开设置窗口
var gEntId  ;
function setElement(entId){
	gEntId = entId;
	$('.formtable tr').remove();
	
	$.post('element_getElementCombobox.action','',function(result){
		
		$.post('record_getEntElements.action',{entId:entId},function(entElements){
			var ent1 = entElements.ent1;
			var ent2 = entElements.ent2;
			var type = entElements.type;
			
			var typeMap = entElements.typeMap;
			
			//企业普通
			if(ent1.length!=0){
				var checks = '';
				for(var i=0;i<result.length;i++){
					var it = result[i];
					checks += '<input type="checkbox" value="'+it.id+'" name="ent1" id="ent1_'+it.id+'">'+it.text;
				}
				var content = '<tr name="ent1" type="1">\
					        <td  width="150px" class="form_label" align="center" style="text-align:center;"><strong>普通:</strong></td>\
						    <td class="form_value">'+checks+'\
						    </td>\
					    </tr>'
						    
				$('.formtable').append(content);
				for(var i=0;i<ent1.length;i++){
					$('#ent1_'+ent1[i].elementId).attr("checked","checked"); 
				}
			}
			//企业 三品一标
			if(ent2.length!=0){
				var checks = '';
				for(var i=0;i<result.length;i++){
					var it = result[i];
					checks += '<input type="checkbox" value="'+it.id+'" name="ent2" id="ent2_'+it.id+'">'+it.text;
				}
				var content = '<tr name="ent2" type="2">\
					        <td  width="150px" class="form_label" align="center" style="text-align:center;"><strong>三品一标:</strong></td>\
						    <td class="form_value">'+checks+'\
						    </td>\
					    </tr>'
				$('.formtable').append(content);
				for(var i=0;i<ent2.length;i++){
					$('#ent2_'+ent2[i].elementId).attr("checked","checked"); 
				}
			}
			//产品分类
			if(type.length!=0){
				for(var i=0;i<type.length;i++){
					var ty = type[i];
					var checks = '';
					for(var j=0;j<result.length;j++){
						var it = result[j];
						checks += '<input type="checkbox" value="'+it.id+'" name="type_'+ty.entId+'" id="type_'+ty.entId+'_'+it.id+'">'+it.text;
					}
					var content = '<tr name="'+ty.entId+'"type="'+(parseInt(ty.typeClass)+2)+'">\
				        <td  width="150px" class="form_label" align="center" style="text-align:center;"><strong>'+ty.typeName+':</strong></td>\
					    <td class="form_value">'+checks+'\
					    </td>\
				    </tr>'
					    
					$('.formtable').append(content);
				}
			}
			
			if(typeMap.length!=0){
				
				for(var item in typeMap){
					var list = typeMap[item];
					if(list.length!=0){
						for(var i=0;i<list.length;i++){
							$('#type_'+item+'_'+list[i].elementId).attr("checked","checked"); 
						}
					}
				}
				
			}
			
			
			
		},'JSON');
	},'JSON');
	$('#showRecord').dialog("open").dialog('setTitle', '设置档案要素');
}

function submitForm(){
	var trList = $('.formtable tr');
	
	var jsonObj = [];
	
	for(var i=0;i<trList.length;i++){
		var tr = trList[i];
		var name = $(tr).attr('name');
		var type = $(tr).attr('type');
		if(isNaN(name)){//企业
			var checks = $('input:checkbox[name="'+name+'"][checked="checked"]')
			var arr = [];
			for(var k=0;k<checks.length;k++){
				arr.push($(checks[k]).val());
			}
			var obj = {'objId':gEntId,'objTypeId':type,'value':arr.join(',')};
			jsonObj.push(obj);
		}else{//分类
			var checks = $('input:checkbox[name="type_'+name+'"][checked="checked"]')
			var arr = [];
			for(var k=0;k<checks.length;k++){
				arr.push($(checks[k]).val());
			}
			var obj = {'objId':name,'objTypeId':type,'value':arr.join(',')};
			jsonObj.push(obj);
		}
	}
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	$.post('objElement_updateObjElementList.action',{jsonMsg:JSON.stringify(jsonObj)},function(result){
		parent.$.messager.progress('close');
		$.messager.show( {
			title : '提示',
			msg : result
		});
	},'TEXT')
}

//搜索
function find() {
	var name = $('#nameSearch').val();
	
	var enterprise = {};
	if(name != ""){enterprise["enterprise.name"]=name};

	dataGrid.datagrid('load', enterprise); // 点击搜索
}


//重置
function clearSearch(){
	$('#nameSearch').val("");
	var enterprise = {};

	dataGrid.datagrid('load',enterprise);
}