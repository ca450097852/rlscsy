define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.meatOutInfoBase;
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
		
		var params = this.meatOutInfoBase;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,textarea').each(function(){
			var name = $(this).attr('xid');
			var value = $(this).val();
			params['meatOutInfoBase.'+name] = value;
		});
		
		var url = '/rlscsy/webmeatOutInfoBase_add.action';
		if(this.option=='update'){
			url = '/rlscsy/webmeatOutInfoBase_update.action';
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
		var meatOutInfoBase = event.params.meatOutInfoBase;
		var option = event.params.option;
		this.meatOutInfoBase = meatOutInfoBase;
		this.option = option;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,textarea').each(function(){
			var xid = $(this).attr('xid');
			self.comp(xid).val('');
		});
		
		if(option=='update'){
			for(var item in meatOutInfoBase){
				if(item){
					var xid = item.split('.')[1];
					if($('input[xid="'+xid+'"],textarea[xid="'+xid+'"]').length!=0)
						self.comp(xid).val(meatOutInfoBase[item]);
				}
			}
		}else{
			var loginCompanyUser = localStorage.getItem("loginCompanyUser");
			var company = localStorage.getItem("company");
			var node =  localStorage.getItem("node");
			
			
			loginCompanyUser = JSON.parse(loginCompanyUser);
			company = JSON.parse(company);
			var companyNode = JSON.parse(node);
			if(company && companyNode){
				meatOutInfoBase['meatOutInfoBase.comId'] = company.comId;
				
				this.comp("butcherFacId").val(companyNode.nodeCode);
				this.comp("butcherFacName").val(companyNode.name);
				
				this.comp("sellerId").val(company.comCode);
				this.comp("sellerName").val(company.name);
			}
		}
		
	};

	return Model;
});