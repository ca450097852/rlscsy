define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	var allData = require("./js/loadData");

	require("$UI/system/lib/cordova/cordova");
	require("cordova!phonegap-plugin-barcodescanner");
	
	var Model = function() {
		this.callParent();
		this.contentName;
		this.tag;
		this.lastContentXid = "homeContent";
		
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


	Model.prototype.getImageUrl = function(row){
		var typeImg = row.val('typeImg');
		 typeImg = typeImg==""?"./main/img/nopic.jpg":"/nytsyFiles/protype/"+typeImg;
		return require.toUrl(typeImg);
	};

	

	Model.prototype.button1Click = function(event){

	};

	

	Model.prototype.openPage = function(event){
		var url = event.source.$domNode.attr('url');				
		if(url.substr(0,1) == "/")
			url = '$UI' + url;
		justep.Shell.showPage(require.toUrl(url));

	};

	

	Model.prototype.exitApp = function(event){

		exitSession();//清除服务器session
		
		//清除本地登录的缓存
		localStorage.setItem("loginCompanyUser", "");
		localStorage.setItem("company", "");
		localStorage.setItem("node", "");
		localStorage.removeItem("loginCompanyUser");
		localStorage.removeItem("company");
		localStorage.removeItem("node");
		
		//调整到欢迎首页
		justep.Shell.showPage(require.toUrl('./index.w'));
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

	
	function exitSession(){
		$.ajax({
			url : '/rlscsy/app_exitSession.action',
	        type: "POST",
	        dataType: "JSON",
	        cache: false
		 });
	}

	return Model;
});