define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
		this.checkId = "";
		this.appType = "1";
	};
	
	//接收修改数据
	Model.prototype.modelParamsReceive = function(event){
		var data = event.data.data;		
		if(data){
			this.checkId = data.checkId;
			this.appType = data.appType;	
		}
		this.comp("list1").refresh();		
	};
	
	Model.prototype.getImageURL = function(appUrl){	
		return require.toUrl("/nytsyFiles/element/"+appUrl);		
	};

	Model.prototype.masterDataCustomRefresh = function(event){
	    var masterData = event.source;	    
	    masterData.clear();	    	    
	    var queryParams = {};   
	    var fkId = this.checkId;
	    queryParams['elementApp.fkId'] = parseInt(fkId);	   
	    queryParams["elementApp.appType"]= this.appType;
	   	
	    $.ajax({
	        type: "GET",
	        url: '/rlscsy/elementApp_findAppList.action',
	        data:queryParams,
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	
	            masterData.loadData(data);//将返回的数据加载到data组件
	        },
	        error: function(x){
	            throw justep.Error.create("加载数据失败");
	        }
	    });	    
	
	};

	//
	Model.prototype.backButton = function(event) {
		this.comp("windowReceiverEdit").windowEnsure({});
	};

	function checkFormValue(value,msg){
		if(value==null||value==""){
			justep.Util.hint(msg, {"type" : "danger"});
			return false;
		}
		return true;
	}
	

	
	Model.prototype.inputImageChange = function(event){	
		//从选取文件对话框返回后，检查用户是否选择了文件，是否选择了图片文件		
		if(!event.target.files){
				return;
		}		
		var file = event.target.files[0];	
		//var fileSize = file.size;
		var appName = file.name;
		var self = this;
		if (/^image\/\w+$/.test(file.type)) {
			var reader = new FileReader(); 
			reader.readAsDataURL(file); 
			reader.onload = function(e){ 
	            var data =this.result; //就是base64
				var params = {
					"uploadifyFileName" : data,
					"elementApp.fkId" : self.checkId,
					"elementApp.appName" : appName,
					"elementApp.appType" : self.appType
				};
				var size = data.length;
				if(size>1398710){
					justep.Util.hint("上传图片大小不能超过1MB!", {"type" : "danger"});
					return;
				}else{
				
				    $.ajax({
				        type: "POST",
				        url: '/rlscsy/elementApp_uploadFileForBase64.action',
				        data:params,
				        dataType: 'TEXT',
				        async: false,//使用同步方式，目前data组件有同步依赖
				        cache: false,
				        success: function(data){	    
				            if(data=="fail"){
				            	justep.Util.hint("上传图片数据失败", {"type" : "danger"});					
							}else{
								self.agriImg = data;
								self.comp("masterData").refreshData();	
							}
				        }
				    });
                }
			}
		} else {
			justep.Util.hint("请选择图片文件", {"type" : "danger"});
		}
	};
	

	
	Model.prototype.button1Click = function(event){
		$("input[xid=inputImage]").click();	
	};
	

	
	Model.prototype.delClick = function(event){

		var self = this;
		var masterData = self.comp("masterData");
		var row = event.bindingContext.$object;			
		var ids = masterData.getValue("appId", row);
		
		justep.Util.confirm("确认要删除吗?", function(){
			$.ajax({
		       	  url:'/rlscsy/elementApp_deleteApps.action',
		       	  data:'ids='+ids,
		       	  type:'post',
		       	  dataType:'text',
		       	  async : false,
		       	  success : function(result) {
		       		justep.Util.hint(result);		
		       		masterData.refreshData();
				  }
		       }); 		
		});	
	
	};
	
	Model.prototype.showClick = function(event){
		var self = this;
		var masterData = self.comp("masterData");
		var row = event.bindingContext.$object;			
		var appUrl = masterData.getValue("appUrl", row);		
		var imgSrc = require.toUrl("/nytsyFiles/element/"+appUrl);		
		this.comp("windowDialogImg").open({"src":require.toUrl("./imgshow.w"),"data":{"imgSrc":imgSrc}});

	};
	
	
	Model.prototype.windowDialogImgReceive = function(event){
		this.comp("masterData").refreshData();	
	};
	

	
	return Model;
});