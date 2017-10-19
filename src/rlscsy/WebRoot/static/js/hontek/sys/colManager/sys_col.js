var treeGrid;
var submitFormUrl = '';
$(function() {
			
     $('#add').window('close');
     treeGrid = $('#mydatagrid').treegrid({
			title:'栏目管理',
			iconCls:'icon-application_cascade',
			width:700,
			height:250,
			rownumbers: true,
			animate:true,
			collapsible:true,
			fitColumns:true,
			url:'col_findColList.action',
			idField:'id',
			treeField:'colName',
			showFooter:true,
			fit:true,
			toolbar:"#tb",
			columns:[[
					{field:'colName',title:'栏目名称',width:100,align:'left'},
					{field:'remarks',title:'栏目说明',width:100,align:'center'},
					{field:'colUrl',title:'连接路径',width:300,align:'center'},
					/*{field:'iconUrl',title:'图片路径',width:300,align:'center'},*/
					{field:'seq',title:'显示顺序',width:100,align:'center'}
		      ]],
		      onLoadSuccess:function(row, data){
		 	 	f_timeout(data);
		  		}
		});
     parent.$.messager.progress('close');
});
		

		// 添加
		function append(){
			$('#add_form').form("clear");		
			var arr = $('#mydatagrid').treegrid("getSelections");
			var defValue = '';
			if(arr.length==1){
				defValue = arr[0].id;
			}
			$("#parentid").combotree({
	    	 	url: 'col_getSysColTree.action?random='+Math.random(),
				method: 'get',
				panelWidth: 200,
				panelHeight: 300,
				value:defValue
			});

			submitFormUrl = 'col_addCol.action';
			$("#col_seq").numberbox('setValue',5);
			$('#add_form').form('validate');
			$("#add").window("open").window('setTitle', '新增栏目');

		}
		
		
		// 修改
		function update(){
			$('#add_form').form("clear");			
			var arr = $('#mydatagrid').treegrid("getSelections");
			var lg = arr.length;
			if(lg==0){
				$.messager.alert('提示','请选择需要修改的栏目!','question');
			}else if(lg!=1){
				$.messager.alert('提示','对不起，一次只能修改一个栏目，请重新选择!','question');
			}else{
				
				submitFormUrl = 'col_updateCol.action';
				var col = arr[0];				
				$("input[name='col.id']").val(col.id);
				$("input[name='col.iconUrl']").val(col.iconUrl);
				//$("#parentid").combotree('setValue',col._parentId);
				$("#parentid").combotree({
		    	 	url: 'col_getSysColTree.action?random='+Math.random(),
					method: 'get',
					panelWidth: 200,
					panelHeight: 300,
					value:col._parentId
				});
				
				$("input[name='col.colName']").val(col.colName);
				$("input[name='col.remarks']").val(col.remarks);
				$("input[name='col.colUrl']").val(col.colUrl);
				$("#col_seq").numberbox('setValue',col.seq);	
				
				$('#add_form').form('validate');
				
				$("#add").window("open").window('setTitle', '修改栏目');
			}			
		}
				
        function submitForm(){
           $('#add_form').form('submit', {
        	   url : submitFormUrl,
				onSubmit : function(result) { 
				    return $(this).form('validate');//对数据进行格式化
				},
				success : function(result) {					
					$.messager.show({ title : '提示', msg : result });
					$('#add').window('close');
					$('#mydatagrid').treegrid("reload");
				}
			});

        	
        }
   		function clearForm(){
   			$('#add_form').form("clear");
        } 		   		
   		
   		function tb_search(){
   			var r_roleName = $("#r_roleName").val();
   			var r_sts = $("#r_sts").val();
   			var cod ={};
   			if(r_roleName!=""){
   				cod["role.roleName"]= r_roleName;
   			}
   			if(r_sts!=-1){
   				cod["role.sts"]= r_sts;
   			}
   			$('#mydatagrid').treegrid('load',cod);
   		}
   		// 删除
		function removeit(){
			var arr = $('#mydatagrid').treegrid("getSelections");
			if(arr.length==0){
				$.messager.alert('提示','请选择要删除的栏目!','question');
				return;
			}
			var obj = new Array();
			for(i=0;i<arr.length;i++){
				obj.push(arr[i].id);
			}
			var ids = obj.join(',');
			$.messager.confirm('提示', '确定删除选择栏目?', function(r){
				if (r){
					$.post('col_deleteCol.action', 'ids='+ids, function(result) {
						$.messager.show({ title : '提示', msg : result });
						$('#mydatagrid').treegrid("reload");
					}, "TEXT");
					 
				}
			});
		}
		