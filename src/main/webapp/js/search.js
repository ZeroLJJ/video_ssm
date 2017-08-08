/**
 * Created by Zero on 2017/3/28.
 */

$(function () {
    //全局变量，以下是url和url的参数，$.getUrlParam为自扩展函数
    var _url = null;
    var _urlSub = null;  //获取url前部分（即参数之前），用于Url拼接
    var _name = null;
    var _pageNo = null;
    var _bigType = null;
    var _smallType = null;
    var _tag = null;
    var _orderType = null;
    var _duration_level = null;
    init(); //初始化选中框

    //获取url和其参数
    function getParam() {
        _url = document.URL;
        _urlSub = _url.split("?")[0];  //获取url前部分（即参数之前），用于Url拼接
        _name = $.getUrlParam('name');
        _pageNo = $.getUrlParam('pageNo');
        _bigType = $.getUrlParam('bigType');
        _smallType = $.getUrlParam('smallType');
        _tag = $.getUrlParam('tag');
        _orderType = $.getUrlParam('orderType');
        _duration_level = $.getUrlParam('duration_level');
    }

    //---------------------    选项栏模块    --------------------
    //用于选择排序类型的url拼接，只有url的排序参数可变，其他参数保持原样
    $("#order .filter-item").each(function () {
        var orderLi = $(this);
        orderLi.click(function(){
            if(!$(this).hasClass('active')){   //如果点击已经选中的则不进行处理
                $("#order .filter-item").each(function () {
                    $(this).removeClass('active');  //清空选中标志
                });
                orderLi.addClass('active');  //给点击的对应li添加选中标志
                getParam(); //获取最新的url和参数
                _pageNo = 1;   //切换类型时，页码置为1，只有翻页时可以设置页码
                var url = _urlSub + '?';
                var orderType = orderLi.data("id");
                if(_name != null){
                    url += "name=" + _name + "&";
                }
                if(_bigType != null){
                    url += "bigType=" + _bigType + "&";
                }
                if(_smallType != null){
                    url += "smallType=" + _smallType + "&";
                }
                if(_tag != null){
                    url += "tag=" + _tag + "&";
                }
                //可变部分
                if(orderType != null && orderType != 0){
                    url += "orderType=" + orderType + "&";
                }
                if(_duration_level != null){
                    url += "duration_level=" + _duration_level + "&";
                }
                //去掉末尾的？或&
                url = url.substring(0,url.length - 1);
                //将拼好的url推入栈，实现url地址变更却不跳转，方便下次参数的获取
                //并且在ajax请求之后，可以使用前进后退，一般ajax的缺点就是会导致前进后退效果失效
                //通过该api可以解决
                history.pushState(null, "", url);
                videoPageAjax(_name, _pageNo, _bigType, _smallType, _tag, orderType, _duration_level);
            }
        });
    });

    //用于选择时长类型的url拼接，只有url的时长参数可变，其他参数保持原样
    $("#duration .filter-item").each(function () {
        var durationLi = $(this);
        durationLi.click(function(){
            if(!$(this).hasClass('active')){   //如果点击已经选中的则不进行处理
                $("#duration .filter-item").each(function () {
                    $(this).removeClass('active');  //清空选中标志
                });
                durationLi.addClass('active');  //给点击的对应li添加选中标志
                getParam(); //获取最新的url和参数
                _pageNo = 1;   //切换类型时，页码置为1，只有翻页时可以设置页码
                var url = _urlSub + '?';
                var duration_level = durationLi.data("id");
                if(_name != null){
                    url += "name=" + _name + "&";
                }
                if(_bigType != null){
                    url += "bigType=" + _bigType + "&";
                }
                if(_smallType != null){
                    url += "smallType=" + _smallType + "&";
                }
                if(_tag != null){
                    url += "tag=" + _tag + "&";
                }
                if(_orderType != null){
                    url += "orderType=" + _orderType + "&";
                }
                //可变部分
                if(duration_level != null && duration_level != 0){
                    url += "duration_level=" + duration_level + "&";
                }
                //去掉末尾的？或&
                url = url.substring(0,url.length - 1);
                //将拼好的url推入栈，实现url地址变更却不跳转，方便下次参数的获取
                //并且在ajax请求之后，可以使用前进后退，一般ajax的缺点就是会导致前进后退效果失效
                //通过该api可以解决
                history.pushState(null, "", url);
                videoPageAjax(_name, _pageNo, _bigType, _smallType, _tag, _orderType, duration_level);
            }
        });
    });

    //用于选择视频类型的url拼接，只有url的视频分类参数可变，其他参数保持原样
    $("#type .filter-item").each(function () {
        var typeLi = $(this);
        typeLi.click(function(){
            if(!$(this).hasClass('active')) {   //如果点击已经选中的则不进行处理
                $("#type .filter-item").each(function () {
                    $(this).removeClass('active');  //清空选中标志
                });
                typeLi.addClass('active');  //给点击的对应li添加选中标志
                getParam(); //获取最新的url和参数
                _pageNo = 1;   //切换类型时，页码置为1，只有翻页时可以设置页码
                var url = _urlSub + '?';
                var bigType = typeLi.data("big");
                var smallType = typeLi.data("small");
                if (_name != null) {
                    url += "name=" + _name + "&";
                }
                //可变部分
                if (bigType != null && bigType != 0) {
                    url += "bigType=" + bigType + "&";
                }
                //可变部分
                if (smallType != null && smallType != 0) {
                    url += "smallType=" + smallType + "&";
                }
                if (_tag != null) {
                    url += "tag=" + _tag + "&";
                }
                if (_orderType != null) {
                    url += "orderType=" + _orderType + "&";
                }
                if (_duration_level != null) {
                    url += "duration_level=" + _duration_level + "&";
                }
                //去掉末尾的？或&
                url = url.substring(0, url.length - 1);
                //将拼好的url推入栈，实现url地址变更却不跳转，方便下次参数的获取
                //并且在ajax请求之后，可以使用前进后退，一般ajax的缺点就是会导致前进后退效果失效
                //通过该api可以解决
                history.pushState(null, "", url);
                videoPageAjax(_name, _pageNo, bigType, smallType, _tag, _orderType, _duration_level);
            }
        });
    });
    //---------------------    选项栏模块    --------------------

    //---------------------    翻页点击模块    --------------------
    $(Document).on('click','.tcd-number',function (){
        var pageNo = $(this).data('page');
        getParam(); //获取最新的url和参数
        var url = _urlSub + '?';
        if(_name != null){
            url += "name=" + _name + "&";
        }
        //可变部分
        if(pageNo != null){
            url += "pageNo=" + pageNo + "&";
        }
        if(_bigType != null){
            url += "bigType=" + _bigType + "&";
        }
        if(_smallType != null){
            url += "smallType=" + _smallType + "&";
        }
        if(_tag != null){
            url += "tag=" + _tag + "&";
        }
        if(_orderType != null){
            url += "orderType=" + _orderType + "&";
        }
        if(_duration_level != null){
            url += "duration_level=" + _duration_level + "&";
        }
        //去掉末尾的？或&
        url = url.substring(0,url.length - 1);
        //将拼好的url推入栈，实现url地址变更却不跳转，方便下次参数的获取
        //并且在ajax请求之后，可以使用前进后退，一般ajax的缺点就是会导致前进后退效果失效
        //通过该api可以解决
        history.pushState(null, "", url);
        videoPageAjax(_name, pageNo, _bigType, _smallType, _tag, _orderType, _duration_level);
    });
    //---------------------    翻页点击模块    --------------------

    //---------------------    Ajax请求新页面信息模块    --------------------
    function videoPageAjax(name, pageNo, bigType, smallType, tag, orderType, duration_level) {
        var url = $.getPath() + '/video/search';
        $.ajax({
            url: url, //请求验证页面
            type: "POST", //请求方式 可换为post 注意验证页面接收方式
            async: true, //异步请求
            data: {  //查询条件
                name: name,
                pageNo: pageNo,
                bigType: bigType,
                smallType: smallType,
                tag: tag,
                orderType: orderType,
                duration_level: duration_level
            },
            dataType: "json",
            success: function (data) { //请求成功时执行操作
                if(data != "") {
                    var videoList = data.list;
                    $('.ajax-render').empty();      //视频列表清空
                    $('#video-paging').empty();      //视频页码列表清空
                    var videoListStr = '';  //视频列表html元素
                    var pageStr = '';  //视频页码html元素
                    if(videoList.length != 0) {
                        //---------------------    视频列表    --------------------
                        $.each(videoList, function (index, videoItem) {
                            videoListStr += '<li class="video matrix" style="width: 170px;height: 210px">';
                            videoListStr += '<a href="' + $.getPath() + '/video/show/' + videoItem.vid + '"  target="_blank" title="' + videoItem.vname + '">';
                            videoListStr += '<div class="img">';
                            videoListStr += '<img src="' + $.getPath() + '/images/' + videoItem.vimg + '" title="' + videoItem.vname + '">';
                            videoListStr += '<span class="so-imgTag_rb">' + videoItem.vduration + '</span>';
                            videoListStr += '</div>';
                            videoListStr += '</a>';
                            videoListStr += '<div class="info">';
                            videoListStr += '<div class="headline">';
                            videoListStr += '<a class="title" title="' + videoItem.vname + '" href="' + $.getPath() + '/video/show/' + videoItem.vid + '" target="_blank">';
                            videoListStr += '<em class="keyword">' + videoItem.vname + '</em>';
                            videoListStr += '</a>';
                            videoListStr += '</div>';
                            videoListStr += '<div class="des hide">' + videoItem.vsummary + '</div>';
                            videoListStr += '<div class="tags">';
                            videoListStr += '<span class="so-icon watch-num" title="观看" style="width: 57px;height: 12px">';
                            videoListStr += '<i class="icon-playtime" ></i>';
                            if (videoItem.vplayTimes >= 10000) {
                                var num = videoItem.vplayTimes / 10000;
                                videoListStr += num.toFixed(1) + '万';
                            } else {
                                videoListStr += videoItem.vplayTimes;
                            }
                            videoListStr += '</span>';
                            videoListStr += '<span class="so-icon hide" title="弹幕">';
                            videoListStr += '<i class="icon-subtitle"></i>' + videoItem.vdanmu;
                            videoListStr += '</span>';
                            videoListStr += '<span class="so-icon time" title="上传时间" style="width: 83px;height: 12px">';
                            videoListStr += '<i class="icon-date"></i>' + videoItem.vaddTime;
                            videoListStr += '</span>';
                            videoListStr += '<span class="so-icon" title="up主" style="width: 61.72px;height: 12px">';
                            videoListStr += '<i class="icon-uper"></i>';
                            videoListStr += '<a href="' + $.getPath() + '/user/space/' + videoItem.userBO.uid + '" class="up-name" target="_blank">';
                            if (videoItem.userBO.uname != null) {
                                videoListStr += videoItem.userBO.uname;
                            } else {
                                videoListStr += videoItem.userBO.uid;
                            }
                            videoListStr += '</a>';
                            videoListStr += '</span>';
                            videoListStr += '</div>';
                            videoListStr += '</div>';
                            videoListStr += '</li>';
                        });
                        $('.ajax-render').append(videoListStr);
                        //---------------------    视频列表    --------------------

                        //---------------------    页码列表    --------------------
                        if (data.hasPreviousPage == true) {
                            pageStr += '<a style="width: 72px; height: 38px;" href="javascript:;" class="tcd-number" data-page="' + (data.currentPage - 1) + '">上一页</a>';
                        }
                        for (var pageNo = 1; pageNo <= data.totalPage; pageNo++) {
                            if (pageNo == data.currentPage) {
                                pageStr += '<span class="current">' + pageNo + '</span>';
                            } else {
                                pageStr += '<a href="javascript:;" class="tcd-number" data-page="' + pageNo + '">' + pageNo + '</a>';
                            }
                        }
                        if (data.hasNextPage == true) {
                            pageStr += '<a style="width: 72px; height: 38px;" href="javascript:;" class="tcd-number" data-page="' + (data.currentPage + 1) + '">下一页</a>';
                        }
                        $('#video-paging').append(pageStr);
                        //---------------------    页码列表    --------------------
                    }else {
                        videoListStr += '<div class="no-result">';
                        videoListStr += '<p class="text">没有相关数据</p>';
                        videoListStr += '<div>';
                        $('.ajax-render').append(videoListStr);
                    }
                }else {
                    $.tip("获取数据失败");
                }
            },
            error: function () {
                $.tip("视频搜索ajax调用失败");
            }
        });
    }
    //---------------------    Ajax请求新页面信息模块    --------------------

    //---------------------    前进后退刷新处理模块    --------------------
    window.addEventListener('popstate',function () {
        var url = window.location.href;
        window.location.href = url ;
    });

    //前进后退时初始化选中框
    function init() {
        getParam();
        if(_orderType == null || _orderType < 0){
            _orderType = 0;
        }
        if(_duration_level == null || _duration_level < 0){
            _duration_level = 0;
        }
        if(_bigType == null && _smallType == null){
            _bigType = 0;
            _smallType = 0;
        }
        $("#order .filter-item").each(function () {
            var id = $(this).data('id');
            $(this).removeClass('active');
            if(id == _orderType){
                $(this).addClass('active');
            }
        });
        $("#duration .filter-item").each(function () {
            var id = $(this).data('id');
            $(this).removeClass('active');
            if(id == _duration_level){
                $(this).addClass('active');
            }
        });
        $("#type .filter-item").each(function () {
            var big = $(this).data('big');
            var small = $(this).data('small');
            $(this).removeClass('active');
            if(big == _bigType && small == _smallType){
                $(this).addClass('active');
            }
        });
    }
    //---------------------    前进后退刷新处理模块    --------------------
})
