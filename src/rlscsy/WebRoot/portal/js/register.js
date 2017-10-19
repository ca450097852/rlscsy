var areaTree = "<option value=\"-1\" selected=\"selected\">请选择</option>";//得到区域树

$(function(){

	getAreaTree('',1);//获取区域级联下拉;
	//验证控件；
	var demo = $("#entForm").Validform({
		tiptype:2
	});
	
	$("#clearform").click(function(){
		demo.resetForm();
	});
	
});


//获取区域级联下拉;
function getAreaTree(item,id){
	if(id!=-1){
		$.ajax({
			url:'mbent_getAreaTree.action',
			data:'entId='+id,
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				var length = result.length;
				var areaSelect1 ="";
				if(item==''){
					areaSelect1 = "<select id=\""+id+"\"  class=\"select\" onchange=\"getAreaTree("+id+",this.value)\" datatype=\"n\" nullmsg=\"请选择所在区域！\" errormsg=\"请选择所在区域！\">";
				}else if(item=='1'){
					$(".forRemove").remove();
					areaSelect1 = "<select id=\""+id+"\" class=\"forRemove\"   onchange=\"getAreaTree("+id+",this.value)\">";
				}
				var areaSelect2 = "</select>";
				if(length>0){
					explaint(result,0);
					$("#quyu").append(areaSelect1+areaTree+areaSelect2+"<span></span>");
					areaTree = "<option value=\"-1\" selected=\"selected\">请选择</option>";
				}
		}
		});
		$("#area").val(id);
		
		getNodeToSelect(id);//获取节点下拉
	}
	
}

//解析区域数据;
function explaint(data,tem){
	
	var vo = {};
	var nb = "";
	for(var j = 0;j<tem;j++){ 
		nb = nb+"&nbsp;&nbsp;";
	}
	for(var i=0;i<data.length;i++){
		vo = data[i];
		areaTree = areaTree+"<option value=\""+vo.id+"\">"+nb+vo.text+"</option>";
		if(vo.children.length>0){
			explaint(vo.children,tem+1);
		}
	}
	
}


//获取节点下拉
var nodeOption = "";//得到节点下拉
function getNodeToSelect(area){
	$.ajax({
		url:'webcompany_getCompanyToSelect.action',
		data:'area='+area,
		async : false,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var length = result.length;
			var select1 = "<select id=\"nselect\" class=\"select\" onchange=\"getNodeValue(this.value)\" datatype=\"n\" nullmsg=\"请选择所属节点！\" errormsg=\"请选择所属节点！\">";
			var select2 = "</select>";
			if(length>0){
				for(var i=0;i<length;i++){
					var vo = result[i];
					nodeOption = nodeOption+"<option value=\""+vo.id+"\">"+vo.text+"</option>";
				}
				$("#nodeSelect").html(select1+nodeOption+select2+"<span></span>");
				nodeOption = "";
			}
	}
	});
}

function getNodeValue(value){
	$("#parentId").val(value);
}

function checkZzjg(value){
	$("input[name='company.nature'][checked]").attr("checked",false);
	$("input[name='company.nature'][value="+value+"]").attr("checked",true); 
}
//验证名称唯一
function isNameOnlyone(){
	
	$("#name").removeClass("Validform_error");
	$("#name_msg").html("");
	var name = $("#name").val();
	if(name==''){
		$("#name_yanzheng").val("0");//验证不通过；
		$("#name").addClass("Validform_error");
		$("#name_msg").html("<span class=\"Validform_checktip Validform_wrong\">请输入企业名称！</span>");
		return ;
	}else{
		var company = {};
		company["company.name"]=name;
		$.ajax({
			  url: "webcompany_findNameIsUnique.action",
			  data:company,
			  type:'post',
			  async : false,
			  dataType:'json',
			  success: function(result){
				if(result){
					$("#name_yanzheng").val("0");//验证不通过；
					$("#name").addClass("Validform_error");
					$("#name_msg").html("<span class=\"Validform_checktip Validform_wrong\">该企业名称已被注册！</span>");
					return ;
				}else{
					$("#name_yanzheng").val("1");//验证通过；
					$("#name_msg").html("<span class=\"Validform_checktip Validform_right\">企业名称验证通过！</span>");
				}
			  }
		});
	}
}

//验证账号唯一
function isAccountOnlyone(){
	$("#account").removeClass("Validform_error");
	$("#account_msg").html("");
	var account = $("#account").val();
	if(account==''){
		$("#account_yanzheng").val("0");//验证不通过；
		$("#account").addClass("Validform_error");
		$("#account_msg").html("<span class=\"Validform_checktip Validform_wrong\">请输入企业账号！</span>");
		return ;
	}else{
		var companyUser = {};
		companyUser["companyUser.account"]=account;
		$.ajax({
			  url: "webcompanyUser_findAccountIsUnique.action",
			  data:companyUser,
			  type:'post',
			  async : false,
			  dataType:'json',
			  success: function(result){
				if(result){
					$("#account_yanzheng").val("0");//验证不通过；
					$("#account").addClass("Validform_error");
					$("#account_msg").html("<span class=\"Validform_checktip Validform_wrong\">该企业账号已被注册！</span>");
					return ;
				}else{
					$("#account_yanzheng").val("1");//验证通过；
					$("#account_msg").html("<span class=\"Validform_checktip Validform_right\">企业账号验证通过！</span>");
				}
			  }
		});
	}
	
}

//验证企业简介
function checkIntro(){
	var introduction = ""+$("#introduction").val();
	if (introduction.length>500) {
    	layer.confirm('请输入500字内的简介信息！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
    	introduction = introduction.substring(0, 500);
    	$("#introduction").val(introduction).focus();
    	$("#introduction").focus();
		return false;
	}
}


function showFwxy(){
	//页面层
	layer.open({
	    type: 1,
	    title:'服务协议',
	    skin: 'layui-layer-rim', //加上边框
	    area: ['800px', '500px'], //宽高
	    content: $('#fwxy')
	});
}


//表单验证 提交表单
var issubmit = 0;
function formsubmit(){
	
	//var nature = $("input[name='company.nature'][checked]").val(); 
	//var parentId = $("#parentId").val();
	
	if($("#area").val()=="1"){
		layer.confirm('请选择区域！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#1").focus();
		return ;
	}
	
	if($("#parentId").val()==""){
		layer.confirm('请选择所属节点！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#nselect").focus();
		return ;
	}
	
	if ($("#name_yanzheng").val() == "0") {
    	layer.confirm('请重新输入企业名称！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
    	$("#name").focus();
		return false;
	}
	if ($("#account_yanzheng").val() == "0") {
    	layer.confirm('请重新输入企业账号！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
    	$("#account").focus();
		return false;
	}
		
	if ($.trim($("#password").val()) == "") {
    	layer.confirm('请输入登录密码！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
    	$("#password").focus();
		return false;
	}
	
	if ($.trim($("#comType").val()) == "") {
    	layer.confirm('请输选择经营类型！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
    	$("#comType").focus();
		return false;
	}
	
	
	if($("#corporate").val()==""){
		layer.confirm('请填写企业法人！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#corporate").focus();
		return ;
	}
	
		
	if($("#addr").val()==""){
		layer.confirm('请填写地址！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#addr").focus();
		return ;
	}
	
	
	
	if($("#phone").val()==""){
		layer.confirm('请填写手机号码！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#phone").focus();
		return ;
	}else if(!ckPhone($("#phone").val())){
		layer.confirm('手机号码格式错误！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#phone").focus();
		return ;
	}	
	
	var introduction = ""+$("#introduction").val();
	if (introduction == "请输入500字内的简介信息！") {
    	layer.confirm('请输入500字内的简介信息！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
    	$("#introduction").focus();
		return ;
	}
	
	if (introduction.length>500) {
    	layer.confirm('请输入500字内的简介信息！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
    	introduction = introduction.substring(0, 500);
    	$("#introduction").val(introduction).focus();
		return ;
	}
	
	
	if(issubmit==0){
		issubmit =1;//已提交
		$.ajax({
			url : 'webcompany_register.action',
	        type: "POST",
	        async : false,
	        data: $('#entForm').serialize(),
	        success:function(data){
//	        	document.location.href = "register_success.jsp";
	        	var jsonType = "";
	        	var entCode = "";
	        	if(data=="-1"){
	    			jsonType = "false";
	    			entCode = "注册企业账号错误";
	    		}else if(data=="-2"){
	    			jsonType = "false";
	    			entCode = "该企业已被注册";
	    		}else{
	    			jsonType = "true";
	    			entCode = data;
	    		}
	        	document.location.href = "register_success.jsp?jsonType="+jsonType+"&entCode="+encodeURI(encodeURI(entCode));
	        }
	    });
		
	
	}else{
		layer.confirm('不能重复提交!', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
	}
}


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


function telOnblur(tel){
	
	$("#tel").removeClass("Validform_error");
	$("#tel_msg").html("");
	if(tel==''){
		$("#tel").addClass("Validform_error");
		$("#tel_msg").html("<span class=\"Validform_checktip Validform_wrong\">请输入手机号码！</span>");
		return ;
	}else{
		if(!ckTel(tel)){
			$("#tel").addClass("Validform_error");
			$("#tel_msg").html("<span class=\"Validform_checktip Validform_wrong\">请输入正确手机号码！</span>");
		}else{
			$("#tel_msg").html("<span class=\"Validform_checktip Validform_right\">手机号码验证通过！</span>");
		}
	}
}

//为上面提供各个JS验证方法提供.trim()属性   
String.prototype.trim = function(){
  return this.replace(/(^\s*)|(\s*$)/g, "");    
}