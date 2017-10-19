define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
	};
	
	//1 ：种植时候   2：采收时间
	var flag = 1;
	
	var formUrl;
	
	var ptaId;
	var maId;
	var maImg = '';
	
	
	Model.prototype.startTimeFocus = function(event){
		var comp = this.comp("datePicker")
		comp.set('type','date');
		comp.show();
		comp.setValue(new Date());
		
		flag = 1;
	};

	Model.prototype.datePickerOK = function(event){
	
		var comp = event.source;
		var value = comp.getValue();
		if(value instanceof Date){
			value= justep.Date.toString(value, "yyyy-MM-dd");
		}else value = "";
	
		if(flag == 1){
			this.comp("startTime").val(value);
		}else if(flag == 2){
			this.comp("getTime").val(value);
		}
	};

	Model.prototype.getTimeFocus = function(event){
		var comp = this.comp("datePicker")
		comp.set('type','date');
		comp.show();
		comp.setValue(new Date());
		
		flag = 2;
	};

	Model.prototype.massifDataCustomRefresh = function(event){
		
		$.ajax({
	        type: "POST",
	        url: '/rlscsy/proTypeQrcode_getLoginProType.action',
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	  
	        	if(data){
	        		$('select').each(function(){
	        			if($(this).attr('xid')=='ptqId'){
	        				for(var i=0;i<data.length;i++){
			        			var row = data[i];
			        			$(this).append('<option value="'+row.value+'">'+row.text+'</option>');
			        		}
	        			}
	        		});
	        	}
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	  

	};

	Model.prototype.windowReceiverReceive = function(event){
		var data = event.data;
		
		if(data.option=='add'){
			ptaId = data.ptaId;
			console.log(ptaId);
			formUrl = '/rlscsy/massif_addMassif.action';	
			maImg = '';
			
			this.comp("title1").set({
				"title":"添加地块"
			})
			
			this.comp("maName").val("");
			this.comp("ptqId").val("");
			this.comp("maAcreage").val("");
			this.comp("startTime").val("");
			this.comp("getTime").val("");
			this.comp("state").val("");
			
		}else if(data.option=='update'){
			formUrl = '/rlscsy/massif_updateMassif.action';	
			
			var row = data.row;
			console.log(row);
			
			ptaId = row.val('ptaId');
			maId = row.val('maId');
			maImg = row.val('maImg');
			
			this.comp("maName").val(row.val("maName"));
			this.comp("ptqId").val(row.val("ptqId"));
			this.comp("maAcreage").val(row.val("maAcreage"));
			this.comp("startTime").val(row.val("startTime"));
			this.comp("getTime").val(row.val("getTime"));
			this.comp("state").val(row.val("state"));
			
			
			this.comp("title1").set({
				"title":"修改地块"
			})
		}
		
		$("span[xid=spanImg]").html(getImageHTML(maImg));
		
	};

	Model.prototype.saveMassifClick = function(event){
	
		var maName = this.comp("maName").val();
		var ptqId = this.comp("ptqId").val();
		var maAcreage = this.comp("maAcreage").val();
		var startTime = this.comp("startTime").val();
		var getTime = this.comp("getTime").val();
		var state = this.comp("state").val();
		
		if(maName==''){
			justep.Util.hint("请输入地块名称");
			return;
		}
		
		if(maAcreage==''){
			justep.Util.hint("请输入地块面积");
			return;
		}
		
		if(startTime==''){
			justep.Util.hint("请输入种植时间");
			return;
		}
		if(getTime==''){
			justep.Util.hint("请输入采收时间");
			return;
		}
		
		
		var formData = {'massif.ptaId':ptaId,'massif.maId':maId,'massif.maImg':maImg,'massif.maName':maName,
						'massif.ptqId':ptqId,'massif.maAcreage':maAcreage,'massif.startTime':startTime,'massif.getTime':getTime,'massif.state':state};
		
		
		var receiver = this.comp("windowReceiver");
		$.ajax({
	        type: "POST",
	        url: formUrl,
	        data:formData,
	        dataType: 'text',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	  
	        	justep.Util.hint(data);
	        	receiver.windowEnsure(null);
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	  

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
				        url: '/rlscsy/elementApp_uploadFileForBase64.action',
				        data:params,
				        dataType: 'TEXT',
				        async: false,//使用同步方式，目前data组件有同步依赖
				        cache: false,
				        success: function(data){	    
				            if(data=="fail"){
				            	justep.Util.hint("上传图片数据失败", {"type" : "danger"});					
							}else{
								maImg = data;
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
	
	var getImageHTML = function (img){
		if(img&&img!=""){
			img='<img src="/nytsyFiles/element/'+img+'" height="30px"  width="40px"/>';
		}else{
			img="";
		}
		return img;
	};
	
	Model.prototype.buttonUploadClick = function(event){
		$("input[xid=inputImage]").click();	
	};

	Model.prototype.buttonRemoveClick = function(event){
		maImg = '';
		$("span[xid=spanImg]").html(getImageHTML(maImg));
	};
	
	
	

	return Model;
});