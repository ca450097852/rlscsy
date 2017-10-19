var proUrl = "proWeixin_findList.action";
var pageSize = 5;	//设置每页显示条数;
var basePath = "";
var basePath2 = "";
$(function() {
	basePath = $('#basePath').val();
	basePath2 = $('#basePath2').val();
	getList(1,pageSize,"");
});

/*获取列表*/
function getList(nowPage,pageSize,searchTitle){
	
	loading();//加载中的gif图片
	
	var url = proUrl+"?page="+nowPage+"&rows="+pageSize;
	var product ={};
	if(searchTitle&&searchTitle!=""){
		product["product.proName"] = searchTitle;
	}
	$('#pageNum').val(nowPage);//页面上保存当前的pageNum;
	
	$.ajax( {
		url : url,
		async : false,
		data : product,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);			
			var htm = "";
			var proName = "";//产品名称
			var entName = "";//机构名称
			var proId = "";	
			for ( var i = 0; i < result.rows.length; i++) {
				var temproImg = "";//产品图片
				
				proName = result.rows[i].proName;
				if(proName.length>12){proName=proName.substring(0, 12)+"…"};
				
				entName= result.rows[i].entName;
				if(entName.length>12){entName=entName.substring(0,12)+"…"};
				
				proId = result.rows[i].proId;
									
				//获取产品图片					
				$.ajax({
					url : 'proWeixin_findProAppList.action',
					async : false,
					data : {'productAppend.proId':proId},
					dataType : 'json',
					success : function(result1) {
						if(result1.total==0){
							return;
						}
						temproImg = result1.rows[0].path;
					}
				});
				if(temproImg!=''){
					temproImg = basePath2+"nytsyFiles/proimg/"+temproImg;
				}else{
					temproImg = basePath+"weixin/images/no_pro.png";
				}
				
				htm+= '<div class="product">';
				htm+= '<div class="product_img"><img src="'+temproImg+'" width="50px" height="50px"/></div>';
				htm+= '<a href="productDetail.jsp?proId='+proId+'">';
				htm+= '<div class="product_text"><h4>'+proName+'</h4><p>'+entName+'</p>';
				htm+= '</div>';
				htm+= '</a>';
				htm+= '<div class="clear"></div>';
				htm+= '</div>';
			}			
			if(total==0){
				htm+= '<div align="center"><img src="'+basePath+"weixin/images/defaultPic.jpg"+'" width="300px" height="300px"/></div>';
			}
			$("#product_list").html(htm);		
			createPageTool(nowPage,total,pageSize);
		}
	});
}
//"<a href=\"product_detail.jsp?proId="+proId+"\"></a>"
//生成 分页工具
function createPageTool(nowPage,total,pageSize){
	var pageCount = Math.ceil(total/pageSize);
		
	var perpage = (nowPage-1)>0?(nowPage-1):1;
	var nextpage = (parseInt(nowPage)+1)<=pageCount?(parseInt(nowPage)+1):pageCount;
	
	//var tools1 = "第"+nowPage+"页/共"+pageCount+"页&nbsp;&nbsp;";
	var tools2 = "<a href=\"javascript:void(0)\" onclick=\"goPage("+perpage+");\">上一页</a>&nbsp;&nbsp;" +
				 "<a href=\"javascript:void(0)\" onclick=\"goPage("+nextpage+");\">下一页</a>&nbsp;&nbsp;" ;
	var tools3 = "第&nbsp;<select name=\"\" onchange=\"goPageSelect(this.value)\">";

	for(var i=1;i<=pageCount;i++){
		if(i==nowPage){
			tools3 = tools3 +"<option  value=\""+i+"\" selected=\"selected\">"+i+"</option>";
		}else{
			tools3 = tools3 +"<option  value=\""+i+"\">"+i+"</option>";
		}
	}
	tools3 =tools3 +"</select>&nbsp;页/共"+pageCount+"页&nbsp;&nbsp;"; 
	$("#pageTools").html(tools2+tools3);
}



//翻页方法
function goPage(tem){
	var pageNum = $('#pageNum').val();//获取页面保存的当前页码
	pageNum = parseInt(pageNum);	
	if(tem!=pageNum)
	{
		var searchText = $('#searchText');
		getList(tem,pageSize,searchText.val());		
	}
}

//翻页方法
function goPageSelect(tem){
	var pageNum = $('#pageNum').val();//获取页面保存的当前页码
	pageNum = parseInt(pageNum);	
	if(tem!=pageNum)
	{
		var searchText = $('#searchText');
		getList(tem,pageSize,searchText.val());		
	}
}


//加载前显示gif
function loading(){
	var loading = "<div  align=\"center\"><img src=\"../static/image/comm/waitanim.gif\"/></div>";
	$("#product_list").html(loading);
}


function searchText() {
	var searchText = $('#searchText');
	getList(1,pageSize,searchText.val());
	return true;	
}

