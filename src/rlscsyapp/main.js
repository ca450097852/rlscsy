define(function(require) {
	var $ = require("jquery");
	var justep = require("$UI/system/lib/justep");
	var ShellImpl = require('$UI/system/lib/portal/shellImpl');
	var CommonUtils = require("$UI/system/components/justep/common/utils");
	
	var Model = function() {
		this.callParent();
		var shellImpl = new ShellImpl(this, {
			"contentsXid" : "pages",
			"pageMappings" : {
				"index" : {
					url : require.toUrl('./index.w')
				}
			}
		});
				
		//shellImpl.setIsSinglePage(true);
		//shellImpl.useDefaultExitHandler = false;
		/*CommonUtils.attachDoubleClickExitApp(function() {
						
			var isHomePage = shellImpl.pagesComp.contents[0].innerContainer.getInnerModel().comp('contents2').getActiveIndex() == 0;
			if (shellImpl.pagesComp.getActiveIndex() === 0 && isHomePage) {
				return true;
			}
			return false;
		});*/
		
		
/*		document.addEventListener("deviceready", function() {
        var exitAppTicker = 0;
        var listener = function(){
        	
        	if(exitAppTicker == 1){                         
                var msg = $('<div class="alert alert-success" style="z-index:999;text-align:center;font-size:16px;-webkit-transition:all 0.4s;-webkit-transform:translate3d(0,-100%,0);font-weight:bold;position:absolute;top:0;left:0;width:100%;">再按一次退出应用</div>').appendTo('body');
                setTimeout(function(){
                        msg.transform('translate3d(0,0,0)');
                },1);
                setTimeout(function(){
                        exitAppTicker = 0;
                        msg.transform('translate3d(0,-100%,0)').transitionEnd(function(){
                                msg.remove();
                        });
                },1000);
	        }else if(exitAppTicker == 2){
	                navigator.app.exitApp();
	        }else{
                history.back();
            }
        
        	
        		if(shellImpl.pagesComp.getActiveIndex() == 1){        			                    
                }else if(shellImpl.pagesComp.getActiveIndex() == 0){
                	 navigator.app.exitApp();
                }        		                
		 exitAppTicker++;
		 setTimeout(function(){
           exitAppTicker = 0;
		 },500);
        };
        document.addEventListener('backbutton', listener, false);
        $(window).on('beforeunload',function(){
                document.removeEventListener('backbutton', listener, false);
        });
 }, false);*/

	};


	Model.prototype.modelParamsReceive = function(event){			
		var context = this.getContext();
		//获取URL中的参数
		var page = context.getRequestParameter("pg");
		var pn = context.getRequestParameter("pn");		
		var pv = context.getRequestParameter("pv");		
		if(page){
			if(pn=="typeId"&&pv){
				justep.Shell.showPage(page,{typeId:pv});
			}else{
				justep.Shell.showPage(page);
			}			
		}else{
			justep.Shell.showPage("index");
		}
		
	};


	return Model;
});