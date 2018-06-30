//---页面加载完毕后---
$(function() {

	// 1. 声明页面中可能会用到组件
	var roleSearchForm, roleGrid, roleDlg, roleForm, selectedPermissionGrid, allPermissionGrid;

	// 2. 缓存页面组件
	roleGrid = $("#roleGrid");
	roleDlg = $("#roleDlg");
	roleForm = $("#roleForm");
	selectedPermissionGrid = $("#selectedPermissionGrid");
	allPermissionGrid = $("#allPermissionGrid");
	roleSearchForm = $("#roleSearchForm");

	$("#permissionModelType").combobox({
		// 相当于html >> select >> onChange事件
		onChange : function() {
			var options = $("#permissionModelType").combobox('getValue');
			allPermissionGrid.datagrid("load", {
				"permissionModelType" : options
			});
		}
	});

	// 4. 定义一个事件处理对象,把事件相关的逻辑都封装到一个对象上(命令对象)
	var cmdObj = {
		create : function() {
			// 清空表单中的输入框内容
			// roleForm.form("clear");
			$("input[name=sn]").val('');
			$("input[name=name]").val('');
			$("input[name=intro]").val('');

			// 清空表格数据
			selectedPermissionGrid.datagrid("loadData", {
				"rows" : [],
				"total" : 0
			});

			roleDlg.dialog("open");
		},
		edit : function() {
			// console.debug('edit:function');
			// 获取行选中数据
			var rowData = roleGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				$.messager.alert("温馨提示", "请选中要编辑的角色！！！", "info");
				return;
			}
			// 重置表单
			// roleForm.form("reset");
			// 打开对话框
			roleDlg.dialog("open");

			// 加载数据
			// 发ajax请求，加载角色对应的权限
			$.get("/permission/getRolePermissions.do", {
				"id" : rowData.id
			}, function(data) {
				selectedPermissionGrid.datagrid("loadData", {
					"rows" : data
				})
			}, "json");

			// 手动把数据放入已选表格中

			/*
			 * form的load方法运行的机制:根据表单中元素的name属性,去json对象中,取值. 例如: <input
			 * name="dept.name"> , {name:"zs",dept:{name:""}} >>
			 * obj["dept.name"] 针对上面特殊情况,{name:"zs",dept:{name:""}}>>
			 * {name:"zs",dept:{name:"ls"},"dept.name":"zs"}
			 */
			roleForm.form("load", rowData);

		},
		del : function() {
			// console.debug('del:function');
			// 获取行选中数据
			var rowData = roleGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				$.messager.alert("温馨提示", "请选中要删除的角色！！！", "info");
				return;
			}
			$.messager.confirm("温馨提示", "是否要删除该角色?", function(yes) {
				if (yes) {
					$.get("/role/delete.do", {
						"id" : rowData.id
					}, function(data) {
						console.debug(data);
						if (data.success) {
							// 关闭对话框
							roleDlg.dialog("close");
							// 提示,刷新
							$.messager.alert("温馨提示", data.message, "info",
									function() {
										// 刷新表格数据
										roleGrid.datagrid("reload");
									});
						} else {
							$.messager.alert("温馨提示", data.message, "info",
									function() {
										// 根据错误码,定位到单元格
									});
						}
					}, "json");
				}
			});
		},
		refresh : function() {
			roleGrid.datagrid("reload");
		},
		save : function() {
			roleForm.submit();
		},
		cancel : function() {
			roleDlg.dialog("close");
		},
		doSearch : function() {
			var paramObj = roleSearchForm.serializeJson();
			// 查询
			roleGrid.datagrid("load", paramObj);
		}
	};
	// 3. 初始化组件
	roleGrid.datagrid({
		url : "/role/list.do",
		fit : true,
		border : false,
		fitColumns : true,
		striped : true,
		toolbar : $("#department-toolbar"),
		rownumbers : true,
		singleSelect : true,
		pagination : true,
	});

	roleDlg.dialog({
		width : 920,
		height : 500,
		title : "添加/编辑角色信息",
		closed : true,
		modal : true,
		buttons : [ {
			text : "添加",
			iconCls : "icon-save",
			handler : cmdObj['save']
		}, {
			text : "取消",
			iconCls : "icon-cancel",
			handler : cmdObj['cancel']
		} ]
	// "#roleDlgBtn"
	});

	// 角色表单
	roleForm.form({
		url : "/role/save.do",
		// 提交额外参数
		onSubmit : function(param) {

			// 期望结构： permissions[index].id = 8
			// param['permissions['+i+'].id']=1;

			// 获取选中表格的所有行
			var selectedRows = selectedPermissionGrid.datagrid("getRows");
			// 遍历
			$.each(selectedRows, function(i, row) {
				// 拼接需要的结构
				param['permissions[' + i + '].id'] = row.id;
			});
		},
		success : function(data) {
			// 把响应结果转换成JSON对象
			data = $.parseJSON(data);
			if (data.success) {
				// 关闭对话框
				roleDlg.dialog("close");
				// 提示,刷新
				$.messager.alert("温馨提示", data.message, "info", function() {
					// 刷新表格数据
					roleGrid.datagrid("reload");
				});
			} else {
				$.messager.alert("温馨提示", data.message, "info", function() {
					// 根据错误码,定位到单元格
				});
			}
		}
	});

	// 所有权限表格
	allPermissionGrid.datagrid({
		url : "/permission/list.do",
		title : "所有权限",
		width : 430,
		height : 340,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		columns : [ [ {
			field : 'name',
			title : '权限名称',
			width : 10
		}, {
			field : 'resource',
			title : '资源地址',
			width : 40
		} ] ],
		onDblClickRow : function(index, rowData) {
			// 获取操作行数据
			console.debug(rowData);

			// 添加前，判断
			// 获取目标表格，取出目标表格的所有数据
			var selectedRows = selectedPermissionGrid.datagrid("getRows");
			console.debug(selectedRows);
			// 遍历对比
			// for (var i = 0; i < selectedRows.length; i++) {
			var isRepeat = false;
			$.each(selectedRows, function(index, row) {
				console.debug(index);
				if (row.id == rowData.id) {
					isRepeat = true;
					// 如果重复，提示
					// $.messager.alert("温馨提示","该权限已被选中！！","info");
					$.messager.show({
						title : '温馨提示',
						msg : '该权限已被选中！！',
						showType : 'show',
						style : {
							right : '',
							bottom : ''
						},
						timeout : 1000
					});
					return false;
				}
			});
			// 没有重复，则添加

			// 找到目标表格,把数据添加到目标行
			if (!isRepeat) {
				selectedPermissionGrid.datagrid("appendRow", rowData);
			}
		}
	});
	// 获取分页组件
	var pagerUI = allPermissionGrid.datagrid("getPager");
	pagerUI.pagination({
		showPageList : false,
		showRefresh : false,
		displayMsg : '',
		buttons : [ {
			iconCls : 'icon-search',
			handler : function() {

			}
		} ]
	});

	// 已选权限表格
	selectedPermissionGrid.datagrid({
		title : "已选权限",
		width : 430,
		height : 340,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		singleSelect : true,
		columns : [ [ {
			field : 'name',
			title : '权限名称',
			width : 10
		}, {
			field : 'resource',
			title : '资源地址',
			width : 40
		}

		] ],
		onDblClickRow : function(index, rowData) {
			selectedPermissionGrid.datagrid("deleteRow", index);
		}
	});

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
