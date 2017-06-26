/**
 * Project Name:split-system <br>
 * File Name:BaseTest.java <br>
 * Package Name:com.xifeng.system.test <br>
 * @author xiezbmf
 * Date:2017年6月22日上午10:41:46 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.system.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.alibaba.fastjson.JSON;
import com.xifeng.common.base.BasicServiceModel;
import com.xifeng.common.base.ServiceParam;
import com.xifeng.common.spring.SpringApplicationContext;
import com.xifeng.system.service.auth.LoginService;

/**
 * ClassName: BaseTest <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2017年6月22日上午10:41:46 <br>
 * @version
 * @since JDK 1.6
 */
@TransactionConfiguration(transactionManager = "tm_db_1", defaultRollback = false)
@ContextConfiguration(locations = {
        "classpath*:xml/split-system.xml"})
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	LoginService loginService ;//= (LoginService)SpringApplicationContext.getBean("loginService");
	@Test
	public void testHello(){
		System.out.println("hello test");
		ServiceParam sp = new ServiceParam();
		String data = "{\"userAccount\":\"111\",\"accPwd\":\"111\"}";
		sp.setData(data);
		BasicServiceModel bsm;
		try {
			bsm = loginService.login(sp);
			System.out.println(JSON.toJSONString(bsm));
		} catch (Exception e) {
			
				// TODO Auto-generated catch block
				e.printStackTrace();
				
		}
		
	}

}

	