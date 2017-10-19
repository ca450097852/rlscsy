define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	
	//require("./geo");
	require("$UI/rlscsyapp/protype/geo");
	
	
	
	
	var Model = function(){
		this.callParent();
		
		this.baserow;
		this.baseoption;
	};
	
	var entId;
	var ptaId;
	var areaImg;
	
	var formUrl ;
	
	
	Model.prototype.saveBtnClick = function(event){
		var areaName = this.comp("areaName").val();
		var province = $('#s1').val();
		var city = $('#s2').val();
		var town = $('#s3').val();
		
		
		
		var areaAddr = this.comp("areaAddr").val();
		
		var scale = this.comp("scale").val();
		
		var lat = this.comp("lat").val();
		var lng = this.comp("lng").val();
		
		
		
		
		if(areaName==''){
			justep.Util.hint("请输入基地名称");	
			return;
		}
		
		if(province=='省份'){
			justep.Util.hint("请选择省份");	
			return;
		}
		if(city=='城市'){
			justep.Util.hint("请选择城市");	
			return;
		}
		if(town=='区、县'){
			justep.Util.hint("请选择区县");	
			return;
		}
		if(areaAddr==''){
			justep.Util.hint("请输入街道地址");	
			return;
		}
		
		if(scale==''){
			justep.Util.hint("请输入基地规模")
			return;
		}
		
		if(lat==''||lng==''){
			justep.Util.hint("请输入位置");
			return;
		}
		
		var formData = {'proTypeArea.areaName':areaName,'proTypeArea.province':province,'proTypeArea.city':city
						,'proTypeArea.town':town,'proTypeArea.areaAddr':areaAddr,'proTypeArea.scale':scale
						,'proTypeArea.lat':lat,'proTypeArea.lng':lng,'proTypeArea.areaImg':areaImg,'proTypeArea.entId':entId,'proTypeArea.ptaId':ptaId};
						
						
						
		var receiver = this.comp("windowReceiver");
		$.ajax({
	        type: "post",
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
	
	

	Model.prototype.windowReceiverReceive = function(event){
		var self = this;
		if(event.data.option=='update'){
			formUrl = '/rlscsy/proTypeArea_updateProTypeArea.action';
			
			var row = event.data.row;
			
			self.baserow = row;
			self.baseoption = 'update';
			
			
			ptaId = row.val("ptaId");
			entId = row.val("entId");
			areaImg = row.val("areaImg");
			
			this.comp("areaName").val(row.val("areaName"));
			this.comp("areaAddr").val(row.val("areaAddr"));
			
			this.comp("scale").val(row.val("scale"));
			this.comp("lng").val(row.val("lng"));
			this.comp("lat").val(row.val("lat"));
			
			var province = row.val("province");
			province = province==null?"省份":province;
			
			var city = row.val("city");
			city = city==null?"城市":city;
			
			var town = row.val("town");
			town = town==null?"区、县":town;
			
			$("#s1").val(province);
			$("#s1").change();	
			 $("#s2").val(city);		 
			 $("#s2").change();		 
			 $("#s3").val(town);
			
			
		}else{
			formUrl = '/rlscsy/proTypeArea_addProTypeArea.action';
			this.comp("areaName").val('');
			this.comp("areaAddr").val('');
			
			this.comp("scale").val('');
			this.comp("lng").val('');
			this.comp("lat").val('');
			
			preselect('广东省');
		}
		$("span[xid=spanImg]").html(getImageHTML(areaImg));
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
								areaImg = data;
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
		areaImg = '';
		$("span[xid=spanImg]").html(getImageHTML(areaImg));
	};
	
	


	Model.prototype.mapbtnClick = function(event){
		var lat = this.comp("lat").val();
		var lng = this.comp("lng").val();
		
		this.comp("windowDialog").open({
			"data":{
				lat:lat,
				lng:lng
			}
		})
	};
	
	


	
	


	Model.prototype.windowDialogReceive = function(event){
		var data = event.data;
		console.log(data);
		
		var t_lng = data.lng;
		var t_lat = data.lat;
		console.log(t_lng+"---"+t_lat);
		
		this.comp("lng").val(t_lng);
		this.comp("lat").val(t_lat);
	};
	
	


	
	


	Model.prototype.modelLoad = function(event){
		var self = this;
		
		setup();
		preselect('广东省');
		
		if(self.baseoption=='update'){
			var row = self.baserow;		
			var province = row.val("province");
			province = province==null?"省份":province;
			
			var city = row.val("city");
			city = city==null?"城市":city;
			
			var town = row.val("town");
			town = town==null?"区、县":town;
		
			$("#s1").val(province);
			$("#s1").change();	
			 $("#s2").val(city);		 
			 $("#s2").change();		 
			 $("#s3").val(town);
		}
		

	};
	
	


	
	


	return Model;
});