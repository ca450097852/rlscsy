<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.hontek.sys.action.EnterpriseActon"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
EnterpriseActon ent = (EnterpriseActon)ac1.getBean("EnterpriseAction");

String ss =  ent.findCompanyTable2();

String total = ent.getEntAreaListTableTotal();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>ECharts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="ECharts">
    <meta name="author" content="linzhifeng@baidu.com">

    <script src="asset/js/esl/esl.js"></script>
    <script src="asset/js/codemirror.js"></script>
    <script src="asset/js/javascript.js"></script>
    <link href="asset/css/bootstrap.css" rel="stylesheet">
    <link href="asset/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="asset/css/codemirror.css" rel="stylesheet">
    <link href="asset/css/monokai.css" rel="stylesheet">
    <link href="asset/css/echartsHome.css" rel="stylesheet">

    <style type="text/css">
        .CodeMirror {
            height: 550px;
        }
    </style>
</head>

<body style="padding: 20px;">
    <!-- NAVBAR================================================== -->
    <div class="container-fluid">
        <div class="row-fluid">
            <div id="sidebar-code" class="span4" style="display:none">
                <div class="well sidebar-nav">
                    <div class="nav-header"><a href="#" onclick="autoResize()" class="icon-resize-full" id ="icon-resize" ></a>option</div>
                    <textarea id="code" name="code">
var ecConfig = require('echarts/config');
var zrEvent = require('zrender/tool/event');

var curIndx = 0;
var mapType = [
    'china','广东'
];

myChart.on(ecConfig.EVENT.MAP_SELECTED, function(param){   
    //获取地址名
	var mt;
	var selected = param.selected;
	for (var i in selected) {
		if (selected[i]) {
			mt = i;
			break;
		}
	}
	if(mt&&mt!='undefined'){
		//parent.addTab(mt,'<%=basePath %>jsp/statistic/entProductChart.jsp?areaname='+mt);
		parent.addTab('企业统计','<%=basePath %>jsp/statistic/companyChart.jsp');
	}
});
option = {
    title: {
        text : '广东农产品质量安全追溯平台',
        subtext : '各地市溯源企业分布图'
    },
    tooltip : {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        x:'right',
        data:['企业数据']
    },
	
    dataRange: {
        min: 0,
        max: <%=total %>,
        //color:['orange','yellow'],
        text:['高','低'],           // 文本，默认为数值文本
        calculable : true
    },
    series : [
        {
            name: '企业数据',
            type: 'map',
            mapType: 'china',
            selectedMode : 'single',
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data:[
                <%=ss %>
            ]
        }
    ]
};
                    </textarea>
              </div><!--/.well -->
            </div><!--/span-->
            <div id="graphic" class="span8">
                <div id="main" class="main" style="height: 530px;"></div>
            </div><!--/span-->
        </div><!--/row-->
        
    </div><!--/.fluid-container-->

    <script src="asset/js/jquery.js"></script>
    <script src="asset/js/bootstrap-transition.js"></script>
    <script src="asset/js/bootstrap-alert.js"></script>
    <script src="asset/js/bootstrap-modal.js"></script>
    <script src="asset/js/bootstrap-dropdown.js"></script>
    <script src="asset/js/bootstrap-scrollspy.js"></script>
    <script src="asset/js/bootstrap-tab.js"></script>
    <script src="asset/js/bootstrap-tooltip.js"></script>
    <script src="asset/js/bootstrap-popover.js"></script>
    <script src="asset/js/bootstrap-button.js"></script>
    <script src="asset/js/bootstrap-collapse.js"></script>
    <script src="asset/js/bootstrap-carousel.js"></script>
    <script src="asset/js/bootstrap-typeahead.js"></script>
    <script src="asset/js/echartsExample.js"></script>
</body>
</html>