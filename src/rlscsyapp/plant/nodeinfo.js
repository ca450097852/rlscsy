define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.recId;
		
		this.niId;
		
		this.total=0;
	};
	
	Model.prototype.getImageUrl = function(url){
		if(url==null||url==''){
			url = "./images/tx.jpg";
		}else{
			url = "/nytsyFiles/element/"+url;
		}
		return require.toUrl(url);
	}

	Model.prototype.scrollView1PullUp = function(event){
		this.comp('nodeinfoData').loadNextPageData();
	};

	Model.prototype.nodeinfoDataCustomRefresh = function(event){
			
		var self = this;
		
	    var nodeinfoData = event.source;	    	    
	    var offset = nodeinfoData.offset;
	    var limit = nodeinfoData.limit;	    
	    var page=1;
	    
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	nodeinfoData.clear();
	    }
	    
	    var condition = {};
		condition['page']=page;
		condition['rows']=limit;
		condition['nodeinfo.recId'] = self.recId;
	    
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/nodeinfo_findNodeinfoList.action',
	        data:condition,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	self.total = data.total;
	            nodeinfoData.loadData(data,true);//将返回的数据加载到data组件
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
			this.comp("nodeinfoData").refreshData();
			this.comp('list1').refresh();
		}
	};

	Model.prototype.updateClick = function(event){
		var row = event.bindingContext.$object;
		var data = row.row;
		var nodeinfo = {};
		for(var item in data){
			nodeinfo['nodeinfo.' + item] = row.val(item);
		}
		
		this.comp("editwindowDialog").open({params:{'nodeinfo':nodeinfo,'option':'update'}});
	};

	Model.prototype.deleteClick = function(event){
		var self = this;
		var row = event.bindingContext.$object;
		this.niId = row.val('niId');
		
		this.comp("messageDialog1").show();
		
	};

	
	//添加基本信息
	Model.prototype.addPfBaseBtnClick = function(event){
		var nodeinfo = {'nodeinfo.recId':this.recId};
		
		this.comp("editwindowDialog").open({params:{'nodeinfo':nodeinfo,'option':'add'}});
	};

	Model.prototype.modelActive = function(event){
		this.comp("nodeinfoData").refreshData();
		this.comp('list1').refresh();
	};

	Model.prototype.messageDialog1Yes = function(event){
		var self = this;
		$.ajax({
			url:'/rlscsy/nodeinfo_deleteNodeinfo.action',
			data:{'ids':this.niId},
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					
					self.comp("nodeinfoData").refreshData();
					self.comp('list1').refresh();
				}
			}
		});
	};



	Model.prototype.editwindowDialogReceived = function(event){
		if(event.data && event.data.option=='refresh'){
			this.comp("nodeinfoData").refreshData();
			this.comp('list1').refresh();
		}
	};





	return Model;
});