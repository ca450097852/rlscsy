define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
	};

	Model.prototype.backBtnClick = function(event){
		this.comp("windowReceiverImg").windowEnsure({});
	};

	Model.prototype.modelParamsReceive = function(event){			
		var imgSrc = event.data.data.imgSrc;
		$("img[xid=image1]").attr("src",imgSrc);	
	};

	return Model;
});