var company_ptqurl = "portal_Company_findPlantProTypeQrcode.action";

var filePath = "";
var rows = 8;	//设置每页显示条数;

$(function() {
	var info ={};
	info["info.rsts"]=1;
	info["info.typeName"]="应急管理";	
	
	$.ajax( {
		url : "portal_Info_findNewsList.action",
		async : false,
		data : info,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			var htm1 = "";
			var title = "";
			var time = "";
			var infoId = "";
			var content = "";
			for ( var i = 0; i < result.rows.length; i++) {
					title = result.rows[i].title;
					content = result.rows[i].content;
					if(title.length>51){title=title.substring(0,50)+"...";}
					time = result.rows[i].crttime;
					if(time.length>11){time=time.substring(0, 10);}
					infoId = result.rows[i].infoId;
					if(i>3){
						htm1="<div style='display:none' id='ul"+i+"'><li><a target=\"_blank\" href=\"newsdetail.jsp?typecode=news&infoId="+infoId+"\">"+title+"</a><span>    ("+time+")</span></li></div>";
					}else{
						htm1="<div id='ul"+i+"'><li><a target=\"_blank\" href=\"newsdetail.jsp?typecode=news&infoId="+infoId+"\">"+title+"</a><span>    ("+time+")</span></li></div>";
					}
				
					$("#infotitle").append(htm1);
					
			}
			
			
		}
	});
	filePath = $("#filePath").val();
	getCompanyPTQ(1,rows);
	
});

function getCompanyPTQ(page,rows){
	$('#pageNum').val(page);//;

	$.ajax( {
		url : company_ptqurl,
		data: {"page":page,"rows":rows},
		type : 'post',
		dataType : 'json',
		success : function(result) {
		  if(result){
			  var plant = result.rows;
			  if(plant){
				  var total = result.total;
				  total = parseInt(total);
				  createPageTool(page,total,rows);
					
				  var ptqhtml = "";
				  
				  for(var i=0;i<plant.length;i++){
					  var ptq = plant[i];
					  if(ptq){
						  
						  var typeImg = ptq.typeImg==null?"":ptq.typeImg;
						  
						  if(ptq.typeClass=='2'){
							  typeImg = typeImg==""?"images/pic1.jpg":filePath+typeImg;
						  }else{
							  typeImg = typeImg==""?"images/pic2.jpg":filePath+typeImg;
						  }
						  
						  
						  
						  var regAddr = ptq.regAddr;
						  var typeName = ptq.typeName;
						  var proName = ptq.proName;
						  var dimenno = ptq.dimenno;
						  var companyName = ptq.companyName;
						  if(companyName&&companyName.length>15){
							  companyName = companyName.substring(0,14)+"...";
						  }
						  if(regAddr&&regAddr.length>15){
							  regAddr = regAddr.substring(0,14)+"...";
						  }
						  if(proName==null||proName==''){
							  proName = typeName;
						  }
						  
						  var linkUrl = "sydetail.jsp?dimenno="+dimenno;
						  
//						  if(i==3||i==7){
//							  ptqhtml +='<div class="product_item fl mar_t20">';
//						  }else{
//							  ptqhtml +='<div class="product_item fl mar_r15 mar_t20">';
//						  }						  
//						  ptqhtml +='<div><img src="'+typeImg+'"  width="233" height="147"></div>'+
//					      	  '<div class="product_item_text">'+
//					          '<p>追溯编码：'+dimenno+'</p>'+
//					          '<p>产品名称：'+typeName+'</p>'+
//					      	  '<p>'+companyName+'</p><p>'+regAddr+'</p>'+
//					          '<div class="product_item_more"><a href="'+linkUrl+'" class="icon pro-more" target="_blank">查看更多 >></a></div>'+
//					       '</div></div>';
						  var pl = (i+1)%4==0?"style=\"margin-right:0px;\"":"";
						 /* ptqhtml += '<div class="product_block" '+pl+'>\
							    	<img src="'+typeImg+'"/>\
							        <p>追溯编码：'+dimenno+'<br />产品名称：'+proName+'<br />'+companyName+'<br />'+regAddr+'</p>\
							        <a target="_blank" href="'+linkUrl+'">查看更多>></a>\
							    </div>';*/
						  ptqhtml += '<li><a href="'+linkUrl+'"><div >\
									<img src="'+typeImg+'"/></div><p>'+proName+'</p>\
									<i href="'+linkUrl+'">点击查看>></i></a></li>';
					  }
				  }
				  
				 $("#qiye_list").html(ptqhtml+'<div class="clear"></div>');
			  }
			  
		  }
		}
	});
	
}

//生成 分页工具
function createPageTool(nowPage,total,pageSize){
	var pageCount = Math.ceil(total/pageSize);
	var perpage = (nowPage-1)>0?(nowPage-1):1;
	var nextpage = (nowPage+1)<=pageCount?(nowPage+1):pageCount;
	var tools="<div class='compage' align='center'><a href='#' onclick='goPage("+perpage+");'> &lt; </a>";
	if(pageCount>10){
		
	
	var index=10;
	var int =1
	if(nowPage>5){
	index=index+(nowPage-5);
	int=int+nowPage-5;
	}
	if(index==pageCount){
		$("#tt").val(int);
	}else if(index>pageCount){
		int=$("#tt").val();
	}
		for (int; int <=index; int++) {
			if(int>pageCount){
				break;
			}
			tools+="<a href='#' onclick='goPage("+int+")' onmouseover='yj("+int+","+nowPage+");' onmouseout='yc("+int+","+nowPage+")'; id='box"+int+"'>"+int+"</a>";
		}
	}
	else{
		for (var int=1; int <=pageCount; int++) {
			tools+="<a href='#' onclick='goPage("+int+")' onmouseover='yj("+int+","+nowPage+");' onmouseout='yc("+int+","+nowPage+")'; id='box"+int+"'>"+int+"</a>";
		
		}
		
	}
	tools+="<a href='#' onclick='goPage("+nextpage+");'> &gt; </a></div>";
	$("#pageTools").html(tools);
	
	var box = document.getElementById("box"+nowPage);
	 box.style.backgroundColor="#20B2AA";
	 box.style.color="#FFF";
}
//鼠标移进
function yj(int,nowPage){
	if(int==nowPage){
		
	}else{
	 var box = document.getElementById("box"+int);
	
	 box.style.backgroundColor="#20B2AA";
	 box.style.color="#FFF";
	}
}
//鼠标移出
function yc(int,nowPage){
	if(int==nowPage){
	}else{
	 var box = document.getElementById('box'+int);
	 box.style.backgroundColor="#FFF";
	 box.style.color="#20B2AA";
}
}


//翻页
function goPage(tem){
	var pageNum = $('#pageNum').val();//页面保存的当前页码
	pageNum = parseInt(pageNum);
	tem = parseInt(tem);
	if(tem!=pageNum)
	{
		getCompanyPTQ(tem,rows);
		
	}
}

