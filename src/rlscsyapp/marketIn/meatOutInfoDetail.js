define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.moidId;
		this.flag = 2;
		this.moibId;
	};

	Model.prototype.scrollView1PullUp = function(event){
		this.comp('meatOutInfoDetailData').loadNextPageData();
	};

	Model.prototype.meatOutInfoDetailDataCustomRefresh = function(event){
			
		var self = this;
		var memberId = self.memberId;
		
	    var meatOutInfoDetailData = event.source;	    	    
	    var offset = meatOutInfoDetailData.offset;
	    var limit = meatOutInfoDetailData.limit;	    
	    var page=1;
	    
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	meatOutInfoDetailData.clear();
	    }
	    
	    var condition = {};
		condition['page']=page;
		condition['rows']=limit;
		condition['meatOutInfoDetail.moibId'] = self.moibId;
	    
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/webmeatOutInfoDetail_findList.action',
	        data:condition,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	self.total = data.total;
	            meatOutInfoDetailData.loadData(data.rows,true);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("加载数据失败");
	        }
	    });	    
	};

	Model.prototype.modelParamsReceive = function(event){
		var moibId = event.params.moibId;
		if(moibId){
			this.moibId = moibId;
			this.comp("meatOutInfoDetailData").refreshData();
			this.comp('list1').refresh();
		}
	};

	Model.prototype.updateClick = function(event){
		var row = event.bindingContext.$object;
		var data = row.row;
		var meatOutInfoDetail = {};
		for(var item in data){
			meatOutInfoDetail['meatOutInfoDetail.' + item] = row.val(item);
		}
//		justep.Shell.showPage(require.toUrl('./editMeatOutInfoDetail.w'),{'meatOutInfoDetail':meatOutInfoDetail,'option':'update'});
		this.comp("editwindowDialog").open({'src':require.toUrl('./editMeatOutInfoDetail.w'),params:{'meatOutInfoDetail':meatOutInfoDetail,'option':'update'}});
	};

	Model.prototype.deleteClick = function(event){
		var self = this;
		var row = event.bindingContext.$object;
		this.moidId = row.val('moidId');
		
		this.comp("messageDialog1").show();
		
	};

	
	//添加基本信息
	Model.prototype.addPfBaseBtnClick = function(event){
		var meatOutInfoDetail = {'meatOutInfoDetail.moibId':this.moibId};
//		justep.Shell.showPage(require.toUrl('./editMeatOutInfoDetail.w'),{'meatOutInfoDetail':meatOutInfoDetail,'option':'add'});
		this.comp("editwindowDialog").open({'src':require.toUrl('./editMeatOutInfoDetail.w'),params:{'meatOutInfoDetail':meatOutInfoDetail,'option':'add'}});
	};

	Model.prototype.modelActive = function(event){
		this.comp("meatOutInfoDetailData").refreshData();
		this.comp('list1').refresh();
	};

	Model.prototype.messageDialog1Yes = function(event){
		var self = this;
		$.ajax({
			url:'/rlscsy/webmeatOutInfoDetail_delete.action',
			data:{'ids':this.moidId},
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					
					self.comp("meatOutInfoDetailData").refreshData();
					self.comp('list1').refresh();
				}
			}
		});
	};


	Model.prototype.detailClick = function(event){
		var row = event.bindingContext.$object;
		var moibId = row.val('moibId');
		
	};


	Model.prototype.editwindowDialogReceived = function(event){
		if(event.data && event.data.option=='refresh'){
			this.comp("meatOutInfoDetailData").refreshData();
		}
	};


	return Model;
});