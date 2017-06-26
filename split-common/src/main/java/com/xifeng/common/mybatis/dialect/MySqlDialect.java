/**
 * Project Name:split-common <br>
 * File Name:MySqlDialect.java <br>
 * Package Name:com.xifeng.common.mybatis.dialect <br>
 * @author xiezbmf
 * Date:2017年6月22日上午11:29:52 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.common.mybatis.dialect;
/**
 * ClassName: MySqlDialect <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2017年6月22日上午11:29:52 <br>
 * @version
 * @since JDK 1.6
 */
public class MySqlDialect extends Dialect{
	
	protected static final String SQL_END_DELIMITER = ";";
	
	public String getLimitString(String sql, boolean hasOffset) {
		return MySqlPageHepler.getLimitString(sql,-1,-1);
	}

	public String getLimitString(String sql, int offset, int limit) {
		return MySqlPageHepler.getLimitString(sql, offset, limit);
	}

	public boolean supportsLimit() {
		return true;
	}

	@Override
	public String getCountString(String sql) {
		// TODO Auto-generated method stub
		return MySqlPageHepler.getCountString(sql);
	}

}

	