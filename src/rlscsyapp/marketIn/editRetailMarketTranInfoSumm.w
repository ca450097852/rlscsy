<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:13px;left:898px;" onParamsReceive="modelParamsReceive"> 
  </div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="编辑销售信息"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">编辑销售信息</div>  
          <div class="x-titlebar-right reverse"> 
          </div>
        </div> 
      </div>  
    <div class="x-panel-content x-cards" xid="content1"><div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu" xid="panel3">
   <div xid="div2">
    <div class="panel-heading" xid="div11">
     <div class="media-left media-middle" xid="mediaLeft1">
      <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[市场编码]]></span></div> 
     <div class="media-body" xid="mediaBody1">
      <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="retailMarketId" dataType="String"></input></div> </div> 
    
    
    <div class="panel-heading" xid="div3">
   <div class="media-left media-middle" xid="mediaLeft2">
    <span class="x-flex" style="width:70px;" xid="span1"><![CDATA[市场名称]]></span></div> 
   <div class="media-body" xid="mediaBody2">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="retailMarketName" dataType="String"></input></div> </div><div class="panel-heading" xid="div12">
     <div class="media-left media-middle" xid="mediaLeft1">
      <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[进货日期]]></span></div> 
     <div class="media-body" xid="mediaBody1">
      <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="inDate" dataType="Date" autoComplete="false"></input></div> </div> 
     
     
  <div class="panel-heading" xid="div4">
   <div class="media-left media-middle" xid="mediaLeft3">
    <span class="x-flex" style="width:70px;" xid="span3"><![CDATA[销售日期]]></span></div> 
   <div class="media-body" xid="mediaBody3">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="saleDate" dataType="Date"></input></div> </div>
  <div class="panel-heading" xid="div5">
   <div class="media-left media-middle" xid="mediaLeft4">
    <span class="x-flex" style="width:70px;" xid="span5"><![CDATA[零售商编码]]></span></div> 
   <div class="media-body" xid="mediaBody4">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="retailerId" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div6">
   <div class="media-left media-middle" xid="mediaLeft5">
    <span class="x-flex" style="width:70px;" xid="span6"><![CDATA[零售商名称]]></span></div> 
   <div class="media-body" xid="mediaBody5">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="retailerName" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div7">
   <div class="media-left media-middle" xid="mediaLeft6">
    <span class="x-flex" style="width:70px;" xid="span7"><![CDATA[零售摊位号]]></span></div> 
   <div class="media-body" xid="mediaBody6">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="positionId" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div8">
   <div class="media-left media-middle" xid="mediaLeft7">
    <span class="x-flex" style="width:70px;" xid="span8"><![CDATA[交易凭证号]]></span></div> 
   <div class="media-body" xid="mediaBody7">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="tranId" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div9">
   <div class="media-left media-middle" xid="mediaLeft8">
    <span class="x-flex" style="width:70px;" xid="span9"><![CDATA[动物产品检疫合格证号]]></span></div> 
   <div class="media-body" xid="mediaBody8">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="quarantineAnimalProductsId" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div10">
   <div class="media-left media-middle" xid="mediaLeft9">
    <span class="x-flex" style="width:70px;" xid="span10"><![CDATA[进货批次号]]></span></div> 
   <div class="media-body" xid="mediaBody9">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="batchId" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div13">
   <div class="media-left media-middle" xid="mediaLeft10">
    <span class="x-flex" style="width:70px;" xid="span11"><![CDATA[商品编码]]></span></div> 
   <div class="media-body" xid="mediaBody10">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="goodsCode" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div16">
   <div class="media-left media-middle" xid="mediaLeft11">
    <span class="x-flex" style="width:70px;" xid="span12"><![CDATA[商品名称]]></span></div> 
   <div class="media-body" xid="mediaBody11">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="goodsName" dataType="String"></input></div> </div><div class="panel-heading" xid="div1">
   <div class="media-left media-middle" xid="mediaLeft12">
    <span class="x-flex" style="width:70px;" xid="span13"><![CDATA[重量]]></span></div> 
   <div class="media-body" xid="mediaBody12">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="weight" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div14">
   <div class="media-left media-middle" xid="mediaLeft13">
    <span class="x-flex" style="width:70px;" xid="span14"><![CDATA[单价]]></span></div> 
   <div class="media-body" xid="mediaBody13">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="price" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div17">
   <div class="media-left media-middle" xid="mediaLeft14">
    <span class="x-flex" style="width:70px;" xid="span15"><![CDATA[零售凭证号]]></span></div> 
   <div class="media-body" xid="mediaBody14">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="saleTranId" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div15">
   <a component="$UI/system/components/justep/button/button" class="btn btn-success  btn-block" label="提交" xid="button2" onClick="submitBtn">
    <i xid="i1"></i>
    <span xid="span4">提交</span></a> </div></div> </div></div>
  </div> 
<span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver1" style="top:12px;left:68px;"></span></div>