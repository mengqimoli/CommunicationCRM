package com.wyl.crm.utils;

/**
 * @author Administrator
 *
 */
public class LogString {

	public static String getDirectServiceName(String serviceName) {
		if (serviceName != null) {
			if (serviceName.equals("ContractItemServiceImpl")) {
				return "合同明细管理";
			} else if (serviceName.equals("ContractServiceImpl")) {
				return "合同管理";
			} else if (serviceName.equals("CustomerDevPlanServiceImpl")) {
				return "客户开发计划管理";
			} else if (serviceName.equals("CustomerServiceImpl")) {
				return "客户信息管理";
			} else if (serviceName.equals("CustomerTraceHistoryServiceImpl")) {
				return "客户跟进历史管理";
			} else if (serviceName.equals("CustomerTransferServiceImpl")) {
				return "客户移交记录管理";
			} else if (serviceName.equals("DepartmentServiceImpl")) {
				return "部门管理";
			} else if (serviceName.equals("EmployeeServiceImpl")) {
				return "员工管理";
			} else if (serviceName.equals("GuaranteeServiceImpl")) {
				return "保修单管理";
			} else if (serviceName.equals("GuaranteetItemServiceImpl")) {
				return "保修单明细管理";
			} else if (serviceName.equals("OrderServiceImpl")) {
				return "定金订单管理";
			} else if (serviceName.equals("PermissionServiceImpl")) {
				return "权限管理";
			} else if (serviceName.equals("PotentialCustomerServiceImpl")) {
				return "潜在客户管理";
			} else if (serviceName.equals("ResourceServiceImpl")) {
				return "资源管理";
			} else if (serviceName.equals("RoleServiceImpl")) {
				return "角色管理";
			} else if (serviceName.equals("SystemDictionaryItemServiceImpl")) {
				return "数据字典明细管理";
			} else if (serviceName.equals("SystemDictionaryTypeServiceImpl")) {
				return "数据字典管理";
			} else if (serviceName.equals("SystemLogServiceImpl")) {
				return "系统日志管理";
			} else if (serviceName.equals("SystemMenuServiceImpl")) {
				return "系统菜单管理";
			} else {
				return "未知模块";
			}
		}
		return "未知模块";
	}

	public static String getDirectFunctionName(String functionName) {
		if (functionName != null) {
			if (functionName.equals("login")) {
				return "登录";
			} else if (functionName.equals("checkLogin")) {
				return "检查登录";
			} else if (functionName.equals("index")) {
				return "页面导向";
			} else if (functionName.equals("list")) {
				return "列表";
			} else if (functionName.equals("save")) {
				return "保存/修改";
			} else if (functionName.equals("delete")) {
				return "删除";
			} else if (functionName.equals("deleteAll")) {
				return "删除全部";
			} else if (functionName.equals("queryCount")) {
				return "查询总条数";
			} else if (functionName.equals("queryPage")) {
				return "分页查询";
			} else if (functionName.equals("getOne")) {
				return "一个条件查询";
			} else if (functionName.equals("getAll")) {
				return "查询所有";
			} else if (functionName.equals("stopUse")) {
				return "停用/离职";
			} else if (functionName.equals("startUse")) {
				return "启用/复职";
			} else if (functionName.equals("getEmployeeByUsername")) {
				return "通过用户名查询员工";
			} else if (functionName.equals("saveEmployeeRoleLinks")) {
				return "绑定用户角色";
			} else if (functionName.equals("deleteEmployeeRoleLinks")) {
				return "删除用户角色";
			} else if (functionName.equals("getRolePermissions")) {
				return "查询角色权限";
			} else if (functionName.equals("getUserPermissions")) {
				return "查询用户权限";
			} else if (functionName.equals("getPermissionByResourceName")) {
				return "根据资源获取权限";
			} else if (functionName.equals("getPermissionByPermissionName")) {
				return "根据权限名称获取权限";
			} else if (functionName.equals("initControllers")) {
				return "初始化资源模块信息";
			} else if (functionName.equals("getControllers")) {
				return "获取控制器";
			} else if (functionName.equals("importControllerResources")) {
				return "导入控制器资源";
			} else if (functionName.equals("importPermission")) {
				return "资源加入权限";
			} else if (functionName.equals("importControllerResources")) {
				return "导入控制器资源";
			} else if (functionName.equals("saveRolePermissionLink")) {
				return "绑定角色权限";
			} else if (functionName.equals("saveRolePermissionLinks")) {
				return "绑定角色权限";
			} else if (functionName.equals("deleteRolePermissionLinks")) {
				return "删除角色权限";
			} else if (functionName.equals("getUserRoles")) {
				return "获取用户角色";
			} else if (functionName.equals("deleteRolePermissionLinks")) {
				return "删除角色权限";
			} else if (functionName.equals("getSystemMenuByEmpId")) {
				return "获取用户菜单";
			} else {
				return "未知方法";
			}
		} else {
			return "未知方法";
		}
	}

	public static String getDirectparams(String paramsName) {
		if (paramsName != null) {
			if (paramsName.startsWith("EmployeeSe")) {
				return "员工";
			} else if (paramsName.startsWith("ContractItem")) {
				return "合同明细";
			} else if (paramsName.startsWith("ContractSe")) {
				return "合同";
			} else if (paramsName.startsWith("CustomerDevP")) {
				return "客户开发计划";
			} else if (paramsName.startsWith("CustomerSe")) {
				return "客户";
			} else if (paramsName.startsWith("CustomerTraceHistoryServiceImpl")) {
				return "客户跟进历史";
			} else if (paramsName.startsWith("CustomerT")) {
				return "客户移交记录";
			} else if (paramsName.startsWith("Department")) {
				return "员工部门";
			} else if (paramsName.startsWith("EmployeeSe")) {
				return "员工";
			} else if (paramsName.startsWith("OrderServ")) {
				return "订单";
			} else if (paramsName.startsWith("Permission")) {
				return "权限";
			} else if (paramsName.startsWith("PotentialCu")) {
				return "潜在客户";
			} else if (paramsName.startsWith("RepairItemS")) {
				return "保修单详细";
			} else if (paramsName.startsWith("RepairSer")) {
				return "保修单";
			} else if (paramsName.startsWith("ResourceSe")) {
				return "系统资源";
			} else if (paramsName.startsWith("RoleSer")) {
				return "角色";
			} else if (paramsName.startsWith("SystemDi")) {
				return "数据字典";
			} else if (paramsName.startsWith("SystemDictionaryItemServiceImpl")) {
				return "数据字典明细";
			} else if (paramsName.startsWith("SystemLog")) {
				return "系统日志";
			} else if (paramsName.startsWith("SystemMen")) {
				return "系统菜单";
			} else {
				return "未知参数";
			}
		} else {
			return null;
		}
	}

}
