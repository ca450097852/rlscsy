define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
	};
	

	Model.prototype.proTypeBatchCustomRefresh = function(event){	
		var proTypeBatchData = event.source;	
		var masterData = event.source;	   
		var company = localStorage.getItem("company");	      	    
	    var companyObj = JSON.parse(company);	
	    var entId = companyObj.comId;
		
		//获取登陆用户的企业ID
		$.ajax({
            type: "GET",
            url: '/rlscsy/proTypeBatch_findProTypeBatch.action',
            data:{rows:100,'proTypeBatch.entId':entId},
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

	Model.prototype.addBatchClick = function(event){
		this.comp("windowDialog").open({
			"data" : {
				"ptqId" : ""
			}
		});
		
	};

	Model.prototype.windowDialogClose = function(event){
		var proTypeBatch = this.comp("proTypeBatch");
		proTypeBatch.clear();
		proTypeBatch.refreshData();
	};
		
	Model.prototype.windowReceiverReceive = function(event){		
		var proTypeBatch = this.comp("proTypeBatch");
		proTypeBatch.clear();
		proTypeBatch.refreshData();
	};

	//批次档案，按节点显示
	Model.prototype.button2Click = function(event){
		var masterData = this.comp("proTypeBatch");
		var row = event.bindingContext.$object;					
		var ptbId = masterData.getValue("ptbId", row);
		justep.Shell.showPage(require.toUrl("../record/recordList.w"),{ptbId:ptbId});
	};

	return Model;
});