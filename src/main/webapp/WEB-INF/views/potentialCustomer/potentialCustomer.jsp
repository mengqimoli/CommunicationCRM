<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.wyl.com/myTag/permission" prefix="wyl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>潜在客户管理</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/potentialCustomer.js"></script>
</head>
<body>
	<table id="potentialCustomerDatagrid" title="潜在客户"
		class="easyui-datagrid" style="width: 700px; height: 250px"
		url="/potentialCustomer/list.do" toolbar="#datagrid-toolbar"
		pagination="true" rownumbers="true" fit="true" fitColumns="true"
		singleSelect="true" pageList="[2,5,10,20,50]" pageSize="20">
		<thead>
			<tr>
				<th field="id" hidden=true width="10"></th>
				<th field="name" width="10">客户名称</th>
				<th field="age" width="10">年龄</th>
				<th field="sex" formatter="sexFormatter" width="10">性别</th>
				<th field="linkMan" width="10">联系人</th>
				<th field="linkManTel" width="15">联系人电话</th>
				<th field="inputTime" width="20">录入时间</th>
				<th field="inputUser" formatter="objectFormatter" width="10">录入人</th>
				<th field="customerSource" formatter="objectFormatter" width="10">客户来源</th>
				<th field="intro" width="20">客户描述</th>
				<th field="successRate" width="10">成功几率</th>
				<th field="seller" formatter="sellerFormatter" width="10">营销人员</th>
				<th field="status" formatter="statusFormatter" width="10">状态</th>
			</tr>
		</thead>
	</table>
	<div id="datagrid-toolbar">
		<wyl:checkPermission permissionName="潜在客户保存/修改">
			<a href="javascript:void(0)" data-cmd="create"
				class="easyui-linkbutton " iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:void(0)" data-cmd="edit"
				class="easyui-linkbutton " iconCls="icon-edit" plain="true">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="潜在客户删除">
			<a href="javascript:void(0)" data-cmd="del"
				class="easyui-linkbutton " iconCls="icon-cut" plain="true">删除</a>
		</wyl:checkPermission>
		<a href="javascript:void(0)" data-cmd="refresh"
			class="easyui-linkbutton " iconCls="icon-reload" plain="true">刷新</a>
		<wyl:checkPermission permissionName="潜在客户导入">
			<a href="javascript:void(0)" data-cmd="openCotentialCustomerUpload"
				class="easyui-linkbutton c1" iconCls="icon-sum" plain="true">潜在客户导入</a>
		</wyl:checkPermission>
		<!-- &nbsp;<a
			href="javascript:void(0)" data-cmd="developCustomer"
			class="easyui-linkbutton c2" iconCls="icon-redo" plain="true">开发潜在客户</a> -->
		<wyl:checkPermission permissionName="潜在客户转正式客户">
			<a href="javascript:void(0)" data-cmd="changeToCustomer"
				class="easyui-linkbutton c6" iconCls="icon-tip" plain="true">转正式客户</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="潜在客户指派">
			<a href="javascript:void(0)" data-cmd="designateCustomer"
				class="easyui-linkbutton c6" iconCls="icon-man" plain="true">潜在客户指派</a>
		</wyl:checkPermission>
	</div>
	<div id="potentialCustomerDialog" class="easyui-dialog"
		style="width: 420px; height: 350px; padding: 10px 30px" modal="true"
		closed="true" buttons="#dlgbuttons">
		<div class="ftitle">潜在客户信息添加/编辑</div>
		<form id="potentialCustomerForm" method="post" novalidate>
			<input name="id" type="hidden"> <input name="inputTime"
				type="hidden"> <input name="status" type="hidden"> <input
				name="inputUser.id" type="hidden" style="width: 80%">
			<table align="center">
				<tr>
					<td align="right">客户名称：</td>
					<td><input class="easyui-textbox" name="name" required="true"></td>
				</tr>
				<tr>
					<td align="right">客户年龄：</td>
					<td><input class="easyui-numberbox" name="age" required="true"></td>
				</tr>
				<tr>
					<td align="right">客户性别：</td>
					<td><input data-sex="sex1" type="radio" name="sex"
						id="defaultRadio" value="true">男 <input data-sex="sex1"
						type="radio" name="sex" value="false">女</td>
				</tr>
				<t r>
					<td align="right">成功几率：</td>
					<td><input class="easyui-textbox" name="successRate"></td>
				</tr>
				<tr>
					<td align="right">客户描述：</td>
					<td><input class="easyui-textbox" name="intro"></td>
				</tr>
				<tr>
					<td align="right">联系人：</td>
					<td><input class="easyui-textbox" name="linkMan"
						required="true"></td>
				</tr>
				<tr>
					<td align="right">联系人电话：</td>
					<td><input class="easyui-numberbox" name="linkManTel"
						required="true"></td>
				</tr>
				<tr>
					<td align="right">客户来源：</td>
					<td><input name="customerSource.id" class="easyui-combotree"
						style="width: 147px;"
						data-options="
				        					url:'/potentialCustomer/customerSource.do'
				        					,method:'get'
				        					,panelHeight:'auto'
				        					,required:'true'
				        					">
						</select></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlgbuttons">
		<a href="javascript:void(0)" data-cmd="save"
			class="easyui-linkbutton c6" iconCls="icon-ok" style="width: 90px">保存</a>
		<a href="javascript:void(0)" data-cmd="cancel"
			class="easyui-linkbutton" iconCls="icon-cancel" style="width: 90px">取消</a>
	</div>
	<!-- 潜在客户导入Excel -->

	<div id="potentialCustomerUploadDlg" class="easyui-dialog"
		style="width: 420px; height: 170px;" modal="true" closed="true">
		<form id="potentialCustomerUploadForm" method="post"
			enctype="multipart/form-data">
			<div class="easyui-panel"
				style="width: 100%; max-width: 420px; padding: 33px 60px;">
				<input id="potentialCustomerExcle" class="easyui-filebox"
					name="potentialCustomerExcle" /> <br /> <br /> <a id="btnUpload"
					href="javascript:void(0)" data-cmd="upload"
					class="easyui-linkbutton c6" iconCls="icon-save"
					style="width: 90px; position: relative; left: 100px;">上传</a>
			</div>
		</form>
	</div>

	<div id="developCustomerDialog" class="easyui-dialog"
		style="width: 400px; height: 350px; padding: 10px 20px" modal="true"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">制定潜在客户开发计划</div>
		<form id="developCustomerForm" method="post" novalidate>
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
		                    panelWidth: 400,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/customerDevPlan/planType.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'name',title:'计划实施方式',width:120},
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
		                    panelWidth: 500,
		                    panelHeight:150,
		                    textField: 'name',
		                    mode: 'remote',
		                    url: '/potentialCustomer/list.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'name',title:'潜在客户姓名',width:120},
		                        {field:'gender',title:'潜在客户性别',width:120}, 
		                        {field:'tel',title:'潜在客户电话',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td align="right">营销人员：</td>
					<td><select id="inputUserId" pagination="true" required="true"
						name="inputUser.id" class="easyui-combogrid" style="width: 147px"
						data-options="
		                    idField: 'id',
		                    panelWidth: 500,
		                    panelHeight:150,
		                    textField: 'realName',
		                    mode: 'remote',
		                    url: '/customer/customerSeller.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'realName',title:'跟进人姓名',width:120},
		                        {field:'tel',title:'跟进人电话',width:120}, 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td align="right" valign="top">详细内容：</td>
					<td><input name="planDetails" class="easyui-textbox"
						required="true" style="width: 148px; height: 80px"
						data-options="multiline:true"></td>
				</tr>
			</table>
		</form>

	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" data-cmd="doVevelop"
			class="easyui-linkbutton c6" iconCls="icon-ok" style="width: 90px">保存</a>
		<a href="javascript:void(0)" data-cmd="cancleVevelop"
			class="easyui-linkbutton" iconCls="icon-cancel" style="width: 90px">取消</a>
	</div>
	<!-- 潜在客户指派 -->
	<div id="designateCustomerDialog" class="easyui-dialog"
		style="width: 400px; height: 200px; padding: 10px 20px" modal="true"
		closed="true" buttons="#designate-dlg-buttons">
		<div class="ftitle">潜在客户指派</div>
		<form id="designateCustomerForm" method="post" novalidate>
			<input name="id" type="hidden">
			<table align="center">
				<tr>
					<td align="right">客户名称：</td>
					<td><input class="easyui-textbox" style="width: 147px"
						name="name" required="true" readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">营销人员：</td>
					<td><select id="sellerId" pagination="true" required="true"
						name="seller.id" class="easyui-combogrid" style="width: 147px"
						data-options="
		                    idField: 'id',
		                    panelWidth: 500,
		                    panelHeight:150,
		                    textField: 'realName',
		                    mode: 'remote',
		                    url: '/customer/customerSeller.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'realName',title:'跟进人姓名',width:120},
		                        {field:'tel',title:'跟进人电话',width:120}, 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="designate-dlg-buttons">
		<a href="javascript:void(0)" data-cmd="doDesignate"
			class="easyui-linkbutton c6" iconCls="icon-ok" style="width: 90px">保存</a>
		<a href="javascript:void(0)" data-cmd="cancleDesignate"
			class="easyui-linkbutton" iconCls="icon-cancel" style="width: 90px">取消</a>
	</div>
	<div id="changeDialog" class="easyui-dialog"
		style="width: 400px; height: 480px; padding: 10px 20px" modal="true"
		closed="true" buttons="#changebuttons">
		<div class="ftitle">转正式客户：请登记客户详细信息</div>
		<form id="changeForm" method="post" novalidate>
			<input name="id" type="hidden" style="width: 80%"> <input
				name="inputTime" type="hidden" style="width: 80%"> <input
				name="inputUser.id" type="hidden" style="width: 80%">
			<table align="center" class="tdright" style="padding-top: 8px;">
				<tr>
					<td>客户：</td>
					<td><input class="easyui-textbox" name="name" required="true"></td>
				</tr>
				<tr>
					<td>联系人：</td>
					<td><input id="contacts" class="easyui-textbox"
						name="contacts" required="true"></td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><input id="tel" class="easyui-textbox" name="tel"
						required="true"></td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td><input class="easyui-textbox" name="age"></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td align="left"><div style="text-align: left">
							<input data-sex="sex1" type="radio" name="sex" value="true">男
							<input data-sex="sex1" type="radio" name="sex" value="false">女
						</div></td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td><input class="easyui-textbox" name="email" required="true"></td>
				</tr>
				<tr>
					<td>QQ：</td>
					<td><input class="easyui-textbox" name="qq"></td>
				</tr>
				<tr>
					<td>微信：</td>
					<td><input class="easyui-textbox" name="wechat"></td>
				</tr>
				<tr>
					<td>服务等级：</td>
					<td><select name="status" class="easyui-combobox"
						data-options="required:true,editable:false,panelHeight:'auto'"
						style="width: 147px;">
							<!-- <option value="1">第一次开发</option>
                				<option value="2">第二次开发</option> -->
							<option value="1">普通客户</option>
							<option value="2">中级客户</option>
							<option value="3">高级客户</option>
							<option value="4">高级客户</option>
					</select></td>
				</tr>
				<tr>
					<td>客户来源：</td>
					<td><select id="sourceCustomId" name="customerSource.id"
						pagination="true" class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 400,
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
					<td>营销人员：</td>
					<td><select id="sellerEmpId" name="seller.id" required="true"
						pagination="true" class="easyui-combogrid" style="width: 147px;"
						data-options="
                    		idField: 'id',
		                    panelWidth: 400,
		                    textField: 'realName',
		                    mode: 'remote',
		                    url: '/customer/customerSeller.do',
		                    method: 'get',
		                    columns: [[
		                        {field:'id',title:'编号',width:80},
		                        {field:'realName',title:'营销人员',width:120} 
		                    ]],
		                    fitColumns: true,
		                    labelPosition: 'top'
		                ">
					</select></td>
				</tr>
				<tr>
					<td>客户职业：</td>
					<td><select id="cusJobId" name="job.id" pagination="true"
						class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 400,
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
					<td>收入阶梯：</td>
					<td><select id="salaryId" name="salaryLevel.id"
						pagination="true" class="easyui-combogrid" style="width: 147px;"
						data-options="
		                    idField: 'id',
		                    panelWidth: 400,
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
	<div id="changebuttons">
		<a href="javascript:void(0)" data-cmd="changeSave"
			class="easyui-linkbutton c6" iconCls="icon-ok" style="width: 90px">保存</a>
		<a href="javascript:void(0)" data-cmd="changeCancle"
			class="easyui-linkbutton" iconCls="icon-cancel" style="width: 90px">取消</a>
	</div>
</body>
</html>