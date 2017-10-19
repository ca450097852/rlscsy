define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
		this.companyinfo;
		
		this.cityValue = "2";
		this.areaid = "";
		this.parentId = "";//节点node，id;
		
		this.comLogo = "";
		this.licenseImg = "";
	}; 

	
	Model.prototype.modelLoad = function(event){
		this.comp("companydata").refreshData();
	};
	
	//企业信息刷新
	Model.prototype.companydataCustomRefresh = function(event){
		var self = this;
		var companydata = event.source;
		companydata.clear();
		var companyinfo = localStorage.getItem("companyinfo");
		if(companyinfo){
			companyinfo = JSON.parse(companyinfo);
			var arraylist = new Array();
			arraylist.push(companyinfo);
			
			companydata.loadData(arraylist);
			
			this.areaid = companyinfo.area;
			this.parentId = companyinfo.parentId;
			
			this.licenseImg = companyinfo.licenseImg;
			this.comLogo = companyinfo.comLogo;
			//console.log("this.areaid=="+this.areaid);
			//console.log("this.parentId=="+this.parentId);
			this.comp('cityData').refreshData();
			this.comp('areaData').refreshData();
			this.comp('nodeData').refreshData();
			
			if(this.comLogo){
				$("#logoView").attr("src",require.toUrl("/nytsyFiles/company/"+this.comLogo));
			}
		    if(this.licenseImg){
				$("#imageUpload1").attr("src",require.toUrl("/nytsyFiles/company/"+this.licenseImg));
			}
			
			this.comp("citySelect").val(2);
			this.comp("areaSelect").val(this.areaid);
			this.comp("nodeSelect").val(this.parentId);
		}
		 
	  };
	
	
	//市级区域
	Model.prototype.cityDataCustomRefresh = function(event){
	    var cityData = event.source;	    	    
	    cityData.clear();
	    
	    $.ajax({
	        type: "GET",
	        url: '/rlscsy/mbent_getAreaTree.action',
	        data:'entId=1',
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){
	            cityData.loadData(data);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	    
	};
	
	//区县级区域
	Model.prototype.areaDataCustomRefresh = function(event){
		var self = this;
		var areaData = event.source;
	    areaData.clear();
	    
	    $.ajax({
	        type: "GET",
	        url: '/rlscsy/mbent_getAreaTree.action',
	        data:'entId='+self.cityValue,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){
	            areaData.loadData(data);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });
	 };
	  

	//节点数据
	Model.prototype.nodeDataCustomRefresh = function(event){
		var self = this;
		var nodeData = event.source;
	    nodeData.clear();
	    
	    $.ajax({
	        type: "GET",
	        url: '/rlscsy/webcompany_getAppNodeToSelect.action',
	        data:'area='+self.areaid,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){
	            nodeData.loadData(data);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });
	    
	   
	};
		  
	//城市拉框onChange事件
    Model.prototype.citySelectChange = function(event){
    	var value = event.value;
    	if(value){
    		this.cityValue = value;
    		this.areaid = value;
    		this.comp('areaData').refreshData();
    		
    		this.comp("areaSelect").val('');
    	}
	};
	
	//县区下拉框onChange事件    
	Model.prototype.areaSelectChange = function(event){
    	var value = event.value;
    	if(value){
    		this.areaid = value;
    		this.comp('nodeData').refreshData();
    		this.comp("nodeSelect").val('');
    	}
	};
	
    //节点下拉框onChange事件    
	Model.prototype.nodeSelectChange = function(event){
    	var value = event.value;
    	if(value){
    		this.parentId = value;
    	}
	};

	//为上面提供各个JS验证方法提供.trim()属性   
	String.prototype.trim = function(){
        return this.replace(/\s+/g,"");//删除所有空格;
    }
    
    //点击logo上传
	Model.prototype.logoUploadClick = function(event){
		$(this.getElementByXid("logoFileOpen")).click();
	};
    //点击logo删除
	Model.prototype.logoRemoveClick = function(event){
		this.comLogo = "";
		$("#logoView").attr("src",require.toUrl("../main/img/default.png"));
	};
    //logo上传事件
	Model.prototype.logoImageChange = function(event){
		//从选取文件对话框返回后，检查用户是否选择了文件，是否选择了图片文件		
		if(!event.target.files){
				return;
		}		
		var file = event.target.files[0];
		var self = this;
		
		if (/^image\/\w+$/.test(file.type)) {
			var reader = new FileReader(); 
			reader.readAsDataURL(file); 
			reader.onload = function(e){ 
	            var data =this.result; //就是base64
	            var size = data.length;
				var params = {
					"uploadifyFileName" : data
				};
				if(size>1398710){
					justep.Util.hint("上传图片大小不能超过1MB!", {"type" : "danger"});
					return;
				}else{
					$.ajax({
				        type: "POST",
				        url: '/rlscsy/webcompany_uploadFileForBase64.action',
				        data:params,
				        dataType: 'TEXT',
				        async: false,//使用同步方式，目前data组件有同步依赖
				        cache: false,
				        success: function(data){	    
				            if(data=="fail"){
				            	self.comLogo = "";
				            	justep.Util.hint("上传图片数据失败", {"type" : "danger"});					
							}else{
								self.comLogo = data;
								$("#logoView").attr("src",require.toUrl("/nytsyFiles/company/"+self.comLogo));
							}
					    }
				    });
				}
			    
			}
		} else {
			justep.Util.hint("请选择图片文件", {"type" : "danger"});
		}
        	
	};
    
    
    //扫描件上传
	Model.prototype.buttonUploadClick = function(event){
		//$("input[xid=inputImage]").click();	
		$(this.getElementByXid("inputImage")).click();
	};
	//扫描件删除
	Model.prototype.buttonRemoveClick = function(event){
		this.licenseImg = "";
		$("#imageUpload1").attr("src",require.toUrl("../main/img/default.png"));
	};

    //扫描件提交
	Model.prototype.inputImageChange = function(event){
		//从选取文件对话框返回后，检查用户是否选择了文件，是否选择了图片文件		
		if(!event.target.files){
				return;
		}		
		var file = event.target.files[0];
		var self = this;
		
		if (/^image\/\w+$/.test(file.type)) {
			var reader = new FileReader(); 
			reader.readAsDataURL(file); 
			reader.onload = function(e){ 
	            var data =this.result; //就是base64
	            var size = data.length;
				var params = {
					"uploadifyFileName" : data
				};
				if(size>1398710){
					justep.Util.hint("上传图片大小不能超过1MB!", {"type" : "danger"});
					return;
				}else{
					$.ajax({
				        type: "POST",
				        url: '/rlscsy/webcompany_uploadFileForBase64.action',
				        data:params,
				        dataType: 'TEXT',
				        async: false,//使用同步方式，目前data组件有同步依赖
				        cache: false,
				        success: function(data){	    
				            if(data=="fail"){
				            	self.licenseImg = "";
				            	justep.Util.hint("上传图片数据失败", {"type" : "danger"});					
							}else{
								self.licenseImg = data;
								$("#imageUpload1").attr("src",require.toUrl("/nytsyFiles/company/"+self.licenseImg));
							}
					    }
				    });
				}
			    
			}
		} else {
			justep.Util.hint("请选择图片文件", {"type" : "danger"});
		}
        	
	};

    
    //
	Model.prototype.windowDialogImgReceive = function(event){

	};
    
    //预览logo
	Model.prototype.showLogoClick = function(event){
		if(this.comLogo){
			var imgSrc = require.toUrl("/nytsyFiles/company/"+this.comLogo);
			this.comp("windowDialogImg").open({"src":require.toUrl("../record/imgshow.w"),"data":{"imgSrc":imgSrc}});
		}
	};
    //预览扫描件  
	Model.prototype.showClick = function(event){
		if(this.licenseImg){
			var imgSrc = require.toUrl("/nytsyFiles/company/"+this.licenseImg);
			this.comp("windowDialogImg").open({"src":require.toUrl("../record/imgshow.w"),"data":{"imgSrc":imgSrc}});
		}
	};
    


	//提交会员信息
	Model.prototype.verifyButton = function(event) {
		var self = this;
		
		var name = self.comp("name").val().trim();
		var comType = self.comp("comType").get('value').trim();
		var area = self.areaid;
		var node = self.parentId;
		
		var corporate = self.comp("corporate").val().trim();
		var addr = self.comp("addr").val();		
		var linkMan = self.comp("linkMan").val();
		var phone = self.comp("phone").val().trim();
		var email = self.comp("email").val().trim();
		var regCode = self.comp("regCode").val().trim();
		var licenseNum = self.comp("licenseNum").val();	
		var recordDate = self.comp("recordDate").val().trim();
		var introduction = self.comp("introduction").val().trim();
		
		if (""==name){
			justep.Util.hint("请输入企业名称!", {"type" : "danger"});
			return;
		}
		
		if (""==area){
			justep.Util.hint("请选择区域!", {"type" : "danger"});
			return;
		}
		
		if (""==node){
			justep.Util.hint("请选择节点!", {"type" : "danger"});
			return;
		}
		if (""==corporate){
			justep.Util.hint("请输入企业法人!", {"type" : "danger"});
			return;
		}
		if (""==addr){
			justep.Util.hint("请输入企业地址!", {"type" : "danger"});
			return;
		}
		if (""==linkMan){
			justep.Util.hint("请输入联系人!", {"type" : "danger"});
			return;
		}
		if (""==phone){
			justep.Util.hint("请输入手机号码!", {"type" : "danger"});
			return;
		}
		
		if (!ckPhone(phone)&&!ckTel(phone)){
			justep.Util.hint("手机号码格式错误!", {"type" : "danger"});
			return;
		}
		
		if (""==introduction){
			justep.Util.hint("请输入企业简介!", {"type" : "danger"});
			return;
		}

		var company = {
			"company.name" : name,
			"company.area" : self.area,
			"company.parentId" : self.parentId,
			"company.licenseImg" : self.licenseImg,
			"company.comLogo" : self.comLogo,
			"company.corporate" : corporate,
			"company.addr" : addr,
			"company.linkMan" : linkMan,
			"company.phone" : phone,
			"company.email" : email,
			"company.regCode" : regCode,
			"company.licenseNum" : licenseNum,
			"company.recordDate" : recordDate,
			"company.introduction" : introduction
		}
		
		$.ajax({
			url : '/rlscsy/app_updateCompany.action',
	        type: "POST",
	        data: company,
	        dataType: "TEXT",
	        success: function (result) {
	        	if(result){	        			        					
					justep.Util.hint("修改成功！");
					self.comp("windowReceiver1").windowEnsure({});//在当前对话框页关闭窗口
	        	}
	        }
	    });
	};


//验证手机号码
function ckPhone(phone){
	var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
	 if (reg.test(phone)) {
		 return true;
	 }else{
		 return false;
	 };
	
}

//验证电话号码
function ckTel(tel){
	var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
	 if (reg.test(tel)) {
		 return true;
	 }else{
		 return false;
	 };
}


    
	return Model;
});