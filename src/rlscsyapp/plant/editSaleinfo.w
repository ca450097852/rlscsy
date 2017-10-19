<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:63px;left:349px;" onParamsReceive="modelParamsReceive"> 
  
  <!-- <div component="$UI/system/components/justep/data/data" autoLoad="true" xid="comboData" idColumn="value" onCustomRefresh="comboDataCustomRefresh" autoNew="false">
  <column label="value" name="value" type="String" xid="xid1"></column>
  <column label="text" name="text" type="String" xid="xid2"></column></div> -->
  
  <div component="$UI/system/components/justep/data/data" autoLoad="true" xid="comboboxData" idColumn="value" autoNew="true" limit="5"><column label="value" name="value" type="String" xid="xid1"></column>
  <column label="text" name="text" type="String" xid="xid2"></column>
  <data xid="default1">[{&quot;value&quot;:&quot;无&quot;,&quot;text&quot;:&quot;无&quot;},{&quot;value&quot;:&quot;有&quot;,&quot;text&quot;:&quot;有&quot;}]</data></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="编辑交易信息"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">编辑交易信息</div>  
          <div class="x-titlebar-right reverse"> 
          </div>
        </div> 
      </div>  
    <div class="x-panel-content x-cards" xid="content1"><div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu" xid="panel3">
   <div xid="div2">
    <div class="panel-heading" xid="div12">
     <div class="media-left media-middle" xid="mediaLeft1">
      <span class="x-flex" style="width:90px;" xid="span2"><![CDATA[　上市日期：]]></span></div> 
     <div class="media-body" xid="mediaBody1">
      <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="listed" dataType="String" autoComplete="false"></input></div> </div><div class="panel-heading" xid="div8">
   <div class="media-left media-middle" xid="mediaLeft8">
    <span class="x-flex" style="width:90px;" xid="span9"><![CDATA[　生产日期：]]></span></div> 
   <div class="media-body" xid="mediaBody8">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="proDate" dataType="Date" autoComplete="false"></input></div> </div><div class="panel-heading" xid="div11">
     <div class="media-left media-middle" xid="mediaLeft1">
      <span class="x-flex" style="width:90px;" xid="span2"><![CDATA[　收购厂家：]]></span></div> 
     <div class="media-body" xid="mediaBody1">
  	
  	<!-- <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择..." class="form-control" xid="funame" bind-options="comboData"  bind-optionsValue="value" bind-optionsLabel="text"></select> -->
  	
  	<input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="sgCompany"></input></div> </div> 
    <div class="panel-heading" xid="div1">
   <div class="media-left media-middle" xid="mediaLeft4">
    <span class="x-flex" style="width:90px;" xid="span5"><![CDATA[　　收购人：]]></span></div> 
   <div class="media-body" xid="mediaBody4">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="sgUser" dataType="String"></input></div> </div><div class="panel-heading" xid="div7">
   <div class="media-left media-middle" xid="mediaLeft6">
    <span class="x-flex" style="width:90px;" xid="span7"><![CDATA[　　销售人：]]></span></div> 
   <div class="media-body" xid="mediaBody6">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="saleUser" dataType="String"></input></div> </div><div class="panel-heading" xid="div3">
   <div class="media-left media-middle" xid="mediaLeft2">
    <span class="x-flex" style="width:90px;" xid="span1"><![CDATA[主要销售地：]]></span></div> 
   <div class="media-body" xid="mediaBody2">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="salearea" dataType="String"></input></div> </div> 
     
     
  <div class="panel-heading" xid="div4">
   <div class="media-left media-middle" xid="mediaLeft3">
    <span class="x-flex" style="width:90px;" xid="span3"><![CDATA[　产品包装：]]></span></div> 
   <div class="media-body" xid="mediaBody3">
    <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择..." class="form-control input-sm" xid="packing" bind-options="comboboxData" bind-optionsValue="value" bind-optionsLabel="text"></select>
  </div> </div>
 
  
   <div class="panel-heading" xid="div6">
   <div class="media-left media-middle" xid="mediaLeft5">
    <span class="x-flex" style="width:90px;" xid="span6"><![CDATA[交易凭证号：]]></span></div> 
   <div class="media-body" xid="mediaBody5">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit requireField" xid="tranId" dataType="String"></input></div> </div>
  
 
    
      
      <!-- <div class="panel-heading" xid="div14">
     <div class="media-left media-middle" xid="mediaLeft1">
      <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[　　备注：]]></span></div> 
     <div class="media-body" xid="mediaBody1">
      <textarea component="$UI/system/components/justep/textarea/textarea" class="form-control" xid="remark"></textarea></div> </div> -->
      
      <div class="panel-heading" xid="div15">
     <a component="$UI/system/components/justep/button/button" class="btn btn-success  btn-block" label="提交" xid="button2" onClick="submitBtn">
      <i xid="i1"></i>
      <span xid="span4">提交</span></a> </div>
  </div> </div></div>
  </div> 
<span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver2" style="top:11px;left:72px;"></span></div>