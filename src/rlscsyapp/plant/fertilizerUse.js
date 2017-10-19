define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.recId;
		this.fuid;
		this.total=0;
	};

	Model.prototype.scrollView1PullUp = function(event){
		this.comp('fertilizerUseData').loadNextPageData();
	};

	Model.prototype.fertilizerUseDataCustomRefresh = function(event){
			
		var self = this;
		
	    var fertilizerUseData = event.source;	    	    
	    var offset = fertilizerUseData.offset;
	    var limit = fertilizerUseData.limit;	    
	    var page=1;
	    
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	fertilizerUseData.clear();
	    }
	    
	    var condition = {};
		condition['page']=page;
		condition['rows']=limit;
		condition['fertilizerUse.recId'] = self.recId;
	    
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/fertilizeruse_findList.action',
	        data:condition,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	self.total = data.total;
	            fertilizerUseData.loadData(data,true);//将返回的数据加载到data组件
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
			this.comp("fertilizerUseData").refreshData();
			this.comp('list1').refresh();
		}
	};

	Model.prototype.updateClick = function(event){
		var row = event.bindingContext.$object;
		var data = row.row;
		var fertilizerUse = {};
		for(var item in data){
			fertilizerUse['fertilizerUse.' + item] = row.val(item);
		}
		
		this.comp("editwindowDialog").open({params:{'fertilizerUse':fertilizerUse,'option':'update'}});
	};

	Model.prototype.deleteClick = function(event){
		var self = this;
		var row = event.bindingContext.$object;
		this.fuid = row.val('fuid');
		
		this.comp("messageDialog1").show();
		
	};

	
	//添加基本信息
	Model.prototype.addPfBaseBtnClick = function(event){
		var fertilizerUse = {'fertilizerUse.recId':this.recId};
//		justep.Shell.showPage(require.toUrl('./editMarketInBase.w'),{'fertilizerUse':fertilizerUse,'option':'add'});
		
		this.comp("editwindowDialog").open({params:{'fertilizerUse':fertilizerUse,'option':'add'}});
	};

	Model.prototype.modelActive = function(event){
//		this.comp("fertilizerUseData").refreshData();
//		this.comp('list1').refresh();
	};

	Model.prototype.messageDialog1Yes = function(event){
		var self = this;
		$.ajax({
			url:'/rlscsy/fertilizeruse_deleteFertilizerUse.action',
			data:{'ids':this.fuid},
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					
					self.comp("fertilizerUseData").refreshData();
					self.comp('list1').refresh();
				}
			}
		});
	};



	Model.prototype.editwindowDialogReceived = function(event){
		if(event.data && event.data.option=='refresh'){
			this.comp("fertilizerUseData").refreshData();
			this.comp('list1').refresh();
		}
	};



	return Model;
});