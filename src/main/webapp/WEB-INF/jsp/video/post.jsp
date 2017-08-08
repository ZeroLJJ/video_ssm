<%--
  Created by IntelliJ IDEA.
  User: Zero
  Date: 2017/5/11
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    //用来表示当前web工程的相对路径，即webapp下的根目录
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>个人空间</title>
    <!-- 自编 -->
    <link rel="stylesheet" href="<%=path%>/css/base.css">
    <!-- bilibili -->
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/page-core.css">
    <link rel="stylesheet" href="<%=path%>/templete/bilibili/css/app.css">
    <!-- 对话框插件 -->
    <link rel="stylesheet" href="<%=path%>/templete/jDialog/jDialog/jDialog.css">

</head>
<body>
<!-- Header -->
<header style="margin-top:40px;">
    <div class="header" style="z-index: 10000;box-shadow: 1px -1px 7px black;height: 50px;">
        <div class="num" style="height: 50px;">
            <div class="menu-wrapper">
                <ul class="nav-menu">
                    <li class="m-i home" style="margin: 0 3px 0 -20px;">
                        <a href="<%=path%>/" class="i-link"><em>首页</em></a>
                    </li>
                    <!-- 菜单栏（视频类型） -->
                    <c:forEach items="${videoType}" var="bigType">
                        <li class="m-i" style="margin: 0 3px;">
                            <a href="<%=path%>/video/all?bigType=${bigType.bid}" class="i-link" style="width: 50px">
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
                <div class="search" style="top:10px;left:700px;">
                    <div id="searchform" target="_blank">
                        <input name="keyword" type="text" class="search-keyword" autocomplete="off" placeholder="你不知道的100多件幻想乡秘闻">
                        <button type="submit" class="search-submit"></button>
                    </div>
                    <a class="link-ranking" href="<%=path%>/video/rank" target="_blank">
                        <span style="font-size: 12px;">排行榜</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="main">
    <div class="container bili-box">
        <input type="hidden" id="video"/>
        <input type="hidden" id="img"/>
        <input type="hidden" id="duration"/>
        <input type="hidden" id="durationLevel"/>
        <input type="hidden" id="type"/>
        <div class="left col-l">
            <div class="cover-wrp">
                <div class="cover-wrp-inner">
                    <div class="cover-box"></div>
                    <input id="uploadCover" type="file" accept="image/jpg,image/jpeg,image/png" style="display: none"/>
                    <div class="picture-box"></div>
                    <div class="preview-loading" style="display: none">正在生成可选封面...</div>
                    <div class="cover-tip-wrp">
                        <div class="tip-list clearfix">
                            <div class="left dot"></div>
                            <div class="left tip-text">建议上传封面像素为960*600</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="right col-r">
            <div class="upload-wrp">
                <ul id="sortWrp" class="file-menu" style="display: none">
                    <li class="sort-item menu-item clearfix" style="cursor: default;">
                        <div class="left meta-wrp">
                            <div class="status-wrp">
                                <div class="upload-status"> 已完成 </div>
                                <div class="upload-status highlight"> 0% </div>
                            </div>
                            <div class="progress-wrp">
                                <div class="progress" style="width: 0%;"></div>
                            </div>
                        </div>
                    </li>
                </ul>
                <button class="btn upload-btn"></button>
                <label style="padding: 5px;color: #9ca2aa;">注：点击左方按钮上传视频</label>
                <input type="file" id="video-upload" name="myvideo" accept=".mp4" data-url="<%=path%>/video/uploadVideo" style="display: none;">
            </div>
            <div>
                <div>
                    <div class="section type-wrp">
                        <h3>选择分区</h3>
                        <div>
                            <ul class="type-menu clearfix">
                                <c:forEach var="bigType" items="${videoType}">
                                <li class="dropdown">
                                    <button type="button" class="type-btn" data-toggle="dropdown">${bigType.bname}</button>
                                    <ul class="dropdown-menu">
                                        <c:forEach items="${bigType.smallTypeBOList}" var="smallType">
                                        <li class="menu-item" data-small="${smallType.sid}">
                                            <span class="name">${smallType.sname}</span>
                                        </li>
                                        </c:forEach>
                                    </ul>
                                </li>
                                </c:forEach>
                            </ul>
                            <div class="type-hint"></div>
                        </div>
                    </div>
                    <div class="section title-wrp">
                        <h3>稿件标题</h3>
                        <div>
                            <div class="title-wrp">
                                <input class="bili-input" id="title-input" type="text" placeholder="请输入稿件标题">
                                <div class="count-wrp" id="title-count">0/80</div>
                            </div>
                        </div>
                    </div>
                    <div class="section template-description-wrp" style="margin: 0">
                        <h3>视频简介</h3>
                        <div>
                            <div class="description-wrp">
                                <textarea id="desc-input" placeholder="请输入简介"></textarea>
                                <div class="count-wrp" id="desc-count">0/250</div>
                            </div>
                        </div>
                    </div>
                    <div class="submit-wrp">
                        <button class="btn submit-btn">提交稿件</button>
                    </div>
                 </div>
            </div>
        </div>
    </div>
</div>

<div class="my-footer" style="margin-top: 850px;">
    <div class="my-right">
        Copyright © 2017.集美大学 计算1312 林俊杰(Zero) 版权所有.
    </div>
</div>

</body>
<!-- JS -->
<script src="<%=path%>/templete/video/js/jquery-2.1.1.js"></script>

<!-- 自编写 -->
<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/templete/jDialog/jDialog.js"></script>
<script src="<%=path%>/js/upload.js"></script>
<script src="<%=path%>/js/post.js"></script>

</html>
