/**
 * Created by Zero on 2017/4/27.
 */
$(function () {
    //选中与取消选择收藏夹明细项
    $('.s-space').on('click','.checkbox',function () {
        var item = $(this).parents('.small-item');  //获取整个子项容器div
        var count = parseInt($('#multi-count').text());  //获取统计数
        //切换类，有则删除，无则添加
        // item.toggleClass('selected');
        if(item.hasClass('selected')){
            item.removeClass('selected');
            $('#multi-count').text(count - 1);
        }else {
            item.addClass('selected');
            $('#multi-count').text(count + 1);
        }
    });

    //复制，移动发起
    $('.s-space').on('click','#multi-move,#multi-copy',function () {
        var type; //标志是复制还是移动
        if($(this).hasClass('copy')){
            type = 'copy';
        }else {
            type = 'move';
        }
        var count = parseInt($('#multi-count').text());  //获取统计数
        if(count > 0){
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
                        var favList = data.obj;
                        var str = '';
                        str += '<div class="modal-container">';
                        str += '<div class="modal-mask"></div>';
                        str += '<div class="modal-wrapper" style="width: 340px;">';
                        str += '<div class="modal">';
                        str += '<div class="modal-header">';
                        str += '<i class="modal-header-close ion-close-round"></i>';
                        str += '<div class="modal-title">';
                        str += '<p class="edit-video-title">你正在移动'+count+'个视频到</p>';
                        str += '<p class="edit-video-subtitle">[选择中]</p>';
                        str += '</div>';
                        str += '</div>';
                        str += '<div class="modal-body">';
                        str += '<div class="target-favlist" id="target-favlist">';
                        str += '<div class="target-favlist-container">';
                        $.each(favList,function (index, fav) {
                            str += '<div class="target-favitem';
                            if(favoriteId == fav.fid){
                                str += ' disabled';
                            }
                            str += '" data-fid="'+fav.fid+'">';
                            str += '<span class="target-fav-name">'+fav.fname+'</span>';
                            str += '<span class="target-fav-count">'+fav.fhave+'</span>';
                            str += '</div>';
                        });
                        str += '</div>';
                        str += '</div>';
                        str += '</div>';
                        str += '<div class="btn-container modal-footer btn-center">';
                        str += '<a class="btn primary';
                        if(type == 'copy'){
                            str += ' copy';
                        }else {
                            str += ' move';
                        }
                        str += '">';
                        str += '<span class="btn-content"> 确定 </span>';
                        str += '</a>';
                        str += '<a class="btn default">';
                        str += '<span class="btn-content"> 取消 </span>';
                        str += '</a>';
                        str += '</div>';
                        str += '</div>';
                        str += '</div>';
                        str += '</div>';
                        $('body').append(str);
                    }else {
                        $.tip(data.msg);
                    }
                },
                error: function () {
                    $.tip('获取收藏夹信息调用ajax失败');
                }
            });
        }else {
            $.tip('操作失败，选择视频数量不能为0');
        }
    });

    //取消选择收藏夹明细
    $(document).on('click','.modal-header-close,.btn.default',function () {
        $('.modal-container').remove();
    });

    //选中收藏夹
    $(document).on('click','.target-favitem',function () {
        var obj = $(this);
        var name = obj.find('.target-fav-name').text();
        //该标志表示点击无效
        if($(this).hasClass('disabled')){
            return false;
        }
        //清楚其他选中标志
        $('.target-favitem').each(function () {
            if($(this).hasClass('selected')){
                $(this).removeClass('selected');
            }
        });
        //添加选中标志
        obj.addClass('selected');
        $('.edit-video-subtitle').text(name);
    });

    //复制/移动提交
    $(document).on('click','.btn.primary',function () {
        var url = $.getPath() + '/favorite';
        var type;
        if($(this).hasClass('copy')){
            url += '/copy';
            type = 'copy';
        }else {
            url += '/move';
            type = 'move';
        }
        //获取被选择的子项id
        var idList = new Array();
        $('.small-item').each(function () {
            if($(this).hasClass('selected')){
                var id = $(this).data('id');
                idList.push(id);
            }
        });
        var targetId = $('.target-favitem.selected').data('fid');
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                idList: idList,
                sourceId: favoriteId,
                targetId: targetId
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(type == 'move'){
                    if(data == '移动成功'){
                        $('.small-item').each(function () {
                            if($(this).hasClass('selected')){
                                $(this).remove();
                            }
                        });
                    }
                }
                $('.modal-container').remove();
                $.tip(data);
            },
            error: function () {
                $.tip('收藏夹操作调用ajax失败');
            }
        });
    });

    //全部取消选择
    $('.s-space').on('click','#multi-cancel',function () {
        $('.small-item').each(function () {
            $(this).removeClass('selected');
            $('#multi-count').text(0);
        });
    });

    //删除选中
    $('.s-space').on('click','#multi-delete',function () {
        var count = parseInt($('#multi-count').text());  //获取统计数
        if(count <= 0){
            $.tip('操作失败，选择视频数量不能为0');
            return false;
        }
        var dialog = jDialog.confirm('确定删除选中的收藏视频吗',{
                handler : function(button,dialog) {
                    del(); //调用删除
                    dialog.close();
                }
            },{
                handler : function(button,dialog) {
                    dialog.close();
                }
            });
    });

    //删除操作方法
    function del() {
        var idList = new Array();
        $('.small-item').each(function () {
            if($(this).hasClass('selected')){
                var id = $(this).data('id');
                idList.push(id);
            }
        });
        var url = $.getPath() + '/favorite/deleteByIdList';
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                idList: idList,
                favoriteId: favoriteId
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(data == '删除成功'){
                    $('.small-item').each(function () {
                        if($(this).hasClass('selected')){
                            $(this).remove();
                        }
                    });
                }
                $.tip(data);
            },
            error: function () {
                $.tip('收藏夹明细删除调用ajax失败');
            }
        });
    }

})