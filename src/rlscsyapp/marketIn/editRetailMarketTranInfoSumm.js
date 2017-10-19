define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.retailMarketTranInfoSumm;
		this.flag = 2;
		this.option;
	};

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
		
		var params = this.retailMarketTranInfoSumm;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,textarea').each(function(){
			var name = $(this).attr('xid');
			var value = $(this).val();
			params['retailMarketTranInfoSumm.'+name] = value;
		});
		
		var url = '/rlscsy/webretailMarketTranInfoSumm_add.action';
		if(this.option=='update'){
			url = '/rlscsy/webretailMarketTranInfoSumm_update.action';
		}
		
		$.ajax({
			url:url,
			data:params,
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
//					setTimeout(function(){self.close();}, 1000);
					self.comp("windowReceiver1").windowEnsure({"option":"refresh"})
				}
			}
		});
	};

	Model.prototype.modelParamsReceive = function(event){
		var self = this;
		var retailMarketTranInfoSumm = event.params.retailMarketTranInfoSumm;
		var option = event.params.option;
		this.retailMarketTranInfoSumm = retailMarketTranInfoSumm;
		this.option = option;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,textarea').each(function(){
			var xid = $(this).attr('xid');
			self.comp(xid).val('');
		});
		
		if(option=='update'){
			for(var item in retailMarketTranInfoSumm){
				if(item){
					var xid = item.split('.')[1];
					if($('input[xid="'+xid+'"],textarea[xid="'+xid+'"]').length!=0)
						self.comp(xid).val(retailMarketTranInfoSumm[item]);
				}
			}
		}
		
	};

	return Model;
});