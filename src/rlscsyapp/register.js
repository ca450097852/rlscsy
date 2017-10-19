define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	
	var Model = function(){
		this.callParent();
		
		this.isCityReload = true;
		this.isAreaReload = true;
		this.isNodeReload = false;
		
		this.areaid = "2";
		this.parentId = "";//节点node，id;
	};

	Model.prototype.regBtnClick = function(event){

		var self = this;
		
		var name = this.comp("name").val();
		var comType = this.comp("comType").val();
		var account = this.comp("account").val();
		var comType = this.comp("comType").val();
		
		var password = this.comp("password").val();
		var repassword = this.comp("repassword").val();
		
		var phone = this.comp("phone").val();		
		
		var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
		var verifyCodeReg = /^\d{6}$/;
		var nameReg = /^[\u4E00-\u9FA5]{2,4}$/;
		
		if (self.areaid==""){
			justep.Util.hint("请选择区域!", {"type" : "danger"});
			return;
		}
		
		if (self.parentId==""){
			justep.Util.hint("请选择节点!", {"type" : "danger"});
			return;
		}
		if (name==""){
			justep.Util.hint("请填写企业名称!", {"type" : "danger"});
			return;
		}
		if (!isNameOnlyone(name)){
			justep.Util.hint("企业名称已被注册!", {"type" : "danger"});
			return;
		}
		
		if (comType==""){
			justep.Util.hint("请选择经营类型!", {"type" : "danger"});
			return;
		}
		
		if (account==""){
			justep.Util.hint("请填写企业账号!", {"type" : "danger"});
			return;
		}
		if (!isAccountOnlyone(account)){
			justep.Util.hint("企业账号已被注册!", {"type" : "danger"});
			return;
		}
		
	
		if (password==""){
			justep.Util.hint("请填写登录密码!", {"type" : "danger"});
			return;
		}

		if (password!=repassword){
			justep.Util.hint("登录密码与确认密码不一致!", {"type" : "danger"});
			return;
		}
		
		if (phone==''||!ckPhone(phone)){
			justep.Util.hint("请正确填写手机号码!", {"type" : "danger"});
			return;
		}
		
		var company = {
			"company.area":self.areaid,
			"company.parentId":self.parentId,
			"company.comType":comType,
			"company.state":1,
			"company.nature":1,
			"company.name":name,
			"company.account":account,
			"company.password":password,
			"company.phone":phone
		}

		$.ajax({
			url : '/rlscsy/webcompany_register.action',
	        type: "POST",
	        data: company,
	        dataType: "JSON",
	        cache: false,
	        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	        success: function (result) {
	        	console.info(result);
	        	if(result=="-1"){
	    			justep.Util.hint("注册企业账号错误", {"type" : "danger"});
	    		}else if(result=="-2"){
	    			justep.Util.hint("该企业已被注册", {"type" : "danger"});
	    		}else{
	    			justep.Shell.showPage(require.toUrl("./login.w?regMsg="+result));
	    		}
	    	}
	    });
	};

	Model.prototype.modelParamsReceive = function(event){
		this.comp("cityData").refreshData();
		
	};

	Model.prototype.loginClick = function(event){
		justep.Shell.showPage(require.toUrl("./login.w"));
	};
	
		
	//验证手机号码
	function ckPhone(phone){
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		 if (reg.test(phone)) {
			 return true;
		 }else{
			 return false;
		 }
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

	  
	//第一下拉框城市级，onShowOption展示下拉数据
	Model.prototype.gridSelect1ShowOption = function(event){
		if (this.isCityReload) {
	        this.comp('cityData').refreshData();
	        this.isCityReload = false;
	    }
	};

	//第一个下拉框onUpdateValue事件的代码
	Model.prototype.gridSelect1UpdateValue = function(event){
		var cityData = this.comp("cityData");
	    var id = cityData.getValue("id");
	    
	    console.log("areaid=="+id);
	    
	    this.areaid = id;
	    this.isAreaReload = true;
	    this.isNodeReload = true;
	    
	};
	
	//第二个下拉框onShowOption事件的代码
	Model.prototype.gridSelect2ShowOption = function(event){
		if (this.isAreaReload) {
	        this.comp('areaData').refreshData();
	        this.isAreaReload = false;
	    }
	};
	
	
	//第二个下拉框onUpdateValue事件的代码
	Model.prototype.gridSelect2UpdateValue = function(event){
		var areaData = this.comp("areaData");
	    var id = areaData.getValue("id");
	    
	    this.areaid = id;
	    this.isNodeReload = true;
	    
	    console.log("areaid=="+id);
	    console.log("isNodeReload=="+this.isNodeReload);
	};
	
	//第三个node下拉框onShowOption事件的代码
	Model.prototype.gridSelect3ShowOption = function(event){
		
		if (this.isNodeReload) {
	        this.comp('nodeData').refreshData();
	        this.isNodeReload = false;
	    }
	};
	
	
		  
	//第三个node下拉
	Model.prototype.gridSelect3UpdateValue = function(event){
		
		var nodeData = this.comp("nodeData");
	    var id = nodeData.getValue("id");
	    
	    this.parentId = id;//节点node，id;
	    console.log("parentId=="+this.parentId);
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
	        data:'entId='+self.areaid,
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
	        url: '/rlscsy/webcompany_getCompanyToSelect.action',
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
	  


	  


		  
	//验证名称唯一
	function isNameOnlyone(name){
		var res = false;
		var company = {};
		company["company.name"]=name;
		$.ajax({
			  url: "/rlscsy/webcompany_findNameIsUnique.action",
			  data:company,
			  type:'post',
			  async : false,
			  dataType:'json',
			  success: function(result){
				if(result){
					res = false;
				}else{
					res = true;
				}
			  }
		});
		return res;
	}
	
	//验证账号唯一
	function isAccountOnlyone(account){
		var res = false;
		var companyUser = {};
		companyUser["companyUser.account"]=account;
		$.ajax({
			  url: "/rlscsy/webcompanyUser_findAccountIsUnique.action",
			  data:companyUser,
			  type:'post',
			  async : false,
			  dataType:'json',
			  success: function(result){
				if(result){
					res = false;
				}else{
					res = true;
				}
			  }
		});
		return res;
	}

	

	return Model;
});