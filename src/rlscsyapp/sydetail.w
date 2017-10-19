<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window">  
  <title>肉类蔬菜流通追溯管理平台</title>  
  <div component="$UI/system/components/justep/model/model" xid="model" style="left:472px;top:72px;height:51px;"
    onParamsReceive="modelParamsReceive">
    
    <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="data1" idColumn="dimenno" onCustomRefresh="data1CustomRefresh">
    <column label="dimenno" name="dimenno" type="String" xid="xid1"></column></div>
    
   </div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full"
    xid="panel1"> 
    <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
        xid="titleBar1" title="溯源结果"> 
        <div class="x-titlebar-left" xid="div1"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon"
            xid="button1" icon="icon-chevron-left" onClick="{operation:'window.close'}"> 
            <i xid="i1" class="icon-chevron-left"/>  
            <span xid="span1"/> 
          </a> 
        </div>  
        <div class="x-titlebar-title" xid="div2">溯源结果</div>  
        <div class="x-titlebar-right reverse" xid="div3"></div> 
      </div> 
    </div>  
    <div class="x-panel-content" xid="content1"> 
      <div component="$UI/system/components/justep/contents/contents" class="x-contents x-full" active="1" xid="contents" style="height:100%;width:100%;"> 
        <iframe src="" xid="iframe1" scrolling="auto;" bind-attr-src='$model.getSrcUrl( $model.data1.val("dimenno"))' height="100%" width="100%"/> 
      </div> 
    </div> 
  </div> 
</div>
