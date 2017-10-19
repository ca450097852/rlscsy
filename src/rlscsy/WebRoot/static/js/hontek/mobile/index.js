var entCode ;
	$(function(){
		
		entCode = $("#entCode").val();
		$('nav a').bind('click',function(){
			if(entCode=="")
				return;
			$('#mainCenter').html("");//清空内容
			var obj = $(this);
			//alert(obj.attr("class").substring(0,2));
			$('nav a').removeClass("active");
			obj.addClass("active");

			var sType = obj.attr("id");
			if("a1"==sType){
				companyInfo();
			}else if("a2"==sType){
				$("#h_appType").val(1);
				zzInfo();
			}else if("a3"==sType){
				proctionInfo();
			}else if("a4"==sType){
				proInfo();
			}
		});
		$("#a1").click();
	});

	function companyInfo(){
		var cond = {};
		cond["entCode"]=entCode;
		$.post('mbent_getCompanyInfoByEntCode.action',cond,function(result){
			var com = result[0];
			
			for(var item in com){
				if($('#'+item).length>0){
					$('#'+item).html(com[item]);
				}
			}
			$('#mainCenter').html($('#div_a1').html());
		},'JSON');
	}

	function zzInfo(){
		var cond = {};
		cond["entCode"]=entCode;
		//cond["appType"]=$("#h_appType").val();
		cond["rows"]=1000;
		$('#mainCenter').html("");//清空内容
		$.post('mbzizhi_findZizhiPagerListforMobile.action',cond,function(result){
			
			$('#container').html("");
			for(var i=0;i<result.length;i++){
				var obj = result[i];
				var content = "<LI>"+
							  	"<div class=libox>"+
						 		"<a href='/nytsyFiles/zizhi/"+obj.path+"'><IMG src='/nytsyFiles/zizhi/"+obj.path+"'></a>"+
							    "<p>"+obj.appName+"</p>"+
							    "</A>"+
							    "<DIV class=info><span>"+obj.zName+"</span>"+
							    "</DIV>"+
							    "</div>"+
							    "</LI>";
				$('#container').append(content);
				content="";
			}
							
			$('#mainCenter').html($('#div_a2').html());
			zzInit();
		},'JSON');
	}

	function proInfo(){
		//$('#mainCenter').html($('#div_a4').html());
		var cond = {};
		cond["ids"]=entCode;
		$.post('mbsupervise_findSuperviseList.action',cond,function(result){
			var ht = "<div class='news_list'><ul>";			
			for(var i=0;i<result.length;i++){
				var supervise = result[i];
				ht+="<li><a onclick='showSupervise("+supervise.supId+")'>"+supervise.title+"</a></li>";
				//ht+="<li><p>"+supervise.contents+"</p></li>";
			}
			 ht += "</ul></div>";	
			 if(result.length==0){
				 ht="暂无监管信息!";
			 }
			$('#mainCenter').html(ht);				
		},'JSON');
	}

	function showSupervise(supId){
		window.location.href="show.jsp?supId="+supId;
	}

	function proctionInfo(){
		//$('#mainCenter').html($('#div_a3').html());
		//mbproduction_findProductForMobile.action
		var cond = {};
		cond["entCode"]=entCode;
		
		$.post('mbproduction_findProductForMobile.action',cond,function(result){
			var rows = result[0].data;
			for(var i=0;i<rows.length;i++){
				var obj = rows[i];
				var appList = obj.appList;

				/*var img = "";
				for(var j=0;j<appList.length;j++){
					var app = appList[j];
					img += "<img src='/nytsyFiles/production/"+app.path+"' width='100'/>";
				}*/
				var cont = "<div style='padding:10px' class='mainbox'>"+
						  	"<div class='maincontent'>"+
							"<p class='newscontent'><span>生产情况：</span>"+obj.productinfo+"</p>"+
							"<div class='newsinfo'><div class='container' id='imgDiv_"+i+"'></div>"+
							"	</div>"+
							"	</div>"+
							"</div>";
				$('#mainCenter').append(cont);
				if(appList.length>0){//存在图片
					var imgStr = '<div id="full-width-slider-'+i+'" class="royalSlider heroSlider rsMinW" style="width: 100%;color: #000;height:200px">';
					for(var j=0;j<appList.length;j++){
						var app = appList[j];
						var imgCont = '<div class="rsContent">\
											<img class="rsImg" src="/nytsyFiles/production/'+app.path+'" alt="" />\
										<div class="infoBlock infoBlockLeftBlack rsABlock" data-fade-effect="" data-move-offset="10" data-move-effect="bottom" data-speed="200">\
											<h4>'+app.appName+'</h4>\
											<p>'+(app.appType==1?'产品图片':'生产许可证扫描件')+'</p>\
										</div>\
									</div>';
						imgStr += imgCont;
					}
					imgStr += '</div>';
					$('#imgDiv_'+i).append(imgStr);
					royalSliderInit('full-width-slider-'+i);
				}
			}
			
			
		
		},'JSON');
	}
	
	/**
	 * 图片滑动
	 * @param sid
	 * @return
	 */
	function royalSliderInit(sid){
		$('#'+sid).royalSlider({
			arrowsNav: true,
			loop: false,
			keyboardNavEnabled: true,
			controlsInside: false,
			imageScaleMode: 'fill',
			arrowsNavAutoHide: false,
			autoScaleSlider: true, 
			autoScaleSliderWidth: 960,     
			autoScaleSliderHeight: 250,
			controlNavigation: 'bullets',
			thumbsFitInViewport: false,
			navigateByClick: true,
			startSlideId: 0,
			autoPlay: false,
			transitionType:'move',
			globalCaption: true,
			deeplinking: {
				enabled: true,
				change: false
			},
			imgWidth: 1400,
			imgHeight: 580
		});
	}