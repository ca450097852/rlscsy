define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.ptbId;
		this.flag = 2;
		this.rmiisId;
	};

	Model.prototype.scrollView1PullUp = function(event){
		this.comp('retailMarketTranInfoSummData').loadNextPageData();
	};

	Model.prototype.retailMarketTranInfoSummDataCustomRefresh = function(event){
			
		var self = this;
		var memberId = self.memberId;
		
	    var retailMarketTranInfoSummData = event.source;	    	    
	    var offset = retailMarketTranInfoSummData.offset;
	    var limit = retailMarketTranInfoSummData.limit;	    
	    var page=1;
	    
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	retailMarketTranInfoSummData.clear();
	    }
	    
	    var condition = {};
		condition['page']=page;
		condition['rows']=limit;
		condition['retailMarketTranInfoSumm.ptbId'] = self.ptbId;
	    
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/webretailMarketTranInfoSumm_findList.action',
	        data:condition,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	self.total = data.total;
	            retailMarketTranInfoSummData.loadData(data.rows,true);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("加载数据失败");
	        }
	    });	    
	};

	Model.prototype.modelParamsReceive = function(event){
		var ptbId = event.params.ptbId;
//		ptbId = 86;
		if(ptbId){
			this.ptbId = ptbId;
			this.comp("retailMarketTranInfoSummData").refreshData();
			this.comp('list1').refresh();
		}
	};

	Model.prototype.updateClick = function(event){
		var row = event.bindingContext.$object;
		var data = row.row;
		var retailMarketTranInfoSumm = {};
		for(var item in data){
			retailMarketTranInfoSumm['retailMarketTranInfoSumm.' + item] = row.val(item);
		}
//		justep.Shell.showPage(require.toUrl('./editRetailMarketTranInfoSumm.w'),{'retailMarketTranInfoSumm':retailMarketTranInfoSumm,'option':'update'});
		this.comp("editwindowDialog").open({'src':require.toUrl('./editRetailMarketTranInfoSumm.w'),params:{'retailMarketTranInfoSumm':retailMarketTranInfoSumm,'option':'update'}});
	};

	Model.prototype.deleteClick = function(event){
		var self = this;
		var row = event.bindingContext.$object;
		this.rmiisId = row.val('rmiisId');
		
		this.comp("messageDialog1").show();
		
	};

	
	//添加基本信息
	Model.prototype.addPfBaseBtnClick = function(event){
		var retailMarketTranInfoSumm = {'retailMarketTranInfoSumm.ptbId':this.ptbId};
//		justep.Shell.showPage(require.toUrl('./editRetailMarketTranInfoSumm.w'),{'retailMarketTranInfoSumm':retailMarketTranInfoSumm,'option':'add'});
		this.comp("editwindowDialog").open({'src':require.toUrl('./editRetailMarketTranInfoSumm.w'),params:{'retailMarketTranInfoSumm':retailMarketTranInfoSumm,'option':'add'}});
	};

	Model.prototype.modelActive = function(event){
		this.comp("retailMarketTranInfoSummData").refreshData();
		this.comp('list1').refresh();
	};

	Model.prototype.messageDialog1Yes = function(event){
		var self = this;
		$.ajax({
			url:'/rlscsy/webretailMarketTranInfoSumm_delete.action',
			data:{'ids':this.rmiisId},
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					
					self.comp("retailMarketTranInfoSummData").refreshData();
					self.comp('list1').refresh();
				}
			}
		});
	};


	Model.prototype.editwindowDialogReceived = function(event){
		if(event.data && event.data.option=='refresh'){
			this.comp("retailMarketTranInfoSummData").refreshData();
		}
	};


	return Model;
});