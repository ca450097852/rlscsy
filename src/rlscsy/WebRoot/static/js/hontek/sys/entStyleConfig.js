var basePath = "";
var scType = 1;//1门户；2企业会员；3监管; 默认为1；
var baseEntId = "";//当前登录主体id
var styleConfigData;//风格列表
var entStyle={};//当前的风格关系

var formUrl = "entStyle_addEntStyle.action";//当前的风格关系为空，添加

var baseScId = "";//当前已选中的风格Id；
$(function() { 	 	
	
	basePath = $("#basePath").val();
	//获取数据
	baseEntId = $("#hrentId").val();

	changeType(1);//打开时，默认是进行门户的风格设置；
	 
	 
	 //网站设置--（logo图片上传）
	 $("#logoImageFile").uploadify({
			'swf' : 'uploadify/uploadify.swf',
			'fileObjName' : 'uploadify',
			'uploader' : 'entStyleUp_uploadFile.action;jsessionid='+$('#jsessionid').val(),
			'buttonText' : '上传logo',
			'height' : 20,
			'width' : 100,
			'multi' : false,
			'fileSizeLimit' : 1024,
			'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
	        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
			'queueID' : 'fileQueue',
			'multi' : true,
			'removeCompleted' : false,
			'onUploadSuccess' : function(file, data, response) {
				if(data){
					var fpath = "/nytsyFiles/entstyle/";
					$('input[name="entStyle.logoImage"]').val(data);
					var img_html = "<a rel=\"previewLogoImg\" href=\""+fpath+data+"\"><img alt=\"预览\" src=\""+fpath+data+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
					
					if(img_html){
						$("#fileQueue").html(img_html);
						
						$("a[rel=previewLogoImg]").fancybox({
							'transitionIn'		: 'none',
							'transitionOut'		: 'none',
							'titlePosition' 	: 'over',
							'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
								return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
							}
						});
						
					}
					
				}
			}
		});
	 
	 
	 
	//网站设置--（banner图片上传）
	 $("#bannerFile").uploadify({
			'swf' : 'uploadify/uploadify.swf',
			'fileObjName' : 'uploadify',
			'uploader' : 'bannerUp_upFile.action;jsessionid='+ $('#jsessionid').val(),
			'formData' : {"entId":baseEntId,"position":1},
			'buttonText' : '上传图片',
			'height' : 20,
			'width' : 100,
			'multi' : false,
			'fileSizeLimit' : 2048,
			'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
	        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
			'queueID' : 'bannerfileQueue',
			'multi' : true,
			'removeCompleted' : true,
			'onUploadSuccess' : function(file, data, response) {
				//alert(data);
			 },
			'onUploadComplete' : function(file) {
				loadBanner();
	        }
		});
	 
	//网站设置--（banner图片上传）
	 $("#advertFile").uploadify({
			'swf' : 'uploadify/uploadify.swf',
			'fileObjName' : 'uploadify',
			'uploader' : 'bannerUp_upFile.action;jsessionid='+ $('#jsessionid').val(),
			'formData' : {"entId":baseEntId,"position":2},
			'buttonText' : '上传图片',
			'height' : 20,
			'width' : 100,
			'multi' : false,
			'fileSizeLimit' : 2048,
			'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
	        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
			'queueID' : 'advertfileQueue',
			'multi' : true,
			'removeCompleted' : true,
			'onUploadSuccess' : function(file, data, response) {
				//alert(data);
			 },
			'onUploadComplete' : function(file) {
				loadBanner();
	        }

		});
	 
	 
});
			
	
	//切换风格类型		     
	function changeType(num){
		scType = num;
		var cn = parseInt(num);
		for(var i=1;i<=3;i++){//风格类型选中效果
			if(i==cn){
				$("#changeType"+i).addClass("current");
			}else{
				$("#changeType"+i).removeClass("current");
			}
		}
		
		entStyle={};//当前的风格关系
		baseScId = "";//当前已选中的风格Id；
		
		getEntStyleInfo();////查找出关系表中数据
		
		getStyleConfig();//获取风格主题列表
		
		deCodeEntStyle();	
		
		if(num==1){//选中门户类型
			$("#bannerHtml").show();
			$("#advertHtml").show();
			
			loadBanner();//加载主体的广告图片；
			
		}else{
			$("#bannerHtml").hide();
			$("#advertHtml").hide();
		}
		
	}	
	
	//查找出关系表中数据
	function getEntStyleInfo(){
		$.ajax( {
			url : 'entStyle_getEntStyleInfo.action?entId='+baseEntId+'&scType='+scType,
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result!=null&&result!=""){
					//console.info("result=="+result);
					//entStyle = result[0];
					entStyle = result;
					formUrl = "entStyle_updateEntStyle.action";
					baseScId = entStyle.scId;////当前已选中的风格Id；
				}else{
					formUrl = "entStyle_addEntStyle.action";
					entStyle=null;
				}	
			}
		});
	}
	
	//按类型查找风格主题列表
	function getStyleConfig(){
		
		var queryParams = {};
		queryParams["styleConfig.scType"]=scType;
		
		
		$.ajax( {
			url : 'styleconfig_findPage.action',
			data:queryParams,
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result){
					var rows = result.rows;
					var scImgHtml = '';
					for(var i=0;i<rows.length;i++){
						var styleconfig = rows[i];
						if(styleconfig){
							var scId = styleconfig.scId;
							var scImg = styleconfig.scImg==null?"":styleconfig.scImg;
							scImg = scImg==""?"/lvdunwang/company/images/d01.png":"/nytsyFiles/styleconfig/"+scImg;
							var scName = styleconfig.scName==null?"":styleconfig.scName;
							var checked = '';
							if(baseScId!=null&&baseScId==scId){
								checked = 'checked="checked"';
							}
							var img = '<a rel="previewScImg" href="'+scImg+'" title="'+scName+'"><img alt="预览"  src="'+scImg+'" width="170px" height="127px" title="'+scName+'" /></a>';
							scImgHtml += '<li>\
							    <span>'+img+'</span>\
							    <h2>'+scName+'</h2>\
							    <p>\
							    <input type="checkbox" class="scImg_checkbox" name="styleconfig"  value="'+scId+'" '+checked+'/>\
							    </p>\
							    </li>';
						}
					}
					
					if(scImgHtml!=''){
						$('#stylelist').html(scImgHtml);
						
						//复选框的单选效果
						$('.scImg_checkbox').click(function(){
							if($(this).is(':checked')){
								$('.scImg_checkbox').attr('checked',false);
								$(this).attr('checked',true);
								baseScId = $(this).val();
								$("input[name='entStyle.scId']").val(baseScId);
								//alert(baseScId);
							}else{
								baseScId = "";
								$('.scImg_checkbox').attr('checked',false);
							}
						});
						
						$("a[rel=previewScImg]").fancybox({
							'transitionIn'		: 'none',
							'transitionOut'		: 'none',
							'titlePosition' 	: 'over',
							'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
								return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
							}
						});
						
					}
				}
			}
		});
		
	}
	

	
	/**
	 * 风格设置表单
	 * @return
	 */
	function deCodeEntStyle(){
		
		if(entStyle!=null&&entStyle!=""){
			$("input[name='entStyle.scId']").val(baseScId);
			$("input[name='entStyle.entId']").val(baseEntId);
			$("input[name='entStyle.scType']").val(scType);
			
			$("input[name='entStyle.esId']").val(entStyle.esId);
			$("input[name='entStyle.logoImage']").val(entStyle.logoImage);
			$("input[name='entStyle.bottomInfo']").val(entStyle.bottomInfo);
			$("input[name='entStyle.userId']").val(entStyle.userId);
			$("input[name='entStyle.createTime']").val(entStyle.createTime);
			$("input[name='entStyle.remark']").val(entStyle.remark);
			$("input[name='entStyle.loginView']").val(entStyle.loginView);
			
			
			if(entStyle.logoImage&&entStyle.logoImage!=''){
				var fpath = "/nytsyFiles/entstyle/";
				$('input[name="entStyle.logoImage"]').val(entStyle.logoImage);
				var img_html = "<a rel=\"preview2Img\" href=\""+fpath+entStyle.logoImage+"\"><img alt=\"预览\" src=\""+fpath+entStyle.logoImage+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
				$("#fileQueue").html(img_html);
				$("a[rel=preview2Img]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}else{
				$("#fileQueue").html("");
			}
			
			
		}else{
			$("#interAccountForm").form("reset");
			
			$("input[name='entStyle.scId']").val(baseScId);
			$("input[name='entStyle.entId']").val(baseEntId);
			$("input[name='entStyle.scType']").val(scType);
			
			$("#fileQueue").html("");
		}	
			
		
	}
	
	
	
	//查找主体的广告列表
	function loadBanner(){
		
		var queryParams = {};
		queryParams["banner.entId"]=baseEntId;
		
		$.ajax( {
			url : 'banner_findList.action',
			data:queryParams,
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result){
					var rows = result.rows;
					var bannerHtml = '';
					var advertHtml = '';
					for(var i=0;i<rows.length;i++){
						var banner = rows[i];
						if(banner){
							var imgId = banner.imgId;
							var position = banner.position;
							var imgUrl = banner.imgUrl==null?"":banner.imgUrl;
							imgUrl = imgUrl==""?"/lvdunwang/company/images/d01.png":"/nytsyFiles/banner/"+imgUrl;
							var imgTitle = banner.imgTitle==null?"":banner.imgTitle;
							
							var img = '<a rel="previewBnImg" href="'+imgUrl+'" title="'+imgTitle+'"><img alt="预览"  src="'+imgUrl+'" width="170px" height="127px" title="'+imgTitle+'" /></a>';
							
							if(position==1){
								img = '<a rel="previewBnImg" href="'+imgUrl+'" title="'+imgTitle+'"><img alt="预览"  src="'+imgUrl+'" width="170px" height="127px" title="'+imgTitle+'" /></a>';
								bannerHtml += '<li>\
								    <span>'+img+'</span>\
								    <h2>'+imgTitle+'</h2>\
								    <p>\
								    <a href="javascript:void(0)" onclick="deleteBanner('+imgId+');">删除</a>\
								    </p>\
								    </li>';
							}else if(position==2){
								img = '<a rel="previewAdImg" href="'+imgUrl+'" title="'+imgTitle+'"><img alt="预览"  src="'+imgUrl+'" width="170px" height="127px" title="'+imgTitle+'" /></a>';
								advertHtml += '<li>\
								    <span>'+img+'</span>\
								    <h2>'+imgTitle+'</h2>\
								    <p>\
								    <a href="javascript:void(0)" onclick="deleteBanner('+imgId+');">删除</a>\
								    </p>\
								    </li>';
							}
							
						}
					}
					
					
					$('#bannerlist').html(bannerHtml);
					$('#advertlist').html(advertHtml);
					if(bannerHtml!=''){
						$("a[rel=previewBnImg]").fancybox({
							'transitionIn'		: 'none',
							'transitionOut'		: 'none',
							'titlePosition' 	: 'over',
							'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
								return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
							}
						});
						
					}
					
					if(advertHtml!=''){
						$("a[rel=previewAdImg]").fancybox({
							'transitionIn'		: 'none',
							'transitionOut'		: 'none',
							'titlePosition' 	: 'over',
							'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
								return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
							}
						});
						
					}
					
				}
			}
		});
		
	}
	
	//提交
    function submitForm(){
    	
    	//console.info("formUrl="+formUrl);
    	//console.info("baseScId="+baseScId);
    	//console.info("baseEntId="+baseEntId);
    	//console.info("scType="+scType);
    	if(baseScId==""){
    		$.messager.show({ title : '提示', msg : '请选择风格!' });  
    		return;
    	}else{
    		$("input[name='entStyle.scId']").val(baseScId);
    	}
    	
    	if($('#interAccountForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#interAccountForm').form('submit', {
			url : formUrl,
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {
				$.messager.show({ title : '提示', msg : result });   	
				parent.$.messager.progress('close');	
			}
		});           		       	 
    }
	
    
    //删除广告图片
    function deleteBanner(id){
    	$.ajax({
	        url: 'banner_delete.action',
	        type: "POST",
	        data: {ids:id},
	        dataType: "TEXT",
	        success: function (result) {
	        	loadBanner();    	         	
	        }
	    });	
    }