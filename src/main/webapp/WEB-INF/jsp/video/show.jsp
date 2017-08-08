<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/3/17
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    //用来表示当前web工程的相对路径，即webapp下的根目录
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>视频展示</title>
    <!-- 自编 -->
    <link rel="stylesheet" href="<%=path%>/css/base.css">
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="<%=path%>/templete/video/css/bootstrap.min.css"  type="text/css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%=path%>/templete/video/css/style.css">
    <!-- bilibili -->
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/page-core.css">
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/tag-index2.0.css">
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/comment.css">
    <!-- Custom Fonts -->
    <link rel="stylesheet" href="<%=path%>/templete/video/font-awesome-4.4.0/css/font-awesome.min.css"  type="text/css">
    <!-- 弹幕 -->
    <link href="<%=path%>/templete/danmu/css/scojs.css" rel="stylesheet">
    <link href="<%=path%>/templete/danmu/css/colpick.css" rel="stylesheet">
    <link href="<%=path%>/templete/danmu/css/main.css" rel="stylesheet">
    <!-- 对话框插件 -->
    <link rel="stylesheet" href="<%=path%>/templete/jDialog/jDialog/jDialog.css">

    <style>
        .info span {
            margin-right: 0;
        }

        /* 弹幕播放按钮 */
        .glyphicon {
            padding-top: 9px;
        }

        .tag-info-pane {
            display: none;
            position: absolute;
            left : 0px;
            top : 30px;
            width : 340px;
        }
    </style>
</head>
<body>
<!-- Header -->
<nav id="top" style="color: #666;background: #fff;font-size: 16px;line-height: 1.6em;font-weight: 400;height: 100px;">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-6" style="padding-top: 10px;">
                <strong>欢迎来到视频分享网站!</strong>
            </div>
            <div class="col-md-6 col-sm-6">
                <ul class="nav nav-pills navbar-right">
                    <c:choose>
                        <c:when test="${empty user}">
                            <li><a target="_blank" href="<%=path%>/login"><i class="fa fa-power-off" style="color: rgba(17, 90, 255, 0.64);"></i> 登录</a></li>
                            <li><a target="_blank" href="<%=path%>/register"><i class="fa fa-user-plus" style="color: rgba(17, 90, 255, 0.64);"></i> 注册</a></li>
                        </c:when>
                        <c:when test="${not empty user}">
                            <li class="dropdown" id="user-menu" style="postion:relative;z-index:1000;">
                                <a target="_blank" href="<%=path%>/space/${user.uid}" class="dropdown-toggle"  data-toggle="dropdown" style="padding: 10px 15px 0 15px">
                                    <img id="userImg" src="<%=path%>/images/${user.uimg}" height="32px" width="32px"
                                         style="transition: all 0.5s;border-radius: 50%;border: 1px solid #F36;">
                                </a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="margin-right: -50px; width: 230px;">
                                    <li class="dropdown-header" style="text-align: center;color: #000;font-weight: bold;">
                                        <c:choose>
                                            <c:when test="${user.uname != null}">
                                                ${user.uname}
                                            </c:when>
                                            <c:when test="${user.uid != null}">
                                                ${user.uid}
                                            </c:when>
                                        </c:choose>
                                    </li>
                                    <li class="divider"></li>
                                    <li style="float:left;width: 110px">
                                        <a href="<%=path%>/user/home" style="padding: 0 10px;margin: 5px">
                                            <i class="b-icon b-icon-p-account"></i>
                                            个人中心
                                        </a>
                                    </li>
                                    <li style="float:left;width: 110px">
                                        <a href="<%=path%>/post" style="padding: 0 10px;margin: 5px">
                                            <i class="b-icon b-icon-p-member"></i>
                                            投稿管理
                                        </a>
                                    </li>
                                    <li style="float:left;width: 110px">
                                        <a href="<%=path%>/space/${user.uid}/favorite" style="padding: 0 10px;margin: 5px">
                                            <i class="b-icon b-icon-p-wallet"></i>
                                            收藏夹
                                        </a>
                                    </li>
                                    <li style="float:left;width: 110px">
                                        <a href="<%=path%>/user/${user.uid}/history" style="padding: 0 10px;margin: 5px">
                                            <i class="b-icon b-icon-p-live"></i>
                                            播放记录
                                        </a>
                                    </li>
                                    <li class="divider" style="clear: left"></li>
                                    <li style="float:right;">
                                        <a href="<%=path%>/exit"><i class="fa fa-power-off"></i> 退出</a>
                                    </li>
                                </ul>
                            </li>
                        </c:when>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</nav>

<header style="color: #666;font-size: 16px;line-height: 1.6em;font-weight: 400;">
    <div class="header">
        <div class="num" style="background-color: rgba(53, 26, 206, 0.40)">
            <div class="menu-wrapper">
                <ul class="nav-menu">
                    <li class="m-i home" style="margin: 0 3px 0 -20px;">
                        <a href="<%=path%>/" class="i-link"><em>首页</em></a>
                    </li>
                    <!-- 菜单栏（视频类型） -->
                    <c:forEach items="${videoType}" var="bigType">
                        <li class="m-i" style="margin: 0 3px;">
                            <a href="<%=path%>/video/all?bigType=${bigType.bid}" class="i-link"  style="width: 50px">
                                <em>${bigType.bname}</em>
                                <div class="v-num">
                                    <span class="addnew_1" style="height: 1.2em;width: 2.2em;">${bigType.countRecentlyPost}</span>
                                </div>
                            </a>
                            <ul class="i_num">
                                <c:forEach items="${bigType.smallTypeBOList}" var="smallType">
                                    <li>
                                        <a href="<%=path%>/video/all?smallType=${smallType.sid}">
                                            <b>${smallType.sname}<em></em></b>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
                <div class="search" style="width: 342px;height: 36px;position: absolute;top:10px;left:700px;">
                    <div id="searchform" target="_blank" style="width: 268px;height: 32px">
                        <input name="keyword" type="text" class="search-keyword" autocomplete="off" placeholder="你不知道的100多件幻想乡秘闻"
                               style="position: relative;bottom: 10px;width: 224px;height: 32px">
                        <button type="submit" class="search-submit" style="width: 48px;height: 32px"></button>
                    </div>
                    <a class="link-ranking" href="<%=path%>/video/rank" target="_blank">
                        <span style="font-size: 12px;">排行榜</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header -->

<!-- /////////////////////////////////////////Content -->
<p style="height: 10px"></p>
<div id="page-content" class="single-page">
    <div class="container">
        <div class="row">
            <div id="main-content" class="col-md-8">
                <div class="viewbox" style="margin-top: -10px;">
                    <div class="info" style="height: 120px;">
                        <div class="v-title">
                            <h1 title="${videoDetail.vname}" style="height: 40px;margin-left: 6px;">
                                ${videoDetail.vname}
                            </h1>
                        </div>
                        <div class="arcrank"></div>
                        <div class="tminfo" style="height: 40px;padding-left: 6px;">
                            <a href="<%=path%>/" rel="v:url" property="v:title">主页</a> &gt;
                            <span>
                                <a href="<%=path%>/video/all?bigType=${videoDetail.bigTypeBO.bid}">
                                    ${videoDetail.bigTypeBO.bname}
                                </a>
                            </span> &gt;
                            <span>
                                <a href="<%=path%>/video/all?smallType=${videoDetail.smallTypeBO.sid}">
                                    ${videoDetail.smallTypeBO.sname}
                                </a>
                            </span>
                            <time><i><fmt:formatDate value="${videoDetail.vaddTime}" type="both"/></i></time>
                            <div class="v-title-info" style="height: 28px;padding-top: 6px;">
                                <div class="v-title-line" title="总播放数"><i class="b-icon b-icon-a b-icon-play"></i><span>${videoDetail.vplayTimes}</span></div>
                                <div class="v-title-line" title="总弹幕数"><i class="b-icon b-icon-a b-icon-danmaku"></i><span>${videoDetail.vdanmu}</span></div>
                                <div class="v-title-line v-stow fav_btn" title="收藏人数" style="float: right">
                                    <i class="b-icon b-icon-a b-icon-stow" style="display: block; background-image: url(<%=path%>/templete/bilibili/images/anim-collect.png);transition: height 0.07s"></i>
                                    <span class="stow-status">收藏</span>
                                    <span id="stow_count" style="width: 25px">${videoDetail.vfavoriteTimes}</span>
                                </div>
                                <div class="v-title-line v-coin coin_btn" title="投币个数" style="float: right;">
                                    <i class="b-icon b-icon-a b-icon-coin" style="display: block; background-image: url(<%=path%>/templete/bilibili/images/anim-coin-small.png);transition: height 0.07s"></i>
                                    <span class="coin-status">硬币</span>
                                    <span class="coin">${videoDetail.vscore}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="wrap-vid">
                        <!-- 弹幕视频播放器 -->
                        <div id="danmup"></div>
                    </div>
                    <div class="share">
                        <ul class="list-inline center">
                            <li><a href="#" class="btn btn-facebook"><i class="fa fa-facebook"></i> Share</a></li>
                            <li><a href="#" class="btn btn-twitter"><i class="fa fa-twitter"></i> Tweet</a></li>
                            <li><a href="#" class="btn btn-pinterest"><i class="fa fa-pinterest"></i> Tweet</a></li>
                            <li><a href="#" class="btn btn-google"><i class="fa fa-google-plus-square"></i> Google+</a></li>
                            <li><a href="#" class="btn btn-mail"><i class="fa fa-envelope-o"></i> E-mail</a></li>
                        </ul>
                    </div>
                    <div class="line"></div>
                    <h4>简介：</h4>
                    <p style="margin-top: 20px;white-space: pre-wrap;">${videoDetail.vsummary}</p>
                    <div class="s_tag" style="margin-top: 20px;">
                        <ul class="tag-area clearfix">
                            <c:forEach items="${videoDetail.tagItemBOList}" var="tagItem" varStatus="Status">
                            <li class="tag">
                                <a href="<%=path%>/video/all?tag=${tagItem.tagBO.tid}" target="_blank">${tagItem.tagBO.tname}</a>
                                <div data-tag-item="${tagItem.tiid}" data-tag="${tagItem.tagBO.tid}" class="tag-info-pane">
                                    <div class="tag-header clearfix">
                                        <div class="tag-title">${tagItem.tagBO.tname}</div>
                                        <a class="btn-sub btn-subscribe" <c:if test="${tagItem.tagBO.tagFollowCount == 0}">style="display: inline;"</c:if>>+ 关注</a>
                                        <a class="btn-sub btn-unsubscribe" <c:if test="${tagItem.tagBO.tagFollowCount > 0}">style="display: inline;"</c:if>>已关注</a>
                                    </div>
                                    <div class="btn-right-box">
                                        <a class="btn-crown">
                                            <div class="like">
                                            <i></i>顶 <span class="agree-num">${tagItem.tiagree}</span>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="tag-footer clearfix">
                                        <div class="btn-left-box">
                                            <a class="btn-close" style="padding:0 6px 10px 6px;margin-top: 5px;">删除</a>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            </c:forEach>
                            <a class="btn-add" style="height: 25px;width: 25px;">
                                <span class="one"></span>
                                <span class="two"></span>
                            </a>
                            <div class="ipt" style="width: 25px;">
                                <input type="text" placeholder="按回车键完成输入" id="tag-text"
                                       style="position: relative;bottom: 11px;width: 140px;">
                                <a class="btn-close"></a>
                            </div>
                        </ul>
                        <span id="newtag"></span>
                    </div>
                    <div class="line"></div>
                    <div class="common">
                        <div class="b-head">
                            <span class="b-head-t results" style="display: inline;">${videoDetail.vcomment}</span>
                            <span class="b-head-t">评论</span>
                        </div>
                        <div class="comm" id="bbComment" style="position: relative;">
                            <div class="bb-comment ">
                                <div class="reply-notice"></div>
                                <div class="comment-header clearfix">
                                    <div class="tabs-order">
                                        <ul class="clearfix">
                                            <li class="on" style="height: 37px">全部评论</li>
                                        </ul>
                                    </div>
                                    <div class="header-page paging-box">
                                        <span class="result">共${commentPage.totalPage}页</span>
                                        <c:if test="${commentPage.hasPreviousPage == true}">
                                            <span class="prev" data-page="${commentPage.currentPage-1}">上一页</span>
                                        </c:if>
                                        <c:forEach var="pageNo" begin="1" end="${commentPage.totalPage}">
                                            <c:if test="${pageNo == commentPage.currentPage}">
                                                <span class="current">${pageNo}</span>
                                            </c:if>
                                            <c:if test="${pageNo != commentPage.currentPage}">
                                                <a href="javascript:;" class="tcd-number" data-page="${pageNo}">${pageNo}</a>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${commentPage.hasNextPage == true}">
                                            <span class="next" data-page="${commentPage.currentPage+1}">下一页</span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="comment-send">
                                    <div class="user-face">
                                        <c:if test="${user != null}">
                                            <img class="user-head" src="<%=path%>/images/${user.uimg}">
                                        </c:if>
                                        <c:if test="${user == null}">
                                            <img class="user-head" src="<%=path%>/images/user/default.jpg">
                                        </c:if>
                                    </div>
                                    <div class="textarea-container">
                                        <i class="ipt-arrow"></i>
                                        <textarea cols="80" name="msg" rows="5" placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。" class="ipt-txt"></textarea>
                                        <button type="submit" class="comment-submit" style="margin-top: 10px;top: 0">发表评论</button>
                                    </div>
                                </div>
                                <div class="comment-list">
                                    <c:forEach items="${commentPage.list}" var="commentItem" varStatus="commentStatus">
                                    <div class="list-item reply-wrap" data-comment="${commentItem.cid}">
                                        <div class="user-face">
                                            <a href="<%=path%>/space/${commentItem.userBO.uid}" target="_blank">
                                                <img src="<%=path%>/images/${commentItem.userBO.uimg}">
                                            </a>
                                        </div>
                                        <div class="con">
                                            <div class="user">
                                                <a href="<%=path%>/space/${commentItem.userBO.uid}" target="_blank" class="name">
                                                    <c:if test="${commentItem.userBO.uname != null}">
                                                        ${commentItem.userBO.uname}
                                                    </c:if>
                                                    <c:if test="${commentItem.userBO.uname == null}">
                                                        ${commentItem.userBO.uid}
                                                    </c:if>
                                                </a>
                                            </div>
                                            <p class="text">
                                                ${commentItem.ctext}
                                            </p>
                                            <div class="info" style="width: 100%">
                                                <span class="time"><fmt:formatDate value="${commentItem.cdate}" type="both"/></span>
                                                <span class="like">
                                                    <i></i>(<span class="agree-num">${commentItem.cagree}</span>)
                                                </span>
                                                <span class="reply btn-hover btn-highlight">参与回复</span>
                                                <c:if test="${commentItem.userBO.uid == user.uid}">
                                                    <span class="del btn-hover del-comment" data-id="${commentItem.cid}">删除</span>
                                                </c:if>
                                            </div>
                                            <div class="reply-box" id="reply${commentItem.cid}">
                                                <c:forEach items="${commentItem.replyPage.list}" var="replyItem" varStatus="replyStatus">
                                                <div class="reply-item reply-wrap" data-reply="${replyItem.rid}">
                                                    <a href="<%=path%>/space/${replyItem.userBO.uid}" target="_blank" class="reply-face">
                                                        <img src="<%=path%>/images/${replyItem.userBO.uimg}" alt="">
                                                    </a>
                                                    <div class="reply-con">
                                                        <div class="user">
                                                            <a href="<%=path%>/space/${replyItem.userBO.uid}" target="_blank" class="name ">
                                                                <c:if test="${replyItem.userBO.uname != null}">
                                                                    ${replyItem.userBO.uname}&nbsp;:&nbsp;
                                                                </c:if>
                                                                <c:if test="${replyItem.userBO.uname == null}">
                                                                    ${replyItem.userBO.uid}&nbsp;:&nbsp;
                                                                </c:if>
                                                            </a>
                                                            <span class="text-con" style="margin-left: 10px;">${replyItem.rtext}</span>
                                                        </div>
                                                        <div class="info">
                                                            <span class="time"><fmt:formatDate value="${replyItem.rdate}" type="both"/></span>
                                                            <span class="like "><i></i>
                                                                (<span class="agree-num">${replyItem.ragree}</span>)
                                                            </span>
                                                            <span class="reply btn-hover">回复</span>
                                                            <c:if test="${replyItem.userBO.uid == user.uid}">
                                                                <span class="del btn-hover del-reply" data-id="${replyItem.rid}">删除</span>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                                </c:forEach>
                                                <div class="paging-box" style="margin-top: 20px;"></div>
                                            </div>
                                            <c:if test="${commentItem.replyPage.totalPage > 1}">
                                            <div class="paging-box reply">
                                                <span class="result">共${commentItem.replyPage.totalPage}页</span>
                                                <c:if test="${commentItem.replyPage.hasPreviousPage == true}">
                                                    <span class="prev" data-page="${commentItem.replyPage.currentPage-1}">上一页</span>
                                                </c:if>
                                                <c:forEach var="pageNo" begin="1" end="${commentItem.replyPage.totalPage}">
                                                    <c:if test="${pageNo == commentItem.replyPage.currentPage}">
                                                        <span class="current">${pageNo}</span>
                                                    </c:if>
                                                    <c:if test="${pageNo != commentItem.replyPage.currentPage}">
                                                        <a href="javascript:;" class="tcd-number" data-page="${pageNo}">${pageNo}</a>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${commentItem.replyPage.hasNextPage == true}">
                                                    <span class="next" data-page="${commentItem.replyPage.currentPage+1}">下一页</span>
                                                </c:if>
                                            </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    </c:forEach>
                                </div>
                                <div class="bottom-page paging-box-big">
                                    <c:if test="${commentPage.hasPreviousPage == true}">
                                        <span class="prev" data-page="${commentPage.currentPage-1}">上一页</span>
                                    </c:if>
                                    <c:forEach var="pageNo" begin="1" end="${commentPage.totalPage}">
                                        <c:if test="${pageNo == commentPage.currentPage}">
                                            <span class="current" data-page="${pageNo}">${pageNo}</span>
                                        </c:if>
                                        <c:if test="${pageNo != commentPage.currentPage}">
                                            <a href="javascript:;" class="tcd-number" data-page="${pageNo}">${pageNo}</a>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${commentPage.hasNextPage == true}">
                                        <span class="next" data-page="${commentPage.currentPage+1}">下一页</span>
                                    </c:if>
                                    <div class="page-jump" style="width: 150px;height: 38px">
                                        共<span>${commentPage.totalPage}</span>页，跳至<input type="text" id="pageNo" style="width: 46px;display: inline-block">页
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="line"></div>
                    <div class="box">
                        <div class="box-header">
                            <h2><i class="fa fa-globe"></i> 最新投稿</h2>
                        </div>
                        <div class="box-content">
                            <div class="row">
                                <c:forEach var="item" items="${newPostList}">
                                <div class="col-md-4" style="width: 256px;height: 218px;">
                                    <div class="wrap-vid">
                                        <div class="zoom-container">
                                            <div class="zoom-caption">
                                                <a target="_blank" href="<%=path%>/video/show/${item.vid}">
                                                    <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                                                </a>
                                                <p>${item.vname}</p>
                                            </div>
                                            <img src="<%=path%>/images/${item.vimg}"/>
                                        </div>
                                        <div class="info">
                                            <h4 style="padding-bottom: 10px;">
                                                <a href="<%=path%>/space/${item.userBO.uid}">
                                                    <c:if test="${item.userBO.uname != null}">${item.userBO.uname}</c:if>
                                                    <c:if test="${item.userBO.uname == null}">${item.userBO.uid}</c:if>
                                                </a>
                                            </h4>
                                            <span><i class="fa fa-calendar"></i>
                                                <fmt:formatDate value="${item.vaddTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                            </span>
                                            <span><i class="fa fa-heart" style="color: #ff3a44"></i>${item.vfavoriteTimes}</span>
                                        </div>
                                    </div>
                                </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="sidebar" class="col-md-4">
                <!---- Start Widget ---->
                <div class="viewbox">
                    <div class="upinfo" style="width: 320px;">
                        <div class="u-face" id="r-info-rank">
                            <a href="<%=path%>/space/${videoDetail.userBO.uid}" title="<c:if test="${videoDetail.userBO.uname != null}">${videoDetail.userBO.uname}</c:if><c:if test="${videoDetail.userBO.uname == null}">${videoDetail.userBO.uid}</c:if>" target="_blank">
                            <img src="<%=path%>/images/${videoDetail.userBO.uimg}" style="width: 68px;height: 68px;">
                            </a>
                        </div>
                        <div class="r-info">
                            <div class="usname">
                                <a class="name" href="<%=path%>/space/${videoDetail.userBO.uid}" title="<c:if test="${videoDetail.userBO.uname != null}">${videoDetail.userBO.uname}</c:if><c:if test="${videoDetail.userBO.uname == null}">${videoDetail.userBO.uid}</c:if>" target="_blank">
                                    <c:if test="${videoDetail.userBO.uname != null}">
                                        ${videoDetail.userBO.uname}
                                    </c:if>
                                    <c:if test="${videoDetail.userBO.uname == null}">
                                        ${videoDetail.userBO.uid}
                                    </c:if>
                                </a>
                            </div>
                            <div class="sign">
                                ${videoDetail.userBO.usign}
                            </div>
                            <div class="up-video-message">
                                <div class="archiveCount">投稿：${videoDetail.userBO.uworks}</div>
                                <div>粉丝：${videoDetail.userBO.ufan}</div>
                            </div>
                            <div data-uid="${videoDetail.userBO.uid}" class="b-btn f" <c:if test="${videoDetail.userBO.userFollowCount == 0}">style="display: block;"</c:if>>+ 关注</div>
                            <div data-uid="${videoDetail.userBO.uid}" class="b-btn unf" <c:if test="${videoDetail.userBO.userFollowCount > 0}">style="display: block;"</c:if>>已关注</div>
                        </div>
                    </div>
                </div>
                <div class="line"></div>
                <!---- Start Widget ---->
                <div class="widget wid-post">
                    <div class="heading"><h4><i class="fa fa-globe"></i> 相似推荐</h4></div>
                    <div class="content">
                        <c:forEach var="item" items="${hotList}">
                        <div class="post wrap-vid">
                            <div class="zoom-container">
                                <div class="zoom-caption">
                                    <a target="_blank" href="<%=path%>/video/show/${item.vid}">
                                        <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                                    </a>
                                    <p>${item.vname}</p>
                                </div>
                                <img src="<%=path%>/images/${item.vimg}"/>
                            </div>
                            <div class="wrapper">
                                <h6 class="vid-name">
                                    <a target="_blank" href="<%=path%>/video/show/${item.vid}">${item.vname}</a>
                                </h6>
                                <div class="info">
                                    <h6>
                                        <a target="_blank" href="<%=path%>/space/${item.userBO.uid}">
                                            <c:if test="${item.userBO.uname != null}">${item.userBO.uname}</c:if>
                                            <c:if test="${item.userBO.uname == null}">${item.userBO.uid}</c:if>
                                        </a>
                                    </h6>
                                    <span><i class="fa fa-calendar"></i>
                                                <fmt:formatDate value="${item.vaddTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                            </span>
                                    <span><i class="fa fa-heart" style="color: #ff3a44"></i>${item.vfavoriteTimes}</span>

                                </div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                    <div class="line"></div>
                </div>
                <!---- Start Widget ---->
                <div class="widget wid-news">
                    <div class="heading"><h4><i class="fa fa-clock-o"></i> 随机推荐</h4></div>
                    <div class="content">
                        <c:forEach var="item" items="${randomList}">
                        <div class="wrap-vid">
                            <div class="zoom-container">
                                <div class="zoom-caption">
                                    <a target="_blank" href="<%=path%>/video/show/${item.vid}">
                                        <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                                    </a>
                                    <p>${item.vname}</p>
                                </div>
                                <img src="<%=path%>/images/${item.vimg}"/>
                            </div>
                            <div class="info">
                                <h4 style="padding: 10px 0">
                                    <a href="<%=path%>/space/${item.userBO.uid}">
                                        <c:if test="${item.userBO.uname != null}">${item.userBO.uname}</c:if>
                                        <c:if test="${item.userBO.uname == null}">${item.userBO.uid}</c:if>
                                    </a>
                                </h4>
                                <span><i class="fa fa-calendar"></i>
                                                <fmt:formatDate value="${item.vaddTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                            </span>
                                <span><i class="fa fa-heart" style="color: #ff3a44"></i>${item.vfavoriteTimes}</span>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="my-footer">
    <div class="my-right">
        Copyright © 2017.集美大学 计算1312 林俊杰(Zero) 版权所有.
    </div>
</div>

<!-- 硬币 -->
<div class="m-layer m_layer m-button m-coin" style="display: none;left: 50%; top: 50%; z-index: 100001; position: fixed; margin-top: -150px; margin-left: -176px;">
    <div class="bg">
        <div class="content">
            <div class="mini">
                <div class="msg-text">
                    <i class="b-icon"></i>
                    <div class="coin-panel">
                        <div class="coin-nav coin-nav-less">
                            <span class="coin-nav-single active">
                                1<span class="coin-nav-text">枚</span>
                            </span>
                            <span class="coin-nav-single">
                                2<span class="coin-nav-text">枚</span>
                            </span>
                            <span class="coin-nav-single">
                                3<span class="coin-nav-text">枚</span>
                            </span>
                            <span class="coin-nav-single">
                                4<span class="coin-nav-text">枚</span>
                            </span>
                            <span class="coin-nav-single">
                                5<span class="coin-nav-text">枚</span>
                            </span>
                        </div>
                        <div class="coin-main">
                            <div class="coin-main-title">
                                投币支持，将消耗您<span class="coin-main-number">1</span>硬币
                            </div>
                        </div>
                    </div>
                </div>
                <div class="btnbox">
                    <a class="b-btn ok">确认</a>
                    <a class="b-btn-cancel cancel">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 遮罩 -->
<div class="m-backdrop" style="display: none;position: fixed; top: 0px; left: 0px; right: 0px; bottom: 0px; opacity: 0.6; background-color: rgb(0, 0, 0); z-index: 100000;"></div>

</body>

<!-- 弹幕 -->
<script src="<%=path%>/templete/danmu/js/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/templete/danmu/js/jquery.shCircleLoader.js"></script>
<script src="<%=path%>/templete/danmu/js/sco.tooltip.js"></script>
<script src="<%=path%>/templete/danmu/js/colpick.js"></script>
<script src="<%=path%>/templete/danmu/js/jquery.danmu.js"></script>
<script src="<%=path%>/templete/danmu/js/main.js"></script>

<script>
    <%--JS gloable varilible，简单类型必须要用双引号或单引号包起来--%>
    var videoId = "${videoDetail.vid}";
    var userId = "${user.uid}";
    var userImg = "${user.uimg}";
</script>

<!-- 自编写 -->
<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/templete/jDialog/jDialog.js"></script>
<script src="<%=path%>/js/show.js"></script>
<script src="<%=path%>/js/tag.js"></script>
<script src="<%=path%>/js/comment.js"></script>

<script>
    $(function(){
        //---------------------  弹幕初始化  -------------------
        $("#danmup").DanmuPlayer({
            src:"<%=path%>/video/${videoDetail.vmedia}",
            height: "480px", //区域的高度
            width: "100%" //区域的宽度
            ,urlToGetDanmu:"<%=path%>/video/getDanmu?videoId=${videoDetail.vid}"
            ,urlToPostDanmu:"<%=path%>/video/saveDanmu?videoId=${videoDetail.vid}"
        });
        //---------------------  弹幕初始化  -------------------

    });

</script>
</html>