<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">
<title>农产品质量安全追溯平台</title>  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:242px;top:61px;" onParamsReceive="modelParamsReceive"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="meatOutInfoBaseData" idColumn="moibId" limit="5" onCustomRefresh="meatOutInfoBaseDataCustomRefresh"><column label="moibId" name="moibId" type="Integer" xid="xid1"></column>
  <column label="comId" name="comId" type="Integer" xid="xid2"></column>
  <column label="ptbId" name="ptbId" type="String" xid="xid3"></column>
  <column label="butcherFacId" name="butcherFacId" type="String" xid="xid4"></column>
  <column label="butcherFacName" name="butcherFacName" type="String" xid="xid5"></column>
  <column label="tranDate" name="tranDate" type="String" xid="xid7"></column>
  <column label="sellerId" name="sellerId" type="String" xid="xid8"></column>
  <column label="sellerName" name="sellerName" type="String" xid="xid6"></column>
  <column label="buyerId" name="buyerId" type="String" xid="xid9"></column>
  <column label="buyerName" name="buyerName" type="String" xid="xid10"></column>
  <column label="dest" name="dest" type="String" xid="xid11"></column>
  <column label="tranId" name="tranId" type="String" xid="xid12"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="交易基本信息"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">交易基本信息</div>  
          <div class="x-titlebar-right reverse"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="button3" icon="icon-android-add" onClick="addClick">
   <i xid="i3" class="icon-android-add"></i>
   <span xid="span18"></span></a></div>
        </div> 
      </div>  
    <div class="x-panel-content  x-scroll-view" xid="content1" _xid="C7192A377500000181111E4A1DA2135B" style="bottom: 0px;"><div class="x-scroll" component="$UI/system/components/justep/scrollView/scrollView" xid="scrollView1" onPullUp="scrollView1PullUp">
   <div class="x-content-center x-pull-down container" xid="div1">
    <i class="x-pull-down-img glyphicon x-icon-pull-down" xid="i4"></i>
    <span class="x-pull-down-label" xid="span19">下拉刷新...</span></div> 
   <div class="x-scroll-content" xid="div3"><div component="$UI/system/components/justep/list/list" class="x-list x-cards" xid="list1" data="meatOutInfoBaseData" limit="-1" disablePullToRefresh="false" disableInfiniteLoad="false" autoLoad="false">
   <ul class="x-list-template  list-group" xid="listTemplateUl1">
    <li xid="li1" style="width:100%;" class="list-group-item">
     <div class="caption" xid="div2">
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row2">
       <div class="x-col" xid="col5">
        <span xid="span2"><![CDATA[屠宰厂编码：]]></span>
        <span xid="span10" bind-text='ref("butcherFacId")'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row1">
       <div class="x-col" xid="col1">
        <span xid="span4">屠宰厂名称：</span>
        <span xid="span3" bind-text='ref("butcherFacName")'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row3">
       <div class="x-col" xid="col2">
        <span xid="span6"><![CDATA[交易日期：]]></span>
        <span xid="span5" bind-text='ref("tranDate")'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row4">
       <div class="x-col" xid="col3">
        <span xid="span8">货主编码：</span>
        <span xid="span7" bind-text='ref("sellerId")'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row5">
       <div class="x-col" xid="col4">
        <span xid="span11">货主名称：</span>
        <span xid="span9" bind-text='ref("sellerName")'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row7">
       <div class="x-col" xid="col7">
        <span xid="span12"><![CDATA[交易凭证号：]]></span>
        <span xid="span1" bind-text='ref("tranId")'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row8">
       <div class="x-col" xid="col9">
        <span xid="span14"><![CDATA[买主名称：]]></span>
        <span xid="span13" bind-text='ref("buyerName")'></span>
        </div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row10">
   <div class="x-col" xid="col16">
   <span xid="span21"><![CDATA[到达地：]]></span>
   <span xid="span22" bind-text='ref("dest")'></span>
   </div>
  </div><div component="$UI/system/components/justep/row/row" class="x-row" xid="row6">
       <div class="x-col" xid="col6">
        <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="修改" xid="button1" icon="icon-edit" onClick="updateClick">
         <i xid="i1" class="icon-edit"></i>
         <span xid="span16">修改</span></a> </div> 
       <div class="x-col" xid="col8">
        <a component="$UI/system/components/justep/button/button" class="btn x-green btn-xs btn-block" label="删除" xid="button2" icon="icon-close" onClick="delClick">
         <i xid="i2" class="icon-close"></i>
         <span xid="span17">删除</span></a> </div> 
  <div class="x-col" xid="col10">
   <a component="$UI/system/components/justep/button/button" class="btn x-green btn-xs btn-block" label="交易明细" xid="button4" icon="linear linear-sad" onClick="detailClick">
    <i xid="i5" class="linear linear-sad"></i>
    <span xid="span15">交易明细</span></a> </div></div> 
  </div> </li> </ul> </div></div>
   <div class="x-content-center x-pull-up" xid="div4">
    <span class="x-pull-up-label" xid="span20">加载更多...</span></div> </div></div>
  </div> 
<span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialogEdit" onReceive="windowDialogEditReceive" style="top:17px;left:53px;"></span>
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialogImg" onReceive="windowDialogImgReceive" style="top:19px;left:92px;"></span></div>