<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window"
  design="device:m;">  
  <title>肉类蔬菜流通追溯管理平台</title>  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:192px;top:26px;"
    onParamsReceive="modelParamsReceive"> 
    </div>  
  <div component="$UI/system/components/justep/popOver/popOver" class="x-popOver"
    xid="popOver"> 
    <div class="x-popOver-overlay" xid="div1"/> 
  </div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full x-card"
    xid="panel1"> 
    <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
        xid="titleBar1"> 
        <div class="x-titlebar-left" xid="div2"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-left"
            label="资质证书" xid="button6" icon="icon-arrow-left-c" onClick="{operation:'window.close'}"> 
            <i xid="i2" class="icon-arrow-left-c"/>  
            <span xid="span6">资质证书</span> 
          </a> 
        </div>  
        <div class="x-titlebar-title" xid="div3"/>  
        <div class="x-titlebar-right reverse" xid="div4"/> 
      </div> 
    </div>  
    <div class="x-panel-content x-cards" xid="content1"> 
      <div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu"
        xid="panel3"> 
        <div> 
          <div class="panel-heading" xid="div10"> 
            <div class="media-left media-middle" xid="mediaLeft10"> 
              </div>  
            <div class="media-body" xid="mediaBody10"> 
              </div> 
          </div>  
          <div class="panel-heading" xid="div11"> 
            <div class="media-left media-middle" xid="mediaLeft11"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[证书名称]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody11"> 
              <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit"
                xid="zizhiName" dataType="String" readonly="true"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div12"> 
            <div class="media-left media-middle" xid="mediaLeft12"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[申请原因]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody12"> 
              <textarea component="$UI/system/components/justep/textarea/textarea" class="form-control" xid="applyCause"></textarea></div> 
          </div>  
          <div class="panel-heading" xid="div15"> 
            <a component="$UI/system/components/justep/button/button" class="btn x-green btn-only-label btn-block"
              label="提交" xid="button2" onClick="verifyButton"> 
              <i xid="i1"/>  
              <span xid="span4">提交</span> 
            </a> 
          </div> 
        </div> 
      </div> 
    </div> 
  </div>  
  <span component="$UI/system/components/justep/windowReceiver/windowReceiver"
    xid="windowReceiverEdit" style="top:79px;left:17px;"/> 
</div>
