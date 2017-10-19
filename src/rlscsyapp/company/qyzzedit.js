define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
		this.id = "";
		this.entId = "";
		
		this.baseData;
		
		this.typeName = "";
		this.zizhiTypeList;
		this.appendixlist;
		
		this.appendixImg = "";
		
		this.leveshow = justep.Bind.observable(false);
		this.levelHtml = justep.Bind.observable("");
	};
	
		
	//接收修改数据
	Model.prototype.modelParamsReceive = function(event){
		
		this.leveshow.set(false);//不显示资质级别行
		$("span[xid=spanImg]").html("");
		
		var data = event.data.data;
		if(data){
			
			this.baseData = data;
			this.id = data.id;
			this.entId = data.entId;
			
			var appType = data.appType==null?"":data.appType;
			var zizhiName = data.zizhiName==null?"":data.zizhiName;
			var grantUnit = data.grantUnit==null?"":data.grantUnit;
			var awardUnit = data.awardUnit==null?"":data.awardUnit;
			var awardTime = data.awardTime==null?"":data.awardTime;
			var remark = data.remark==null?"":data.remark;
			
			
						
			var p_typeName = data.typeName;
			var p_zizhiTypeList = data.zizhiTypeList;
			var p_appendixlist = data.appendixlist;
			
			if(p_typeName){
				this.typeName = p_typeName;
				this.comp('zizhiType').val(p_typeName);
			}else{
				this.comp('zizhiType').val("");
			}
			
			//获取资质级别复选框
			if(p_zizhiTypeList){
				this.zizhiTypeList = p_zizhiTypeList;
				var res = zizhiTypeEvent(this.typeName,this.zizhiTypeList);
				if(res!=''){
					this.leveshow.set(true);//显示资质级别行
				}else{
					this.leveshow.set(false);//不显示资质级别行
				}
				this.levelHtml.set(res);//动态html内容
			}
			
			//获取资质附件
			if(p_appendixlist){
				this.appendixlist = p_appendixlist;
				var appendix = p_appendixlist[0];
				if(appendix){
					var appimg = appendix.path==null?"":appendix.path;
					if(appimg!=''){
						this.appendixImg = appimg;
						$("span[xid=spanImg]").html(getImageHTML(appimg));
					}
				}
			}else{
				this.appendixImg = "";
				$("span[xid=spanImg]").html(getImageHTML(""));
			}
			
			
			this.comp('zizhiName').val(zizhiName);
			this.comp('awardUnit').val(awardUnit);
			this.comp('awardTime').val(awardTime);
			this.comp('remark').val(remark);
			
		}
		
	};
	
	
	
	//下拉选择事件
	Model.prototype.zizhiTypeChange = function(event){
		var value = event.value;
		
		var res = zizhiTypeEvent(value,this.zizhiTypeList);
		if(res!=''){
			this.leveshow.set(true);
			this.levelHtml.set(res);
		}else{
			this.leveshow.set(false);
			this.levelHtml.set("");
		}
		
	};
	
	//下拉选项数据
	function zizhiTypeEvent(val,types){
		var self = this;
		
		var resHtml = '';
		if(val==''||val=='家庭农场'||val=='其他'){
			resHtml = '';
			localStorage.setItem("baseZizhiType", 0);
		}else{
			var typeflag =1;
			if(val=='龙头企业类'||val=='示范合作社'||val=='示范区、场'){
				typeflag = 1;
				localStorage.setItem("baseZizhiType", 1);
			}else if(val=='认证类'){
				typeflag = 2;
				localStorage.setItem("baseZizhiType", 2);
			}
			
			if(typeflag!=''){
				$.ajax({
					url : '/rlscsy/level_getLevelListByType.action',
					data : 'ids='+typeflag,
					type : 'post',
					async : false,
					dataType : 'json',
					success : function(result) {
					if (result) {
						localStorage.setItem("baseZizhiTypeData", JSON.stringify(result));
						
						for(var i=0;i<result.length;i++){
							var row = result[i];
							var value = row.levelId;
							var text = row.levelTitle;
							//用 zizhiType的remark字段保存 level的id值；如（"1,2,3,4"）
							
							////已有的类型级别；赋予选中效果
							var flags = 0;
							if(types!=null&&types.length>0){
								for(var j=0;j<types.length;j++){
									var type  = types[j];
									if(type){
										var levelId = type.levelId;
										if(levelId==value){
											//alert(value+"@"+levelId);
											flags = 1;
											break;
										}else{
											flags = 0;
										}
									}
								}
							}
							
							if(flags==1){
								//xid=\"checkbox"+value+"\"
								resHtml += "<input type=\"checkbox\" value=\""+value+"\" name=\"zizhiType.remark\"  checked=\"true\" id=\"chk"+value+"\"></input>"+text+"";
							}else{
								resHtml += "<input type=\"checkbox\" value=\""+value+"\" name=\"zizhiType.remark\"  id=\"chk"+value+"\"  ></input>"+text+"";
							}
							
							
						}
					}
					}
				});
				
			}
		}
		
		return resHtml;
	}
	
	
	/*function checkboxx(obj){
		$obj.attr();
		
		$obj.attr('checked')
	
	}*/



	//提交表单信息
	Model.prototype.verifyButton = function(event) {
		
		var self = this;
		
		var baseZizhiType = localStorage.getItem("baseZizhiType");
		var baseZizhiTypeData = JSON.parse(localStorage.getItem("baseZizhiTypeData"));
		
		
		var zizhiType = this.comp("zizhiType").val().trim();
		var zizhiName = this.comp("zizhiName").val().trim();
		//var grantUnit = this.comp("grantUnit").val().trim();
		var awardUnit = this.comp("awardUnit").val().trim();
		var awardTime = this.comp("awardTime").val().trim();
		var remark = this.comp("remark").val().trim();	
		
		var resm = "";
		if(baseZizhiType>0){
			if(baseZizhiTypeData.length>0){
				for(var i=0;i<baseZizhiTypeData.length;i++){
					var row = baseZizhiTypeData[i];
					var value = row.levelId;
					var text = row.levelTitle;
					//alert($("#chk"+value).is(':checked'));
					if($("#chk"+value).is(':checked')){
						resm += value+",";
					}
				}
				
				if(resm!=''){
					resm = resm.substring(0, resm.length-1);
				}
			}
		}
		
		//alert(resm);	
				
		if (!checkFormValue(zizhiType, '请选择资质类型!')) {
			return;
		}
		if (!checkFormValue(zizhiName, '请填写证书名称!')) {
			return;
		}
		if (!checkFormValue(awardUnit, '请填写颁发单位!')) {
			return;
		}
		if (!checkFormValue(awardTime, '请选择颁发时间!')) {
			return;
		}
		if (!checkFormValue(self.appendixImg, '请选择资质图片附件!')) {
			return;
		}
				
		var data = {
			"zizhiType.typeName" : zizhiType,
			"zizhiType.remark" : resm,
			"zizhi.id" : self.id,
			"zizhi.entId" : self.entId,
			"zizhi.zizhiName" : zizhiName,
			"zizhi.awardUnit" : awardUnit,
			"zizhi.awardTime" : awardTime,
			"zizhi.state" : 1,
			"zizhi.remark" : remark,
			"appendixList[0].appName" : self.appendixImg,
			"appendixList[0].path" : self.appendixImg
		}
		var url = "";
		if(this.id==""){
			url = '/rlscsy/zizhi_addZizhiAndAppend.action';
		}else{
			url = '/rlscsy/zizhi_updateZizhiAndAppend.action';
		}
		
		$.ajax({
			url : url,
	        type: "POST",
	        data: data,
	        dataType: "TEXT",
	        success: function (result) {
	        	if(result){	        			        					
					justep.Util.hint(result);						
					self.comp("windowReceiverEdit").windowEnsure({});				
	        	}
	        }
	    });
	};
	
	//非空验证及提示
	function checkFormValue(value,msg){
		if(value==null||value==""){
			justep.Util.hint(msg, {"type" : "danger"});
			return false;
		}
		return true;
	}

	
	var getImageHTML = function (img){
		if(img&&img!=""){
			img='<img src="/nytsyFiles/zizhi/'+img+'" height="30px"  width="40px"/>';
		}else{
			img="";
		}
		return img;
	};

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
				var params = {
					"uploadifyFileName" : data
				};
				var size = data.length;
				if(size>1398710){
					justep.Util.hint("上传图片大小不能超过1MB!", {"type" : "danger"});
					return;
				}else{
				    $.ajax({
				        type: "POST",
				        url: '/rlscsy/zizhiAppendix_uploadFileForBase64.action',
				        data:params,
				        dataType: 'TEXT',
				        async: false,//使用同步方式，目前data组件有同步依赖
				        cache: false,
				        success: function(data){	    
				            if(data=="fail"){
				            	justep.Util.hint("上传图片数据失败", {"type" : "danger"});					
							}else{
								self.appendixImg = data;
								$("span[xid=spanImg]").html(getImageHTML(data));								
							}
				        }
				    });
                }
			}
		} else {
			justep.Util.hint("请选择图片文件", {"type" : "danger"});
		}
        	
	};

	Model.prototype.buttonUploadClick = function(event){
		$("input[xid=inputImage]").click();	
	};

	Model.prototype.buttonRemoveClick = function(event){
		this.appendixImg = '';
		$("span[xid=spanImg]").html(getImageHTML(this.appendixImg));
	};


	//为上面提供各个JS验证方法提供.trim()属性   
	String.prototype.trim = function(){
        return this.replace(/\s+/g,"");//删除所有空格;
    }
	
	return Model;
});