/**
 * Created by Zero on 2017/5/3.
 */
$(function () {

    /*用户-冻结*/
    $(Document).on('click','.frozen',function () {
        var obj = $(this);
        var id = $(this).data('id');
        layer.confirm('确认要冻结吗？',function(index){
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/user/frozen',
                data:{
                    userId: id
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

    /*用户-解冻*/
    $(Document).on('click','.unfrozen',function () {
        var obj = $(this);
        var id = $(this).data('id');
        layer.confirm('确认要解冻吗？',function(index){
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/user/unfrozen',
                data:{
                    userId: id
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

    //用户删除
    $(Document).on('click','.delete',function (){
        var obj = $(this);
        var id = $(this).data('id');
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/user/delete',
                data:{
                    userId: id
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
            var id = document.getElementsByName('uid');
            var value = new Array();
            for(var i = 0; i < id.length; i++){
                if(id[i].checked){
                    value.push(id[i].value);
                }
            }
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/user/multiDelete',
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

    //任命管理员
    $(Document).on('click','.up',function () {
        var obj = $(this);
        var id = $(this).data('id');
        layer.confirm('确认要任命为管理员吗？',function(index){
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/user/up',
                data:{
                    userId: id
                },
                dataType: 'text',
                success: function(data){
                    layer.closeAll('dialog');
                    $(obj).parents("tr").find(".td-level").html('<span class="label label-secondary radius">管理员</span>');
                    $(obj).parents("tr").find(".down").css("display","inline-block");
                    $(obj).css("display","none");
                    layer.msg(data,{icon: 5,time:1000});
                },
                error:function(data) {
                    console.log(data);
                },
            });
        });
    });

    //撤销管理员
    $(Document).on('click','.down',function () {
        var obj = $(this);
        var id = $(this).data('id');
        layer.confirm('确认要撤销该管理员吗？',function(index){
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/user/down',
                data:{
                    userId: id
                },
                dataType: 'text',
                success: function(data){
                    layer.closeAll('dialog');
                    $(obj).parents("tr").find(".td-level").html('<span class="label label-success radius">普通用户</span>');
                    $(obj).parents("tr").find(".up").css("display","inline-block");
                    $(obj).css("display","none");
                    layer.msg(data,{icon: 5,time:1000});
                },
                error:function(data) {
                    console.log(data);
                },
            });
        });
    });
})