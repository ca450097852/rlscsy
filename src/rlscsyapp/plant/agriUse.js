define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.recId;
		
		this.archId;
		
		this.total=0;
	};

	Model.prototype.scrollView1PullUp = function(event){
		this.comp('agriUseData').loadNextPageData();
	};

	Model.prototype.agriUseDataCustomRefresh = function(event){
			
		var self = this;
		
	    var agriUseData = event.source;	    	    
	    var offset = agriUseData.offset;
	    var limit = agriUseData.limit;	    
	    var page=1;
	    
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	agriUseData.clear();
	    }
	    
	    var condition = {};
		condition['page']=page;
		condition['rows']=limit;
		condition['agriUse.recId'] = self.recId;
	    
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/agriUse_findAgriUseList.action',
	        data:condition,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	self.total = data.total;
	            agriUseData.loadData(data,true);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("加载数据失败");
	        }
	    });	    
	};

	Model.prototype.modelParamsReceive = function(event){
		var recId = event.params.ptbId;
		if(recId){
			this.recId = recId;
			this.comp("agriUseData").refreshData();
			this.comp('list1').refresh();
		}
	};

	Model.prototype.updateClick = function(event){
		var row = event.bindingContext.$object;
		var data = row.row;
		var agriUse = {};
		for(var item in data){
			agriUse['agriUse.' + item] = row.val(item);
		}
		
		this.comp("editwindowDialog").open({params:{'agriUse':agriUse,'option':'update'}});
	};

	Model.prototype.deleteClick = function(event){
		var self = this;
		var row = event.bindingContext.$object;
		this.archId = row.val('archId');
		
		this.comp("messageDialog1").show();
		
	};

	
	//添加基本信息
	Model.prototype.addPfBaseBtnClick = function(event){
		var agriUse = {'agriUse.recId':this.recId};
//		justep.Shell.showPage(require.toUrl('./editMarketInBase.w'),{'agriUse':agriUse,'option':'add'});
		
		this.comp("editwindowDialog").open({params:{'agriUse':agriUse,'option':'add'}});
	};

	Model.prototype.modelActive = function(event){
		this.comp("agriUseData").refreshData();
		this.comp('list1').refresh();
	};

	Model.prototype.messageDialog1Yes = function(event){
		var self = this;
		$.ajax({
			url:'/rlscsy/agriUse_deleteAgriUse.action',
			data:{'ids':this.archId},
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					
					self.comp("agriUseData").refreshData();
					self.comp('list1').refresh();
				}
			}
		});
	};



	Model.prototype.editwindowDialogReceived = function(event){
		if(event.data && event.data.option=='refresh'){
			this.comp("agriUseData").refreshData();
			this.comp('list1').refresh();
		}
	};



	return Model;
});