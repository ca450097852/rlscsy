define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.recId;
		
		this.saleId;
		
		this.total=0;
	};

	Model.prototype.scrollView1PullUp = function(event){
		this.comp('saleinfoData').loadNextPageData();
	};

	Model.prototype.saleinfoDataCustomRefresh = function(event){
			
		var self = this;
		
	    var saleinfoData = event.source;	    	    
	    var offset = saleinfoData.offset;
	    var limit = saleinfoData.limit;	    
	    var page=1;
	    
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	saleinfoData.clear();
	    }
	    
	    var condition = {};
		condition['page']=page;
		condition['rows']=limit;
		condition['saleinfo.recId'] = self.recId;
	    
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/saleinfo_findSaleinfoList.action',
	        data:condition,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	self.total = data.total;
	            saleinfoData.loadData(data,true);//将返回的数据加载到data组件
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
			this.comp("saleinfoData").refreshData();
			this.comp('list1').refresh();
		}
	};

	Model.prototype.updateClick = function(event){
		var row = event.bindingContext.$object;
		var data = row.row;
		var saleinfo = {};
		for(var item in data){
			saleinfo['saleinfo.' + item] = row.val(item);
		}
		
		this.comp("editwindowDialog").open({params:{'saleinfo':saleinfo,'option':'update'}});
	};

	Model.prototype.deleteClick = function(event){
		var self = this;
		var row = event.bindingContext.$object;
		this.saleId = row.val('saleId');
		
		this.comp("messageDialog1").show();
		
	};

	
	//添加基本信息
	Model.prototype.addPfBaseBtnClick = function(event){
		var saleinfo = {'saleinfo.recId':this.recId};
		
		this.comp("editwindowDialog").open({params:{'saleinfo':saleinfo,'option':'add'}});
	};

	Model.prototype.modelActive = function(event){
		this.comp("saleinfoData").refreshData();
		this.comp('list1').refresh();
	};

	Model.prototype.messageDialog1Yes = function(event){
		var self = this;
		$.ajax({
			url:'/rlscsy/saleinfo_deleteSaleinfo.action',
			data:{'ids':this.saleId},
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					
					self.comp("saleinfoData").refreshData();
					self.comp('list1').refresh();
				}
			}
		});
	};



	Model.prototype.editwindowDialogReceived = function(event){
		if(event.data && event.data.option=='refresh'){
			this.comp("saleinfoData").refreshData();
			this.comp('list1').refresh();
		}
	};



	return Model;
});