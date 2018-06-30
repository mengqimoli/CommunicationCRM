package com.wyl.crm.exception;

/**
 * 逻辑异常（由用户误操作导致的异常）
 * 
 * @author Administrator
 *
 */
public class LogicException extends RuntimeException {

	private Integer errorCode = -99; // 默认值

	public Integer getErrorCode() {
		return errorCode;
	}

	public LogicException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public LogicException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public LogicException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LogicException(String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
		// TODO Auto-generated constructor stub
	}

	public LogicException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
