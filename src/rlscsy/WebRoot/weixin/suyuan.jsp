<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广东省农产品质量安全监管追溯平台-溯源查询</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>		
<script src="<%=basePath %>weixin/js/suyuan.js" type="text/javascript"></script>		
<link type="text/css" rel="stylesheet" href="<%=basePath %>weixin/css/weixin.css"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no" /> 
		
</head>
  
<body>
<input id="basePath" type="hidden" value="<%=basePath %>"/>
<DIV id="INDEX">
<DIV class="CENTER">
        <div class="CENTER_content">
            <div class="product_title"><img src="<%=basePath %>weixin/images/icon.png" /> <font>当前位置:溯源查询</font></div>
                <div class="suyuan_content">
                	<div class="con">
                    	<h3>输入二维码进行溯源查询</h3>
                        <div class="symSreach"><input id="num"  value="440200000079"/></div>
                        <div class="con_NO">
                            <i onclick="javascript:subnumber(1)">1</i>
                            <i onclick="javascript:subnumber(2)">2</i>
                            <i onclick="javascript:subnumber(3)">3</i>
                            <i class="color" onclick="javascript:subnumber(-2)">清除</i>
                            <i onclick="javascript:subnumber(4)">4</i>
                            <i onclick="javascript:subnumber(5)">5</i>
                            <i onclick="javascript:subnumber(6)">6</i>
                            <i class="color" onclick="javascript:subnumber(-1)">退格</i>
                            <i onclick="javascript:subnumber(7)">7</i>
                            <i onclick="javascript:subnumber(8)">8</i>
                            <i onclick="javascript:subnumber(9)">9</i>
                            <i onclick="javascript:subnumber(0)">0</i>
                            <div class="clear"></div>
                        </div>
                        <div class="btn btn_symSreach"><a href="javascript:void(0);" onclick="sySreach();">立刻查询</a></div>
                	</div>
                    <div class="suyuan_text">
民以食为天，食以安为先，全社会一直在关注这样一个问题：如何才能更加有效的规范和引导企业在生产过程中保障产品质量？如何让消费者购买的食物变得更加的安全可靠？二维码溯源系统是从产品的源头开始规范管理，由事后检测转变成事前预案、过程管控，包括产地检测、种苗处理、施肥、用药、采摘、运输、加工等环节，以提高产品质量。实现"生产有记录、过程留痕迹、质量安全保障"的农产品质量安全管理模式。
                    </div>
                </div>
            </div>           
    </DIV>
    <DIV class="BOTTOM">
    	广东省农业厅版权所有       技术支持:广州薪火网络科技有限公司 
    </DIV>         	
 </DIV>
</body>
</html>
