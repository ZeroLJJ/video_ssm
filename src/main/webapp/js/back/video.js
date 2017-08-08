/**
 * Created by Zero on 2017/5/2.
 */
$(function () {

    /*视频-审核*/
    $(Document).on('click','.check',function () {
        var obj = $(this);
        var id = $(this).data('id');
        layer.confirm('视频审核', {
                btn: ['通过','不通过'],
                shade: false
            },
            function(){
                $.ajax({
                    type: 'POST',
                    url: $.getPath() + '/manage/video/check',
                    data:{
                        videoId: id,
                        pass: true
                    },
                    dataType: 'text',
                    success: function(data){
                        layer.closeAll('dialog');
                        $(obj).parents("tr").find(".frozen").css("display","inline-block");
                        $(obj).css("display","none");
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">正常</span>');
                        layer.msg(data, {icon:6,time:1000});
                    },
                    error:function(data) {
                        console.log(data);
                    },
                });
            },
            function(){
                $(obj).css("display","none");
                $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
                layer.msg('审核未通过', {icon:5,time:1000});
                $.ajax({
                    type: 'POST',
                    url: $.getPath() + '/manage/video/check',
                    data:{
                        videoId: id,
                        pass: false
                    },
                    dataType: 'text',
                    success: function(data){
                        layer.closeAll('dialog');
                        $(obj).css("display","none");
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
                        layer.msg(data,{icon: 5,time:1000});
                    },
                    error:function(data) {
                        console.log(data);
                    },
                });
            });
    });

    /*视频-冻结*/
    $(Document).on('click','.frozen',function () {
        var obj = $(this);
        var id = $(this).data('id');
        layer.confirm('确认要冻结吗？',function(index){
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/video/frozen',
                data:{
                    videoId: id
                },
                dataType: 'text',
                success: function(data){
                    layer.closeAll('dialog');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">已冻结</span>');
                    $(obj).parents("tr").find(".unfrozen").css("display","inline-block");
                    $(obj).css("display","none");;
                    layer.msg(data,{icon: 5,time:1000});
                },
                error:function(data) {
                    console.log(data);
                },
            });
        });
    });

    /*视频-解冻*/
    $(Document).on('click','.unfrozen',function () {
        var obj = $(this);
        var id = $(this).data('id');
        layer.confirm('确认要解冻吗？',function(index){
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/video/unfrozen',
                data:{
                    videoId: id
                },
                dataType: 'text',
                success: function(data){
                    layer.closeAll('dialog');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">正常</span>');
                    $(obj).parents("tr").find(".frozen").css("display","inline-block");
                    $(obj).css("display","none");
                    layer.msg(data,{icon: 5,time:1000});
                },
                error:function(data) {
                    console.log(data);
                },
            });
        });
    });

    //视频删除
    $(Document).on('click','.delete',function (){
        var obj = $(this);
        var id = $(this).data('id');
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/video/delete',
                data:{
                    videoId: id
                },
                dataType: 'text',
                success: function(data){
                    layer.closeAll('dialog');
                    $(obj).parents("tr").remove();
                    layer.msg(data,{icon:1,time:1000});
                },
                error:function(data) {
                    console.log(data);
                },
            });
        });
    });

    //批量删除
    $('#del_multi').click(function () {
        layer.confirm('确认要删除吗？',function(index){
            var id = document.getElementsByName('vid');
            var value = new Array();
            for(var i = 0; i < id.length; i++){
                if(id[i].checked){
                    //isNaN检查出非数字型
                    if(isNaN(id[i].value)==false){
                        value.push(id[i].value);
                    }
                }
            }
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/video/multiDelete',
                data:{
                    idList: value
                },
                dataType: 'text',
                success: function(data){
                    layer.closeAll('dialog');
                    location.replace(location.href);
                },
                error:function(data) {
                    console.log(data);
                },
            });
        });
    });

})