
function closeWin(id){
	$("#"+id).dialog("close");
}

function closeDialog(id){
	$("#"+id).dialog("close");
}

/**
 * 会话超时
 * @param data
 * @return
 */
function f_timeout(data) {
	if (data&&data.rows&&data.rows.statusCode != null) {
		if (data.statusCode = '301') { 
			$.messager.alert('提示','用户会话已超时，请重新登录！','question',function(){
				top.window.parent.location = 'ajaxSessionTimeout.action';
				
			});
			
		}
	}
}


/**
 * 企业后台会话超时
 * @param data
 * @return
 */
function f_timeoutToCompany(data) {
	if (data&&data.rows&&data.rows.statusCode != null) {
		if (data.statusCode = '301') { 
			$.messager.alert('提示','用户会话已超时，请重新登录！','question',function(){
				top.window.parent.location = 'ajaxSessionTimeoutToCom.action';
				
			});
			
		}
	}
}

/**
 * 扩展验证
 */
$.extend($.fn.validatebox.defaults.rules, {
	
	//前后日期比较
	TimeCheck:{
	    validator:function(value,param){     
	       var s = $("#"+param).datebox('getValue');
	       //因为日期是统一格式的所以可以直接比较字符串 否则需要Date.parse(_date)转换
	       return value>=s;
	    },
	    message:'非法数据'
   },
   
   username : {// 验证用户名 
       validator : function(value) { 
           return /^[a-zA-Z][a-zA-Z0-9_]{1,20}$/i.test(value); 
       }, 
       message : '用户名不合法（字母开头，允许2-20字节，允许字母数字下划线）' 
   }, 
	//验证身份证
	idcard : {
		validator: function (value,param) {
			//return idCard(value);
	   		return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
		},
	    message : '证件号码格式不正确，应为18位字符'
	},
	//验证年龄
	age : {
		validator: function (value,param) {
		if(!(/^\d+$/.test(value))){
			$.fn.validatebox.defaults.rules.age.message='年龄不能为非数字';
			return false;
		}
		if(value<0||value>120){
			$.fn.validatebox.defaults.rules.age.message='超出年龄的范围（0-120）';
			return false;
		}
		return true;
	},
    message : ''
	},
    equalTo: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '两次输入的字符不一至'
    },
	mobile :{
		validator: function (value,param) {
		  /*/^1[3|4|5|8][0-9]\d{4,8}$/.test(value);			
		  var reg1=/^1\d{10}$/; 
		  var my=false;
		  if (reg1.test(value))my=true;		  
			return my;*/
    	 return /^(13|14|15|17|18)\d{9}$/i.test(value); 
		},
	    message : '手机号码格式不正确'
	},
    ZIP: {
        validator: function (value, param) {
            return /^[1-9]\d{5}$/.test(value);
        },
        message: '邮政编码不正确'
    },
	//验证电话号码
    phone: {
        validator: function (value,param) {
             return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        },
        message: '电话号码不正确，请使用下面格式:020-88888888'
    },
    //验证数字
    number: {
        validator: function (value,param) {
            return /^\d+$/.test(value);
        },
        message: '请输入数字'
    },
    
    //验证中文
    CHS: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '请输入汉字'
    },
    //ajax 验证唯一性，组合验证
    //uniqueMore有5个参数
    //第一个参数：JS正则表达式 ，
    //第二个：正则验证返回的错误提示，
    //第三个参数：服务器端验证URL，
    //第四个参数：post传递的参数，
    //第五个参数：服务器端验证返回的出错提示
    //<input type="text" name="user_name" id="username" class="easyui-validatebox" required="true"
    //validType="unique1[\\s\\S,'输入用户名格式不准确','checkInstru_findCheckInstruName','name','该用户名已经存在']"/>
    uniqueMore: {  
       validator: function(value, param) { 
           var m_reg = new RegExp(param[0]); //传递过来的正则字符串中的"\"必须是"\\"  
           if (!m_reg.test(value)) {  
               $.fn.validatebox.defaults.rules.uniqueMore.message = param[1];  
               return false;  
           }else{  
               var postdata = {};  
               postdata[param[3]] = value;  
               var result = $.ajax({  
            	   url: param[2]+'.action',  
                   data: postdata,  
                   async: false,  
                   type: "post"  
               }).responseText;  
               if (result == "true") {  
                   $.fn.validatebox.defaults.rules.uniqueMore.message = param[4];  
                   return false;  
               }else{  
                   return true;  
               }  
           }  
       },  
       message: ''  
   },
   //ajax 验证唯一性
   //Unique有3个参数
   //第1个参数：服务器端验证URL，
   //第2个参数：post传递的参数，
   //第3个参数：服务器端验证返回的出错提示
   //<input type="text" name="user_name" id="username" class="easyui-validatebox" required="true"
   //validType="uniqueName['checkInstru_findCheckInstruName','name','该用户名已经存在']"/>
   uniqueName: {  
       validator: function(value, param) {
           var postdata = {};  
           postdata[param[1]] = value;  
           var result = $.ajax({  
        	   url: param[0]+'.action',  
               data: postdata,  
               async: false,  
               type: "post"  
           }).responseText;  
           if (result == "true") {  
               $.fn.validatebox.defaults.rules.uniqueName.message = param[2];  
               return false;  
           }else{  
               return true;  
           }              
       },  
       message: ''  
   }    ,
   uniqueNameNew: {  
       validator: function(value, param) {
    	   
    	   if(value==$('#'+param[3]).attr('oldValue'))
    		   return true;
           var postdata = {};  
           postdata[param[1]] = value;  
           var result = $.ajax({  
        	   url: param[0]+'.action',  
               data: postdata,  
               async: false,  
               type: "post"  
           }).responseText;  
           if (result == "true") {  
        	   
               $.fn.validatebox.defaults.rules.uniqueNameNew.message = param[2];  
               return false;  
           }else{  
               return true;  
           }              
       },  
       message: ''  
   }    ,
   uniqueNoticeUrl: {  
       validator: function(value, param) {
           var postdata = {};  
           postdata[param[1]] = value;  
           var url = $("#h_noticeUrl").val();
           if(url==value){
        	   return true;
           }
           var result = $.ajax({  
        	   url: param[0]+'.action',  
               data: postdata,  
               async: false,  
               type: "post"  
           }).responseText;  
           if (result == "true") {  
               $.fn.validatebox.defaults.rules.uniqueNoticeUrl.message = param[2];  
               return false;  
           }else{  
               return true;  
           }              
       },  
       message: ''  
   }   ,
   uniqueUserNameInEnt: {  
       validator: function(value, param) {
           var postdata = {};  
           postdata[param[1]] = value;  
           var ent = $("#user_entid").combotree('getValue');
           if(ent==null||ent==''){
        	   $.fn.validatebox.defaults.rules.uniqueUserNameInEnt.message = "请先选择机构";  
               return false;  
           }          
           postdata["accountentId"] = ent;
           var result = $.ajax({  
        	   url: param[0]+'.action',  
               data: postdata,  
               async: false,  
               type: "post"  
           }).responseText;  
           if (result == "true") {  
               $.fn.validatebox.defaults.rules.uniqueUserNameInEnt.message = param[2];  
               return false;  
           }else{  
               return true;  
           }              
       },  
       message: ''  
   }   ,
   uniqueAccountName: {  
       validator: function(value, param) {
           var postdata = {};  
           postdata[param[1]] = value;  
           var result = $.ajax({  
        	   url: param[0]+'.action',  
               data: postdata,  
               async: false,  
               type: "post"  
           }).responseText;  
           if (result == "true") {  
               $.fn.validatebox.defaults.rules.uniqueAccountName.message = param[2];  
               return false;  
           }else{  
               return true;  
           }              
       },  
       message: ''  
   }   ,
   uniqueRoleNameInEnt: {  
       validator: function(value, param) {
           var postdata = {};  
           postdata[param[1]] = value;  
           var ent = $("#role_entid").combotree('getValue');
           if(ent==null||ent==''){
        	   $.fn.validatebox.defaults.rules.uniqueRoleNameInEnt.message = "请先选择机构";  
               return false;  
           }          
           postdata["role.entId"] = ent;
           var result = $.ajax({  
        	   url: param[0]+'.action',  
               data: postdata,  
               async: false,  
               type: "post"  
           }).responseText;  
           if (result == "true") {  
               $.fn.validatebox.defaults.rules.uniqueRoleNameInEnt.message = param[2];  
               return false;  
           }else{  
               return true;  
           }              
       },  
       message: ''  
   }   ,
   uniqueMobileInEnt: {  
       validator: function(value, param) {
           var postdata = {};  
           postdata[param[1]] = value;  
           var flag = /^(13|14|15|17|18)\d{9}$/i;
           if(!flag.test(value)){
               $.fn.validatebox.defaults.rules.uniqueMobileInEnt.message = "手机号码格式不正确";
               return false;
           }      
           var mobileOld = $("#mobileOld").val();
           var result = $.ajax({  
        	   url: param[0]+'.action',  
               data: postdata,  
               async: false,  
               type: "post"  
           }).responseText;  
           if (result == "true"&&(mobileOld!=value||mobileOld=='')) {  
               $.fn.validatebox.defaults.rules.uniqueMobileInEnt.message = param[2];  
               return false;  
           }else{  
               return true;  
           }              
       },  
       message: ''  
   }
});

//身份证验证
var idCard = function (value) {
    if (value.length == 18 && 18 != value.length) return false;
    var number = value.toLowerCase();
    var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
    var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
    if (re == null || a.indexOf(re[1]) < 0) return false;
    if (re[2].length == 9) {
        number = number.substr(0, 6) + '19' + number.substr(6);
        d = ['19' + re[4], re[5], re[6]].join('-');
    } else d = [re[9], re[10], re[11]].join('-');
    if (!isDateTime.call(d, 'yyyy-MM-dd')) return false;
    for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
    return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
}
//验证身份证号中所包含的时间信息
var isDateTime = function (format, reObj) {
    format = format || 'yyyy-MM-dd';
    var input = this, o = {}, d = new Date();
    var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
    var len = f1.length, len1 = f3.length;
    if (len != f2.length || len1 != f4.length) return false;
    for (var i = 0; i < len1; i++) if (f3[i] != f4[i]) return false;
    for (var i = 0; i < len; i++) o[f1[i]] = f2[i];
    o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
    o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
    o.dd = s(o.dd, o.d, d.getDate(), 31);
    o.hh = s(o.hh, o.h, d.getHours(), 24);
    o.mm = s(o.mm, o.m, d.getMinutes());
    o.ss = s(o.ss, o.s, d.getSeconds());
    o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
    if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0) return false;
    if (o.yyyy < 100) o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
    d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
    var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh && d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
    return reVal && reObj ? d : reVal;
    function s(s1, s2, s3, s4, s5) {
        s4 = s4 || 60, s5 = s5 || 2;
        var reVal = s3;
        if (s1 != undefined && s1 != '' || !isNaN(s1)) reVal = s1 * 1;
        if (s2 != undefined && s2 != '' && !isNaN(s2)) reVal = s2 * 1;
        return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
    }
};


/**
 * 防止 panel dialog window 超出父边界
 * @param left
 * @param top
 * @return
 */
var easyuiPanelOnMove = function(left, top) {
    var parentObj = $(this).panel('panel').parent();
    if (left < 0) {
        $(this).window('move', {
            left : 1
        });
    }
    if (top < 0) {
        $(this).window('move', {
            top : 1
        });
    }
    var width = $(this).panel('options').width;
    var height = $(this).panel('options').height;
    var right = left + width;
    var buttom = top + height;
    var parentWidth = parentObj.width();
    var parentHeight = parentObj.height();
    if(parentObj.css("overflow")=="hidden"){
        if(left > parentWidth-width){
            $(this).window('move', {
                "left":parentWidth-width
            });
    }
        if(top > parentHeight-$(this).parent().height()){
            $(this).window('move', {
                "top":parentHeight-$(this).parent().height()
            });
    }
    }
};
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;