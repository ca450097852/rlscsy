<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="top:16px;left:641px;height:auto;" onParamsReceive="modelParamsReceive"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="nodeinfoData" idColumn="niId" onCustomRefresh="nodeinfoDataCustomRefresh" limit="10">
  
  <column label="saleId" name="niId" type="Integer" xid="xid1"></column>
  <column label="recId" name="recId" type="Integer" xid="xid2"></column>
  <column label="niName" name="niName" type="String" xid="xid3"></column>
  <column label="nodeImg" name="nodeImg" type="String" xid="xid4"></column>
  <column label="userId" name="userId" type="String" xid="xid5"></column>
  <column label="crttime" name="crttime" type="String" xid="xid17"></column>
  <column label="remark" name="remark" type="String" xid="xid10"></column></div>
  
  </div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="生产节点"
          class="x-titlebar" xid="marketTitle">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">生产节点</div>  
          <div class="x-titlebar-right reverse"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="addPfBaseBtn" icon="linear linear-bus" onClick="addPfBaseBtnClick">
   <i xid="i1" class="linear linear-bus"></i>
   <span xid="span1"></span></a></div>
        </div> 
      </div>  
    <div class="x-panel-content  x-scroll-view" xid="content1" _xid="C787DDC3E180000112ADB9C0A81D8900" style="bottom: 0px;"><div class="x-scroll" component="$UI/system/components/justep/scrollView/scrollView" xid="scrollView1" onPullUp="scrollView1PullUp" style="width:100%;">
   <div class="x-content-center x-pull-down container" xid="div1">
    <i class="x-pull-down-img glyphicon x-icon-pull-down" xid="i4"></i>
    <span class="x-pull-down-label" xid="span19">下拉刷新...</span></div> 
   <div class="x-scroll-content" xid="div3">
    <div component="$UI/system/components/justep/list/list" class="x-list x-cards" xid="list1" data="nodeinfoData" limit="-1" disablePullToRefresh="false" disableInfiniteLoad="false" autoLoad="false">
     <ul class="x-list-template  list-group" xid="listTemplateUl1">
      <li xid="li1" style="width:100%;" class="list-group-item">
       <div class="caption" xid="div2">
        <div class="media" xid="media2">
   <div class="media-left" xid="mediaLeft1">
    <a href="#" xid="a1">
     <img class="media-object" src="" alt="" xid="image1" style="width:75px;" height="60" bind-attr-src='$model.getImageUrl( val("nodeImg"))'></img></a> </div> 
   <div class="media-body" xid="mediaBody2">
    <div xid="div9">
     <span xid="span8" bind-text=' val("niName")'></span></div> 
    <div class="text-muted" xid="div5">
     <span xid="span2" bind-text=' val("remark")'></span></div> 
    <div class="text-muted" xid="div8">
     <span xid="span2" bind-text=' val("crttime")'></span></div> 
    </div> </div><div component="$UI/system/components/justep/row/row" class="x-row" xid="row6">
         <div class="x-col" xid="col6">
          <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="修改" xid="button1" icon="icon-edit" onClick="updateClick">
           <i xid="i2" class="icon-edit"></i>
           <span xid="span16">修改</span></a> </div> 
         <div class="x-col" xid="col3">
          <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs btn-block" label="删除" xid="button4" icon="icon-close" onClick="deleteClick">
           <i xid="i5" class="icon-close"></i>
           <span xid="span7">删除</span></a> </div> 
         
         </div></div> </li> </ul> </div> </div> 
   <div class="x-content-center x-pull-up" xid="div4">
    <span class="x-pull-up-label" xid="span20">加载更多...</span></div> </div></div>
  </div> 
<span component="$UI/system/components/justep/messageDialog/messageDialog" xid="messageDialog1" type="YesNo" onYes="messageDialog1Yes" message="是否删除该信息" style="top:13px;left:30px;"></span>
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="editwindowDialog" src="$UI/rlscsyapp/plant/editNodeinfo.w" onReceived="editwindowDialogReceived" style="top:13px;left:71px;"></span></div>