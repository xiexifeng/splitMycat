package com.xifeng.common.log;

import org.apache.log4j.Logger;

import com.xifeng.common.util.StringUtil;

public abstract class BaseLogger implements Log {
	/** 日志级别：调试 */
	private static final int DEBUG = 1;
	/** 日志级别：调试 另加打印异常信息 */
	private static final int DEBUG_EXCEPTION = 11;
	/** 日志级别：普通 */
	private static final int INFO = 2;
	/** 日志级别：普通 另加打印异常信息 */
	private static final int INFO_EXCEPTION = 22;
	/** 日志级别：警告 */
	private static final int WARN = 3;
	/** 日志级别：警告 另加打印异常信息 */
	private static final int WARN_EXCEPTION = 33;
	/** 日志级别：错误 */
	private static final int ERROR = 4;
	/** 日志级别：错误 另加打印异常信息 */
	private static final int ERROR_EXCEPTION = 44;
	/** 消息所属和消息内容的分隔符 */
	private static final String MSG_SPLIT = " - ";
	/** 消息对的分隔符 */
	private static final String PROPERTIES_SPLIT = " | ";

	private Logger logger = null;
	private static final String CLASS_NAME = BaseLogger.class.getName();

	protected abstract String getLogName();

	protected Logger getLogger() {
		String logName = getLogName();
		if (logger == null) {
			if (!StringUtil.isEmpty(logName)) {
				logger = Logger.getLogger(logName);
			} else {
				logger = Logger.getRootLogger();
			}
		}
		return logger;
	}

	/**
	 * 返回实际打印日志的类名和行号
	 * 
	 * @param ste
	 * @return
	 */
	protected String getStackMsg(StackTraceElement[] ste) {
		if (ste == null)
			return null;

		for (int i = 0; i < ste.length; i++) {
			StackTraceElement s = ste[i];
			// 定位本类的堆栈
			if (CLASS_NAME.equals(s.getClassName())) {
				String result = ste[i + 1].toString();
				return result.substring(result.indexOf("("));
			}
		}

		return null;
	}

	
	private void log(int level, String message,StackTraceElement[] ste, Exception e) {
		message = getStackMsg(ste) + MSG_SPLIT + message;
		// 转入具体实现，此处为log4j，可以改为其他不同的日志实现。
		switch (level) {
			case INFO_EXCEPTION:
				getLogger().info(message, e);
				break;
			case DEBUG_EXCEPTION:
				getLogger().debug(message, e);
				break;
			case WARN_EXCEPTION:
				getLogger().warn(message, e);
				break;
			case ERROR_EXCEPTION:
				getLogger().error(message, e);
				break;
			default:
				getLogger().debug(message);
			}
	}
	
	private void log(int level, String message,StackTraceElement[] ste) {
		message = getStackMsg(ste) + MSG_SPLIT + message;
		// 转入具体实现，此处为log4j，可以改为其他不同的日志实现。
		switch (level) {
		case DEBUG:
			getLogger().debug(message);
			break;
		case INFO:
			getLogger().info(message);
			break;
		case WARN:
			getLogger().warn(message);
			break;
		case ERROR:
			getLogger().error(message);
			break;
		default:
			getLogger().debug(message);
		}
	}
	
	/**
	 * 把 String array 转换成按 "|"分隔的字符串
	 * 
	 * @param messages
	 *            日志信息
	 * @return
	 */
	private String changeArrayToString(String[] messages) {
		if (messages == null || messages.length == 0) {
			return null;
		}

		StringBuilder message = new StringBuilder();
		for (String msg : messages) {
			message.append(msg + PROPERTIES_SPLIT);
		}

		return message.subSequence(0, message.length() - 3).toString();
	}

	@Override
	public void debug(String message) {
		this.log(DEBUG, message,Thread.currentThread().getStackTrace());
	}

	@Override
	public void debug(String messages, Exception e) {
		this.log(DEBUG, messages,Thread.currentThread().getStackTrace(), e);
	}

	@Override
	public void debug(String[] messages) {
		this.log(DEBUG, changeArrayToString(messages),Thread.currentThread().getStackTrace());
	}

	@Override
	public void debug(String[] messages, Exception e) {
		this.log(DEBUG_EXCEPTION, changeArrayToString(messages), Thread.currentThread().getStackTrace(),e);
	}

	@Override
	public void info(String message) {
		this.log(INFO, message,Thread.currentThread().getStackTrace());
	}

	@Override
	public void info(String message, Exception e) {
		this.log(INFO_EXCEPTION, message,Thread.currentThread().getStackTrace(), e);

	}

	@Override
	public void info(String[] messages) {
		this.log(INFO, changeArrayToString(messages),Thread.currentThread().getStackTrace());
	}

	@Override
	public void info(String[] messages, Exception e) {
		this.log(INFO_EXCEPTION, changeArrayToString(messages), Thread.currentThread().getStackTrace(),e);
	}

	@Override
	public void warn(String message) {
		this.log(WARN, message,Thread.currentThread().getStackTrace());
	}

	@Override
	public void warn(String message, Exception e) {
		this.log(WARN_EXCEPTION, message,Thread.currentThread().getStackTrace(), e);
	}

	@Override
	public void warn(String[] messages) {

		this.log(WARN, changeArrayToString(messages),Thread.currentThread().getStackTrace());

	}

	@Override
	public void warn(String[] messages, Exception e) {

		this.log(WARN_EXCEPTION, changeArrayToString(messages),Thread.currentThread().getStackTrace(), e);
	}

	@Override
	public void error(String message) {
		this.log(ERROR, message,Thread.currentThread().getStackTrace());
	}


	@Override
	public void error(String message, Exception e) {

		this.log(ERROR_EXCEPTION, message,Thread.currentThread().getStackTrace(), e);

	}

	@Override
	public void error(String[] messages) {
		this.log(ERROR, changeArrayToString(messages),Thread.currentThread().getStackTrace());
	}

	@Override
	public void error(String[] messages, Exception e) {
		this.log(ERROR_EXCEPTION, changeArrayToString(messages),Thread.currentThread().getStackTrace(), e);

	}

}
