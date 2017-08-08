<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	//用来表示当前web工程的相对路径，即webapp下的根目录
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/templete/back/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/templete/back/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/templete/back/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/templete/back/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/templete/back/static/h-ui.admin/css/style.css" />

	<style>
		.td-manage a{
			text-decoration:none;
			display: none;
		}
	</style>
<title>视频列表</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 视频管理
	<span class="c-gray en">&gt;</span> 视频列表
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i>
	</a>
</nav>
<div class="page-container">
	<div class="text-c"> 日期范围：
		<form action="<%=path%>/manage/video-list" method="post">
			<input type="text" name="minTime" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
			-
			<input type="text" name="maxTime" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
			<input type="text" name="videoName" placeholder=" 视频名称" style="width:250px" class="input-text">
			<button id="search" class="btn btn-success" type="submit">
				<i class="Hui-iconfont">&#xe665;</i> 搜视频
			</button>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" id="del_multi" class="btn btn-danger radius">
				<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
			</a>
		</span>
		<span class="r">共有数据：<strong>${fn:length(videoList)}</strong> 条</span>
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="40"><input name="vid" type="checkbox"></th>
					<th width="80">ID</th>
					<th width="100">分类</th>
					<th width="100">视频名称</th>
					<th width="100">视频</th>
					<th width="150">上传时间</th>
					<th width="60">视频状态</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${videoList}">
				<tr class="text-c">
					<td><input name="vid" class="vid" type="checkbox" value="${item.vid}"></td>
					<td>${item.vid}</td>
					<td>${item.bigTypeBO.bname}-${item.smallTypeBO.sname}</td>
					<td class="text-l">${item.vname}</td>
					<td>
						<video style="width: 280px;height: 200px;" controls="controls" src="<%=path%>/video/${item.vmedia}" preload="none"></video>
					</td>
					<td><fmt:formatDate value="${item.vaddTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
					<td class="td-status">
						<c:if test="${item.vstatus=='0'}"><span class="label label-warning radius">未审核</span></c:if>
						<c:if test="${item.vstatus=='1'}"><span class="label label-success radius">正常</span></c:if>
						<c:if test="${item.vstatus=='2'}"><span class="label label-danger radius">已冻结</span></c:if>
						<c:if test="${item.vstatus=='3'}"><span class="label label-danger radius">未通过</span></c:if>
					</td>
					<td class="td-manage">
						<a class="check" <c:if test="${item.vstatus=='0'}">style="display: inline-block"</c:if>
						   data-id="${item.vid}" href="javascript:;" title="审核">
							<i class="Hui-iconfont">&#xe6bc;</i>
						</a>
						<a class="frozen" <c:if test="${item.vstatus=='1'}">style="display: inline-block"</c:if>
						   data-id="${item.vid}" href="javascript:;" title="冻结">
							<i class="Hui-iconfont">&#xe60e;</i>
						</a>
						<a class="unfrozen" <c:if test="${item.vstatus=='2'}">style="display: inline-block"</c:if>
						   data-id="${item.vid}" href="javascript:;" title="解结">
							<i class="Hui-iconfont">&#xe605;</i>
						</a>
						<a class="delete" data-id="${item.vid}" style="display: inline-block" href="javascript:;" title="删除">
							<i class="Hui-iconfont">&#xe6e2;</i>
						</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/templete/back/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/templete/back/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/lib/laypage/1.2/laypage.js"></script>

<script src="<%=path%>/js/base.js"></script>
<script src="<%=path%>/js/back/video.js"></script>
<script type="text/javascript">

	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{"orderable":false,"aTargets":[0,7]}// 制定列不参与排序
		]
	});

</script>
</body>
</html>