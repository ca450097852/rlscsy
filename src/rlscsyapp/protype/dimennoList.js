define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.ptbId;
	};
	
	//图片路径转换
	Model.prototype.getImageUrl = function(url){		
		return require.toUrl("/nytsyFiles/qrcode/"+url);
	};
	

	Model.prototype.modelParamsReceive = function(event){
		this.ptbId = event.params.ptbId;
		this.comp("proTypeBatch").refreshData();
	};
	
	

	Model.prototype.proTypeBatchCustomRefresh = function(event){	
		var proTypeBatchData = event.source;			
	    var ptbId = this.ptbId;		
	    
            	console.info("ptbId==="+ptbId);
	    
		$.ajax({
            type: "GET",
            url: '/rlscsy/proTypeBatch_findProTypeBatch.action',
            data:{'proTypeBatch.ptbId':ptbId},
            dataType: 'json',
            async: false,
            cache: false,
            success: function(result){
            	console.info(result);
            	if(result){
            		var rows = result.rows;        
            		proTypeBatchData.loadData(rows, true);           		 		          		
            	}            	            	
            },
            error: function(){
              throw justep.Error.create("请检查网络是否连接");
            }
        });	
	
	};
	
	

	return Model;
});