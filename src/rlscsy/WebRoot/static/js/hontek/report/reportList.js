$(function() {	
		 
		 //隐藏显示窗口	
		 $('#showDesc').window('close');	
		 var purchaseConveyVo = {};
		 purchaseConveyVo["purchaseConveyVo.rsts"]='7,8';//已包装
		 //表格数据
		 $('#conveydatagrid').datagrid({  
		      title : '举报信息列表',  
		      iconCls : 'icon-ok',  
		      pageSize : 15,//默认选择的分页是每页10行数据  
		      pageList : [ 10, 15,20,25 ],//可以选择的分页集合  
		      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		      striped : true,//设置为true将交替显示行背景。  
		      //collapsible : true,//显示可折叠按钮  
		      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
		      url:'report_findReportList.action',//url调用Action方法  
		      //queryParams:purchaseConveyVo,//在请求远程数据的时候发送额外的参数
		      loadMsg : '数据装载中......',  			      
		      fit:true,			      
		      fitColumns:true,//允许表格自动缩放，以适应父容器  
		      remoteSort : false,  
		      frozenColumns : [ [ {  
		           field : 'ck',  
		           checkbox : true  
		      } ] ],   			      
		      columns:[[//表字段
		                {field:'creater',title:'举报人',width:100,align:'center'},
		                {field:'phome',title:'联系电话',width:120,align:'center'},
		                {field:'content',title:'举报内容',width:300,align:'center'},
		                {field:'crttime',title:'举报时间',width:100,align:'center'},
		                {field:'isRep',title:'是否回复',width:50,align:'center',formatter: function(value,row,index){
			              	  if(value==0){
			              		  return "未回复";
			              	  }else{
			              		  return "已回复";
			              	  }
			                }},
		                {field:'flag',title:'操作',align:'center',formatter:function(value,row,index){
		              	  var e ="<a href='javascript:void(0)' onclick='showConvey("+row.repId+","+index+")'>回复</a>&nbsp;&nbsp;";
		              	 /* if(row.isRep==1){
		              		e ="<a href='javascript:void(0)' onclick='showRep("+row.repId+")'>查看回复</a>&nbsp;&nbsp;";
		              	  }*/
		              	  
		              	  return e;
		                }}
		            ]],
              pagination : true,//分页  
              rownumbers : true//行数
	           ,
               onLoadSuccess:function(data){
		 		f_timeout(data);
	 		   }
		      });		 		 	
		});


     // 隐藏
		function clearForm(){
           $('#show').window('close');
        }

		function mbStringLength(s) {
	        var totalLength = 0;
	        var i;
	        var charCode;
	        for (i = 0; i < s.length; i++) {
	          charCode = s.charCodeAt(i);
	          if (charCode < 0x007f) {
	            totalLength = totalLength + 1;
	          } else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
	            totalLength += 2;
	          } else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
	            totalLength += 3;
	          }
	        }
	        // alert(totalLength);
	        return totalLength;
	      } 
	    var isRep;
		// 显示
		function showConvey(repId,index){
			//alert(purchId);
			$("#repId").val(repId);
			var row = $('#conveydatagrid').datagrid("getRows")[index];
			$("#question").html(row.content);
			
			isRep = row.isRep;
			
			$("#bt1").show();
			$("#bt2").hide();
			$("#bt3").hide();
			
			resetItem();
			
			$('#showDesc').window('open').dialog('setTitle','举报回复');		
		}
		
		//修改回复
		function updateRep(anId,repId){
			//alert(anId);
			$("#t_answer").val($("#item_"+anId).html());
			$("#anId").val(anId);
			$("#bt1").hide();
			$("#bt2").show();
			$("#bt3").show();
		}
		
		//删除回复
		function deleteRep(anId,repId){
			$.post('report_deleteReportAnswer.action', 'anId='+anId, function(result) {
				
				resetItem();
				
			}, "TEXT");
		}
		
		function resetItem(){
			$("#answer").html("");
			$.post('report_findAllReportAnswerList.action', 'repId='+$("#repId").val(), function(result) {
				
				for(var i=0;i<result.length;i++){
					$("#answer").append("<li><span id='item_"+result[i].anId+"'>"+result[i].answer+"</span><a class='opter_a' href='javascript:void(0);' onclick='updateRep("+result[i].anId+");'>修改</a>&nbsp;&nbsp;<a href='javascript:void(0);' onclick='deleteRep("+result[i].anId+")'>删除</a></li>");
				}
				
			}, "JSON");
		}
		
		function submitForm(){
			
	           $('#reportanswer').form('submit', {
					url : 'report_addReportAnswer.action',
					onSubmit : function(result) { 
					    return $(this).form('validate');
					},
					success : function(result) {
						//回复成功
						if(result=="SUCCESS"){
							resetItem();
							$("#t_answer").val("");//清空内容
						}else{//回复失败
							$.messager.show({ title : '提示', msg : "回复失败。" });
						}
						//如何已经回复了则不用更新列表
						if(isRep==0){
							$('#conveydatagrid').datagrid("reload");
						}
					}
				});
	        }
		
		function submitForm1(){
			
	           $('#reportanswer').form('submit', {
					url : 'report_updateReportAnswer.action',
					onSubmit : function(result) { 
					    return $(this).form('validate');
					},
					success : function(result) {
						//回复成功
						if(result=="SUCCESS"){
							//$("#answer").append("<li>"+$("#t_answer").val()+"</li>");
							$("#t_answer").val("");//清空内容
							
							resetItem();
							$("#bt1").show();
							$("#bt2").hide();
							$("#bt3").hide();
							
						}else{//回复失败
							$.messager.show({ title : '提示', msg : "回复失败。" });
						}
						//如何已经回复了则不用更新列表
						if(isRep==0){
							$('#conveydatagrid').datagrid("reload");
						}
					}
				});
	        }	
		
		function cencelUpdate(){
			$("#t_answer").val("");
			$("#bt1").show();
			$("#bt2").hide();
			$("#bt3").hide();
		}
		
		function findReport(){
			var col = {};
			
			var creater = $("#creater").val();
			var phome = $("#phome").val();
			var startDate = $("#startDate").datebox('getValue');
   			var endDate = $("#endDate").datebox('getValue');
   			
   			if(creater!=""){
   				col["creater"]=creater;
   			}
   			if(phome!=""){
   				col["phome"]=phome;
   			}
   			if(startDate!=""){
   				col["startDate"]=startDate;
   			}
   			if(endDate!=""){
   				col["endDate"]=endDate;
   			}
			
			$('#conveydatagrid').datagrid('load',col);
		}
		//清除查询条件
		function resetSearch(){
			$('#creater').val("");
			$('#phome').val("");
			$('#startDate').datebox('setValue', '');
			$('#endDate').datebox('setValue', '');
		}