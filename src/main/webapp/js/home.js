/**
 * Created by Zero on 2017/4/18.
 */
$(function() {

    //上传头像
    $("#pic").click(function () {
        $("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
        $("#upload").on("change",function(){
            var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
            if (objUrl) {
                $("#pic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
            }
        });
    });

    //建立个可以存储该file的url
    function getObjectURL(file) {
        var url = null ;
        if (window.createObjectURL!=undefined) { // basic
            url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }

    //性别选择切换
    $('#sex_ul li').each(function () {
        $(this).click(function () {
            $('#sex_ul li').each(function () {
                $(this).removeClass('blue');
            });
            $(this).addClass('blue');
            $('#select_sex').val($(this).data('sex'));
        })
    });

    $("form").submit(function(){
        var reg1 =/^1[3|5|7|8][0-9]{9}$/;    //电话格式
        var str1 = $("#phone").val();
        if((str1 != "") && (!reg1.test(str1))){
            $.tip("电话格式不正确");
            return false;
        }

        var reg2 =/^\w+@\w+\.\w+$/;    //邮箱格式
        var str2 = $("#email").val();
        if((str2 != "") && (!reg2.test(str2))){
            $.tip("邮箱格式不正确");
            return false;
        }
    });
});