/**
 * Created by Zero on 2017/3/30.
 */

$(function () {
    var uidStatus = '0';  //用户名是否正确，为1时才可提交
    var pwdStatus = '0';  //密码是否正确，为1时才可提交
    var pwdCheckStatus = '0';  //再次输入密码是否正确，为1时才可提交

    //用户名
    $("#uid").focus(function () {
        this.placeholder = '';
        this.value = "";
        $("#uidTip").attr("class","");
    }).blur(function () {
        if (this.value == '') {
            this.placeholder = '用户名';
            return;
        }

        uidStatus = '1';

        //  ^ 匹配输入字符串的开始位置         $ 匹配输入字符串的结束位置,既以什么开头什么结尾。
        //   斜杠包括起来的表示是正则表达式，不是转义
        var reg1 = /^.{6,16}$/;      //长度
        var reg2 = /^[0-9]+/;        //数字开头
        var reg3 = /^\w+$/;          //内容类型
        var str = $("#uid").val();
        //使用if-else if来达到只要有一条不符合，就不进行其他判断
        if (!reg1.test(str)) {
            $(this).attr("placeholder", $(this).val() + "  :  长度需为6-16位，请重新输入")
            uidStatus = '0';
        }
        else if (reg2.test(str)) {
            $(this).attr("placeholder", $(this).val() + "  :  首字母不能为数字，请重新输入")
            uidStatus = '0';
        }
        else if (!reg3.test(str)) {
            $(this).attr("placeholder", $(this).val() + "  :  只能为字母、数字、下划线，请重新输入")
            uidStatus = '0';
        }else{
            //验证是否重复，用jquery+ajax+json
            //url要加时间戳，防止服务器缓存ajax请求
            var url = $.getPath() + "/user/isExist?time=" + new Date().getTime();
            $.ajax({
                url: url, //请求验证页面
                type: "POST", //请求方式 可换为post 注意验证页面接收方式
                async: false, //同步请求,避免异步请求导致，后面判断不对
                data: {userId: $("#uid").val()},//取得表文本框数据，作为提交数据 注意前面的 user 此处格式 key=value 其他方式请参考ajax手册
                dataType: "text",
                success: function (data) { //请求成功时执行操作
                    if(data != "") {
                        //不能直接给$("#uid").placeholder赋值，有点类似私有变量那种限制，
                        //在ajax之后，就相当于离开的本页面，但可以通过方法设置，即类似公有方法，如下
                        $("#uid").attr("placeholder", $("#uid").val() + "  :  " + data);
                        uidStatus = '0';
                    }
                },
                error: function () {
                    $.tip("用户名重复查询ajax调用失败");
                }
            });
        }
        // 以下部分可能会先于ajax回调函数部分执行（因为异步的原因），所以不行
        // 将ajax设为同步的话即可
        if(uidStatus == '1') {
            $("#uidTip").attr("class","fa fa-check tip");
        }else {
            $("#uidTip").attr("class","fa fa-close tip");
            $("#uid").val("");
        }

    });

    //密码
    $("#pwd").focus(function () {
        this.placeholder = '';
        this.value = "";
        $("#pwdTip").attr("class","");
    }).blur(function () {
        if (this.value == '') {
            this.placeholder = '密码';
            return;
        }

        pwdStatus = '1';

        var reg1 =/^.{6,16}$/;    //长度
        var reg2 =/^\w+$/;        //内容类型
        var str = $("#pwd").val();
        if (!reg1.test(str)) {
            $(this).attr("placeholder", "******  :  长度需为6-16位，请重新输入")
            pwdStatus = '0';
        }
        else if (!reg2.test(str)) {
            $(this).attr("placeholder", "******  :  只能为字母、数字、下划线，请重新输入")
            pwdStatus = '0';
        }

        if(pwdStatus == '1') {
            $("#pwdTip").attr("class","fa fa-check tip");
        }else {
            $("#pwdTip").attr("class","fa fa-close tip");
            $("#pwd").val("");
        }
    });

    //确认密码
    $("#pwdCheck").focus(function () {
        this.placeholder = '';
        this.value = "";
        $("#pwdCheckTip").attr("class","");
    }).blur(function () {
        if (this.value == '') {
            this.placeholder = '确认密码';
            return;
        }

        pwdCheckStatus = '1';
        if ($("#pwd").val() != $("#pwdCheck").val()) {
            $(this).attr("placeholder", "******  :  再次输入的密码不匹配，请重新输入")
            pwdCheckStatus = '0';
        }
        if(pwdCheckStatus == '1') {
            $("#pwdCheckTip").attr("class","fa fa-check tip");
        }else {
            $("#pwdCheckTip").attr("class","fa fa-close tip");
            $("#pwdCheck").val("");
        }
    });

    $("form").submit(function(){
        if(uidStatus != '1' || pwdStatus != '1' || pwdCheckStatus != '1'){
            $.tip('信息不完整，请填写完整后再提交');
            return false;
        }
    });

})
