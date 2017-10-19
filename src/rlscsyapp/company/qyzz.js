define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		
		this.basezizhidata;
		
		this.total = "";
		this.thiscomId = "";
	};


	
	
	Model.prototype.zizhiDataCustomRefresh = function(event){
	    var self = this;
	    var zizhiData = event.source;	    	    
	    var offset = zizhiData.offset;
	    var limit = zizhiData.limit;	    
	    var page=1;
	    if(self.total<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	zizhiData.clear();
	    }
	    
	    var queryParams = {};
	    if(this.thiscomId){
	    	queryParams['zizhi.entId'] = this.thiscomId;
	    }
	    queryParams["page"]=page;
	    queryParams["rows"]=limit;
	    
	    $.ajax({
	        type: "GET",
	        url: '/rlscsy/zizhi_findZizhiPagerList.action',
	        data:queryParams,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){
	        	if(data.rows.length>0){
	        		self.basezizhidata = data;
	        	}
	        	self.total = data.total;
	            zizhiData.loadData(data,true);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	    
	};
	
	
	
	//证书-typeName
	Model.prototype.typeNameStr = function(value){
		var self = this;
		var ress = "";
		
		if(self.basezizhidata){
			var data = self.basezizhidata;
			var rows = data.rows;
			
			var id = "";
			var appType = "";
			var zizhiTypeList = {};
			for(var i=0;i<rows.length;i++){
				var zizhi = rows[i];
				if(zizhi){
					id = zizhi.id;
					appType = zizhi.appType;
					if(id==value){
						zizhiTypeList = zizhi.zizhiTypeList;
					}
				}
			}
			
			if(zizhiTypeList){
				for(var j=0;j<zizhiTypeList.length;j++){
					var zizhiType = zizhiTypeList[j];
					if(zizhiType){
						ress = zizhiType.typeName;
					}
				}
			}
		
		}
		return ress;
	}
	
	//证书-appendix--URL
	Model.prototype.appendixUrl = function(value){
		var imgstr ="";
		var self = this;
		if(self.basezizhidata){
			var data = self.basezizhidata;
			var rows = data.rows;
			
			var id = "";
			var appendixlist = {};
			for(var i=0;i<rows.length;i++){
				var zizhi = rows[i];
				if(zizhi){
					id = zizhi.id;
					if(id==value){
						appendixlist = zizhi.appendix;
					}
				}
			}
			
			if(appendixlist){
				var appendix = appendixlist[0];
				if(appendix){
					imgstr = "/nytsyFiles/zizhi/"+appendix.path;
				}
				/*for(var j=0;j<appendixlist.length;j++){
					var appendix = appendixlist[j];
					if(appendix){
						imgstr = "/nytsyFiles/zizhi/"+appendix.path;
					}
				}*/
			}
		
		}
		
		return require.toUrl(imgstr);
	}
	
	Model.prototype.getState = function(state){
		if(state==1||state==''){
			return "正常";
		}else if(state==2){
			return "申请中";
		}else if(state==3){
			return "同意修改";
		}
	}
	
	//修改按钮
	Model.prototype.updateClick = function(event){
		var self = this;
		
		var zizhiData = self.comp("zizhiData");
		var row = event.bindingContext.$object;								
		
		var id = zizhiData.getValue("id", row);
		var entId = this.thiscomId;
		var state = zizhiData.getValue("state", row);		
		var appType = zizhiData.getValue("appType", row);
		var zizhiName = zizhiData.getValue("zizhiName", row);
		var grantUnit = zizhiData.getValue("grantUnit", row);
		var awardUnit = zizhiData.getValue("awardUnit", row);
		var awardTime = zizhiData.getValue("awardTime", row);		
		var remark = zizhiData.getValue("remark", row);
		
				
		if(state==2){
			justep.Util.hint('正在申请修改或者删除中，请等待监管部门审核后再操作！');
			return;
		}else if(state==3){
			console.info("可以修改");
		}else{
			//需要填写申请
			var data = {
				"id" : id,
				"entId" : entId,
				"appType" : appType,
				"zizhiName" : zizhiName,
				"grantUnit" : grantUnit,
				"awardUnit" : awardUnit,
				"awardTime" : awardTime,
				"remark" : remark,
			};	
			this.comp("windowDialogEdit").open({"src":require.toUrl("./qyzzeditApply.w"),"data":data});							
			return;
		}
		
		var typeName = "";
		var zizhiTypeList = {};
		var appendixlist = {};
		if(self.basezizhidata){
			var data = self.basezizhidata;
			var rows = data.rows;
			var thisid = "";
			for(var i=0;i<rows.length;i++){
				var zizhi = rows[i];
				if(zizhi){
					thisid = zizhi.id;
					if(thisid==id){
						zizhiTypeList = zizhi.zizhiTypeList;
						appendixlist = zizhi.appendix;
					}
				}
			}
			
			if(zizhiTypeList){
				for(var j=0;j<zizhiTypeList.length;j++){
					var zizhiType = zizhiTypeList[j];
					if(zizhiType){
						typeName = zizhiType.typeName;
					}
				}
			}
		}
		
		
		var data = {
			"id" : id,
			"entId" : entId,
			"appType" : appType,
			"zizhiName" : zizhiName,
			"grantUnit" : grantUnit,
			"awardUnit" : awardUnit,
			"awardTime" : awardTime,
			"remark" : remark,
			//后面是辅助数据
			"typeName" : typeName,
			"zizhiTypeList" : zizhiTypeList,
			"appendixlist" : appendixlist
		};	
		this.comp("windowDialogEdit").open({"src":require.toUrl("./qyzzedit.w"),"data":data});

	};


	//删除
	Model.prototype.delClick = function(event){
		var self = this;
		var zizhiData = self.comp("zizhiData");
		var row = event.bindingContext.$object;			
		var ids = zizhiData.getValue("id", row);
		
		var id = zizhiData.getValue("id", row);
		var entId = this.thiscomId;
		var state = zizhiData.getValue("state", row);		
		var appType = zizhiData.getValue("appType", row);
		var zizhiName = zizhiData.getValue("zizhiName", row);
		var grantUnit = zizhiData.getValue("grantUnit", row);
		var awardUnit = zizhiData.getValue("awardUnit", row);
		var awardTime = zizhiData.getValue("awardTime", row);		
		var remark = zizhiData.getValue("remark", row);
		
/*		if(state==2){
			justep.Util.hint('正在申请修改或者删除中，请等待监管部门审核后再操作！');
			return;
		}else if(state==3){
			console.info("可以修改");
		}else{
			//需要填写申请
			var data = {
				"id" : id,
				"entId" : entId,
				"appType" : appType,
				"zizhiName" : zizhiName,
				"grantUnit" : grantUnit,
				"awardUnit" : awardUnit,
				"awardTime" : awardTime,
				"remark" : remark,
			};	
			this.comp("windowDialogEdit").open({"src":require.toUrl("./qyzzeditApply.w"),"data":data});							
			return;
		}
		*/
		
		justep.Util.confirm("确认要删除吗?", function(){
			$.ajax({
		       	  url:'/zqsy/zizhi_deleteZizhi.action',
		       	  data:'ids='+ids,
		       	  type:'post',
		       	  dataType:'text',
		       	  async : false,
		       	  success : function(result) {
		       		justep.Util.hint(result);		
		       		zizhiData.refreshData();
				  }
		       }); 		
		});	
	};
	
	
	//新增
	Model.prototype.addClick = function(event){
		var self = this;
		var data = {
			"id" : "",
			"entId" : self.thiscomId,
			"appType" : "",
			"zizhiName" : "",
			"grantUnit" : "",
			"awardUnit" : "",
			"awardTime" : "",
			"remark" : "",
			//后面是辅助数据
			"typeName" : "",
			"zizhiTypeList" : "",
			"appendixlist" : ""
		};	
		this.comp("windowDialogEdit").open({"src":require.toUrl("./qyzzedit.w"),"data":data});
	};
	
	
	
	//滚动加载下一页内容
	Model.prototype.scrollView1PullUp = function(event){
		this.comp('zizhiData').loadNextPageData();
		
	};
	
	
	
	Model.prototype.modelLoad = function(event){
	    var company = localStorage.getItem("company");
	    if(company){
	    	company = JSON.parse(company);
	    	this.thiscomId = company.comId;
	    }else{
	    	justep.Shell.showPage(require.toUrl('./index.w'));
	    }
	    
		this.comp('zizhiData').refreshData();
	};
	
	
	
	
	
	Model.prototype.windowDialogEditReceive = function(event){
		this.comp("zizhiData").refreshData();
	};
	
	
	
	
	
	Model.prototype.zizhiTypeDataCustomRefresh = function(event){
		
	};
	
	
	
	
	
	Model.prototype.windowDialogEditClose = function(event){
		this.comp("zizhiData").refreshData();
	};
	
	
	
	
	
	Model.prototype.windowDialogImgReceive = function(event){

	};
	
	
	
	
	
	Model.prototype.showClick = function(event){
		var self = this;
		var zizhiData = self.comp("zizhiData");
		var row = event.bindingContext.$object;			
		var value = zizhiData.getValue("id", row);
		
		var imgstr ="";
		if(this.basezizhidata){
			var data = self.basezizhidata;
			var rows = data.rows;
			
			var id = "";
			var appendixlist = {};
			for(var i=0;i<rows.length;i++){
				var zizhi = rows[i];
				if(zizhi){
					id = zizhi.id;
					if(id==value){
						appendixlist = zizhi.appendix;
					}
				}
			}
			
			if(appendixlist){
				var appendix = appendixlist[0];
				if(appendix){
					imgstr = "/nytsyFiles/zizhi/"+appendix.path;
				}
			}
		}
		var imgSrc = require.toUrl(imgstr);		
		this.comp("windowDialogImg").open({"src":require.toUrl("../record/imgshow.w"),"data":{"imgSrc":imgSrc}});
	};
	
	
	
	
	
	return Model;
});