
/**
 * Created by Zero on 2017/3/28.
 */

$(function() {
    //获取url参数
    (function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null)
                return decodeURI(r[2]);
            return null;
        }
    })(jQuery);

    //解析hash，该项目hash格式：例如***/#!/aaa/bbb?type=123&name=456
    //  aaa/bbb为path路径，type和name为search的键，后面为键
    (function ($) {
        $.analyzeHash = function (hash) {
            var split = hash.substr(3).split("?");
            var path = split[0];
            var search = split[1];
            var result = new Object();
            if(path != null){
                result.path = path;
            }else {
                result.path = null;
            }
            if(search != null){
                var param = new Object();
                var item = search.split("&");
                for (var i = 0; i < item.length; i++){
                    var entry = item[i].split("=");
                    param[entry[0]] = entry[1];
                }
                result.param = param
            }else {
                result.param = null;
            }
            return result
        }
    })(jQuery);

    //扩展延时处理类
    (function ($) {
        $.fn.hoverDelay = function (options) {
            var defaults = {
                hoverDuring: 200,
                outDuring: 200,
                hoverEvent: function () {
                    $.noop();
                },
                outEvent: function () {
                    $.noop();
                }
            };
            var sets = $.extend(defaults, options || {});
            var hoverTimer, outTimer;
            return $(this).each(function () {
                $(this).mouseenter(function () {
                    clearTimeout(outTimer);
                    hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring);
                }).mouseleave(function () {
                    clearTimeout(hoverTimer);
                    outTimer = setTimeout(sets.outEvent, sets.outDuring);
                });
            });
        }
    })(jQuery);

    //获取项目根目录
    (function ($) {
        $.getPath = function() {
            var localObj = window.location;
            var contextPath = localObj.pathname.split("/")[1];
            var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
            return basePath;
        }
    })(jQuery);

    //获取项目相对路径
    (function ($) {
        $.getContext = function() {
            var localObj = window.location;
            var contextPath = localObj.pathname.split("/")[1];
            return "/" + contextPath;
        }
    })(jQuery);

    //自定义提示框
    (function ($) {
        $.tip = function(text) {
            //my-tip样式在base.css中
            var str = '<div class="my-tip">'+ text +'</div>';
            $('body').append(str);
            //显示后，在2.5秒内慢慢消失
            $('.my-tip').show().fadeOut(2500,function () {
                //消失后移除该提示标签
                $(this).remove();
            });
        }
    })(jQuery);


    //---------------------  头像动效  -------------------
    $('#user-menu').hoverDelay({
        hoverDuring: 500,
        outDuring: 500,
        hoverEvent: function () {
            //显示菜单下拉框并放大头像
            $('#user-menu').addClass('open');
            var img = document.getElementById('userImg');
            img.style.width = "64px";
            img.style.height = "64px";
        },
        outEvent: function () {
            //收回菜单下拉框并缩小头像
            $('#user-menu').removeClass('open');
            var img = document.getElementById('userImg');
            img.style.width = "32px";
            img.style.height = "32px";
        }
    });
    //---------------------  头像动效  -------------------

    //---------------------  搜索框处理  -------------------
    $(".search").on('click','.search-submit',function () {
        var name = $(this).prev().val();
        if(name == ""){
            $.tip("输入的内容不可为空");
        }else {
            window.location.href = $.getPath() + '/video/all?name=' + name;
        }
    });

    $('.search').on('keydown','.search-keyword',function (event) {
        if(event.keyCode == 13){  //回车键才处理
            var name = $(this).val();
            if(name == ""){
                $.tip("输入的内容不可为空");
            }else {
                window.location.href = $.getPath() + '/video/all?name=' + name;
            }
        }
    });
    //---------------------  搜索框处理  -------------------

})