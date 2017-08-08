/**
 * Created by Zero on 2017/3/27.
 */
$(function () {
    //---------------------  评论换页模块  -------------------
    //用于评论翻页处理
    function commentTurn(url, pageNo) {
        $.ajax({
            url: url, //请求验证页面
            type: "POST", //请求方式 可换为post 注意验证页面接收方式
            data: {
                videoId: videoId,
                pageNo: pageNo
            },
            dataType: "json",
            success: function (data) { //请求成功时执行操作
                if(data != "") {
                    var commentList = data.list;
                    $('.header-page.paging-box').empty();
                    $('.comment-list').empty();
                    $('.bottom-page.paging-box-big').empty();
                    var headerPageStr = ""; //顶部分页html元素
                    var commentListStr = "";  //评论列表html元素
                    var bottomPageStr = ""; //底部分页html元素

                    //---------------------    顶部分页    --------------------
                    headerPageStr += '<span class="result">共' + data.totalPage + '页</span>';
                    if(data.hasPreviousPage === true){
                        headerPageStr += '<span class="prev" data-page="'+(data.currentPage-1)+'">上一页</span>';
                    }
                    for(var i = 1; i <= data.totalPage; i++){
                        if(pageNo == i){
                            headerPageStr += '<span class="current" data-page="'+i+'">'+i+'</span>';
                        }else{
                            headerPageStr += '<a href="javascript:;" class="tcd-number" data-page="'+i+'">'+i+'</a>';
                        }
                    }
                    if(data.hasNextPage === true){
                        headerPageStr += '<span class="next" data-page="'+(data.currentPage+1)+'">下一页</span>';
                    }
                    $('.header-page.paging-box').append(headerPageStr);
                    //---------------------    顶部分页    --------------------

                    //---------------------    评论列表    --------------------
                    $.each(commentList, function (index, commentItem) {
                        commentListStr += '<div class="list-item reply-wrap" data-comment="'+commentItem.cid+'">';
                        commentListStr += '<div class="user-face">';
                        commentListStr += '<a href="'+$.getPath()+'/space/'+commentItem.userBO.uid+'" target="_blank">';
                        commentListStr += '<img src="'+$.getPath()+'/images/'+commentItem.userBO.uimg+'">';
                        commentListStr += '</a>';
                        commentListStr += '</div>';
                        commentListStr += '<div class="con">';
                        commentListStr += '<div class="user">';
                        commentListStr += '<a href="'+$.getPath()+'/space/'+commentItem.userBO.uid+'" target="_blank" class="name">';
                        if(commentItem.userBO.uname != null){
                            commentListStr += commentItem.userBO.uname;
                        }else {
                            commentListStr += commentItem.userBO.uid;
                        }
                        commentListStr += '</a>';
                        commentListStr += '</div>';
                        commentListStr += '<p class="text">'+commentItem.ctext+'</p>';
                        commentListStr += '<div class="info" style="width: 100%">';
                        commentListStr += '<span class="time">'+commentItem.cdate+'</span>';
                        commentListStr += '<span class="like">';
                        commentListStr += '<i></i>(<span class="agree-num">'+ commentItem.cagree +'</span>)';
                        commentListStr += '</span>';
                        commentListStr += '<span class="reply btn-hover btn-highlight">参与回复</span>';
                        if(commentItem.userBO.uid == userId){
                            commentListStr += '<span class="del btn-hover del-comment" data-id="'+commentItem.cid+'">删除</span>';
                        }
                        commentListStr += '</div>';
                        commentListStr += '<div class="reply-box" id="reply'+commentItem.cid+'">';
                        var replyList = commentItem.replyPage.list;
                        $.each(replyList, function (index, replyItem) {
                            commentListStr += '<div class="reply-item reply-wrap" data-reply="'+replyItem.rid+'">';
                            commentListStr += '<a href="'+$.getPath()+'/space/'+replyItem.userBO.uid+'" target="_blank" class="reply-face">';
                            commentListStr += '<img src="'+$.getPath()+'/images/'+replyItem.userBO.uimg+'" alt="">';
                            commentListStr += '</a>';
                            commentListStr += '<div class="reply-con">';
                            commentListStr += '<div class="user">';
                            commentListStr += '<a href="'+$.getPath()+'/space/'+replyItem.userBO.uid+'" target="_blank" class="name">';
                            if(replyItem.userBO.uname != null){
                                commentListStr += replyItem.userBO.uname + ' : ';
                            }else {
                                commentListStr += replyItem.userBO.uid + ' : ';
                            }
                            commentListStr += '</a>';
                            commentListStr += '<span class="text-con" style="margin-left: 10px;">'+replyItem.rtext+'</span>';
                            commentListStr += '</div>';
                            commentListStr += '<div class="info">';
                            commentListStr += '<span class="time">'+replyItem.rdate+'</span>';
                            commentListStr += '<span class="like "><i></i>';
                            commentListStr += '(<span class="agree-num">'+ replyItem.ragree +'</span>)';
                            commentListStr += '</span>';
                            commentListStr += '<span class="reply btn-hover">回复</span>';
                            if(replyItem.userBO.uid == userId){
                                commentListStr += '<span class="del btn-hover del-reply" data-id="'+replyItem.rid+'">删除</span>';
                            }
                            commentListStr += '</div>';
                            commentListStr += '</div>';
                            commentListStr += '</div>';
                        })
                        commentListStr += '<div class="paging-box" style="margin-top: 20px;"></div>';
                        commentListStr += '</div>';
                        if(commentItem.replyPage.totalPage > 1){
                            commentListStr += '<div class="paging-box reply">';
                            commentListStr += '<span class="result">共'+commentItem.replyPage.totalPage+'页</span>';
                            if(commentItem.replyPage.hasPreviousPage == true){
                                commentListStr += '<span class="prev" data-page="'+(commentItem.replyPage.currentPage-1)+'>上一页</span>';
                            }
                            for(var i = 1; i <= commentItem.replyPage.totalPage; i++){
                                if(i == commentItem.replyPage.currentPage){
                                    commentListStr += '<span class="current">'+i+'</span>';
                                }else {
                                    commentListStr += '<a href="javascript:;" class="tcd-number" data-page="'+i+'">'+i+'</a>';
                                }
                            }
                            if(commentItem.replyPage.hasNextPage == true){
                                commentListStr += '<span class="next" data-page="'+(commentItem.replyPage.currentPage+1)+'">下一页</span>';
                            }
                            commentListStr += '</div>';
                        }
                        commentListStr += '</div>';
                        commentListStr += '</div>';
                    })
                    $('.comment-list').append(commentListStr);
                    //---------------------    评论列表    --------------------

                    //---------------------    底部分页    --------------------
                    if(data.hasPreviousPage === true){
                        bottomPageStr += '<span class="prev" data-page="'+(data.currentPage-1)+'">上一页</span>';
                    }
                    for(var i = 1; i <= data.totalPage; i++){
                        if(pageNo == i){
                            bottomPageStr += '<span class="current" data-page="'+i+'">'+i+'</span>';
                        }else{
                            bottomPageStr += '<a href="javascript:;" class="tcd-number" data-page="'+i+'">'+i+'</a>';
                        }
                    }
                    if(data.hasNextPage === true){
                        bottomPageStr += '<span class="next" data-page="'+(data.currentPage+1)+'">下一页</span>';
                    }
                    bottomPageStr += '<div class="page-jump" style="width: 150px;height: 38px">';
                    bottomPageStr += '共<span>'+data.totalPage+'</span>页，跳至<input type="text" id="pageNo" style="width: 46px;display: inline-block">页';
                    bottomPageStr += '</div>';
                    $('.bottom-page.paging-box-big').append(bottomPageStr);
                    //---------------------    底部分页    --------------------
                }else{
                    $.tip("获取数据为空");
                }
            },
            error: function () {
                $.tip("翻页ajax调用失败");
            }
        });
    }

    //用于回复翻页处理
    function replyTurn(url, pageNo, commentId){
        $.ajax({
            url: url, //请求验证页面
            type: "POST", //请求方式 可换为post 注意验证页面接收方式
            data: {
                commentId: commentId,
                pageNo: pageNo
            },
            dataType: "json",
            success: function (data) { //请求成功时执行操作
                if(data != "") {
                    var replyList = data.list;
                    $('#reply'+commentId).empty();
                    $('.paging-box.reply').empty();
                    var replyListStr = "";  //回复列表html元素
                    var replyPageStr = "";  //回复页面html元素
                    //---------------------    回复列表    --------------------
                    $.each(replyList, function (index, replyItem) {
                        replyListStr += '<div class="reply-item reply-wrap" data-reply="'+replyItem.rid+'">';
                        replyListStr += '<a href="'+$.getPath()+'/space/'+replyItem.userBO.uid+'" target="_blank" class="reply-face">';
                        replyListStr += '<img src="'+$.getPath()+'/images/'+replyItem.userBO.uimg+'" alt="">';
                        replyListStr += '</a>';
                        replyListStr += '<div class="reply-con">';
                        replyListStr += '<div class="user">';
                        replyListStr += '<a href="'+$.getPath()+'/space/'+replyItem.userBO.uid+'" target="_blank" class="name">';
                        if(replyItem.userBO.uname != null){
                            replyListStr += replyItem.userBO.uname + ' : ';
                        }else {
                            replyListStr += replyItem.userBO.uid + ' : ';
                        }
                        replyListStr += '</a>';
                        replyListStr += '<span class="text-con" style="margin-left: 10px;">'+replyItem.rtext+'</span>';
                        replyListStr += '</div>';
                        replyListStr += '<div class="info">';
                        replyListStr += '<span class="time">'+replyItem.rdate+'</span>';
                        replyListStr += '<span class="like "><i></i>';
                        replyListStr += '(<span class="agree-num">'+ replyItem.ragree +'</span>)';
                        replyListStr += '</span>';
                        replyListStr += '<span class="reply btn-hover">回复</span>';
                        if(replyItem.userBO.uid == userId){
                            replyListStr += '<span class="del btn-hover del-reply" data-id="'+replyItem.rid+'">删除</span>';
                        }
                        replyListStr += '</div>';
                        replyListStr += '</div>';
                        replyListStr += '</div>';
                    })
                    $('#reply'+commentId).append(replyListStr);
                    //---------------------    回复列表    --------------------

                    //---------------------    回复页面    --------------------
                    replyPageStr += '<span class="result">共'+data.totalPage+'页</span>';
                    if(data.hasPreviousPage == true){
                        replyPageStr += '<span class="prev" data-page="'+(data.currentPage-1)+'">上一页</span>';
                    }
                    for(var i = 1; i <= data.totalPage; i++){
                        if(i == data.currentPage){
                            replyPageStr += '<span class="current">'+i+'</span>';
                        }else {
                            replyPageStr += '<a href="javascript:;" class="tcd-number" data-page="'+i+'">'+i+'</a>';
                        }
                    }
                    if(data.hasNextPage == true){
                        replyPageStr += '<span class="next" data-page="'+(data.currentPage+1)+'">下一页</span>';
                    }
                    $('.paging-box.reply').append(replyPageStr);
                    //---------------------    回复页面    --------------------
                }else{
                    $.tip("获取数据为空");
                }
            },
            error: function () {
                $.tip("翻页ajax调用失败");
            }
        });
    }

    //换页点击事件
    $(Document).on('click','.tcd-number,.prev,.next',function () {
        var parent = String( $(this).parent().attr('class') );
        var url = $.getPath();
        var pageNo = $(this).data("page");
        if(parent == 'paging-box reply'){  //回复翻页
            var commentId = $(this).parent().parent().parent().data('comment')
            url += "/comment/getReplyList";
            replyTurn(url, pageNo, commentId);
        }else {   //评论翻页
            url += "/comment/getCommentList";
            commentTurn(url, pageNo);
        }
    })

    //输入框换页
    $(Document).on('keydown','#pageNo',function (event) {
        if(event.keyCode == 13) {
            var url = $.getPath() + "/comment/getCommentList";
            var pageNo = $("#pageNo").val();
            commentTurn(url, pageNo);
        }
    })
    //---------------------  评论换页模块  -------------------

    //---------------------  评论综合模块  -------------------
    //显示回复框
    $(Document).on('click','.reply.btn-hover',function (){
        //先清空已打开的回复框状态
        $('.list-item.reply-wrap.open-reply').each(function () {
            if($(this).hasClass('open-reply')){
                $(this).removeClass('open-reply');
                $(this).find('.comment-send').remove();
            }
        })
        //查找要添加回复框的父元素，给个类做标识
        var parent = $(this).parents('.list-item.reply-wrap');
        parent.addClass('open-reply')
        var reply = '';  //回复框的html
        reply += '<div class="comment-send">';
        reply += '<div class="user-face">';
        if(userId != ''){
            reply += '<img class="user-head" src="'+$.getPath()+'/images/'+userImg+'">';
        }else {
            reply += '<img class="user-head" src="'+$.getPath()+'/images/user/default.jpg">';
        }
        reply += '</div>';
        reply += '<div class="textarea-container">';
        reply += '<i class="ipt-arrow"></i>';
        reply += '<textarea cols="80" name="msg" rows="5" class="ipt-txt" ';
        reply += 'placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。">';
        if(!$(this).hasClass('btn-highlight')){
            var name = $(this).parents('.reply-con').find('.name').html().trim();
            reply += '回复 @'+ name + ' ';
        }
        reply += '</textarea>';
        reply += '<button type="submit" class="comment-submit" style="margin-top: 10px;">发表评论</button>';
        reply += '</div>';
        reply += '</div>';
        parent.children('.con').append(reply);
        //让输入框获取焦点，可以使当前窗口定位到输入框
        parent.find('textarea').focus();
    })

    //提交评论/回复
    $(Document).on('click','.comment-submit',function (){
        if(userId != '') {
            var textObj = $(this).prev();   //输入框jquery对象
            var commentId = '';     //评论编号，用于插入回复
            var comment = $(this).parents('.list-item.reply-wrap');
            var isReply = comment.hasClass('list-item'); //有则说明是某个评论下的回复框
            if(isReply){
                commentId = comment.data('comment');
            }
            var text = textObj.val();   //原始输入文本
            var str = text.trim();    //用于匹配正则表达式的字符串
            var pos = text.indexOf(":");   //获取冒号位置
            if(pos != -1){          //不为-1表示有冒号
                str = text.substring(pos + 2).trim();   //记得用trim去掉空白字符
            }
            var reg = /^.{3,}$/;   //字符长度大于3
            if(reg.test(str)){
                var url = $.getPath();
                textObj.val("");
                if(isReply){
                    url += '/comment/insertReply';
                    replyCommit(url, videoId, commentId, userId, text);
                }else{
                    url += '/comment/insertComment';
                    commentCommit(url, videoId, userId, text);
                }
            }else {
                $.tip('输入的内容不能少于3个字符');
            }
        }else {
            $.tip('用户未登录，请先登录后才能评论');
        }
    })

    //评论提交ajax
    function commentCommit(url, videoId, userId, text) {
        $.ajax({
            url: url, //请求验证页面
            async: false, //同步请求,避免异步请求导致多次提交评论
            type: "POST", //请求方式 可换为post 注意验证页面接收方式
            data: {
                videoId: videoId,
                userId: userId,
                text: text
            },
            dataType: "json",
            success: function (data) {
                if(data != null){
                    var newComment = '';
                    newComment += '<div class="list-item reply-wrap" data-comment="'+data.cid+'">';
                    newComment += '<div class="user-face">';
                    newComment += '<a href="'+$.getPath()+'/space/'+data.userBO.uid+'" target="_blank"><img src="'+$.getPath()+'/images/'+ data.userBO.uimg +'"></a>';
                    newComment += '</div>';
                    newComment += '<div class="con">';
                    newComment += '<div class="user">';
                    newComment += '<a href="'+$.getPath()+'/space/'+data.userBO.uid+'" target="_blank" class="name">';
                    if(data.userBO.uname != null){
                        newComment += data.userBO.uname + '&nbsp;:&nbsp;';
                    }else {
                        newComment += data.userBO.uid + '&nbsp;:&nbsp;';
                    }
                    newComment += '</a>';
                    newComment += '</div>';
                    newComment += '<p class="text">'+ data.ctext +'</p>';
                    newComment += '<div class="info" style="width: 100%">';
                    newComment += '<span class="time">'+ data.cdate +'</span>';
                    newComment += '<span class="like"><i></i>(<span class="agree-num">'+ data.cagree +'</span>)</span>';
                    newComment += '<span class="reply btn-hover btn-highlight">参与回复</span>';
                    if(data.userBO.uid == userId){
                        newComment += '<span class="del btn-hover del-comment" data-id="'+data.cid+'">删除</span>';
                    }
                    newComment += '</div>';
                    newComment += '<div class="reply-box" id="reply'+ data.cid +'">';
                    newComment += '<div class="paging-box" style="margin-top: 20px;">';
                    newComment += '</div>';
                    newComment += '</div>';
                    newComment += '</div>';
                    newComment += '</div>';
                    $('.comment-list').append(newComment);
                    $('.results').text(parseInt($('.results').text()) + 1);
                }else {
                    $.tip("评论失败");
                }
            },
            error: function () {
                $.tip("插入评论ajax调用失败");
            }
        });
    }

    //回复提交ajax
    function replyCommit(url, videoId, commentId, userId, text) {
        $.ajax({
            url: url, //请求验证页面
            async: false, //同步请求,避免异步请求导致多次提交评论
            type: "POST", //请求方式 可换为post 注意验证页面接收方式
            data: {
                videoId: videoId,
                commentId: commentId,
                userId: userId,
                text: text
            },
            dataType: "json",
            success: function (data) {
                if(data != null){
                    var newReply = '';
                    newReply += '<div class="reply-item reply-wrap" data-reply="'+ data.rid +'">';
                    newReply += '<a href="'+$.getPath()+'/space/'+data.userBO.uid+'" target="_blank" class="reply-face">';
                    newReply += '<img src="'+ $.getPath() +'/images/'+ data.userBO.uimg +'">';
                    newReply += '</a>';
                    newReply += '<div class="reply-con">';
                    newReply += '<div class="user">';
                    newReply += '<a href="'+$.getPath()+'/space/'+data.userBO.uid+'" target="_blank" class="name">';
                    if(data.userBO.uname != null){
                        newReply += data.userBO.uname + '&nbsp;:&nbsp;';
                    }else {
                        newReply += data.userBO.uid + '&nbsp;:&nbsp;';
                    }
                    newReply += '</a>';
                    newReply += '<span class="text-con" style="margin-left: 10px;">'+ data.rtext +'</span>';
                    newReply += '</div>';
                    newReply += '<div class="info">';
                    newReply += '<span class="time">'+ data.rdate +'</span>';
                    newReply += '<span class="like "><i></i>(<span class="agree-num">'+ data.ragree +'</span>)</span>';
                    newReply += '<span class="reply btn-hover">回复</span>';
                    if(data.userBO.uid == userId){
                        newReply += '<span class="del btn-hover del-reply" data-id="'+data.rid+'">删除</span>';
                    }
                    newReply += '</div>';
                    newReply += '</div>';
                    newReply += '</div>';
                    $('#reply' + data.commentId).append(newReply);
                    $('.results').text(parseInt($('.results').text()) + 1);
                }else {
                    $.tip("回复失败");
                }
            },
            error: function () {
                $.tip("插入评论ajax调用失败");
            }
        });
    }

    //评论/回复删除
    $(Document).on('click','.del.btn-hover',function (){
        var obj = $(this);
        //防止不小心点到，弹出确认框，若取消，则执行中止
        var dialog = jDialog.confirm('确定删除该评论吗',{
            handler : function(button,dialog) {
                del(obj);
                dialog.close();
            }
        },{
            handler : function(button,dialog) {
                dialog.close();
            }
        });
    });

    function del(obj) {
        var url = $.getPath();
        if(obj.hasClass('del-comment')){  //删除评论
            var commentId = obj.data('id');
            url += '/comment/delComment/'+commentId;
        }else {     //删除回复
            var replyId = obj.data('id');
            var commentId = obj.parents('.list-item.reply-wrap').data('comment');
            url += '/comment/'+commentId+'/delReply/'+replyId;
        }
        $.ajax({
            url: url, //请求验证页面
            type: "post", //请求方式 可换为post 注意验证页面接收方式
            data: {videoId: videoId},
            dataType: "text",
            success: function (data) { //请求成功时执行操作
                if(data > 0){
                    obj.parent().parent().parent().remove();
                    $('.results').text(parseInt($('.results').text()) - data);
                }else {
                    $.tip('删除失败');
                }
            },
            error: function () {
                $.tip('删除评论调用ajax失败');
            }
        });
    }
    //---------------------  评论模块  -------------------

})