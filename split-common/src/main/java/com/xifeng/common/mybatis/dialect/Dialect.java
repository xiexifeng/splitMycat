/**
 * Project Name:split-common <br>
 * File Name:Dialect.java <br>
 * Package Name:com.xifeng.common.mybatis.dialect <br>
 * @author xiezbmf
 * Date:2017年6月22日上午11:26:52 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.common.mybatis.dialect;
/**
 * ClassName: Dialect <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2017年6月22日上午11:26:52 <br>
 * @version
 * @since JDK 1.6
 */
public abstract class Dialect {

	public static enum Type{
		MYSQL,
		ORACLE,
		MSSQL
	}
	
	public abstract String getLimitString(String sql,int skipResults,int maxResults);
	
	public String getCountString(String sql){
		return null;
	}

}

	