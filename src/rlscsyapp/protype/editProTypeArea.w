<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:340px;left:181px;" onLoad="modelLoad"> 
  </div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
            <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="编辑基地信息"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">编辑基地信息</div>  
          <div class="x-titlebar-right reverse"> 
          </div>
        </div> 
      </div>  
	<div class="x-panel-content x-cards" xid="content1">
		<div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu" xid="panel3">
   <div xid="div12">
    <div class="panel-heading" xid="div8">
     <div class="media-left media-middle" xid="mediaLeft1">
      <span class="x-flex" xid="span2" style="width:70px;"><![CDATA[基地名称]]></span></div> 
     <div class="media-body" xid="mediaBody1">
      <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="areaName"></input></div> </div> 
    <div class="panel-heading" xid="div1">
     <div class="media-left media-middle" xid="mediaLeft2">
      <span class="x-flex" xid="span2" style="width:70px;"><![CDATA[基地地址]]></span></div> 
     <div class="media-body" xid="mediaBody2">
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row6">
   <div class="x-col" xid="col12">
    <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择..." class="form-control" xid="s1"></select></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row7">
   <div class="x-col" xid="col15">
    <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择..." class="form-control" xid="s2"></select></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row8">
   <div class="x-col" xid="col16">
    <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择..." class="form-control" xid="s3"></select></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row9">
   <div class="x-col" xid="col17">
    <input component="$UI/system/components/justep/input/input" class="form-control" xid="areaAddr"></input></div> </div></div> </div> 
    <div class="panel-heading" xid="div14">
     <div class="media-left media-middle" xid="mediaLeft3">
      <span class="x-flex" xid="span4" style="width:70px;"><![CDATA[基地规模]]></span></div> 
     <div class="media-body" xid="mediaBody3">
      <input component="$UI/system/components/justep/input/input" class="form-control" xid="scale"></input></div> </div> 
    <div class="panel-heading" xid="div13">
     <div class="media-left media-middle" xid="mediaLeft4">
      <span class="x-flex" xid="span5" style="width:70px;"><![CDATA[地图位置]]></span></div> 
     <div class="media-body" xid="mediaBody4">
      
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row1">
   <div class="x-col" xid="col1"><input component="$UI/system/components/justep/input/input" class="form-control" xid="lng"></input><span xid="span3"><![CDATA[-]]></span><input component="$UI/system/components/justep/input/input" class="form-control" xid="lat"></input></div>
   <div class="x-col x-col-center center-block" xid="col2"><a component="$UI/system/components/justep/button/button" class="btn btn-default" label="地图" xid="mapbtn" style="width:50px;" onClick="mapbtnClick">
   <i xid="i1"></i>
   <span xid="span1">地图</span></a></div>
   </div></div> </div> 
    <div class="panel-heading" xid="div7">
     <div class="media-left media-middle" xid="mediaLeft5">
      <span class="x-flex" style="width:70px;" xid="span15">种类图片</span></div> 
     <div class="media-body" xid="mediaBody5">
      <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs" label="上传" xid="buttonUpload" icon="icon-android-add" bind-click="buttonUploadClick">
       <i xid="i5" class="icon-android-add"></i>
       <span xid="span24">上传</span></a> 
      <a component="$UI/system/components/justep/button/button" class="btn x-green btn-xs" label="移除" xid="buttonRemove" style="margin-left:10px;" icon="icon-android-remove" onClick="buttonRemoveClick">
       <i xid="i6" class="icon-android-remove"></i>
       <span xid="span25">移除</span></a> 
      <input type="file" value="" xid="inputImage" size="1" bind-change="inputImageChange" accept="image/*" style="display:none;"></input></div> 
     <div class="media-right" xid="mediaRight1">
      <span xid="spanImg"></span></div> </div> 
    <div class="panel-heading" xid="div6">
     <a component="$UI/system/components/justep/button/button" class="btn x-green btn-only-label btn-block" label="提交" xid="button2" onClick="saveBtnClick">
      <i xid="i2"></i>
      <span xid="span7">提交</span></a> </div> </div> </div></div>
  </div> 
<span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver" onReceive="windowReceiverReceive" style="top:85px;left:37px;"></span>
  <resource xid="resource2"></resource>
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialog" src="$UI/rlscsyapp/protype/map.w" style="top:16px;left:74px;" onReceive="windowDialogReceive"></span></div>