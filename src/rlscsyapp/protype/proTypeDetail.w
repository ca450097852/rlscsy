<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="top:347px;left:160px;height:80px;"> 
  
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="proTypeData" idColumn="ptqId" onCustomRefresh="proTypeCustomRefresh" limit="10"><column label="ptqId" name="ptqId" type="String" xid="xid3"></column>
  <column label="entId" name="entId" type="Integer" xid="xid4"></column>
  <column label="typeId" name="typeId" type="Integer" xid="xid5"></column>
  <column label="dimenno" name="dimenno" type="String" xid="xid6"></column>
  <column label="url" name="url" type="String" xid="xid7"></column>
  <column label="codeImg" name="codeImg" type="String" xid="xid8"></column>
  <column label="crrtime" name="crrtime" type="String" xid="xid1"></column>
  <column label="certificate" name="certificate" type="Integer" xid="xid2"></column>
  <column label="quantity" name="quantity" type="Integer" xid="xid9"></column>
  <column label="listed" name="listed" type="String" xid="xid10"></column>
  <column label="salearea" name="salearea" type="String" xid="xid11"></column>
  <column label="typeImg" name="typeImg" type="String" xid="xid12"></column>
  <column label="typeName" name="typeName" type="String" xid="xid13"></column></div>
  </div>  
  <div component="$UI/system/components/justep/panel/panel" 
    class="x-panel x-full" xid="panel1"> 
      <!-- <div class="x-panel-top" xid="top1"> 
        <div component="$UI/system/components/justep/titleBar/titleBar" title="种类信息"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-chevron-left"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-chevron-left"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">种类信息</div>  
          <div class="x-titlebar-right reverse"> 
          </div>
        </div> 
      </div>   -->
      
      <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
        xid="titleBar1" title="种类信息"> 
        <div class="x-titlebar-left" xid="div2"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-left btn-only-icon"
            xid="button6" icon="icon-arrow-left-c" onClick="{operation:'window.close'}"> 
            <i xid="i2" class="icon-arrow-left-c"/>  
            <span xid="span6"></span> 
          </a> 
        </div>  
        <div class="x-titlebar-title" xid="div3">种类信息</div>  
        <div class="x-titlebar-right reverse" xid="div4"><a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon" label="button" xid="addProType" icon="icon-android-add" onClick="addProTypeClick">
   <i xid="i1" class="icon-android-add"></i>
   <span xid="span4"></span></a></div> 
      </div> 
    </div> 
      
      
    <div class="x-panel-content x-cards" xid="content1" _xid="C71913D782D000012A6911F17EDC11A0" style="bottom: 0px;"><div component="$UI/system/components/justep/list/list" class="x-list" xid="list1" data="proTypeData">
   <ul class="x-list-template" xid="listTemplateUl1">
    <li xid="li1"><div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu" xid="panel3">
   <div xid="div3">
    
    
    
    
    
    
    
    
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row9">
   <div class="x-col x-col-fixed x-col-top" xid="col25">
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row10">
     <div class="x-col" xid="col29">
      <span xid="span1">种类名称：</span>
      <span xid="span9" bind-text=' val("typeName")'></span></div> </div> 
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row11">
     <div class="x-col" xid="col33">
      <span xid="span2">是否认证：</span>
      <span xid="certificate" bind-text=" val(&quot;certificate&quot;)==1?'有机':val(&quot;certificate&quot;)==2?'绿色':val(&quot;certificate&quot;)==3?'无公害产品':val(&quot;certificate&quot;)==4?'地理标志认证':'无'"></span></div> </div> 
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row1">
     <div class="x-col" xid="col1">
      <span xid="span3">年商品量(吨)：</span>
      <span xid="quantity" bind-text='ref("quantity")'></span></div> </div> 
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row2">
     <div class="x-col" xid="col4">
      <span xid="span5">上市期：</span>
      <span xid="listed" bind-text='ref("listed")'></span></div> </div> 
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row3">
     <div class="x-col" xid="col7">
      <span xid="span7">主要销售地：</span>
      <span xid="salearea" bind-text='ref("salearea")'></span></div> </div> 
    <div component="$UI/system/components/justep/row/row" class="x-row" xid="row4">
     <div class="x-col x-col-fixed" xid="col12">
      <a component="$UI/system/components/justep/button/button" class="btn x-green btn-sm" label="修改" xid="updateBt" onClick="updateBtClick" style="width:100%;">
       <i xid="i5"></i>
       <span xid="span13">修改</span></a> 
  </div> 
  <div class="x-col x-col-fixed" xid="col2">
   <a component="$UI/system/components/justep/button/button" class="btn x-green btn-sm" label="删除" xid="delbtn" onClick="delbtnClick" style="width:100%;">
    <i xid="i4"></i>
    <span xid="span10">删除</span></a> </div>
  <div class="x-col x-col-fixed" xid="col3" bind-visible=" $model.isBatch == 1">
   <a component="$UI/system/components/justep/button/button" class="btn x-green btn-sm" label="批次信息" xid="button1" onClick="batchbtn" style="width:100%;">
    <i xid="i3"></i>
    <span xid="span8">批次信息</span></a> </div></div> </div> </div></div> </div></li></ul> </div>
  </div>
  </div> 
<span component="$UI/system/components/justep/windowDialog/windowDialog" xid="updateDialog" style="top:412px;left:86px;" src="$UI/rlscsyapp/protype/updateProType.w" onClose="updateDialogClose"></span>
  <span component="$UI/system/components/justep/messageDialog/messageDialog" xid="messageDialog" style="top:407px;left:419px;" title="提示" message="是否删除信息？" type="OKCancel" onOK="messageDialogOK"></span></div>