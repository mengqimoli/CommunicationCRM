<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>通信设备公司CRM系统-登录</title> <%@ include
		file="/WEB-INF/views/common/common.jsp"%>
	<script type="text/javascript" src="/js/getPassword.js"></script>
	<link href="/style/getPassword.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="second_body">
		<form id="getPassword-form" class="easyui-form" method="post">
			<div class="logo"></div>
			<div class="title-zh">密码找回</div>
			<div class="message"></div>
			<table border="0" style="width: 300px;">
				<tr>
					<td style="white-space: nowrap; text-align: right;">登录账号：</td>
					<td><input name="username" id="username" class="login" /></td>
				</tr>
				<tr>
					<td></td>
					<td><span id="errorMsg"></span></td>
				</tr>
				<tr>
					<td style="white-space: nowrap; text-align: right;">邮箱地址：</td>
					<td><input name="email" id="email" class="login" /><span
						id="emailMsg"></span></td>
				</tr>
				<tr>
					<td><br /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center"><a id="getPassword"
						href="#" class="easyui-linkbutton c1" style="width: 90px;"><font
							color="white">找回密码</font></a>&emsp;&emsp;&emsp;<a href="#"
						onclick="goLogin();" class="easyui-linkbutton c8"
						style="width: 90px;"><font color="white">返回登陆</font></a>
					<!-- <input
						id="getPassword" type="button" value="找回" class="login_button" />&emsp;
						<input onclick="goLogin();" type="button" value="登录" class="reset_botton" /> --></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
