var basePath;
var urlType = 1;
var ttList;
var formUrl;
var alltotal;
var num;
var size;
$(function(){
	basePath = $("#basePath").val();
	parent.layer.closeAll();
	initttList(1,10);
	 $("#epage").pagination({  
         total: alltotal, //数据总条数  
         pageSize: 10,//页大小，即每页显示多少条数据  
         pageNumber: 1,//当前页  
         pageList: [ 10, 15, 20], //。10条是每页显示10条数据  15表示每页显示15条数据，20表示每页显示20条数据  
         //用户选择一个新的页面的时候触发此事件，可以传递2个惨（pageNumber, pageSize）分别表示当前页，和页大小  
         onSelectPage: function (pageNumber, pageSize) {
        	 initttList(pageNumber,pageSize);
         }  
     })  
});
//init表单分页获取list
function initttList(page,rows){	
	$('#ttDiv').show();
	$('.center_content3').hide();
	$('.tools').show();
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['teamBuyAcceptanceInfo.comId']=$('#comId').val();
	$.ajaxSetup({async : false});
	$.post('teamBuyAcceptanceInfo_findTeamBuyAcceptanceInfo.action?tt='+Math.random(),condition,function(result){		
		if(result){	
			alltotal=result.total;
			ttList = result.rows;
			var content='';
			for(var i=0;i<ttList.length;i++){
				var teamBuyAcceptanceInfo = ttList[i];	
				 content += '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+teamBuyAcceptanceInfo.teamConsumeName+'</td>\
			        <td>'+teamBuyAcceptanceInfo.inDate+'</td>\
					<td>'+teamBuyAcceptanceInfo.goodsCode+'</td>\
					<td>'
					+"<a class='tablelink' href='javascript:void(0)' onclick='update("+i+")'>修改</a>"
					+"<a class='tablelink' href='javascript:void(0)' onclick='deleteArea("+teamBuyAcceptanceInfo.tbaiId+")'>删除</a>"
					+'</td>\
			        </td>\
			        </tr>';
			}
			$('#tbody').html(content);
			//没有数据
			if(ttList.length==0){
				$('#tbody').html('<tr><td align="center" colspan="'+$('#ttTab tr:first').find('th').length+'">暂无数据</td></tr>');
			}	
			
			 $("#epage").pagination({  
		         total: alltotal, //数据总条数  
		         pageList: [5, 10, 15, 20], //5表示页显示5条数据。10条是每页显示10条数据  15表示每页显示15条数据，20表示每页显示20条数据  
		         //用户选择一个新的页面的时候触发此事件，可以传递2个惨（pageNumber, pageSize）分别表示当前页，和页大小  
		         onSelectPage: function (pageNumber, pageSize) {
		        	 initttList(pageNumber,pageSize);
		         }  
		     })  
		}		
	},'JSON');
	
}



//添加团体消费信息
function addTeamBuyInfo(){
	$('.formtitle span').html('添加团体消费进货验收信息');
	urlType = 1;
	$('#ttDiv').hide();
	$('.center_content3').show();
	$('#ttForm').form('reset');
	var d = new Date();
	var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
	　$("#tt_inDate").datebox("setValue",str);
	formUrl="teamBuyAcceptanceInfo_addTeamBuyAcceptanceInfo.action";
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
				url : 'teamBuyAcceptanceInfo_deleteTeamBuyAcceptanceInfo.action',
				data : 'id='+ id,
				type : 'post',
				dataType : 'text',
				success : function(result) {
					layer.msg(result);
					initttList(1,10);
					$('.easyui-panel').show();
				}
			});
		}, function(){
		    layer.close(index);
		});
	}
}

//提交表单
function submitForm(){	
	if($('#ttForm').form('validate')==false){
		layer.msg('必填验证没有通过');
		return;
	}
	
	layer.msg('数据处理中，请稍后....', {icon: 16});
	 var params=$('#ttForm').serialize();
     $.ajax({
       url:formUrl,
       type:"post",
       data:params,
       success:function(msg){
    	   layer.closeAll();
			layer.msg(msg);
			initttList(1,10);
			$('.easyui-panel').show();
       }
       
    });
	

}


//修改
function update(index){
	var teamBuyInfo = ttList[index];
	if(teamBuyInfo){
		$('.formtitle span').html('修改团体消费进货验收信息');
		$('#ttDiv').hide();
		$('.easyui-panel').hide();
		$('.center_content3').show();
		$('#ttForm').form('reset');
		$('#tbaiId').val(teamBuyInfo.tbaiId);
		$('#tt_teamConsumeId').val(teamBuyInfo.teamConsumeId);
		$('#tt_teamConsumeName').val(teamBuyInfo.teamConsumeName);
		$("#tt_inDate").datebox("setValue",teamBuyInfo.inDate);
		$('#tt_supplierId').val(teamBuyInfo.supplierId);
		$('#tt_supplierName').val(teamBuyInfo.supplierName);
		$('#tt_tranId').val(teamBuyInfo.tranId);
		$('#tt_goodsCode').val(teamBuyInfo.goodsCode);
		$('#tt_goodsName').val(teamBuyInfo.goodsName);
		$('#tt_weight').val(teamBuyInfo.weight);
		$('#tt_price').val(teamBuyInfo.price);
		$('#tt_wsSupplierId').val(teamBuyInfo.wsSupplierId);
		$('#tt_wsSupplierName').val(teamBuyInfo.wsSupplierName);
		$('#ttForm').form('validate');
	}
	formUrl="teamBuyAcceptanceInfo_updateTeamBuyAcceptanceInfo.action";
	
}

//退出
function exitContent(){
	$('#ttDiv').show();
	$('.center_content3').hide();
	$('.easyui-panel').show();
		
}


