define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	
	var typeImg;

	var proType;
	
	var receiver;
	
	var messageDialog;

	var Model = function(){
		this.callParent();
	};
	
	
	Model.prototype.windowReceiver1Receive = function(event) {
		proType = event.data.rowData;
		//this.comps("certificate").val(proType.certificate);
		this.comp("certificate").val(proType.certificate);
		
		this.comp("quantity").val(proType.quantity);
		
		this.comp("listed").val(proType.listed);
		
		this.comp("salearea").val(proType.salearea);
		
		typeImg = proType.typeImg;
		
		$("span[xid=spanImg]").html(getImageHTML(typeImg));	
		
	};

	Model.prototype.saveBtnClick = function(event){
		
		proType.certificate = this.comp("certificate").val();
		proType.quantity = this.comp("quantity").val();
		proType.listed = this.comp("listed").val();
		proType.salearea = this.comp("salearea").val();
		
		var params = {};
		for(var item in proType){
			params["proTypeQrcode."+item] = proType[item];
		}
		
		console.log(params);
		
		receiver = this.comp("windowReceiver1");
		messageDialog = this.comp("messageDialog");
		
		$.ajax({
            type: "POST",
            url: '/rlscsy/proTypeQrcode_updateProTypeQrcode.action',
            data:params,
            dataType: 'text',
            async: false,
            cache: false,
            success: function(data){
            	/*messageDialog.on('onClose', function(event) {
            		
				}, this);*/
            	justep.Util.hint(data);
            	receiver.windowEnsure(null);
            	/*messageDialog.show({
						message : data
					});*/
            	
            },
            error: function(){
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
			    $.ajax({
			        type: "POST",
			        url: '/rlscsy/elementApp_uploadFileDorForBase64.action',
			        data:params,
			        dataType: 'TEXT',
			        async: false,//使用同步方式，目前data组件有同步依赖
			        cache: false,
			        success: function(data){	    
			            if(data=="fail"){
			            	justep.Util.hint("上传图片数据失败", {"type" : "danger"});					
						}else{
							proType.typeImg = data;
							$("span[xid=spanImg]").html(getImageHTML(data));								
						}
			        }
			    });
                
			}
		} else {
			justep.Util.hint("请选择图片文件", {"type" : "danger"});
		}
        	
	};
	
	var getImageHTML = function (img){
		if(img&&img!=""){
			img='<img src="/nytsyFiles/protype/'+img+'" height="30px"  width="40px"/>';
		}else{
			img="";
		}
		return img;
	};
	
	Model.prototype.buttonUploadClick = function(event){
		$("input[xid=inputImage]").click();	
	};

	Model.prototype.buttonRemoveClick = function(event){
		typeImg = '';
		$("span[xid=spanImg]").html(getImageHTML(typeImg));
	};
	
	
	

	return Model;
});