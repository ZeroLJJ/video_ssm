/**
 * Created by Zero on 2017/4/27.
 */
$(function () {
    //收藏夹编辑框显示
    $('.section.fav').on('click','.edit.icon',function () {
        var item = $(this).parents('.fav-item'); //编辑的收藏夹项
        var fav = item.data('fav');
        var url = $.getPath() + '/favorite/getById';
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                favoriteId: fav
            },
            dataType: "json",
            success: function (data) { //请求成功时执行操作
                if(data != null){
                    var str = '';
                    str += '<div class="modal-container">';
                    str += '<div class="modal-mask"></div>';
                    str += '<div class="modal-wrapper" style="width: 300px;">';
                    str += '<div class="modal">';
                    str += '<div class="modal-header">';
                    str += '<i class="modal-header-close ion-close-round"></i>';
                    str += '<span class="modal-title"> 编辑收藏夹 </span>';
                    str += '</div>';
                    str += '<div class="modal-body">';
                    str += '<div class="sp-input">';
                    str += '<input id="fav-name" type="text" value="'+data.fname+'" maxlength="15">';
                    str += '<div class="letter-count" style="display: none;"><span id="letter-count">0</span>/15</div>';
                    str += '</div>';
                    str += '<div class="switcher-container ';
                    if(data.fprivacy == 1){
                        str += 'switcher-on';
                    }else {
                        str += 'switcher-off';
                    }
                    str += '">';
                    str += '<div class="switcher">';
                    str += '<i class="cursor"></i>';
                    str += '</div>';
                    str += '<div class="label">';
                    str += '<span>公开该收藏夹</span>';
                    str += '</div>';
                    str += '</div>';
                    str += '</div>';
                    str += '<div class="modal-footer">';
                    str += '<a class="btn primary edit" data-id="'+data.fid+'">';
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
                    $.tip('收藏夹信息获取失败');
                }
            },
            error: function () {
                $.tip('添加至收藏夹调用ajax失败');
            }
        });
    });

    //收藏夹隐私设置切换
    $(document).on('click','.switcher',function () {
        var parent = $(this).parents('.switcher-container');
        if(parent.hasClass('switcher-on')){
            parent.removeClass('switcher-on');
            parent.addClass('switcher-off');
        }else {
            parent.removeClass('switcher-off');
            parent.addClass('switcher-on');
        }
    });

    //取消收藏夹编辑
    $(document).on('click','.modal-header-close,.btn.default',function () {
        $('.modal-container').remove();
    });

    //收藏夹名获得焦点显示长度提示
    $(document).on('focus','#fav-name',function () {
        var count = $('#fav-name').val().length;
        $('#letter-count').text(count);
        $('.letter-count').css('display','block');
    });

    //实时统计收藏夹编辑输入名称长度
    $(document).on('keyup','#fav-name',function () {
        var count = $('#fav-name').val().length;
        $('#letter-count').text(count);
    });

    //收藏夹名丢失焦点关闭长度提示
    $(document).on('blur','#fav-name',function () {
        $('.letter-count').css('display','none');
    });

    //收藏夹编辑提交
    $(document).on('click','.btn.primary.edit',function () {
        var fav = $(this).data('id');
        var name = $('#fav-name').val();
        var privacy = '1';  //默认公开
        if($('.switcher-container').hasClass('switcher-off')){
            privacy = '0';  //设为私密
        }
        var url = $.getPath() + '/favorite/commitEdit';
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                favoriteId: fav,
                name: name,
                privacy: privacy
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                $('.modal-container').remove();//移除编辑框
                if(data == '编辑成功'){
                    $('.fav-item').each(function () {
                        //查找出编辑的收藏夹，修改名字和隐私级别
                        if($(this).data('fav') == fav){
                            $(this).find('.name').attr('title',name);
                            $(this).find('.name').text(name);
                            if(privacy == '1'){
                                $(this).find('.state').text('公开');
                            }else {
                                $(this).find('.state').text('私密');
                            }
                        }
                    });
                }
                $.tip(data);
            },
            error: function () {
                $.tip('收藏夹编辑调用ajax失败');
            }
        });
    });

    //收藏夹删除
    $(document).on('click','.delete.icon',function () {
        var item = $(this).parents('.fav-item'); //删除的收藏夹项
        var fav = item.data('fav');
        var url = $.getPath() + '/favorite/deleteById';
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                favoriteId: fav
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(data == '删除成功'){
                    item.remove();
                }
                $.tip(data);
            },
            error: function () {
                $.tip('删除收藏夹调用ajax失败');
            }
        });
    });

    //收藏夹新建框显示
    $('.create-covers-meta').click(function () {
        var str = '';
        str += '<div class="modal-container">';
        str += '<div class="modal-mask"></div>';
        str += '<div class="modal-wrapper" style="width: 300px;">';
        str += '<div class="modal">';
        str += '<div class="modal-header">';
        str += '<i class="modal-header-close ion-close-round"></i>';
        str += '<span class="modal-title"> 新建收藏夹 </span>';
        str += '</div>';
        str += '<div class="modal-body">';
        str += '<div class="sp-input">';
        str += '<input id="fav-name" type="text" maxlength="15">';
        str += '<div class="letter-count" style="display: none;"><span id="letter-count">0</span>/15</div>';
        str += '</div>';
        str += '<div class="switcher-container switcher-on">';
        str += '<div class="switcher">';
        str += '<i class="cursor"></i>';
        str += '</div>';
        str += '<div class="label">';
        str += '<span>公开该收藏夹</span>';
        str += '</div>';
        str += '</div>';
        str += '</div>';
        str += '<div class="modal-footer">';
        str += '<a class="btn primary new">';
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
    });

    //收藏夹新建提交
    $(document).on('click','.btn.primary.new',function () {
        var name = $('#fav-name').val();
        var privacy = '1';  //默认公开
        if($('.switcher-container').hasClass('switcher-off')){
            privacy = '0';  //设为私密
        }
        var url = $.getPath() + '/favorite/add';
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                userId: uid,
                name: name,
                privacy: privacy
            },
            dataType: "json",
            success: function (data) { //请求成功时执行操作
                $('.modal-container').remove();//移除编辑框
                if(data.msg == '新增成功'){
                    var new_fav = data.obj;
                    var str = '<div class="fav-item" data-fav="'+new_fav.fid+'">';
                    str += '<a class="fav-covers" href="/video_ssm/space/Zero/favorite_item?fid='+new_fav.fid+'">';
                    str += '</a>';
                    str += '<span class="fav-count">0</span>';
                    str += '<div class="m">';
                    str += '<span class="state">';
                    if(new_fav.fprivacy == '1'){
                        str += '公开';
                    }else {
                        str += '私密';
                    }
                    str += '</span>';
                    str += '<a class="name" title="'+new_fav.fname+'" href="/video_ssm/space/Zero/favorite_item?fid='+new_fav.fid+'">'+new_fav.fname+'</a>';
                    str += '</div>';
                    str += '<div class="fav-action">';
                    str += '<span class="delete icon">删除</span>';
                    str += '<span class="edit icon">编辑</span>';
                    str += '</div>';
                    str += '</div>';
                    $('.fav-item.fav-create.list-create').before(str);
                }
                $.tip(data.msg);
            },
            error: function () {
                $.tip('新建收藏夹调用ajax失败');
            }
        });
    });

})