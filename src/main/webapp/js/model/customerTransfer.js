$(function() {

	var $cutomerTransferDatagrid = $('#cutomerTransferDatagrid');

	var cmdObj = {
		// 删除
		"cutomerTransferDelete" : function() {
			var row = $cutomerTransferDatagrid.datagrid('getSelected');
			if (row) {
				$.messager.confirm('温馨提示', '您确定要删除这个客户移交记录吗?', function(r) {
					if (r) {
						$.post('/customerTransfer/delete.do', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$.messager.alert('温馨提示', result.message, 'info');
								$cutomerTransferDatagrid.datagrid('reload');
							} else {
								$.messager.alert('温馨提示', result.message, 'error');
							}
						}, 'json');
					}
				});
			} else {
				// 如果没有选中删除行
				$.messager.alert('温馨提示', '请选中要删除的客户移交记录！！！', 'info');
			}
		},
		// 刷新
		"cutomerTransferRefesh" : function() {
			$("#cutomerTransferDatagrid").datagrid('reload');
		}

	}

	$("a[data-cmd]").click(function() {

		var dataCmd = $(this).data("cmd");
		cmdObj[dataCmd]();
	})

})
