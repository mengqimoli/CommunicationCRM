<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>通信设备公司CRM系统-登录</title> <%@ include
		file="/WEB-INF/views/common/common.jsp"%>
	<link href="/style/login.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		//alert($);
		//console.debug(document.documentElement);
		$(document.documentElement).on("keyup", function(event) {
			//console.debug(event.keyCode);
			var keyCode = event.keyCode;
			console.debug(keyCode);

			if (keyCode === 13) { // 捕获回车 
				submitForm(); // 提交表单
			}
		});
		// 检查自己是否是顶级页面
		if (window.top != window) {// 如果不是顶级
			//把子页面的地址，赋给顶级页面显示
			window.top.location.href = window.location.href;
		}
	</script>
</head>
<body>
	<script type="text/javascript">
		function submitForm() {
			$("#loginForm").form(
					"submit",
					{
						url : "/checkLogin.do",
						success : function(data) {
							data = $.parseJSON(data);
							console.debug(data);
							if (data.success) {
								location.href = "/index.do";
							} else {
								$.messager.alert("温馨提示", data.message, "info",
										function() {
											if (data.errorCode == -100) {// 用户名错误
												$("input[name=username]")
														.focus();
											} else if (data.errorCode == -101) {// 密码错误
												$("input[name=password]")
														.focus();
											}
										});
							}

						}

					});
		}
		function goRegister() {
			window.location.href = "/goRegister.do";
		}
	</script>
	<div class="second_body">
		<form id="loginForm" class="easyui-form" method="post">
			<div class="logo"></div>
			<div class="title-zh">通信设备公司CRM系统</div>
			<div class="title-en" >Communication
				Equipment Company CRM System</div>
			<div class="message" data-bind="html:message"></div>
			<table border="0" style="width: 300px;">
				<tr>
					<td style="white-space: nowrap; padding-bottom: 5px; width: 55px;">用户名：</td>
					<td colspan="2"><input name="username" type="text"
						id="userCode" class="login" value="wangkejing"
						data-bind="value:form.userCode" /></td>
				</tr>
				<tr>
					<td class="lable"
						style="white-space: nowrap; letter-spacing: 0.5em; vertical-align: middle">密码：</td>
					<td colspan="2"><input name="password" type="password"
						id="password" class="login" value="000000"
						data-bind="value:form.password" /></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input type="checkbox"
						data-bind="checked:form.remember" /><span>记住我</span></td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center"><input
						type="button" value="登录" onclick="submitForm();"
						class="login_button" /> <input type="button" value="去注册"
						onclick="goRegister();" class="reset_botton"
						data-bind="click:resetClick" /></td>
				</tr>
				<tr>
					<td></td>
					<td>&emsp;<font size="2px;"><a href="/goGetPassword.do"
							style="text-decoration: none; color: #0074a6">忘记密码？</a></font></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
