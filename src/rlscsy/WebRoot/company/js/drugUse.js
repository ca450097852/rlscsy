
var urlType = 1;
$(function(){
	parent.layer.closeAll();
	
	initProList(1,10);
	
	//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'drugUse'},
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

var gdrugUseList;
function initProList(page,rows){
	
	$('#proDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['drugUse.recId'] = $('#h_recId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('drugUse_findDrugUseList.action?tt='+Math.random(),condition,function(result){
		
		if(result){
			gdrugUseList = result.rows;
			//显示条数
			$('#total').html(result.total);
			$('#page').html(page);
			
			var drugUseList = result.rows;
			for(var i=0;i<drugUseList.length;i++){
				var drugUse = drugUseList[i];
				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+drugUse.drugName+'</td>\
			        <td>'+drugUse.useObject+'</td>\
			        <td>'+drugUse.useWay+'</td>\
			        <td>'+drugUse.useDosage+'</td>\
			        <td>'+drugUse.useTotal+'</td>\
			        <td>'+drugUse.useMan+'</td>\
			        <td>'+drugUse.useDate+'</td>\
			        <td>'+drugUse.safeDay+'</td>\
			        <td>'+drugUse.safeDate+'</td>\
			        <td><a class="tablelink" onclick="updateRecord('+i+')">修改</a>\
			        <a class="tablelink" onclick="deletedrugUse('+drugUse.drugId+')">删除</a>\
			        </td>\
			        </tr> ';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(drugUseList.length==0){
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
function deletedrugUse(id){
	if(id){
		var index = layer.confirm('确定删除信息?', {
		    btn: ['确定','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    layer.close(index);
		    $.post('drugUse_deleteDrugUse.action',{ids:id},function(result){
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
	var formUrl = 'drugUse_addDrugUse.action';
	if(urlType!=1){
		formUrl = 'drugUse_updateDrugUse.action';
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
	$('#drugName').combobox({    
		    url:'agriInput_findAgriInputComboList.action?ids=5'
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
	var drugUse = gdrugUseList[index];
	if(drugUse){
		addRecord(false);
		urlType = 2;	
		$('.formtitle span').html('修改药品使用记录');	
		$('input[name="drugUse.drugId"]').val(drugUse.drugId);
		$('input[name="drugUse.crttime"]').val(drugUse.crttime);	
		$('input[name="drugUse.drugName"]').val(drugUse.drugName);
		$('input[name="drugUse.useObject"]').val(drugUse.useObject);
		$('input[name="drugUse.useDosage"]').val(drugUse.useDosage);				
		$('#useTotal').val(drugUse.useTotal);
		$('#useWay').combobox('setValue',drugUse.useWay);
		$('#useDate').datebox('setValue',drugUse.useDate);
		$('#safeDay').numberbox('setValue',drugUse.safeDay);
		$('#safeDate').datebox('setValue',drugUse.safeDate);
		$('#useMan').val(drugUse.useMan);
		
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
