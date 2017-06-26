/**
 * Project Name:split-system <br>
 * File Name:SysUserDao.java <br>
 * Package Name:com.xifeng.system.dao.user <br>
 * @author xiezbmf
 * Date:2017年6月26日下午2:25:05 <br>
 * Copyright (c) 2017, 深圳市彩付宝网络技术有限公司 All Rights Reserved.
 */

package com.xifeng.system.dao.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xifeng.system.model.user.SysUser;

/**
 * ClassName: SysUserDao <br>
 * Description: 系统用户表数据操作接口
 * @author xiezbmf
 * @Date 2017年6月26日下午2:25:05 <br>
 * @version
 * @since JDK 1.6
 */
@Repository("sysUserDao")
public interface SysUserDao {
	
	int saveSysUser(SysUser sysUser);
	
	List<SysUser> querySysUserByParams(Map<String,String> params);
}
	