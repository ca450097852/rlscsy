<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window"
  design="device:m;">  
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;top:49px;left:581px;"> 
    
    <div component="$UI/system/components/justep/data/data" autoLoad="false"
      xid="cityData" limit="50" idColumn="id" onCustomRefresh="cityDataCustomRefresh"> 
      <column label="id" name="id" type="String" xid="xid1"/>  
      <column label="text" name="text" type="String" xid="xid2"/> 
    </div>  
    <div component="$UI/system/components/justep/data/data" autoLoad="false"
      xid="areaData" limit="50" idColumn="id" onCustomRefresh="areaDataCustomRefresh"> 
      <column label="id" name="id" type="String" xid="default1"/>  
      <column label="text" name="text" type="String" xid="default2"/> 
    </div>  
    <div component="$UI/system/components/justep/data/data" autoLoad="false"
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
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full"
    xid="panel1"> 
    <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
        xid="titleBar1" title="企业注册"> 
        <div class="x-titlebar-left" xid="left1"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon"
            label="button" xid="button1" icon="icon-chevron-left" onClick="{&quot;operation&quot;:&quot;window.close&quot;}"> 
            <i xid="i2" class="icon-chevron-left"/>  
            <span xid="span1"/> 
          </a> 
        </div>  
        <div class="x-titlebar-title" xid="title1">企业注册</div>  
        <div class="x-titlebar-right reverse" xid="right1"/> 
      </div> 
    </div>  
    <div class="x-panel-content" xid="content1"> 
      <div component="$UI/system/components/justep/controlGroup/controlGroup"
        class="x-control-group" title="title" xid="controlGroup1"/>  
      <div xid="div3"> 
        <div component="$UI/system/components/justep/row/row" class="x-row"
          xid="row4"> 
          <div class="x-col x-col-25" xid="col8"> 
            <span class="x-flex" style="width:60px;" xid="span6"><![CDATA[所在城市]]></span> 
          </div>  
          <div class="x-col x-col-75" xid="col3"> 
            <div class="x-gridSelect" component="$UI/system/components/justep/gridSelect/gridSelect"
              xid="gridSelect1" onUpdateValue="gridSelect1UpdateValue" bind-labelRef="$model.cityData.ref(&quot;text&quot;)"
              onShowOption="gridSelect1ShowOption"> 
              <option xid="option1" data="cityData" value="id" label="text" autoLoad="false"/> 
            </div> 
          </div> 
        </div>  
        <div component="$UI/system/components/justep/row/row" class="x-row"
          xid="row5"> 
          <div class="x-col x-col-25" xid="col9"> 
            <span class="x-flex" style="width:60px;" xid="span7"><![CDATA[所在区县]]></span> 
          </div>  
          <div class="x-col x-col-75" xid="col10"> 
            <div class="x-gridSelect" component="$UI/system/components/justep/gridSelect/gridSelect"
              xid="gridSelect2" onShowOption="gridSelect2ShowOption" onUpdateValue="gridSelect2UpdateValue"
              bind-labelRef="$model.areaData.ref(&quot;text&quot;)"> 
              <option xid="option2" data="areaData" value="id" label="text" autoLoad="false"
                multiselectWidth="50"/> 
            </div> 
          </div> 
        </div>  
        <div component="$UI/system/components/justep/row/row" class="x-row"
          xid="row6"> 
          <div class="x-col x-col-25" xid="col11"> 
            <span class="x-flex" style="width:60px;" xid="span9"><![CDATA[所在节点]]></span> 
          </div>  
          <div class="x-col x-col-75" xid="col12"> 
            <div class="x-gridSelect" component="$UI/system/components/justep/gridSelect/gridSelect"
              xid="gridSelect3" onShowOption="gridSelect3ShowOption" bind-labelRef="$model.nodeData.ref(&quot;text&quot;)"
              onUpdateValue="gridSelect3UpdateValue"> 
              <option xid="option3" data="nodeData" value="id" label="text" autoLoad="false"
                multiselectWidth="50"/> 
            </div> 
          </div> 
        </div>  
        <div component="$UI/system/components/justep/row/row" class="x-row"
          xid="row7"> 
          <div class="x-col x-col-25" xid="col14"> 
            <span class="x-flex" style="width:60px;" xid="span10"><![CDATA[企业名称]]></span> 
          </div>  
          <div class="x-col x-col-75" xid="col13"> 
            <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
              xid="name" placeHolder="请输入企业名称" maxLength="50"/> 
          </div> 
        </div>  
        <div component="$UI/system/components/justep/row/row" class="x-row"
          xid="row9"> 
          <div class="x-col x-col-25" xid="col17"> 
            <span class="x-flex" style="width:60px;" xid="span12"><![CDATA[经营类型]]></span> 
          </div>  
          <div class="x-col x-col-75" xid="col18"> 
            <select component="$UI/system/components/justep/select/select" bind-optionsCaption="请选择经营类型"
              class="form-control" xid="comType" bind-options="comTypeData" bind-optionsValue="id"
              bind-optionsLabel="text"/> 
          </div> 
        </div>  
        <div component="$UI/system/components/justep/row/row" class="x-row"
          xid="row8"> 
          <div class="x-col x-col-25" xid="col16"> 
            <span class="x-flex" style="width:60px;" xid="span11"><![CDATA[企业账号]]></span> 
          </div>  
          <div class="x-col x-col-75" xid="col15"> 
            <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
              xid="account" placeHolder="请输入企业账号"  maxLength="25"/> 
          </div> 
        </div>  
        <div component="$UI/system/components/justep/row/row" class="x-row"
          xid="row2"> 
          <div class="x-col x-col-25" xid="col5"> 
            <span class="x-flex" style="width:60px;" xid="span3"><![CDATA[登录密码]]></span> 
          </div>  
          <div class="x-col x-col-75" xid="col4"> 
            <input component="$UI/system/components/justep/input/password" class="form-control x-edit"
              xid="password" placeHolder="请输入密码"/> 
          </div> 
        </div>  
        <div component="$UI/system/components/justep/row/row" class="x-row"
          xid="row3"> 
          <div class="x-col x-col-25" xid="col7"> 
            <span class="x-flex" style="width:60px;" xid="span3">确认密码</span> 
          </div>  
          <div class="x-col x-col-75" xid="col6"> 
            <input component="$UI/system/components/justep/input/password" class="form-control x-edit"
              xid="repassword" placeHolder="确认密码"/> 
          </div> 
        </div>  
        <div component="$UI/system/components/justep/row/row" class="x-row"
          xid="row1"> 
          <div class="x-col x-col-25" xid="col1"> 
            <span class="x-flex" style="width:60px;" xid="span4">手机号码</span> 
          </div>  
          <div class="x-col x-col-75" xid="col2"> 
            <input component="$UI/system/components/justep/input/input" class="form-control x-edit"
              xid="phone" placeHolder="请输入手机" maxLength="20"/> 
          </div> 
        </div>  
        <div class="panel-heading" xid="div6"> 
          <a component="$UI/system/components/justep/button/button" class="btn x-green center-block"
            label="注册" xid="regBtn" onClick="regBtnClick"> 
            <i xid="i1"/>  
            <span xid="span2">注册</span> 
          </a> 
        </div>  
        <div class="list-group" xid="listGroup1"> 
          <div class="list-group-item" xid="div1"> 
            <div class="h5" xid="div2"> 
              <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-xs btn-only-icon pull-right"
                label="button" xid="button2" icon="icon-ios7-arrow-right"> 
                <i xid="i3" class="icon-ios7-arrow-right text-muted"/>  
                <span xid="span5"/> 
              </a>  
              <span xid="span8" class="text-black"><![CDATA[已有账号？]]></span>  
              <span class="text-danger" xid="phoneReg" bind-click="loginClick"
                url="registerMobile"><![CDATA[立即登录]]></span> 
            </div> 
          </div> 
        </div> 
      </div> 
    </div>  
    <div class="x-panel-bottom" xid="bottom1"/> 
  </div> 
</div>
