
var caId='';
$(function() {
		$('#showWin').window('close');
		$('#showAllWin').window('close');
		
		 //表格数据
		 $('#comAssessdatagrid').datagrid({  
		      title : '企业考核管理',  
		      iconCls : 'icon-ok',  
		      pageSize : 15,//默认选择的分页是每页10行数据  
		      pageList : [ 10, 15,20,30,50 ],//可以选择的分页集合  
		      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		      striped : true,//设置为true将交替显示行背景。  
		      //collapsible : true,//显示可折叠按钮  
		      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
		      url:'comAssess_findComAssessList.action',//url调用Action方法  
		      loadMsg : '数据装载中......',  			      
		      fit:true,			      
		      //singleSelect:true,//为true时只能选择单行  
		      fitColumns:true,//允许表格自动缩放，以适应父容器  
		      //sortName : 'purchNo',//当数据表格初始化时以哪一列来排序  
		      //sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。  
		      remoteSort : false,  
		      frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				}] ],
		      columns:[[
						{field:'name',title:'企业名称',width:100,align:'left'},
						{field:'state',title:'状态',width:100,align:'center',formatter: function(value,row,index){ 
							if(value==2){return "待审核";}
							if(value==3){return "已审核";}
							else{return "待提交";}
							}
						},
						{field:'crrtime',title:'创建时间',width:100,align:'center'},
						{field : 'caId',title : '操作',width :90,align : 'center',
							formatter : function(value, row, index) {
								var e = "<button onclick='check("+index+")' style='width:100px'>审 核</button>";
								if(row.state==3){
									e = "<button onclick='showAll("+index+")' style='width:100px'>查看详情</button>";
								}
								return e;
							}
						}
			      ]],
              pagination : true,//分页  
              rownumbers : true//行数
	           ,
               onLoadSuccess:function(data){
		 		f_timeout(data);
	 		   }
		      });   
		});

function check(index){
	var comType;
	var cadId=[];
	var comAssess = $('#comAssessdatagrid').datagrid("getRows")[index];
	 caId=comAssess.caId;
	 var comId=comAssess.comId;
	 $.ajax({
         url:"company_getCompanyInfoByComId.action?comId="+comId,
         type:"get",
         dataType:'json',
         async: false,
         success:function(data){
        	 if(data){
					var a = data[0];
					comType=a.comType;
         }
         }
         
      });
	$.ajax({
		type : "get", 
		data : null,
		url : "comAssess_findComAssessListByCaId.action?caId="+caId, //请求路径
		dataType : "json",
		success : function(data) {
			  var rows =data.rows;
			   var op = '' ;
			   var checkSelf='';
			   for(var i= 0 ; i < rows.length; i++){
				  if(rows[i].checkSelf=='1'){
					  checkSelf='是';
				  }else{
					  checkSelf='否';
				  }
				   op +="<tr>"+
				   		"<td style='text-align:center;line-height: 35px;' ><div>"+rows[i].itemName+"</div></td>" +
				   		"<td style='text-align:left;line-height: 35px;'><div>"+rows[i].itemDesc+"</div></td>" +
				   		"<td style='text-align:center;line-height: 35px;'>" +checkSelf+
				   		"</td>"+
				   		"<td style='text-align:center;line-height: 35px;'>" +
				   		"<div>" +
				   		"<ul>" +
				   		"<li><input type='radio' name='checkAudit"+i+"' id='c"+i+"' value='1'/><label>是</label></li>" +
				   		"<li><input type='radio' checked='checked' name='checkAudit"+i+"' value='2'/><label>否</label></li>" +
				   		"</ul>" +
				   		"</div> </td>"+
				   		"</tr>";
				   cadId.push(rows[i].cadId);
				}
			   $("#cadId").val(cadId);
			   if(comType=='2'){
				   op=op+"<tr height='50px' style='text-align:center;line-height: 35px;'><td rowspan='3'>基本情况</td><td>摊位数量（个）</td><td><span>"+comAssess.tanwei+"（个）</span></td><td><input type='text'  id='tanwei2' ></td></tr>"+
				   "<tr height='50px' style='text-align:center;line-height: 35px;'><td>智能溯源秤或交易终端数（台）</td><td><span>"+comAssess.suyuanchen+"（台）</span></td><td><input type='text'  id='suyuanchen2'></td></tr>"+
				   "<tr height='50px' style='text-align:center;line-height: 35px;'><td>是否有零售交易</td><td><span>"+comAssess.lingsou+"</span></td><td><input type='text'  id='lingsou2'></td></tr>";
			   }
			   if(comType=='3'||comType=='4'){
				   op=op+"<tr height='50px' style='text-align:center;line-height: 35px;'><td rowspan='2'>基本情况</td><td>摊位数量（个）</td><td><span>"+comAssess.tanwei+"（个）</span></td><td><input type='text' id='tanwei2' ></td></tr>"+
				   "<tr height='50px' style='text-align:center;line-height: 35px;'><td>智能溯源秤或交易终端数（台）</td><td><span>"+comAssess.suyuanchen+"（台）</span></td><td><input type='text' id='suyuanchen2' ></td></tr>";
			   }
			   $('#khtbody').html(op);
			   $('#inUser').html(comAssess.inUser);
			   $('#caName').html(comAssess.name);
			   $('#addr').html(comAssess.addr);
			   $('#linkUser').html(comAssess.linkUser);
			   $('#phone').html(comAssess.phone);
			   $('#inDate').html(comAssess.inDate);
			   if(comType=="1"){
				   $('#nodeType').html("屠宰企业");
			   }
			   if(comType=="2"){
				   $('#nodeType').html("批发市场");
			   }
			   if(comType=="3"){
				   $('#nodeType').html("菜市场");
			   }
			   if(comType=="4"){
				   $('#nodeType').html("超市");
			   }
			   if(comType=="5"){
				   $('#nodeType').html("团体消费单位");
			   }
		},
		error : function(err) {
			alert("错误：" + err);
		} //异步请求失败后的回调函数
	}); 
		$("#showWin").dialog("open").dialog('setTitle', '审核');
		$('#showWin').window('open').window("maximize");
	}




function submitForm(){
	var cadId=$("#cadId").val();
	var checkAudit=[];
	var len = $("input[type='radio']:checked").length;
	for(var i =0;i< len;i++){  
	    var flag = $("input[name='checkAudit"+i+"']:checked").val();  
	    checkAudit.push(flag);
	 }  
    var tanwei2=$("#tanwei2").val();
    var suyuanchen2=$("#suyuanchen2").val();
    var lingsou2=$("#lingsou2").val();
         $.ajax({
           url:"assessDetail_updateAssessDetail.action?checkAudit="+checkAudit+"&cadId="+cadId+"&caId="+caId,
           type:"post",
           data:{tanwei2:tanwei2,suyuanchen2:suyuanchen2,lingsou2:lingsou2},
           success:function(msg){
      	   alert("审核成功");
        	$('#showWin').window('close');
        	 $('#comAssessdatagrid').datagrid("reload");
           }
           
        });
    }

function removeit(){ 
	var rows = $("#comAssessdatagrid").datagrid("getSelections");
	 //判断是否选择行
	if (!rows || rows.length == 0) {
		$.messager.alert('提示', '请选择要删除的记录!', 'info');
		return false;
	}else{
	    var temp;   //循环给提交删除参数赋值
		$.each(rows, function (i, n) {
			if (i == 0) {
	        	temp = n.caId;
		   } else {
				temp += "," + n.caId;
	       }
		}); 
		$.messager.confirm('提示', '是否删除选中数据?', function (r){
			if (!r) {
				return;
		    }else{
		         parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
		         });
		         $.ajax({
		         	  url:'comAssess_deleteComassess.action',
		         	  data:'ids='+temp,
		         	  type:'post',
		         	  dataType:'text',
		         	  success : function(result) {
		         		   parent.$.messager.progress('close');
						   $.messager.show({ title : '提示', msg : result});
						   $('#comAssessdatagrid').datagrid("reload");
					  }    
		         });
		    }
		});    
	}
	
}

function showAll(index){
	var comType;
	var comAssess = $('#comAssessdatagrid').datagrid("getRows")[index];
	 caId=comAssess.caId;
	var comId=comAssess.comId;
	 $.ajax({
         url:"company_getCompanyInfoByComId.action?comId="+comId,
         type:"get",
         dataType:'json',
         async: false,
         success:function(data){
        	 if(data){
					var a = data[0];
					comType=a.comType;
         }
         }
         
      });
	$.ajax({
		type : "get", 
		data : null,
		url : "comAssess_findComAssessListByCaId.action?caId="+caId, //请求路径
		dataType : "json",
		success : function(data) {
			  var rows =data.rows;
			   var op = '' ;
			   var checkSelf='';
			   var checkAudit='';
			   for(var i= 0 ; i < rows.length; i++){
				   if(rows[i].checkAudit=='1'){
					   checkAudit='是';
				   }else{
					   checkAudit='否';
				   }
				  if(rows[i].checkSelf=='1'){
					  checkSelf='是';
				  }else{
					  checkSelf='否';
				  }
				   op +="<tr>"+
				   		"<td style='text-align:center;line-height: 35px;'><div>"+rows[i].itemName+"</div></td>" +
				   		"<td style='text-align:left;line-height: 35px;'><div>"+rows[i].itemDesc+"</div></td>" +
				   		"<td style='text-align:center;line-height: 35px;'>" +checkSelf+"</td>"+
				   		"<td style='text-align:center;line-height: 35px;'>" +checkAudit+"</td>"+
				   		"</tr>";
				}
			   if(comType=='2'){
				   op=op+"<tr height='50px' style='text-align:center;line-height: 35px;'><td rowspan='3'>基本情况</td><td>摊位数量（个）</td><td><span>"+comAssess.tanwei2+"（个）</span></td><td><span>"+comAssess.tanwei2+"</span></td></tr>"+
				   "<tr height='50px' style='text-align:center;line-height: 35px;'><td>智能溯源秤或交易终端数（台）</td><td><span>"+comAssess.suyuanchen2+"（台）</span></td><td><span>"+comAssess.suyuanchen2+"</span></td></tr>"+
				   "<tr height='50px' style='text-align:center;line-height: 35px;'><td>是否有零售交易</td><td><span>"+comAssess.lingsou2+"</span></td><td><span>"+comAssess.lingsou2+"</span></td></tr>";
			   }
			   if(comType=='3'||comType=='4'){
				   op=op+"<tr height='50px' style='text-align:center;line-height: 35px;'><td rowspan='2'>基本情况</td><td>摊位数量（个）</td><td><span>"+comAssess.tanwei+"（个）</span></td><td><span>"+comAssess.tanwei2+"</span></td></tr>"+
				   "<tr height='50px' style='text-align:center;line-height: 35px;'><td>智能溯源秤或交易终端数（台）</td><td><span>"+comAssess.suyuanchen2+"（台）</span></td><td><span>"+comAssess.suyuanchen2+"</span></td></tr>";
			   }
			  // alert(op);
			   $('#khtbody2').html(op);
			   $('#inUser2').html(comAssess.inUser);
			   $('#caName2').html(comAssess.name);
			   $('#addr2').html(comAssess.addr);
			   $('#linkUser2').html(comAssess.linkUser);
			   $('#phone2').html(comAssess.phone);
			   $('#inDate2').html(comAssess.inDate);
			   if(comType=="1"){
				   $('#nodeType2').html("屠宰企业");
			   }
			   if(comType=="2"){
				   $('#nodeType2').html("批发市场");
			   }
			   if(comType=="3"){
				   $('#nodeType2').html("菜市场");
			   }
			   if(comType=="4"){
				   $('#nodeType2').html("超市");
			   }
			   if(comType=="5"){
				   $('#nodeType2').html("团体消费单位");
			   }
		},
		error : function(err) {
			alert("错误：" + err);
		} //异步请求失败后的回调函数
	}); 
		$("#showAllWin").dialog("open").dialog('setTitle', '审核');
		$('#showAllWin').window('open').window("maximize");
	}

        
