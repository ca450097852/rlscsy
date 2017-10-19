define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.agriUse;
		this.option;
	};

	

	Model.prototype.modelParamsReceive = function(event){
		var self = this;
		var agriUse = event.params.agriUse;
		var option = event.params.option;
		
		this.agriUse = agriUse;
		this.option = option;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,select,textarea').each(function(){
			var xid = $(this).attr('xid');
			self.comp(xid).val('');
		});
		
		if(option=='update'){
			for(var item in agriUse){
				if(item){
					var xid = item.split('.')[1];
					if($('input[xid="'+xid+'"],select[xid="'+xid+'"],textarea[xid="'+xid+'"]').length!=0)
						self.comp(xid).val(agriUse[item]);
				}
			}
		}else{
			this.comp("useDate").val(getNowFormatDate());
			
		}
		
	};


	
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	    return currentdate;
	}

	
	Model.prototype.submitBtn = function(event){
		var self = this;
		var flag = false;
		$('.requireField').each(function(){
			var v = $(this).val();
			if(v == ''){
				var text = $(this).parent().prev().text();
				justep.Util.hint(text+"不能为空！", {"type" : "danger"});
				flag = true;
				return;
			}
		});
		if(flag){
			return;
		}
		
		var params = this.agriUse;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,select,textarea').each(function(){
			var name = $(this).attr('xid');
			var value = $(this).val();
			params['agriUse.'+name] = value;
			
		});
		
		var url = '/rlscsy/agriUse_addAgriUse.action';
		if(this.option=='update'){
			url = '/rlscsy/agriUse_updateAgriUse.action';
		}
		
		$.ajax({
			url:url,
			data:params,
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					self.comp("windowReceiver2").windowEnsure({"option":"refresh"})
				}
			}
		});
	};


	return Model;
});