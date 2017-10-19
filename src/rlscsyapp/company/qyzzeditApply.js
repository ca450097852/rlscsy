define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
		this.id = "";
		this.entId = "";
				
		this.typeName = "";

			
	};
	
		
	//接收修改数据
	Model.prototype.modelParamsReceive = function(event){
		
		$("span[xid=spanImg]").html("");
		
		var data = event.data.data;
		if(data){
			
			this.baseData = data;
			this.id = data.id;
			this.entId = data.entId;			
			var appType = data.appType==null?"":data.appType;
			var zizhiName = data.zizhiName==null?"":data.zizhiName;
			var grantUnit = data.grantUnit==null?"":data.grantUnit;
			var awardUnit = data.awardUnit==null?"":data.awardUnit;
			var awardTime = data.awardTime==null?"":data.awardTime;
			var remark = data.remark==null?"":data.remark;
			var p_typeName = data.typeName;
			var p_zizhiTypeList = data.zizhiTypeList;
			var p_appendixlist = data.appendixlist;
			this.comp('zizhiName').val(zizhiName);			
		}		
	};





	//提交表单信息
	Model.prototype.verifyButton = function(event) {
		
		var self = this;
		var zizhiName = this.comp("zizhiName").val().trim();
		var applyCause = this.comp("applyCause").val().trim();			
		if (!checkFormValue(applyCause, '请填写申请原因!')) {
			return;
		}				
		var data = {
			"zizhiAuditRecord.zid" : self.id,
			"zizhiAuditRecord.entId" : self.entId,
			"zizhiAuditRecord.zname" : zizhiName,
			"zizhiAuditRecord.applyCause" : applyCause
		}
		var url ='/rlscsy/zizhiAudit_addZizhiAuditRecordFromApp.action';
				
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
	
	//非空验证及提示
	function checkFormValue(value,msg){
		if(value==null||value==""){
			justep.Util.hint(msg, {"type" : "danger"});
			return false;
		}
		return true;
	}

	
	var getImageHTML = function (img){
		if(img&&img!=""){
			img='<img src="/nytsyFiles/zizhi/'+img+'" height="30px"  width="40px"/>';
		}else{
			img="";
		}
		return img;
	};

	Model.prototype.inputImageChange = function(event){};

	Model.prototype.buttonUploadClick = function(event){
		$("input[xid=inputImage]").click();	
	};

	Model.prototype.buttonRemoveClick = function(event){
		this.appendixImg = '';
		$("span[xid=spanImg]").html(getImageHTML(this.appendixImg));
	};


	//为上面提供各个JS验证方法提供.trim()属性   
	String.prototype.trim = function(){
        return this.replace(/\s+/g,"");//删除所有空格;
    }
	
	return Model;
});