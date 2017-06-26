package com.xifeng.common.tool;

import java.text.DecimalFormat;

public class TimeCount {
	private long sTime = 0;
	private long eTime = 0;
	private final static String LOG_TIME = " cost time ";

	/**
	 * 启动计时器
	 */
	public void start() {
		sTime = System.currentTimeMillis();
		eTime = sTime;
	}

	/**
	 * 停止计时器
	 */
	public void stop() {
		eTime = System.currentTimeMillis();
	}

	/**
	 * 获取时间，以数字型返回，单位为 ms
	 * 
	 * @return
	 */
	public long getTime() {
		return eTime - sTime;
	}

	/**
	 * 重设计时器
	 * 
	 * @return
	 */
	public void reset() {
		sTime = 0;
		eTime = 0;
	}

	/**
	 * 获取时间的字符串型
	 * 
	 * @return
	 */
	public String getTimeString() {
		long t = eTime - sTime;
		return String.valueOf(t);
	}

	/**
	 * 获取要打印日志的时间格式，以 time=30 ms格式返回
	 * 
	 * @return
	 */
	public String getLogString() {
		this.stop();
		long t = eTime - sTime;
		return String.format("%s%s ms", LOG_TIME,
				new DecimalFormat(",###").format(t));
	}
}
