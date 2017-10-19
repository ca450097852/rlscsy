<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%><%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/";
	
	Object obj = session.getAttribute("enterprse");

	if(obj==null){
		response.sendRedirect("ajaxSessionTimeoutToCom.action");
		return;
	}
	
	String proId = request.getParameter("proId");
	proId = proId==null?"":proId;
	//1种植类； 2养殖类
	String proType = request.getParameter("proType");
	proType = proType==null?"1":proType;
	
	String name1 = "饲料";
	String name2 = "喂养";
	if("1".equals(proType)){
		//1种植类； 2养殖类
		name1 = "肥料";
		name2 = "施肥";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-产品管理</title>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
<script type="text/javascript" src="<%=basePath %>company/js/trace.js"></script>
    
<link type="text/css" rel="stylesheet" href="<%=basePath %>/company/css/all_style.css"/>

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.weitianxie table td{line-height:25px;text-align: center;}

.step04{width: 112px;}
.combo{
background-color: #fff;
border-color: #07aa94;
}
.noInfo{text-align: center; margin-top: 100px;height: 150px;}
.center_content3{width: 900px;margin-top: 10px;}
</style>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="proListNew.jsp">产品管理</a></li>
    <li><a href="#">产品溯源信息</a></li>
    </ul>
    </div>

<div>
    <div class="CENTER">
    	<div class="title_step2_1">
        	<div class="step04Check" onclick="changeTab('proArea',this)"><a>产地管理</a></div>
            <div class="" onclick="changeTab('proSeed',this)"><a>种源管理</a></div>
            <div class="" onclick="changeTab('plantRaise',this)"><a><%=proType.equals("1")?"施肥管理":"喂养管理" %></a></div>
            <div class="" onclick="changeTab('prevention',this)"><a><%=proType.equals("1")?"植保管理":"防疫管理" %></a></div>
            <div class="" onclick="changeTab('process',this)"><a>加工包装</a></div>
            <div class="" onclick="changeTab('storeTransport',this)"><a>仓储运输</a></div>
            <div class="" onclick="changeTab('proCheck',this)"><a>产品检验</a></div>
            <div class="" onclick="changeTab('proBatch',this)"><a>批次管理</a></div>
        </div>
        <!-- 产地管理 -->
        <div class="center_content3" id="proArea">
	        <div class="baocunhou" id="proFormTable" style="display:none;">
	        	<form id="proAreaForm" method="post">
	        		<div id="hiddenValue" style="display: none;">
	        			<input type="hidden" name="proArea.proId" value="<%=proId %>"/>
	        		</div>
	        		<div id="fileList"></div>
	            	<table class="formtable">
	                  <tr>
					      	<td class="form_label" style="width:180px;">产地名称:</td>
					    	<td class="form_value">
								<input class="easyui-validatebox" name="proArea.areaName" style="width: 500px" maxlength="100"  />
					    	</td>
						</tr>				    
					    <tr>
					    	<td class="form_label">产地地址:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="proArea.areaAddr" style="width: 500px" maxlength="100" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">产地概况:</td>
						    <td class="form_value">
								<textarea class="easyui-validatebox" name="proArea.areaIntro" style="width: 600px" maxlength="1500" rows="3"></textarea>
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">土壤环境:</td>
						    <td class="form_value">
								<textarea class="easyui-validatebox" name="proArea.edatope" style="width: 600px" maxlength="500" rows="3"></textarea>
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">灌溉水源:</td>
						    <td class="form_value">
								<textarea class="easyui-validatebox" name="proArea.areaWater" style="width: 600px" maxlength="500" rows="3"></textarea>
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">气候环境:</td>
						    <td class="form_value">
								<textarea class="easyui-validatebox" name="proArea.climatope" style="width: 600px" maxlength="500" rows="3"></textarea>
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">附件:</td>
						    <td class="form_value">
								<input type="file" id="append_proArea"/>
								<div id="fileQueue"></div>
					    	</td>
					    </tr>
	               </table>
	               </form>
	               <div class="btn_area">
	               	<input  name="" type="button" class="btn" onclick="submitProAreaForm();" value="保 存"/>
	               	<input  name="" type="button" class="btn" onclick="cancelProArea();" value="取消"/>
	               	</div>
	            </div>
	        
	        	<div id="proAreaTable">
				<div class="caozuo"><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addProArea();">添加</a></div>	            	
					<div id="proAreaList"></div>
	            </div>
         </div>
         
         <!-- 种源管理 -->
         <div class="center_content3" id="proSeed" style="display: none">
	        <div class="baocunhou" id="proSeedFormTable" style="display: none">
	        	<form id="proSeedForm" method="post">
	        		<div id="hiddenValue_seed" style="display: none;">
	        			<input type="hidden" name="proSeed.proId" value="<%=proId %>"/>
	        		</div>
	        		<div id="fileList_seed"></div>
	            	<table class="formtable">
	                  <tr>
					      	<td class="form_label" style="width:180px;">种苗名称:</td>
					    	<td class="form_value">
								<input class="easyui-validatebox" name="proSeed.seedName" maxlength="100" style="width: 500px" />
					    	</td>
						</tr>				    
					    <tr>
					    	<td class="form_label">种苗地址:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="proSeed.seedAddr" maxlength="100" style="width: 500px"/>
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">种苗厂家:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="proSeed.seedCompany" maxlength="100" style="width: 500px"/>
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">特征特性:</td>
						    <td class="form_value">
								<textarea class="easyui-validatebox" name="proSeed.feature" maxlength="1000" rows="3" style="width: 500px"></textarea>
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">附件:</td>
						    <td class="form_value">
								<input type="file" id="append_seed"/>
								<div id="fileQueue_seed"></div>
					    	</td>
					    </tr>
	               </table>
	               </form>
	               <div class="btn_area">
	               		<input  name="" type="button" class="btn" onclick="submitProSeedForm();" value="保 存"/>
	               		<input  name="" type="button" class="btn" onclick="cancelProSeed();" value="取 消"/>
	               	</div>
	            </div>
	        
	        	<div id="proSeedTable">
				<div class="caozuo"><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addProSeed();">添加</a></div>
	            	<div id="proSeedList"></div>
	            	
	            </div>
         </div>
         
         <!-- 施肥/喂养管理 -->
         
         <div class="center_content3" id="plantRaise" style="display: none">
	        <div class="baocunhou" id="plantRaiseFormTable" style="display: none">
	        	<form id="plantRaiseForm" method="post">
	        		<div id="hiddenValue_plantRaise" style="display: none;">
	        			<input type="hidden" name="plantRaise.proId" value="<%=proId %>"/>
	        		</div>
	        		<div id="fileList_plantRaise"></div>
	            	<table class="formtable">
	                  <tr>
					      	<td class="form_label"><%=name1%>名称:</td>
					    	<td class="form_value">
								<input class="easyui-validatebox" name="plantRaise.feedName" style="width: 500px" maxlength="50"  />
					    	</td>
						</tr>				    
					    <tr>
					    	<td class="form_label"><%=name1%>厂家:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="plantRaise.feedCompany" style="width: 500px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label"><%=name2%>方法:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="plantRaise.feedWay" style="width: 500px" maxlength="200" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label"><%=name2%>周期:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="plantRaise.feedCycle" style="width: 500px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label"><%=name2%>时间:</td>
						    <td class="form_value">
								<input class="easyui-datebox" name="plantRaise.feedTime" id="feedTime_id" style="width: 150px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label"><%=name2%>用量:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="plantRaise.dosage" style="width: 500px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">附件:</td>
						    <td class="form_value">
								<input type="file" id="append_plantRaise"/>
								<div id="fileQueue_plantRaise"></div>
					    	</td>
					    </tr>
	               </table>
	               </form>
	               <div class="btn_area">
	               		<input  name="" type="button" class="btn" onclick="submitplantRaisedForm();" value="保 存"/>
	               		<input  name="" type="button" class="btn" onclick="cancelPlantRaise();" value="取 消"/>
	               	</div>
	            </div>
	        
	        	<div id="plantRaiseTable">
				<div class="caozuo"><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addPlantRaise();">添加</a></div>
	            	<div id="plantRaiseList"></div>
	            </div>
         </div>
         
         
         <!-- 防疫/植保管理-->
         <div class="center_content3" id="prevention" style="display: none">
	        <div class="baocunhou" id="preventionFormTable" style="display: none">
	        	<form id="preventionForm" method="post">
	        		<div id="hiddenValue_prevention" style="display: none;">
	        			<input type="hidden" name="prevention.proId" value="<%=proId %>"/>
	        		</div>
	        		<div id="fileList_prevention"></div>
	            	<table class="formtable">
	                  <tr>
					      	<td class="form_label" style="width:180px;">药品名称:</td>
					    	<td class="form_value">
								<input class="easyui-validatebox" name="prevention.drugName" style="width: 500px" maxlength="50"  />
					    	</td>
						</tr>				    
					    <tr>
					    	<td class="form_label">药品厂家:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="prevention.drugCompany" style="width: 500px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">防治对象:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="prevention.drugObject" style="width: 500px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">用药方法:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="prevention.drugWay" style="width: 500px" maxlength="200" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">用药周期:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="prevention.drugCycle" style="width: 500px" maxlength="50" />
					    	</td>
					    </tr>
					    
					    <tr>
					    	<td class="form_label">用药时间:</td>
						    <td class="form_value">
								<input class="easyui-datebox" name="prevention.drugTime" id="drugTime_id" style="width: 150px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">用药剂量:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="prevention.dosage" style="width: 500px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">附件:</td>
						    <td class="form_value">
								<input type="file" id="append_prevention"/>
								<div id="fileQueue_prevention"></div>
					    	</td>
					    </tr>
	               </table>
	               </form>
	               <div class="btn_area">
	               		<input  name="" type="button" class="btn" onclick="submitpreventionForm();" value="保 存"/>
	               		<input  name="" type="button" class="btn" onclick="cancelprevention();" value="取 消"/>
	               	</div>
	            </div>
	        
	        	<div id="preventionTable">
				<div class="caozuo"><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addPrevention();">添加</a></div>
	            	<div id="preventionList"></div>
	            </div>
         </div>
         
         <!-- 产品检验 -->
         <div class="center_content3" id="proCheck" style="display: none">
	        <div class="baocunhou" id="proCheckFormTable" style="display: none">
	        	<form id="proCheckForm" method="post">
	        		<div id="hiddenValue_proCheck" style="display: none;">
	        			<input type="hidden" name="proCheck.proId" value="<%=proId %>"/>
	        		</div>
	        		<div id="fileList_proCheck"></div>
	            	<table class="formtable">
	                  <tr>
					      	<td class="form_label" style="width:180px;">报告名称:</td>
					    	<td class="form_value">
								<input class="easyui-validatebox" name="proCheck.checkName" style="width: 500px" maxlength="50"  />
					    	</td>
						</tr>				    
					    <tr>
					    	<td class="form_label">报告编号:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="proCheck.checkNum" style="width: 500px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">检验单位:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="proCheck.checkUnit" style="width: 500px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">检验时间:</td>
						    <td class="form_value">
								<input class="easyui-datebox" name="proCheck.checkTime" id="checkTime_id" style="width: 150px" maxlength="25" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">检验结果:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="proCheck.checkResult" style="width: 500px" maxlength="50" />
					    	</td>
					    </tr>
					    <tr>
					    	<td class="form_label">附件:</td>
						    <td class="form_value">
								<input type="file" id="append_proCheck"/>
								<div id="fileQueue_proCheck"></div>
					    	</td>
					    </tr>
	               </table>
	               </form>
	               <div class="btn_area">
	               		<input  name="" type="button" class="btn" onclick="submitProCheckForm();" value="保 存"/>
	               		<input  name="" type="button" class="btn" onclick="cancelProCheck();" value="取 消"/>
	               	</div>
	            </div>
	        
	        	<div id="proCheckTable">
				<div class="caozuo"><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addProCheck();">添加</a></div>
	            	<div id="proCheckList"></div>
	            </div>
         </div>
         
         <!-- 加工包装 -->
         <div class="center_content3" id="process" style="display: none">
	        <div class="baocunhou" id="processFormTable" style="display: none">
	        	<form id="processForm" method="post">
	        		<div id="hiddenValue_process" style="display: none;">
	        			<input type="hidden" name="process.proId" value="<%=proId %>"/>
	        		</div>
	        		<div id="fileList_process"></div>
	            	<table class="formtable">
	                 	 <tr>
					    	<td class="form_label" style="width:180px;">加工厂家:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="process.processCompany" style="width: 500px" maxlength="100" />
					    	</td>
					     </tr>
	                 	 <tr>
					      	<td class="form_label">加工负责人:</td>
					    	<td class="form_value">
								<input class="easyui-validatebox" name="process.processUser" style="width: 500px" maxlength="50"  />
					    	</td>
						</tr>				    
					    <tr>
					    	<td class="form_label">加工地址:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="process.processAddr" style="width: 500px" maxlength="100" />
					    	</td>
					    </tr>	
					    <tr>
					    	<td class="form_label">加工时间:</td>
						    <td class="form_value">
								<input class="easyui-datebox" name="process.processTime" id="processTime_id" style="width: 150px" maxlength="25" />
					    	</td>
					    </tr>

					    <tr>
					    	<td class="form_label">附件:</td>
						    <td class="form_value">
								<input type="file" id="append_process"/>
								<div id="fileQueue_process"></div>
					    	</td>
					    </tr>
	               </table>
	               </form>
	               <div class="btn_area">
	               		<input  name="" type="button" class="btn" onclick="submitProcessForm();" value="保 存"/>
	               		<input  name="" type="button" class="btn" onclick="cancelProcess();" value="取 消"/>
	               	</div>
	            </div>
	        
	        	<div id="processTable">
				<div class="caozuo"><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addProcess();">添加</a></div>
	            	<div id="processList"></div>
	            </div>
         </div>
         <!-- 仓储运输 -->
         <div class="center_content3" id="storeTransport" style="display: none">
	        <div class="baocunhou" id="storeTransportFormTable" style="display: none">
	        	<form id="storeTransportForm" method="post">
	        		<div id="hiddenValue_storeTransport" style="display: none;">
	        			<input type="hidden" name="storeTransport.proId" value="<%=proId %>"/>
	        		</div>
	        		<div id="fileList_storeTransport"></div>
	            	<table class="formtable">
	                 	 <tr>
					    	<td class="form_label" style="width:180px;">仓储方式:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="storeTransport.storageWay" style="width: 500px" maxlength="100" />
					    	</td>
					     </tr>
	                 	 <tr>
					      	<td class="form_label">仓储条件:</td>
					    	<td class="form_value">
								<input class="easyui-validatebox" name="storeTransport.storageCondi" style="width: 500px" maxlength="50"  />
					    	</td>
						</tr>				    
					    <tr>
					    	<td class="form_label">运输方式:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="storeTransport.transportWay" style="width: 500px" maxlength="100" />
					    	</td>
					    </tr>	
					    <tr>
					    	<td class="form_label">附件:</td>
						    <td class="form_value">
								<input type="file" id="append_storeTransport"/>
								<div id="fileQueue_storeTransport"></div>
					    	</td>
					    </tr>
	               </table>
	               </form>
	               <div class="btn_area">
	               		<input  name="" type="button" class="btn" onclick="submitStoreTransportForm();" value="保 存"/>
	               		<input  name="" type="button" class="btn" onclick="cancelStoreTransport();" value="取 消"/>
	               	</div>
	            </div>
	        
	        	<div id="storeTransportTable">
				<div class="caozuo"><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addStoreTransport();">添加</a></div>
	            	<div id="storeTransportList"></div>
	            </div>
         </div>
         <!-- 批次信息 -->
         <div class="center_content3" id="proBatch" style="display: none">
	        <div class="baocunhou" id="proBatchFormTable" style="display: none">	        
	        	<form id="proBatchForm" method="post">
	        		<div id="hiddenValue_proBatch" style="display: none;">
	        			<input type="hidden" name="proBatch.proId" value="<%=proId %>"/>
	        		</div>
	            	<table class="formtable">
	                 	 <tr>
					    	<td class="form_label" style="width:180px;">批次编号:</td>
						    <td class="form_value">
								<input class="easyui-validatebox" name="proBatch.batchNo" style="width: 300px" maxlength="50" />(如不填写，系统将自动生成批次编号)
					    	</td>
					     </tr>
	                 	 <tr>
					      	<td class="form_label">生产日期:</td>
					    	<td class="form_value">
								<input class="easyui-datebox" name="proBatch.proTime"  data-options="required:true,editable:false" style="width: 150px" maxlength="50"  />
					    	</td>
						</tr>				    
					    	
	               </table>
	               </form>
	               <div class="btn_area">
	               		<input  name="" type="button" class="btn" onclick="submitProBatchForm();" value="保 存"/>
	               		<input  name="" type="button" class="btn" onclick="cancelProBatch();" value="取 消"/>
	               	</div>
	            </div>
	        
	        	<div class="weitianxie" id="proBatchTab">
				<div class="caozuo"><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addProBatch();">添加</a></div>
	            	<table width="100%" id="proBatchTable" border="0" cellspacing="1" cellpadding="1" style="margin:0;" bgcolor="#CCCCCC">

					</table>
					<div class="page"></div>
	          </div>
	          
          	<!-- 显示批次二维码信息 -->
			<div id="batchCode" class="easyui-window" data-options="iconCls:'icon-qrcode',closed:true" title="批次二维码信息" style="width:550px;height:400px;top:100px">
		     	 <div style="height:20px;width: 100%" align="center">
			         <br/>
			         	<span id="sh_info"></span>	
			         <br/>	 
			     </div>
			        <!--startprint-->
			        <div style="height:250px;width: 100%" align="center">
			        	<span id="code_Img"></span>
			        </div>
			        <!--endprint-->
			        <div style="height:50px;width: 100%" align="center">
			        	<input type="hidden" id="Dimenno_Img">
			        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-download'" onclick="downloadCodeImg(1)">下载标签图片</a>&nbsp;&nbsp;
			        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-download'" onclick="downloadCodeImg(2)">下载二维码图片</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-download'" onclick="downloadCodeImg(3)">下载二维码高清大图</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="$('#batchCode').window('close')">关闭页面</a>
					</div>
			</div>
         </div>
         
        <div class="clear"></div>
        <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
        <input type="hidden" id="proId" value="<%=proId %>"/>
        <input type="hidden" id="filePath" value="<%=filePath %>"/>
        <input type="hidden" id="proType" value="<%=proType %>"/>
    </div>
    <div class="clear"></div>
</div>
</body>
</html>
