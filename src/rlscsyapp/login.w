<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" class="main13" component="$UI/system/components/justep/window/window"
  design="device:mobile;" xid="window">
 <title>肉类蔬菜流通追溯管理平台</title> 
  <div component="$UI/system/components/justep/model/model" xid="model" style="height:auto;left:84px;top:1px;" onParamsReceive="modelParamsReceive"> 
    <!--   <div component="$UI/system/components/justep/data/baasData" autoLoad="false" xid="myPlatformData"></div> --> 
  </div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full x-card x-has-iosstatusbar"
    xid="panel2"> 
    <div class="x-panel-top" xid="top1"> 
      <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
        xid="titleBar1" title="企业用户登录"> 
        <div class="x-titlebar-left" xid="div2"> 
          <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-left"
            xid="button6" icon="icon-arrow-left-c" onClick="button6Click"> 
            <i xid="i2" class="icon-arrow-left-c"/>  
            <span xid="span6"></span> 
          </a> 
        </div>  
        <div class="x-titlebar-title" xid="div3">企业用户登录</div>  
        <div class="x-titlebar-right reverse" xid="div4"/> 
      </div> 
    </div>  
    <div class="x-panel-content  x-cards panel-body" xid="content1"> 
      <div xid="div1" class="list-group"> 
        <div class="list-group-item"> 
          <div class="input-group" xid="div7"> 
            <span class="input-group-addon" xid="span2"> 
              <i class="icon-ios7-contact"/> 
            </span>  
            <input component="$UI/system/components/justep/input/input" class="form-control x-inputText"
              xid="nameInput" placeHolder="请输入登录账号" onChange="nameInputChange"/> 
          </div> 
        </div>  
        <div class="list-group-item" xid="div6"> 
          <div class="input-group" xid="div8"> 
            <span class="input-group-addon" xid="span3"> 
              <i class="icon-unlocked" xid="i3"/> 
            </span>  
            <input component="$UI/system/components/justep/input/password" class="form-control x-inputText"
              xid="passwordInput" placeHolder="请输入登录密码" onChange="passwordInputChange"/> 
          </div> 
        </div> 
      </div>  
      <a component="$UI/system/components/justep/button/button" class="btn x-green btn-only-label btn-block"
        label="登录" xid="loginIsmBtn" onClick="loginIsmBtn"> 
        <i xid="i4"/>  
        <span xid="span4">登录</span> 
      </a>  
      <div class="list-group"> 
        <div class="list-group-item"> 
          <div class="h5" xid="div5">
   <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-xs btn-only-icon pull-right" label="button" xid="button2" icon="icon-ios7-arrow-right">
    <i xid="i1" class="icon-ios7-arrow-right text-muted"></i>
    <span xid="span5"></span></a> 
   <span xid="span8" class="text-black"><![CDATA[没有账号？]]></span>
   <span class="text-danger" xid="phoneReg" bind-click="registerClick" url="registerMobile"><![CDATA[立即注册]]></span></div></div> 
      </div> 
    </div> 
  </div> 
<span component="$UI/system/components/justep/windowReceiver/windowReceiver" xid="loginWindowReceiver"></span></div>
