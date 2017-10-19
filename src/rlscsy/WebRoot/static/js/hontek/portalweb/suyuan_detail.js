//溯源查询数字输入
var filePath = '';
var code = '';
var saId = '';
$(function() {
	filePath = $('#filePath').val();
	
	var dimenno = $('#dimenno').val();
	code = $('#code').val();

	var cond = {};
	cond["entCode"]=code;
	//企业信息
	$.ajax({
   	    url:'mbent_getCompanyInfoByEntCode.action',
   	    data:cond,
   	    type:'post',
   	    async : false,
   	    dataType:'json',
   	    success : function(data) {
			for(var i=0;i<data.length;i++){
				var a = data[i];
				$("#qyjbxx").html("" +
						"<h4>"+a.name+"</h4>"+
					    "<p>简称:"+a.simpleName+"</p>"+
					    "<p>溯源码:"+a.entCode+"</p>"+
					    "<p>企业法人:"+a.legalPerson+"</p>");
				
				$("#qylxfs").html("" +
					    "<p>电话:"+a.tel+"</p>"+
					    "<p>邮箱:"+a.email+"</p>"+
					    "<p>邮编:"+a.postCode+"</p>"+
					    "<p>注册地址:"+a.regAddr+"</p>"+
					    "<p>经营地址:"+a.manageAddr+"</p>"+
					    "<p>网址:"+a.domName+"</p>"+
					    "");
				
				$("#qyjj").html("" +
					    "<p>"+a.intro+"</p>");
				
			}
	    }
   });

	/////资质信息
	$("#section_zzxx").hide();
	cond["rows"]=1000;
	$.ajax({
	    url:'mbzizhi_findZizhiPagerListforMobile.action',
	    data:cond,
	    type:'post',
	    async : false,
	    dataType:'json',
	    success : function(data) {
		$("#section_zzxx").show();
	    var tp="";
		var jght="";   //用来循环保存图片的标题
		var jgsb="";

		for(var i=0;i<data.length;i++){
			var a = data[i];
			jgsb+="<li><p><a rel=\"example_group\" href=\""+filePath+"zizhi/"+a.path+"\" title=\""+a.appName+"\"><img src=\""+filePath+"zizhi/"+a.path+"\" height=\"175\"></a></p><p>"+a.zName+"</p></li>";
		}
		
		$("#qyzztp").html(jgsb);
		
}
}); 
	
	var cond = {};
	cond["ids"]=code;
	$.post('mbsupervise_findSuperviseList.action',cond,function(result){
		var ht = "";			
		for(var i=0;i<result.length;i++){
			var supervise = result[i];
			ht+="<li class=\"bg01\"><table><tbody><tr><th>监管信息"+(i+1)+"</th><td><p>"+supervise.contents+"</p></td></tr></tbody></table></li>";
			//ht+="<li><a  onclick='showSupervise("+supervise.supId+")'>"++"</a></li>";
		}
		$('#qyjgxx').html(ht);				
	},'JSON');
	
	
	// 企业生产信息
	$("#section_qyscxx").hide();
		$.ajax({
	   	    url:'mbproduction_findProductForMobile.action',
	   	    data:cond,
	   	    type:'post',
	   	    async : false,
	   	    dataType:'json',
	   	    success : function(data) {
				$("#section_qyscxx").show();
				var rows = data[0].data;
				var cont = '';
				var imgStr = '';
				for(var i=0;i<rows.length;i++){
					var obj = rows[i];
					var appList = obj.appList;
					cont+="<li class=\"bg01\"><table><tbody><tr><th>生产情况</th><td><p>"+obj.productinfo+"</p></td></tr></tbody></table></li>";
					
					if(appList.length>0){//存在图片
						
						for(var j=0;j<appList.length;j++){
							var app = appList[j];
							var appType =app.appType;
							appType = appType==1?'产品图片':'生产许可证扫描件';
							var imgCont = "<li><p><a rel=\"scxx_group\" href=\""+filePath+"production/"+app.path+"\" title=\""+app.appName+"\"><img src=\""+filePath+"production/"+app.path+"\" height=\"175\"></a></p><p>"+appType+"</p></li>";
							imgStr += imgCont;
						}
					}
				}
				$("#qyscqk").html(cont);
				$("#qysctpxx").html(imgStr);
				
		    }    
	    }); 

	
	//图片横向滚动
	var imgScroll_01 = new funPicScrollX(".c_scrollPic01 .picBox",".c_scrollPic01 .prevBtn span",".c_scrollPic01 .nextBtn span",28,0).init();
	var imgScroll_02 = new funPicScrollX(".c_scrollPic02 .picBox",".c_scrollPic02 .prevBtn span",".c_scrollPic02 .nextBtn span",28,0).init();
	
	$("[class^=picMoveX]").each(function(){  //图片右滑按钮变灰
		var lenth = $(this).find("li").length;
		if(lenth<=4){
			$(this).find(".nextBtn span").eq(0).addClass("last");
		}
	});
	
	////加工图片预览效果
	$("a[rel=example_group]").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'titlePosition' 	: 'over',
		'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
			return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
		}
	});
	
	////原料图片预览效果
	$("a[rel=scxx_group]").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'titlePosition' 	: 'over',
		'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
			return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
		}
	});
	
	
});

//图片横向滚动
function funPicScrollX(scrollBox,prevBtn,nextBtn,spacing,isCycle){
    this.scroll = $(scrollBox);
    this.lBtn = $(prevBtn);
    this.rBtn = $(nextBtn);
    this.bd = this.scroll.find('ul');
	this.spacing = spacing;
    //是否循环播放
    this.isCycle = isCycle ? isCycle : false;
    //容器显示的宽度
    this.W = this.scroll.width();
    //item集合
    this.item = this.bd.find('li');
    //每一个item容器的宽度
    this.w = this.scroll.find('li').eq(0).width() + this.spacing;
    //可见item个数
    this.isShowNum = this.W / this.w;
    //item容器的个数
    this.len = this.scroll.find('li').length;
    //动画播放key
    this.key = true;
    //起始位置
    this.start = 0;
    //结束位置
    this.end = 0;
    this.timer = null;
}
funPicScrollX.prototype = {
    constructor: funPicScrollX,
    init: function(){
        var that = this, i = 0;
        
        //如果item个数不足以滚动就退出
        if (that.isShowNum > that.len) 
            return;
        
        //是否循环播放
        if (that.isCycle) {
            for (; i < that.isShowNum; i++) {
                that.item.eq(i).clone().appendTo(that.bd);
            }
        }
        else {
            that.bd.css('width', that.len * that.w + 'px');
        }
        
        that.rBtn.click(function(){
            that.play(1);
        })
        
        that.rBtn.bind('click', function(){
            that.play(1);
        })
        
        that.lBtn.bind('click', function(){
            that.play(0);
        })
    },
    play: function(k){
        var that = this, m = that.w;
        if (!k) {
            m = -that.w;
        }
        if (that.key) {
            that.key = false;
            that.scroll.animate({
                scrollLeft: that.scroll.scrollLeft() + m
            }, 500, function(){
                that.key = true;
				if(!that.isCycle && that.scroll.scrollLeft() == 0){
					that.lBtn.addClass('last');
				}else{
					that.lBtn.removeClass('last');
				}
				if(!that.isCycle && that.scroll.scrollLeft() == that.w * (that.len-that.isShowNum)){
					that.rBtn.addClass('last');
				}else{
					that.rBtn.removeClass('last');
				}
            })
        }
    }
}

