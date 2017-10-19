<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="top:394px;left:212px;height:auto;"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="true" xid="itemData" idColumn="typeId"><column label="typeId" name="typeId" type="Integer" xid="xid1"></column>
  <column label="typeName" name="typeName" type="String" xid="xid2"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="设置产品名称"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">设置产品名称</div>  
          <div class="x-titlebar-right reverse"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="saveBtn" icon="icon-android-checkmark" onClick="saveBtnClick">
   <i xid="i1" class="icon-android-checkmark"></i>
   <span xid="span1"></span></a></div>
        </div> 
      </div>  
    <div class="x-panel-content x-cards" xid="content1">
  <div component="$UI/system/components/justep/list/list" class="x-list" xid="list1" data="itemData">
   <ul class="x-list-template" xid="listTemplateUl1">
    <li xid="li1"><input component="$UI/system/components/justep/input/input" class="form-control" xid="input1" style="width:100%;" bind-ref='ref("typeName")'></input></li></ul> </div></div>
  </div> 
<span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver1" onReceive="windowReceiver1Receive" style="top:128px;left:38px;"></span></div>