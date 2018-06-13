<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	   String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("listMesage");
	String moneyTotal = (String)request.getAttribute("moneyTotal");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head href="<%=basePath%>">
<title>学生管理</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

	});
</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">数据表</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>

	<div class="rightinfo">
		<div class="formtitle">
			<span>订单总额：<%=moneyTotal%>元</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>序号</th>
					<th>订单用户</th>
					<th>订单时间</th>
					<th>订单金额</th>
					<th>车辆车牌</th>
					<th>订单状态</th>
				</tr>
			</thead>
			<tbody>




				<%
					int houseNumber = 0;
													if (!list.isEmpty()) {
														/*  for(Map<String,Object> map:list){ */
														for (int i = 0; i < list.size(); i++) {
															houseNumber = i + 1;
															Map<String, Object> map = list.get(i);
				%>


				<tr>
					<td><%=houseNumber%></td>
					<td><%=map.get("orderUserName")%></td>
					<td><%=map.get("orderCreateTime")%></td>
					<td><%=map.get("orderMoney")%>元</td>
					<td><%=map.get("orderName")%></td>
					<td>
					订单正常

					</td>

					<td>


				</tr>

				<%
					}
													}
				%>
			</tbody>
		</table>







	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
