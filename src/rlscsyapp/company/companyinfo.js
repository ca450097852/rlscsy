define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	
	var Model = function(){
		this.callParent();
		this.companyinfo;
	};


	//获取状态
	 Model.prototype.getState = function(value){
		var res = '无';
		if(value=='1'){
			res = '使用';
		}else if(value=='2'){
			res = '停用';
		}
		return res;
	}
	
	//获取节点类型
	 Model.prototype.getFlag = function(value){
		var res = '无';//1代表屠宰企业、2代表批发企业、3代表菜市场、4代表超市、5代表团体消费单位、6代表其他
		if(value=='1'){
			res = '屠宰企业';
		}else if(value=='2'){
			res = '批发企业';
		}else if(value=='3'){
			res = '菜市场';
		}else if(value=='4'){
			res = '超市';
		}else if(value=='5'){
			res = '团体消费单位';
		}else if(value=='6'){
			res = '其他';
		}
		return res;
	}
	
	//企业性质
	 Model.prototype.getNature = function(value){
		var res = '无';
		if(value=='1'){
			res = '企业';
		}else if(value=='2'){
			res = '个体户';
		}
		return res;
	}
	
	//经营者类型
	 Model.prototype.getComType = function(value){
		var res = '无';//主要分生猪批发商、肉类蔬菜批发商、肉类蔬菜零售商、配送企业、其他等类型
		if(value=='1'){
			res = '生猪批发商';
		}else if(value=='2'){
			res = '肉菜批发商';
		}else if(value=='3'){
			res = '肉菜零售商';
		}else if(value=='4'){
			res = '配送企业';
		}else if(value=='5'){
			res = '其他';
		}
		return res;
	}

	Model.prototype.companydataCustomRefresh = function(event){
		var self = this;
		var companydata = event.source;
		companydata.clear();
		$.ajax({
			url : '/rlscsy/app_getLoginCompany.action',
	        type: "POST",
	        dataType: "JSON",
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function (result) {
	        	if(result){
	        		self.companyinfo = result[0];
	        		localStorage.setItem("companyinfo", JSON.stringify(self.companyinfo));
	        		companydata.loadData(result);
	        	}
	        }
	    });
	};

	Model.prototype.modelLoad = function(event){
		var self = this;
		this.comp("companydata").refreshData();
		
		if(self.companyinfo.comLogo){
			$("#comLogo").attr("src",require.toUrl("/nytsyFiles/company/"+self.companyinfo.comLogo));
		}
	    if(self.companyinfo.licenseImg){
			$("#imageUpload").attr("src",require.toUrl("/nytsyFiles/company/"+self.companyinfo.licenseImg));
		}
	};
	
	Model.prototype.button1Click = function(event){
		this.comp("windowDialog").open({"src":require.toUrl("./companyinfoedit.w")});
	};
	
	Model.prototype.windowDialogReceive = function(event){
	};
	
	Model.prototype.windowDialogClose = function(event){
		this.comp("companydata").refreshData();
	};
	
	Model.prototype.windowDialogImgReceive = function(event){
	};
	
	Model.prototype.showClick = function(event){
		var self = this;
		if(self.companyinfo.licenseImg){
			var imgSrc = require.toUrl("/nytsyFiles/company/"+self.companyinfo.licenseImg);
			this.comp("windowDialogImg").open({"src":require.toUrl("../record/imgshow.w"),"data":{"imgSrc":imgSrc}});
		}
	};
	
	Model.prototype.showLogoClick = function(event){
		var self = this;
		if(self.companyinfo.comLogo){
			var imgSrc = require.toUrl("/nytsyFiles/company/"+self.companyinfo.comLogo);
			this.comp("windowDialogImg").open({"src":require.toUrl("../record/imgshow.w"),"data":{"imgSrc":imgSrc}});
		}
	};
	
	
	return Model;
});