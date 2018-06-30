package com.wyl.crm.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Permission;
import com.wyl.crm.domain.Role;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.service.IEmployeeService;
import com.wyl.crm.service.IPermissionService;
import com.wyl.crm.service.IRoleService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.EncryptUtil;
import com.wyl.crm.utils.UserContext;

@Controller
public class LoginController {
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private MailSender mailSender;

	@RequestMapping("/goLogin")
	public String goLogin() {
		return "login";
	}

	@RequestMapping("/checkLogin")
	@ResponseBody
	public AjaxResult checkLogin(String username, String password) {
		// 声明
		AjaxResult ar = null;
		try {

			// 检查登陆
			Employee emp = employeeService.checkLogin(username, EncryptUtil.encryptPassword(password));
			// 把用户放入session
			UserContext.setUser(emp);
			// 查到用户对应的角色信息
			List<Role> userRoles = roleService.getUserRoles(emp.getId());
			// 把用户的角色放入session
			UserContext.setUserRoles(userRoles);
			// 查到用户对应的权限信息
			List<Permission> userPermissions = permissionService.getUserPermissions(emp);
			// 把用户的权限放入session
			UserContext.setUserPermissions(userPermissions);
			// 封装响应信息
			ar = new AjaxResult("登录成功!");
		} catch (LogicException e) {
			// 封装响应信息
			ar = new AjaxResult("登陆失败，" + e.getMessage(), e.getErrorCode());
		}
		// 返回
		return ar;
	}

	@RequestMapping("/checkUsername")
	@ResponseBody
	public AjaxResult checkUsername(String username) {
		AjaxResult ar = null;
		Employee employee = employeeService.getEmployeeByUsername(username);
		if (employee != null) {
			ar = new AjaxResult("该用户名已注册！！！", -101);
		} else {
			ar = new AjaxResult("<font color='green'>√</font>");
		}
		return ar;
	}

	// 退出登录
	@RequestMapping("/logout")
	public String logout() {
		UserContext.removeSession();
		return "login";
	}

	// 跳转注册页面
	@RequestMapping("/goRegister")
	public String goRegister() {
		System.out.println("goRegister..........");
		return "register";
	}

	// 员工注册
	@RequestMapping("/register")
	@ResponseBody
	public AjaxResult register(Employee employee) {
		System.out.println("register......");
		AjaxResult ar = null;
		employee.setStatus(1);
		employee.setInputTime(new Date());
		employee.setPassword(EncryptUtil.encryptPassword(employee.getPassword()));
		employeeService.save(employee);
		ar = new AjaxResult("注册成功！！！");

		return ar;
	}

	// 跳转忘记密码
	@RequestMapping("/goGetPassword")
	public String goGetPassword(String username, String email) {
		System.out.println("goGetPassword......");
		return "getPassword";
	}

	// 通过邮箱获取密码
	@RequestMapping("/getPassword")
	@ResponseBody
	public AjaxResult getPassword(String username, String email) {
		System.out.println("getPassword.........");
		AjaxResult ar = null;

		Employee employee = employeeService.getEmployeeByUsername(username);
		if (employee == null) {
			ar = new AjaxResult("密码找回失败，登录账号错误！！！", -101);
		} else {
			if (email.equals(employee.getEmail())) {
				// 简单邮件对象
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				// 发送人:和配置一致
				mailMessage.setFrom("wyl513901@163.com");
				// 收件人
				mailMessage.setTo(email);
				// 主题
				mailMessage.setSubject("密码找回通知");
				// 内容
				mailMessage.setText("您的密码是：“" + employee.getPassword() + "”，请您注意保管好您的密码！");
				// 发送
				mailSender.send(mailMessage);
				ar = new AjaxResult("密码找回成功，请注意接收邮箱信息!");
			} else {
				ar = new AjaxResult("密码找回失败，邮箱地址错误！！！", -102);
			}
		}

		return ar;
	}

	// 通过邮箱获取密码
	@RequestMapping("/sendEmailCode")
	@ResponseBody
	public AjaxResult checkEmailCode(String email) {
		System.out.println("sendEmailCode.........");
		String emailCode = "572485";
		AjaxResult ar = null;
		try {
			// 简单邮件对象
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			// 发送人:和配置一致
			mailMessage.setFrom("wyl513901@163.com");
			// 收件人
			mailMessage.setTo(email);
			// 主题
			mailMessage.setSubject("通信设备公司员工注册");
			// 内容
			mailMessage.setText("员工注册验证码：“" + emailCode + "”，请您注意保管！");
			// 发送
			mailSender.send(mailMessage);
			ar = new AjaxResult(emailCode);
		} catch (MailException e) {
			e.printStackTrace();
			ar = new AjaxResult("验证码发送失败!", -101);
		}
		return ar;
	}

}
