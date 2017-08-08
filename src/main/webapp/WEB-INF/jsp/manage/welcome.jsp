<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<title>条形统计</title>
</head>
<body>
<div class="page-container">
	<div id="container" style="min-width:700px;height:400px"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/templete/back/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/templete/back/lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
<script type="text/javascript" src="<%=path%>/templete/back/lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
<script type="text/javascript">
	$(function () {
        <%--var series = [];--%>
        <%--<c:forEach var="item" items="count">--%>
            <%--var object = new Object();--%>
            <%--object.name = ${item.name};--%>
            <%--var data = [];--%>
            <%--<c:forEach var="d" items="${item.data}">--%>
                <%--data.push(d);--%>
            <%--</c:forEach>--%>
            <%--object.data = data;--%>
        <%--</c:forEach>--%>

		$('#container').highcharts({
			chart: {
				type: 'column'
			},
			title: {
				text: '每月播放量'
			},
			xAxis: {
				categories: [
					'一月',
					'二月',
					'三月',
					'四月',
					'五月',
					'六月',
					'七月',
					'八月',
					'九月',
					'十月',
					'十一月',
					'十二月'
				]
			},
			yAxis: {
				min: 0,
				title: {
					text: '播放量'
				}
			},
			tooltip: {
				headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
				'<td style="padding:0"><b>{point.y}</b></td></tr>',
				footerFormat: '</table>',
				shared: false,
				useHTML: false
			},
			plotOptions: {
				column: {
					pointPadding: 0.2,
					borderWidth: 0
				}
			},
            series: ${count}
		});
	});
</script>
</body>
</html>