define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.ptbId;
		this.flag = 2;
		this.miibId;
	};

	Model.prototype.scrollView1PullUp = function(event){
		this.comp('market_in_info_base').loadNextPageData();
	};

	Model.prototype.market_in_info_baseCustomRefresh = function(event){
			
		var self = this;
		var memberId = self.memberId;
		
	    var market_in_info_base = event.source;	    	    
	    var offset = market_in_info_base.offset;
	    var limit = market_in_info_base.limit;	    
	    var page=1;
	    
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	market_in_info_base.clear();
	    }
	    
	    var condition = {};
		condition['page']=page;
		condition['rows']=limit;
		condition['marketInInfoBase.ptbId'] = self.ptbId;
		condition['marketInInfoBase.flag'] = this.flag;
	    
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/webmarketInInfoBase_findList.action',
	        data:condition,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	self.total = data.total;
	            market_in_info_base.loadData(data.rows,true);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("加载数据失败");
	        }
	    });	    
	};

	Model.prototype.modelParamsReceive = function(event){
		var ptbId = event.params.ptbId;
		var flag = event.params.flag;
//		ptbId = 86;
		if(ptbId){
			if(!flag){
				flag = 2;
			}
		
			this.ptbId = ptbId;
			this.flag = flag;
			this.comp("market_in_info_base").refreshData();
			this.comp('list1').refresh();
			
			var title = '';
			if(flag==2){
				title = '批发市场进场基本信息';
			}else if(flag==3){
				title = '零售市场进场基本信息';
			}else if(flag==4){
				title = '超市进场基本信息';
			}
			this.comp("marketTitle").set({"title" : title});
		}
	};

	Model.prototype.updateClick = function(event){
		var row = event.bindingContext.$object;
		var data = row.row;
		var marketInInfoBase = {};
		for(var item in data){
			marketInInfoBase['marketInInfoBase.' + item] = row.val(item);
		}
//		justep.Shell.showPage(require.toUrl('./editMarketInBase.w'),{'marketInInfoBase':marketInInfoBase,'option':'update'});
		
		this.comp("editwindowDialog").open({params:{'marketInInfoBase':marketInInfoBase,'option':'update'}});
	};

	Model.prototype.deleteClick = function(event){
		var self = this;
		var row = event.bindingContext.$object;
		this.miibId = row.val('miibId');
		
		this.comp("messageDialog1").show();
		
	};

	Model.prototype.detailClick = function(event){
		var row = event.bindingContext.$object;
		justep.Shell.showPage(require.toUrl('./marketInInfoDetail.w'),{'miibId':row.val('miibId')});
	};
	
	//添加基本信息
	Model.prototype.addPfBaseBtnClick = function(event){
		var marketInInfoBase = {'marketInInfoBase.ptbId':this.ptbId,'marketInInfoBase.flag':this.flag};
//		justep.Shell.showPage(require.toUrl('./editMarketInBase.w'),{'marketInInfoBase':marketInInfoBase,'option':'add'});
		
		this.comp("editwindowDialog").open({params:{'marketInInfoBase':marketInInfoBase,'option':'add'}});
	};

	Model.prototype.modelActive = function(event){
		this.comp("market_in_info_base").refreshData();
		this.comp('list1').refresh();
	};

	Model.prototype.messageDialog1Yes = function(event){
		var self = this;
		$.ajax({
			url:'/rlscsy/webmarketInInfoBase_delete.action',
			data:{'ids':this.miibId},
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					
					self.comp("market_in_info_base").refreshData();
				}
			}
		});
	};



	Model.prototype.editwindowDialogReceived = function(event){
		if(event.data && event.data.option=='refresh'){
			this.comp("market_in_info_base").refreshData();
		}
	};



	return Model;
});