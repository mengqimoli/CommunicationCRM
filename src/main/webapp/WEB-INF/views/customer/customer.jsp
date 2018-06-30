<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息管理</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/customer.js"></script>
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
		<wyl:checkPermission permissionName="客户信息保存/修改">
			<a href="javascript:void(0)" data-cmd="customerAdd"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:void(0)" data-cmd="customerUpdate"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="客户信息删除">
			<a href="javascript:void(0)" data-cmd="customerDelete"
				class="easyui-linkbutton" iconCls="icon-cut" plain="true">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" data-cmd="customerRefesh"
			class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
		<%-- <wyl:checkPermission permissionName="客户信息详情">
			<a href="javascript:void(0)" data-cmd="customershift"
				class="easyui-linkbutton" iconCls="icon-remove" plain="true">客户详情</a>
		</wyl:checkPermission> --%>
		<wyl:checkPermission permissionName="客户放入资源池">
			<a href="javascript:void(0)" data-cmd="putPool"
				class="easyui-linkbutton" iconCls="icon-cancel" plain="true">放入资源池</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="客户移交">
			<a href="javascript:void(0)" data-cmd="transfer"
				class="easyui-linkbutton" iconCls="icon-redo" plain="true">移交</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="客户信息导出">
			<a href="/customer/exports.do" class="easyui-linkbutton"
				iconCls="icon-sum" plain="true">Excle导出</a>
		</wyl:checkPermission>

		<div>
			<form id="searchForm" action="">
				关键字：<input id="searchKey" class="easyui-textbox" name="searchKey" />
				客户状态：<select id="dataStatus" name="status" class="easyui-combobox"
					data-options="panelHeight:'auto'">
					<option value="0">--请选择--</option>
					<option value="1">普通</option>
					<option value="2">中级</option>
					<option value="3">高级</option>
					<option value="4">VIP</option>
				</select> <a href="javascript:void(0)" data-cmd="searchCustomer"
					class="easyui-linkbutton" iconCls="icon-search">查询</a> <a
					href="javascript:void(0)" data-cmd="openAdvanceSearch"
					class="easyui-linkbutton c4" iconCls="icon-search">高级查询</a>
			</form>
		</div>
	</div>

	<div id="customerDialog" class="easyui-dialog"
		style="width: 400px; height: 480px; padding: 10px 20px" modal="true"
		closed="true" buttons="#dlg-buttons">
		<form id="customerForm" method="post" novalidate>
			<div class="ftitle">客户信息添加/编辑</div>
			<input name="id" type="hidden"> <input name="inputTime"
				type="hidden"> <input name="inputUser.id" type="hidden">
			<input name="pool" type="hidden">
			<table align="center">
				<tr>
					<td align="right">客户：</td>
					<td><input class="easyui-textbox" name="name" required="true"></td>
				</tr>
				<tr>
					<td align="right">联系人：</td>
					<td><input class="easyui-textbox" name="contacts"
						required="true"></td>
				</tr>
				<tr>
					<td align="right">年龄：</td>
					<td><input class="easyui-numberbox" name="age" required="true"></td>
				</tr>
				<tr>
					<td align="right">性别：</td>
					<td><input data-sex="sex1" type="radio" name="sex"
						value="true">男 <input data-sex="sex1" type="radio"
						name="sex" value="false">女</td>
				</tr>
				<tr>
					<td align="right">电话：</td>
					<td><input class="easyui-numberbox" validType="hzOrywOrszOrxhx" name="tel" required="true"></td>
				</tr>
				<tr>
					<td align="right">邮箱：</td>
					<td><input class="easyui-textbox" name="email" required="true"></td>
				</tr>
				<tr>
					<td align="right">QQ：</td>
					<td><input class="easyui-textbox" name="qq" required="true"></td>
				</tr>
				<tr>
					<td align="right">微信：</td>
					<td><input class="easyui-textbox" name="wechat"
						required="true"></td>
				</tr>
				<tr>
					<td align="right">服务级别：</td>
					<td><select id="status" name="status" class="easyui-combobox"
						data-options="editable:false,panelHeight:'auto'"
						style="width: 147px;">
							<option value="1">普通客户</option>
							<option value="2">中级客户</option>
							<option value="3">高级客户</option>
							<option value="4">VIP客户</option>
					</select>
				</tr>
				<tr>
					<td align="right">客户来源：</td>
					<td><select id="sourceId" name="customerSource.id"
						pagination="true" class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/customer/customerSource.do',
		                    method: 'get',
		                    columns: [[
		             	        {field:'id',title:'编号',width:80},
		                        {field:'name',title:'客户来源',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>

				<tr>
					<td align="right">营销人员：</td>
					<td><select id="sellerId" name="seller.id" pagination="true"
						class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'realName',
		                    mode: 'remote',
		                    url: '/customer/customerSeller.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'realName',title:'营销人员',width:120},
 								{field:'tel',title:'电话',width:120}, 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>

				<tr>
					<td align="right">客户职业：</td>
					<td><select id="cusJobId" name="job.id" pagination="true"
						class="easyui-combogrid" style="width: 147px"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/customer/customerJob.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'name',title:'客户职业',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td align="right">收入阶梯：</td>
					<td><select id="salaryId" name="salaryLevel.id"
						pagination="true" class="easyui-combogrid" style="width: 100%"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/customer/customerSalaryLevel.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'name',title:'客户收入阶梯',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" data-cmd="customerSave"
			class="easyui-linkbutton c6" iconCls="icon-ok" style="width: 90px">保存</a>
		<a href="javascript:void(0)" data-cmd="customerCancle"
			class="easyui-linkbutton" iconCls="icon-cancel" style="width: 90px">取消</a>
	</div>

	<!--客户移交面板  -->
	<div id="transDialog" class="easyui-dialog"
		style="width: 400px; padding: 10px 20px" modal="true" closed="true"
		buttons="#dialogbuttons">
		<div class="ftitle">客户移交</div>
		<form id="transForm" method="post" novalidate>
			<input name="id" type="hidden">
			<table align="center">
				<tr>
					<td align="right">客户姓名：</td>
					<td><input class="easyui-textbox" id="cusName"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">原营销人员：</td>
					<td><input class="easyui-textbox" id="oldSeller"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">新营销人员：</td>
					<td><select id="newSellerId" name="newSeller.id"
						pagination="true" class="easyui-combogrid" style="width: 147px"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'realName',
		                    mode: 'remote',
		                    url: '/customer/customerSeller.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'realName',title:'营销人员',width:50},
		                        {field:'tel',title:'电话',width:60}
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td align="right" valign="top">移交原因：</td>
					<td><input name="transReason" class="easyui-textbox"
						style="width: 147px; height: 100px" data-options="multiline:true"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dialogbuttons">
		<a href="javascript:void(0)" data-cmd="transferSave"
			class="easyui-linkbutton c4" iconCls="icon-ok" style="width: 90px">确定</a>
		<a href="javascript:void(0)" data-cmd="transferCancle"
			class="easyui-linkbutton c2" iconCls="icon-cancel"
			style="width: 90px">取消</a>
	</div>

	<!--高级查询弹出框  -->
	<div id="as" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" modal="true"
		closed="true" buttons="#customerAdvanceDialog-buttons">
		<div class="ftitle">客户信息查询</div>
		<form id="customerAdvanceForm" method="post" novalidate>
			<table align="center">
				<tr>
					<td>营销人员：</td>
					<td><input id="customer_seller" name="sellerName"
						class="easyui-textbox" data-options="panelHeight:'auto'" /></td>
				</tr>
				<tr>
					<td align="right">收入水平：</td>
					<td><select id="ccff" class="easyui-combogrid" name="salaryId"
						style="width: 147px;"
						data-options="   
				            panelWidth: 450,
				            mode:'remote',
				            idField:'id',  
				            fitColumns:true,
				            textField:'name',    
				            url:'/customer/customerSalaryLevel.do', 
				            pagination:true,   
				            columns:[[    
				                {field:'name',title:'收入阶梯',width:100}     
				            ]]    
			        "></select>
					</td>
				</tr>
				<tr>
					<td>客户职业：</td>
					<td><select id="cusJobId" name="jobId" pagination="true"
						class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/customer/customerJob.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'name',title:'客户职业',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td align="right">客户来源：</td>
					<td><select id="sourceId" name="customerSource.id"
						pagination="true" class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 450,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/customer/customerSource.do',
		                    method: 'get',
		                    columns: [[
		             	        {field:'id',title:'编号',width:80},
		                        {field:'name',title:'客户来源',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 高级查询的bottons -->
	<div id="customerAdvanceDialog-buttons">
		<a href="javascript:void(0)" data-cmd="customerAdvanceSearch"
			class="easyui-linkbutton c6" iconCls="icon-ok" style="width: 90px">查询</a>
		<a href="javascript:void(0)" data-cmd="customerAdvanceCancle"
			class="easyui-linkbutton" iconCls="icon-cancel" style="width: 90px">取消</a>
	</div>
	<!-- 查看详情信息 -->
</body>
</html>