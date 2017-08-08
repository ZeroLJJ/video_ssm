/**
 * Created by Zero on 2017/4/21.
 */

$(function () {
    //初始化
    displyTag();
    //添加标签输入框显示
    $(".btn-add").click(function () {
        $(this).css({
            'display' : 'none'
        });
        $(".ipt").css({
            'display' : 'block',
            'opacity' : '1',
            'width' : '158px'
        });
    })

    //添加标签输入框关闭
    $(".btn-close").click(function () {
        btnClose();
    });

    //关闭函数
    function btnClose() {
        $("#tag-text").val("");
        $(".btn-add").css({
            'display' : 'block'
        });
        $(".ipt").css({
            'display' : 'none'
        });
    }

    //新增标签
    $(Document).on('keydown','#tag-text',function (event) {
        if(event.keyCode == 13){
            var url = $.getPath() + '/tag/add';
            var name = $('#tag-text').val();
            $.ajax({
                url: url, //请求验证页面
                type: "post", //请求方式 可换为post 注意验证页面接收方式
                data:{
                    name: name,
                    userId: userId,
                    videoId: videoId
                },
                dataType: "json",
                success: function (data) { //请求成功时执行操作
                    if(data.message == '新增成功'){
                        var str = '';
                        var tagItem = data.object;   //获取标签详情对象
                        var num = 1;   //标志第几个tag
                        $('.tag').each(function () {
                            num++;
                        });
                        str += '<li class="tag">';
                        str += '<a href="'+$.getPath()+'/video/all?tag='+tagItem.tagBO.tid+'" target="_blank">'+tagItem.tagBO.tname+'</a>';
                        str += '<div data-tag-item="'+tagItem.tiid+'" data-tag="'+tagItem.tagBO.tid+'" class="tag-info-pane">';
                        str += '<div class="tag-header clearfix">';
                        str += '<div class="tag-title">'+tagItem.tagBO.tname+'</div>';
                        str += '<a class="btn-sub btn-subscribe" style="display: inline;">+ 关注</a>';
                        str += '<a class="btn-sub btn-unsubscribe">已关注</a>';
                        str += '</div>';
                        str += '<div class="btn-right-box">';
                        str += '<a class="btn-crown">';
                        str += '<div class="like">';
                        str += '<i></i>顶 <span class="agree-num">'+tagItem.tiagree+'</span>';
                        str += '</div>';
                        str += '</a>';
                        str += '</div>';
                        str += '<div class="tag-footer clearfix">';
                        str += '<div class="btn-left-box">';
                        str += '<a class="btn-close" style="padding:0 6px 10px 6px;margin-top: 5px;">删除</a>';
                        str += '</div>';
                        str += '</div>';
                        str += '</div>';
                        str += '</li>';
                        $('.btn-add').before(str);  //添加元素
                        btnClose();  //关闭输入框
                        displyTag();
                    }else {
                        $.tip(data.message);
                    }
                },
                error: function () {
                    $.tip('新增标签调用ajax失败');
                }
            });
        }
    });

    //显示标签操作菜单
    //加一层window.load可以防止图片或视频未渲染完，导致offset获取的偏移坐标错误）
    //以下采用position:absolute相对父元素定位，所以不用offset偏移坐标
    function displyTag() {
        $(".tag").each(function () {
            var obj = $(this).find(".tag-info-pane");
            $(this).hoverDelay({
                hoverDuring: 500,
                outDuring: 500,
                hoverEvent: function(){
                    obj.css('display', 'block');
                },
                outEvent: function(){
                    obj.css('display', 'none');
                }
            })
        });
    }

    //标签关注与取消关注
    $('.s_tag').on('click','.btn-sub',function () {
        if(userId == ''){
            $.tip('请登录后再进行该操作');
            return false;
        }
        var url = $.getPath() + '/tag/';
        var pane = $(this).parents('.tag-info-pane');  //获取某个标签的pane容器
        var tagId = pane.data('tag');
        if($(this).hasClass('btn-subscribe')){
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
                    pane.find('.btn-sub.btn-subscribe').css('display', 'none');
                    pane.find('.btn-sub.btn-unsubscribe').css('display', 'inline');
                }else if(data == '取消关注成功'){
                    pane.find('.btn-sub.btn-subscribe').css('display', 'inline');
                    pane.find('.btn-sub.btn-unsubscribe').css('display', 'none');
                }
                $.tip(data);
            },
            error: function () {
                $.tip('标签关注调用ajax失败');
            }
        });
    });

    //标签删除
    $('.s_tag').on('click','.btn-close',function () {
        var pane = $(this).parents('.tag-info-pane');  //获取某个标签的pane容器
        //防止不小心点到，弹出确认框，若取消，则执行中止
        var dialog = jDialog.confirm('确定删除该标签吗',{
            handler : function(button,dialog) {
                del(pane);
                dialog.close();
            }
        },{
            handler : function(button,dialog) {
                dialog.close();
            }
        });
    });

    function del(pane) {
        var url = $.getPath() + '/tag/delete';
        var tagItemId = pane.data('tag-item');
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data:{
                tagItemId: tagItemId
            },
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(data == '删除成功'){
                    pane.parent().remove();
                }else {
                    $.tip(data);
                }
            },
            error: function () {
                $.tip('标签删除调用ajax失败');
            }
        });
    }

})