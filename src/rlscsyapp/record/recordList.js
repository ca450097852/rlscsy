define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.ptbId = "";
	};
	
	Model.prototype.modelParamsReceive = function(event){
		this.ptbId = event.params.ptbId;
	};

	Model.prototype.recordDataCustomRefresh = function(event){
		var masterData = event.source;
	    masterData.clear();
	    var queryParams = {};
	    var self = this;
	    var company = localStorage.getItem("company");	      	    
	    var companyObj = JSON.parse(company);	    
	    var comType = companyObj.comType;	    
	    var data ;
	    if(comType==1){
	    	data =  [ {"recId" : "1","typeName" : "生猪进厂信息","pagename" : "./marketIn_tz.w"},
			           {"recId" : "2","typeName" : "检验检疫信息","pagename" : "./quarantineInfo.w"},
			           {"recId" : "3","typeName" : "交易信息","pagename" : "./meatOutInfoBase_tz.w"},
			           {"recId" : "4","typeName" : "追溯标识","pagename" : "../protype/dimennoList.w"}];
	    }else if(comType==2){
	    	data =  [ {"recId" : "1","typeName" : "批发进场信息","pagename" : "../marketIn/marketInBase.w"},
			           {"recId" : "2","typeName" : "检验检疫信息","pagename" : "../marketIn/qmarketDetectionInfo.w"},
			           {"recId" : "3","typeName" : "交易信息","pagename" : "../marketIn/meatOutInfoBase.w"}];
	    }else if(comType==3){
	    	data =  [ {"recId" : "1","typeName" : "零售进场信息","pagename" : "../marketIn/marketInBase.w"},
			           {"recId" : "2","typeName" : "销售汇总信息","pagename" : "../marketIn/retailMarketTranInfoSumm.w"}];
	    }else if(comType==4){
	    	data =  [ {"recId" : "1","typeName" : "超市进场信息","pagename" : "../marketIn/marketInBase.w"}];
	    }else if(comType==5){
	    	data =  [ {"recId" : "1","typeName" : "肥料使用记录","pagename" : "../plant/fertilizerUse.w"},
	    	          {"recId" : "2","typeName" : "农药使用记录","pagename" : "../plant/agriUse.w"},
	    	          {"recId" : "3","typeName" : "生产节点","pagename" : "../plant/nodeinfo.w"},
	    	          {"recId" : "4","typeName" : "检验检疫信息","pagename" : "../plant/checkinfo.w"},
	    	          {"recId" : "5","typeName" : "交易信息","pagename" : "../plant/saleinfo.w"}];
	    }          
		   
	    masterData.loadData(data);//将返回的数据加载到data组件
    
	};


	/**
	 *页面跳转
	 */
	Model.prototype.li2Click = function(event){	
	
		var company = localStorage.getItem("company");	      	    
	    var companyObj = JSON.parse(company);	    
	    var comType = companyObj.comType;	    
	
		var masterData = this.comp("recordData");
		var row = event.bindingContext.$object;					
		var pagename = masterData.getValue("pagename", row);		
		justep.Shell.showPage(require.toUrl(pagename), {ptbId:this.ptbId,flag:comType});
	};

	


	return Model;
});