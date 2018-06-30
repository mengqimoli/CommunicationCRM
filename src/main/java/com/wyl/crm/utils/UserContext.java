package com.wyl.crm.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Permission;
import com.wyl.crm.domain.Role;
import com.wyl.crm.service.IPermissionService;

/**
 * 用户上下文对象
 * 
 * @author Administrator
 *
 */
@Component
public class UserContext {

	private static IPermissionService permissionService;

	@Autowired
	public void setPermissionService(IPermissionService permissionService) {
		UserContext.permissionService = permissionService;
	}

	public static final String USER_IN_SESSION = "user_in_session";
	public static final String USER_PERMISSIONS_IN_SESSION = "user_permissions_in_session";
	public static final String USER_ROLES_IN_SESSION = "user_roles_in_session";

	/**
	 * 提供一个方法，获取全局的request
	 * 
	 * @return
	 */
	private static HttpServletRequest getRequest() {
		// 从全局请求对象持有者中，获取ServletRequestAttributes
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		// 从ServletRequestAttributes对象中，取出放入的request请求对象
		return requestAttributes.getRequest();
	}

	/**
	 * 获取session
	 * 
	 * @return
	 */
	private static HttpSession getSession() {
		return getRequest().getSession(true);
	}

	/**
	 * 移除session
	 * 
	 * @param emp
	 */
	public static void removeSession() {
		getRequest().getSession().invalidate();
	}

	/**
	 * 把用户放入session
	 * 
	 * @param emp
	 */
	public static void setUser(Employee emp) {
		getSession().setAttribute(USER_IN_SESSION, emp);
	}

	/**
	 * 从session中取出用户
	 * 
	 * @param session
	 * @return
	 */
	public static Employee getUser() {
		return (Employee) getSession().getAttribute(USER_IN_SESSION);
	}

	public static String getRequestIP() {
		return getRequest().getRemoteAddr(); // 获取IP
	}

	/**
	 * 根据用户传入的资源，验证用户的访问权限
	 * 
	 * @param resource
	 * @return true : 有权限<br/>
	 *         false ： 没有权限
	 */
	public static boolean checkUserPermissionByResource(String resource) {
		// 获取资源权限
		Permission dbPermission = permissionService.getPermissionByResourceName(resource);
		// 检查资源权限
		return UserContext.checkUserPermission(dbPermission);

	}

	public static boolean checkUserPermission(Permission dbPermission) {
		// System.out.println(permissionService);
		// 1、 验证资源是否有权限（是否有锁）
		// Permission dbPermission =
		// permissionService.getPermissionByResource(resource);
		// System.out.println(dbPermission);
		if (dbPermission == null) {
			return true; // 直接放行
		}

		// 2、验证用户是否有对应权限（是否有钥匙）
		// 1) 获取当前登录用户的所有权限
		List<Permission> userPermissions = UserContext.getUserPermissions();
		// System.out.println("userPermissions:" + userPermissions);
		// 2) 把用户的权限，一一和 当前访问资源的权限进行比对
		// 1> 比对细节权限
		for (Permission userPermission : userPermissions) {
			if (userPermission.getId() == dbPermission.getId()) {
				return true; // 存在则放行
			}
		}
		// 2> 比对all权限
		String resource = dbPermission.getResource();
		if (!StringUtils.isEmpty(resource)) {
			// 获取all资源地址
			// cn.itsource.crm.web.controller.EmployeeController:save
			// cn.itsource.crm.web.controller.EmployeeController:ALL
			String allResource = resource.split(":")[0] + ":ALL";
			for (Permission userPermission : userPermissions) {
				// 比较资源地址
				if (allResource.equals(userPermission.getResource())) {
					// 存在则放行
					return true;
				}
			}
		}
		// 3、 如果都没有匹配，那么返回false，拦截请求
		return false;
	}

	/**
	 * 从sesssion中取出用户权限
	 * 
	 * @return
	 */
	public static List<Permission> getUserPermissions() {
		// TODO Auto-generated method stub
		return (List<Permission>) getSession().getAttribute(USER_PERMISSIONS_IN_SESSION);
	}

	/**
	 * 把用户的权限放入session
	 * 
	 * @param userPermissions
	 */
	public static void setUserPermissions(List<Permission> userPermissions) {
		getSession().setAttribute(USER_PERMISSIONS_IN_SESSION, userPermissions);
	}

	public static boolean checkUserPermissionByPermissionName(String permissionName) {
		// 根据权限名称，获取权限
		Permission dbPermission = permissionService.getPermissionByPermissionName(permissionName);
		// 验证权限
		return UserContext.checkUserPermission(dbPermission);
	}

	public static boolean isRole(String roleStr) {
		//获取传入的角色集合
		String[] roleArr = roleStr.split(",");
		//获取用户所有的角色 
		List<Role> userRoles = UserContext.getUserRoles();
		//比对，看用户是否有指定角色
		for (String roleSn : roleArr) {
			for (Role role : userRoles) {
				if(roleSn.equals(role.getSn())){
					return true;
				}
			}	
		}
		//没有指定角色，返回false
		return false;
	}

	//把用户角色放入session
	public static void setUserRoles(List<Role> userRoles) {
		getSession().setAttribute(USER_ROLES_IN_SESSION, userRoles);
	}
	//从session中获取用户角色
	public static List<Role> getUserRoles(){
		return (List<Role>) getSession().getAttribute(USER_ROLES_IN_SESSION);
	}

}
