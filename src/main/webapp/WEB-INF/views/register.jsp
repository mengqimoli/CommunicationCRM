<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>通信设备公司CRM系统-注册</title> <%@ include
		file="/WEB-INF/views/common/common.jsp"%>
	<script type="text/javascript" src="/js/register.js"></script>
	<link href="/style/register.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="second_body">
		<form id="register-form" class="easyui-form" method="post">
			<div class="logo"></div>
			<div class="title-zh">公司员工注册</div>
			<div class="message"></div>
			<table class="table-zh" border="0" style="width: 460px;">
				<tr>
					<td style="white-space: nowrap;" width="70px;">用户名：</td>
					<td><input name="username" id="username" class="login"
						width="180px;" placeholder="Username" /></td>
					<td width="210px;"><span id="usernameMsg"</span></td>
				</tr>
				<tr>
					<td
						style="white-space: nowrap; letter-spacing: 0.5em; vertical-align: middle;">密码：</td>
					<td><input name="password" type="password" id="password"
						class="login" placeholder="●●●●●●" /></td>
					<td><span id="passwordMsg"></span></td>
				</tr>
				<tr>
					<td style="white-space: nowrap; vertical-align: middle;">确认密码：</td>
					<td><input type="password" id="checkPassword" class="login"
						placeholder="●●●●●●" /></td>
					<td><span id="checkPasswordMsg"></span></td>
				</tr>
				<tr>
					<td style="white-space: nowrap; vertical-align: middle;">真实姓名：</td>
					<td><input name="realName" id="realName" class="login"
						placeholder="张三" /></td>
					<td><span id="realNameMsg"></td>
				</tr>
				<tr>
					<td
						style="white-space: nowrap; letter-spacing: 0.5em; vertical-align: middle">电话：</td>
					<td><input name="tel" id="tel" class="login"
						placeholder="15230805967" /></td>
					<td><span id="telMsg"></span></td>
				</tr>
				<tr>
					<td
						style="white-space: nowrap; letter-spacing: 0.5em; vertical-align: middle">邮箱：</td>
					<td><input name="email" id="email" class="login" placeholder="admin@qq.com"/></td>
					<td><span id="emailMsg"></span></td>
				</tr>
				<tr>
					<td></td>
					<td><a id="sendEmailCode" href="#"
						class="easyui-linkbutton c6" style="width: 100px;"><font
							color="white">获取邮箱验证</font></a>&emsp;<input id="emailCode"
						class="easyui-textbox" style="width: 80px;"
						data-options="prompt:'邮箱验证码'" /></td>
					<td></td>
				</tr>
				<tr>
					<!-- <td colspan="2" style="text-align: center; padding-top: 15px;"><input
						id="doRegister" type="button" value="注册" class="login_button" />
						<input onclick="goLogin();" type="button" value="去登录"
						class="reset_botton" /></td> -->
					<td colspan="2" style="text-align: right; padding-top: 18px;"><a
						id="doRegister" href="#" class="easyui-linkbutton c1"
						style="width: 90px;"><font color="white">注册</font></a>&emsp;&emsp;&emsp;<a
						href="#" onclick="goLogin();" class="easyui-linkbutton c8"
						style="width: 90px;"><font color="white">返回登陆</font></a></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
