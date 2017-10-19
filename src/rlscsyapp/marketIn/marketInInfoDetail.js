define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.miibId;
		this.flag = 2;
		this.miidId;
	};

	Model.prototype.scrollView1PullUp = function(event){
		this.comp('market_in_info_detail').loadNextPageData();
	};

	Model.prototype.market_in_info_detailCustomRefresh = function(event){
			
		var self = this;
		var memberId = self.memberId;
		
	    var market_in_info_detail = event.source;	    	    
	    var offset = market_in_info_detail.offset;
	    var limit = market_in_info_detail.limit;	    
	    var page=1;
	    
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	market_in_info_detail.clear();
	    }
	    
	    var condition = {};
		condition['page']=page;
		condition['rows']=limit;
		condition['marketInInfoDetail.miibId'] = self.miibId;
	    
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/webmarketInInfoDetail_findList.action',
	        data:condition,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	self.total = data.total;
	            market_in_info_detail.loadData(data.rows,true);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("加载数据失败");
	        }
	    });	    
	};

	Model.prototype.modelParamsReceive = function(event){
		var miibId = event.params.miibId;
		if(miibId){
			this.miibId = miibId;
			this.comp("market_in_info_detail").refreshData();
			this.comp('list1').refresh();
		}
	};

	Model.prototype.updateClick = function(event){
		var row = event.bindingContext.$object;
		var data = row.row;
		var marketInInfoDetail = {};
		for(var item in data){
			marketInInfoDetail['marketInInfoDetail.' + item] = row.val(item);
		}
//		justep.Shell.showPage(require.toUrl('./editMarketInDetail.w'),{'marketInInfoDetail':marketInInfoDetail,'option':'update'});
		this.comp("editwindowDialog").open({'src':require.toUrl('./editMarketInDetail.w'),params:{'marketInInfoDetail':marketInInfoDetail,'option':'update'}});
	};

	Model.prototype.deleteClick = function(event){
		var self = this;
		var row = event.bindingContext.$object;
		this.miidId = row.val('miidId');
		
		this.comp("messageDialog1").show();
		
	};

	
	//添加基本信息
	Model.prototype.addPfBaseBtnClick = function(event){
		var marketInInfoDetail = {'marketInInfoDetail.miibId':this.miibId};
//		justep.Shell.showPage(require.toUrl('./editMarketInDetail.w'),{'marketInInfoDetail':marketInInfoDetail,'option':'add'});
		this.comp("editwindowDialog").open({'src':require.toUrl('./editMarketInDetail.w'),params:{'marketInInfoDetail':marketInInfoDetail,'option':'add'}});
	};

	Model.prototype.modelActive = function(event){
		this.comp("market_in_info_detail").refreshData();
		this.comp('list1').refresh();
	};

	Model.prototype.messageDialog1Yes = function(event){
		var self = this;
		$.ajax({
			url:'/rlscsy/webmarketInInfoDetail_delete.action',
			data:{'ids':this.miidId},
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					
					self.comp("market_in_info_detail").refreshData();
					self.comp('list1').refresh();
				}
			}
		});
	};

	Model.prototype.editwindowDialogReceived = function(event){
		if(event.data && event.data.option=='refresh'){
			this.comp("market_in_info_detail").refreshData();
		}
	};

	return Model;
});