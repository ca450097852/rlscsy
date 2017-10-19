define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
	};

	Model.prototype.pfbtnClick = function(event){
		justep.Shell.showPage(require.toUrl("./marketInBase_pf.w"), {"ptbId":86});
	};

	return Model;
});