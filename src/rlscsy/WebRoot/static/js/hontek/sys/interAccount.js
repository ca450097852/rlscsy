var formUrl = '';
var oldValue = '';

$(function() { 	 	
	//表格数据
	$('#interAccountDataGrid').datagrid({  
		  title:'接入系统账号列表',
	      iconCls : 'icon-computer',  
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	      url:'interAccount_findInterAccountPagerList.action',//url调用Action方法  
	      loadMsg : '数据装载中......',  			      
	      fit:true,			      
	      //singleSelect:true,//为true时只能选择单行  
	      fitColumns:true,//允许表格自动缩放，以适应父容器  
	      remoteSort : false,  
	      frozenColumns : [ [ {  
	           field : 'ck',  
	           checkbox : true  
	      } ] ],
	      columns:[[
				{field:'sysCode',title:'系统标识',width:100,align:'center'},			
				{field:'sysName',title:'系统名称',width:150,align:'center'},			
				{field:'account',title:'系统账号',width:100,align:'center'},
				{field:'ip',title:'IP地址',width:150,align:'center'},			
				{field:'sts',title:'使用状态',width:50,align:'center',
					formatter: function(value,row,index){ 					
						if(value==0){
							return "使用";
						}else{
							return "<font color=red>停用</font>";
						}
					}
				},			
				{field:'crttime',title:'开通时间',width:100,align:'center'}
	      ]],
          pagination : true,//分页  
          rownumbers : true,//行数  
          
          onLoadSuccess : function(data) {
			f_timeout(data);
		 }
	}); 	
	
	parent.$.messager.progress('close');
		
	$("#interAccount_account").blur( function() {
		var account = $("#interAccount_account").val();		
		if(account==''){
			$.messager.alert('提示','系统账号不能为空!','info');  
			//$("#interAccount_account").focus();
		}else if(account==oldValue){
			//等于原来的值 
		}else{
			$.ajax({
	            type: "post",
	            async: false,
	            url: "interAccount_findInterAccountExist.action?ids="+account,
	            dataType: "json",
	            cache: false,
	            success: function (bool) {
	                //返回的数据获取内容
					if(bool==false||bool=='false'){
						$.messager.alert('提示','该系统账号【'+account+'】已经存在，请重新输入！','info');  
						$("#interAccount_account").val('');
		        		return;
					}
	            },
	            error: function (err) {
	                alert(err);
	            }
	        });
		}
	}); 
});
			
	/**
	 * 添加
	 * @return
	 */
	function appendInterAccount(){
		
		$("#parentId").combotree({
			url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
			method:'get',
			required:true,
			onLoadSuccess:function(node, data){
			if(data){
				if(data[0].checked){
					$("#parentId").combotree("setValue",data[0].id);
				}
			}
			f_timeout(data);			
			}
		});		
		
		
		$("#areaId").combotree({
			url:'ent_getEntAreaTree.action?tt='+Math.floor(Math.random()*20)+1,
			method:'get',
			required:true,
			onLoadSuccess:function(node, data){			
				$("#areaId").combotree("setValue",data[0].id);			
			}
		});
		oldValue = '';
		$('#interAccountForm').form("reset");
		$('#in_value').html("");
		formUrl = 'interAccount_addInterAccount.action';
		$("#interAccountDlg").window("open").window('setTitle', '添加接入系统账号');
	}	
	
	
	/**
	 * 修改
	 * @return
	 */
	function updateInterAccount(){		
		var arr = $('#interAccountDataGrid').datagrid("getSelections");
		var lg = arr.length;
		if(lg==0){
			$.messager.alert('提示','请选择需要修改的信息!','question');
		}else if(lg!=1){
			$.messager.alert('提示','对不起，一次只能修改一条信息，请重新选择!','question');
		}else{
			var interAccount = arr[0];
			
			formUrl = 'interAccount_updateInterAccount.action';
			
			$('#interAccountForm').form("reset");				
			$("#in_value").html($("#h_value").html());
			//原来的值
			oldValue = interAccount.account;
			
			$("input[name='interAccount.sysCode']").val(interAccount.sysCode);
			
			$("#areaId").combotree({
				url:'ent_getEntAreaTree.action?tt='+Math.floor(Math.random()*20)+1,
				method:'get',
				required:true,
				value:interAccount.entId,
				onLoadSuccess:function(node, data){			
					f_timeout(data);			
				}
			});			
			
			$("#parentId").combotree({
				url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
				method:'get',
				required:true,
				onLoadSuccess:function(node, data){
				f_timeout(data);			
				}
			});		
			$("input[name='interAccount.sysName']").val(interAccount.sysName);
			$("input[name='interAccount.account']").val(interAccount.account);
			$("input[name='interAccount.pass']").val(interAccount.pass);
			$("input[name='interAccount.ip']").val(interAccount.ip);
			$("#sts").combobox('setValue',interAccount.sts);		
			$("input[name='interAccount.crttime']").val(interAccount.crttime);
			$("input[name='interAccount.userId']").val(interAccount.userId);
			$("textarea[name='interAccount.sysDesc']").val(interAccount.sysDesc);			
			$('#interAccountForm').form("validate");
			$("#areaId").combotree('setValue',interAccount.areaId);
			$("#parentId").combotree('setValue',interAccount.entId);
			$("#interAccountDlg").window("open").window('setTitle', '修改接入系统账号');

		}	
	}
	
	//提交
    function submitForm(){
    	if($('#interAccountForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#interAccountForm').form('submit', {
			url : formUrl,
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {    	
				$.messager.show({ title : '提示', msg : result });   	
				//重新加载数据
	       		$('#interAccountDataGrid').datagrid("reload");  		       		
	       		//关闭进度条
	       		parent.$.messager.progress('close');	
		       	$('#interAccountDlg').window('close');
			}       		
		});     		        		       	 
    }
	
	
	//删除
	function removeInterAccount(){					
		var arr = $('#interAccountDataGrid').datagrid("getSelections");
		if(arr.length==0){
			$.messager.alert('提示','请选择要删除的信息!','question');
			return;
		}
		var obj = new Array();
		for(i=0;i<arr.length;i++){
			obj.push(arr[i].sysCode);
		}
		var ids = obj.join(',');
		$.messager.confirm('提示', '确定删除选择信息?', function(r){
			if (r){
				$.post('interAccount_deleteInterAccount.action', 'ids='+ids, function(result) {
					$.messager.show({ title : '提示', msg : result });
					$('#interAccountDataGrid').datagrid("reload"); 
				}, "TEXT");				 
			}
		});
	}
	
		     
		