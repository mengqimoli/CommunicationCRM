$(function() {

	var $planDialog = $('#planDialog');
	var $planForm = $('#planForm');
	var $planDatagrid = $('#planDatagrid');
	var customerDevPlanSearchKeyForm = $("#customerDevPlanSearchKeyForm");
	var customerAdvanceSearchDlg = $("#customerAdvanceSearchDlg");
	var customerAdvanceSearchForm = $("#customerAdvanceSearchForm");
	// 添加

	var cmdObj = {
		// 添加
		"planAdd" : function() {
			$planDialog.dialog('open').dialog('center').dialog('setTitle',
					'添加对话框');
			$planForm.form('clear');
		},
		// 编辑
		"planUpdate" : function() {
			var row = $planDatagrid.datagrid('getSelected');
			if (row) {
				
				$planDialog.dialog("setTitle", "编辑对话框");
				
				if (row.inputUser) {
					row["inputUser.id"] = row.inputUser.id;
				}
				if (row.seller) {
					row["seller.id"] = row.seller.id;
				}
				
				$("#planTypeId").combogrid('setValue', row.planType);
				$("#poteId").combogrid('setValue', row.pote);
				/*$("#inputUserId").combogrid('setValue', row.inputUser);*/
				$("#result").combobox('setValue', row.result);
				
				$planForm.form('load', row);
				
				$planDialog.dialog("open");
				
				url = '/customerDevPlan/save.do?id=' + row.id;
			} else {
				$.messager.alert('温馨提示', '请选中要编辑的客户开发计划！！！', 'info');
			}
		},
		// 删除
		"planDelete" : function() {
			var row = $planDatagrid.datagrid('getSelected');
			if (row) {
				$.messager.confirm('温馨提示', '您确定要删除【' + row.name
						+ '】客户的这个开发计划吗?', function(r) {
					if (r) {
						$.post('/customerDevPlan/delete.do', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$.messager.alert('温馨提示', '客户的这个开发计划删除成功！！！', 'info');
								$planDatagrid.datagrid('reload'); // reload
								// the user
								// data
							} else {
								$.messager.alert('温馨提示', '删除失败，原因是：'
										+ result.massage + '', 'error');
							}
						}, 'json');
					}
				});
			} else {
				// 如果没有选中删除行
				$.messager.alert('温馨提示', '请选中要删除的客户开发计划！！！', 'info');
			}
		},
		// 刷新
		"planRefesh" : function() {
			$planDatagrid.datagrid('reload', {});
		},
		// 保存
		"planSave" : function() {
			$planForm.form('submit', {
				url : '/customerDevPlan/save.do',
				onSubmit : function() {
					return $planForm.form("validate");
				},
				success : function(result) {
					var $result = $.parseJSON(result);
					if (!$result.success) {
						$.messager.alert('温馨提示', '该客户开发计划保存失败，原因是：'
								+ $result.massage + '', 'error');
					} else {
						$planDialog.dialog('close');
						$planDatagrid.datagrid('reload');
					}
				}
			});
		},
		doSearch : function() {// 过滤查询
			var paramObj = customerDevPlanSearchKeyForm.serializeJson();
			// 查询
			$planDatagrid.datagrid("load", paramObj);
		},
		// 取消
		"planCancle" : function() {
			$planDialog.dialog('close');
		},
		openAdvanceSearch : function() { // 打开搜索对话框
			console.debug('openSearchDlg:function!');
			// 清空表单
			// 设置标题
			customerAdvanceSearchDlg.dialog("setTitle", "高级搜索对话框");
			// 打开搜索对话框
			customerAdvanceSearchDlg.dialog("open");
		},
		doAdvanceSearch : function() {// 过滤查询
			var paramObj = customerAdvanceSearchForm.serializeJson();
			// 查询
			$planDatagrid.datagrid("load", paramObj);
		},
		cancelAdvanceSearchDlg : function() {
			console.debug('cancelSearchDlg:function!');
			customerAdvanceSearchDlg.dialog("close");
		},
		clearAdvanceSearchForm : function() {
			// 清空表单
			customerAdvanceSearchForm.form("clear");
		}
	}

	$("a[data-cmd]").click(function() {

		var dataCmd = $(this).data("cmd");
		cmdObj[dataCmd]();
	})

})
