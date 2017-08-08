/**
 * Created by Zero on 2017/5/4.
 */
$(function () {
    $('#login').click(function () {
        var id = $('#id').val();
        var password = $('#password').val();
        if(id == ''){
            $.tip('账号不能为空');
            return false;
        }
        if(password == ''){
            $.tip('密码不能为空');
            return false;
        }
        var url = $.getPath() + '/manage/login';
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                id: id,
                password: password
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(data == '登录成功'){
                    window.location.href = $.getPath() + '/manage/index';
                }else {
                    $.tip(data);
                }
            },
            error: function () {
                $.tip('点赞评论调用ajax失败');
            }
        });
    });

})