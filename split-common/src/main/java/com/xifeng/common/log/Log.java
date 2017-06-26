package com.xifeng.common.log;

public interface Log {
	/**
	 * 记录debug级别的日志
	 * 
	 * @param message
	 */
	public void debug(String message);

	/**
	 * 记录debug级别的日志
	 * 
	 * @param message
	 * @param e
	 */
	public void debug(String message, Exception e);

	/**
	 * 记录debug级别的日志
	 * 
	 * @param messages
	 */
	public void debug(String[] messages);
	

	/**
	 * 记录debug级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void debug(String[] messages, Exception e);
	
	/**
	 * 记录info级别的日志
	 * 
	 * @param message 日志信息
	 */
	public void info(String message);

	/**
	 * 记录info级别的日志
	 * 
	 * @param message
	 * @param e
	 */
	public void info(String message, Exception e);

	/**
	 * 记录info级别的日志
	 * 
	 * @param messages
	 */
	public void info(String[] messages);

	/**
	 * 记录info级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void info(String[] messages, Exception e);

	/**
	 * 记录warn级别的日志
	 * 
	 * @param message
	 */
	public void warn(String message);

	/**
	 * 记录warn级别的日志
	 * 
	 * @param message
	 * @param e
	 */
	public void warn(String message, Exception e);

	/**
	 * 记录warn级别的日志
	 * 
	 * @param messages
	 */
	public void warn(String[] messages);

	/**
	 * 记录warn级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void warn(String[] messages, Exception e);

	/**
	 * 记录error级别的日志
	 * 
	 * @param message
	 */
	public void error(String message);
	
	

	/**
	 * 记录error级别的日志
	 * 
	 * @param message
	 * @param e
	 */
	public void error(String message, Exception e);
	
	/**
	 * 记录error级别的日志
	 * 
	 * @param messages
	 */
	public void error(String[] messages);

	/**
	 * 记录error级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void error(String[] messages, Exception e);
}
