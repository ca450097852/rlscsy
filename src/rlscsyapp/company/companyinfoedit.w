<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window"
  design="device:m;">  
  <title>肉类蔬菜流通追溯管理平台</title>  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:192px;top:26px;"
    onLoad="modelLoad"> 
    <div component="$UI/system/components/justep/data/data" autoLoad="false"
      xid="companydata" onCustomRefresh="companydataCustomRefresh" idColumn="comId"> 
      <column label="comId" name="comId" type="Integer" xid="xid1"></column>
  <column label="parentId" name="parentId" type="Integer" xid="xid2"></column>
  <column label="account" name="account" type="String" xid="xid3"></column>
  <column label="name" name="name" type="String" xid="xid4"></column>
  <column label="nature" name="nature" type="String" xid="xid5"></column>
  <column label="comType" name="comType" type="String" xid="xid6"></column>
  <column label="comCode" name="comCode" type="String" xid="xid8"></column>
  <column label="nodeCode" name="nodeCode" type="String" xid="xid9"></column>
  <column label="flag" name="flag" type="String" xid="xid10"></column>
  <column label="linkMan" name="linkMan" type="String" xid="xid7"></column>
  <column label="phone" name="phone" type="String" xid="xid11"></column>
  <column label="fax" name="fax" type="String" xid="xid12"></column>
  <column label="addr" name="addr" type="String" xid="xid13"></column>
  <column label="email" name="email" type="String" xid="xid14"></column>
  <column label="area" name="area" type="String" xid="xid15"></column>
  <column label="introduction" name="introduction" type="String" xid="xid16"></column>
  <column label="corporate" name="corporate" type="String" xid="xid17"></column>
  <column label="regCode" name="regCode" type="String" xid="xid18"></column>
  <column label="licenseNum" name="licenseNum" type="String" xid="xid19"></column>
  <column label="recordDate" name="recordDate" type="Date" xid="xid20"></column>
  <column label="licenseImg" name="licenseImg" type="String" xid="xid222"></column>
  <column label="state" name="state" type="String" xid="xid21"></column>
  <column label="regTime" name="regTime" type="String" xid="xid22"></column>
  <column label="remark" name="remark" type="String" xid="xid23"></column>
  <column label="entId" name="entId" type="String" xid="xid24"></column>
  <column label="nodeName" name="nodeName" type="String" xid="xid25"></column>
  <column label="areaName" name="areaName" type="String" xid="xid26"></column>
  <column label="userName" name="userName" type="String" xid="xid27"></column></div>  
    <div component="$UI/system/components/justep/data/data" autoLoad="true"
      xid="cityData" limit="50" idColumn="id" onCustomRefresh="cityDataCustomRefresh"> 
      <column label="id" name="id" type="String" xid="xid1"/>  
      <column label="text" name="text" type="String" xid="xid2"/> 
    </div>  
    <div component="$UI/system/components/justep/data/data" autoLoad="true"
      xid="areaData" limit="50" idColumn="id" onCustomRefresh="areaDataCustomRefresh"> 
      <column label="id" name="id" type="String" xid="default1"/>  
      <column label="text" name="text" type="String" xid="default2"/> 
    </div>  
    <div component="$UI/system/components/justep/data/data" autoLoad="true"
      xid="nodeData" limit="50" idColumn="id" onCustomRefresh="nodeDataCustomRefresh"> 
      <column label="id" name="id" type="String" xid="column1"/>  
      <column label="text" name="text" type="String" xid="column2"/> 
    </div>  
    <div component="$UI/system/components/justep/data/data" autoLoad="true"
      xid="comTypeData" limit="50" idColumn="id" autoNew="true"> 
      <column label="id" name="id" type="String" xid="column3"/>  
      <column label="text" name="text" type="String" xid="column4"/>  
      <data xid="default3">[{"id":"1","text":"生猪批发商"},{"id":"2","text":"肉菜批发商"},{"id":"3","text":"肉菜零售商"},{"id":"4","text":"配送企业"},{"id":"5","text":"其它"}]</data> 
    </div> 
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
            label="企业基本信息修改" xid="button6" icon="icon-arrow-left-c" onClick="{operation:'window.close'}"> 
            <i xid="i2" class="icon-arrow-left-c"/>  
            <span xid="span6">企业基本信息修改</span> 
          </a> 
        </div>  
        <div class="x-titlebar-title" xid="div3"/>  
        <div class="x-titlebar-right reverse" xid="div4"/> 
      </div> 
    </div>  
    <div class="x-panel-content" xid="content1"> 
      <div component="$UI/system/components/justep/panel/panel" class="panel panel-default tb-noborder x-card x-tuniu"
        xid="panel3"> 
        <div> 
          <div xid="div18" align="center">
   <span xid="span3" style="width:50%;vertical-align:middle;height:100px;" bind-click="showLogoClick">
    <img src="" alt="" id="logoView" style="width:200px;" height="100px" align="middle" xid="logoImg"></img></span> </div><div xid="div11">
   <div class="media" xid="media6">
    <div class="media-left media-middle" xid="mediaLeft6">
     <span class="center-block" style="width:100px;text-align:right;" xid="span18"><![CDATA[企业logo：]]></span></div> 
    <div class="media-body" xid="mediaBody6">
     <a component="$UI/system/components/justep/button/button" class="btn btn-success btn-xs" label="上传" xid="button3" icon="icon-android-add" onClick="logoUploadClick">
      <i xid="i1" class="icon-android-add"></i>
      <span xid="span17">上传</span></a> 
     <a component="$UI/system/components/justep/button/button" class="btn x-green btn-xs" label="移除" xid="button1" style="margin-left:10px;" icon="icon-android-remove" onClick="logoRemoveClick">
      <i xid="i3" class="icon-android-remove"></i>
      <span xid="span10">移除</span></a> 
     <input type="file" name="file" value="" xid="logoFileOpen" size="1" bind-change="logoImageChange" accept="image/*" style="display:none;"></input></div> </div> </div><div xid="div8" align="center"> 
            <span xid="span1" style="width:50%;vertical-align:middle;height:100px;" bind-click="showClick"> 
              <img src="" alt="" id="imageUpload1" style="width:200px;" height="100px"
                align="middle"/> 
            </span> 
          </div>  
          <div xid="div21"> 
            <div class="media" xid="media13"> 
              <div class="media-left media-middle" xid="mediaLeft4"> 
                <span class="center-block" style="width:100px;text-align:right;" xid="span12"><![CDATA[营业执照：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody4"> 
                <a component="$UI/system/components/justep/button/button"
                  class="btn btn-success btn-xs" label="上传" xid="buttonUpload" icon="icon-android-add"
                  onClick="buttonUploadClick"> 
                  <i xid="i5" class="icon-android-add"/>  
                  <span xid="span24">上传</span> 
                </a>  
                <a component="$UI/system/components/justep/button/button"
                  class="btn x-green btn-xs" label="移除" xid="buttonRemove" style="margin-left:10px;"
                  icon="icon-android-remove" onClick="buttonRemoveClick" bind-click="buttonRemoveClick"> 
                  <i xid="i6" class="icon-android-remove"/>  
                  <span xid="span25">移除</span> 
                </a>  
                <input type="file" name="file" value="" xid="inputImage" size="1"
                  bind-change="inputImageChange" accept="image/*" style="display:none;"/>  
                <!-- <input type="file" value="" xid="inputImage" size="1" bind-change="inputImageChange" accept="image/*" style="display:none;"/> --> 
              </div> 
            </div> 
          </div>  
          <div xid="div17"> 
            <div class="media" xid="media1"> 
              <div class="media-left media-middle" xid="mediaLeft1"> 
                <span xid="p5" style="width:100px;text-align:right;" class="center-block"><![CDATA[企业名称：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody1"> 
                <input component="$UI/system/components/justep/input/input" xid="name"
                  placeHolder="请输入企业名称" bind-ref="$model.companydata.ref(&quot;name&quot;)" class="form-control input-sm x-edit"/> 
              </div> 
            </div> 
          </div>  
          <div xid="div1"> 
            <div class="media" xid="media2"> 
              <div class="media-left media-middle" xid="mediaLeft2"> 
                <span xid="p1" style="width:100px;text-align:right;" class="center-block"><![CDATA[经营类型：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody2"> 
                <select component="$UI/system/components/justep/select/select"
                  bind-optionsCaption="请选择经营类型" class="form-control input-sm" xid="comType"
                  bind-options="comTypeData" bind-optionsValue="id" bind-optionsLabel="text"
                  bind-labelRef="$model.comTypeData.ref(&quot;text&quot;)" bind-ref="$model.companydata.ref(&quot;comType&quot;)"/> 
              </div> 
            </div> 
          </div>  
          <!-- <div xid="div8">
   <div class="media" xid="media4">
    <div class="media-left media-middle" xid="mediaLeft4">
     <span xid="span1" style="width:90px;" class="x-flex"><![CDATA[所属区域]]></span></div> 
    <div class="media-body" xid="mediaBody4">
     <input component="$UI/system/components/justep/input/input" xid="areaId" placeHolder="" bind-ref='$model.companydata.ref("areaId")' class="form-control input-sm"></input></div> </div> </div>
      -->  
          <div xid="div22"> 
            <div class="media" xid="media4"> 
              <div class="media-left media-middle" xid="mediaLeft13"> 
                <span xid="span13" style="width:100px;text-align:right;" class="center-block"><![CDATA[所在城市：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody13"> 
                <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择..." class="form-control input-sm" xid="citySelect" optionsAutoLoad="false" bind-options="cityData" bind-optionsValue="id" bind-optionsLabel="text" onChange="citySelectChange"></select></div> 
            </div> 
          </div>  
          <div xid="div23"> 
            <div class="media" xid="media14"> 
              <div class="media-left media-middle" xid="mediaLeft14"> 
                <span xid="span14" style="width:100px;text-align:right;" class="center-block"><![CDATA[所在区县：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody14"> 
                <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择..." class="form-control input-sm" xid="areaSelect" bind-options="areaData" bind-optionsValue="id" bind-optionsLabel="text" onChange="areaSelectChange" optionsAutoLoad="false"></select></div> 
            </div> 
          </div>  
          <div xid="div10"> 
            <div class="media" xid="media5"> 
              <div class="media-left media-middle" xid="mediaLeft5"> 
                <span xid="span2" style="width:100px;text-align:right;" class="center-block"><![CDATA[所在节点：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody5"> 
                <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择..." class="form-control input-sm" xid="nodeSelect" optionsAutoLoad="false" bind-options="nodeData" bind-optionsValue="id" bind-optionsLabel="text" onChange="nodeSelectChange"></select></div> 
            </div> 
          </div>  
          <div xid="div5"> 
            <div class="media" xid="media3"> 
              <div class="media-left media-middle" xid="mediaLeft3"> 
                <span xid="p2" style="width:100px;text-align:right;" class="center-block"><![CDATA[企业法人：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody3"> 
                <input component="$UI/system/components/justep/input/input" xid="corporate"
                  placeHolder="请输入企业法人" bind-ref="$model.companydata.ref(&quot;corporate&quot;)" class="form-control input-sm x-edit"/> 
              </div> 
            </div> 
          </div>  
          <div xid="div12"> 
            <div class="media" xid="media7"> 
              <div class="media-left media-middle" xid="mediaLeft7"> 
                <span xid="span5" style="width:100px;text-align:right;" class="center-block"><![CDATA[经营地址：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody7"> 
                <input component="$UI/system/components/justep/input/input" xid="addr"
                  placeHolder="请输入经营地址" bind-ref="$model.companydata.ref(&quot;addr&quot;)" class="form-control input-sm x-edit"/> 
              </div> 
            </div> 
          </div>  
          <div xid="div14"> 
            <div class="media" xid="media8"> 
              <div class="media-left media-middle" xid="mediaLeft8"> 
                <span xid="span7" style="width:100px;text-align:right;" class="center-block"><![CDATA[联系人：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody8"> 
                <input component="$UI/system/components/justep/input/input" xid="linkMan"
                  placeHolder="请输入联系人" bind-ref="$model.companydata.ref(&quot;linkMan&quot;)" class="form-control input-sm x-edit"/> 
              </div> 
            </div> 
          </div>  
          <div xid="div15"> 
            <div class="media" xid="media9"> 
              <div class="media-left media-middle" xid="mediaLeft9"> 
                <span xid="span8" style="width:100px;text-align:right;" class="center-block"><![CDATA[手机号码 ：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody9"> 
                <input component="$UI/system/components/justep/input/input" xid="phone"
                  placeHolder="请输入手机号码" bind-ref="$model.companydata.ref(&quot;phone&quot;)" class="form-control input-sm x-edit"/> 
              </div> 
            </div> 
          </div>  
          <div xid="div16"> 
            <div class="media" xid="media10"> 
              <div class="media-left media-middle" xid="mediaLeft10"> 
                <span xid="span9" style="width:100px;text-align:right;" class="center-block"><![CDATA[电子邮箱：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody10"> 
                <input component="$UI/system/components/justep/input/input" xid="email"
                  placeHolder="请输入电子邮件" bind-ref="$model.companydata.ref(&quot;email&quot;)" class="form-control input-sm x-edit"/> 
              </div> 
            </div> 
          </div>  
          <div xid="div19"> 
            <div class="media" xid="media12"> 
              <div class="media-left media-middle" xid="mediaLeft12"> 
                <span xid="span11" style="width:100px;text-align:right;" class="center-block"><![CDATA[证件号：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody12"> 
                <input component="$UI/system/components/justep/input/input" xid="regCode"
                  placeHolder="请输入注册号或身份证号" bind-ref="$model.companydata.ref(&quot;regCode&quot;)" class="form-control input-sm x-edit"/> 
              </div> 
            </div> 
          </div>  
          <div xid="div24"> 
            <div class="media" xid="media15"> 
              <div class="media-left media-middle" xid="mediaLeft15"> 
                <span xid="span15" style="width:100px;text-align:right;" class="center-block"><![CDATA[营业执照：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody15"> 
                <input component="$UI/system/components/justep/input/input" xid="licenseNum"
                  placeHolder="请输入营业执照或者注册号" bind-ref="$model.companydata.ref(&quot;licenseNum&quot;)" class="form-control input-sm x-edit"/> 
              </div> 
            </div> 
          </div>  
          <div xid="div25"> 
            <div class="media" xid="media16"> 
              <div class="media-left media-middle" xid="mediaLeft16"> 
                <span xid="span16" style="width:100px;text-align:right;" class="center-block"><![CDATA[备案日期：]]></span> 
              </div>  
              <div class="media-body" xid="mediaBody16"> 
                <input component="$UI/system/components/justep/input/input" xid="recordDate"
                  placeHolder="请输入备案日期" class="form-control input-sm x-edit" dataType="Date"
                  format="yyyy-MM-dd" bind-ref="$model.companydata.ref(&quot;recordDate&quot;)"/> 
              </div> 
            </div> 
          </div>  
          <div xid="div20"/>  
          <div xid="div7"> 
            <p xid="p3" style="padding:5px 5px 0px 10px;"><![CDATA[　　　　简介：]]></p>  
            <textarea component="$UI/system/components/justep/textarea/textarea"
              class="form-control" xid="introduction" bind-ref="$model.companydata.ref(&quot;introduction&quot;)"
              style="height:119px;width:98%;" placeHolder="请输入企业简介"/> 
          </div>  
          <div class="panel-heading" xid="div6"> 
            <div class="media-body" style="width:119px;" xid="div13" align="justify"/>  
            <a component="$UI/system/components/justep/button/button" class="btn x-green btn-only-label btn-block"
              label="提交" xid="button2" onClick="verifyButton"> 
              <i xid="i2"/>  
              <span xid="span4">提交</span> 
            </a> 
          </div> 
        
  </div> 
      </div> 
    </div> 
  </div>  
  <span component="$UI/system/components/justep/windowReceiver/windowReceiver"
    xid="windowReceiver1"/> 
<span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialogImg" onReceive="windowDialogImgReceive" style="top:5px;left:144px;"></span></div>
