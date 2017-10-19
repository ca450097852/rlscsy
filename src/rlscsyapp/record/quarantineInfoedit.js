define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
		this.qiId = "";
		this.comId = "";
		this.createTime = "";
		this.ptbId="";
	};
	
	//接收修改数据
	Model.prototype.modelParamsReceive = function(event){
		var data = event.data.data;		
		if(data){
			this.qiId = data.qiId;
			this.comId = data.comId;
			this.createTime = data.createTime;
			this.ptbId = data.ptbId;			
			this.comp("butcherFacId").val(data.butcherFacId);
			this.comp("butcherFacName").val(data.butcherFacName);			
			this.comp("sellerId").val(data.sellerId);
			this.comp("sellerName").val(data.sellerName);
			this.comp("quarantineId").val(data.quarantineId);	
			
			this.comp("quarantineAnimalProductsId").val(data.quarantineAnimalProductsId);			
			this.comp("inspectionMeatId").val(data.inspectionMeatId);	
			this.comp("sampleNum").val(data.sampleNum);	
			this.comp("sampleId").val(data.sampleId);	
			this.comp("detector").val(data.detector);	
			this.comp("checkDate").val(data.checkDate);	
			this.comp("positiveNum").val(data.positiveNum);	
			this.comp("checkNum").val(data.checkNum);						
		}		
	};


	//提交表单信息
	Model.prototype.verifyButton = function(event) {
		var self = this;
		
		var butcherFacName = this.comp("butcherFacName").val();
		var butcherFacId = this.comp("butcherFacId").val();
		var sellerId = this.comp("sellerId").val();
		var sellerName = this.comp("sellerName").val();
		
		var quarantineId = this.comp("quarantineId").val();
		var quarantineAnimalProductsId = this.comp("quarantineAnimalProductsId").val();		
		var inspectionMeatId = this.comp("inspectionMeatId").val();		
		var sampleNum = this.comp("sampleNum").val();	
		var sampleId = this.comp("sampleId").val();	
		var detector = this.comp("detector").val();	
		var checkDate = this.comp("checkDate").val();	
		var positiveNum = this.comp("positiveNum").val();	
		var checkNum = this.comp("checkNum").val();	
		
		if (!checkFormValue(butcherFacName, '请填写屠宰厂名称!')) {
			return;
		}		
		if (!checkFormValue(butcherFacId, '请填写屠宰厂编码!')) {
			return;
		}						
		if (!checkFormValue(quarantineId, '产地检疫证号!')) {
			return;
		}
		if (!checkFormValue(quarantineAnimalProductsId, '产品检疫合格证号!')) {
			return;
		}
		if (!checkFormValue(checkDate, '抽检日期!')) {
			return;
		}
		if (!checkFormValue(sellerId, '货主编码!')) {
			return;
		}
		if (!checkFormValue(sellerName, '货主名称!')) {
			return;
		}
		
		var data = {
			"quarantineInfo.qiId" : this.qiId,
			"quarantineInfo.comId" : this.comId,
			"quarantineInfo.createTime" : this.createTime,
			"quarantineInfo.ptbId" : this.ptbId,
			"quarantineInfo.butcherFacName" : butcherFacName,
			"quarantineInfo.butcherFacId" : butcherFacId,
			"quarantineInfo.quarantineId" : quarantineId,
			"quarantineInfo.quarantineAnimalProductsId" : quarantineAnimalProductsId,
			"quarantineInfo.sellerId" : sellerId,
			"quarantineInfo.sellerName" : sellerName,
			"quarantineInfo.inspectionMeatId" : inspectionMeatId,
			"quarantineInfo.sampleNum" : sampleNum,
			"quarantineInfo.sampleId" : sampleId,
			"quarantineInfo.detector" : detector,
			"quarantineInfo.checkDate" : checkDate,
			"quarantineInfo.positiveNum" : positiveNum,
			"quarantineInfo.checkNum" : checkNum
		};	
		var url = "";
		if(this.qiId==""){
			url = '/rlscsy/quarantineInfo_add.action';
		}else{
			url = '/rlscsy/quarantineInfo_update.action';
		}
		$.ajax({
			url : url,
	        type: "POST",
	        data: data,
	        dataType: "TEXT",
	        success: function (result) {
	        	if(result){	        			        					
					justep.Util.hint(result);						
					self.comp("windowReceiverEdit").windowEnsure({});				
	        	}
	        }
	    });
	};

	function checkFormValue(value,msg){
		if(value==null||value==""){
			justep.Util.hint(msg, {"type" : "danger"});
			return false;
		}
		return true;
	}

	return Model;
});