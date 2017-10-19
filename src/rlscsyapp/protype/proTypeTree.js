
define(function(require){
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	
	var typeidArr ;

	var Model = function(){
		this.callParent();
	};

	Model.prototype.proTypeDataCustomRefresh = function(event){
	
		typeidArr = new Array();
	
		var proTypeData = event.source;
		
		
//		$.ajax({
//	        type: "GET",
//	        url: '/rlscsy/proType_getEntTypeTree.action',
//	        dataType: 'json',
//	        async: false,//使用同步方式，目前data组件有同步依赖
//	        cache: false,
//	        success: function(data){	    
//	        	if(data){
//	        		for(var i=0;i<data.length;i++){
//	        			//typeidArr.push(data[i].id);
//	        			oldTypeIdArr.push(data[i].id);
//	        		}
//	        	}
//	        },
//	        error: function(x){
//	            throw justep.Error.create("请检查网络是否连接");
//	        }
//	    });	
		
	
		$.ajax({
	        type: "GET",
	        url: '/rlscsy/proType_getList.action',
	        dataType: 'json',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	
	        	if(data){
	        		for(var i=0;i<data.length;i++){
	        			var parentRow = proTypeData.getRowByID(data[i].upcateId);
	        			var options;
	        			if(parentRow){
	        				options = {
	        						parent:parentRow,
	        						defaultValues:[data[i]]
	        				};
	        			}else{
	        				options = {
	        						defaultValues:[data[i]]
	        				};
	        			}
	        			
	        			proTypeData.newData(options);
	        		}
	        	}
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	
	};
	
	
	
	Model.prototype.checkboxChange = function(event){
		var row = event.bindingContext.$object;
		var typeId = row.val('typeId');
		var typeName = row.val('typeName');
		if(event.checked){
			typeidArr.push({'typeId':typeId,'typeName':typeName});
		}else{
			var index;
			for(var i=0;i<typeidArr.length;i++){
				if(typeId==typeidArr[i].typeId){
					index = i;
				}
			}
		
			typeidArr.splice(index, 1);
		}
	};
	
	justep.checkExit = function(typeId){
		for(var i=0;i<typeidArr.length;i++){
			if(typeId==typeidArr[i].typeId){
				return true;
			}
		}
		return false;
	}
	
//	justep.checkExit2 = function(typeId){
//		for(var i=0;i<oldTypeIdArr.length;i++){
//			if(typeId==oldTypeIdArr[i]){
//				return false;
//			}
//		}
//		return true;
//	}

	Model.prototype.saveBtnClick = function(event){
		
		var windiwDialog = this.comp("windowDialog");
		windiwDialog.open({
			data:{
				items:typeidArr
			}
		});
		
		return;
		//判断新增产品
		var addArr = new Array();
		var delArr = new Array();
		
		
		
		var params = {};
		
		var receiver = this.comp("windowReceiver");
		$.ajax({
	        type: "GET",
	        url: '/rlscsy/record_addOrDelRecord.action',
	        data:params,
	        dataType: 'text',
	        async: false,//使用同步方式，目前data组件有同步依赖
	        cache: false,
	        success: function(data){	    
	        	if(data){
	        		justep.Util.hint(data);
	        		receiver.windowEnsure(null);
	        	}
	        },
	        error: function(x){
	            throw justep.Error.create("请检查网络是否连接");
	        }
	    });	
		
		
	};

	Model.prototype.windowReceiverReceive = function(event){
		var proTypeData = this.comp("proTypeData");
		proTypeData.clear();
		proTypeData.refreshData();
        
	};


	return Model;
});