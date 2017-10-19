// JavaScript Document
/***banner焦点图***/
(function($){
    $.fn.x_sliding = function(options){
        var defaults = {
		   setInterval_obj:'',
	       speed : 500,//速度
	       auto_speed : 5000,//自动滑动时间
		   x_move:724//移动像素
        }
        var options = $.extend(defaults, options);
        this.each(function(){
		var silding_obj=$(this);
/**实现代码**/
	var page_size = silding_obj.find('.slider_con').length//页面数
	var x_page = 0;//当前页面
	var son = silding_obj.find('.slider_con')//动画对象
if(page_size>1){
	son.eq(x_page).css({'z-index':'5'})
	son.eq(x_page).siblings().css({'z-index':'0','left':defaults.x_move})
		silding_obj.find('.slider_btn li').eq(x_page).addClass('click')
		silding_obj.find('.slider_btn li').click(function(){		
		if (!son.is(":animated")) {
			$(this).addClass('click')
			$(this).siblings().removeClass('click')
			page_feture = $(this).index()//按钮索引值
			if(x_page!=page_feture&&page_feture>x_page){
			son.eq(x_page).siblings().css({'z-index':'0','left':defaults.x_move})
			son.eq(x_page).animate({left:-defaults.x_move},defaults.speed,function(){
				$(this).css({'z-index':'0','left':defaults.x_move})
				})
			son.eq(page_feture).css({'z-index':'5'}).animate({left:0},defaults.speed,		function(){
			son.eq(page_feture).siblings().css({'z-index':'0','left':defaults.x_move})
				})
				x_page = page_feture
			}else if(x_page!=page_feture&&page_feture<x_page){
			son.eq(x_page).siblings().css({'z-index':'0','left':-defaults.x_move})
			son.eq(x_page).animate({left:defaults.x_move},defaults.speed,function(){
				$(this).css({'z-index':'0','left':-defaults.x_move})
				})	
				son.eq(page_feture).css({'z-index':'5'}).animate({left:0},defaults.speed,function(){
				son.eq(page_feture).siblings().css({'z-index':'0','left':-defaults.x_move})
				})
				x_page = page_feture
				}
			
		}		
		})		
			function autoShow(){
				if (!son.is(":animated")) {
				if(x_page < page_size-1){
				son.eq(x_page).siblings().css({'z-index':'0','left':defaults.x_move})
				son.eq(x_page).animate({left:-defaults.x_move},defaults.speed,function(){
				$(this).css({'z-index':'0','left':defaults.x_move})
				silding_obj.find('.slider_btn li').eq(x_page).addClass('click')
				silding_obj.find('.slider_btn li').eq(x_page).siblings().removeClass('click')
				})
			son.eq(x_page).next().css({'z-index':'5'}).animate({left:0},defaults.speed)
				x_page++
					}else{
				x_page = 0		
			son.eq(page_size-1).animate({left:-defaults.x_move},defaults.speed,function(){
				$(this).css({'z-index':'0','left':defaults.x_move})
				silding_obj.find('.slider_btn li').eq(x_page).addClass('click')
				silding_obj.find('.slider_btn li').eq(x_page).siblings().removeClass('click')
				})
				son.eq(x_page).css({'z-index':'5'}).animate({left:0},defaults.speed,function(){
				son.eq(x_page).siblings().css({'z-index':'0','left':defaults.x_move})
				})
						}
				}
			}
		defaults.setInterval_obj = setInterval(autoShow,defaults.auto_speed);
		son.hover(function(){
				clearInterval(defaults.setInterval_obj);
			},function(){
			defaults.setInterval_obj = setInterval(autoShow,defaults.auto_speed);
			})
		silding_obj.find('.slider_btn li').hover(function(){
			clearInterval(defaults.setInterval_obj);
			},function(){
			defaults.setInterval_obj = setInterval(autoShow,defaults.auto_speed);
			})
}
/**实现代码**/
        });
		
    };
})(jQuery);

