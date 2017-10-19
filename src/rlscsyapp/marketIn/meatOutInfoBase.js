define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.ptbId;
		this.flag = 2;
		this.moibId;
	};

	Model.prototype.scrollView1PullUp = function(event){
		this.comp('meatOutInfoBaseData').loadNextPageData();
	};

	Model.prototype.meatOutInfoBaseDataCustomRefresh = function(event){
			
		var self = this;
		var memberId = self.memberId;
		
	    var meatOutInfoBaseData = event.source;	    	    
	    var offset = meatOutInfoBaseData.offset;
	    var limit = meatOutInfoBaseData.limit;	    
	    var page=1;
	    
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	meatOutInfoBaseData.clear();
	    }
	    
	    var condition = {};
		condition['page']=page;
		condition['rows']=limit;
		condition['meatOutInfoBase.ptbId'] = self.ptbId;
		condition['meatOutInfoBase.flag'] = this.flag;
	    
	    $.ajax({
	        type: "post",
	        url: '/rlscsy/webmeatOutInfoBase_findList.action',
	        data:condition,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	self.total = data.total;
	            meatOutInfoBaseData.loadData(data.rows,true);//将返回的数据加载到data组件
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
			this.comp("meatOutInfoBaseData").refreshData();
			this.comp('list1').refresh();
		}
	};

	Model.prototype.updateClick = function(event){
		var row = event.bindingContext.$object;
		var data = row.row;
		var meatOutInfoBase = {};
		for(var item in data){
			meatOutInfoBase['meatOutInfoBase.' + item] = row.val(item);
		}
//		justep.Shell.showPage(require.toUrl('./editMeatOutInfoBase.w'),{'meatOutInfoBase':meatOutInfoBase,'option':'update'});
		this.comp("editwindowDialog").open({'src':require.toUrl('./editMeatOutInfoBase.w'),params:{'meatOutInfoBase':meatOutInfoBase,'option':'update'}});
	};

	Model.prototype.deleteClick = function(event){
		var self = this;
		var row = event.bindingContext.$object;
		this.moibId = row.val('moibId');
		
		this.comp("messageDialog1").show();
		
	};

	
	//添加基本信息
	Model.prototype.addPfBaseBtnClick = function(event){
		var company = localStorage.getItem("company");
		company = JSON.parse(company);
	
		var meatOutInfoBase = {'meatOutInfoBase.ptbId':this.ptbId,'meatOutInfoBase.flag':this.flag,'meatOutInfoBase.comId':company.comId};
//		justep.Shell.showPage(require.toUrl('./editMeatOutInfoBase.w'),{'meatOutInfoBase':meatOutInfoBase,'option':'add'});
		this.comp("editwindowDialog").open({'src':require.toUrl('./editMeatOutInfoBase.w'),params:{'meatOutInfoBase':meatOutInfoBase,'option':'add'}});
	};

	Model.prototype.modelActive = function(event){
		this.comp("meatOutInfoBaseData").refreshData();
		this.comp('list1').refresh();
	};

	Model.prototype.messageDialog1Yes = function(event){
		var self = this;
		$.ajax({
			url:'/rlscsy/webmeatOutInfoBase_delete.action',
			data:{'ids':this.moibId},
			type:'post',
			async:false,
			success:function(result){
				if(result){
					justep.Util.hint(result);
					
					self.comp("meatOutInfoBaseData").refreshData();
					self.comp('list1').refresh();
				}
			}
		});
	};


	Model.prototype.detailClick = function(event){
		var row = event.bindingContext.$object;
		var moibId = row.val('moibId');
		
		justep.Shell.showPage(require.toUrl('./meatOutInfoDetail.w'),{'moibId':moibId});
		
	};


	Model.prototype.editwindowDialogReceived = function(event){
		if(event.data && event.data.option=='refresh'){
			this.comp("meatOutInfoBaseData").refreshData();
		}
	};


	return Model;
});