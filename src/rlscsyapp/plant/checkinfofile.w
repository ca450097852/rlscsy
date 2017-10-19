<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window"
  design="device:m;">  
  <title>农产品质量安全追溯平台</title>
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:192px;top:26px;"
    onParamsReceive="modelParamsReceive">  
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="masterData" idColumn="appId" onCustomRefresh="masterDataCustomRefresh"><column label="appId" name="appId" type="Integer" xid="xid1"></column>
  <column label="fkId" name="fkId" type="Integer" xid="xid2"></column>
  <column label="appName" name="appName" type="String" xid="xid3"></column>
  <column label="appType" name="appType" type="String" xid="xid4"></column>
  <column label="appUrl" name="appUrl" type="String" xid="xid5"></column>
  <column label="uploadTime" name="uploadTime" type="String" xid="xid6"></column>
  <column label="seq" name="seq" type="String" xid="xid7"></column>
  <column label="remark" name="remark" type="String" xid="xid8"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full x-card"
    xid="panel1"> 
    <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
        xid="titleBar1" title="检验报告"> 
        <div class="x-titlebar-left" xid="div2"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-left btn-only-icon"
            xid="button6" icon="icon-arrow-left-c" onClick="backButton"> 
            <i xid="i2" class="icon-arrow-left-c"/>  
            <span xid="span6">关闭</span> 
          </a> 
        </div>  
        <div class="x-titlebar-title" xid="div3">检验报告</div>  
        <div class="x-titlebar-right reverse" xid="div4"><a component="$UI/system/components/justep/button/button" class="btn btn-link" label="上传" xid="button1" icon="icon-android-image" bind-click="button1Click">
   <i xid="i4" class="icon-android-image"></i>
   <span xid="span2">上传</span></a></div> 
      </div> 
    </div>  
    <div class="x-panel-content x-cards" xid="content1"> 
      <div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu"
        xid="panel3"> 
        <div component="$UI/system/components/justep/list/list" class="x-list x-cards" xid="list1" data="masterData" limit="-1" disablePullToRefresh="false" disableInfiniteLoad="false" autoLoad="false">
   <ul class="x-list-template  list-group" xid="listTemplateUl1">
    <li xid="li1" style="width:100%;" class="list-group-item">
     <div class="caption" xid="div1">
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row1">
       <div class="x-col x-col-67" xid="col1">
        <img src="" alt="" xid="image1" bind-attr-src=' $model.getImageURL( val("appUrl"))' height="60" style="width:80;"></img></div> 
  <div class="x-col x-col-33" xid="col8">
   <a component="$UI/system/components/justep/button/button" class="btn x-green btn-xs btn-block" label="查看大图" xid="button2" icon="icon-android-search" onClick="showClick">
    <i xid="i3" class="icon-android-search"></i>
    <span xid="span17">查看大图</span></a> 
  <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="删除" xid="button3" icon="icon-close" onClick="delClick" style="margin-top:20px;">
   <i xid="i1" class="icon-close"></i>
   <span xid="span1">删除</span></a></div>
  </div> 
      
      
      
      
      
      </div> </li> </ul> </div>
  <input type="file" value="" xid="inputImage" size="1" bind-change="inputImageChange" accept="image/*" style="display:none;"></input></div> 
    </div> 
  </div>  
  <span component="$UI/system/components/justep/windowReceiver/windowReceiver"
    xid="windowReceiverEdit"/> 
<span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialogImg" onReceive="windowDialogImgReceive"></span></div>
