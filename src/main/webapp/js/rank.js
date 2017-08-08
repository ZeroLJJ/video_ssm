/**
 * Created by Zero on 2017/4/14.
 */

$(function () {
    init(); //初始化

    //---------------------    左侧滚动栏模块    --------------------
    $('.side-bar-tag').each(function () {
        $(this).click(function () {
            if ($(this).hasClass('on')) {  //已选中，点击不触发效果
                return false;
            } else {
                var type = $(this).data('type');
                var url = $.getPath() + '/video/rank#!/' + type;
                window.location.href = url;
                return false;
            }
        });
    });
    //---------------------    左侧滚动栏模块    --------------------

    //---------------------    ajax获取数据模块    --------------------
    //初始化
    function init() {
        var hash = $.analyzeHash(location.hash);
        if(hash.path == ''){
            hash.path = 'all';
        }
        var url = $.getPath() + '/video/rankAjax/' + hash.path;
        ajaxRender(url);
    }
    
    //捕捉hash变动
    $(window).on('hashchange', function() {
        var hash = $.analyzeHash(location.hash);
        if(hash.path == ''){
            hash.path = 'all';
        }
        var url = $.getPath() + '/video/rankAjax/' + hash.path;
        ajaxRender(url);
    });

    //动态加载
    function ajaxRender(url) {
        $.ajax({
            url: url, //请求验证页面
            type: "get", //请求方式 可换为post 注意验证页面接收方式
            dataType: "json",
            success: function (data) {
                var dayRank = data.dayRank;   //日排行列表
                var weekRank = data.weekRank;   //周排行列表
                var monthRank = data.monthRank;   //月排行列表

                //----------------------  选中处理 -----------------------
                $('.side-bar-tag').each(function () {
                    if($(this).data('type') == data.videoType){
                        $(this).addClass('on');   //给点击li添加选中效果
                    }else {
                        $(this).removeClass('on'); //清除其他选中效果
                    }
                });
                //----------------------  选中处理 -----------------------

                //----------------------  日排行 -----------------------
                $('#day').empty();
                var dayRankStr = '';    //日排行列表html元素
                $.each(dayRank, function (index, item) {
                    dayRankStr += '<li class="';
                    if(index == 9){
                        dayRankStr += 'clear-border';
                    }
                    dayRankStr += '">';
                    dayRankStr += '<div class="icon">'+(index+1)+'</div>';
                    dayRankStr += '<div class="image-holder">';
                    dayRankStr += '<a target="_blank" href="'+$.getPath()+'/video/show/'+item.vid+'">';
                    dayRankStr += '<img src="'+$.getPath()+'/images/'+item.vimg+'">';
                    dayRankStr += '</a>';
                    dayRankStr += '</div>';
                    dayRankStr += '<div class="title">';
                    dayRankStr += '<a target="_blank" href="'+$.getPath()+'/video/show/'+item.vid+'">'+item.vname+'</a>';
                    dayRankStr += '</div>';
                    dayRankStr += '</li>';
                });
                if(dayRank.length < 10){
                    for (var i = dayRank.length; i < 10; i++){
                        dayRankStr += '<li class="';
                        if(i == 9){
                            dayRankStr += 'clear-border';
                        }
                        dayRankStr += '">';
                        dayRankStr += '<div class="image-holder">';
                        dayRankStr += '<a target="_blank" href=""></a>';
                        dayRankStr += '</div>';
                        dayRankStr += '<div class="title">';
                        dayRankStr += '<a target="_blank" href=""></a>';
                        dayRankStr += '</div>';
                        dayRankStr += '</li>';
                    }
                }
                $('#day').append(dayRankStr);
                //----------------------  日排行 -----------------------

                //----------------------  周排行 -----------------------
                $('#week').empty();
                var weekRankStr = '';    //日排行列表html元素
                $.each(weekRank, function (index, item) {
                    weekRankStr += '<li class="';
                    if(index == 9){
                        weekRankStr += 'clear-border';
                    }
                    weekRankStr += '">';
                    weekRankStr += '<div class="icon">'+(index+1)+'</div>';
                    weekRankStr += '<div class="image-holder">';
                    weekRankStr += '<a target="_blank" href="'+$.getPath()+'/video/show/'+item.vid+'">';
                    weekRankStr += '<img src="'+$.getPath()+'/images/'+item.vimg+'">';
                    weekRankStr += '</a>';
                    weekRankStr += '</div>';
                    weekRankStr += '<div class="title">';
                    weekRankStr += '<a target="_blank" href="'+$.getPath()+'/video/show/'+item.vid+'">'+item.vname+'</a>';
                    weekRankStr += '</div>';
                    weekRankStr += '</li>';
                });
                if(weekRank.length < 10){
                    for (var i = weekRank.length; i < 10; i++){
                        weekRankStr += '<li class="';
                        if(i == 9){
                            weekRankStr += 'clear-border';
                        }
                        weekRankStr += '">';
                        weekRankStr += '<div class="image-holder">';
                        weekRankStr += '<a target="_blank" href=""></a>';
                        weekRankStr += '</div>';
                        weekRankStr += '<div class="title">';
                        weekRankStr += '<a target="_blank" href=""></a>';
                        weekRankStr += '</div>';
                        weekRankStr += '</li>';
                    }
                }
                $('#week').append(weekRankStr);
                //----------------------  周排行 -----------------------

                //----------------------  月排行 -----------------------
                $('#month').empty();
                var monthRankStr = '';    //日排行列表html元素
                $.each(monthRank, function (index, item) {
                    monthRankStr += '<li class="';
                    if(index == 9){
                        monthRankStr += 'clear-border';
                    }
                    monthRankStr += '">';
                    monthRankStr += '<div class="icon">'+(index+1)+'</div>';
                    monthRankStr += '<div class="image-holder">';
                    monthRankStr += '<a target="_blank" href="'+$.getPath()+'/video/show/'+item.vid+'">';
                    monthRankStr += '<img src="'+$.getPath()+'/images/'+item.vimg+'">';
                    monthRankStr += '</a>';
                    monthRankStr += '</div>';
                    monthRankStr += '<div class="title">';
                    monthRankStr += '<a target="_blank" href="'+$.getPath()+'/video/show/'+item.vid+'">'+item.vname+'</a>';
                    monthRankStr += '</div>';
                    monthRankStr += '</li>';
                });
                if(monthRank.length < 10){
                    for (var i = monthRank.length; i < 10; i++){
                        monthRankStr += '<li class="';
                        if(i == 9){
                            monthRankStr += 'clear-border';
                        }
                        monthRankStr += '">';
                        monthRankStr += '<div class="image-holder">';
                        monthRankStr += '<a target="_blank" href=""></a>';
                        monthRankStr += '</div>';
                        monthRankStr += '<div class="title">';
                        monthRankStr += '<a target="_blank" href=""></a>';
                        monthRankStr += '</div>';
                        monthRankStr += '</li>';
                    }
                }
                $('#month').append(monthRankStr);
                //----------------------  月排行 -----------------------
            },
            error: function () {
                $.tip("调用排行ajax请求失败");
            }
        });
    }
    //---------------------    hash变动处理模块    --------------------

});