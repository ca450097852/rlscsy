var dataGrid;
$(function() {
			
 dataGrid = $('#mydatagrid').datagrid({  
      title : '系统日志管理',  
      iconCls : 'icon-log',  
      pageSize : 15,//默认选择的分页是每页5行数据  
	  pageList : [ 10, 15,20, 30, 50 ],// 可以选择的分页集合
      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
      striped : true,//设置为true将交替显示行背景。  
      //collapsible : true,//显示可折叠按钮  
      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
      url:'log_findLogList.action',//url调用Action方法  
      loadMsg : '数据装载中......',  
      fit:true,
      remoteSort : false,  
      pagination : true,//分页  
      rownumbers : true,//行数  
      frozenColumns : [ [ {  
           field : 'ck',  
           checkbox : true  
      } 
      ] ],   
      columns:[[
			{field:'colName',title:'栏目名称',width:150,align:'center'},
			{field:'funcName',title:'功能名称',width:150,align:'center'},
			{field:'actType',title:'操作类型',width:100,align:'center',formatter: function(value,row,index){
				if(value == 1){
					return "新增";
				}else if(value == 2){
					return "删除";
				}else if(value == 3){
					return "修改";
				}else if(value == 4){
					return "查询";
				}else if(value == 5){
					return "登陆";
				}else if(value == 6){
					return "退出";
				}else if(value == 7){
					return "发布";
				}else if(value == 8){
					return "停用";
				}
				
			}},
			{field:'account',title:'用户所属机构',width:100,align:'center'},
			{field:'userName',title:'操作用户',width:100,align:'center'},
			{field:'computerIp',title:'操作电脑IP',width:150,align:'center'},
			{field:'logTime',title:'日期',width:150,align:'center'}
      ]],
      onLoadSuccess:function(data){
	 	f_timeout(data);
 		}
      });   
});
		
   		
	function tb_search(){
		var actType = $("#actType").val();
		var opt_user = $("#opt_user").val();
		var startDate = $("#startDate").datebox('getValue');
		var endDate = $("#endDate").datebox('getValue');
		var cod ={};
		if(actType!=-1){
			cod["logVo.actType"]= actType;
		}
		if(opt_user!=""){
			cod["logVo.userName"]= opt_user;
		}
		if(startDate!=""){
			cod["startDate"]=startDate;
		}
		if(endDate!=""){
			cod["endDate"]=endDate;
		}
		$('#mydatagrid').datagrid('load',cod);
	}
	function tb_reset(){
		$('#search_form').form("clear");
		$("#actType").val(-1);
	}