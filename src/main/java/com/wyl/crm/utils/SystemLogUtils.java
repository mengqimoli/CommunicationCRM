package com.wyl.crm.utils;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wyl.crm.domain.SystemLog;
import com.wyl.crm.service.ISystemLogService;

/**
 * 面向切面： 1、切入点 2、通知 3、连接点(JoinPoint)
 */
@Component("logUtils")
public class SystemLogUtils {

	@Autowired
	private ISystemLogService logService;

	// 写入日志
	public void writeLog(JoinPoint joinPoint) {
		System.out.println("getTarget:" + joinPoint.getTarget().getClass());
		System.out.println("getThis:" + joinPoint.getThis().getClass());
		// if(1==1)
		// return;
		Object serviceClz = joinPoint.getTarget();// 这一步就是去获取当前用户操作的是哪一个service

		// 前置检查
		// 这个是判断当前用户如果操作的是ISystemLogService
		// 日志本身的Service的话就不能进行记录直接忽略，也是不能进行记录的，因为这个会造成死循环
		if (serviceClz instanceof ISystemLogService) {
			// 如果是日志操作，不需要做后续了，中断返回
			return;
		}

		// 创建一个日志对象
		SystemLog systemLog = new SystemLog();
		// 封装日志

		// 设置操作的具体时间
		systemLog.setOpUser(UserContext.getUser());
		// 设置操作的具体时间
		systemLog.setOpTime(new Date());
		// 设置操作的IP
		systemLog.setOpIp(UserContext.getRequestIP()); // 获取IP

		System.out.println("joinPoint:" + joinPoint);
		System.out.println("logService:" + logService);
		// cn.itsource.crm.service.impl.EmployeeServiceImpl:checkLogin
		// 员工管理:登录
		// 然后是去获取用户操作的是哪个Service的哪个方法
		String serviceName = joinPoint.getTarget().getClass().getSimpleName();// 这个是获得操作的Service的名称
		System.out.println("Service的方法名称.........." + serviceName);
		String methodName = joinPoint.getSignature().getName();// 这个是获取操作的方法名称
		System.out.println("操作的方法名称.........." + methodName);
		String function = "";
		if (UserContext.getUser() != null) {
			/*function = UserContext.getUser().getUsername() + "：操作了“" + LogString.getDirectServiceName(serviceName)
					+ "”中的 “" + LogString.getDirectFunctionName(methodName) + "”方法";*/
			function = UserContext.getUser().getUsername() + "：操作了“" + serviceName
			+ "”中的 “" + methodName + "”方法";
		} else {
			function = "未登录人员：操作了“" + LogString.getDirectServiceName(serviceName) + "”中的 “"
					+ LogString.getDirectFunctionName(methodName) + "”方法";
		}
		systemLog.setFunction(function);
		if (methodName.equals("findPermissionByLoginUserId") || methodName.equals("getDepartmentTree")) {
			return;// 当判定当前用户是操作的这两个方法时，就不需要去记录这个用户的ip和参数了，因为这个是我们资格系统自动在调用的方法
		}

		// 写日志
		logService.save(systemLog);
	}
}
