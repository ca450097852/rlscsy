$(function() {
	var datas = {};
	datas["enterprise.chartType"] = "0";	
	
	$.ajax({
	   	url:'ent_findCompanyChart.action',
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(result) {
		//alert(result);
			$("#companyChart").html(result);
		}
	});
	
	$.ajax({
	   	url:'ent_findCompanyTable.action?random='+Math.random(),
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(r) {
			if(r){
				$("#companyTable").html(r);
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
	if(typeId==1||typeId==2){
		$("#startDate").datebox('setValue','');
		$("#endDate").datebox('setValue','');		
	}	
	var datas = {};
	datas["enterprise.startDate"] = startDate;
	datas["enterprise.endDate"] = endDate;
	datas["enterprise.typeId"] = typeId;	
	datas["enterprise.chartType"] = chartType;	

	$.ajax({
	   	url:'ent_findCompanyChart.action',
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(result) {
			f_timeout(result);
			$("#companyChart").html(result);
		}
	});
	
	
	$.ajax({
	   	url:'ent_findCompanyTable.action?random='+Math.random(),
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(r) {
			if(r){
				$("#companyTable").html(r);
			}
		}
	});	
}