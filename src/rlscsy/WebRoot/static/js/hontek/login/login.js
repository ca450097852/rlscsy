var handle = function() {
	var account = $("#account").attr("value");
	var userName = $("#userName").attr("value");
	var password = $("#password").attr("value");
	var yzm = $("#yzm").attr("value");
	
	// 企业号
	var e = /^[a-z0-9_-]{5,16}$/;
	if (account.length>0) {
		if (!e.test(account)) {
			$("#info")[0].innerHTML = "企业号只能由字母和数字，且长度在5-16之间.";
			$("#account").focus();
			return;
		}else{
			//$.ajax( {
				//url : 'ent_findEnterpriseByAccount.action',
				//data : 'account=' + account,
				//type : 'post',
				//dataType:'text',
				//success : function(result) {
					//if(result=="Y"){
						//$("#userName").focus();
					//}else{
						//$("#info")[0].innerHTML = "企业号<font color=red> '"+account+"' </font>不存在!";
						//$("#account").attr("value","");
						//$("#account").focus();
						//return false;
					//} 
				//}
			//});
			
			$("#userName").focus();
		}
	}
	
	// 验证用户名
	var u = /^[a-z0-9_-]{5,16}$/;
	if (!userName) {
		$("#info")[0].innerHTML = '用户名不能为空!';
		$("#userName").focus();
		return false;
	} else {
		if (!u.test(userName)) {
			$("#info")[0].innerHTML = "用户名只能由字母和数字，且长度在5-16之间.";
			$("#userName").focus();
			return false;
		}
	}
	// 验证密码
	var p = /^[a-z0-9_-]{5,16}$/;
	if (!password) {
		$("#info")[0].innerHTML = '密码不能为空!';
		$("#password").focus();
		return false;
	} else {
		if (!p.test(password)) {
			$("#info")[0].innerHTML = "密码只能由字母和数字，且长度在5-16之间.";
			$("#password").focus();
			return false;
		}
	}
	// 初步检测验证码
	if (!yzm) {
		$("#info")[0].innerHTML = '请输入验证码!';
		$("#yzm").focus();
		return false;
	} else {
		check_code();
	}

	document.onkeydown = function(e) {
		if (!e)
			e = window.event;
		if ((e.keyCode || e.which) == 13) {
			handle();
		}
	}

}
