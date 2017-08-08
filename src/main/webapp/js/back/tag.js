/**
 * Created by Zero on 2017/5/3.
 */
$(function () {
    //标签删除
    $(Document).on('click','.delete',function (){
        var obj = $(this);
        var id = $(this).data('id');
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: $.getPath() + '/manage/tag/delete',
                data:{
                    tagId: id
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
            var id = document.getElementsByName('tid');
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
                url: $.getPath() + '/manage/tag/multiDelete',
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