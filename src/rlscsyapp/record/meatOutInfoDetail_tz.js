define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.moibId="";
		this.total = 0;
	};

	//添加修改后返回刷新数据
	Model.prototype.windowDialogEditReceive = function(event){
		this.comp("meatOutInfoDetailData").refreshData();
	};
	
	//修改按钮
	Model.prototype.updateClick = function(event){
		var meatOutInfoDetailData = this.comp("meatOutInfoDetailData");
		var row = event.bindingContext.$object;								
		
		var moidId = meatOutInfoDetailData.getValue("moidId", row);
		var moibId = meatOutInfoDetailData.getValue("moibId", row);		
		var dest = meatOutInfoDetailData.getValue("dest", row);
		var tranId = meatOutInfoDetailData.getValue("tranId", row);
		
		var quarantineAnimalProductsId = meatOutInfoDetailData.getValue("quarantineAnimalProductsId", row);
		var inspectionMeatId = meatOutInfoDetailData.getValue("inspectionMeatId", row);
		var meatCode = meatOutInfoDetailData.getValue("meatCode", row);
		var meatName = meatOutInfoDetailData.getValue("meatName", row);
		var weight = meatOutInfoDetailData.getValue("weight", row);		
		var price = meatOutInfoDetailData.getValue("price", row);

		
		var data = {
			"moidId" : moidId,
			"comId" : comId,
			"moibId" : moibId,
			"meatCode" : meatCode,
			"quarantineAnimalProductsId" : quarantineAnimalProductsId,
			"inspectionMeatId" : inspectionMeatId,
			"meatName" : meatName,
			"weight" : weight,
			"price" : price,
			"dest" : dest,
			"tranId" : tranId
		};		
		this.comp("windowDialogEdit").open({"src":require.toUrl("./meatOutInfoDetail_tzedit.w"),"data":data});

	};


	//删除
	Model.prototype.delClick = function(event){
		var self = this;
		var meatOutInfoDetailData = self.comp("meatOutInfoDetailData");
		var row = event.bindingContext.$object;			
		var ids = meatOutInfoDetailData.getValue("moidId", row);	
		justep.Util.confirm("确认要删除吗?", function(){
			$.ajax({
		       	  url:'/rlscsy/meatOutInfoDetail_delete.action',
		       	  data:'ids='+ids,
		       	  type:'post',
		       	  dataType:'text',
		       	  async : false,
		       	  success : function(result) {
		       		justep.Util.hint(result);		
		       		meatOutInfoDetailData.refreshData();
				  }
		       }); 		
		});	
	};
	
	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
		return currentdate;
	}
	//新增
	Model.prototype.addClick = function(event){
		var data = {
			"moidId" : "",
			"moibId" : this.moibId,
			"dest" : "",
			"tranId" : "",
			"quarantineAnimalProductsId" : "",
			"inspectionMeatId" : "",
			"meatCode" : "",
			"meatName" : "",
			"weight" : "",
			"price" : ""
		};	
		this.comp("windowDialogEdit").open({"src":require.toUrl("./meatOutInfoDetail_tzedit.w"),"data":data});
	};
	
	Model.prototype.meatOutInfoDetailDataCustomRefresh = function(event){
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
	    var moibId = this.moibId;
	    queryParams['meatOutInfoDetail.moibId'] = parseInt(moibId);	   
	    queryParams["page"]=page;
	    queryParams["rows"]=limit;
	    queryParams["sort"]='moidId';
	    queryParams["order"]='desc';
	    
	    $.ajax({
	        type: "GET",
	        url: '/rlscsy/meatOutInfoDetail_findList.action',
	        data:queryParams,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	
	        	self.total = data.total;
	            masterData.loadData(data,true);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	    
	};
	
	//滚动加载下一页内容
	Model.prototype.scrollView1PullUp = function(event){
		this.comp('meatOutInfoDetailData').loadNextPageData();
		
	};
	
	Model.prototype.modelParamsReceive = function(event){
		var data = event.data.data;		
		this.moibId = data.moibId;
		this.comp("list1").refresh();
	};
	
	
	Model.prototype.windowDialogImgReceive = function(event){

	};
	
	
	return Model;
});