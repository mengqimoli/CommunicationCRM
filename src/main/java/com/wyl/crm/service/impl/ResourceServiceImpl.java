package com.wyl.crm.service.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Permission;
import com.wyl.crm.domain.Resource;
import com.wyl.crm.mapper.PermissionMapper;
import com.wyl.crm.mapper.ResourceMapper;
import com.wyl.crm.query.ResourceQuery;
import com.wyl.crm.service.IResourceService;
import com.wyl.crm.utils.PageResult;

@Service
public class ResourceServiceImpl implements IResourceService {

	private ResourceMapper resourceMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	public void setResourceMapper(ResourceMapper resourceMapper) {
		this.resourceMapper = resourceMapper;

		resourceMapper.createTable();
	}

	@Override
	public void save(Resource resource) {
		resourceMapper.save(resource);
	}

	@Override
	public void update(Resource resource) {
		resourceMapper.update(resource);
	}

	@Override
	public void delete(Long id) {
		resourceMapper.delete(id);
	}

	@Override
	public Resource getOne(Long id) {
		return resourceMapper.getOne(id);
	}

	@Override
	public List<Resource> getAll() {
		return resourceMapper.getAll();
	}

	private String[] DEFAULT_MVC_PACKGES = { "com.wyl.crm.web.controller" };//自定义包路径

	private Map<String, List<String>> modules = new HashMap<>();

	@Override
	public void initControllers() {
		// Tomcat启动时初始化控制器
		for (String pkgPath : DEFAULT_MVC_PACKGES) {
			try {
				loadPkgController(pkgPath);//加载包下的所有控制器(controller)
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
	}

	/**
	 * 加载包下资源
	 * 
	 * @param pkgPath
	 * @throws IOException
	 */
	private void loadPkgController(String pkgPath) throws IOException {
		if (modules.get(pkgPath) == null) {
			List<String> clzNames = new ArrayList<>();
			/*
			 * 把包下的模块缓存
			 */
			String packageDirName = pkgPath.replace(".", "/");
			Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				File file = new File(url.getFile());
				// 把此目录下的所有文件列出
				String[] classes = file.list();
				// 循环此数组，并把.class去掉
				for (String className : classes) {
					className = className.substring(0, className.length() - 6);
					// 拼接上包名，变成全限定名
					String qName = pkgPath + "." + className;

					try {
						Class<?> class1 = Class.forName(qName);//根据类的全限定名称获取类对象
						//判定类或者方法是否加入了Resource
						if (Class.forName(qName).isAnnotationPresent(com.wyl.crm.utils.Resource.class)) {
							clzNames.add(qName);//将注解里的说明加入到list中
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					/*
					 * try { // 检查是否需要展示的资源（进而是否支持权限控制） if
					 * (Class.forName(qName).isAnnotationPresent(com.wyl.crm.
					 * utils.Resource.class)) { clzNames.add(qName); } } catch
					 * (ClassNotFoundException e) { e.printStackTrace(); }
					 */

				}
			}
			modules.put(pkgPath, clzNames);
			System.out.println(modules);
		}
	}

	@Override
	public Map<String, List<String>> getControllers() {
		return modules;
	}

	@Override
	public void importControllerResources(String moudle) {
		try {
			// 获得模块控制器对应的字节码对象
			Class<?> clz = Class.forName(moudle);

			// 获得控制器方法
			com.wyl.crm.utils.Resource cResource = (com.wyl.crm.utils.Resource) clz
					.getAnnotation(com.wyl.crm.utils.Resource.class);
			// 如果有自定义（中文）名称，就用定义的，没有就用原始全类名
			String cName = cResource.value() != "" ? cResource.value() : clz.getName();

			// 扫描方法
			Method[] declaredMethods = clz.getMethods();
			for (Method method : declaredMethods) {
				// 验证打了资源标签的方法
				if (method.isAnnotationPresent(com.wyl.crm.utils.Resource.class)) {
					// 获得方法名称
					com.wyl.crm.utils.Resource mResource = method.getAnnotation(com.wyl.crm.utils.Resource.class);
					String mName = mResource.value() != "" ? mResource.value() : method.getName();
					// 根据注解内容，自动添加资源
					addMoudleResource(moudle, cName, method.getName(), mName);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void addMoudleResource(String moudle, String moudleAlias, String methodName, String methodNameAlias) {
		// 获得资源地址
		String url = moudle + ":" + methodName;
		// 获得资源名称
		String name = moudleAlias + ":" + methodNameAlias;

		if (!checkExistResource(url)) {

			Resource r = new Resource();
			r.setName(name);
			r.setUrl(url);
			r.setController(moudle);

			resourceMapper.save(r);
		}

	}

	private boolean checkExistResource(String url) {
		List<Resource> list = resourceMapper.getByUrl(url);// baseDao.findByHql("FROM
															// Resource WHERE
															// name=?", qName);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public PageResult<Resource> queryPage(ResourceQuery query) {
		PageResult<Resource> pr = new PageResult<Resource>();
		List<Resource> rows = resourceMapper.queryPage(query);
		pr.setRows(rows);

		Long total = resourceMapper.queryCount(query);
		pr.setTotal(total);

		return pr;
	}

	@Override
	public void importPermission(String moudle) {
		try {
			// 获得模块控制器对应的字节码对象
			Class<?> clz = Class.forName(moudle);

			// 获得控制器方法
			com.wyl.crm.utils.Resource cResource = (com.wyl.crm.utils.Resource) clz
					.getAnnotation(com.wyl.crm.utils.Resource.class);
			// 如果有自定义（中文）名称，就用定义的，没有就用原始全类名
			String cName = cResource.value() != "" ? cResource.value() : clz.getName();

			// 扫描方法
			Method[] declaredMethods = clz.getMethods();
			for (Method method : declaredMethods) {
				// 验证打了资源标签的方法
				if (method.isAnnotationPresent(com.wyl.crm.utils.Resource.class)) {
					// 获得方法名称
					com.wyl.crm.utils.Resource mResource = method.getAnnotation(com.wyl.crm.utils.Resource.class);
					String mName = mResource.value() != "" ? mResource.value() : method.getName();
					// 根据注解内容，自动添加资源
					addPermission(moudle, cName, method.getName(), mName);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void addPermission(String moudle, String moudleAlias, String methodName, String methodNameAlias) {
		// 获得资源地址
		String resource = moudle + ":" + methodName;

		if (!checkExistPermission(resource)) {

			Permission permission = new Permission();
			permission.setName(methodNameAlias);
			permission.setResource(resource);
			permissionMapper.save(permission);
		}

		resource = moudle + ":" + "ALL";
		if (!checkExistPermission(resource)) {

			Permission permission = new Permission();
			permission.setName(moudleAlias);
			permission.setResource(resource);
			permissionMapper.save(permission);
		}

	}

	private boolean checkExistPermission(String resource) {
		Permission permission = permissionMapper.getPermissionByResourceName(resource);// baseDao.findByHql("FROM
		// Resource WHERE
		if (permission != null) {
			return true;
		}

		return false;
	}

}
