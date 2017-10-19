$(function() {
	
	var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
	var len = $("#focus ul li").length; //获取焦点图个数
	var index = 0;
	var picTimer;
	
	//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
	var mybtn = "<div class='mybtnBg'></div><div class='mybtn'>";
	for(var i=0; i < len; i++) {
		mybtn += "<span></span>";
	}
	mybtn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
	$("#focus").append(mybtn);
	$("#focus .mybtnBg").css("opacity",0.5);

	//为小按钮添加鼠标滑入事件，以显示相应的内容
	$("#focus .mybtn span").css("opacity",0.4).mouseover(function() {
		index = $("#focus .mybtn span").index(this);
		showPics(index);
	}).eq(0).trigger("mouseover");

	//上一页、下一页按钮透明度处理
	$("#focus .preNext").css("opacity",0.2).hover(function() {
		$(this).stop(true,false).animate({"opacity":"0.5"},300);
	},function() {
		$(this).stop(true,false).animate({"opacity":"0.2"},300);
	});

	//上一页按钮
	$("#focus .pre").click(function() {
		index -= 1;
		if(index == -1) {index = len - 1;}
		showPics(index);
	});

	//下一页按钮
	$("#focus .next").click(function() {
		index += 1;
		if(index == len) {index = 0;}
		showPics(index);
	});

	//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
	$("#focus ul").css("width",sWidth * (len));
	
	//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
	$("#focus").hover(function() {
		clearInterval(picTimer);
	},function() {
		picTimer = setInterval(function() {
			showPics(index);
			index++;
			if(index == len) {index = 0;}
		},4000); //此4000代表自动播放的间隔，单位：毫秒
	}).trigger("mouseleave");
	
	//显示图片函数，根据接收的index值显示相应的内容
	function showPics(index) { //普通切换
		var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
		$("#focus ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
		//$("#focus .mybtn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
		$("#focus .mybtn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果
	}

	$(document).ready(function(){
		
		$("*[name='topItem']").mouseover(function() {
			$(".i-mc").hide();
			$("*[name='topItem']").removeClass("spanhover");
			$(this).addClass("spanhover");
			var over = $(this).attr('id');
			$("div [name="+over+"]").show();
			});

			$("#catogory").mouseleave(function() {
				$("*[name='topItem']").removeClass("spanhover");
				$(".i-mc").hide();
			});
	});
	
	//新闻信息列
	var info ={};
	info["info.rsts"]=1;
//	info["info.typeName"]="农产品安全监管";//新闻发布//农产品安全监管
	$.ajax( {
		url : 'portal_Info_findNewsList.action?page=1&rows=6',
		async : false,
		data : info,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			
			var htm = "";
			var title = "";
			var time = "";
			var infoId = "";
			for ( var i = 0; i < result.rows.length; i++) {
					title = result.rows[i].title;
					if(title.length>26){title=title.substring(0, 25)+"...";}
					time = result.rows[i].crttime;
					if(time.length>11){time=time.substring(0, 10);}
					infoId = result.rows[i].infoId;
					htm=htm+"<li><span>"+time+"</span><a target=\"_blank\" href=\"portalweb/news_detail.jsp?typecode=news&infoId="+infoId+"\">"+title+"</a></li>";
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#newslist").html(htm);
		}
	});
});


//溯源查询数字输入
function subnumber(param){
	var param = param;
	if(param >= 0){
		$('#num').val($('#num').val()+param);
	}else if(param == -2){
		$('#num').val('');
	}else {
		var arr = $('#num').val();
   		var NewNum = arr.substring(0,arr.length-1);
   		$('#num').val(NewNum);
	}
}

function sySreach(){
	var dimenno = $('#num').val();
	if(dimenno!=''){
		if(dimenno.length==10){
			window.open("portalweb/suyuan_detail2.jsp?dimenno="+dimenno,"","");//企业溯源
		}else if(dimenno.length==19){
			window.open("portalweb/suyuan_prodetail.jsp?dimenno="+dimenno,"","");//接入企业产品溯源
		}else if(dimenno.length==12){
			window.open("portalweb/suyuan_entdetail.jsp?dimenno="+dimenno,"","");//接入企业溯源new
		}else if(dimenno.length==16){
			window.open("portalweb/suyuan_entdetail.jsp?dimenno="+dimenno,"","");//接入企业分类溯源new
		}else {
			//checkForRedirect(dimenno);//产品溯源
			window.open("portalweb/suyuan_detail.jsp?dimenno="+dimenno,"","");//接入企业产品溯源
		}
	}else{
		alert("请输入溯源码!");
		$('#num').focus();
	}
}

//跳转

function checkForRedirect(dimenno){

	var proBatch = {};
	proBatch["proBatch.dimenno"]=dimenno;
	proBatch["proBatch.batchSts"]='1';
	
	$.ajax({
   	    url:'web_ProBatch!findProBatchList.action',
   	    data:proBatch,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(result) {
		alert(result.total);
		if(result.total==0){
			window.open("portalweb/suyuan_prodetail.jsp?dimenno="+dimenno,"","");//产品溯源
		}else{
			window.open("portalweb/suyuan_detail.jsp?dimenno="+dimenno,"","");//接入企业产品溯源
		}
	    }
   }); 
	   
}