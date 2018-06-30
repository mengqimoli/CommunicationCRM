//在页面加载完毕后
$(function() {
	// 1、声明需要缓存的对象
	var systemLogGrid, systemLogSearchForm,systemMenuAdvanceSearchDlg,systemMenuAdvanceSearchForm;
	// 2、做缓存
	// 数据表格
	systemLogGrid = $("#systemLog-datagrid");
	// 简单查询表单
	systemLogSearchForm = $("#systemLog-search-form")
	// 高级查询对话框
	systemMenuAdvanceSearchDlg = $("#systemMenuAdvanceSearchDlg");
	// 高级查询表单
	systemMenuAdvanceSearchForm = $("#systemMenuAdvanceSearchForm ");
	// 3、初始化组件
	// 4、创建一个命令对象，打包方法
	var cmdObj = {
		del : function() {
			console.debug('del:function!');
			// 获取选中行
			var rowData = systemLogGrid.datagrid("getSelected");
			// 判断
			if (!rowData) {
				// 提示
				$.messager.alert("温馨提示", "请选中要删除的日志！！！", "info");
				return;
			}
			// 确认删除
			$.messager.confirm("温馨提示", "日志很重要,确认删除?", function(yes) {
				if (yes) {
					// 获取数据的唯一标识
					var id = rowData.id;
					// 发送AJAX请求，修改ID对应数据状态
					$.get("/systemLog/delete.do?id=" + id, function(data) {
						if (data.success) {
							$.messager.alert("温馨提示", data.message, "info",
									function() {
										systemLogGrid.datagrid("reload");
									});
						}
					}, "json");
				}
			});
		},
		delALL : function() {
			var rows = $(systemLogGrid.datagrid("selectAll"));
			var rows = $(systemLogGrid.datagrid("getSelections"));
			var ids = "";
			// 弹出一个删除确认框
			$.messager.confirm('删除确认框', '您确定要删除当前页所有日志信息吗，请考虑确认删除?', function(r) {
				if (r) {
					$.each(rows, function(index, ele) {
						ids = ids + ele.id + ',';
					})
					$.get("/systemLog/deleteALL.do", {
						ids : ids
					}, function(data) {
						if (data.success) {
							systemLogGrid.datagrid("reload");
						} else {
							$.messager.alert("错误提示信息",
									"<h3 style= 'color:red;'>" + data.message
											+ "</h3>", 'error');
						}
					})
				}
			})
		},
		refresh : function() {
			systemLogGrid.datagrid("reload", {});
		},
		doSearch : function() {// 过滤查询
			console.debug('doSearch:function!');
			var paramObj = systemLogSearchForm.serializeJson();
			// 查询
			systemLogGrid.datagrid("load", paramObj);
		},
		// 打开高级查询对话框
		openAdvanceSearch : function() {
			systemMenuAdvanceSearchDlg.form("clear");
			// 弹出之前先修改弹出框的title
			systemMenuAdvanceSearchDlg.dialog('setTitle', "查询对话框");
			// 这个是把添加窗口弹出来
			systemMenuAdvanceSearchDlg.dialog('open');
		},
		// 高级查询
		doAdvanceSearch : function() {
			var json = systemMenuAdvanceSearchForm.serializeJson();
			systemLogGrid.datagrid('load', json);
		},
		// 清空高级查询
		clearAdvanceSearchForm : function() {
			// 清空表单
			systemMenuAdvanceSearchForm.form("clear");
		},
		// 取消高级查询
		cancelAdvanceSearchDlg : function() {
			systemMenuAdvanceSearchDlg.dialog("close");
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
function statusFormatter(v, r, i) {
	switch (v) {
	case -1:
		return "<font color='red'>离职</font>";
		break;

	default:
		return "<font color='green'>在职</font>";
		break;
	}
}