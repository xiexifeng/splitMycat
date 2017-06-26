/**
 * Project Name:split-common <br>
 * File Name:SpringApplicationContext.java <br>
 * Package Name:com.xifeng.common.spring <br>
 * @author xiezbmf
 * Date:2017年6月22日上午10:56:20 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * ClassName: SpringApplicationContext <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2017年6月22日上午10:56:20 <br>
 * @version
 * @since JDK 1.6
 */
public class SpringApplicationContext implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	@Override
	public final void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringApplicationContext.applicationContext = applicationContext;
	}
	
	public static Object getBean(String name) {
		if(applicationContext == null){
			return null;
		}
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		if(applicationContext == null){
			return null;
		}
		return applicationContext.getBean(clazz);
	}

}

	