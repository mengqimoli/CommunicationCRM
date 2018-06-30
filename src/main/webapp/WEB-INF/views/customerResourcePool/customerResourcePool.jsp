<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户资源池</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/customerResourcePool.js"></script>
</head>
<body>
	<table id="customerDatagrid" title="客户资源池" class="easyui-datagrid"
		style="width: 1000px; height: 250px" url="/customer/poolList.do"
		toolbar="#toolbar" pagination="true" rownumbers="true" fit="true"
		fitColumns="true" singleSelect="true" pageList="[2,5,10,20,50]"
		pageSize="20">
		<thead>
			<tr>
				<th field="name" width=10">客户姓名</th>
				<th field="age" width="10">年龄</th>
				<th field="sex" formatter="sexFormatter" width="10">性别</th>
				<th field="tel" width="10">电话</th>
				<th field="email" width="15">邮箱</th>
				<th field="qq" width="15">QQ</th>
				<th field="wechat" width="10">微信</th>
				<th field="salaryLevel" formatter="objectFormatter" width="15">收入水平</th>
				<th field="job" formatter="objectFormatter" width="15">职业</th>
				<th field="inputUser" formatter="objectFormatter" width="10">录入人员</th>
				<th field="customerSource" formatter="objectFormatter" width="10">客户来源</th>
				<th field="customerStatus" formatter="objectFormatter" width="10">客户状态</th>
				<th field="seller" formatter="objectFormatter" width="10">营销人员</th>
				<th field="status" formatter="customerstatusFormatter" width="10">服务等级</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<wyl:checkPermission permissionName="客户信息删除">
			<a href="javascript:void(0)" data-cmd="poolDelete"
				class="easyui-linkbutton" iconCls="icon-cut" plain="true">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" data-cmd="poolRefesh"
			class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
		<wyl:checkPermission permissionName="客户跟进">
			<a href="javascript:void(0)" data-cmd="followCustomer"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">跟进客户</a>
		</wyl:checkPermission>
	</div>
</body>
</html>