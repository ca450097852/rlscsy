<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window"
  design="device:m;">  
  <title>农产品质量安全追溯平台</title>
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:192px;top:26px;"
    onParamsReceive="modelParamsReceive">  
  </div>  
  <div component="$UI/system/components/justep/popOver/popOver" class="x-popOver"
    xid="popOver"> 
    <div class="x-popOver-overlay" xid="div9"/> 
  </div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full x-card"
    xid="panel1"> 
    <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
        xid="titleBar1" title="编辑生产节点"> 
        <div class="x-titlebar-left" xid="div0"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-left"
            xid="button6" icon="icon-arrow-left-c" onClick="{operation:'window.close'}"> 
            <i xid="i2" class="icon-arrow-left-c"/>  
          </a> 
        </div>  
        <div class="x-titlebar-title" xid="div3">编辑生产节点</div>  
        <div class="x-titlebar-right reverse" xid="div4"/> 
      </div> 
    </div>  
    <div class="x-panel-content x-cards" xid="content1"> 
      <div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu"
        xid="panel3"> 
        <div xid="div2"> 
          <div class="panel-heading" xid="div8"> 
            <div class="media-left media-middle" xid="mediaLeft1"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[节点名称]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody1"> 
              <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit"
                xid="niName"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div1">
   <div class="media-left media-middle" xid="mediaLeft2">
    <span class="x-flex" style="width:70px;" xid="span1"><![CDATA[节点日期]]></span></div> 
   <div class="media-body" xid="mediaBody2">
    <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit" xid="crttime" dataType="Date"></input></div> </div>
  <div class="panel-heading" xid="div7">
   <div class="media-left media-middle" xid="mediaLeft4">
    <span class="x-flex" style="width:70px;" xid="span5"><![CDATA[节点图片]]></span></div> 
   <div class="media-body" xid="mediaBody4">
    <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs" label="上传" xid="buttonUpload" icon="icon-android-add" bind-click="buttonUploadClick">
   <i xid="i5" class="icon-android-add"></i>
   <span xid="span24">上传</span></a>
  <a component="$UI/system/components/justep/button/button" class="btn x-green btn-xs" label="移除" xid="buttonRemove" style="margin-left:10px;" icon="icon-android-remove" onClick="buttonRemoveClick">
   <i xid="i6" class="icon-android-remove"></i>
   <span xid="span25">移除</span></a>
  <input type="file" value="" xid="inputImage" size="1" bind-change="inputImageChange" accept="image/*" style="display:none;"></input></div> 
   <div class="media-right" xid="mediaRight1">
    <span xid="spanImg"></span></div> </div><div class="panel-heading" xid="div6">
   <a component="$UI/system/components/justep/button/button" class="btn x-green btn-only-label btn-block" label="提交" xid="button2" onClick="verifyButton">
    <i xid="i1"></i>
    <span xid="span4">提交</span></a> </div>
  </div> 
      </div> 
    </div> 
  </div>  
  <span component="$UI/system/components/justep/windowReceiver/windowReceiver"
    xid="windowReceiver2" style="top:10px;left:102px;"/> 
</div>
