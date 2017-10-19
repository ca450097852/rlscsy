

$(function() {
		var nodeType=$('#nodeType').val();
		var checkSelf=[];
		var itemIds=[];
		$.ajax({
			type : "get", 
			data : null,
			url : 'assessItem_findAssessItemListByNodeType.action?nodeType='+nodeType, //请求路径
			dataType : "json",
			success : function(data) {
				  var rows =data.rows;
				   var op = '' ;
				   for(var i= 0 ; i < rows.length; i++){
					   op +="<tr>"+
					   		"<td style='text-align:center;line-height: 35px;' ><div>"+rows[i].itemName+"</div></td>" +
					   		"<td><div>"+rows[i].itemDesc+"</div></td>" +
					   		"<td style='text-align:center;line-height: 35px;'>" +
					   		"<div>" +
					   		"<ul>" +
					   		"<li><input type='radio' name='checkSelf["+i+"]' value='1'/><label>是</label></li>" +
					   		"<li><input type='radio' checked='checked' name='checkSelf["+i+"]' value='2'/><label>否</label></li>" +
					   		"</ul>" +
					   		"</div> </td>"+
					   		"</tr>";
					   itemIds.push(rows[i].itemId);
					}
				   $("#itemIds").val(itemIds);
				   
				   if(nodeType=='2'){
					   op=op+"<tr height='50px' style='text-align:center;line-height: 35px;'><td rowspan='3'>基本情况</td><td>摊位数量（个）</td><td><input type='text' name='comAssess.tanwei' placeholder='请输入'>（个）</td></tr>"+
					   "<tr height='50px' style='text-align:center;line-height: 35px;'><td>智能溯源秤或交易终端数（台）</td><td><input type='text' placeholder='请输入' name='comAssess.suyuanchen'>（台）</td></tr>"+
					   "<tr height='50px' style='text-align:center;line-height: 35px;'><td>是否有零售交易</td><td><ul><li><input type='radio' name='comAssess.lingsou' value='是'/><label>是</label></li>" +
					   "<li><input type='radio' name='comAssess.lingsou' value='否'/><label>否</label></li></ul></td></tr>";
				   }
				   if(nodeType=='3'||nodeType=='4'){
					   op=op+"<tr height='50px' style='text-align:center;line-height: 35px;'><td rowspan='2'>基本情况</td><td>摊位数量（个）</td><td><input type='text' name='comAssess.tanwei' placeholder='请输入'>（个）</td></tr>"+
					   "<tr height='50px' style='text-align:center;line-height: 35px;'><td>智能溯源秤或交易终端数（台）</td><td><input type='text' name='comAssess.suyuanchen' placeholder='请输入'>（台）</td></tr>";
				   }
				 
				   $('#khtbody').html(op);
				   parent.layer.closeAll();
			},
			error : function(err) {
				alert("错误：" + err);
			} //异步请求失败后的回调函数
		});
		
		});
var flag=0;
function submitForm(){
	flag++;
	if(flag>=2){
		alert("请勿重复提交");
		return;
	}
	var checkSelf=[];
	var itemIds=[];
	itemIds= $("#itemIds").val();
	
	var obj = document.getElementsByTagName("input");
    for(var i=0; i<obj.length; i ++){
        if(obj[i].checked){
        	checkSelf.push(obj[i].value);
        }
    }
    $("#checkSelf").val(checkSelf);
    
        var params=$('#tForm').serialize();
         $.ajax({
           url:"assessDetail_addAssessDetail.action",
           type:"post",
           data:params,
           success:function(msg){
        	layer.msg(msg);
   			init();
           }
           
        });
    }
    
    
    
    
    
    
    
    
    
    
    
    




