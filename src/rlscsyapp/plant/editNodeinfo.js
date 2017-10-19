define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
		
		this.nodeinfo;
		this.option;
		
		this.nodeImg;
		
	};
	
	//接收修改数据
	Model.prototype.modelParamsReceive = function(event){
		var self = this;
		var nodeinfo = event.params.nodeinfo;
		var option = event.params.option;
		
		this.nodeinfo = nodeinfo;
		this.option = option;
		
		
		this.comp("niName").val('');
		this.comp("crttime").val('');
		
		
		
		if(option=='update'){
			for(var item in nodeinfo){
				if(item){
					var xid = item.split('.')[1];
					if($('input[xid="'+xid+'"],select[xid="'+xid+'"],textarea[xid="'+xid+'"]').length!=0)
						self.comp(xid).val(nodeinfo[item]);
					if(item=='nodeImg'){
						self.nodeImg = nodeinfo[item];
						$("span[xid=spanImg]").html(getImageHTML(nodeinfo[item]));
					}
						
				}
			}
		}
	};


	//提交表单信息
	Model.prototype.verifyButton = function(event) {
		var self = this;
		var niName = this.comp("niName").val();
		var crttime = this.comp("crttime").val();
		
		if (!checkFormValue(niName, '请填写节点名称!')) {
			return;
		}
		if (!checkFormValue(crttime, '请选择节点日期!')) {
			return;
		}
		/*if (!checkFormValue(this.nodeImg, '请上传节点图片!')) {
			return;
		}*/
			
		var params = this.nodeinfo;
		
		var fieldsDiv = this.getElementByXid("div2");
		$(fieldsDiv).find('input,select,textarea').each(function(){
			var name = $(this).attr('xid');
			var value = $(this).val();
			params['nodeinfo.'+name] = value;
			
			alert(name+"-------"+value);
		});
		
		params['nodeinfo.nodeImg'] = this.nodeImg;
		
		alert("this.nodeImg-------"+this.nodeImg);
		
		var url = '/rlscsy/nodeinfo_addNodeinfo.action';
		if(this.option=='update'){
			url = '/rlscsy/nodeinfo_updateNodeinfo.action';
		}
		
		$.ajax({
			url : url,
			data : params,
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(result) {
				if(result){
					justep.Util.hint(result);
					self.comp("windowReceiver2").windowEnsure({"option":"refresh"})
				}
			}
		});
		
	};

	function checkFormValue(value,msg){
		if(value==null||value==""){
			justep.Util.hint(msg, {"type" : "danger"});
			return false;
		}
		return true;
	}

	
	
	var getImageHTML = function (img){
		if(img&&img!=""){
			img='<img src="/nytsyFiles/element/'+img+'" height="30px"  width="40px"/>';
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
				        url: '/rlscsy/elementApp_uploadFileForBase64.action',
				        data:params,
				        dataType: 'TEXT',
				        async: false,//使用同步方式，目前data组件有同步依赖
				        cache: false,
				        success: function(data){	    
				            if(data=="fail"){
				            	justep.Util.hint("上传图片数据失败", {"type" : "danger"});					
							}else{
								self.nodeImg = data;
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
		this.nodeImg = '';
		$("span[xid=spanImg]").html(getImageHTML(this.nodeImg));
	};

	return Model;
});