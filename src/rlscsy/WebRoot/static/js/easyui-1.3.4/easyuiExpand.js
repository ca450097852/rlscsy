
//Datagrid默认视图修改 - 支持formatter返回组件 - 支持二级filed
//本js使用方法，只需要在easyui.min.js之后导入便可。基于1.3.3的版本修改。没有测试向前的兼容性。
$.extend($.fn.datagrid.defaults.view, {
    render: function (target, container, frozen) {
        var state = $.data(target, "datagrid");
        var opts = state.options;
        var rows = state.data.rows;
        var fields = $(target).datagrid("getColumnFields", frozen);
        if (frozen) {
            if (!(opts.rownumbers || (opts.frozenColumns && opts.frozenColumns.length))) {
                return;
            }
        }
        var table = ["<table class=\"datagrid-btable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>"];
        for (var i = 0; i < rows.length; i++) {
            var cls = (i % 2 && opts.striped) ? "class=\"datagrid-row datagrid-row-alt\"" : "class=\"datagrid-row\"";
            var styleValue = opts.rowStyler ? opts.rowStyler.call(target, i, rows[i]) : "";
            var style = styleValue ? "style=\"" + styleValue + "\"" : "";
            var rowId = state.rowIdPrefix + "-" + (frozen ? 1 : 2) + "-" + i;
            table.push("<tr id=\"" + rowId + "\" datagrid-row-index=\"" + i + "\" " + cls + " " + style + ">");
            table.push(this.renderRow.call(this, target, fields, frozen, i, rows[i]));
            table.push("</tr>");
        }
        table.push("</tbody></table>");
        $(container).html(table.join(""));
        //增加此句以实现,formatter里面可以返回easyui的组件，以便实例化。例如：formatter:function(){ return "<a href='javascript:void(0)' class='easyui-linkbutton'>按钮</a>" }}
        $.parser.parse(container);
    },
    renderRow: function (target, fields, frozen, rowIndex, rowData) {
        var opts = $.data(target, "datagrid").options;
        var cc = [];
        if (frozen && opts.rownumbers) {
            var rownumber = rowIndex + 1;
            if (opts.pagination) {
                rownumber += (opts.pageNumber - 1) * opts.pageSize;
            }
            cc.push("<td class=\"datagrid-td-rownumber\"><div class=\"datagrid-cell-rownumber\">" + rownumber + "</div></td>");
        }
        for (var i = 0; i < fields.length; i++) {
            var field = fields[i];
            var col = $(target).datagrid("getColumnOption", field);
            if (col) {
                //修改默认的value取值，改了此句之后field就可以用关联对象了。如：people.name
                var value = jQuery.proxy(function () { try { return eval('this.' + field); } catch (e) { return ""; } }, rowData)();
                var styleValue = col.styler ? (col.styler(value, rowData, rowIndex) || "") : "";
                var style = col.hidden ? "style=\"display:none;" + styleValue + "\"" : (styleValue ? "style=\"" + styleValue + "\"" : "");
                cc.push("<td field=\"" + field + "\" " + style + ">");
                if (col.checkbox) {
                    var style = "";
                } else {
                    var style = styleValue;
                    if (col.align) {
                        style += ";text-align:" + col.align + ";";
                    }
                    if (!opts.nowrap) {
                        style += ";white-space:normal;height:auto;";
                    } else {
                        if (opts.autoRowHeight) {
                            style += ";height:auto;";
                        }
                    }
                }
                cc.push("<div style=\"" + style + "\" ");
                if (col.checkbox) {
                    cc.push("class=\"datagrid-cell-check ");
                } else {
                    cc.push("class=\"datagrid-cell " + col.cellClass);
                }
                cc.push("\">");
                if (col.checkbox) {
                    cc.push("<input type=\"checkbox\" name=\"" + field + "\" value=\"" + (value != undefined ? value : "") + "\"/>");
                } else {
                    if (col.formatter) {
                        cc.push(col.formatter(value, rowData, rowIndex));
                    } else {
                        cc.push(value);
                    }
                }
                cc.push("</div>");
                cc.push("</td>");
            }
        }
        return cc.join("");
    }
});

//扩展Panel 设置 icon 方法
$.extend($.fn.panel.methods, {
    setIcon: function (jq, icon) {
        return jq.each(function () {
            $.data(this, "panel").options.iconCls = icon;
            var iconHtml = "<div class=\"panel-icon " + icon + "\"></div>";
            $(this).panel("header").find("div.panel-title").addClass("panel-with-icon");

            var iconClass = $(this).panel("header").find("div.panel-icon");
            if (iconClass) {
                iconClass.remove();
            }
            $(this).panel("header").find("div.panel-title").after(iconHtml);
        });
    }
});

(function ($) {

    function guidDialogId() {
        var s4 = function () {
            return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        };
        return "ZhouZhou-" + (s4() + s4() + "-" + s4() + "-" + s4() + "-" + s4() + "-" + s4() + s4() + s4());
    }

    $.hDialog = function (options) {
        options = $.extend({}, $.hDialog.defaults, options || {});

        var dialogId = guidDialogId();
        if (options.id)
            dialogId = options.id;

        //if (!options.href && !options.content) {
        //    alert('缺少必要的参数 href or content');
        //    return false;
        //}

        var defaultBtn = [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: options.submit
        }, {
            text: '关闭',
            iconCls: 'icon-cancel',
            handler: function () {
                $("#" + dialogId).dialog("close");
            }
        }];

        if (!options.showBtns)
            defaultBtn = [];

        if (options.buttons.length == 0)
            options.buttons = defaultBtn;

        if (options.max) {
            //dialog.dialog('maximize');
            var winWidth = $(window).width();
            var winHeight = $(window).height();
            options.width = winWidth - 20;
            options.height = winHeight - 20;
        }


        var $dialog = $('<div/>').css('padding', options.boxPadding).appendTo($('body'));

        var dialog = $dialog.dialog($.extend(options, {
            onClose: function () {
                dialog.dialog('destroy');
            }
        })).attr('id', dialogId);
        //.dialog('refresh').dialog('open')

        $dialog.find('.dialog-button').css('text-align', options.align);

        return dialog;
    };

    $.hDialog.defaults = $.extend({}, $.fn.dialog.defaults, {
        boxPadding: '3px',
        align: 'right', //按钮对齐方式
        href: '',
        id: '',
        content: '',
        height: 200,
        width: 400,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        closable: true,
        modal: true,
        shadow: false,
        mask: true,
        cache: false,
        closed: false,//默认是否关闭窗口 如果为true,需调用open方法打开
        showBtns: true,
        buttons: [],
        submit: function () {
            alert('写入可执行代码');
            return false;
        },
        onBeforeClose: function () {
            $(this).find(".combo-f").each(function () {
                var panel = $(this).data().combo.panel;
                panel.panel("destroy");
            });
            $(this).empty();
        },
        onMove: function (left, right) {
            $('.validatebox-tip').remove();
        }

    });

    ///////////////////////////////////////////////////////////////////////////////////////////////

    $.hWindow = function (options) {
        var windowId = guidDialogId();

        options = $.extend({}, $.hDialog.defaults, options || {});
        if (!options.href && !options.content) {
            alert('缺少必要的参数 href or content');
            return false;
        }

        var $dialog = $('<div/>').attr('id', windowId).appendTo($('body'));

        if (options.max) {
            //dialog.dialog('maximize');
            var winWidth = $(window).width();
            var winHeight = $(window).height();
            options.width = winWidth - 20;
            options.height = winHeight - 20;
        }

        var win = $dialog.window($.extend(options, {
            onClose: function () {
                win.window('destroy');
            }
        })).window('refresh').attr('id', windowId);


        return win;
    };

    $.hWindow.defaults = $.extend({}, $.fn.window.defaults, {
        href: '',
        content: '',
        height: 300,
        width: 400,
        iconCls: '',
        collapsible: false, 	//折叠
        closable: true,         //显示右上角关闭按钮
        minimizable: false, 	//最小化
        maximizable: false, 	//最大化
        resizable: false, 	    //是否允许改变窗口大小
        title: '窗口标题', 	    //窗口标题
        modal: true, 		    //模态	
        draggable: true,        //允许拖动
        max: false,
        onBeforeClose: function () {
            $(this).find(".combo-f").each(function () {
                var panel = $(this).data().combo.panel;
                alert(panel.html());
                panel.panel("destroy");
            });
            $(this).empty();
        }
    });


    ///////////////////////////////////////////////////////////////////////////////////////////////

    //扩展datagrid 方法 getSelectedIndex
    $.extend($.fn.datagrid.methods, {
        getSelectedIndex: function (jq) {
            var row = $(jq).datagrid('getSelected');
            if (row)
                return $(jq).datagrid('getRowIndex', row);
            else
                return -1;
        },
        checkRows: function (jq, idValues) {
            if (idValues && idValues.length > 0) {
                var rows = $(jq).datagrid('getRows');
                var keyFild = $(jq).datagrid('options').idField;
                $.each(rows, function (i, n) {
                    if ($.inArray(n[keyFild], idValues)) {
                        $(jq).datagrid('checkRow', row);
                    }
                })
            }
            return jq;
        }
    });
    //扩展 combobox 方法 selectedIndex
    $.extend($.fn.combobox.methods, {
        selectedIndex: function (jq, index) {
            if (!index)
                index = 0;
            var data = $(jq).combobox('options').data;
            var vf = $(jq).combobox('options').valueField;
            $(jq).combobox('setValue', eval('data[index].' + vf));
        }
    });

    //释放IFRAME内存
    $.fn.panel.defaults = $.extend({}, $.fn.panel.defaults, {
        onBeforeDestroy: function () {
            var frame = $('iframe', this);
            if (frame.length > 0) {
                frame[0].contentWindow.document.write('');
                frame[0].contentWindow.close();
                frame.remove();
                if ($.browser.msie) {
                    CollectGarbage();
                }
            }
        }
    });

    //tree 方法扩展 全选、取消全选
    $.extend($.fn.tree.methods, {
        checkedAll: function (jq, target) {
            var data = $(jq).tree('getChildren');
            if (target)
                data = $(jq).tree('getChildren', target);

            $.each(data, function (i, n) {
                $(jq).tree('check', n.target);
            });
        }
    });

    $.extend($.fn.tree.methods, {
        uncheckedAll: function (jq) {
            var data = $(jq).tree('getChildren');
            $.each(data, function (i, n) {
                $(jq).tree('uncheck', n.target);
            });
        }
    });

})(jQuery)
/**  
 * @author {CaoGuangHui}  
 */
$.extend($.fn.tabs.methods, {
    /**
     * 加载iframe内容  
     * @param  {jq Object} jq     [description]  
     * @param  {Object} params    params.which:tab的标题或者index;params.iframe:iframe的相关参数  
     * @return {jq Object}        [description]  
     */
    loadTabIframe: function (jq, params) {
        return jq.each(function () {
            var $tab = $(this).tabs('getTab', params.which);
            if ($tab == null) return;

            var $tabBody = $tab.panel('body');

            //销毁已有的iframe   
            var $frame = $('iframe', $tabBody);
            if ($frame.length > 0) {
                try {//跨域会拒绝访问，这里处理掉该异常   
                    $frame[0].contentWindow.document.write('');
                    $frame[0].contentWindow.close();
                } catch (e) {
                    //Do nothing   
                }
                $frame.remove();
                if ($.browser.msie) {
                    CollectGarbage();
                }
            }
            $tabBody.html('');

            $tabBody.css({ 'overflow': 'hidden', 'position': 'relative' });
            var $mask = $('<div style="position:absolute;z-index:2;width:100%;height:100%;background:#ccc;z-index:1000;opacity:0.3;filter:alpha(opacity=30);"><div>').appendTo($tabBody);
            var $maskMessage = $('<div class="mask-message" style="z-index:3;width:auto;height:16px;line-height:16px;position:absolute;top:50%;left:50%;margin-top:-20px;margin-left:-92px;border:2px solid #d4d4d4;padding: 12px 5px 10px 30px;background: #ffffff url(\'/js/themes/default/images/loading.gif\') no-repeat scroll 5px center; text-align:\'center\';">' + (params.iframe.message || '加载中,请稍等 ...') + '</div>').appendTo($tabBody);
            var $containterMask = $('<div style="position:absolute;width:100%;height:100%;z-index:1;background:#fff;"></div>').appendTo($tabBody);
            var $containter = $('<div style="position:absolute;width:100%;height:100%;z-index:0;"></div>').appendTo($tabBody);

            var iframe = document.createElement("iframe");
            iframe.src = params.iframe.src;
            iframe.frameBorder = params.iframe.frameBorder || 0;
            iframe.height = params.iframe.height || '100%';
            iframe.width = params.iframe.width || '100%';
            if (iframe.attachEvent) {
                iframe.attachEvent("onload", function () {
                    $([$mask[0], $maskMessage[0]]).fadeOut(params.iframe.delay || 'slow', function () {
                        $(this).remove();
                        if ($(this).hasClass('mask-message')) {
                            $containterMask.fadeOut(params.iframe.delay || 'slow', function () {
                                $(this).remove();
                            });
                        }
                    });
                });
            } else {
                iframe.onload = function () {
                    $([$mask[0], $maskMessage[0]]).fadeOut(params.iframe.delay || 'slow', function () {
                        $(this).remove();
                        if ($(this).hasClass('mask-message')) {
                            $containterMask.fadeOut(params.iframe.delay || 'slow', function () {
                                $(this).remove();
                            });
                        }
                    });
                };
            }
            $containter[0].appendChild(iframe);
        });
    },
    /**
     * 增加iframe模式的标签页  
     * @param {[type]} jq     [description]  
     * @param {[type]} params [description]  
     */
    addIframeTab: function (jq, params) {
        return jq.each(function () {
            if (params.tab.href) {
                delete params.tab.href;
            }
            $(this).tabs('add', params.tab);
            $(this).tabs('loadTabIframe', { 'which': params.tab.title, 'iframe': params.iframe });
        });
    },
    /**
     * 更新tab的iframe内容  
     * @param  {jq Object} jq     [description]  
     * @param  {Object} params [description]  
     * @return {jq Object}        [description]  
     */
    updateIframeTab: function (jq, params) {
        return jq.each(function () {
            params.iframe = params.iframe || {};
            if (!params.iframe.src) {
                var $tab = $(this).tabs('getTab', params.which);
                if ($tab == null) return;
                var $tabBody = $tab.panel('body');
                var $iframe = $tabBody.find('iframe');
                if ($iframe.length === 0) return;
                $.extend(params.iframe, { 'src': $iframe.attr('src') });
            }
            $(this).tabs('loadTabIframe', params);
        });
    }
});

$.extend($.fn.combo.methods, {
    /**
     * 激活点击文本框也显示下拉面板的功能  
     * @param {Object} jq  
     */
    activeTextArrow: function (jq) {
        return jq.each(function () {
            var textbox = $(this).combo("textbox");
            var that = this;
            var panel = $(this).combo("panel");
            textbox.bind('click.mycombo', function () {
                if (panel.is(":visible")) {
                    $(that).combo('hidePanel');
                } else {
                    $("div.combo-panel").panel("close");
                    $(that).combo('showPanel');
                }
            });
        });
    },
    /**
     * 取消点击文本框也显示下拉面板的功能  
     * @param {Object} jq  
     */
    inactiveTextArrow: function (jq) {
        return jq.each(function () {
            var textbox = $(this).combo("textbox");
            textbox.unbind('click.mycombo');
        });
    }
});

$.extend($.fn.datagrid.methods, {
    /**
     * 开打提示功能    
     * @param {} jq    
     * @param {} params 提示消息框的样式    
     * @return {}    
     */
    doCellTip: function (jq, params) {
        function showTip(showParams, td, e, dg) {
            //无文本，不提示。      
            if ($(td).text() == "") return;

            params = params || {};
            var options = dg.data('datagrid');
            showParams.content = '<div class="tipcontent">' + showParams.content + '</div>';
            //增加默认值
            params.position = params.position || "top";
            $(td).tooltip({
                content: showParams.content,
                trackMouse: true,
                position: params.position,
                onHide: function () {
                    $(this).tooltip('destroy');
                },
                onShow: function () {
                    var tip = $(this).tooltip('tip');
                    if (showParams.tipStyler) {
                        tip.css(showParams.tipStyler);
                    }
                    else {
                        //增加默认值
                        tip.css({ 'backgroundColor': '#fff000', borderColor: '#ff0000', maxWidth: '200px', boxShadow: '1px 1px 3px #292929' });
                    }
                    if (showParams.contentStyler) {
                        tip.find('div.tipcontent').css(showParams.contentStyler);
                    }
                }
            }).tooltip('show');

        };
        return jq.each(function () {
            var grid = $(this);
            var options = $(this).data('datagrid');
            if (!options.tooltip) {
                var panel = grid.datagrid('getPanel').panel('panel');
                panel.find('.datagrid-body').each(function () {
                    var delegateEle = $(this).find('> div.datagrid-body-inner').length ? $(this).find('> div.datagrid-body-inner')[0] : this;
                    $(delegateEle).undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove').delegate('td[field]', {
                        'mouseover': function (e) {
                            //if($(this).attr('field')===undefined) return;      
                            var that = this;
                            var setField = null;
                            if (params.specialShowFields && params.specialShowFields.sort) {
                                for (var i = 0; i < params.specialShowFields.length; i++) {
                                    if (params.specialShowFields[i].field == $(this).attr('field')) {
                                        setField = params.specialShowFields[i];
                                    }
                                }
                            }
                            if (setField == null) {
                                options.factContent = $(this).find('>div').clone().css({ 'margin-left': '-5000px', 'width': 'auto', 'display': 'inline', 'position': 'absolute' }).appendTo('body');
                                var factContentWidth = options.factContent.width();
                                params.content = $(this).text();
                                //增加默认值
                                params.onlyShowInterrupt = params.onlyShowInterrupt || true;
                                if (params.onlyShowInterrupt) {
                                    if (factContentWidth > $(this).width()) {
                                        showTip(params, this, e, grid);
                                    }
                                } else {
                                    showTip(params, this, e, grid);
                                }
                            } else {
                                panel.find('.datagrid-body').each(function () {
                                    var trs = $(this).find('tr[datagrid-row-index="' + $(that).parent().attr('datagrid-row-index') + '"]');
                                    trs.each(function () {
                                        var td = $(this).find('> td[field="' + setField.showField + '"]');
                                        if (td.length) {
                                            params.content = td.text();
                                        }
                                    });
                                });
                                showTip(params, this, e, grid);
                            }
                        },
                        'mouseout': function (e) {
                            if (options.factContent) {
                                options.factContent.remove();
                                options.factContent = null;
                            }
                        }
                    });
                });
            }
        });
    },
    /**
     * 关闭消息提示功能    
     * @param {} jq    
     * @return {}    
     */
    cancelCellTip: function (jq) {
        return jq.each(function () {
            var data = $(this).data('datagrid');
            if (data.factContent) {
                data.factContent.remove();
                data.factContent = null;
            }
            var panel = $(this).datagrid('getPanel').panel('panel');
            panel.find('.datagrid-body').undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove')
        });
    }
});

$.extend($.fn.panel.methods, {
    loading: function (jq) {
        return jq.each(function () {
            var $this = $(this);

            $("<div class=\"datagrid-mask\" style=\"display:block\"></div>").appendTo($this);
            var msg = $("<div class=\"datagrid-mask-msg\" style=\"display:block;left:50%;z-index:1000;\"></div>").html("加载中,请稍等....").appendTo($this);
            msg.css("marginLeft", -msg.outerWidth() / 2);
        });
    },
    loaded: function (jq) {
        return jq.each(function () {
            $(this).children("div.datagrid-mask-msg").remove();
            $(this).children("div.datagrid-mask").remove();
        });
    }
});