/**
 * Project Name:split-common <br>
 * File Name:PaginationInterceptor.java <br>
 * Package Name:com.xifeng.common.mybatis <br>
 * @author xiezbmf
 * Date:2017年6月22日上午11:24:42 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.common.mybatis.plugin;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import com.xifeng.common.constant.ReqStatusConst;
import com.xifeng.common.log.CommonLogger;
import com.xifeng.common.log.Log;
import com.xifeng.common.log.LogTemplate;
import com.xifeng.common.mybatis.dialect.Dialect;
import com.xifeng.common.mybatis.dialect.MySqlDialect;
import com.xifeng.common.mybatis.dialect.OracleDialect;

/**
 * ClassName: PaginationInterceptor <br>
 * Description: mybatis分页
 * @author xiezbmf
 * @Date 2017年6月22日上午11:24:42 <br>
 * @version
 * @since JDK 1.6
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PaginationInterceptor implements Interceptor{

	private final static Log logger = CommonLogger.getInstance();
	/**
     * 默认ObjectFactory
     */
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    /**
     * 默认ObjectWrapperFactory
     */
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    /**
     * 默认ReflectorFactory
     */
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
//		BoundSql boundSql = statementHandler.getBoundSql();
		//version 3.3.0之前用MetaObject.forObject(statementHandler)
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
		 // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
	    while (metaStatementHandler.hasGetter("h")) {
	        Object object = metaStatementHandler.getValue("h");
	        metaStatementHandler = MetaObject.forObject(object, 
	        		DEFAULT_OBJECT_FACTORY, 
	        		DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
	    }
	    // 分离最后一个代理对象的目标类
	    while (metaStatementHandler.hasGetter("target")) {
	        Object object = metaStatementHandler.getValue("target");
	        metaStatementHandler = MetaObject.forObject(object,
	        		DEFAULT_OBJECT_FACTORY, 
	        		DEFAULT_OBJECT_WRAPPER_FACTORY,
	        		DEFAULT_REFLECTOR_FACTORY);
	    }
		
		RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");
		if(rowBounds == null || rowBounds == RowBounds.DEFAULT){
			return invocation.proceed();
		}
		Configuration configuration = (Configuration)metaStatementHandler.getValue("delegate.configuration");
		Dialect.Type databaseType  = null;
		try{
			databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		} catch(Exception e){
			//ignore
			logger.error(String.format(LogTemplate.COMMON_SYS,"common:PaginationInterceptor",ReqStatusConst.FAIL,"get dialect property from sqlmap config failed"), e);
		}
		if(databaseType == null){
			throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : " + configuration.getVariables().getProperty("dialect"));
		}
		Dialect dialect = null;
		switch(databaseType){
			case MYSQL:
				dialect = new MySqlDialect();
				break;
			case ORACLE:
				dialect = new OracleDialect();
				break;
			default:
				dialect = new MySqlDialect();
				
		}
		
		String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");
		metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()) );
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET );
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT );
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

}

	