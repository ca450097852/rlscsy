define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.ptbId;
		this.flag = 2;
		this.qdiId;
	};

	Model.prototype.scrollView1PullUp = function(event){
		this.comp('qmarket_detection_info').loadNextPageData();
	};

	Model.prototype.qmarket_detection_infoCustomRefresh = function(event){
			
		var self = this;
		var memberId = self.memberId;
		
	    var qmarket_detection_info = event.source;	    	    
	    var offset = qmarket_detection_info.offset;
	    var limit = qmarket_detection_info.limit;	    
	    var page=1;
	    
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	qmarket_detection_info.clear();
	    }
	    
	    var condition = {};
		condition['page']=page;
		condition['rows']=limit;
		condition['qmarketDetectionInfo.ptbId'] = self.ptbId;
	    
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/webqmarketDetectionInfo_findList.action',
	        data:condition,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	self.total = data.total;
	            qmarket_detection_info.loadData(data.rows,true);//将返回的数据加载到data组件
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
			this.comp("qmarket_detection_info").refreshData();
			this.comp('list1').refresh();
		}
	};

	Model.prototype.updateClick = function(event){
		var row = event.bindingContext.$object;
		var data = row.row;
		var qmarketDetectionInfo = {};
		for(var item in data){
			qmarketDetectionInfo['qmarketDetectionInfo.' + item] = row.val(item);
		}
//		justep.Shell.showPage(require.toUrl('./editQmarketDetectionInfo.w'),{'qmarketDetectionInfo':qmarketDetectionInfo,'option':'update'});
		
		this.comp("editwindowDialog").open({'src':require.toUrl('./editQmarketDetectionInfo.w'),params:{'qmarketDetectionInfo':qmarketDetectionInfo,'option':'update'}});
	};

	Model.prototype.deleteClick = function(event){
		var self = this;
		var row = event.bindingContext.$object;
		this.qdiId = row.val('qdiId');
		
		this.comp("messageDialog1").show();
		
	};

	
	//添加基本信息
	Model.prototype.addPfBaseBtnClick = function(event){
		var company = localStorage.getItem("company");
		company = JSON.parse(company);
	
		var qmarketDetectionInfo = {'qmarketDetectionInfo.ptbId':this.ptbId,'qmarketDetectionInfo.comId':company.comId};
//		justep.Shell.showPage(require.toUrl('./editQmarketDetectionInfo.w'),{'qmarketDetectionInfo':qmarketDetectionInfo,'option':'add'});
		this.comp("editwindowDialog").open({'src':require.toUrl('./editQmarketDetectionInfo.w'),params:{'qmarketDetectionInfo':qmarketDetectionInfo,'option':'add'}});
	};

	Model.prototype.modelActive = function(event){
		this.comp("qmarket_detection_info").refreshData();
		this.comp('list1').refresh();
	};

	Model.prototype.messageDialog1Yes = function(event){
		var self = this;
		$.ajax({
			url:'/rlscsy/webqmarketDetectionInfo_delete.action',
			data:{'ids':this.qdiId},
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					
					self.comp("qmarket_detection_info").refreshData();
					self.comp('list1').refresh();
				}
			}
		});
	};

	Model.prototype.editwindowDialogReceived = function(event){
		if(event.data && event.data.option=='refresh'){
			this.comp("qmarket_detection_info").refreshData();
		}
	};

	return Model;
});