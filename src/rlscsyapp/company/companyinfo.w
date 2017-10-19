<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window"
  design="device:m;">  
  <title>肉类蔬菜流通追溯管理平台</title>  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:224px;top:90px;"
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
  <column label="recordDate" name="recordDate" type="String" xid="xid20"></column>
  <column label="licenseImg" name="licenseImg" type="String" xid="xid20"></column>
  <column label="state" name="state" type="String" xid="xid21"></column>
  <column label="regTime" name="regTime" type="String" xid="xid22"></column>
  <column label="remark" name="remark" type="String" xid="xid23"></column>
  <column label="entId" name="entId" type="String" xid="xid24"></column>
  <column label="nodeName" name="nodeName" type="String" xid="xid25"></column>
  <column label="areaName" name="areaName" type="String" xid="xid26"></column>
  <column label="userName" name="userName" type="String" xid="xid27"></column></div> 
  </div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full"
    xid="panel1" style="background-color:#F2F2F2;"> 
    <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
        xid="titleBar1"> 
        <div class="x-titlebar-left" xid="left1">
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon"
            label="返回" xid="backBtn" icon="icon-arrow-left-c" onClick="{operation:'window.close'}"> 
            <i xid="i2" class="icon-arrow-left-c"/>  
            <span xid="span5">返回</span>
          </a>
        </div>  
        <div class="x-titlebar-title" xid="title1">
          <span xid="span4"><![CDATA[企业基本信息]]></span>
        </div>  
        <div class="x-titlebar-right reverse" xid="right1"/> 
      </div>
    </div>  
    <div class="x-panel-content" xid="content1"> 
      <div component="$UI/system/components/justep/contents/contents" class="x-contents x-full"
        active="0" xid="contents1"> 
        <div class="x-contents-content" xid="content2">
          <div component="$UI/system/components/justep/panel/panel" class="panel panel-default x-card"
            xid="panel2"> 
            <div class="list-group" xid="div1"> 
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item" xid="row6">
   <div class="x-col" xid="col17">
    <span xid="span40"><![CDATA[　企业logo：]]></span>
    <span xid="span39" bind-click="showLogoClick">
     <img src="" alt="" xid="comLogo" id="comLogo" style="width:160px;" height="80px" align="middle"></img></span> </div> </div><div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row21"> 
                <div class="x-col" xid="col4"> 
                  <span xid="span6"><![CDATA[　企业名称：]]></span>  
                  <span xid="span7" bind-text="$model.companydata.val(&quot;name&quot;)"/> 
                </div> 
              </div>  
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row22"> 
                <div class="x-col" xid="col2"> 
                  <span xid="span27"><![CDATA[　经营类型：]]></span>  
                  <span xid="span29" bind-text=' $model.getComType($model.companydata.val("comType"))'/>
                </div> 
              </div>
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row23"> 
                <div class="x-col" xid="col7"> 
                  <span xid="span8"><![CDATA[　所属区域：]]></span>  
                  <span xid="span9" bind-text=' $model.companydata.val("areaName")'/>
                </div> 
              </div>  
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row24"> 
                <div class="x-col" xid="col9"> 
                  <span xid="span10"><![CDATA[　所属节点：]]></span>  
                  <span xid="span11" bind-text=' $model.companydata.val("nodeName")'/>
                </div> 
              </div>  
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row25"> 
                <div class="x-col" xid="col11"> 
                  <span xid="span12"><![CDATA[　企业性质：]]></span>  
                  <span xid="span13" bind-text=' $model.getNature( $model.companydata.val("nature"))'/>
                </div> 
              </div>  
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row26"> 
                <div class="x-col" xid="col16"> 
                  <span xid="span21"><![CDATA[经营者编码：]]></span>  
                  <span xid="span18" bind-text=' $model.companydata.val("comCode")'/>
                </div> 
              </div>  
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row27" bind-if='$model.companydata.val("flag")  &gt;0'> 
                <div class="x-col" xid="col1"> 
                  <span xid="span25"><![CDATA[　节点类型：]]></span>  
                  <span xid="span24" bind-text=' $model.getFlag( $model.companydata.val("flag"))'><![CDATA[]]></span> 
                </div> 
              </div>  
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row28" bind-if='$model.companydata.val("flag")  &gt;0'> 
                <div class="x-col" xid="col3">
                  <span xid="span26"><![CDATA[　节点编码：]]></span>  
                  <span xid="span30" bind-text=' $model.companydata.val("nodeCode")'/>
                </div> 
              </div>  
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row29"> 
                <div class="x-col" xid="col8">
                  <span xid="span31"><![CDATA[　　联系人：]]></span>  
                  <span xid="span32" bind-text=' $model.companydata.val("linkMan")'/>
                </div> 
              </div>  
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row30"> 
                <div class="x-col" xid="col14">
                  <span xid="span34"><![CDATA[　手机号码：]]></span>  
                  <span xid="span33" bind-text=' $model.companydata.val("phone")'/>
                </div> 
              </div>  
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row31"> 
                <div class="x-col" xid="col18">
                  <span xid="span36" bind-text=' $model.companydata.val("email")'><![CDATA[　电子邮箱：]]></span>  
                  <span xid="span35" bind-text=' $model.companydata.val("email")'/>
                </div> 
              </div>
              <div component="$UI/system/components/justep/row/row" class="x-row list-group-item"
                xid="row32"> 
                <div class="x-col" xid="col13"> 
                  <span xid="span14"><![CDATA[　　　法人：]]></span>  
                  
                <span xid="span15" bind-text=' $model.companydata.val("corporate")' /></div> 
              </div> 
            <div component="$UI/system/components/justep/row/row" class="x-row list-group-item" xid="row3">
   <div class="x-col" xid="col10">
    <span xid="span19"><![CDATA[　　　地址：]]></span>
    <span xid="span20" bind-text=' $model.companydata.val("addr")'></span></div> </div><div component="$UI/system/components/justep/row/row" class="x-row list-group-item" xid="row4">
   <div class="x-col" xid="col12">
    <span xid="span22"><![CDATA[　备案日期：]]></span>
    <span xid="span23" bind-text=' $model.companydata.val("recordDate")'></span></div> </div><div component="$UI/system/components/justep/row/row" class="x-row list-group-item" xid="row1">
   <div class="x-col" xid="col5">
    <span xid="span2"><![CDATA[　　证件号：]]></span>
    <span xid="span3" bind-text=' $model.companydata.val("regCode")'></span></div> </div>
  <div component="$UI/system/components/justep/row/row" class="x-row list-group-item" xid="row2">
   <div class="x-col" xid="col6">
    <span xid="span16"><![CDATA[　营业执照：]]></span>
    <span xid="span17" bind-text=' $model.companydata.val("licenseNum")'></span></div> </div>
  
  <div component="$UI/system/components/justep/row/row" class="x-row list-group-item" xid="row5">
   <div class="x-col" xid="col15">
    <span xid="span37"><![CDATA[　营业执照：]]></span>
    <span xid="span38" bind-click="showClick">
     <img src="" alt="" xid="imageUpload" id="imageUpload" style="width:160px;" height="80px" align="middle"></img></span> </div> </div>
  </div> 
          </div>  
          <div component="$UI/system/components/justep/panel/panel" class="panel panel-default x-card"
            xid="panel4"> 
            <div xid="div10" class="list-group-item"> 
              <h4 xid="h41"><![CDATA[简介]]></h4>  
              <div xid="div2"> 
                <span xid="span28" bind-text=' $model.companydata.val("introduction")'/>
              </div> 
            </div> 
          </div>  
          <a component="$UI/system/components/justep/button/button" class="btn x-green"
            label="修改" xid="button2" style="width:100%;" icon="icon-edit" onClick="button1Click"> 
            <i xid="i1" class="icon-edit"/>  
            <span xid="span1">修改</span>
          </a>
        </div>
      </div>
    </div> 
  </div>  
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialog"
    onReceive="windowDialogReceive" onClose="windowDialogClose" style="top:9px;left:273px;"/>  
  <span component="$UI/system/components/justep/windowDialog/windowDialog" xid="windowDialogImg"
    onReceive="windowDialogImgReceive" style="top:5px;left:144px;"/>
</div>
