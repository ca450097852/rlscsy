<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="top:16px;left:641px;height:auto;width:295px;" onParamsReceive="modelParamsReceive" onActive="modelActive"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="meatOutInfoBaseData" idColumn="moibId" onCustomRefresh="meatOutInfoBaseDataCustomRefresh"><column label="rmiisId" name="moibId" type="Integer" xid="xid1"></column>
  <column label="comId" name="comId" type="Integer" xid="xid4"></column>
  <column label="ptbId" name="ptbId" type="Integer" xid="xid5"></column>
  <column label="butcherFacId" name="butcherFacId" type="String" xid="xid6"></column>
  <column label="butcherFacName" name="butcherFacName" type="String" xid="xid7"></column>
  <column label="tranDate" name="tranDate" type="String" xid="xid8"></column>
  <column label="sellerId" name="sellerId" type="String" xid="xid9"></column>
  <column label="sellerName" name="sellerName" type="String" xid="xid10"></column>
  <column label="buyerId" name="buyerId" type="String" xid="xid11"></column>
  <column label="buyerName" name="buyerName" type="String" xid="xid12"></column>
  <column label="dest" name="dest" type="String" xid="xid13"></column>
  <column label="tranId" name="tranId" type="String" xid="xid14"></column>
  <column label="flag" name="flag" type="String" xid="xid15"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="批发交易基本信息"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">批发交易基本信息</div>  
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
    <div component="$UI/system/components/justep/list/list" class="x-list x-cards" xid="list1" data="meatOutInfoBaseData" limit="-1" disablePullToRefresh="false" disableInfiniteLoad="false" autoLoad="false">
     <ul class="x-list-template  list-group" xid="listTemplateUl1">
      <li xid="li1" style="width:100%;" class="list-group-item">
       <div class="caption" xid="div2">
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row2">
         <div class="x-col" xid="col5">
          <span xid="span2"><![CDATA[批发市场编码：]]></span>
          <span xid="span10" bind-text='ref("butcherFacId")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row1">
         <div class="x-col" xid="col1">
          <span xid="span4"><![CDATA[批发市场名称：]]></span>
          <span xid="span3" bind-text=' val("butcherFacName")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row3">
         <div class="x-col" xid="col2">
          <span xid="span6"><![CDATA[交易日期：]]></span>
          <span xid="span5" bind-text='ref("tranDate")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row7">
         <div class="x-col" xid="col9">
          <span xid="span14"><![CDATA[货主名称：]]></span>
          <span xid="span13" bind-text='ref("sellerName")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row4">
         <div class="x-col" xid="col7">
          <span xid="span9"><![CDATA[买主名称：]]></span>
          <span xid="span8" bind-text='ref("buyerName")'></span></div> </div> 
        
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row6">
         <div class="x-col" xid="col6">
          <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="修改" xid="button1" icon="icon-edit" onClick="updateClick">
           <i xid="i2" class="icon-edit"></i>
           <span xid="span16">修改</span></a> </div> 
         <div class="x-col" xid="col3">
          <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="删除" xid="button4" icon="icon-close" onClick="deleteClick">
           <i xid="i5" class="icon-close"></i>
           <span xid="span7">删除</span></a> </div> 
         
         <div class="x-col" xid="col4">
   <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="明细" xid="button2" icon="linear linear-magnifier" onClick="detailClick">
    <i xid="i3" class="linear linear-magnifier"></i>
    <span xid="span11">明细</span></a> </div></div> </div> </li> </ul> </div> </div> 
   <div class="x-content-center x-pull-up" xid="div4">
    <span class="x-pull-up-label" xid="span20">加载更多...</span></div> </div></div>
  </div> 
<span component="$UI/system/components/justep/messageDialog/messageDialog" xid="messageDialog1" type="YesNo" onYes="messageDialog1Yes" message="是否删除交易信息" style="top:16px;left:79px;"></span>
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="editwindowDialog" onReceived="editwindowDialogReceived" style="top:10px;left:79px;"></span></div>