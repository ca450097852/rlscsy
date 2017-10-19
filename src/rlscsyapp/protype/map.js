define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
	};
	
	
	Model.prototype.modelParamsReceive = function(event){
	};

	Model.prototype.windowReceiverReceive = function(event){
		var lat = event.data.lat;
		var lng = event.data.lng;
		
		console.log(lat,lng);
		var url =  '/rlscsy/company/baiduAppMap.html';
		
		if(lat&&lng){
			url += '?lat='+lat+'&lng='+lng;
		}
		var mapFrame = this.getElementByXid("mapFrame");
		$(mapFrame).attr('src',url);
		
	};

	Model.prototype.okbtnClick = function(event){
		var mapFrame = this.getElementByXid("mapFrame");
		
		var lng = $($(mapFrame).contents().find("#lng")).val();
		var lat = $($(mapFrame).contents().find("#lat")).val();
		
		var receiver = this.comp("windowReceiver");
		//receiver.windowEnsure(null);
		receiver.windowEnsure({
				lng:lng,
				lat:lat
		})
		
		
	};

	return Model;
});