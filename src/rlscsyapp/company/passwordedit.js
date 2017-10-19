define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();		
	};
	
	
	Model.prototype.modelParamsReceive = function(event){		
		this.comp("oldpassword").val('');
		this.comp("newpassword").val('');
		this.comp("repassword").val('');	
	};


	//提交密码信息
	Model.prototype.verifyButton = function(event) {
		var self = this;
		
		var oldpassword = this.comp("oldpassword").val().trim();
		var newpassword = this.comp("newpassword").val().trim();
		var repassword = this.comp("repassword").val().trim();		
		
		if (oldpassword==''){
			justep.Util.hint("请正确填写原密码!", {"type" : "danger"});
			return;
		}
		if (newpassword==''){
			justep.Util.hint("请正确填写新密码!", {"type" : "danger"});
			return;
		}
		if (''==repassword){
			justep.Util.hint("请正确填写确认密码!", {"type" : "danger"});
			return;
		}
		if (newpassword!=repassword){
			justep.Util.hint("新密码与确认密码不一致!", {"type" : "danger"});
			return;
		}
		
		var condition = {};
		condition['password']=oldpassword;
		condition['newPassword']=newpassword;
		
		$.ajax({
			url : '/rlscsy/app_updatePassword.action',
	        type: "POST",
	        data: condition,
	        dataType: "json",
	        success: function (result) {
	        	if(result.code=='10000'){
					justep.Util.hint(result.msg);
					self.comp("oldpassword").val("");
					self.comp("newpassword").val("");
					self.comp("repassword").val("");	
					justep.Shell.showPage(require.toUrl("../index-company.w"), {});	
	        	}else{
	        		justep.Util.hint(result.msg, {"type" : "danger"});
	        	}
	        }
	    });
	};
	
	
	//为上面提供各个JS验证方法提供.trim()属性   
	String.prototype.trim = function(){
        return this.replace(/\s+/g,"");//删除所有空格;
    }

	return Model;
});