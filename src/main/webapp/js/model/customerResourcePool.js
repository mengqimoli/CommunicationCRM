//在页面加载完毕后
$(function() {
	// 1、声明需要缓存的对象
	var customerDatagrid;
	// 2、做缓存
	customerDatagrid = $("#customerDatagrid");
	// 3、初始化组件
	// 4、创建一个命令对象，打包方法
	var cmdObj = {
		poolDelete : function() {
			// 获取选中行
			var rowData = customerDatagrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要删除资源池中的客户！！！", "info");
				return;
			}
			// 确认删除
			$.messager.confirm("温馨提示", "您确定要从客户资源池中删除【" + rowData.name
					+ "】客户吗，删除之后无法恢复客户信息?", function(yes) {
				if (yes) {
					// 获取数据的唯一标识
					var id = rowData.id;
					// 发送AJAX请求，修改ID对应数据状态
					$.get("/customer/delete.do?id=" + id, function(data) {
						if (data.success) {
							$.messager.alert("温馨提示", "客户彻底删除成功！！！", "info",
									function() {
										customerDatagrid.datagrid("reload");
									});
						} else {
							$.messager.alert("温馨提示",
									"删除失败，原因是：" + data.massage, "error");
						}
					}, "json");
				}
			});
		},
		followCustomer : function() {
			// 获取选中行
			var rowData = customerDatagrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要继续跟进的客户！！！", "info");
				return;
			}
			$.messager
					.confirm(
							"温馨提示",
							"确认要继续跟进【" + rowData.name + "】客户?",
							function(yes) {
								if (yes) {
									// 获取数据的唯一标识
									var id = rowData.id;
									// 发送AJAX请求，修改ID对应数据状态
									$
											.get(
													"/customer/followCustomer.do?id="
															+ id,
													function(data) {
														if (data.success) {
															$.messager
																	.alert(
																			"温馨提示",
																			"客户跟进成功,请加油！！！",
																			"info",
																			function() {
																				customerDatagrid
																						.datagrid("reload");
																			});
														}
													}, "json");
								}
							});
		},
		poolRefesh : function() {
			customerDatagrid.datagrid('reload');
		}
	}
	// 5、对页面所有按钮，进行一次统一监听
	$("a[data-cmd]").on("click", function() {
		// 获取命令信息
		var cmd = $(this).data("cmd");
		if (cmd) {
			// 根据cmd命令值，调用方法
			cmdObj[cmd]();
		}
	});
});