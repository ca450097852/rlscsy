<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="top:16px;left:641px;height:auto;" onParamsReceive="modelParamsReceive"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="market_in_info_base" idColumn="miibId" onCustomRefresh="market_in_info_baseCustomRefresh"><column label="miibId" name="miibId" type="Integer" xid="xid1"></column>
  <column label="comId" name="comId" type="Integer" xid="xid2"></column>
  <column label="ptbId" name="ptbId" type="Integer" xid="xid3"></column>
  <column label="flag" name="flag" type="String" xid="xid4"></column>
  <column label="marketId" name="marketId" type="String" xid="xid5"></column>
  <column label="marketName" name="marketName" type="String" xid="xid6"></column>
  <column label="inDate" name="inDate" type="String" xid="xid7"></column>
  <column label="wholesalerId" name="wholesalerId" type="String" xid="xid8"></column>
  <column label="wholesalerName" name="wholesalerName" type="String" xid="xid9"></column>
  <column label="tranId" name="tranId" type="String" xid="xid10"></column>
  <column label="quarantineAnimalProductsId" name="quarantineAnimalProductsId" type="String" xid="xid11"></column>
  <column label="inspectionMeatId" name="inspectionMeatId" type="String" xid="xid12"></column>
  <column label="provId" name="provId" type="String" xid="xid13"></column>
  <column label="quarantineVegeId" name="quarantineVegeId" type="String" xid="xid14"></column>
  <column label="batchId" name="batchId" type="String" xid="xid15"></column>
  <column label="transporterId" name="transporterId" type="String" xid="xid16"></column>
  <column label="createTime" name="createTime" type="String" xid="xid17"></column>
  <column label="remark" name="remark" type="String" xid="xid18"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="批发市场肉类蔬菜进场基本信息"
          class="x-titlebar" xid="marketTitle">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">批发市场肉类蔬菜进场基本信息</div>  
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
    <div component="$UI/system/components/justep/list/list" class="x-list x-cards" xid="list1" data="market_in_info_base" limit="-1" disablePullToRefresh="false" disableInfiniteLoad="false" autoLoad="false">
     <ul class="x-list-template  list-group" xid="listTemplateUl1">
      <li xid="li1" style="width:100%;" class="list-group-item">
       <div class="caption" xid="div2">
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row2">
         <div class="x-col" xid="col5">
          <span xid="span2" bind-text=" val(&quot;flag&quot;)=='4'?'超市编码：':'市场编码：'"><![CDATA[市场编码：]]></span>
          <span xid="span10" bind-text='ref("marketId")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row1">
         <div class="x-col" xid="col1">
          <span xid="span4" bind-text=" val(&quot;flag&quot;)=='4'?'超市名称：':'市场名称：'"><![CDATA[市场名称：]]></span>
          <span xid="span3" bind-text=' val("marketName")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row3">
         <div class="x-col" xid="col2">
          <span xid="span6"><![CDATA[进  场  日  期 ：]]></span>
          <span xid="span5" bind-text='ref("inDate")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row7">
         <div class="x-col" xid="col9">
          <span xid="span14"><![CDATA[检疫合格证号：]]></span>
          <span xid="span13" bind-text='ref("quarantineAnimalProductsId")'></span></div> </div> 
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row4">
         <div class="x-col" xid="col7">
          <span xid="span9"><![CDATA[进 货 批 次 号：]]></span>
          <span xid="span8" bind-text='ref("batchId")'></span></div> </div> 
        
        <div component="$UI/system/components/justep/row/row" class="x-row" xid="row6">
         <div class="x-col" xid="col6">
          <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="修改" xid="button1" icon="icon-edit" onClick="updateClick">
           <i xid="i2" class="icon-edit"></i>
           <span xid="span16">修改</span></a> </div> 
         <div class="x-col" xid="col3">
          <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="删除" xid="button4" icon="icon-close" onClick="deleteClick">
           <i xid="i5" class="icon-close"></i>
           <span xid="span7">删除</span></a> </div> 
         
         <div class="x-col" xid="col8">
   <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="明细" xid="button3" icon="icon-search" onClick="detailClick">
    <i xid="i3" class="icon-search"></i>
    <span xid="span11">明细</span></a> </div></div> </div> </li> </ul> </div> </div> 
   <div class="x-content-center x-pull-up" xid="div4">
    <span class="x-pull-up-label" xid="span20">加载更多...</span></div> </div></div>
  </div> 
<span component="$UI/system/components/justep/messageDialog/messageDialog" xid="messageDialog1" type="YesNo" onYes="messageDialog1Yes" message="是否删除进场信息" style="top:13px;left:30px;"></span>
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="editwindowDialog" src="$UI/rlscsyapp/marketIn/editMarketInBase.w" onReceived="editwindowDialogReceived" style="top:13px;left:71px;"></span></div>