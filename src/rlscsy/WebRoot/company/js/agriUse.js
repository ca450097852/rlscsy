var basePath;
var urlType = 1;
$(function(){
	//parent.layer.closeAll();
	basePath = $("#basePath").val();
	
	initProList(1,10);
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'agriUse'},
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
	
	$("#useDate").datebox({
	    onSelect: function(date){
	    	var value = $('#safeDay').numberbox("getValue");
	    	if(value){
	    		var date2 = AddDays(date, value);    	
		    	var m = (date2.getMonth()+1);
		    	if(m<10){
		    		m="0"+m;
		    	}	    	
		    	var d = date2.getDate();
		    	if(d<10){
		    		d = "0"+d;
		    	}	    		    	
		    	$("#safeDate").datebox("setValue",date2.getFullYear()+"-"+m+"-"+d);  
		    	
	    	}	    	  	
	    }
	});

	$('#safeDay').numberbox({    
		onChange: function(newValue,oldValue){	       
			var date = $("#useDate").datebox('getValue');
			if(date&&newValue){				
				var d = new Date();
				var ds = date.split("-");				
				var m1 = parseInt(ds[1])-1;			
				d.setFullYear(ds[0],m1,ds[2])				
				
		    	var date2 = AddDays(d, newValue);
				
		    	var m = (date2.getMonth()+1);
		    	if(m<10){
		    		m="0"+m;
		    	}	    	
		    	var d = date2.getDate();
		    	if(d<10){
		    		d = "0"+d;
		    	}	    		    	
		    	$("#safeDate").datebox("setValue",date2.getFullYear()+"-"+m+"-"+d);  	    			    	
		    	
				//layer.msg("本次农药施用的安全间隔天数为【"+newValue+"】天，安全间隔期到期时间为"+$("#safeDate").datebox("getValue"));

			}
	    }
	});  
	
});


//增加天  
function AddDays(date, value) {
    date.setDate(parseInt( date.getDate() )+parseInt( value));
    return date;
}

var gagriUseList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['agriUse.recId'] = $('#h_recId').val();
	$('#proTab tr:not(:first)').remove();
	$.post(basePath+'agriUse_findAgriUseList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gagriUseList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var agriUseList = result.rows;
			for(var i=0;i<agriUseList.length;i++){
				var agriUse = agriUseList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+agriUse.agriName+'</td>\
			        <td>'+agriUse.useObject+'</td>\
			        <td>'+agriUse.useWay+'</td>\
			        <td>'+agriUse.useDosage+'</td>\
			        <td>'+agriUse.useTotal+'</td>\
			        <td>'+agriUse.useMan+'</td>\
			        <td>'+agriUse.useDate+'</td>\
			        <td>'+agriUse.safeDay+'</td>\
			        <td>'+agriUse.safeDate+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        <a class="tablelink" onclick="deleteAgriUse('+agriUse.archId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(agriUseList.length==0){
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
function deleteAgriUse(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('agriUse_deleteAgriUse.action',{ids:id},function(result){
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
	var formUrl = 'agriUse_addAgriUse.action';
	if(urlType!=1){
		formUrl = 'agriUse_updateAgriUse.action';
	}	
	
	//询问框
	layer.confirm("本次农药施用的安全间隔天数为【"+$('#safeDay').numberbox("getValue")+"】天，安全间隔期到期时间为"+$("#safeDate").datebox("getValue"), {
	    btn: ['确定'] //按钮
	}, function(){
		$('#recordForm').form('submit', {
			url : formUrl,
			success : function(result) {
				layer.msg(result);
				initProList(1,10);
			}
		});
	});

}


//添加档案
function addRecord(flag){
	$('.formtitle span').html('添加药品使用记录');
	urlType = 1;
	$('#proDiv').hide();
	$('.center_content3').show();
	$('#recordForm').form('reset');
	
	$('#agriName').combobox({    
		    url:'agriInput_findAgriInputComboList.action?ids=2'
		});
	
	 /*$.post('proTypeQrcode_findTypeName.action',{recId:$('#h_recId').val()},function(result){
		 $("#useObject").val(result);
	 },'TEXT');*/
	
	if(!flag){
		$('.tools').hide();
	}
}
//修改档案
function updateRecord(index){
	var agriUse = gagriUseList[index];
	if(agriUse){
		addRecord(false);
		urlType = 2;
		
		$('.formtitle span').html('修改药品使用记录');
		
		$('input[name="agriUse.archId"]').val(agriUse.archId);
		$('input[name="agriUse.crttime"]').val(agriUse.crttime);	
		$('input[name="agriUse.agriName"]').val(agriUse.agriName);
		$('input[name="agriUse.useObject"]').val(agriUse.useObject);
		$('input[name="agriUse.useDosage"]').val(agriUse.useDosage);				
		$('#useTotal').val(agriUse.useTotal);
		$('#useWay').combobox('setValue',agriUse.useWay);
		$('#useDate').datebox('setValue',agriUse.useDate);
		$('#safeDay').numberbox('setValue',agriUse.safeDay);
		$('#safeDate').datebox('setValue',agriUse.safeDate);
		$('#useMan').val(agriUse.useMan);
		
		$('#recordForm').form('validate')
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
		$('#typeId').combotree({'required':true});
	}
}
