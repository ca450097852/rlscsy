define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.recId="";
		this.total = 0;
	};

	Model.prototype.getcheckType = function(c){
		var checkType = c==1?"第三方检测报告":"企业自检报告";
		return checkType;
	};
	
	//添加修改后返回刷新数据
	Model.prototype.windowDialogEditReceive = function(event){
		this.comp("masterData").refreshData();
	};
	
	//修改按钮
	Model.prototype.updateClick = function(event){
		var masterData = this.comp("masterData");
		var row = event.bindingContext.$object;								
		
		var checkId = masterData.getValue("checkId", row);
		var recId = masterData.getValue("recId", row);
		
		var checkName = masterData.getValue("checkName", row);
		var checkNum = masterData.getValue("checkNum", row);
		var checkUnit = masterData.getValue("checkUnit", row);
		var checkTime = masterData.getValue("checkTime", row);
		var checkResult = masterData.getValue("checkResult", row);
		var checkType = masterData.getValue("checkType", row);		
		
		var data = {
			"checkId" : checkId,
			"checkName" : checkName,
			"recId" : recId,
			"checkTime" : checkTime,
			"checkNum" : checkNum,
			"checkResult" : checkResult,
			"checkType" : checkType,
			"checkUnit" : checkUnit
		};		
		this.comp("windowDialogEdit").open({"src":require.toUrl("./checkinfoedit.w"),"data":data});

	};
	
	//新增
	Model.prototype.addClick = function(event){
		var data = {
			"checkId" : "",
			"checkName" : "",
			"recId" : this.recId,
			"crttime" : "",
			"checkTime" : "",
			"checkNum" : "",
			"checkResult" : "",
			"checkType" : "",
			"saleUser" : "",
			"checkUnit" : ""
		};	
		this.comp("windowDialogEdit").open({"src":require.toUrl("./checkinfoedit.w"),"data":data});
	};
	

	//删除
	Model.prototype.delClick = function(event){
		var self = this;
		var masterData = self.comp("masterData");
		var row = event.bindingContext.$object;			
		var ids = masterData.getValue("checkId", row);
		
		justep.Util.confirm("确认要删除吗?", function(){
			$.ajax({
		       	  url:'/rlscsy/checkinfo_deleteCheckinfo.action',
		       	  data:'ids='+ids,
		       	  type:'post',
		       	  dataType:'text',
		       	  async : false,
		       	  success : function(result) {
		       		justep.Util.hint(result);		
		       		masterData.refreshData();
				  }
		       }); 		
		});	
	};
	
	Model.prototype.masterDataCustomRefresh = function(event){
	    var masterData = event.source;	    	    
	    var offset = masterData.offset;
	    var limit = masterData.limit;	    
	    var page=1;
	    var self = this;
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	masterData.clear();
	    }
	    
	    var queryParams = {};   
	    var recId = this.recId;
	    queryParams['checkinfo.recId'] = parseInt(recId);	   
	    queryParams["page"]=page;
	    queryParams["rows"]=limit;
	    queryParams["sort"]='checkTime';
	    queryParams["order"]='desc';
	    
	    $.ajax({
	        type: "GET",
	        url: '/rlscsy/checkinfo_findCheckinfoList.action',
	        data:queryParams,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	
	        	self.total = data.total;
	            masterData.loadData(data,true);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("加载数据失败");
	        }
	    });	    
	};
	
	//滚动加载下一页内容
	Model.prototype.scrollView1PullUp = function(event){
		this.comp('masterData').loadNextPageData();
		
	};
	
	Model.prototype.modelParamsReceive = function(event){
		var recId = event.params.ptbId;
		if(recId){
			this.recId = recId;
			this.comp("masterData").refreshData();
			this.comp('list1').refresh();
		}
		
	};

	Model.prototype.buttonShowReportClick = function(event){
		var masterData = this.comp("masterData");
		var row = event.bindingContext.$object;										
		var checkId = masterData.getValue("checkId", row);
		
		this.comp("windowDialogEdit").open({"src":require.toUrl("./checkinfofile.w"),"data":{"checkId":checkId,"appType":1}});

	};

	return Model;
});