package com.hontek.comm.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 根异常，指示本异常实例产生的根本原因.
	 */
	protected Throwable rootCause = null;
	@SuppressWarnings("unchecked")
	private Collection exceptions = new ArrayList();
	private String messageKey = null;
	private Object[] messageArgs = null;

	/**
	 * 构�?函数.
	 */
	public AppException() {
		super();
	}

	/**
	 * 构�?函数.
	 * 
	 * @param msg
	 *            异常信息.
	 */
	public AppException(String msg) {
		super(msg);
	}

	/**
	 * 构�?函数.
	 * 
	 * @param cause
	 *            引起本异常的异常.
	 */
	public AppException(Throwable cause) {
		this.rootCause = cause;
	}

	/**
	 * 构�?函数.
	 * 
	 * @param msg
	 *            异常信息.
	 * @param cause
	 *            引起本异常的异常.
	 */
	public AppException(String msg, Throwable cause) {
		super(msg);
		this.rootCause = cause;
	}

	/**
	 * 获得本异常实例所包含的多异常实例.
	 * 
	 * @return 本异常实例所包含的多异常实例.
	 */
	@SuppressWarnings("unchecked")
	public Collection getCollection() {
		return exceptions;
	}

	/**
	 * 增加�?��异常实例，这个异常实例包装在本异常实例中.
	 * 
	 * @param ex
	 *            新的异常实例.
	 */
	@SuppressWarnings("unchecked")
	public void addException(AppException ex) {
		exceptions.add(ex);
	}

	/**
	 * 设置异常消息的keyCode，主要为支持国际�?
	 * 
	 * @param key
	 *            异常消息的keyCode.
	 */
	public void setMessageKey(String key) {
		this.messageKey = key;
	}

	/**
	 * 获得异常的keyCode，主要为了支持国际化.
	 * 
	 * @return 异常消息的keyCode.
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * 设置异常消息的参数，主要为了支持国际�? 异常消息使用如下格式定义带参数的消息: {0} is larger than {1}.
	 * 
	 * @param args
	 *            异常消息的参数数�?
	 */
	public void setMessageArgs(Object[] args) {
		this.messageArgs = args;
	}

	/**
	 * 获得异常消息的参数，主要为了支持国际�?
	 * 
	 * @return 异常消息的参数数�?
	 */
	public Object[] getMessageArgs() {
		return messageArgs;
	}

	/**
	 * 设置根异�?
	 * 
	 * @param anException
	 *            根异�?
	 */
	public void setRootCause(Throwable anException) {
		rootCause = anException;
	}

	/**
	 * 获得根异�?
	 * 
	 * @return 根异�?
	 */
	public Throwable getRootCause() {
		return rootCause;
	}

	/**
	 * 将异常的栈信息打印到System.err，先打印本异常的栈信息，然后打印根异常的栈信�?
	 */
	public void printStackTrace() {
		printStackTrace(System.err);
	}

	/**
	 * 将异常的栈信息打印到输出流，先打印本异常的栈信息，然后打印根异常的栈信息.
	 * 
	 * @param outStream
	 *            输出�?
	 */
	public void printStackTrace(PrintStream outStream) {
		printStackTrace(new PrintWriter(outStream));
	}

	/**
	 * 将异常的栈信息打印到输出流，先打印本异常的栈信息，然后打印根异常的栈信息.
	 * 
	 * @param writer
	 *            输出�?
	 */
	public void printStackTrace(PrintWriter writer) {
		super.printStackTrace(writer);

		if (getRootCause() != null) {
			getRootCause().printStackTrace(writer);
		}
		writer.flush();
	}
}
