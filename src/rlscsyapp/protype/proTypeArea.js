define(function(require){
	var $ = require("jquery");
	
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
	};

	Model.prototype.proTypeAreaDataCustomRefresh = function(event){
	
		var proTypeAreaData = event.source;
		var queryParams = {rows:1000};
	
		$.ajax({
	        type: "GET",
	        url: '/rlscsy/proTypeArea_findProTypeAreaList.action',
	        data:queryParams,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	if(data){
	        		var rows = data.rows;
	        		proTypeAreaData.loadData(rows,true);
	        	}
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	  
	};

	Model.prototype.updateAreaClick = function(event){
		var row = event.bindingContext.$object;
		
		
		var src = require.toUrl('./editProTypeArea.w');
		var windowDialog = this.comp("windowDialog");
		windowDialog.set({src:src});
		windowDialog.open({
			'data':{
				'option':'update',
				'row':row
			}
		});
	};

	Model.prototype.addAreaClick = function(event){
		var src = require.toUrl('./editProTypeArea.w');
		var windowDialog = this.comp("windowDialog");
		windowDialog.set({src:src});
		windowDialog.open({
			'data':{
				'option':'add'
			}
		});
	};

	Model.prototype.windowDialogClose = function(event){
		this.comp("proTypeAreaData").clear();
		this.comp("proTypeAreaData").refreshData({});
	};
	var PTAID;
	Model.prototype.deleteAreaClick = function(event){
		var row = event.bindingContext.$object;
		PTAID = row.val('ptaId');
		this.comp("messageDialog").show();
	};

	Model.prototype.messageDialogOK = function(event){
		
		var proTypeAreaData = this.comp("proTypeAreaData");
		
		$.ajax({
	        type: "GET",
	        url: '/rlscsy/proTypeArea_deleteProTypeArea.action',
	        data:{ids:PTAID},
	        dataType: 'text',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
        		justep.Util.hint(data);
        		proTypeAreaData.clear();
        		proTypeAreaData.refreshData({});
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	  
	};

	Model.prototype.massifBtnClick = function(event){
		var row = event.bindingContext.$object;
		var ptaId = row.val('ptaId');
		
		var src = require.toUrl('./massif.w');
		var windowDialog = this.comp("windowDialog");
		windowDialog.set({src:src});
		windowDialog.open({
			'data':{
				'ptaId':ptaId
			}
		});
	};

	return Model;
});