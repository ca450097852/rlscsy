<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:307px;left:289px;"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="true" xid="proTypeAreaData" idColumn="ptaId" onCustomRefresh="proTypeAreaDataCustomRefresh"><column label="ptaId" name="ptaId" type="Integer" xid="xid1"></column>
  <column label="ptqId" name="ptqId" type="Integer" xid="xid2"></column>
  <column label="scale" name="scale" type="String" xid="xid3"></column>
  <column label="startTime" name="startTime" type="String" xid="xid4"></column>
  <column label="town" name="town" type="String" xid="xid5"></column>
  <column label="areaAcreage" name="areaAcreage" type="String" xid="xid6"></column>
  <column label="areaAddr" name="areaAddr" type="String" xid="xid7"></column>
  <column label="areaImg" name="areaImg" type="String" xid="xid8"></column>
  <column label="areaName" name="areaName" type="String" xid="xid9"></column>
  <column label="areaValue" name="areaValue" type="String" xid="xid10"></column>
  <column label="chandi" name="chandi" type="String" xid="xid11"></column>
  <column label="city" name="city" type="String" xid="xid12"></column>
  <column label="entId" name="entId" type="Integer" xid="xid13"></column>
  <column label="getTime" name="getTime" type="String" xid="xid14"></column>
  <column label="lat" name="lat" type="String" xid="xid15"></column>
  <column label="lng" name="lng" type="String" xid="xid16"></column>
  <column label="province" name="province" type="String" xid="xid17"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="基地信息"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">基地信息</div>  
          <div class="x-titlebar-right reverse"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="addArea" icon="icon-android-add" onClick="addAreaClick">
   <i xid="i5" class="icon-android-add"></i>
   <span xid="span11"></span></a></div>
        </div> 
      </div>  
    <div class="x-panel-content x-cards" xid="content1"><div component="$UI/system/components/justep/list/list" class="x-list" xid="list1" data="proTypeAreaData">
   <ul class="x-list-template" xid="listTemplateUl1">
    <li xid="li1"><div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu" xid="panel3">
   <div xid="div3">
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row1">
   <div class="x-col" xid="col1">
    <span xid="span1">基地名称：</span>
    <span xid="areaName" bind-text='ref("areaName")'></span></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row2">
   <div class="x-col" xid="col4">
    <span xid="span3">基地地址：</span>
    <span xid="chandi" bind-html='val("chandi") + val("areaAddr")'></span></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row3">
   <div class="x-col" xid="col5">
    <span xid="span5">基地规模：</span>
    <span xid="scale" bind-text='ref("scale")'></span></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row4">
   <div class="x-col" xid="col6">
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row8">
     <div class="x-col" xid="col12">
      <a component="$UI/system/components/justep/button/button" class="btn x-green btn-sm center-block" label="地块信息" xid="massifBtn" onClick="massifBtnClick">
       <i xid="i2"></i>
       <span xid="span8">地块信息</span></a> </div> 
     <div class="x-col" xid="col13">
      <a component="$UI/system/components/justep/button/button" class="btn x-green btn-sm center-block" label="修改" xid="updateArea" onClick="updateAreaClick">
       <i xid="i3"></i>
       <span xid="span9">修改</span></a> </div> 
     <div class="x-col" xid="col14">
      <a component="$UI/system/components/justep/button/button" class="btn x-green btn-sm center-block" label="删除" xid="deleteArea" onClick="deleteAreaClick">
       <i xid="i4"></i>
       <span xid="span10">删除</span></a> </div> </div> </div> </div></div> </div></li></ul> </div></div>
  </div> 
<span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialog" style="top:474px;left:172px;" onClose="windowDialogClose"></span>
  <span component="$UI/system/components/justep/messageDialog/messageDialog" xid="messageDialog" style="top:467px;left:317px;" type="OKCancel" onOK="messageDialogOK" title="提示" message="是否删除？"></span></div>