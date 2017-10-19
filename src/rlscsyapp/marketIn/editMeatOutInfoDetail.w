<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:13px;left:898px;" onParamsReceive="modelParamsReceive"> 
  </div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="编辑交易明细信息"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">编辑交易明细信息</div>  
          <div class="x-titlebar-right reverse"> 
          </div>
        </div> 
      </div>  
    <div class="x-panel-content x-cards" xid="content1"><div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu" xid="panel3">
   <div xid="div2">
    <div class="panel-heading" xid="div11">
     <div class="media-left media-middle" xid="mediaLeft1">
      <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[动物产品检疫合格证号]]></span></div> 
     <div class="media-body" xid="mediaBody1">
      <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="quarantineAnimalProductsId" dataType="String"></input></div> </div> 
    
    
    <div class="panel-heading" xid="div3">
   <div class="media-left media-middle" xid="mediaLeft2">
    <span class="x-flex" style="width:70px;" xid="span1"><![CDATA[肉品品质检验证号]]></span></div> 
   <div class="media-body" xid="mediaBody2">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="inspectionMeatId" dataType="String"></input></div> </div><div class="panel-heading" xid="div12">
     <div class="media-left media-middle" xid="mediaLeft1">
      <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[商品编码]]></span></div> 
     <div class="media-body" xid="mediaBody1">
      <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="meatCode" dataType="String" autoComplete="false"></input></div> </div> 
     
     
  <div class="panel-heading" xid="div4">
   <div class="media-left media-middle" xid="mediaLeft3">
    <span class="x-flex" style="width:70px;" xid="span3"><![CDATA[商品名称]]></span></div> 
   <div class="media-body" xid="mediaBody3">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="meatName" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div5">
   <div class="media-left media-middle" xid="mediaLeft4">
    <span class="x-flex" style="width:70px;" xid="span5"><![CDATA[重量]]></span></div> 
   <div class="media-body" xid="mediaBody4">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="weight" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div6">
   <div class="media-left media-middle" xid="mediaLeft5">
    <span class="x-flex" style="width:70px;" xid="span6"><![CDATA[单价]]></span></div> 
   <div class="media-body" xid="mediaBody5">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="price" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div15">
   <a component="$UI/system/components/justep/button/button" class="btn btn-success  btn-block" label="提交" xid="button2" onClick="submitBtn">
    <i xid="i1"></i>
    <span xid="span4">提交</span></a> </div></div> </div></div>
  </div> 
<span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver1" style="top:16px;left:48px;"></span></div>