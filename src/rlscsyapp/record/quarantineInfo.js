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
		this.comp("quarantineInfoData").refreshData();
	};
	
	//修改按钮
	Model.prototype.updateClick = function(event){
		var quarantineInfoData = this.comp("quarantineInfoData");
		var row = event.bindingContext.$object;								
		
		var qiId = quarantineInfoData.getValue("qiId", row);
		var ptbId = quarantineInfoData.getValue("ptbId", row);		
		var comId = quarantineInfoData.getValue("comId", row);
		var butcherFacId = quarantineInfoData.getValue("butcherFacId", row);
		var butcherFacName = quarantineInfoData.getValue("butcherFacName", row);
		var sellerId = quarantineInfoData.getValue("sellerId", row);
		var sellerName = quarantineInfoData.getValue("sellerName", row);		
		var quarantineId = quarantineInfoData.getValue("quarantineId", row);
		
		var sampleNum = quarantineInfoData.getValue("sampleNum", row);
		var sampleId = quarantineInfoData.getValue("sampleId", row);
		var detector = quarantineInfoData.getValue("detector", row);		
		var checkDate = quarantineInfoData.getValue("checkDate", row);
		var positiveNum = quarantineInfoData.getValue("positiveNum", row);
		var quarantineAnimalProductsId = quarantineInfoData.getValue("quarantineAnimalProductsId", row);
		var inspectionMeatId = quarantineInfoData.getValue("inspectionMeatId", row);
		var checkNum = quarantineInfoData.getValue("checkNum", row);
		var createTime = quarantineInfoData.getValue("createTime", row);
		
		
		var data = {
			"qiId" : qiId,
			"comId" : comId,
			"ptbId" : ptbId,
			"butcherFacId" : butcherFacId,
			"butcherFacName" : butcherFacName,
			"sellerId" : sellerId,
			"sellerName" : sellerName,
			"quarantineId" : quarantineId,
			"sampleNum" : sampleNum,
			"sampleId" : sampleId,
			"detector" : detector,
			"checkDate" : checkDate,
			"positiveNum" : positiveNum,
			"quarantineAnimalProductsId" : quarantineAnimalProductsId,
			"inspectionMeatId" : inspectionMeatId,
			"checkNum" : checkNum,
			"createTime" : createTime
		};		
		this.comp("windowDialogEdit").open({"src":require.toUrl("./quarantineInfoedit.w"),"data":data});

	};


	//删除
	Model.prototype.delClick = function(event){
		var self = this;
		var quarantineInfoData = self.comp("quarantineInfoData");
		var row = event.bindingContext.$object;			
		var ids = quarantineInfoData.getValue("qiId", row);	
		justep.Util.confirm("确认要删除吗?", function(){
			$.ajax({
		       	  url:'/rlscsy/quarantineInfo_delete.action',
		       	  data:'ids='+ids,
		       	  type:'post',
		       	  dataType:'text',
		       	  async : false,
		       	  success : function(result) {
		       		justep.Util.hint(result);		
		       		quarantineInfoData.refreshData();
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
		var checkDate = getNowFormatDate();

		var data = {
			"qiId" : "",
			"comId" : comId,
			"ptbId" : this.ptbId,
			"sampleId" : "",
			"detector" : "",
			"butcherFacId" : butcherFacId,
			"butcherFacName" : butcherFacName,
			"sellerId" : sellerId,
			"sellerName" : sellerName,
			"quarantineId" : "",
			"sampleNum" : "",
			"detector" : "",
			"checkDate" : checkDate,
			"positiveNum" : "",
			"quarantineAnimalProductsId" : "",
			"inspectionMeatId" : "",
			"checkNum" : "",
			"createTime" : ""
		};	
		this.comp("windowDialogEdit").open({"src":require.toUrl("./quarantineInfoedit.w"),"data":data});
	};
	
	Model.prototype.quarantineInfoDataCustomRefresh = function(event){
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
	    queryParams['quarantineInfo.ptbId'] = parseInt(ptbId);	   
	    queryParams["page"]=page;
	    queryParams["rows"]=limit;
	    queryParams["sort"]='qiId';
	    queryParams["order"]='desc';
	    
	    $.ajax({
	        type: "GET",
	        url: '/rlscsy/quarantineInfo_findList.action',
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
		this.comp('quarantineInfoData').loadNextPageData();
		
	};
	
	Model.prototype.modelParamsReceive = function(event){
		this.ptbId = event.params.ptbId;
		this.comp("list1").refresh();
	};
	
	Model.prototype.showClick = function(event){
		var self = this;
		var masterData = self.comp("quarantineInfoData");
		var row = event.bindingContext.$object;			
		var appUrl = masterData.getValue("detector", row);		
		var imgSrc = require.toUrl("/nytsyFiles/element/"+appUrl);		
		this.comp("windowDialogImg").open({"src":require.toUrl("./imgshow.w"),"data":{"imgSrc":imgSrc}});

	};
	
	Model.prototype.windowDialogImgReceive = function(event){

	};
	
	
	
	return Model;
});