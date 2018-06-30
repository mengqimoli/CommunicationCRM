<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 页面跳转
	//response.sendRedirect("/index");
	request.getRequestDispatcher("/index.do").forward(request, response);
%>