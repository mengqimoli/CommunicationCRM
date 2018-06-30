package com.wyl.crm.exception;

/**
 * 逻辑异常（由用户误操作导致的异常）
 * 
 * @author Administrator
 *
 */
public class SystemException extends RuntimeException {

	public SystemException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SystemException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
