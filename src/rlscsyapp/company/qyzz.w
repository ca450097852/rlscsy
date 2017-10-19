<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">
<title>农产品质量安全追溯平台</title>  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:242px;top:61px;" onLoad="modelLoad"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="zizhiData" idColumn="id" onCustomRefresh="zizhiDataCustomRefresh" limit="10">
  <column label="id" name="id" type="Integer" xid="xid1"></column>
  <column label="entId" name="entId" type="Integer" xid="xid2"></column>
  <column label="appType" name="appType" type="Integer" xid="xid3"></column>
  <column label="zizhiName" name="zizhiName" type="String" xid="xid4"></column>
  <column label="grantUnit" name="grantUnit" type="String" xid="xid5"></column>
  <column label="awardUnit" name="awardUnit" type="String" xid="xid7"></column>
  <column label="awardTime" name="awardTime" type="String" xid="xid8"></column>
  <column label="remark" name="remark" type="String" xid="xid6"></column>
  <column label="state" name="state" type="String" xid="xid9"></column></div>
  
  </div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="资质证书"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-chevron-left"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-chevron-left"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">资质证书</div>  
          <div class="x-titlebar-right reverse"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="button3" icon="icon-android-add" onClick="addClick">
   <i xid="i3" class="icon-android-add"></i>
   <span xid="span18"></span></a></div>
        </div> 
      </div>  
    <div class="x-panel-content  x-scroll-view" xid="content1" _xid="C7192A377500000181111E4A1DA2135B" style="bottom: 0px;"><div class="x-scroll" component="$UI/system/components/justep/scrollView/scrollView" xid="scrollView1" onPullUp="scrollView1PullUp">
   <div class="x-content-center x-pull-down container" xid="div1">
    <i class="x-pull-down-img glyphicon x-icon-pull-down" xid="i4"></i>
    <span class="x-pull-down-label" xid="span19">下拉刷新...</span></div> 
   <div class="x-scroll-content" xid="div3"><div component="$UI/system/components/justep/list/list" class="x-list x-cards" xid="list1" data="zizhiData" limit="-1" disablePullToRefresh="false" disableInfiniteLoad="false" autoLoad="true">
   <ul class="x-list-template  list-group" xid="listTemplateUl1">
    <li xid="li1" style="width:100%;" class="list-group-item">
     <div class="caption" xid="div2">
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row2">
       <div class="x-col" xid="col5">
        <span xid="span2"><![CDATA[资质类型：]]></span>
        <span xid="span10" bind-text=' $model.typeNameStr( val("id"))'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row1">
       <div class="x-col" xid="col1">
        <span xid="span4"><![CDATA[证书名称：]]></span>
        <span xid="span3" bind-text='val("zizhiName")'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row3">
       <div class="x-col" xid="col2">
        <span xid="span6"><![CDATA[颁发单位：]]></span>
        <span xid="span5" bind-text='val("awardUnit")'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row4">
       <div class="x-col" xid="col3">
        <span xid="span8"><![CDATA[颁发时间：]]></span>
        <span xid="span7" bind-text='val("awardTime")'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row5">
       <div class="x-col" xid="col4">
        <span xid="span11"><![CDATA[状态：]]></span>
        <span xid="span9" bind-text=' $model.getState(  val("state"))'></span></div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row7">
       <div class="x-col" xid="col7">
        <span xid="span12"><![CDATA[附件：]]></span>
        <span xid="span1" bind-click="showClick">
        	<img src="" alt="" class="pull-right img-rounded img-responsive media-object" xid="image18" align="bottom" bind-attr-src='$model.appendixUrl( val("id"))' style="width:50px;" height="35px"></img>
        </span>
        </div> </div> 
      <div component="$UI/system/components/justep/row/row" class="x-row" xid="row6">
       <div class="x-col" xid="col6">
        <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="修改" xid="button1" icon="icon-edit" onClick="updateClick">
         <i xid="i1" class="icon-edit"></i>
         <span xid="span16">修改</span></a> </div> 
       <div class="x-col" xid="col8">
        <a component="$UI/system/components/justep/button/button" class="btn x-green btn-xs btn-block" label="删除" xid="button2" icon="icon-close" onClick="delClick">
         <i xid="i2" class="icon-close"></i>
         <span xid="span17">删除</span></a> </div> </div> </div> </li> </ul> </div></div>
   <div class="x-content-center x-pull-up" xid="div4">
    <span class="x-pull-up-label" xid="span20">加载更多...</span></div> </div></div>
  </div> 
<span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialogEdit" onReceive="windowDialogEditReceive" onClose="windowDialogEditClose"></span>
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialogImg" onReceive="windowDialogImgReceive"></span></div>