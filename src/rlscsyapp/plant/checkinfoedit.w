<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window"
  design="device:m;">  
  <title>农产品质量安全追溯平台</title>
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:192px;top:26px;"
    onParamsReceive="modelParamsReceive">  
  </div>  
  <div component="$UI/system/components/justep/popOver/popOver" class="x-popOver"
    xid="popOver"> 
    <div class="x-popOver-overlay" xid="div9"/> 
  </div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full x-card"
    xid="panel1"> 
    <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
        xid="titleBar1" title="检验报告"> 
        <div class="x-titlebar-left" xid="div2"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-left btn-only-icon"
            xid="button6" icon="icon-arrow-left-c" onClick="{operation:'window.close'}"> 
            <i xid="i2" class="icon-arrow-left-c"/>  
            <span xid="span6">关闭</span> 
          </a> 
        </div>  
        <div class="x-titlebar-title" xid="div3">检验报告</div>  
        <div class="x-titlebar-right reverse" xid="div4"/> 
      </div> 
    </div>  
    <div class="x-panel-content x-cards" xid="content1"> 
      <div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu"
        xid="panel3"> 
        <div> 
          <div class="panel-heading" xid="div8"> 
            <div class="media-left media-middle" xid="mediaLeft1"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[报告名称]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody1"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="checkName" dataType="String"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div5">
   <div class="media-left media-middle" xid="mediaLeft3">
    <span class="x-flex" style="width:70px;" xid="span3"><![CDATA[报告编号]]></span></div> 
   <div class="media-body" xid="mediaBody3">
    <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="checkNum" dataType="String"></input></div> </div><div class="panel-heading" xid="div8"> 
            <div class="media-left media-middle" xid="mediaLeft1"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[检验单位]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody1">  
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="checkUnit" dataType="String"></input></div> 
          </div>  
          <div class="panel-heading" xid="div7">
   <div class="media-left media-middle" xid="mediaLeft4">
    <span class="x-flex" style="width:70px;" xid="span5"><![CDATA[检验时间]]></span></div> 
   <div class="media-body" xid="mediaBody4">
    <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="checkTime" dataType="Date"></input></div> </div><div class="panel-heading" xid="div10">
   <div class="media-left media-middle" xid="mediaLeft5">
    <span class="x-flex" style="width:70px;" xid="span7"><![CDATA[检验结果]]></span></div> 
   <div class="media-body" xid="mediaBody5">
    <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="checkResult" dataType="String"></input></div> </div><div class="panel-heading" xid="div8"> 
            <div class="media-left media-middle" xid="mediaLeft1"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[报告类型]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody1"> 
              <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择..." class="form-control x-edit" xid="checkType">
   				 <option value="1">第三方检测报告</option>
				 <option value="2">企业自检报告</option></select></div> 
          </div>  
          <div class="panel-heading" xid="div6">
   <a component="$UI/system/components/justep/button/button" class="btn x-green btn-only-label btn-block" label="提交" xid="button2" onClick="verifyButton">
    <i xid="i1"></i>
    <span xid="span4">提交</span></a> </div>
  </div> 
      </div> 
    </div> 
  </div>  
  <span component="$UI/system/components/justep/windowReceiver/windowReceiver"
    xid="windowReceiverEdit"/> 
</div>
