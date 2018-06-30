<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据字典管理</title>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/systemDictionary.js"></script>
</head>
<body>
	<div id="systemDictionary-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-save" style="width: 90px" data-cmd="save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-cancel" style="width: 90px" data-cmd="cancel">取消</a>
	</div>
	<div id="systemDictionaryItem-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-save" style="width: 90px" data-cmd="saveItem">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-cancel" style="width: 90px" data-cmd="cancelItem">取消</a>
	</div>

	<div id="systemDictionary-dialog" class="easyui-dialog" title="数据添加"
		style="width: 450px; height: 300px; padding: 10px 20px"
		buttons="#systemDictionary-dialog-buttons" closed="true" modal="true">
		<div class="ftitle">字典明细添加/编辑</div>
		<form do="systemDictionary_save.do" method="post"
			id="systemDictionary-form">
			<input type="hidden" name="id" />
			<table align="center">
				<tr>
					<td>字典名称：</td>
					<td><input name="name" class="easyui-textbox"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td>字典编号：</td>
					<td><input name="sn" class="easyui-textbox"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td valign="top">字典描述：</td>
					<td><textarea rows="3" style="width: 200px;" name="intro">
						</textarea></td>
				</tr>
			</table>
		</form>
	</div>


	<div id="systemDictionary-datagrid-toolbar">
		<div>
			<wyl:checkPermission permissionName="数据字典保存/修改">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" data-cmd="create">新建</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
			</wyl:checkPermission>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
		</div>
	</div>

	<div id="systemDictionaryItem-datagrid-toolbar">
		<div>
			<wyl:checkPermission permissionName="数据字典明细保存/修改">
				<a id="systemDictionaryItemCreateBtn" href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-add" plain="true"
					data-cmd="addItem">添加</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" data-cmd="editItem">编辑</a>
			</wyl:checkPermission>
			<wyl:checkPermission permissionName="数据字典明细删除">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cut" plain="true" data-cmd="deleteItem">删除</a>
			</wyl:checkPermission>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" data-cmd="refreshItem">刷新</a>
		</div>
	</div>

	<div id="systemDictionaryItem-dialog" class="easyui-dialog"
		style="width: 450px; height: 330px; padding: 10px 20px" modal="true"
		closed="true" buttons="#systemDictionaryItem-dialog-buttons">
		<div class="ftitle">字典明细添加/编辑</div>
		<form id="systemDictionaryItem-form" do="systemDictionaryItem_save.do"
			method="post">
			<input type="hidden" name="id" />
			<table align="center">
				<tr>
					<td style="text-align: right;">数据字典名称：</td>
					<td><input id="systemDictionaryCombo" name="type.id"
						class="easyui-combotree" style="width: 147px;"
						data-options="
				        					url:'/systemDictionaryType/getAll.do'
				        					,method:'get'
				        					,panelHeight:'auto'
				        					,required:'true'
				        					">

					</td>
				</tr>
				<tr>
					<td style="text-align: right;">字典明细名称/值：</td>
					<td><input validType="hzOrywOrszOrxhx" name="name"
						class="easyui-textbox" data-options="required:true" /></td>
				</tr>

				<tr>
					<td style="text-align: right;">显示顺序：</td>
					<td><input id="onlyNum" validType="number" name="sequence"
						class="easyui-textbox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td style="text-align: right;" valign="top">字典明细描述：</td>
					<td><textarea rows="3" style="width: 200px; resize: none;"
							name="intro">
						</textarea></td>
				</tr>
			</table>
		</form>
	</div>


	<div class="easyui-layout" id="layout" border="false"
		data-options="fit:true,border:false">
		<div id="westPanel" region="west" style="width: 200px;"
			headerCls="border-right" bodyCls="border-right">
			<table class="easyui-datagrid" id="systemDictionary-datagrid"
				title="数据字典目录" border="false" pageList="[2,5,10,20,50]"
				data-options="fit:true,border:false,url:'/systemDictionaryType/list.do',fitColumns:true,singleSelect:true,pagination:true,toolbar:'#systemDictionary-datagrid-toolbar'">
				<thead>
					<tr>
						<th data-options="field:'name',width:10">字典名称</th>
						<th data-options="field:'sn',width:15">字典编号</th>
					</tr>
				</thead>
			</table>
		</div>

		<div region="center" id="center" headerCls="border-right">
			<table class="easyui-datagrid" id="systemDictionaryItem-datagrid"
				title="数据字典明细" border="false" pageList="[2,5,10,20,50]"
				data-options="fit:true,border:false,url:'/systemDictionaryItem/list.do',fitColumns:true,singleSelect:true,pagination:true,toolbar:'#systemDictionaryItem-datagrid-toolbar'">
				<thead>
					<tr>
						<th data-options="field:'type',formatter:objectFormatter,width:10">字典名称</th>
						<th data-options="field:'name',width:10">字典明细名称/值</th>
						<th data-options="field:'sequence',width:5">显示顺序</th>
						<th
							data-options="field:'status',formatter:statusFormatter,width:5">状态</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

</body>
</html>