
define(function(require){

	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	
	var Model = function() {
		this.callParent();
		this.dimenno = "";
	};
	
	//接收
	Model.prototype.modelParamsReceive = function(event){	
		
		this.dimenno = event.params.dimenno;
		//alert(this.dimenno);
		
		var data1 =  this.comp("data1");
		data1.refreshData();
	};
	
	
	//iframe1==src
	Model.prototype.getSrcUrl = function(dimenno) {
		var src = require.toUrl("/rlscsy/trace.jsp?code="+dimenno);
		return src;
	}
		
	Model.prototype.data1CustomRefresh = function(event){
		var self = this;
		
		var data1 = event.source;
		data1.clear();
		
		var data = new Array();
		if(self.dimenno!=''){
			data.push({"dimenno":self.dimenno});
		}
		
		//alert(self.dimenno);
		
		data1.loadData(data);

	};
	
		
	Model.prototype.button1Click = function(event){
		history.back();
	};
	
		
	return Model;
});