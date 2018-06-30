<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息管理</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/pie.js"></script>
<script src="/resources/Highcharts/js/highcharts.js"></script>
<script src="/resources/Highcharts/js/highcharts-3d.js"></script>
<script src="/resources/Highcharts/js/modules/exporting.js"></script>
<script src="/resources/echarts/build/dist/echarts.js"></script>
<style type="text/css">
th {
	text-align: center;
}
</style>
</head>
<body>
	<table id="customerDatagrid" title="客户管理" class="easyui-datagrid"
		style="width: 1000px; height: 250px" url="/customer/list.do"
		toolbar="#toolbar" pagination="true" rownumbers="true" fit="true"
		fitColumns="true" singleSelect="true" pageList="[2,5,10,20,50]"
		pageSize="20">
		<thead>
			<tr>
				<th field="name" width=10">客户</th>
				<th field="contacts" width=10">联系人</th>
				<th field="age" width="10">年龄</th>
				<th field="sex" formatter="sexFormatter" width="10">性别</th>
				<th field="tel" width="10">电话</th>
				<th field="email" width="15">邮箱</th>
				<th field="qq" width="15">QQ</th>
				<th field="wechat" width="10">微信</th>
				<th field="inputUser" formatter="objectFormatter" width="10">录入人员</th>
				<th field="inputTime" width="10">录入时间</th>
				<th field="seller" formatter="objectFormatter" width="10">营销人员</th>
				<th field="salaryLevel" formatter="objectFormatter" width="15">收入水平</th>
				<th field="job" formatter="objectFormatter" width="15">职业</th>
				<th field="customerSource" formatter="objectFormatter" width="10">客户来源</th>
				<th field="customerStatus" formatter="objectFormatter" width="10">客户状态</th>
				<th field="status" formatter="customerstatusFormatter" width="10">服务级别</th>
				<%-- <wyl:checkPermission permissionName="客户信息管理">
					<th field="status" formatter="poolFormatter" width="10">资源池</th>
				</wyl:checkPermission> --%>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		客户录入时间：<input id="inputTimeStart" name="inputTimeStart"
			class="easyui-datebox" style="width: 120px"> - <input
			id="inputTimeEnd" name="inputTimeEnd" class="easyui-datebox"
			style="width: 120px">
		<wyl:checkPermission permissionName="数据统计客户来源">
			<a href="javascript:void(0)" data-cmd="getPieByCustomerSource3D"
				class="easyui-linkbutton" iconCls="icon-large-clipart" plain="true">客户来源3D饼图</a>
			<a href="javascript:void(0)" data-cmd="getPieByCustomerSource"
				class="easyui-linkbutton" iconCls="icon-large-chart" plain="true">客户来源柱状图</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="数据统计客户状态">
			<a href="javascript:void(0)" data-cmd="getPieByCustomerStatus3D"
				class="easyui-linkbutton" iconCls="icon-large-clipart" plain="true">客户状态3D饼图</a>
			<a href="javascript:void(0)" data-cmd="getPieByCustomerStatus"
				class="easyui-linkbutton" iconCls="icon-large-chart" plain="true">客户状态柱状图</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="数据统计客户服务级别">
			<a href="javascript:void(0)" data-cmd="chart3d"
				class="easyui-linkbutton" iconCls="icon-large-clipart" plain="true">服务级别3D饼图</a>
			<a href="javascript:void(0)" data-cmd="echart"
				class="easyui-linkbutton" iconCls="icon-large-chart" plain="true">服务级别柱状图</a>
		</wyl:checkPermission>
	</div>

	<div id="pieDialog" closed="true" class="easyui-dialog"
		title="Basic Dialog" data-options="iconCls:'icon-save'"
		style="width: 650px; height: 400px; padding: 10px">
		<div id="container" style="height: 300px; width: 300;"></div>
	</div>

	<!--柱状图  -->
	<div id="barDialog" closed="true" class="easyui-dialog"
		title="Basic Dialog" data-options="iconCls:'icon-save'"
		style="width: 650px; height: 400px; padding: 10px">
		<div id="main" style="height: 350px"></div>
	</div>
</body>
</html>