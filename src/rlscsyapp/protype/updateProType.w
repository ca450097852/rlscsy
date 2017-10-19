<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:433px;left:253px;"> 
  </div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      
      <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
        xid="titleBar1"> 
        <div class="x-titlebar-left" xid="div2"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-left"
            label="修改种类信息" xid="button6" icon="icon-arrow-left-c" onClick="{operation:'window.close'}"> 
            <i xid="i2" class="icon-arrow-left-c"/>  
            <span xid="span6">修改种类信息</span> 
          </a> 
        </div>  
        <div class="x-titlebar-title" xid="div3"/>  
        <div class="x-titlebar-right reverse" xid="div4"/> 
      </div> 
    </div>  
    
  <div class="x-panel-content x-cards" xid="content1">
    
  <div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu" xid="panel3">
   <div xid="div12">
    
    
    
    
    
    
    
    
    <div class="panel-heading" xid="div8">
   <div class="media-left media-middle" xid="mediaLeft1">
    <span class="x-flex" xid="span2" style="width:70px;">是否认证</span></div> 
   <div class="media-body" xid="mediaBody1">
    <select component="$UI/system/components/justep/select/select" class="form-control x-edit" xid="certificate" optionsAutoLoad="false">
     <option value="5" xid="default6">无</option>
     <option value="1" xid="default7">有机</option>
     <option value="2" xid="default8">绿色</option>
     <option value="3" xid="default9">无公害产品</option>
     <option value="4" xid="default10">地理标志认证</option></select> </div> </div>
  <div class="panel-heading" xid="div1">
   <div class="media-left media-middle" xid="mediaLeft2">
    <span class="x-flex" xid="span3" style="width:70px;">年商品量</span></div> 
   <div class="media-body" xid="mediaBody2">
    <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="quantity" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div14">
   <div class="media-left media-middle" xid="mediaLeft3">
    <span class="x-flex" xid="span4" style="width:70px;">上市期</span></div> 
   <div class="media-body" xid="mediaBody3">
    <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="listed" dataType="String"></input></div> </div>
  <div class="panel-heading" xid="div13">
   <div class="media-left media-middle" xid="mediaLeft4">
    <span class="x-flex" xid="span5" style="width:70px;">主要销售地</span></div> 
   <div class="media-body" xid="mediaBody4">
    <select component="$UI/system/components/justep/select/select" class="form-control x-edit" xid="salearea">
     <option value="内销" xid="default16">内销</option>
     <option value="出口" xid="default17">出口</option></select> </div> </div>
  <div class="panel-heading" xid="div7">
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
   <a component="$UI/system/components/justep/button/button" class="btn x-green btn-only-label btn-block" label="提交" xid="button2" onClick="saveBtnClick">
    <i xid="i1"></i>
    <span xid="span7">提交</span></a> </div></div> </div></div></div> 
<span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver1" style="top:411px;left:163px;" onReceive="windowReceiver1Receive"></span>
  <span component="$UI/system/components/justep/messageDialog/messageDialog" xid="messageDialog" style="top:427px;left:527px;" title="提示"></span></div>