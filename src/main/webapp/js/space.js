/**
 * Created by Zero on 2017/4/26.
 */
$(function () {
    //设置蓝色光标位置与宽度
    $('.n-btn').each(function () {
        if($(this).hasClass('active')){
            $('.n-cursor').css({
                width: $(this).width(),
                left: $(this).position().left
            });
        }
    });

    //---------------  关注模块  -----------------
    $('.section.fans').on('click','.fans-action-btn.follow,' +
        '.fans-action-btn.unfollow',function () {
        var obj = $(this);
        var item = $(this).parents('.fans-action');
        var follow_uid = item.data('follow');
        var followed_uid = item.data('followed');
        var url = $.getPath() + '/user/';
        if(obj.hasClass('follow')){
            url += 'follow';
        }else {
            url += 'unfollow';
        }
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                followUid: follow_uid,
                followedUid: followed_uid
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(data == '关注成功'){
                    obj.removeClass('follow');
                    obj.addClass('unfollow');
                    obj.text('已关注');
                }else if(data == '取消关注成功'){
                    obj.removeClass('unfollow');
                    obj.addClass('follow');
                    obj.text('+ 关注');
                }
                $.tip(data);
            },
            error: function () {
                $.tip('关注调用ajax失败');
            }
        });
    });

    $('.section.subs').on('click','.sub-action',function () {
        var obj = $(this);
        var url = $.getPath() + '/tag/';
        var tagId = obj.data('tag');
        var userId = obj.data('user');
        if($(this).hasClass('sub')){
            url += 'follow';
        }else {
            url += 'unfollow';
        }
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                userId: userId,
                tagId: tagId
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(data == '关注成功'){
                    obj.removeClass('sub');
                    obj.addClass('unsub');
                    obj.text('取消订阅');
                }else if(data == '取消关注成功'){
                    obj.removeClass('unsub');
                    obj.addClass('sub');
                    obj.text('添加订阅');
                }
                $.tip(data);
            },
            error: function () {
                $.tip('标签关注调用ajax失败');
            }
        });
    });
    //---------------  关注模块  -----------------

});