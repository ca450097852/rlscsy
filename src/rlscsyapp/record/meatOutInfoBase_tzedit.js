define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
		this.moibId = "";
		this.comId = "";
		this.createTime = "";
		this.ptbId="";
	};
	
	//接收修改数据
	Model.prototype.modelParamsReceive = function(event){
		var data = event.data.data;		
		if(data){
			this.moibId = data.moibId;
			this.comId = data.comId;
			this.createTime = data.createTime;
			this.ptbId = data.ptbId;			
			this.comp("butcherFacId").val(data.butcherFacId);
			this.comp("butcherFacName").val(data.butcherFacName);			
			this.comp("sellerId").val(data.sellerId);
			this.comp("sellerName").val(data.sellerName);
			this.comp("buyerId").val(data.buyerId);						
			this.comp("buyerName").val(data.buyerName);	
			this.comp("dest").val(data.dest);	
			this.comp("tranDate").val(data.tranDate);	
			this.comp("tranId").val(data.tranId);	
					
		}		
	};


	//提交表单信息
	Model.prototype.verifyButton = function(event) {
		var self = this;
		
		var butcherFacName = this.comp("butcherFacName").val();
		var butcherFacId = this.comp("butcherFacId").val();
		var sellerId = this.comp("sellerId").val();
		var sellerName = this.comp("sellerName").val();		
		var buyerId = this.comp("buyerId").val();
		var tranId = this.comp("tranId").val();		
		var buyerName = this.comp("buyerName").val();		
		var dest = this.comp("dest").val();	
		var tranDate = this.comp("tranDate").val();	
		
		if (!checkFormValue(butcherFacName, '请填写屠宰厂名称!')) {
			return;
		}		
		if (!checkFormValue(butcherFacId, '请填写屠宰厂编码!')) {
			return;
		}						
		if (!checkFormValue(buyerName, '买主名称!')) {
			return;
		}
		if (!checkFormValue(tranId, '交易凭证证号!')) {
			return;
		}
		if (!checkFormValue(tranDate, '交易日期!')) {
			return;
		}
		if (!checkFormValue(sellerId, '货主编码!')) {
			return;
		}
		if (!checkFormValue(sellerName, '货主名称!')) {
			return;
		}
		
		var data = {
			"meatOutInfoBase.moibId" : this.moibId,
			"meatOutInfoBase.comId" : this.comId,
			"meatOutInfoBase.createTime" : this.createTime,
			"meatOutInfoBase.ptbId" : this.ptbId,
			"meatOutInfoBase.butcherFacName" : butcherFacName,
			"meatOutInfoBase.butcherFacId" : butcherFacId,
			"meatOutInfoBase.buyerId" : buyerId,
			"meatOutInfoBase.tranId" : tranId,
			"meatOutInfoBase.sellerId" : sellerId,
			"meatOutInfoBase.sellerName" : sellerName,
			"meatOutInfoBase.buyerName" : buyerName,
			"meatOutInfoBase.dest" : dest,
			"meatOutInfoBase.flag" : "1",
			"meatOutInfoBase.tranDate" : tranDate}
		var url = "";
		if(this.moibId==""){
			url = '/rlscsy/meatOutInfoBase_add.action';
		}else{
			url = '/rlscsy/meatOutInfoBase_update.action';
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