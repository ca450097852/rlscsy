<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" xid="window" class="window" component="$UI/system/components/justep/window/window"
  design="device:m;">  
  <title>肉类蔬菜流通追溯管理平台</title>  
  <div component="$UI/system/components/justep/model/model" xid="model" style="left:18px;height:97px;top:52px;"
    onModelConstruct="modelModelConstruct"> 
    <div component="$UI/system/components/justep/data/data" autoLoad="true"
      xid="imgData" idColumn="id" onCustomRefresh="imgDataCustomRefresh"> 
      <column label="id" name="id" type="String" xid="xid1"/>  
      <column label="图片" name="fImgUrl" type="String" xid="xid2"/>  
      <column label="链接地址" name="fUrl" type="String" xid="xid9"/>
    </div>  
    <div component="$UI/system/components/justep/data/data" xid="fileData"
      idColumn="filePath" autoNew="false"> 
      <column label="col0" name="filePath" type="String" xid="default1"/>  
      <column label="col1" name="fileName" type="String" xid="default2"/>  
      <column label="col2" name="createTime" type="String" xid="default3"/>
    </div>
  </div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full"> 
    <div class="x-panel-content tb-trans"> 
      <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full x-has-iosstatusbar"> 
        <div class="x-panel-top" xid="top1"> 
          <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar"
            xid="titleBar2" title="肉类蔬菜流通追溯管理平台"> 
            <div class="x-titlebar-title" xid="title1">肉类蔬菜流通追溯管理平台</div>  
            <div class="x-titlebar reverse" xid="right1"/>
          </div>
        </div>  
        <div class="x-panel-content" xid="content3" style="bottom: 0px;" _xid="C71915341BC00001236E1184B7409F90"> 
          <div component="$UI/system/components/justep/panel/panel" class="panel panel-default x-card"
            xid="panel1"> 
            <div component="$UI/system/components/bootstrap/carousel/carousel"
              class="x-carousel carousel" xid="carousel1" auto="true" style="width:100%;"> 
              <div class="x-contents carousel-inner" component="$UI/system/components/justep/contents/contents"
                active="0" slidable="true" xid="contentsImg" routable="false" role="listbox"> 
                <div class="x-contents-content" xid="content2"> 
                  <img src="" alt="" xid="image13" class="tb-img1" pagename=""/>
                </div> 
              </div> 
            </div>  
            <div class="container" xid="div1"> 
              <div component="$UI/system/components/bootstrap/row/row" class="row grid9"
                xid="row1"> 
                <div class="col cell col-xs-4 col-sm-3 col-md-2 col-lg-1" xid="col1"> 
                  <div class="card" xid="div2"> 
                    <a component="$UI/system/components/justep/button/button"
                      class="btn btn-link btn-icon-top" label="企业信息" xid="button5"
                      icon="icon-android-earth" onClick="openPage" url="./company/companyinfo.w"> 
                      <i xid="i16" class="icon-android-earth" style="color: rgb(252, 100, 0);"/>  
                      <span xid="span16">企业信息</span>
                    </a> 
                  </div> 
                </div>  
                <div class="col cell col-xs-4 col-sm-3 col-md-2 col-lg-1" xid="col2"> 
                  <div class="card" xid="div3"> 
                    <a component="$UI/system/components/justep/button/button"
                      class="btn btn-link btn-icon-top" label="资质信息" xid="button2"
                      icon="icon-android-camera" onClick="openPage" url="./company/qyzz.w"> 
                      <i xid="i3" class="icon-android-camera" style="color: #2fa4e7;"/>  
                      <span xid="span53">资质信息</span>
                    </a> 
                  </div> 
                </div>  
                <div class="col cell col-xs-4 col-sm-3 col-md-2 col-lg-1" xid="col3"> 
                  <div class="card" xid="div8"> 
                    <a component="$UI/system/components/justep/button/button"
                      class="btn btn-link btn-icon-top" onClick="openPage" label="产品种类"
                      xid="button7" icon="icon-grid" url="./protype/proTypeDetail.w"> 
                      <i xid="i8" class="icon-grid" style="color: rgb(202, 14, 222);"/>  
                      <span xid="span8">产品种类</span>
                    </a> 
                  </div> 
                </div>  
                <!-- <div class="col cell col-xs-4 col-sm-3 col-md-2 col-lg-1" xid="col4">
      <div class="card" xid="div9">
       <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-top" label="生产基地" xid="buttonScan" icon="icon-android-location" onClick="openPage" url="./protype/proTypeArea.w">
        <i xid="i14" class="icon-android-location"></i>
        <span xid="span14">生产基地</span></a> </div> </div> 
     <div class="col cell col-xs-4 col-sm-3 col-md-2 col-lg-1" xid="col5">
      <div class="card" xid="div10">
       <a component="$UI/system/components/justep/button/button" class="btn btn-link btn-icon-top" label="追溯标识" xid="button6" icon="icon-android-printer" onClick="openPage" url="./protype/dimennoList.w">
        <i xid="i7" class="icon-android-printer" style="color: black;"></i>
        <span xid="span7">追溯标识</span></a> </div> </div> -->  
                <div class="col cell col-xs-4 col-sm-3 col-md-2 col-lg-1" xid="col6"> 
                  <div class="card" xid="div11"> 
                    <a component="$UI/system/components/justep/button/button"
                      class="btn btn-link btn-icon-top" label="批次管理" xid="button2"
                      icon="icon-android-note" onClick="openPage" url="./protype/proTypeBatch.w"> 
                      <i xid="i2" class="icon-android-note" style="color: rgb(153, 196, 18);"/>  
                      <span xid="span2">批次管理</span>
                    </a> 
                  </div> 
                </div>  
                <!-- <div class="col cell col-xs-4 col-sm-3 col-md-2 col-lg-1" xid="col7"> 
                  <div class="card" xid="div12"> 
                    <a component="$UI/system/components/justep/button/button"
                      class="btn btn-link btn-icon-top" label="监管信息" xid="button4"
                      icon="icon-android-forums" onClick="openPage" url="./supervise/supervise.w"> 
                      <i xid="i15" class="icon-android-microphone icon-android-forums"
                        style="color: black;"/>  
                      <span xid="span15">监管信息</span>
                    </a> 
                  </div> 
                </div>   -->
                <div class="col cell col-xs-4 col-sm-3 col-md-2 col-lg-1" xid="col8"> 
                  <div class="card" xid="div13"> 
                    <a component="$UI/system/components/justep/button/button"
                      class="btn btn-link btn-icon-top" label="密码修改" xid="button11"
                      icon="icon-edit" onClick="openPage" url="./company/passwordedit.w"> 
                      <i xid="i5" class="icon-edit" style="color:#0080FF;"/>  
                      <span xid="span5">密码修改</span>
                    </a> 
                  </div> 
                </div>  
                <div class="col cell col-xs-4 col-sm-3 col-md-2 col-lg-1" xid="col13"> 
                  <div class="card" xid="div7"> 
                    <a component="$UI/system/components/justep/button/button"
                      class="btn btn-link btn-icon-top" label="退出系统" xid="button12"
                      icon="icon-arrow-right-a" onClick="exitApp"> 
                      <i xid="i6" class="icon-arrow-right-a" style="color:#FF8000;"/>  
                      <span xid="span6">退出系统</span>
                    </a> 
                  </div> 
                </div> 
              </div> 
            </div> 
          </div>
        </div> 
      </div>
    </div> 
  </div> 
</div>
