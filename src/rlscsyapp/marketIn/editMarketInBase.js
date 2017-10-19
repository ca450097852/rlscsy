define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	
	require("$UI/system/lib/cordova/cordova");
	require("cordova!phonegap-plugin-barcodescanner");

	var Model = function(){
		this.callParent();
		this.marketInInfoBase;
		this.option;
		this.STORE_ID = "com.justep.demo.advice.barcodedata";
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
		
		var params = this.marketInInfoBase;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,textarea').each(function(){
			var name = $(this).attr('xid');
			var value = $(this).val();
			params['marketInInfoBase.'+name] = value;
		});
		
		var url = '/rlscsy/webmarketInInfoBase_add.action';
		if(this.option=='update'){
			url = '/rlscsy/webmarketInInfoBase_update.action';
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
					self.comp("windowReceiver2").windowEnsure({"option":"refresh"})
				}
			}
		});
	};

	Model.prototype.modelParamsReceive = function(event){
		var self = this;
		var marketInInfoBase = event.params.marketInInfoBase;
		var option = event.params.option;
		this.marketInInfoBase = marketInInfoBase;
		this.option = option;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,textarea').each(function(){
			var xid = $(this).attr('xid');
			self.comp(xid).val('');
		});
		
		if(option=='update'){
			for(var item in marketInInfoBase){
				if(item){
					var xid = item.split('.')[1];
					if($('input[xid="'+xid+'"],textarea[xid="'+xid+'"]').length!=0)
						self.comp(xid).val(marketInInfoBase[item]);
				}
			}
		}else{
			var loginCompanyUser = localStorage.getItem("loginCompanyUser");
			var company = localStorage.getItem("company");
			var node =  localStorage.getItem("node");
			
			this.comp("inDate").val(getNowFormatDate());
			
			
			loginCompanyUser = JSON.parse(loginCompanyUser);
			company = JSON.parse(company);
			var companyNode = JSON.parse(node);
			if(company && companyNode){
				marketInInfoBase['marketInInfoBase.comId'] = company.comId;
				
				this.comp("marketId").val(companyNode.nodeCode);
				this.comp("marketName").val(companyNode.name);
				
				this.comp("wholesalerId").val(company.comCode);
				this.comp("wholesalerName").val(company.name);
			}
			
		}
		
		var flag = marketInInfoBase['marketInInfoBase.flag']
		if(flag==2){
			//$(this.getElementByXid("div16")).show();
			
		}else if(flag==3){
			$(this.getElementByXid("div16")).remove();
			
			$(this.getElementByXid("span3")).html('零售商编码');
			$(this.getElementByXid("span5")).html('零售商名称');
			
		}else if(flag==4){
			$(this.getElementByXid("div16")).remove();
			
			$(this.getElementByXid("span2")).html('超市编码');
			$(this.getElementByXid("span1")).html('超市名称');
			
			$(this.getElementByXid("span3")).html('供应商编码');
			$(this.getElementByXid("span5")).html('供应商名称');
			
		}
		
		
	};


	Model.prototype.scanbtnClick = function(event){
		var self = this;
		function onSuccess(result) {
			var text = result.text;
			if(text){
				var code = text.split('code=')[1];
				if(code){
					$.ajax({
						url:'/rlscsy/quarantineInfo_getQuarantineInfoByCode.action',
						data:{'code':code},
						type:'post',
						dataType:'json',
						success:function(result){
							if(result){
								self.comp("quarantineAnimalProductsId").val(result.quarantineAnimalProductsId);
								self.comp("inspectionMeatId").val(result.inspectionMeatId);
							}else{
								justep.Util.hint("没有扫描到数据");
							}
						}
					});
				}
			}
		}
		
		function onError(error) {
			alert(error);
		}
		
		cordova.plugins.barcodeScanner.scan(onSuccess, onError);
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


	return Model;
});