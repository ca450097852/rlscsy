<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:344px;left:462px;"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="proTypeBatch" idColumn="ptbId" onCustomRefresh="proTypeBatchCustomRefresh"><column label="ptbId" name="ptbId" type="Integer" xid="xid1"></column>
  <column label="ptqId" name="ptqId" type="Integer" xid="xid2"></column>
  <column label="recId" name="recId" type="Integer" xid="xid3"></column>
  <column label="entId" name="entId" type="Integer" xid="xid4"></column>
  <column label="batchName" name="batchName" type="String" xid="xid5"></column>
  <column label="batchState" name="batchState" type="String" xid="xid6"></column>
  <column label="batchTime" name="batchTime" type="String" xid="xid7"></column>
  <column label="codeImg" name="codeImg" type="String" xid="xid8"></column>
  <column label="dimenno" name="dimenno" type="String" xid="xid9"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
          <div class="x-panel-top" xid="top1">
  <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar" xid="titleBar1">
   <div class="x-titlebar-left" xid="left1"><a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="返回" xid="backBtn" icon="icon-arrow-left-c" onClick="{operation:'window.close'}">
   <i xid="i2" class="icon-arrow-left-c"></i>
   <span xid="span5">返回</span></a></div>
   <div class="x-titlebar-title" xid="title1"><span xid="span4"><![CDATA[批次信息]]></span></div>
   <div class="x-titlebar-right reverse" xid="right1"><a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="addBatch" icon="icon-android-add" onClick="addBatchClick">
   <i xid="i1" class="icon-android-add"></i>
   <span xid="span9"></span></a></div>
  </div></div> 
    <div class="x-panel-content" xid="content1"><div component="$UI/system/components/justep/list/list" class="x-list" xid="list1" data="proTypeBatch" style="background-color:#C0C0C0;">
   <ul class="x-list-template" xid="listTemplateUl1">
    <li xid="li1" class="bg-success" style="margin-bottom:3px;"><div component="$UI/system/components/justep/row/row" class="x-row list-group-item" xid="row1">
   <div class="x-col" xid="col1"><span xid="span1"><![CDATA[批次名称：]]></span>
  <span xid="span2" bind-text='ref("batchName")'></span></div>
   </div>
  <div component="$UI/system/components/justep/row/row" class="x-row list-group-item" xid="row2">
   <div class="x-col" xid="col4"><span xid="span3"><![CDATA[生产时间：]]></span>
  <span xid="span6" bind-text='ref("batchTime")'></span></div>
   </div>
  <div component="$UI/system/components/justep/row/row" class="x-row list-group-item" xid="row3">
   <div class="x-col" xid="col7"><span xid="span7"><![CDATA[批次编码：]]></span><span xid="span8" bind-text='ref("dimenno")'></span></div>
   <div class="x-col x-col-25" xid="col6"><a component="$UI/system/components/justep/button/button" class="btn x-green btn-sm" label="批次档案" xid="button2" onClick="button2Click">
   <i xid="i3"></i>
   <span xid="span11">批次档案</span></a></div></div>
  </li></ul> </div></div>
  </div> 
<span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialog" src="$UI/rlscsyapp/protype/addProTypeBatch.w" onClose="windowDialogClose"></span>
  <span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver" onReceive="windowReceiverReceive"></span>
  </div>