var areaTree = "<option value=\"-1\" selected=\"selected\">请选择</option>";//得到区域树
//var entTree = "<option value=\"-1\" selected=\"selected\">请选择行政机构</option>";//得到机构树
var typeTree = "<option value=\"-1\" selected=\"selected\">请选择企业类型</option>";//得到企业类型树

var zzjg_need ="<input type=\"text\" name=\"enterprise.orgCode\" id=\"orgCode\"  style=\"width: 350px;\" class=\"inputxt\" datatype=\"*\" sucmsg=\"组织机构代码验证通过！\" nullmsg=\"请输入组织机构代码！\"/>";
var zzjg_unneed ="<input type=\"text\" name=\"enterprise.orgCode\" id=\"orgCode\"  style=\"width: 350px;\" class=\"inputxt\" />";
var zzjg_needTitle = "<label><font class=\"red_txt\">*</font>组织机构代码</label>";
var zzjg_unneedTitle = "<label>组织机构代码</label>";
$(function(){

	getAreaTree('',1);//获取区域级联下拉;
	
	$.ajax({
		url:'mbenttype_getEntTypeToSelect.action',
		data:'typeId=2',
		async : false,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var html = "";
			var vo;
			var checked = "";
			if(result){
				for(var i=0;i<result.length;i++){
					vo = result[i];
					if(vo.text=='企业'){
						checked = "checked=\"checked\"";
					}
					html+=vo.text+":<input name=\"enterprise.entType\" type=\"radio\" value=\""+vo.id+"\" "+checked+" onclick=\"checkZzjg(this.value);\"  style=\"width: 10px;\"/>&nbsp;&nbsp;";
					checked = "";
					
				}
				
			}
			$("#entType_html").append(html);
		}
	});
	

	

	//验证控件；
	var demo = $("#entForm").Validform({
		tiptype:2
	});
	
	
	$("#clearform").click(function(){
		demo.resetForm();
	});
	
});

function checkZzjg(value){
	$("input[name='enterprise.entType'][checked]").attr("checked",false);
	$("input[name='enterprise.entType'][value="+value+"]").attr("checked",true); 
	if(value<=2){
		$("#orgCode_html").html(zzjg_need);
		$("#orgCode_title").html(zzjg_needTitle);
	}else{
		$("#orgCode_html").html(zzjg_unneed);
		$("#orgCode_title").html(zzjg_unneedTitle);
		$("#orgCode_msg").html("<span class=\"Validform_checktip\"></span>");
	}
	
}

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
					areaSelect1 = "<select id=\""+id+"\" style=\"width: 177px;height: 30px;line-height: 30px;\" class=\"inputxt\" onchange=\"getAreaTree("+id+",this.value)\" datatype=\"n\" nullmsg=\"请选择所在区域！\" errormsg=\"请选择所在区域！\">";
				}else if(item=='1'){
					$(".forRemove").remove();
					areaSelect1 = "<select id=\""+id+"\" class=\"forRemove inputxt\" style=\"width: 177px;height: 30px;line-height: 30px;\"  onchange=\"getAreaTree("+id+",this.value)\">";
				}
				var areaSelect2 = "</select>";
				if(length>0){
					explaint(result,0);
					$("#quyu").append(areaSelect1+areaTree+areaSelect2+"<span></span>");
					areaTree = "<option value=\"-1\" selected=\"selected\">请选择</option>";
				}
		}
		});
		$("#areaId").val(id);
		
		/*
		//获取管理机构
		$.ajax({
			url:'mbent_getEntManagerId.action',
			data:'entId='+id,
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result){
					$("#parentId").val(result);
					getLevelAndAudit(result);//获取审核机构
				}
			}
		});
		*/
		
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

//解析企业类型数据;
function explaint3(data,tem){
	var vo = {};
	var nb = "";
	for(var j = 0;j<tem;j++){ 
		nb = nb+"&nbsp;&nbsp;";
	}
	for(var i=0;i<data.length;i++){
		vo = data[i];
		typeTree = typeTree+"<option value=\""+vo.id+"\">"+nb+vo.text+"</option>";
		if(vo.children!=null&&vo.children.length>0){
			explaint3(vo.children,tem+1);
		}
	}
	
}

//获取企业级别和默认审核机构数据;
function getLevelAndAudit(id){
	var entId = id;  // 在用户点击的时候提示
	var entlevel=0;
	if(entId!=-1){
		//指定审核机构
		$.ajax({
			  url: "mbent_getAuditTree.action?entId="+entId,
			  cache: false,
			  success: function(auditEntHTML){
				if(auditEntHTML){
					if(auditEntHTML=='0'){
						alert("请选择市级以下机构！");
						
						$("#auditEnt").html("");
					}else{
						$("#auditEnt").html(auditEntHTML);
					}
				}
			  }
		});
				
	}else{
		$("#auditEnt").html("");
	}
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
		var enterprise = {};
		enterprise["enterprise.name"]=name;
		$.ajax({
			  url: "mbent_findNameIsUnique.action",
			  data:enterprise,
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
		var enterprise = {};
		enterprise["enterprise.account"]=account;
		$.ajax({
			  url: "mbent_findAccountIsUnique.action",
			  data:enterprise,
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
	var intro = ""+$("#intro").val();
	if (intro.length>500) {
    	layer.confirm('请输入500字内的简介信息！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
    	intro = intro.substring(0, 500);
    	$("#intro").val(intro).focus();
    	$("#intro").focus();
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
	
	var entType = $("input[name='enterprise.entType'][checked]").val(); 
	
	if($("#areaId").val()=="1"){
//		alert("请选择区域!");
		layer.confirm('请选择区域！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#1").focus();
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
	if($("#legalPerson").val()==""){
		layer.confirm('请填写企业法人！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#legalPerson").focus();
		return ;
	}
	
	
	if(entType<=2&&$("#orgCode").val()==""){
		layer.confirm('请填写组织机构代码！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#orgCode").focus();
		return ;
	}
	
	
	if($("#regAddr").val()==""){
		layer.confirm('请填写注册地址！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#regAddr").focus();
		return ;
	}
	/*if($("#manageAddr").val()==""){
		layer.confirm('经营地址至少需填写一个！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#manageAddr").focus();
		return ;
	}*/
	
	if($("#tel").val()==""){
		layer.confirm('请填写联系电话！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#tel").focus();
		return ;
	}else if(!ckTel($("#tel").val())){
		layer.confirm('联系电话格式错误！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#tel").focus();
		return ;
	}
	
	
	if($("#mobile").val()==""){
		layer.confirm('请填写手机号码！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#mobile").focus();
		return ;
	}else if(!ckPhone($("#mobile").val())){
		layer.confirm('手机号码格式错误！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
		$("#mobile").focus();
		return ;
	}	
	
	var intro = ""+$("#intro").val();
	if (intro == "请输入500字内的简介信息！") {
    	layer.confirm('请输入500字内的简介信息！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
    	$("#intro").focus();
		return ;
	}
	
	if (intro.length>500) {
    	layer.confirm('请输入500字内的简介信息！', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
		});
    	intro = intro.substring(0, 500);
    	$("#intro").val(intro).focus();
		return ;
	}
	
	
	if(issubmit==0){
		issubmit =1;//已提交
//		$('#entForm').form('submit', {
//			url : 'mbent_register.action'
//		});
		
		$.ajax({
			url : 'register.action',
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