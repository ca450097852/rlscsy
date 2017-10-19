	var htmlTree = "<option value=\"-1\" selected=\"selected\">请选择区域,此项必须选择!</option>";//得到区域树
	$(function(){			
		$.ajax({
			url:'compfor_getEntTree.action',
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(result) {
			explaint(result,0);
			$("#entId").html(htmlTree);
			htmlTree = "<option value=\"-1\" selected=\"selected\">请选择区域,此项必须选择!</option>";
		}
		});
	});
	//解析区域数据;
	function explaint(data,tem){
		var vo = {};
		var nb = "";
		for(var j = 0;j<tem;j++){ 
			nb = nb+"&nbsp;&nbsp;&nbsp;";
		}
		for(var i=0;i<data.length;i++){
			vo = data[i];
			htmlTree = htmlTree+"<option value=\""+vo.id+"\">"+nb+vo.text+"</option>";
			if(vo.children.length>0){
				explaint(vo.children,tem+1);
			}
		}			
	}
	
	
	//表单验证 提交表单
	function formsubmit(){
		
		if($("#typeNo").val()==""){
			layer.confirm('请选择举报类型！', {icon: 3, title:'提示'}, function(index){
			    layer.close(index);
			});
			$("#typeNo").focus();
			return ;
		}

		if($("#title").val()==""){
			layer.confirm('请输入标题！', {icon: 3, title:'提示'}, function(index){
			    layer.close(index);
			});
			$("#title").focus();
			return ;
		}
		if($("#entId").val()=="-1"){
			layer.confirm('请选择区域！', {icon: 3, title:'提示'}, function(index){
			    layer.close(index);
			});
			$("#entId").focus();
			return ;
		}
		if($("#username").val()==""){
			layer.confirm('请输入您的称呼！', {icon: 3, title:'提示'}, function(index){
			    layer.close(index);
			});
			$("#username").focus();
			return ;
		}
		if($("#phone").val()==""){
			layer.confirm('请输入您的电话号码！', {icon: 3, title:'提示'}, function(index){
			    layer.close(index);
			});
			$("#phone").focus();
			return ;
		}
		if($("#content").val()==""){
			layer.confirm('请输入投诉内容！', {icon: 3, title:'提示'}, function(index){
			    layer.close(index);
			});
			$("#content").focus();
			return ;
		}
		
		$('#complaintForm').form('submit', {
			url : 'compfor_addComplaint.action',
			success : function(result) {
//				alert("举报成功!");
				layer.confirm('举报成功！', {icon: 3, title:'提示'}, function(index){
				    layer.close(index);
				});
				History().back();
			}
		});
	}