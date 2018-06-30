<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>潜在客户开发计划</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/customerDevPlan.js"></script>
</head>
<body>
	<table id="planDatagrid" title="潜在客户开发计划" class="easyui-datagrid"
		style="width: 700px; height: 400px" url="/customerDevPlan/list.do"
		toolbar="#toolbar" pagination="true" rownumbers="true" fit="true"
		fitColumns="true" singleSelect="true" pageList="[2,5,10,20,50]"
		pageSize="20">
		<thead>
			<tr>
				<th field="id" hidden=true width="50"></th>
				<th field="planTime" width="50">开发计划时间</th>
				<th field="planSubject" width="50">开发计划主题</th>
				<th field="planDetails" width="50">详细内容</th>
				<th field="planType" formatter="objectFormatter" width="50">计划实施方式</th>
				<th field="pote" formatter="objectFormatter" width="50">潜在客户</th>
				<th field="seller" formatter="objectFormatter" width="50">营销人员</th>
				<th field="inputUser" formatter="objectFormatter" width="50">创建人员</th>
				<th field="inputTime" width="50">创建时间</th>
				<th field="result" formatter="resultFormatter" width="50">开发结果</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<wyl:checkPermission permissionName="潜在客户开发计划保存/修改">
			<a href="javascript:void(0)" data-cmd="planAdd"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">新建计划</a>
			<a href="javascript:void(0)" data-cmd="planUpdate"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="潜在客户开发计划删除">
			<a href="javascript:void(0)" data-cmd="planDelete"
				class="easyui-linkbutton" iconCls="icon-cut" plain="true">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" data-cmd="planRefesh"
			class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
		<div>
			<form id="customerDevPlanSearchKeyForm" method="post"
				enctype="application/x-www-form-urlencoded">
				关键字 : <input name="searchKey" class="easyui-textbox"
					style="width: 120px"> 录入时间: <input name="planTimeStart"
					class="easyui-datebox" style="width: 120px"> 到: <input
					name="planTimeEnd" class="easyui-datebox" style="width: 120px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search"
					data-cmd="doSearch">搜索</a><a href="#" class="easyui-linkbutton c4"
					iconCls="icon-search" data-cmd="openAdvanceSearch">高级搜索</a>
			</form>
		</div>
	</div>

	<div id="planDialog" class="easyui-dialog"
		style="width: 400px; height: 37 0px; padding: 10px 20px" modal="true"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">开发计划添加/编辑</div>
		<form id="planForm" method="post" novalidate>
			<input name="id" type="hidden"> <input name="inputTime"
				type="hidden"><input name="inputUser.id" type="hidden"><input
				name="seller.id" type="hidden">
			<table align="center">
				<tr>
					<td align="right">计划时间：</td>
					<td><input class="easyui-datebox" name="planTime"
						required="true" style="width: 147px;"></td>
				</tr>
				<tr>
					<td align="right">开发主题：</td>
					<td><input name="planSubject" class="easyui-textbox"
						validType="hzOrywOrszOrxhx" required="true"></td>
				</tr>
				<tr>
					<td align="right">计划实施方式：</td>
					<td><select id="planTypeId" pagination="true" required="true"
						name="planType.id" class="easyui-combogrid" style="width: 147px"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/customerDevPlan/planType.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'name',title:'计划实施方式',width:100},
		                        {field:'intro',title:'简介',width:120}, 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td align="right">潜在客户：</td>
					<td><select id="poteId" pagination="true" name="pote.id"
						class="easyui-combogrid" style="width: 147px" required="true"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/potentialCustomer/list.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'name',title:'潜在客户',width:100},
		                        {field:'linkMan',title:'联系人',width:100},
		                        {field:'linkManTel',title:'联系人电话',width:100},
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>

				<%-- <wyl:checkPermission permissionName="潜在客户开发计划管理">
					<tr>
						<td align="right">营销人员：</td>
						<td><select id="sellerId" pagination="true" required="true"
							name="seller.id" class="easyui-combogrid" style="width: 147px"
							data-options="
		                    idField: 'id',
		                    panelWidth:450,
		                    textField: 'realName',
		                    mode: 'remote',
		                    url: '/customer/customerSeller.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'realName',title:'姓名',width:100},
		                        {field:'tel',title:'电话',width:120}, 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
						</select></td>
					</tr>
				</wyl:checkPermission> --%>

				<tr>
					<td align="right" valign="top">详细内容：</td>
					<td><input name="planDetails" class="easyui-textbox"
						required="true" style="width: 148px; height: 80px"
						data-options="multiline:true"></td>
				</tr>
				<tr>
					<td align="right">开发结果：</td>
					<td><select id="result" name="result" class="easyui-combobox"
						data-options="editable:false,panelHeight:'auto'"
						style="width: 147px;">
							<option value="1">成功</option>
							<option value="-1">失败</option>
					</select></td>
				</tr>
			</table>
		</form>

	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" data-cmd="planSave"
			class="easyui-linkbutton c6" iconCls="icon-ok" style="width: 90px">保存</a>
		<a href="javascript:void(0)" data-cmd="planCancle"
			class="easyui-linkbutton" iconCls="icon-cancel" style="width: 90px">取消</a>
	</div>
	<!-- 3、查询对话框 -->
	<div id="customerAdvanceSearchDlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" modal="true"
		closed="true" buttons="#customer-search-dlg-buttons" novalidate>
		<div class="ftitle">开发计划信息查询</div>
		<form id="customerAdvanceSearchForm" method="post">
			<table align="center">
				<tr>
					<td>开发计划主题：</td>
					<td><input name="planSubject" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>详细内容：</td>
					<td><input name="planDetails" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>计划实施方式：</td>
					<td><input name="planTypeId" class="easyui-combotree"
						style="width: 150px;"
						data-options="
				        					url:'/customerDevPlan/planType.do'
				        					,method:'get'
				        					,panelHeight:'auto'
				        					">
					</td>
				</tr>
				<tr>
					<td>潜在客户：</td>
					<td><input name="poteId" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>创建人员：</td>
					<td><input name="inputUserId" class="easyui-textbox">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 查询对话框按钮 -->
	<div id="customer-search-dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c5"
			iconCls="icon-search" style="width: 90px" data-cmd="doAdvanceSearch">搜索</a>
		<a href="javascript:void(0)" class="easyui-linkbutton c1"
			iconCls="icon-reload" style="width: 90px"
			data-cmd="clearAdvanceSearchForm">清空</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel" style="width: 90px"
			data-cmd="cancelAdvanceSearchDlg">取消</a>
	</div>
</body>
</html>