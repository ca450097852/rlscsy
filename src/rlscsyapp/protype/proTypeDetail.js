define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.isBatch;
		this.ptqId;
	};
	

	Model.prototype.proTypeCustomRefresh = function(event){
	    var ptqData = event.source;
		var queryParams = {};
		queryParams['rows'] = 1000;
		var self = this;
        $.ajax({
            type: "GET",
            url: '/rlscsy/proTypeQrcode_getLoginProTypeQrcode.action',
            data:queryParams,
            dataType: 'json',
            async: false,
            cache: false,
            success: function(data){
            	self.ptqtotal = data.total;
            	ptqData.loadData(data,true);//将返回的数据加载到data组件
            },
            error: function(){
              throw justep.Error.create("请检查网络是否连接");
            }
        });
	};


	Model.prototype.updateBtClick = function(event){
//		var ptqId = this.comp("proTypeData").getValue("ptqId");
		var data = {"ptqId":"","certificate":"","quantity":"","listed":"","salearea":"","typeImg":""};
		
		var row = event.bindingContext.$object;
		
		for(var item in data){
			var v = row.val(item);
			if((v+'')=='NaN'){
				v = ''; 
			}
			data[item] = v;
		}
		
		var src = require.toUrl('./updateProType.w');
		this.comp("updateDialog").set({
			src:src
		})
		
		this.comp("updateDialog").open({
			"data" : {
				"rowData" : data
			}
		});
	};

	Model.prototype.updateDialogClose = function(event){
		//Model.prototype.proTypeCustomRefresh();
		this.comp("proTypeData").clear();
		this.comp("proTypeData").refreshData({});
	};

	Model.prototype.addProTypeClick = function(event){
		var src = require.toUrl('./proTypeTree.w');
		this.comp("updateDialog").set({
			src:src
		})
		
		this.comp("updateDialog").open();
	};

	Model.prototype.delbtnClick = function(event){
		var row = event.bindingContext.$object;
//		var ptqId = row.val('ptqId');
		this.ptqId = row.val('ptqId')
		var messageDialog = this.comp("messageDialog");
		messageDialog.show();

	};

	Model.prototype.batchbtn = function(event){
		var row = event.bindingContext.$object;
		var ptqId = row.val('ptqId');
		var src = require.toUrl('./proTypeBatch.w');
		this.comp("updateDialog").set({
			src:src
		})
		
		this.comp("updateDialog").open({
			"data" : {
				"ptqId" : ptqId
			}
		});
		
	};

	Model.prototype.messageDialogOK = function(event){
		var params = {'dels':this.ptqId};
		
		var proTypeData = this.comp("proTypeData");
		
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
	        		
	        		proTypeData.clear();
	        		proTypeData.refreshData();
	        	}
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	
	};

	return Model;
});