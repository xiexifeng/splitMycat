/**
 * Project Name:split-common <br>
 * File Name:SqlSessionFactoryBeanUtil.java <br>
 * Package Name:com.xifeng.common.spring <br>
 * @author xiezbmf
 * Date:2017年6月22日上午10:56:45 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.common.spring;

import java.io.IOException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.NestedIOException;

/**
 * ClassName: SqlSessionFactoryBeanUtil <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2017年6月22日上午10:56:45 <br>
 * @version
 * @since JDK 1.6
 */
public class SqlSessionFactoryBeanUtil extends SqlSessionFactoryBean {
	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		try{
			return super.buildSqlSessionFactory();
		} catch(NestedIOException e){
			e.printStackTrace();
			System.exit(0);
		}finally{
			ErrorContext.instance().reset();
		}
		return null;
	
	}

}

	