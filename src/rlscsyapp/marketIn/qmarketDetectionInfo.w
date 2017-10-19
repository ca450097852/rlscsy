<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="top:16px;left:641px;height:auto;" onParamsReceive="modelParamsReceive" onActive="modelActive"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="qmarket_detection_info" idColumn="qdiId" onCustomRefresh="qmarket_detection_infoCustomRefresh"><column label="miibId" name="qdiId" type="Integer" xid="xid1"></column>
  <column label="comId" name="comId" type="Integer" xid="xid2"></column>
  <column label="ptbId" name="ptbId" type="Integer" xid="xid3"></column>
  <column label="flag" name="marketId" type="String" xid="xid4"></column>
  <column label="marketId" name="marketName" type="String" xid="xid5"></column>
  <column label="marketName" name="wholesalerId" type="String" xid="xid6"></column>
  <column label="inDate" name="wholesalerName" type="String" xid="xid7"></column>
  <column label="wholesalerId" name="tranId" type="String" xid="xid8"></column>
  <column label="wholesalerName" name="quarantineAnimalProductsId" type="String" xid="xid9"></column>
  <column label="tranId" name="inspectionMeatId" type="String" xid="xid10"></column>
  <column label="quarantineAnimalProductsId" name="batchId" type="String" xid="xid11"></column>
  <column label="inspectionMeatId" name="goodsCode" type="String" xid="xid12"></column>
  <column label="provId" name="goodsName" type="String" xid="xid13"></column>
  <column label="quarantineVegeId" name="sampleId" type="String" xid="xid14"></column>
  <column label="batchId" name="detector" type="String" xid="xid15"></column>
  <column label="transporterId" name="detectionDate" type="String" xid="xid16"></column>
  <column label="createTime" name="detectionResult" type="String" xid="xid17"></column>
  <column label="resultExpl" name="resultExpl" type="String" xid="xid18"></column>
  <column label="creatTime" name="creatTime" type="String" xid="xid19"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="批发检验管理"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">批发检验管理</div>  
          <div class="x-titlebar-right reverse"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="addPfBaseBtn" icon="linear linear-bus" onClick="addPfBaseBtnClick">
   <i xid="i1" class="linear linear-bus"></i>
   <span xid="span1"></span></a></div>
        </div> 
      </div>  
    <div class="x-panel-content  x-scroll-view" xid="content1" _xid="C787DDC3E180000112ADB9C0A81D8900" style="bottom: 0px;"><div class="x-scroll" component="$UI/system/components/justep/scrollView/scrollView" xid="scrollView1" onPullUp="scrollView1PullUp" style="width:100%;">
   <div class="x-content-center x-pull-down container" xid="div1">
    <i class="x-pull-down-img glyphicon x-icon-pull-down" xid="i4"></i>
    <span class="x-pull-down-label" xid="span19">下拉刷新...</span></div> 
   <div class="x-scroll-content" xid="div3">
    <div component="$UI/system/components/justep/list/list" class="x-list x-cards" xid="list1" data="qmarket_detection_info" limit="-1" disablePullToRefresh="false" disableInfiniteLoad="false" autoLoad="false">
     <ul class="x-list-template  list-group" xid="listTemplateUl1">
      <li xid="li1" style="width:100%;" class="list-group-item">
       <div class="caption" xid="div2">
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row2">
         <div class="x-col" xid="col5">
          <span xid="span2"><![CDATA[批发市场编码：]]></span>
          <span xid="span10" bind-text='ref("marketId")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row1">
         <div class="x-col" xid="col1">
          <span xid="span4"><![CDATA[批发市场名称：]]></span>
          <span xid="span3" bind-text=' val("marketName")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row3">
         <div class="x-col" xid="col2">
          <span xid="span6"><![CDATA[检测日期：]]></span>
          <span xid="span5" bind-text='ref("detectionDate")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row7">
         <div class="x-col" xid="col9">
          <span xid="span14"><![CDATA[商品名称：]]></span>
          <span xid="span13" bind-text='ref("goodsName")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row4">
         <div class="x-col" xid="col7">
          <span xid="span9"><![CDATA[检测结果：]]></span>
          <span xid="span8" bind-text='ref("resultExpl")'></span></div> </div> 
        
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row6">
         <div class="x-col" xid="col6">
          <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="修改" xid="button1" icon="icon-edit" onClick="updateClick">
           <i xid="i2" class="icon-edit"></i>
           <span xid="span16">修改</span></a> </div> 
         <div class="x-col" xid="col3">
          <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="删除" xid="button4" icon="icon-close" onClick="deleteClick">
           <i xid="i5" class="icon-close"></i>
           <span xid="span7">删除</span></a> </div> 
         
         </div> </div> </li> </ul> </div> </div> 
   <div class="x-content-center x-pull-up" xid="div4">
    <span class="x-pull-up-label" xid="span20">加载更多...</span></div> </div></div>
  </div> 
<span component="$UI/system/components/justep/messageDialog/messageDialog" xid="messageDialog1" type="YesNo" onYes="messageDialog1Yes" message="是否删除进场信息" style="top:13px;left:95px;"></span>
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="editwindowDialog" style="top:12px;left:126px;" onReceived="editwindowDialogReceived"></span></div>