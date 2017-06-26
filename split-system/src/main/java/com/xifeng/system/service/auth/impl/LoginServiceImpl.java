/**
 * Project Name:split-system <br>
 * File Name:LoginServiceImpl.java <br>
 * Package Name:com.xifeng.system.service.auth.impl <br>
 * @author xiezbmf
 * Date:2017年6月26日下午2:26:33 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.system.service.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xifeng.common.base.BasicServiceModel;
import com.xifeng.common.base.ServiceParam;
import com.xifeng.system.component.user.SysUserComponent;
import com.xifeng.system.model.user.SysUser;
import com.xifeng.system.service.auth.LoginService;

/**
 * ClassName: LoginServiceImpl <br>
 * Description: 登陆服务接口实现类
 * @author xiezbmf
 * @Date 2017年6月26日下午2:26:33 <br>
 * @version
 * @since JDK 1.6
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Autowired
	private SysUserComponent sysUserComponent;

	@Override
	public BasicServiceModel login(ServiceParam sp) {
		String data = sp.getData();
		JSONObject json = JSONObject.parseObject(data);
		String userAccount = json.getString("userAccount");
		String accPwd = json.getString("accPwd");
		SysUser sysUser = sysUserComponent.querySysUserByAuth(userAccount, accPwd);
		BasicServiceModel bsm = new BasicServiceModel();
		bsm.setData(sysUser);
		return bsm;
	}
}

	