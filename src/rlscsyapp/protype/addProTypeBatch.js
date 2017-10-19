define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");

	var Model = function(){
		this.callParent();
		this.entId;
	};
	

	Model.prototype.saveBatchClick = function(event){
	
		var ptqId = this.comp("ptqId").val();
		
		if(ptqId==''){
			justep.Util.hint('请选择品种名称');
			return;
		}
	
		if(this.comp("batchName").val()==''){
			justep.Util.hint('请输入批次名称');
			return;
		}
		
		if(this.comp("batchTime").val()==''){
			justep.Util.hint('请选择生产日期');
			return;
		}
	
		var self = this;
		var data = {'proTypeBatch.entId':this.entId,'proTypeBatch.ptqId':ptqId,'proTypeBatch.batchName':this.comp("batchName").val(),'proTypeBatch.batchTime':this.comp("batchTime").val()};
		
		
		var messageDialog = this.comp("messageDialog");
		var receiver = this.comp("windowReceiver");
		$.ajax({
            type: "GET",
            url: '/rlscsy/proTypeBatch_addProTypeBatch.action',
            data:data,
            dataType: 'text',
            async: false,
            cache: false,
            success: function(result){
            	justep.Util.hint(result);
	        	receiver.windowEnsure(null);
            },
            error: function(){
              throw justep.Error.create("请检查网络是否连接");
            }
        });
		
	};


	Model.prototype.batchTimeFocus = function(event){
		var comp = this.comp("batchTimePicker")
		comp.set('type','date');
		comp.show();
		comp.setValue(new Date());
	};


	Model.prototype.batchTimePickerOK = function(event){
		var comp = event.source;
		var value = comp.getValue();
		if(value instanceof Date){
			value= justep.Date.toString(value, "yyyy-MM-dd");
		}else value = "";
		this.comp("batchTime").val(value);
	};


	Model.prototype.proTypeCustomRefresh = function(event){
		var masterData = event.source;	   
		var company = localStorage.getItem("company");	      	    
	    var companyObj = JSON.parse(company);	
		this.entId = companyObj.comId;
		
		$.ajax({
            type: "GET",
            url: '/rlscsy/proTypeQrcode_findProTypeQrcode.action?proTypeQrcode.entId='+this.entId,
            dataType: 'json',
            async: false,
            cache: false,
            success: function(result){          	
            console.info("result="+result);
            	if(result){
            		//masterData.loadData(result,true);        
            		$('select').each(function(){
		            			if($(this).attr('xid')=='ptqId'){
		            				for(var i=0;i<result.length;i++){
				            			var row = result[i];
				            			$(this).append('<option value="'+row.ptqId+'">'+row.typeName+'</option>');
				            		}
		            			}
		            		});  		
            	}           	          	
            },
            error: function(){
              throw justep.Error.create("请检查网络是否连接");
            }
        });
       
	};


	Model.prototype.windowReceiverReceive = function(event){
		this.comp("batchName").val('');
		this.comp("batchTime").val('');
		
		console.info("windowReceiverReceive");
	};


	return Model;
});