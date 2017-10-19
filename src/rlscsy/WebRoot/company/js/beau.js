function ShowAddForm(n) {
    n == 0 ? ($("#gd_add_form").hide(), $("#pd_add_form").toggle()) : ($("#pd_add_form").hide(), $("#gd_add_form").toggle())
}

function GetCurrentTime() {
    var n, u, t, i, r;
    n = new Date;
    u = n.getMonth() + 1;
    t = n.getHours();
    i = n.getMinutes();
    r = n.getSeconds();
    t < 10 && (t = "0" + t);
    i < 10 && (i = "0" + i);
    r < 10 && (r = "0" + r);
    n = "时间：" + n.getFullYear() + "/" + u + "/" + n.getDate() + " " + t + ":" + i + ":" + r;
    $("#timer").html(n)
}
function SubmitSuccess(n) {
    if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
        $("#myModal").hide();
    })
}
function SubmitFailure(n){
    var i = decodeURIComponent(n.getResponseHeader('X-Error-Message'));
    if (CreateModal('错误提示', i), true) $("#myModal").on("hide.bs.modal", function () {
        $("#myModal").hide();
    })
}
function DeleteSuccess(n) {
    if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
        location.reload();
    })
}
function SystemMsg(n, t, i, r) {
    if (CreateModal(t, i), n == !0) $("#myModal").on("hide.bs.modal", function () {
        location.href = r
    })
}
function CreateModal(n, t) {
    if ($("#myModal").length > 0) $("#myModalLabel").html(n), $("#myModalbody").html(t), $("#myModal").modal({
        backdrop: 'static',keyboard: !0
    });
    else {
        var i = '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">×<\/button><h4 class="modal-title" id="myModalLabel"> ' + n + '<\/h4><\/div><div class="modal-body" id="myModalbody">' + t + '<\/div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">关闭<\/button><\/div><\/div><\/div><\/div>';
        $("body").append(i);
        $("#myModal").modal({
            backdrop: 'static',keyboard: !0
        })
    }
}
function ShowMatchSell(n, t) {
    var i = layer.load();
    $("#data-lists .match-conn").remove();
    $.ajax({
        type: "post",
        url: "index.php?r=gml",
        data: {
            sn: t
        },
        dataType: "html",
        success: function (t) {
            $("#data-lists .match-conn").remove();
            $(n).parent().parent().after(t);
			$(".countdownbox").each(function(){
				$(this).countdown();
			});
            layer.close(i)
        }
    })
}
function ShowMatchBuy(n, t) {
    var i = layer.load();
    $("#data-lists .match-conn").remove();
    $.ajax({
        type: "post",
        url: "index.php?r=pml",
        data: {
            sn: t
        },
        dataType: "html",
        success: function (t) {
            $("#data-lists .match-conn").remove();
            $(n).parent().parent().after(t);
			$(".countdownbox").each(function(){
				$(this).countdown();
			});
            layer.close(i)
        }
    })
}
function GetPayInfo(n) {
    $.ajax({
        type: "post",
        url: "/index.php?r=trt&sn="+n,
        dataType: "json",
        success: function (n) {
            n != null && CreateModal("联系方式", "<span class='user-info'>" + n.html + "<span>")
        }
    })
}
function GetPayerInfo(n) {
    $.ajax({
        type: "post",
        url: "/index.php?r=tpi&sn="+n,
        dataType: "json",
        success: function (n) {
            n != null && CreateModal("联系方式", "<span class='user-info'>" + n.html + "<span>")
        }
    })
}
function GetStockDeal(n) {
    $.ajax({
        type: "post",
        url: "/index.php?r=ssd&id="+n,
        dataType: "json",
        success: function (n) {
            n != null && CreateModal("成交记录", "<div class='stock-deal'>" + n.html + "<div>")
        }
    })
}
function CancelBuy(n) {
    confirm("您确定要取消该订单吗？") && $.ajax({
        type: "post",
        url: "/index.php?r=pcl",
        data: {
            sn: n
        },
        dataType: "json",
         error:function (n) {
            SubmitFailure(n)
        },
        success: function (n) {
                if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
       location.reload();
    })}
    })
}
function CancelTrade(n) {
    confirm("您确定要取消打款吗？") && $.ajax({
        type: "post",
        url: "/index.php?r=tct",
        data: {
            sn: n
        },
        dataType: "json",
         error:function (n) {
            SubmitFailure(n)
        },
        success: function (n) {
                if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
       location.reload();
    })}
    })
}
function CancelStockTrade(n) {
    confirm("您确定要取消该订单吗？") && $.ajax({
        type: "post",
        url: "/index.php?r=sct",
        data: {
            id: n
        },
        dataType: "json",
         error:function (n) {
            SubmitFailure(n)
        },
        success: function (n) {
                if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
       location.reload();
    })}
    })
}
function DeleteUser(n) {
    confirm("您确定要删除该用户吗？") && $.ajax({
        type: "post",
        url: "/index.php?r=bdu",
        data: {
            username: n
        },
        dataType: "json",
         error:function (n) {
            SubmitFailure(n)
        },
        success: function (n) {
                if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
       location.reload();
    })}
    })
}
function DeleteLetter(n) {
    confirm("您确定要删除该信息吗？") && $.ajax({
        type: "post",
        url: "/index.php?r=ldl&id="+n,
        dataType: "json",
        success: function (n) {
            DeleteSuccess(n)
        },
        error:function (n) {
            SubmitFailure(n)
        }
    })
}
function PaySuccess(n) {
    confirm("您确认您已收到此笔款项吗？") && $.ajax({
        type: "post",
        url: "/index.php?r=tps&sn="+n,
        dataType: "json",
        error:function (n) {
            SubmitFailure(n)
        },
        success: function (n) {
            if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
       location.reload();
    })}
    })
}
function Complaint(n) {
    var i = layer.open({
        type: 2,
        title: "我要投诉",
        content: ["index.php?r=tcn&sn=" + n, "no"]
    });
    layer.full(i)
}
function ConfirmPay(n) {
    var i = layer.open({
        type: 2,
        title: "确认打款",
        content: ["index.php?r=tcm&sn=" + n, "no"]
    });
    layer.full(i)
}
function NoteUser(n) {
    layer.open({
        type: 2,
        title: "备注信息",
        content: ["index.php?r=bmn&id=" + n, "no"]
    });
}
function AjaxUploadSubmit() {
    var n = {
		dataType: "json",
        error: function (n) {
            SubmitFailure(n)
        },
        success: function (n) {
            SubmitSuccess(n)
        }
    };
    $("#LetterAdd").ajaxForm(n)
}
function AjaxUploadSubmit2() {
    var n = {
		dataType: "json",
        error: function (n) {
            SubmitFailure(n)
        },
        success: function (n) {
                if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
       location.href='index.php?r=lsl';
    })

        }
    };
    $("#LetterAdd").ajaxForm(n)
}
function AjaxComplaintSubmit() {
    var t = parent.layer.getFrameIndex(window.name),
        n = {
			dataType: "json",
            error: function (n) {
            SubmitFailure(n);
        },
            success: function (n) {
                alert(n);
                parent.location.reload();
            }
        };
    $("#ComplaintAdd").ajaxForm(n)
}
function AjaxConfirmPaySubmit() {
    var t = parent.layer.getFrameIndex(window.name),
        n = {
			dataType: "json",
            error: function (n) {
            SubmitFailure(n);
        },
            success: function (n) {
                alert(n);
                parent.location.reload();
            }
        };
    $("#ConfirmPay").ajaxForm(n)
}
function SearchOrderID(n) {
    layer.open({
        type: 2,
        title: "排位列表",
		area: ['600px', '465px'],
        content: "index.php?r=sso&id=" + n
    });
}
function GetPhoneCode(n, t) {
    var r = $("#Phone").val(),
        i;
    /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(r) ? (i = "", t == 1 && (i = "/index.php?r=usc&phone="+r), t == 2 && (i = "/index.php?r=usc&phone="+r), i.length > 10 && $.ajax({
        type: "post",
        url: i,
        dataType: "json",
        error: function (t) {
            SubmitFailure(t);
        },
        success: function (t) {
			countdown=t.timespan;
            settime(n);
			alert(t.msg)
        }
    })) : (alert("请输入有效的手机号码！"), $("#Phone").focus())
}
function PhoneBindSuccess(n) {
    if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
         location.href = "index.php?r=smf";
    })
}
function settime(n) {
    countdown == 0 ? ($(n).attr("disabled",false), $(n).html("获取验证码"), countdown = 300) : ($(n).attr("disabled", true), $(n).html("重新获取(" + countdown + ")"), countdown--, setTimeout(function () {
        settime(n)
    }, 1e3))
}
function GetRecomUserList(n, t) {
    var i = $(n).parent().nextAll("[role='" + t + "']");
    i.html() == undefined && ($(n).append("（加载中...）"), $.ajax({
        type: "post",
        url: "/index.php?r=bgm",
        data: {
            username: t
        },
        dataType: "html",
        success: function (t) {
            $(n).parent().after(t);
            $(n).parent().attr("class", "o");
            $(n).html($(n).html().replace("（加载中...）", ""))
        }
    }));
    $(n).parent().attr("class", "c");
    $(n).parent().nextAll("[role='" + t + "']").remove()
}
$(document).ready(function () {
    setInterval(GetCurrentTime, 1e3);
});
function RegSuccess(n) {
    if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
        location.href="index.php?r=uln";
    })
}
function ActiveSuccess(n) {
    if (CreateModal('系统提示', n), true) $("#myModal").on("hide.bs.modal", function () {
        location.href="index.php?r=btm";
    })
}
function SubmitRedirect(n) {
    if (CreateModal('系统提示', n.data), true) $("#myModal").on("hide.bs.modal", function () {
        location.href=n.url;
    })
}

var countdown = 300
//ScrollUp
$(function () {
  $.scrollUp({
	scrollName: 'scrollUp', // Element ID
	topDistance: '0', // Distance from top before showing element (px)
	topSpeed: 300, // Speed back to top (ms)
	animation: 'fade', // Fade, slide, none
	animationInSpeed: 400, // Animation in speed (ms)
	animationOutSpeed: 400, // Animation out speed (ms)
	scrollText: 'Top', // Text for element
	activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
  });
});