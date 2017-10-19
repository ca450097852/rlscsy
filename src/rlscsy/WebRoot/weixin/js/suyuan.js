	

	//获取屏幕的分辨率
	function getScreen(){
		var width = document.body.clientWidth;
		var height = document.body.clientHeight;
		document.getElementById("INDEX").style.width = width+"px";
		document.getElementById("INDEX").style.height = height+"px";
		//alert("分辨率:"+width+"*"+height);
	}

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

	/**
	 * 二维码查询
	 * @return
	 */
	function sySreach(){
		var basePath = $('#basePath').val();
		var dimenno = $('#num').val();
		if(dimenno==null||dimenno==''){
			alert("请输入二维码进行查询!");
			return;
		}else{
			window.open(basePath+"trace.jsp?code="+dimenno,"","");//接入企业溯源new
		}
		/*
		$.ajax({
			url : 'traceWeixin_findTrace.action?qrCode='+dimenno,
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(r) {
				//console.info(r);
				if(r){
					window.open(r.purl,"","");
				}else{
					alert("没有找到相应的产品，请检查输入的二维码是否正确!");
				}
			}
		});	
		*/	
	}