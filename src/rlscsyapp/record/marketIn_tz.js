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
		this.comp("marketIn_tzData").refreshData();
	};
	
	//修改按钮
	Model.prototype.updateClick = function(event){
		var marketIn_tzData = this.comp("marketIn_tzData");
		var row = event.bindingContext.$object;								
		
		var vrId = marketIn_tzData.getValue("vrId", row);
		var ptbId = marketIn_tzData.getValue("ptbId", row);		
		var comId = marketIn_tzData.getValue("comId", row);
		var butcherFacId = marketIn_tzData.getValue("butcherFacId", row);
		var butcherFacName = marketIn_tzData.getValue("butcherFacName", row);
		var inDate = marketIn_tzData.getValue("inDate", row);
		var sellerId = marketIn_tzData.getValue("sellerId", row);
		var sellerName = marketIn_tzData.getValue("sellerName", row);		
		var quarantineId = marketIn_tzData.getValue("quarantineId", row);
		var quarantineNum = marketIn_tzData.getValue("quarantineNum", row);
		var price = marketIn_tzData.getValue("price", row);
		var amount = marketIn_tzData.getValue("amount", row);
		
		var deadNum = marketIn_tzData.getValue("deadNum", row);
		var checkResult = marketIn_tzData.getValue("checkResult", row);
		var areaOriginId = marketIn_tzData.getValue("areaOriginId", row);
		var areaOriginName = marketIn_tzData.getValue("areaOriginName", row);
		var farmName = marketIn_tzData.getValue("farmName", row);
		var transporterId = marketIn_tzData.getValue("transporterId", row);
		
		
		var data = {
			"vrId" : vrId,
			"comId" : comId,
			"ptbId" : ptbId,
			"inDate" : inDate,
			"butcherFacId" : butcherFacId,
			"butcherFacName" : butcherFacName,
			"sellerId" : sellerId,
			"sellerName" : sellerName,
			"quarantineId" : quarantineId,
			"quarantineNum" : quarantineNum,
			"price" : price,
			"amount" : amount,
			"deadNum" : deadNum,
			"checkResult" : checkResult,
			"areaOriginId" : areaOriginId,
			"areaOriginName" : areaOriginName,
			"farmName" : farmName,
			"transporterId" : transporterId
		};		
		this.comp("windowDialogEdit").open({"src":require.toUrl("./marketIn_tzedit.w"),"data":data});

	};


	//删除
	Model.prototype.delClick = function(event){
		var self = this;
		var marketIn_tzData = self.comp("marketIn_tzData");
		var row = event.bindingContext.$object;			
		var ids = marketIn_tzData.getValue("vrId", row);	
		justep.Util.confirm("确认要删除吗?", function(){
			$.ajax({
		       	  url:'/rlscsy/animalInInfo_delete.action',
		       	  data:'ids='+ids,
		       	  type:'post',
		       	  dataType:'text',
		       	  async : false,
		       	  success : function(result) {
		       		justep.Util.hint(result);		
		       		marketIn_tzData.refreshData();
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
		var inDate = getNowFormatDate();

		var data = {
			"vrId" : "",
			"comId" : comId,
			"ptbId" : this.ptbId,
			"price" : "",
			"amount" : "",
			"inDate" : inDate,
			"butcherFacId" : butcherFacId,
			"butcherFacName" : butcherFacName,
			"sellerId" : sellerId,
			"sellerName" : sellerName,
			"quarantineId" : "",
			"quarantineNum" : "",
			"amount" : "",
			"deadNum" : "",
			"checkResult" : "",
			"areaOriginId" : "",
			"areaOriginName" : "",
			"farmName" : "",
			"transporterId" : ""
		};	
		this.comp("windowDialogEdit").open({"src":require.toUrl("./marketIn_tzedit.w"),"data":data});
	};
	
	Model.prototype.marketIn_tzDataCustomRefresh = function(event){
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
	    queryParams['animalInInfo.ptbId'] = parseInt(ptbId);	   
	    queryParams["page"]=page;
	    queryParams["rows"]=limit;
	    queryParams["sort"]='vrId';
	    queryParams["order"]='desc';
	    
	    $.ajax({
	        type: "GET",
	        url: '/rlscsy/animalInInfo_findList.action',
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
		this.comp('marketIn_tzData').loadNextPageData();
		
	};
	
	Model.prototype.modelParamsReceive = function(event){
		this.ptbId = event.params.ptbId;
		this.comp("list1").refresh();
	};
	
	Model.prototype.showClick = function(event){
		var self = this;
		var masterData = self.comp("marketIn_tzData");
		var row = event.bindingContext.$object;			
		var appUrl = masterData.getValue("amount", row);		
		var imgSrc = require.toUrl("/nytsyFiles/element/"+appUrl);		
		this.comp("windowDialogImg").open({"src":require.toUrl("./imgshow.w"),"data":{"imgSrc":imgSrc}});

	};
	
	Model.prototype.windowDialogImgReceive = function(event){

	};
	
	
	return Model;
});