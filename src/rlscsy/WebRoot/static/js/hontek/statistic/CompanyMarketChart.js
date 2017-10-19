var url="meatOutInfoBase_companyMarketList.action";
var marketChart=$("#marketChart");

$(function() {
		$('#tt').tree({
	    	url:'company_findNodeCompanyList.action' ,
			onClick: function(node){
				if(node.attributes&&node.attributes.isNode){
					parentId = node.id;
					$('#comId').val(parentId);
					var pp = $('#tab').tabs('getSelected');    
					var tab = pp.panel('options').title;
					if(tab=="统计企业屠宰量"){
						find1();
					}else{
						find();
						
						
						}
					
				}
				var a =$('#tt').tree('getParent',node.target).text;
				var tabs = $("#tab").tabs("tabs");
				var tab1 = tabs.length;
				if(tab1=2 &&tab!='统计企业屠宰量'){
					$('#tab').tabs('close',"统计企业屠宰量");
				}
				if(tab!='统计企业屠宰量'&& a=="屠宰企业"){
				   $('#tab').tabs('add',{
					   title:"统计企业屠宰量",
					   href:'jsp/statistic/CompanyMarketChart1.jsp', 
					   onLoad:function(){
						   find1();
					   },
				   });
				   
				}else if(a!="屠宰企业"){
					$('#tab').tabs('close',"统计企业屠宰量");
				}
				
				
			} 
		});
	  
	var datas = {};
	datas["enterprise.chartType"] = "0";	
	$.ajax({
	   	url:url,
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(result) {
	   		
			$("#marketChart").html(result);
			
		}
	});

	$('#tab').tabs({  
	    border:false, 
	    onSelect:function(title,index){
	    	if(title=="统计企业屠宰量"){
	    		find1();
	    	}else{
	    		find();
	    	}
	    }
   
	});  


	 

});

/**
 * 统计
 * @param typeId 0 按条件统计、1统计当前月份、2统计当前年份
 * @return
 */
function find(typeId){
	var startDate = $("#startDate").datebox('getValue');
	var endDate = $("#endDate").datebox('getValue');	
	var chartType = $("#chartType").combobox('getValue');
	var comId=$('#comId').val();
	
	if(typeId==1||typeId==2){
		$("#startDate").datebox('setValue','');
		$("#endDate").datebox('setValue','');		
	}	
	var datas = {};
	datas["enterprise.startDate"] = startDate;
	datas["enterprise.endDate"] = endDate;
	datas["enterprise.typeId"] = typeId;	
	datas["enterprise.chartType"] = chartType;	
	datas["enterprise.entId"]=comId;
	$.ajax({
	   	url:url,
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(result) {
			f_timeout(result);
			$("#marketChart").html(result);
		}
	});
	
	
	
}
function find1(typeId1){
	var startDate = $("#startDate1").datebox('getValue');
	var endDate = $("#endDate1").datebox('getValue');	
	var chartType = $("#chartType1").combobox('getValue');
	var comId=$('#comId').val();
	if(typeId1==1||typeId1==2){
		$("#startDate1").datebox('setValue','');
		$("#endDate1").datebox('setValue','');		
	}	
	var datas = {};
	datas["enterprise.startDate"] = startDate;
	datas["enterprise.endDate"] = endDate;
	datas["enterprise.typeId"] = typeId1;	
	datas["enterprise.chartType"] = chartType;	
	datas["enterprise.entId"]=comId;
	$.ajax({
	   	url:'animalInInfo_getAnimalInList.action',
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(result) {
	   		
			$("#marketChart1").html(result);
			
		}
	});
	
}