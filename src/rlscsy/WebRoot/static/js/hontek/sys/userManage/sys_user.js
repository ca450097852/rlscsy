var mydatagrid;
$(function() {
	//判断是否显示二次接入选项操作
	
			 var isAdmin = $("#isAdmin").val();
		     $('#add').window('close');
			 $('#assignRoleWindow').window('close');
		     $("#dlg").window('close');
		     
		     mydatagrid= $('#mydatagrid').datagrid({  
			      title : '用户管理',  
			      iconCls : 'icon-user',  
			      pageSize : 15,//默认选择的分页是每页10行数据  
			      pageList : [ 10, 15,20,30,50 ],//可以选择的分页集合  
			      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
			      striped : true,//设置为true将交替显示行背景。  
			      //collapsible : true,//显示可折叠按钮  
			      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
			      url:'user_findUserList.action',//url调用Action方法  
			      loadMsg : '数据装载中......',  			      
			      fit:true,
			      pagination : true,//分页  
			      rownumbers : true,//行数  			      
			      remoteSort : false,  
			      columns : [ [ {  
			           field : 'ck',  
			           checkbox : true  
			      } ,
			      {field:'entName',title:'所属机构',width:200,halign:'center'},
			      {field:'userName',title:'帐号',width:100,align:'center'},
			      {field:'nickname',title:'昵称',width:100,align:'center'},
				  {field:'sts',title:'使用状态',width:60,align:'center',formatter: function(value,row,index){
						if(value==0){
							return "使用";
						}else{
							return "<font color=red>禁用</font>";
						}
					}},
					{field:'phone',title:'联系电话',width:100,align:'center'},
					{field:'email',title:'联系邮箱',width:120,align:'center'},

					{field:'regDate',title:'注册日期',width:120,align:'center'},
					{field:'action',title:'操作',width:100,align:'center',formatter: function(value,row,index){
						var e = "<a href='javascript:void(0)' onclick='assignRole("+index+")'>分配角色</a>&nbsp;&nbsp;";
						return e;
					}}
			      ] ],   			     
			      onClickRow: function(rowIndex, rowData){
		 			$(this).datagrid('unselectAll');
		 			$(this).datagrid('selectRow',rowIndex);
		     	  } ,
			      onLoadSuccess:function(data){
				 	f_timeout(data);
			 		}
			      });   

		    
		     //查询条件显示 - 组织机构
		     $("#parentId").combotree({
		     	url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
		     	method:'get',
		     	panelWidth: '200'
		     });	
		     
		     /**分配角色*/

			$('#addRole').click(function(){
				var $options = $('#wait option:selected');//获取当前选中的项
				var $remove = $options.remove();//删除下拉列表中选中的项
				$remove.appendTo('#had');//追加给对方
			});
			
			$('#removeRole').click(function(){
				var $removeOptions = $('#had option:selected');
				$removeOptions.appendTo('#wait');//删除和追加可以用appendTo()直接完成
			});
			/*
			$('#addAll').click(function(){
				var $options = $('#wait option');
				$options.appendTo('#had');
			});
			
			$('#removeAll').click(function(){
				var $options = $('#had option');
				$options.appendTo('#wait');
			});
			*/
			
			
			//保存角色信息
			$('#saveRole').click(function(){
				
				var $options = $('#had option');				
				var obj = new Array();				
				 $('#had option').each(function(i) {
			          var value = $(this).val();
			          obj.push(value);
				 });									
				var ids = obj.join(',');
				
				//alert(userId+"---"+ids);
				//保存分配角色
				$.ajax({
		         	  url:'role_saveRoleUser.action',
		         	  data:'roleIds='+ids+'&userId='+userId,
		         	  type:'post',
		         	  dataType:'text',
		         	  success : function(r) {
						$.messager.show({ title : '提示', msg : r });
						$('#assignRoleWindow').window('close');
					  }    
		         });
			});
			
			//双击事件
			$('#wait').dblclick(function(){
				var $options = $('option:selected', this);//注意此处“option”与“:”之间的空格，有空格是不可以的
				$options.appendTo('#had');
			});
			
			$('#had').dblclick(function(){
				$('#had option:selected').appendTo('#wait');
			});
			
			//关闭
			$('#closeRoleWindow').click(function(){
				$('#assignRoleWindow').window('close');
			});
		      
		});
		
		/**
		 * 分配角色
		 * @param index
		 * @return
		 */
		var userId ='' ;			
		function assignRole(index){
			//
			var row = $('#mydatagrid').datagrid("getRows")[index];			
			userId = row.userId;			
			var entId = row.entId;
			
			$('#wait option').remove();		
			$('#had option').remove();	
			//读取未拥有角色
			$.ajax({
	         	  url:'role_getEntRoleTreeList.action',
	         	  data:'entId='+entId+'&userId='+userId,
	         	  type:'post',
	         	  dataType:'text',
	         	  success : function(options) {				
					$('#wait').append(options);
				  }    
	         }); 
			//读取已拥有角色
			$.ajax({
	         	  url:'role_getUserRoleTreeList.action',
	         	  data:'userId='+userId,
	         	  type:'post',
	         	  dataType:'text',
	         	  success : function(options) {					
					$('#had').append(options);										
				  }    
	         }); 
			
			//打开
			$('#assignRoleWindow').window('open');							
		}
		
		function showDet(index){}

		// 添加
		function append(){
			
			$('#add_form').form("reset");
			
			$("#user_entid").combotree({
				url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
				method:'get',
				required:true,
				onLoadSuccess:function(node, data){
				if(data){
					if(data[0].checked){
						$("#user_entid").combotree("setValue",data[0].id);
					}
				}
				f_timeout(data);			
			}
		   });	
		   
			$("#method").val("add");		
		
			$("#user_se_sts").combobox("setValue",0);	
			$("#user_pri").numberbox("setValue",5);	
			$("#user_entid").combotree("setValue","");			
		
			$("input[name='user.userName']").attr("readonly",false);
			$("input[name='user.userName']").validatebox('enableValidation');
			
			$("#add").dialog("open").dialog('setTitle', '增加用户');

		}
		
		
		// 修改
		function update(){
			
			var arr = $('#mydatagrid').datagrid("getSelections");
			var lg = arr.length;
//			alert(lg);
			if(lg==0){
				$.messager.alert('提示','请选择需要修改的用户!','question');
			}else if(lg!=1){
				$.messager.alert('提示','对不起，一次只能修改一个用户，请重新选择!','question');
			}else{
				var user = arr[0];
				$("#method").val("update");
				$("input[name='user.userId']").val(user.userId);

				$("#user_entid").combotree({
					url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
					method:'get',
					required:true,
					value:user.entId
				});

								
				$("input[name='user.userName']").val(user.userName);
				
				$("input[name='user.userName']").attr("readonly",true);
				//禁用验证
				$("input[name='user.userName']").validatebox('disableValidation');
				
				$("input[name='user.password']").val(user.password);
				
				$("#user_flag").combobox("setValue", user.flag);
				$("#birthDate").datebox("setValue", user.birthDate);
				
				$("input[name='user.nickname']").val(user.nickname);
				$("input[name='user.sex']").val(user.sex);
				$("input[name='user.age']").val(user.age);
				//$("select[name='user.flag']").val(user.flag);
				$("input[name='user.phone']").val(user.phone);
				$("input[name='user.email']").val(user.email);
				//$("select[name='sts']").val(user.sts);
				$("#user_se_sts").combobox("setValue",user.sts);
				
				$("input[name='user.signature']").val(user.signature);
				$("input[name='user.qqnum']").val(user.qqnum);
				$("input[name='user.tel']").val(user.tel);
				$("input[name='user.fax']").val(user.fax);
				$("input[name='user.addr']").val(user.addr);
				$("textarea[name='user.intrest']").val(user.intrest);
				$("input[name='user.regDate']").val(user.regDate);
				
				$("#add_form").form('validate');
				$("#add").dialog("open").dialog('setTitle', '修改用户');
			}
			
		}
		// 删除
		function removeit(){
			
			//getSelections
			var arr = $('#mydatagrid').datagrid("getSelections");
			if(arr.length==0){
				$.messager.alert('提示','请选择要删除的用户!','question');
				return;
			}
			var obj = new Array();
			for(i=0;i<arr.length;i++){
				obj.push(arr[i].userId);
			}
			//alert(obj.join(','));
			var ids = obj.join(',');
			$.messager.confirm('提示', '确定删除选择用户?', function(r){
				if (r){
					$.post('user_deleteUsers.action', 'ids='+ids, function(result) {
						$.messager.show({ title : '提示', msg : result });
						$('#mydatagrid').datagrid("reload");
					}, "TEXT");
					 
				}
			});
		}
		
		
        function submitForm(){
        	if($("#add_form").form('validate')==false){
				$.messager.show({ title : '提示', msg : '请检查必填项是否已经填写!' });
				return ;
        	}
        	var url = '';
        	var m = $("#method").val();
        	if(m=='add'){
        		url = 'user_addUser.action';
        	}else if(m=='update'){
        		url = 'user_updateUser.action';
        	}else{
        		return;
        	}
        		
           $('#add_form').form('submit', {
				url : url,
				onSubmit : function(result) { 
				    return $(this).form('validate');
				},
				success : function(result) {					
					$.messager.show({ title : '提示', msg : result });
					$('#add').window('close');
					$('#mydatagrid').datagrid("reload");
				}
			});
        }
        
   		function closeWin(){
			$('#add').window('close');
   		}
   		  		
   		//搜索
   		function tb_search(){
   			var u_sts = $("#u_sts").val();
   			var userName = $("#userName").val();
   			var ent_id = $("#parentId").combotree('getValue');
   			var flag = $("#u_flag").combobox('getValue');

   			
   			var cod ={};
   			if(u_sts!=-1){
   				cod["user.sts"]= u_sts;
   			}
   			if(userName!=""){
   				cod["user.userName"]=userName;
   			}
   			if(ent_id!=""){
   				cod["user.entId"]= ent_id;
   			}
   			if(flag!=""){
   				cod["user.flag"]= flag;
   			}
   			$('#mydatagrid').datagrid('load',cod);
   		}
   		
   		//重置
   		function tb_reset(){
   			$("#u_sts").val('-1');
   			$("#userName").val('');
   			$("#parentId").combotree('setValue','');
   			var cod ={};
   			$('#mydatagrid').datagrid('load',cod);
   		}
   		
   		var userid;
   		//授权
   		function empower(){
   			var arr = $('#mydatagrid').datagrid("getSelections");
			if(arr.length==0){
				$.messager.alert('提示','请选择要授权的用户!','question');
				return;
			}else if(arr.length!=1){
				$.messager.alert('提示','对不起一次只能选择一个用户！','question');
			}else{
				//alert(arr[0].userId);
				$('#empower').window('open');
				userid = arr[0].userId;
				var combogd;
				combogd = $("#role").combogrid({
					panelWidth: 500,
					//multiple: true,
					idField: 'roleId',
					textField: 'roleName',
					url: 'role_findRoleList.action?role.sts=1&role.entId='+arr[0].entId,
				    columns:[[
							{field:'roleName',title:'角色名称',width:100,align:'center'},
							{field:'level',title:'级别',width:100,align:'center'},
							{field:'desc',title:'角色描述',width:300,align:'center'}
				      ]],
					fitColumns: true,
					onLoadSuccess:function(data){
						var varobj = data.rows;
						
						$.post('user_getUserPower.action', 'user.userId='+userid, function(result) {
							for(var i=0;i<result.length;i++){
								$('#role').combogrid('grid').datagrid('selectRecord',result[i].roleId);
							}
						}, "JSON");
					}
				});
				
				
			}
			
   		}
   		
   		
   		/**
   		 * 更改用户状态
   		 * type=0 使用
   		 * type=1 禁用
   		 */
   		function changeSts(type){
   			var arr = $('#mydatagrid').datagrid("getSelections");
   			var arrall = new Array();
   			var lg = arr.length;
   			if(lg==0){
   				$.messager.alert('提示','请选要操作的对象!','question');
   			}else{
   				for(var i=0;i<lg;i++){
   					arrall.push(arr[i].userId)
   				}
   				$.messager.confirm('提示', '是否要继续执行?', function(r){
   						$.post('user_changeSts.action', 'ids='+arrall.join(",")+"&user.sts="+type, function(result) {
   							$.messager.show({ title : '提示', msg : result });
   							$('#mydatagrid').datagrid("reload");
   						}, "TEXT");
   					}
   				)
   			}
   		}
 
   		