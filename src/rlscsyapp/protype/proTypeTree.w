<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;"
  xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="top:154px;left:161px;height:auto;"> 
    <div component="$UI/system/components/justep/data/data" autoLoad="false"
      xid="proTypeData" idColumn="typeId" isTree="true" onCustomRefresh="proTypeDataCustomRefresh">
      <column label="typeId" name="typeId" type="Integer" xid="xid6"></column>
  <column label="typeNo" name="typeNo" type="String" xid="xid7"></column>
  <column label="typeName" name="typeName" type="String" xid="xid8"></column>
  <column label="upcateId" name="upcateId" type="Integer" xid="xid9"></column>
  <column label="entId" name="entId" type="Integer" xid="xid10"></column>
  <column label="state" name="state" type="Integer" xid="xid11"></column>
  <column label="remark" name="remark" type="String" xid="xid12"></column>
  <column label="typeClass" name="typeClass" type="String" xid="xid13"></column>
  <treeOption delayLoad="false" parentRelation="upcateId" xid="default1"></treeOption>
  <column label="chct" name="chct" type="Integer" xid="xid17"></column></div>
  </div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full"
    xid="panel1"> 
    <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" title="种类设置"
        class="x-titlebar"> 
        <div class="x-titlebar-left"> 
          <a component="$UI/system/components/justep/button/button" label=""
            class="btn btn-link btn-only-icon" icon="icon-arrow-left-c" onClick="{operation:'window.close'}"
            xid="backBtn"> 
            <i class="icon-arrow-left-c"/>  
            <span/> 
          </a> 
        </div>  
        <div class="x-titlebar-title">种类设置</div>  
        <div class="x-titlebar-right reverse"><a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="saveBtn" icon="icon-android-checkmark" onClick="saveBtnClick">
   <i xid="i2" class="icon-android-checkmark"></i>
   <span xid="span3"></span></a></div> 
      </div> 
    </div>  
    <div class="x-panel-content" xid="content1">
      <div component="$UI/system/components/justep/tree/tree" class="x-tree x-inner-scroll"
        xid="tree1" data="proTypeData" labelColumn="typeName" rootLabel="产品种类" autoLoad="false"> 
        <div class="x-tree-head" xid="div4"> 
          <ul component="$UI/system/components/bootstrap/breadcrumb/breadcrumb"
            class="breadcrumb" xid="ul1"/>
        </div>  
        <div class="x-tree-content x-scroll-view" xid="div5"> 
          <div component="$UI/system/components/justep/scrollView/scrollView"
            supportPullDown="true" supportPullUp="true" hScroll="false" vScroll="true"
            hScrollbar="false" vScrollbar="true" bounce="true" class="x-scroll" xid="scrollView1"> 
            <ul class="x-tree-template x-scroll-content" xid="treeTemplateUl1"> 
              <li xid="li1" class="x-tree-link"> 
                <div component="$UI/system/components/justep/row/row" class="x-row x-row-center"
                  xid="row1"> 
                  <div class="x-col" xid="col1"> 
                    <div component="$UI/system/components/justep/output/output"
                      class="x-output" xid="output1" bind-ref="ref(&quot;typeName&quot;)"/>
                  </div>  
                  <div class="x-col x-col-fixed" xid="col4" style="width:auto;"> 
                    <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-sm btn-only-icon" label="button" xid="button2" icon="icon-chevron-right" bind-visible=' val("chct")!=0'>
   <i xid="i3" class="icon-chevron-right"></i>
   <span xid="span5"></span></a>
  <span component="$UI/system/components/justep/button/checkbox" class="x-checkbox" xid="checkbox" bind-visible='val("chct")==0' onChange="checkboxChange" bind-checked='justep.checkExit(val("typeId"))'></span></div> 
                </div> 
              </li> 
            </ul> 
          </div> 
        </div> 
      </div>
    </div> 
  </div> 
<span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="windowReceiver" onReceive="windowReceiverReceive" style="top:324px;left:396px;"></span>
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialog" style="top:308px;left:269px;" src="$UI/rlscsyapp/protype/setProName.w"></span></div>