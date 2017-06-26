/**
 * Project Name:split-system <br>
 * File Name:LoginService.java <br>
 * Package Name:com.xifeng.system.service.auth <br>
 * @author xiezbmf
 * Date:2017年6月26日下午2:26:13 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.system.service.auth;

import com.xifeng.common.base.BasicServiceModel;
import com.xifeng.common.base.ServiceParam;

/**
 * ClassName: LoginService <br>
 * Description: 登陆服务接口
 * @author xiezbmf
 * @Date 2017年6月26日下午2:26:13 <br>
 * @version
 * @since JDK 1.6
 */
public interface LoginService {
	public BasicServiceModel login(ServiceParam sp);
}

	