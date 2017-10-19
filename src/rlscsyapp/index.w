<?xml version="1.0" encoding="utf-8"?>

<div xmlns="http://www.w3.org/1999/xhtml" class="main13" component="$UI/system/components/justep/window/window"
  design="device:mobile;" xid="window">  
  <title>广州市肉类蔬菜流通追溯管理平台</title>
  <div component="$UI/system/components/justep/model/model" xid="model" style="left:18px;height:97px;top:52px;"
    onModelConstruct="modelModelConstruct" onLoad="modelLoad"> 
     
  <div component="$UI/system/components/justep/data/data" autoLoad="false" xid="ptqData" idColumn="ptqId" onCustomRefresh="ptqDataCustomRefresh" limit="10"><column label="ptqId" name="ptqId" type="String" xid="xid3"></column>
  <column label="typeImg" name="typeImg" type="String" xid="xid4"></column>
  <column label="regAddr" name="regAddr" type="String" xid="xid5"></column>
  <column label="typeName" name="typeName" type="String" xid="xid6"></column>
  <column label="dimenno" name="dimenno" type="String" xid="xid7"></column>
  <column label="companyName" name="companyName" type="String" xid="xid8"></column></div>
  <div component="$UI/system/components/justep/data/data" autoLoad="true" xid="imgData" idColumn="id" onCustomRefresh="imgDataCustomRefresh">
   <column label="id" name="id" type="String" xid="xid1"></column>
   <column label="图片" name="fImgUrl" type="String" xid="xid2"></column>
   <column label="链接地址" name="fUrl" type="String" xid="xid9"></column></div></div>  
  <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full"> 
    <div class="x-panel-content tb-trans"> 
      <div component="$UI/system/components/justep/panel/panel" class="x-panel x-full x-has-iosstatusbar"> 
              
            <div class="x-panel-top" xid="top1"> 
              <div component="$UI/system/components/justep/titleBar/titleBar" class="x-titlebar" xid="titleBar2" title="肉类蔬菜流通追溯管理平台">
   <div class="x-titlebar-title" xid="title1">肉类蔬菜流通追溯管理平台</div>
   <div class="x-titlebar reverse" xid="right1"><a component="$UI/system/components/justep/button/button" class="btn btn-link btn-only-icon user-btn" label="button" xid="button1" icon="linear linear-chevronrightcircle" onClick="button2Click">
   <i xid="i1" class="linear linear-chevronrightcircle"></i>
   <span xid="span1"></span></a></div></div></div>
            
            <div class="x-panel-content  x-scroll-view" xid="content3" style="bottom: 0px;"> 
              <div class="x-scroll" component="$UI/system/components/justep/scrollView/scrollView" xid="scrollView" onPullDown="scrollViewPullDown" onPullUp="scrollViewPullUp" autoPullUp="false"> 
                <div class="x-content-center x-pull-down container" style="display:none;" xid="div16"> 
                  <i class="x-pull-down-img glyphicon x-icon-pull-down" xid="i9" />  
                  <span class="x-pull-down-label" xid="span17">下拉刷新...</span> 
                </div>  
                <div class="x-scroll-content" xid="div17"> 
                  <div component="$UI/system/components/justep/panel/panel" class="panel panel-default x-card" xid="panel1"> 
                    <div component="$UI/system/components/bootstrap/carousel/carousel" class="x-carousel carousel" xid="carousel1" auto="false" style="height:133px;">
   <div class="x-contents carousel-inner" component="$UI/system/components/justep/contents/contents" active="0" slidable="true" xid="contentsImg" routable="false" role="listbox">
    <div class="x-contents-content" xid="content2"></div></div> 
  <img src="" alt="" xid="image13" pagename="" class="tb-img1"></img></div></div>  
                  <div component="$UI/system/components/justep/panel/panel" class="panel panel-default x-card" xid="panel5"> 
                    <!-- <h4 xid="h41" class="list-group-item text-black"><![CDATA[溯源产品]]></h4> -->
                    
                     <div class="media" xid="media1" style="padding-left:10px;">
   <div class="media-left" xid="mediaLeft1" style="width:100%;">
    <input component="$UI/system/components/justep/input/input" class="form-control" xid="searchtxt" style="width:100%;padding-left:3px;"></input></div> 
   <div class="media-body" xid="mediaBody1">
    <a component="$UI/system/components/justep/button/button" class="btn x-green btn-sm btn-only-icon" label="查询" xid="searchbutton" onClick="searchbuttonClick" icon="icon-search">
   <i xid="i5" class="icon-search"></i>
   <span xid="span5">查询</span></a></div> 
  <div class="media-right" xid="mediaRight1" style="padding-right:5px;">
   <a component="$UI/system/components/justep/button/button" class="btn x-green btn-xs btn-only-icon" label="扫一扫" xid="handbutton" onClick="handbuttonClick" icon="e-commerce e-commerce-saomiao">
   <i xid="i6" class="e-commerce e-commerce-saomiao"></i>
   <span xid="span6">扫一扫</span></a></div></div>
  </div> 
               
                  
         <div component="$UI/system/components/justep/list/list" class="x-list x-cards" xid="list1" limit="-1" data="ptqData" bind-click="listClick">
		   <ul class="x-list-template x-min-height list-group" xid="listTemplateUl1" componentname="$UI/system/components/justep/list/list#listTemplateUl" id="undefined_listTemplateUl1">
			    <li xid="li1" class="media x-min-height list-group-item" componentname="li(html)" id="undefined_li1">
					    <div class="x-blob x-blob-radius pull-left media-object x-news-image" xid="div2" style="width:30%;">
					   <img class="x-blob-img x-autofill" bind-attr-src="$model.getImageUrl($object)" xid="image3" style="width:100%;"></img></div>
					   <div class="media-body"> 
					     <h4 class="media-heading" bind-text="ref('typeName')"/>  
					     <div bind-text="ref('dimenno')"/>
					     <div bind-text="ref('companyName')"/> 
					     <!-- <div bind-text="ref('regAddr')"/>  -->
					   </div> 
			   </li>
		   </ul> 
		  </div>
   
   
   		</div>  
                <div class="x-content-center x-pull-up" xid="div18"> 
                  <span class="x-pull-up-label" xid="span18">加载更多...</span> 
                </div> 
              </div> 
            </div> 
          </div></div>  
    </div> 
<div component="$UI/system/components/justep/popMenu/popMenu" class="x-popMenu" xid="popMenu" direction="right-bottom" anchor="button1">
   <div class="x-popMenu-overlay" xid="div5"></div>
   <ul component="$UI/system/components/justep/menu/menu" class="x-menu dropdown-menu x-popMenu-content" xid="menu1">
    <li class="x-menu-item" xid="item1">
     <a component="$UI/system/components/justep/button/button" class="btn btn-link" label="企业用户登录" xid="button2" onClick="button2Click" icon="icon-android-contact">
      <i xid="i2" class="icon-android-contact"></i>
      <span xid="span2">企业用户登录</span></a> </li> 
      
    <li class="x-menu-divider divider" xid="divider1"></li>
    
    <li class="x-menu-item" xid="item2">
     <a component="$UI/system/components/justep/button/button" class="btn btn-link" label="监管用户登录" xid="button4" icon="icon-android-hand" onClick="button4Click">
      <i xid="i4" class="icon-ios7-contact icon-android-hand"></i>
      <span xid="span4">监管用户登录</span></a> </li> 
  <li class="x-menu-divider divider" xid="divider2"></li>
  <li class="x-menu-item" xid="item4">
   <a component="$UI/system/components/justep/button/button" class="btn btn-link" label="扫一扫" xid="button5" onClick="buttonScanClick" icon="icon-android-hand">
    <i xid="i7" class="icon-android-hand"></i>
    <span xid="span7">扫一扫</span></a> </li><li class="x-menu-divider divider" xid="divider3"></li><li class="x-menu-item" xid="item3">
   <a component="$UI/system/components/justep/button/button" class="btn btn-link" label="退出" xid="button3" onClick="button3Click" icon="icon-power">
    <i xid="i3" class="icon-power"></i>
    <span xid="span3">退出</span></a> </li>
  </ul> </div></div>