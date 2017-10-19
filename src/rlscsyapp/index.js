define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	var allData = require("./js/loadData");
	
	require("$UI/system/lib/cordova/cordova");
	require("cordova!phonegap-plugin-barcodescanner");
	
	require("cordova!cordova-plugin-geolocation");
	
	var Model = function() {
		this.callParent();
		this.contentName;
		this.tag;
		this.lastContentXid = "homeContent";
		
		this.ptqtotal = 0;//产品总数
		
		this.searchtxt = "";//查询关键字
	};
	
	Model.prototype.contents2ActiveChange = function(event){
		var to = event.to;
		if (to >= 1) {
			// 优化内存占用
			$('.x-window-container', this.comp('content2').$domNode).css('display', 'none');
			this.comp('navContainer' + (to + 1)).$domNode.css('display', 'block');
			this.comp('navContainer' + (to + 1)).load();
		}
	};

	// 图片路径转换
	Model.prototype.getImageUrl = function(url) {
		return require.toUrl(url);
	};

	
	/*
	 * 写首页图片数据缓存的代码 1、数据模型创建时事件
	 * 2、判断有没有localStorage，如果有显示localStorage中的内容，否则显示静态内容。
	 * 3、从服务端获取最新数据和图片，获取之后，更新界面并写入localStorage
	 */
	Model.prototype.modelModelConstruct = function(event) {
		/*
		 * 1、数据模型创建时事件 2、加载静态图片或从缓存中加载图片
		 */
		var carousel = this.comp("carousel1");

		var fImgUrl = localStorage.getItem("index_BannerImg_src");
		if (fImgUrl == undefined) {
			$(carousel.domNode).find("img").eq(0).attr({
				"src" : "./main/img/carouselBox61.jpg",
				"pagename" : "./detail.w"
			});
		} else {
			var fUrl = localStorage.getItem("index_BannerImg_url");
			$(carousel.domNode).find("img").eq(0).attr({
				"src" : fImgUrl,
				"pagename" : fUrl
			});
		}
	};

	Model.prototype.imgDataCustomRefresh = function(event) {
		/*
		 * 1、加载轮换图片数据
		 * 2、根据data数据动态添加carouse组件中的content页面 
		 * 3、如果img已经创建了，只修改属性
		 * 4、第一张图片信息存入localStorage
		 */
		var url = require.toUrl("./main/json/imgData.json");
		allData.loadDataFromFile(url, event.source, true);
		var me = this;
		var carousel = this.comp("carousel1");
		event.source.each(function(obj) {
			var fImgUrl = require.toUrl(obj.row.val("fImgUrl"));
			var fUrl = require.toUrl(obj.row.val("fUrl"));
			if (me.comp('contentsImg').getLength() > obj.index) {
				$(carousel.domNode).find("img").eq(obj.index).attr({
					"src" : fImgUrl,
					"pagename" : fUrl
				});
				if (obj.index == 0) {
					localStorage.setItem("index_BannerImg_src", fImgUrl);
					localStorage.setItem("index_BannerImg_url", fUrl);
				}
			} else {
				carousel.add('<img src="' + fImgUrl + '" class="tb-img1" pagename="' + fUrl + '"/>');//bind-click="openPageClick" 
			}
		});
	};

	
	//扫描二维码
	Model.prototype.buttonScanClick = function(event){	
		function onSuccess(result) {		
			var scanUrl = result.text;
			var index = scanUrl.indexOf('=');
			var dimenno = "";
			if(index>0){
				dimenno = scanUrl.substr(parseInt(index)+1);
				justep.Shell.showPage(require.toUrl('./sydetail.w'),{dimenno:dimenno});				
			}else{
				alert("扫描失败!不是有效的溯源二维码!");
			}			
		}
		
		function onError(error) {
			alert("扫描失败！" + error);
		}
		
		cordova.plugins.barcodeScanner.scan(onSuccess, onError);
	};
	
	
	Model.prototype.getImageUrl = function(row){
		var typeImg = row.val('typeImg');
		 typeImg = typeImg==""?"./main/img/nopic.jpg":"/nytsyFiles/protype/"+typeImg;
		return require.toUrl(typeImg);
	};
	
	/*
	 *获取种植类-产品数据
	 */
	Model.prototype.ptqDataCustomRefresh = function(event){
		
		var self = this;
	    var ptqData = event.source;
	    var offset = ptqData.offset;
	    var limit = ptqData.limit;	    
	    var page=1;
	    if(self.ptqtotal<offset){
	    	return;
	    }
	    if(offset>0){
	    	page = (offset/limit)+1;
	    }else{
	    	ptqData.clear();
	    }	    
	    
		var queryParams = {};
		queryParams['page'] = page;
		queryParams['rows'] = limit;
		
		if(self.searchtxt!=''){
			queryParams['param'] = self.searchtxt;
		}
	
       $.ajax({
            type: "POST",
            url: '/rlscsy/portal_Company_appFindPTQ.action',
            data:queryParams,
            dataType: 'json',
            async: false,
            cache: false,
            success: function(data){
            	self.ptqtotal = data.total;
            	ptqData.loadData(data,true);//将返回的数据加载到data组件
            },
            error: function(){
              throw justep.Error.create("请检查网络是否连接");
            }
        });
	};

	//向上划滑动--获取下一页
	Model.prototype.scrollViewPullUp = function(event){
		this.comp('ptqData').loadNextPageData();
	};

	//向下划滑动--刷新
	Model.prototype.scrollViewPullDown = function(event) {
		this.comp('ptqData').refreshData();
	};

	// 进入详细页
	Model.prototype.listClick = function(event) {
		/*
		 * 1、获取当前行 2、进入详细页面，并传值rowid
		 * 代码如下：
		 */	   	   
	   //justep.Shell.showPage(require.toUrl("../zqsy/portal/sydetail.jsp"), {dimenno : this.comp("ptqData").getValue("dimenno")});
	   //window.location.href = "/zqsy/trace.jsp?code="+this.comp("ptqData").getValue("dimenno");
	   
	   var url = require.toUrl('./sydetail.w');
	   var dimenno = this.comp("ptqData").getValue("dimenno");
	   //alert(dimenno);
	  
	   var params = {dimenno : dimenno};
	   justep.Shell.showPage(url, params);
	   	    
	};
	

	Model.prototype.button1Click = function(event){
		this.comp('popMenu').show();
	};
	

	//企业用户登录
	Model.prototype.button2Click = function(event){	   
	   var url = require.toUrl('./login.w');	  
	   var params = {};
	   justep.Shell.showPage(url, params);
	};
	
	
	Model.prototype.button3Click = function(event){
		  navigator.app.exitApp();
	};
	
	//查询
	Model.prototype.searchbuttonClick = function(event){
		var searchtxt = this.comp("searchtxt").val();
		this.searchtxt = searchtxt;
		this.comp('ptqData').refreshData();
	};
	
	//搜索--扫一扫
	Model.prototype.handbuttonClick = function(event){
		function onSuccess(result) {		
			var scanUrl = result.text;
			var index = scanUrl.indexOf('=');
			var dimenno = "";
			if(index>0){
				dimenno = scanUrl.substr(parseInt(index)+1);
				justep.Shell.showPage(require.toUrl('./sydetail.w'),{dimenno:dimenno});				
			}else{
				alert("扫描失败!不是有效的溯源二维码!");
			}			
		}
		function onError(error) {
			alert("扫描失败！" + error);
		}
		cordova.plugins.barcodeScanner.scan(onSuccess, onError);
	};
	
	//监管用户登录
	Model.prototype.button4Click = function(event){
		var url = require.toUrl('./jglogin.w');	  
	    var params = {};
	    justep.Shell.showPage(url, params);
	};
	
	function onSuccess(position){
       var longitude =  position.coords.longitude;
       var latitude =  position.coords.latitude;
       
       localStorage.setItem("longitude", longitude);
       localStorage.setItem("latitude", latitude);
    }
 
    function onError(){
       //localStorage.setItem("longitude", "113.301941");
       //localStorage.setItem("latitude", "23.13382");
    }
    

	Model.prototype.modelLoad = function(event){
		/*先获取定位（经纬度）*/
		//navigator.geolocation.getCurrentPosition(onSuccess, onError);
	};
	
	return Model;
});