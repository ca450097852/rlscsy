define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.fertilizerUse;
		this.option;
	};

	

	Model.prototype.modelParamsReceive = function(event){
		var self = this;
		var fertilizerUse = event.params.fertilizerUse;
		var option = event.params.option;
		
		this.fertilizerUse = fertilizerUse;
		this.option = option;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,textarea').each(function(){
			var xid = $(this).attr('xid');
			self.comp(xid).val('');
		});
		
		if(option=='update'){
			for(var item in fertilizerUse){
				if(item){
					var xid = item.split('.')[1];
					if($('input[xid="'+xid+'"],textarea[xid="'+xid+'"]').length!=0)
						self.comp(xid).val(fertilizerUse[item]);
				}
			}
		}else{
			this.comp("usedate").val(getNowFormatDate());
			
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


	/*Model.prototype.comboDataCustomRefresh = function(event){
		var self = this;
	    var comboData = event.source;
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/agriInput_findAgriInputComboList.action',
	        data:{"ids":3},
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	            comboData.loadData(data);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("加载数据失败");
	        }
	    });	

	};*/
	
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
		
		var params = this.fertilizerUse;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,textarea').each(function(){
			var name = $(this).attr('xid');
			var value = $(this).val();
			params['fertilizerUse.'+name] = value;
		});
		
		var url = '/rlscsy/fertilizeruse_addFertilizerUse.action';
		if(this.option=='update'){
			url = '/rlscsy/fertilizeruse_updateFertilizerUse.action';
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