define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	
	var Model = function() {
		this.callParent();
	};

	//登录
	Model.prototype.loginIsmBtn = function(event) {
		var self = this;
		var phoneInput = this.comp("nameInput").val().trim();
		var passwordInput = this.comp("passwordInput").val().trim();
		
		if(phoneInput==""||phoneInput==null){
			justep.Util.hint("账号不能为空！", {"type" : "danger"});
			return ;
		}
		if(passwordInput==""||passwordInput==null){
			justep.Util.hint("密码不能为空！", {"type" : "danger"});
			return ;
		}
		

		var data = {"companyUser.account":phoneInput,"companyUser.password":passwordInput}
		
		$.ajax({
			url : '/rlscsy/app_applogin.action',
	        type: "POST",
	        data: data,
	        dataType: "JSON",
	        cache: false,
	        success: function (result) {
	        	if(result.code=='10001'){	        		
					justep.Util.hint(result.msg, {"type" : "danger"});
	        	}else{
	        		justep.Util.hint("登录成功!");	      
	        			        		
					localStorage.setItem("loginCompanyUser", JSON.stringify(result.loginCompanyUser));
					localStorage.setItem("company", JSON.stringify(result.company));
					localStorage.setItem("node", JSON.stringify(result.node));
					//清空值
					self.comp("nameInput").val('');
					self.comp("passwordInput").val('');
					
					justep.Shell.showPage(require.toUrl("./index-company.w"));
				}
	        }
	    });
	};
	
	
	function isLogin(){
		var res = true;
		var loginCompanyUser= localStorage.getItem("loginCompanyUser");
		var company= localStorage.getItem("company");
		var node= localStorage.getItem("node");
		
		if(loginCompanyUser!=null&&company!=null&&node!=null){
			$.ajax({
				url : '/rlscsy/app_isLogin.action',
		        type: "POST",
		        dataType: "JSON",
		        cache: false,
		        success: function (result) {
		        	if(result){	
						res = true;
		        	}else{
		        		res = false;
					}
		        }
		    });
		}else{
			res = false;
		}		
		
		console.log("isLogin------res=="+res);	
	   return res; 
	}


	Model.prototype.button6Click = function(event){
		this.comp("loginWindowReceiver").windowEnsure({});
	};
	
	

    
	//输入账号时去除空格
	Model.prototype.nameInputChange = function(event){
		var phoneInput = this.comp("nameInput").val().trim();
		this.comp("nameInput").val(phoneInput);
	};
	
	//输入密码时去除空格
	Model.prototype.passwordInputChange = function(event){
		var passwordInput = this.comp("passwordInput").val().trim();
		this.comp("passwordInput").val(passwordInput);
	};

	
	//为上面提供各个JS验证方法提供.trim()属性   
	String.prototype.trim = function(){
        return this.replace(/\s+/g,"");//删除所有空格;
    }
    
	Model.prototype.modelParamsReceive = function(event){
		if(isLogin()){
		  justep.Shell.showPage(require.toUrl("./index-company.w"));
		}else{
			var regMsg = this.getContext().getRequestParameter("regMsg");
			if(regMsg){
			 justep.Util.hint(regMsg);
			}
		}
		
	};
    
	Model.prototype.registerClick = function(event){
		justep.Shell.showPage(require.toUrl("./register.w"));
	};
    
	return Model;
});