<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同管理</title>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/js/model/contract.js"></script>
</head>
<body>
	<div class="easyui-layout" id="layout" border="false"
		data-options="fit:true,border:false">
		<div id="westPanel" region="west" headerCls="border-right"
			bodyCls="border-right">
			<table id="contractDatagrid" class="easyui-datagrid"
				url="/contract/list.do" title="合同管理" fit="true" border="false"
				fitColumns="true" singleSelect="true" rownumbers="true"
				toolbar="#contdatagridtoolbar" pagination="true" pageSize="20"
				pageList="[2,5,10,20,50]">
				<thead>
					<tr>
						<th field="sn" width="10">合同编号</th>
						<th field="customer" width="10" formatter="objectFormatter">合同客户</th>
						<th field="signTime" width="10">签订时间</th>
						<th field="sum" width="10">合同金额</th>
						<th field="seller" width="10" formatter="objectFormatter">营销人员</th>
						<th field="status" width="10" formatter="statusFormatter">状态</th>
					</tr>
				</thead>
			</table>
		</div>

		<div region="center" id="center" headerCls="border-right">
			<table id="contractItemDatagrid" class="easyui-datagrid"
				url="/contractItem/list.do" title="合同明细管理" fit="true" border="false"
				fitColumns="true" singleSelect="true" rownumbers="true"
				toolbar="#contItemdatagridtoolbar" pagination="true" pageSize="10"
				pageList="[2,5,10,20,50]">
				<thead>
					<tr>
						<th field="contract" width="10" formatter="objectFormatter">所属合同</th>
						<th field="money" width="10">付款金额</th>
						<th field="scale" width="10">所占比例</th>
						<th field="payTime" width="10">付款时间</th>
						<th field="status" width="10" formatter="statusItemFormatter">状态</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="contdatagridtoolbar">
		<wyl:checkPermission permissionName="合同保存/修改">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				data-cmd="contractAdd">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
				plain="true" data-cmd="contractEdit">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="合同删除">
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"
				data-cmd="contractDelete">删除</a>
		</wyl:checkPermission>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload"
			plain="true" data-cmd="contractRefesh">刷新</a> <a
			data-cmd="printContract" href="javascript:void(0)"
			class="easyui-linkbutton c6 leave" iconCls="icon-print">打印合同</a>
		<div>
			<form id="contractSearchForm" method="post"
				enctype="application/x-www-form-urlencoded">
				关键字: <input name="searchKey" style="width: 120px;"
					class="easyui-textbox"> <a href="#"
					class="easyui-linkbutton color-blue" iconCls="icon-search"
					data-cmd="searchForm">搜索</a> <a href="#"
					class="easyui-linkbutton color-blue" iconCls="icon-cut"
					data-cmd="searchFormClear">清空查询</a> <a href="#"
					class="easyui-linkbutton c4 color-blue" iconCls="icon-search"
					data-cmd="advanceSearch">高级查询</a>
			</form>
		</div>
	</div>
	<div id="contItemdatagridtoolbar">
		<wyl:checkPermission permissionName="合同明细保存/修改">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				data-cmd="addItem">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
				plain="true" data-cmd="editItem">编辑</a>
		</wyl:checkPermission>
		<wyl:checkPermission permissionName="合同明细删除">
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"
				data-cmd="deleteItem">删除</a>
		</wyl:checkPermission>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload"
			plain="true" data-cmd="refreshItem">刷新</a>
	</div>

	<!-- 合同的添加或编辑对话框 -->
	<div id="contractDatagridDlg" class="easyui-dialog"
		style="width: 400px; height: 300px; padding: 10px 20px" modal="true"
		closed="true" buttons="#contractDatagridDlgButtons">
		<div class="ftitle">合同信息添加/编辑</div>
		<form id="contractDatagridDlgForm" method="post" novalidate>
			<!-- 区分是添加还是编辑 -->
			<input name="id" type="hidden" />
			<table align="center" class="tdright">
				<!-- <tr>
					<td>合同编号：</td>
					<td><input name="sn" class="easyui-textbox"
						required="required"></td>
				</tr> -->
				<tr>
					<td>合同客户：</td>
					<td><select id="cg" class="easyui-combogrid" required="true"
						name="customer.id" style="width: 147px;"
						data-options="    
				            panelWidth:450,    
				            idField:'id', 
				            textField:'name',    
				            url:'/customer/list.do',   
				            fitColumns:true,
				            pagination:true,
				            columns:[[    
				                {field:'name',title:'客户姓名',width:60},    
				                {field:'email',title:'邮箱',width:120},    
				                {field:'tel',title:'电话',width:100},
				                {field:'qq',title:'QQ',width:100}    
				            ]]    
        				"></select>
					</td>
				</tr>
				<tr>
					<td>签订时间：</td>
					<td><input name="signTime" class="easyui-datebox"
						required="true"> </input></td>
				</tr>
				<tr>
					<td>合同金额：</td>
					<td><input name="sum" required="true" class="easyui-numberbox"
						value="100" data-options="min:0,prefix:'￥',precision:2"></input></td>
				</tr>
				<tr>
					<td>营销人员：</td>
					<td><input id="cc" name="seller.id" style="width: 147px;"
						data-options="required:true,editable:false,panelHeight:'auto'"></td>
				</tr>

				<tr>
					<td>合同状态：</td>
					<td><select name="status" class="easyui-combobox"
						data-options="required:true,editable:false,panelHeight:'auto'"
						style="width: 147px;">
							<option value="1">生效</option>
							<option value="-1">停用</option>
					</select></td>
				</tr>
			</table>
		</form>
		<!-- 添加或编辑对话框按钮 -->
		<div id="contractDatagridDlgButtons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-save" style="width: 90px" data-cmd="contractSave">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" style="width: 90px" data-cmd="contractCancel">取消</a>
		</div>
	</div>
	<!-- 合同明细的添加或编辑对话框 -->
	<div id="contractItemDatagridDlg" class="easyui-dialog"
		style="width: 400px; height: 320px; padding: 10px 20px" modal="true"
		closed="true" buttons="#contractItemDatagridDlgButtons">
		<div class="ftitle">合同明细添加/编辑</div>
		<form id="contractItemDatagridDlgForm" method="post" novalidate>
			<input name="id" type="hidden" />
			<!-- 区分是添加还是编辑 -->
			<table align="center" class="tdright">
				<tr>
					<td>所属合同：</td>
					<td><input id="contractCombo" name="contract.id"
						class="easyui-combotree" style="width: 147px;"
						data-options="
				        					method:'get'
				        					,panelHeight:'auto'
				        					,required:'true'
				        					,setValue:'id'
				        					,setText:'sn'
				        					"></td>
				</tr>
				<tr>
					<td>付款金额：</td>
					<td><input name="money" required="true"
						class="easyui-numberbox" value="100"
						data-options="min:0,prefix:'￥',precision:2"></input></td>
				</tr>
				<tr>
					<td>付款时间：</td>
					<td><input name="payTime" class="easyui-datebox"
						required="true"> </input></td>
				</tr>
				<tr>
					<td>合同状态：</td>
					<td><select name="status" class="easyui-combobox"
						data-options="required:true,editable:false,panelHeight:'auto'"
						style="width: 147px;">
							<option value="1">已付款</option>
							<option value="-1">未付款</option>
					</select></td>
				</tr>
				<tr>
					<td valign="top">摘要：</td>
					<td><input name="intro" class="easyui-textbox"
						style="width: 147px; height: 80px" data-options="multiline:true"></td>
					</td>
				</tr>
			</table>
		</form>
		<!-- 添加或编辑明细对话框按钮 -->
		<div id="contractItemDatagridDlgButtons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-save" style="width: 90px" data-cmd="saveItem">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" style="width: 90px" data-cmd="cancelItem">取消</a>
		</div>
	</div>
	<!-- 高级查询对话框 -->
	<div id="contractSearchDatagridDlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" modal="true"
		closed="true" buttons="#contractSearchDatagridDlgButtons">
		<div class="ftitle">合同信息查询</div>
		<form id="contractSearchDatagridDlgForm" method="post" novalidate>
			<table align="center" class="tdright">
				<tr>
					<td>开始时间：</td>
					<td><input name="beginTime" class="easyui-datebox"></input></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td><input name="endTime" class="easyui-datebox"></input></td>
				</tr>
				<tr>
					<td>合同金额：</td>
					<td><input name="sum" class="easyui-numberbox"></td>
				</tr>
				<tr>
					<td>合同客户：</td>
					<td><select id="cg" class="easyui-combogrid" name="customerId"
						style="width: 147px;"
						data-options="    
				            panelWidth:450,    
				            idField:'id', 
				            textField:'name',    
				            url:'/customer/list.do',   
				            fitColumns:true,
				            pagination:true,
				            columns:[[    
				                {field:'name',title:'客户姓名',width:60},    
				                {field:'age',title:'年龄',width:100},    
				                {field:'email',title:'邮箱',width:120},    
				                {field:'qq',title:'QQ',width:100}    
				            ]]    
        			"></select>
					</td>
				</tr>
				<wyl:checkPermission permissionName="合同管理">
					<tr>
						<td>营销人员：</td>
						<td><input id="order_seller" name="sellerId"
							data-options="editable:false,panelHeight:'auto'" /></td>
					</tr>
				</wyl:checkPermission>
			</table>
		</form>
	</div>
	<!-- 高级查询对话框按钮 -->
	<div id="contractSearchDatagridDlgButtons">
		<a data-cmd="contractSearchSave" href="javascript:void(0)"
			class="easyui-linkbutton c6" iconCls="icon-ok" style="width: 90px">查询</a>
		<a data-cmd="contractSearchCancel" href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<!-- 打印的Dialog start -->
	<div id="printDialog" iconCls="icon-print" title="打印合同" modal="true"
		class="easyui-dialog"
		style="width: 700px; height: 520px; padding: 10px 20px" closed="true">
		<div iconCls="icon-print" modal="true">
			<a href="javascript:window.print();"
				style="text-decoration: underline;">点击打印</a>
			<table width=100%>
				<tr>
					<th width="50" align="left"></th>
					<th width="50" align="right">合同编号：<span id="sn"></span></th>
				</tr>
				<tr>
					<th align="left">甲方：<span id="jiafang"></span></th>
					<th align="right">签订地点：石家庄市</th>
				</tr>
				<tr>
					<th align="left">乙方：<span id="yifang"></span></th>
					<th align="right">签订时间：<span id="time"></span></th>
				</tr>
			</table>

			<p style="font-size: 14px;">甲乙双方经协商一致，就__乙__方向__甲__方购买事宜达成以下协议，双方共同遵守:</p>
			<table border="1" cellpadding="0" cellspacing="0"
				style="width: 100%; border: 1px solid #333; text-align: center; margin: 20px 0 20px; font-size: 16px;">
				<tr>
					<td colspan="6">合同内容</td>
				</tr>
				<tr>
					<th>合同编号</th>
					<th>合同客户</th>
					<th>签订时间</th>
					<th>合同金额</th>
					<th>营销人员</th>
					<th>备注</th>
				</tr>
				<tr id="printContractList"></tr>
			</table>
			<table border="1" cellpadding="0" cellspacing="0"
				style="width: 100%; border: 1px solid #333; text-align: center; margin: 20px 0; font-size: 16px;">
				<thead>
					<tr>
						<td colspan="4">合同明细列表</td>
					</tr>
					<tr>
						<th>已交金额</th>
						<th>总金额</th>
						<th>回款占比</th>
						<th>付款时间</th>
					</tr>
				</thead>
				<tbody id="contractItemList"></tbody>
			</table>
			<p style="font-size: 14px;">预付款。甲方于本合同签署之日起，在30 日内，将合同总成交价的 30%
				，即人民币(元)，作为预付款支付给乙方。乙方在收到上述款项后，以传真向甲方确认。如甲方不按上述规定准时支付预付款，则延迟货物进场时间.
			</p>
			<p style="font-size: 14px;">
				甲方和乙双方合同信息如有变更，变更一方应在合同规定的相关付款期限前二十天内以书面方式通知对方，如未按时通知或通知有误应付相关及连带责任
			</p>
		</div>
	</div>
</body>
</html>