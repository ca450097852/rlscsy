define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.ptbId="";
		this.total = 0;
	};

	Model.prototype.getImageHTML = function(img){
		if(img&&img!=""){
			img='<img src="/nytsyFiles/element/'+img+'" height="30px"  width="30px"/>';
		}else{
			img="无";
		}
		return img;
	};

	//添加修改后返回刷新数据
	Model.prototype.windowDialogEditReceive = function(event){
		this.comp("meatOutInfoBaseData").refreshData();
	};
	
	//修改按钮
	Model.prototype.updateClick = function(event){
		var meatOutInfoBaseData = this.comp("meatOutInfoBaseData");
		var row = event.bindingContext.$object;								
		
		var moibId = meatOutInfoBaseData.getValue("moibId", row);
		var ptbId = meatOutInfoBaseData.getValue("ptbId", row);		
		var comId = meatOutInfoBaseData.getValue("comId", row);
		var butcherFacId = meatOutInfoBaseData.getValue("butcherFacId", row);
		var butcherFacName = meatOutInfoBaseData.getValue("butcherFacName", row);
		var tranDate = meatOutInfoBaseData.getValue("tranDate", row);
		var sellerId = meatOutInfoBaseData.getValue("sellerId", row);
		var sellerName = meatOutInfoBaseData.getValue("sellerName", row);		
		var buyerId = meatOutInfoBaseData.getValue("buyerId", row);
		var buyerName = meatOutInfoBaseData.getValue("buyerName", row);
		var dest = meatOutInfoBaseData.getValue("dest", row);
		var tranId = meatOutInfoBaseData.getValue("tranId", row);
		
		var data = {
			"moibId" : moibId,
			"comId" : comId,
			"ptbId" : ptbId,
			"tranDate" : tranDate,
			"butcherFacId" : butcherFacId,
			"butcherFacName" : butcherFacName,
			"sellerId" : sellerId,
			"sellerName" : sellerName,
			"buyerId" : buyerId,
			"buyerName" : buyerName,
			"dest" : dest,
			"tranId" : tranId
		};		
		this.comp("windowDialogEdit").open({"src":require.toUrl("./meatOutInfoBase_tzedit.w"),"data":data});

	};


	//删除
	Model.prototype.delClick = function(event){
		var self = this;
		var meatOutInfoBaseData = self.comp("meatOutInfoBaseData");
		var row = event.bindingContext.$object;			
		var ids = meatOutInfoBaseData.getValue("moibId", row);	
		justep.Util.confirm("确认要删除吗?", function(){
			$.ajax({
		       	  url:'/rlscsy/meatOutInfoBase_delete.action',
		       	  data:'ids='+ids,
		       	  type:'post',
		       	  dataType:'text',
		       	  async : false,
		       	  success : function(result) {
		       		justep.Util.hint(result);		
		       		meatOutInfoBaseData.refreshData();
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
	
		var company = localStorage.getItem("company");	      	    
	    var companyObj = JSON.parse(company);	
	    
	    var node = localStorage.getItem("node");	      	    
	    var nodeObj = JSON.parse(node);	  

		var comId = companyObj.comId;
		var butcherFacId = nodeObj.nodeCode;
		var butcherFacName = nodeObj.name;
		var sellerId = companyObj.comCode;
		var sellerName = companyObj.name;		
		var tranDate = getNowFormatDate();

		var data = {
			"moibId" : "",
			"comId" : comId,
			"ptbId" : this.ptbId,
			"dest" : "",
			"tranId" : "",
			"tranDate" : tranDate,
			"butcherFacId" : butcherFacId,
			"butcherFacName" : butcherFacName,
			"sellerId" : sellerId,
			"sellerName" : sellerName,
			"buyerId" : "",
			"buyerName" : "",
			"tranId" : ""
		};	
		this.comp("windowDialogEdit").open({"src":require.toUrl("./meatOutInfoBase_tzedit.w"),"data":data});
	};
	
	Model.prototype.meatOutInfoBaseDataCustomRefresh = function(event){
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
	    var ptbId = this.ptbId;
	    queryParams['meatOutInfoBase.ptbId'] = parseInt(ptbId);	   
	    queryParams["page"]=page;
	    queryParams["rows"]=limit;
	    queryParams["sort"]='moibId';
	    queryParams["order"]='desc';
	    
	    $.ajax({
	        type: "GET",
	        url: '/rlscsy/meatOutInfoBase_findList.action',
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
		this.comp('meatOutInfoBaseData').loadNextPageData();
		
	};
	
	Model.prototype.modelParamsReceive = function(event){
		this.ptbId = event.params.ptbId;
		this.comp("list1").refresh();
	};
	
	
	Model.prototype.windowDialogImgReceive = function(event){

	};
	
	
	Model.prototype.detailClick = function(event){

		var meatOutInfoBaseData = this.comp("meatOutInfoBaseData");
		var row = event.bindingContext.$object;								
		
		var moibId = meatOutInfoBaseData.getValue("moibId", row);
		var ptbId = meatOutInfoBaseData.getValue("ptbId", row);		
		var comId = meatOutInfoBaseData.getValue("comId", row);
		var butcherFacId = meatOutInfoBaseData.getValue("butcherFacId", row);
		var butcherFacName = meatOutInfoBaseData.getValue("butcherFacName", row);
		var tranDate = meatOutInfoBaseData.getValue("tranDate", row);
		var sellerId = meatOutInfoBaseData.getValue("sellerId", row);
		var sellerName = meatOutInfoBaseData.getValue("sellerName", row);		
		var buyerId = meatOutInfoBaseData.getValue("buyerId", row);
		var buyerName = meatOutInfoBaseData.getValue("buyerName", row);
		var dest = meatOutInfoBaseData.getValue("dest", row);
		var tranId = meatOutInfoBaseData.getValue("tranId", row);
		
		var data = {
			"moibId" : moibId,
			"comId" : comId,
			"ptbId" : ptbId,
			"tranDate" : tranDate,
			"butcherFacId" : butcherFacId,
			"butcherFacName" : butcherFacName,
			"sellerId" : sellerId,
			"sellerName" : sellerName,
			"buyerId" : buyerId,
			"buyerName" : buyerName,
			"dest" : dest,
			"tranId" : tranId
		};		
		this.comp("windowDialogEdit").open({"src":require.toUrl("./meatOutInfoDetail_tz.w"),"data":data});

	
	};
	
	
	return Model;
});