<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:341px;left:224px;"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="true" xid="proType" idColumn="ptqId" onCustomRefresh="proTypeCustomRefresh"><column label="ptqId" name="ptqId" type="Integer" xid="xid1"></column>
  <column label="typeName" name="typeName" type="String" xid="xid2"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1">
  <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar" xid="titleBar1">
   <div class="x-titlebar-left" xid="left1"><a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="返回" xid="backBtn" icon="icon-arrow-left-c" onClick="{operation:'window.close'}">
   <i xid="i2" class="icon-arrow-left-c"></i>
   <span xid="span5">返回</span></a></div>
   <div class="x-titlebar-title" xid="title1"><span xid="span4"><![CDATA[添加批次信息]]></span></div>
   <div class="x-titlebar-right reverse" xid="right1"><a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="saveBatch" icon="icon-android-checkmark" onClick="saveBatchClick">
   <i xid="i1" class="icon-android-checkmark"></i>
   <span xid="span9"></span></a></div>
  </div></div>  
    <div class="x-panel-content" xid="content1" style="padding:8px 8px 8px 8px;"><div component="$UI/system/components/justep/labelEdit/labelEdit" class="x-label-edit x-label30" xid="labelEdit3">
   <label class="x-label" xid="label4"><![CDATA[品种名称：]]></label>
   <select component="$UI/system/components/justep/select/select" class="form-control x-edit" xid="ptqId"></select></div><div component="$UI/system/components/justep/labelEdit/labelEdit" class="x-label-edit x-label30" xid="labelEdit2">
   <label class="x-label" xid="label2"><![CDATA[批次名称：]]></label>
   <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="batchName"></input></div>
  <div component="$UI/system/components/justep/labelEdit/labelEdit" class="x-label-edit x-label30" xid="labelInput1">
   <label class="x-label" xid="label3"><![CDATA[生产日期：]]></label>
   <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="batchTime" onFocus="batchTimeFocus"></input></div>
  </div>
  </div> 
<div component="$UI/system/components/justep/datePicker/datePicker" class="x-popPicker" xid="batchTimePicker" style="top:394px;left:261px;" onOK="batchTimePickerOK">
   <div class="x-popPicker-overlay" xid="div4"></div>
   <div class="x-popPicker-content" xid="div5">
    <div class="x-poppicker-header" xid="div6">
     <button class="btn btn-default x-btn-ok" xid="button2">确定</button></div> </div> </div>
  <span component="$UI/system/components/justep/messageDialog/messageDialog" xid="messageDialog" title="提示" style="top:74px;left:24px;"></span>
  <span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver" onReceive="windowReceiverReceive"></span></div>