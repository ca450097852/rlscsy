<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-企业注册</title>

<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/complaint.css"/>

<script src="<%=basePath %>static/js/hontek/portalweb/jquery-1.8.3.min.js"　type="text/javascript"></script>


<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	

<script src="<%=basePath %>static/js/hontek/portalweb/register.js"　type="text/javascript"></script>

<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/validform.css"/>
<script type="text/javascript" src="<%=basePath %>static/js/hontek/portalweb/Validform_v5.3.js"></script>


</head>
  
<body>
<DIV>
<jsp:include page="head.jsp"><jsp:param value="jmqy" name="navckeckId"/></jsp:include>
<DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>企业注册:</font></div>
                <form name="entForm" id="entForm" class="entForm" method="post" action="mbent_addCompany2.action" enctype="multipart/form-data">
                	<!-- 隐藏域字段 -->
                	<input type="hidden" id="issubmit" value="0">
                	
                	<input type="hidden" name="enterprise.flag" value="3">
				    <input type="hidden" name="enterprise.integrityRecord" value=""> 
				    <input type="hidden" name="enterprise.isReported" value="0">
				   	<input type="hidden" name="enterprise.sysCode" value="086020">
				   	<input type="hidden" name="enterprise.sts" value="0">
				   	<input type="hidden" name="enterprise.seq" value="5">
				   	<input type="hidden" name="enterprise.townRsts" id="townRsts" value="">
				   	<input type="hidden" name="enterprise.provinceRsts" id="provinceRsts" value="">
				   	<!--用于保存企业名称和账号验证是否通过 -->
				   	<input type="hidden" id="name_yanzheng">
				   	<input type="hidden" id="account_yanzheng">
				   	<!-- 区域字段 -->
				   	<input type="hidden" name="enterprise.areaId" id="areaId">
				   	<!-- 所属行政机构字段 （选择区域时控制）-->
				   	<input type="hidden" name="enterprise.parentId" id="parentId" value="0">
				   	<!-- 档案类型  （选择分类时控制）-->
				   	<input type="hidden" name="enterprise.recordType" id="recordType" value="1">
				   	<div id="auditEnt">
				   	<!-- 指定审核机构 （选择区域时控制）-->
				   	</div>
	                <div class="complaint_content">
	                	<dl class="div_line01" style="hight:50px;">
	                        <dt style="text-align: right"><strong>行政区域</strong><font style="color: red">*</font></dt>
	                        <dd id="quyu">
							</dd>
							<dd>
	                        <div></div>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>企业名称</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        <input type="text" name="enterprise.name" id="name" style="width: 250px;"  class="inputxt" onchange="isNameOnlyone();" onblur="isNameOnlyone();" />
	                        </dd>
	                        <dd id="name_msg">
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>用户名</strong><font style="color: red">*</font></dt>
							<dd>
							<input type="text" name="enterprise.account" id="account" style="width: 250px;" class="inputxt" onchange="isAccountOnlyone();" onblur="isAccountOnlyone();"/>
							</dd>
							<dd  id="account_msg">
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                     <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>密码</strong><font style="color: red">*</font></dt>
							<dd>
							<input type="password" name="enterprise.password" id="password" style="width: 250px;"  class="inputxt" datatype="*6-15" errormsg="密码范围在6~15位之间！" nullmsg="请输入密码！" />
							</dd>
							<dd>
	                        <div></div>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>企业法人</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        <input type="text" name="enterprise.legalPerson" id="legalPerson" style="width: 250px;" class="inputxt" datatype="*" sucmsg="企业法人验证通过！" nullmsg="请输入企业法人！" />
	                        </dd>
	                        <dd>
	                        <div></div>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>企业类型</strong><font style="color: red">*</font></dt>
	                        <dd id="entType_html">
	                        <!-- <select name="enterprise.entType" id="entType_id"  style="width: 200px;">
								</select>
							 -->
							 
							</dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt id="orgCode_title"><strong>组织机构代码</strong><font style="color: red">*</font></dt>
	                        <dd id="orgCode_html"><input type="text" name="enterprise.orgCode" id="orgCode"  style="width: 250px;" class="inputxt" datatype="*" sucmsg="组织机构代码验证通过！" nullmsg="请输入组织机构代码！"/></dd>
	                        <dd id="orgCode_msg">
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>联系电话</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        <input type="text" name="enterprise.tel" id="tel" style="width: 250px;" maxlength="25" class="inputxt" datatype="*" sucmsg="联系电话验证通过！" nullmsg="请输入电话号码！"/>
	                        </dd>
	                        <dd>
	                        <div></div>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>注册地址</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        <input type="text" name="enterprise.regAddr" id="regAddr" style="width: 250px;"  class="inputxt" datatype="*"  sucmsg="注册地址验证通过！" nullmsg="请输入注册地址！"/>
	                        </dd>
	                        <dd>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>经营地址</strong><font style="color: red">*</font></dt>
	                        <dd><input type="text" name="enterprise.manageAddr" id="manageAddr" style="width: 250px;" class="inputxt"  datatype="*"  sucmsg="经营地址验证通过！" nullmsg="请输入经营地址！"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>手机号码</strong><font style="color: red">*</font></dt>
	                        <dd><input type="text" name="enterprise.mobile" style="width: 250px;" id="mobile" maxlength="25" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="inputxt" datatype="*"  sucmsg="手机号码验证通过！" nullmsg="请输入手机号码！"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>企业网址</strong></dt>
	                        <dd><input type="text" name="enterprise.domName" id="domName" style="width: 250px;" class="inputxt"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>电子邮箱</strong></dt>
	                        <dd><input type="text" name="enterprise.email" id="email" style="width: 250px;" class="inputxt"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    
	                    <dl class="div_line02">
	                        <dt style="height: 80px;line-height: 80px;"><strong>企业简介</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        <textarea name="enterprise.intro" id="intro" cols="20" rows="" style="width: 550px;height: 75px;font-size: 12px;" tip="请输入500字内的简介信息！" datatype="s" altercss="gray" class="gray" onblur="checkIntro();">请输入500字内的简介信息！ </textarea>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    
	                     <dl class="div_line02">
	                        <dt style="height: 95px;line-height: 95px;"><strong>服务协议</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        	<textarea  style="width: 550px;height: 80px;font-size: 12px;">1、广东省农产品质量安全追溯网服务条款的确认和接纳
　　广东省农产品质量安全追溯网的各项服务的所有归属广东省农业厅，由广州薪火网络科技有限公司运作。广东省农产品质量安全追溯网提供的服务将完全按照其发布的章程、服务条款和操作规则严格执行。用户必须完全同意所有服务条款并完成注册程序，才能成为广东省农产品质量安全追溯网的正式用户。 

2、服务简介
　　广东省农产品质量安全追溯网运用自己的操作系统通过国际互联网络为用户提供网络服务。同时，用户必须：
　　(1)自行配备上网的所需设备， 包括个人电脑、调制解调器或其他必备上网装置。
(2)自行负担个人上网所支付的与此服务有关的电话费用、 网络费用。
　　为提高广东省农产品质量安全追溯网信息服务的准确性，用户应同意：
　　(1)提供详尽、准确的公司或个人资料。
(2)不断更新注册资料，符合及时、详尽、准确的要求。
(3)默认开通农博士业务，信息费5元/月，由当地运营商代收。
　　广东省农产品质量安全追溯网不公开用户的电子邮箱和笔名，除以下情况外：
　　(1)用户授权广东省农产品质量安全追溯网透露这些信息。
　　(2)相应的法律及程序要求广东省农产品质量安全追溯网提供用户的个人资料。
　　如果用户提供的资料包含有不合法的信息，广东省农产品质量安全追溯网保留结束用户使用网络服务资格的权利。

3、服务条款的修改和服务修订
　　广东省农产品质量安全追溯网有权在必要时修改服务条款，广东省农产品质量安全追溯网服务条款一旦发生变动，将会在重要页面上提示修改内容。如果不同意所改动的内容，用户可以主动取消获得的网络服务。如果用户继续享用网络服务，则视为接受服务条款的变动。广东省农产品质量安全追溯网保留随时修改或中断服务而不需知照用户的权利。广东省农产品质量安全追溯网行使修改或中断服务的权利，不需对用户或第三方负责。

4、用户隐私制度
　　尊重用户个人隐私是广东省农产品质量安全追溯网的一项基本政策。所以，作为对以上个人注册资料分析的补充，广东省农产品质量安全追溯网保证不在未经合法用户授权时公开、编辑或透露其注册资料及保存在广东省农产品质量安全追溯网中的非公开内容，除非有法律许可要求或广东省农产品质量安全追溯网在诚信的基础上认为透露这些信件在以下四种情况是必要的：
　　(1)遵守有关法律规定，遵从广东省农产品质量安全追溯网合法服务程序。
　　(2)保持维护广东省农产品质量安全追溯网的商标所有权。
　　(3)在紧急情况下竭力维护用户个人和社会大众的隐私安全。
　　(4)符合其他相关的要求。

5、用户的帐号，密码和安全性
　　用户一旦注册成功，成为广东省农产品质量安全追溯网的合法用户，将得到一个密码和用户名。
　　用户将对用户名和密码安全负全部责任。另外，每个用户都要对以其用户名进行的所有活动和事件负全责。您可随时根据指示改变您的密码。
　　用户若发现任何非法使用用户帐号或存在安全漏洞的情况，请立即通告广东省农产品质量安全追溯网。
6、拒绝提供担保 
　　用户对网络服务的使用承担风险。广东省农产品质量安全追溯网对此不作任何类型的担保，不论是明确的或隐含的。广东省农产品质量安全追溯网不担保服务一定能满足用户的要求，也不担保服务不会受中断，对服务的及时性，安全性，出错发生都不作担保。广东省农产品质量安全追溯网对在广东省农产品质量安全追溯网上得到的任何信息不作担保。

7、有限责任
　　广东省农产品质量安全追溯网对任何直接、间接、偶然、特殊及继起的损害不负责任，这些损害可能来自：不正当使用网络服务，根据网上信息进行交易而造成损失，非法使用网络服务或发布虚假信息。这些行为都有可能会导致广东省农产品质量安全追溯网的形象受损，所以广东省农产品质量安全追溯网事先提出这种损害的可能性。

8、对用户信息的存储和限制 
　　广东省农产品质量安全追溯网不对用户所发布信息的删除或储存失败负责。广东省农产品质量安全追溯网有判定用户的行为是否符合广东省农产品质量安全追溯网服务条款的要求和精神的保留权利，如果用户违背了服务条款的规定，广东省农产品质量安全追溯网有中断对其提供网络服务的权利。

9、用户管理
　　用户单独承担发布内容的责任。用户对服务的使用是根据所有适用于广东省农产品质量安全追溯网的国家法律、地方法律和国际法律标准的。
　　用户必须遵循：
　　(1)从中国境内向外传输技术性资料时必须符合中国有关法规。
　　(2)使用网络服务不作非法用途。
　　(3)不干扰或混乱网络服务。
　　(4)遵守所有使用网络服务的网络协议、规定、程序和惯例。
　　用户须承诺不传输任何非法的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的，淫秽等信息资料。另外，用户也不能传输任何教唆他人构成犯罪行为的资料；不能传输助长国内不利条件和涉及国家安全的资料；不能传输任何不符合当地法规、国家法律和国际法律的资料。未经许可而非法进入其它电脑系统是禁止的。若用户的行为不符合以上提到的服务条款，广东省农产品质量安全追溯网将作出独立判断立即取消用户服务帐号。用户需对自己在网上的行为承担法律责任。用户若在广东省农产品质量安全追溯网上散布和传播反动、色情或其他违反国家法律的信息，广东省农产品质量安全追溯网的系统记录有可能作为用户违反法律的证据。

10、保障
　　用户同意保障和维护广东省农产品质量安全追溯网全体成员的利益，负责支付由用户使用超出服务范围引起的律师费用，违反服务条款的损害补偿费用等。

11、结束服务
　　用户或广东省农产品质量安全追溯网可随时根据实际情况中断一项或多项网络服务。广东省农产品质量安全追溯网不需对任何个人或第三方负责而随时中断服务。用户对后来的条款修改有异议，或对广东省农产品质量安全追溯网的服务不满，可以行使如下权利：
　　(1)停止使用广东省农产品质量安全追溯网的网络服务。
　　(2)通告广东省农产品质量安全追溯网停止对该用户的服务。
　　结束用户服务后，用户使用网络服务的权利马上终止。从那时起，用户没有权利，广东省农产品质量安全追溯网也没有义务传送任何未处理的信息或未完成的服务给用户或第三方。

12、免责声明
　　用户可以在广东省农产品质量安全追溯网上发布企业及追溯相关信息，对于这些信息的真实性广东省农产品质量安全追溯网不承担任何责任。

13、网络服务内容的所有权
　　广东省农产品质量安全追溯网定义的网络服务内容包括：文字、软件、声音、图片、录象、图表、广告中的全部内容；电子邮件的全部内容；广东省农产品质量安全追溯网为用户提供的其他信息。所有这些内容受版权、商标、标签和其它财产所有权法律的保护。所以，用户只能在广东省农产品质量安全追溯网和广告商授权下才能使用这些内容，而不能擅自复制、再造这些内容、或创造与内容有关的派生产品。广东省农产品质量安全追溯网所有的文章版权归原文作者和广东省农产品质量安全追溯网共同所有，任何人需要转载广东省农产品质量安全追溯网的文章，必须征得原文作者或广东省农产品质量安全追溯网授权。

14、法律
网络服务条款要与中华人民共和国的法律解释相一致，用户和广东省农产品质量安全追溯网一致同意服从高等法院所有管辖。如发生广东省农产品质量安全追溯网服务条款与中华人民共和国法律相抵触时，则这些条款将完全按法律规定重新解释，而其它条款则依旧保持对用户产生法律效力和影响。
	                        	 </textarea>
	                        	 
	                        	 	                        <input id="xieyi" type="checkbox"/>我已阅读并同意以上服务协议！注：必须同意协议才能提交注册信息！
	                        	 
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                                          	
	                    
	                    
	                    
	                    <div class="div_btn">
	                        <span class="btn" id="subForm" style="cursor: pointer;display: none;" onclick="formsubmit()">提交</span>
	                        <span class="btn" id="clearform"  style="cursor: pointer">重置</span>
	                    </div>
                </div>
                </form>
            </div>
            
            
            
            <div class="CENTER_content_right">
            <!-- 右边列表 -->
            <jsp:include page="news_info.jsp"></jsp:include>
            
            </div>
        </div>
        <div class="clear"></div>
    </DIV>
    <div class="clear" style="height: 30px;"></div>
 <jsp:include page="bottom.jsp"></jsp:include>
 </DIV>
</body>
</html>
