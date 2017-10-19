$(function() {
	var datas = {};
	datas["product.chartType"] = "0";	

	$.ajax({
	   	url:'pro_findProductChart.action',
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(result) {
			f_timeout(result);
			$("#productChart").html(result);
		}
	});
	
	$.ajax({
	   	url:'pro_findProductTable.action?random='+Math.random(),
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(r) {
			if(r){
				$("#productTable").html(r);
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
	datas["product.startDate"] = startDate;
	datas["product.endDate"] = endDate;
	datas["product.typeId"] = typeId;	
	datas["product.chartType"] = chartType;	
	
	$.ajax({
	   	url:'pro_findProductChart.action',
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(result) {
			f_timeout(result);
			$("#productChart").html(result);
		}
	});
	
	$.ajax({
	   	url:'pro_findProductTable.action?random='+Math.random(),
	   	data: datas,
	   	type:'post',
	   	dataType:'text',
	   	success : function(r) {
			if(r){
				$("#productTable").html(r);
			}
		}
	});	
}