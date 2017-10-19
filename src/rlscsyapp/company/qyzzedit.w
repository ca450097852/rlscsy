<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window"
  design="device:m;">  
  <title>肉类蔬菜流通追溯管理平台</title>  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:192px;top:26px;"
    onParamsReceive="modelParamsReceive"> 
    <div component="$UI/system/components/justep/data/data" autoLoad="true"
      xid="baseTypeData" idColumn="value" autoNew="false"> 
      <column label="value" name="value" type="String" xid="xid2"/>  
      <column label="text" name="text" type="String" xid="xid9"/>  
      <data xid="default1">[{"value":"龙头企业类","text":"龙头企业类"},{"value":"认证类","text":"认证类"},{"value":"示范合作社","text":"示范合作社"},{"value":"示范区、场","text":"示范区、场"},{"value":"家庭农场","text":"家庭农场"},{"value":"其他","text":"其他"}]</data>
    </div>  
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
              <span class="x-flex" xid="span2" style="width:70px;"><![CDATA[资质类型]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody10"> 
              <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择..."
                class="form-control input-sm" xid="zizhiType" optionsAutoLoad="true"
                bind-options="baseTypeData" bind-optionsValue="value" bind-optionsLabel="text"
                onChange="zizhiTypeChange" style="width:100%;"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div5" bind-visible="leveshow"> 
            <div class="media-left media-middle" xid="mediaLeft5"> 
              <span class="x-flex" style="width:70px;" xid="span1"><![CDATA[资质级别]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody5" bind-html="levelHtml"></div> 
          </div>  
          <div class="panel-heading" xid="div11"> 
            <div class="media-left media-middle" xid="mediaLeft11"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[证书名称]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody11"> 
              <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit"
                xid="zizhiName" dataType="String"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div12"> 
            <div class="media-left media-middle" xid="mediaLeft12"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[颁发单位]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody12"> 
              <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit"
                xid="awardUnit" dataType="String"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div13"> 
            <div class="media-left media-middle" xid="mediaLeft13"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[颁发时间]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody13"> 
              <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit"
                xid="awardTime" dataType="Date"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div14"> 
            <div class="media-left media-middle" xid="mediaLeft14"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[备注]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody14"> 
              <input component="$UI/system/components/justep/input/input" class="form-control input-sm x-edit"
                xid="remark"/> 
            </div> 
          </div> 
          <div class="panel-heading" xid="div7"> 
            <div class="media-left media-middle" xid="mediaLeft4"> 
              <span class="x-flex" style="width:70px;" xid="span5"><![CDATA[图片附件]]></span>
            </div>  
            <div class="media-body" xid="mediaBody4"> 
              <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs"
                label="上传" xid="buttonUpload" icon="icon-android-add" bind-click="buttonUploadClick" > 
                <i xid="i5" class="icon-android-add"/>  
                <span xid="span24">上传</span>
              </a>  
              <a component="$UI/system/components/justep/button/button" class="btn x-green btn-xs"
                label="移除" xid="buttonRemove" style="margin-left:10px;" icon="icon-android-remove"
                onClick="buttonRemoveClick"> 
                <i xid="i6" class="icon-android-remove"/>  
                <span xid="span25">移除</span>
              </a>  
              <input type="file" name="file" value="" xid="inputImage" size="1" bind-change="inputImageChange"
                accept="image/*" style="display:none;"/>
            </div>  
            <div class="media-right" xid="mediaRight1"> 
              <span xid="spanImg"/>
            </div> 
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
    xid="windowReceiverEdit"/> 
</div>
