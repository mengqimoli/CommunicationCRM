$(function() {
	var $customerDialog = $('#customerDialog');
	var $customerForm = $('#customerForm');
	var $customerDatagrid = $('#customerDatagrid');
	var cmdObj = {
		// 查看详情
		customerDetails : function() {
			$customerDialog.dialog('open').dialog('center').dialog('setTitle',
					'添加对话框');
			$customerForm.form('clear');
		},
		// 添加
		"customerAdd" : function() {
			$customerDialog.dialog('open').dialog('center').dialog('setTitle',
					'添加对话框');
			$customerForm.form('clear');
		},
		// 编辑
		"customerUpdate" : function() {
			var row = $customerDatagrid.datagrid('getSelected');
			if (row) {
				$customerDialog.dialog('open').dialog('center').dialog(
						'setTitle', '编辑对话框');
				$customerForm.form('load', row);
				console.debug(row);
				$("#sellerId").combogrid('setValue', row.seller.id);
				$("#sourceId").combogrid('setValue', row.customerSource);
				$("#cusJobId").combogrid('setValue', row.job.id);
				$("#salaryId").combogrid('setValue', row.salaryLevel.id);
				url = '/customer/save.do?id=' + row.id;
			} else {
				$.messager.alert('温馨提示', '请选中要编辑的客户信息！！！', 'info');
			}
		},
		// 删除
		"customerDelete" : function() {
			var row = $customerDatagrid.datagrid('getSelected');
			if (row) {
				$.messager
						.confirm(
								'温馨提示',
								'您确定要删除【' + row.name + '】客户吗?',
								function(r) {
									if (r) {
										$
												.post(
														'/customer/delete.do',
														{
															id : row.id
														},
														function(result) {
															if (result.success) {
																$.messager
																		.alert(
																				'温馨提示',
																				'客户删除成功！！！',
																				'info');
																$customerDatagrid
																		.datagrid('reload');
															} else {
																$.messager
																		.alert(
																				'温馨提示',
																				'删除失败,原因是:'
																						+ result.massage
																						+ '',
																				'error');
															}
														}, 'json');
									}
								});
			} else {
				// 如果没有选中删除行
				$.messager.alert('温馨提示', '请选中要删除的客户信息！！！', 'info');
			}
		},
		// 刷新
		"customerRefesh" : function() {
			$("#customerDatagrid").datagrid('reload', {});
		},
		// 保存
		"customerSave" : function() {
			$('#customerForm').form(
					'submit',
					{
						url : '/customer/save.do',
						onSubmit : function() {
							return $('#customerForm').form("validate");
						},
						success : function(result) {
							var $result = $.parseJSON(result);
							if (!$result.success) {
								$.messager.alert('温馨提示', '保存失败,原因是:'
										+ $result.massage + '', 'error');
							} else {
								$customerDialog.dialog('close');
								$customerDatagrid.datagrid('reload');
							}
						}
					});
		},
		// 取消
		"customerCancle" : function() {
			$customerDialog.dialog('close');
		},
		// 放入资源池
		"putPool" : function() {
			// 拿到选择的行数据
			var row = $customerDatagrid.datagrid('getSelected');
			if (row) {
				$.messager
						.confirm(
								'温馨提示',
								'您确定要把【' + row.name + '】客户放入资源池?',
								function(r) {
									if (r) {
										// 获取数据的唯一标识
										var id = row.id;
										// 发送AJAX请求，修改ID对应数据状态
										$
												.get(
														"/customer/putPool.do?id="
																+ id,
														function(data) {
															if (data.success) {
																$.messager
																		.alert(
																				"温馨提示",
																				"【"
																						+ row.name
																						+ "】客户成功放入资源池！！！",
																				"info",
																				function() {
																					$customerDatagrid
																							.datagrid("reload");
																				});
															} else {
																$.messager
																		.alert(
																				'温馨提示',
																				'客户放入资源池失败：'
																						+ result.massage
																						+ '',
																				'error');
															}
														}, "json");

									}
								});
			} else {
				$.messager.alert('温馨提示', '请选中要放入资源池的客户！！！', 'info');
			}
		},
		// 客户移交对话框
		"transfer" : function() {
			var row = $customerDatagrid.datagrid('getSelected');
			if (row) {
				$.messager
						.confirm('温馨提示', '您确定要移交【' + row.name + '】客户吗?',
								function(r) {
									if (r) {
										$('#transForm').form('load', row);
										$('#cusName').textbox('setText',
												row.name);
										$('#oldSeller').textbox({
											value : row.seller.realName
										});

										$("#transDialog").dialog('open')
												.dialog('center').dialog(
														'setTitle', '客户移交对话框');
									}
								});

			} else {
				$.messager.alert('温馨提示', '请选中要移交的客户！！！', 'error');
			}
		},
		// 确认移交
		"transferSave" : function() {
			$('#transForm').form(
					'submit',
					{
						url : '/customer/transferCustomer.do',
						onSubmit : function() {
							return $('#transForm').form("validate");
						},
						success : function(result) {
							result = $.parseJSON(result);
							if (!result.success) {
								$.messager.alert('温馨提示', '客户移交失败,原因是:'
										+ $result.massage + '', 'error');
							} else {
								// 关闭对话框
								$("#transDialog").dialog("close");

								$.messager.alert("温馨提示",
										"客户移交成功，该客户已经不属于你了！！！", "info",
										function() {
											// 刷新
											$customerDatagrid
													.datagrid("reload");
										});
							}
						}
					});
		},
		// 移交面板取消
		"transferCancle" : function() {
			$("#transDialog").dialog('close');
		},
		// 普通查询
		"searchCustomer" : function() {
			var pJson = $("#searchForm").serializeJson();// 扩展了原型的方法，
			$customerDatagrid.datagrid('load', pJson);
		},
		"customerAdvanceSearch" : function() {
			// 高级查询的条件
			var pJson = $("#customerAdvanceForm").serializeJson();
			$customerDatagrid.datagrid('load', pJson);
		},
		"customerAdvanceCancle" : function() {
			// 取消
			$("#as").dialog('close');
		},
		// 打开高级查询弹出框
		"openAdvanceSearch" : function() {
			// 打开高级查询的dialog对话框
			$("#as").dialog("open").dialog("center").dialog("setTitle",
					"高级查询对话框");
		}
	}

	$("a[data-cmd]").click(function() {

		var dataCmd = $(this).data("cmd");
		cmdObj[dataCmd]();
	});
});
