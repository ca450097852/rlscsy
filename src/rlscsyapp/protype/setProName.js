define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
	};




	Model.prototype.windowReceiver1Receive = function(event){
		var data = event.data;
		var items = data.items;
		
		if(items){
			this.comp("itemData").clear();
			this.comp("itemData").loadData(items);
		}
	};



	Model.prototype.saveBtnClick = function(event){
	
		var ids = new Array();
		this.comp("itemData").each(function(){
			ids.push(this.val('typeId')+'@-@'+this.val('typeName'));
		});
		
		var params = {'ids':ids.join('@--@')};
		
		$.ajax({
	        type: "GET",
	        url: '/rlscsy/record_addOrDelRecord.action',
	        data:params,
	        dataType: 'text',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	if(data){
	        		justep.Util.hint(data);
	        		setTimeout(function(){
	        			window.location.href="./protype/proTypeDetail.w"
	        		}, 800);
	        	}
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	
	};



	return Model;
});