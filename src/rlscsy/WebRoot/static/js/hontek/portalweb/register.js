var areaTree = "<option value=\"-1\" selected=\"selected\">请选择</option>";//得到区域树
//var entTree = "<option value=\"-1\" selected=\"selected\">请选择行政机构</option>";//得到机构树
var typeTree = "<option value=\"-1\" selected=\"selected\">请选择企业类型</option>";//得到企业类型树

var zzjg_need ="<input type=\"text\" name=\"enterprise.orgCode\" id=\"orgCode\"  style=\"width: 250px;\" class=\"inputxt\" datatype=\"*\" sucmsg=\"组织机构代码验证通过！\" nullmsg=\"请输入组织机构代码！\"/>";
var zzjg_unneed ="<input type=\"text\" name=\"enterprise.orgCode\" id=\"orgCode\"  style=\"width: 250px;\" class=\"inputxt\" />";
var zzjg_needTitle = "<strong>组织机构代码</strong><font style=\"color: red\">*</font>";
var zzjg_unneedTitle = "<strong>组织机构代码</strong>";
$(function(){
	
	$("#xieyi").click(function(){
		
		if($("#xieyi").attr("checked")!='checked'){
			$("#subForm").hide();
		}else{
			$("#subForm").show();
		}
		
	});
	
	
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
					html+=vo.text+":<input name=\"enterprise.entType\" type=\"radio\" value=\""+vo.id+"\" "+checked+" onclick=\"checkZzjg(this.value);\" />&nbsp;&nbsp;";
					checked = "";
					
				}
				
			}
			$("#entType_html").html(html);
		}
	});
	

	

	//验证控件；
//	$(".entForm").Validform();
	var demo = $(".entForm").Validform({
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
					areaSelect1 = "<select id=\""+id+"\" style=\"width: 128px;\"  onchange=\"getAreaTree("+id+",this.value)\" datatype=\"n\" nullmsg=\"请选择所在区域！\" errormsg=\"请选择所在区域！\">";
				}else if(item=='1'){
					$(".forRemove").remove();
					areaSelect1 = "<select id=\""+id+"\" class=\"forRemove\" style=\"width: 128px;\"  onchange=\"getAreaTree("+id+",this.value)\">";
				}
				var areaSelect2 = "</select>";
				if(length>0){
					explaint(result,0);
					$("#quyu").append(areaSelect1+areaTree+areaSelect2);
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
    	alert("请输入500字内的简介信息！！");
    	$("#intro").focus();
		return false;
	}
}

//表单验证 提交表单
function formsubmit(){
	
	if($("#xieyi").attr("checked")!='checked'){
		alert("您必须同意服务协议才能注册！");
		return false;
	}
	var entType = $("input[name='enterprise.entType'][checked]").val(); 
	
	if($("#areaId").val()=="1"){
		alert("请选择区域!");
		$("#1").focus();
		return ;
	}
	
	if ($("#name_yanzheng").val() == "0") {
    	alert("请重新输入企业名称!");
		return false;
	}
	if ($("#account_yanzheng").val() == "0") {
    	alert("请重新输入企业账号!");
		return false;
	}
		
	if ($.trim($("#password").val()) == "") {
    	alert("请输入登录密码!");
    	$("#password").focus();
		return false;
	}
	if($("#legalPerson").val()==""){
		alert("请填写企业法人!");
		$("#legalPerson").focus();
		return ;
	}
	
	
	if(entType<=2&&$("#orgCode").val()==""){
		alert("请填写组织机构代码!");
		$("#orgCode").focus();
		return ;
	}
	
	
	if($("#regAddr").val()==""){
		alert("请填写注册地址!");
		$("#regAddr").focus();
		return ;
	}
	if($("#manageAddr").val()==""){
		alert("经营地址至少需填写一个!");
		$("#manageAddr").focus();
		return ;
	}
	
	if($("#tel").val()==""){
		alert("请填写联系电话!");
		$("#tel").focus();
		return ;
	}
	
	
	if($("#mobile").val()==""){
		alert("请填写手机号码!");
		$("#mobile").focus();
		return ;
	}
	
	
	var intro = ""+$("#intro").val();
	if (intro == "请输入500字内的简介信息！！") {
    	alert("请输入500字内的简介信息！！");
    	$("#intro").focus();
		return false;
	}
	
	if (intro.length>500) {
    	alert("请输入500字内的简介信息！！");
    	$("#intro").focus();
		return false;
	}
	

	
	
	if($("#issubmit").val()==0){
		$("#issubmit").val(1);//已提交
		$('#entForm').form('submit', {
			url : 'mbent_addCompany2.action',
			success : function(result) {
				if(result=='-1'){
					alert("注册失败!");
					$('#entForm').form("clear");
					document.location.href="register.jsp";
				}else if(result=='-2'){
					alert("企业不能重复注册!");
					$('#entForm').form("clear");
					document.location.href="register.jsp";
				}else{
					var ss=""+result;
					document.location.href="register_save.jsp?result="+encodeURI(encodeURI(ss));
				}
			}
		});
	
	}else{
		alert("不能重复提交!");
	}
}


