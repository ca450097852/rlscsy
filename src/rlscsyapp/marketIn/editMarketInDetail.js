define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.marketInInfoDetail;
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
		
		var params = this.marketInInfoDetail;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,textarea').each(function(){
			var name = $(this).attr('xid');
			var value = $(this).val();
			params['marketInInfoDetail.'+name] = value;
		});
		
		var url = '/rlscsy/webmarketInInfoDetail_add.action';
		if(this.option=='update'){
			url = '/rlscsy/webmarketInInfoDetail_update.action';
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
		var marketInInfoDetail = event.params.marketInInfoDetail;
		var option = event.params.option;
		this.marketInInfoDetail = marketInInfoDetail;
		this.option = option;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,textarea').each(function(){
			var xid = $(this).attr('xid');
			self.comp(xid).val('');
		});
		
		if(option=='update'){
			for(var item in marketInInfoDetail){
				if(item){
					var xid = item.split('.')[1];
					if($('input[xid="'+xid+'"],textarea[xid="'+xid+'"]').length!=0)
						self.comp(xid).val(marketInInfoDetail[item]);
				}
			}
		}
		
	};

	return Model;
});