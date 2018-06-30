<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户移交记录</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/customerTransfer.js"></script>
</head>
<body>
	<table id="cutomerTransferDatagrid" title="客户移交记录"
		class="easyui-datagrid" style="width: 700px; height: 250px"
		url="/customerTransfer/list.do" toolbar="#toolbar" pagination="true"
		rownumbers="true" fit="true" fitColumns="true" singleSelect="true"
		pageList="[2,5,10,20,50]" pageSize="20">
		<thead>
			<tr>
				<th field="customer" formatter="objectFormatter" width="10">客户</th>
				<th field="transUser" formatter="objectFormatter" width="10">移交人员</th>
				<th field="transTime" width="20">移交时间</th>
				<th field="oldSeller" formatter="objectFormatter" width="10">老市场专员</th>
				<th field="newSeller" formatter="objectFormatter" width="10">新市场专员</th>
				<th field="transReason" width="40">移交原因</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<wyl:checkPermission permissionName="客户移交记录删除">
			<a href="javascript:void(0)" data-cmd="cutomerTransferDelete"
				class="easyui-linkbutton" iconCls="icon-cut" plain="true">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" data-cmd="cutomerTransferRefesh"
			class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
	</div>
</body>
</html>