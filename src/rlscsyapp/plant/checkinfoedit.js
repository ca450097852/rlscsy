define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
		this.checkId = "";
		this.recId = "";
	};
	
	//接收修改数据
	Model.prototype.modelParamsReceive = function(event){
		var data = event.data.data;		
		if(data){
			this.checkId = data.checkId;
			this.recId = data.recId;

			this.comp("checkName").val(data.checkName);
			this.comp("checkNum").val(data.checkNum);
			this.comp("checkUnit").val(data.checkUnit);
			this.comp("checkTime").val(data.checkTime);	
			this.comp("checkResult").val(data.checkResult);
			this.comp("checkType").val(data.checkType);				
			
		}		
	};


	//提交表单信息
	Model.prototype.verifyButton = function(event) {
		var self = this;
	
		var checkNum = this.comp("checkNum").val();
		var checkTime = this.comp("checkTime").val();
		var checkResult = this.comp("checkResult").val();
		var checkUnit = this.comp("checkUnit").val();	
		var checkName = this.comp("checkName").val();
		var checkType = this.comp("checkType").val();		

		if (!checkFormValue(checkName, '请填写报告名称!')) {
			return;
		}		
		if (!checkFormValue(checkUnit, '请填写检验单位!')) {
			return;
		}		
		if (!checkFormValue(checkResult, '请填写检验结果!')) {
			return;
		}
		if (!checkFormValue(checkTime, '请填写检验时间!')) {
			return;
		}
		
		var data = {			
			"checkinfo.recId" : this.recId,
			"checkinfo.checkId" : this.checkId,
			"checkinfo.checkTime" : checkTime,
			"checkinfo.checkResult" : checkResult,
			"checkinfo.checkName" : checkName,
			"checkinfo.checkNum" : checkNum,
			"checkinfo.checkType" : checkType,
			"checkinfo.checkUnit" : checkUnit
		}	
				
		var url = "/rlscsy/checkinfo_updateCheckinfo.action";
		if(this.checkId==""){
			url = '/rlscsy/checkinfo_addCheckinfo.action';
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