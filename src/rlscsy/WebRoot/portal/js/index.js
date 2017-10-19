//var company_ptqurl = "portal_Company_findTwoProTypeQrcode.action";
var company_ptqurl = "portal_Company_findPlantProTypeQrcode.action";
var filePath = "";

$(function() {
//广告栏目
		$(".lubo").lubo({

		});

	filePath = $("#filePath").val();
	getCompanyPTQ();
	getNewsList();//应急管理列表
	getCompanyList();//企业名录
	
});

function getCompanyPTQ(){
	$.ajax( {
		url : company_ptqurl,
		data: {"page":1,"rows":10},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var rows = result.rows;
			var ptqhtml = "";
			  for(var i=0;i<rows.length;i++){
				  
				  var ptq = rows[i];
				  if(ptq){
					  var typeImg = ptq.typeImg==null?"":ptq.typeImg;
					  
					  if(ptq.typeClass=='2'){
						  typeImg = typeImg==""?"images/pic1.jpg":filePath+typeImg;
					  }else{
						  typeImg = typeImg==""?"images/pic2.jpg":filePath+typeImg;
					  }
					  
//					  var regAddr = ptq.regAddr;
//					  if(regAddr&&regAddr.length>13){
//						  regAddr = regAddr.substring(0,12)+"..."
//					  }
					  var typeName = ptq.typeName;
					  var proName = ptq.proName;
					  var dimenno = ptq.dimenno;
//					  var companyName = ptq.companyName;
					  
					  if(proName==null||proName==''){
						  proName = typeName;
					  }
					  
					  var linkUrl = "sydetail.jsp?dimenno="+dimenno;
					  
					  ptqhtml = '<li><a href="'+linkUrl+'" target="_blank">'+
						'<div class="product-img"><img src="'+typeImg+'" /></div>'+
						'<div class="product-name">'+proName+'</div></a></li>';
					$("#suyuan_list").append(ptqhtml);
				  }
			  }
			  $(".mr_frUl ul li img").hover(function() {
					$(this).css("border-color", "#A0C0EB");
				}, function() {
					$(this).css("border-color", "#d8d8d8")
				});
				jQuery(".mr_frbox").slide({
					titCell: "",
					mainCell: ".mr_frUl ul",
					autoPage: true,
					effect: "leftLoop",
					autoPlay: true,
					vis: 6
				});
			  
			  
		}});
	
}

function getCompanyPTQ_bak(){
	
	$.ajax( {
		url : company_ptqurl,
		type : 'post',
		dataType : 'json',
		success : function(result) {
		  if(result){
			  var animal = result.animal;
			  var plant = result.plant;
			  var ct = 1;
			  if(animal){
				  var ptqhtml = "";
				  for(var i=0;i<animal.length;i++){
					  var ptq = animal[i];
					  if(ptq){
						  var typeImg = ptq.typeImg;
						  typeImg = typeImg==""?"images/pic1.jpg":filePath+typeImg;
						  var regAddr = ptq.regAddr;
						  if(regAddr&&regAddr.length>13){
							  regAddr = regAddr.substring(0,12)+"..."
						  }
						  var typeName = ptq.typeName;
						  var dimenno = ptq.dimenno;
						  var companyName = ptq.companyName;
						  var linkUrl = "sydetail.jsp?dimenno="+dimenno;
						  
						  var pl = ct%4==0?"style=\"margin-right:0px;\"":"";
						  pl = '';
						  ptqhtml = '<div class="product_block" '+pl+'>\
							    	<img src="'+typeImg+'"/>\
							        <p>追溯编码：'+dimenno+'<br />产品名称：'+typeName+'<br />'+companyName+'<br />'+regAddr+'</p>\
							        <a target="_blank" href="'+linkUrl+'">查看更多>></a>\
							    </div>';
						  
					  }
				  }
				  
				  $("#plantul").append(ptqhtml);
			  }
			  
			  if(plant){
				  var ptqhtml = "";
				  for(var j=0;j<plant.length;j++){
					  var ptq = plant[j];
					  if(ptq){

						  var typeImg = ptq.typeImg;
						  typeImg = typeImg==""?"images/pic2.jpg":filePath+typeImg;
						  
						  var regAddr = ptq.regAddr;
						  if(regAddr&&regAddr.length>15){
							  regAddr = regAddr.substring(0,14)+"..."
						  }
						  var typeName = ptq.typeName;
						  var dimenno = ptq.dimenno;
						  var companyName = ptq.companyName;
						  if(companyName&&companyName.length>15){
							  companyName = companyName.substring(0,14)+"..."
						  }
						  						  
						  var linkUrl = "sydetail.jsp?dimenno="+dimenno;
//						  
//						  if(j==3){
//							  ptqhtml +='<div class="product_item fl">';
//						  }else{
//							  ptqhtml +='<div class="product_item fl mar_r15">';
//						  }						  
//						  ptqhtml +='<div><img src="'+typeImg+'"  width="233" height="147"></div>'+
//					      	  '<div class="product_item_text">'+
//					          '<p>追溯编码：'+dimenno+'</p>'+
//					          '<p>产品名称：'+typeName+'</p>'+
//					      	  '<p>'+companyName+'</p><p>'+regAddr+'</p>'+
//					          '<div class="product_item_more"><a href="'+linkUrl+'" class="icon pro-more" target="_blank">查看更多 >></a></div>'+
//					       '</div></div>';
						  var pl = ct%4==0?"style=\"margin-right:0px;\"":"";
						  pl = '';
						  ptqhtml = '<div class="product_block" '+pl+'>\
						    	<img src="'+typeImg+'"/>\
						        <p>追溯编码：'+dimenno+'<br />产品名称：'+typeName+'<br />'+companyName+'<br />'+regAddr+'</p>\
						        <a target="_blank" href="'+linkUrl+'">查看更多>></a>\
						    </div>';
					  }
					  $("#plantul").append(ptqhtml);
				  }
				  
			  }			  
		  }
		}
	});
	
}

//应急管理list
function getNewsList(){
	var infoUrl = "portal_Info_findNewsList.action";
	var types = 1;//默认类型为“农产品安全事件公布”
	var pageSize = 5;	//设置每页显示条数;
	var url = infoUrl+"?page=1"+"&rows="+pageSize;
	
	var info ={};
	info["info.rsts"]=1;
	info["info.typeName"]="应急管理";	
	
	$.ajax( {
		url : url,
		async : false,
		data : info,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			$("#news_recent").html('');
			var titleImg=result.rows[0].titleImg;
			if(titleImg == '' || titleImg==null){
				titleImg = 'images/news-img.png"';
			}else{
				titleImg = '/nytsyFiles/titleImg/'+titleImg;
			}
			var title = result.rows[0].title;
			var content =result.rows[0].content; 
			content=delHtmlTag(content);
			if(title.length>51){title=title.substring(0,50)+"...";}
			var time = result.rows[0].crttime;
			if(time.length>11){time=time.substring(0, 10);}
			var infoId = result.rows[0].infoId;
			
			var first="<div class='news-img'><img src='"+titleImg+"'/></div>"
			+"<div class='news-recent-content'><a href='newsdetail.jsp?typecode=news&infoId="+infoId+"' class='title'>"+title+"</a>"
			+"<p>"+content+"</p><p class='news-date'>"+time+"</p></div>";
			$("#news_recent").html(first);
			var total = result.total;
			total = parseInt(total);
			for ( var i = 1; i < result.rows.length; i++) {
					 title = result.rows[i].title;
					 time = result.rows[i].crttime;
					if(time.length>11){time=time.substring(0, 10);}
					if(title.length>51){title=title.substring(0,50)+"...";}
					infoId = result.rows[i].infoId;
					htm = '<li><a href="newsdetail.jsp?typecode=news&infoId='+infoId+'">'+title+'</a>'+
					'<p class="news-date">'+time+'</li>';
					$("#news_list").append(htm);
					
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
				$("#news_list").html(htm);
			}
		}
	});
}

//企业名录list
function getCompanyList(){
	var compUrl = "portal_Company_findList.action";
	var rows = 6;	//设置每页显示条数;
	var url = compUrl+"?page="+1+"&rows="+rows;	
	$.ajax( {
		url : url,
		async : false,
		data : null,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);	
			var name = "";
			var comId = "";
			var htm = "";
			for ( var i =0; i < result.rows.length; i++) {
					name = result.rows[i].name;					
					logoUrl = result.rows[i].logoUrl;
					var linkUrl = "sydetail.jsp?dimenno="+result.rows[i].comCode;
					htm+='<li><a target="_blank" href="'+linkUrl+'">'+name+'</a></li>';
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
			}
			$("#companys_list").html(htm);
		}
	});
}




function delHtmlTag(str){
return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
}

