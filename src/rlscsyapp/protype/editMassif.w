<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="top:484px;left:192px;height:auto;"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="true" xid="massifData" onCustomRefresh="massifDataCustomRefresh"></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar" xid="title1">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title"></div>  
          <div class="x-titlebar-right reverse"> 
          </div>
        </div> 
      </div>  
    <div class="x-panel-content x-cards" xid="content1">
  <div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu" xid="panel3">
   <div xid="div12">
    <div class="panel-heading" xid="div8">
     <div class="media-left media-middle" xid="mediaLeft1">
      <span class="x-flex" xid="span2" style="width:90px;"><![CDATA[地块名称]]></span></div> 
     <div class="media-body" xid="mediaBody1">
      <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="maName"></input></div> </div> 
    <div class="panel-heading" xid="div4">
     <div class="media-left media-middle" xid="mediaLeft2">
      <span class="x-flex" xid="span3" style="width:90px;"><![CDATA[种植品种]]></span></div> 
     <div class="media-body" xid="mediaBody2">
      <select component="$UI/system/components/justep/select/select" class="form-control x-edit" xid="ptqId"></select></div> </div> 
    <div class="panel-heading" xid="div14">
     <div class="media-left media-middle" xid="mediaLeft3">
      <span class="x-flex" xid="span4" style="width:90px;"><![CDATA[地块面积]]></span></div> 
     <div class="media-body" xid="mediaBody3">
      <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="maAcreage"></input></div> </div> 
    <div class="panel-heading" xid="div13">
     <div class="media-left media-middle" xid="mediaLeft4">
      <span class="x-flex" xid="span5" style="width:90px;"><![CDATA[种植时间]]></span></div> 
     <div class="media-body" xid="mediaBody4">
      <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="startTime" onFocus="startTimeFocus"></input></div> </div> 
    <div class="panel-heading" xid="div5">
   <div class="media-left media-middle" xid="mediaLeft6">
    <span class="x-flex" xid="span6" style="width:90px;"><![CDATA[采收时间]]></span></div> 
   <div class="media-body" xid="mediaBody6">
    <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="getTime" onFocus="getTimeFocus"></input></div> </div><div class="panel-heading" xid="div9">
   <div class="media-left media-middle" xid="mediaLeft7">
    <span class="x-flex" xid="span8" style="width:90px;"><![CDATA[上期种植品种]]></span></div> 
   <div class="media-body" xid="mediaBody7">
    <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="state"></input></div> </div><div class="panel-heading" xid="div7">
     <div class="media-left media-middle" xid="mediaLeft5">
      <span class="x-flex" style="width:70px;" xid="span15">种类图片</span></div> 
     <div class="media-body" xid="mediaBody5">
      <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs" label="上传" xid="buttonUpload" icon="icon-android-add" bind-click="buttonUploadClick" onClick="buttonUploadClick">
       <i xid="i5" class="icon-android-add"></i>
       <span xid="span24">上传</span></a> 
      <a component="$UI/system/components/justep/button/button" class="btn x-green btn-xs" label="移除" xid="buttonRemove" style="margin-left:10px;" icon="icon-android-remove" onClick="buttonRemoveClick">
       <i xid="i6" class="icon-android-remove"></i>
       <span xid="span25">移除</span></a> 
      <input type="file" value="" xid="inputImage" size="1" bind-change="inputImageChange" accept="image/*" style="display:none;"></input></div> 
     <div class="media-right" xid="mediaRight1">
      <span xid="spanImg"></span></div> </div> 
    <div class="panel-heading" xid="div6">
     <a component="$UI/system/components/justep/button/button" class="btn x-green btn-only-label btn-block" label="提交" xid="button1" onClick="saveMassifClick">
      <i xid="i2"></i>
      <span xid="span7">提交</span></a> </div> 
  
  </div> </div></div>
  </div> 
<div component="$UI/system/components/justep/datePicker/datePicker" class="x-popPicker" xid="datePicker" style="top:478px;left:457px;" onOK="datePickerOK">
   <div class="x-popPicker-overlay" xid="div1"></div>
   <div class="x-popPicker-content" xid="div2">
    <div class="x-poppicker-header" xid="div3">
     <button class="btn btn-default x-btn-ok" xid="button2">确定</button></div> </div> </div>
  <span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver" style="top:477px;left:527px;" onReceive="windowReceiverReceive"></span></div>