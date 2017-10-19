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
        xid="titleBar1"> 
        <div class="x-titlebar-left" xid="div2"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-left"
            label="生猪进厂记录" xid="button6" icon="icon-arrow-left-c" onClick="{operation:'window.close'}"> 
            <i xid="i2" class="icon-arrow-left-c"/>  
            <span xid="span6">生猪进厂记录</span> 
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
          <div class="panel-heading" xid="div8"> 
            <div class="media-left media-middle" xid="mediaLeft1"> 
              <span class="x-flex" xid="span2" style="width:70px;"><![CDATA[屠宰厂编码]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody1"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit" xid="butcherFacId" dataType="String"></input></div> 
          </div>  
          <div class="panel-heading" xid="div8"> 
            <div class="media-left media-middle" xid="mediaLeft1"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[屠宰厂名称]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody1"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="butcherFacName" dataType="String"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div8"> 
            <div class="media-left media-middle" xid="mediaLeft1"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[货主编码]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody1"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="sellerId" dataType="String"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div8"> 
            <div class="media-left media-middle" xid="mediaLeft1"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[货主名称]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody1">             
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="sellerName" dataType="String"/>
            </div> 
          </div>  
          <div class="panel-heading" xid="div8"> 
            <div class="media-left media-middle" xid="mediaLeft1"> 
              <span class="x-flex" style="width:70px;" xid="span2"><![CDATA[进场日期]]></span> 
            </div>  
            <div class="media-body" xid="mediaBody1"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="inDate" dataType="Date"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div1"> 
            <div class="media-left media-middle" xid="mediaLeft2"> 
              <span class="x-flex" style="width:70px;" xid="span1"><![CDATA[产地检疫证号]]></span>
            </div>  
            <div class="media-body" xid="mediaBody2"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="quarantineId" dataType="String"/>
            </div> 
          </div>  
          <div class="panel-heading" xid="div5"> 
            <div class="media-left media-middle" xid="mediaLeft3"> 
              <span class="x-flex" style="width:70px;" xid="span3"><![CDATA[进场数量]]></span>
            </div>  
            <div class="media-body" xid="mediaBody3"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="quarantineNum" style="width:100%;" dataType="Float"/> 
            </div> 
          </div>  
          <div class="panel-heading" xid="div7"> 
            <div class="media-left media-middle" xid="mediaLeft4"> 
              <span class="x-flex" style="width:70px;" xid="span5">采购价</span>
            </div>  
            <div class="media-body" xid="mediaBody4"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="price" style="width:100%;" dataType="Float"/>
            </div> 
          </div>  
          <div class="panel-heading" xid="div10"> 
            <div class="media-left media-middle" xid="mediaLeft5"> 
              <span class="x-flex" style="width:70px;" xid="span7">实际进场数量及重量</span>
            </div>  
            <div class="media-body" xid="mediaBody5"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="amount" style="width:100%;" dataType="String"/>
            </div> 
          </div>  
          <div class="panel-heading" xid="div11"> 
            <div class="media-left media-middle" xid="mediaLeft6"> 
              <span class="x-flex" style="width:70px;" xid="span8">途亡数量</span>
            </div>  
            <div class="media-body" xid="mediaBody6"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="deadNum" style="width:100%;" dataType="Float"/>
            </div> 
          </div>  
          <div class="panel-heading" xid="div12"> 
            <div class="media-left media-middle" xid="mediaLeft7"> 
              <span class="x-flex" style="width:70px;" xid="span9">检疫结果</span>
            </div>  
            <div class="media-body" xid="mediaBody7"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="checkResult" style="width:100%;" dataType="String"/>
            </div> 
          </div>  
          <div class="panel-heading" xid="div13"> 
            <div class="media-left media-middle" xid="mediaLeft8"> 
              <span class="x-flex" style="width:70px;" xid="span10">产地编码</span>
            </div>  
            <div class="media-body" xid="mediaBody8"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="areaOriginId" style="width:100%;" dataType="String"/>
            </div> 
          </div>  
          <div class="panel-heading" xid="div14"> 
            <div class="media-left media-middle" xid="mediaLeft9"> 
              <span class="x-flex" style="width:70px;" xid="span11">产地名称</span>
            </div>  
            <div class="media-body" xid="mediaBody9"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="areaOriginName" style="width:100%;" dataType="String"/>
            </div> 
          </div>  
          <div class="panel-heading" xid="div15"> 
            <div class="media-left media-middle" xid="mediaLeft10"> 
              <span class="x-flex" style="width:70px;" xid="span12">养殖场名称</span>
            </div>  
            <div class="media-body" xid="mediaBody10"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="farmName" style="width:100%;" dataType="String"/>
            </div> 
          </div>  
          <div class="panel-heading" xid="div16"> 
            <div class="media-left media-middle" xid="mediaLeft11"> 
              <span class="x-flex" style="width:70px;" xid="span13">运输车牌号</span>
            </div>  
            <div class="media-body" xid="mediaBody11"> 
              <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
                xid="transporterId" style="width:100%;" dataType="String"/>
            </div> 
          </div>
          <div class="panel-heading" xid="div6"> 
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
    xid="windowReceiverEdit" style="top:36px;left:127px;"/> 
</div>
