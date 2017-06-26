package com.xifeng.common.log;

public class CommonLogger extends BaseLogger {
	
	private static CommonLogger commonLogger = null;

	/**
	 * 私有构造函数
	 */
	private CommonLogger() {
	};
	
	/**
	 * getInstance:返回贷款模块的日志记录器实例 <br/>
	 *
	 * @author xiezbmf
	 * Date:2016年9月9日上午10:28:34 <br/>
	 * @return
	 */
	public static CommonLogger getInstance() {
		if (commonLogger == null) {
			commonLogger = new CommonLogger();
		}
		return commonLogger;
	}

	@Override
	protected String getLogName() {
		return "common";
	}

}
