define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
		this.vrId = "";
		this.comId = "";
		this.createTime = "";
		this.ptbId="";
	};
	
	//接收修改数据
	Model.prototype.modelParamsReceive = function(event){
		var data = event.data.data;		
		if(data){
			this.vrId = data.vrId;
			this.comId = data.comId;
			this.createTime = data.createTime;
			this.ptbId = data.ptbId;			
			this.comp("butcherFacId").val(data.butcherFacId);
			this.comp("butcherFacName").val(data.butcherFacName);			
			this.comp("sellerId").val(data.sellerId);
			this.comp("sellerName").val(data.sellerName);
			this.comp("inDate").val(data.inDate);
			this.comp("quarantineId").val(data.quarantineId);	
			this.comp("quarantineNum").val(data.quarantineNum);
			this.comp("price").val(data.price);	
			this.comp("amount").val(data.amount);	
			this.comp("deadNum").val(data.deadNum);	
			this.comp("checkResult").val(data.checkResult);	
			this.comp("areaOriginId").val(data.areaOriginId);	
			this.comp("areaOriginName").val(data.areaOriginName);	
			this.comp("farmName").val(data.farmName);	
			this.comp("transporterId").val(data.transporterId);	
					
		}		
	};


	//提交表单信息
	Model.prototype.verifyButton = function(event) {
		var self = this;
		
		var butcherFacName = this.comp("butcherFacName").val();
		var butcherFacId = this.comp("butcherFacId").val();
		var sellerId = this.comp("sellerId").val();
		var sellerName = this.comp("sellerName").val();
		var inDate = this.comp("inDate").val();			
		var quarantineId = this.comp("quarantineId").val();
		var quarantineNum = this.comp("quarantineNum").val();		
		var price = this.comp("price").val();		
		var amount = this.comp("amount").val();	
		var deadNum = this.comp("deadNum").val();	
		var checkResult = this.comp("checkResult").val();	
		var areaOriginId = this.comp("areaOriginId").val();	
		var areaOriginName = this.comp("areaOriginName").val();	
		var farmName = this.comp("farmName").val();	
		var transporterId = this.comp("transporterId").val();	
		
		if (!checkFormValue(butcherFacName, '请填写屠宰厂名称!')) {
			return;
		}
		
		if (!checkFormValue(butcherFacId, '请填写屠宰厂编码!')) {
			return;
		}		
				
		if (!checkFormValue(quarantineId, '产地检疫证号!')) {
			return;
		}
		if (!checkFormValue(quarantineNum, '进场数量!')) {
			return;
		}
		if (!checkFormValue(inDate, '进场日期!')) {
			return;
		}
		if (!checkFormValue(sellerId, '货主编码!')) {
			return;
		}
		if (!checkFormValue(sellerName, '货主名称!')) {
			return;
		}
		
		var data = {
			"animalInInfo.vrId" : this.vrId,
			"animalInInfo.comId" : this.comId,
			"animalInInfo.createTime" : this.createTime,
			"animalInInfo.ptbId" : this.ptbId,
			"animalInInfo.butcherFacName" : butcherFacName,
			"animalInInfo.butcherFacId" : butcherFacId,
			"animalInInfo.quarantineId" : quarantineId,
			"animalInInfo.quarantineNum" : quarantineNum,
			"animalInInfo.sellerId" : sellerId,
			"animalInInfo.sellerName" : sellerName,
			"animalInInfo.price" : price,
			"animalInInfo.inDate" : inDate,
			"animalInInfo.amount" : amount,
			"animalInInfo.deadNum" : deadNum,
			"animalInInfo.checkResult" : checkResult,
			"animalInInfo.areaOriginId" : areaOriginId,
			"animalInInfo.areaOriginName" : areaOriginName,
			"animalInInfo.farmName" : farmName,
			"animalInInfo.transporterId" : transporterId
		};	
		var url = "";
		if(this.vrId==""){
			url = '/rlscsy/animalInInfo_add.action';
		}else{
			url = '/rlscsy/animalInInfo_update.action';
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