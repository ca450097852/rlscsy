define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
		this.moibId = "";
		this.moidId="";
	};
	
	//接收修改数据
	Model.prototype.modelParamsReceive = function(event){
		var data = event.data.data;		
		if(data){
			this.moibId = data.moibId;
			this.moidId = data.moidId;	
					
			this.comp("quarantineAnimalProductsId").val(data.quarantineAnimalProductsId);
			this.comp("inspectionMeatId").val(data.inspectionMeatId);			
			this.comp("meatCode").val(data.meatCode);
			this.comp("meatName").val(data.meatName);
			this.comp("weight").val(data.weight);						
			this.comp("price").val(data.price);	
			this.comp("dest").val(data.dest);	
			this.comp("tranId").val(data.tranId);	
					
		}		
	};


	//提交表单信息
	Model.prototype.verifyButton = function(event) {
		var self = this;
		
		var inspectionMeatId = this.comp("inspectionMeatId").val();
		var quarantineAnimalProductsId = this.comp("quarantineAnimalProductsId").val();
		var meatCode = this.comp("meatCode").val();
		var meatName = this.comp("meatName").val();		
		var weight = this.comp("weight").val();
		var tranId = this.comp("tranId").val();		
		var price = this.comp("price").val();		
		var dest = this.comp("dest").val();	
		
		if (!checkFormValue(inspectionMeatId, '请填写肉品品质检验证号!')) {
			return;
		}		
		if (!checkFormValue(quarantineAnimalProductsId, '请填写动物产品检疫合格证号!')) {
			return;
		}						
		if (!checkFormValue(price, '请填写单价!')) {
			return;
		}
		if (!checkFormValue(tranId, '请填写交易凭证证号!')) {
			return;
		}
		if (!checkFormValue(weight, '请填写重量!')) {
			return;
		}
		if (!checkFormValue(meatCode, '请填写商品编码!')) {
			return;
		}
		if (!checkFormValue(meatName, '请填写商品名称!')) {
			return;
		}
		
		var data = {
			"meatOutInfoDetail.moibId" : this.moibId,
			"meatOutInfoDetail.moidId" : this.moidId,
			"meatOutInfoDetail.inspectionMeatId" : inspectionMeatId,
			"meatOutInfoDetail.quarantineAnimalProductsId" : quarantineAnimalProductsId,
			"meatOutInfoDetail.weight" : weight,
			"meatOutInfoDetail.tranId" : tranId,
			"meatOutInfoDetail.meatCode" : meatCode,
			"meatOutInfoDetail.meatName" : meatName,
			"meatOutInfoDetail.price" : price,
			"meatOutInfoDetail.dest" : dest}
		var url = "";
		if(this.moidId==""){
			url = '/rlscsy/meatOutInfoDetail_add.action';
		}else{
			url = '/rlscsy/meatOutInfoDetail_update.action';
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