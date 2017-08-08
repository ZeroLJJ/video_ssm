/**
 * Created by Zero on 2017/5/14.
 */
$(function(){
    var isUpload = false; //是否已上传的标志

    //视频上传按钮触发
    $('.btn.upload-btn').click(function () {
        $('#video-upload').click();
    });

    //视频ajax上传事件
    $('#video-upload').uploadFile({
        beforeSend : function(){
            //发送前
            $.tip('开始上传');
            $('.picture-box').empty();
            $('.preview-loading').text('正在生成可选封面...');
            $('.file-menu').css('display','block');
            $('.preview-loading').css('display','block');
        },
        uploadProgress : function(event, position, total, percent){
            //上传时
            $('.upload-status.highlight').text(' '+percent+'% ');
            $('.progress').css('width', percent+'%');
        },
        success : function(data){
            //发送成功,显示提示信息，回填数据
            $.tip(data.message);
            $('#video').val(data.videoPath);
            $('#img').val(data.imgPath);
            $('#duration').val(data.duration);
            $('#durationLevel').val(data.durationLevel);
            //延迟输出封面，因为封面处理需要时间，而ajax是不同步的
            setTimeout(function () {
                $('.picture-box').append('<img class="picture" src="'+$.getContext()+'/images/'+data.imgPath+'" alt="封面获取失败"' +
                    ' style="width: 160px;height: 100px; margin-bottom: 20px; border-radius: 4px;">');
                $('.preview-loading').text('以上为当前封面');
            },10000);
            isUpload = true;
        }
    });

    //点击后显示/关闭视频类型下拉框
    $('.dropdown').each(function () {
        $(this).click(function () {
            $(this).find('.dropdown-menu').toggleClass('active');
        });
    });

    //视频类型选中事件
    $('.menu-item').each(function () {
        $(this).click(function () {
            //初始化，清空被选中标志
            $('.type-btn').each(function () {
                $(this).removeClass('is-selected');
            });
            $('.menu-item').each(function () {
                $(this).removeClass('is-selected');
            });
            //关闭下拉框显示
            $('.dropdown-menu').each(function () {
                $(this).removeClass('active');
            });
            var sname = $(this).find('.name').text();
            $('#type').val($(this).data('small'));
            var parent = $(this).parent().prev();
            //添加选中标志
            $(this).addClass('is-selected');
            parent.addClass('is-selected');
            var bname = parent.text();
            //修改提示信息
            $('.type-hint').empty();
            $('.type-hint').append('您当前选择的分区是：<span class="outline">'+bname+' → '+sname+'</span>');
            //使用false结束冒泡行为
            return false;
        });
    });

    //实时统计视频标题输入长度
    $(document).on('keyup','#title-input',function () {
        var count = $('#title-input').val().length;
        $('#title-count').text(count+'/80');
    });

    //实时统计视频简介输入长度
    $(document).on('keyup','#desc-input',function () {
        var count = $('#desc-input').val().length;
        $('#desc-count').text(count+'/250');
    });

    //确认投稿
    $('.btn.submit-btn').click(function () {
        //先进行验证
        if(isUpload === false){
            $.tip('还未上传视频');
            return false;
        }
        if($('#type').val() == ''){
            $.tip('视频投稿分区还未选择');
            return false;
        }
        if($('#title-input').val() == ''){
            $.tip('标题不能为空');
            return false;
        }
        //验证后进行ajax请求
        var url = $.getPath() + '/video/doPost';
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                vname: $('#title-input').val(),
                vsummary: $('#desc-input').val(),
                vmedia: $('#video').val(),
                vimg: $('#img').val(),
                vduration: $('#duration').val(),
                vdurationLevel: $('#durationLevel').val(),
                smallTypeId: $('#type').val()
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(data == "上传成功"){
                    window.location.href = $.getPath() + "/success";
                }
            },
            error: function () {
                $.tip('上传视频调用ajax失败');
            }
        });
    });

    //上传封面
    $(".cover-box").click(function () {
        if(isUpload === false){
            $.tip('还未上传视频，无法自定义封面');
            return false;
        }
        $("#uploadCover").click(); //隐藏了input:file样式后，点击头像就可以本地上传
        $("#uploadCover").on("change",function(){
            //回显封面
            var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
            if (objUrl) {
                $('.picture-box').empty();
                //将图片路径存入src中，显示出图片
                $(".picture-box").append('<img class="picture" src="'+objUrl+'" alt="封面获取失败"' +
                    ' style="width: 160px;height: 100px; margin-bottom: 20px; border-radius: 4px;">');
                $('.preview-loading').text('以上为当前封面');
            }
            //ajax上传新的封面
            var url = $.getPath() + '/video/uploadCover';
            var formData = new FormData();
            formData.append('uploadCover',this.files[0]);
            $.ajax({
                url: url, //请求验证页面
                type: "post", //请求方式 可换为post 注意验证页面接收方式
                data: formData,
                dataType: "text",
                processData     :    false,
                contentType     :    false,
                cache           :    false,
                success: function (data) { //请求成功时执行操作
                    if(data != ""){
                        $('#img').val(data);
                    }else {
                        $.tip('封面上传失败');
                    }
                },
                error: function () {
                    $.tip('上传视频调用ajax失败');
                }
            });
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
});