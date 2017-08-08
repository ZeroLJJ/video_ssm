
/**
 * Created by Zero on 2017/3/27.
 */
$(function(){
    //---------------------  硬币和收藏动效  -------------------
    $(".b-icon.b-icon-a.b-icon-stow").mouseenter(function () {
        flag1 = 0;   //重置
        if(run1 == undefined){   //若有计时器，则不再创建
            setTimeout(play1(this),0);
        }
    }).mouseleave(function () {
        flag1 = 1;   //准备停止
    });

    $(".b-icon.b-icon-a.b-icon-coin").mouseenter(function () {
        flag2 = 0;
        if(run2 == undefined){
            setTimeout(play2(this),0);
        }
    }).mouseleave(function () {
        flag2 = 1;
    });

    var run1, run2;   //存储计时器
    var flag1 = 0, flag2 = 0;  //0表示计时器循环，1表示计时器准备停止，2表示计时器停止
    function play1(e) {
        var i = 0;  //表示每层循环循环到第几次，第20次重置为0
        run1 = setInterval(function () {
            if(flag1 < 2) {     //加这层的意思是，即使调用了clearInterval也不会立即停止，所以添加个停止状态
                i++;
                if (i == 19 && flag1 == 1) {     //判断是否处于准备停止且循环完一层
                    flag1 == 2;
                    window.clearInterval(run1);
                    run1 = undefined;             //重置存储器
                }
                if (i == 19) {
                    i = 0;
                }
                e.style.backgroundPosition = i * (-60) + "px 0px";
            }
        },70);
    }

    function play2(e) {
        var i = 0;  //表示每层循环循环到第几次，第20次重置为0
        run2 = setInterval(function () {
            if(flag2 < 2) {     //加这层的意思是，即使调用了clearInterval也不会立即停止，所以添加个停止状态
                i++;
                if (i == 19 && flag2 == 1) {     //判断是否处于准备停止且循环完一层
                    flag2 == 2;
                    window.clearInterval(run2);
                    run2 = undefined;             //重置存储器
                }
                if (i == 19) {
                    i = 0;
                }
                e.style.backgroundPosition = i * (-60) + "px 0px";
            }
        },70);
    }
    //---------------------  硬币和收藏动效  -------------------

    //---------------------  投币功能模块  -------------------
    //发起
    $(".b-icon-coin").click(function () {
        $(".m-coin").css({
            'display' : 'block'
        });
        $(".m-backdrop").css({
            'display' : 'block'
        });
    });

    //硬币数量切换
    var coin = $(".coin-nav-single");
    coin.each(function (index) {
        $(this).click(function(){
            coin.removeClass("active");
            $(this).addClass("active");
            $(".coin-main-number").text(index+1); //设置显示硬币数量值
        });
    });

    //提交
    $(".b-btn.ok").click(function () {
        if(userId == ''){
            $.tip('请登录后再进行该操作');
            return false;
        }
        var url = $.getPath() + '/video/score'
        var coin_num = 1;   //默认投一个
        coin.each(function (index) {
            if($(this).hasClass("active")){
                coin_num = index + 1;  //硬币数量
            };
        });
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                userId: userId,
                num: coin_num,
                videoId: videoId
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(data == '投币成功'){
                    var coin = parseInt($('.coin').text());
                    $('.coin').text(coin + coin_num);
                    $(".m-coin").css('display', 'none');
                    $(".m-backdrop").css('display', 'none');
                }
                $.tip(data);
            },
            error: function () {
                $.tip('投币调用ajax失败');
            }
        });
    });

    //取消
    $(".b-btn-cancel.cancel").click(function () {
        $(".m-coin").css('display', 'none');
        $(".m-backdrop").css('display', 'none');
    });
    //---------------------  投币发起，提交和取消  -------------------

    //---------------------  收藏功能模块  -------------------
    var fav_count = 0;  //收藏夹个数
    //发起
    $(".b-icon-stow").click(function () {
        if(userId == ''){
            $.tip('请登录后再进行该操作');
            return false;
        }
        //ajax获取收藏夹信息
        var url = $.getPath() + '/favorite/getFavorite';
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                userId: userId
            },
            dataType: "json",
            success: function (data) { //请求成功时执行操作
                if(data.msg == '获取成功'){
                    var fav = data.obj;   //收藏夹信息
                    var str = '';  //存放收藏夹html元素
                    str += '<div class="fav-wnd" id="fav_wnd" style="margin-top: -196px; z-index: 100001; position: fixed; ">';
                    str += '<div class="fav-hd">添加到收藏夹<i class="b-icon b-icon-a b-icon-close"></i></div>';
                    str += '<div class="fav-bd">';
                    str += '<div class="fav-mask" style="display: none;"></div>';
                    str += '<div class="fav-tip"></div>';
                    str += '<div class="fav-list-wrp">';
                    str += '<ul class="fav-list" style="margin-bottom: 28px;">';
                    $.each(fav, function (index, item) {
                        fav_count++;
                        str += '<li data-fav="'+ item.fid +'" class="list-item">';
                        str += '<div class="list-item-name" title="'+item.fname+'">';
                        str += '<i class="radius-box off"></i>'+item.fname;
                        str += '<div class="fav-count">';
                        str += '<span class="fav-have">'+item.fhave+'</span>/'+item.ftotal;
                        str += '</div>';
                        str += '</div>';
                        str += '</li>';
                    })
                    str += '</ul>';
                    str += '<div class="fav-new-placeholder" style="display: block;">';
                    str += '<span class="fav-icon-add"></span>';
                    str += '<span class="txt">新建收藏夹</span>';
                    str += '</div>';
                    str += '<div class="fav-new-wrp" style="display: none;">';
                    str += '<input class="fav-input" placeholder="最多输入15个字" style="width:200px">';
                    str += '<div class="fav-new-btns" style="height: 34px;position: relative;right: 3px;">新建</div>';
                    str += '</div>';
                    str += '</div>';
                    str += '<div class="fav-sure-wrp">';
                    str += '<div class="fav-sure">确定</div>';
                    str += '</div>';
                    str += '</div>';
                    str += '</div>';
                    $('body').append(str);
                    init_fav();  //初始化收藏夹，主要添加js动效
                }else {
                    $.tip(data.msg);
                }
            },
            error: function () {
                $.tip('获取收藏夹信息调用ajax失败');
            }
        });
        $(".m-backdrop").css('display', 'block');
    });

    function init_fav() {
        //进入添加收藏夹模式
        $('.fav-new-placeholder').click(function () {
            $(".fav-new-wrp").css('display', 'block');
            $(".fav-new-placeholder").css('display', 'none');
            $(".fav-mask").css('display', 'block');
        });

        //退出添加收藏夹模式
        $(".fav-mask").click(function(e){
            var _target = $(e.target);
            //closest返回被选元素（_target）的第一个祖先元素(.fav-new-wrp)，为0表示搜索不到该祖先元素，即在输入框外
            if (_target.closest(".fav-new-wrp").length == 0) {
                $(".fav-new-wrp").css('display', 'none');
                $(".fav-new-placeholder").css('display', 'block');
                $(".fav-mask").css('display', 'none');
            }
        });

        //收藏夹选定与撤销
        $('.fav-wnd').on('click','.list-item-name',function () {
            var item = $(this);
            var obj = $(this).find('.radius-box');
            if(obj.hasClass("on")){
                obj.removeClass("on");
                obj.addClass("off");
                item.find('.fav-have').text(parseInt(item.find('.fav-have').text()) - 1);
            }else if(obj.hasClass("off")){
                obj.removeClass("off");
                obj.addClass("on");
                item.find('.fav-have').text(parseInt(item.find('.fav-have').text()) + 1);
            }
        });

        //取消
        $(".b-icon-close").click(function () {
            $(".fav-wnd").remove();
            $(".m-backdrop").css('display', 'none');
        });

        //新增收藏夹提交
        function add_fav_commit() {
            var name = $('.fav-input').val();
            var url = $.getPath() + '/favorite/add'
            $.ajax({
                url: url, //请求验证页面
                type: "post", //请求方式 可换为post 注意验证页面接收方式
                data:{
                    userId: userId,
                    name: name
                },
                dataType: "json",
                success: function (data) { //请求成功时执行操作
                    if(data.msg == '新增成功'){
                        var item = data.obj;
                        var str = '';  //存放新增收藏夹html元素信息
                        fav_count++;
                        str += '<li data-fav="'+ item.fid +'" class="list-item">';
                        str += '<div class="list-item-name" title="'+item.fname+'">';
                        str += '<i class="radius-box on"></i>'+item.fname;
                        str += '<div class="fav-count">';
                        str += '<span class="fav-have">'+(item.fhave+1)+'</span>/'+item.ftotal;
                        str += '</div>';
                        str += '</div>';
                        str += '</li>';
                        $(".fav-new-wrp").css('display', 'none');
                        $(".fav-new-placeholder").css('display', 'block');
                        $(".fav-mask").css('display', 'none');
                        $('.fav-list').append(str);
                    }else {
                        $.tip(data.msg);
                    }
                },
                error: function () {
                    $.tip('新增收藏夹调用ajax失败');
                }
            });
        }

        $('.fav-input').keydown(function (e) {
            if(e.keyCode == 13){
                add_fav_commit();
            }
        });

        $('.fav-new-btns').click(function () {
            add_fav_commit();
        });

        //添加至收藏夹提交
        $(".fav-sure").click(function () {
            var fav_list = new Array();  //存放需要提交的收藏夹id列表
            $('.radius-box').each(function () {
                //拼凑id列表,on表示选中
               if($(this).hasClass('on')) {
                   var id = $(this).parents('.list-item').data('fav');
                   fav_list.push(id);
               }
            });
            var url = $.getPath() + '/favorite/commit';
            $.ajax({
                url: url, //请求验证页面
                type: "post", //请求方式 可换为post 注意验证页面接收方式
                data:{
                    idList: fav_list,
                    videoId: videoId
                },
                dataType: "text",
                success: function (data) { //请求成功时执行操作
                    if(data == '添加至收藏夹成功'){
                        var fav = parseInt($('#stow_count').text());
                        $('#stow_count').text(fav + 1);
                        $(".fav-wnd").remove();
                        $(".m-backdrop").css('display', 'none');
                    }
                    $.tip(data);
                },
                error: function () {
                    $.tip('添加至收藏夹调用ajax失败');
                }
            });
        });

    }
    //---------------------  收藏功能模块  -------------------

    //---------------------  点赞模块  -------------------
    $(Document).on('click','.like',function () {
        if(userId == ''){  //说明未登录
            $.tip('用户未登录，请登录后才可点赞');
        }else {
            var url = $.getPath() + '/agree';
            var like = $(this);
            var obj = $(this).parent().parent().parent();
            var type; //目标类型
            var targetId; //目标编号
            if(obj.hasClass('tag-info-pane')){
                type = "标签";
                targetId = obj.data('tag-item');
            }else if(obj.hasClass('list-item')){  //点赞评论
                type = "评论";
                targetId = obj.data('comment');
            }else{ //点赞回复
                type = "回复";
                targetId = obj.data('reply');
            }
            $.ajax({
                url: url, //请求验证页面
                type: "post", //请求方式 可换为post 注意验证页面接收方式
                data:{
                    userId: userId,
                    type: type,
                    targetId: targetId
                },
                dataType: "text",
                success: function (data) { //请求成功时执行操作
                    if(data == '点赞成功'){
                        var num = parseInt(like.find('.agree-num').text());
                        like.find('.agree-num').text(num + 1);
                    }else {
                        $.tip(data);
                    }
                },
                error: function () {
                    $.tip('点赞评论调用ajax失败');
                }
            });
        }
    });
    //---------------------  点赞模块  -------------------

    // 用户关注与取消关注
    $('.upinfo').on('click','.b-btn',function () {
        if(userId == ''){
            $.tip('用户未登录，请登录后再进行该操作');
            return false;
        }
        var url = $.getPath() + '/user/';
        var obj = $(this);
        var followedUid = obj.data('uid');
        if($(this).hasClass('f')){
            url += 'follow';
        }else {
            url += 'unfollow';
        }
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                followUid: userId,
                followedUid: followedUid
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(data == '关注成功'){
                    $('.b-btn.f').css('display','none');
                    $('.b-btn.unf').css('display','block');
                }else if(data == '取消关注成功'){
                    $('.b-btn.f').css('display','block');
                    $('.b-btn.unf').css('display','none');
                }
                $.tip(data);
            },
            error: function () {
                $.tip('关注调用ajax失败');
            }
        });
    });

});