define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
	};
	
	var ptaId;
	
	
	Model.prototype.massifDataCustomRefresh = function(event){
		if(!ptaId){
			return;
		}
		var massifData = event.source;
		var queryParams = {rows:1000,ptaId:ptaId};
		console.log(queryParams);
		$.ajax({
	        type: "POST",
	        url: '/rlscsy/massif_findMassifList.action',
	        data:queryParams,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	  
	        	if(data){
	        		var rows = data.rows;
	        		massifData.loadData(rows,true);
	        	}
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	  
	};
	
	Model.prototype.windowReceiverReceive = function(event){
		var data = event.data;
		ptaId = data.ptaId;
		this.comp("massifData").clear();
		this.comp("massifData").refreshData();
	};
	

	Model.prototype.windowDialogClose = function(event){
		this.comp("massifData").clear();
		this.comp("massifData").refreshData();
	};
	

	Model.prototype.addMissifClick = function(event){
		this.comp("windowDialog").open({
			'data':{
				'option':'add',
				'ptaId':ptaId
			}
		})
		
	};
	

	Model.prototype.updateMassifClick = function(event){
		var row = event.bindingContext.$object;
		this.comp("windowDialog").open({
			'data':{
				'option':'update',
				'row':row
			}
		})
	};
	
	var MAID;
	Model.prototype.deleteMassifClick = function(event){
		var row = event.bindingContext.$object;
		MAID = row.val('maId');
		this.comp("messageDialog").show();
		
	};
	

	Model.prototype.messageDialogOK = function(event){
		var massifData = this.comp("massifData");
		
		$.ajax({
	        type: "GET",
	        url: '/rlscsy/massif_deleteMassif.action',
	        data:{ids:MAID},
	        dataType: 'text',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
        		justep.Util.hint(data);
        		massifData.clear();
        		massifData.refreshData({});
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	  
	};
	

	return Model;
});