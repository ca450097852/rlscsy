<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
	<title>农产品质量安全追溯平台</title>
  <div component="$UI/system/components/justep/model/model" xid="model" style="top:48px;left:208px;height:auto;" onParamsReceive="modelParamsReceive"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="true" xid="recordData" idColumn="recId" onCustomRefresh="recordDataCustomRefresh">
   <column label="recId" name="recId" type="Integer" xid="xid1"></column>
  <column label="typeName" name="typeName" type="Integer" xid="xid2"></column>
  <column label="objId" name="objId" type="String" xid="xid3"></column>
  <column label="pagename" name="pagename" type="String" xid="xid4"></column></div>
  </div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="批次档案"
          class="x-titlebar">
          <div class="x-titlebar-left" style="padding-top:10px;"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-chevron-left"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-chevron-left"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">批次档案</div>  
          <div class="x-titlebar-right reverse"> 
          </div>
        </div> 
      </div>  
    <div class="x-panel-content" xid="content1"><div component="$UI/system/components/justep/panel/panel" class="panel panel-default x-card" xid="panel2">
   <h4 xid="h42" class="list-group-item text-black"><![CDATA[档案信息]]></h4>
  <div component="$UI/system/components/justep/list/list" class="x-list" xid="list2" data="recordData">
   <ul class="x-list-template list-group" xid="listTemplateUl2">
    <li xid="li2" class="list-group-item" bind-click="li2Click" bind-value=' val("recId")'>
     <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon pull-right" label="button" xid="button1" icon="icon-ios7-arrow-right">
   <i xid="i1" class="icon-ios7-arrow-right text-muted"></i>
   <span xid="span1"></span></a>
  <span xid="span2" bind-text='ref("typeName")'><![CDATA[]]></span></li> 
    </ul>
     </div></div></div>
  </div> 
</div>