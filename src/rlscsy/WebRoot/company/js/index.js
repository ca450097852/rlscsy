var basePath;

$(function(){
	$.ajaxSetup({async: false });
	var comId=$('#entId').val();
	basePath=$('#basePath').val();
	
	var company=init();
	$.ajax({
		type : "get", 
		data : null,
		url : 'webcompany_findCompanyAreaName.action?comId='+comId, //请求路径
		dataType : "text",
		success : function(data) {
			var comtype=company.comType;
			if(comtype=="1"){
				comtype="屠宰企业";
			}
			else if(comtype=="2"){
				comtype="批发企业";
			}
			else if(comtype=="3"){
				comtype="肉类蔬菜零售商";
			}
			else if(comtype=="4"){
				comtype="配送企业";
			}
			else if(comtype=="5"){
				comtype="蔬菜种植企业";
			}
			else comtype="其他";
			var content="<span>"+company.name+"</span>"+data+"|"+comtype+" <b></b>";
			$('#company_msg').html(content);
			
		},
		error : function(err) {
		}
	});
	getScanCount();
	initProList(1,6);
	
	getNewsList(1,6,"");

	parent.layer.closeAll();
	

});


function getNewsList(nowPage,pageSize,searchTitle){	
	var url = "portal_Info_findNewsList.action?page="+nowPage+"&rows="+pageSize;
	var info ={};
	info["info.rsts"]=1;
	info["info.typeName"]="应急管理";	
	if(searchTitle!="")
	{
		info["info.title"]=searchTitle;
	}
	
	$.ajax( {
		url : url,
		data : info,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			$("#news_list").html('');
			var total = result.total;
			total = parseInt(total);
			var htm = "";
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
					
					htm =  '<li><a target="_blank" href="'+basePath+'portal/newsdetail.jsp?typecode=news&infoId='+infoId+'">'+title+'</a></li>';
					$("#news_list").append(htm);
					
			}
			if(total==0){
				htm="<div align=\"center\"><p>暂无数据!</p></div>";
				$("#news_list").html(htm);
			}
		}
	});
}

function getScanCount(){
	
	var comId=$('#entId').val();
	$.ajax({
		type : "get", 
	    url:'scanCount_findScanCount.action?comId='+comId,//url调用Action方法  
		dataType : "text",
		success : function(data) {
			$("#scanCount").html(data);
		}
	});
	
	$.ajax({
		type : "get", 
	    url:'proTypeBatch_getProTypeBatchConut.action?ids='+comId,//url调用Action方法  
		dataType : "text",
		success : function(data) {
			$("#createCount").html(data);
			$("#printCount").html(data);
		}
	});
	
}


function init(){
 var comId=$('#entId').val();
 var company;
	$.post('webcompany_getLoginCompany.action?tt='+Math.floor(Math.random()*20)+1,'',function(result){
		if(result){
			company = result[0];
			if(company.comLogo){
				$("#logoImg").attr("src","/nytsyFiles/company/"+company.comLogo);
				
			}else{
				
				$("#logoImg").attr("src","images/logo1.png");

			}
			
		}
		
	},'JSON');
	
	return company;
}



var proTypeList;
function initProList(page,rows){	
	var comType = $("#comType").val();
	var condition = {};
	condition['page']=page;
	condition['rows']=rows;
	condition['proTypeBatch.entId']=$('#entId').val();
	$('#proTab tr:not(:first)').remove();
	$.post('proTypeBatch_findProTypeBatch.action?tt='+Math.random(),condition,function(result){		
		if(result){		
			proTypeList = result.rows;
			for(var i=0;i<proTypeList.length;i++){
				var proTypeBatch = proTypeList[i];			
				
				//生猪进厂信息
				var actionHtml="";
				if(comType==1){
					actionHtml='<a class="tablelink" onclick="showPage(\'生猪进厂信息\',\'marketIn_tz.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">生猪进厂信息</a> '+
											'<a class="tablelink"onclick="showPage(\'检验检疫信息\',\'quarantine.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">检验检疫信息</a> '+
											'<a class="tablelink"onclick="showPage(\'交易信息\',\'meatOutInfoBase_tz.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">交易信息</a>';
				}else if(comType==2){
					actionHtml='<a class="tablelink"onclick="showPage(\'批发进场信息\',\'marketIn_pf.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">批发进场信息</a> '+
								' <a class="tablelink"onclick="showPage(\'检验检疫信息\',\'qmarketDetectionInfo.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">检验检疫信息</a>'+
								' <a class="tablelink"onclick="showPage(\'交易信息\',\'meatOutInfoBase_pf.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">交易信息</a>';
				}else if(comType==3){
					actionHtml='<a class="tablelink"onclick="showPage(\'零售进场信息\',\'marketIn_ls.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">零售进场信息</a> '+
								' <a class="tablelink"onclick="showPage(\'销售汇总信息\',\'retailMarketTranInfoSumm.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">销售汇总信息</a>';
				}else if(comType==4){
					actionHtml='<a class="tablelink"onclick="showPage(\'超市进场信息\',\'marketIn_cs.jsp?ptbId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">超市进场信息</a> ';
				}else if(comType==5){
					actionHtml='<a class="tablelink"onclick="showPage(\'肥料使用记录\',\'fertilizerUse.jsp?recId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">肥料使用记录</a> '+
					  '<a class="tablelink"onclick="showPage(\'农药使用记录\',\'agriUse.jsp?recId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">农药使用记录</a>'+
					  '<a class="tablelink"onclick="showPage(\'生产节点\',\'nodeinfo.jsp?recId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">生产节点</a>'+
					  '<a class="tablelink"onclick="showPage(\'检验检疫信息\',\'checkinfo.jsp?recId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">检验检疫信息</a>'+										  
					 '<a class="tablelink"onclick="showPage(\'交易信息\',\'saleinfo.jsp?recId='+proTypeBatch.ptbId+'&batchName='+encodeURI(encodeURI(proTypeBatch.batchName))+'\')">交易信息</a>';
				}
				var value = proTypeBatch.codeImg;
				if(value){
					var maImg = ' <a class="tablelink" href=\'javascript:void(0);\' onclick=\'showCodeImg("'+value+'");\'>追溯标识</a>';
					actionHtml+=maImg;
				}

				var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
			        <td>'+proTypeBatch.batchName+'</td>\
			        <td>'+proTypeBatch.batchTime+'</td>\
					<td>'+proTypeBatch.dimenno+'</td>\
					<td>'+actionHtml+'</td>\
			        </td>\
			        </tr>';
			        
			     $('#proTab').append(content);
			}
			
			//没有数据
			if(proTypeList.length==0){
				$('#proTab').append('<tr><td align="center" colspan="'+$('#proTab tr:first').find('th').length+'">暂无数据</td></tr>');
			}						
		}		
	},'JSON');
	
}

function showPage(title,url){
	//iframe层
	layer.open({
	  type: 2,
	  title: title,
	  shadeClose: true,
	  maxmin: true, //开启最大化最小化按钮
	  shade: 0.5,
	  area: ['95%', '95%'],
	  content: url //iframe的url
	}); 
}

//显示二维码
function showCodeImg(codeImg){
	var content="<img src='/nytsyFiles/qrcode/"+codeImg+
				"'/>";
	layer.alert(content);

}


//这个函数是必须的，因为在geo.js里每次更改地址时会调用此函数
function promptinfo()
{
   /* var address = document.getElementById('address');
    var s1 = document.getElementById('s1');
    var s2 = document.getElementById('s2');
    var s3 = document.getElementById('s3');
    address.value = s1.value + s2.value + s3.value;*/
}
