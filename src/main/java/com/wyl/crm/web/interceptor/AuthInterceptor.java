package com.wyl.crm.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.utils.UserContext;

public class AuthInterceptor implements HandlerInterceptor {

	public AuthInterceptor() {
		System.out.println("AuthInterceptor初始化。。。。");
	}

	public static final String LOGIN_PATH = "/checkLogin.do";
	public static final String goLogin_PATH = "/goLogin.do";
	public static final String goRegister_PATH = "/goRegister.do";
	public static final String register_PATH = "/register.do";
	public static final String goGetPassword_PATH = "/goGetPassword.do";
	public static final String getPassword_PATH = "/getPassword.do";
	public static final String checkUsername_PATH = "/checkUsername.do";
	public static final String sendEmailCode_PATH = "/sendEmailCode.do";

	/**
	 * 1.在调用控制器方法前，拦截
	 * 
	 * 返回值为false，代表拦截 返回值为true，代表放行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// System.out.println("AuthInterceptor....preHandle");
		/**
		 * 1、登陆验证
		 */
		// 获取session用户
		Employee user = UserContext.getUser();
		System.out.println("拦截器..." + request.getRequestURI());
		// 检查用户是否存在
		if (!LOGIN_PATH.equals(request.getRequestURI()) && !goLogin_PATH.equals(request.getRequestURI())
				&& !goRegister_PATH.equals(request.getRequestURI()) && !register_PATH.equals(request.getRequestURI())
				&& !goGetPassword_PATH.equals(request.getRequestURI())
				&& !getPassword_PATH.equals(request.getRequestURI())
				&& !checkUsername_PATH.equals(request.getRequestURI())
				&& !sendEmailCode_PATH.equals(request.getRequestURI())) {
			// 检查用户是否存在
			if (user == null) {
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				return false;
			} else {
				/**
				 * 2、 权限验证
				 */
				// 获取请求的资源地址
				// System.out.println(handler.getClass());
				if (handler instanceof HandlerMethod) {
					// 获取“拦截器上下文”对象
					HandlerMethod hm = (HandlerMethod) handler;
					// 获取控制器地址
					String controllerName = hm.getBeanType().getName();
					// 获取方法
					String methodName = hm.getMethod().getName();
					// 获取资源地址
					String resource = controllerName + ":" + methodName;

					// 检查用户是否可以访问资源
					if (!UserContext.checkUserPermissionByResource(resource)) {
						// 如果没有权限，拦截，提示！！
						response.sendRedirect("/data/noPermission.json");
						return false; // 拦截
					}
					// 检查用户是否可以访问资源，如果有权限，放行
				}
			}
		}

		return true;// 放行
	}

	/**
	 * 2.在调用控制器方法后，拦截（在生成视图之前）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// System.out.println("AuthInterceptor..postHandle.....");
		// request.setAttribute("userContext", new UserContext());

	}

	/**
	 * 3.在视图生成之后（后台所有所有逻辑都完成后）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("AuthInterceptor...afterCompletion.....");

	}

}
