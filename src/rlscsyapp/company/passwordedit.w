<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window"
  design="device:m;">
  <title>肉类蔬菜流通追溯管理平台</title>  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:192px;top:26px;"
    onParamsReceive="modelParamsReceive"> 
    <!--     <div component="$UI/system/components/justep/data/baasData" autoLoad="true" -->  
    <!--       xid="baasData2" queryAction="queryUsers" tableName="taobao_user" url="/justep/taobao"> -->  
    <!--       <filter name="userfilter" xid="filter2"><![CDATA[fID=:user]]></filter></div>  --> 
  </div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full x-card" xid="panel2">
   <div class="x-panel-top" xid="top1">
    <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar" xid="titleBar1">
     <div class="x-titlebar-left" xid="div2">
      <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-left" label="修改密码" xid="button6" icon="icon-arrow-left-c" onClick="{operation:'window.close'}">
       <i xid="i2" class="icon-arrow-left-c"></i>
       <span xid="span6">修改密码</span></a> </div> 
     <div class="x-titlebar-title" xid="div3"></div>
     <div class="x-titlebar-right reverse" xid="div4"></div></div> </div> 
   <div class="x-panel-content x-cards" xid="content1">
    <div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu" xid="panel3">
     <div xid="div1">
      <div class="panel-heading" xid="div5">
       <div class="media-left media-middle" xid="mediaLeft1">
        <span class="x-flex" style="width:60px;" xid="span1">原密码</span></div> 
       <div class="media-body" style="width:119px;" xid="mediaBody1">
        <input component="$UI/system/components/justep/input/password" class="form-control input-sm tb-noborder text-muted" xid="oldpassword" style="width:220px;height:32px;"></input></div> </div> 
      <div class="panel-heading" xid="div7">
       <div class="media-left media-middle" xid="mediaLeft2">
        <span class="x-flex" style="width:60px;" xid="span2">新密码</span></div> 
       <div class="media-body" style="width:119px;" xid="mediaBody2">
        <input component="$UI/system/components/justep/input/password" class="form-control x-edit" xid="newpassword" style="width:220px;height:32px;" dataType="String"></input></div> </div> 
      <div class="panel-heading" xid="div8">
       <div class="media-left media-middle" xid="mediaLeft3">
        <span class="x-flex" style="width:60px;" xid="span3">确认密码</span></div> 
       <div class="media-body" style="width:119px;" xid="mediaBody3">
        <input component="$UI/system/components/justep/input/password" class="form-control x-edit" xid="repassword" style="width:220px;height:32px;" dataType="String"></input></div> 
       <div class="panel-heading" xid="div6">
        <div class="media-body" style="width:119px;" align="justify" xid="mediaBody4"></div>
        <a component="$UI/system/components/justep/button/button" class="btn x-green btn-only-label btn-block" label="提交" xid="button2" onClick="verifyButton">
         <i xid="i23"></i>
         <span xid="span4">提交</span></a> </div> </div> </div> </div> </div> 
   <span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiverpwd"></span></div>
  </div>