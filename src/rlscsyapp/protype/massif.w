<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:460px;left:452px;"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="true" xid="massifData" idColumn="maId" onCustomRefresh="massifDataCustomRefresh"><column label="maId" name="maId" type="Integer" xid="xid1"></column>
  <column name="maImg" type="String" xid="xid2"></column>
  <column label="maName" name="maName" type="String" xid="xid3"></column>
  <column label="ptaId" name="ptaId" type="Integer" xid="xid4"></column>
  <column label="ptqId" name="ptqId" type="Integer" xid="xid5"></column>
  <column label="startTime" name="startTime" type="String" xid="xid6"></column>
  <column label="state" name="state" type="String" xid="xid7"></column>
  <column label="typeName" name="typeName" type="String" xid="xid8"></column>
  <column label="areaName" name="areaName" type="String" xid="xid9"></column>
  <column label="getTime" name="getTime" type="String" xid="xid10"></column>
  <column label="maAcreage" name="maAcreage" type="String" xid="xid11"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="地块信息"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">地块信息</div>  
          <div class="x-titlebar-right reverse"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="addMissif" icon="icon-android-add" onClick="addMissifClick">
   <i xid="i4" class="icon-android-add"></i>
   <span xid="span16"></span></a></div>
        </div> 
      </div>  
    <div class="x-panel-content x-cards" xid="content1">
  <div component="$UI/system/components/justep/list/list" class="x-list" xid="list1" data="massifData">
   <ul class="x-list-template" xid="listTemplateUl1">
    <li xid="li1"><div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu" xid="panel3">
   <div xid="div3">
    
    
    
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row1">
   <div class="x-col" xid="col1">
    <span xid="span1">地块名称：</span>
    <span xid="maName" bind-text='ref("maName")'></span></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row2">
   <div class="x-col" xid="col4">
    <span xid="span3">地块面积：</span>
    <span xid="maAcreage" bind-text='ref("maAcreage")'></span></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row3">
   <div class="x-col" xid="col5">
    <span xid="span5">种植品种：</span>
    <span xid="typeName" bind-text='ref("typeName")'></span></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row4">
   <div class="x-col" xid="col6">
    <span xid="span7">种植时间：</span>
    <span xid="startTime" bind-text='ref("startTime")'></span></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row5">
   <div class="x-col" xid="col7">
    <span xid="span9">收获时间：</span>
    <span xid="getTime" bind-text='ref("getTime")'></span></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row6">
   <div class="x-col" xid="col8">
    <span xid="span11">上期种植品种：</span>
    <span xid="state" bind-text='ref("state")'></span></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row" xid="row7">
   <div class="x-col" xid="col9">
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row8">
     <div class="x-col" xid="col10">
      <a component="$UI/system/components/justep/button/button" class="btn x-green btn-sm" label="修改" xid="updateMassif" style="width:100%;" onClick="updateMassifClick">
       <i xid="i2"></i>
       <span xid="span14">修改</span></a> </div> 
     <div class="x-col" xid="col11">
      <a component="$UI/system/components/justep/button/button" class="btn x-green btn-sm" label="删除" xid="deleteMassif" style="width:100%;" onClick="deleteMassifClick">
       <i xid="i3"></i>
       <span xid="span15">删除</span></a> </div> </div> </div> </div></div> </div></li></ul> 
  </div></div>
  </div> 
<span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver" style="top:465px;left:220px;" onReceive="windowReceiverReceive"></span>
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialog" style="top:461px;left:283px;" onClose="windowDialogClose" src="$UI/rlscsyapp/protype/editMassif.w"></span>
  <span component="$UI/system/components/justep/messageDialog/messageDialog" xid="messageDialog" style="top:467px;left:404px;" type="OKCancel" onOK="messageDialogOK" title="提示" message="是否删除？"></span></div>