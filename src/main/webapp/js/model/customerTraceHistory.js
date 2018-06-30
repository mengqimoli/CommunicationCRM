$(function() {

	var $historyDialog = $('#historyDialog');
	var $historyForm = $('#historyForm');
	var $historyDatagrid = $('#historyDatagrid');
	// 添加

	var cmdObj = {
		// 添加
		"historyAdd" : function() {
			$historyDialog.dialog('open').dialog('center').dialog('setTitle',
					'添加对话框');
			$historyForm.form('clear');

		},
		// 编辑
		"historyUpdate" : function() {
			var row = $historyDatagrid.datagrid('getSelected');
			if (row) {
				$historyDialog.dialog('open').dialog('center').dialog(
						'setTitle', '编辑对话框');
				$historyForm.form('load', row);
				$("#customerId").combogrid('setValue', row.customer);
				$("#traceTypeId").combogrid('setValue', row.traceType);
				$("#sellerId").combogrid('setValue', row.seller);
				url = '/customerTraceHistory/save.do?id=' + row.id;
			} else {
				$.messager.alert('温馨提示', '请选中要编辑的客户跟进历史！！！', 'info');
			}
		},
		// 删除
		"historyDelete" : function() {
			var row = $historyDatagrid.datagrid('getSelected');
			if (row) {
				$.messager.confirm('温馨提示', '您确定要删除【'+row.customer.name+'】客户的跟进历史吗?', function(r) {
					if (r) {
						$.post('/customerTraceHistory/delete.do', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$.messager.alert('温馨提示', result.message, 'info');
								$historyDatagrid.datagrid('reload'); // reload
																		// data
							} else {
								$.messager.alert('温馨提示', result.message, 'error');
							}
						}, 'json');
					}
				});
			} else {
				// 如果没有选中删除行
				$.messager.alert('温馨提示', '请选中要删除的客户跟进历史！！！', 'info');
			}
		},
		// 刷新
		"#historyRefesh" : function() {
			$historyDatagrid.datagrid('reload');
		},
		// 保存
		"historySave" : function() {
			$('#historyForm').form(
					'submit',
					{
						url : '/customerTraceHistory/save.do',
						onSubmit : function() {
							return $('#historyForm').form("validate");
						},
						success : function(result) {
							var $result = $.parseJSON(result);
							if (!$result.success) {
								$.messager.alert('温馨提示', result.message, 'error');
							} else {
								$historyDialog.dialog('close'); 
								$historyDatagrid.datagrid('reload'); 
							}
						}
					});
		},
		// 取消
		"historyCancle" : function() {
			$historyDialog.dialog('close');
		},
		"historyRefesh" : function(){
			$historyDatagrid.datagrid('reload',{}); 
		}
	}

	$("a[data-cmd]").click(function() {
		var dataCmd = $(this).data("cmd");
		cmdObj[dataCmd]();
	})

})
