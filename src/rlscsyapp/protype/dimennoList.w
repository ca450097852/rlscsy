<?xml version="1.0" encoding="utf-8"?>
<div xmlns="http://www.w3.org/1999/xhtml" component="$UI/system/components/justep/window/window" design="device:m;" xid="window" class="window">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:294px;left:374px;" onParamsReceive="modelParamsReceive"> 
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="proTypeBatch" idColumn="ptbId" onCustomRefresh="proTypeBatchCustomRefresh">
   <column label="ptbId" name="ptbId" type="Integer" xid="xid1"></column>
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
        <div component="$UI/system/components/justep/titleBar/titleBar" title="追溯标识"
          class="x-titlebar">
          <div class="x-titlebar-left"> 
            <a component="$UI/system/components/justep/button/button"
              label="" class="btn btn-link btn-only-icon" icon="icon-arrow-left-c"
              onClick="{operation:'window.close'}" xid="backBtn"> 
              <i class="icon-arrow-left-c"/>  
              <span></span> 
            </a> 
          </div>  
          <div class="x-titlebar-title">追溯标识</div>  
          <div class="x-titlebar-right reverse"> 
          </div>
        </div> 
      </div>  
    <div class="x-panel-content" xid="content1" _xid="C7197539DE100001C6169398112814EE" style="bottom: 0px;"><div component="$UI/system/components/justep/row/row" class="x-row" xid="row2">
   <div class="x-col x-col-fixed tb-nopadding" xid="col2">
    <img src="" alt="" xid="image1" bind-attr-src='$model.getImageUrl( $model.proTypeBatch.val("codeImg"))' class="tb-img-good" style="width:100%;"></img></div> </div></div>
  </div> 
</div>