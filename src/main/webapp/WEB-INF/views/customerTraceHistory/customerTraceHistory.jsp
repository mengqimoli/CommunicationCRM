<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户跟进历史</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/customerTraceHistory.js"></script>
</head>
<body>
	<table id="historyDatagrid" title="客户跟进记录" class="easyui-datagrid"
		style="width: 700px; height: 250px"
		url="/customerTraceHistory/list.do" toolbar="#toolbar"
		pagination="true" rownumbers="true" fit="true" fitColumns="true"
		singleSelect="true" pageList="[2,5,10,20,50]" pageSize="20">
		<thead>
			<tr>
				<th field="id" hidden=true width="50"></th>
				<th field="traceTime" width="50">跟进时间</th>
				<th field="customer" formatter="objectFormatter" width="50">跟进客户</th>
				<th field="title" width="50">跟进主题</th>
				<th field="traceType" formatter="objectFormatter" width="50">跟进方式</th>
				<th field="traceResult" width="50">跟进效果</th>
				<th field="seller" formatter="objectFormatter" width="50">跟进人员</th>
				<th field="remark" width="50">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<wyl:checkPermission permissionName="客户跟进历史保存/修改">
			<a href="javascript:void(0)" data-cmd="historyAdd"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:void(0)" data-cmd="historyUpdate"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="客户跟进历史删除">
			<a href="javascript:void(0)" data-cmd="historyDelete"
				class="easyui-linkbutton" iconCls="icon-cut" plain="true">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" data-cmd="historyRefesh"
			class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
	</div>
	<div id="historyDialog" class="easyui-dialog"
		style="width: 400px; height: 370px; padding: 10px 20px" modal="true"
		closed="true" buttons="#dlg-buttons">
		<form id="historyForm" method="post" novalidate>
			<div class="ftitle">客户跟进历史添加/编辑</div>
			<input name="id" type="hidden" style="width: 80%">
			<table align="center">
				<tr>
					<td align="right">跟进客户：</td>
					<td><select id="customerId" pagination="true" required="true"
						style="width: 147px;" name="customer.id" class="easyui-combogrid"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/customer/list.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'name',title:'客户',width:120},
		                        {field:'contacts',title:'联系人',width:120},
		                        {field:'tel',title:'电话',width:120} ,
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td align="right">跟进主题：</td>
					<td><input class="easyui-textbox" name="title" required="true"></td>
				</tr>
				<tr>
					<td align="right">跟进方式：</td>
					<td><select id="traceTypeId" pagination="true" required="true"
						style="width: 147px;" name="traceType.id" class="easyui-combogrid"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/customerTraceHistory/customerSource.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'name',title:'跟进方式',width:120},
		                        {field:'intro',title:'简介',width:120}, 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>

				<tr>
					<td align="right">跟进人员：</td>
					<td><select id="sellerId" pagination="true" required="true"
						style="width: 147px;" name="seller.id" class="easyui-combogrid"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'realName',
		                    mode: 'remote',
		                    url: '/customerTraceHistory/seller.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'realName',title:'姓名',width:120},
		                        {field:'tel',title:'电话',width:120}, 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td align="right">跟进时间：</td>
					<td><input class="easyui-datetimebox" name="traceTime"
						required="true" labelPosition="top"></td>
				</tr>

				<tr>
					<td align="right">跟进效果：</td>
					<td><input class="easyui-textbox" name="traceResult"
						required="true"></td>
				</tr>
				<tr>
					<td align="right" valign="top">备注：</td>
					<td><input name="remark" required="true"
						class="easyui-textbox" style="width: 147px; height: 80px"
						data-options="multiline:true" />
				</tr>
			</table>
		</form>

	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" data-cmd="historySave"
			class="easyui-linkbutton c6" iconCls="icon-ok" style="width: 90px">保存</a>
		<a href="javascript:void(0)" data-cmd="historyCancle"
			class="easyui-linkbutton" iconCls="icon-cancel" style="width: 90px">取消</a>
	</div>
</body>
</html>